package com.example.projectmonitoringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    public static final String DATABASE_NAME = "project_monitoring.db";

    // members for the projects table
    public static final String PROJECTS_TABLE_NAME = "projects_table";
    public static final String COL_PROJECTS_NUMBER = "NUMBER";
    public static final String COL_PROJECTS_TITLE = "TITLE";
    public static final String COL_PROJECTS_ID = "ID";
    public static final String COL_PROJECTS_TUTOR = "SUPERVISOR";
    public static final String COL_PROJECTS_CLIENT = "CLIENT";
    public static final String COL_PROJECTS_UNIT = "UNIT";


    // members for the students table
    public static final String STUDENTS_TABLE_NAME = "students_table";
    public static final String COL_STUDENTS_NUMBER = "NUMBER";
    public static final String COL_STUDENTS_ID = "ID";
    public static final String COL_STUDENTS_FIRSTNAME = "FIRSTNAME";
    public static final String COL_STUDENTS_LASTNAME = "LASTNAME";
    public static final String COL_STUDENTS_GRADE = "GRADE";


    // members for the groups table
    public static final String GROUPS_TABLE_NAME = "groups_table";
    public static final String COL_GROUPS_NUMBER = "NUMBER";
    public static final String COL_GROUPS_ID = "ID";
    public static final String COL_GROUPS_PROJECT_ID = "PROJECT_ID";
    public static final String COL_GROUPS_NUM_OF_WEEKS = "NUM_OF_WEEKS";
    public static final String COL_GROUPS_START_DATE = "START_DATE";


    // members for the clients table
    public static final String CLIENTS_TABLE_NAME = "clients_table";
    public static final String COL_CLIENTS_NUMBER = "NUMBER";
    public static final String COL_CLIENTS_FULL_NAME = "FULL_NAME";
    public static final String COL_CLIENTS_PROJECT_ID = "PROJECT_ID";
    public static final String COL_CLIENTS_PHONE = "PHONE";
    public static final String COL_CLIENTS_EMAIL = "EMAIL";
    public static final String COL_CLIENTS_ADDRESS = "ADDRESS";


    // members for the students in groups table
    public static final String SIG_TABLE_NAME = "sig_table";
    public static final String COL_SIG_NUMBER = "NUMBER";
    public static final String COL_SIG_STUDENT_ID = "STUDENT_ID";
    public static final String COL_SIG_STUDENT_ROLE = "STUDENT_ROLE";
    public static final String COL_SIG_GROUP_ID = "GROUP_ID";


    // members for the weeks for student table
    public static final String WFS_TABLE_NAME = "wfs_table";
    public static final String COL_WFS_NUMBER = "NUMBER";
    public static final String COL_WFS_WEEK_NUM = "WEEK_NUM";
    public static final String COL_WFS_STUDENT_ID = "STUDENT_ID";
    public static final String COL_WFS_GROUP_ID = "GROUP_ID";
    public static final String COL_WFS_ATTENDANCE = "ATTENDANCE";
    public static final String COL_WFS_CONTRIBUTION = "CONTRIBUTION";
    public static final String COL_WFS_ADDITIONAL_NOTES = "NOTES";


    // members for the weeks for group table
    public static final String WFG_TABLE_NAME = "wfg_table";
    public static final String COL_WFG_NUMBER = "NUMBER";
    public static final String COL_WFG_WEEK_NUM = "WEEK_NUM";
    public static final String COL_WFG_GROUP_ID = "GROUP_ID";
    public static final String COL_WFG_TASK = "TASK";
    public static final String COL_WFG_TASK_COMPLETED = "TASK_COMPLETED";
    public static final String COL_WFG_PROGRESS = "PROGRESS";
    public static final String COL_WFG_NOTES = "NOTES";
    public static final String COL_WFG_ISSUES = "ISSUES";
    public static final String COL_WFG_WEEK_MARKED = "WEEK_MARKED";


    // query for creating projects table
    public static final String QUERY_CREATE_PROJECTS_TABLE = "create table "
            + PROJECTS_TABLE_NAME + " ("
            + COL_PROJECTS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_PROJECTS_TITLE + " TEXT,"
            + COL_PROJECTS_ID + " TEXT UNIQUE,"
            + COL_PROJECTS_TUTOR + " TEXT,"
            + COL_PROJECTS_CLIENT + " TEXT,"
            + COL_PROJECTS_UNIT + " TEXT)";


    // query for creating students table
    public static final String QUERY_CREATE_STUDENTS_TABLE = "create table "
            + STUDENTS_TABLE_NAME + " ("
            + COL_STUDENTS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_STUDENTS_ID + " INTEGER UNIQUE,"
            + COL_STUDENTS_FIRSTNAME + " TEXT,"
            + COL_STUDENTS_LASTNAME + " TEXT,"
            + COL_STUDENTS_GRADE + " TEXT)";


    // query for creating groups table
    public static final String QUERY_CREATE_GROUPS_TABLE = "create table "
            + GROUPS_TABLE_NAME + " ("
            + COL_GROUPS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_GROUPS_ID + " TEXT UNIQUE,"
            + COL_GROUPS_PROJECT_ID + " TEXT,"
            + COL_GROUPS_NUM_OF_WEEKS + " INTEGER,"
            + COL_GROUPS_START_DATE + " TEXT)";


    //  query for creating clients table
    public static final String QUERY_CREATE_CLIENTS_TABLE = "create table "
            + CLIENTS_TABLE_NAME + " ("
            + COL_CLIENTS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_CLIENTS_FULL_NAME + " TEXT,"
            + COL_CLIENTS_PROJECT_ID + " TEXT UNIQUE,"
            + COL_CLIENTS_PHONE + " NUMBER,"
            + COL_CLIENTS_EMAIL + " TEXT,"
            + COL_CLIENTS_ADDRESS+" TEXT)";


    // query for creating SIG (Student in group) table
    public static final String QUERY_CREATE_SIG_TABLE = "create table "
            + SIG_TABLE_NAME + " ("
            + COL_SIG_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_SIG_STUDENT_ID + " INTEGER UNIQUE,"
            + COL_SIG_STUDENT_ROLE + " TEXT,"
            + COL_SIG_GROUP_ID + " TEXT)";


    // query for creating WFS (Weeks for student) table
    public static final String QUERY_CREATE_WFS_TABLE = "create table "
            + WFS_TABLE_NAME + " ("
            + COL_WFS_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_WFS_WEEK_NUM + " INTEGER,"
            + COL_WFS_STUDENT_ID + " INTEGER,"
            + COL_WFS_ATTENDANCE + " INTEGER,"
            + COL_WFS_CONTRIBUTION + " TEXT,"
            + COL_WFS_ADDITIONAL_NOTES + " TEXT,"
            + COL_WFS_GROUP_ID + " TEXT)";


    // query for creating WFG (Weeks for group) table
    public static final String QUERY_CREATE_WFG_TABLE = "create table "
            + WFG_TABLE_NAME + " ("
            + COL_WFG_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_WFG_WEEK_NUM + " INTEGER,"
            + COL_WFG_GROUP_ID + " TEXT,"
            + COL_WFG_TASK + " TEXT,"
            + COL_WFG_TASK_COMPLETED + " INTEGER,"
            + COL_WFG_PROGRESS + " TEXT,"
            + COL_WFG_NOTES + " TEXT,"
            + COL_WFG_ISSUES + " TEXT,"
            + COL_WFG_WEEK_MARKED + " INTEGER)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_STUDENTS_TABLE);
        db.execSQL(QUERY_CREATE_PROJECTS_TABLE);
        db.execSQL(QUERY_CREATE_GROUPS_TABLE);
        db.execSQL(QUERY_CREATE_CLIENTS_TABLE);
        db.execSQL(QUERY_CREATE_SIG_TABLE);
        db.execSQL(QUERY_CREATE_WFS_TABLE);
        db.execSQL(QUERY_CREATE_WFG_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + PROJECTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GROUPS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SIG_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WFS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WFG_TABLE_NAME);
        onCreate(db);
    }


    // helper functions to insert data into tables

    public boolean insertProjectData(String title,String id,String tutor,String client,String unit){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PROJECTS_TITLE, title);
        contentValues.put(COL_PROJECTS_ID, id);
        contentValues.put(COL_PROJECTS_TUTOR, tutor);
        contentValues.put(COL_PROJECTS_CLIENT, client);
        contentValues.put(COL_PROJECTS_UNIT, unit);
        long result = db.insert(PROJECTS_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertGroupData(String id,String projectID,int numOfWeeks, String startDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GROUPS_ID, id);
        contentValues.put(COL_GROUPS_PROJECT_ID, projectID);
        contentValues.put(COL_GROUPS_NUM_OF_WEEKS, numOfWeeks);
        contentValues.put(COL_GROUPS_START_DATE, startDate);
        long result = db.insert(GROUPS_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertStudentData(int studentId,String firstName,String lastName, String grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STUDENTS_ID, studentId);
        contentValues.put(COL_STUDENTS_FIRSTNAME, firstName);
        contentValues.put(COL_STUDENTS_LASTNAME, lastName);
        contentValues.put(COL_STUDENTS_GRADE, grade);
        long result = db.insert(STUDENTS_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertClientData(String fullName,String projectId,String phone, String email, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CLIENTS_FULL_NAME,fullName);
        contentValues.put(COL_CLIENTS_PROJECT_ID,projectId);
        contentValues.put(COL_CLIENTS_PHONE,phone);
        contentValues.put(COL_CLIENTS_EMAIL,email);
        contentValues.put(COL_CLIENTS_ADDRESS,address);
        long result = db.insert(CLIENTS_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertStudentWeek(int weekNum, int studentID, int attend, String contribution, String notes, String groupID, int weekMarked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_WFS_WEEK_NUM, weekNum);
        contentValues.put(COL_WFS_STUDENT_ID, studentID);
        contentValues.put(COL_WFS_ATTENDANCE, attend);
        contentValues.put(COL_WFS_CONTRIBUTION, contribution);
        contentValues.put(COL_WFS_ADDITIONAL_NOTES, notes);
        contentValues.put(COL_WFS_GROUP_ID, groupID);

        long result = db.insert(WFS_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertGroupWeek(int weekNum, String groupID, String task, int taskCompleted, String progress, String notes, String issues, int weekMarked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_WFG_WEEK_NUM, weekNum);
        contentValues.put(COL_WFG_GROUP_ID, groupID);
        contentValues.put(COL_WFG_TASK, task);
        contentValues.put(COL_WFG_TASK_COMPLETED, taskCompleted);
        contentValues.put(COL_WFG_PROGRESS, progress);
        contentValues.put(COL_WFG_NOTES, notes);
        contentValues.put(COL_WFG_ISSUES, issues);
        contentValues.put(COL_WFG_WEEK_MARKED, weekMarked);

        long result = db.insert(WFG_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    public boolean insertStudentInGroup(int studentId,String role,String groupId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SIG_STUDENT_ID, studentId);
        contentValues.put(COL_SIG_STUDENT_ROLE, role);
        contentValues.put(COL_SIG_GROUP_ID, groupId);
        long result = db.insert(SIG_TABLE_NAME, null, contentValues);
        if(result < 0) return false;
        return true;
    }


    // helper function to update tables data

    public boolean updateProjectData(String title,String id,String tutor,String client,String unit){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PROJECTS_TITLE, title);
        contentValues.put(COL_PROJECTS_ID, id);
        contentValues.put(COL_PROJECTS_TUTOR, tutor);
        contentValues.put(COL_PROJECTS_CLIENT, client);
        contentValues.put(COL_PROJECTS_UNIT, unit);
        db.update(PROJECTS_TABLE_NAME, contentValues, "ID = ?",new String [] { id });
        return true;

    }


    public boolean updateStudentGrade(int studentId, String grade){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STUDENTS_GRADE, grade);

        db.update(STUDENTS_TABLE_NAME, contentValues, "ID = ?",new String [] { studentId+"" });
        return true;
    }


    public boolean updateGroupWeek(int weekNum, String groupID, String task, int taskCompleted, String progress, String notes, String issues, int weekMarked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_WFG_WEEK_NUM, weekNum);
        contentValues.put(COL_WFG_GROUP_ID, groupID);
        contentValues.put(COL_WFG_TASK, task);
        contentValues.put(COL_WFG_TASK_COMPLETED, taskCompleted);
        contentValues.put(COL_WFG_PROGRESS, progress);
        contentValues.put(COL_WFG_NOTES, notes);
        contentValues.put(COL_WFG_ISSUES, issues);
        contentValues.put(COL_WFG_WEEK_MARKED, weekMarked);

        long result = db.update(WFG_TABLE_NAME, contentValues, COL_WFG_GROUP_ID + " = ? AND " + COL_WFG_WEEK_NUM + " = ?", new String[]{groupID, weekNum+""});
        if(result < 0) return false;
        return true;
    }


    public boolean updateStudentWeek(int weekNum, int studentID, int attend, String contribution, String notes, String groupID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_WFS_WEEK_NUM, weekNum);
        contentValues.put(COL_WFS_STUDENT_ID, studentID);
        contentValues.put(COL_WFS_ATTENDANCE, attend);
        contentValues.put(COL_WFS_CONTRIBUTION, contribution);
        contentValues.put(COL_WFS_ADDITIONAL_NOTES, notes);
        contentValues.put(COL_WFS_GROUP_ID, groupID);

        long result = db.update(WFS_TABLE_NAME, contentValues,COL_WFS_GROUP_ID + " = ? AND " + COL_WFS_STUDENT_ID + " = ? AND " + COL_WFS_WEEK_NUM + " = ?", new String[]{groupID,studentID+"", weekNum+""});
        if(result < 0) return false;
        return true;
    }


    public boolean updateGroupDetails(String id,String projectID,int numOfWeeks, String startDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_GROUPS_ID, id);
        contentValues.put(COL_GROUPS_PROJECT_ID, projectID);
        contentValues.put(COL_GROUPS_NUM_OF_WEEKS, numOfWeeks);
        contentValues.put(COL_GROUPS_START_DATE, startDate);
        long result = db.update(GROUPS_TABLE_NAME,contentValues,COL_GROUPS_ID + " = ?",new String[]{ id });
        if(result < 0) return false;
        return true;
    }


    public boolean updateClientData(String fullName,String projectId,String phone, String email, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CLIENTS_FULL_NAME,fullName);
        contentValues.put(COL_CLIENTS_PROJECT_ID,projectId);
        contentValues.put(COL_CLIENTS_PHONE,phone);
        contentValues.put(COL_CLIENTS_EMAIL,email);
        contentValues.put(COL_CLIENTS_ADDRESS,address);
        long result = db.update(CLIENTS_TABLE_NAME, contentValues, "PROJECT_ID = ?",new String [] { projectId });
        if(result < 0) return false;
        return true;
    }


    // helper functions to get different tables data

    public ArrayList<Project> getProjectsList(){
        ArrayList<Project> projectsList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PROJECTS_TABLE_NAME, null);

        if(res.getCount()!=0){
            while(res.moveToNext()){
                Project thisProject = new Project(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5));

                projectsList.add(thisProject);
            }
        }
        return projectsList;
    }


    public Client getClientDetails(String projectId){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+CLIENTS_TABLE_NAME+" where "+COL_CLIENTS_PROJECT_ID+"='"+projectId+"'", null);
        Client client = null;

        if(res.getCount()!=0){
            while(res.moveToNext()){
                client = new Client(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                );
            }
        }
        return client;
    }


    public ArrayList<Group> getGroupsList(){
        ArrayList<Group> groupsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+GROUPS_TABLE_NAME, null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                Group thisGroup = new Group(res.getString(1),
                        res.getString(2),
                        "",
                        "",
                        res.getInt(3),
                        res.getString(4));

                groupsList.add(thisGroup);
            }
        }
        return groupsList;
    }


    public GroupWeek getGroupWeek(String groupId,int weekNum){
        GroupWeek thisWeek = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+WFG_TABLE_NAME+" where "+COL_WFG_GROUP_ID+"='"+groupId+"' and "+COL_WFG_WEEK_NUM+"='"+weekNum+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                thisWeek = new GroupWeek(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getInt(4),
                        res.getString(5),
                        res.getString(7),
                        res.getString(6));
            }
        }
        return thisWeek;
    }


    public StudentWeek getStudentWeek(String groupId, int studentId, int weekNum){
        StudentWeek thisWeek = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+WFS_TABLE_NAME+" where "
                +COL_WFS_GROUP_ID+"='"+groupId+"' and "
                +COL_WFS_STUDENT_ID+"='"+studentId+"' and "
                +COL_WFS_WEEK_NUM+"='"+weekNum+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                thisWeek = new StudentWeek(res.getInt(1),
                        res.getInt(2),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(3));
            }
        }
        return thisWeek;
    }


    public ArrayList<StudentWeek> getAllWeeksForStudent(String groupId, int studentId){

        ArrayList<StudentWeek> studentWeeks = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+WFS_TABLE_NAME+" where "+COL_WFS_GROUP_ID+"='"+groupId+"' and "+COL_WFS_STUDENT_ID+"='"+studentId+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                StudentWeek thisWeek = new StudentWeek(res.getInt(1),
                        res.getInt(2),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(3));

                studentWeeks.add(thisWeek);
            }
        }
        return studentWeeks;
    }


    public ArrayList<Student> getStudentsList(){
        ArrayList<Student> studentsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+STUDENTS_TABLE_NAME, null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                Student thisStudent = new Student(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        "",
                        "",
                        "",
                        "");
                studentsList.add(thisStudent);
            }
        }
        return studentsList;
    }


    public ArrayList<Student> getSearchedStudents(String subString){
        ArrayList<Student> studentsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+STUDENTS_TABLE_NAME, null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                Student thisStudent = new Student(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        "",
                        "",
                        "",
                        "");
                if(res.getString(1).contains(subString)) studentsList.add(thisStudent);
            }
        }
        return studentsList;
    }


    public int getNumOfWeeksMarked(String groupId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+WFG_TABLE_NAME+" where "+COL_WFG_GROUP_ID+"='" + groupId +"'" , null);
        int weeksMarked = 0;
        if(res.getCount()!=0){
            while(res.moveToNext()){
                if(res.getInt(8) == 1) weeksMarked += 1;
            }
        }
        return weeksMarked;
    }


    public Student getStudentDetails(int studentId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+STUDENTS_TABLE_NAME+" where "+COL_STUDENTS_ID+"='"+studentId+"'", null);
        Student thisStudent = null;
        if(res.getCount()!=0){
            while(res.moveToNext()){
                thisStudent = new Student(res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        "",
                        "",
                        "",
                        "");
            }
        }
        return thisStudent;
    }


    public Project getProjectDetails(String projectId){
        Project thisProject = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PROJECTS_TABLE_NAME+" where "+COL_PROJECTS_ID+"='"+projectId+"'", null);

        if(res.getCount()!=0){
            while(res.moveToNext()){
                thisProject = new Project(res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5));
            }
        }
        return thisProject;
    }


    public ArrayList<StudentInGroup> getStudentsInGroup(String groupId){
        ArrayList<StudentInGroup> studentsInGroup = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+SIG_TABLE_NAME+" where "+COL_SIG_GROUP_ID+"='"+groupId+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                StudentInGroup thisStudent = new StudentInGroup(res.getInt(1),
                        getStudentDetails(res.getInt(1)).getFirstName() + " " + getStudentDetails(res.getInt(1)).getLastName(),
                        res.getString(2),
                        res.getString(3));
                studentsInGroup.add(thisStudent);
            }
        }
        return studentsInGroup;
    }


    // helper functions to delete tables data

    public int deleteProjectData(String projectId){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PROJECTS_TABLE_NAME, "ID = ?", new String[] { projectId });
    }


    public int removeStudent(String studentId){

        SQLiteDatabase db = this.getWritableDatabase();
        if(db.delete(STUDENTS_TABLE_NAME, "ID = ?", new String[] { studentId })<=0) return 0;
        if(db.delete(SIG_TABLE_NAME, "STUDENT_ID = ?", new String[] { studentId })<=0) return 0;
        if(db.delete(WFS_TABLE_NAME, "STUDENT_ID = ?", new String[] { studentId })<=0) return 0;
        return 1;
    }


    // other helper functions

    public String getGroupIdForStudent(int studentId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + COL_SIG_GROUP_ID + " from "+SIG_TABLE_NAME+" where "+COL_SIG_STUDENT_ID+"='"+studentId+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                return res.getString(0);
            }
        }
        return "";
    }


    public String getProjectIdForGroup(String groupId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + COL_GROUPS_PROJECT_ID + " from "+GROUPS_TABLE_NAME+" where "+COL_GROUPS_ID+"='"+groupId+"'", null);
        if(res.getCount()!=0){
            while(res.moveToNext()){
                return res.getString(0);
            }
        }
        return "";
    }


    public boolean doesProjectExist(String projectId){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+PROJECTS_TABLE_NAME+" where "+COL_PROJECTS_ID+"='"+projectId+"'", null);
        if(res.getCount()!=0){
            return true;
        }
        return false;
    }


    public boolean isProjectAssociatedWithAGroup(String projectId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+GROUPS_TABLE_NAME+" where "+COL_GROUPS_PROJECT_ID+"='"+projectId+"'", null);
        if(res.getCount()!=0){
            return true;
        }
        return false;
    }
}
