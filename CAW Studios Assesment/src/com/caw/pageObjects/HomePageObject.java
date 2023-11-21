package com.caw.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageObject {
	
	@FindBy(xpath = "/html/body/div/div[1]/div[2]/a")
	public static WebElement nav_bar;
	
	@FindBy(xpath = "/html/body/div/h1")
	public static WebElement heading;
	
	@FindBy(id = "dynamictable")
	public static WebElement table;
	
	@FindBy(xpath = "/html/body/div/div[3]/details/summary")
	public static WebElement button_to_display_textbox;
	
	@FindBy(id = "jsondata")
	public static WebElement field_to_insert_input_data;
	
	@FindBy(id = "refreshtable")
	public static WebElement refresh_button;
	
	@FindBy(xpath = "//table[@id='dynamictable']/tr")
	public static List<WebElement> get_row_from_table;
	
	@FindBy(tagName = "td")
	public static List<WebElement> get_cell_data_from_table;
	

}
