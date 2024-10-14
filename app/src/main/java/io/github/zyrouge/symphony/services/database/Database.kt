package app.uvteam.symphony.services.database

import app.uvteam.symphony.Symphony

class Database(symphony: Symphony) {
    val songCache = SongCache(symphony)
    val lyricsCache = LyricsCache(symphony)
    val playlists = PlaylistsBox(symphony)
}
