package com.robert.planYourDay.ui.home

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.robert.planYourDay.R
import com.robert.planYourDay.databinding.ActivityMainBinding
import com.robert.planYourDay.receiver.TimerReceiver
import com.robert.planYourDay.ui.my_plan.MyPlan
import com.robert.planYourDay.ui.savePlan.SavePlan
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var randomNumber:Int?=null
    private val TAG="HomeActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        randomNumber= Random.nextInt(0,100)



        val bounceAnim=AnimationUtils.loadAnimation(this,
            R.anim.bounce
        )
        val cardAnim=AnimationUtils.loadAnimation(this,
            R.anim.card_anim
        )

        binding.emojiView.startAnimation(bounceAnim)
        binding.cardReview.startAnimation(cardAnim)
        binding.cardSave.startAnimation(cardAnim)

       GlobalScope.launch(Dispatchers.Main) {
           binding.randomNumber.text="Guess Number 1 to 100"
           delay(3000)
           for(i in 1..100){
               if(i!=randomNumber){
                   delay(10)
                   binding.randomNumber.text = i.toString()
               }

               else{
                   break
               }

           }
       }

        binding.cardImage1.setOnClickListener{
            val intent= Intent(this, SavePlan::class.java)
            val options= ViewCompat.getTransitionName(cardImage1)?.let { it1 ->
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,binding.cardImage1,
                    it1
                )
            }

            startActivity(intent,options?.toBundle())
        }

        binding.cardImage2.setOnClickListener {
            val intent=Intent(this,MyPlan::class.java)
            startActivity(intent)
        }
    }
}

