package au.com.jacobdev.clockinapp.ui.screens.clocked_in_out_history

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.repo.ClockInOutRepo
import au.com.jacobdev.clockinapp.data.repo.EmployeeRepo
import au.com.jacobdev.clockinapp.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockedInOutHistoryViewModel @Inject constructor(
    private val clockInOutRepo: ClockInOutRepo,
    private val employeeRepo: EmployeeRepo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var employeesList = employeeRepo.getEmployees()
    private var clockInOutList = clockInOutRepo.getClockInOuts()

    var employees by mutableStateOf<List<Employee>>(emptyList())
        private set

    var historyItems by mutableStateOf<List<HistoryItem>>(emptyList())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        viewModelScope.launch {

            employeesList.collect { employees = it }

            clockInOutList.collect() { list ->
                for (clockInOut in list) {
                    if (clockInOut.timestampOut != -1L) {
                        // Clocked out
                        historyItems = historyItems + HistoryItem(
                            timestamp = clockInOut.timestampOut,
                            clockInOut = clockInOut,
                            employee = employees.find { it.id == clockInOut.employeeId },
                            clockedIn = false,
                        )
                    }
                    // Clocked in
                    historyItems = historyItems + HistoryItem(
                        timestamp = clockInOut.timestampIn,
                        clockInOut = clockInOut,
                        employee = employees.find { it.id == clockInOut.employeeId },
                        clockedIn = true,
                    )

                    historyItems = historyItems.sortedByDescending { it.timestamp }
                }
            }
        }
    }

//    fun onEvent(event: SelectEmployeeEvent) {
//        when (event) {
//            is SelectEmployeeEvent.SearchTextUpdated -> {
//                viewModelScope.launch {
//
//                    employees =
//                        if (event.text.isNotBlank()) {
//                            employeesList.map {
//                                it.filter { employee -> employee.fullName().contains(event.text, ignoreCase = true) }
//                            }
//
//                        } else employeesList
//                }
//            }
//        }
//    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}