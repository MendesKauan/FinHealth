package com.example.finhealth.data.models.GainOutlay

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface GainOutlayDao {

    // Retorna uma lista observável de todos os itens
    @Query("SELECT * FROM table_GainOutlay ORDER BY id DESC")
    fun listGainOutlay(): Flow<List<GainOutlayModel>>

    // Retorna uma lista única de todos os itens (usado para operações que não precisam de fluxo)
    @Query("SELECT * FROM table_GainOutlay ORDER BY id DESC")
    suspend fun listGainOutlayOnce(): List<GainOutlayModel>

    // Inserir vários itens em lote (com conflito ignorado)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(outlays: List<GainOutlayModel>)

    // Atualizar um item específico
    @Update
    suspend fun update(outlay: GainOutlayModel)

    // Obter um item por ID
    @Query("SELECT * FROM table_GainOutlay WHERE id = :idx")
    suspend fun getById(idx: Int): GainOutlayModel

    // Inserção ou atualização inteligente
    @Upsert
    suspend fun updateAndSaveGainOutlay(gainOutlayModel: GainOutlayModel)

    // Deletar um item
    @Delete
    suspend fun delete(gainOutlayModel: GainOutlayModel)
}
