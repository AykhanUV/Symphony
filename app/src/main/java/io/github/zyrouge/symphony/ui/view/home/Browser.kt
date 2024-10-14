package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.components.SongExplorerList
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun BrowserView(context: ViewContext) {
    val isUpdating by context.symphony.groove.song.isUpdating.collectAsState()
    val id by context.symphony.groove.song.id.collectAsState()
    val explorer = context.symphony.groove.song.explorer
    val lastUsedFolderPath by context.symphony.settings.lastUsedBrowserPath.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        SongExplorerList(
            context,
            initialPath = lastUsedFolderPath,
            key = id,
            explorer = explorer,
            onPathChange = { path ->
                context.symphony.settings.setLastUsedBrowserPath(path)
            }
        )
    }
}
