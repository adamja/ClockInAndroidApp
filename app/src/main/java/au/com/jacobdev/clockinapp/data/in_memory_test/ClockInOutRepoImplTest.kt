package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.dao.ClockInOutDao
import au.com.jacobdev.clockinapp.data.repo.ClockInOutRepo
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.in_memory_test.TestDataRepo.CLOCK_IN_OUTS
import au.com.jacobdev.clockinapp.util.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClockInOutRepoImplTest(
    private val dao: ClockInOutDao
) : ClockInOutRepo {


    override suspend fun insertClockInOut(clockInOut: ClockInOut) {
        CLOCK_IN_OUTS = CLOCK_IN_OUTS + clockInOut
    }


    override suspend fun updateClockInOut(clockInOut: ClockInOut) {
        CLOCK_IN_OUTS = CLOCK_IN_OUTS.map {
            if (it.id == clockInOut.id) clockInOut else it
        }
    }


    override suspend fun deleteClockInOut(clockInOut: ClockInOut) {
        CLOCK_IN_OUTS = CLOCK_IN_OUTS.filter {
            it.id != clockInOut.id
        }
    }


    override suspend fun getClockInOutById(id: String): ClockInOut? {
        return CLOCK_IN_OUTS.find { it.id == id }
    }


    override fun getClockInOuts(): Flow<List<ClockInOut>> {
        return flow {
            emit(CLOCK_IN_OUTS)
        }
    }


}