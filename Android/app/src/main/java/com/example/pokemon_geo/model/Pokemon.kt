package com.example.pokemon_geo.model

import android.R

class Pokemon {
    var order: Int
    var name: String
    var height = 0
    var weight = 0
    var frontResource: Int
    private var type1: POKEMON_TYPE
    private var type2: POKEMON_TYPE? = null

    constructor() {
        order = 1
        name = "Unknown"
        frontResource = R.drawable.alert_dark_frame
        type1 = POKEMON_TYPE.Plante
    }

    constructor(
        order: Int,
        name: String,
        frontResource: Int,
        type1: POKEMON_TYPE,
        type2: POKEMON_TYPE?
    ) {
        this.order = order
        this.name = name
        this.frontResource = frontResource
        this.type1 = type1
        this.type2 = type2
    }

    fun getType1(): POKEMON_TYPE {
        return type1
    }

    fun setType1(type1: POKEMON_TYPE) {
        this.type1 = type1
    }

    fun getType2(): POKEMON_TYPE? {
        return type2
    }

    fun setType2(type2: POKEMON_TYPE?) {
        this.type2 = type2
    }

    val type1String: String
        get() = type1.name
    val type2String: String?
        get() = type2?.name
}