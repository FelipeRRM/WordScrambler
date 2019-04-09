package feliperrm.com.wordscrambler.data

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by FelipeRRM on 09/04/2019.
 */
class ScrambledWordTest {

    @Test
    fun testScrambledTest() {
        val word1 = Word(word ="car")
        val scrambled1 = ScrambledWord(word1)
        val scrambledString = scrambled1.toString()

    }
}