package com.qait.automation.tatoc_advance_maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


class App
{
WebDriver driver;
public App(WebDriver driver)
{
	this.driver=driver;
}
public void step1advancelink()
{
	
    WebElement element1=driver.findElement(By.linkText("Advanced Course"));
    Assert.assertTrue(element1.isDisplayed());
    element1.click();
    
}
public void step2menu()
{
    WebElement element=driver.findElement(By.className("menutitle"));
    Assert.assertTrue(element.isDisplayed());
}
public void step2hoveractions()
{
	WebElement element=driver.findElement(By.className("menutitle"));
    Actions action = new Actions(driver);
    action.moveToElement(element).moveToElement(driver.findElement(By.xpath("//*[text()[contains(.,'Go Next')]]"))).click().build().perform();
    String title=driver.findElement(By.tagName("h1")).getText();
    Assert.assertEquals(title,"Query Gate");
}
public Connection getcoonection()
{
    Connection conn=null;
try

{
    String url = "jdbc:mysql://10.0.1.86";
    System.out.println(url);
    Class.forName ("com.mysql.jdbc.Driver");
    System.out.println("before connection");
    conn = DriverManager.getConnection (url,"tatocuser","tatoc01");
    System.out.println ("Database connection established");
    boolean flag;
    if(conn!=null)
    	flag=true;
    else
    	flag=false;
    Assert.assertTrue(flag);
}
catch (Exception e)
   {
    e.printStackTrace();

   }
   return conn;

}

public int step3getid() throws SQLException
{
	int id=0;
	String symbol=driver.findElement(By.name("symboldisplay")).getText();
	Connection conn=getcoonection();
	String query="use tatoc";
	Statement smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(query);
	System.out.println(symbol);
	query="select id from identity where symbol='"+symbol+"'";
	smt=conn.createStatement();
	rs=smt.executeQuery(query);
	while(rs.next())
	{
		id=rs.getInt(1);
	}
	return id;
}
public String step3getName() throws SQLException
{
	String name=null;
	int id=step3getid();
	
	Connection conn=getcoonection();
	String query="use tatoc";
	Statement smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(query);
	query="select name from credentials where id ='"+id+"'";
	smt=conn.createStatement();
	rs=smt.executeQuery(query);
	while(rs.next())
	{
		name=rs.getString(1);
	}
	return name;
}
public String step3getkey() throws SQLException
{
	String passkey=null;
	int id=step3getid();
	Connection conn=getcoonection();
	String query="use tatoc";
	Statement smt=conn.createStatement();
	ResultSet rs=smt.executeQuery(query);
	query="select passkey from credentials where id ='"+id+"'";
	smt=conn.createStatement();
	rs=smt.executeQuery(query);
	while(rs.next())
	{
		passkey=rs.getString(1);
	}
	return passkey;
}
public void login(String name,String passkey)
{
	WebElement username=driver.findElement(By.id("name"));
	WebElement pass=driver.findElement(By.id("passkey"));
	
	username.sendKeys(name);
	pass.sendKeys(passkey);
	driver.findElement(By.id("submit")).click();
	
	Assert.assertEquals(driver.getCurrentUrl(),"http://10.0.1.86/tatoc/advanced/video/player");
}

}