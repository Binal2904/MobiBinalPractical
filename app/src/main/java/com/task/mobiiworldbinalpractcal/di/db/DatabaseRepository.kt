package com.task.mobiiworldbinalpractcal.di.db

import androidx.annotation.WorkerThread
import com.task.mobiiworldbinalpractcal.data.response.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val alertDao: RepositoryDao) {

    suspend fun getRepositoryPaged(page: Int, limit: Int) = withContext(Dispatchers.IO) {
        val offset = page * limit
        alertDao.getRepositoryPaged(offset, limit)
    }

    suspend fun getSelectedRepo(page: Int) = withContext(Dispatchers.IO) {
        alertDao.getSelectedRepo(page)
    }

    suspend fun insertAll(posts: List<Post>) = withContext(Dispatchers.IO) {
        alertDao.insertAll(posts)
    }


    suspend fun addOrUpdateBookmark(bookmark: Post) {
        alertDao.addOrUpdateBookmark(bookmark)
    }

}