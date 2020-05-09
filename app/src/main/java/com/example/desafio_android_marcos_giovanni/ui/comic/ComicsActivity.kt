package com.example.desafio_android_marcos_giovanni.ui.comic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_android_marcos_giovanni.databinding.ActivityComicsBinding
import com.example.desafio_android_marcos_giovanni.model.Comics
import com.example.desafio_android_marcos_giovanni.util.toCurrency
import com.squareup.picasso.Picasso

class ComicsActivity : AppCompatActivity() {

    lateinit var viewModel: ComicsViewModel
    lateinit var binding: ActivityComicsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var heroId = intent.getIntExtra(INTENT_HERO_ID, 0)
        viewModel = ViewModelProviders.of(this).get(ComicsViewModel::class.java)
        viewModel.setHeroId(heroId)
        viewModel.mostExpensiveComics.observe(this, Observer { c -> updateUi(c) })
    }

    private fun updateUi(comics: Comics) {
        binding.comicTitle.text = comics.title
        binding.comicPrice.text = comics.price.toCurrency()
        binding.comicDescription.text = comics.description
        Picasso.get().load(comics.imageUrl).into(binding.comicCover)
    }

    companion object {
        val INTENT_HERO_ID = "intent_hero_id"
    }
}
