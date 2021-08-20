package com.soft.credit911.fcm

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.Keep
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
import com.scanlibrary.SplashActivity
import com.soft.credit911.R
import org.greenrobot.eventbus.EventBus
import java.io.Serializable


class MyFirebaseMessagingService : FirebaseMessagingService() {


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Prefs.with(this).save(SharedPreferencesName.DEVICETOKEN, p0)
        Log.e("Token","nbndva"+p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.e("Token","nbndva"+p0.data)
        if (p0 == null)
            return
        if (p0.notification != null) {
            Log.e(TAG + p0.notification!!.title!!, "Notification Body: " + p0.notification!!.body!!)
        }

        val pushType = Integer.parseInt(p0.data.get("push_type").toString())
        var myNotiObj=notificationObject()
        if (pushType == 1 ||pushType == 2) {

            try {
                if (p0.data.size > 0) {
                    val title = p0.data.get("title").toString()
                    val type = p0.data.get("push_type").toString()
                    val message = p0.data.get("message").toString()
                    val combatData = p0.data.get("combat_data").toString()

                    myNotiObj.title = title
                    myNotiObj.message = message
                    myNotiObj.notificationType = type.toInt()
                    myNotiObj.combatData = combatData
                    if (p0.data.containsKey("data_xp_points"))
                    {
//                        val  xp_pont=p0.data.get("data_xp_points")
//                        try {
//                            val myPointData = Gson().fromJson(
//                                xp_pont.toString(),
//                                data_rewards::class.java
//                            )
//                            myNotiObj.data_rewards = myPointData
//                        }catch (e:Exception){}
                    }
                    showNotification(myNotiObj)
                }
            }catch (e:Exception){

                e.printStackTrace()
            }
        }else{
            val title = p0.data.get("title").toString()
            val type = p0.data.get("push_type").toString()
            val message = p0.data.get("message").toString()
            val  xp_pont=p0.data.get("data_xp_points")?.toString()
            myNotiObj.title = title
            myNotiObj.message = message
            if (xp_pont!=null)
            {
//                val myPointData = Gson().fromJson(
//                    xp_pont.toString(),
//                    data_rewards::class.java
//                )
//                myNotiObj.data_rewards=myPointData
            }

            myNotiObj.notificationType = type.toInt()
            showNotification(myNotiObj)
        }

    }

    override fun onSendError(p0: String, p1: Exception) {
        super.onSendError(p0, p1)
        Log.e("Token","nbndva"+p0)
    }




    @SuppressLint("RemoteViewLayout")
    private fun getCustomDesign(
        pushData:notificationObject
    ): RemoteViews? {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.notification
        )
        remoteViews.setTextViewText(R.id.title, pushData.title)
        remoteViews.setTextViewText(R.id.message,  pushData.message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.mipmap.ic_launcher
        )
        return remoteViews
    }

    fun showNotification(
        pushData:notificationObject
    )
    {

        val intent = Intent(this, com.soft.credit911.ui.Splash.SplashActivity::class.java)
        val channel_id = "notification_channel"
        intent.putExtra("push_data",pushData)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        var builder = NotificationCompat.Builder(
            applicationContext,
            channel_id
        )
            .setSmallIcon(R.drawable.ic_case_management_icon)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        builder = if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.JELLY_BEAN
        ) {
            builder.setContent(
                getCustomDesign(pushData)
            )
        }
        else {
            builder.setContentTitle(pushData.title)
                .setContentText(pushData.message)
                .setSmallIcon(R.drawable.ic_case_management_icon)
        }
        val notificationManager = getSystemService(
            NOTIFICATION_SERVICE
        ) as NotificationManager
        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channel_id, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(
                notificationChannel
            )
        }
        if (pushData?.notificationType==1 &&!isAppIsInBackground(this))
        {
            EventBus.getDefault().post(pushData)
        }else
        {
            notificationManager.notify(0, builder.build())
        }


    }

}
@Keep
class  notificationObject:Serializable{
    var title:String?=null
    var message:String?=null
    var notificationType:Int?=0
    var combatData:String?=null
    var refId:Int?=0
//    var data_rewards: data_rewards?=null
}

private fun isAppIsInBackground(context: Context): Boolean {
    var isInBackground = true
    val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
        val runningProcesses = am.runningAppProcesses
        for (processInfo in runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (activeProcess in processInfo.pkgList) {
                    if (activeProcess == context.packageName) {
                        isInBackground = false
                    }
                }
            }
        }
    } else {
        val taskInfo = am.getRunningTasks(1)
        val componentInfo = taskInfo[0].topActivity
        if (componentInfo?.packageName == context.packageName) {
            isInBackground = false
        }
    }

    return isInBackground
}