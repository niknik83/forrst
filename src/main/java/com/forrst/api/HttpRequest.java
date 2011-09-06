package com.forrst.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpRequest {

	/**
	 * Fetch data from Forrst using the request URI
	 * without URL parameters
	 * 
	 * @param requestURI Forrst URI requested
	 * @return JSONObject containing a response
	 */
	public JSONObject get(String requestURI) {
		return getData(requestURI);
	}
	
	/**
	 * Fetch data from Forrst using the request URI
	 * without URL parameters
	 * 
	 * @param requestURI Forrst URI requested
	 * @param args Map of URL parameters and their values
	 * @return JSONObject containing a response
	 */
	public JSONObject get(String requestURI, Map<String,String> args) {
		return getData(requestURI + stringifyArgs(args));
	}
	
	/**
	 * Workhorse method that reads data from a URL and
	 * returns the result as a JSON object
	 * 
	 * @param requestURI The Forrst endpoint requested
	 * @return JSONObject containing the full Forrst API response
	 */
	protected JSONObject getData(String requestURI) {
		String jsonResult = "";
		
		JSONObject json = null;

		try {
			URL url = new URL(requestURI);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String responseLine;
			while ((responseLine = in.readLine()) != null) {
				jsonResult += responseLine;
			}

			in.close();
			
			json = new JSONObject(jsonResult);
			json = json.getJSONObject("resp");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL requested");
		} catch (IOException e) {
			throw new RuntimeException("Could not read from requested stream");
		} catch (JSONException e) {
			throw new RuntimeException("JSON could not be formed");
		}
		
		return json;
	}
	
	/**
	 * Creates a string version of the URL params
	 * 
	 * @param args
	 * @return
	 */
	protected String stringifyArgs(Map<String,String> args) {
		StringBuilder argString = new StringBuilder("?");

		for(String key : args.keySet()) {
			argString.append(key);
			argString.append('=');
			argString.append(args.get(key));
			argString.append('&');
		}

		String stringifiedArgs = argString.toString();

		return stringifiedArgs.substring(0, stringifiedArgs.length() - 1);
	}

}
