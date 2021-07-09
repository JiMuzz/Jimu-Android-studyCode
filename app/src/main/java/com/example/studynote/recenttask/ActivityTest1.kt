package com.example.studynote.recenttask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.studynote.R
import kotlinx.android.synthetic.main.activity_recent.*


class ActivityTest1 : AppCompatActivity() {
    private val TAG = "ActivityRecent"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("lz", TAG + "onCreate")
        setContentView(R.layout.activity_recent)

//        Log.e("lz", "isTaskRoot=$isTaskRoot")
//        if (!this.isTaskRoot) {
//            // 不是该Task的根部
//            if (intent != null) {
//                var action = intent.action
//                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action) {
//                    // 从桌面启动的,finish掉该类，直接打开该Task中现存的Activity
//                    finish()
//                    return
//                }
//            }
//        }


        image.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        image.setText("ActivityTest1")
        image.setOnClickListener {
//            var intent = Intent(this, ActivityTest2::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)

//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT )
//            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK )

            startActivityForResult(intent,100)
//            startActivity(intent)

//            var intent = Intent("com.jimu.test2")
//            startActivity(intent)
        }


//
//        val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val pm: PackageManager = getPackageManager()
//        var taskList = am.getRecentTasks(20, ActivityManager.RECENT_IGNORE_UNAVAILABLE)
//        for (task in taskList){
//            val intent = Intent(task.baseIntent)
//            if (task.origActivity != null) {
//                intent.component = task.origActivity
//            }
//
//            intent.flags = (intent.flags and Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED.inv()
//                    or Intent.FLAG_ACTIVITY_NEW_TASK)
//
//            val resolveInfo: ResolveInfo = pm.resolveActivity(intent, 0)
//            val activityInfo = resolveInfo.activityInfo
//            val title = activityInfo.loadLabel(pm).toString()
//            val icon = activityInfo.loadIcon(pm)
//            if (title.isNotEmpty() && icon != null) {
//                Log.e(TAG, "title=$title")
//            }
//
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e("lz", "requestCode=$requestCode,_resultCode=$resultCode")
    }

    override fun onPause() {
        image2.visibility = View.VISIBLE
        super.onPause()

//        finishAndRemoveTask()
    }

    override fun onResume() {
        super.onResume()
        image2.visibility = View.GONE
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("lz", TAG + "onNewIntent")
    }
}
