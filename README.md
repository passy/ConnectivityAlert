ConnectivityAlert
=================

Dagger 2 experiments on Android.


Setup
-----

Create a new Parse app at
[Parse.com](https://www.parse.com/apps/quickstart#analytics/events/mobile/android/native/new)
and copy the keys into `gradle.properties`:

```
parseApplicationId=[your app id]
parseMasterKey=[your master key]
```


Building
--------

Make sure that your `JAVA_HOME` points to a valid **JDK 8** installation.


```
./gradlew :app:installDebug
```
