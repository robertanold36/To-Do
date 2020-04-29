package com.robert.planYourDay.database

import com.robert.planYourDay.model.PlanModel

class Repository(private val db:PlanDatabase) {


    suspend fun insert(planModel: PlanModel)=db.planDao().insert(planModel)

    fun getPlan() = db.planDao().getAllPlan()

    fun getCount()=db.planDao().getCount()
}