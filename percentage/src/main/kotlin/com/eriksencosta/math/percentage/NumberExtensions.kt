@file:JvmName("NumberPercentage")
@file:Suppress("TooManyFunctions")

package com.eriksencosta.math.percentage

import com.eriksencosta.math.common.PreciseRounding
import com.eriksencosta.math.common.Rounding
import java.math.RoundingMode

/**
 * Creates a [Percentage] based on this number. Calculations using it won't be rounded.
 *
 * @receiver [Number]
 * @return The [Percentage] value of this number.
 */
public fun Number.percent(): Percentage = percent(Rounding.no())

/**
 * Creates a [Percentage] based on this number. Calculations using it will be rounded.
 *
 * @receiver [Number]
 * @param[precision] The precision scale to round percentage calculations. The rounding is done using the
 * [PreciseRounding] policy (i.e. rounds using [RoundingMode.HALF_UP] mode).
 * @return The [Percentage] value of this number.
 */
public infix fun Number.percent(precision: Int): Percentage = percent(Rounding.to(precision))

/**
 * Creates a [Percentage] based on this number. Calculations using it will be rounded.
 *
 * @receiver [Number]
 * @param[rounding] The [Rounding] strategy to round the percentage calculations.
 * @return The [Percentage] value of this number.
 */
public infix fun Number.percent(rounding: Rounding): Percentage = Percentage.of(this, rounding)

/**
 * Alias to [Number.percent].
 */
public fun Number.toPercentage(): Percentage = percent()

/**
 * Alias to [Number.percent].
 */
public infix fun Number.toPercentage(precision: Int): Percentage = percent(precision)

/**
 * Alias to [Number.percent].
 */
public infix fun Number.toPercentage(rounding: Rounding): Percentage = percent(rounding)

/**
 * Creates a [Percentage] based on a number returned by the lambda function. Calculations using it won't be rounded.
 *
 * @receiver (() -> Number)
 * @return A [Percentage].
 */
public fun (() -> Number).percent(): Percentage = Percentage.of(this())

/**
 * Creates a [Percentage] based on a number returned by the lambda function. Calculations using it will be rounded.
 *
 * @receiver (() -> Number)
 * @param[precision] The precision scale to round percentage calculations. The rounding is done using the
 *   [PreciseRounding] policy (i.e. rounds using [RoundingMode.HALF_UP] mode).
 * @return A [Percentage].
 */
public fun (() -> Number).percent(precision: Int): Percentage = Percentage.of(this(), precision)

/**
 * Creates a [Percentage] based on a number returned by the lambda function. Calculations using it will be rounded.
 *
 * @receiver (() -> Number)
 * @param[rounding] The [Rounding] strategy to round the percentage calculations.
 * @return A [Percentage].
 */
public fun (() -> Number).percent(rounding: Rounding): Percentage = Percentage.of(this(), rounding)

/**
 * Creates a [Percentage] based on the ratio of this number and other number. Calculations using it won't be rounded.
 * Example:
 *
 * ```
 * 1.ratioOf(5) // 20%
 * ```
 *
 * Or using the infix notation:
 *
 * ```
 * 1 ratioOf 5
 * ```
 *
 * @receiver [Number]
 * @param[other] The other number.
 * @throws[IllegalArgumentException] When [other] is zero.
 * @return A [Percentage] that represents the ratio of this number and the [other] number.
 * @see Percentage.ratioOf
 */
public infix fun Number.ratioOf(other: Number): Percentage = ratioOf(other, Rounding.no())

/**
 * Creates a [Percentage] based on the ratio of this number and other number. Calculations using it will be rounded.
 * Example:
 *
 * ```
 * 1.ratioOf(5, 2) // 20%
 * ```
 *
 * @receiver [Number]
 * @param[other] The other number.
 * @param[precision] The precision scale to round percentage calculations. The rounding is done using the
 *   [PreciseRounding] policy (i.e. rounds using [RoundingMode.HALF_UP] mode).
 * @throws[IllegalArgumentException] When [other] is zero.
 * @return A [Percentage] that represents the ratio of this number and the [other] number.
 * @see Percentage.ratioOf
 */
public fun Number.ratioOf(other: Number, precision: Int): Percentage = ratioOf(other, Rounding.to(precision))

/**
 * Creates a [Percentage] based on the ratio of this number and other number. Calculations using it will be rounded.
 * Example:
 *
 * ```
 * 1.ratioOf(5, Rounding.to(2, RoundingMode.HALF_DOWN)) // 20%
 * ```
 *
 * @receiver [Number]
 *
 * @param[other] The other number.
 * @param[rounding] The [Rounding] strategy to round the percentage calculations.
 * @throws[IllegalArgumentException] When [other] is zero.
 * @return A [Percentage] that represents the ratio of this number and the [other] number.
 * @see Percentage.ratioOf
 */
public fun Number.ratioOf(other: Number, rounding: Rounding): Percentage = Percentage.ratioOf(this, other, rounding)

/**
 * Creates a [Percentage] which represents the relative change of this number to another number. Calculations using it
 * won't be rounded. Example:
 *
 * ```
 * 1.changeOf(5) // 400%
 * ```
 *
 * Or using the infix notation:
 *
 * ```
 * 1 changeOf 5
 * ```
 *
 * When this number is zero, an [IllegalArgumentException] exception is thrown as the relative change for this case
 * [is not defined](https://en.wikipedia.org/wiki/Relative_change).
 *
 * When both numbers are zero, a `Percentage(0)` is returned meaning no change happened instead of returning a
 * `Percentage(NaN)` (which would happen as a result o dividing 0 by 0, both computationally and
 * [mathematically](https://www.math.utah.edu/~pa/math/0by0.html)).
 *
 * @receiver [Number]
 * @param[other] The other number.
 * @throws[IllegalArgumentException] When this number is zero.
 * @return A [Percentage] that represents the percentage change of this number and the [other] number.
 */
public infix fun Number.relativeChange(other: Number): Percentage = relativeChange(other, Rounding.no())

/**
 * Creates a [Percentage] which represents the relative change of this number to another number. Calculations using it
 * will be rounded. Example:
 *
 * ```
 * 1.changeOf(5, 2) // 400%
 * ```
 *
 * When this number is zero, an [IllegalArgumentException] exception is thrown as the relative change for this case
 * [is not defined](https://en.wikipedia.org/wiki/Relative_change).
 *
 * When both numbers are zero, a `Percentage(0)` is returned meaning no change happened instead of returning a
 * `Percentage(NaN)` (which would happen as a result o dividing 0 by 0, both computationally and
 * [mathematically](https://www.math.utah.edu/~pa/math/0by0.html)).
 *
 * @receiver [Number]
 * @param[other] The other number.
 * @param[precision] The precision scale to round percentage calculations. The rounding is done using the
 *   [PreciseRounding] policy (i.e. rounds using [RoundingMode.HALF_UP] mode).
 * @throws[IllegalArgumentException] When this number is zero.
 * @return A [Percentage] that represents the percentage change of this number and the [other] number.
 */
public fun Number.relativeChange(other: Number, precision: Int): Percentage =
    relativeChange(other, Rounding.to(precision))

/**
 * Creates a [Percentage] which represents the relative change of this number to another number. Calculations using it
 * will be rounded. Example:
 *
 * ```
 * 1.changeOf(5, Rounding.to(2, RoundingMode.HALF_DOWN)) // 400%
 * ```
 *
 * When this number is zero, an [IllegalArgumentException] exception is thrown as the relative change for this case
 * [is not defined](https://en.wikipedia.org/wiki/Relative_change).
 *
 * When both numbers are zero, a `Percentage(0)` is returned meaning no change happened instead of returning a
 * `Percentage(NaN)` (which would happen as a result o dividing 0 by 0, both computationally and
 * [mathematically](https://www.math.utah.edu/~pa/math/0by0.html)).
 *
 * @receiver [Number]
 * @param[other] The other number.
 * @param[rounding] The [Rounding] strategy to round the percentage calculations.
 * @throws[IllegalArgumentException] When this number is zero.
 * @return A [Percentage] that represents the percentage change of this number and the [other] number.
 */
public fun Number.relativeChange(other: Number, rounding: Rounding): Percentage =
    Percentage.relativeChange(this, other, rounding)

/**
 * Calculates the base value of this number for the given [Percentage]. This method helps to answer the question:
 * "5 is 20% of what number?" Example:
 *
 * ```
 * 5.of(Percentage(20)) // 25.0 as 5 is 20% of 25
 * ```
 *
 * Or using the infix notation:
 *
 * ```
 * 5 of 20.percent()
 * ```
 *
 * @receiver [Number]
 * @param[percentage] The [Percentage] value that this number represents.
 * @throws[IllegalStateException] When the [Percentage] value is zero.
 * @return The number that this number represents as the given [Percentage].
 */
public infix fun Number.valueWhen(percentage: Percentage): Double = percentage valueWhen this

/**
 * Multiplies this number by the given [Percentage].
 *
 * @receiver [Number]
 * @param[percentage] The [Percentage] to multiply this number by.
 * @return The resulting value.
 * @see Percentage.times
 */
public operator fun Number.times(percentage: Percentage): Double = percentage * this

/**
 * Increases this number by the given [Percentage].
 *
 * @receiver [Number]
 * @param[percentage] The [Percentage] to increase this number by.
 * @return The resulting value.
 * @see Percentage.increase
 */
public infix fun Number.increaseBy(percentage: Percentage): Number = percentage increase this

/**
 * Decreases this number by the given [Percentage].
 *
 * @receiver [Number]
 * @param[percentage] The [Percentage] to decrease this number by.
 * @return The resulting value.
 * @see Percentage.decrease
 */
public infix fun Number.decreaseBy(percentage: Percentage): Number = percentage decrease this
