package firstapp.heyci.com.firstapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.w("TAG_", "boot");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, OnBootService.class));
            } else {
                context.startService(new Intent(context, OnBootService.class));
            }
        }
        //Toast.makeText(context, "Changed Language", Toast.LENGTH_SHORT).show();
        else {
            Notification notification = intent.getParcelableExtra("MyKey");
            NotificationManagerCompat ncm = NotificationManagerCompat.from(context);
            ncm.notify(29, notification);
        }
    }
}
