package com.foo.assignment.ui.books

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.data.repository.ApiRepository
import com.foo.assignment.data.repository.PagedListWrapper
import com.foo.assignment.data.repository.NetworkState
import com.foo.assignment.ui.base.BaseViewModel
import javax.inject.Inject


/**
 * Created by srinivas on 2019-06-29.
 */
class BooksViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private var userPagedListWrapper: PagedListWrapper<BooksResponse.Item> = apiRepository.fetBooks()

    fun retry() {
        apiRepository.retry()
    }

    fun refresh() {
        apiRepository.refresh()
    }

    fun getBookPagedList(): LiveData<PagedList<BooksResponse.Item>> {
        return userPagedListWrapper.getPagedList()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return userPagedListWrapper.getNetworkState()
    }

    fun getRefreshState(): LiveData<NetworkState> {
        return userPagedListWrapper.getRefreshState()
    }

    override fun onCleared() {
        super.onCleared()
        apiRepository.clear()
    }
}