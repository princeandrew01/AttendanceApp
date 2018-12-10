package com.jevon.studentrollrecorder.factory;

import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.utils.Punctuality;

//This is a factory class that generates the object of concrete classes
//The concrete classes here are attendance and punctuality
//

public class Statistics {

    public Calculations getcalculations(String caltype, String studentID, Course course){
        if (caltype == null){
            return null;
        }//end if
        if (caltype.equalsIgnoreCase("Attendance")){
            Attendance a = new Attendance();
            a.setCourse(course);
           // a.setSessions(sessions);
            a.setStudentID(studentID);
            a.Calculate();
            return a;
        }//get calculation for attendance
        else if (caltype.equalsIgnoreCase("Punctuality")){
            Punctuality a = new Punctuality();
            a.setCourse(course);
            // a.setSessions(sessions);
            a.setStudentID(studentID);
            a.Calculate();
            return a;
          // return new Punctuality();
        }//get calculation for punctuality

    return null;
    }//end method getCalculations

}//end factory class Statitsics
