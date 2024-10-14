package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.AlbumArtistGrid
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun AlbumArtistsView(context: ViewContext) {
    val isUpdating by context.symphony.groove.albumArtist.isUpdating.collectAsState()
    val albumArtistNames by context.symphony.groove.albumArtist.all.collectAsState()
    val albumArtistsCount by context.symphony.groove.albumArtist.count.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        AlbumArtistGrid(
            context,
            albumArtistNames = albumArtistNames,
            albumArtistsCount = albumArtistsCount,
        )
    }
}
