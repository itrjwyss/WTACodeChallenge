package com.aknowledgejourney.wtacodecallenge.data.remote.dto

import com.aknowledgejourney.wtacodecallenge.data.local.entity.CharacterEntity
import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("char_id")
    val charId: Long,
    val name: String,
    val birthday: String, //Can be Unknown
    val occupation: List<String>,
    val img: String,
    val status: String,
    val nickname: String,
    val appearance: List<Int>,
    val portrayed: String,
    val category: String,
    @SerializedName("better_call_saul_appearance")
    val betterCallSaul: List<String>
) {

    fun getEntity(): CharacterEntity {
        return CharacterEntity(
            charId,
            name,
            birthday,
            occupation,
            img,
            status,
            nickname,
            appearance,
            portrayed,
            category,
            betterCallSaul
        )
    }

}
