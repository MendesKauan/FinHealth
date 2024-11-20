package com.example.finhealth.data.repository

import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun fetchAll(): Flow<List<GainOutlayModel>>
    suspend fun fetchById(id: Int): GainOutlayModel?
    suspend fun saveAll(gainOutlays: List<GainOutlayModel>)
    suspend fun save(gainOutlay: GainOutlayModel)
    suspend fun update(gainOutlay: GainOutlayModel)
    suspend fun delete(gainOutlay: GainOutlayModel)
}
