package com.salah.rxdatetimepickerapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.salah.rxdatetimepicker.RxDateConverters
import com.salah.rxdatetimepicker.RxDateTimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.Calendar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_date_only.setOnClickListener{
            RxDateTimePicker
                    .with(this@MainActivity)
                    .pickDateOnly()
                    .show()
                    .flatMap { date -> RxDateConverters.toString(date,"dd-MM-yyyy") }
                    .subscribe { date ->
                        tv_date_only.text = date
                    }
        }

        btn_time_only.setOnClickListener {
            RxDateTimePicker
                    .with(this@MainActivity)
                    .pickTimeOnly()
                    .is24HourView(false)
                    .show()
                    .flatMap { date -> RxDateConverters.toString(date,"hh-mm") }
                    .subscribe ({ time ->
                        tv_time_only.text = time.toString()
                    }) { throwable ->
                        Toast.makeText(this@MainActivity,throwable.localizedMessage,LENGTH_LONG).show()
                    }
        }

        btn_pick_both.setOnClickListener{
            RxDateTimePicker
                    .with(this@MainActivity)
                    .show()
                    .flatMap { date -> RxDateConverters.toCalender(date) }
                    .subscribe { calender ->
                        tv_date_and_time.text = calender.getDisplayName(Calendar.MONTH, LONG,Locale.US)
                    }
        }
    }
}
