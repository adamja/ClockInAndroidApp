package au.com.jacobdev.clockinapp.ui.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import au.com.jacobdev.clockinapp.ui.theme.BACKGROUND
import au.com.jacobdev.clockinapp.util.Routes

@Composable
fun StartScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: StartScreenViewModel = hiltViewModel(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(BACKGROUND)
    ) {

//        Button(
//            onClick = { onNavigate(Routes.LOGIN_DIALOG) },
//            modifier = Modifier.padding(vertical = 16.dp)
//        ) {
//            Text(text = "Login Dialog")
//        }

        Text(
            text = viewModel.time,
            style = TextStyle(
                fontSize = 70.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(vertical = 30.dp)
            )


        Button(
            onClick = { onNavigate(Routes.SELECT_EMPLOYEE) },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "START / FINISH", style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            ))
        }


        Button(
            onClick = { onNavigate(Routes.CLOCKED_IN_EMPLOYEES) },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "Active Employees", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ))
        }

        Button(
            onClick = { onNavigate(Routes.CLOCK_IN_OUT_HISTORY) },
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "History", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ))
        }

    }
}