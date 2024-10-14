package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.components.SongTreeList
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun TreeView(context: ViewContext) {
    val isUpdating by context.symphony.groove.song.isUpdating.collectAsState()
    val songIds by context.symphony.groove.song.all.collectAsState()
    val songsCount by context.symphony.groove.song.count.collectAsState()
    val disabledTreePaths by context.symphony.settings.lastDisabledTreePaths.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        SongTreeList(
            context,
            songIds = songIds,
            songsCount = songsCount,
            initialDisabled = disabledTreePaths,
            onDisable = { paths ->
                context.symphony.settings.setLastDisabledTreePaths(paths)
            },
        )
    }
}
