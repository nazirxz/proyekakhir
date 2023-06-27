package com.nazirman.proyekakhir.data.kuis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface KuisDao {

    @Query("SELECT * FROM kuis")
    fun getKuis(): Flow<List<Kuis>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kuis: Kuis)

}