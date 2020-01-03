package com.example.multilayerperceptron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.multilayerperceptron.Perceptron.Couche;
import com.example.multilayerperceptron.Perceptron.Perceptron;
import com.example.multilayerperceptron.model.DataXY;
import com.example.multilayerperceptron.model.Model;
import com.example.multilayerperceptron.Perceptron.Reseau;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MaiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataXY trainingData = Model.getModel(this).getTrainingData();
        trainingData.init(R.raw.diagnosis,6,2);
        Reseau reseau = new Reseau(3);
        Reseau.MI = 0.4;
        reseau.getCouche(0).setNbNeurone(6).setType(Couche.INPUT_LAYER);
        reseau.getCouche(1).setNbNeurone(4);
        reseau.getCouche(2).setNbNeurone(2).setType(Couche.OUTPUT_LAYER);
        reseau.init();
        Perceptron perceptron = new Perceptron();
        perceptron.setReseau(reseau);
        perceptron.learn(trainingData);

        GraphView graphView = findViewById(R.id.graph);
        graphView.setVisibility(View.VISIBLE);
        DataPoint[] errorSeries = reseau.getErrorSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(errorSeries);
        try {
            graphView.addSeries(series);
            graphView.getViewport().setScalable(true);
            graphView.getViewport().setScalableY(true);
            graphView.getViewport().setScrollableY(true);
        }catch (IllegalArgumentException e){
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }finally {

        }
    }
}
