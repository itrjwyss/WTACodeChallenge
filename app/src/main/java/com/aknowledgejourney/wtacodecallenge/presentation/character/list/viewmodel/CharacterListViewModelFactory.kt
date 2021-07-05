package com.aknowledgejourney.wtacodecallenge.presentation.character.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRemoteRepository
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRoomRepository

class CharacterListViewModelFactory(
    private val roomRepository: CharacterRoomRepository,
    private val characterRemoteRepository: CharacterRemoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterListViewModel(roomRepository, characterRemoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}