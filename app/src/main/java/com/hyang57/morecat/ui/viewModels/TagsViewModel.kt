package com.hyang57.morecat.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hyang57.morecat.MoreCatApp
import com.hyang57.morecat.tags.TagsRepository
import com.hyang57.morecat.tags.TagsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TagsViewModel(private val tagsRepo: TagsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TagsUiState())
    val uiState: StateFlow<TagsUiState> = _uiState

    private fun updateState(tagsResponse: TagsResponse) {
        _uiState.value = _uiState.value.copy(
            tags = tagsResponse.tags,
            id = tagsResponse._id
        )
        Log.i("_uiState","$_uiState")
    }

    // Fetch facts and images
    fun fetchData(fromLocal: Boolean) {
        if (fromLocal) {
            val tagsSample = MoreCatApp.tagsSample
            updateState(tagsSample)
        } else {
            tagsRepo.fetchData( onFailure = {
                updateState(MoreCatApp.tagsSample)
                Log.w("Fetched tags failure","Fetched tags failure")
            }) {
                tagsResponse ->
                Thread.sleep(300)
                updateState(tagsResponse)
                Log.i("Fetched tags success","$tagsResponse")
            }
        }
    }
}

data class TagsUiState(
    val tags: List<String> = emptyList(),
    val id: String = "",
)