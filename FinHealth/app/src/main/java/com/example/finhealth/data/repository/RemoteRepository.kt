package com.example.finhealth.data.repository

import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RemoteRepository : IRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val gainOutlayCollection = firestore.collection("gain_outlays")

    override fun listGainOutlay(): Flow<List<GainOutlayModel>> = callbackFlow {
        val listener = gainOutlayCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val gainOutlays = snapshot?.documents?.mapNotNull {
                it.toObject(GainOutlayModel::class.java)
            } ?: emptyList()
            trySend(gainOutlays).isSuccess
        }
        awaitClose { listener.remove() }
    }

    override suspend fun getById(idx: Int): GainOutlayModel {
        val document = gainOutlayCollection.document(idx.toString()).get().await()
        return document.toObject(GainOutlayModel::class.java)
            ?: throw Exception("Registro não encontrado.")
    }

    override suspend fun updateAndSaveGainOutlay(gainOutlayModel: GainOutlayModel) {
        gainOutlayCollection.document(gainOutlayModel.id.toString())
            .set(gainOutlayModel)
            .await()
    }

    override suspend fun delete(gainOutlayModel: GainOutlayModel) {
        gainOutlayCollection.document(gainOutlayModel.id.toString()).delete().await()
    }
}