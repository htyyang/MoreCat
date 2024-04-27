package com.hyang57.morecat.ui.screens

import androidx.lifecycle.ViewModel
import com.hyang57.morecat.MoreCatApp
import com.hyang57.morecat.MoreCatApp.Companion.factsSample
import com.hyang57.morecat.MoreCatApp.Companion.imagesSample
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
    }

    fun fetchData(fromFile: Boolean) {
        if (fromFile) {
            val factsSample = MoreCatApp.factsSample
            val imagesSample = MoreCatApp.imagesSample
            updateState(factsSample, imagesSample)
        } else {
            var facts = FactsResponse(data = listOf())
            var images: List<String> = listOf()
            factsRepo.fetchData(onFailure = {
                // Todo: add error handling
            }) { factsResponse ->
                facts = factsResponse
            }
            imagesRepo.fetchData(onFailure = {
                // Todo: add error handling
            }) { imagesList ->
                images = imagesList
            }
            updateState(facts, images)
        }
    }

}

data class FactsUiState(
    val facts: List<String> = listOf(),
    val images: List<String> = listOf(),
)
