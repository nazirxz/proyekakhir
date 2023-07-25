package com.nazirman.proyekakhir.ui.kuis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.databinding.FragmentResultsBinding
class ResultFragment : Fragment() {
    private var currentQuestionNumber = 0 // Declare currentQuestionNumber here
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Retrieve the score from arguments
        var score = arguments?.getInt("score", 0) ?: 0

        // Display the score and appropriate message
        if (score <= 5) {
            binding.tvNameResult.text = "Sayang sekali, $name!"
            binding.tvScore.text = "Jumlah soal yang Anda jawab dengan benar kurang dari 5 dengan nilai $score/10 soal. Teruslah berlatih dan semangat!"
            binding.kuis.visibility = View.GONE
            binding.unhappy.visibility = View.VISIBLE
        } else {
            binding.tvNameResult.text = "Selamat, $name!"
            binding.tvScore.text = "Kamu berhasil menjawab $score/10 soal"
            binding.unhappy.visibility = View.GONE
            binding.kuis.visibility = View.VISIBLE
        }

        binding.btnFinish.setOnClickListener {
            // Reset the score and currentQuestionNumber when the game finishes
            score = 0
            currentQuestionNumber = 0

            // Go to homepage
            findNavController().navigate(R.id.action_navigation_result_to_main)
        }

        return view
    }
}