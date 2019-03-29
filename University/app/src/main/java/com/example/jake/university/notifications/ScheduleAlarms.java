package com.example.jake.university.notifications;

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

    private void setAlarm(Context context, String hour, String min, int pendCode)
    {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("code",Integer.toString(pendCode));
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

    public void startAlarmBundle(Context context)
    {
        setAlarm(context,"8","0", 8);
        setAlarm(context, "10", "0", 10);
        setAlarm(context,"11","50",12);
        setAlarm(context,"13","40", 14);
        setAlarm(context,"15","30", 15);
        setAlarm(context,"17", "20", 17);
        setAlarm(context,"19","10", 19);

    }

}
