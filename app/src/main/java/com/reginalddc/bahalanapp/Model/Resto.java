package com.reginalddc.bahalanapp.Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by reginalddc on 03/04/2017.
 */

public class Resto {
    private JSONArray obj;
    private JSONObject obj1;
    private static int[] restoId;
    private static String[] restoName;
    private static int selectedRestoId;
    private static String selectedRestoName;
    private static String selectedRestoHours;
    private static String selectedRestoBest;
    private static String selectedRestoPrice;
    private static String selectedRestoContact;
    private static String selectedRestoVisit;
    private static String selectedRestoDetailedLoc;
    private static String selectedRestoLongitude;
    private static String selectedRestoLatitude;
    private static int selectedRestoRating;
    private static String selectedRestoImage;

    public Resto() {}

    public Resto(JSONArray obj) { this.obj = obj; }

    public Resto(JSONObject obj1) { this.obj1 = obj1; }

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

    public static String getSelectedRestoName() {
        return selectedRestoName;
    }

    public static void setSelectedRestoName(String selectedRestoName) {
        Resto.selectedRestoName = selectedRestoName;
    }

    public static String getSelectedRestoHours() {
        return selectedRestoHours;
    }

    public static void setSelectedRestoHours(String selectedRestoHours) {
        Resto.selectedRestoHours = selectedRestoHours;
    }

    public static String getSelectedRestoBest() {
        return selectedRestoBest;
    }

    public static void setSelectedRestoBest(String selectedRestoBest) {
        Resto.selectedRestoBest = selectedRestoBest;
    }

    public static String getSelectedRestoPrice() {
        return selectedRestoPrice;
    }

    public static void setSelectedRestoPrice(String selectedRestoPrice) {
        Resto.selectedRestoPrice = selectedRestoPrice;
    }

    public static String getSelectedRestoContact() {
        return selectedRestoContact;
    }

    public static void setSelectedRestoContact(String selectedRestoContact) {
        Resto.selectedRestoContact = selectedRestoContact;
    }

    public static String getSelectedRestoVisit() {
        return selectedRestoVisit;
    }

    public static void setSelectedRestoVisit(String selectedRestoVisit) {
        Resto.selectedRestoVisit = selectedRestoVisit;
    }

    public static String getSelectedRestoDetailedLoc() {
        return selectedRestoDetailedLoc;
    }

    public static void setSelectedRestoDetailedLoc(String selectedRestoDetailedLoc) {
        Resto.selectedRestoDetailedLoc = selectedRestoDetailedLoc;
    }

    public static String getSelectedRestoLongitude() {
        return selectedRestoLongitude;
    }

    public static void setSelectedRestoLongitude(String selectedRestoLongitude) {
        Resto.selectedRestoLongitude = selectedRestoLongitude;
    }

    public static String getSelectedRestoLatitude() {
        return selectedRestoLatitude;
    }

    public static void setSelectedRestoLatitude(String selectedRestoLatitude) {
        Resto.selectedRestoLatitude = selectedRestoLatitude;
    }

    public static int getSelectedRestoRating() {
        return selectedRestoRating;
    }

    public static void setSelectedRestoRating(int selectedRestoRating) {
        Resto.selectedRestoRating = selectedRestoRating;
    }

    public static String getSelectedRestoImage() {
        return selectedRestoImage;
    }

    public static void setSelectedRestoImage(String selectedRestoImage) {
        Resto.selectedRestoImage = selectedRestoImage;
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

    public void specificRestoDataRetrieval(){
        try {
            selectedRestoName = obj1.getString("resto_name");
            selectedRestoHours = obj1.getString("opening_hours");
            selectedRestoBest = obj1.getString("best_seller");
            selectedRestoPrice = obj1.getString("price_range");

            if(obj1.getString("contact_us") != "null")
                selectedRestoContact = obj1.getString("contact_us");
            else
                selectedRestoContact = "None";

            selectedRestoVisit = obj1.getString("visit_us");
            selectedRestoDetailedLoc = obj1.getString("detailed_location");
            selectedRestoLongitude = obj1.getString("geo_long_loc");
            selectedRestoLatitude = obj1.getString("geo_lat_loc");
            selectedRestoRating = obj1.getInt("rating");
            selectedRestoImage = obj1.getString("image_url");

        } catch (Exception e) {}
    }
}
