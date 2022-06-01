package com.shatokhin.heroesdota2.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shatokhin.heroesdota2.R
import com.shatokhin.heroesdota2.data.models.HeroJson
import com.shatokhin.heroesdota2.utilites.BASE_URL
import com.shatokhin.heroesdota2.utilites.PRIMARY_ATTR_AGILITY
import com.shatokhin.heroesdota2.utilites.PRIMARY_ATTR_INTELLECT
import com.shatokhin.heroesdota2.utilites.PRIMARY_ATTR_STRENGTH

class AdapterRvHeroes(private val clickListenerHero: ClickListenerHero): ListAdapter<HeroJson, AdapterRvHeroes.ViewHolderHero>(HeroesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHero {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_hero, parent, false)
        return ViewHolderHero(view, clickListenerHero)
    }

    override fun onBindViewHolder(holder: ViewHolderHero, position: Int) {
        holder.bind( getItem(position) )
    }

    class ViewHolderHero(itemView: View, private val clickListenerHero: ClickListenerHero): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.image)
        val iconPrimaryAttr = itemView.findViewById<ImageView>(R.id.iconPrimaryAttr)
        val name = itemView.findViewById<TextView>(R.id.name)

        fun bind(hero: HeroJson){
            val urlImage = BASE_URL + hero.img
            image.load(urlImage)

            when (hero.primaryAttr){
                PRIMARY_ATTR_AGILITY -> iconPrimaryAttr.setImageResource(R.drawable.hero_agility)
                PRIMARY_ATTR_STRENGTH -> iconPrimaryAttr.setImageResource(R.drawable.hero_strength)
                PRIMARY_ATTR_INTELLECT -> iconPrimaryAttr.setImageResource(R.drawable.hero_intelligence)
            }

            name.text = hero.name

            itemView.setOnClickListener {
                clickListenerHero.clickHero(hero)
            }
        }

    }
    interface ClickListenerHero{
        fun clickHero(hero: HeroJson)
    }
}

object HeroesDiffCallback : DiffUtil.ItemCallback<HeroJson>() {
    override fun areItemsTheSame(oldItem: HeroJson, newItem: HeroJson): Boolean {
        return oldItem.idItemJson == newItem.idItemJson
    }

    override fun areContentsTheSame(oldItem: HeroJson, newItem: HeroJson): Boolean {
        return oldItem == newItem
    }
}