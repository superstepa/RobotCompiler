package com.superstepa.bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;

public class CommandParser{
    public static Pattern[] patterns ={
        Pattern.compile("(CLICK) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(GOTO) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(WAIT) ([0-9]*)"),
        Pattern.compile("(TYPE) ([^\n]*)"),
        Pattern.compile("(ENTER)")
    };

    //public enum patterns {CLICK, GOTO, WAIT, TYPE, ENTER};

    public static void parseCommand(String command, String[] args){
        String arguments = Arrays.toString(args);
        switch(command){
            case "CLICK":
                System.out.println("Clicking at " + arguments);
                break;
            case "GOTO":
                System.out.println("Going to " + arguments);
                break;
            case "WAIT":
                System.out.println("Waiting for " + arguments);
                break;
            case "TYPE":
                System.out.println("Typing " + arguments);
                break;
            }
        }

    public static void readFile (String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null){
                processLine(line);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void processLine(String line){
        for (Pattern p: patterns){
            Matcher matcher = p.matcher(line);
            while (matcher.find()){
                int count = matcher.groupCount();
                String[] strArgs = new String[count-1];
                 /*
                    All regex patterns have at least 1 group capture
                    Therefore the first group capture will be the command itself
                    Captures are 1 indexed.
                */
                String command = matcher.group(1);
                for (int i=2; i <=count;i++){
                    strArgs[i - 2] = matcher.group(i);
                }
                parseCommand(command, strArgs);
            }
        }
    }
}
