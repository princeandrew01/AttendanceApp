package com.jevon.studentrollrecorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jevon.studentrollrecorder.facade.ChartRendering;
import com.jevon.studentrollrecorder.factory.Statistics;
import com.jevon.studentrollrecorder.helpers.FirebaseHelper;
import com.jevon.studentrollrecorder.pojo.Attendee;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.pojo.Lecture;
import com.jevon.studentrollrecorder.pojo.Session;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.utils.Punctuality;
import com.jevon.studentrollrecorder.utils.Utils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/*This activity shows analytics for a selected student*/

public class ViewIndividualStudentAnalytics extends AppCompatActivity {

    private String courseCode;
    private String courseName;
    private ArrayList<Session> sessions;
    private String studentId;
    private String studentName;

    private Course course;

    /* variables for analytics. */
    private int totalSessionsAttended = 0;
    private int totalSessionsNotAttended = 0;
    private int totalSessions = 0;

    private int earlySessions;
    private int lateSessions;

    /* set default late time for second graph as 15 mins. */
    private int lateTime = 15;

    PieChart attendancePieChart;
            //= null;
    PieChart punctualityPieChart = null;

    private ChartRendering cr;
    private ChartRendering punctChart;
    private  Statistics stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_student_analytics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* retrieve data from the intent. */
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            courseCode = bundle.getString(Utils.COURSE_CODE);
            courseName = bundle.getString(Utils.COURSE_NAME);
            studentId = bundle.getString(Utils.ID);
            studentName = bundle.getString(Utils.NAME);
        }

        /* Draw charts. */
        stats = new Statistics();
        cr = new ChartRendering();
        punctChart= new ChartRendering();

       
        /* get the course from firebase. */
        getCourse();

        /* display basic student and course information. */
        studentInfo();
        courseInfo();

    }

    public void studentInfo(){
        TextView student = (TextView) findViewById(R.id.txt_student);
        student.setText(studentName + " (" + studentId + ")");
    }

    public void courseInfo(){
        TextView course = (TextView) findViewById(R.id.txt_course);
        course.setText(courseName + " (" + courseCode + ")");
    }

    public void attendanceCalculations(){

        cr.setPieChart((PieChart) findViewById(R.id.attendance_graph));
        this.attendancePieChart = cr.drawAttendancePieChart((Attendance) stats.getcalculations("Attendance",studentId, course));
    }

    
    
    public void getCourse(){
        /* get a firebase helper. */
        FirebaseHelper firebaseHelper = new FirebaseHelper();

        /* get a ref to the lecturer courses. */
        Firebase ref = firebaseHelper.getRef_id();

        /* narrow down to the course we are interested in . */
        Firebase courseRef = ref.child(courseCode);

        /* get the course from firebase and store it locally. */
        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /* load in the course. */
                course = dataSnapshot.getValue(Course.class);

                if(course.getSessions() != null && course.getLecturess() != null){
                    attendanceCalculations();
                    punctualityCalculations();
                   
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //new punctualityCalculations
    public void punctualityCalculations(){
        punctChart.setPunctPieChart((PieChart) findViewById(R.id.punctuality_graph));
        this.punctualityPieChart = punctChart.drawPunctualityPieChart((Punctuality)stats.getcalculations("Punctuality",studentId, course));

    }//end NEW punctualityCalculations

   
    public void updateLateGraph(View view){

        /* get edit text view. */
        EditText late_edit = (EditText) findViewById(R.id.edit_txt_late_time);

        /* get the late time entered in mins. */
        try{
            this.lateTime = Integer.parseInt(late_edit.getText().toString());
        }
        catch(Exception e){
            /* toast for invalid number.*/
            Toast.makeText(this, "You did not enter a valid number", Toast.LENGTH_LONG).show();
        }
        /*update punctuality pie chart*/
        punctualityCalculations();
      
    }
}
