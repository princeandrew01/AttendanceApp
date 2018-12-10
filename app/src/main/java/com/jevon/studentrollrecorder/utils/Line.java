package com.jevon.studentrollrecorder.utils;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.jevon.studentrollrecorder.interfaces.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class Line implements Chart {

    LineChart lineChart;
    long numStudents=0;
    LineData data;
    PieChart p;


    public PieChart draw(String s, String d){
        return p;
    }

    public void setListener (LineChart linechart){
        this.lineChart = linechart;
    }

    public LineChart drawLine(){

        //lineChart.setOnChartValueSelectedListener(this);

        // descriptive text to explain to the user why there is no chart available
       // lineChart.setNoDataTextDescription("No Student Attendance Data Available.");
        // descriptive text that appears in the bottom right corner of the chart
       // lineChart.setDescription("Student Attendance");
        lineChart.setDescriptionColor(Color.BLACK);

        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        lineChart.setDrawGridBackground(true);

        // both axes can be scaled simultaneously on zoom
        lineChart.setPinchZoom(true);

        // set an alternative background color
        lineChart.setBackgroundColor(Color.BLACK);

        data = new LineData();

        // add empty data
        lineChart.setData(data);

        // get the legend
        Legend legend = lineChart.getLegend();

        // modify the legend
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setTextColor(Color.WHITE);

        // set up Y axis

       /* YAxis leftAxis = lineChart.getAxisLeft();
        // set the max value on Y axis to the number of students in the course
        leftAxis.setAxisMaxValue((float)numStudents);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextColor(Color.WHITE);

        // indicate the number of students registered for course as a line on the y axis
        LimitLine maxStudents = new LimitLine((float)(numStudents), "# registered");
        maxStudents.setLineColor(Color.GREEN);
        maxStudents.setTextColor(Color.BLACK);
        maxStudents.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        maxStudents.setTextSize(5);
        leftAxis.addLimitLine(maxStudents);

        YAxis rightAxis = lineChart.getAxisRight();
        // do not draw this
        rightAxis.setEnabled(false);

        // set up X axis
        XAxis xl = lineChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setSpaceBetweenLabels(5);
        xl.setEnabled(true);*/
       return lineChart;

    }


}
