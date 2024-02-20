package fr.cpe.pokemon_geo.usecase

import com.google.android.gms.maps.model.LatLng
import fr.cpe.pokemon_geo.service.location.ILocationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    operator fun invoke(): Flow<LatLng?> = locationService.requestLocationUpdates()
}