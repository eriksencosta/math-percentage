# Module Percentage

Percentage calculations made easy:

```kotlin
150 * 5.5.percent()          // 8.25
150 decreaseBy 5.5.percent() // 141.75
150 increaseBy 5.5.percent() // 158.25
```

Under the hood, all calculations are done by the immutable and thread-safe
[Percentage][com.eriksencosta.math.percentage.Percentage] class. You can always query for the percentage's original
value, and its decimal representation (i.e., its value divided by 100):

```kotlin
val percentage = 5.5.percent()
percentage.decimal // 0.055
percentage.value   // 5.5
```

## Installation and documentation

The [installation procedures](https://github.com/eriksencosta/math-percentage#installation) and the
[how-to-use](https://github.com/eriksencosta/math-percentage#usage) documentation can be read in the
[project repository on GitHub](https://github.com/eriksencosta/math-percentage).

# Package com.eriksencosta.math.percentage

Core functions and types.
