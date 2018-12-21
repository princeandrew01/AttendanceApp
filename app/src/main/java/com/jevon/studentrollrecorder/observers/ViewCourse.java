package com.jevon.studentrollrecorder.observers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jevon.studentrollrecorder.interfaces.LiveDB;
import com.jevon.studentrollrecorder.pojo.Course;

import java.util.ArrayList;

/**
 * Will be used as an observer
 */
public class ViewCourse extends ViewModel {
    private LiveDB data;

    /**
     * Creata a new instance of FirebaseLiveData
     */
    public ViewCourse(){
        data = new FirebaseLiveData();
    }

    /**
     * Returns ArrayList of Courses using the firebaseLiveData
     * @return
     */
    public LiveData<ArrayList<Course>> getCourseLiveData(){
        data.getQuery("course");
        return data.getCourses();
    }
}
