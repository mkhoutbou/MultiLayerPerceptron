package com.example.multilayerperceptron.model;

import java.util.ArrayList;
import java.util.List;

public class Reseau {
    public static double N = 0;
    public static double MI = 0;
    private List<Couche> couches;
    private List<Double> inputData;
    public static List<Double> outputData;
    private int nbCouche;

    public Reseau(int nbCouche) {
        this.nbCouche = nbCouche;
        couches = new ArrayList<>();
        inputData = new ArrayList<>();
        outputData = new ArrayList<>();
    }

    public void initialiserPoids(){
        for (int i = 0; i < nbCouche; i++) {
            couches.get(i).initialiserPoids();
        }
    }

    public static double fonctionActiation(double a) {
        double result = 1/(1 + Math.exp(-a));
        return result;
    }

    public Couche getCouche(int i) {
        return couches.get(i);
    }

    private void forward(){
        Couche inputLayer = getInputLayer();
        for (int i = 0; i < inputLayer.getNbNeurone(); i++) {
            inputLayer.getNeurone(i).setSortie(inputData.get(i));
        }
        inputLayer.forward();
    }

    private void backward(){
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

}
