package com.aknowledgejourney.wtacodecallenge.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aknowledgejourney.wtacodecallenge.R
import com.aknowledgejourney.wtacodecallenge.data.local.dao.CharacterDao
import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.aknowledgejourney.wtacodecallenge.data.local.entity.ListIntTypeConverters
import com.aknowledgejourney.wtacodecallenge.data.local.entity.ListStringTypeConverters

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListStringTypeConverters::class, ListIntTypeConverters::class)
abstract class CharacterRoomDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): CharacterRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        CharacterRoomDatabase::class.java,
                        context.getString(R.string.room_database_name)
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}