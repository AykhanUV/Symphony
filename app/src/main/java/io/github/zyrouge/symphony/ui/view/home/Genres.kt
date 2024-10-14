package app.uvteam.symphony.ui.view.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.uvteam.symphony.ui.components.GenreGrid
import app.uvteam.symphony.ui.components.LoaderScaffold
import app.uvteam.symphony.ui.helpers.ViewContext

@Composable
fun GenresView(context: ViewContext) {
    val isUpdating by context.symphony.groove.genre.isUpdating.collectAsState()
    val genreNames by context.symphony.groove.genre.all.collectAsState()
    val genresCount by context.symphony.groove.genre.count.collectAsState()

    LoaderScaffold(context, isLoading = isUpdating) {
        GenreGrid(
            context,
            genreNames = genreNames,
            genresCount = genresCount,
        )
    }
}
