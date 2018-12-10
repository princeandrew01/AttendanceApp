package com.jevon.studentrollrecorder.facade;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jevon.studentrollrecorder.R;
import com.jevon.studentrollrecorder.factory.Statistics;
import com.jevon.studentrollrecorder.helpers.SortByDateHelper;
import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Session;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.utils.Pie;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/** facade solution*/

public class ChartRendering {
    PieChart attendancePieChart;
    PieChart punctualityPieChart = null;
    private int earlySessions;
    private int lateSessions;
    private int totalSessionsAttended = 0;
    private int totalSessionsNotAttended = 0;
    int typeGraph;
    String centerText;
    String label;
    LineData data;
    String source;
    LineChart lineChart;
    long numStudents;

    public ChartRendering(){
    }

    public void setPieChart (PieChart pie) {
        this.attendancePieChart = pie;
    }

    public void setPunctPieChart (PieChart pie) {
        this.punctualityPieChart = pie;
    }

    public void setLineChart (LineChart line) {
        this.lineChart = line;
    }

    public PieChart drawAttendancePieChart(Attendance calcs){
        this.totalSessionsAttended = calcs.getTotalSessionsAttended();
        this.totalSessionsNotAttended = calcs.getTotalSessionsNotAttended();
        Pie pieCharts = new Pie("Percentage Attendance","Breakdown of Student Attendance",attendancePieChart);
        String a = pieCharts.getSource();
        String d = pieCharts.getDescription();
        this.attendancePieChart = pieCharts.draw(a,d);
        ArrayList<Entry> entries = pieCharts.getEntries();
        ArrayList<String> labels = pieCharts.getLabels();

        //Add entried
        entries.add(new Entry(totalSessionsAttended, 0));
        entries.add(new Entry(totalSessionsNotAttended,1));

        /* convert to pieDataSet. */
        PieDataSet dataset = new PieDataSet(entries, "Sessions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //Add labels
        labels.add("Attended");
        labels.add("Missed");

        PieData data = new PieData(labels, dataset);
        this.attendancePieChart.setData(data);
        this.attendancePieChart.notifyDataSetChanged();
        this.attendancePieChart.invalidate();
        this.attendancePieChart.animateY(1500);

        return this.attendancePieChart;
    }

    public PieChart drawPunctualityPieChart(){
        Pie pieCharts = new Pie("Punctuality Percentage","Breakdown of Student Punctuality",punctualityPieChart);
        String a = pieCharts.getSource();
        String d = pieCharts.getDescription();
        this.punctualityPieChart = pieCharts.draw(a,d);
        ArrayList<Entry> entries = pieCharts.getEntries();
        ArrayList<String> labels = pieCharts.getLabels();

        entries.add(new Entry(earlySessions, 0));
        entries.add(new Entry(lateSessions,1));

        /* convert to pieDataSet. */
        PieDataSet dataset = new PieDataSet(entries, "Sessions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        labels.add("Early");
        labels.add("Late");

        PieData data = new PieData(labels, dataset);
        this.punctualityPieChart.setData(data);

        return this.punctualityPieChart;
    }

    public LineChart setUpLineChart(){

        //lineChart.setOnChartValueSelectedListener(this);
        // descriptive text to explain to the user why there is no chart available
        lineChart.setNoDataTextDescription("No Student Attendance Data Available.");
        // descriptive text that appears in the bottom right corner of the chart
        lineChart.setDescription("Student Attendance");
        //lineChart.setDescriptionColor(Color.BLACK);

        // enable touch gestures
        //lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        /*lineChart.setDragEnabled(true);
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
        legend.setTextColor(Color.WHITE);*/

        // set up Y axis

        YAxis leftAxis = lineChart.getAxisLeft();
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
        xl.setEnabled(true);

        return lineChart;

    }
}
