package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.components.SongList
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun SongsView(context: ViewContext) {
    val isUpdating by context.symphony.groove.song.isUpdating.collectAsState()
    val songIds by context.symphony.groove.song.all.collectAsState()
    val songsCount by context.symphony.groove.song.count.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        SongList(
            context,
            songIds = songIds,
            songsCount = songsCount,
        )
    }
}
