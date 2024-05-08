package com.example.assignment3.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.assignment3.Entity.Hospital
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {

    @Query("SELECT * FROM hospital")
    fun getAll(): Flow<List<Hospital>>

    @Insert
    fun insertAll(vararg hospitals: Hospital)
}