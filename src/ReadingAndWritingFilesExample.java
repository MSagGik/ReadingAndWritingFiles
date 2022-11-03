import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadingAndWritingFilesExample {
    public static void main(String[] args) {
        try {
            // считывание файла
            Scanner sc = new Scanner(new File("resources/planet.txt"));
            Map<String, Integer> popularPlanet = new HashMap<String, Integer>();
            int maxWeightPlanet = 0;
            String nameMaxWeightPlanet = null;
            while(sc.hasNext()) {
                String id = sc.nextLine();
                String planet = sc.nextLine();
                int weight = Integer.parseInt(sc.nextLine());
                // подсчёт планет
                int count = 1;
                if(popularPlanet.containsKey(planet)) // добавление счётчика количества упоминаний каждой планеты в файле
                    count = popularPlanet.get(planet) + 1;
                popularPlanet.put(planet, count);
                // вычисление самой массивной планеты
                if(weight > maxWeightPlanet) {
                    maxWeightPlanet = weight;
                    nameMaxWeightPlanet = planet;
                }
            }
            sc.close();
            // запись файла
            FileWriter fw = new FileWriter(new File("resources/resultPlanet.txt"));
            // вычисление счётчика самой часто встречающейся планеты
            int popularObject = Collections.max(popularPlanet.values());
            // поиск по показанию счётчика названия планеты
            String favoriteObject = "";
            for (Map.Entry entry : popularPlanet.entrySet())
                if(entry.getValue().equals(popularObject))
                    favoriteObject = (String)entry.getKey();
            fw.write("Одна из наиболее часто встречающихся планет " + favoriteObject + "\n");
            fw.write("Самая массивная планета " + nameMaxWeightPlanet + ", масса которой равна " + maxWeightPlanet + "e+19");
            fw.close();
            // вывод в консоль всех планет
            System.out.println("\nВсе планеты:");
            for (Map.Entry entry : popularPlanet.entrySet()) {
                System.out.println(entry.getKey() + " встречается " + entry.getValue() + " раз");
            }
        } catch (IOException e) {
            System.out.println("Файл не найден!");
        }
    }
}