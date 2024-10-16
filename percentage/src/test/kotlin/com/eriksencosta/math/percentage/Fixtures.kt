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

package com.eriksencosta.math.percentage

import com.eriksencosta.math.common.Rounding
import java.math.RoundingMode

internal object Fixtures {
    private val ceiling = RoundingMode.CEILING
    private val up = RoundingMode.UP
    private val down = RoundingMode.DOWN
    private val floor = RoundingMode.FLOOR
    private val halfUp = RoundingMode.HALF_UP
    private val halfDown = RoundingMode.HALF_DOWN
    private val halfEven = RoundingMode.HALF_EVEN

    private val zero = Percentage.of(0)
    private val one = Percentage.of(1)
    private val minusOne = Percentage.of(-1)

    val accessors = listOf(
        AccessorsTestTable(
            percentage = Percentage.of(-1),
            isZero = false,
            isNotZero = true,
            isPositive = false,
            isPositiveOrZero = false,
            isNegative = true,
            isNegativeOrZero = true,
            isOneHundred = false,
            isNotOneHundred = true,
            hasRounding = false,
        ),
        AccessorsTestTable(
            percentage = Percentage.of(0),
            isZero = true,
            isNotZero = false,
            isPositive = false,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = true,
            isOneHundred = false,
            isNotOneHundred = true,
            hasRounding = false,
        ),
        AccessorsTestTable(
            percentage = Percentage.of(1),
            isZero = false,
            isNotZero = true,
            isPositive = true,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = false,
            isOneHundred = false,
            isNotOneHundred = true,
            hasRounding = false,
        ),
        AccessorsTestTable(
            percentage = Percentage.of(100),
            isZero = false,
            isNotZero = true,
            isPositive = true,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = false,
            isOneHundred = true,
            isNotOneHundred = false,
            hasRounding = false,
        ),
        AccessorsTestTable(
            percentage = Percentage.of(-100),
            isZero = false,
            isNotZero = true,
            isPositive = false,
            isPositiveOrZero = false,
            isNegative = true,
            isNegativeOrZero = true,
            isOneHundred = true,
            isNotOneHundred = false,
            hasRounding = false,
        ),
        AccessorsTestTable(
            percentage = Percentage.of(100.001, Rounding.to(2)),
            isZero = false,
            isNotZero = true,
            isPositive = true,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = false,
            isOneHundred = false,
            isNotOneHundred = true,
            hasRounding = true,
        )
    )

    val ratioOf = listOf(
        Triple(0, 1, Percentage.of(0)),
        Triple(1, 2, Percentage.of(50)),
        Triple(1, -2, Percentage.of(-50)),
        Triple(-1, -2, Percentage.of(50)),
    )

    val ratioOfWithPrecision = listOf(
        Quadruple(1, 2, 1, Percentage.of(50, 1)),
        Quadruple(1, -2, 2, Percentage.of(-50, 2)),
        Quadruple(-1, -2, 3, Percentage.of(50, 3)),
    )

    val ratioOfWithRounding = listOf(
        Quadruple(1, 2, Rounding.no(), Percentage.of(50, Rounding.no())),
        Quadruple(1, -2, Rounding.to(2, up), Percentage.of(-50, Rounding.to(2, up))),
        Quadruple(-1, -2, Rounding.to(3, down), Percentage.of(50, Rounding.to(3, down))),
    )

    val relativeChange = listOf(
        Triple(50, -250, Percentage.of(-600)),
        Triple(40, -80, Percentage.of(-300)),
        Triple(10, -10, Percentage.of(-200)),
        Triple(-20, -40, Percentage.of(-100)),
        Triple(20, 10, Percentage.of(-50)),
        Triple(-10, -10, Percentage.of(0)),
        Triple(10, 15, Percentage.of(50)),
        Triple(10, 20, Percentage.of(100)),
        Triple(-20, 20, Percentage.of(200)),
        Triple(-10, 20, Percentage.of(300)),
        Triple(-5, 25, Percentage.of(600)),

        // Zero cases
        Triple(-10, 0, Percentage.of(100)),
        Triple(10, 0, Percentage.of(-100)),
        Triple(0, 0, Percentage.of(0)),
    )

    val relativeChangeWithPrecision = listOf(
        Quadruple(50, -250, 1, Percentage.of(-600, 1)),
        Quadruple(40, -80, 2, Percentage.of(-300, 2)),
        Quadruple(10, -10, 3, Percentage.of(-200, 3)),
        Quadruple(-20, -40, 4, Percentage.of(-100, 4)),
        Quadruple(20, 10, 5, Percentage.of(-50, 5)),
        Quadruple(-10, -10, 6, Percentage.of(0, 6)),
        Quadruple(10, 15, 7, Percentage.of(50, 7)),
        Quadruple(10, 20, 8, Percentage.of(100, 8)),
        Quadruple(-20, 20, 9, Percentage.of(200, 9)),
        Quadruple(-10, 20, 10, Percentage.of(300, 10)),
        Quadruple(-5, 25, 11, Percentage.of(600, 11)),

        // Zero cases
        Quadruple(-10, 0, 12, Percentage.of(100, 12)),
        Quadruple(10, 0, 13, Percentage.of(-100, 13)),
        Quadruple(0, 0, 14, Percentage.of(0, 14)),
    )

    val relativeChangeWithRounding = listOf(
        Quadruple(
            50,
            -250,
            Rounding.to(1),
            Percentage.of(-600, Rounding.to(1))
        ),
        Quadruple(
            40,
            -80,
            Rounding.to(2, ceiling),
            Percentage.of(-300, Rounding.to(2, ceiling))
        ),
        Quadruple(
            10,
            -10,
            Rounding.to(3, up),
            Percentage.of(-200, Rounding.to(3, up))
        ),
        Quadruple(
            -20,
            -40,
            Rounding.to(4, down),
            Percentage.of(-100, Rounding.to(4, down))
        ),
        Quadruple(
            20,
            10,
            Rounding.to(5, floor),
            Percentage.of(-50, Rounding.to(5, floor))
        ),
        Quadruple(
            -10,
            -10,
            Rounding.to(6, halfUp),
            Percentage.of(0, Rounding.to(6, halfUp))
        ),
        Quadruple(
            10,
            15,
            Rounding.to(7, halfDown),
            Percentage.of(50, Rounding.to(7, halfDown))
        ),
        Quadruple(
            10,
            20,
            Rounding.to(8, halfEven),
            Percentage.of(100, Rounding.to(8, halfEven))
        ),

        // Zero cases
        Quadruple(
            -10,
            0,
            Rounding.to(12, halfUp),
            Percentage.of(100, Rounding.to(12, halfUp))
        ),
        Quadruple(
            10,
            0,
            Rounding.to(13, halfDown),
            Percentage.of(-100, Rounding.to(13, halfDown))
        ),
        Quadruple(
            0,
            0,
            Rounding.to(14, halfEven),
            Percentage.of(0, Rounding.to(14, halfEven))
        ),
    )

    val withPrecision = listOf(
        Triple(Percentage.of(100), 2, Percentage.of(100, 2)),
        Triple(Percentage.of(100), 4, Percentage.of(100, 4)),
        Triple(Percentage.of(100, 4), 4, Percentage.of(100, 4)),
        Triple(Percentage.of(100, 4), 6, Percentage.of(100, 6)),
        Triple(Percentage.of(100, 6), 6, Percentage.of(100, 6)),
        Triple(
            Percentage.of(100, Rounding.to(6, halfDown)),
            6,
            Percentage.of(100, Rounding.to(6, halfDown))
        ),
    )

    val withRounding = listOf(
        Triple(
            Percentage.of(100),
            Rounding.no(),
            Percentage.of(100)
        ),
        Triple(
            Percentage.of(100),
            Rounding.to(2, halfDown),
            Percentage.of(100, Rounding.to(2, halfDown))
        ),
        Triple(
            Percentage.of(100, Rounding.to(2, halfUp)),
            Rounding.no(),
            Percentage.of(100, Rounding.no())
        ),
    )

    val valueWhen = listOf(
        Triple(Percentage.of(125), 25.0, 20.0),
        Triple(Percentage.of(60), 45.0, 75.0),
        Triple(Percentage.of(10), 10.0, 100.0),
        Triple(Percentage.of(20), 25.0, 125.0),

        Triple(Percentage.of(-125), 25.0, -20.0),
        Triple(Percentage.of(-60), 45.0, -75.0),
        Triple(Percentage.of(-10), 10.0, -100.0),
        Triple(Percentage.of(-20), 25.0, -125.0),

        Triple(Percentage.of(-125), -25.0, 20.0),
        Triple(Percentage.of(-60), -45.0, 75.0),
        Triple(Percentage.of(-10), -10.0, 100.0),
        Triple(Percentage.of(-20), -25.0, 125.0),

        Triple(Percentage.of(10), 0.0, 0.0),
        Triple(Percentage.of(50), 0.0, 0.0),
        Triple(Percentage.of(100), 0.0, 0.0),

        // Precision and rounding cases
        Triple(Percentage.of((77 / 3.0 * 100)), 33.3, 1.2974025974025971),
        Triple(Percentage.of((77 / 3.0 * 100), Rounding.to(4)), 33.3, 1.2974),
        Triple(Percentage.of((77 / 3.0 * 100), Rounding.to(4, up)), 33.3, 1.2975),
    )

    val unaryPlus = listOf(
        minusOne to one,
        zero to zero,
        one to one,

        // Precision case
        Percentage.of(-11.11, 4) to Percentage.of(11.11, 4),

        // Rounding case
        Percentage.of(-11.11, Rounding.to(2, halfDown)) to
            Percentage.of(11.11, Rounding.to(2, halfDown))
    )

    val unaryMinus = listOf(
        one to minusOne,
        zero to zero,
        minusOne to one,

        // Precision case
        Percentage.of(11.11, 4) to Percentage.of(-11.11, 4),

        // Rounding case
        Percentage.of(11.11, Rounding.to(2, halfDown)) to
            Percentage.of(-11.11, Rounding.to(2, halfDown))
    )

    val times = listOf(
        Triple(100.0, Percentage.of(100), 100.0),
        Triple(500.0, Percentage.of(20), 100.0),
        Triple(1.0, Percentage.of(-100), -1.0),
        Triple(100.0, Percentage.of(0), 0.0),
        Triple(0.0, Percentage.of(100), 0.0),

        // Precision and rounding cases
        Triple(33.3, Percentage.of((77 / 3.0 * 100)), 854.7),
        Triple(33.3, Percentage.of((77 / 3.0 * 100), Rounding.to(4)), 854.7),
        Triple(33.3, Percentage.of((77 / 3.0 * 100), Rounding.to(4, up)), 854.7),
    )

    val increase = listOf(
        Triple(100.0, Percentage.of(100), 200.0),
        Triple(500.0, Percentage.of(20), 600.0),
        Triple(1.0, Percentage.of(-100), 0.0),
        Triple(100.0, Percentage.of(0), 100.0),
        Triple(0.0, Percentage.of(100), 0.0),

        // Precision and rounding cases
        Triple(33.33, Percentage.of((77 / 3.0 * 100)), 888.8000000000002),
        Triple(33.33, Percentage.of((77 / 3.0 * 100), Rounding.to(4)), 888.8),
        Triple(33.33, Percentage.of((77 / 3.0 * 100), Rounding.to(4, up)), 888.8001),
    )

    val decrease = listOf(
        Triple(100.0, Percentage.of(10), 90.0),
        Triple(500.0, Percentage.of(20), 400.0),
        Triple(1.0, Percentage.of(-100), 2.0),
        Triple(100.0, Percentage.of(0), 100.0),
        Triple(0.0, Percentage.of(100), 0.0),

        // Precision and rounding cases
        Triple(33.33, Percentage.of((77 / 3.0 * 100)), -822.1400000000001),
        Triple(33.33, Percentage.of((77 / 3.0 * 100), Rounding.to(4)), -822.14),
        Triple(33.33, Percentage.of((77 / 3.0 * 100), Rounding.to(4, up)), -822.1401),
    )

    val collections = listOf(
        listOf(
            Percentage.of(5),
            Percentage.of(15, 2),
            Percentage.of(30, 4),
        ) to Percentage.of(50),

        listOf(
            Percentage.of(50, 2),
            Percentage.of(30, 4),
            Percentage.of(20, 6),
        ) to Percentage.of(100, 2),

        listOf(
            Percentage.of(25, Rounding.to(2, up)),
            Percentage.of(50, Rounding.no()),
            Percentage.of(75, Rounding.to(4, up)),
        ) to Percentage.of(150, Rounding.to(2, up)),
    )

    val strings = listOf(
        Percentage.of(-10) to "-10%",
        Percentage.of(1) to "1%",
        Percentage.of(4.5, 2) to "4.5%",
        Percentage.of(12.75, 4) to "12.75%",
        Percentage.of(100) to "100%",
        Percentage.of((1 / 3.0) * 100) to "33.33333333333333%",
        Percentage.of(-100.0 / 3.0) to "-33.333333333333336%",
        Percentage.of(-100.0 / 3.0) to "-33.333333333333336%",
        Percentage.of(513.0 / 53.0) to "9.679245283018869%",
    )

    val detailedStrings = listOf(
        Percentage.of(-10) to
            "Percentage[value=[-10] rounding=[NoRounding]]",
        Percentage.of(1) to
            "Percentage[value=[1] rounding=[NoRounding]]",
        Percentage.of(4.5, 2) to
            "Percentage[value=[4.5] rounding=[PreciseRounding[2 HALF_EVEN]]]",
        Percentage.of(12.75, Rounding.to(4, halfDown)) to
            "Percentage[value=[12.75] rounding=[PreciseRounding[4 HALF_DOWN]]]",
        Percentage.of(100) to
            "Percentage[value=[100] rounding=[NoRounding]]",
        Percentage.of((1 / 3.0) * 100) to
            "Percentage[value=[33.33333333333333] rounding=[NoRounding]]",
        Percentage.of(-100.0 / 3.0) to
            "Percentage[value=[-33.333333333333336] rounding=[NoRounding]]",
        Percentage.of(-100.0 / 3.0) to
            "Percentage[value=[-33.333333333333336] rounding=[NoRounding]]",
        Percentage.of(513.0 / 53.0) to
            "Percentage[value=[9.679245283018869] rounding=[NoRounding]]",
    )
}

internal data class AccessorsTestTable(
    val percentage: Percentage,
    val isZero: Boolean,
    val isNotZero: Boolean,
    val isPositive: Boolean,
    val isPositiveOrZero: Boolean,
    val isNegative: Boolean,
    val isNegativeOrZero: Boolean,
    val isOneHundred: Boolean,
    val isNotOneHundred: Boolean,
    val hasRounding: Boolean,
)

internal data class Quadruple<out A, out B, out C, out D>(val first: A, val second: B, val third: C, val fourth: D) {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}
