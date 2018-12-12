package com.jevon.studentrollrecorder.observers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jevon.studentrollrecorder.pojo.Course;

import java.util.ArrayList;

public class ViewCourse extends ViewModel {
    private FirebaseLiveData firebaseLiveData;

    public ViewCourse(){
        firebaseLiveData = new FirebaseLiveData();
    }

    public LiveData<ArrayList<Course>> getCourseLiveData(){
        firebaseLiveData.getQuery("course");
        return firebaseLiveData.getCourses();
    }
}
