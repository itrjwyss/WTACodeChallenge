package com.aknowledgejourney.wtacodecallenge.domain.repository

import android.util.Log
import com.aknowledgejourney.wtacodecallenge.data.remote.RetrofitHelper
import com.aknowledgejourney.wtacodecallenge.data.remote.api.CharacterApi
import com.aknowledgejourney.wtacodecallenge.data.remote.dto.CharacterDto
import com.aknowledgejourney.wtacodecallenge.util.RepositoryStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class CharacterRemoteRepository {

    companion object {
        private const val TAG = "TAG_Char_Service"
    }
    private val characterApi = RetrofitHelper.getRetrofit().create(CharacterApi::class.java)

    fun getCharacters(limit: Int, offset: Long, statusFunction: KFunction1<RepositoryStatus, Unit>,
                      dataFunction: KFunction1<List<CharacterDto>, Unit>) {
        characterApi.getCharacters(limit, offset).enqueue(object :
            Callback<List<CharacterDto>> {
            override fun onResponse(
                call: Call<List<CharacterDto>>,
                response: Response<List<CharacterDto>>
            ) {
                if (response.isSuccessful) {
                    dataFunction(response.body() ?: emptyList())
                } else {
                    statusFunction(RepositoryStatus.FAIL)
                    Log.e(TAG, "${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<CharacterDto>>, t: Throwable) {
                statusFunction(RepositoryStatus.FAIL)
                t.printStackTrace()
            }
        })
    }

}