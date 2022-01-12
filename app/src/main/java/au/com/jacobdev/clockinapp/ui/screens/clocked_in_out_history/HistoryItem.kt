package au.com.jacobdev.clockinapp.ui.screens.clocked_in_out_history

import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.util.getDateAusFormatFromTimeStamp
import au.com.jacobdev.clockinapp.util.getTime12HFromTimeStamp

data class HistoryItem(
    val timestamp: Long,
    val clockedIn: Boolean,
    val clockInOut: ClockInOut,
    val employee: Employee?,
    val employeeName: String = employee?.fullName() ?: "Unknown(id: ${clockInOut.id})",
) {
    fun getTextDisplay(): String {
        val time = getTime12HFromTimeStamp(timestamp)
        val date = getDateAusFormatFromTimeStamp(timestamp)

        return if (clockedIn) {
            "$employeeName has clocked in at $time on $date"
        } else {
            "$employeeName has clocked out at $time on $date"
        }
    }
}
