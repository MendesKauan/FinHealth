package com.example.finhealth.viewModel

import android.app.Application
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import com.example.finhealth.data.repository.IRepository
import com.example.finhealth.data.repository.LocalRepository
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

    private val localRepository = LocalRepository(application)
    private val firestore = FirebaseFirestore.getInstance()
    private var isFirestoreListenerActive = false

    private val _gainOutlays = MutableStateFlow<List<GainOutlayModel>>(emptyList())
    val gainOutlays: StateFlow<List<GainOutlayModel>> get() = _gainOutlays

    init {
        viewModelScope.launch {
            localRepository.listGainOutlay().collect { list ->
                _gainOutlays.value = list
            }
        }
        syncWithFirestore()
        startFirestoreListener()// Sincroniza dados na inicialização
    }

    fun syncWithFirestore() {
        firestore.collection("GainOutlayColection")
            .get()
            .addOnSuccessListener { snapshot ->
                val gainOutlaysFromFirestore = snapshot.documents.mapNotNull {
                    it.toObject(GainOutlayModel::class.java)
                }
                viewModelScope.launch(Dispatchers.IO) {
                    localRepository.updateAndSaveGainOutlayList(gainOutlaysFromFirestore)
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    fun startFirestoreListener() {
        if (isFirestoreListenerActive) return // Evita múltiplas inscrições
        isFirestoreListenerActive = true

        firestore.collection("GainOutlayColection")
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
                        viewModelScope.launch(Dispatchers.IO) {
                            localRepository.updateAndSaveGainOutlayList(gainOutlaysFromFirestore)
                        }
                    }
                }
            }
    }

    fun updateAndSaveGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            localRepository.updateAndSaveGainOutlay(gainOutlay)
        }
    }

    fun deleteGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            localRepository.delete(gainOutlay)
        }
    }

}