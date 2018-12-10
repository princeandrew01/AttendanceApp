package com.jevon.studentrollrecorder.factory;

import com.jevon.studentrollrecorder.utils.Punctuality;
import com.jevon.studentrollrecorder.utils.Attendance;
import com.jevon.studentrollrecorder.interfaces.Calculations;

//This is a factory class that generates the object of concrete classes
//The concrete classes here are attendance and punctuality
//

public class Statistics {

    public Calculations getcalculations(String caltype){
        if (caltype == null){
            return null;
        }//end if
        if (caltype.equalsIgnoreCase("Attendance")){
            //return new Attendance();
        }//get calculation for attendance
        else if (caltype.equalsIgnoreCase("Punctuality")){
          // return new Punctuality();
        }//get calculation for punctuality

    return null;
    }//end method getCalculations

}//end factory class Statitsics
