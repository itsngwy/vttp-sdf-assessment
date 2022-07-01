package task02.assessment;

import java.io.IOException;
import java.net.Socket;

public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        System.err.println("Connecting to the server at port 80");
        Socket sock = new Socket("task02.chuklee.com", 80);
        System.out.println("Connected...");

        Calculator cal = new Calculator(sock);
        cal.initializeStreams();
        cal.read();
        cal.readBoo();
        cal.closeStreams();
        sock.close();
    }
}
