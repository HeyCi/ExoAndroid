package firstapp.heyci.com.firstapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class OnBootService extends Service {
    public OnBootService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(1, NotificationUtils.getNotification(this, "Démarrage, lancer appli ici"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
