package com.aknowledgejourney.wtacodecallenge.presentation.character.list.adapater

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity

class CharacterListAdapter(
    val showDetail: (CharacterEntity) -> Unit,
    val changeFav: (CharacterEntity) -> Unit
) : ListAdapter<CharacterEntity, CharacterViewHolder>(CharacterComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

        holder.binding.itemCharacterContainer.setOnClickListener {
            showDetail(character)
        }

        holder.binding.btnCharacterFav.setOnClickListener {
            changeFav(character)
        }
    }

}