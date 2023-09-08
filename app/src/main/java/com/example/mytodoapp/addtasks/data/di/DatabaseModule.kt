package com.example.mytodoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.example.mytodoapp.addtasks.data.TaskDao
import com.example.mytodoapp.addtasks.data.TodoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): TodoDB {
        return Room.databaseBuilder(appContext, TodoDB::class.java, "TaskDatabase").build()
    }

    @Provides
    fun providesTaskDao(todoDB: TodoDB):TaskDao{
        return todoDB.getTaskDao()
    }
}