package com.foo.assignment.di.module

import androidx.lifecycle.ViewModel
import com.foo.assignment.di.ViewModelKey
import com.foo.assignment.ui.detail.UserDetailViewModel
import com.foo.assignment.ui.books.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by srinivas on 2019-06-28.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun bindUserDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel
}