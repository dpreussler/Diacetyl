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


You can pass multiple classes into, for viewholder for example.
```java
	 Diacetyl.butterForTests(viewHolder1, viewHolder2);
```



It tries to use mockitoid mocks if available else simple mocks.
The list of supported views is still limited


TODO:
=====
* tests
* more views
* generic view
* performance measurements and maybe optimization


Licensed under Apache 2.0
(c) 2016 Danny Preussler

Uses SuperReflect based on jOOR, 2011-2013, Lukas Eder, lukas.eder@gmail.com