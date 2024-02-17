package fr.cpe.pokemon_geo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.osmdroid.library.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class PokemonGeo: Application() {

    override fun onCreate() {
        super.onCreate()

        // To display logs in debug mode only
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}