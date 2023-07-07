package com.example.domain.impl.db

import androidx.room.Room
import com.example.utils.ContextHolder

private const val DB_NAME = "Dota2_Database"

object DatabaseService {
    val db by lazy {
        Room
            .databaseBuilder(
                ContextHolder.instance!!,
                AppDatabase::class.java,
                DB_NAME,
            )
            .build()
    }
}