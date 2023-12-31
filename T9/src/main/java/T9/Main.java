package T9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 =  new ArrayList<>();
        ArrayList<String> seq = new ArrayList<>();

        T9 t = new T9();
        load(list);

        for (String s : list) {
            String sequence = "";
            for (int i = 0; i < s.length(); i++) {
                sequence += (t.charToCode(s.charAt(i)) / 3) + 1;
            }
            seq.add(sequence);
        }



        for (int i = 0; i < list.size(); i++) {
            boolean found = false;
            list2 = t.getWords(seq.get(i));

            for (int j = 0; j < list.size(); j++) {
                if (list2.get(j).equals(list.get(i))) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Missing: '" + list.get(i) + "'");
                //break;
            }
            //System.out.println("Good...");
        }
        System.out.println("DONE!!!");


        list2 = t.getWords("752224");
        for (String s: list2){
           System.out.println(s);
        }

        System.out.println();

    }

    static void load(ArrayList<String> list){
        String fileName = "kelly.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(Main.class.getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
            System.out.println(e);
        }
    }
}