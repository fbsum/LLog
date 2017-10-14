[![](https://jitpack.io/v/fbsum/LLog.svg)](https://jitpack.io/#fbsum/LLog)

# LLog
Android log util

### Features
* Log without tag
* Locate code from log
* Support for json log

### Dependency
Step 1. Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```
dependencies {
    compile 'com.github.fbsum:LLog:1.0.0'
}
```

### Usage
```
    LLog.init(true);
    LLog.e(log);
    LLog.e(tag, log);
    LLog.d(log);
    LLog.d(tag,log);
    LLog.json(json);
    LLog.json(tag,json);
```
![result](http://7xsi11.com1.z0.glb.clouddn.com/llog_test_result.png)

### Proguard (Optional)
Add gradle config:
```gradle
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
```
Add proguard rules:
```
# Delete LLog code
-assumenosideeffects class com.fbsum.android.llog.LLog {
    public static void init(...);
    public static void d(...);
    public static void e(...);
    public static void json(...);
}
```

### Thanks
https://github.com/ZhaoKaiQiang/KLog
