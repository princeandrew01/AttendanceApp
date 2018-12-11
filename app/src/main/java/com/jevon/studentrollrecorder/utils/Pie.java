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

public class Pie implements Chart{
    PieChart PieChart = null;
    int val = 0;
    /*int typeGraph;
    String centerText;
    String label;*/
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

    public PieChart draw(String s, String d){
        /* text in the center of graph. */
        //SpannableString centerText = new SpannableString(this.source);
        SpannableString centerText = new SpannableString(s);
        this.p.setCenterText(centerText);
        this.p.setUsePercentValues(true);
        this.p.setDescription(d);
        /*this.p.notifyDataSetChanged();
        this.p.invalidate();
        this.p.animateY(1500);
        */
        return p;
    }


}


