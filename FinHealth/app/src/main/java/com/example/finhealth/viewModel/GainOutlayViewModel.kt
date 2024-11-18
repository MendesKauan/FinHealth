package com.example.finhealth.viewModel

import android.app.Application
import android.util.Log
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
        // Coleta os dados da ROOM e atualiza o estado
        viewModelScope.launch {
            localRepository.listGainOutlay().collect { list ->
                _gainOutlays.value = list
            }
        }

        syncWithFirestore()
        // Inicia o listener do Firestore para escutar mudanças
        startFirestoreListener() // Sincroniza dados na inicialização
    }

    fun syncWithFirestore() {
        firestore.collection("GainOutlayColection")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                snapshot?.let { documentSnapshot ->
                    val gainOutlaysFromFirestore = documentSnapshot.documents.mapNotNull { doc ->
                        doc.toObject(GainOutlayModel::class.java)?.copy(id = null) // Ignora o id para auto-generate
                    }

                    viewModelScope.launch(Dispatchers.IO) {
                        val existingOutlays = localRepository.listGainOutlayOnce()
                        val newOutlays = gainOutlaysFromFirestore.filter { fireStoreOutlay ->
                            existingOutlays.none { it.id == fireStoreOutlay.id }
                        }

                        if (newOutlays.isNotEmpty()) {
                            localRepository.updateAndSaveGainOutlayList(newOutlays)
                        }
                    }
                }
            }
    }

    // Método para iniciar o listener do Firestore
    fun startFirestoreListener() {
        if (isFirestoreListenerActive) {
            Log.d("FirestoreListener", "Listener já está ativo.")
            return
        }
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

                    Log.d("FirestoreListener", "Dados sincronizados com o Firestore.")

                    viewModelScope.launch(Dispatchers.IO) {
                        localRepository.updateAndSaveGainOutlayList(gainOutlaysFromFirestore)
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