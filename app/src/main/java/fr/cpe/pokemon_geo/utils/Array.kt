package fr.cpe.pokemon_geo.utils

fun Set<Any>.hasSameContent(other: List<Any>): Boolean {
    if (this.size != other.size) return false
    return this.containsAll(other)
}