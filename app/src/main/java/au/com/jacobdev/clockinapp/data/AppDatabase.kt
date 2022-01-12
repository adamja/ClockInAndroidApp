package au.com.jacobdev.clockinapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import au.com.jacobdev.clockinapp.data.dao.ClockInOutDao
import au.com.jacobdev.clockinapp.data.dao.EmployeeDao
import au.com.jacobdev.clockinapp.data.dao.ImageDao
import au.com.jacobdev.clockinapp.data.model.ClockInOut
import au.com.jacobdev.clockinapp.data.model.Employee
import au.com.jacobdev.clockinapp.data.model.Image

@Database(
    entities = [Employee::class, ClockInOut::class, Image::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract val employeeDao: EmployeeDao
    abstract val clockInOutDao: ClockInOutDao
    abstract val imageDao: ImageDao
}