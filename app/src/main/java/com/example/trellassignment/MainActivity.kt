package com.example.trellassignment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.trellassignment.base.BaseActivity
import com.example.trellassignment.databinding.ActivityMainBinding
import com.example.trellassignment.helpers.FileManager
import com.example.trellassignment.util.StorageUtility
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : BaseActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var storage: File
    private lateinit var allPath: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bindAndSetupUi()
        loadAllVideoFiles()
    }

    override fun bindAndSetupUi() {
        videoRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        videoRecyclerView.adapter = VideoAdapter(this)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(videoRecyclerView)
    }

    private fun loadAllVideoFiles() {
        allPath = StorageUtility.getStorageDirectories(this)
        for (path in allPath) {
            storage = File(path)
            FileManager.checkForPermissions(this)
            FileManager.loadVideoFilesFromStorage(storage)
        }
    }

}
