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
