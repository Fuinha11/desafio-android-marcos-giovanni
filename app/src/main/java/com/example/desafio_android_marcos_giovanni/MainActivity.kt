package com.example.desafio_android_marcos_giovanni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafio_android_marcos_giovanni.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
