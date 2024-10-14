package app.uvteam.symphony.ui.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import app.uvteam.symphony.ui.helpers.ViewContext
import app.uvteam.symphony.utils.copyToClipboardWithToast

@Composable
fun LongPressCopyableText(context: ViewContext, text: String) {
    Text(
        text,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = {
                copyToClipboardWithToast(context, text)
            })
        }
    )
}
