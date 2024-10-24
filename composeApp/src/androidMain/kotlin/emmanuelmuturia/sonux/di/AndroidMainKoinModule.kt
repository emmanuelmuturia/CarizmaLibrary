package emmanuelmuturia.sonux.di

import emmanuelmuturia.sonux.viewmodel.SonuxViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidMainKoinModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    viewModel {
        SonuxViewModel(audioEffects = get(), coroutineDispatcher = get())
    }

}