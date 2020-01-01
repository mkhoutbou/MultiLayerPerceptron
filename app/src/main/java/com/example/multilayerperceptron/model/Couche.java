package com.example.multilayerperceptron.model;

import java.util.ArrayList;
import java.util.List;

public class Couche  {
    public static final String HIDDEN_LAYER = "hidden";
    public static final String INPUT_LAYER = "input";
    public static final String OUTPUT_LAYER = "output";
    private String type;
    private List<Neurone> neurones;
    private int nbNeurone;
    private int indice;
    private Reseau reseau;

    public Couche(int taille,Reseau reseau) {
        this.reseau = reseau;
        this.nbNeurone = taille;
        neurones = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbNeurone() {
        return nbNeurone;
    }

    public Couche getNext(){
        return reseau.getCouche(indice+1);
    }
    public Couche getPrevious(){
        return reseau.getCouche(indice - 1);
    }

    public Neurone getNeurone(int indice) {
        return neurones.get(indice);
    }
    public void forward(){
        if (!type.equals(INPUT_LAYER)){
            for (int i = 0; i < nbNeurone; i++) {
                neurones.get(i).forward();
            }
        }
        if (!type.equals(OUTPUT_LAYER)) getNext().forward();
    }

    public void backward(){
        if (!getType().equals(INPUT_LAYER)){
            for (int i = 0; i < nbNeurone; i++) {
                neurones.get(i).backward();
            }
            getPrevious().backward();
        }

    }
}
