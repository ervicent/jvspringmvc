package tesis.playon.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author gmorales
 *
 */
public class LatitudlongitudUtil {

    public GeoposicionDePlaya getLocationFromAddress(String address) throws Exception {

	GeoposicionDePlaya gpdp = new GeoposicionDePlaya();

	String encodedAddress = URLEncoder.encode(address, "UTF-8");
	URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress
		+ "&sensor=false");
	String addresses = readURL(url);
	JSONObject json = new JSONObject(addresses);

	String status = (String) json.get(LatitudLongitudConstantes.STATUS_LABEL);

	if (LatitudLongitudConstantes.OK_MESSAGE.equalsIgnoreCase(status)) {
	    JSONArray results = json.getJSONArray(LatitudLongitudConstantes.RESULTS_LABEL);
	    if (results.length() <= 0) {
		return null;
	    }
	    JSONObject result = results.getJSONObject(0);
	    JSONObject jsonLocation = result.getJSONObject(LatitudLongitudConstantes.GEOMETRY_LABEL).getJSONObject(
		    LatitudLongitudConstantes.LOCATION_LABEL);
	    gpdp.setLatitud((Double) jsonLocation.get(LatitudLongitudConstantes.LATITUDE_LABEL));
	    gpdp.setLongitud((Double) jsonLocation.get(LatitudLongitudConstantes.LONGITUDE_LABEL));
	} else {
	    return null;
	}
	return gpdp;
    }

    private static String readURL(URL url) throws Exception {
	URLConnection connection = url.openConnection();
	StringBuilder builder = new StringBuilder();
	BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String line;
	while ((line = reader.readLine()) != null) {
	    builder.append(line);
	}
	return builder.toString();
    }

    public class GeoposicionDePlaya {

	Double latitud;

	Double longitud;

	public Double getLatitud() {
	    return latitud;
	}

	public void setLatitud(Double latitud) {
	    this.latitud = latitud;
	}

	public Double getLongitud() {
	    return longitud;
	}

	public void setLongitud(Double longitud) {
	    this.longitud = longitud;
	}
	
	public String toString (){
	    return  latitud.toString() + "," + longitud.toString();  
	}

    }

}