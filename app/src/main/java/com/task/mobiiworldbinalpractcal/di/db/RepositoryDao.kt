package com.task.mobiiworldbinalpractcal.di.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.mobiiworldbinalpractcal.data.response.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Delete
    fun delete(alert: Post)

    @Query("SELECT * FROM post LIMIT :limit OFFSET :offset")
    fun getRepositoryPaged(offset: Int, limit: Int): Flow<List<Post>>

    @Query("SELECT * FROM post Where id = :id")
    fun getSelectedRepo(id: Int): Flow<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateBookmark(post: Post)
}