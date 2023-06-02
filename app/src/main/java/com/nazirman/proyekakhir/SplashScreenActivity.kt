package com.nazirman.proyekakhir

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        window.setFlags(
            FULL_SCREEN_FLAG,
            FULL_SCREEN_FLAG
        )
        Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))

            finish()
        }, TIME)
    }

    companion object {
        private const val TIME = 3000L
        private const val FULL_SCREEN_FLAG = 1024
    }
}
