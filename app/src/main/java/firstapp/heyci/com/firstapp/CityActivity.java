package firstapp.heyci.com.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCode;
    private Button btnSearchCity;
    private RecyclerView rwCity;
    private ArrayList<City> cityList;
    private CityAdapter adapter;
    private CityAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        etCode = findViewById(R.id.etCode);
        btnSearchCity = findViewById(R.id.btnSearchCity);
        rwCity = findViewById(R.id.rwCity);

        btnSearchCity.setOnClickListener(this);

        cityList = new ArrayList<>();

        adapter = new CityAdapter(cityList);

        rwCity.setAdapter(adapter);
        rwCity.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Recherche de villes", Toast.LENGTH_SHORT).show();
        asyncTask = new CityAsyncTask();
        asyncTask.execute();
    }

    public class CityAsyncTask extends AsyncTask {
        private ArrayList<City> resultat;
        private Exception exception;
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = WSCityUtils.loadCityFromWeb(etCode.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;

            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (exception != null) {
                Toast.makeText(CityActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                cityList.clear();
                cityList.addAll(resultat);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
