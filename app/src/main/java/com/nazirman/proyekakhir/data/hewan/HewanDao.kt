package com.nazirman.proyekakhir.data.hewan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface HewanDao {

    @Query("SELECT * FROM hewan ORDER BY namahewan")
    fun getPatients(): Flow<List<Hewan>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Hewan: Hewan)


}