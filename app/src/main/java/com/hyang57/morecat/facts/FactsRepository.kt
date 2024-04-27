package com.hyang57.morecat.facts

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FactsRepository {

    companion object {
        private const val BASE_URL = "https://meowfacts.herokuapp.com/"
        val factsApi: FactsApi by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return@lazy retrofit.create(FactsApi::class.java)
        }
    }

    fun fetchData(count:Int, onFailure: () -> Unit, onSuccess: (FactsResponse) -> Unit) {

        val request: Call<FactsResponse> = factsApi.fetchCatFacts(
            count = count,
        )

        request.enqueue(object : Callback<FactsResponse> {
            override fun onFailure(call: Call<FactsResponse>, t: Throwable) {
                onFailure()
            }

            override fun onResponse(
                call: Call<FactsResponse>,
                response: Response<FactsResponse>,
            ) {
                if (response.isSuccessful) {
                    val data: FactsResponse? = response.body()
                    data?.let {
                        onSuccess(it)
                    }
                }
            }
        })
    }
}
