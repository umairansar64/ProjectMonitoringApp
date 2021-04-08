package com.example.projectmonitoringapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validate {

    Validate(){

    }

    // main input terms like project and group ids
    public boolean isTermValid(String term){

        if(term.trim().length()<=0){ // not left blank
            return false;
        }
        else if(!term.matches("[ - 0-9a-zA-Z]+")){ // match these characters
            return false;
        }
        return true;
    }

    public boolean isEmailValid(String term){

        if(term.trim().length()<=0){
            return false;
        }
        else if(!term.matches("[ - 0-9a-zA-Z@.]+")){
            return false;
        }
        else if(!term.contains("@") || !term.contains(".")){ // must contain these characters
            return false;
        }
        return true;
    }

    public boolean isGradeValid(String term){

        if(term.trim().length()<=0){
            return false;
        }
        else if(!term.matches("[A-FU*]+")){ // A,B,C,D,E,F,U,A* etc
            return false;
        }
        return true;
    }

    public boolean isDateValid(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public String getDateInYMDformat(String date) { // convert date to YYYY-MM-DD format

        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "yyyy-MM-dd";

        String oldDateString = date;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException pe) {

        }
        sdf.applyPattern(NEW_FORMAT);
        return (sdf.format(d));
    }

    public String getDateInDMYformat(String date) { // convert date to DD/MM/YYYY format

        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd/MM/yyyy";

        String oldDateString = date;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException pe) {

        }
        sdf.applyPattern(NEW_FORMAT);
        return (sdf.format(d));
    }
}
