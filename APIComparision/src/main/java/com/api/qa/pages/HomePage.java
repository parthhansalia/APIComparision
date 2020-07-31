package com.api.qa.pages;

import com.api.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class HomePage extends TestBase {

	private String getCity = prop.getProperty("city");

	private String cityXpath = "//*[@id= \""+getCity+"\"]";

	private boolean cityFoundOnMap = false;

	private List<String> whetherData = new ArrayList<>();

	//Page Factory - OR:
	@FindBy(xpath="//*[@id=\"h_sub_menu\"]")
	WebElement subMenu;
	
	@FindBy(xpath="//*[@id=\"subnav\"]/div/div/div/div/div/a[8]")
	WebElement whetherOption;
	
	@FindBy(id="searchBox")
	WebElement searchBox;

	
	//Initializing the Page Objects:
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean goToWhetherSection() throws InterruptedException {

		Thread.sleep(5000);
		subMenu.click();

		whetherOption.click();

		return searchBox.isDisplayed();
	}




	
}
