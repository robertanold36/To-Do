package com.robert.planYourDay.ui.my_plan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.robert.planYourDay.adapter.MyPlanAdapter
import com.robert.planYourDay.R
import com.robert.planYourDay.databinding.ActivityMyPlanBinding
import com.robert.planYourDay.receiver.TimerReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyPlan : AppCompatActivity() {

    lateinit var adapter: MyPlanAdapter
    private lateinit var myPlanViewModel: MyPlanViewModel
    private lateinit var binding:ActivityMyPlanBinding

    lateinit var timerReceiver: TimerReceiver

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_my_plan)

        timerReceiver= TimerReceiver()

        adapter= MyPlanAdapter(this,mutableListOf())

        GlobalScope.launch(Dispatchers.Main) {
            binding.count.text="Total Reminder"
            delay(3000)
            for (i in 1..adapter.itemCount){
                delay(10)
                binding.count.text=i.toString()
            }
        }

        binding.recyclerview.adapter=adapter

        myPlanViewModel=ViewModelProvider(this).get(MyPlanViewModel::class.java)

        myPlanViewModel.getAllPlan().observe(this, Observer {
            adapter.planModels=it
            adapter.notifyDataSetChanged()
        })

    }
}
