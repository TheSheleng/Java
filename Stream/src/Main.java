import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        try {
            Task_1("test.txt");
            Task_2(Arrays.asList(1, 2, 3, 4, 1, 2, 1, 3, 1, 4, 4, 4, 4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Task_1(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        List<String> filteredLines = lines.stream()
                .filter(line -> line.startsWith("A"))
                .toList();

        filteredLines.forEach(System.out::println);
    }

    //
    public static void Task_2(List<Integer> numbers)  {
        int mostFrequent = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        System.out.println("Елемент, який зустрічається найчастіше: " + mostFrequent);
    }
}