import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileStats {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("sample.txt");
        Files.write(file, List.of("Java is fun", "Object oriented programming"));

        List<String> lines = Files.readAllLines(file);

        int words = 0;
        int characters = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                words += line.trim().split(" +").length;    // split on runs of spaces
            }
            characters += line.length();
        }

        System.out.println("Lines: " + lines.size());
        System.out.println("Words: " + words);
        System.out.println("Characters (excluding line breaks): " + characters);
    }
}
