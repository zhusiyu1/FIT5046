package com.example.assignment3.DAO

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment3.Entity.Hospital
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {

    @WorkerThread
    @Query("SELECT * FROM hospital")
    fun getAll(): Flow<List<Hospital>>

    @WorkerThread
    @Insert
    fun insertAll(vararg hospitals: Hospital)

    @Query("DELETE FROM hospital")
    fun deleteAll()
}