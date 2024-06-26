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
import com.hyang57.morecat.tags.TagsRepository
import com.hyang57.morecat.ui.viewModels.FactsViewModel
import com.hyang57.morecat.ui.viewModels.MemeViewModel
import com.hyang57.morecat.ui.viewModels.TagsViewModel
import com.hyang57.morecat.ui.theme.MoreCatTheme

class FactsViewModelFactory(private val factsRepository: FactsRepository, private val imagesRepository: ImagesRepository ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FactsViewModel(factsRepo = factsRepository, imagesRepo = imagesRepository) as T
    }
}
class TagsViewModelFactory(private val tagsRepository: TagsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TagsViewModel(tagsRepository) as T
    }
}
class MainActivity : ComponentActivity() {

    private val factsRepository = FactsRepository()
    private val imagesRepository = ImagesRepository()
    private val tagsRepository = TagsRepository()

    private val viewModelFactory = FactsViewModelFactory(factsRepository, imagesRepository)
    private val tagsViewModelFactory = TagsViewModelFactory(tagsRepository = tagsRepository)
    private val factsViewModel: FactsViewModel by viewModels { viewModelFactory }
    private val tagsViewModel: TagsViewModel by viewModels { tagsViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val readLocal = remember { mutableStateOf(false) }
            val monoMeme = remember { mutableStateOf(false) }
            val squareMeme = remember { mutableStateOf(false) }
            val fenwickFont = remember { mutableStateOf(false) }
            factsViewModel.fetchData(fromLocal = readLocal.value)
            tagsViewModel.fetchData(fromLocal = readLocal.value)

            MoreCatTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoreCatNav(
                        factsViewModel = factsViewModel,
                        memeViewModel = MemeViewModel(),
                        tagsViewModel = tagsViewModel,
                        readLocal = readLocal,
                        squareMeme = squareMeme,
                        monoMeme = monoMeme,
                        fenwickFont = fenwickFont
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
        count = 30,
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
        count = 30,
        onFailure = { println("Failed to fetch images") },
        onSuccess = { urls ->
            println("Fetched images successfully")
            urls.forEach { url ->
                println(url)
            }
        }
    )

    val repository3 = TagsRepository()

    repository3.fetchData(
        onFailure = { println("Failed to fetch tags") },
        onSuccess = { response ->
            println("Fetched tags successfully")
            response.tags.forEach { tag ->
                println(tag)
            }
            response._id.forEach { id ->
                println(id)
            }
        }
    )
    MoreCatTheme {
        Greeting("Android")
    }
}