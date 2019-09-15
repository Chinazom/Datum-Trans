package com.example.datumtransfomationgoogle;

public class projectLatLong {
    double X;
    double Y;
    public void projectLatLong (double lat, double longitude) {
        double aSource = 6378137.0;
        double bSource = 6356752.314245;
        double eSquare = ((aSource * aSource) - (bSource * bSource)) / (aSource * aSource);
        double N = (aSource / (Math.sqrt(1 - (eSquare * Math.sin(Math.pow(lat, 2))))));
        double latRad = Math.toDegrees(lat);
        double longRad = Math.toDegrees(longitude);
        X =(N * Math.cos(latRad)* Math.cos(longRad));
        Y = (N * Math.cos(latRad)* Math.sin(longRad));
    }
    public double getX (){ return X ;}
    public double getY (){ return Y ;}
}
