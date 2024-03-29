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
            number = -1,
            isZero = false,
            isNotZero = true,
            isPositive = false,
            isPositiveOrZero = false,
            isNegative = true,
            isNegativeOrZero = true
        ),
        AccessorsTestTable(
            number = 0,
            isZero = true,
            isNotZero = false,
            isPositive = false,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = true
        ),
        AccessorsTestTable(
            number = 1,
            isZero = false,
            isNotZero = true,
            isPositive = true,
            isPositiveOrZero = true,
            isNegative = false,
            isNegativeOrZero = false
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
            Percentage.of(100, Rounding.to(6, RoundingMode.HALF_DOWN)),
            6,
            Percentage.of(100, Rounding.to(6, RoundingMode.HALF_DOWN))
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
            Rounding.to(2, RoundingMode.HALF_DOWN),
            Percentage.of(100, Rounding.to(2, RoundingMode.HALF_DOWN))
        ),
        Triple(
            Percentage.of(100, Rounding.to(2, RoundingMode.UP)),
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
        Pair(minusOne, one),
        Pair(zero, zero),
        Pair(one, one),

        // Precision case
        Pair(Percentage.of(-11.11, 4), Percentage.of(11.11, 4)),

        // Rounding case
        Pair(
            Percentage.of(-11.11, Rounding.to(2, RoundingMode.HALF_DOWN)),
            Percentage.of(11.11, Rounding.to(2, RoundingMode.HALF_DOWN))
        ),
    )

    val unaryMinus = listOf(
        Pair(one, minusOne),
        Pair(zero, zero),
        Pair(minusOne, one),

        // Precision case
        Pair(Percentage.of(11.11, 4), Percentage.of(-11.11, 4)),

        // Rounding case
        Pair(
            Percentage.of(11.11, Rounding.to(2, RoundingMode.HALF_DOWN)),
            Percentage.of(-11.11, Rounding.to(2, RoundingMode.HALF_DOWN))
        ),
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
}

internal data class AccessorsTestTable(
    val number: Number,
    val isZero: Boolean,
    val isNotZero: Boolean,
    val isPositive: Boolean,
    val isPositiveOrZero: Boolean,
    val isNegative: Boolean,
    val isNegativeOrZero: Boolean
)

internal data class Quadruple<out A, out B, out C, out D>(val first: A, val second: B, val third: C, val fourth: D) {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}
