package com.example.multilayerperceptron.model;

import java.util.ArrayList;
import java.util.List;

public class Reseau {
    public static double N = 0;
    public static double MI = 0;
    private List<Couche> couches;
    private int nbCouche;

    public Reseau(int nbCouche) {
        this.nbCouche = nbCouche;
        couches = new ArrayList<>();
    }

    public static double fonctionActiation(double a) {
        return 0;
    }

    public Couche getCouche(int i) {
        return couches.get(i);
    }

    private void forward(){
        getInputLayer().forward();
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
}
