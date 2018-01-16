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

```kotlin
RxDateTimePicker
      .with(this@MainActivity)
      .pickDateOnly()
      .show()
      .subscribe { date -> Log.d("Date",date.toString()) }
```

```java

 RxDateTimePicker
       .with(this)
       .pickTimeOnly()
       .is24HourView(false)
       .show()
       .subscribe(new Consumer<Date>() {
                   @Override
                   public void accept(@NonNull Date date) throws Exception {
                   		Log.d("Date",date.toString());
                   }
                 });
```
