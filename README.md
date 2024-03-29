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
    implementation("com.eriksencosta.math:percentage:${percentageVersion}")
}
```

If you're using Maven, add to your POM xml file:

```xml
<dependency>
    <groupId>com.eriksencosta.math</groupId>
    <artifactId>percentage</artifactId>
    <version>${percentageVersion}</version>
</dependency>
```

## Usage

The library provides the `Percentage` type: an immutable and thread-safe class that makes percentage calculations easy.

```kotlin
val percentage = 5.5.percent() // 5.5%

150 * percentage          // 8.25
150 decreaseBy percentage // 141.75
150 increaseBy percentage // 158.25
```

Other convenience functions are also available. To create a percentage based on a ratio, use the `ratioOf` function:

```kotlin
1 ratioOf 4 // 25%
```

To calculate the relative percentage change between two numbers, use the `relativeChange` function:

```kotlin
1 relativeChange 3 // 200%
```

And to discover the base value of a number when its represents a given percentage, use the `valueWhen` function:

```kotlin
5 valueWhen 20.percent() // 25.0 (i.e., 5 is 20% of 25)
```

## API documentation

Read the [API documentation](https://blog.eriksen.com.br/opensource/math-percentage/) for further details.

## License

[The Apache Software License, Version 2.0](https://choosealicense.com/licenses/apache/)
