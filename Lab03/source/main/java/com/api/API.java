package com.api;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;

public class API {
    private String response;
    Map<String, String> continents;
    Map<String, String> adminDivisions;

    public API(){
        continents = new HashMap<>();
        continents.put("Africa", "geonames:AF");
        continents.put("Afryce", "geonames:AF");
        continents.put("Antarctica", "geonames:AN");
        continents.put("Antarktydzie", "geonames:AN");
        continents.put("Asia", "geonames:AS");
        continents.put("Azji", "geonames:AS");
        continents.put("Europe", "geonames:EU");
        continents.put("Europie", "geonames:EU");
        continents.put("North America", "geonames:NA");
        continents.put("Ameryce Polnocnej", "geonames:NA");
        continents.put("Oceania", "geonames:OC");
        continents.put("Oceanii", "geonames:OC");
        continents.put("South America", "geonames:SA");
        continents.put("Ameryce Poludniowej", "geonames:SA");

        adminDivisions = new HashMap<>();
        adminDivisions.put("Poland", "iso_alpha2:PL");
        adminDivisions.put("Polsce", "iso_alpha2:PL");
        adminDivisions.put("United States", "iso_alpha2:US");
        adminDivisions.put("Stanach Zjednoczonych", "iso_alpha2:US");
        adminDivisions.put("United Kingdom", "iso_alpha2:GB");
        adminDivisions.put("Wielkiej Brytanii", "iso_alpha2:GB");
        adminDivisions.put("Sweden", "iso_alpha2:SE");
        adminDivisions.put("Szwecji", "iso_alpha2:SE");
        adminDivisions.put("Spain", "iso_alpha2:ES");
        adminDivisions.put("Hiszpanii", "iso_alpha2:ES");
        adminDivisions.put("Germany", "iso_alpha2:DE");
        adminDivisions.put("Niemczech", "iso_alpha2:DE");
    }
    public String getResponse(String firstHttpPart, String secondHttpPart, String name) {
        try {
            response =  Unirest.get("https://api.teleport.org/api/" + firstHttpPart + "/" + name + "/" + secondHttpPart)
                    .asString()
                    .getBody();
            response = response.substring(response.length() - 4);
            response = response.substring(response.indexOf(":") + 1, response.length() - 1);
            return response;
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getContinentId(String name){
        return continents.get(name);
    }

    public String getCountryId(String name){
        return adminDivisions.get(name);
    }
}
