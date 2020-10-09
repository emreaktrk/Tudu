package com.akturk.tudu

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layoutResId: Int) : AppCompatActivity(layoutResId)
