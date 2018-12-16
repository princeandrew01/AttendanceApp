package com.jevon.studentrollrecorder.utils;
/**Attendance class implements Calculations interface
Calculates total attendance for a particular student*/

import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.pojo.Session;
import java.util.HashMap;

public class Attendance implements Calculations{

  
    private Course course;
    private int totalSessions;
    private int totalSessionsNotAttended;
    private int totalSessionsAttended;
    private String studentId;

    public Attendance(){

    }
    public void setStudentID(String studentId){
        this.studentId = studentId;
    }
 
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



}//end class

