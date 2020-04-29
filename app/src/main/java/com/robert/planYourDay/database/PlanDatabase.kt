package com.robert.planYourDay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robert.planYourDay.model.PlanModel


@Database(entities = [PlanModel::class],version = 1, exportSchema = false)
abstract class PlanDatabase :RoomDatabase(){

    abstract fun planDao():PlanDao

    companion object{

        @Volatile
        private var INSTANCE: PlanDatabase? = null

        fun getInstance(context: Context): PlanDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    val databaseName = "sleep_history_database"
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlanDatabase::class.java,databaseName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}
