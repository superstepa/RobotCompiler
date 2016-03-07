package bot;

import bot.SimpleBot;
public class CommandParser{
    public CommandParser(String filename, bot.SimpleBot robot){
        bot.SimpleBot robotHost = robot;
        robotHost.delay(2000);
        robotHost.typeString(filename);

    }

}
