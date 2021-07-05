package com.aknowledgejourney.wtacodecallenge.presentation.character.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRoomRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val roomRepository: CharacterRoomRepository
) : ViewModel() {

    fun update(characterEntity: CharacterEntity) = viewModelScope.launch {
        roomRepository.update(characterEntity)
    }

}