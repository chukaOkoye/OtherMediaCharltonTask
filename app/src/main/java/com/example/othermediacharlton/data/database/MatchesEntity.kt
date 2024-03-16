package com.example.othermediacharlton.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity(tableName = "fixtures")
data class FixturesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val matches: String
    )



