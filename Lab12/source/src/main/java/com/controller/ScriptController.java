package com.controller;

import javax.script.*;
import java.io.*;
import java.util.Scanner;

public class ScriptController {
    private ScriptEngineManager factory;
    private ScriptEngine engine;
    private Invocable invocable;
    private int[][] input, nextStep;
    private FileReader script;

    public ScriptController(){
        this.factory = new ScriptEngineManager();
        this.engine = factory.getEngineByName("nashorn");
    }

    public void setScript(String fileName){
        try {
            this.script = new FileReader(fileName);
        } catch (FileNotFoundException e) { e.printStackTrace(); }
    }

    public void evalScript(){
        try {
            this.engine.eval(script);
        } catch (final ScriptException se) { se.printStackTrace(); }
    }

    public void invokeNextStep(int rows, int columns){
        this.invocable = (Invocable) engine;
        try {
            Object result = invocable.invokeFunction("nextStep", (Object) input, (Object) nextStep, rows, columns);
            deepCopy((int[][]) result, input);
            deepCopy((int[][]) result, nextStep);
        } catch (ScriptException | NoSuchMethodException e) { e.printStackTrace(); }
    }

    public void loadInput(String fileName, int rows, int columns) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        this.input = new int[rows][columns];
        this.nextStep = new int[rows][columns];
        while(scanner.hasNextLine()) {
            for (int i=0; i< input.length; i++) {
                String[] line = scanner.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    input[i][j] = Integer.parseInt(line[j]);
                    nextStep[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
    }

    public void deepCopy(int[][] sourceArray, int[][] destinationArray){
        for(int i = 0; i < sourceArray.length; i++){
            for(int j = 0; j < sourceArray[i].length; j++){
                destinationArray[i][j] = sourceArray[i][j];
            }
        }
    }

    public int[][] getInput(){
        return this.input;
    }
}
