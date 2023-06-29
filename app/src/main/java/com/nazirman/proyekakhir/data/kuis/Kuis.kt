package com.nazirman.proyekakhir.data.kuis

import com.nazirman.proyekakhir.R


data class Kuis(
    val id: Int,
    val soal: String,
    val image: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val jawaban: String
)


fun getKuis(): ArrayList<Kuis> {
    val Kuis = ArrayList<Kuis>()

    Kuis.add(
        Kuis(
            0,
            "Apa nama hewan yang punya bulu lembut dan berkaki empat?",
            R.drawable.hkucing,
            "Kucing",
            "Ikan",
            "Ular",
            "Gajah",
            "Kucing"
        )
    )
    Kuis.add(
        Kuis(
            1,
            "Apa yang dilakukan ayam saat pagi hari?",
            R.drawable.hayam,
            "Terbang",
            "Berenang",
            "Berkokok",
            "Serapan",
            "Berkokok"
        )
    )

    Kuis.add(
        Kuis(
            2,
            "Apa yang dilakukan seekor burung saat ingin terbang?",
            R.drawable.hburung,
            "Melompat",
            "Berenang",
            "Berjalan",
            "Mengibaskan sayapnya",
            "Mengibaskan sayapnya"
        )
    )

    Kuis.add(
        Kuis(
            3,
            "Bagaimana cara kucing berkomunikasi dengan manusia?",
            R.drawable.hkucing,
            "Menggonggong",
            "Menari",
            "Mencakar",
            "Berlari",
            "Menggonggong"
        )
    )
    Kuis.add(
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

    return Kuis
}
