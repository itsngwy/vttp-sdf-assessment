package task01.assessment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main 
{
    public static void main( String[] args )
    {
        String csvFile = args[0];
        String tempFile = args[1];

        Reader reader;
        BufferedReader br;

        try {
            Mail myMail = new Mail(tempFile);
            reader = new FileReader(csvFile);
            br = new BufferedReader(reader);
            System.out.printf("%s file opened\n", csvFile);
            System.out.println();
            
            String data = br.readLine();
            myMail.setKey(data);

            //System.out.println(data);
            while ((data = br.readLine()) != null) {
                myMail.read(data);
            }
            reader.close();

        }  
        catch (FileNotFoundException ex) {
            System.err.printf("Error: %s\n", ex.getMessage());

        } catch (IOException ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
        }
    }
}
