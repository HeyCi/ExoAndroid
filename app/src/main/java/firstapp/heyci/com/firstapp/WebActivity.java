package firstapp.heyci.com.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etWeb;
    private Button btnSearch;
    private WebView webView;
    private TextView tvWeb;
    private WebAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        etWeb = findViewById(R.id.etWeb);
        btnSearch = findViewById(R.id.btnSearch);
        webView = findViewById(R.id.webView);
        tvWeb = findViewById(R.id.tvWeb);

        btnSearch.setOnClickListener(this);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "chargement de la page", Toast.LENGTH_SHORT).show();
        webView.loadUrl(etWeb.getText().toString());
        asyncTask = new WebAsyncTask();
        asyncTask.execute();
    }

    private class WebAsyncTask extends AsyncTask {
        private String resultat;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                resultat = OkHttpUtils.sendGetOkHttpRequest(etWeb.getText().toString());
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
                Toast.makeText(WebActivity.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                tvWeb.setText(resultat);
            }
        }
    }
}
