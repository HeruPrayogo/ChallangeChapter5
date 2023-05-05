package com.example.challangechapter5.ROOM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username:String,
    var email: String,
    var password:String
)