package com.jevon.studentrollrecorder.observers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jevon.studentrollrecorder.pojo.Course;

import java.util.ArrayList;

/**
 * Will be used as an observer
 */
public class ViewCourse extends ViewModel {
    private FirebaseLiveData firebaseLiveData;

    /**
     * Creata a new instance of FirebaseLiveData
     */
    public ViewCourse(){
        firebaseLiveData = new FirebaseLiveData();
    }

    /**
     * Returns ArrayList of Courses using the firebaseLiveData
     * @return
     */
    public LiveData<ArrayList<Course>> getCourseLiveData(){
        firebaseLiveData.getQuery("course");
        return firebaseLiveData.getCourses();
    }
}
