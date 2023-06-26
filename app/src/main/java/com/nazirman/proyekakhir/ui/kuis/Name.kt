package com.nazirman.proyekakhir.ui.kuis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nazirman.proyekakhir.R
import kotlinx.android.synthetic.main.fragment_namequestion.*

var name = ""
class Name : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartQuiz.setOnClickListener {
            if (tvName.text.toString().isEmpty()) {
                tvNameParent.error = "Please provide a name"
            } else {
                name = tvName.text.toString()
                startActivity(
                    Intent(
                        this,
                        QuestionsActivity::class.java,
                    )
                )
            }
        }
    }
}