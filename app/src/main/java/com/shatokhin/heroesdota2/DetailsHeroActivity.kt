package com.shatokhin.heroesdota2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import coil.load
import com.shatokhin.heroesdota2.databinding.ActivityDetailsHeroBinding
import com.shatokhin.heroesdota2.databinding.ActivityMainBinding
import com.shatokhin.heroesdota2.domain.models.HeroParcelable
import com.shatokhin.heroesdota2.utilites.*

class DetailsHeroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = intent.getParcelableArrayListExtra<HeroParcelable>(MainActivity.EXTRA_HERO_PARCELABLE)

        val hero = list?.get(0)

        if (hero != null){
            setDataHero(hero)
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        }

    }

    private fun setDataHero(hero: HeroParcelable) {
        val urlImage = BASE_URL + hero.img
        binding.imageView.load(urlImage)

        when (hero.primaryAttr){
            PRIMARY_ATTR_AGILITY -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_agility)
            PRIMARY_ATTR_STRENGTH -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_strength)
            PRIMARY_ATTR_INTELLECT -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_intelligence)
        }

        binding.tvName.text = hero.name
        binding.tvHealth.text = hero.baseHealth.toString()
        binding.tvMana.text = hero.baseMana.toString()

        binding.tvValueAttackType.text = hero.attackType
        binding.tvValueRoles.text = hero.roles.toString()
        binding.tvValueBaseArmor.text = hero.baseArmor.toString()

        binding.tvValueBaseStrength.text = hero.baseStrength.toString()
        binding.tvValueBaseAgility.text = hero.baseAgility.toString()
        binding.tvValueBaseIntellect.text = hero.baseIntellect.toString()

    }
}