package au.com.jacobdev.clockinapp.ui.screens.clock_in_out_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.jacobdev.clockinapp.data.repo.ClockInOutRepo
import au.com.jacobdev.clockinapp.data.repo.EmployeeRepo
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.model.Image
import au.com.jacobdev.clockinapp.util.*
import au.com.jacobdev.clockinapp.util.AppSettings.ENABLE_IMAGE_ON_CLOCK_IN
import au.com.jacobdev.clockinapp.util.AppSettings.ENABLE_IMAGE_ON_CLOCK_OUT
import au.com.jacobdev.clockinapp.util.AppSettings.FORCE_IMAGE_ON_CLOCK_IN
import au.com.jacobdev.clockinapp.util.AppSettings.FORCE_IMAGE_ON_CLOCK_OUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockInOutScreenViewModel @Inject constructor(
    private val clockInOutRepo: ClockInOutRepo,
    private val employeeRepo: EmployeeRepo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var employee by mutableStateOf<Employee?>(null)
        private set

    var clockInOut by mutableStateOf<ClockInOut?>(null)
        private set

    var title by mutableStateOf<String>("Clock In")
        private set

    var status by mutableStateOf<String>("Clock In")
        private set

    var takePictureButtonText by mutableStateOf<String>("Take Picture")
        private set

    var image by mutableStateOf<Image?>(null)
        private set

    var clockInOutButtonText by mutableStateOf<String>("Clock In")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        val employeeId = savedStateHandle.get<String>("employeeId")!!
        updateUi(employeeId)
    }


    private fun updateUi(employeeId: String) {

        if (employeeId.isNotBlank()) {
            viewModelScope.launch {
                employee = employeeRepo.getEmployeeById(employeeId)
                clockInOut =
                    employee?.lastClockInOutId?.let { clockInOutRepo.getClockInOutById(it) }

                employee?.let {
                    clockInOut?.let {

                        /* Employee is clocked in */
                        title = "Clock Out"
                        status = "clocked in"
                        takePictureButtonText = "Take Picture"
                        image = null
                        clockInOutButtonText = "Clock Out"

                    } ?: let {

                        /* Employee is not clocked in */
                        title = "Clock In"
                        status = "clocked out"
                        takePictureButtonText = "Take Picture"
                        image = null
                        clockInOutButtonText = "Clock In"

                    }
                } ?: let {
                    viewModelScope.launch {
                        sendUiEvent(UiEvent.ShowSnackbar("Error: no employee found"))
                        sendUiEvent(UiEvent.PopBackStack)
                    }
                }
            }
        }
    }

    private fun getNewClockInOut(): ClockInOut {
        clockInOut?.let {
            return it.copy(
                timestampOut = getCurrentMillis(),
                imageOutId = image?.id
            )
        } ?: let {
            return ClockInOut(
                id = getUuid(),
                employeeId = employee!!.id,
                timestampIn = getCurrentMillis(),
                imageInId = image?.id
            )
        }
    }

    fun onEvent(event: ClockInOutEvent) {
        when (event) {

            is ClockInOutEvent.OnTakePicture -> {
                image = event.image
            }

            is ClockInOutEvent.OnClockInOut -> {
                viewModelScope.launch {

                    if ((employee!!.isClockedIn() && ENABLE_IMAGE_ON_CLOCK_IN && FORCE_IMAGE_ON_CLOCK_IN) ||
                        (!employee!!.isClockedIn() && ENABLE_IMAGE_ON_CLOCK_OUT && FORCE_IMAGE_ON_CLOCK_OUT)
                    ) {
                        if (image == null) {
                            sendUiEvent(UiEvent.ShowSnackbar("Please take a photo first."))
                            return@launch
                        }
                    }

                    val newClockInOut = getNewClockInOut()
                    employeeRepo.updateEmployee(employee!!, newClockInOut)

                    val title: String
                    val message: String
                    if (clockInOut != null) {
                        val time = getTime12HFromTimeStamp(newClockInOut.timestampOut)
                        val date = getDateAusFormatFromTimeStamp(newClockInOut.timestampOut)
                        val totalTime = getTotalHoursMinutesFromTimestamp(newClockInOut.timestampOut - newClockInOut.timestampIn)

                        title = "You have clocked out"
                        message =  "${employee!!.fullName()} clocked out at $time on $date ($totalTime hours). "
                    } else {
                        val time = getTime12HFromTimeStamp(newClockInOut.timestampIn)
                        val date = getDateAusFormatFromTimeStamp(newClockInOut.timestampIn)
                        title = "You have clocked in"
                        message =  "${employee!!.fullName()} clocked in at $time on $date."
                    }

//                    sendUiEvent(UiEvent.ShowSnackbar(title))

                    sendUiEvent(
                        UiEvent.ShowDialog(
                            title = title,
                            message = message
                        )
                    )
                }
            }

            ClockInOutEvent.OnDialogDismiss -> sendUiEvent(UiEvent.Navigate(Routes.START))
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}