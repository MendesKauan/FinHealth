package com.example.finhealth.data.models.GainOutlay


data class GainOutlayModel (
    val id: Long? = null,
    val value: Double = 0.0,
    val description: String = "",
    val type: Boolean = true
) {
    constructor() : this(null, 0.0, "", true)
}