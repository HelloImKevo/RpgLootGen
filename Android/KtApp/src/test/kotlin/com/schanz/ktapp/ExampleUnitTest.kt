package com.schanz.ktapp

import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun whenExpressionNullSafetyCheck() {
        var value: String? = getRandomString()
        when (value) {
            "Potatoes" -> println("Mash em, Bake em, Put em in a stew.")
            "Carrots" -> println("Feed them to a rabbit.")
            else -> println("Shrug.")
        }

        value = null
        when (value) {
            "Winning" -> println("Damn, it feels good to be a gangster.")
            else -> println("Looks like the 'else' block is execute when null.")
        }
    }

    @Suppress("RedundantNullableReturnType")
    private fun getRandomString(): String? = "Potatoes"
}
