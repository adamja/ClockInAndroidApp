package au.com.jacobdev.clockinapp.util

import androidx.appcompat.app.AlertDialog

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
    data class ShowDialog(val title: String, val message: String): UiEvent()
    object DismissDialog: UiEvent()
    object TimerUpdate: UiEvent()
}