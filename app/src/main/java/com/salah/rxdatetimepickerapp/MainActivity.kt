package com.salah.rxdatetimepickerapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.salah.rxdatetimepicker.RxDateTimePicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_date_only.setOnClickListener{
            RxDateTimePicker
                    .with(this@MainActivity)
                    .pickDateOnly()
                    .show()
                    .subscribe { date ->
                        tv_date_only.text = date.toString()
                    }
        }

        btn_time_only.setOnClickListener {
            RxDateTimePicker
                    .with(this@MainActivity)
                    .pickTimeOnly()
                    .show()
                    .subscribe { time ->
                        tv_time_only.text = time.toString()
                    }
        }

        btn_pick_both.setOnClickListener{
            RxDateTimePicker
                    .with(this@MainActivity)
                    .show()
                    .subscribe { date ->
                        tv_date_and_time.text = date.toString()
                    }
        }
    }
}
