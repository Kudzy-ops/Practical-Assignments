import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotesDemo {
    public static void main(String[] args) {
        Path file = Path.of("notes.txt");
        Scanner in = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(in.nextLine());
        }

        try {
            Files.write(file, lines);                       // create or overwrite
            System.out.println("Saved " + lines.size() + " lines to " + file);

            System.out.println("--- file contents ---");
            for (String line : Files.readAllLines(file)) {  // read them back
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
