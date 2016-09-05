# Toolbelt [![Build Status](https://travis-ci.org/mehmetakiftutuncu/Toolbelt.svg?branch=master)](https://travis-ci.org/mehmetakiftutuncu/Toolbelt) [![Download](https://api.bintray.com/packages/mehmetakiftutuncu/maven/toolbelt/images/download.svg)](https://bintray.com/mehmetakiftutuncu/maven/toolbelt/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Toolbelt-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3989)
Toolbelt is an Android library for common tools and utilities for optional values, easier logging and operations on ```String```s. I need these mostly boilerplate stuff for almost all of my Android projects so I decided to serve them as a library.

## How to Include In Your Project?
Toolbelt is served on ```Bintray``` and ```jCenter```. So, simply add following

```gradle
compile 'com.github.mehmetakiftutuncu:toolbelt:latestVersion'
```

inside ```dependencies``` in ```build.gradle``` file in your application module. If somehow dependency resolution fails, you should also add the following repository

```gradle
maven {url 'https://dl.bintray.com/mehmetakiftutuncu/maven'}
```

inside ```repositories```. Don't forget to replace ```latestVersion``` with the latest version number found in the title.

## What's In It?
### 1. Optional
It represents an optional value that may or may not exist. This is an attempt to avoid using ```null```s as they are not safe.

```java
// Creates an existing optional
Optional<String> optional1 = Optional.with("Hello"); // Short for Optional.<String>with();

// Creates an empty optional
Optional<Integer> optional2 = Optional.empty(); // Short for Optional.<Integer>empty();

// Also creates an empty optional if the passed value is null
Optional<List<String>> optional3 = Optional.with(null);

optional1.get();              // Yields "Hello"
optional2.get();              // Throws a NoSuchElementException because value does not exist
optional1.getOrElse("World"); // Yields "Hello"
optional3.getOrElse("World"); // Yields "World"
optional1.isDefined();        // Yields true
optional2.isDefined();        // Yields false
optional1.isEmpty();          // Yields false
optional2.isEmpty();          // Yields true

Optional<String> alternative = Optional.with("World");

optional1.or(alternative); // Yields optional1 itself because value exists in optional1
optional2.or(alternative); // Yields passed alternative because value does not exist in optional2

// Here is an example use case below that extracts the extension of from a file name.
// Instead of using getExtensionUnsafe dealing with null values, use getExtensionSafe
// so whoever calls it will know if extension exists or not without dealing with nulls.
// Of course this is just a demonstration, these methods could have been improved greatly.

public String getExtensionUnsafe(String fileName) {
  if (fileName == null) return null;
  
  String[] parts = fileName.split("\\.");
  
  return (parts.length == 0) ? null : parts[parts.length - 1]; 
}

public Optional<String> getExtensionSafe(String fileName) {
  String[] parts = Optional.with(fileName).getOrElse("").split("\\.");
  
  return (parts.length == 0) ? Option.<String>empty() : Optional.with(parts[parts.length - 1]);
}
```

### 2. Log
It wraps ```android.util.Log``` methods to get dynamic tags using the caller reference and supports message formatting as done in ```String.format()```. It also simplifies log levels to just ```DEBUG```, ```WARN``` and ```ERROR```.

```java
// Debug level log methods
public static void debug(String tag, String message, Object... args)
public static void debug(Class<?> caller, String message, Object... args)

// Warn level log methods
public static void warn(String tag, String message, Object... args)
public static void warn(Class<?> caller, String message, Object... args)

// Error level log methods with and without a Throwable
public static void error(String tag, String message, Object... args)
public static void error(String tag, Throwable throwable, String message, Object... args)
public static void error(Class<?> caller, String message, Object... args)
public static void error(Class<?> caller, Throwable throwable, String message, Object... args)

/* Example call with dynamic tag from a non-static scope where 'this' is accessible, for example in an instance method of class Foo
 *
 * This would log following with debug level:
 * 
 * Tag | Message
 * ----|--------
 * Foo | Doing some work with 'test' and '123'...
 */
Log.debug(this, "Doing some work with '%s' and '%d'...", "test", 123);

/* Example call with dynamic tag from a static scope where 'this' is inaccessible, for example in a static method of class Bar
 *
 * This would log following with warn level:
 * 
 * Tag | Message
 * ----|--------
 * Bar | Doing some work with 'test' and '123'...
 */
Log.warn(Bar.class, "Doing some work with '%s' and '%d'...", "test", 123);
```

### 3. StringUtilities
Name says it all.

```java
StringUtilities.isEmpty(null);  // Yields true
StringUtilities.isEmpty("");    // Yields true
StringUtilities.isEmpty("foo"); // Yields false

List<String> list = new ArrayList<>();
list.add("foo");
list.add("bar");
list.add("baz");

// These methods work on any collection extending java.util.Collection
StringUtilities.makeString(list, "['", "', '", "']"); // Yields: "['foo', 'bar', 'baz']"
StringUtilities.makeString(list, ", ");               // Yields: "foo, bar, baz"
```

### 4. Timer
You can use it to time stuff and see how long they take to run.

```java
// Manage start and stop yourself
Timer.start("foo");
// Do some work
long duration = Timer.stop("foo");
printf("Finished in %d milliseconds!", duration);

// Or wrap a block of work and time it automatically
Timer.ActionResult<Double> piResult = Timer.time(new Timer.Action<Double>() {
    @Override public Double perform() {
        // Perform some advanced math here to calculate the value of PI
        return 3.14;
    }
});
printf("Calculated PI as %f and it took %d milliseconds!", piResult.result, piResult.duration);
```

## Contribution
Please feel free to and contribute. I'd love to hear your feedback, issue reports and pull requests.

## License
Toolbelt is licensed under Apache License Version 2.0.

```
Copyright (C) 2016 Mehmet Akif Tütüncü

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
