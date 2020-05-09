package com.example.desafio_android_marcos_giovanni.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio_android_marcos_giovanni.R
import com.example.desafio_android_marcos_giovanni.ui.herolist.HeroListActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            val i = Intent(this, HeroListActivity::class.java)
            startActivity(i)
        }, 5000)
    }
}
