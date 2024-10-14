package app.uvteam.symphony.ui.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.uvteam.symphony.MainActivity
import app.uvteam.symphony.Symphony
import app.uvteam.symphony.services.groove.GrooveKinds
import app.uvteam.symphony.ui.helpers.FadeTransition
import app.uvteam.symphony.ui.helpers.Routes
import app.uvteam.symphony.ui.helpers.ScaleTransition
import app.uvteam.symphony.ui.helpers.SlideTransition
import app.uvteam.symphony.ui.helpers.ViewContext
import app.uvteam.symphony.ui.helpers.getRouteParameter
import app.uvteam.symphony.ui.theme.SymphonyTheme

@Composable
fun BaseView(symphony: Symphony, activity: MainActivity) {
    val context = ViewContext(
        symphony = symphony,
        activity = activity,
        navController = rememberNavController(),
    )

    SymphonyTheme(context) {
        Surface(color = MaterialTheme.colorScheme.background) {
            NavHost(
                navController = context.navController,
                startDestination = Routes.Home.route,
            ) {
                composable(
                    Routes.Home.template(),
                    enterTransition = { FadeTransition.enterTransition() },
                ) {
                    HomeView(context)
                }
                composable(
                    Routes.NowPlaying.template(),
                    enterTransition = { SlideTransition.slideUp.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                    popEnterTransition = { FadeTransition.enterTransition() },
                    popExitTransition = { SlideTransition.slideDown.exitTransition() },
                ) {
                    NowPlayingView(context)
                }
                composable(
                    Routes.Queue.template(),
                    enterTransition = { SlideTransition.slideUp.enterTransition() },
                    exitTransition = { SlideTransition.slideDown.exitTransition() },
                ) {
                    QueueView(context)
                }
                composable(
                    Routes.Settings.template(),
                    enterTransition = { ScaleTransition.scaleDown.enterTransition() },
                    exitTransition = { ScaleTransition.scaleUp.exitTransition() },
                ) {
                    SettingsView(context)
                }
                composable(
                    Routes.Artist.template(),
                    enterTransition = { SlideTransition.slideLeft.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                ) { backStackEntry ->
                    ArtistView(context, backStackEntry.getRouteParameter())
                }
                composable(
                    Routes.Album.template(),
                    enterTransition = { SlideTransition.slideLeft.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                ) { backStackEntry ->
                    AlbumView(context, backStackEntry.getRouteParameter())
                }
                composable(
                    Routes.Search.template(),
                    enterTransition = { SlideTransition.slideDown.enterTransition() },
                    exitTransition = { SlideTransition.slideUp.exitTransition() },
                ) { backStackEntry ->
                    SearchView(
                        context,
                        backStackEntry.getRouteParameter()
                            .takeIf { it != "null" }
                            ?.let { GrooveKinds.valueOf(it) }
                    )
                }
                composable(
                    Routes.AlbumArtist.template(),
                    enterTransition = { SlideTransition.slideLeft.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                ) { backStackEntry ->
                    AlbumArtistView(context, backStackEntry.getRouteParameter())
                }
                composable(
                    Routes.Genre.template(),
                    enterTransition = { SlideTransition.slideLeft.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                ) { backStackEntry ->
                    GenreView(context, backStackEntry.getRouteParameter())
                }
                composable(
                    Routes.Playlist.template(),
                    enterTransition = { SlideTransition.slideLeft.enterTransition() },
                    exitTransition = { FadeTransition.exitTransition() },
                ) { backStackEntry ->
                    PlaylistView(context, backStackEntry.getRouteParameter())
                }
                composable(
                    Routes.Lyrics.template(),
                    enterTransition = { SlideTransition.slideUp.enterTransition() },
                    exitTransition = { SlideTransition.slideDown.exitTransition() },
                ) {
                    LyricsView(context)
                }
            }
        }
    }
}
