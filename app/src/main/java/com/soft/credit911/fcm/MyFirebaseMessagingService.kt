package com.soft.credit911.fcm

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.Keep
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ing.quiz.shared_prefrences.Prefs
import com.ing.quiz.shared_prefrences.SharedPreferencesName
import com.soft.credit911.R
import com.soft.credit911.ui.dashboard.LandingActivity
import java.io.IOException
import java.io.Serializable
import java.net.URL


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

                    val title = p0.data.get("title").toString()
                    val type = p0.data.get("action").toString()
                    val message = p0.data.get("message").toString()
        var obj=notificationObject()
        obj.title=title
        obj.message=message
        obj.notificationType=type
        try{
            obj.  imageIcon=p0.data.get("imageUrl").toString()
        }catch (e:Exception){

        }
        showNotification(obj)
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

        try {
            val url = URL(pushData.imageIcon)
            val image: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            remoteViews.setImageViewBitmap(R.id.icon, image)
        } catch (e: IOException) {
            System.out.println(e)
        }
        return remoteViews
    }

    fun showNotification(
        pushData:notificationObject
    )
    {

        val intent = Intent(this, LandingActivity::class.java)
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

            notificationManager.notify(0, builder.build())

        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.start()

        val v: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(1000)
        }
    }

}
@Keep
class  notificationObject:Serializable{
    var title:String?=null
    var message:String?=null
    var imageIcon:String?=null
    var notificationType:String?=null
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