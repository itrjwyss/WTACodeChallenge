package com.aknowledgejourney.wtacodecallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM ${CharacterEntity.TABLE_NAME} ORDER BY ${CharacterEntity.COLUMN_FAV} DESC")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterEntity: CharacterEntity)

    @Query("UPDATE ${CharacterEntity.TABLE_NAME} SET ${CharacterEntity.COLUMN_FAV} = :favorite WHERE ${CharacterEntity.COLUMN_ID} = :charId")
    suspend fun update(favorite: Boolean, charId: Long)

}