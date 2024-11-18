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

    suspend fun listGainOutlayOnce(): List<GainOutlayModel> {
        return dao.listGainOutlayOnce()  // Retorna diretamente os dados da Room de forma síncrona
    }

    // Atualize o método para lidar com múltiplos registros
    override suspend fun updateAndSaveGainOutlayList(gainOutlays: List<GainOutlayModel>) {
        // Pega os registros do banco de dados local
        val existingOutlays = dao.listGainOutlayOnce()

        // Cria um set com os IDs dos registros existentes no banco
        val existingIds = existingOutlays.map { it.id }.toSet()

        // Filtra os registros novos que não estão presentes no banco
        val newOutlays = gainOutlays.filter { it.id !in existingIds }

        // Insere apenas os novos registros (que não estão no banco)
        if (newOutlays.isNotEmpty()) {
            dao.insertAll(newOutlays)  // Inserção em lote
        }

        // Atualiza os registros que já existem (se necessário)
        gainOutlays.filter { it.id in existingIds }
            .forEach { dao.update(it) }  // Atualização individual
    }


    override suspend fun updateAndSaveGainOutlay(gainOutlay: GainOutlayModel) {
        return dao.updateAndSaveGainOutlay(gainOutlay)
    }

    override suspend fun delete(gainOutlayModel: GainOutlayModel) {
        dao.delete(gainOutlayModel)
    }
}


