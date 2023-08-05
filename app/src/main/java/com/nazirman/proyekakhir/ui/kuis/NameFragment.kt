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

                // Call the function to initialize the quiz data
                addInitialKuisData()

                // Navigate to KuisFragment
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
                "Mengeong",
                "Menari",
                "Mencakar",
                "Berlari",
                "Mengeong"
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
            ),
            Kuis(
                5,
                "Hewan apa yang tinggal di hutan dan padang rumput di afrika dan asia?",
                R.drawable.hgajah,
                "Gajah",
                "Harimau",
                "Sapi",
                "Panda",
                "Gajah"
            ),
            Kuis(
                6,
                "Hewan apa yang memiliki kebiasaan tidur yang lama?",
                R.drawable.kidguess,
                "Gajah",
                "Harimau",
                "Sapi",
                "Panda",
                "Gajah"
            ),
            Kuis(
                7,
                "Hewan apakah ini yang memiliki garis-garis yang khas bewarna hitam dan putih?",
                R.drawable.hzebra,
                "Rusa",
                "Zebra",
                "Sapi",
                "Panda",
                "Zebra"
            ),
            Kuis(
                8,
                "Hewan apa yang memiliki sayap berwarna-warni dan indah ?",
                R.drawable.hkupu2,
                "Gajah",
                "Zebra",
                "Sapi",
                "Kupu-kupu",
                "Kupu-kupu"
            ),

            Kuis(
                9,
                "Hewan apa yang disebut kucing terbesar di dunia ?",
                R.drawable.hharimau,
                "Gajah",
                "Harimau",
                "Sapi",
                "Kucing",
                "Harimau"
            ),
        )

        Log.d("Data ",kuisList.toString())
    }
    override fun onDestroy() {
        super.onDestroy()
        // Perform any cleanup or resource release operations here
    }
}
