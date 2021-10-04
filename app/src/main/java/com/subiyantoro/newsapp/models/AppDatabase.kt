package com.subiyantoro.newsapp.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.subiyantoro.newsapp.services.NewsDaoService

@Database(entities = [Articles::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun daoNews(): NewsDaoService
}