import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberImagePicker(
    onImageSelected: (Uri) -> Unit
): () -> Unit {

    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { onImageSelected(it) }
        }

    return {
        imagePickerLauncher.launch("image/*")
    }
}
