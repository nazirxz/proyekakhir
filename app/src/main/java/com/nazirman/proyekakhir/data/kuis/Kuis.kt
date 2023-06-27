package com.nazirman.proyekakhir.data.kuis

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Kuis(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "soal")
    val soal: String,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "option1")
    val option1: String,
    @ColumnInfo(name = "option2")
    val option2: String,
    @ColumnInfo(name = "option3")
    val option3: String,
    @ColumnInfo(name = "option4")
    val option4: String,
    @ColumnInfo(name = "jawaban")
    val jawaban: String
)

