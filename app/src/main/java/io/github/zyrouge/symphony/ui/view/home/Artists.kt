package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.ArtistGrid
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun ArtistsView(context: ViewContext) {
    val isUpdating by context.symphony.groove.artist.isUpdating.collectAsState()
    val artistNames by context.symphony.groove.artist.all.collectAsState()
    val artistsCount by context.symphony.groove.artist.count.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        ArtistGrid(
            context,
            artistName = artistNames,
            artistsCount = artistsCount,
        )
    }
}
