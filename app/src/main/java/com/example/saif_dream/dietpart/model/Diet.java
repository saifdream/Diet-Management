package com.example.saif_dream.dietpart.model;

import java.util.ArrayList;

/**
 * Created by saif-dream on 4/19/2016.
 */
public class Diet {
    private Integer id;
    private String dietType;
    private String dietMenu;
    private String dietDate;
    private String dietTime;
    private String dailyAlarm;
    private String reminder;

    public Diet() {}

    public Diet(Integer id, String dietType, String dietMenu, String dietDate, String dietTime, String dailyAlarm, String reminder) {
        this.id = id;
        this.dietType = dietType;
        this.dietMenu = dietMenu;
        this.dietDate = dietDate;
        this.dietTime = dietTime;
        this.dailyAlarm = dailyAlarm;
        this.reminder = reminder;
    }

    public Diet(String dietType, String dietMenu, String dietDate, String dietTime, String dailyAlarm, String reminder) {
        this.dietType = dietType;
        this.dietMenu = dietMenu;
        this.dietDate = dietDate;
        this.dietTime = dietTime;
        this.dailyAlarm = dailyAlarm;
        this.reminder = reminder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public String getDietMenu() {
        return dietMenu;
    }

    public void setDietMenu(String dietMenu) {
        this.dietMenu = dietMenu;
    }

    public String getDietDate() {
        return dietDate;
    }

    public void setDietDate(String dietDate) {
        this.dietDate = dietDate;
    }

    public String getDietTime() {
        return dietTime;
    }

    public void setDietTime(String dietTime) {
        this.dietTime = dietTime;
    }

    public String getDailyAlarm() {
        return dailyAlarm;
    }

    public void setDailyAlarm(String dailyAlarm) {
        this.dailyAlarm = dailyAlarm;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public static ArrayList<Diet> getMockDiet() {

        ArrayList<Diet> listDiet = new ArrayList<Diet>();

        listDiet.add(new Diet(1,"Breakfast", "Egg","19/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(2,"Launch", "Rice, Egg","19/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(3,"Breakfast", "Apple","20/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(4,"Launch", "Rice, Dal","20/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(5,"Breakfast", "Egg","21/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(6,"Launch", "Rice, Egg","21/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(7,"Breakfast", "Apple","22/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(8,"Launch", "Rice, Dal","22/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(9,"Breakfast", "Egg","23/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(10,"Launch", "Rice, Egg","23/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(11,"Breakfast", "Apple","24/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(12,"Launch", "Rice, Dal","24/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(13,"Breakfast", "Egg","25/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(14,"Launch", "Rice, Egg","25/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(15,"Breakfast", "Apple","26/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(16,"Launch", "Rice, Dal","26/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(17,"Breakfast", "Egg","27/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(18,"Launch", "Rice, Egg","27/04/2016","2:30 am","Yes","No"));
        listDiet.add(new Diet(19,"Breakfast", "Apple","28/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(20,"Launch", "Rice, Dal","28/04/2016","2:30 am","Yes","No"));

        return listDiet;
    }

    public static ArrayList<Diet> getTodayDiet() {

        ArrayList<Diet> listDiet = new ArrayList<Diet>();

        listDiet.add(new Diet(1,"Breakfast", "Egg","19/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(2,"Launch", "Rice, Egg","19/04/2016","2:30 am","Yes","No"));

        return listDiet;
    }

    public static ArrayList<Diet> getUpComingDiet() {

        ArrayList<Diet> listDiet = new ArrayList<Diet>();

        listDiet.add(new Diet(3,"Breakfast", "Apple","20/04/2016","8:30 am","Yes","No"));
        listDiet.add(new Diet(4,"Launch", "Rice, Dal","20/04/2016","2:30 am","Yes","No"));

        return listDiet;
    }
}
