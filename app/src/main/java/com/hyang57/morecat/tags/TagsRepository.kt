package com.hyang57.morecat.tags

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hyang57.morecat.facts.FactsApi
import com.hyang57.morecat.facts.FactsRepository.Companion.factsApi
import com.hyang57.morecat.facts.FactsResponse
import com.hyang57.morecat.tags.TagsResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TagsRepository {

    companion object {
        private const val BASE_URL = "https://cataas.com/"
        val tagsApi: TagsApi by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(TagsApi::class.java)
        }
    }

    fun fetchData(onFailure: () -> Unit, onSuccess: (TagsResponse) -> Unit) {

        val request: Call<TagsResponse> = tagsApi.fetchCatTags(
            json = true
        )

        request.enqueue(object : Callback<TagsResponse> {
            override fun onFailure(call: Call<TagsResponse>, t: Throwable) {
                println("Failed to fetch tags due to an error: ${t.message}")
                t.printStackTrace()
                onFailure()
            }

            override fun onResponse(
                call: Call<TagsResponse>,
                response: Response<TagsResponse>,
            ) {
                if (response.isSuccessful) {
                    val data: TagsResponse? = response.body()
                    data?.let {
                        onSuccess(it)
                    }
                }
            }
        })
    }
}