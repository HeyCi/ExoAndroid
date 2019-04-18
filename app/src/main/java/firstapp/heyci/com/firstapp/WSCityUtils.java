package firstapp.heyci.com.firstapp;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WSCityUtils {
    private static String resultat;
    private static Gson gson = new Gson();

    public static ArrayList<City> loadCityFromWeb(String cp) throws Exception {
        Result result = gson.fromJson(OkHttpCityUtils.sendGetOkHttpRequest("http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=" + cp), Result.class);
        if(result.getErrors() != null) {
            throw new Exception("Erreur " + result.getErrors().getCode() + " : " +result.getErrors().getMessage());
        } else {
            return result.getResults();
        }
    }
}
