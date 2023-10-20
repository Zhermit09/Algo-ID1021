package HashTables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

public class Zip3 {

    public Node[] data;
    int length;

    public class Node {

        public String code;
        String region;
        Integer pop;

        public Node(String code, String region, Integer pop) {
            this.code = code;
            this.region = region;
            this.pop = pop;
        }

    }

    public Zip3(String fileName) {

        data = new Node[100_000];

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                int i = Integer.parseInt(row[0].replaceAll("\\s", ""));
                data[i] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }

            length = data.length;
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

    }

    public Node find(String code) {
        return data[Integer.parseInt(code.replaceAll("\\s", ""))];
    }
}
