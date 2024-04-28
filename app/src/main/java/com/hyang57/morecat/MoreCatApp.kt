package com.hyang57.morecat

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hyang57.morecat.facts.FactsResponse
import java.lang.reflect.Type

class MoreCatApp : Application() {

    companion object {

        private lateinit var instance: MoreCatApp

        var factsSample = FactsResponse(data = listOf("Read from Local"))
        var imagesSample: List<String> = listOf("https://cdn.shibe.online/cats/f2f84ec007bea508baec72bbb70a47c335522c9a.jpg")
        const val MEME_TEXT = "hello"
        const val MEME_API_PART_1 = "https://cataas.com/cat/says/"
        const val MEME_API_PART_2 = "?fontSize=70&fontColor="
        const val MEME_API_PART_2_SQUARE = "?fontSize=50&fontColor="
        const val DEFAULT_MEME_COLOR = "red"
        const val DEFAULT_COUNT= 30
    }

    override fun onCreate() {

        instance = this
        super.onCreate()

        // For loading data locally
        val gson = Gson()
        val jsonString =
            assets.open("facts.json").bufferedReader().use {
                it.readText()
            }
        factsSample = gson.fromJson(jsonString, FactsResponse::class.java)

        print(factsSample)
        val jsonString2 =
            assets.open("images.json").bufferedReader().use {
                it.readText()
            }
        val listType: Type = object : TypeToken<List<String>>() {}.type
        imagesSample = gson.fromJson(jsonString2, listType)
        print(imagesSample)
    }
}