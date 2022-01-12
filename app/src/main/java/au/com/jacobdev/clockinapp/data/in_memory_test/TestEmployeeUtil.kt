package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.model.Employee

object TestEmployeeUtil {

    fun getEmployees(): List<Employee> {
        return listOf(

            Employee(
                id = "1",
                firstName = "Adam",
                lastName = "Jacob",
                lastClockInOutId = TestClockInOutUtil.getClockInOuts().find { it.id == "4" }?.id
            ),

            Employee(
                id = "2",
                firstName = "Chris",
                lastName = "Wagener",
                lastClockInOutId = TestClockInOutUtil.getClockInOuts().find { it.id == "2" }?.id
            ),

            Employee(
                id = "3",
                firstName = "Richard",
                lastName = "Stickland",
                lastClockInOutId = TestClockInOutUtil.getClockInOuts().find { it.id == "3" }?.id
            ),

            Employee(
                id = "4",
                firstName = "Luc",
                lastName = "Jacob",
            ),

            Employee(
                id = "5",
                firstName = "Terry",
                lastName = "Bowker",
            ),

            Employee(
                id = "6",
                firstName = "John",
                lastName = "Smith",
            ),

            )
    }

}