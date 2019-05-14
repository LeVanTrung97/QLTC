package com.example.qlct;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lo_view)
    LinearLayout loView;
    @BindView(R.id.lo_chart)
    LinearLayout loChart;
    @BindView(R.id.txt_total_revenue)
    TextView txtTotalRevenue;
    @BindView(R.id.txt_total_expenditure)
    TextView txtTotalExpenditure;
    @BindView(R.id.txt_surplus)
    TextView txtSurplus;
    @BindView(R.id.txt_chart_demo)
    TextView txtChartDemo;
    @BindView(R.id.img_chart)
    ImageView imgChart;
    @BindView(R.id.img_text)
    ImageView imgText;
    @BindView(R.id.rdg_time)
    RadioGroup rdgTime;
    @BindView(R.id.rb_month)
    RadioButton rbMonth;
    @BindView(R.id.rb_year)
    RadioButton rbYear;

    private boolean isText = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);
        fillData();
        init();
    }

    private void fillData() {
        txtTotalRevenue.setText("month text");
        loChart.setVisibility(View.GONE);
    }

    private void init() {
        rdgTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_month){
                    if(isText){
                        txtTotalRevenue.setText("month text");
                    } else {
                        txtChartDemo.setText("month chart");
                    }
                } else {
                    if(isText){
                        txtTotalRevenue.setText("year text");
                    } else {
                        txtChartDemo.setText("year chart");
                    }
                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @OnClick(R.id.img_chart)
    void showChart(){
        loChart.setVisibility(View.VISIBLE);
        loView.setVisibility(View.GONE);
        imgChart.setVisibility(View.GONE);
        imgText.setVisibility(View.VISIBLE);
        isText = false;
        if(rdgTime.getCheckedRadioButtonId() == R.id.rb_month){
            txtChartDemo.setText("month chart");
        } else {
            txtChartDemo.setText("year chart");
        }
    }

    @OnClick(R.id.img_text)
    void showText(){
        loChart.setVisibility(View.GONE);
        loView.setVisibility(View.VISIBLE);
        imgChart.setVisibility(View.VISIBLE);
        imgText.setVisibility(View.GONE);
        isText = true;
        if(rdgTime.getCheckedRadioButtonId() == R.id.rb_month){
            txtChartDemo.setText("month chart");
        } else {
            txtChartDemo.setText("year chart");
        }
    }
}
