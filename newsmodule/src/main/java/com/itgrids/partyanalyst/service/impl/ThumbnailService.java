package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.service.IThumbnailService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.JpegReader;



/**
 * @author Anilkumar r
 *
 */
public class ThumbnailService implements IThumbnailService {


public static Logger log = Logger.getLogger(ThumbnailService.class); 
private IFilePathsDAO filePathsDAO;
	
	public IFilePathsDAO getFilePathsDAO() {
	return filePathsDAO;
}

public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
	this.filePathsDAO = filePathsDAO;
}



public void  crateThumnailDynamically( List<String> filePaths, String realContextPath, int width ,int height   ) 
	
	{ 

	log.debug("Dynamic thumnail creation");
	
//	   int widthThumb=120;
//	   int heightThumb=90;
//			   int widthSmall=150;
//			   int heightSmall=110;
//					   int widthMedium=320;
//					   int heightMedium=240;
//					   
//					   String sizeThumb="Small";
//						String sizeSmall="Medium";	
//						String sizeMedium="Large";	
		
		//write call for getting filepaths  list<strings>	
		
		 String filePath="";	 String filePat="";
	 String dbpath1 ="";String dbpath2 ="";String dbpath3 ="";
	 File f=null;
		//process filepaths to convert as images
	
	
	      for (int i=0; i<filePaths.size();i++)
	      { 
	    	  
	    	filePat = (String) filePaths.get(i);
	    	filePath=realContextPath+filePat;
	    	File   f1 =new File(filePat);
	    	
	    	   f =new File(filePath);
	    	   System.out.println(f1.getAbsolutePath());
	    	   try {
				System.out.println(f1.getAbsoluteFile().getCanonicalPath());
				System.out.println(f1.getAbsoluteFile().getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        	  if(f.exists() && f.canRead())
	        	  {
	        		  
	        		  dbpath1 = resizeImages(filePat,IConstants.SMALL,IConstants.SMALL_WIDTH,IConstants.SMALL_HEIGHT,f,realContextPath);
	            	  dbpath2 = resizeImages(filePat,IConstants.MEDIUM,IConstants.MEDIUM_WIDTH,IConstants.MEDIUM_HEIGHT,f,realContextPath);
	            	  dbpath3 = resizeImages(filePat,IConstants.LARGE,IConstants.LARGE_WIDTH,IConstants.LARGE_HEIGHT,f,realContextPath);
	        	  }
	        	  
	        	      }
	   		}

public  String  resizeImages(String path, String destinationSize, int width ,int height ,File f2,String realContextPath) 

{
	
	BufferedImage originalImage=null;
	
	BufferedImage thumbnail =null;
	File file=null;
	File f1=null;
	String destPath="";
	try{
	        	//	  originalImage = ImageIO.read(f2);
		
		String parent = path.substring(0, path.indexOf("/"));
		
		String parent1 = realContextPath+ path.substring(0, path.indexOf("/"));

		String parent2 = realContextPath+"Thumbs";
		
		String imgpath ="/"+destinationSize+"/"+path;
		
		String child =imgpath.substring(0, imgpath.lastIndexOf("/"));
		  
		String total =parent2+imgpath;
		
		String originalPath=realContextPath+path;
		
		
		 JpegReader jr=new JpegReader();			 
		 originalImage =  jr.readImage(new File(originalPath));
		
		thumbnail =	Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY,Scalr.Mode.FIT_EXACT, width, height);	 
		

		
		System.out.println(parent);
		System.out.println(imgpath);
		System.out.println(child);
		System.out.println(total);
		
		String sysPath=parent2+imgpath;
		
		
		 file=new File(parent2,imgpath);
		 f1=new File(realContextPath,path);
		 f2=new File(parent2,child);
		
		 if(f1.exists()){
		if(f2.exists() )
		ImageIO.write(thumbnail, "jpeg", file);
		else 
		{
			f2.mkdirs();
		ImageIO.write(thumbnail, "jpeg", file);
		}
		
		 }
if (new File(sysPath).exists()){
	
	destPath=total;
}
else {
	log.warn("thumbnail not created properly");
	destPath="failure because of unsucessful thumb creation ";
	
}	
	}catch(Exception e)	    {
		log.error("Exception Occured in thumbnail processing method, Exception is - "+e+path); 
		e.printStackTrace() ;
		destPath="failure because of exception raised at------ "+path+ "conversion";
	}
	finally{
		
		file=null;
		f1=null;
		thumbnail=null;
		originalImage=null;
	}
	return destPath;
}
 /* 
	public String  crateThumnailForAdmin(int[] ids1,String realContextPath)
	
	{ 
		ResultStatus resultStatus = new ResultStatus();
		String[] ids= {"1","2","5","10","11","24","25","35"};
		log.debug("admin task thumbnails creation");
	
				
		//write call for getting filepaths  list<strings>
				
			    List<String> filetypeIds =Arrays.asList(ids);
			    
					
	List<Object>  filePaths =	filePathsDAO.getFilePathsBasedOnFileTypeIds(filetypeIds);
	 String filePath=""; String filePat="";
      String dbpath1 ="";String dbpath2 ="";String dbpath3 ="";
      List<String> paths= new ArrayList<String>();
	 File f=null;
		//process filepaths to convert as images
	String flag="sucess";
	      for (int i=0; i<filePaths.size();i++)
	      { 
	    	  
	    	filePat = (String) filePaths.get(i);
	    	filePath=realContextPath+filePat;
	    	
	    	
	    	   f =new File(filePath);
	    	   System.out.println(f.getPath());
	    	
	    	 

	        	  if(f.exists() && f.canRead())
	        	  {
	        		   dbpath1 ="1"; dbpath2 ="2"; dbpath3 ="3";
	        		   dbpath1 = resizeImages(filePat,IConstants.SMALL,IConstants.SMALL_WIDTH,IConstants.SMALL_HEIGHT,f,realContextPath);
	             	  dbpath2 = resizeImages(filePat,IConstants.MEDIUM,IConstants.MEDIUM_WIDTH,IConstants.MEDIUM_HEIGHT,f,realContextPath);
	             	  dbpath3 = resizeImages(filePat,IConstants.LARGE,IConstants.LARGE_WIDTH,IConstants.LARGE_HEIGHT,f,realContextPath);
	        	  }else
	        	  { 
	        		  flag="failure beacause of not exists";
	        	  }
	        	  if(dbpath1.equalsIgnoreCase("1") || dbpath2.equalsIgnoreCase("2") || dbpath3.equalsIgnoreCase("3") )
	        	  {
	        		  flag="failure because of converting";
	        		 
	        	  }else{
	        		  paths.add(filePat);
	        	  }
	        	  
	        	
	      }//for
	      
	      if(filePaths.size()==0)
	      {
	    	  flag="no records to process";
	    	return flag;  
	      }
	      if(paths.size()>0 )
	      {
	    int  res=   filePathsDAO.updateFilePathsThumbnails(paths);
	    if(res == 0)
		   flag +="false beacause of databae updation but thumnail creation is sucess";
	      }else
	        flag +="no thumnail is processed";
	      return flag;
	}


	
public String  crateThumnaiForProfiles( String filePaths, String realContextPath ) 

{ 

log.debug("profiles thumnail creation");


	//write call for getting filepaths  list<strings>	
	
	 String filePath="";	 String filePat="";
	 String dbpath1 ="";String dbpath2 ="";String dbpath3 ="";
   String flag="sucess";
 File f=null;
	//process filepaths to convert as images


     
    	  
    	filePat =  filePaths.substring(filePaths.indexOf("pictures")).replace("\\", "/");
    	filePath=realContextPath+filePat;
    	File   f1 =new File(filePat);
    	
    	   f =new File(filePath);
    	 
    	

        	  if(f.exists() && f.canRead())
        	  {
        		  dbpath1 ="1"; dbpath2 ="2"; dbpath3 ="3";
    		  dbpath1 = resizeImages(filePat,IConstants.SMALL,IConstants.SMALL_WIDTH,IConstants.SMALL_HEIGHT,f,realContextPath);
        	  dbpath2 = resizeImages(filePat,IConstants.MEDIUM,IConstants.MEDIUM_WIDTH,IConstants.MEDIUM_HEIGHT,f,realContextPath);
        	  dbpath3 = resizeImages(filePat,IConstants.LARGE,IConstants.LARGE_WIDTH,IConstants.LARGE_HEIGHT,f,realContextPath);
        	  }else
        	  { 
        		  flag="failure beacause of not exists";
        	  }
        	  if(dbpath1.equalsIgnoreCase("1") || dbpath2.equalsIgnoreCase("2") || dbpath3.equalsIgnoreCase("3") || dbpath3.contains("failure") || dbpath2.contains("failure") || dbpath2.contains("failure")  )
        	  {
        		 
        		  flag="failure because of converting";
        		 
        	  }
        	  return flag;
        	      
   		}
	
	
	
	
	public static void main(String[] args) {
		
		new ThumbnailService().resizeImages("images/candidates/HARAK SINGH RAWAT.jpg", "small", 300, 300, new File("")," D:\\anilpawarsbackup\\dec10\\PartyAnalyst\\");
	}
	
	
*/}
