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
    Kuis.add(
        Kuis(
        5,
        "Hewan apa yang tinggal di hutan dan padang rumput di afrika dan asia?",
        R.drawable.hgajah,
        "Gajah",
        "Harimau",
        "Sapi",
        "Panda",
        "Gajah"
        )
    )
    Kuis.add(
        Kuis(
        6,
        "Hewan apa yang memiliki kebiasaan tidur yang lama?",
        R.drawable.kidguess,
        "Gajah",
        "Harimau",
        "Sapi",
        "Panda",
        "Gajah"
     )
    )
    Kuis.add(
        Kuis(
        7,
        "Hewan apakah ini yang memiliki garis-garis yang khas bewarna hitam dan putih?",
        R.drawable.hzebra,
        "Rusa",
        "Zebra",
        "Sapi",
        "Panda",
        "Zebra"
     )
    )
    Kuis.add(
        Kuis(
        8,
        "Hewan apa yang memiliki sayap berwarna-warni dan indah ?",
        R.drawable.hkupu2,
        "Gajah",
        "Zebra",
        "Sapi",
        "Kupu-kupu",
        "Kupu-kupu"
        )
    )

    Kuis.add(
        Kuis(
        9,
        "Hewan apa yang disebut kucing terbesar di dunia ?",
        R.drawable.hharimau,
        "Gajah",
        "Harimau",
        "Sapi",
        "Kucing",
        "Harimau"
        )
    )

    return Kuis
}
