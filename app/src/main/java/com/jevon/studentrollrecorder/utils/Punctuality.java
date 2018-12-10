package com.jevon.studentrollrecorder.utils;

import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Attendee;
import com.jevon.studentrollrecorder.pojo.Lecture;
import com.jevon.studentrollrecorder.pojo.Session;
import com.jevon.studentrollrecorder.pojo.Course;
import java.util.Date;
import java.util.HashMap;


public class Punctuality implements Calculations {

   // private HashMap<String, Session> sessions;
    private Course course;
    private int totallateSessions;
    private int totalearlySessions;
    private int totalSessionsAttended;
    private int totalSessions;
    private String studentId;
    private int lateTime = 15;

    public Punctuality(String studentId) {
        this.studentId = studentId; //bundle.getString(Utils.ID);
    }//

    public void setCourse(Course course){
        this.course=course;

    }//setCourses

    public int getlateSessions(){ return this.totallateSessions; }

    public int getearlySessions(){ return this.totalearlySessions; }

    public void Calculate() {
        /* initialize variables.  */
        this.totallateSessions =0;
        this.totalearlySessions =0;

        /* checks the total number of sessions stored in the hashmap. */
        this.totalSessions =  this.course.getSessions().size();

        /* iterate over each session. */
        for(HashMap.Entry<String, Session> currentSession : this.course.getSessions().entrySet()) {

            /* check if this student attended the session. */
            Attendee attendee = currentSession.getValue().getAttendees().get(studentId);


            /* when the student arrived */
            if (attendee != null) {
                int studentHrArrival = attendee.getHr();
                int studentMinsArrival = attendee.getMin();

                /* was previously time now changed to date-nrl.  */
                Date studentArrivalTime = new java.sql.Date(studentHrArrival, studentMinsArrival, 0);
                /* we need to figure out when the session started, we need the session key to find the lecture. */
                String sessionKey = (String) currentSession.getKey();

                /* trim the spaces */
                sessionKey = sessionKey.trim();

                /* divide key into parts to identify lecture. */
                String[] keyParts = sessionKey.split("\\s+");

                /* build string to indentify the lecture. */
                String lectureKey = keyParts[0].substring(0,3) + " " + keyParts[2];

                /* get the lecture object from lecturekey. */
                Lecture lecture = course.getLecturess().get(lectureKey);


                /* make sure the lecture exist. */
                if(lecture != null){


                    /* get lecture start time. */
                    Date lectureStartTime = new java.sql.Date(lecture.getStartHr(), lecture.getStartMin(), 0);

                    /* if the student comes before the lecture, early.  */
                    if(studentArrivalTime.compareTo(lectureStartTime) < 0){
                        totalearlySessions++;
                    }//if student arrives before the lecture
                    else{

                        int lateLectureHr = lecture.getStartHr();
                        int lateLectureMin = lecture.getStartMin();

                        int lateHours = 0;
                        int lateMins = 0;
                        lateHours = lateTime / 60;
                        lateMins = lateTime % 60;

                        if(lateLectureMin + lateMins < 60){
                            lateLectureMin = lateLectureMin + lateMins;
                        }
                        else{
                            lateLectureMin = lateLectureMin + lateMins - 60;
                            lateLectureHr++;
                        }

                        lateLectureHr += lateHours;

                        Date lateLectureTime = new java.sql.Date(lateLectureHr, lateLectureMin, 0);
                        /* check if student comes before. */
                        if(studentArrivalTime.compareTo(lateLectureTime) < 0){
                            totalearlySessions++;
                        }
                        else{
                            totallateSessions++;
                        }//end else student late
                    }//end else student not early

            }//if student attended



            /**/


        }//end for loop

        }//end calculate


    }//end calculate


}//end punctuality class
