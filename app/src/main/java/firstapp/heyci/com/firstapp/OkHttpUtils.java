package firstapp.heyci.com.firstapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    public static String sendGetOkHttpRequest(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if(response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse incorrecte du serveur : " + response.code());
        } else {
            return response.body().string();
        }
    }
}
