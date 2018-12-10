package com.jevon.studentrollrecorder.utils;

import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.pojo.Session;

import java.util.HashMap;

public class Attendance implements Calculations{

   // private HashMap<String, Session> sessions;
    private Course course;
    private int totalSessions;
    private int totalSessionsNotAttended;
    private int totalSessionsAttended;
    private String studentId;

   // private String course;

    public Attendance(){

    }
    public void setStudentID(String studentId){
        this.studentId = studentId;
    }
   /** public void setSessions(HashMap<String, Session> sessions){
        this.sessions=sessions;

    }//setSessions*/
    public void setCourse(Course course){
        this.course=course;

    }//setCourses

    public int getTotalSessionsAttended(){
        return this.totalSessionsAttended;
    }

    public int getTotalSessionsNotAttended(){
        return this.totalSessionsNotAttended;
    }


    public void Calculate(){
        /* initialize variables.  */
        this.totalSessions  = 0;
        this.totalSessionsNotAttended = 0;
        this.totalSessionsAttended = 0;

             /* checks the total number of sessions stored in the hashmap. */
        this.totalSessions =  this.course.getSessions().size();

        /* iterate over each session. */
        for(HashMap.Entry<String, Session> currentSession : this.course.getSessions().entrySet()){

            /* check if the student we are interested in attended this session. */
            if(currentSession.getValue().getAttendees().get(studentId) != null){
                totalSessionsAttended++;
            }
            else{
                totalSessionsNotAttended++;
            }
        }//for loop
    }//end Calculate



    // e.getSessions() != null && course.getLecturess() != null){
    //    attendanceCalculations();
   // }

   /** public void attendanceCalculations(String courseCode){
        this.firebaseHelper = new FirebaseHelper();

        /* get a ref to the lecturer courses.
        Firebase ref = firebaseHelper.getRef_id();

        /* narrow down to the course we are interested in .
        Firebase courseRef = ref.child(courseCode);

        /* get the course from firebase and store it locally.

        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /* load in the course.
                course = dataSnapshot.getValue(Course.class);

                if(course.getSessions() != null && course.getLecturess() != null){
                    //attendanceCalculations();

                    /*initialize variables.
                    this.totalSessions  = 0;
                    this.totalSessionsNotAttended = 0;
                    this.totalSessionsAttended = 0;

                    /* get the sessions from the course.
                    HashMap<String, Session> sessions = course.getSessions();

                    /* checks the total number of sessions stored in the hashmap.
                    this.totalSessions =  sessions.size();

                    /* iterate over each session.
                    for(HashMap.Entry<String, Session> currentSession : sessions.entrySet()){

                        /* check if the student we are interested in attended this session.
                            totalSessionsAttended++;
                        }
                        else{
                            totalSessionsNotAttended++;
                        }


                    }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                }

        }
                                        }*/
      /**  courseRef.addValueEventListener(new ValueEventListener() {
        /*initialize variables.
        this.totalSessions  = 0;
        this.totalSessionsNotAttended = 0;
        this.totalSessionsAttended = 0;

        /* get the sessions from the course.
        HashMap<String, Session> sessions = course.getSessions();

        /* checks the total number of sessions stored in the hashmap.
        this.totalSessions =  sessions.size();

        /* iterate over each session.
        for(HashMap.Entry<String, Session> currentSession : sessions.entrySet()){

            /* check if the student we are interested in attended this session.
            if(currentSession.getValue().getAttendees().get(studentId) != null){
                totalSessionsAttended++;
            }
            else{
                totalSessionsNotAttended++;
            }
        }

        updateAttendancePieChart();

    }
       }//end attendance calculations*/




}//end class

