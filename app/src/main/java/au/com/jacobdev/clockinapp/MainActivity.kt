package au.com.jacobdev.clockinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import au.com.jacobdev.clockinapp.ui.component.ShowAlertDialog
import au.com.jacobdev.clockinapp.ui.screens.clock_in_out_screen.ClockInOutScreen
import au.com.jacobdev.clockinapp.ui.screens.ClockedInEmployeesScreen
import au.com.jacobdev.clockinapp.ui.screens.ClockedInOutHistoryScreen
import au.com.jacobdev.clockinapp.ui.screens.select_employee.SelectEmployeeScreen
import au.com.jacobdev.clockinapp.ui.screens.start.StartScreen
import au.com.jacobdev.clockinapp.ui.theme.AppTheme
import au.com.jacobdev.clockinapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        setContent {

            AppTheme {
                val navController = rememberNavController()
                val isDialogOpen = remember { mutableStateOf(false) }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = Routes.START
                    ) {

                        composable(route = Routes.START) {
                            StartScreen(
                                onNavigate = { route -> navController.navigate(route) }
                            )
                        }

                        composable(route = Routes.LOGIN_DIALOG) {
                            isDialogOpen.value = true
                        }

                        composable(route = Routes.SELECT_EMPLOYEE) {
                            SelectEmployeeScreen(
                                onNavigate = {
                                    navController.navigate(Routes.CLOCK_IN_OUT + "?employeeId=${it.id}")
                                }
                            )
                        }

                        composable(route = Routes.CLOCKED_IN_EMPLOYEES) {
                            ClockedInEmployeesScreen(
                                onPopBackStack = { navController.popBackStack() },
                                onNavigate = {
                                    navController.navigate(Routes.CLOCK_IN_OUT + "?employeeId=${it.id}")
                                }
                            )
                        }

                        composable(route = Routes.CLOCK_IN_OUT_HISTORY) {
                            ClockedInOutHistoryScreen(
                                onPopBackStack = { navController.popBackStack() },
                                onNavigate = {
                                    navController.navigate(Routes.CLOCK_IN_OUT + "?employeeId=${it.id}")
                                }
                            )
                        }

                        composable(
                            route = Routes.CLOCK_IN_OUT + "?employeeId={employeeId}",
                            arguments = listOf(
                                navArgument(name = "employeeId") {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            ClockInOutScreen(
                                onPopBackStack = { navController.popBackStack() },
                                onNavigate = { route, clearBackStack ->
                                    if (clearBackStack) {
                                        navController.navigate(route) {
                                            popUpTo(Routes.START) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        navController.navigate(route)
                                    }
                                }
                            )
                        }
                    }

                    ShowAlertDialog(
                        isDialogOpen,
                        onDismissRequest = { isDialogOpen.value = false },
                        onSubmit = { username, password ->
                            isDialogOpen.value = false
                            navController.navigate(Routes.START) {
                                popUpTo(Routes.START) {
                                    inclusive = true
                                }
                            }
                        }
                    )

                }
            }
        }
    }
}