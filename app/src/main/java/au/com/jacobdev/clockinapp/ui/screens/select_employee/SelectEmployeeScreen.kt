package au.com.jacobdev.clockinapp.ui.screens.select_employee

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.ui.HeadingText
import au.com.jacobdev.clockinapp.ui.theme.BACKGROUND


@Preview
@Composable
fun SelectEmployeeScreen(
    onNavigate: (Employee) -> Unit = {},
    viewModel: SelectEmployeeViewModel = hiltViewModel(),
) {
    val employees = viewModel.employees.collectAsState(initial = emptyList())
    val scrollState = rememberScrollState()
    var textFieldState by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {

            HeadingText(text = "Select Employee")

        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(20.dp)
        ) {

            TextField(
                value = textFieldState,
                label = {
                    Text(text = "Employee Search")
                },
                onValueChange = {
                    viewModel.onEvent(SelectEmployeeEvent.SearchTextUpdated(it))
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .defaultMinSize(minHeight = 300.dp, minWidth = 200.dp)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(.8f)
                .padding(20.dp)
                .border(1.dp, Color.Black)
                .verticalScroll(state = scrollState)
        ) {
            Column(
                modifier = Modifier
            ) {

                for (employee in employees.value) {
                    Text(
                        text = employee.fullName(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onNavigate(employee)
                            }
                            .padding(vertical = 24.dp)
                    )
                }

            }

        }

    }
}
