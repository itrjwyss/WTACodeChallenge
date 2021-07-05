package com.aknowledgejourney.wtacodecallenge.domain.repository

import androidx.annotation.WorkerThread
import com.aknowledgejourney.wtacodecallenge.data.local.dao.CharacterDao
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

class CharacterRoomRepository(
    private val characterDao: CharacterDao
) {

    val allCharacters: Flow<List<CharacterEntity>> = characterDao.getCharacters()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(characterEntity: CharacterEntity) {
        characterDao.insert(characterEntity)
    }

    suspend fun update(characterEntity: CharacterEntity) {
        characterDao.update(!characterEntity.favorite, characterEntity.charId)
    }

}