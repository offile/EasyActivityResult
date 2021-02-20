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

## LICENSE
```
   Copyright 2021 offile

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
