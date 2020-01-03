package com.example.multilayerperceptron.Perceptron;

import com.jjoe64.graphview.series.DataPoint;
import java.util.ArrayList;
import java.util.List;

public class Reseau {
    public static int N = 0;
    public static double MI = 0.0;
    public static double alphat = 0.0;
    private List<Couche> couches;
    private List<Double> inputData;
    private List<Double> outputData;
    private DataPoint[] errorSeries;
    public static List<Double> errors;
    private int nbCouche;

    public Reseau(int nbCouche) {
        this.nbCouche = nbCouche;
        errors = new ArrayList<>();
        couches = new ArrayList<>();
        errorSeries = new DataPoint[120];
        for (int i = 0; i < nbCouche; i++) {
            couches.add(new Couche(this,i));
        }
    }

    public void init(){
        for (int i = 0; i < nbCouche; i++) {
            couches.get(i).init();
        }
        for (int i = 0; i < getOutputLayer().getNbNeurone(); i++) {
            errors.add(0.0);
        }
    }

    private void calculateError(){
        Couche outPutLayer = getOutputLayer();
        for (int i = 0; i < outPutLayer.getNbNeurone(); i++) {
            errors.set(i,outputData.get(i) - outPutLayer.getNeurone(i).getSortie());
        }
    }

    public static double fonctionActiation(double a) {
        double result = 1/(1 + Math.exp(-a));
        return result;
    }

    public Couche getCouche(int i) {
        return couches.get(i);
    }

    public void forward(){
        Couche inputLayer = getInputLayer();
        for (int i = 0; i < inputLayer.getNbNeurone(); i++) {
            inputLayer.getNeurone(i).setSortie(inputData.get(i));
        }
        N++;
        inputLayer.forward();
    }

    public void backward(){
        calculateError();
        errorSeries[N-1]= new DataPoint(N,(errors.get(0) + errors.get(1))/2);
        getOutputLayer().backward();
    }

    private Couche getOutputLayer() {
        return couches.get(nbCouche - 1);
    }

    private Couche getInputLayer() {
        return couches.get(0);
    }

    public void setInputData(List<Double> inputData) {
        this.inputData = inputData;
    }

    public void setOutputData(List<Double> outputData) {
        this.outputData = outputData;
    }

    public DataPoint[] getErrorSeries() {
        return errorSeries;
    }
}
