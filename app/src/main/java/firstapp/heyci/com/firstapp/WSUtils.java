package firstapp.heyci.com.firstapp;

import android.os.SystemClock;

public class WSUtils {
    public static Contact loadContactFromWeb() throws Exception {
        SystemClock.sleep(5000);
        return new Contact("Toto", "Tata");
    }
}
