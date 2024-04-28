package com.hyang57.morecat.ui.parts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyang57.morecat.R
import com.hyang57.morecat.ui.theme.MoreCatTheme

// Change meme's word
@Composable
fun TextBar(
    text: String,
    onText: (String) -> Unit,
) {
    var newText by remember { mutableStateOf(text) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
    ) {
        TextField(
            modifier = Modifier.weight(4f),
            value = newText,
            onValueChange = {
                newText = it
            })
        Spacer(Modifier.size(12.dp))
        Button(
            modifier = Modifier.weight(2f),
            onClick = { onText(newText) },
        ) {
            Text(stringResource(id = R.string.meme))
        }
    }
}

@Preview
@Composable
fun GoBarPreview() {
    MoreCatTheme {
        TextBar(
            text = "hello",
            onText = {},
        )
    }
}
