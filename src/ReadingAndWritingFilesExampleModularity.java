import java.io.*;
import java.util.*;

public class ReadingAndWritingFilesExampleModularity {
    public static void main(String[] args) {
        Map<String, Integer> objectPlanet = readData("resources/planet.txt");
        if (objectPlanet != null) {
            // вычисление счётчика самой часто встречающейся планеты
            int popularObject = Collections.max(objectPlanet.values());
            // поиск по показанию счётчика названия планеты
            Optional<String> result1 = objectPlanet.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(popularObject))
                    .map(Map.Entry::getKey)
                    .findFirst();
            // вычисление самой массивной планеты
            Optional<String> result2 = objectPlanet.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(-1))
                    .map(Map.Entry::getKey)
                    .findFirst();
            if (result1.isPresent()&&result2.isPresent()) printResult(result1.get(),result2.get());
        }
    }

    public static Map<String, Integer> readData(String fileName) {
        try {
            Scanner sc = new Scanner(new File("resources/planet.txt"));
            Map<String, Integer> popularPlanet = new HashMap<String, Integer>();
            int maxWeightPlanet = 0;
            String nameMaxWeightPlanet = null;
            String planet = null;
            int weight = 0;
            while(sc.hasNext()) {
                String id = sc.nextLine();
                if(sc.hasNext()) planet = sc.nextLine();
                else throw new Exception();
                if(sc.hasNext()) weight = Integer.parseInt(sc.nextLine());
                else throw new Exception();
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
            popularPlanet.put(nameMaxWeightPlanet + ", которая имеет массу " + maxWeightPlanet + "e+19", -1);
            sc.close();
            return popularPlanet;
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден!");
            return null;
        } catch (Exception e){
            System.out.println("Входной файл заполнен с ошибкой!");
            return null;
        }
    }

    public static void printResult(String favoriteObject, String namePlanet) {
        try {
            // запись файла
            FileWriter fw = new FileWriter(new File("resources/resultPlanetModularity.txt"));
            fw.write("Одна из наиболее часто встречающихся планет " + favoriteObject + "\n");
            fw.write("Самая массивная планета " + namePlanet);
            fw.close();
        } catch(IOException e){
            System.out.println("Файл не найден!");
        }
    }
}