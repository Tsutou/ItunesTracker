package jp.co.geisha.diggin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jp.co.geisha.diggin.data.structure.Music

@Dao
interface MusicDao {
    @Query("SELECT * FROM music")
    fun getAll(): LiveData<List<Music>>

    @Query("SELECT * FROM music WHERE title IN (:keyword)")
    fun loadAllByIds(keyword: String): LiveData<List<Music>>

    @Insert
    fun insert(vararg music: Music)

    @Insert
    fun bulkInsert(musicList: List<Music>)

    @Query("DELETE FROM music")
    fun deleteAll()
}
