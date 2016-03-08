package com.superstepa.bot;

import com.superstepa.bot.SimpleBot;
import com.superstepa.bot.CommandParser;

public class BotRunner{
    public static void main(String[] args){
        try{
            SimpleBot robot  = new SimpleBot();
            CommandParser.readFile(args[0]);
        } catch (Exception e){
            System.out.printf("Exception: %s", e);
        }
    }
}
