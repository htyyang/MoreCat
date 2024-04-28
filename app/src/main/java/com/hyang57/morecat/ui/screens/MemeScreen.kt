package com.hyang57.morecat.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hyang57.morecat.MoreCatApp
import com.hyang57.morecat.ui.parts.TextBar
import com.hyang57.morecat.ui.theme.MoreCatTheme
import com.hyang57.morecat.ui.viewModels.MemeUiState

@Composable
fun MemeScreen(
    modifier: Modifier = Modifier,
    monoMeme: Boolean,
    squareMeme: Boolean,
    memeUiState: MemeUiState,
    onText: (String) -> Unit,
) {

    val text by remember { mutableStateOf(MoreCatApp.MEME_TEXT) }

    // Animation
    val imageAlpha = remember { Animatable(0f) }
    val imageScale = remember { Animatable(0.5f) }
    LaunchedEffect(key1 = true) {
        imageAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 300)
        )
        imageScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
        )
    }

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

        // Meme in different options
        Row {
            if(!monoMeme && !squareMeme) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(MoreCatApp.MEME_API_PART_1 + memeUiState.text + MoreCatApp.MEME_API_PART_2 + memeUiState.color)
                        .build(),
                    contentDescription = "meme",
                    modifier = Modifier
                        .size(500.dp)
                        .graphicsLayer(alpha = imageAlpha.value,
                            scaleX = imageScale.value,
                            scaleY = imageScale.value,
                            )
                )
            }
            else if(monoMeme && !squareMeme){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(MoreCatApp.MEME_API_PART_1 + memeUiState.text + MoreCatApp.MEME_API_PART_2 + memeUiState.color + "&filter=mono")
                        .build(),
                    contentDescription = "meme",
                    modifier = Modifier
                        .size(500.dp)
                        .graphicsLayer(alpha = imageAlpha.value,
                            scaleX = imageScale.value,
                            scaleY = imageScale.value,
                        )
                )
            }
            else if(!monoMeme && squareMeme){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(MoreCatApp.MEME_API_PART_1 + memeUiState.text + MoreCatApp.MEME_API_PART_2_SQUARE + memeUiState.color + "&type=square")
                        .build(),
                    contentDescription = "meme",
                    modifier = Modifier
                        .size(500.dp)
                        .graphicsLayer(alpha = imageAlpha.value,
                            scaleX = imageScale.value,
                            scaleY = imageScale.value,
                        )
                )
            }
            else{
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(MoreCatApp.MEME_API_PART_1 + memeUiState.text + MoreCatApp.MEME_API_PART_2_SQUARE + memeUiState.color + "&filter=mono&type=square")
                        .build(),
                    contentDescription = "meme",
                    modifier = Modifier
                        .size(500.dp)
                        .graphicsLayer(alpha = imageAlpha.value,
                            scaleX = imageScale.value,
                            scaleY = imageScale.value,
                        )
                )
            }
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
            monoMeme = false,
            squareMeme = false,
            onText = {},
        )
    }
}