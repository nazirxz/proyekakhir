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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set name on result page
        binding.tvNameResult.text = "Selamat !"
        binding.tvScore.text = "Kamu berhasil menjawab dari $score/5 soal"

        binding.btnFinish.setOnClickListener {
            // Reset the score when the game finishes
            score = 0

            // Go to homepage
            findNavController().navigate(R.id.action_navigation_result_to_main)
        }

        return view
    }
}
