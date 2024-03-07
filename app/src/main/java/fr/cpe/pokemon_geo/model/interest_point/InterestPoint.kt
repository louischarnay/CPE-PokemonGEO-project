package fr.cpe.pokemon_geo.model.interest_point

class InterestPoint(
    private var name: String,
    private var latitude: Double,
    private var longitude: Double
) {
    fun getName(): String {
        return name
    }
    fun getLatitude(): Double {
        return latitude
    }
    fun getLongitude(): Double {
        return longitude
    }
}