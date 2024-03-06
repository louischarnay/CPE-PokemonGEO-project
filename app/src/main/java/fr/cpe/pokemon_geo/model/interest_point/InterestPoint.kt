package fr.cpe.pokemon_geo.model.interest_point

class InterestPoint {
    private var name: String
    private var latitude: Double
    private var longitude: Double

    constructor(name: String, latitude: Double, longitude: Double) {
        this.name = name
        this.latitude = latitude
        this.longitude = longitude
    }

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