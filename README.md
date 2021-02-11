Easy Activity Result
===================
[![Language](https://img.shields.io/badge/compatible-java%20%7C%20kotlin-brightgreen.svg)](https://www.github.com/offile/EasyActivityResult)

Help you simplify the process of using startActivityForResult 
or requestPermissions in the activity (or fragment), and use the callback to receive the result

## Benefits

- No need to override any methods, use fragment proxy
- No need to set annoying requestCode, the library will automatically configure for you

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
[![](https://jitpack.io/v/offile/EasyActivityResult.svg)](https://jitpack.io/#offile/EasyActivityResult)

```gradle
dependencies {
    implementation "com.github.offile:EasyActivityResult:Tag"
}
```

## Usage

Create a `EasyActivityResult` instance :
```kotlin
val easyActivityResult = EasyActivityResult.with(this) // where this is an Activity or Fragment instance
```

use `startActivityForResult` :
```kotlin
val intent = Intent(this, ResultActivity::class.java)
easyActivityResult.startActivityForResult(intent){ resultCode, data ->
    // process result
}
```

use `requestPermissions` :
```kotlin
easyActivityResult.requestPermissions(
    Manifest.permission.CALL_PHONE,
    Manifest.permission.CAMERA
){ permissions, grantResults ->
    // process result
}
```
