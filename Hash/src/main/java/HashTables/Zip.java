package HashTables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

public class Zip {

    Node[] data;
    int length;

    public class Node {

        String code;
        String region;
        Integer pop;

        public Node(String code, String region, Integer pop) {
            this.code = code;
            this.region = region;
            this.pop = pop;
        }

    }

    public Zip(String fileName) {

        data = new Node[10000];

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i] = new Node(row[0], row[1], Integer.valueOf(row[2]));
                i++;
            }

            length = i;
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }

    }

    public Node linearSearch(String code) {
        for (Node n : data) {
            if (n.code.equals(code)) {
                return n;
            }
        }
        return null;
    }

    public Node binarySearch(String code) {
        int start = 0;
        int end = length - 1;

        while (start <= end) {
            int i = (start + end) / 2;
            Node n = data[i];
            int num = Integer.parseInt(n.code.replaceAll("\\s", ""));
            int key = Integer.parseInt(code.replaceAll("\\s", ""));


            if (num > key) {
                end = i - 1;
                continue;
            }

            if (num < key) {
                start = i + 1;
                continue;
            }
            return n;
        }
        return null;
    }
}
