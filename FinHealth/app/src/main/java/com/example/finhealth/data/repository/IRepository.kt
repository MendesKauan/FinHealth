package com.example.finhealth.data.repository
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

interface IRepository {

    fun listGainOutlay(): Flow<List<GainOutlayModel>>

    suspend fun getById(idx: Int): GainOutlayModel

    suspend fun updateAndSaveGainOutlay(gainOutlayModel: GainOutlayModel)

    suspend fun delete(gainOutlayModel: GainOutlayModel)
}