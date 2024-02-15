package fr.cpe.pokemon_geo.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasLocationPermission(): Boolean {
    return this.hasPermission(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            this.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)
}
