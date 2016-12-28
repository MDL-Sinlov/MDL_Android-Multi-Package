[TOC]

# Android multipackage

Android provides :
- ~~Full method count 00~~

Less Runtime :
- minSdkVersion 9
- gradle or maven
- jar [You can Download just like this Path]((https://github.com/MDL-Sinlov/MDL-Android-Repo/raw/master/mvn-repo/mdl/sinlov/android/))

> eclipse just use every repo at version `multipackage-x.x.x-jarLib.jar`

Project Runtime:
- Android Studio 2.2
- appcompat-v7:23.4.0
- Gradle 2.14.1
- com.android.tools.build:gradle:2.2.0
- minSdkVersion 15

# Last Version Info

- version 0.0.1
- repo at https://github.com/MDL-Sinlov/MDL-Android-Repo

# Dependency

at root project `build.gradle`

```gradle
repositories {
    maven {
        url 'https://raw.githubusercontent.com/MDL-Sinlov/MDL-Android-Repo/master/mvn-repo/'
    }
    jcenter()
    ...
}
```

in module `build.gradle`

```gradle
dependencies {
    compile 'mdl.sinlov.android:multipackage:0.0.1'
}
```

# Usage

- init

```java
boolean isSuccess = ChannelContent.getInstance().initChannelContent(this.getApplicationContext());
```

- getChannel name

```java
String channel_name = ChannelContent.getInstance().getChannel_name();
```

- getChannel properties

```java
Map<String, String> fullInfo = ChannelContent.getInstance().getChanelInfo("channel");
```

# Principle

apk will not check `META-INF` at package

you can new file at path `./META-INF`

like `pl_channel_` + `channel_name`

> Inner file format is properties!

Android API will read this file

default properties is

|key|value|
|---|-----|
|channel|channel_name|

###License

---

Copyright 2016 sinlovgm@gmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
