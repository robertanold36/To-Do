package com.robert.planYourDay.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_info")
data class PlanModel(

    @ColumnInfo(name = "plan")
    var plan:String="",

    @ColumnInfo(name="date")
    var date:String="",

    @ColumnInfo(name="time")
    var time:String=""


){
    @PrimaryKey(autoGenerate = true)
    var infoId:Int?=null
}
