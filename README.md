[![Build Status](https://travis-ci.org/dpreussler/Diacetyl.svg?branch=master)](https://travis-ci.org/dpreussler/Diacetyl)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/Diacetyl/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/Diacetyl)


Diacetyl
==========
 Diacetyl ... is added to some foods to impart its buttery flavor.
 (source: wikipedia)

 It adds artifical butterflavor to your Butterknife in test environments.
 When running unit tests you don't need to run Butterknife or similar as this
 might need real views which leads to the use of Robolectric or similar.


```java
class MyButterKnifeActivtiy {
    ...
    @Bind TextView textView;
    @Bind EditText editText;
    ...

class MyButterKnifeActivtiyTest {

  @Test 
  public void test() {
 	 MyButterKnifeActivtiy tested = new MyButterKnifeActivtiy();
	 Diacetyl.butterForTests(tested);
```

 Diacetyl initializes any android view found as a field for a given class with a Mock.
 There is no need to run Butterknife logic.
 It will use mocktoid mocks if available else simple mockito mocks.
 It will look into parent classes too.
 It will ignore fields that already have a value.
 It is not bound to Butterknife, it does not check for annotations and is not looking into your layouts.


You can pass multiple classes into, for viewholder for example.
```java
	 Diacetyl.butterForTests(viewHolder1, viewHolder2);
```

Gradle
------

```groovy
repositories {
    ...
    maven { url 'https://oss.sonatype.org/content/repositories/staging/'}
}
...

dependencies {
    testCompile 'de.jodamob.android:Diacetyl:0.5'
}

```

Todo
-------
* performance measurements and maybe optimization


License
-------

    Copyright 2016 Danny Preussler
    Uses SuperReflect based on jOOR, 2011-2013, Lukas Eder, lukas.eder@gmail.com

    Both licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


