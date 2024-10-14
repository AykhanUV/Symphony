package app.uvteam.symphony.utils

import java.io.File

object FileX {
    fun ensureFile(file: File) {
        file.parentFile?.mkdirs()
        file.createNewFile()
    }
}
