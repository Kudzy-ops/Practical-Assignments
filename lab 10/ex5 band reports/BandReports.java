import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

record Learner(String name, int roll, double gpa) {
    static Learner fromCsv(String line) {
        String[] p = line.split(",");
        return new Learner(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]));
    }
}

public class BandReports {

    static String band(Learner l) {
        if (l.gpa() >= 3.5) return "A";
        if (l.gpa() >= 3.0) return "B";
        return "C";
    }

    public static void main(String[] args) throws IOException {
        Path csv = Path.of("learners.csv");
        Files.write(csv, List.of(
            "Ada,1,3.9", "Kuda,2,3.2", "Rudo,3,3.9",
            "Farai,4,3.1", "Chipo,5,2.6", "Tariro,6,3.5"));

        // LOAD: CSV lines -> Learner objects
        List<Learner> learners = Files.readAllLines(csv).stream()
            .filter(line -> !line.isBlank())
            .map(Learner::fromCsv)
            .toList();

        // GROUP: band letter -> learners in that band (TreeMap keeps A, B, C in order)
        Map<String, List<Learner>> byBand = learners.stream()
            .collect(Collectors.groupingBy(BandReports::band, TreeMap::new, Collectors.toList()));

        // WRITE: one report file per band
        for (Map.Entry<String, List<Learner>> entry : byBand.entrySet()) {
            String band = entry.getKey();
            List<Learner> members = entry.getValue();

            List<String> lines = new ArrayList<>();
            lines.add("Band " + band + ": " + members.size() + " record(s)");
            for (Learner l : members) {
                lines.add(l.name() + " - GPA " + l.gpa());
            }

            Path out = Path.of("report_" + band + ".txt");
            Files.write(out, lines);
            System.out.println("Wrote " + out + ": " + members.size() + " record(s)");
        }

        System.out.println("--- report_A.txt ---");
        Files.readAllLines(Path.of("report_A.txt")).forEach(System.out::println);
    }
}
