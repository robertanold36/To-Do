package com.robert.planYourDay.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robert.planYourDay.model.PlanModel

@Dao
interface PlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(planModel: PlanModel)

    @Query("select*from reminder_info order by infoId desc")
    fun getAllPlan(): LiveData<MutableList<PlanModel>>

    @Query("select count(infoId) from reminder_info")
    fun getCount():LiveData<Int>
}