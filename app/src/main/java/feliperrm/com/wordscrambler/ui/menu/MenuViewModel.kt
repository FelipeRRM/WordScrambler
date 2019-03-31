package feliperrm.com.wordscrambler.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel : ViewModel() {

    val title = MutableLiveData<String>().apply { value = "Good morning!" }

}
