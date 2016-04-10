package com.superstepa.bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;


//The spaghetti is spilling out of my pockets. I'll need to rework this later.
public class CommandParser{
    private static final Pattern[] PATTERNS ={
        Pattern.compile("(CLICK) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(RIGHTCLICK) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(GOTO) ([0-9]*) ([0-9]*)"),
        Pattern.compile("(WAIT) ([0-9]*)"),
        Pattern.compile("(TYPE) ([^\n]*)"),
        Pattern.compile("(ENTER)")
    };

    //Map initialization has to take place inside a method.
    public static Map<String,String> commands = createMap();
    private static Map<String, String> createMap(){
        Map<String, String> result = new HashMap<>();
        result.put("CLICK","clickPoint");
        result.put("RIGHTCLICK","clickPoint");
        result.put("GOTO","mouseMove");
        result.put("WAIT","delay");
        result.put("TYPE","typeString");
        result.put("ENTER","enter");
        return result;
    }

    //Proof of concept, will change later.
    public static String parseCommand(String command, String[] args){
        String arg_str = "";
        for (String arg : args) {
            arg_str = String.format("%s,%s", arg_str, arg);
        }
        arg_str = (arg_str.length() > 0) ? arg_str.substring(1) : arg_str; //Remove the leading comma if there are arguments.
        return String.format("bot.%s(%s);\n", commands.get(command), arg_str);
    }

    public static String[] readLines (String filename){
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = br.readLine()) != null){
                result.add(processLine(line));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return result.toArray(new String[result.size()]);
    }
    /**
     *Separates a given line into the command and the parameters.
     *
     */
    private static String processLine(String line){
        for (Pattern p: PATTERNS){
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
                return parseCommand(command, strArgs);
            }
        }
    return "";
    }
}
