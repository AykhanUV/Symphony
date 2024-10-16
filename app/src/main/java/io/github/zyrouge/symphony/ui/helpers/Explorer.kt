package app.uvteam.symphony.ui.helpers

import app.uvteam.symphony.services.groove.GrooveExplorer

fun GrooveExplorer.Folder.navigateToFolder(parts: List<String>): GrooveExplorer.Folder? {
    var folder: GrooveExplorer.Folder? = this
    parts.forEach { part ->
        folder = folder?.let {
            val child = it.children[part]
            if (child is GrooveExplorer.Folder) child else null
        }
    }
    return folder
}
