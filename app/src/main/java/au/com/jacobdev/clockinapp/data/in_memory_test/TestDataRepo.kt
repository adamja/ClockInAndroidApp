package au.com.jacobdev.clockinapp.data.in_memory_test

import au.com.jacobdev.clockinapp.data.model.Image


object TestDataRepo {


    var EMPLOYEES = TestEmployeeUtil.getEmployees()
    var CLOCK_IN_OUTS = TestClockInOutUtil.getClockInOuts()
    var IMAGES : List<Image> = emptyList()

}