package com.robert.planYourDay.fragment


import android.content.Intent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import com.robert.planYourDay.R
import com.robert.planYourDay.databinding.FragmentHomeBinding
import com.robert.planYourDay.ui.my_plan.MyPlan
import com.robert.planYourDay.ui.savePlan.SavePlan


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        val bounceAnim = AnimationUtils.loadAnimation(
            context,
            R.anim.bounce
        )
        val cardAnim = AnimationUtils.loadAnimation(
            context,
            R.anim.card_anim
        )

        binding.emojiView.startAnimation(bounceAnim)
        binding.cardReview.startAnimation(cardAnim)
        binding.cardSave.startAnimation(cardAnim)



        binding.cardImage1.setOnClickListener {
            val intent = Intent(context, SavePlan::class.java)
            startActivity(intent)
        }

        binding.cardImage2.setOnClickListener {
            val intent = Intent(context, MyPlan::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}
