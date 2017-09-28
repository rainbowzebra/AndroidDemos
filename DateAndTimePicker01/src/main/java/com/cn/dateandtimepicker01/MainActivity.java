package com.cn.dateandtimepicker01;

import java.util.Calendar;
import java.util.Locale;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    private Context mContext;
    public Calendar mCalendar;
    private Button mDatePickerButton;
    public DatePickerDialog mDatePickerDialog;
    private ClickListenerImpl mClickListenerImpl;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
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
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                String date = "year="+year+", month="+(monthOfYear+1)+",day="+dayOfMonth;
                Toast.makeText(mContext,date,Toast.LENGTH_SHORT).show();
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
                default:
                    break;
            }
        }
    }
}

