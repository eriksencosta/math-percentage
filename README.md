# Percentage

![Codacy grade](https://img.shields.io/codacy/grade/f4cafede889843cf9bf7196689fa6126)
![Codacy coverage](https://img.shields.io/codacy/coverage/f4cafede889843cf9bf7196689fa6126)

Percentage calculations made easy.

## Installation

Add Percentage to your Gradle build script:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.eriksencosta.math:percentage:0.1.0")
}
```

If you're using Maven, add to your POM xml file:

```xml
<dependency>
    <groupId>com.eriksencosta.math</groupId>
    <artifactId>percentage</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

The library provides the `Percentage` type: an immutable and thread-safe class that makes percentage calculations easy.

```kotlin
150 * 5.5.percent()          // 8.25
150 decreaseBy 5.5.percent() // 141.75
150 increaseBy 5.5.percent() // 158.25
```

Under the hood, all calculations are done by the immutable and thread-safe `Percentage` class. You can always query for
the percentage's original value, and its decimal representation (i.e., its value divided by 100):

```kotlin
val percentage = 5.5.percent()
percentage.decimal // 0.055
percentage.value   // 5.5
```

### Rounding

If you need to round the resulting calculations using a `Percentage`, just pass an instance of the `Rounding` class to 
the `percent()` method. Use the `Rounding.to()` factory method to create the object, passing the number of decimal 
places and the desired rounding mode:

```kotlin
val percentage = 11.603773.percent()
val roundsFloor = 11.603773.percent(Rounding.to(2, RoundingMode.FLOOR))

val value = 127
value * percentage  // 14.73679171
value * roundsFloor // 14.73
```

The rounding mode to use is defined by one of `RoundingMode` enum values. If you need to use `HALF_EVEN`, just pass the
number of desired decimal places:

```kotlin
val roundsHalfUp = 11.603773.percent(2)
value * roundsHalfUp // 14.74
```

### Other utilities

#### Create a Percentage based on a ratio

To create a `Percentage` based on a ratio (e.g. 1/2, 1/3, 1/4, and so on), use the `ratioOf()` function:

```kotlin
1 ratioOf 4 // 25%
1 ratioOf 3 // 33.33%
```

The function also has overloaded versions to control the rounding strategy of the returned `Percentage` object:

```kotlin
// rounds using 2 decimal places and with RoundingMode.HALF_EVEN
1.ratioOf(3, 2)

// rounds using 2 decimal places and with RoundingMode.UP
1.ratioOf(3, Rounding.to(2, RoundingMode.UP))
```

#### Calculate the relative change as a Percentage for two numbers

To calculate the relative change between two numbers, use the `relativeChange()` function:

```kotlin
1 relativeChange 3 // 200%
3 relativeChange 1 // -66.67%
```

The function also has overloaded versions to control the rounding strategy of the returned `Percentage` object:

```kotlin
// rounds using 2 decimal places and with RoundingMode.HALF_EVEN
3.relativeChange(1, 2)

// rounds using 2 decimal places and with RoundingMode.UP
3.relativeChange(1, Rounding.to(2, RoundingMode.UP))
```

#### Calculate the base value of a number when it's a given Percentage

To calculate the base value of a number when it's a given Percentage, use the `valueWhen()` function:

```kotlin
5 valueWhen 20.percent() // 25.0
```

In other words, the function helps to answer the question "5 is 20% of what number?"

### Code examples

The [UsageExamples](./percentage/src/test/kotlin/com/eriksencosta/math/percentage/UsageExamples.kt) file has more
examples of calculations using the Percentage library.

## API documentation

Read the [API documentation](https://blog.eriksen.com.br/opensource/math-percentage/) for further details.

## License

[The Apache Software License, Version 2.0](https://choosealicense.com/licenses/apache/)
