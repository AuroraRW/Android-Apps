package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item:Item)

    @Update
    suspend fun update(item:Item)

    @Delete
    suspend fun delete(item:Item)

    @Query("select * from items where id=:id")
    fun getItem(id: Int): Flow<Item>

    @Query("select * from items order by name ASC")
    fun getAllItem(): Flow<List<Item>>
}