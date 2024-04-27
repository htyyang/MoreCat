package com.hyang57.morecat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyang57.morecat.facts.FactsRepository
import com.hyang57.morecat.images.ImagesRepository
import com.hyang57.morecat.ui.screens.FactsViewModel
import com.hyang57.morecat.ui.theme.MoreCatTheme

class FactsViewModelFactory(private val factsRepository: FactsRepository, private val imagesRepository: ImagesRepository ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FactsViewModel(factsRepo = factsRepository, imagesRepo = imagesRepository) as T
    }
}
class MainActivity : ComponentActivity() {

    private val factsRepository = FactsRepository()
    private val imagesRepository = ImagesRepository()

    private val viewModelFactory = FactsViewModelFactory(factsRepository, imagesRepository)
    private val factsViewModel: FactsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val readLocal = remember { mutableStateOf(true) }
            factsViewModel.fetchData(fromFile = readLocal.value)

            MoreCatTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoreCatNav(
                        factsViewModel = factsViewModel,
                        readLocal = readLocal,
                    ) {
                        factsViewModel.fetchData(readLocal.value)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val repository = FactsRepository()

    repository.fetchData(
        onFailure = { println("Failed to fetch data") },
        onSuccess = { response ->
            println("Fetched data successfully")
            response.data.forEach { fact ->
                println(fact)
            }
        }
    )
    val repository2 = ImagesRepository()

    repository2.fetchData(
        onFailure = { println("Failed to fetch images") },
        onSuccess = { urls ->
            println("Fetched images successfully")
            urls.forEach { url ->
                println(url)
            }
        }
    )
    MoreCatTheme {
        Greeting("Android")
    }
}