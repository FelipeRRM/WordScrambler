package feliperrm.com.wordscrambler.data

/**
 * Created by FelipeRRM on 03/04/2019.
 */
class ScrambledWord(val word: Word) {

    val scrambledText = scramble()

    private fun scramble(): String {
        return word.word.asIterable().shuffled().toString()
    }

    override fun toString(): String {
        return scrambledText
    }
}