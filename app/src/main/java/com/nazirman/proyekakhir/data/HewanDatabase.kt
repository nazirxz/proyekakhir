package com.nazirman.proyekakhir.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazirman.proyekakhir.data.hewan.Hewan
import com.nazirman.proyekakhir.data.hewan.HewanDao
import com.nazirman.proyekakhir.data.kuis.Kuis
import com.nazirman.proyekakhir.data.kuis.KuisDao

@Database(
    entities = [Hewan::class],
    version = 2,
    exportSchema = false
)
abstract class HewanDatabase : RoomDatabase() {
    abstract fun hewanDao(): HewanDao
//    abstract fun kuisDao(): KuisDao
    companion object {
        @Volatile
        private var INSTANCE: HewanDatabase? = null

        fun getDatabase(context: Context): HewanDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HewanDatabase::class.java,
                    "animal_database"
                ).allowMainThreadQueries()
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}