package au.com.jacobdev.clockinapp.di

import android.app.Application
import androidx.room.Room
import au.com.jacobdev.clockinapp.data.*
import au.com.jacobdev.clockinapp.data.in_memory_test.ClockInOutRepoImplTest
import au.com.jacobdev.clockinapp.data.in_memory_test.EmployeeRepoImplTest
import au.com.jacobdev.clockinapp.data.in_memory_test.ImageRepoImplTest
import au.com.jacobdev.clockinapp.data.repo.ClockInOutRepo
import au.com.jacobdev.clockinapp.data.repo.EmployeeRepo
import au.com.jacobdev.clockinapp.data.repo.ImageRepo
import au.com.jacobdev.clockinapp.util.AppSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideEmployeeDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "clock_in_app_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideImageRepository(db: AppDatabase): ImageRepo {
        when {
            AppSettings.USE_IN_MEMORY_TEST_DATABASE -> {
                return ImageRepoImplTest(db.imageDao)
            }
            AppSettings.USE_ROOM_DATABASE -> {
                //TODO
            }
            AppSettings.USE_FIREBASE_DATABASE -> {
                //TODO
            }
        }
        return ImageRepoImplTest(db.imageDao)
    }


    @Provides
    @Singleton
    fun provideClockInOutRepository(db: AppDatabase): ClockInOutRepo {
        when {
            AppSettings.USE_IN_MEMORY_TEST_DATABASE -> {
                return ClockInOutRepoImplTest(db.clockInOutDao)
            }
            AppSettings.USE_ROOM_DATABASE -> {
                //TODO
            }
            AppSettings.USE_FIREBASE_DATABASE -> {
                //TODO
            }
        }
        return ClockInOutRepoImplTest(db.clockInOutDao)
    }


    @Provides
    @Singleton
    fun provideEmployeeRepository(db: AppDatabase, clockInOutRepo: ClockInOutRepo): EmployeeRepo {
        when {
            AppSettings.USE_IN_MEMORY_TEST_DATABASE -> {
                return (EmployeeRepoImplTest(db.employeeDao, clockInOutRepo))
            }
            AppSettings.USE_ROOM_DATABASE -> {
                //TODO
            }
            AppSettings.USE_FIREBASE_DATABASE -> {
                //TODO
            }
        }
        return (EmployeeRepoImplTest(db.employeeDao, clockInOutRepo))
    }

}