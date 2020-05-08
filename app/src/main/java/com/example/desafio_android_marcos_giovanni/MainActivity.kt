package com.example.desafio_android_marcos_giovanni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.ui.main.DetailsFragment
import com.example.desafio_android_marcos_giovanni.ui.main.ListFragment
import com.example.desafio_android_marcos_giovanni.ui.main.MainViewModel

class MainActivity : AppCompatActivity(), ListFragment.HeroSelectedListener,
    DetailsFragment.MoveToComicsListener {

    private lateinit var viewModel: MainViewModel
    val listFragment : ListFragment = ListFragment.newInstance(this)
    var onDetails = false

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame, listFragment)
                    .commitNow()
        }
    }

    private fun transitToDetails() {
        onDetails = true
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, DetailsFragment.newInstance(this))
            .commitNow()
    }

    private fun transitToList() {
        onDetails = false
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, listFragment)
            .commitNow()
    }

    override fun onHeroSelected(hero: Hero) {
        viewModel.selectedHero = hero
        transitToDetails()
    }

    override fun onButtonClick() {
        Toast.makeText(baseContext, "lol", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (onDetails)
            transitToList()
        else
            super.onBackPressed()
    }
}
