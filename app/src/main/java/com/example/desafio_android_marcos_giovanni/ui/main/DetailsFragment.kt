package com.example.desafio_android_marcos_giovanni.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.desafio_android_marcos_giovanni.R
import com.example.desafio_android_marcos_giovanni.databinding.FragmentDetailsBinding
import com.example.desafio_android_marcos_giovanni.model.Hero

class DetailsFragment(val listener: MoveToComicsListener) : Fragment() {

    lateinit var viewModel: MainViewModel
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
        viewModel = this.activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }!!
        populateView()
        return binding.root
    }

    private fun populateView() {
        hero = viewModel.selectedHero
        binding.detailName.setText(viewModel.selectedHero?.name)
        binding.detailDescription.setText(viewModel.selectedHero?.discription)
        binding.detailButton.setOnClickListener { listener.onButtonClick() }
    }

    companion object {
        fun newInstance(listener: MoveToComicsListener) = DetailsFragment(listener)
    }


}
