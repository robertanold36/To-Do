package com.robert.planYourDay.adapter

import com.robert.planYourDay.R
import com.robert.planYourDay.model.PlanModel
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.all_plan.view.*

class PlanAdapter(private val planModel: PlanModel):Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.all_plan
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_time.text=planModel.time
        viewHolder.itemView.tv_date.text=planModel.date
        viewHolder.itemView.tv_plan.text=planModel.plan

    }
}