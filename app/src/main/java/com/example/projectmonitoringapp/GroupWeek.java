package com.example.projectmonitoringapp;

public class GroupWeek {

    private String weekNum;
    private String groupID;
    private String task;
    private int taskCompleted;
    private String progress;
    private String issues;
    private String notes;

    public GroupWeek(String weekNum, String groupID, String task, int taskCompleted, String progress, String issues, String notes) {
        this.weekNum = weekNum;
        this.groupID = groupID;
        this.task = task;
        this.taskCompleted = taskCompleted;
        this.progress = progress;
        this.issues = issues;
        this.notes = notes;
    }

    public String getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(String weekNum) {
        this.weekNum = weekNum;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(int taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
