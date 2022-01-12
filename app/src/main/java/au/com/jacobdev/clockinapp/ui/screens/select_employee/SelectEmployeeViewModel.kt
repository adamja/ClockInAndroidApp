package au.com.jacobdev.clockinapp.ui.screens.select_employee

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.jacobdev.clockinapp.data.repo.EmployeeRepo
import au.com.jacobdev.clockinapp.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectEmployeeViewModel @Inject constructor(
    private val employeeRepo: EmployeeRepo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var employeesList = employeeRepo.getEmployees()
    var employees = employeesList

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        viewModelScope.launch {
            employees = employeeRepo.getEmployees()
        }
    }

    fun onEvent(event: SelectEmployeeEvent) {
        when (event) {
            is SelectEmployeeEvent.SearchTextUpdated -> {
                viewModelScope.launch {

                    employees =
                        if (event.text.isNotBlank()) {
                            employeesList.map {
                                it.filter { employee ->
                                    employee.fullName().contains(event.text, ignoreCase = true)
                                }
                            }

                        } else employeesList

                    employees = employees.map { list ->
                        list.sortedBy { employee -> employee.fullName() }
                    }
                }
            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}