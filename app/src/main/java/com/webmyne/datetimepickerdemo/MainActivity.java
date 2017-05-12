package com.webmyne.datetimepickerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_DatePicker;
    private Button btn_TimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initi();
    }

    private void initi() {
        btn_DatePicker=(Button)findViewById(R.id.btn_datePicker);
        btn_TimePicker=(Button)findViewById(R.id.btn_TimePicker);

        clickListener();
    }

    private void clickListener() {
        btn_DatePicker.setOnClickListener(this);
        btn_TimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_datePicker:
                Intent intentDate=new Intent(getApplicationContext(),DatePickerActivity.class);
                startActivity(intentDate);
                break;

            case R.id.btn_TimePicker:
                Intent intentTime=new Intent(getApplicationContext(),TimePickerActivity.class);
                startActivity(intentTime);
                break;
        }
    }
}
