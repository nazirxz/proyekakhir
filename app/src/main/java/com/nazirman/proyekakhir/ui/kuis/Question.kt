package com.nazirman.proyekakhir.ui.kuis

import com.nazirman.proyekakhir.R

data class Question(
    val id: Int,
    val text: String,
    val image: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: String
)


fun getQuestions(): ArrayList<Question> {
    val questions = ArrayList<Question>()

    questions.add(
        Question(
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
    questions.add(
        Question(
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

    questions.add(
        Question(
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

    questions.add(
        Question(
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
    questions.add(
        Question(
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

    return questions
}