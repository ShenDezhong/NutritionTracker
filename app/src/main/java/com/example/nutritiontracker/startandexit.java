package com.example.nutritiontracker;

import android.app.Application;
import java.util.*;
import android.app.Activity;

public class startandexit extends Application {

    private List<Activity> activityList = new ArrayList<>();
    private static startandexit instance;
    private startandexit(){}
    public static synchronized startandexit getInstance(){
        if (null == instance){
            instance = new startandexit();
        }
        return instance;
    }

    /*
     *Add Activity into the list
     */
    public void addActivity(Activity activity){
        activityList.add(activity);
    }
    /*
     * Destroy all activities
     */
    public void exit(){
        for(Activity activity: activityList){
            if (activity != null){
                activity.finish();
            }
        }
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
    }


}
