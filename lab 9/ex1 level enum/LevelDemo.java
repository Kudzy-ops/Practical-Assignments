enum Level { LOW, MEDIUM, HIGH }

public class LevelDemo {

    static void printAdvice(Level level) {
        switch (level) {
            case LOW    -> System.out.println("LOW: no action needed");
            case MEDIUM -> System.out.println("MEDIUM: keep an eye on it");
            case HIGH   -> System.out.println("HIGH: act immediately");
        }
    }

    public static void main(String[] args) {
        for (Level level : Level.values()) {      // values() returns all constants in order
            printAdvice(level);
        }
    }
}
