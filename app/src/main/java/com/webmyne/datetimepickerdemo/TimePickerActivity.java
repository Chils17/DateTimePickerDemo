package com.webmyne.datetimepickerdemo;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

@TargetApi(Build.VERSION_CODES.N)
public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTime1;
    private EditText editTime2;
    private EditText editStartTime;
    private EditText editEndTime;
    private int mHour, mMinute, mAm;
    private Button btn_time_diff;
    private TextView txt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        initi();
    }

    private void initi() {
        editTime1 = (EditText) findViewById(R.id.editTime1);
        editTime2 = (EditText) findViewById(R.id.editTime2);
        editStartTime = (EditText) findViewById(R.id.editStartTime);
        editEndTime = (EditText) findViewById(R.id.editEndTime);

        txt_time = (TextView) findViewById(R.id.txt_time);

        btn_time_diff = (Button) findViewById(R.id.btn_time_diff);

        clickListener();
    }

    private void clickListener() {
        editTime1.setOnClickListener(this);
        editTime2.setOnClickListener(this);
        editStartTime.setOnClickListener(this);
        editEndTime.setOnClickListener(this);
        btn_time_diff.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editTime1:
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mAm = c.get(Calendar.AM_PM);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String timeSet = "";
                        if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            timeSet = "PM";
                        } else if (hourOfDay == 0) {
                            hourOfDay += 12;
                            timeSet = "AM";
                        } else if (hourOfDay == 12)
                            timeSet = "PM";
                        else
                            timeSet = "AM";

                        String minutes = "";
                        if (minute < 10)
                            minutes = "0" + minute;
                        else
                            minutes = String.valueOf(minute);
                        editTime1.setText(hourOfDay + ":" + minutes + " " + timeSet);

                    }
                }, mHour, mMinute, false);

                // timePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
                timePickerDialog.show();

                break;

            case R.id.editTime2:
                // Get Current Time
                final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);
                mAm = calendar.get(Calendar.AM_PM);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String timeSet = "";
                                if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    timeSet = "PM";
                                } else if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    timeSet = "AM";
                                } else if (hourOfDay == 12)
                                    timeSet = "PM";
                                else
                                    timeSet = "AM";

                                String minutes = "";
                                if (minute < 10)
                                    minutes = "0" + minute;
                                else
                                    minutes = String.valueOf(minute);

                                editTime2.setText(hourOfDay + ":" + minutes + " " + timeSet);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog1.show();

                break;

            case R.id.btn_time_diff:
                String time1 = editTime1.getText().toString();
                String time2 = editTime2.getText().toString();
                if (!time1.isEmpty() && !time2.isEmpty()) {
                    DateFormat df = new SimpleDateFormat("hh:mm a");

                    try {
                        Date startTime = df.parse(time1);
                        Date endTime = df.parse(time2);

                        if (endTime.before(startTime)) {
                            Toast.makeText(getApplicationContext(), "Please select proper time", Toast.LENGTH_SHORT).show();
                        } else {
                            printDifference(startTime, endTime);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fields are empty !", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.editStartTime:
                // Get Current Time
                final Calendar calendar1 = Calendar.getInstance();
                mHour = calendar1.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar1.get(Calendar.MINUTE);
                mAm = calendar1.get(Calendar.AM_PM);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog2 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                editEndTime.setText("");
                                String timeSet = "";
                                if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                    timeSet = "PM";
                                } else if (hourOfDay == 0) {
                                    hourOfDay += 12;
                                    timeSet = "AM";
                                } else if (hourOfDay == 12)
                                    timeSet = "PM";
                                else
                                    timeSet = "AM";

                                String minutes = "";
                                if (minute < 10)
                                    minutes = "0" + minute;
                                else
                                    minutes = String.valueOf(minute);

                                editStartTime.setText(hourOfDay + ":" + minutes + " " + timeSet);

                            }
                        }, mHour, mMinute, false);
                timePickerDialog2.show();
                break;

            case R.id.editEndTime:
                final String startTime = editStartTime.getText().toString();
                if (!startTime.isEmpty()) {
                    // Get Current Time
                    final Calendar calendar2 = Calendar.getInstance();
                    mHour = calendar2.get(Calendar.HOUR_OF_DAY);
                    mMinute = calendar2.get(Calendar.MINUTE);
                    mAm = calendar2.get(Calendar.AM_PM);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog3 = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            String timeSet = "";
                            if (hourOfDay > 12) {
                                hourOfDay -= 12;
                                timeSet = "PM";
                            } else if (hourOfDay == 0) {
                                hourOfDay += 12;
                                timeSet = "AM";
                            } else if (hourOfDay == 12)
                                timeSet = "PM";
                            else
                                timeSet = "AM";

                            String minutes = "";
                            if (minute < 10)
                                minutes = "0" + minute;
                            else
                                minutes = String.valueOf(minute);

                            DateFormat df = new SimpleDateFormat("HH:mm");

                            try {
                                Date sTime = df.parse(startTime);
                                Date eTime = df.parse(hourOfDay + ":" + minutes + " " + timeSet);
                                if (eTime.before(sTime)) {
                                    Toast.makeText(getApplicationContext(), "Please select proper time", Toast.LENGTH_SHORT).show();
                                } else {
                                    editEndTime.setText(hourOfDay + ":" + minutes + " " + timeSet);
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    }, mHour, mMinute, false);
                    timePickerDialog3.show();

                    break;
                } else {
                    Toast.makeText(getApplicationContext(), "First field is empty !", Toast.LENGTH_SHORT).show();
                }
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

        txt_time.setText(String.valueOf(elapsedHours) + " hours  " + String.valueOf(elapsedMinutes) + " minutes");

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);

    }
}