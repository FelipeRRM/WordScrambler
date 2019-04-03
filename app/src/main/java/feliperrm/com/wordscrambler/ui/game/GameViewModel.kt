package feliperrm.com.wordscrambler.ui.game

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feliperrm.com.wordscrambler.data.Database
import feliperrm.com.wordscrambler.data.ScrambledWord
import feliperrm.com.wordscrambler.data.Word
import kotlinx.coroutines.launch

private const val WORDS_PER_REQUEST = 50
private const val SIZE_TO_REQUEST_MORE = 10
private const val MIN_WORD_SIZE = 3

class GameViewModel(private val db: Database) : ViewModel() {

    val wordsList = ObservableArrayList<Word>()
    val scrambledWord = MutableLiveData<ScrambledWord>()
    val timeElapsedText = MutableLiveData<String>().apply { value = "10 seconds" }
    val rightAnswersSession = MutableLiveData<String>().apply { value = "0" }
    val wrongAnswersSession = MutableLiveData<String>().apply { value = "0" }

    init {
        viewModelScope.launch {
            wordsList.addAll(db.wordDao().getRandomWords(WORDS_PER_REQUEST, MIN_WORD_SIZE))
            wordsList.addOnListChangedCallback(listListener)
        }
    }

    private val listListener = object : ObservableList.OnListChangedCallback<ObservableArrayList<Word>>() {

        override fun onChanged(sender: ObservableArrayList<Word>?) {}

        /* Loads more words from the DB if we reach a certain threshold.
         The idea is to always have some words available in memory and not do DB operations all the time,
         as these are much more expensive.
         */
        override fun onItemRangeRemoved(sender: ObservableArrayList<Word>?, positionStart: Int, itemCount: Int) {
            if (wordsList.size < SIZE_TO_REQUEST_MORE) {
                viewModelScope.launch {
                    wordsList.addAll(db.wordDao().getRandomWords(WORDS_PER_REQUEST, MIN_WORD_SIZE))
                }
            }
        }

        override fun onItemRangeMoved(
            sender: ObservableArrayList<Word>?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<Word>?, positionStart: Int, itemCount: Int) {}

        override fun onItemRangeChanged(sender: ObservableArrayList<Word>?, positionStart: Int, itemCount: Int) {}

    }


}
