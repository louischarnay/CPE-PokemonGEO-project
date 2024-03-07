package fr.cpe.pokemon_geo.model.interest_point

data class InterestPointResponse(
    val elements: List<Element>
)

data class Element(
    val type: String,
    val id: Long,
    val lat: Double,
    val lon: Double,
    val tags: Tags
)

data class Tags(
    val amenity: String,
    val name: String
)