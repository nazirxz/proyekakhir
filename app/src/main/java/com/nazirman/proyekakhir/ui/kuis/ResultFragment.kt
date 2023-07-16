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
        if (score <= 5 ) {
            binding.tvNameResult.text = "Sayang sekali! "
            binding.tvScore.text = "Jumlah soal yang Anda jawab dengan benar kurang dari 5. Teruslah berlatih dan semangat!"
            binding.kuis.visibility = View.GONE
            binding.unhappy.visibility = View.VISIBLE
        } else {
            binding.tvNameResult.text = "Selamat !"
            binding.tvScore.text = "Kamu berhasil menjawab dari $score/10 soal"
            binding.unhappy.visibility = View.GONE
            binding.kuis.visibility = View.VISIBLE
        }

        binding.btnFinish.setOnClickListener {
            // Reset the score when the game finishes
            score = 0

            // Go to homepage
            findNavController().navigate(R.id.action_navigation_result_to_main)
        }

        return view
    }
}
