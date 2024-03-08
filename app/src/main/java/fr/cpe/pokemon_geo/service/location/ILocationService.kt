package fr.cpe.pokemon_geo.service.location

import kotlinx.coroutines.flow.Flow
import org.osmdroid.util.GeoPoint

interface ILocationService {
    fun requestLocationUpdates(): Flow<GeoPoint?>
    suspend fun requestCurrentLocation(): GeoPoint?
}