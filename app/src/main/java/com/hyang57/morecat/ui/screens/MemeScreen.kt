package com.hyang57.morecat.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hyang57.morecat.MoreCatApp
import com.hyang57.morecat.ui.TextBar
import com.hyang57.morecat.ui.theme.MoreCatTheme

@Composable
fun MemeScreen(
    modifier: Modifier = Modifier,
    memeUiState: MemeUiState,
    onText: (String) -> Unit,
) {
    val text by remember { mutableStateOf(MoreCatApp.MEME_TEXT) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            TextBar(
                text = text,
                onText = {
                    onText(it)
                }
            )
        }
        Spacer(Modifier.width(70.dp))
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(MoreCatApp.MEME_API_PART_1 + memeUiState.text + MoreCatApp.MEME_API_PART_2 + memeUiState.color)
                    .crossfade(true)
                    .build(),
                contentDescription = "meme",
                modifier = Modifier.size(500.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MemeScreenPreview() {
    MoreCatTheme {
        MemeScreen(
            modifier = Modifier.fillMaxSize(),
            memeUiState = MemeUiState(),
            onText = {},
        )
    }
}