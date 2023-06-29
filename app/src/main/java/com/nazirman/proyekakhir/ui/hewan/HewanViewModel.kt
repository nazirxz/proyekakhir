package com.nazirman.proyekakhir.ui.hewan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.data.hewan.HewanDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HewanViewModel(private val HewanDao: HewanDao) : ViewModel() {

    fun getAnimals(): Flow<List<Hewan>> {
        return HewanDao.getAnimals()
    }

    fun insertAnimal(hewanList: List<Hewan>) {
        viewModelScope.launch {
            HewanDao.insert(hewanList as ArrayList<Hewan>)
        }
    }
}