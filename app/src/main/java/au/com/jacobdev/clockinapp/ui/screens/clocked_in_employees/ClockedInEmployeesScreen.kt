package au.com.jacobdev.clockinapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
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
import au.com.jacobdev.clockinapp.data.in_memory_test.TestDataRepo
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.ui.HeadingText
import au.com.jacobdev.clockinapp.ui.theme.BACKGROUND


@Preview
@Composable
fun ClockedInEmployeesScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (Employee) -> Unit = {},
) {
    val scrollState = rememberScrollState()
    var textFieldState by remember {
        mutableStateOf("")
    }


    val employees by remember {
        mutableStateOf(TestDataRepo.EMPLOYEES.filter { it.lastClockInOutId != null })
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
            modifier = Modifier.padding(vertical = 30.dp)
        ) {

            HeadingText(text = "Clocked in Employees")

        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(.8f)
                .padding(20.dp)
                .border(1.dp, Color.Black)
                .verticalScroll(state = scrollState)
        ) {
            Column {

                for (employee in employees) {
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