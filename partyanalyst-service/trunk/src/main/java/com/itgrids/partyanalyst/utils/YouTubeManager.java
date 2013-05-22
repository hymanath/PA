package com.itgrids.partyanalyst.utils;


import java.net.URL;
import java.util.LinkedList;
import java.util.List;


import com.google.gdata.client.youtube.YouTubeService;

import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YtPublicationState;
import com.google.gdata.util.ServiceException;


public class YouTubeManager {
  private boolean valid=true;
   
    
    private String clientID;
    
 
    public YouTubeManager(String clientID) {
        this.clientID = clientID;
    }
           
    public boolean getVedioDetails(String id) 
    {  
    	
    	YouTubeService service1 = new YouTubeService(clientID);
    	String videoEntryUrl = "http://gdata.youtube.com/feeds/api/videos/"+id.trim();
    	VideoEntry videoEntry=null;
    	URL url=null;
    	try{
    	url=	new URL(videoEntryUrl);
    	 videoEntry = service1.getEntry(url, VideoEntry.class);
    	//printVideoEntry(videoEntry, id);
    	}catch(ServiceException se)
    	{  
    		valid=false;
    		//System.out.println("invalid video "+id);
    		se=null;
    	}catch(NullPointerException npe)
    	{
    		npe=null;
    	}
    	catch(java.net.SocketException se)
    	{
    		System.err.println("---socketExceptionIsRaised");
    	}
    	catch(Exception e)
    	{ 
    		e.printStackTrace();
    		e=null;
    	}
    	finally{
    		 videoEntryUrl=null;
    		 service1=null;
    		 url=null;
    	}
    	return valid;
    }
    public  void printVideoEntry(VideoEntry videoEntry,String id) {

    	  if(videoEntry.isDraft()) {
    	   // System.out.println("Video is not live "+id);
    	    YtPublicationState pubState = videoEntry.getPublicationState();
    	    if(pubState!=null)
    	    if(pubState.getState() == YtPublicationState.State.PROCESSING) {
    	      System.out.println("Video is still being processed."+id);
    	    }
    	    else if(pubState.getState() == YtPublicationState.State.REJECTED) {
    	      System.out.print("Video has been rejected because: " +id);
    	      System.out.println(pubState.getDescription());
    	      System.out.print("For help visit: ");
    	      System.out.println(pubState.getHelpUrl());
    	    }
    	    else if(pubState.getState() == YtPublicationState.State.FAILED) {
    	      System.out.print("Video failed uploading because: "
    	    		  +id);
    	      System.out.println(pubState.getDescription());
    	      System.out.print("For help visit: ");
    	      System.out.println(pubState.getHelpUrl());
    	    }
    	    }
    	  
    	 //System.out.println("Title: " + videoEntry.getTitle().getPlainText());

    	  }
    }

