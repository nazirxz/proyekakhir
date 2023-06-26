package com.nazirman.proyekakhir.data.hewan

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Hewan::class],
    version = 1,
    exportSchema = false
)
abstract class HewanDatabase : RoomDatabase() {
    abstract fun hewanDao():HewanDao

}