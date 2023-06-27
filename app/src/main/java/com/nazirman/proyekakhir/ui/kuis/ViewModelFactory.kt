package com.nazirman.proyekakhir.ui.kuis

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazirman.proyekakhir.data.kuis.KuisDao

class ViewModelFactory(private val kuisDao: KuisDao) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KuisViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KuisViewModel(kuisDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}