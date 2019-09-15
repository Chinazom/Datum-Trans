package com.example.datumtransfomationgoogle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
    String ITRF_2005 = "-2.0 -0.9 -4.7 0.94 0.00 0.00 0.00 2000.0,0.3 0.0 0.0 0.00 0.00 0.00 0.00";
    String ITRF_2000 = "-1.9 -1.7 -10.5 1.34 0.00 0.00 0.00 2000.0,0.1 0.1 -1.8 0.08 0.00 0.00 0.00";
    String ITRF_1997 = "4.8 2.6 -33.2 2.92 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1996 = "4.8 2.6 -33.2 2.92 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1994 = "4.8 2.6 -33.2 2.92 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1993 = "-24.0 2.4 -38.6 3.41 -1.71 -1.48 -0.30 2000.0,-2.8 -0.1 -2.4 0.09 -0.11 -0.19 0.07";
    String ITRF_1992 = "12.8 4.6 -41.2 2.21 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1991 = "24.8 18.6 -47.2 3.61 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1990 = "22.8 14.6 -63.2 3.91 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ITRF_1989 = "27.8 38.6 -101.2 7.31 0.00 0.00 0.06 2000.0,0.1 -0.5 -3.2 0.09 0.00 0.00 0.02";
    String ETRF_2014 = "-1.6 -1.9 -2.4 0.02 1.785 11.151 -16.170 2010,0.0 0.0 0.1 -0.03 0.085 0.531 -0.770";
    String ETRF_2000 = "53.1 50.3 -76.5 2.14 1.701 10.290 -16.632 2010,0.1 0.1 -1.8 0.08 0.081 0.490 -0.792";


    List<JSONObject> epochList = new ArrayList<>();
    JSONObject jsonObject = new JSONObject();

    public Parameters() {

        try {
            jsonObject.put("ITRF_2005", ITRF_2005);
            jsonObject.put("ITRF_2000", ITRF_2000);
            jsonObject.put("ITRF_1997", ITRF_1997);
            jsonObject.put("ITRF_1996", ITRF_1996);
            jsonObject.put("ITRF_1994", ITRF_1994);
            jsonObject.put("ITRF_1993", ITRF_1993);
            jsonObject.put("ITRF_1992", ITRF_1992);
            jsonObject.put("ITRF_1991", ITRF_1991);
            jsonObject.put("ITRF_1990", ITRF_1990);
            jsonObject.put("ITRF_1989", ITRF_1989);
            jsonObject.put("ETRF_2014", ETRF_2014);
            jsonObject.put("ETRF_2000", ETRF_2000);
            epochList.add(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getEpochParm(String epoch) {

        String epochPram = "";

        for (int i = 0; i < epochList.size(); i++) {
            JSONObject object = epochList.get(i);

            try {
                epochPram = object.getString(epoch);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return epochPram;
    }
}

