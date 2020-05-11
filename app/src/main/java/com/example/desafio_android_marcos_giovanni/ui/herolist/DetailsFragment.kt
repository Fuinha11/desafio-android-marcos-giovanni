package com.example.desafio_android_marcos_giovanni.ui.herolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.desafio_android_marcos_giovanni.databinding.FragmentDetailsBinding
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.squareup.picasso.Picasso

class DetailsFragment(val listener: MoveToComicsListener) : Fragment() {

    lateinit var viewModel: HeroListViewModel
    lateinit var binding: FragmentDetailsBinding
    var hero: Hero? = null

    interface MoveToComicsListener {
        fun onButtonClick()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        viewModel =
            this.activity?.let { ViewModelProviders.of(it).get(HeroListViewModel::class.java) }!!
        populateView()
        return binding.root
    }

    private fun populateView() {
        hero = viewModel.selectedHero
        binding.detailName.text = viewModel.selectedHero?.name
        binding.detailButton.setOnClickListener { listener.onButtonClick() }
        Picasso.get().load(hero?.imageUrl).fit().into(binding.detailImage)
        binding.detailImage.transitionName = hero?.name + hero?.id
        if (hero?.description?.isNotEmpty()!!)
            binding.detailDescription.text = viewModel.selectedHero?.description
    }

    companion object {
        fun newInstance(listener: MoveToComicsListener) = DetailsFragment(listener)
    }


}
