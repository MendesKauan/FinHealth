package com.example.finhealth.data.repository

import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao: GainOutlayDao
): IRepository {

    override fun listGainOutlay(): Flow<List<GainOutlayModel>> {
        return dao.listGainOutlay()
    }

    override suspend fun getById(idx: Int): GainOutlayModel {
        return dao.getById(idx)
    }

    override suspend fun updateAndSaveGainOutlay(gainOutlayModel: GainOutlayModel) {
        dao.updateAndSaveGainOutlay(gainOutlayModel)
    }

    override suspend fun delete(gainOutlayModel: GainOutlayModel) {
        dao.delete(gainOutlayModel)
    }

}