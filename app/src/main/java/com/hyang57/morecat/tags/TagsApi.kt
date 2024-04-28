package com.hyang57.morecat.tags

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TagsApi {
    @GET("cat")
    fun fetchCatTags(
        @Query("json") json: Boolean
    ): Call<TagsResponse>
}