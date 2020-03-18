package jp.co.geisha.diggin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import jp.co.geisha.diggin.data.dao.MusicDao
import jp.co.geisha.diggin.data.structure.Music
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Music::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun musicDao(): MusicDao

    companion object {
        @Volatile
        private var INSTANCE: MusicDatabase? = null

        fun loadDataBase(context: Context, scope: CoroutineScope): MusicDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: Room.databaseBuilder(
                            context,
                            MusicDatabase::class.java,
                            "music")
                            .addCallback(MusicDatabaseCallback(scope))
                            .build()
                            .also { INSTANCE = it }
                }
    }

    class MusicDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.musicDao())
                }
            }
        }

        private fun populateDatabase(musicDao: MusicDao) {
            musicDao.deleteAll()
        }
    }
}