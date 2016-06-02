[![Build Status](https://travis-ci.org/dpreussler/Diacetyl.svg?branch=master)](https://travis-ci.org/dpreussler/Diacetyl)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/Diacetyl/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.jodamob.android/Diacetyl)


DRAFT VERSION


Diacetyl
==========
 Diacetyl ... is added to some foods to impart its buttery flavor.
 (source: wikipedia)

 For Junit tests initializes any android view for a given class to a mock 
 (to replace Butterknife or similar while Testing)


Gradle
======

```groovy
testCompile 'de.jodamob.android:Diacetyl:0.5'
 
```


Example:
========
```java
class MyActivtiy {
...
@Bind TextView textView;
@Bind EditText editText;
...

class MyActivityTest {

  @Test 
  public void test() {
 	 MyActity tested = new MyActity();
	 Diacetyl.butterForTest(tested);
```

Licensed under Apache 2.0
(c) 2015 Danny Preussler

Uses SuperReflect based on jOOR, 2011-2013, Lukas Eder, lukas.eder@gmail.com