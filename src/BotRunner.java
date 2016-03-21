package com.superstepa.bot;

import com.superstepa.bot.CommandParser;
import com.superstepa.bot.TemplateGenerator;


public class BotRunner{
    public static void main(String[] args){
        try{
            TemplateGenerator gen = new TemplateGenerator();
            JarCompiler compiler = new JarCompiler();
            gen.writeFile(gen.replaceLines(args[0],"//This is a comment\n        bot.typeString(\"THIS IS ATEST\");"));
            compiler.compileFile(gen.defaultFileName);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
