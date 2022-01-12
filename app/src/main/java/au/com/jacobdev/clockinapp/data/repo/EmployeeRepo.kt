package au.com.jacobdev.clockinapp.data.repo

import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import kotlinx.coroutines.flow.Flow

interface EmployeeRepo {

    suspend fun insertEmployee(employee: Employee)

    suspend fun updateEmployee(employee: Employee, clockInOut: ClockInOut)

    suspend fun deleteEmployee(employee: Employee)

    suspend fun getEmployeeById(id: String): Employee?

    fun getEmployees(): Flow<List<Employee>>

}