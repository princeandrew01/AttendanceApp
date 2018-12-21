package com.jevon.studentrollrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.arch.lifecycle.Observer;
import com.jevon.studentrollrecorder.observers.ViewCourse;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.utils.Utils;

import java.util.ArrayList;

/*Shows the current list of courses for the lecturer. Clicking one takes you to the list of options for the course*/

public class ViewCoursesActivity extends AppCompatActivity{
    private ListView lv_courses;
    private ViewCourse viewModel;
    private ArrayList<Course> courses;
    private ArrayAdapter<Course> adapter;
    private static final String LOG_TAG = "ViewCourseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewModel = new ViewCourse();
        setSupportActionBar(toolbar);
        setUpUI();
        setUpListView();
    }

    private void setUpUI(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab!=null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ViewCoursesActivity.this, AddCourseActivity.class));
                }
            });
        lv_courses = (ListView) findViewById(R.id.lv_view_courses);
    }
    private void setUpListView(){
        courses = new ArrayList<>();
        getCourses();
        adapter = new ArrayAdapter<>(ViewCoursesActivity.this,R.layout.layout_listview_item_med,courses);
        lv_courses.setAdapter(adapter);

        lv_courses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ViewCoursesActivity.this,ViewCourseInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Utils.COURSE_CODE,courses.get(position).getCourseCode());
                bundle.putString(Utils.COURSE_NAME,courses.get(position).getCourseName());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    /**
     * 04086518
     * ViewCourseActivity observes the ViewCourse to see if any data changes occur
     * and udpates the list of courses as necessary.
     */
    public void getCourses(){
        viewModel.getCourseLiveData().observe(this, new Observer<ArrayList<Course>>(){
            @Override
            public void onChanged(ArrayList<Course> courses) {
            if (courses.isEmpty() ){
                Log.i(LOG_TAG, "No Courses found");
                Toast.makeText(ViewCoursesActivity.this,"No courses found on your account",Toast.LENGTH_LONG).show();
            } else {
                adapter.clear();
                adapter.addAll(courses);
            }
            }
        });

    }
}
