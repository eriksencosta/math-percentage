package com.eriksencosta.math.percentage

/**
 * Returns a detailed string representation of the object.
 *
 * @receiver [Percentage]
 * @return A formatted string.
 */
public fun Percentage.toDetailedString(): String = "Percentage[value=[${formattedValue()}] rounding=[$rounding]]"
