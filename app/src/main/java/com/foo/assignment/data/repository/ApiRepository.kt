package com.foo.assignment.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.data.remote.ApiServices
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiServices: ApiServices) {

    private val compositeDisposable = CompositeDisposable()

    private val perPage: Int = 20
    private lateinit var sourceFactory: BookListDataSourceFactory

    fun fetBooks(): PagedListWrapper<BooksResponse.Item> {

        sourceFactory = BookListDataSourceFactory(compositeDisposable, apiServices)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(perPage)
            .setInitialLoadSizeHint(perPage * 2)
            .setEnablePlaceholders(false)
            .build()


        val pagedList = LivePagedListBuilder(sourceFactory, config).build()

        return PagedListWrapper(
            pagedList,
            Transformations.switchMap<BookListDataSource, NetworkState>(
                sourceFactory.usersDataSourceLiveData
            ) { it.networkState },
            Transformations.switchMap<BookListDataSource, NetworkState>(
                sourceFactory.usersDataSourceLiveData
            ) { it.initialLoad })
    }


    fun retry() {
        sourceFactory.usersDataSourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.usersDataSourceLiveData.value!!.invalidate()
    }

    fun clear() {
        compositeDisposable.dispose()
    }

}