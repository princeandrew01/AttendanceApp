package com.jevon.studentrollrecorder.interfaces;

import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.charts.LineChart;

public interface Chart {
    PieChart draw(String s, String d);
    //LineChart drawLine();
}
