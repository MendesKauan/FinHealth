package com.example.finhealth.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.example.finhealth.data.repository.IRepository
import com.example.finhealth.data.repository.LocalRepository
import com.example.finhealth.data.repository.RemoteRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GainOutlayViewModel (
    application: Application,
): AndroidViewModel(application) {

    private val remoteRepository: IRepository = RemoteRepository()
    private val firestore = FirebaseFirestore.getInstance()
    private var isFirestoreListenerActive = false

    private val _gainOutlays = MutableStateFlow<List<GainOutlayModel>>(emptyList())
    val gainOutlays: StateFlow<List<GainOutlayModel>> get() = _gainOutlays
    val gainOutlayList: LiveData<List<GainOutlayModel>> = remoteRepository.fetchAll().asLiveData()

    init {
        syncWithFirestore()
        startFirestoreListener()
    }

    fun syncWithFirestore() {
        firestore.collection("GainOutlayCollection")
            .get()
            .addOnSuccessListener { snapshot ->
                val gainOutlaysFromFirestore = snapshot.documents.mapNotNull {
                    it.toObject(GainOutlayModel::class.java)
                }
                Log.d("GainOutlay", "Dados do Firestore: $gainOutlaysFromFirestore")
                _gainOutlays.value = gainOutlaysFromFirestore
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun startFirestoreListener() {
        if (isFirestoreListenerActive) return // Evita múltiplas inscrições
        isFirestoreListenerActive = true

        firestore.collection("GainOutlayCollection")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                snapshot?.let {
                    val gainOutlaysFromFirestore = it.documents.mapNotNull { doc ->
                        doc.toObject(GainOutlayModel::class.java)
                    }
                    val currentList = _gainOutlays.value
                    if (currentList != gainOutlaysFromFirestore) {
                        _gainOutlays.value = gainOutlaysFromFirestore // Atualiza a lista local com os dados mais recentes do Firebase
                    }
                }
            }
    }

    fun saveGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            remoteRepository.save(gainOutlay)
        }
    }

    fun deleteGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            remoteRepository.delete(gainOutlay)
        }
    }

    fun updateGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            remoteRepository.update(gainOutlay)
        }
    }

}