package bot;

import bot.SimpleBot;
import bot.CommandParser;

public class BotRunner{
    public static void main(String[] args){
        try{
            SimpleBot robot  = new SimpleBot();
            CommandParser parser = new CommandParser("TESt", robot);
        } catch (Exception e){
            System.out.printf("Exception: %s", e);
        }
    }
}
