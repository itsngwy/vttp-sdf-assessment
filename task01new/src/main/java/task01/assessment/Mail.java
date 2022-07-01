package task01.assessment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Mail {

    private Map<String, String> dataSet = new HashMap<>();
    private List<String> oldMail = new ArrayList<>();
    private String first;
    private String sec;
    private String third;
    private String fourth;
    private String[] myKeys;
    BufferedReader br;
    Reader reader;
    private String txtFile;
    String[] fields;

    public Mail (String myTxtFile) throws FileNotFoundException, IOException {
        this.txtFile = myTxtFile;
        String line = "";
        reader = new FileReader(txtFile);
        br = new BufferedReader(reader);
        while ((line = br.readLine()) != null) {
            oldMail.add(line.replaceAll("_{2,}", ""));
        }
        //System.out.println(oldMail);
    }

    public void read(String data) {
        if ((null == data) || (data.trim().length() <= 0))
            return;

        fields = data.split(",");
        first = fields[0];
        sec = fields[1];
        third = fields[2];
        fourth = fields[3];

        for (int i = 0; i < myKeys.length; i++) {
            // Eg it puts the name as the key and then make a 
            dataSet.put(myKeys[i], fields[i]);
            //System.out.println(myKeys[i] + fields[i]);
        }

        updateFile();

    }

    public void setKey(String firstRow) {

        myKeys = firstRow.split(",");

    }

    public void updateFile() {

        for (int i = 0; i < oldMail.size(); i++) {
            String txtLine = oldMail.get(i);
            //System.out.println(txtLine);
            String[] words = txtLine.split(" ");

            for (int j = 0; j < words.length; j++) {
                for (String key: dataSet.keySet()) {
                    if (words[j].equals(key + ",")) {
                        words[j] = dataSet.get(key);
                    }
                    if (words[j].equals(key + ".")) {
                        words[j] = dataSet.get(key);
                    }
                    if (words[j].equals(key)) {
                        words[j] = dataSet.get(key);
                        //System.out.println(words[j]);
                    }
                }
            }
            txtLine = String.join(" ", words);
            System.out.println(txtLine);
            
        }
        System.out.println();
    }
}
