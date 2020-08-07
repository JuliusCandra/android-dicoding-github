package dev.arganaphangquestian.github.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import dev.arganaphangquestian.github.R
import dev.arganaphangquestian.github.utils.REMINDERID

class RemindBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationBuilder =
            NotificationCompat.Builder(context, "remindGithub")
                .setSmallIcon(R.drawable.ic_bell)
                .setContentTitle("Reminder Github")
                .setContentText("This Reminder come from Alarm Manager + Notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(REMINDERID, notificationBuilder.build())
    }
}