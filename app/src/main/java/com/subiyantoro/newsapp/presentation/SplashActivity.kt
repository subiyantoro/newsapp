package com.subiyantoro.newsapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.subiyantoro.newsapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    lateinit var runner: Runnable
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        delayToMainPage()
    }

    private fun delayToMainPage() {
        val mainIntent = Intent(this, MainActivity::class.java)
        runner = Runnable {
            kotlin.run {
                startActivity(mainIntent)
                finish()
            }
        }
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runner, 3000)
    }

    override fun onPause() {
        handler.removeCallbacks(runner)
        super.onPause()
    }

    override fun onResume() {
        handler.postDelayed(runner, 3000)
        super.onResume()
    }
}