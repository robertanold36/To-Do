package com.robert.planYourDay.ui.savePlan

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.robert.planYourDay.R
import com.robert.planYourDay.databinding.ActivitySavePlanBinding
import com.robert.planYourDay.receiver.TimerReceiver
import com.robert.planYourDay.ui.my_plan.MyPlan
import java.util.*


class SavePlan : AppCompatActivity(), EventListener {

    private lateinit var binding: ActivitySavePlanBinding
    private lateinit var savePlanViewModel: SavePlanViewModel
    private val TAG = "SavePlan"

    private lateinit var alarmManager: AlarmManager
    private lateinit var receiverIntent: Intent

    private lateinit var pendingIntent: PendingIntent


    private val timeDate = Calendar.getInstance()
    private val tYear = timeDate.get(Calendar.YEAR)
    private val tMonth = timeDate.get(Calendar.MONTH)
    private val tDay = timeDate.get(Calendar.DAY_OF_WEEK)
    private val hour = timeDate.get(Calendar.HOUR_OF_DAY)
    private val tMinute = timeDate.get(Calendar.MINUTE)


    private var timeDay: Int? = null
    private var timeMonth: Int? = null
    private var timeYear: Int? = null
    private var timeHour: Int? = null
    private var timeMinute: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_save_plan
        )

        alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        receiverIntent = Intent(this@SavePlan, TimerReceiver::class.java)


        savePlanViewModel = ViewModelProvider(this).get(SavePlanViewModel::class.java)
        binding.planViewModel = savePlanViewModel
        savePlanViewModel.eventListener = this

        binding.setDate.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                setDate()
            }
        }
        binding.setTime.setOnClickListener { setTime() }
        binding.btnSave.setOnClickListener { save() }

    }

    override fun onEmpty() {
        binding.plan.error = "insert information"
    }

    override fun onSuccess() {

        Toast.makeText(this, "well data inserted", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "DATA INSERTED")
        val intent=Intent(this,MyPlan::class.java)
        startActivity(intent)
        finish()

    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    fun setDate() {

        val datePickerDialog =
            DatePickerDialog(
                this,
                android.R.style.Theme_DeviceDefault_Dialog_Alert,
                DatePickerDialog.OnDateSetListener
                { view_, year, month, dayOfMonth ->
                    val monthOfYear = month + 1
                    timeYear = year
                    timeMonth = month
                    timeDay = dayOfMonth

                    binding.setDate.text = "$dayOfMonth/$monthOfYear/$year"
                }, tYear, tMonth, tDay
            )

        datePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    fun setTime() {

        val timePickerDialog =
            TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener
                { view, hourOfDay, minute ->
                    binding.setTime.text = "$hourOfDay:$minute"

                    timeHour = hourOfDay
                    timeMinute = minute

                }, hour, tMinute, true
            )

        timePickerDialog.show()

    }

    fun save() {

        when {
            binding.setTime.text.isEmpty() -> {
                Toast.makeText(this, "please set time", Toast.LENGTH_SHORT).show()
            }
            binding.setDate.text.isEmpty() -> {
                Toast.makeText(this, "please set date", Toast.LENGTH_SHORT).show()
            }

            binding.plan.text.isEmpty() -> {
                Toast.makeText(this, "please set plan", Toast.LENGTH_SHORT).show()
            }
            else -> {

                val calendar = Calendar.getInstance().apply {
                    timeInMillis = System.currentTimeMillis()
                    set(
                        timeYear!!,
                        timeMonth!!,
                        timeDay!!,
                        timeHour!!,
                        timeMinute!!,
                        0
                    )
                }

                when {
                    calendar.before(Calendar.getInstance()) -> {
                        calendar.add(Calendar.DATE, 1)
                    }

                    else -> {
                        val id = Calendar.getInstance().timeInMillis.toInt()

                        receiverIntent.putExtra("msg",binding.plan.text.toString())

                        pendingIntent = PendingIntent.getBroadcast(
                            this, id,
                            receiverIntent,
                            PendingIntent.FLAG_ONE_SHOT
                        )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            alarmManager.setExact(
                                AlarmManager
                                    .RTC_WAKEUP,
                                calendar.timeInMillis,
                                pendingIntent
                            )
                        }

                    }
                }

                val theDate = binding.setDate.text.toString()
                val theTime = binding.setTime.text.toString()
                val plan=binding.plan.text.toString()
                savePlanViewModel.save(plan,theDate, theTime)

                binding.setTime.text = ""
                binding.setDate.text = ""
                binding.plan.text.clear()

            }
        }

    }

}
