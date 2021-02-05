package com.example.studynote.appstartup

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

class LibraryGContentProvider :ContentProvider(){
    private  val TAG = "lz"

    override fun onCreate(): Boolean {
        try {
            Thread.sleep(TIME_COUST)
            Log.e(TAG, "LibraryAContentProvider")
        } catch (ex: Exception) {
        }
        return false
    }

    override fun query(
            uri: Uri?,
            projection: Array<String?>?,
            selection: String?,
            selectionArgs: Array<String?>?,
            sortOrder: String?): Cursor? {
        return null
    }

    override fun getType(uri: Uri?): String? {
        return null
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<String?>?): Int {
        return 0
    }

    override fun update(
            uri: Uri?,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<String?>?): Int {
        return 0
    }

}