package com.foo.assignment.ui.detail

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.foo.assignment.R
import com.foo.assignment.data.model.BooksResponse
import com.foo.assignment.databinding.ActivityDetailBinding
import com.foo.assignment.ui.base.BaseActivity


class BookDetailActivity : BaseActivity<UserDetailViewModel, ActivityDetailBinding>(UserDetailViewModel::class.java) {

    companion object {
        const val USER: String = "USER"
        const val TRANSITION_NAME: String = "TRANSITION_NAME"
    }

    lateinit var item: BooksResponse.Item

    override fun getLayoutRes(): Int {
        return R.layout.activity_detail
    }

    override fun initViewModel(viewModel: UserDetailViewModel) {
        binding.item = item
        binding.executePendingBindings()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        item = intent!!.extras!!.get(USER) as BooksResponse.Item
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName = intent.getStringExtra(TRANSITION_NAME)
            binding.ivProfilePic.transitionName = imageTransitionName
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}