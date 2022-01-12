package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.model.ClockInOut

object TestClockInOutUtil {

    fun getClockInOuts(): List<ClockInOut> {
        return listOf(

            /*

            Timestamps: https://timestamp.online/

            Employees:
            1: Adam
            2: Chris
            3: Richard
            4: Luc
            5: Terry
            6: John
             */

            ClockInOut(
                id = "1",
                employeeId = "1",  // Adam
                timestampIn = 1640735097000,  // 12/29/2021, 7:44:57 AM
                timestampOut = 1640761240000,  // 2021-12-29 15:00:40
            ),

            ClockInOut(
                id = "2",
                employeeId = "2",  // Chris
                timestampIn = 1609714800000,  // 2021-01-04 7:00:00
                timestampOut = 1609754400000, // 2021-01-04 18:00:00
            ),

            ClockInOut(
                id = "3",
                employeeId = "3",  // Richard
                timestampIn = 1609803000000,  // 2021-01-04 7:00:00
            ),

            ClockInOut(
                id = "4",
                employeeId = "1",  // Adam
                timestampIn = 1609714800000,  // 2021-01-04 7:00:00
//                timestampOut = 1609754400000, // 2021-01-04 18:00:00
            ),

            ClockInOut(
                id = "5",
                employeeId = "5",  // Terry
                timestampIn = 1609799400000,  // 2021-01-05 06:30:00
                timestampOut = 1609842600000,  // 2021-01-05 18:30:00
            ),

            ClockInOut(
                id = "6",
                employeeId = "6",  // John
                timestampIn = 1609799400000,  // 2021-01-05 06:30:00
                timestampOut = 1609842600000,  // 2021-01-05 18:30:00
            ),

            )
    }

}