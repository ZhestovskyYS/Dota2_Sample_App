package com.example.domain.impl.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.impl.db.dao.HeroDao
import com.example.domain.impl.db.entity.HeroEntity

@Database(
    entities = [
        HeroEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}