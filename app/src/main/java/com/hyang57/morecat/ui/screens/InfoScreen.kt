package com.hyang57.morecat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hyang57.morecat.BuildConfig
import com.hyang57.morecat.R
import com.hyang57.morecat.ui.theme.MoreCatTheme
import com.hyang57.morecat.ui.viewModels.FactsUiState
import com.hyang57.morecat.ui.viewModels.MemeUiState
import com.hyang57.morecat.ui.viewModels.TagsUiState
import java.io.File.separator

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    tagsUiState: TagsUiState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(6f)
                .padding(vertical = 30.dp)
        ) {
            Image(
                painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "icon",
                Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White)
                    .size(100.dp),
            )

            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.fenwick_outline),
                            Font(R.font.fenwick_outline, weight = FontWeight.Bold)
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp
                    )
                )
                Text(
                    text = BuildConfig.VERSION_NAME,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                )
                Text(
                    text = BuildConfig.language,
                    modifier = modifier,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily(
                            Font(R.font.fenwick_outline),
                            Font(R.font.fenwick_outline, weight = FontWeight.Bold)
                        ),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )

            }
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(2f)

        ){
            // Lucky Cat
            // Coil

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://cataas.com/cat/" + tagsUiState.id)
                    .crossfade(true)
                    .build(),
                contentDescription = "lucky cat",
                modifier = Modifier.fillMaxHeight(),

            )

        }
        Row(
            modifier = Modifier
                .weight(1.3f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.lucky) + " " + tagsUiState.tags.joinToString(separator = ", "),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            )
        }

        Row(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = BuildConfig.author,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 15.sp,
                )
            )
        }
        Row(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = BuildConfig.institution,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 15.sp,
                )
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = stringResource(id = R.string.copyright),
                modifier = modifier,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    MoreCatTheme {
        InfoScreen(tagsUiState = TagsUiState(tags = listOf("highway_hoop","upii"), id = "fewECg3UpBnPjxNr"))
    }
}