package firstapp.heyci.com.firstapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSubmit;
    private RadioButton rbtnLike;
    private RadioButton rbtnDislike;
    private EditText editText;
    private Button btnCancel;
    private RadioGroup rbtnGroup;
    private ImageView imgCenter;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        rbtnLike = findViewById(R.id.rbtnLike);
        rbtnDislike = findViewById(R.id.rbtnDislike);
        editText = findViewById(R.id.editText);
        btnCancel = findViewById(R.id.btnCancel);
        rbtnGroup = findViewById(R.id.rbtnGroup);
        imgCenter = findViewById(R.id.imgCenter);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSubmit) {
            imgCenter.setImageResource(R.mipmap.done);
            imgCenter.setColorFilter(Color.CYAN);
            if (rbtnLike.isChecked()) {
                editText.setText(rbtnLike.getText());
            } else if (rbtnDislike.isChecked()) {
                editText.setText(rbtnDislike.getText());
            }
        } else if (v == btnCancel) {
            imgCenter.setImageResource(R.mipmap.deleteforever);
            imgCenter.setColorFilter(getResources().getColor(R.color.colorWarning, getTheme()));
            rbtnGroup.clearCheck();
            editText.setText("");
        } else if (v == btnNext) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
