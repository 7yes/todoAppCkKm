package com.example.mytodoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TodoDB:RoomDatabase() {
    abstract  fun getTaskDao():TaskDao
}