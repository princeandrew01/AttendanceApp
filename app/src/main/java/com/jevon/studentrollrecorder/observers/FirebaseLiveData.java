package com.jevon.studentrollrecorder.observers;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.util.Log;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.FirebaseError;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.utils.MyApplication;
import com.jevon.studentrollrecorder.utils.Utils;

import java.util.ArrayList;

/**
 * 04086518
 * Firebase Live Data will replace the firebase Helper function in the long run.
 *
 * Current Implementation is just for Courses.
 **/
public class FirebaseLiveData extends LiveData<DataSnapshot> {
    /**
     * uid - Current user ID
     * myApplication - current application running.
     * LOG_TAG - used for debugging purposes and logging any messages
     * query - holds the current firebase query
     * listener - Listener used to pull data for Firebase
     */
    private String uid;
    private MyApplication myApplication;
    private static final String LOG_TAG = "FirebaseQueryLiveData";

    private Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    /**
     * Gets the current application to get the user ID
     * Sets the Query URL for the firebase
     */
    public FirebaseLiveData(){
        myApplication = MyApplication.getInstance();
        uid = myApplication.getSharedPreferences(Utils.SHAREDPREF, Context.MODE_PRIVATE).getString(Utils.ID,null);
        this.query = new Firebase(Utils.FIREBASE_URL).child(Utils.LECTURERS).child(uid);
    }
    /**
     * Sets the query depending on what is being pulled.
     */
    public void getQuery(String type){
        switch(type){
            case "course":
                    this.query = myApplication.getRef().child(Utils.LECTURERS).child(uid);
                break;

        }
    }
    /**
     * When activity is started adds the ValueEventListener
     */
    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }
    /**
     * When activity is ended adds the ValueEventListener
     */
    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        query.removeEventListener(listener);
    }


    /**
     * gets the Courses using the Deserialiser
     */
    public LiveData<ArrayList<Course>> getCourses(){
        return Transformations.map(this, new CourseDeserialiser());
    }
    /**
     * ValueEventListener
     */
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

    /**
     * Course Deserialiser converts a DataSnapShot to an arraylist of courses
     */
    private class CourseDeserialiser implements Function<DataSnapshot, ArrayList<Course>> {
        @Override
        public ArrayList<Course> apply(DataSnapshot input) {
            ArrayList<Course> courses = new ArrayList<>();
            for (DataSnapshot coursesSnapshot: input.getChildren()) {
                courses.add(coursesSnapshot.getValue(Course.class));
            }
            return courses;
        }
    }
}
