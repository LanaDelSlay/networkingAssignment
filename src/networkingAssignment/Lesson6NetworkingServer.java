package networkingAssignment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class Lesson6NetworkingServer implements Runnable {
   Socket csocket;
   Lesson6NetworkingServer(Socket csocket) {
      this.csocket = csocket;
   }
   
   public static void main(String args[]) throws Exception { 
	   String pattern = ("\\d+");
	   int port = 1235;
	   	if (Arrays.toString(args).contains("--port")) {
		   	if (args[1].matches(pattern)) {
			   	if (Integer.parseInt(args[1]) > 65535) {
			   		System.out.println("Port cant be over 65,535");
			   		System.exit(0);
			   		} else {
				   port = Integer.parseInt(args[1]);}
		   	} else {
			  System.out.println(args[1] + " is not a valid port");
			  System.exit(0);
		   }
	   } 
	
      ServerSocket ssock = new ServerSocket(port);
      System.out.println("Listening on " + port + ", type stop to stop :)");
     	while (true) {
         Socket sock = ssock.accept();
         System.out.println("Connected");
         new Thread(new Lesson6NetworkingServer(sock)).start();
	   	}
   }
   public void run() {
      try {
    	  	PrintStream pstream = new PrintStream(csocket.getOutputStream(), true);
			OutputStream outStream = csocket.getOutputStream();
			InputStream inStream = csocket.getInputStream();
		pstream.println("HTTP/1.0 200 OK");
		pstream.println("Content-Type: text/html");
		pstream.println("\r\n");
		pstream.println("<HTML>");
		pstream.println("<head><title>Java Networking</title></head>");
		pstream.println("<body>");
		pstream.println("<body><h1>Java Networking2</h1>" + "</body>");
		pstream.println("</body>");
		pstream.println("</HTML>");
		
         pstream.close();
         csocket.close();
      } catch (IOException e) {
         System.out.println(e);
      } 
	}
   }
