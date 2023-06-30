package com.nazirman.proyekakhir.data.hewan

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hewan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "namahewan")
    var namaHewan: String,
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,
    @ColumnInfo(name = "keteranganHabitat")
    var keteranganHabitat: String,
    @ColumnInfo(name = "gambar")
    var gambar: Int = 0,
    @ColumnInfo(name = "gambarHabitat")
    var gambarHabitat: Int = 0,
    )