package com.foo.assignment.ui.detail

import com.foo.assignment.data.repository.ApiRepository
import com.foo.assignment.ui.base.BaseViewModel
import javax.inject.Inject


class BookDetailViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

}