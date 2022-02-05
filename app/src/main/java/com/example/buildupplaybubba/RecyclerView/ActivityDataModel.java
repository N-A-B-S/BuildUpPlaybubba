package com.example.buildupplaybubba.RecyclerView;

public class ActivityDataModel {
    private String dbID;
    private String date;
    private String activity;
    //private String stepCount;
    private String activityTitle;
    private String caloriesBurnt;

    public ActivityDataModel(String date, String activityTitle, String caloriesBurnt, String dbID, String activity) {
        this.dbID = dbID;
        this.date = date;
        this.activity = activity;
        //this.stepCount = stepCount;
        this.activityTitle = activityTitle;
        this.caloriesBurnt = caloriesBurnt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
       this.date = date;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

//    public String getStepCount() {
//        return stepCount;
//    }
//
//    public void setStepCount(String stepCount) {
//        this.stepCount = stepCount;
//    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }
}
