package com.robert.planYourDay.ui.my_plan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.robert.planYourDay.database.PlanDatabase
import com.robert.planYourDay.database.Repository
import kotlinx.coroutines.Job

class MyPlanViewModel(application:Application):AndroidViewModel(application) {

    private val db= PlanDatabase

    private  val repository: Repository by lazy {
        Repository(db.getInstance(application))
    }

    private val myPlanJob= Job()


    fun getAllPlan()=repository.getPlan()


    override fun onCleared() {
        super.onCleared()
        myPlanJob.cancel()
    }
}