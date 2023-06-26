package com.nazirman.proyekakhir.kuis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nazirman.proyekakhir.MainActivity
import com.nazirman.proyekakhir.R

import kotlinx.android.synthetic.main.fragment_results.*


class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_results)

        // Set name on result page
        tvNameResult.text = "Congratulations "
        tvScore.text = "Your score is $score/${getQuestions().size}"

        btnFinish.setOnClickListener {
            // Reset the score when the game finishes
            score = 0

            // Go to homepage
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java,
                )
            )

        }

    }
}
