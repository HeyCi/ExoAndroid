package firstapp.heyci.com.firstapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Changed Language", Toast.LENGTH_SHORT).show();
        Notification notification = intent.getParcelableExtra("MyKey");
        NotificationManagerCompat ncm = NotificationManagerCompat.from(context);
        ncm.notify(29, notification);
    }
}
