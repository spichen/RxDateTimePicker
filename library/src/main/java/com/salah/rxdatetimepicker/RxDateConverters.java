package com.salah.rxdatetimepicker;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by salah on 16/1/18.
 */

public class RxDateConverters {

    public static Maybe<String> toString(final Date date, final String pattern) {
        return Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> emitter) throws Exception {
                try {
                    SimpleDateFormat simpleDate =  new SimpleDateFormat(pattern,Locale.US);
                    emitter.onSuccess(simpleDate.format(date));
                } catch (IllegalArgumentException e){
                    Log.e(RxDateConverters.class.getSimpleName(), "Error formatting date", e);
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Maybe<Calendar> toCalender(final Date date) {
        return Maybe.create(new MaybeOnSubscribe<Calendar>() {
            @Override
            public void subscribe(MaybeEmitter<Calendar> emitter) throws Exception {
                try {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    emitter.onSuccess(cal);
                } catch (Exception e){
                    Log.e(RxDateConverters.class.getSimpleName(), "Error", e);
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

}
