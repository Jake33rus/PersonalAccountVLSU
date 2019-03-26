package com.example.jake.university.API;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.widget.Toast;

import com.example.jake.university.MainActivity;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static androidx.core.content.ContextCompat.getSystemService;

public class AlarmReceiver extends BroadcastReceiver
{
    public static int ALARM_TYPE_RTC = 100;
    public static int ALARM_TYPE_ELAPSED = 101;
    public static String CHANNEL_ID = "my_channel_01";
    @Override
    public void onReceive(Context context, Intent intent) {

        //Intent для вызова приложения по клику.
        //Мы хотим запустить наше приложение (главную активность) при клике на уведомлении
        Intent intentToRepeat = new Intent(context, MainActivity.class);
        //настроим флаг для перезапуска приложения
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, ALARM_TYPE_RTC, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);


        //Создаём уведомление
        Notification repeatedNotification = buildLocalNotification(context, pendingIntent, "blet", CHANNEL_ID).build();

        //Отправляем уведомление
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(ALARM_TYPE_RTC, repeatedNotification);

        getNotificationManager(context).notify(ALARM_TYPE_RTC, repeatedNotification);


    }

    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent, String title, String channelId)
    {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);

        builder.setContentIntent(pendingIntent)
                // обязательные настройки
                .setSmallIcon(android.R.drawable.arrow_up_float)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Напоминание")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Пора покормить кота"); // Текст уведомления

        return builder;
    }

    public static NotificationManager getNotificationManager(Context context)
    {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void enableBootReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * Disable boot receiver when user cancels/opt-out from notifications
     */
    public static void disableBootReceiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
