package com.nazirman.proyekakhir.ui.kuis

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.AnimalApllication
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.databinding.FragmentQuestionsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

var score = 0
class KuisFragment : Fragment() {
    private var currentQuestionId = -1
    private var selectedAnswers = mutableMapOf<Int, String>()

    private lateinit var binding: FragmentQuestionsBinding
    private val viewModel: KuisViewModel by activityViewModels {
        ViewModelFactory(
            (activity?.application as AnimalApllication).database.kuisDao()
        )
    }


    private val originalOptionTextColor = Color.parseColor("#4A4A4A")
    private val questions: MutableLiveData<List<Kuis>> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        observeKuis()

        fun changeQuestion() {
            val currentQuestions = questions.value ?: return
            // Go to results screen if it's the end of questions Array
            if (currentQuestionId + 1 == currentQuestions.size) {
                startActivity(Intent(requireActivity(), ResultsFragment::class.java))
                return
            }
            currentQuestionId += 1

            val question = currentQuestions[currentQuestionId]

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

        // Add color changing listener to all options
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

        // Initial question when the user presses "Start quiz"
        changeQuestion()

        binding.btnAnswerSubmit.setOnClickListener {
            if (selectedAnswers.containsKey(currentQuestionId)) {
                // If this is the last question, calculate score
                if (currentQuestionId + 1 == questions.value?.size) {
                    val currentSelectedAnswers = selectedAnswers.toList()
                    val currentQuestions = questions.value ?: return@setOnClickListener
                    for ((questionIndex, answer) in currentSelectedAnswers) {
                        if (currentQuestions[questionIndex].jawaban == answer) {
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

    private fun observeKuis() {
        viewModel.getKuis().onEach { kuisList ->
            questions.value = kuisList
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Perform any cleanup or resource release operations here
    }
}
