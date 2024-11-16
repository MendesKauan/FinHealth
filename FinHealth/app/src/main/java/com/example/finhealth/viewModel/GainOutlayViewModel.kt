package com.example.finhealth.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finhealth.data.ROOM.DataBaseROOM
import com.example.finhealth.data.models.GainOutlay.GainOutlayDao
import com.example.finhealth.data.models.GainOutlay.GainOutlayModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GainOutlayViewModel (application: Application): AndroidViewModel(application) {

    private val dao = DataBaseROOM.getInstance(application).getGainOutlayDao()

    private val _gainOutlays = MutableStateFlow<List<GainOutlayModel>>(emptyList())
    val gainOutlays: StateFlow<List<GainOutlayModel>> get() = _gainOutlays

    init {
        viewModelScope.launch {
            dao.listGainOutlay().collect { list ->
                _gainOutlays.value = list
            }
        }
    }

    fun updateAndSaveGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            dao.updateAndSaveGainOutlay(gainOutlay)
        }
    }

    fun deleteGainOutlay(gainOutlay: GainOutlayModel) {
        viewModelScope.launch {
            dao.delete(gainOutlay)
        }
    }

}