package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

import com.itgrids.voterdata.util.IConstants;

public class DeletedVoterDownloader {

    public static void main(String args[]) throws InterruptedException, Exception 
    {
    	int i = 40;
    	int n = 40;
    	int d = 4;
    	
    	for(;i<=n;i++)
    	{
    		try{
    			downloadData("M","D:\\Kamal\\"+i+"-Door_Locked.txt",i,d);
    			//downloadData("E","D:\\Kamal\\"+i+"-Expired.txt",i,d);
    	    	downloadData("S","D:\\Kamal\\"+i+"-Shifted.txt",i,d);
    	    	//downloadData("R","D:\\Kamal\\"+i+"-Duplicate.txt",i,d);
    	    	//downloadData("M","D:\\Kamal\\"+i+"-Door_Locked.txt",i,d);
    	    	//downloadData("Q","D:\\Kamal\\"+i+"-Inelgible.txt",i,d);
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    }
    		
    		
    public static void downloadData(String type,String filePath,int constituencyId,int districtId) throws InterruptedException, Exception 
    {
        
    	FirefoxBinary binary = new FirefoxBinary(new File(IConstants.FIREFOX_PATH));
        FirefoxProfile profile = new FirefoxProfile();
        
        profile.setPreference("browser.download.folderList",2);
        profile.setPreference("browser.download.manager.showWhenStarting",false);
        profile.setPreference("browser.download.dir",IConstants.DOWNLOAD_DIRECTORY);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
        profile.setPreference("extensions.addons", false);
        
        WebDriver driver = new FirefoxDriver(binary,profile);

        driver.get(IConstants.VOTER_DATA_SITE_URL);
        
        BufferedWriter outwriter = new BufferedWriter(new FileWriter(filePath));
        
        Select district = new Select(driver.findElement(By.name("ddldistrict")));
        
        int d = districtId;
        district.selectByValue(String.valueOf(d));
        Select constituency = new Select(driver.findElement(By.id("ddlacname")));
        int numOfconstituencies = constituency.getOptions().size();
        System.out.println("numOfconstituencies:"+numOfconstituencies);
        
        int i = constituencyId;
        
        constituency.selectByValue(String.valueOf(i));
        
        Select reason = new Select(driver.findElement(By.name("ddlreason")));
        reason.selectByValue(type);
        System.out.println(reason.getFirstSelectedOption().getText());
        
        WebElement submit = driver.findElement(By.id("Button1"));
        submit.click();
        
        getTableData(driver,outwriter,1);
        outwriter.close();
        
        driver.quit();
    }
    
    public static void getTableData(WebDriver driver,BufferedWriter outwriter,int linkNo)
    {
    	try{
    		WebElement voterTable = driver.findElement(By.id("GridView1"));
            
            List<WebElement> allRows = voterTable.findElements(By.tagName("tr"));
            
            for(WebElement row :allRows)
            {
            	try{
            		List<WebElement> cells = row.findElements(By.tagName("td"));
            		
            		if(cells.size() > 6 && cells.size() < 10)
            		{
            			StringBuilder sb = new StringBuilder();
                		for(WebElement cell : cells)
                		{
                			sb.append("\t"+cell.getText());
                		}
                		System.out.println(sb.toString());
                		outwriter.write(sb.toString().trim()+"\n");
            		}
            		else
            		{
            			for(WebElement cell : cells)
                		{
                			WebElement inTable = cell.findElement(By.tagName("table"));
                			List<WebElement> inTableRows = inTable.findElements(By.tagName("tr"));
                			for(WebElement inTableRow : inTableRows)
                			{
                				List<WebElement> inTableCells = inTableRow.findElements(By.tagName("td"));
                				int k = 0;
                				for(WebElement inTableCell : inTableCells)
                				{
                					String cellLinkNoStr = inTableCell.getText().trim();
                					int cellLinkNo = 0;
                					
                					if(!cellLinkNoStr.equals("..."))
                						cellLinkNo = Integer.valueOf(cellLinkNoStr);
                					
                					if(k > 0 && (cellLinkNoStr.equals("...") || cellLinkNo == linkNo+1))
                					{
                						WebElement anchorLink = inTableCell.findElement(By.tagName("a"));
                						anchorLink.click();
                						getTableData(driver,outwriter,linkNo+1);
                					}
                					k++;
                				}
                				
                			}
                		}
            		}
            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}
            }
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
}
