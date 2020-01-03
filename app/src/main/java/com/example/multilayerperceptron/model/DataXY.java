package com.example.multilayerperceptron.model;

import android.content.Context;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataXY{

    private InputStream inputStream;
    private InputStreamReader file;
    private BufferedReader reader;
    private Context context;
    private XY xy;
    private int sizeOfX;
    private int sizeOfY;
    private int position = -1;
    private List<XY> xor;

    public DataXY(Context context){
        this.context = context;
        xor = new ArrayList<>();
        xy = new XY();
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        xy = new XY();
        x.add(0.05);
        x.add(0.05);
        y.add(0.05);
        xy.setX(x);
        xy.setY(y);
        xor.add(xy);
        xy = new XY();
        x.add(0.05);
        x.add(0.95);
        y.add(0.95);
        xy.setX(x);
        xy.setY(y);
        xor.add(xy);
        xy = new XY();
        x.add(0.95);
        x.add(0.95);
        y.add(0.05);
        xy.setX(x);
        xy.setY(y);
        xor.add(xy);
        xy = new XY();
        x.add(0.95);
        x.add(0.05);
        y.add(0.95);
        xy.setX(x);
        xy.setY(y);
        xor.add(xy);

    }

    public void init(int fileResource,int sizeOfX,int sizeOfY){
        this.sizeOfX = sizeOfX;
        this.sizeOfY = sizeOfY;
        inputStream = context.getResources().openRawResource(fileResource);
        file = new InputStreamReader(inputStream);
        reader = new BufferedReader(file);
    }
    public boolean hasNext() {
       /* String line = null;
        try {
            if ((line = reader.readLine()) == null) return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] row = line.split("\\s+");
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        double temperature = Double.parseDouble(row[0]);
        temperature = Math.cos(temperature/2);
        x.add(temperature);
        for (int i = 1; i < sizeOfX; i++) {
            x.add(Double.parseDouble(row[i]));
        }
        x.add(1.0);
        for (int i = sizeOfX; i < sizeOfY + sizeOfX; i++) {
            y.add(Double.parseDouble(row[i]));
        }
        xy.setX(x);
        xy.setY(y);
        return true;*/
       position++;
       return (position<4);
    }

    public XY getNext(){
        //return xy;
        return xor.get(position);

    }
}
