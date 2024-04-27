package com.hyang57.morecat.ui.screens

import androidx.lifecycle.ViewModel
import com.hyang57.morecat.MoreCatApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MemeViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(MemeUiState())
    val uiState: StateFlow<MemeUiState> = _uiState

    fun fetchMeme(text: String) {
        _uiState.value = _uiState.value.copy(
            text = text,
        )
    }

    fun updateColor(color: String){
        _uiState.value = _uiState.value.copy(
            color = color
        )
    }
}
data class MemeUiState(
    val text: String = MoreCatApp.MEME_TEXT,
    val color: String = MoreCatApp.DEFAULT_MEME_COLOR,
)
