package com.example.finhealth.data.models.GainOutlay


import com.example.finhealth.ui.theme.utils.getCurrentDate
import com.google.firebase.firestore.DocumentId


data class GainOutlayModel (
    @DocumentId val id: String? = null,
    val value: Double = 0.0,
    val description: String = "",
    val type: Boolean = true,
    val date: String = getCurrentDate()

) {
    constructor() : this("", 0.0, "", true, "")

}