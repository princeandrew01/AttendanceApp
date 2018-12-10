package com.jevon.studentrollrecorder.observers;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.arch.lifecycle.ViewModel;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.helpers.FirebaseHelper;

import java.util.ArrayList;

public class ViewCourseViewModel extends ViewModel {
    private final static FirebaseHelper firebaseHelper = new FirebaseHelper();
    private final static Firebase ref_id = firebaseHelper.getRef_id();

    private final FirebaseLiveData liveData = new FirebaseLiveData(ref_id);

    private final LiveData<ArrayList<Course>> courseLiveData = Transformations.map(liveData, new Deserializer());

    private class Deserializer implements Function<DataSnapshot, ArrayList<Course>> {
        @Override
        public ArrayList<Course> apply(DataSnapshot input) {
            ArrayList<Course> courses = new ArrayList<>();
            for (DataSnapshot coursesSnapshot: input.getChildren()) {
                courses.add(coursesSnapshot.getValue(Course.class));
            }
            return courses;
        }
    }
    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }

    public LiveData<ArrayList<Course>> getCourseLiveData(){
        return courseLiveData;
    }
}
