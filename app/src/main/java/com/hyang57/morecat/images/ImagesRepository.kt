package com.hyang57.morecat.images


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesRepository {

    companion object {
        private const val BASE_URL = "https://shibe.online/"
        val imagesApi: ImagesApi by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(ImagesApi::class.java)
        }
    }

    fun fetchData(onFailure: () -> Unit, onSuccess: (List<String>) -> Unit) {

        val request = imagesApi.fetchCatImages(
            30,
        )

        request.enqueue(object : Callback<List<String>> {
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                println("Failed to fetch images due to an error: ${t.message}")
                t.printStackTrace()  // Print the full stack trace to understand the exception
                onFailure()
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onFailure()
                } else {
                    onFailure()
                }
            }
        })
    }
}
