package feliperrm.com.wordscrambler.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import feliperrm.com.wordscrambler.data.Database

class GameViewModel(val db: Database) : ViewModel() {

    val scrambledWord = MutableLiveData<String>().apply { value = "feliep" }
    val timeElapsedText = MutableLiveData<String>().apply { value = "10 seconds" }
    val rightAnswersSession = MutableLiveData<String>().apply { value = "0" }
    val wrongAnswersSession = MutableLiveData<String>().apply { value = "0" }
}
