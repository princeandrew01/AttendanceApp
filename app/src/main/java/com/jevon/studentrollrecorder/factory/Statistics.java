package com.jevon.studentrollrecorder.factory;

import com.jevon.studentrollrecorder.pojo.Session;
import com.jevon.studentrollrecorder.utils.Punctuality;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.interfaces.Calculations;

import java.util.HashMap;

//This is a factory class that generates the object of concrete classes
//The concrete classes here are attendance and punctuality
//

public class Statistics {

    public Calculations getcalculations(String caltype, String studentID, HashMap<String, Session> sessions){
        if (caltype == null){
            return null;
        }//end if
        if (caltype.equalsIgnoreCase("Attendance")){
            Attendance a = new Attendance();
            a.setSessions(sessions);
            a.setStudentID(studentID);
            a.Calculate();
            return a;
        }//get calculation for attendance
        else if (caltype.equalsIgnoreCase("Punctuality")){
          // return new Punctuality();
        }//get calculation for punctuality

    return null;
    }//end method getCalculations

}//end factory class Statitsics
