package firstapp.heyci.com.firstapp;

import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpCityUtils {
    public static InputStreamReader sendGetOkHttpRequest(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        InputStreamReader isr = new InputStreamReader(response.body().byteStream());

        if(response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse incorrecte du serveur : " + response.code());
        } else {
            return isr;
        }
    }
}