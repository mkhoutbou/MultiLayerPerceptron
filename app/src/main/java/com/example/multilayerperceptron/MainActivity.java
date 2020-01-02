package com.example.multilayerperceptron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.multilayerperceptron.model.Couche;
import com.example.multilayerperceptron.model.DataXY;
import com.example.multilayerperceptron.model.Model;
import com.example.multilayerperceptron.model.Reseau;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MaiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataXY data = Model.getModel(this).gettrainingData();
        data.init(R.raw.diagnosis,6,2);
        Reseau reseau = new Reseau(2);
        Reseau.MI = 0.5;
        reseau.getCouche(0).setNbNeurone(6).setType(Couche.INPUT_LAYER);
        reseau.getCouche(1).setNbNeurone(2).setType(Couche.OUTPUT_LAYER);
        reseau.init();
        Perceptron perceptron = new Perceptron();
        perceptron.setReseau(reseau);
        perceptron.learn(Model.getModel(this).gettrainingData());
        TextView textView = findViewById(R.id.parent);
    }
}
