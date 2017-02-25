package ro.sci.h9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Emanuel Pruker
 */
public class Main {

    public static void main(String[] args) {

        SkiRace skiRace = new SkiRace();
        Path inPath = Paths.get("src", "input.csv");
        System.out.println("csv exists: " + Files.exists(inPath));

        try (BufferedReader reader = new BufferedReader(new FileReader(inPath.toAbsolutePath().toString()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                skiRace.addResult(line);
            }

            System.out.println(skiRace.printTop3());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
