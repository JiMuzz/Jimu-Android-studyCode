package com.example.studynote.android11

import android.Manifest
import android.app.AppOpsManager

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.studynote.R
import com.example.studynote.showToast
import kotlinx.android.synthetic.main.activity_test1.*
import java.io.FileWriter


class Android11TestActivity : AppCompatActivity() {
    private val TAG = "Android11TestActivity"


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test1)

//        ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_NUMBERS), 100)
//
//
//        ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.CAMERA), 101)
//
//        btn1.setOnClickListener {
//            var intent = Intent()
//            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//            startActivity(intent)
//        }
//
//        btn2.setOnClickListener {
//            val tm = this.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//            val phoneNumber = tm.line1Number
//            showToast(phoneNumber)
//        }
//    }

//    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

//        attributionContext = createAttributionContext("shareLocation")
//
//        val appOpsCallback = object : AppOpsManager.OnOpNotedCallback() {
//            private fun logPrivateDataAccess(
//                    opCode: String, attributionTag: String, trace: String) {
//                Log.i(TAG, "Private data accessed. " +
//                        "Operation: $opCode\n " +
//                        "Attribution Tag:$attributionTag\nStack Trace:\n$trace")
//            }
//
//            override fun onNoted(syncNotedAppOp: SyncNotedAppOp) {
//                syncNotedAppOp.attributionTag?.let {
//                    logPrivateDataAccess(syncNotedAppOp.op,
//                            it,
//                            Throwable().stackTrace.toString())
//                }
//            }
//
//            override fun onSelfNoted(syncNotedAppOp: SyncNotedAppOp) {
//                syncNotedAppOp.attributionTag?.let {
//                    logPrivateDataAccess(syncNotedAppOp.op,
//                            it,
//                            Throwable().stackTrace.toString())
//                }
//            }
//
//            override fun onAsyncNoted(asyncNotedAppOp: AsyncNotedAppOp) {
//                asyncNotedAppOp.attributionTag?.let {
//                    logPrivateDataAccess(asyncNotedAppOp.op,
//                            it,
//                            asyncNotedAppOp.message)
//                }
//            }
//        }
//
//        val appOpsManager =
//                getSystemService(AppOpsManager::class.java) as AppOpsManager
//        appOpsManager.setOnOpNotedCallback(mainExecutor, appOpsCallback)
//
//        btn1.setOnClickListener {
////            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION), 100)
//            getLocation()
//        }
    }



    private lateinit var attributionContext: Context


    @RequiresApi(Build.VERSION_CODES.M)
    fun getLocation() {
        val locationManager = attributionContext.getSystemService(
                LocationManager::class.java) as LocationManager
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val location: Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        if (location != null) {

            showToast("${location.latitude}")
        }


    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test1)
//
//
//
//        btn1.setOnClickListener {
//            saveFile()
//        }
//    }
//
    fun saveFile() {
        if (checkPermission()) {
            val filePath = Environment.getExternalStoragePublicDirectory("").toString() + "/test3.txt"
//            val filePath2 = getExternalFilesDir("image").toString()+"/testnew.txt"
            val fw = FileWriter(filePath)
            fw.write("hello world2")
            fw.close()
            showToast("文件写入成功")
        }
    }

    private fun checkPermission(): Boolean {
        var isGranted = true
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false
            }
            if (!isGranted) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
            }
        }
        return isGranted
    }


}