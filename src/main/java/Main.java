package main.java;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {
	private static String myJSon = "";

	public static void main(String[] args) {

		port(Integer.valueOf(System.getenv("PORT")));
		staticFileLocation("/public");


		get("/", (request, response) -> {  	
			if(myJSon == ""){
				ParseAddresses parser = new ParseAddresses();
				myJSon = parser.readLargeFile2("src/main/resources/data/Addresses_small_columns7.csv");
			}

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("jsonAddresses", myJSon);

			return new ModelAndView(attributes, "heatmap.ftl");
		}, new FreeMarkerEngine());
		
		get("/bounds", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();

			return new ModelAndView(attributes, "bounds.ftl");
		}, new FreeMarkerEngine());


		post("/boundsanswers", (request, response) -> {
			String minLat= "";
			String maxLat = "";
			String minLon = "";
			String maxLon = "";
			try {
				// make sure you define in ftl and input with the right ID, "minLat" here
				minLat= request.queryParams("minLat") != null ? request.queryParams("minLat") : "";
				maxLat= request.queryParams("maxLat") != null ? request.queryParams("maxLat") : "";
				minLon= request.queryParams("minLon") != null ? request.queryParams("minLon") : "";
				maxLon= request.queryParams("maxLon") != null ? request.queryParams("maxLon") : "";
			} catch (Exception e) {

			}

			Bounds bounds = new Bounds(myJSon, minLat, maxLat, minLon, maxLon);
			String answer = bounds.parseJson();

			Map<String, Object> attributes = new HashMap<>();
			attributes.put("finalanswer", answer);

			return new ModelAndView(attributes, "boundanswers.ftl");
		}, new FreeMarkerEngine());

	}

}
