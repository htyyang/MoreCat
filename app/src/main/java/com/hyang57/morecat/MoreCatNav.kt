package com.hyang57.morecat

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hyang57.morecat.MoreCatApp.Companion.factsSample
import com.hyang57.morecat.facts.FactsRepository
import com.hyang57.morecat.images.ImagesRepository
import com.hyang57.morecat.ui.ReadLocalDialog
import com.hyang57.morecat.ui.screens.FactsScreen
import com.hyang57.morecat.ui.screens.FactsViewModel
import com.hyang57.morecat.ui.screens.MemeScreen
import com.hyang57.morecat.ui.screens.MemeViewModel
import com.hyang57.morecat.ui.screens.SettingsScreen

object Route {
    const val FACTS = "Facts"
    const val MEME = "Meme"
    const val SETTINGS = "Settings"
}

data class Destination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val textId: Int,
)

val DESTINATIONS = listOf(
    Destination(
        route = Route.FACTS,
        selectedIcon = Icons.Default.Menu,
        unselectedIcon = Icons.Default.Menu,
        textId = R.string.title_facts
    ),
    Destination(
        route = Route.MEME,
        selectedIcon = Icons.Default.Face,
        unselectedIcon = Icons.Default.Face,
        textId = R.string.title_meme
    ),
    Destination(
        route = Route.SETTINGS,
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        textId = R.string.title_settings
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreCatNav(
    factsViewModel: FactsViewModel,
    memeViewModel: MemeViewModel,
    readLocal: MutableState<Boolean>,
    refresh: () -> Unit,
) {
    val destination = remember { mutableStateOf(Route.FACTS) }
    var showReadLocalDialog by remember { mutableStateOf(false) }

    val factsUiState by factsViewModel.uiState.collectAsState()
    val memeUiState by memeViewModel.uiState.collectAsState()

    if (showReadLocalDialog) {
        ReadLocalDialog(
            readLocal = readLocal,
            onDone = {
                showReadLocalDialog = false
            },
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(
                        text = when (destination.value) {
                            Route.FACTS -> stringResource(id = R.string.title_facts)
                            Route.MEME -> stringResource(id = R.string.title_meme)
                            Route.SETTINGS -> stringResource(id = R.string.title_settings)
                            else -> ""
                        })
                },

                actions = {
                    IconButton(onClick = {
                        showReadLocalDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Build,
                            contentDescription = stringResource(id = R.string.title_settings)
                        )
                    }

                }
            )
        },

        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                DESTINATIONS.forEach { dest ->
                    NavigationBarItem(
                        label = { Text(text = stringResource(id = dest.textId)) },
                        selected = destination.value == dest.route,
                        onClick = { destination.value = dest.route },
                        icon = {
                            Icon(
                                imageVector = dest.selectedIcon,
                                contentDescription = stringResource(id = dest.textId)
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (destination.value) {
                Route.FACTS -> {
                    FactsScreen(
                        modifier = Modifier.fillMaxSize(),
                        factsUiState = factsUiState,
                        refresh = refresh
                    )
                    Log.i("factsUiState","$factsUiState")
                }

                Route.MEME -> {
                    MemeScreen(
                        modifier = Modifier.fillMaxSize(),
                        memeUiState = memeUiState,
                        onText = {
                            memeViewModel.fetchMeme(it)
                        },
                    )
                }

                Route.SETTINGS -> {
                    SettingsScreen(
                        modifier = Modifier.fillMaxSize(),
                        factsUiState = factsUiState,
                        memeUiState = memeUiState,
                        changeMemeColor = {memeViewModel.updateColor(it)},
                        updateCount = {factsViewModel.updateCount(it)}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreCatPreview() {
    MaterialTheme {
        val readLocal = remember { mutableStateOf(false) }  // Mock state for the preview

        MoreCatNav(
            readLocal = readLocal,
            factsViewModel = FactsViewModel(FactsRepository(), ImagesRepository()),
            memeViewModel = MemeViewModel(),
            refresh = {},
        )
    }
}