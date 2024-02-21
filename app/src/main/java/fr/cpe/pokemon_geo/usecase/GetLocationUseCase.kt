package fr.cpe.pokemon_geo.usecase

import fr.cpe.pokemon_geo.service.location.ILocationService
import kotlinx.coroutines.flow.Flow
import org.osmdroid.util.GeoPoint
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    operator fun invoke(): Flow<GeoPoint?> = locationService.requestLocationUpdates()
}