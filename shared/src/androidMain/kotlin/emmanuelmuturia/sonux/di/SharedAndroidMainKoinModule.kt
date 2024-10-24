package emmanuelmuturia.sonux.di

import android.content.Context
import android.media.MediaPlayer
import emmanuelmuturia.sonux.effects.AndroidAudioEffects
import emmanuelmuturia.sonux.effects.AudioEffects
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedAndroidMainKoinModule = module {

    single<Context> {
        androidApplication()
    }

    single<AudioEffects> {
        AndroidAudioEffects(mediaPlayer = MediaPlayer())
    }
}