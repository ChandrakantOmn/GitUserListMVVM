package com.foo.assignment.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.data.remote.ApiServices
import io.reactivex.disposables.CompositeDisposable


class BookListDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val apiServices: ApiServices
) : DataSource.Factory<Long, BooksResponse.Item>() {

    val usersDataSourceLiveData = MutableLiveData<BookListDataSource>()

    override fun create(): DataSource<Long, BooksResponse.Item> {
        val usersDataSource = BookListDataSource(apiServices, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}