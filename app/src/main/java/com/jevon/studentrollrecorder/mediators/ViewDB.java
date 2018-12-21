package com.jevon.studentrollrecorder.mediators;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jevon.studentrollrecorder.interfaces.LiveDB;
import com.jevon.studentrollrecorder.observers.FirebaseLiveData;
import com.jevon.studentrollrecorder.pojo.Course;

import java.util.ArrayList;


public class ViewDB {
    private LiveDB data;

    /**
     * Creata a new instance of FirebaseLiveData
     */
    public ViewDB(){
        data = new FirebaseLiveData();
    }

    /**
     * Returns ArrayList of Courses using the firebaseLiveData
     * @return
     */
    public LiveData<ArrayList<Course>> getCourses(){
        return data.getCourses();
    }
}
