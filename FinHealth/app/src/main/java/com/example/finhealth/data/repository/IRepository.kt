package com.example.finhealth.data.repository
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun listGainOutlay(): Flow<List<GainOutlayModel>>
    suspend fun getById(idx: Int): GainOutlayModel
    suspend fun updateAndSaveGainOutlayList(gainOutlays: List<GainOutlayModel>) // Nome no plural
    suspend fun updateAndSaveGainOutlay(gainOutlay: GainOutlayModel) // Nome no plural
    suspend fun delete(gainOutlayModel: GainOutlayModel)
}


