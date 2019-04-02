package feliperrm.com.wordscrambler.utils

import android.app.Application
import androidx.room.Room
import feliperrm.com.wordscrambler.data.Database

/**
 * Created by FelipeRRM on 4/2/2019.
 */
class App : Application() {
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java, "db_v1"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: App
    }

}