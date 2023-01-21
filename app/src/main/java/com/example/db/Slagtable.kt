package com.example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "slangtable")
data class Slangtable(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    @ColumnInfo(name = "slang")
    var slang: String?,
    @ColumnInfo(name = "synonym")
    var synonym: String?,
    @ColumnInfo(name = "description")
    var description: String?,
)