package app.uvteam.symphony.services.database

import app.uvteam.symphony.Symphony
import app.uvteam.symphony.services.database.adapters.FileDatabaseAdapter
import app.uvteam.symphony.services.groove.Playlist
import app.uvteam.symphony.services.groove.PlaylistRepository
import app.uvteam.symphony.utils.toList
import org.json.JSONArray
import org.json.JSONObject
import java.nio.file.Paths

class PlaylistsBox(val symphony: Symphony) {
    data class Data(
        val custom: List<Playlist>,
        val local: List<Playlist.Local>,
        val favorites: Playlist,
    ) {
        fun toJSONObject() = JSONObject().apply {
            put(CUSTOM, JSONArray(custom.map { it.toJSONObject() }))
            put(LOCAL, JSONArray(local.map { it.toJSONObject() }))
            put(FAVORITES, favorites.toJSONObject())
        }

        companion object {
            private const val CUSTOM = "0"
            private const val LOCAL = "1"
            private const val FAVORITES = "2"

            fun fromJSONObject(json: JSONObject) = json.run {
                Data(
                    custom = json.getJSONArray(CUSTOM)
                        .toList { Playlist.fromJSONObject(getJSONObject(it)) },
                    local = json.getJSONArray(LOCAL)
                        .toList { Playlist.Local.fromJSONObject(getJSONObject(it)) },
                    favorites = when {
                        json.has(FAVORITES) -> Playlist.fromJSONObject(getJSONObject(FAVORITES))
                        else -> createFavoritesPlaylist()
                    },
                )
            }

            fun createFavoritesPlaylist() = Playlist(
                id = PlaylistRepository.generatePlaylistId(),
                title = "Favorites",
                songIds = emptyList(),
                numberOfTracks = 0,
                local = null,
            )
        }
    }

    private val adapter = FileDatabaseAdapter(
        Paths
            .get(symphony.applicationContext.dataDir.absolutePath, "playlists.json")
            .toFile()
    )

    fun read(): Data {
        val content = adapter.read()
        return Data.fromJSONObject(JSONObject(content))
    }

    fun update(value: Data) {
        adapter.overwrite(value.toJSONObject().toString())
    }
}

