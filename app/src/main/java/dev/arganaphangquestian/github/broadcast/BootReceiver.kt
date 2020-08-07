package dev.arganaphangquestian.github.broadcast

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import dev.arganaphangquestian.github.utils.REMINDERTIME
import dev.arganaphangquestian.github.utils.SETREMINDER
import dev.arganaphangquestian.github.utils.SharedPrefs
import java.util.*

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            createNotificationChannel(context)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, RemindBroadcast::class.java),
                0
            )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            if (pendingIntent != null && alarmManager != null) {
                alarmManager.cancel(pendingIntent)
            }
            if (SharedPrefs.get(SETREMINDER, false) as Boolean) {
                alarmManager?.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    REMINDERTIME.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "GithubReminderChannel"
            val description = "Channel for Github Reminder"
            val important = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("remindGithub", name, important)
            channel.description = description
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

}