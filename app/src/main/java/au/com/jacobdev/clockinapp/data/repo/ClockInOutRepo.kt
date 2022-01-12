package au.com.jacobdev.clockinapp.data.repo

import au.com.jacobdev.clockinapp.data.model.ClockInOut
import kotlinx.coroutines.flow.Flow

interface ClockInOutRepo {

    suspend fun insertClockInOut(clockInOut: ClockInOut)

    suspend fun updateClockInOut(clockInOut: ClockInOut)

    suspend fun deleteClockInOut(clockInOut: ClockInOut)

    suspend fun getClockInOutById(id: String): ClockInOut?

    fun getClockInOuts(): Flow<List<ClockInOut>>

}