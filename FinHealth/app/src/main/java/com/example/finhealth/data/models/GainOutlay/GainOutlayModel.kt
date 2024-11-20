package com.example.finhealth.data.models.GainOutlay

import com.example.finhealth.ui.theme.utils.getDate
import com.google.firebase.firestore.DocumentId
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class GainOutlayModel (
    @DocumentId val id: String? = null,
    val value: Double = 0.0,
    val description: String = "",
    val type: Boolean = true,

) {
    constructor() : this("", 0.0, "", true)


}