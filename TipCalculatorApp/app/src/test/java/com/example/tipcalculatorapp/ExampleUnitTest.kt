package com.example.tipcalculatorapp

import org.junit.Test

import org.junit.Assert.*
import java.text.NumberFormat

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun calculateTip_20PercentNoRoundup(){
        val amount = 10.00
        val tipPercent =20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount,tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_30PercentNoRoundup(){
        val amount = 10.00
        val tipPercent =30.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(3)
        val actualTip = calculateTip(amount,tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}



