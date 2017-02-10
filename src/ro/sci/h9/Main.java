package ro.sci.h9;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Emanuel Pruker
 */
public class Main {

    public static void main(String[] args) {
        Path inPath = Paths.get("src", "input.csv");
        System.out.println("csv exists: " + Files.exists(inPath));

        try (BufferedReader reader = new BufferedReader(new FileReader(inPath.toAbsolutePath().toString()))){

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            SkiRace skiRace = new SkiRace(builder.toString());
            System.out.println(skiRace.printTop3());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
