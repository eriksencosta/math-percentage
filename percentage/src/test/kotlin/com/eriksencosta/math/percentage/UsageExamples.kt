package com.eriksencosta.math.percentage

import kotlin.test.Test
import kotlin.test.assertEquals

class UsageExamples {
    @Test
    fun `Multiply a number`() {
        val result = 100 * 50.percent()
        // or: Percentage.of(50) * 100

        assertEquals(50.0, result)
    }

    @Test
    fun `Increase a number by a given percentage`() {
        val result = 100 increaseBy 50.percent()
        // or: Percentage.of(50).increase(100)

        assertEquals(150.0, result)
    }

    @Test
    fun `Decrease a number by a given percentage`() {
        val result = 100 decreaseBy 50.percent()
        // or: Percentage.of(50).decrease(100)

        assertEquals(50.0, result)
    }

    @Test
    fun `Calculate the percentage value of a ratio`() {
        val result = 1 ratioOf 4
        // or: Percentage.ratioOf(1, 4)

        assertEquals(25.percent(), result)
    }

    @Test
    fun `Calculate the base value of a given number and percentage`() {
        val result = 5 valueWhen 50.percent()
        // or: Percentage.of(50) valueWhen 5

        assertEquals(10.0, result)
    }

    @Test
    fun `Calculate the relative percentage change between two numbers`() {
        val result = 1 relativeChange 4
        // or: Percentage.relativeChange(1, 4)

        assertEquals(300.percent(), result)
    }

    @Test
    @Suppress("CyclomaticComplexMethod")
    fun `Use Percentage in more complex calculations`() {
        run { // 50 * 50% increase by 25%
            val result = (50 * 50.percent()) increaseBy 25.percent()
            // or: (Percentage.of(50) * 50) increaseBy Percentage.of(25)

            assertEquals(31.25, result)
        }

        run { // 300 * 125% decrease by 8%
            val result = (300 * 125.percent()) decreaseBy 8.percent()
            // or: Percentage.of(8) decrease Percentage.of(125) * 300

            assertEquals(345.0, result)
        }

        run { // 33 increase by 5% then decrease by 5%
            val result = 33 increaseBy 5.percent() decreaseBy 5.percent()
            // or: Percentage.of(5) decrease (Percentage.of(5) increase 33)

            assertEquals(32.9175, result)
        }

        // In the following examples, [.x] means the number of decimal places to round to.
        // Example: [.3] means round to 3 decimal places.

        run { // 33 increase by 5[.3]% then decrease by 5[.1]%
            val result = 33 increaseBy 5.percent(3) decreaseBy 5.percent(1)
            // or: Percentage.of(5, 1) decrease (Percentage.of(5, 3) increase 33)

            assertEquals(32.9, result)
        }

        run { // (33 increase by 5% decrease by 5%) * 10%
            val result = (33 increaseBy 5.percent() decreaseBy 5.percent()) * 10.percent()
            // or: Percentage.of(10) * (Percentage.of(5) decrease (Percentage.of(5) increase 33))

            assertEquals(3.29175, result)
        }

        run { // 100 * (base value when 80% is 7) increase by 1/4 (25%) -- 7 is 80% of 8.75
            val result = (100 * (7 valueWhen 80.percent())) increaseBy (1 ratioOf 4)
            // or: (Percentage.ratioOf(1, 4)) increase ((Percentage.of(80) valueWhen 7) * 100)

            assertEquals(1093.75, result)
        }

        run { // 100 * (base value when 80% is 7) increase by 1/4[.1] (30%) -- 7 is 80% of 8.75
            val result = (100 * (7 valueWhen 80.percent())) increaseBy (1.ratioOf(4, 1))
            // or: (Percentage.ratioOf(1, 4, 1)) increase ((Percentage.of(80) valueWhen 7) * 100)

            assertEquals(1093.8, result)
        }

        run { // (100 increase by relative change from 33 to 77) * 10[.2]%
            val result = (100 increaseBy (33 relativeChange 77)) * 10.percent(2)
            // or: Percentage.of(10, 2) * (Percentage.relativeChange(33, 77) increase 100)

            assertEquals(23.33, result)
        }

        run { // (100 increase by relative change[.4] from 33 to 77) * 10[.2]%
            val result = (100 increaseBy (33.relativeChange(77, 4))) * 10.percent(2)
            // or: Percentage.of(10, 2) * (Percentage.relativeChange(33, 77, 4) increase 100)

            assertEquals(23.33, result)
        }
    }
}
