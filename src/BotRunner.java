package com.superstepa.bot;

import com.superstepa.bot.CommandParser;
import com.superstepa.bot.TemplateGenerator;

public class BotRunner{
    public static void main(String[] args){
        try{

            TemplateGenerator gen = new TemplateGenerator();
            JarCompiler compiler = new JarCompiler();
            String[] lines = CommandParser.readLines(args[0]);
            String command = "";
            for (String s: lines){
                command = String.format("%s\n%s\n", command, s);
            }
            gen.writeFile(gen.replaceLines("./templates/robot.jtemplate",command));
            compiler.compileFile(gen.defaultFileName);
            compiler.createJar();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
