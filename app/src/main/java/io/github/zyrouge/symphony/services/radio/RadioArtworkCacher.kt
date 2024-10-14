package app.uvteam.symphony.services.radio

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
import app.uvteam.symphony.Symphony
import app.uvteam.symphony.services.groove.Song
import app.uvteam.symphony.ui.helpers.Assets

class RadioArtworkCacher(val symphony: Symphony) {
    private var default: Bitmap? = null
    private var cached = mutableMapOf<Long, Bitmap>()
    private val cacheLimit = 3

    suspend fun getArtwork(song: Song): Bitmap {
        return cached[song.id] ?: kotlin.run {
            val result = symphony.applicationContext.imageLoader
                .execute(song.createArtworkImageRequest(symphony).build())
            val bitmap = result.drawable?.toBitmap() ?: getDefaultArtwork()
            updateCache(song.id, bitmap)
            bitmap
        }
    }

    private fun getDefaultArtwork(): Bitmap {
        return default ?: run {
            val bitmap = BitmapFactory.decodeResource(
                symphony.applicationContext.resources,
                Assets.placeholderDarkId,
            )
            default = bitmap
            bitmap
        }
    }

    private fun updateCache(key: Long, value: Bitmap) {
        if (!cached.containsKey(key) && cached.size >= cacheLimit) {
            cached.remove(cached.keys.first())
        }
        cached[key] = value
    }
}
