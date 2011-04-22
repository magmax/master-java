package org.magmax.masterjava.tema5.red_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResolveTest {
	@Test
	public void resolveLocalhost () throws UnknownHostException  {
		InetAddress address = InetAddress.getByName("127.0.0.1");
		assertEquals("localhost", address.getHostName());
	}
	
	@Test
	public void resolveGoogle() throws UnknownHostException {
		InetAddress address = InetAddress.getByName("8.8.8.8");
		assertEquals ("google-public-dns-a.google.com", address.getHostName());
	}
	
	@Test
	public void resolveExample() throws UnknownHostException {
		InetAddress address = InetAddress.getByName("example.com");
		assertEquals ("example.com", address.getHostName());
		assertEquals ("192.0.32.10", address.getHostAddress());
	}
	
	@Test
	public void getExampleMainPage() throws IOException {
		URL url = new URL("http://www.example.org");
		InputStreamReader stream = new InputStreamReader(url.openStream());
		BufferedReader reader = new BufferedReader(stream);
		boolean found = false;
		try {
			while (!found){
				String line = reader.readLine();
				if (line == null)
					break;
				System.out.println(line);
				found |= line.contains("we maintain a number of domains such as EXAMPLE.COM and EXAMPLE.ORG");
			}
		} catch (IOException e)
		{
			// end of file
		}
		finally {
			reader.close();
		}
		assertTrue(found);
	}
}
