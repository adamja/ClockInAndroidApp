package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.repo.ClockInOutRepo
import au.com.jacobdev.clockinapp.data.dao.EmployeeDao
import au.com.jacobdev.clockinapp.data.repo.EmployeeRepo
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.in_memory_test.TestDataRepo.EMPLOYEES
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EmployeeRepoImplTest(
    private val dao: EmployeeDao,
    private val clockInOutRepo: ClockInOutRepo,
): EmployeeRepo {

    override suspend fun insertEmployee(employee: Employee) {
        EMPLOYEES = EMPLOYEES + employee
    }

    override suspend fun updateEmployee(employee: Employee, clockInOut: ClockInOut) {
        val newEmployee = if (clockInOut.timestampOut == -1L) {
            // Employee is clocking in
            clockInOutRepo.insertClockInOut(clockInOut)  /* Add new ClockInOut */

            employee.copy(
                lastClockInOutId = clockInOut.id
            )
        } else {
            // Employee is clocking out: set last clock in to null
            clockInOutRepo.updateClockInOut(clockInOut)  /* Update ClockInOut */
            employee.copy(
                lastClockInOutId = null
            )
        }

        EMPLOYEES = EMPLOYEES.map {
            if (newEmployee.id == it.id) newEmployee else it
        }

    }

    override suspend fun deleteEmployee(employee: Employee) {
        EMPLOYEES = EMPLOYEES.filter { it.id != employee.id }
    }

    override suspend fun getEmployeeById(id: String): Employee? {

        return EMPLOYEES.find { it.id ==  id}
    }

    override fun getEmployees(): Flow<List<Employee>> {
        return flow {
            emit(EMPLOYEES)
        }
    }

}