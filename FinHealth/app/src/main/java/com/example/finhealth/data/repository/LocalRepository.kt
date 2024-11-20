package com.example.finhealth.data.repository

import android.app.Application
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    application: Application
) {

//    private val dao: GainOutlayDao = DataBaseROOM.getInstance(application).getGainOutlayDao()

     suspend fun fetchById(id: Int): GainOutlayModel? {
        // Similarmente, este método não será usado, mas precisa ser implementado.
        return null  // Apenas retornando null como placeholder
    }

     suspend fun saveAll(gainOutlays: List<GainOutlayModel>) {
        // Método vazio para evitar erros
    }

     suspend fun save(gainOutlay: GainOutlayModel) {
        // Método vazio para evitar erros
    }

     suspend fun update(gainOutlay: GainOutlayModel) {
        // Método vazio para evitar erros
    }

     suspend fun delete(gainOutlay: GainOutlayModel) {
        // Método vazio para evitar erros
    }
}

