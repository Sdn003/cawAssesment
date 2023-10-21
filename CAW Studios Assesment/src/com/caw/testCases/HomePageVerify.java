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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.caw.commonFunctions.InitilaizeTest;
import com.caw.pageObjects.HomePageObject;

public class HomePageVerify extends InitilaizeTest {
	
	//To Verify Elements are avaiable in Homepage
	
	String heading = null;
	String menu = null;
	boolean table;
	
	public void verifyNavBar() {
	menu = HomePageObject.nav_bar.getText();
	log.info("Nav bar located " + menu);
	
}
	
	public void verifHeading() {
		heading = HomePageObject.heading.getText();
		log.info("Headin located " + heading);
		
	}
	
	public void verifyTable() {
		table = HomePageObject.table.isDisplayed();
		log.info("Table located " + table);
	}
	
	@Test(priority = 0)
	public void verifyHomePage() {
		PageFactory.initElements(driver, HomePageObject.class);
		verifyNavBar();
		Assert.assertEquals(menu, "Page");
		verifHeading();
		Assert.assertEquals(heading, "Dynamic HTML TABLE Tag");
		verifyTable();
		Assert.assertEquals(table, true);
	}
	
	@Test(priority =1, dependsOnMethods = "verifyHomePage")
	public void insertDataIntoTextBox() throws IOException {
		PageFactory.initElements(driver, HomePageObject.class);
		HomePageObject.button_to_display_textbox.click();
		log.info("Drop down to display Textbox is displayed");
		
		//Clearing the text in the textbox
		HomePageObject.field_to_insert_input_data.clear();
		log.info("Clered the text inbox");
		
		//Getting the data from JSON file and inserting into Textbox
		String jsonContent = new String(Files.readAllBytes(Paths.get("data.json")));
		HomePageObject.field_to_insert_input_data.sendKeys(jsonContent);
		HomePageObject.refresh_button.click();
		log.info("Data Added to the Table");
		
	}
	
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
	
		
		

	}
	
	
	@Test(priority =2, dependsOnMethods = "insertDataIntoTextBox")
	public void isOutputMatches() throws IOException, ParseException {
		getDataFromJson();
		getDataFromTable();
		boolean areEqual = dataListFromTable.equals(dataListFromJSONFile);
		Assert.assertEquals(areEqual, true);
		if(areEqual) {
			log.info("Test is Successful");
		}
		else {
			log.warn("Test is unSuccessful");
		}
		
	}


	

}
