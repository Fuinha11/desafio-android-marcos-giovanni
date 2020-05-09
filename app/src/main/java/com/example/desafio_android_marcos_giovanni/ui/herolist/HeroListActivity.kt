package com.example.desafio_android_marcos_giovanni.ui.herolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_android_marcos_giovanni.R
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.ui.comic.ComicsActivity

class HeroListActivity : AppCompatActivity(), ListFragment.HeroSelectedListener,
    DetailsFragment.MoveToComicsListener {

    private lateinit var viewModel: HeroListViewModel
    val listFragment : ListFragment = ListFragment.newInstance(this)
    var onDetails = false

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(HeroListViewModel::class.java)
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
        val i = Intent(this, ComicsActivity::class.java)
        i.putExtra(ComicsActivity.INTENT_HERO_ID, viewModel.selectedHero?.id)
        startActivity(i)
    }

    override fun onBackPressed() {
        if (onDetails)
            transitToList()
        else
            super.onBackPressed()
    }
}
