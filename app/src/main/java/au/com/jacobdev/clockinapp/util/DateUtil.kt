package au.com.jacobdev.clockinapp.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.minutes
import kotlin.time.Duration.Companion.minutes as minutes1

/*
 * SimpleDateFormat
 *
 * Reference: https://developer.android.com/reference/kotlin/java/text/SimpleDateFormat
 */

fun getCurrentMillis() : Long {
    return System.currentTimeMillis()
}

fun getDateTimeIsoFormatFromTimeStamp(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}

fun getDateIsoFormatFromTimeStamp(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}

fun getDateAusFormatFromTimeStamp(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd/MM/yy")
    return format.format(date)
}

fun getTime12HFromTimeStamp(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("hh:mm a")
    return format.format(date)
}

fun getTime24HFromTimeStamp(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}

fun getTotalHoursMinutesFromTimestamp(time: Long): String {
    val totalMinutes = time / 1000 / 60
    val hours = (totalMinutes / 60).toString()
    val minutes = (totalMinutes % 60).toString().padStart(2, '0')

    return "$hours:$minutes"
}
