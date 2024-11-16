package com.task.mobiiworldbinalpractcal.repository

import com.task.mobiiworldbinalpractcal.restfullapi.ApiService

class MainRepository(
    private val apiHelper: ApiService
) {
    suspend fun getRepositoryData(
        currentPage: Int, pageSize: Int
    ) = apiHelper.getRepositoryData(page = currentPage, pageSize = pageSize)

}