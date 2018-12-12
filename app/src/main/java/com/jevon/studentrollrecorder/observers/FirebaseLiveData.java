package com.jevon.studentrollrecorder.observers;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.util.Log;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.FirebaseError;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.utils.MyApplication;
import com.jevon.studentrollrecorder.utils.Utils;

import java.util.ArrayList;


public class FirebaseLiveData extends LiveData<DataSnapshot> {
    private String uid;
    private MyApplication myApplication;
    private static final String LOG_TAG = "FirebaseQueryLiveData";

    private Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    public FirebaseLiveData(){
        myApplication = MyApplication.getInstance();
        uid = myApplication.getSharedPreferences(Utils.SHAREDPREF, Context.MODE_PRIVATE).getString(Utils.ID,null);
        this.query = myApplication.getRef().child(Utils.LECTURERS).child(uid);
    }

    public void getQuery(String type){
        switch(type){
            case "course":
                    this.query = myApplication.getRef().child(Utils.LECTURERS).child(uid);
                break;

        }
    }
    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        query.removeEventListener(listener);
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(FirebaseError error) {
            Log.e(LOG_TAG, "Can't listen to query " + query, error.toException());
        }
    }


    private class CourseDeserializer implements Function<DataSnapshot, ArrayList<Course>> {
        @Override
        public ArrayList<Course> apply(DataSnapshot input) {
            ArrayList<Course> courses = new ArrayList<>();
            for (DataSnapshot coursesSnapshot: input.getChildren()) {
                courses.add(coursesSnapshot.getValue(Course.class));
            }
            return courses;
        }
    }
    public LiveData<ArrayList<Course>> getCourses(){
        return Transformations.map(this, new CourseDeserializer());
    }
}
