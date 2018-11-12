package codeclinic.geolocation;

import org.json.*;
import java.io.*;
import java.net.*;

public class App {

	public static void main(String[] args) {

		String myIP = "";
		String ipWebsite = "http://bot.whatismyipaddress.com";

		try {
			URL ipURL = new URL(ipWebsite);

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(ipURL.openStream()));

			myIP = reader.readLine().trim();
			reader.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Your public IP address is: " + myIP);

		String data = getLocation(myIP);
		JSONObject json = new JSONObject(data);
		
		String city = json.getString("city");
		String region = json.getString("region");
		String coords = json.getString("loc");
		String mapLink = "https://www.google.com/maps/?q=" + coords;

		System.out.println("You are in the city: " + city);
		System.out.println("You are in the country: " + region);
		System.out.println("Your location can be found at: " + mapLink);
	}

	public static String getLocation(String ipAddress) {

		String result = "";

		try {

			String website = "https://ipinfo.io/" + ipAddress;
			URL url = new URL(website);

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openStream()));

			String line = reader.readLine();

			while (line != null) {

				System.out.println(line);
				result += (line + "\n");
				line = reader.readLine();
			}

			reader.close();

		}

		catch (Exception e) {
			System.out.println(e);
		}

		return result;

	}
}
