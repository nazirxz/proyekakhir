package com.nazirman.proyekakhir.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.data.hewan.HewanDao
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.data.kuis.KuisDao

@Database(
    entities = [Hewan::class, Kuis::class],
    version = 1,
    exportSchema = false
)
abstract class HewanDatabase : RoomDatabase() {
    abstract fun hewanDao(): HewanDao
    abstract fun kuisDao(): KuisDao

}