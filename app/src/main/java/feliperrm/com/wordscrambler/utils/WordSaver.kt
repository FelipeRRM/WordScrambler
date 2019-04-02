package feliperrm.com.wordscrambler.utils

import feliperrm.com.wordscrambler.data.Word
import java.io.File

/**
 * Created by FelipeRRM on 4/1/2019.
 */
class WordSaver(val file: File) {

    suspend fun saveWordsToDb() {
        file.forEachLine { text ->
            suspend {
                App.application.db.wordDao().insertWords(Word(word = text, timesPlayed = 0))
            }
        }
    }

}