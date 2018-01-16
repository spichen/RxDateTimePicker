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
      .flatMap { date -> RxDateConverters.toString(date,"dd-MM-yyyy") }
      .subscribe { date -> tv_date_only.text = date }
```
