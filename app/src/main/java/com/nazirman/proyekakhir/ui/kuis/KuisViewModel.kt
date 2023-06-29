package com.nazirman.proyekakhir.ui.kuis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nazirman.proyekakhir.R
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.data.kuis.KuisDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class KuisViewModel(private val kuisDao:KuisDao) : ViewModel() {
//
//    fun getKuis(): Flow<List<Kuis>> {
//        return kuisDao.getKuis()
//    }
//
//    fun insertKuis(kuisList: List<Kuis>) {
//        viewModelScope.launch {
//            kuisDao.insert(kuisList)
//        }
//    }


}