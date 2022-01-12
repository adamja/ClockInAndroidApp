package au.com.jacobdev.clockinapp.data.dao

import androidx.room.*
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface ClockInOutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClockInOut(clockInOut: ClockInOut)

    @Delete
    suspend fun deleteClockInOut(clockInOut: ClockInOut)

    @Query("SELECT * FROM clockInOut WHERE id = :id")
    suspend fun getClockInOutById(id: Int): ClockInOut?

    @Query("SELECT * FROM clockInOut")
    fun getClockInOuts(): Flow<List<ClockInOut>>
}
