package com.task.mobiiworldbinalpractcal.view.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.mobiiworldbinalpractcal.data.response.Post
import com.task.mobiiworldbinalpractcal.di.db.DatabaseRepository
import com.task.mobiiworldbinalpractcal.repository.MainRepository
import com.task.mobiiworldbinalpractcal.util.ApiResource
import com.task.mobiiworldbinalpractcal.util.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val databaseRepository: DatabaseRepository,
    private val networkHelper: NetworkHelper,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val pageSize = 10 // Define how many items per page
    private var isLastPage = false

    private val mGetMutableActivityData = MutableLiveData<ApiResource<List<Post>>>()
    val mActivityDataList: LiveData<ApiResource<List<Post>>> get() = mGetMutableActivityData

    private val _selectedRepository = MutableLiveData<ApiResource<Post>>()
    val selectedRepository: LiveData<ApiResource<Post>> get() = _selectedRepository

    fun getRepoData(page: Int) {
        if (isLastPage) return
        mGetMutableActivityData.postValue(ApiResource.loading())
        viewModelScope.launch {
            val localData = databaseRepository.getRepositoryPaged(page, 10).first()
            if (localData.isNotEmpty()) {
                mGetMutableActivityData.postValue(ApiResource.success(localData))
            } else {
                getRepositoryData(page)
            }
        }
    }

    private fun getSelectedRepoData(id: Int) {
        _selectedRepository.postValue(ApiResource.loading())
        viewModelScope.launch {
            val localData = databaseRepository.getSelectedRepo(id).first()
            _selectedRepository.postValue(ApiResource.success(localData))

        }
    }

    private fun getRepositoryData(page: Int) {
        viewModelScope.launch {
            mGetMutableActivityData.postValue(ApiResource.loading())
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getRepositoryData(page, pageSize).let {
                    if (it.isSuccessful) {
                        it.body()?.let { it1 ->
                            databaseRepository.insertAll(
                                it1
                            )
                        }
                        val localData = databaseRepository.getRepositoryPaged(page, 10).first()
                        mGetMutableActivityData.postValue(ApiResource.success(localData))

                    } else {
                        mGetMutableActivityData.postValue(
                            ApiResource.error(
                                it.errorBody().toString(), null
                            )
                        )
                    }
                }
            } else {
                mGetMutableActivityData.postValue(
                    ApiResource.error(
                        "No Active Internet Connection", null
                    )
                )
            }
        }
    }

    fun addOrUpdateBookmark(post: Post) {
        viewModelScope.launch {
            databaseRepository.addOrUpdateBookmark(post)
        }
    }

    fun setClickedData(id: Int) {
        sharedPreferences.edit().putInt("id", id).apply()

    }

    fun getClickedData(id: Int) {
        val selectedId = sharedPreferences.getInt("id", id)
        getSelectedRepoData(selectedId)
    }
}
