package com.superstepa.bot;

import java.lang.StringBuilder;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class TemplateGenerator{

    private StringBuilder sourceBuilder;
    private String pattern = "{0}";
    public String defaultFileName = "CompiledBot.java";

    public String replaceLines (String filename, String newText){
        StringBuilder sourceBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null){
                sourceBuilder.append(line).append("\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        int index = sourceBuilder.indexOf(pattern);
        sourceBuilder.replace(index,index+pattern.length(), newText);
        return sourceBuilder.toString();
    }

    public void writeFile(String data, String filename){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(data);
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeFile(String data){
        writeFile(data, defaultFileName);
    }

}
