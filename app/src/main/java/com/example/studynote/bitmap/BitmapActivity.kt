package com.example.studynote.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R


class BitmapActivity : AppCompatActivity() {
    private val TAG = "BitmapActivity"

    val options by lazy {
        BitmapFactory.Options()
    }

    val reuseBitmap by lazy {
        options.inMutable = true
        BitmapFactory.decodeResource(resources, R.drawable.test, options)
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvitiy_bitmap)


//        val options = BitmapFactory.Options()
//        options.inSampleSize = 2
//
//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test2, options)
//        img.setImageBitmap(bitmap)
//
//
//        Log.e(TAG, "dpi = ${resources.displayMetrics.densityDpi}")
//        Log.e(TAG, "size = ${bitmap.allocationByteCount}")
//
//
//
//        btn1.setOnClickListener {
//            img.setImageBitmap(getBitmap(R.drawable.test))
//        }
//
//        btn2.setOnClickListener {
//            img.setImageBitmap(getBitmap(R.drawable.test2))
//        }
        loadBitmapFromView()

    }

    fun getBitmap(resId: Int): Bitmap {
        options.inMutable = true
        options.inBitmap = reuseBitmap
        return BitmapFactory.decodeResource(resources, resId, options)
    }

//    fun getImage(): Bitmap {
//        var options = BitmapFactory.Options()
//        options.inJustDecodeBounds = true
//        BitmapFactory.decodeResource(resources, R.drawable.test2, options)
//        // 计算最佳采样率
//        options.inSampleSize = getImageSampleSize(options.outWidth, options.outHeight)
//        options.inJustDecodeBounds = false
//        return BitmapFactory.decodeResource(resources, R.drawable.test2, options)
//    }




    fun loadBitmapFromView() {
//        val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8)
//        bitmap.setPixel(0, 0, Color.RED)
//        val pixel = bitmap.getPixel(0, 0)
//        val a: Float = Color.alpha(pixel.toLong())
//        val r: Int = Color.red(pixel)
//        val g: Int = Color.green(pixel)
//        val b: Int = Color.blue(pixel)
//        Log.e(TAG,"a=$a,r=$r,g=$g,b=$b")
    }
}