package feliperrm.com.wordscrambler.ui.statistics

import android.graphics.Color
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import feliperrm.com.wordscrambler.R
import feliperrm.com.wordscrambler.data.Database
import feliperrm.com.wordscrambler.data.Word
import kotlinx.coroutines.launch

class StatisticsViewModel(private val db: Database) : ViewModel() {


    private val rightAnswers = MutableLiveData<Int>()
    private val wrongAnswers = MutableLiveData<Int>()
    val answersPieEntries = MediatorLiveData<List<PieEntry>>().apply {
        addSource(rightAnswers) {
            if (rightAnswers.value ?: 0 > 0 && wrongAnswers.value ?: 0 > 0) {
                value = getAnswersPieEntries(rightAnswers.value, wrongAnswers.value)
            }
        }
        addSource(wrongAnswers) {
            if (rightAnswers.value ?: 0 > 0 && wrongAnswers.value ?: 0 > 0) {
                value = getAnswersPieEntries(rightAnswers.value, wrongAnswers.value)
            }
        }
    }

    val sessionsPlayed = MutableLiveData<String>()
    val timePlayed = MutableLiveData<String>()
    val easiestWord = MutableLiveData<Word>()
    val easiestWordSeconds = MutableLiveData<Int>()
    val hardestWord = MutableLiveData<Word>()
    val hardestWordSeconds = MutableLiveData<Int>()

    private fun getAnswersPieEntries(right: Int?, wrong: Int?) =
        ArrayList<PieEntry>().apply {
            right?.let { right ->
                wrong?.let { wrong ->
                    add(PieEntry(right.toFloat(), "Right"))
                    add(PieEntry(wrong.toFloat(), "Wrong"))
                }
            }
        }


    init {
        viewModelScope.launch {
            sessionsPlayed.value = db.sessionDao().getTotalSessions().toString()
            rightAnswers.value = db.sessionDao().getTotalRightAnswers()
            wrongAnswers.value = db.sessionDao().getTotalWrongAnswers()
            timePlayed.value = db.sessionDao().getTotalTimePlayed().toString()
            val easiestAnswerWord = db.rightAnswerDao().getEasiestAnswerWord()
            val hardestAnswerWord = db.rightAnswerDao().getHardestAnswerWord()
            easiestAnswerWord.wordId?.let {
                easiestWord.value = db.wordDao().getWordById(it)
            }
            hardestAnswerWord.wordId?.let {
                hardestWord.value = db.wordDao().getWordById(it)
            }
            easiestWordSeconds.value = easiestAnswerWord.secondsPlayed
            hardestWordSeconds.value = hardestAnswerWord.secondsPlayed
        }
    }


}

@BindingAdapter("pieEntries")
fun PieChart.setData(pieEntries: List<PieEntry>?) {
    pieEntries?.let {
        val dataSet = PieDataSet(it, "").apply {
            setColors(
                context.resources.getColor(R.color.colorPrimary),
                context.resources.getColor(R.color.colorAccent)
            )
            valueTextSize = 24f
            valueTextColor = Color.WHITE
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }
        }
        val data = PieData(dataSet)
        setData(data)
        animateXY(400, 400, Easing.EaseInOutSine)
    }
}
