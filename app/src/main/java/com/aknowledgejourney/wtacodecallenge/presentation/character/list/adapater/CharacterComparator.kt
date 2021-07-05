package com.aknowledgejourney.wtacodecallenge.presentation.character.list.adapater

import androidx.recyclerview.widget.DiffUtil
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity

class CharacterComparator : DiffUtil.ItemCallback<CharacterEntity>() {

    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        // Implementar equals
        return oldItem.nickname == oldItem.nickname
    }

}