package emmanuelmuturia.sonux.repository

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.arthenica.ffmpegkit.FFmpegKit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

actual class SonuxRepository(
    private val context: Context
) {

    /**
     * This function converts an input audio file to one with the 8D effect applied and then
     * downloads the resulting file using Option 2: copying to a public Downloads directory,
     * scanning the file via MediaScannerConnection, and displaying a Toast when ready.
     *
     * @param inputUri A string representing the content URI of the input audio file.
     * @param frequency A value (in Hz) controlling the auto-panning speed.
     * @param coroutineDispatcher The CoroutineDispatcher to perform processing on (e.g., Dispatchers.IO).
     */
    actual suspend fun convertAndDownloadAudio(
        inputUri: String, // This is a content URI (e.g., "content://...")
        frequency: Float,
        coroutineDispatcher: CoroutineDispatcher
    ) {
        withContext(coroutineDispatcher) {
            // Convert the content URI to a temporary file.
            val tempInputFile = copyUriToTempFile(context, Uri.parse(inputUri), "temp_audio_file.mp3")
            if (tempInputFile == null || !tempInputFile.exists()) {
                Log.e("convertAudioFileTo8D", "Temporary input file could not be created.")
                return@withContext
            }

            // Define the output file (placed in the external Music directory)
            val mp3File = File(context.getExternalFilesDir(Environment.DIRECTORY_MUSIC), "audio_with_8d_effect.mp3")

            // Build FFmpeg command (adjust filters as desired)
            val command = "-y -i ${tempInputFile.absolutePath} -af \"apulsator=hz=$frequency,aecho=0.8:0.88:60:0.4\" ${mp3File.absolutePath}"
            Log.d("FFmpegCommand", command)

            FFmpegKit.executeAsync(command) { session ->
                if (session.returnCode.isValueSuccess) {
                    Log.d("convertAudioFileTo8D", "MP3 conversion successful: ${mp3File.absolutePath}")

                    // Move the file to public Downloads
                    val movedFile = moveFileToDownloads(mp3File, "Your_Converted_Audio_File.mp3", context)

                    movedFile?.let {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context, "File saved to Downloads: ${it.absolutePath}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Log.e("convertAudioFileTo8D", "MP3 conversion failed with return code: ${session.returnCode}")
                }
            }
        }
    }
}

/**
 * Copies a content URI to a temporary file in the app's cache directory.
 *
 * @param context The Android Context.
 * @param uri The content URI to copy.
 * @param fileName The desired file name for the temporary file.
 * @return The created temporary File, or null if the copy failed.
 */
fun copyUriToTempFile(context: Context, uri: Uri, fileName: String): File? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val tempFile = File(context.cacheDir, fileName)
        FileOutputStream(tempFile).use { outputStream ->
            inputStream.copyTo(outputStream)
        }
        tempFile
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun moveFileToDownloads(sourceFile: File, fileName: String, context: Context): File? {
    val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val destinationFile = File(downloadsDirectory, fileName)

    return try {
        // Move the file
        sourceFile.copyTo(destinationFile, overwrite = true)
        sourceFile.delete() // Remove original file after moving

        // Notify the system that a new file was added
        MediaScannerConnection.scanFile(
            context,
            arrayOf(destinationFile.absolutePath),
            null
        ) { _, uri ->
            Log.d("moveFileToDownloads", "File scanned and available at: $uri")
        }

        destinationFile
    } catch (e: IOException) {
        Log.e("moveFileToDownloads", "Error moving file: ${e.message}")
        null
    }
}