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

    public DataXY(Context context){
        this.context = context;
        xy = new XY();
    }

    public void init(int fileResource,int sizeOfX,int sizeOfY){
        this.sizeOfX = sizeOfX;
        this.sizeOfY = sizeOfY;
        inputStream = context.getResources().openRawResource(fileResource);
        file = new InputStreamReader(inputStream);
        reader = new BufferedReader(file);
    }
    public boolean hasNext() {
        String line = null;
        try {
            if ((line = reader.readLine()) == null) return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] row = line.split("\\s+");
        List<Double> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        for (int i = 0; i < sizeOfX; i++) {
            x.add(Double.parseDouble(row[i]));
        }
        for (int i = sizeOfX; i < sizeOfY + sizeOfX; i++) {
            y.add(Double.parseDouble(row[i]));
        }
        xy.setX(x);
        xy.setY(y);
        return true;
    }

    public XY getNext(){
        return xy;
    }
}
