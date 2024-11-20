package com.example.finhealth.data.repository

import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RemoteRepository : IRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val gainOutlayCollection = firestore.collection("GainOutlayCollection")

    override fun fetchAll(): Flow<List<GainOutlayModel>> = callbackFlow {
        val listener = gainOutlayCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val gainOutlays = snapshot?.documents?.mapNotNull { it.toObject(GainOutlayModel::class.java) } ?: emptyList()
            trySend(gainOutlays).isSuccess
        }
        awaitClose { listener.remove() }
    }

    override suspend fun fetchById(id: Int): GainOutlayModel? {
        val data = gainOutlayCollection.document(id.toString()).get().await()
        val gainOutlay = data.toObject(GainOutlayModel::class.java)

        return gainOutlay
    }

    override suspend fun saveAll(gainOutlays: List<GainOutlayModel>) {
        gainOutlays.forEach { save(it) }
    }

    override suspend fun save(gainOutlay: GainOutlayModel) {
        val document = gainOutlayCollection.document(gainOutlay.id.toString())
        document.set(gainOutlay).await()
    }

    override suspend fun update(gainOutlay: GainOutlayModel) {
        val document = gainOutlayCollection.document(gainOutlay.id.toString())
        document.update(
            "value", gainOutlay.value,
            "description", gainOutlay.description,
            "type", gainOutlay.type
        ).await()
    }

    override suspend fun delete(gainOutlay: GainOutlayModel) {
        gainOutlayCollection.document(gainOutlay.id.toString()).delete().await()
    }
}
