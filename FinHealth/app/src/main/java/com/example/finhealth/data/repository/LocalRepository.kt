package com.example.finhealth.data.repository

import android.app.Application
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    application: Application
) : IRepository {

    private val dao: GainOutlayDao = DataBaseROOM.getInstance(application).getGainOutlayDao()

    override fun listGainOutlay(): Flow<List<GainOutlayModel>> {
        return dao.listGainOutlay()
    }

    override suspend fun getById(idx: Int): GainOutlayModel {
        return dao.getById(idx)
    }

    // Atualize o método para lidar com múltiplos registros
    override suspend fun updateAndSaveGainOutlayList(gainOutlays: List<GainOutlayModel>) {
        val existingOutlays = dao.listGainOutlayOnce()
        val existingIds = existingOutlays.map { it.id }.toSet()

        val newOutlays = gainOutlays.filter { it.id !in existingIds }
        val updatedOutlays = gainOutlays.filter { it.id in existingIds }

        dao.insertAll(newOutlays)
        updatedOutlays.forEach { dao.update(it) }
    }

    override suspend fun updateAndSaveGainOutlay(gainOutlay: GainOutlayModel) {
        return dao.updateAndSaveGainOutlay(gainOutlay)
    }

    override suspend fun delete(gainOutlayModel: GainOutlayModel) {
        dao.delete(gainOutlayModel)
    }
}


