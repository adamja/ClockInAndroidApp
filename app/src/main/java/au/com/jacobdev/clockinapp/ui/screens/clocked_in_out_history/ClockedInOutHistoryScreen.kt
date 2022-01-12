package au.com.jacobdev.clockinapp.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.ui.HeadingText
import au.com.jacobdev.clockinapp.ui.screens.clocked_in_out_history.ClockedInOutHistoryViewModel
import au.com.jacobdev.clockinapp.ui.theme.BACKGROUND


@Preview
@Composable
fun ClockedInOutHistoryScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (Employee) -> Unit = {},
    viewModel: ClockedInOutHistoryViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

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
            modifier = Modifier.padding(vertical = 16.dp)
        ) {

            HeadingText(text = "Employee History")

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.historyItems) { historyItem ->
                Text(
                    text = historyItem.getTextDisplay(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            historyItem.employee?.let { onNavigate(it) }
                        }
                        .padding(vertical = 24.dp, horizontal = 8.dp)
                )
            }
        }
    }
}