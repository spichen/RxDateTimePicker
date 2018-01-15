package com.salah.rxdatetimepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Maybe;
import io.reactivex.subjects.MaybeSubject;

/**
 * Created by salah on 15/1/18.
 */

public class RxDateTimePicker implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    private Context context;

    private Calendar calendar = Calendar.getInstance();

    private final static int TYPE_DATE_AND_TIME = 1;
    private final static int TYPE_DATE_ONLY = 2;
    private final static int TYPE_TIME_ONLY = 3;
    private int TYPE = TYPE_DATE_AND_TIME;
    private boolean is24HourView = true;

    private MaybeSubject<Date> subject;

    private RxDateTimePicker(Context context) {
        this.context = context;
        subject = MaybeSubject.create();
    }

    public static RxDateTimePicker with(Context context) {
        return new RxDateTimePicker(context);
    }

    public RxDateTimePicker pickDateOnly(){
        TYPE = TYPE_DATE_ONLY;
        return this;
    }

    public RxDateTimePicker pickTimeOnly(){
        TYPE = TYPE_TIME_ONLY;
        return this;
    }

    public RxDateTimePicker is24HourView(boolean is24HourView){
        this.is24HourView = is24HourView;
        return this;
    }

    public Maybe<Date> show(){
        if(TYPE == TYPE_TIME_ONLY)
            showTimePickerDialog();
        else
            showDatePickerDialog();
        return subject;
    }

    private void showDatePickerDialog(){
        new DatePickerDialog(this.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePickerDialog(){
        new TimePickerDialog(this.context,
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24HourView)
                .show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();
        if(TYPE == TYPE_DATE_ONLY)
            subject.onSuccess(date);
        else
            showTimePickerDialog();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        Date date = calendar.getTime();
        subject.onSuccess(date);
    }
}
