package com.superstepa.bot;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.jar.JarOutputStream;

public class JarCompiler{

    public void compileFile(String filename){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, filename);
    }

}
