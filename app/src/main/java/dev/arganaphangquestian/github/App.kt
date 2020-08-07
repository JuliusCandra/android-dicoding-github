package dev.arganaphangquestian.github

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import dev.arganaphangquestian.github.broadcast.RemindBroadcast
import dev.arganaphangquestian.github.utils.REMINDERTIME
import dev.arganaphangquestian.github.utils.SETREMINDER
import dev.arganaphangquestian.github.utils.SharedPrefs
import java.util.*

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannel()
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, Intent(this, RemindBroadcast::class.java), 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "GithubReminderChannel"
            val description = "Channel for Github Reminder"
            val important = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("remindGithub", name, important)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}