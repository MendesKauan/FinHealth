package com.example.finhealth.data.models.GainOutlay

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_GainOutlay")

data class GainOutlayModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val value: Double = 0.0,
    val description: String = "",
    val type: Boolean = true
)