package com.hyang57.morecat.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hyang57.morecat.ui.viewModels.FactsUiState
import com.hyang57.morecat.ui.viewModels.MemeUiState

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    factsUiState: FactsUiState,
    memeUiState: MemeUiState,
    changeMemeColor: (String) -> Unit = {},
    updateCount: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(PaddingValues(16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(
                "What text color do you want for memes?               \n",
                style = MaterialTheme.typography.titleMedium
            )

            Row(modifier = Modifier.padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = memeUiState.color == "red",
                    onClick = { changeMemeColor("red") }
                )
                Text(
                    text = "Red",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = memeUiState.color == "white",
                    onClick = { changeMemeColor("white") }
                )
                Text(
                    text = "White",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = memeUiState.color == "black",
                    onClick = { changeMemeColor("black") }
                )
                Text(
                    text = "Black",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Column {
            Text(
                "\nHow many facts do you want in a single loading?\n",
                style = MaterialTheme.typography.titleMedium
            )

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = factsUiState.count == 1,
                    onClick = { updateCount(1) }
                )
                Text(
                    text = "1",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = factsUiState.count == 10,
                    onClick = { updateCount(10) }
                )
                Text(
                    text = "10",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = factsUiState.count == 30,
                    onClick = { updateCount(30) }
                )
                Text(
                    text = "30",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                RadioButton(
                    selected = factsUiState.count == 50,
                    onClick = { updateCount(50) }
                )
                Text(
                    text = "50",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

    }
}