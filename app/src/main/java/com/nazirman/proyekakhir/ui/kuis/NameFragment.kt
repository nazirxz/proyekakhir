package com.nazirman.proyekakhir.ui.kuis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.AnimalApllication
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.databinding.FragmentNamequestionBinding
import kotlinx.android.synthetic.main.fragment_namequestion.*

var name = ""
class NameFragment : Fragment() {
    private lateinit var binding: FragmentNamequestionBinding
//    private val viewModel: KuisViewModel by activityViewModels {
//        ViewModelFactory(
//            (activity?.application as AnimalApllication).database.kuisDao()
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamequestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartQuiz.setOnClickListener {
            if (binding.tvName.text.toString().isEmpty()) {
                binding.tvNameParent.error = "Masukkan nama anda"
            } else {
                name = binding.tvName.text.toString()

                addInitialKuisData()
                findNavController().navigate(R.id.action_navigation_kuis_to_soalkuis)
            }
        }
    }

    fun addInitialKuisData() {
        val kuisList = listOf(
            Kuis(
                0,
                "Apa nama hewan yang punya bulu lembut dan berkaki empat?",
                R.drawable.hkucing,
                "Kucing",
                "Ikan",
                "Ular",
                "Gajah",
                "Kucing"
            ),
            Kuis(
                1,
                "Apa yang dilakukan ayam saat pagi hari?",
                R.drawable.hayam,
                "Terbang",
                "Berenang",
                "Berkokok",
                "Serapan",
                "Berkokok"
            ),
            Kuis(
                2,
                "Apa yang dilakukan seekor burung saat ingin terbang?",
                R.drawable.hburung,
                "Melompat",
                "Berenang",
                "Berjalan",
                "Mengibaskan sayapnya",
                "Mengibaskan sayapnya"
            ),
            Kuis(
                3,
                "Bagaimana cara kucing berkomunikasi dengan manusia?",
                R.drawable.hkucing,
                "Menggonggong",
                "Menari",
                "Mencakar",
                "Berlari",
                "Menggonggong"
            ),
            Kuis(
                4,
                "Apa makanan kesukaan kelinci?",
                R.drawable.hkelinci,
                "Rumput",
                "Ikan",
                "Roti",
                "Daging",
                "Rumput"
            )
        )

//        viewModel.insertKuis(kuisList)

        Log.d("Data ",kuisList.toString())
    }
    override fun onDestroy() {
        super.onDestroy()
        // Perform any cleanup or resource release operations here
    }
}
