package com.superstepa.bot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.jar.JarEntry;


//Here be dragons

public class JarCompiler{
    String version = "1.0.0";
    String author = "RobotCompiler";
    String filename = "output.jar";
    String main_class = "CompiledBot";


    public int compileFile(String fileToCompile){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        return compiler.run(null, null, null, fileToCompile);
    }

    public void createJar(){
        File target = new File(main_class+".class");
        Manifest manifest = new Manifest();
        Attributes attr = manifest.getMainAttributes();
        attr.put(Attributes.Name.MANIFEST_VERSION, version);
        attr.put(new Attributes.Name("Main-Class"),main_class);

        BufferedInputStream in = null;
        try{
            JarOutputStream joutput = new JarOutputStream(new FileOutputStream(filename), manifest);
            joutput.putNextEntry(new JarEntry(target.getPath()));
            in = new BufferedInputStream(new FileInputStream(target));
            byte[] buffer = new byte[1024];
            while (true){
                int count = in.read(buffer);
                if (count == -1)
                    break;
                joutput.write(buffer, 0, count);
            }
            joutput.closeEntry();
            joutput.close();
        }
        catch (Exception e){

        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch (Exception e){

                }
            }
        }
    }

}
