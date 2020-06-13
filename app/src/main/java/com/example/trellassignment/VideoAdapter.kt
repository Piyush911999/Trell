package com.example.trellassignment

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trellassignment.databinding.VideoItemLayoutBinding
import com.example.trellassignment.helpers.FileManager
import com.example.trellassignment.helpers.SharedPreferenceHelper
import com.google.android.exoplayer2.Player


class VideoAdapter(private val context: MainActivity) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val viewBinding = DataBindingUtil.inflate<VideoItemLayoutBinding>(
            LayoutInflater.from(parent.context), R.layout.video_item_layout, parent, false
        )
        return VideoViewHolder(viewBinding)
    }

    override fun getItemCount() = FileManager.allVideoList.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val videoFileItem = FileManager.allVideoList[position]
        with(holder.binding) {
            val uri: Uri = Uri.fromFile(videoFileItem)
            videoUri = uri
            val bookmarkState = SharedPreferenceHelper.getSharedPreferenceBoolean(
                context,
                "BOOKMARK$position",
                false
            )
            bookmark.isChecked = bookmarkState

            bookmark.setOnClickListener(View.OnClickListener {
                if (!bookmark.isChecked) {
                    SharedPreferenceHelper.setSharedPreferenceBoolean(
                        context,
                        "BOOKMARK$position",
                        false
                    )
                } else {
                    SharedPreferenceHelper.setSharedPreferenceBoolean(
                        context,
                        "BOOKMARK$position",
                        true
                    )
                }
            })
            executePendingBindings()
        }
    }

    class VideoViewHolder constructor(val binding: VideoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}
