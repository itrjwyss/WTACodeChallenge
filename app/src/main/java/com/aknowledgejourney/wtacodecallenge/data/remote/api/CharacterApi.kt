package com.aknowledgejourney.wtacodecallenge.data.remote.api

import com.aknowledgejourney.wtacodecallenge.data.remote.dto.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("characters?")
    fun getCharacters(@Query("limit") limit: Int, @Query("offset") offset: Long): Call<List<CharacterDto>>

}