package com.example.multilayerperceptron.Perceptron;

import java.util.ArrayList;
import java.util.List;

public class Couche  {
    public static final String HIDDEN_LAYER = "hidden";
    public static final String INPUT_LAYER = "input";
    public static final String OUTPUT_LAYER = "output";
    private String type = HIDDEN_LAYER;
    private List<Neurone> neurones;
    private int nbNeurone;
    private int indice;
    private Reseau reseau;

    public Couche(Reseau reseau,int indice) {
        this.reseau = reseau;
        this.indice = indice;
        neurones = new ArrayList<>();
    }



    public String getType() {
        return type;
    }

    public void init(){
        Neurone neurone ;
        int i;

        for (i = 0; i < nbNeurone; i++) {
            neurone = new Neurone(i,this);
            if (!type.equals(OUTPUT_LAYER)){
                neurone.initialiserPoids();
            }
            if (!type.equals(INPUT_LAYER)) neurone.initialiserSommeDesJeNeSaisPas();
            neurones.add(neurone);
        }

        if (!type.equals(OUTPUT_LAYER)){
            neurone = new Neurone(nbNeurone,this);
            neurone.setSortie(1.0);
            neurone.initialiserPoids();
            neurones.add(neurone);
        }

    }

    public Couche setType(String type) {
        this.type = type;
        return this;
    }

    public int getNbNeurone() {
        return nbNeurone;
    }

    public Couche setNbNeurone(int nbNeurone) {
        this.nbNeurone = nbNeurone;
        return this;
    }

    public Couche getNext(){
        return reseau.getCouche(indice+1);
    }
    public Couche getPrevious(){
        return reseau.getCouche(indice - 1);
    }

    public Neurone getNeurone(int i) {
        return neurones.get(i);
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
            for (int i = 0; i < nbNeurone ; i++) {
                neurones.get(i).backward();
            }
            getPrevious().backward();
        }

    }
}
