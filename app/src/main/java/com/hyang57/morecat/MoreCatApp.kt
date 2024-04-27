package com.hyang57.morecat

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hyang57.morecat.facts.FactsResponse
import java.lang.reflect.Type

class MoreCatApp : Application() {

    companion object {

        private lateinit var instance: MoreCatApp

        lateinit var factsSample: FactsResponse
        lateinit var imagesSample: List<String>
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

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