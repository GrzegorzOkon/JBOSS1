package okon;

import okon.config.ConfigurationParamsReader;
import okon.exception.AppException;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

public class JBOSS1App {
    static final List<Job> messages = ConfigurationParamsReader.readConfigurationParams(new File("./config/config.xml"));;

    public static void main (String args[]) {
        JBOSS1App jboss1_app = new JBOSS1App();
        jboss1_app.print(messages);
    }

    static String getJarFileName() {
        String path = JBOSS1App.class.getResource(JBOSS1App.class.getSimpleName() + ".class").getFile();
        path = path.substring(0, path.lastIndexOf('!'));
        path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf('.'));
        return path;
    }

    private void print(List<Job> content) {
        printToConsole(content);
        printToFile(content);
    }

    private void printToConsole(List<Job> content) {
        for(Job job : content) {
            for (String row: job.getResults()) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    private void printToFile(List<Job> content) {
        try (Writer out = new FileWriter(new java.io.File(JBOSS1App.getJarFileName() + ".txt"))) {
            for (Job job : content) {
                for (String row: job.getResults()) {
                    out.write(row);
                    out.write(System.getProperty("line.separator"));
                }
                out.write(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}