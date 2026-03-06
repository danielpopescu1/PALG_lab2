import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static void main(String[] args) {
        int[] dimensions = {1000, 10000, 100000, 1000000};
        measureExecutionTime(dimensions);
    }

    private static void measureExecutionTime(int[] dimensions) {
        String[] types = {"L", "S", "Q"};
        for (int n : dimensions) {
            for (String type : types) {
                String filename = n + type + ".txt";
                long executionTime = readDataFromFile(filename);
                logExecutionTime(n, type, executionTime);
            }
        }
    }

    private static long readDataFromFile(String filename) {
        long startTime = System.nanoTime();

        List<Integer> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void logExecutionTime(int n, String type, long executionTime) {
        String logFilename = "execution_time_log.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilename, true))) {
            writer.write("Dimensiunea vectorului: " + n + ", Tip: " + type + ", Timp de execuție: " + executionTime + " ns\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
