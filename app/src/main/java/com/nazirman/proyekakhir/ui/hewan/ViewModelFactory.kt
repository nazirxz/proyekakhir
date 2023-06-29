package com.nazirman.proyekakhir.ui.hewan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazirman.proyekakhir.data.hewan.HewanDao
import com.nazirman.proyekakhir.data.kuis.KuisDao

class ViewModelFactory(private val hewanDao: HewanDao) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HewanViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HewanViewModel(hewanDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}