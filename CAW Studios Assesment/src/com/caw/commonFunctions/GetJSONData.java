package com.caw.commonFunctions;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;


import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class GetJSONData {

	public static void main(String[] args) throws IOException, ParseException {

		String jsonContent = new String(Files.readAllBytes(Paths.get("data.json")));
	
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonContent);

		 for (Object obj : jsonArray) {
             JSONObject jsonObject = (JSONObject) obj;

             String name = (String) jsonObject.get("name");
             long age = (Long) jsonObject.get("age");
             String gender = (String) jsonObject.get("gender");

             System.out.println("Name: " + name);
             System.out.println("Age: " + age);
             System.out.println("Gender: " + gender);
             System.out.println();
	}
		 
	}
}
