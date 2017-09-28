package com.cn.dateandtimepicker02;

import java.util.Calendar;
import java.util.Locale;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    private Context mContext;
    public Calendar mCalendar;
    private Button mTimePickerButton;
    public TimePickerDialog mTimePickerDialog;
    private ClickListenerImpl mClickListenerImpl;
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
        mTimePickerButton=(Button) findViewById(R.id.timePickButton);
        mTimePickerButton.setOnClickListener(mClickListenerImpl);

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

