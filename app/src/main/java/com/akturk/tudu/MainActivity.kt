package com.akturk.tudu

import android.app.Application
import android.os.Bundle
import com.akturk.tudu.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main),
    ViewModelSupport<MainViewModel>,
    BindingSupport<ActivityMainBinding>,
    MainViewModel.Presenter {

    override val viewModel: MainViewModel by invoke(this, MainViewModel::class.java)

    override val binding: ActivityMainBinding by invoke(this, R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel
        viewModel.presenter = this

        viewModel.observeItems()
        viewModel.observeSearch()
    }

    override fun presentApplication(): Application = application
}