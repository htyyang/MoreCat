package com.hyang57.morecat.images


import com.hyang57.morecat.facts.FactsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface ImagesApi {
    @GET("api/cats")
    fun fetchCatImages(
        @Query("count") count: Int
    ): Call<List<String>>
}