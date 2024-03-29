package com.eriksencosta.math.percentage

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

internal class UsageExamples {
    @Suppress("LongMethod")
    @TestFactory
    fun `Run expressions using regular methods or extensions functions`() = listOf(
        Quadruple(
            "100 * 50%",
            { Percentage.of(50) * 100 },
            { 100 * 50.percent() },
            50.0
        ),
        Quadruple(
            "100 increase by 50%",
            { Percentage.of(50) increase 100 },
            { 100 increaseBy 50.percent() },
            150.0
        ),
        Quadruple(
            "100 decrease by 50%",
            { Percentage.of(50) decrease 100 },
            { 100 decreaseBy 50.percent() },
            50.0
        ),
        Quadruple(
            "percentage value of 1/4",
            { Percentage.ratioOf(1, 4) },
            { 1 ratioOf 4 },
            Percentage.of(25)
        ),
        Quadruple(
            "base value when 50% is 5",
            { Percentage.of(50) valueWhen 5 },
            { 5 valueWhen 50.percent() },
            10.0
        ),
        Quadruple(
            "relative change from 1 to 4",
            { Percentage.relativeChange(1, 4) },
            { 1 relativeChange 4 },
            Percentage.of(300)
        ),
        Quadruple(
            "50 * 50% increase by 25%",
            { (Percentage.of(50) * 50) increaseBy Percentage.of(25) },
            { (50 * 50.percent()) increaseBy 25.percent() },
            31.25
        ),
        Quadruple(
            "300 * 125% decrease by 8%",
            { Percentage.of(8) decrease Percentage.of(125) * 300 },
            { (300 * 125.percent()) decreaseBy 8.percent() },
            345.0
        ),
        Quadruple(
            "33 increase by 5% then decrease by 5%",
            { Percentage.of(5) decrease (Percentage.of(5) increase 33) },
            { 33 increaseBy 5.percent() decreaseBy 5.percent() },
            32.9175
        ),
        Quadruple(
            "33 increase by 5[.3]% then decrease by 5[.1]%",
            { Percentage.of(5, 1) decrease (Percentage.of(5, 3) increase 33) },
            { 33 increaseBy 5.percent(3) decreaseBy 5.percent(1) },
            32.9
        ),
        Quadruple(
            "(33 increase by 5% decrease by 5%) * 10%",
            { Percentage.of(10) * (Percentage.of(5) decrease (Percentage.of(5) increase 33)) },
            { (33 increaseBy 5.percent() decreaseBy 5.percent()) * 10.percent() },
            3.29175
        ),
        Quadruple(
            "100 * (base value when 80% is 7) increase by 1/4 (25%)", // 7 is 80% of 8.75
            { (Percentage.ratioOf(1, 4)) increase ((Percentage.of(80) valueWhen 7) * 100) },
            { (100 * (7 valueWhen 80.percent())) increaseBy (1 ratioOf 4) },
            1093.75
        ),
        Quadruple(
            "100 * (base value when 80% is 7) increase by 1/4[.1] (30%)", // 7 is 80% of 8.75
            { (Percentage.ratioOf(1, 4, 1)) increase ((Percentage.of(80) valueWhen 7) * 100) },
            { (100 * (7 valueWhen 80.percent())) increaseBy (1.ratioOf(4, 1)) },
            1093.8
        ),
        Quadruple(
            "(100 increase by relative change from 33 to 77) * 10[.2]%",
            { Percentage.of(10, 2) * (Percentage.relativeChange(33, 77) increase 100) },
            { (100 increaseBy (33 relativeChange 77)) * 10.percent(2) },
            23.33
        ),
        Quadruple(
            "(100 increase by relative change[.4] from 33 to 77) * 10[.2]%",
            { Percentage.of(10, 2) * (Percentage.relativeChange(33, 77, 4) increase 100) },
            { (100 increaseBy (33.relativeChange(77, 4))) * 10.percent(2) },
            23.33
        )
    )
        .map { (case, regularMethods, extensionFunctions, expected) ->
            DynamicTest.dynamicTest(
                "$case = $expected"
            ) {
                assertEquals(expected, regularMethods())
                assertEquals(expected, extensionFunctions())
            }
        }
}
