package app.uvteam.symphony.ui.helpers

import androidx.navigation.NavHostController
import app.uvteam.symphony.MainActivity
import app.uvteam.symphony.Symphony

data class ViewContext(
    val symphony: Symphony,
    val activity: MainActivity,
    val navController: NavHostController
)
