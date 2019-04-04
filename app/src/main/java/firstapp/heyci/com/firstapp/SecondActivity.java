package firstapp.heyci.com.firstapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final int ID_MENU_DATE_PICKER = 1;
    private final int ID_MENU_TIME_PICKER = 2;
    private final int ID_MENU_ALERT_DIALOG = 3;
    private Calendar calendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ID_MENU_DATE_PICKER, 0, "Date Picker");
        menu.add(0, ID_MENU_TIME_PICKER, 0, "Time Picker");
        menu.add(0, ID_MENU_ALERT_DIALOG, 0, "Alert Dialog");
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
        }
        return super.onOptionsItemSelected(item);
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
}
