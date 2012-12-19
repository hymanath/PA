package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.dto.UserProfileVO;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.service.IUserProfileService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class UserProfileService implements IUserProfileService {
	
    private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;
	
	private IPartySubscriptionsDAO partySubscriptionsDAO;
	
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;
	
	private IFileGallaryDAO fileGallaryDAO;
	
	private static final Logger log = Logger.getLogger(UserProfileService.class);
			
   public ICandidateSubscriptionsDAO getCandidateSubscriptionsDAO() {
		return candidateSubscriptionsDAO;
	}


	public void setCandidateSubscriptionsDAO(
			ICandidateSubscriptionsDAO candidateSubscriptionsDAO) {
		this.candidateSubscriptionsDAO = candidateSubscriptionsDAO;
	}


	public IPartySubscriptionsDAO getPartySubscriptionsDAO() {
		return partySubscriptionsDAO;
	}


	public void setPartySubscriptionsDAO(
			IPartySubscriptionsDAO partySubscriptionsDAO) {
		this.partySubscriptionsDAO = partySubscriptionsDAO;
	}


	public ISpecialPageSubscriptionsDAO getSpecialPageSubscriptionsDAO() {
		return specialPageSubscriptionsDAO;
	}


	public void setSpecialPageSubscriptionsDAO(
			ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO) {
		this.specialPageSubscriptionsDAO = specialPageSubscriptionsDAO;
	}


	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}


	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}


  public List<UserProfileVO> getPartyAnalystLatestUpdates(Date fromDate,Date toDate,Long userId){
	  List<UserProfileVO> userProfileVOList = new ArrayList<UserProfileVO>();
	try{
	  List<Long> candidateIds = candidateSubscriptionsDAO.getAllCandidatesSubscribedByUser(userId);
	  List<Long> partyIds = partySubscriptionsDAO.getAllPartiesSubscribedByUser(userId);
	  List<Long> specialPageIds = specialPageSubscriptionsDAO.getUserSubscribedSpecialPageIds(userId);
	
	 Map<Long,Long> gallaryIdsMap = new HashMap<Long,Long>();
	 Map<Long,Long> fileIdsMap = new HashMap<Long,Long>();
	 UserProfileVO userProfileVO = null;
	   if(candidateIds != null && !candidateIds.isEmpty()){
		   List<FileGallary> gallariesList = fileGallaryDAO.getCandidateGallaryDetailsForProfilePageStreaming(fromDate,toDate,new HashSet<Long>(candidateIds));
	       if(gallariesList != null && gallariesList.size() > 0){
	    	   for(FileGallary fileGallary:gallariesList){
	    		   Long fileId = fileGallary.getFile().getFileId();
	    		   Long gallaryId = fileGallary.getGallary().getGallaryId();
	    		   if(gallaryIdsMap.get(gallaryId) == null){
	    			   if(fileIdsMap.get(fileId) == null){
	    				   gallaryIdsMap.put(gallaryId,gallaryId);
	    				   fileIdsMap.put(fileId, fileId);
	    				    userProfileVO = new UserProfileVO();
	    				    userProfileVO.setPageType("candidate");
	    				   userProfileVOList.add(populateDataToVOForCandidate(fileGallary,userProfileVO));
	    			   }
	    		   }
	    	   }
	       }
	   }
	   
	   if(partyIds != null && !partyIds.isEmpty()){
		   List<Object[]> gallariesList = fileGallaryDAO.getPartyGallaryDetailsForSubscribers(fromDate,toDate,new HashSet<Long>(partyIds),null);
		   if(gallariesList != null && gallariesList.size() > 0){
			   for(Object[] array : gallariesList){
				   FileGallary fileGallary =(FileGallary)array[0];
	    		   Long fileId = fileGallary.getFile().getFileId();
	    		   Long gallaryId = fileGallary.getGallary().getGallaryId();
	    		   if(gallaryIdsMap.get(gallaryId) == null){
	    			   if(fileIdsMap.get(fileId) == null){
	    				   gallaryIdsMap.put(gallaryId,gallaryId);
	    				   fileIdsMap.put(fileId, fileId);
	    				    userProfileVO = new UserProfileVO();
	    				    userProfileVO.setPageType("party");
	    				    userProfileVO.setId((Long)array[1]);
	    				    userProfileVO.setName(array[2]!=null?array[2].toString():"");
	    				    userProfileVO.setHeaderTitle("Updates for "+array[2]!=null?array[2].toString():"");
	    				    userProfileVO.setImageUrl("images/party_flags/"+array[2].toString().toUpperCase()+".png");
	    				   userProfileVOList.add(populateDataToVOForCandidate(fileGallary,userProfileVO));
	    			   }
	    		   }
	    	   }
	       }
	   }
	   
	   if(specialPageIds != null && !specialPageIds.isEmpty()){
		   List<Object[]> gallariesList = fileGallaryDAO.getSpecialPageGallaryDetailsForSubscribers(fromDate,toDate,new HashSet<Long>(specialPageIds),null);
		   if(gallariesList != null && gallariesList.size() > 0){
			   for(Object[] array : gallariesList){
				   FileGallary fileGallary =(FileGallary)array[0];
	    		   Long fileId = fileGallary.getFile().getFileId();
	    		   Long gallaryId = fileGallary.getGallary().getGallaryId();
	    		   if(gallaryIdsMap.get(gallaryId) == null){
	    			   if(fileIdsMap.get(fileId) == null){
	    				   gallaryIdsMap.put(gallaryId,gallaryId);
	    				   fileIdsMap.put(fileId, fileId);
	    				    userProfileVO = new UserProfileVO();
	    				    userProfileVO.setPageType("specialpage");
	    				    userProfileVO.setId((Long)array[1]);
	    				    userProfileVO.setName(array[2]!=null?array[2].toString():"");
	    				    userProfileVO.setImageUrl(array[3]!=null?array[3].toString():"");
	    				    userProfileVO.setHeaderTitle("Updates for "+array[2]!=null?array[2].toString():"");
	    				   userProfileVOList.add(populateDataToVOForCandidate(fileGallary,userProfileVO));
	    			   }
	    		   }
	    	   }
	       }
	   }
	   Collections.sort(userProfileVOList,userProfileVOSort);
	}catch(Exception e){
		log.error("Exception rised in getPartyAnalystLatestUpdates method ",e);
	}
	   return userProfileVOList;
   }

	public UserProfileVO populateDataToVOForCandidate(FileGallary fileGallary,UserProfileVO userProfileVO){
		
		
		 if(fileGallary.getGallary().getContentType().getContentTypeId() == 1l){
			 userProfileVO.setImageUrl(getPath(fileGallary,"photo"));
			 userProfileVO.setType("photo");
		 }else if(fileGallary.getGallary().getContentType().getContentTypeId() == 2l){
			 if(fileGallary.getFile().getFilePath() != null){
				 userProfileVO.setImageUrl(fileGallary.getFile().getFilePath());
			 }else{
				 if(userProfileVO.getPageType().equals("candidate"))
				   userProfileVO.setImageUrl("images/candidates/"+fileGallary.getGallary().getCandidate().getCandidateId()+".jpg");
			 }
			 userProfileVO.setType("news");
		 }else if(fileGallary.getGallary().getContentType().getContentTypeId() == 4l){
			 userProfileVO.setImageUrl(getPath(fileGallary,"video"));
			 userProfileVO.setType("video");
		 }
		 if(userProfileVO.getPageType().equals("candidate")){
		   userProfileVO.setId(fileGallary.getGallary().getCandidate().getCandidateId());
		   userProfileVO.setName(fileGallary.getGallary().getCandidate().getLastname());
		   userProfileVO.setHeaderTitle("Updates for "+fileGallary.getGallary().getCandidate().getLastname());
		   userProfileVO.setFileLink("candidateElectionResultsAction.action?candidateId="+fileGallary.getGallary().getCandidate().getCandidateId()+"&contentId="+fileGallary.getFileGallaryId());
		 }else if(userProfileVO.getPageType().equals("party")){
			 userProfileVO.setFileLink("partyPageAction.action?partyId="+userProfileVO.getId()+"&contentId="+fileGallary.getFileGallaryId());
			 
		 }else if(userProfileVO.getPageType().equals("specialpage")){
			 userProfileVO.setFileLink("specialPageAction.action?specialPageId="+userProfileVO.getId()+"&contentId="+fileGallary.getFileGallaryId());
			 
		 }
		 userProfileVO.setTitle(fileGallary.getFile().getFileTitle());
		 userProfileVO.setDescription(fileGallary.getFile().getFileDescription());
		 
		 userProfileVO.setFileGallaryId(fileGallary.getFileGallaryId());
		
		 getDateDifference(fileGallary.getFile().getFileDate(),fileGallary.getUpdateddate(),userProfileVO);
		 
		 return userProfileVO;
		 
	}
	
	private String getPath(FileGallary fileGallary,String type){
		String filePath = null;
		Set<FileSourceLanguage> fileSourceLanguageSet = fileGallary.getFile().getFileSourceLanguage();
		List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
		 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
		 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList)
		 {
			 if(filePath == null){
			   Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			   List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
			   Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
			 
			     for(FilePaths singleFilePath : filePathsList)
			     {
			    	 if(filePath == null)
				      filePath = singleFilePath.getFilePath(); 
			    	 else
			    	  break;
			     }
			  }else{
				  break;
			  }
		 }
		  if(type.equalsIgnoreCase("video")){
			  filePath = "http://img.youtube.com/vi/"+filePath+"/1.jpg";
		  }
		 return filePath;
	}
	
	private void getDateDifference(Date fileDate,Date fileGallaryDate,UserProfileVO userProfileVO){
		DateUtilService dateUtilService = new DateUtilService();
		Date currentDate = dateUtilService.getCurrentDateAndTime();
		Date previousDate = null;
		if(fileDate != null){
			Calendar calendarFile = Calendar.getInstance();
			Calendar calendarFileGal = Calendar.getInstance();
			calendarFile.setTime(fileDate);
			calendarFileGal.setTime(fileGallaryDate);
			calendarFile.add(Calendar.HOUR, calendarFileGal.get(Calendar.HOUR_OF_DAY));
			calendarFile.add(Calendar.MINUTE, calendarFileGal.get(Calendar.MINUTE));
			calendarFile.add(Calendar.SECOND, calendarFileGal.get(Calendar.SECOND));
			
			previousDate = calendarFile.getTime();
		}else{
			previousDate = fileGallaryDate;
		}
		userProfileVO.setDate(previousDate);
		long[] difference = getdifference(previousDate,currentDate);
		if(difference[0] > 2l){
		 userProfileVO.setDateDiff(difference[0]+" days ago");
		}else{
			if(difference[0] == 0l){
				userProfileVO.setDateDiff(difference[1]+"h "+difference[2]+"m ago");
			}else{
				userProfileVO.setDateDiff(difference[0]+"d "+difference[1]+"h "+difference[2]+"m ago");
			}
		}
	}
	
	private long[] getdifference(Date previousDate,Date currentDate){
		
		long[] result = new long[5];
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(previousDate);

        long t1 = cal.getTimeInMillis();
        cal.setTime(currentDate);

        long diff = Math.abs(cal.getTimeInMillis() - t1);
        final int ONE_DAY = 1000 * 60 * 60 * 24;
        final int ONE_HOUR = ONE_DAY / 24;
        final int ONE_MINUTE = ONE_HOUR / 60;
        final int ONE_SECOND = ONE_MINUTE / 60;

        long d = diff / ONE_DAY;
        diff %= ONE_DAY;

        long h = diff / ONE_HOUR;
        diff %= ONE_HOUR;

        long m = diff / ONE_MINUTE;
        diff %= ONE_MINUTE;

        long s = diff / ONE_SECOND;
        long ms = diff % ONE_SECOND;
        result[0] = d;
        result[1] = h;
        result[2] = m;
        result[3] = s;
        result[4] = ms;

        return result;
	}
	public static Comparator<UserProfileVO> userProfileVOSort = new Comparator<UserProfileVO>()
			{
						  
		      public int compare (UserProfileVO m1, UserProfileVO m2){
                return m2.getDate().compareTo(m1.getDate());
               }
			};
}
