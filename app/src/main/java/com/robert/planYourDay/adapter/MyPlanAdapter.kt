package com.robert.planYourDay.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robert.planYourDay.R
import com.robert.planYourDay.model.PlanModel
import kotlinx.android.synthetic.main.all_plan.view.*

class MyPlanAdapter(var context:Context,var planModels:MutableList<PlanModel>):RecyclerView.Adapter<MyPlanAdapter.MyPlanViewHolder>() {

    inner class MyPlanViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlanViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.all_plan,parent,false)
        return MyPlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(planModels.size>0){
            planModels.size
        }
        else{
            0
        }
    }


    override fun onBindViewHolder(holder: MyPlanViewHolder, position: Int) {
        val planModel:PlanModel=planModels[position]
        holder.itemView.tv_plan.text=planModel.plan
        holder.itemView.tv_date.text=planModel.date
        holder.itemView.tv_time.text=planModel.time

    }
}