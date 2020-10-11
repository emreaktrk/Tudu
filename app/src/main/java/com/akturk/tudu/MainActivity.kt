package com.akturk.tudu

import android.app.Application
import android.os.Bundle
import com.akturk.tudu.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main),
    ViewModelSupport<MainViewModel>,
    BindingSupport<ActivityMainBinding>,
    MainViewModel.Presenter {

    override val viewModel: MainViewModel by invoke(this, MainViewModel::class.java)

    override val binding: ActivityMainBinding by invoke(this, R.layout.activity_main)

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        viewModel.presenter = this

        viewModel.observe()
    }

    override fun presentApplication(): Application = application
}