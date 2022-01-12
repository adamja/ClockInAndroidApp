package au.com.jacobdev.clockinapp.data.repo

import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepo {

    suspend fun insertImage(image: Image)

    suspend fun updateImage(image: Image)

    suspend fun deleteImage(image: Image)

    suspend fun getImageById(id: String): Image?

    fun getImages(): Flow<List<Image>>

}