package com.hyang57.morecat.ui.screens


import android.util.Log
import androidx.lifecycle.ViewModel
import com.hyang57.morecat.MoreCatApp

import com.hyang57.morecat.facts.FactsRepository
import com.hyang57.morecat.facts.FactsResponse
import com.hyang57.morecat.images.ImagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FactsViewModel(private val factsRepo: FactsRepository,
                     private val imagesRepo: ImagesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FactsUiState())
    val uiState: StateFlow<FactsUiState> = _uiState

    private fun updateState(factsResponse: FactsResponse, imagesList: List<String>) {
        _uiState.value = _uiState.value.copy(
            facts = factsResponse.data,
            images = imagesList,
        )
        Log.i("_uiState","$_uiState")
    }

    fun fetchData(fromFile: Boolean) {
        if (fromFile) {
            val factsSample = MoreCatApp.factsSample
            val imagesSample = MoreCatApp.imagesSample
            updateState(factsSample, imagesSample)
        } else {
            var facts = FactsResponse(data = listOf("test"))
            var images: List<String> = listOf("https://cdn.shibe.online/cats/f2f84ec007bea508baec72bbb70a47c335522c9a.jpg")
            factsRepo.fetchData(onFailure = {
                // Todo: add error handling
                Log.i("facts api fail","facts api fail")
            }) { factsResponse ->
                facts = factsResponse
                Log.i("facts api success","$facts")
            }
            imagesRepo.fetchData(onFailure = {
                // Todo: add error handling
                Log.i("images api fail","images api fail")
            }) { imagesList ->
                images = imagesList
                updateState(facts, images)
                Log.i("images api success","$images")
            }
        }
    }

}

data class FactsUiState(
    val facts: List<String> = emptyList(),
    val images: List<String> = emptyList(),
)
