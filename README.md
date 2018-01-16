# RxDateTimePicker
An easy way to pick date and time on Android using RxJava2


## Setup

To use this library your ` minSdkVersion` must be >= 16.

In your build.gradle :

```gradle
dependencies {
    implementation 'com.salah.rxdatetimepicker:rxdatetimepicker:1.0.1'
}
```


## Usage

Kotlin :
```kotlin
RxDateTimePicker
        .with(this)
        .show()
        .subscribe { date -> Log.d("Date",date.toString()) }
```

Java :
```java
RxDateTimePicker
        .with(this)
        .show()
        .subscribe(new Consumer<Date>() {
            @Override
            public void accept(@NonNull Date date) throws Exception {
                Log.d("Date",date.toString());
            }
        });
```
By default both date and time picker dialog will show. You can specify date only or time only picker as shown below

```kotlin
RxDateTimePicker
        .with(this)
        .pickTimeOnly() // or .pickDateOnly()
        .show()
        .subscribe { date -> Log.d("Date",date.toString()) }
```

### Converters

Be default Java Date object are being emitted. You can convert this to String in required format.

Kotlin :
```kotlin
RxDateTimePicker
        .with(this)
        .pickDateOnly()
        .show()
        .flatMap { date -> RxDateConverters.toString(date,"dd-MM-yyyy") }
        .subscribe { date -> Log.d("Date",date) }
```

Java :
```java
RxDateTimePicker
        .with(this)
        .pickTimeOnly()
        .is24HourView(false)
        .show()
        .flatMap(new Function<Date, MaybeSource<String>>() {
            @Override
            public MaybeSource<String> apply(@NonNull Date date) throws Exception {
                return RxDateConverters.toString(date,"mm-hh");
            }
        })
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String date) throws Exception {
                Log.d("Date",date);
            }
        });
```

Converting to Calender object
```kotlin
RxDateTimePicker
        .with(this)
        .show()
        .flatMap { date -> RxDateConverters.toCalender(date) }
        .subscribe { calender -> Log.d("Date",calender.getDisplayName(Calendar.MONTH, LONG,Locale.US)) }
```

License
-------

    Copyright (C) 2018 Salah

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.






