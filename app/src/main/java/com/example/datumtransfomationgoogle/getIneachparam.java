package com.example.datumtransfomationgoogle;

public class getIneachparam {
    public void getparameters(String datum, String technique,String X, String Y,String Z) {
        Parameters param = new Parameters();
        String parameters = param.getEpochParm(datum);
        double Xdouble = Double.parseDouble(X);
        double Ydouble = Double.parseDouble(Y);
        double Zdouble = Double.parseDouble(Z);



        String[] params = parameters.split(",");
        String parameter = params[0];
        String rates = params[1];
        String[] arrOfParm = parameter.split(" ");

        String TX = arrOfParm[0];
        String TY = arrOfParm[1];
        String TZ = arrOfParm[2];
        String scale = arrOfParm[3];
        String RX = arrOfParm[4];
        String RY = arrOfParm[5];
        String RZ = arrOfParm[6];
        String epoch = arrOfParm[7];
        double TrX = Double.parseDouble(TX);
        double TrY = Double.parseDouble(TY);
        double TrZ = Double.parseDouble(TZ);
        double scal = Double.parseDouble(scale);
        double RoX = Double.parseDouble(RX);
        double RoY = Double.parseDouble(RY);
        double RoZ = Double.parseDouble(RZ);


        String[] arrOfRates = rates.split(" ");
        String T1Rate = arrOfRates[0];
        String T2Rate = arrOfRates[1];
        String T3Rate = arrOfRates[2];
        String scaleRate = arrOfRates[3];
        String R1Rate = arrOfRates[4];
        String R2Rate = arrOfRates[5];
        String R3Rate = arrOfRates[6];
        double Tr1Rate = Double.parseDouble(T1Rate);
        double Tr2Rate = Double.parseDouble(T2Rate);
        double Tr3Rate = Double.parseDouble(T3Rate);
        double scalRate =Double.parseDouble(scaleRate);
        double Ro1Rate = Double.parseDouble(R1Rate);
        double Ro2Rate = Double.parseDouble(R2Rate);
        double Ro3Rate = Double.parseDouble(R3Rate);


        TransformationTechnique fourMethods = new TransformationTechnique();
        fourMethods.methods(TrX,TrY,TrZ,scal,RoX,RoY,RoZ,technique,Xdouble,Ydouble,Zdouble);
    }
}
