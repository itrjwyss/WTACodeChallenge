package com.aknowledgejourney.wtacodecallenge.presentation.character.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRoomRepository

class CharacterDetailViewModelFactory(
    private val roomRepository: CharacterRoomRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterDetailViewModel(roomRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}