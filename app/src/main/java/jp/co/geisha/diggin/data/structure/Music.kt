package jp.co.geisha.diggin.data.structure

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Music(
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "thumbnailUrl")
        val thumbnailUrl: String,
        @ColumnInfo(name = "contentUrl")
        val contentUrl: String,
        @ColumnInfo(name = "artist")
        val artist: String,
        @ColumnInfo(name = "mediaType")
        val mediaType: Int
)
