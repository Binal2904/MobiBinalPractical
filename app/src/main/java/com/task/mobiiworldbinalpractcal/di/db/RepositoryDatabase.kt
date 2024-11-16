package com.task.mobiiworldbinalpractcal.di.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.mobiiworldbinalpractcal.data.response.Post

@Database(entities = [Post::class], version = 1)
abstract class RepositoryDatabase : RoomDatabase(){
    abstract fun repositoryDao():RepositoryDao

}