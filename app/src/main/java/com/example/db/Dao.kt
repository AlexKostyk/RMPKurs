package com.example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insert(slangtable: Slangtable)
    @Query("SELECT * FROM slangtable")
    fun getAllItem(): Flow<List<Slangtable>>
    @Query("SELECT description FROM slangtable WHERE slang LIKE :slang")
    fun getDescription(slang: String): String?
    @Query("SELECT synonym FROM slangtable WHERE slang LIKE :slang")
    fun getSynonym(slang: String): String?
    @Query("SELECT id FROM slangtable WHERE id = 1")
    fun getId1(): Long?
    @Query("DELETE FROM slangtable")
    fun clear()
    @Query("SELECT * FROM slangtable ORDER BY id DESC LIMIT 1")
    fun getTonight(): Slangtable?

}