package com.nazirman.proyekakhir.ui.kuis

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.AnimalApllication
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.data.kuis.getKuis
import com.nazirman.proyekakhir.databinding.FragmentQuestionsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

var score = 0
class KuisFragment : Fragment() {
    private var currentQuestionId = -1

    private var selectedAnswers = mutableMapOf<Int, String>()

    private lateinit var binding: FragmentQuestionsBinding

    private val originalOptionTextColor = Color.parseColor("#4A4A4A")
    private val questions: ArrayList<Kuis> = getKuis()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allOptions = arrayListOf(
            binding.tvOption1,
            binding.tvOption2,
            binding.tvOption3,
            binding.tvOption4
        )

        fun changeQuestion() {
            // Go to results screen if it's the end of questions Array
            if (currentQuestionId + 1 == questions.size) {
                val navController = findNavController(view)
                navController.navigate(R.id.action_navigation_soalkuis_to_result)
                return
            }
            currentQuestionId += 1

            val question = questions[currentQuestionId]

            binding.tvQuestion.text = question.soal
            binding.ivQuestion.setImageResource(question.image)

            binding.tvOption1.text = question.option1
            binding.tvOption2.text = question.option2
            binding.tvOption3.text = question.option3
            binding.tvOption4.text = question.option4
        }

        fun resetOptionsColor() {
            for (option in allOptions) {
                option.setTextColor(originalOptionTextColor)
                option.typeface = Typeface.DEFAULT
                option.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.default_option_border
                )
            }
        }

        // Add color changing listener in all options
        for (option in allOptions) {
            option.setOnClickListener {
                resetOptionsColor() // To prevent multi-selection

                option.setTextColor(Color.parseColor("#417EFF"))
                option.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.selected_option_border
                )
                selectedAnswers[currentQuestionId] = option.text.toString()
            }
        }
        // Initial question when user presses "Start quiz"
        changeQuestion()

        binding.btnAnswerSubmit.setOnClickListener {
            if (selectedAnswers.containsKey(currentQuestionId)) {
                // If this is the last question, calculate score
                if (currentQuestionId + 1 == questions.size) {
                    for ((questionIndex, answer) in selectedAnswers) {
                        println("${questionIndex.toString()} ${answer.toString()}")
                        if (questions[questionIndex].jawaban == answer) {
                            score += 1
                        }
                    }
                }
                // Change question and options
                changeQuestion()
                resetOptionsColor()
            }
        }

    }
}
