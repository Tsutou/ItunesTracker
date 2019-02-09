package jp.co.geisha.itunestracker.service.callback

import jp.co.geisha.itunestracker.service.model.Artist

interface ArtistClickCallback {
    fun onClick(artist: Artist)
}
