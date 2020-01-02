package com.example.multilayerperceptron.model;

import java.util.ArrayList;
import java.util.List;

public class Neurone {
    private double sortie;
    private double gradient;
    private List<Double> sommeDesJeNeSaisPas;
    private int indice;
    private Couche couche;
    private List<Double> poids;

    public Neurone(int indice, Couche couche) {
        this.indice = indice;
        this.couche = couche;
        poids = new ArrayList<>();
        sommeDesJeNeSaisPas = new ArrayList<>();
    }

    public List<Double> getPoids() {
        return poids;
    }


    public double getSortie() {
        return sortie;
    }

    public void setSortie(double sortie) {
        this.sortie = sortie;
    }

    public void initialiserPoids(){
        for (int i = 0; i < couche.getNext().getNbNeurone(); i++) {
            poids.add(-0.5 + Math.random());
        }
    }

    public void initialiserSommeDesJeNeSaisPas(){
        for (int i = 0; i < couche.getPrevious().getNbNeurone(); i++) {
            sommeDesJeNeSaisPas.add(0.0);
        }
    }
    public void forward(){
        Couche previousLayer = couche.getPrevious();
        double a = 0;
        for (int i = 0; i < previousLayer.getNbNeurone(); i++) {
            Neurone neurone = previousLayer.getNeurone(i);
            a += neurone.getSortie()*neurone.getPoids().get(indice);
        }
        sortie = Reseau.fonctionActiation(a);

    }
    public double getGradient() {
        return gradient;
    }

    public void calculerGradientETsommeDesJeNeSaisPas(){
        if (couche.getType().equals(Couche.OUTPUT_LAYER)){
            gradient = Reseau.errors.get(indice) * sortie * (1 - sortie);
        }
        else {
            double somme = 0;
            Couche nextLayer = couche.getNext();
            for (int i = 0; i < nextLayer.getNbNeurone(); i++) {
                somme += nextLayer.getNeurone(i).getGradient()*poids.get(i);
            }

            gradient = sortie*(1-sortie)*somme;
        }

        Couche previousLayer = couche.getPrevious();
        for (int i = 0; i < previousLayer.getNbNeurone(); i++) {
            double sommeDes = sommeDesJeNeSaisPas.get(i)  +  gradient*previousLayer.getNeurone(i).getSortie();
            sommeDesJeNeSaisPas.set(i,sommeDes);
        }
    }

    public void backward(){
        if (!couche.getType().equals(Couche.INPUT_LAYER)){
            calculerGradientETsommeDesJeNeSaisPas();
            Couche previousLayer = couche.getPrevious();
            for (int i = 0; i < previousLayer.getNbNeurone(); i++) {
                Neurone neurone = previousLayer.getNeurone(i);
                double poids = neurone.getPoids().get(indice) + Reseau.MI*sommeDesJeNeSaisPas.get(i)/Reseau.N;
                neurone.getPoids().set(indice,poids);
            }
        }
    }

}
