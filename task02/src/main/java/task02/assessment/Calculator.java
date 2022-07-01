package task02.assessment;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Calculator {
    
    private String requestID;
    private String integers;
    private float mean;
    private Socket sock;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    OutputStream os;
    InputStream is;
    private static final String NAME = "NG WEI YANG";
    private static final String EMAIL = "ngwy17@gmail.com";
    private Boolean myBoolean;

    public Calculator(Socket s) {
        this.sock = s;
    }

    public void initializeStreams() throws IOException {
        os = sock.getOutputStream();
        oos = new ObjectOutputStream(os);
        is = sock.getInputStream();
        ois = new ObjectInputStream(is);
    }

    public void read() throws IOException {
        String response = ois.readUTF();
        String[] resp = response.split(" ");
        this.requestID = resp[0];
        this.integers = resp[1];
        findMean();
    }

    public void findMean() throws IOException {
        
        String[] myIntS = integers.split(",");
        int total = 0;
        
        for (int i = 0; i < myIntS.length; i++) {
            int value = Integer.parseInt(myIntS[i]);
            total += value;
        }
        mean = ((float) total) /  myIntS.length;
        System.out.println("My requestID is: " + requestID);
        System.out.println("My String of values are: " + integers);
        System.out.println("Total value is: " + total);
        System.out.println("How many values given: " + myIntS.length);
        System.out.println("The mean value is: " + mean);
        answer();
    }

    public void answer() throws IOException {
        oos.writeUTF(requestID);
        oos.writeUTF(NAME);
        oos.writeUTF(EMAIL);
        oos.writeFloat(mean);
        oos.flush();
    }

    public void closeStreams() throws IOException {
        os.close();
        is.close();
    }

    public void readBoo() {
        try {
            myBoolean = ois.readBoolean();
            if (myBoolean == true) {
                System.out.println("SUCCESS");
            } else if (myBoolean == false) {
                System.out.println("FAILED");
                sock.close();
            }
        } catch (IOException ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
        }
        
    }
    
}
