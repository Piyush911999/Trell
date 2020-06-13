package com.example.trellassignment.helpers

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.core.app.ActivityCompat
import com.example.trellassignment.util.AppConstants
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class FileManager {

    companion object {
        var READ_EXTERNAL_STORAGE_CODE = 1001

        fun checkForPermissions(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE_CODE
                )
            } else {
                // Permission not required or already granted
            }

        }

        fun loadVideoFilesFromStorage(directory: File) {
            val fileList: Array<File> = directory.listFiles()
            if (fileList.isNotEmpty()) {
                for (fileItem in fileList) {
                    if (fileItem.isDirectory) {
                        loadVideoFilesFromStorage(fileItem)
                    } else {
                        val fileName: String = fileItem.name.toLowerCase(Locale.ROOT)
                        for (extension in AppConstants.videoFileFormats) {
                            if (fileName.endsWith(extension)) {
                                allVideoList.add(fileItem)
                                break
                            }
                        }
                    }
                }
            }
        }

        var allVideoList: ArrayList<File> = ArrayList()
    }
}