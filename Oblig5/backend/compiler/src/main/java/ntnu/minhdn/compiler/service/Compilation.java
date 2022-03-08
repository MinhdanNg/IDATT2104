package ntnu.minhdn.compiler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Compilation {
    Logger logger = LoggerFactory.getLogger(Compilation.class);

    public void writeToFile(String code) throws IOException {
        FileWriter writer = new FileWriter("file/main.py");
        writer.write(code);
        writer.close();
    }

    public String compile(String code) throws IOException {
        writeToFile(code);

        logger.info("Run docker build");
        var build = Runtime.getRuntime().exec("docker build ./file -t python");
        logger.info(new String(build.getErrorStream().readAllBytes()));

        logger.info("Run docker run");
        var run = Runtime.getRuntime().exec("docker run --rm python");

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(run.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(run.getErrorStream()));

        String output = "";
        String s = null;
        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            output += s;
        }

        // Read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
            output += s;
        }


        return output;
    }
}
