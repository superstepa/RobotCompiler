package bot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.BufferedReader;
import java.io.FileReader;

public class CommandParser{
    private static final String CLICK = "CLICK ([0-9]*) ([0-9]*)";
    private static final String GOTO = "GOTO ([0-9]*) ([0-9]*)";
    private static final String WAIT = "WAIT ([0-9]*)";
    private static final String TYPE = "TYPE ([^\n]*)";
    private static final String ENTER = "(ENTER)";
    private StringBuffer source = null;


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
        System.out.println(line);
    }

}
