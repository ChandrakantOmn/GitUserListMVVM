package com.foo.assignment.di.module

import com.foo.assignment.ui.detail.BookDetailActivity
import com.foo.assignment.ui.books.BooksActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by srinivas on 2019-06-28.
 */

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): BooksActivity

    @ContributesAndroidInjector
    abstract fun contributeUserDetailActivity(): BookDetailActivity
}