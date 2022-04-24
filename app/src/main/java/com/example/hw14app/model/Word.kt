package com.example.hw14app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int, var persian: String, var english: String, var example: String,
    var synonym: String, var weakiLink: String, var soundAddress: String
)