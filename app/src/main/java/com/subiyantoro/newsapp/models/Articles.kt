package com.subiyantoro.newsapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Articles(
//    @PrimaryKey(autoGenerate = true) var id: Int = 0,
//    @Embedded var source: SourceNews?,
    var author: String,
    @PrimaryKey var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
): Serializable
