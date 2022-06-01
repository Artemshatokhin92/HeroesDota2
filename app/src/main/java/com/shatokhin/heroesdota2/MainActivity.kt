package com.shatokhin.heroesdota2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shatokhin.heroesdota2.data.models.HeroJson
import com.shatokhin.heroesdota2.databinding.ActivityMainBinding
import com.shatokhin.heroesdota2.domain.models.HeroParcelable
import com.shatokhin.heroesdota2.presentation.adapters.AdapterRvHeroes
import com.shatokhin.heroesdota2.presentation.viewmodels.ViewModelListAllHeroes
import com.shatokhin.heroesdota2.presentation.viewmodels.ViewModelListAllHeroesFactory
import com.shatokhin.heroesdota2.utilites.TAG_FOR_LOGCAT

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_HERO_PARCELABLE = "EXTRA_HERO_PARCELABLE"
    }
    private val viewModelListAllHeroes: ViewModelListAllHeroes by viewModels { ViewModelListAllHeroesFactory() }
    private lateinit var adapterRvHeroes: AdapterRvHeroes
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // создание макета из XML с помощью binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycleView()

        findViewById<Button>(R.id.btnLoadHeroes).setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModelListAllHeroes.loadListAllHeroes()
        }

    }

    private fun initRecycleView() {
        val clickListener = object : AdapterRvHeroes.ClickListenerHero {
            override fun clickHero(hero: HeroJson) {
                val heroParcelable = HeroParcelable(hero.name, hero.primaryAttr, hero.attackType, hero.roles, hero.img,
                    hero.baseHealth, hero.baseMana, hero.baseArmor, hero.baseStrength, hero.baseAgility, hero.baseIntellect)

                val intent = Intent(this@MainActivity, DetailsHeroActivity::class.java)
                intent.putParcelableArrayListExtra(EXTRA_HERO_PARCELABLE, arrayListOf(heroParcelable))
                startActivity(intent)
            }
        }

        adapterRvHeroes = AdapterRvHeroes(clickListener)

        binding.rvHero.adapter = adapterRvHeroes

        val lmForRvHeroes = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.rvHero.layoutManager = lmForRvHeroes

        viewModelListAllHeroes.listHeroJson.observe(this) { listHeroJson ->
            binding.progressBar.visibility = View.GONE
            if (listHeroJson.isEmpty()) Toast.makeText(this, "Download error, please repeat later", Toast.LENGTH_LONG).show()
            adapterRvHeroes.submitList(listHeroJson)
        }
    }

}

