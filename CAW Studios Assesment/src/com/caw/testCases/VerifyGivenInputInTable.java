package com.caw.testCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.caw.commonFunctions.InitilaizeTest;
import com.caw.pageObjects.HomePageObject;

public class VerifyGivenInputInTable extends InitilaizeTest {


	ArrayList<String> dataListFromTable;
	ArrayList<String> dataListFromJSONFile;

	
	public void getDataFromJson() throws IOException, ParseException {
		String jsonContent = new String(Files.readAllBytes(Paths.get("data.json")));
		dataListFromJSONFile  = new ArrayList<>();
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonContent);

		for (Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject) obj;

			String name = (String) jsonObject.get("name");
			Long age = (Long) jsonObject.get("age");
			String gender = (String) jsonObject.get("gender");

			dataListFromJSONFile.add(name + " " + age + " " + gender);
		}

	}
	
	public void getDataFromTable(){
		
		PageFactory.initElements(driver, HomePageObject.class);
		dataListFromTable = new ArrayList<>();
		List<WebElement> getRows = HomePageObject.get_row_from_table;
		for (int row = 1; row < getRows.size(); row++) {
			String rowText = getRows.get(row).getText();
			dataListFromTable.add(rowText);    
		}
		System.out.println(dataListFromTable);
		System.out.println(dataListFromJSONFile);
		
		

	}
	
	
	@Test
	public void isOutputMatches() throws IOException, ParseException {
		getDataFromJson();
		getDataFromTable();
		boolean areEqual = dataListFromTable.equals(dataListFromJSONFile);
		System.out.println(areEqual);
		 Assert.assertEquals(areEqual, false);
	}



}
