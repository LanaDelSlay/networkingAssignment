package networkingAssignment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Lesson6NetworkingClient {

	public static void main(String[] args) throws IOException {
 String ip = "";
 String port = "";
		for (int i = 0; i < args.length;i++) {
			if (args[i].contains("server")) {
				ip = args[i+1];
			}
			
			if (args[i].contains("port")) {
				port = args[i+1];
			}
		}
		
		if (ip.isBlank()) {
			ip = "localhost";
		}
		
		if (port.isBlank()) {
			port = "1010";
		}
		
		URL url = new URL("http://"+""+":"+"");
		URLConnection conn = url.openConnection();
		System.out.println("connecting...");
		conn.connect();
		System.out.println(conn.getContent().toString());
		Map<String,List<String>> headers = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry: headers.entrySet()) {
			String key = entry.getKey();
			for (String value : entry.getValue()) {
				System.out.println(key + ": " + value);
			}
		}
		
	}

}
