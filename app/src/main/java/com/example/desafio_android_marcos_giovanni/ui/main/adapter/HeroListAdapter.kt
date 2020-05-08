package com.example.desafio_android_marcos_giovanni.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android_marcos_giovanni.databinding.HeroListItemBinding
import com.example.desafio_android_marcos_giovanni.model.Hero
import com.example.desafio_android_marcos_giovanni.ui.main.ListFragment
import com.squareup.picasso.Picasso

class HeroListAdapter(val listener: ListFragment.HeroSelectedListener) : PagedListAdapter<Hero, ViewHolder>(HeroDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = HeroListItemBinding.inflate(
            LayoutInflater.from(parent.context))
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding.root.setOnClickListener { listener.onHeroSelected(item) }
            holder.binding.heroListText.text = item.name
            Picasso.get().load(item.imageUrl).resize(200, 200).into(holder.binding.heroListImage)
        }
    }

    companion object {
        val HeroDiffUtil  = object : DiffUtil.ItemCallback<Hero>() {
            override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
                return oldItem.id == newItem.id
            }

        }

    }
}


class ViewHolder(val binding: HeroListItemBinding) : RecyclerView.ViewHolder(binding.root)