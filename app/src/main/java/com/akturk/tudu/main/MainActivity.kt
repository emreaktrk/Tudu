package com.akturk.tudu.main

import android.app.Application
import android.os.Bundle
import com.akturk.domain.model.TodoItem
import com.akturk.tudu.BaseActivity
import com.akturk.tudu.BindingSupport
import com.akturk.tudu.R
import com.akturk.tudu.ViewModelSupport
import com.akturk.tudu.add.AddActivity
import com.akturk.tudu.databinding.ActivityMainBinding
import com.akturk.tudu.map.MapActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main),
    ViewModelSupport<MainViewModel>,
    BindingSupport<ActivityMainBinding>,
    MainViewModel.Presenter {

    override val viewModel: MainViewModel by invoke(this, MainViewModel::class.java)

    override val binding: ActivityMainBinding by invoke(this, R.layout.activity_main)

    private val adapter = TodoAdapter()

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recycler.adapter = adapter
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.presenter = this
        viewModel.observe()
    }

    @FlowPreview
    override fun onResume() {
        super.onResume()

        viewModel.clearSearch()
    }

    override fun presentApplication(): Application = application

    override fun presentItems(items: List<TodoItem>) {
        adapter.items = items
        binding.recycler.scrollToPosition(adapter.itemCount - 1)
    }

    override fun presentAddScreen() {
        AddActivity.start(this)
    }

    override fun presentMapScren() {
        MapActivity.start(this)
    }
}