package com.akturk.tudu

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

interface ViewModelSupport<T : ViewModel> {
    val viewModel: T

    fun invoke(owner: ViewModelStoreOwner, clazz: Class<T>): Lazy<T> {
        return lazy { ViewModelProvider(owner).get(clazz) }
    }
}

interface BindingSupport<T : ViewDataBinding> {

    val binding: T

    fun invoke(activity: Activity, @LayoutRes layoutResId: Int): Lazy<T> {
        return lazy { DataBindingUtil.setContentView(activity, layoutResId) as T }
    }
}