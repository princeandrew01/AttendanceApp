package com.jevon.studentrollrecorder.utils;

import android.text.SpannableString;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jevon.studentrollrecorder.R;
import com.jevon.studentrollrecorder.interfaces.Chart;
import android.support.v7.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import java.util.ArrayList;

/**Pie class implements the Chart interface. It draws and returns a Pie Chart object for other classes to access*/

public class Pie implements Chart{
    //Initialize variables
    PieChart PieChart = null;
    int val = 0;
    String source;
    String description;
    PieChart p;
    LineChart l;
    ArrayList<Entry> entries = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<String>();
    PieDataSet dataset;

    public Pie(){};

    public Pie(String source, String desc, PieChart p) {
        this.source = source;
        this.description = desc;
        this.p = p;
    }

    public void setDataset(PieDataSet dataset){
        this.dataset = dataset;
    }

   /* public LineChart drawLine(){
        return l;
    }*/

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public PieChart getPieChart() {
        return p;
    }

    public ArrayList<Entry> getEntries (){
        return entries;
    }

    public ArrayList<String> getLabels(){
        return labels;
    }
    //Method to draw and return a PieChart object. This is utilized by the ChartRendering class
    public PieChart draw(String s, String d){
        SpannableString centerText = new SpannableString(s);
        this.p.setCenterText(centerText);
        this.p.setUsePercentValues(true);
        this.p.setDescription(d);
        return p;
    }


}


