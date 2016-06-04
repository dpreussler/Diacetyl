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

 Diacetyl initializes any android view for a given class with a Mock.
 There is no need to run Butterknife logic.
 It will use mocktoid mocks if available else simple mockito mocks

 It will ignore fields that already have a value
 It is not bound to Butterknife, it does not check for annotations.

You can pass multiple classes into, for viewholder for example.
```java
	 Diacetyl.butterForTests(viewHolder1, viewHolder2);
```

Gradle
======

```groovy
testCompile 'de.jodamob.android:Diacetyl:0.5'

```

TODO:
=====
* performance measurements and maybe optimization


Licensed under Apache 2.0
(c) 2016 Danny Preussler

Uses SuperReflect based on jOOR, 2011-2013, Lukas Eder, lukas.eder@gmail.com, Apache 2.0