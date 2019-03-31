package feliperrm.com.wordscrambler.ui.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val scrambledWord = MutableLiveData<String>().apply { value = "feliep" }
    val timeElapsedText = MutableLiveData<String>().apply { value = "10 seconds" }
    val rightAnswersSession = MutableLiveData<String>().apply { value = "0" }
    val wrongAnswersSession = MutableLiveData<String>().apply { value = "0" }
}
