package au.com.jacobdev.clockinapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.jacobdev.clockinapp.util.getDateIsoFormatFromTimeStamp


@Entity
data class ClockInOut(
    @PrimaryKey
    val id: String,
    val employeeId: String,
    val timestampIn: Long,
    val timestampOut: Long = -1,
    val imageInId: String? = null,
    val imageOutId: String? = null,
) {
    fun timestampTotalTime(): Long = if (timestampOut != -1L) {timestampOut - timestampIn} else { -1L }

    fun timestampInString() : String = getDateIsoFormatFromTimeStamp(timestampIn)
}