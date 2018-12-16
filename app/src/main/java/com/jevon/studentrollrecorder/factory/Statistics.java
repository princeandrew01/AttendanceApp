package com.jevon.studentrollrecorder.factory;

import com.jevon.studentrollrecorder.interfaces.Calculations;
import com.jevon.studentrollrecorder.pojo.Course;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.utils.Punctuality;

//This is a factory class that generates the object of subclasses classes
//The subclasses are the util classes:attendance and punctuality


public class Statistics {
    //generated the necessary objects attendance and punctuality
    //casting the return type as Calculations
    public Calculations getcalculations(String caltype, String studentID, Course course){
        if (caltype == null){
            return null;
        }//end if
        if (caltype.equalsIgnoreCase("Attendance")){
            Attendance attend = new Attendance();
            attend.setCourse(course);
           
            attend.setStudentID(studentID);
            attend.Calculate();
            return attend;
        }//get calculation for attendance
        else if (caltype.equalsIgnoreCase("Punctuality")){
            Punctuality punct = new Punctuality();
            punct.setCourse(course);
           
            punct.setStudentID(studentID);
            punct.Calculate();
            return punct;
         
        }//get calculation for punctuality

    return null;
    }//end method getCalculations

}//end factory class Statitsics
