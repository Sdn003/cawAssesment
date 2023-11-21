package com.caw.testCases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.caw.commonFunctions.InitilaizeTest;
import com.caw.pageObjects.HomePageObject;

public class InputDataToTableAndRefresh extends InitilaizeTest {

	@Test
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
	
}
