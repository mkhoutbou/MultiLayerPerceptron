package com.example.multilayerperceptron;

import android.util.Log;

import com.example.multilayerperceptron.model.DataXY;
import com.example.multilayerperceptron.model.Reseau;
import com.example.multilayerperceptron.model.XY;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Perceptron {
    
    private Reseau reseau;

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

    public void learn(DataXY trainingData){

        while (trainingData.hasNext()){
            XY xy = trainingData.getNext();
            reseau.setInputData(xy.getX());
            reseau.setOutputData(xy.getY());
            reseau.forward();
            reseau.backward();
        }
        Log.i(TAG, "learn: eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    }
}
