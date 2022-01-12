package au.com.jacobdev.clockinapp.ui.screens.clock_in_out_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import au.com.jacobdev.clockinapp.ui.HeadingText
import au.com.jacobdev.clockinapp.R
import au.com.jacobdev.clockinapp.ui.BodyText
import au.com.jacobdev.clockinapp.ui.components.ImageCard
import au.com.jacobdev.clockinapp.ui.components.TextDialog
import au.com.jacobdev.clockinapp.ui.theme.BACKGROUND
import au.com.jacobdev.clockinapp.util.AppSettings.ENABLE_IMAGE_ON_CLOCK_IN
import au.com.jacobdev.clockinapp.util.AppSettings.ENABLE_IMAGE_ON_CLOCK_OUT
import au.com.jacobdev.clockinapp.util.UiEvent
import au.com.jacobdev.clockinapp.util.getDateAusFormatFromTimeStamp
import au.com.jacobdev.clockinapp.util.getTime12HFromTimeStamp
import kotlinx.coroutines.flow.collect

@Composable
fun ClockInOutScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (route: String, clearBackStack: Boolean) -> Unit,
    viewModel: ClockInOutScreenViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()

    val dialogShow = remember { mutableStateOf(false) }
    val dialogTitle = remember { mutableStateOf("") }
    val dialogMessage = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                is UiEvent.Navigate -> onNavigate(event.route, true)
                is UiEvent.ShowDialog -> {
                    dialogTitle.value = event.title
                    dialogMessage.value = event.message
                    dialogShow.value = true
                }
                UiEvent.DismissDialog -> {
                    dialogShow.value = false
                }
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            // .padding(16.dp)
            .fillMaxSize(),
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(BACKGROUND)
        ) {

            TextDialog(
                title = dialogTitle,
                message = dialogMessage,
                show = dialogShow,
                confirmButtonText = "OK",
                onConfirm = { viewModel.onEvent(ClockInOutEvent.OnDialogDismiss) },
                onDismiss = { viewModel.onEvent(ClockInOutEvent.OnDialogDismiss) },
            )

            HeadingText(
                text = "Clock In",
                modifier = Modifier.padding(20.dp)
            )

            BodyText(
                text = "Employee: ${viewModel.employee?.fullName()} is ${viewModel.status}",
                modifier = Modifier.padding(8.dp)
            )

            viewModel.clockInOut?.let {
                BodyText(
                    text = "Clocked in at ${getTime12HFromTimeStamp(it.timestampIn)} on ${
                        getDateAusFormatFromTimeStamp(
                            it.timestampIn
                        )
                    }",
                    modifier = Modifier.padding(16.dp)
                )
            }

            var show = false
            if (viewModel.employee?.isClockedIn() == true && ENABLE_IMAGE_ON_CLOCK_IN) {
                show = true
            } else if (viewModel.employee?.isClockedIn() == false && ENABLE_IMAGE_ON_CLOCK_OUT) {
                show = true
            }

            if (show) {
                Button(onClick = {
                    // TODO
                }) {
                    Text(text = "Take Picture")
                }

                val painter = painterResource(id = R.drawable.placeholder_image)

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.6f)
                ) {
                    ImageCard(
                        painter = painter
                    )
                }
            }


            Button(onClick = {
                viewModel.onEvent(ClockInOutEvent.OnClockInOut)
            }) {
                Text(
                    text = "Clock In/Out"
                )
            }
        }
    }
}