package com.example.finhealth.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface GainOutlayDao {

    @Query("select * from table_GainOutlay")
    fun listGainOutlay(): Flow<List<GainOutlayModel>>

    @Query("select * from table_GainOutlay where id = :idx")
    suspend fun getById(idx: Int): GainOutlayModel

    @Upsert
    suspend fun registerAndSaveGainOutlay(gainOutlayModel: GainOutlayModel)

    @Delete
    suspend fun delete(gainOutlayModel: GainOutlayModel)
}