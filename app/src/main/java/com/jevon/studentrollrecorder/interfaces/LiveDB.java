package com.jevon.studentrollrecorder.interfaces;

import android.arch.lifecycle.LiveData;
import com.jevon.studentrollrecorder.pojo.Course;
import java.util.ArrayList;

/* Interface for the Database to get courses and anything else */
public interface LiveDB {
    LiveData<ArrayList<Course>> getCourses();
}
