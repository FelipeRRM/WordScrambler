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
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(vararg words: Word)

    @Query("SELECT * FROM Word WHERE length(word) >= :minLength ORDER BY RANDOM() LIMIT :number")
    suspend fun getRandomWords(number: Int, minLength: Int = 1): List<LiveData<Word>>

}