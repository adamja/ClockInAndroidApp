package au.com.jacobdev.clockinapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val profileImageId: String? = null,
    val lastClockInOutId: String? = null,
) {
    fun fullName(): String = "$firstName $lastName"

    fun isClockedIn() = lastClockInOutId.isNullOrBlank()
}