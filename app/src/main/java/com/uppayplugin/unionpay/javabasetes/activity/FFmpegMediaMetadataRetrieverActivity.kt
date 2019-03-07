package com.uppayplugin.unionpay.javabasetes.activity
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.uppayplugin.unionpay.javabasetes.R
import com.uppayplugin.unionpay.javabasetes.base.ToolBarActivity
import kotlinx.android.synthetic.main.activity_ffmpeg_media_metadata_retriever.*
import wseemann.media.FFmpegMediaMetadataRetriever

class FFmpegMediaMetadataRetrieverActivity : ToolBarActivity() {
    override fun initToolBar() {
    }

    override fun getBundleExtras(extras: Bundle?) {
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_ffmpeg_media_metadata_retriever
    }

    override fun initViewsAndEvents() {
        /*Glide
            .with(this)
            .applyDefaultRequestOptions(RequestOptions().apply {
                set(FFmpegVideoDecoder.PERCENTAGE_DURATION, 0.03F)
                // set(FFmpegVideoDecoder.FRAME_AT_TIME, 1000 * 1000 * 1) // One second
            })
            .load(Uri.parse("http://192.168.2.251/fastdfs/group2/M00/03/67/wKgDbFx8mYqAOT4xAC-4m_5CTRo920.mp4?token=8419843bbdfa127029a5b0d37719db86&ts=1551671012"))
            .into(image_video)*/
        var imageUrl = "http://192.168.2.251/fastdfs/group2/M00/03/68/wKgDbFx8zyqAQt92AC365_BF_Sk206.mp4?token=d11cae25f8652f5fdc66d22da1157ed7&ts=1551684389"
        image_video.setImageBitmap(getNetVideoBitmap(imageUrl))
        /*var mm = MediaMetadataRetriever()
        Thread(Runnable {
            try {
                //获取视频文件数据
                mm.setDataSource(imageUrl)
                val bitmap = mm.frameAtTime
                val msg = Message()
                msg.arg1 = 0
                msg.obj = bitmap
                myHandler.sendMessage(msg)
                Log.d("========", "发送Message")
            } catch (e: Exception) {
            } finally {
                mm.release()
            }
        }).start()*/
    }

    //更新主界面，显示缩略图
    internal var myHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.arg1) {
                0 -> {
                    val bitmap = msg.obj as Bitmap
                    image_video.setImageBitmap(bitmap)
                }
            }
            super.handleMessage(msg)
        }
    }

    private fun GetFramePictures(imageUrl: String, timeUs: Long): Bitmap? {
        var videoShortCut: Bitmap? = null
        val mediaMetadataRetriever = FFmpegMediaMetadataRetriever()
        try {
            mediaMetadataRetriever.setDataSource(imageUrl)
            videoShortCut = mediaMetadataRetriever.getFrameAtTime(timeUs, FFmpegMediaMetadataRetriever.OPTION_CLOSEST)
        } catch (e: IllegalArgumentException) {
        }
        mediaMetadataRetriever.release()
        return videoShortCut
    }

    override fun getLoadingTargetView(): View? {
        return null
    }

    /**
     * 服务器返回url，通过url去获取视频的第一帧
     * Android 原生给我们提供了一个MediaMetadataRetriever类
     * 提供了获取url视频第一帧的方法,返回Bitmap对象
     *
     * @param videoUrl
     * @return
     */
    fun getNetVideoBitmap(videoUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        val retriever = MediaMetadataRetriever()
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, HashMap())
            //获得第一帧图片
            bitmap = retriever.frameAtTime
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
        return bitmap
    }
}
