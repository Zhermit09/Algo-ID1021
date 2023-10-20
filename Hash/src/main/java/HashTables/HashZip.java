package HashTables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

public class HashZip {

    HashTable2 hush;
    public class Node {

        Integer code;
        String region;
        Integer pop;

        public Node(String code, String region, Integer pop) {
            this.code = Integer.parseInt(code.replaceAll("\\s", ""));
            this.region = region;
            this.pop = pop;
        }

    }

    public HashZip(String fileName) {

        hush = new HashTable2(20_000);

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int i = Integer.parseInt(row[0].replaceAll("\\s", ""));
                hush.add(i, new Node(row[0], row[1], Integer.valueOf(row[2])));
            }
            System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

    }

    public int find(String code) {
        return hush.find(Integer.parseInt(code.replaceAll("\\s", "")));
    }
}
