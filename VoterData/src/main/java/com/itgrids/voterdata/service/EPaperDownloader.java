package com.itgrids.voterdata.service;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.itgrids.voterdata.util.IConstants;

public class EPaperDownloader {

    public static void main(String args[]) throws InterruptedException {
        
    	FirefoxBinary binary = new FirefoxBinary(new File(IConstants.FIREFOX_PATH));
        FirefoxProfile profile = new FirefoxProfile();
        
        profile.setPreference("browser.download.folderList",2);
        profile.setPreference("browser.download.manager.showWhenStarting",false);
        profile.setPreference("browser.download.dir",IConstants.DOWNLOAD_DIRECTORY);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
        profile.setPreference("extensions.addons", false);
        
        WebDriver driver = new FirefoxDriver(binary,profile);

        driver.get("http://epaper.andhrabhoomi.net/");
        
        WebElement submit = driver.findElement(By.id("DataList1_ImageButton1_1"));
        submit.click();
        
        WebElement div = driver.findElement(By.id("Panel1"));
        System.out.println(div.getAttribute("innerHTML"));
        System.out.println(div.getText());
       
        driver.quit();
    }
}
