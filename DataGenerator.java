import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class DataGenerator {
    public static void main(String[] args) {
        int[] dimensions = {1000, 10000, 100000, 1000000};
        generateAndSaveData(dimensions);
    }

    private static void generateAndSaveData(int[] dimensions) {
        Random random = new Random();

        for (int n : dimensions) {
            int[] vector = new int[n];
            for (int i = 0; i < n; i++) {
                vector[i] = random.nextInt(100);
            }

            saveToFile(vector, n, "L");
            saveToFile(vector, n, "S");
            saveToFile(vector, n, "Q");
        }
    }

    private static void saveToFile(int[] vector, int n, String type) {
        String filename = n + type + ".txt";
        long startTime = System.nanoTime();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int value : vector) {
                writer.write(value + "\n");
            }
            long endTime = System.nanoTime();
            long writeTime = endTime - startTime;
            logWriteTime(n, type, writeTime);
            System.out.println("Saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logWriteTime(int n, String type, long writeTime) {
        String logFilename = "write_time.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilename, true))) {
            writer.write("Dimensiunea vectorului: " + n + ", Tip: " + type + ", Timp de scriere: " + writeTime + " ns\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
