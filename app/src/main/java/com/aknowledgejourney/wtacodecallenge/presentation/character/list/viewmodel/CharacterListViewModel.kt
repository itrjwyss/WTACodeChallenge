package com.aknowledgejourney.wtacodecallenge.presentation.character.list.viewmodel

import androidx.lifecycle.*
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.data.remote.dto.CharacterDto
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRoomRepository
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRemoteRepository
import com.aknowledgejourney.wtacodecallenge.util.RepositoryStatus
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val roomRepository: CharacterRoomRepository,
    private val characterRemoteRepository: CharacterRemoteRepository
) : ViewModel() {

    val serviceStatus = MutableLiveData<RepositoryStatus>()

    val characterList: LiveData<List<CharacterEntity>> = roomRepository.allCharacters.asLiveData()

    fun update(characterEntity: CharacterEntity) = viewModelScope.launch {
        roomRepository.update(characterEntity)
    }

    private fun insert(characterEntity: CharacterEntity) = viewModelScope.launch {
        roomRepository.insert(characterEntity)
    }

    fun loadData(offset: Long) {
        characterRemoteRepository.getCharacters(20, offset, ::updateServiceStatus, ::processCharacters)
    }

    private fun updateServiceStatus(status: RepositoryStatus) {
        serviceStatus.value = status
    }

    private fun processCharacters(list: List<CharacterDto>) {
        if (list.isEmpty()) {
            serviceStatus.value = RepositoryStatus.COMPLETE
        } else {
            list.forEach {
                insert(it.getEntity())
            }

            loadData(list[list.size - 1].charId)
        }
    }

}