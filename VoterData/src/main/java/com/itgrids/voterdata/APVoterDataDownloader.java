package com.itgrids.voterdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class APVoterDataDownloader {

    public static void main(String args[]) throws InterruptedException {
        
    	/* Create a new instance of the Firefox driver
         Notice that the remainder of the code relies on the interface,
         not the implementation 
         */
    	    	
        FirefoxBinary binary = new FirefoxBinary(new File(IConstants.FIREFOX_PATH));
        FirefoxProfile profile = new FirefoxProfile();
        
        profile.setPreference("browser.download.folderList",2);
        profile.setPreference("browser.download.manager.showWhenStarting",false);
        profile.setPreference("browser.download.dir",IConstants.DOWNLOAD_DIRECTORY);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf");
        profile.setPreference("extensions.addons", false);
        
        WebDriver driver = new FirefoxDriver(binary,profile);

        driver.get(IConstants.VOTER_DATA_SITE_URL);
        
        // Find the text input element by its name
        Select district = new Select(driver.findElement(By.name("ddlDist")));
        
        int numOfDistricts = district.getOptions().size();
        
        int d = 19;
        //for (; d<=numOfDistricts; d++) {
            district.selectByValue(String.valueOf(d));
            Select constituency = new Select(driver.findElement(By.name("ddlAC")));
            int numOfconstituencies = constituency.getOptions().size();
            System.out.println("numOfconstituencies:"+numOfconstituencies);
            int i =0;
            if (d == 1) {
                i = 3;
            } else {
                i = 1;
            }
            i= 233;
            //for(; i<=numOfconstituencies; i++){
                constituency.selectByValue(String.valueOf(i));
                String constituencyName = constituency.getFirstSelectedOption().getText();
                System.out.println("Constituency Name:" + constituencyName);
                WebElement submit = driver.findElement(By.id("btnGetPollingStations"));
                submit.click();
                WebElement boothTable = driver.findElement(By.xpath("//*[@id=\"GridView1\"]"));
                int boothCount = boothTable.findElements(By.tagName("tr")).size() - 1;
                System.out.println("boothCount:"+boothCount);
                WebElement downloadLink = null;
                int j = 0;
                if (i == 274) {
                  j = 133;
                }
                for (; j < boothCount; j++)
                {
                
                 try{
                    int no = j + 2;
                   
                    if (no < 10) {
                        downloadLink =  driver.findElement(By.id("GridView1_ctl0"+no+"_lnkEnglish"));
                    } else {
                        downloadLink =  driver.findElement(By.id("GridView1_ctl"+no+"_lnkEnglish"));
                    }
                    downloadLink.click();

                    String boothName = driver.findElement(By.xpath("//*[@id=\"GridView1\"]/tbody/tr["+no+"]/td[2]")).getText();
                    String boothNo = driver.findElement(By.xpath("//*[@id=\"GridView1\"]/tbody/tr["+no+"]/td[1]")).getText();
                    File downloadedFile = new File(IConstants.DOWNLOAD_DIRECTORY+"/"+"PDFGeneration.aspx");
                    final File downloadedFilePart = new File(IConstants.DOWNLOAD_DIRECTORY+"/"+"PDFGeneration.aspx.part");

                    String boothFileName = constituencyName+"-"+boothNo+"-"+boothName+".pdf";
                    System.out.println("Booth No:"+boothNo+" and Name:"+boothName+" FileName:"+boothFileName);
                    (new WebDriverWait(driver, 300, 10)).until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver d) {
                            return !downloadedFilePart.exists();
                        }
                    });
                    if (downloadedFile.exists()) {
                        downloadedFile.renameTo(new File(IConstants.DOWNLOAD_DIRECTORY+"/"+boothFileName));
                        downloadedFile.delete();
                    }
                    
                 }catch(Exception e){
                	 e.printStackTrace();
                 }
                
                }
            //}
        //}
        driver.quit();


    }
}
