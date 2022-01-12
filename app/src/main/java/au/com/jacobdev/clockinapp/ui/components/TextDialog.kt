package au.com.jacobdev.clockinapp.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


/*
 * Alert Dialog: https://foso.github.io/Jetpack-Compose-Playground/material/alertdialog/
 */

@Composable
fun TextDialog(
    title: MutableState<String>,
    message: MutableState<String>,
    content: @Composable (() -> Unit)? = null,
    show: MutableState<Boolean>,
    confirmButtonText: String = "",
    onConfirm: () -> Unit = {},
    dismissButtonText: String = "",
    onDismiss: () -> Unit = {},
) {
    MaterialTheme {
        if (show.value) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    show.value = false
                },
                title = {
                    Text(text = title.value)
                },
                text = content ?: { Text(message.value) },
                confirmButton = if (confirmButtonText.isNotBlank()) {
                    {
                        Button(
                            onClick = {
                                show.value = false
                                onConfirm()
                            }) {
                            Text(confirmButtonText)
                        }
                    }
                } else {
                    { }
                },
                dismissButton = if (dismissButtonText.isNotBlank()) {
                    {
                        Button(
                            onClick = {
                                show.value = false
                                onDismiss()
                            }) {
                            Text(dismissButtonText)
                        }
                    }
                } else {
                    { }
                }
            )
        }
    }
}