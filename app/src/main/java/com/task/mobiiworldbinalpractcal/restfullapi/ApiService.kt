package com.task.mobiiworldbinalpractcal.restfullapi

import com.task.mobiiworldbinalpractcal.data.response.AdvertiseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("orgs/square/repos")
    suspend fun getRepositoryData(
        @Query("page") page: Int, @Query("pageSize") pageSize: Int
    ): Response<AdvertiseResponse>

}