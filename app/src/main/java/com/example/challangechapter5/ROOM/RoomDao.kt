package com.example.challangechapter5.ROOM

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RoomDao {
    @Insert
    fun insertData(data: RoomData)
}