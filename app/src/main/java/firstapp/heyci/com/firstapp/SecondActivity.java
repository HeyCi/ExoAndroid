package firstapp.heyci.com.firstapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, View.OnClickListener {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final int ID_MENU_DATE_PICKER = 1;
    private final int ID_MENU_TIME_PICKER = 2;
    private final int ID_MENU_ALERT_DIALOG = 3;
    private final int ID_MENU_SERVICE = 4;
    private final int ID_MENU_WEB = 5;
    private final int ID_MENU_MAPS = 6;
    private Calendar calendar;
    private Button btnLater;
    private Button btnNow;
    private Button btnCity;
    private Button btnContact;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        calendar = Calendar.getInstance();
        btnLater = findViewById(R.id.btnLater);
        btnNow = findViewById(R.id.btnNow);
        btnCity = findViewById(R.id.btnCity);
        btnContact = findViewById(R.id.btnContact);

        btnNow.setOnClickListener(this);
        btnLater.setOnClickListener(this);
        btnCity.setOnClickListener(this);
        btnContact.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ID_MENU_DATE_PICKER, 0, "Date Picker");
        menu.add(0, ID_MENU_TIME_PICKER, 0, "Time Picker");
        menu.add(0, ID_MENU_ALERT_DIALOG, 0, "Alert Dialog");
        menu.add(0, ID_MENU_SERVICE, 0, "Service Example");
        menu.add(0, ID_MENU_WEB, 0, "Web Example");
        menu.add(0, ID_MENU_MAPS, 0, "Maps example");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ID_MENU_DATE_PICKER:
                createDatePicker();
                break;
            case ID_MENU_TIME_PICKER:
                createTimePicker();
                break;
            case ID_MENU_ALERT_DIALOG:
                createAlertDialog();
                break;
            case ID_MENU_SERVICE:
                launchService();
                break;
            case ID_MENU_WEB:
                Intent intentWeb = new Intent(this, WebActivity.class);
                startActivity(intentWeb);
                break;
            case ID_MENU_MAPS:
                Intent intentMaps = new Intent(this, MapsActivity.class);
                startActivity(intentMaps);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 25) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(this, ServiceExActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void createDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void createTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    public void createAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("print a toast");
        alertDialogBuilder.setTitle("My title");
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SecondActivity.this, "click on ok", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.show();
    }

    public void launchService() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(this, ServiceExActivity.class);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 25);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnNow) {
            NotificationUtils.createInstantNotification(this, "Ceci est une notification instantanée");
        } else if (v == btnLater) {
            NotificationUtils.scheduleNotification(this, "Ceci est une notification à retardement", 15000);
        } else if (v == btnCity) {
            Intent intent = new Intent(this, CityActivity.class);
            startActivity(intent);
        } else if (v == btnContact) {
            Intent intent = new Intent(this, RecyclerViewActivity.class);
            startActivity(intent);
        }
    }
}
