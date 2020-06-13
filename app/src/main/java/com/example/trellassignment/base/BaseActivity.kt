package com.example.trellassignment.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun bindAndSetupUi()
}