package com.example.trellassignment.adapters

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.*
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@BindingAdapter("video_uri")
fun PlayerView.loadVideo(uri: Uri) {

    val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
    val videoTrackSelectionFactory: TrackSelection.Factory =
        AdaptiveTrackSelection.Factory(bandwidthMeter)
    val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
    val player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)

    player.playWhenReady = false
    player.repeatMode = Player.REPEAT_MODE_OFF
    setKeepContentOnPlayerReset(true)
    // Show the controller
    this.useController = true
    val defaultBandwidthMeter = DefaultBandwidthMeter()
    val dataSourceFactory = DefaultDataSourceFactory(
        context,
        Util.getUserAgent(context, "TrellAssignment"),
        defaultBandwidthMeter
    )
    val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    player.prepare(mediaSource)

    this.player = player
    this.player!!.addListener(object : Player.EventListener {
        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {

        }

        override fun onSeekProcessed() {

        }

        override fun onTracksChanged(
            trackGroups: TrackGroupArray?,
            trackSelections: TrackSelectionArray?
        ) {

        }

        override fun onPlayerError(error: ExoPlaybackException?) {

        }

        override fun onLoadingChanged(isLoading: Boolean) {

        }

        override fun onPositionDiscontinuity(reason: Int) {

        }

        override fun onRepeatModeChanged(repeatMode: Int) {

        }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

        }

        override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {

        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

        }

    })
}