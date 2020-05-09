package com.example.desafio_android_marcos_giovanni.ui.herolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafio_android_marcos_giovanni.databinding.ListFragmentBinding
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.ui.herolist.adapter.HeroListAdapter

class ListFragment(val listener: HeroSelectedListener) : Fragment() {

    interface HeroSelectedListener {
        fun onHeroSelected(hero : Hero)
    }

    companion object {
        fun newInstance(listener: HeroSelectedListener) = ListFragment(listener)
    }

    private lateinit var viewModel: HeroListViewModel
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(HeroListViewModel::class.java) }!!
        val adapter = HeroListAdapter(listener)
        binding.heroRecyclerList.apply {
            layoutManager = GridLayoutManager(activity, 2)
            binding.heroRecyclerList.adapter = adapter
        }
        viewModel.heroList.observe(this, Observer<PagedList<Hero>> { t -> adapter.submitList(t)})
    }
}
