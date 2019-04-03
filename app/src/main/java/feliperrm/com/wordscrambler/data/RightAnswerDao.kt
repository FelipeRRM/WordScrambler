package feliperrm.com.wordscrambler.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by FelipeRRM on 4/2/2019.
 */
@Dao
interface RightAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRightAnswer(rightAnswer: RightAnswer)

}