package HashTables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

class Hash {
    public static void main(String[] args) {
        HashZip hz = new HashZip("postnummer.csv");
        Zip3 z = new Zip3("postnummer.csv");

        for (Zip3.Node n: z.data){
            if (n == null){
                continue;
            }

            HashZip.Node item = hz.find(n.code);

            int code = Integer.parseInt(n.code.replaceAll("\\s", ""));

            if ((code != item.code) || (!n.region.equals(item.region)) ||(!n.pop.equals(item.pop)) ){
                System.out.println("YOU FUCKED UP!!!");
                break;
            }
            System.out.println("Good...");
        }
        System.out.println("Complete!!!");
    }
}


/*
        static int[] codes = new int[9675];

        load("postnummer.csv");

        for (int i = 0; i < 10; i++) {
            collisions((i+1)*10_000);
        }

        collisions(12_345);
        collisions(13_513);
        collisions(13_600);
        collisions(14_000);


public static void load(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(Hash.class.getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;
            int i = 0;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                codes[i] = Integer.parseInt(row[0].replaceAll("\\s", ""));
                i++;
            }
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
        }
    }

    public static void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < codes.length; i++) {
            Integer index = codes[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.println("   Mod: " + mod);
        for (int i = 0; i < 10; i++) {
            System.out.printf("%15s%1d", "Collisions #", i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%16d", cols[i]);
        }
        System.out.println("\n/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");
    }*/

