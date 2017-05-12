package com.webmyne.datetimepickerdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editDate1;
    private EditText editDate2;
    private EditText editStart;
    private EditText editEnd;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Button btn_date_diff;
    private TextView txt_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        initi();
    }

    private void initi() {
        editDate1 = (EditText) findViewById(R.id.editDate1);
        editDate2 = (EditText) findViewById(R.id.editDate2);
        editStart = (EditText) findViewById(R.id.editStart);
        editEnd = (EditText) findViewById(R.id.editEnd);

        txt_day = (TextView) findViewById(R.id.txt_day);

        btn_date_diff = (Button) findViewById(R.id.btn_date_diff);

        clickListener();
    }

    private void clickListener() {
        editDate1.setOnClickListener(this);
        editDate2.setOnClickListener(this);
        editStart.setOnClickListener(this);
        editEnd.setOnClickListener(this);

        btn_date_diff.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editDate1:
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        // Create a Date variable/object with user chosen date
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(0);
                        cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        Date chosenDate = cal.getTime();

                        // Format the date  using style medium and UK locale
                        DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                        String df_medium_uk_str = df_medium_uk.format(chosenDate);
                        // Display the formatted date
                        editDate1.setText(df_medium_uk_str);
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                datePickerDialog.show();

                break;

            case R.id.editDate2:
                // Get Current Date
                final Calendar c1 = Calendar.getInstance();
                mYear = c1.get(Calendar.YEAR);
                mMonth = c1.get(Calendar.MONTH);
                mDay = c1.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                // Create a Date variable/object with user chosen date
                                Calendar cal = Calendar.getInstance();
                                cal.setTimeInMillis(0);
                                cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                                Date chosenDate = cal.getTime();

                                // Format the date  using style medium and UK locale
                                DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                                String df_medium_uk_str = df_medium_uk.format(chosenDate);
                                // Display the formatted date
                                editDate2.setText(df_medium_uk_str);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog1.getDatePicker().setMinDate(c1.getTimeInMillis());
                datePickerDialog1.show();

                break;

            case R.id.btn_date_diff:
                String date1 = editDate1.getText().toString();
                String date2 = editDate2.getText().toString();
                if (!date1.isEmpty() && !date2.isEmpty()) {
                    DateFormat df = new SimpleDateFormat("dd MMM yyyy");

                    try {
                        Date startDate = df.parse(date1);
                        Date endDate = df.parse(date2);
                        printDifference(startDate, endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fields are empty !", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.editStart:

                // Get Current Date
                final Calendar c2 = Calendar.getInstance();
                mYear = c2.get(Calendar.YEAR);
                mMonth = c2.get(Calendar.MONTH);
                mDay = c2.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        editEnd.setText("");

                        // Create a Date variable/object with user chosen date
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(0);
                        cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                        Date chosenDate = cal.getTime();

                        // Format the date  using style medium and UK locale
                        DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                        String df_medium_uk_str = df_medium_uk.format(chosenDate);
                        // Display the formatted date
                        editStart.setText(df_medium_uk_str);

                    }
                }, mYear, mMonth, mDay);
                pickerDialog.show();

                break;

            case R.id.editEnd:
                String startDate = editStart.getText().toString();
                if (!startDate.isEmpty()) {
                    // Get Current Date
                    final Calendar c3 = Calendar.getInstance();
                    mYear = c3.get(Calendar.YEAR);
                    mMonth = c3.get(Calendar.MONTH);
                    mDay = c3.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog pickerDialog1 = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    // Create a Date variable/object with user chosen date
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTimeInMillis(0);
                                    cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                                    Date chosenDate = cal.getTime();

                                    // Format the date  using style medium and UK locale
                                    DateFormat df_medium_uk = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                                    String df_medium_uk_str = df_medium_uk.format(chosenDate);
                                    // Display the formatted date
                                    editEnd.setText(df_medium_uk_str);

                                }
                            }, mYear, mMonth, mDay);

                    DateFormat df = new SimpleDateFormat("dd MMM yyyy");

                    try {
                        Date date = df.parse(startDate);
                        pickerDialog1.getDatePicker().setMinDate(date.getTime());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    pickerDialog1.show();
                } else {
                    Toast.makeText(getApplicationContext(), "First field is empty !", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    public void printDifference(Date startDate, Date endDate) {

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        txt_day.setText(String.valueOf(elapsedDays) + " days");

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }
}
