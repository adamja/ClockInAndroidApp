package au.com.jacobdev.clockinapp.ui.screens.select_employee

sealed class SelectEmployeeEvent {
    data class SearchTextUpdated(val text: String): SelectEmployeeEvent()
}