Activity Result Helper
===================
[![Language](https://img.shields.io/badge/compatible-java%20%7C%20kotlin-brightgreen.svg)](https://www.github.com/offile/ActivityResultHelper)

Use ActivityResultHelper to receive activity results directly after **startActivityForResult** or **requestPermissions** and use callbacks，
no need to override any methods

## Setup

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

include the following in your app module build.gradle file:

this Tag is:
[![](https://jitpack.io/v/offile/ActivityResultHelper.svg)](https://jitpack.io/#offile/ActivityResultHelper)

```gradle
dependencies {
    implementation "com.github.offile:ActivityResultHelper:Tag"
}
```

## Usage

Create a `ActivityResultHelper` instance :
```kotlin
val activityResultHelper = ActivityResultHelper(this) // where this is an Activity or Fragment instance
```

use startActivityForResult :
```kotlin
val intent = Intent(this, ResultActivity::class.java)
activityResultHelper.startActivityForResult(intent){ resultCode, data ->
    // process result
}
```

request permissions :
```kotlin
activityResultHelper.requestPermissions(
    Manifest.permission.CALL_PHONE,
    Manifest.permission.CAMERA
){ permissions, grantResults ->
    // process result
}
```
