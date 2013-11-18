package com.itgrids.voterdata.service;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itgrids.voterdata.util.IConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
            int i = 28;
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
                List<Integer> missedList = null;
                
                if(IConstants.IS_MISSED_FILES_DOWNLOAD)
                {
                	File downloadedDirecetoty = new File(IConstants.DOWNLOAD_DIRECTORY);
                	String filesNameList[] = downloadedDirecetoty.list();
                	if(filesNameList != null && filesNameList.length > 0)
                	{
                		List<Integer> fileExistanceList = new ArrayList<Integer>(0);
                		missedList = new ArrayList<Integer>(0);
                		for(String fileName : filesNameList)
                		{
                			File file = new File(IConstants.DOWNLOAD_DIRECTORY+"\\"+fileName);
                			if(file.isFile() && fileName.endsWith(".pdf") && !file.isHidden())
                				fileExistanceList.add(new Integer(fileName.split("-")[2]));
                			else
                				file.deleteOnExit();
                		}
                		for(int k=1;k<=boothCount;k++)
                			if(!fileExistanceList.contains(k))
                				missedList.add(k);
                	}
                }
                
                int j = 0;
                if (i == 274) {
                  j = 133;
                }
                for (; j < boothCount; j++)
                {
                
                 try{
                	
                	if(IConstants.IS_MISSED_FILES_DOWNLOAD && !missedList.contains(j+1))
                		continue;
                	//Thread.sleep(100);
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
                    	boothFileName = boothFileName.replaceAll("/","");
                    	boothFileName = boothFileName.replaceAll("\"","");
                        FileUtils.moveFile(downloadedFile, new File(IConstants.DOWNLOAD_DIRECTORY+"\\"+boothFileName));
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
