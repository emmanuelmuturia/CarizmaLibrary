package emmanuelmuturia.sonux.effects

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.arthenica.ffmpegkit.FFmpegKit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Exception

/**
 * This function converts an input audio file to one with the 8D effect applied and then
 * downloads the resulting file via DownloadManager.
 *
 * @param context The Android Context.
 * @param inputUri A string representing the absolute path of the input audio file.
 * @param frequency A value (in Hz) controlling the auto-panning speed.
 * @param coroutineDispatcher The CoroutineDispatcher to perform processing on (e.g., Dispatchers.IO).
 */
suspend fun convertAudioFileTo8DAndDownload(
    context: Context,
    inputUri: String,
    frequency: Float,
    coroutineDispatcher: CoroutineDispatcher
) {
    withContext(coroutineDispatcher) {
        try {
            // Input file (assumed to be a local file path)
            val inputFile = File(inputUri)
            if (!inputFile.exists()) {
                Log.e("convertAudioFileTo8D", "Input file does not exist: $inputUri")
                return@withContext
            }

            // Define the output file (placed in the app's external Music directory)
            val outputFile = File(
                context.getExternalFilesDir(Environment.DIRECTORY_MUSIC),
                "audio_with_8d_effect.mp3"
            )

            // Build FFmpeg command:
            // - The "apulsator" filter applies an auto-panning effect at the given frequency.
            // - The "aecho" filter adds a simple echo which can simulate reverb.
            // Adjust filter parameters as needed for your desired effect.
            val command = "-y -i ${inputFile.absolutePath} " +
                "-af \"apulsator=hz=$frequency, aecho=0.8:0.88:60:0.4\" " +
                "${outputFile.absolutePath}"

            Log.d("FFmpegCommand", command)

            // Execute FFmpeg command asynchronously
            FFmpegKit.executeAsync(command) { session ->
                if (session.returnCode.isValueSuccess) {
                    Log.d("convertAudioFileTo8D", "MP3 conversion successful: ${outputFile.absolutePath}")

                    // Step 2: Copy the processed file to a public Downloads directory
                    val downloadedFile = copyFileToDownloads(outputFile, "Your_Converted_Audio_File.mp3", context)
                    if (downloadedFile != null) {
                        Log.d("convertAudioFileTo8D", "File copied to Downloads: ${downloadedFile.absolutePath}")
                        // Step 3: Enqueue the download via DownloadManager
                        enqueueFileDownload(context, downloadedFile)
                    } else {
                        Log.e("convertAudioFileTo8D", "Failed to copy file to Downloads")
                    }
                } else {
                    Log.e("convertAudioFileTo8D", "MP3 conversion failed with return code: ${session.returnCode}")
                }
            }
        } catch (e: Exception) {
            Log.e("convertAudioFileTo8D", "Error during conversion: ${e.message}")
        }
    }
}

/**
 * Copies a file to the app's external Downloads directory.
 *
 * @param sourceFile The source File.
 * @param fileName The name for the copied file.
 * @param context The Android Context.
 * @return The destination File, or null if the copy failed.
 */
fun copyFileToDownloads(sourceFile: File, fileName: String, context: Context): File? {
    // Use getExternalFilesDir for app-specific downloads; you might also use Environment.getExternalStoragePublicDirectory
    val downloadsDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
    val destinationFile = File(downloadsDirectory, fileName)
    return try {
        sourceFile.copyTo(destinationFile, overwrite = true)
        destinationFile
    } catch (e: Exception) {
        Log.e("copyFileToDownloads", "Error copying file: ${e.message}")
        null
    }
}

/**
 * Enqueues the provided file with DownloadManager so the user is notified and can access it.
 *
 * @param context The Android Context.
 * @param file The file to download.
 */
fun enqueueFileDownload(context: Context, file: File) {
    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val uri = Uri.fromFile(file)
    val request = DownloadManager.Request(uri)
        .setTitle("8D Audio File")
        .setDescription("Your audio file with the 8D effect has been processed and is ready for download.")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, file.name)
    downloadManager.enqueue(request)
}