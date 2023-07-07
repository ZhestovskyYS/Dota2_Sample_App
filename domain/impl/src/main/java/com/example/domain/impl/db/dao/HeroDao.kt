package com.example.domain.impl.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.impl.db.entity.HeroEntity

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero")
    suspend fun getAll(): List<HeroEntity>

    @Query(
        """
        SELECT * FROM hero WHERE
        id LIKE :id
        LIMIT 1
        """
    )
    suspend fun finById(id: Int): HeroEntity

    @Insert
    suspend fun insertAll(heroList: List<HeroEntity>)

    @Delete
    suspend fun remove(heroEntity: HeroEntity)
}