# LLog
Android log util

### Features
* Log without tag
* Locate code from log
* Support for json log

### Usage
```
    LLog.e(log);
    LLog.e(tag, log);
    LLog.d(log);
    LLog.d(tag,log);
    LLog.json(json);
    LLog.json(tag,json);
```

### Proguard
Gradle config:
```gradle
    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
```
Proguard rules:
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