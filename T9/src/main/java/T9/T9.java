package T9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;

public class T9 {
    Node root;

    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }
    }

    public T9() {
        root = new Node();
        loadWords();
    }

    private void loadWords() {
        String fileName = "kelly.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(Paths.get(this.getClass().getClassLoader().getResource(fileName).toURI()).toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                addWord(line.toCharArray());
            }
            //System.out.println(" Successfully loaded " + fileName);
        } catch (Exception e) {
            System.out.println(" file " + fileName + " not found");
            System.out.println(e);
        }
    }

    private void addWord(char[] arr) {

        Node curr = root;
        Node[] list = curr.next;

        for (char ch : arr) {
            int code = charToCode(ch);

            if (list[code] == null) {
                list[code] = new Node();
            }

            curr = list[code];
            list = curr.next;
        }
        curr.valid = true;
    }

    public ArrayList<String> getWords(String seq) {

        ArrayList<String> list = new ArrayList<>();
        getWord("", root, list, seq);
        return list;
    }

    private void getWord(String str, Node node, ArrayList<String> list, String seq) {

        if (node.valid && seq.isEmpty()) {
            list.add(str);
            return;
        }

        if(seq.isEmpty()){
            return;
        }

        Node[] next = node.next;
        int code = (Integer.parseInt("" + seq.charAt(0)) - 1) * 3;

        for (int i = code; i < code + 3; i++) {
            Node n = next[i];
            if (n == null) {
                continue;
            }
            getWord(str + codeToChar(i), n, list, seq.substring(1));

        }

    }


    public int charToCode(char ch) {
        int code = (int) Character.toLowerCase(ch);

        if (code == 229) {
            return 24;
        }
        if (code == 228) {
            return 25;
        }
        if (code == 246) {
            return 26;
        }

        if (code < 113) {
            return code - 97;
        } else if (code < 119) {
            return code - 98;
        } else {
            return code - 99;
        }
    }

    public char codeToChar(int code) {

        if (code == 24) {
            return 'å';
        }
        if (code == 25) {
            return 'ä';
        }
        if (code == 26) {
            return 'ö';
        }

        if (code < 16) {
            return (char) (code + 97);
        } else if (code < 21) {
            return (char) (code + 98);
        } else {
            return (char) (code + 99);
        }

    }

}
