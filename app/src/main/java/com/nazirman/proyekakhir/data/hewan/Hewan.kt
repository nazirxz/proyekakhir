package com.nazirman.proyekakhir.data.hewan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hewan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "namahewan")
    val namaHewan: String,
    @ColumnInfo(name = "habitathewan")
    val habitatHewan: String,
    @ColumnInfo(name = "deskripsi")
    val deskripsi: String,
    )