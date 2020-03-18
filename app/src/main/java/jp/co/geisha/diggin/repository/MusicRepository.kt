package jp.co.geisha.diggin.repository

import androidx.lifecycle.LiveData
import jp.co.geisha.diggin.data.dao.MusicDao
import jp.co.geisha.diggin.data.structure.Music

class MusicRepository private constructor(val musicDao: MusicDao) {

    companion object {
        @Volatile
        private var INSTANCE: MusicRepository? = null

        fun getInstance(musicDao: MusicDao): MusicRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: MusicRepository(musicDao)
                            .also { INSTANCE = it }
                }
    }

    fun getMusicList(): LiveData<List<Music>> = musicDao.getAll()
    fun getMusicListByKeyword(keyword: String): LiveData<List<Music>> = musicDao.loadAllByIds(keyword)
    fun insert(music: Music) = musicDao.insert(music)
    fun bulkInsert(musicList: List<Music>) = musicDao.bulkInsert(musicList)

}