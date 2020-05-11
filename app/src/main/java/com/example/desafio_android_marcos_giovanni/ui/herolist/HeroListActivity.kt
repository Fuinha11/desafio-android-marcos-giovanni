package com.example.desafio_android_marcos_giovanni.ui.herolist

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_android_marcos_giovanni.R
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.ui.comic.ComicsActivity


class HeroListActivity : AppCompatActivity(), ListFragment.HeroSelectedListener,
    DetailsFragment.MoveToComicsListener {

    private lateinit var detailFragment: DetailsFragment
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

    private fun performTransition(
        imageView: ImageView,
        previousFrag: Fragment,
        nextFragment: Fragment
    ) {
        // 1. Exit for Previous Fragment

        val exitFade = Fade()
        val FADE_DEFAULT_TIME = 500L
        val MOVE_DEFAULT_TIME = 500L
        exitFade.duration = FADE_DEFAULT_TIME
        previousFrag.exitTransition = exitFade

        // 2. Shared Elements Transition
        val enterTransitionSet = TransitionSet()
        enterTransitionSet.addTransition(
            TransitionInflater.from(this).inflateTransition(android.R.transition.move)
        )
        enterTransitionSet.duration = MOVE_DEFAULT_TIME
        enterTransitionSet.startDelay = FADE_DEFAULT_TIME
        nextFragment.sharedElementEnterTransition = enterTransitionSet

        // 3. Enter Transition for New Fragment
        val enterFade = Fade()
        enterFade.startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME
        enterFade.duration = FADE_DEFAULT_TIME
        nextFragment.enterTransition = enterFade

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.addSharedElement(imageView, imageView.transitionName)
        fragmentTransaction.replace(R.id.frame, nextFragment)
        fragmentTransaction.commit()
    }

    private fun transitToList() {
        onDetails = false
        performTransition(detailFragment.binding.detailImage, detailFragment, listFragment)
    }

    override fun onHeroSelected(hero: Hero, imageView: ImageView) {
        onDetails = true
        viewModel.selectedHero = hero
        detailFragment = DetailsFragment.newInstance(this)
        performTransition(imageView, listFragment, detailFragment)
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
