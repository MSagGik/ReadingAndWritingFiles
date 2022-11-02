import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadingAndWritingFiles {
    public static void main(String[] args) {
        ArrayList<Integer> metrics = new ArrayList<>();
        int max, min;
        double medium;
        try {
            // считывание файла
            Scanner sc = new Scanner(new File("resources//input.txt"));
            while(sc.hasNextInt())
                metrics.add(sc.nextInt());
            sc.close();
            // вычисления
            max = Collections.max(metrics);
            min = Collections.min(metrics);
            int sum = 0;
            for(int x: metrics)
                sum += x;
            medium = (double) sum / metrics.size();
            // запись в файл
            FileWriter fw = new FileWriter(new File("resources//output.txt"), true); // true - до запись, false - перезапись
            fw.write("Максимальное число " + max + "\n");
            fw.write("Минимальное число " + min + "\n");
            fw.write("Среднее арифметическое значение " + medium + "\n");
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Файл для записи не удалось открыть");
        }
    }
}