package com.aknowledgejourney.wtacodecallenge.presentation.character.list.adapater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.databinding.ItemCharacterBinding

class CharacterViewHolder(
    val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(
                ItemCharacterBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    fun bind(characterEntity: CharacterEntity) {
        binding.character = characterEntity
    }

}