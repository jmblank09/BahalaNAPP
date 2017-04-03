package com.reginalddc.bahalanapp.Model;

import org.json.JSONArray;

/**
 * Created by reginalddc on 03/04/2017.
 */

public class Resto {
    private JSONArray obj;
    private static int[] restoId;
    private static String[] restoName;
    private static int selectedRestoId;

    public Resto() {}

    public Resto(JSONArray obj) { this.obj = obj; }

    public static int[] getRestoId() {
        return restoId;
    }

    public static void setRestoId(int[] restoId) {
        Resto.restoId = restoId;
    }

    public static String[] getRestoName() {
        return restoName;
    }

    public static void setRestoName(String[] restoName) {
        Resto.restoName = restoName;
    }

    public static int getSelectedRestoId() {
        return selectedRestoId;
    }

    public static void setSelectedRestoId(int selectedRestoId) {
        Resto.selectedRestoId = selectedRestoId;
    }

    public void dataRetrieval() {
        try {
            restoId = new int[0];
            restoName = new String[0];

            if(obj.length() > 0) {
                restoId = new int[obj.length()];
                restoName = new String[obj.length()];

                for(int i = 0; i < obj.length(); i++) {
                    restoId[i] = obj.getJSONObject(i).getInt("resto_id");
                    restoName[i] = obj.getJSONObject(i).getString("resto_name");
                }
            }
        } catch (Exception e) {}
    }
}
