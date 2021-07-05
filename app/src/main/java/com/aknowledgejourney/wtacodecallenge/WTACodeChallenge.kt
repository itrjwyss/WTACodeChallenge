package com.aknowledgejourney.wtacodecallenge

import android.app.Application
import com.aknowledgejourney.wtacodecallenge.data.local.db.CharacterRoomDatabase
import com.aknowledgejourney.wtacodecallenge.domain.repository.CharacterRoomRepository

class WTACodeChallenge : Application() {

    private val characterDatabase by lazy { CharacterRoomDatabase.getDatabase(this) }
    val characterRepository by lazy { CharacterRoomRepository(characterDatabase.characterDao()) }

}