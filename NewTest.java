package com.qait.automation.tatoc_advance_maven;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
	App app;
	@Test(priority=0)
	public void step1_advanceclick()
	{
		app.step1advancelink();
	}
	@Test(priority=1)
	public void step2_menu()
	{
		app.step2menu();
	}
	@Test(priority=2)
	public void step2_ishovering_performed()
	{
		app.step2hoveractions();
	}
	@Test(priority=3)
	public void step3check_connectivity()
	{
		app.getcoonection();
	}
	@Test(priority=4)
	public void step3queryprocess() throws SQLException
	{
		int id=app.step3getid();
		String name=app.step3getName();
		String passkey=app.step3getkey();
		System.out.println(id);
		System.out.println(name);
		System.out.println(passkey);
		app.login(name, passkey);
		
		//app.step3query();
	}
  @BeforeClass
  public void before()
  {
	  driver=new ChromeDriver();
	  driver.get("http://10.0.1.86/tatoc");
	  app=new App(driver);
  }
  @AfterClass
  public void after()
  {
	 // driver.quit();
  }
}
