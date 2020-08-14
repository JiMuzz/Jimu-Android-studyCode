package com.example.studynote.android11

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.telephony.PhoneStateListener
import android.telephony.TelephonyDisplayInfo
import android.telephony.TelephonyManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.studynote.R
import com.example.studynote.showToast
import kotlinx.android.synthetic.main.activity_test1.*


class Android11Test2Activity : AppCompatActivity() {

    private  val TAG = "Android11Test2Activity"

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)


        btn1.setOnClickListener {

//            showToast("APN NAME:"+Telephony.Carriers.CARRIER_ID)

//            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 100)
//            requestPermissions(arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), 100)
            requestPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
//            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
//            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)

//            val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//            manager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
//                override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
//                    super.onCapabilitiesChanged(network, networkCapabilities)
//
//                    //true 代表连接不按流量计费
//                    val isNotFlowPay=networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) ||
//                            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_TEMPORARILY_NOT_METERED)
//
//                    showToast("isNotFlowPay="+isNotFlowPay)
//                }
//            })


//            val mainIntent = Intent(Intent.ACTION_MAIN, null)
//            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
//            val mApps = packageManager.queryIntentActivities(mainIntent, 0)


//            val pm = this.packageManager
//            val listAppcations: List<ApplicationInfo> = pm
//                    .getInstalledApplications(PackageManager.GET_META_DATA)
//            for (app in listAppcations) {
//                Log.e("lz",app.packageName)
//            }

//            val i=Intent()
//            i.action= StorageManager.ACTION_MANAGE_STORAGE
//            startActivity(i)

//            val i=Intent()
//            i.action= StorageManager.ACTION_CLEAR_APP_CACHE
//            startActivity(i)


//            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            intent.type = "image/*"
//            startActivityForResult(intent, 100)


            val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")
//            val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, arrayOf(MediaStore.Images.Media._ID), MediaStore.Images.Media.DATA + "=? ", null, null)

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    Log.e(TAG,"image uri is $uri")
                }
                cursor.close()
            }


//            val urisToModify = listOf(Uri.parse("content://com.android.providers.media.documents/document/image%3A39"),Uri.parse("content://com.android.providers.media.documents/document/image%3A38"))
//            val editPendingIntent = MediaStore.createWriteRequest(contentResolver,
//                    urisToModify)
//
//            startIntentSenderForResult(editPendingIntent.intentSender, 200,
//                    null, 0, 0, 0)

//            val file=File(URI.create("content://com.android.providers.media.documents/document/image%3A39"))
//            if (file.exists()){
//                showToast("file exists")
//            }



//            val intent = Intent()
//            intent.action= Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
//            startActivity(intent)
//
//            //判断是否获取MANAGE_EXTERNAL_STORAGE权限：
//            val isHasStoragePermission= Environment.isExternalStorageManager()


//            val file = File(filesDir, "")
//            val appSpecificExternalDir = File(getExternalFilesDir(""), "")

//            getNetworkType()
        }
    }



    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null || resultCode != Activity.RESULT_OK) return
        if (requestCode == 100) {
            val uri = data.data
            println("image uri is $uri")
            data.data?.let { getImagePath(it) }
        }
    }

    private fun getImagePath(uri: Uri): String? {
        var path: String? = null
        val cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        println("image uri is $path")
        return path
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getPathForSearch(uri: Uri) {
        val selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":".toRegex()).toTypedArray()[1])
        val cursor: Cursor? = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + "=?",
                selectionArgs, null)
        if (null != cursor) {
            if (cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                if (index > -1) {
                    val path: String = cursor.getString(index)
                    println("image path is $uri")
                    showToast(path)
                }
            }
            cursor.close()
        }
    }


    private fun getNetworkType() {
        val tManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        tManager.listen(object : PhoneStateListener() {

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onDisplayInfoChanged(telephonyDisplayInfo: TelephonyDisplayInfo) {
                if (ActivityCompat.checkSelfPermission(this@Android11Test2Activity, android.Manifest.permission.READ_PHONE_STATE) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    return
                }
                super.onDisplayInfoChanged(telephonyDisplayInfo)
                showToast("type="+telephonyDisplayInfo.networkType)
                when (telephonyDisplayInfo.networkType) {
                    TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_LTE_ADVANCED_PRO -> showToast("高级专业版 LTE (5Ge)")
                    TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_NR_NSA -> showToast("NR (5G) - 5G Sub-6 网络")
                    TelephonyDisplayInfo.OVERRIDE_NETWORK_TYPE_NR_NSA_MMWAVE -> showToast("5G+/5G UW - 5G mmWave 网络")
                    else -> showToast("other")
                }
            }

        }, PhoneStateListener.LISTEN_DISPLAY_INFO_CHANGED)


    }

}