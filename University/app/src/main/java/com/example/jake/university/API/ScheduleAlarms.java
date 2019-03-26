package com.example.jake.university.API;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.ALARM_SERVICE;

public class ScheduleAlarms

{
    private static ScheduleAlarms scheduleAlarms = new ScheduleAlarms();

    private ScheduleAlarms()
    {}

    public static ScheduleAlarms getInstance()
    {
        return scheduleAlarms;
    }

    public void startAlarm(Context context, String hour, String min, int pendCode)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntentRTC = PendingIntent.getBroadcast(context, pendCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManagerRTC = (AlarmManager)context.getSystemService(ALARM_SERVICE);

        alarmManagerRTC.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                setTimeForAlarm(hour,min).getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntentRTC);
    }

    private Calendar setTimeForAlarm(String hour, String min)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //Настраиваем время (здесь 8 утра) отправки ежедневного уведомления
        calendar.set(Calendar.HOUR_OF_DAY,
                Integer.getInteger(hour, 8),
                Integer.getInteger(min, 0));
        return calendar;

    }

}
