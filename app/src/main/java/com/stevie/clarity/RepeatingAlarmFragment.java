package com.stevie.clarity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;


public class RepeatingAlarmFragment extends Fragment {

    // What does this do?
    public static final int REQUEST_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.screenshot_5) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setAction(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            final int FIVE_MIN_IN_MILLIS = 300000;
            
            // To create a BroadcastIntent instead, simply call getBroadcast instead of getIntent.
            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), REQUEST_CODE,
                    intent, 0);

            int alarmType = AlarmManager.ELAPSED_REALTIME;

            AlarmManager alarmManager = (AlarmManager)
                    getActivity().getSystemService(getActivity().ALARM_SERVICE);

            alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + FIVE_MIN_IN_MILLIS,
                    FIVE_MIN_IN_MILLIS, pendingIntent);
            Log.i("RepeatingAlarmFragment", "Alarm set.");
        }
        else if(item.getItemId() == R.id.screenshot_10) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setAction(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            final int TEN_MIN_IN_MILLIS = 600000;

            // To create a BroadcastIntent instead, simply call getBroadcast instead of getIntent.
            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), REQUEST_CODE,
                    intent, 0);

            int alarmType = AlarmManager.ELAPSED_REALTIME;

            AlarmManager alarmManager = (AlarmManager)
                    getActivity().getSystemService(getActivity().ALARM_SERVICE);

            alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + TEN_MIN_IN_MILLIS,
                    TEN_MIN_IN_MILLIS, pendingIntent);
            Log.i("RepeatingAlarmFragment", "Alarm set.");
        }
        else if(item.getItemId() == R.id.screenshot_20) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setAction(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            final int TWENTY_MIN_IN_MILLIS = 1200000;

            // To create a BroadcastIntent instead, simply call getBroadcast instead of getIntent.
            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), REQUEST_CODE,
                    intent, 0);

            int alarmType = AlarmManager.ELAPSED_REALTIME;

            AlarmManager alarmManager = (AlarmManager)
                    getActivity().getSystemService(getActivity().ALARM_SERVICE);

            alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + TWENTY_MIN_IN_MILLIS,
                    TWENTY_MIN_IN_MILLIS, pendingIntent);
            Log.i("RepeatingAlarmFragment", "Alarm set.");
        }
        return true;
    }
}
