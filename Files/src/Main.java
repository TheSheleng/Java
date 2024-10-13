import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "tipa_file_ahahaha.txt";

        try {
            Task_1(filePath);
            Task_2(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Task_1(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            lines.add("Line: " + i);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();

        System.out.println("Task 1: done in " + filePath);
    }

    public static void Task_2(String filePath) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));

        if (!fileLines.isEmpty()) {
            Random random = new Random();
            int indexToRemove = random.nextInt(fileLines.size());
            System.out.println("Rem line: " + fileLines.get(indexToRemove));
            fileLines.remove(indexToRemove);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String line : fileLines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

            System.out.println("Task 2: done");
        }
        else {
            System.out.println("Empty.");
        }
    }
}