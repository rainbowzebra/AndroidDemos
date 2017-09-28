package com.cn.dateandtimepicker03;

import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    private Context mContext;
    public Calendar mCalendar;
    private Button mDatePickerButton;
    private Button mTimePickerButton;
    public DatePickerDialog mDatePickerDialog;
    public TimePickerDialog mTimePickerDialog;
    private ClickListenerImpl mClickListenerImpl;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        mCalendar = Calendar.getInstance(Locale.CHINA);
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH);
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(Calendar.MINUTE);

        mClickListenerImpl=new ClickListenerImpl();
        mDatePickerButton=(Button) findViewById(R.id.datePickButton);
        mDatePickerButton.setOnClickListener(mClickListenerImpl);
        mTimePickerButton=(Button) findViewById(R.id.timePickButton);
        mTimePickerButton.setOnClickListener(mClickListenerImpl);

        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                String date = "year="+year+", month="+(monthOfYear+1)+",day="+dayOfMonth;
                Toast.makeText(mContext,date,Toast.LENGTH_SHORT).show();
            }
        };

        mTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time="hour="+hourOfDay+",minute="+minute;
                Toast.makeText(mContext,time,Toast.LENGTH_SHORT).show();
            }
        };
    }


    private class ClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.datePickButton:
                    mDatePickerDialog=new DatePickerDialog(mContext, mDateSetListener, year, month, day);
                    mDatePickerDialog.show();
                    break;
                case R.id.timePickButton:
                    mTimePickerDialog=new TimePickerDialog(mContext, mTimeSetListener, hour, minute, true);
                    mTimePickerDialog.show();
                    break;
                default:
                    break;
            }
        }
    }
}
