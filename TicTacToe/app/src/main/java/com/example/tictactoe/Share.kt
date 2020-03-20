package com.example.tictactoe

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri


class Share {
    val url = "https://www.facebook.com/Netflixmx/"

    fun shareOnFacebook(activity:Activity){
        var facebookAppFound = false
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(url))

        val pm: PackageManager = activity.packageManager
        val activityList: List<ResolveInfo> = pm.queryIntentActivities(shareIntent, 0)
        for (app in activityList) {
            if (app.activityInfo.packageName.contains("com.facebook.katana")) {
                val activityInfo: ActivityInfo = app.activityInfo
                val name = ComponentName(
                    activityInfo.applicationInfo.packageName,
                    activityInfo.name
                )
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                shareIntent.component = name
                facebookAppFound = true
                break
            }
        }
        if (! facebookAppFound) {
            val sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=$url"
            shareIntent = Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl))
        }
        activity.startActivity(shareIntent)
    }
}