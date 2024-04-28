package com.hyang57.morecat.ui.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hyang57.morecat.R
import com.hyang57.morecat.ui.theme.MoreCatTheme

@Composable
fun OptionsDialog(
    // option switch boolean
    readLocal: MutableState<Boolean>,
    monoMeme: MutableState<Boolean>,
    squareMeme: MutableState<Boolean>,
    fenwickFont: MutableState<Boolean>,
    onDone: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Load Data Locally
                Row {
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.read_local),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Switch(
                        modifier = Modifier
                            .weight(1f),
                        checked = readLocal.value,
                        onCheckedChange = { isChecked ->
                            readLocal.value = isChecked
                        })
                }

                //  Mono Meme
                Row {
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.mono_meme),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Switch(
                        modifier = Modifier
                            .weight(1f),
                        checked = monoMeme.value,
                        onCheckedChange = { isChecked ->
                            monoMeme.value = isChecked
                        })
                }

                //  Square Meme
                Row {
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.square_meme),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Switch(
                        modifier = Modifier
                            .weight(1f),
                        checked = squareMeme.value,
                        onCheckedChange = { isChecked ->
                            squareMeme.value = isChecked
                        })
                }

                //  Fenwick Font
                Row {
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = stringResource(R.string.fenwick_font),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Switch(
                        modifier = Modifier
                            .weight(1f),
                        checked = fenwickFont.value,
                        onCheckedChange = { isChecked ->
                            fenwickFont.value = isChecked
                        })
                }

                Spacer(Modifier.size(48.dp))

                Row {
                    Button(
                        onClick = { onDone() },
                    ) { Text(text = stringResource(R.string.done)) }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OptionsDialogPreview() {
    MoreCatTheme {
        val state = remember { mutableStateOf(false) }
        OptionsDialog(
            readLocal = state,
            monoMeme = remember { mutableStateOf(false) },
            squareMeme = remember { mutableStateOf(false) },
            fenwickFont = remember { mutableStateOf(false) },
            onDone = {},
        )
    }
}