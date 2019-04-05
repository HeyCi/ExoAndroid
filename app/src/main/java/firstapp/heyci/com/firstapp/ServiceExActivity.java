package firstapp.heyci.com.firstapp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class ServiceExActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnStop;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ex);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        textView = findViewById(R.id.textView);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getBus().unregister(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            startService(new Intent(this, MyService.class));
        } else if (v == btnStop) {
            stopService(new Intent(this, MyService.class));
        }
    }

    @Subscribe
    public void printLocation(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        textView.setText(latitude.toString() + " / " + longitude.toString());
    }
}
