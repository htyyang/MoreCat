package com.hyang57.morecat.facts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsApi {
    @GET("/")
    fun fetchCatFacts(
        @Query("count") count: Int
    ): Call<FactsResponse>
}