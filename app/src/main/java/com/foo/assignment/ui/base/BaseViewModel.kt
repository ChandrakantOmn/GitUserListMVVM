package com.foo.assignment.ui.base

import androidx.lifecycle.ViewModel


open class BaseViewModel() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}