package com.superstepa.bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;

public class CommandParser{
    private static Pattern[] patterns ={
        Pattern.compile("(CLICK) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(GOTO) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(WAIT) ([0-9]*)"),
        Pattern.compile("(TYPE) ([^\n]*)"),
        Pattern.compile("(ENTER)")
    };

    //Map initialization has to take place inside a method.
    public static Map<String,String> commands = createMap();
    private static Map<String, String> createMap(){
        Map<String, String> result = new HashMap<String,String>();
        result.put("CLICK","clickPoint");
        result.put("GOTO","mouseMove");
        result.put("WAIT","delay");
        result.put("TYPE","typeString");
        result.put("ENTER","enter");
        return result;
    }

    //Proof of concept, will change later.
    public static void parseCommand(String command, String[] args){
        String arg_str = "";
        for (int i = 0; i < args.length; i++){
            arg_str = String.format("%s,%s",arg_str,args[i]);
        }
        arg_str = (arg_str.length() > 0) ? arg_str.substring(1) : arg_str; //Remove the leading comma if there are arguments.
        System.out.printf("%s(%s)\n", commands.get(command), arg_str);
    }

    public static void readLines (String filename){
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
    /**
     *Separates a given line into the command and the parameters.
     *
     */
    private static void processLine(String line){
        for (Pattern p: patterns){
            Matcher matcher = p.matcher(line);
            while (matcher.find()){
                int count = matcher.groupCount();
                String[] strArgs = new String[count-1];
                 /*
                    All regex patterns have at least 1 group capture
                    Therefore the first group capture will always be the command itself
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
