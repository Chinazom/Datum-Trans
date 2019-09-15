package com.example.datumtransfomationgoogle;

import Jama.Matrix;

/*TODO
 * transformtion of XYZ using the four techniques supported by RTCM
 * implement a method the will convert lat long to cartesian
 * */
public class TransformationTechnique {
    projectLatLong pLatLong = new projectLatLong();
    static double x_Coordinate;
    static double y_Coordinate;
    static double Z_Coordinate;



    public void methods(double TrX, double TrY, double TrZ, double scal, double RoX, double RoY, double RoZ, String technique, double X, double Y, double height) {
   /*     double lat = 50.7978187947533;
        double longitude = 4.35922042080428;*/

        String linear = "linear expression Helmert";
        String strict = "Strict formula Helmert standard";
        String abridged = "Abridged Molodenski";
        String molodenski = "Molodenski-Bakedas";
        /*  double Xo = pLatLong.getX();
        double Yo = pLatLong.getY();
        double Zo = 0;

        double Xo = 4027893.6719;
        double Yo = 307045.9064;
        double Zo = 4919475.1704;*/

        double[][] XYZ = {{X}, {Y}, {height}};
        double Xt = TrX * 0.001;
        double Yt = TrY * 0.001;
        double Zt = TrZ * 0.001;
        double[][] changeInXYZ = {{Xt}, {Yt}, {Zt}};

        double Rz = RoZ * Math.PI / (3600000 * 180);
        double Rx = RoX * Math.PI / (3600000 * 180);
        double Ry = RoY * Math.PI / (3600000 * 180);
        Matrix translation = new Matrix(changeInXYZ);
        Matrix coordinate = new Matrix(XYZ);

        if (linear.equals(technique)) {
            double m = 1 + scal * Math.pow(10, (-9));
            double[][] rotation = {{m, (+Rz * m), (-Ry * m)}, {(-Rz * m), m, (+Rx * m)}, {(+Ry * m), (-Rx * m), m}};

            //Creating Matrix Objects with arrays
            Matrix rotatn = new Matrix(rotation);
            //Calculate Solved Matrix
            Matrix scalWRotAndCoord = rotatn.times(coordinate);
            //double [][]result = ans.getArray();
            Matrix transformedCoordinate = scalWRotAndCoord.plus(translation);
            x_Coordinate = transformedCoordinate.get(0, 0);
            y_Coordinate = transformedCoordinate.get(1, 0);
            Z_Coordinate = transformedCoordinate.get(2, 0);

        } else if (strict.equals(technique)) {


            double[][] Rze = {{Math.cos(RoZ), Math.sin(RoZ), 0}, {(Math.sin(-RoZ)), Math.cos(RoZ), 0}, {0, 0, 1}};
            double[][] Rye = {{Math.cos(RoY), 0, Math.sin(-RoY)}, {0, 1, 0}, {Math.sin(RoY), 0, Math.cos(RoY)}};
            double[][] Rxe = {{1, 0, 0}, {0, Math.cos(RoX), Math.sin(RoX)}, {0, Math.sin(-RoX), Math.cos(RoX)}};
            double m = 1 + scal * Math.pow(10, (-9));

            double[][] scalAndRotation = {{((Math.cos(RoY) * Math.cos(RoZ)) * m), ((-Math.cos(RoX) * Math.sin(RoZ) + (Math.sin(RoX) * Math.sin(RoY) * Math.cos(RoZ))) * m), (m * ((Math.sin(RoX) * Math.sin(RoZ)) + (Math.cos(RoX) * Math.sin(RoY) * Math.cos(RoZ))))}, {(m * (Math.cos(RoY) * Math.sin(RoZ))), (m * ((Math.cos(RoX) * Math.cos(RoZ)) + (Math.sin(RoX) * Math.sin(RoY) * Math.sin(RoZ)))), (m * ((-Math.sin(RoX) * Math.cos(RoZ)) + (Math.cos(RoX) * Math.sin(RoY) * Math.sin(RoZ))))}, {(-Math.sin(RoY) * m), (m * (Math.sin(RoX) * Math.cos(RoY))), (m * (Math.cos(RoX) * Math.cos(RoY)))}};
            Matrix scaleRotation = new Matrix(scalAndRotation);
            Matrix rotaionWithCoord = scaleRotation.times(coordinate);
            Matrix transformedCoordinate = translation.plus(rotaionWithCoord);
            x_Coordinate = transformedCoordinate.get(0, 0);
            y_Coordinate = transformedCoordinate.get(1, 0);
            Z_Coordinate = transformedCoordinate.get(2, 0);
        } else if (abridged.equals(technique)) {

            double lat = X;
            double longitude = Y;

            double aSource = 6378137.0;
            double bSource = 6356752.314245;
            double aTarget = 6378137.0;
            double bTarget = 6356752.314140;
            double eSquare = ((aSource * aSource) - (bSource * bSource)) / (aSource * aSource);
            double sumDenomi = (eSquare * Math.sin(Math.pow(lat, 2)));
            double da = aTarget - aSource;
            double flatteningSource = (aSource / (aSource - bSource));
            double flatteningTarget = (aTarget / (aTarget - bTarget));
            double N = (aSource / (Math.sqrt(1 - (eSquare * Math.sin(Math.pow(lat, 2))))));
            double M = ((aSource * (1 - eSquare)) / (Math.pow((Math.sqrt(1 - sumDenomi)), 3)));
            double df = ((1 / flatteningTarget) - (1 / flatteningSource));
            double deltaLat = (-TrX * (Math.sin(lat) * Math.cos(longitude)) - (TrY * (Math.sin(lat) * Math.sin(longitude))) + (TrZ * Math.cos(lat)) + (((M * (aSource / bSource) + N * (bSource / aSource)) * df + (((N * eSquare) / aSource) * da)) * (Math.sin(2 * lat) / 2))) / (M + height);
            double deltaLong = ((-TrX * Math.sin(longitude)) + (TrY * Math.cos(longitude))) / ((N + height) * Math.cos(lat));
            double deltaHeight = (TrX * (Math.cos(lat) * Math.cos(longitude))) + (TrY * (Math.cos(lat) * Math.sin(longitude))) + (TrZ * Math.sin(lat)) + ((df * N * bSource / aSource) * (Math.sin(lat) * Math.sin(lat))) - (da * aSource / N);
            x_Coordinate = lat + deltaLat;
            y_Coordinate = longitude + deltaLong;
            Z_Coordinate = height + deltaHeight;
        } else if (molodenski.equals(technique)) {

            double Xp = 0;
            double Yp = 0;
            double Zp = 0;
            double[][] datumInTargetAndCentroid = {{X - Xp}, {Y - Yp}, {height - Zp}};
            Matrix xyztarAndCentroid = new Matrix(datumInTargetAndCentroid);
            double[][] XYZcentroid = {{Xp}, {Yp}, {Zp}};
            Matrix centroidCoord = new Matrix(XYZcentroid);
            double m = 1 + scal * Math.pow(10, (-9));
            double[][] rotation = {{m, (+Rz * m), (-Ry * m)}, {(-Rz * m), m, (+Rx * m)}, {(+Ry * m), (-Rx * m), m}};
            //Creating Matrix Objects with arrays
            Matrix rotatn = new Matrix(rotation);
            Matrix scalRotDcentroid = rotatn.times(xyztarAndCentroid);
            Matrix ScRotDcentroidCentroid = scalRotDcentroid.plus(centroidCoord);
            Matrix coordTarget = ScRotDcentroidCentroid.plus(translation);
            x_Coordinate = coordTarget.get(0, 0);
            y_Coordinate = coordTarget.get(1, 0);
            Z_Coordinate = coordTarget.get(2, 0);
        }


    }
    public static String getCoordinate() {
       String trans=  "Latitude: " + x_Coordinate + "\nLongitude: " + y_Coordinate + "\nHeight: " + Z_Coordinate;
        return trans;
    }

}
