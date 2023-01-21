package com.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(slangtable: Slangtable)
    @Query("SELECT * FROM slangtable")
    fun getAllItem(): Flow<List<Slangtable>>
    @Query("SELECT * FROM slangtable WHERE id = :key")
    fun get(key: Long): Slangtable?
    @Query("DELETE FROM slangtable")
    fun clear()
    @Query("DELETE FROM slangtable WHERE id = 9999")
    fun clearTestValue()
}