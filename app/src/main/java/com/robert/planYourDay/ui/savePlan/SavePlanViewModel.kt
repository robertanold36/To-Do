package com.robert.planYourDay.ui.savePlan

import android.app.*
import androidx.lifecycle.AndroidViewModel
import com.robert.planYourDay.database.PlanDatabase
import com.robert.planYourDay.database.Repository
import com.robert.planYourDay.model.PlanModel
import kotlinx.coroutines.*

class SavePlanViewModel(private val app: Application) : AndroidViewModel(app) {


    private val db = PlanDatabase

    private val repository: Repository by lazy {
        Repository(db.getInstance(app))
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    var plan: String? = ""

    var eventListener: EventListener? = null

    fun save(date: String, theTime: String) = uiScope.launch {

            val planModel = PlanModel(plan!!, date, theTime)
            repository.insert(planModel)
            plan = ""
            withContext(Dispatchers.Main) {
                eventListener?.onSuccess()
            }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}