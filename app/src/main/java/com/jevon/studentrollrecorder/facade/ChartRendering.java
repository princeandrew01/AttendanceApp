package com.jevon.studentrollrecorder.facade;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.utils.Pie;
import com.jevon.studentrollrecorder.utils.Punctuality;

import java.util.ArrayList;

/** This class provides facade solution.
 * It creates and displays two Pie Charts: Attendance and Punctuality.
 * For each, it first creates the chart using the Pie Class then using the calculations from the Statistics factory,
 * the relevant data is populated.*/

public class ChartRendering {
    //Initialize variables
    PieChart attendancePieChart;
    PieChart punctualityPieChart;
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

    //setters for PieChart intitialization in ViewIndividualStudentAnalytics
    public void setPieChart (PieChart pie) {
        this.attendancePieChart = pie;
    }

    public void setPunctPieChart (PieChart pie) {
        this.punctualityPieChart = pie;
    }

    public void setLineChart (LineChart line) {
        this.lineChart = line;
    }

    //Method to draw and populate the Attendance Pie Chart
    public PieChart drawAttendancePieChart(Attendance calcs){
        //Use the calculations from the Statistics Factory
        this.totalSessionsAttended = calcs.getTotalSessionsAttended();
        this.totalSessionsNotAttended = calcs.getTotalSessionsNotAttended();
        //Draw the Attendance Pie CHart
        Pie pieCharts = new Pie("Percentage Attendance","Breakdown of Student Attendance",attendancePieChart);
        String a = pieCharts.getSource();
        String d = pieCharts.getDescription();
        this.attendancePieChart = pieCharts.draw(a,d);
        ArrayList<Entry> entries = pieCharts.getEntries();
        ArrayList<String> labels = pieCharts.getLabels();

        //Add entries
        entries.add(new Entry(totalSessionsAttended, 0));
        entries.add(new Entry(totalSessionsNotAttended,1));

        /* convert to pieDataSet. */
        PieDataSet dataset = new PieDataSet(entries, "Sessions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //Add labels
        labels.add("Attended");
        labels.add("Missed");
        //Update the Pie Chart with the data calculated
        PieData data = new PieData(labels, dataset);
        this.attendancePieChart.setData(data);
        this.attendancePieChart.notifyDataSetChanged();
        this.attendancePieChart.invalidate();
        this.attendancePieChart.animateY(1500);

        return this.attendancePieChart;
    }//end drawAttendancePieChart

    //Method to draw and populate the Punctuality Pie Chart
    public PieChart drawPunctualityPieChart(Punctuality calcs){
        //Draw the Punctuality Pie Chart
        Pie pieCharts = new Pie("Punctuality Percentage","Breakdown of Student Punctuality",punctualityPieChart);
        String a = pieCharts.getSource();
        String d = pieCharts.getDescription();
        //Use the calculations from the Statistics Factory
        this.earlySessions=calcs.getearlySessions();
        this.lateSessions=calcs.getlateSessions();
        this.punctualityPieChart = pieCharts.draw(a,d);
        ArrayList<Entry> entries = pieCharts.getEntries();
        ArrayList<String> labels = pieCharts.getLabels();

        //add entries
        entries.add(new Entry(earlySessions, 0));
        entries.add(new Entry(lateSessions,1));

        /* convert to pieDataSet. */
        PieDataSet dataset = new PieDataSet(entries, "Sessions");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        //Add labels
        labels.add("Early");
        labels.add("Late");

        //Update the Pie Chart with the data calculated
        PieData data = new PieData(labels, dataset);
        this.punctualityPieChart.setData(data);
        this.punctualityPieChart.notifyDataSetChanged();
        this.punctualityPieChart.invalidate();
        this.punctualityPieChart.animateY(1500);

        return this.punctualityPieChart;
    }// end drawPunctualityPieChart


}//end ChartRendering class
