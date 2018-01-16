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
