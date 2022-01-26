package com.example.moviecatalogue.ui.until;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArrayHelper {

    public static List<MovieResultsItem> parseJsonToArrayList(String responseJson) {
        List<MovieResultsItem> itemResponse = new ArrayList<>();

        try {
            JSONObject responseObject = new JSONObject(responseJson);
            JSONArray responseArray = responseObject.getJSONArray("results");

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject response = responseArray.getJSONObject(i);

                String title = response.getString("title");
            }

        } catch (JSONException e) {

        }

        return itemResponse;
    }

}
