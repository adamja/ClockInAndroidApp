package au.com.jacobdev.clockinapp.ui.screens.clock_in_out_screen

import au.com.jacobdev.clockinapp.data.model.Image

sealed class ClockInOutEvent {
    data class OnTakePicture(val image: Image): ClockInOutEvent()
    object OnClockInOut : ClockInOutEvent()
    object OnDialogDismiss : ClockInOutEvent()
}