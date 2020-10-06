package com.foo.assignment.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.data.remote.ApiServices
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class BookListDataSource(
    private val apiServices: ApiServices,
    private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Long, BooksResponse.Item>() {


    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    /**
     * Keep Completable reference for the retry event
     */
    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, {})
            )
        }
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<BooksResponse.Item>) {
        // update states.
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(apiServices.getBooks("health", 20).subscribe({ users ->
            // clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            users.items?.let { callback.onResult(it) }
        }, { throwable ->
            // keep a Completable for future retry
            setRetry(Action { loadInitial(params, callback) })
            val error = NetworkState.error(throwable.message)
            // publish the error
            networkState.postValue(error)
            initialLoad.postValue(error)
        }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<BooksResponse.Item>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(apiServices.getBooks("health", 0).subscribe({ users ->
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            users.items?.let { callback.onResult(it) }
        }, { throwable ->
            setRetry(Action { loadAfter(params, callback) })
            networkState.postValue(NetworkState.error(throwable.message))
        }))
    }

    override fun getKey(item: BooksResponse.Item): Long {
        return 20
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<BooksResponse.Item>) {
        // ignored
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

}