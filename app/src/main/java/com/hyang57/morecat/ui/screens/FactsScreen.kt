package com.hyang57.morecat.ui.screens

import android.util.Log

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.compose.foundation.lazy.items

import androidx.compose.ui.tooling.preview.Preview




import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.hyang57.morecat.ui.theme.MoreCatTheme


@Composable
fun FactsScreen( modifier: Modifier = Modifier,
                factsUiState: FactsUiState,
                ) {
    Box(
        modifier = modifier
    ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                itemsIndexed(
                    items = factsUiState.facts,
                    itemContent = { index, fact ->
                        FactItem(
                            fact = fact,
                            image = factsUiState.images[index]
                        )
                    }
                )
            }

    }
}

@Composable
fun FactItem(
    fact: String,
    image: String,
) {
    Log.i("MyActivity", "Image url $image")
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp),
    ) {
        val imgModifier = Modifier
            .size(120.dp)
        Row {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = imgModifier
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = fact,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(Modifier.width(30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FactsScreenPreview() {
    MoreCatTheme {
        Surface(tonalElevation = 5.dp) {
            FactsScreen(
                factsUiState = FactsUiState(
                    facts = listOf("Fact 1", "Fact 2", "Fact 3"),
                    images = listOf("https://cdn.shibe.online/cats/f2f84ec007bea508baec72bbb70a47c335522c9a.jpg",
                        "https://cdn.shibe.online/cats/1185ee8a74505d148b82dba0120fdc10d4817eca.jpg",
                        "https://cdn.shibe.online/cats/05d4511d380fad8387b023866220ed3ab8a183e8.jpg",)
                ),
            )
        }
    }
}