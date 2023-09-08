package com.example.mytodoapp.addtasks.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * from task_table")
    fun getTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(item: TaskEntity)

    @Update
    suspend fun updateTask(item: TaskEntity)

    @Delete
    suspend fun deleteTask(item: TaskEntity)
}