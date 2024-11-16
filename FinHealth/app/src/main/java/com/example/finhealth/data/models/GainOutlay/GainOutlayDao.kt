package com.example.finhealth.data.models.GainOutlay

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface GainOutlayDao {

    @Query("select * from table_GainOutlay ORDER BY id DESC")
    fun listGainOutlay(): Flow<List<GainOutlayModel>>

    @Query("select * from table_GainOutlay where id = :idx")
    suspend fun getById(idx: Int): GainOutlayModel

    @Query("SELECT * FROM table_GainOutlay")
    fun getAllGainOutlays(): Flow<List<GainOutlayModel>>

    @Upsert
    suspend fun updateAndSaveGainOutlay(gainOutlayModel: GainOutlayModel)

    @Delete
    suspend fun delete(gainOutlayModel: GainOutlayModel)


}