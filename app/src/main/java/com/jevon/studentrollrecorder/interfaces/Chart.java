package com.jevon.studentrollrecorder.interfaces;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.LineChart;

/**Interface for ChartRendering facade design and Pie subclass*/
public interface Chart {
    PieChart draw(String s, String d);
    //LineChart drawLine();
}//end Chart Interface
