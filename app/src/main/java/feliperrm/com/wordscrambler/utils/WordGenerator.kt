package feliperrm.com.wordscrambler.utils

import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by FelipeRRM on 4/1/2019.
 */
class WordGenerator(file: File) {

    private val words: ConcurrentLinkedQueue<String> = ConcurrentLinkedQueue()

    fun getNextWord(): String {
        val word = words.remove()
        if (words.size > MIN_WORDS_QUEUE) {
            loadMoreWords()
        }
        return word
    }

    // Loads more words into the queue
    private fun loadMoreWords() {

    }

    companion object {
        private const val MIN_WORDS_QUEUE = 10
    }

}