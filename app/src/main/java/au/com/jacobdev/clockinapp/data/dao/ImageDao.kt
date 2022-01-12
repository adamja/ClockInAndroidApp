package au.com.jacobdev.clockinapp.data.dao

import androidx.room.*
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.model.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: Image)

    @Delete
    suspend fun deleteImage(image: Image)

    @Query("SELECT * FROM image WHERE id = :id")
    suspend fun getImageById(id: Int): Image?

    @Query("SELECT * FROM image")
    fun getImages(): Flow<List<Image>>
}
