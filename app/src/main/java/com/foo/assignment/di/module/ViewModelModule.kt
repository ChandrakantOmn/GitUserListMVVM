package com.foo.assignment.di.module

import androidx.lifecycle.ViewModel
import com.foo.assignment.di.ViewModelKey
import com.foo.assignment.ui.detail.BookDetailViewModel
import com.foo.assignment.ui.books.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: BooksViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    abstract fun bindUserDetailViewModel(bookDetailViewModel: BookDetailViewModel): ViewModel
}