package feliperrm.com.wordscrambler.data

/**
 * Created by FelipeRRM on 03/04/2019.
 */
class ScrambledWord(val word: Word) {

    val scrambledText = scramble()

    private fun scramble() {
        word.word.asIterable().shuffled().toString()
    }

}