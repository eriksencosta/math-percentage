@file:JvmName("PercentageEx")

package com.eriksencosta.math.percentage

/**
 * Sums all [Percentage] elements in the collection. The rounding strategy of the first element is preserved in the
 * resulting [Percentage].
 *
 * @receiver [Iterable]
 * @return The resulting [Percentage].
 */
public fun Iterable<Percentage>.sum(): Percentage = (this as Collection<Percentage>).sum()

/**
 * Sums all [Percentage] elements in the collection. The rounding strategy of the first element is preserved in the
 * resulting [Percentage].
 *
 * @receiver [Collection]
 * @return The resulting [Percentage].
 */
public fun Collection<Percentage>.sum(): Percentage = sumOf { it.value }.percent(this.first().rounding)

/**
 * Returns a detailed string representation of the object.
 *
 * @receiver [Percentage]
 * @return A formatted string.
 */
public fun Percentage.toDetailedString(): String = "Percentage[value=[${formattedValue()}] rounding=[$rounding]]"
