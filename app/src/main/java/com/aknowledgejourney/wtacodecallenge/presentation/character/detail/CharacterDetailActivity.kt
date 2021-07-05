package com.aknowledgejourney.wtacodecallenge.presentation.character.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.aknowledgejourney.wtacodecallenge.R
import com.aknowledgejourney.wtacodecallenge.WTACodeChallenge
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.databinding.ActivityDetailBinding
import com.aknowledgejourney.wtacodecallenge.presentation.character.detail.viewmodel.CharacterDetailViewModel
import com.aknowledgejourney.wtacodecallenge.presentation.character.detail.viewmodel.CharacterDetailViewModelFactory
import com.aknowledgejourney.wtacodecallenge.presentation.character.list.CharacterListActivity

class CharacterDetailActivity : AppCompatActivity() {

    private val characterDetailViewModel: CharacterDetailViewModel by viewModels {
        CharacterDetailViewModelFactory((application as WTACodeChallenge).characterRepository)
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras?.containsKey(CharacterListActivity.EXTRA_CHARACTER) == true) {
            val character = intent.extras?.get(CharacterListActivity.EXTRA_CHARACTER) as CharacterEntity

            title = character.name
            binding.character = character

            binding.fab.setOnClickListener {
                characterDetailViewModel.update(character)
                character.favorite = !character.favorite
                setFacIcon(character.favorite)
            }

            setFacIcon(character.favorite)
        } else {
            finish()
        }
    }

    private fun setFacIcon(favorite: Boolean) {
        if (favorite) {
            binding.fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_24, theme))
        } else {
            binding.fab.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_favorite_border_24, theme))
        }
    }

}