/*
 * This file is part of the Percentage package.
 *
 * Copyright (c) Eriksen Costa <eriksencosta@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
