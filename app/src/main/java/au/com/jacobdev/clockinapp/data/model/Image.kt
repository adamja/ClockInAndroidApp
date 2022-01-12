package au.com.jacobdev.clockinapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey
    val id: String,
    val name: String,
    val path: String,
)
