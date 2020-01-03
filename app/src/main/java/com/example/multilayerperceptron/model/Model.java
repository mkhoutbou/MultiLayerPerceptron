package com.example.multilayerperceptron.model;

import android.content.Context;



public class Model {

    private static Model model;
    private Context c;
    private DataXY trainingData;
    private DataXY testingData;

    private Model(Context c){
        this.c = c;
        trainingData = new DataXY(c);
        testingData = new DataXY(c);
    }

    public static Model getModel(Context c){

        if (model == null){
            model = new Model(c);
        }

        return model;
    }

    public DataXY getTrainingData(){
        return trainingData;
    }

    public DataXY getTestingData() {
        return testingData;
    }
}
