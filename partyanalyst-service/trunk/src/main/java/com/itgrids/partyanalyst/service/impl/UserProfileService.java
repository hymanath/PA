package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICandidateSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ICommentCategoryCandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartySubscriptionsDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageSubscriptionsDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserConnectedtoDAO;
import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;
import com.itgrids.partyanalyst.dao.IUserPrivacySettingsDAO;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserRolesDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.PublicProfileStreemVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserProfileVO;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;
import com.itgrids.partyanalyst.model.CommentData;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserProfileService;
import com.itgrids.partyanalyst.utils.CommonStringUtils;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserProfileService implements IUserProfileService {
	
    private ICandidateSubscriptionsDAO candidateSubscriptionsDAO;
	private ISpecialPageDAO specialPageDAO;
	private IPartySubscriptionsDAO partySubscriptionsDAO;
	private IStateDAO stateDAO;
	private ISpecialPageSubscriptionsDAO specialPageSubscriptionsDAO;
	private IDistrictDAO districtDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private IConstituencyDAO constituencyDAO;
	private IUserProblemDAO userProblemDAO;
	private IUserFavoriteLinksDAO userFavoriteLinksDAO;
	private ICommentCategoryCandidateDAO commentCategoryCandidateDAO;
	private IConstituencySubscriptionsDAO constituencySubscriptionsDAO;
	private IUserConnectedtoDAO userConnectedtoDAO;
	private ICustomMessageDAO customMessageDAO;
	private IStaticDataService staticDataService;
	private ICandidateDetailsService candidateDetailsService ;
	private IUserPrivacySettingsDAO userPrivacySettingsDAO;
	private IUserRolesDAO userRolesDAO;
	SimpleDateFormat df = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
	private static final Logger log = Logger.getLogger(UserProfileService.class);
	private INominationDAO nominationDAO;
	
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}


	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}


   public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}


	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}


public IUserPrivacySettingsDAO getUserPrivacySettingsDAO() {
		return userPrivacySettingsDAO;
	}


	public void setUserPrivacySettingsDAO(
			IUserPrivacySettingsDAO userPrivacySettingsDAO) {
		this.userPrivacySettingsDAO = userPrivacySettingsDAO;
	}


public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}


	public ISpecialPageDAO getSpecialPageDAO() {
		return specialPageDAO;
	}


	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}


public IStateDAO getStateDAO() {
		return stateDAO;
	}


	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}


public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}


	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}


public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}


	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


public IUserFavoriteLinksDAO getUserFavoriteLinksDAO() {
		return userFavoriteLinksDAO;
	}


	public void setUserFavoriteLinksDAO(IUserFavoriteLinksDAO userFavoriteLinksDAO) {
		this.userFavoriteLinksDAO = userFavoriteLinksDAO;
	}


public IConstituencySubscriptionsDAO getConstituencySubscriptionsDAO() {
		return constituencySubscriptionsDAO;
	}


	public void setConstituencySubscriptionsDAO(
			IConstituencySubscriptionsDAO constituencySubscriptionsDAO) {
		this.constituencySubscriptionsDAO = constituencySubscriptionsDAO;
	}


public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


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


	
  public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}


	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}


	public ICommentCategoryCandidateDAO getCommentCategoryCandidateDAO() {
		return commentCategoryCandidateDAO;
	}


	public void setCommentCategoryCandidateDAO(
			ICommentCategoryCandidateDAO commentCategoryCandidateDAO) {
		this.commentCategoryCandidateDAO = commentCategoryCandidateDAO;
	}

	public IUserConnectedtoDAO getUserConnectedtoDAO() {
		return userConnectedtoDAO;
	}
	public void setUserConnectedtoDAO(IUserConnectedtoDAO userConnectedtoDAO) {
		this.userConnectedtoDAO = userConnectedtoDAO;
	}
	public ICustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}
	public void setCustomMessageDAO(ICustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
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
		 userProfileVO.setTitle(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileTitle()));
		 userProfileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(fileGallary.getFile().getFileDescription()));
		 
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
			
	public ProblemBeanVO getRecentConnectedPeople(Long userId){
		Map<Long,String> recentConnectedMap=new HashMap<Long,String>();
		List<Object[]> connectedPeople = userConnectedtoDAO.getRecentConnectedPeopleForUser(userId);
		ProblemBeanVO problemBeanVO=new ProblemBeanVO();
		
		for(Object[] obj:connectedPeople){
			if(Long.parseLong(obj[0].toString())!=userId){
				if(recentConnectedMap.get(Long.parseLong(obj[0].toString())) == null){
					recentConnectedMap.put(Long.parseLong(obj[0].toString()), obj[1].toString()+" "+obj[2].toString());
				}
			}
			if(Long.parseLong(obj[3].toString())!=userId){
				if(recentConnectedMap.get(Long.parseLong(obj[3].toString())) == null){
					recentConnectedMap.put(Long.parseLong(obj[3].toString()), obj[4].toString()+" "+obj[5].toString());
				}
			}
		}
		
		if(recentConnectedMap.size()>0){
			List<String> recentConnected=new ArrayList<String>(recentConnectedMap.values());
			if(recentConnected.size()>0){
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setRecentConnected(recentConnected);
			}
		}
		return problemBeanVO;
	}
   public List<ProblemBeanVO> getStreamingDataForPublicProfile(Long userId,int startIndex, int maxIndex)
   {
	  List<ProblemBeanVO> problemBeanVOList = new ArrayList<ProblemBeanVO>(0);
		try{
		   ProblemBeanVO problemBeanVO = null;
			List<Object[]> problemsList = userProblemDAO.getProblemDetailsForPublicProfile(userId, startIndex, maxIndex);
			List comments = commentCategoryCandidateDAO.getPostedPoliticalReasonsByUserId(userId, startIndex, maxIndex);
			
			String name = "";
			if(problemsList != null && problemsList.size() > 0)
			{
				for(Object[] params: problemsList)
				{
					problemBeanVO = new ProblemBeanVO();
					problemBeanVO.setResponseType("Problems");
					problemBeanVO.setProblemId((Long)params[0]);
					problemBeanVO.setProblem(params[1].toString());
					problemBeanVO.setDescription(params[2].toString());
					problemBeanVO.setReportedDate(params[3].toString());
					problemBeanVO.setExistingFrom(params[4].toString());
					problemBeanVO.setPostDate((Date)params[3]);
					problemBeanVO.setPostedDate(params[3].toString());
					problemBeanVO.setUserID((Long)params[10]);
					problemBeanVO.setUserImageURL((params[11] != null && params[11].toString().length() > 0)?params[11].toString() : "human.jpg");
					if(params[12] != null)
						name = params[12].toString();
					if(params[13] != null)
						name += " "+params[13].toString();
					problemBeanVO.setPostedPersonName(name);
					
					problemBeanVOList.add(problemBeanVO);
				}
			}
			
			if(comments != null && comments.size() > 0)
			{
				
				for(int i=0; i<comments.size();i++)
				{
					SimpleDateFormat formatter =  new SimpleDateFormat("yy/MM/dd");
					
					
					Object[] commentList = (Object[])comments.get(i);
					CommentData commentData = (CommentData)commentList[0];
					problemBeanVO = new ProblemBeanVO();
					problemBeanVO.setResponseType("Comments");
					problemBeanVO.setPostDate((Date)commentData.getCommentDate());
					problemBeanVO.setPostedDate(formatter.format(commentData.getCommentDate()));
					problemBeanVO.setDescription(commentData.getCommentDesc());
					problemBeanVO.setPostedPersonName(commentData.getCommentBy());
					problemBeanVO.setProblem(commentData.getCommentDataCategory().getCommentDataCategoryType());
					problemBeanVO.setProblemId((Long)commentList[1]); //CandidateId
					problemBeanVO.setName(commentList[2].toString()); //Candidate Name
					problemBeanVO.setProblemImpactLevelId((Long)commentList[5]); //rank
					problemBeanVO.setProblemType(commentList[6].toString()); //election type
					problemBeanVO.setConstituency(commentList[4].toString());
					problemBeanVO.setDepartment(commentList[3].toString()); //party name
					problemBeanVO.setUserImageURL((commentList[9]!=null && commentList[9].toString().length() > 0 )? commentList[9].toString() : "human.jpg");
					problemBeanVO.setUserID((Long)commentList[8]);
					problemBeanVOList.add(problemBeanVO);
					
				}
			}
					
			return problemBeanVOList;
			}catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in getStreamingDataForPublicProfile() Method,Exception is - "+e);
			return problemBeanVOList;
			}
	}	
   
   
   public List<ProblemBeanVO> getStreamingDataForPublicProfileByProfileId(Long userId,int startIndex, int maxIndex)
   {
	   List<ProblemBeanVO> problemBeanVOList = null;
	   List<ProblemBeanVO> list = getStreamingDataForPublicProfile(userId, startIndex, maxIndex);
	   
	   try{
		   Map<String, ProblemBeanVO> mapList = new HashMap<String, ProblemBeanVO>(0);
		   
		   if(list != null && list.size() > 0)
		   {
			   for(ProblemBeanVO params : list)
			   {
				   String key = params.getPostedDate();
				   if(mapList.get(key) == null)
				   {
					 ProblemBeanVO problemBeanVO = new ProblemBeanVO();
					 problemBeanVO.setPostedDate(key);
					 problemBeanVO.setPostDate(params.getPostDate());
					 List<ProblemBeanVO> proList = new ArrayList<ProblemBeanVO>(0);
					 proList.add(params);
					 problemBeanVO.setProblemBeanVOList(proList);
					 mapList.put(key, problemBeanVO);
				   }else{
					   ProblemBeanVO problemBeanVO = mapList.get(key);
					   problemBeanVO.getProblemBeanVOList().add(params); 
				   }
			   }
		   }
		   problemBeanVOList = new ArrayList<ProblemBeanVO>(mapList.values());

		   Collections.sort(problemBeanVOList, new Comparator<ProblemBeanVO>() {
			    public int compare(ProblemBeanVO m1, ProblemBeanVO m2) {
			        return m2.getPostDate().compareTo(m1.getPostDate());
			    }
			});
		   
		   return problemBeanVOList;  
	   }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getStreamingDataForPublicProfileByProfileId() Method, Exception - "+e);
		return problemBeanVOList;
	}
   }
   
   
   public String getUserConnectStatus(Long profileId, Long userId)
   {
	   String status = IConstants.NOTCONNECTED;
	   try{
		   List<Long> result = userConnectedtoDAO.getUserConnectStatus(profileId, userId);
		   if(result != null && result.size() > 0)
			   status = IConstants.CONNECTED;
		   else 
		   {
			   List<Object[]> list = customMessageDAO.getUserStatus(profileId, userId);
			   if(list != null && list.size() > 0)
				   status = IConstants.PENDING;
			   else
			   {
				   List<Object[]> list1 = customMessageDAO.getUserConnectStatus(profileId, userId);
				   if(list1 != null && list1.size() > 0)
					   status = "Respond to Friend";
			   }
		   }
		   
		   return status; 
	   }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getUserConnectStatus() Method, Exception - "+e);
		return status;
	}
   }
	
   public List<CandidateVO> getBlockRequestDetails(Long userId){
	   List<ProblemBeanVO> results = new ArrayList<ProblemBeanVO>();
	   List<Long> value = new ArrayList<Long>();
	   value.add(userId);
	   
	   ResultStatus resultStatus = new ResultStatus();
		List<CandidateVO> candiateVO = new ArrayList<CandidateVO>(0); 
		DataTransferVO dataTransferVO = new DataTransferVO();
		Long totalMsgCount = 0l;
		Long unreadMsgCount= 0l;
		String message,data;
		
	   List<Object> result = customMessageDAO.getAllMessagesForUser(value,"BLOCK");
	   if(result!=null && result.size()!=0){
			totalMsgCount = new Long(result.size());
			for(int i=0;i<result.size();i++){
				Object[] parms = (Object[])result.get(i);
				CandidateVO candidateResults = new CandidateVO();
				
				if(parms[0] != null)
					message = staticDataService.removeSpecialCharectersFromString(parms[0].toString());
				else
					message = "";
				
				
				
				data = StringUtils.replace(parms[0].toString(),"\n"," ");
				data = staticDataService.removeSpecialCharectersFromString(data);
				
				if(data.length() < 20)
					candidateResults.setData(data);
				else
					candidateResults.setData(data.substring(0, 19));
				
				candidateResults.setMessage(message);
				candidateResults.setId(new Long(parms[1].toString()));
				String candidateName="";
				
				if(parms[2]!=null)
					candidateName+=parms[2].toString().concat("  ").concat("  ");
				
				if(parms[3]!=null)
					candidateName+=parms[3].toString();

				candidateResults.setState(parms[4].toString());
				candidateResults.setDistrict(parms[5].toString());					
				candidateResults.setConstituencyName(parms[6].toString());
				candidateResults.setCandidateName(candidateName.toUpperCase());
				String status = (parms[8]!=null)?parms[8].toString():"";
				candidateResults.setStatus(status);
				if(IConstants.MSG_UNREAD.equalsIgnoreCase(status))
					unreadMsgCount++;
				
				candidateResults.setRecepientId(parms[10]!=null?(Long)parms[10]:null);
				candidateResults.setPostedDate(parms[9]!=null?DateService.timeStampConversion(parms[9].toString()):"");
				candidateResults.setCostumMessageId(parms[7]!=null?(Long)parms[7]:null);
				candiateVO.add(candidateResults);
			}
		}
	   return candiateVO;
   }
   
   public List<CandidateVO> getCandidatesToSubscribe(Long userId,Long stateId,String name,String type,Integer startIndex,Integer endIndex){
	   List<CandidateVO> candiates = null;
	   List<Long> candidateIds1 = new ArrayList<Long>();
	   List<Object[]> cadidateList = nominationDAO.getCandidatesToSubScribe(stateId,name,type,startIndex,endIndex);
	   List<Object[]> cadidateList1 = nominationDAO.getCandidatesToSubScribe(stateId,name,type,null,null);
	   List<Long> subscribedCandidates= candidateSubscriptionsDAO.getAllCandidatesSubscribedByUser(userId);
	   if(subscribedCandidates != null && subscribedCandidates.size() > 0)
		{
		   candiates = new ArrayList<CandidateVO>();
		   if(cadidateList1.size() > 0){
		   for(Object[] vo:cadidateList1)
		   {
			  if(!subscribedCandidates.contains((Long)vo[0]))
				{
				  candidateIds1.add((Long)vo[0]);
				}
		   }
		   }
		   if(cadidateList.size() > 0){
		   for(Object[] vo:cadidateList)
		   {
			   if(!subscribedCandidates.contains((Long)vo[0])){
			   CandidateVO candidate = new CandidateVO();
			   candidate.setCandidateId((Long)vo[0]) ;
			   candidate.setCandidateName(vo[1].toString());
			   candiates.add(candidate);
			   }
		   }
		   }
		}
	   if(candidateIds1.size()>0)
	   candiates.get(0).setTotalSearchCount(new Long(candidateIds1.size()));
	   return candiates;
   }
   
    
   public List<PublicProfileStreemVO> getPublicProfileDataStreaming(Long userId,Date todate,Date fromDate)
   {
	   List<PublicProfileStreemVO> returnList = new ArrayList<PublicProfileStreemVO>();
	   List<Object[]> assecPoliticanDetails = commentCategoryCandidateDAO.getCommentDataForPublicStreeming(userId,todate,fromDate);
	   if(assecPoliticanDetails != null && assecPoliticanDetails.size() > 0)
	   {
		   fillAcssenPoliticanVO(assecPoliticanDetails,returnList);
	   }
	   List<Object[]> problemDetails = userProblemDAO.getUserPostedProblemsForPublicStreaming(userId,todate,fromDate);
	   if(problemDetails != null && problemDetails.size() > 0)
	   {
		   fillproblemDetailsVO(problemDetails,returnList);
	   }
	   List<Object[]> candidateSubScriptions = candidateSubscriptionsDAO.getCandidateSubscriptionsForPublicProfileStreeming(userId,todate,fromDate);
	   if(candidateSubScriptions != null && candidateSubScriptions.size() > 0)
	   { 
		   fillSubscriptionsVO(candidateSubScriptions,returnList,"Subscriptions","Politician");
	   }
	   List<Object[]> partySubscriptions = partySubscriptionsDAO.getpartySubscriptionsForPublicProfileStreeming(userId,todate,fromDate);
	   if(partySubscriptions != null && partySubscriptions.size() > 0 )
	   {
		   fillSubscriptionsVO(partySubscriptions,returnList,"Subscriptions","Party");
	   }	
	   List<Object[]> specialPageSubscriptions = specialPageSubscriptionsDAO.getSpecialSubscriptionsForPublicProfileStreeming(userId, todate, fromDate);
	   if(specialPageSubscriptions != null && specialPageSubscriptions.size() > 0)
	   {
		   fillSubscriptionsVO(specialPageSubscriptions,returnList,"Subscriptions","Special Page");
	   }
	   List<Object[]> constituencySubscriptions = constituencySubscriptionsDAO.getConctituencySubscriptionsForPublicProfileStreeming(userId, todate, fromDate);
	   if(constituencySubscriptions != null && constituencySubscriptions.size() > 0)
	   {
		   fillSubscriptionsVO(constituencySubscriptions,returnList,"Subscriptions","Constituency");
	   }
	   List<Object[]> frindsList = userConnectedtoDAO.getUserConnectedForPublicProfileStreeming(userId, todate, fromDate);
	   if(frindsList != null && frindsList.size() > 0)
	   {
		   fillFrindsListVO(frindsList,returnList);
	   }
	   List<Object[]> faviroteLiksList = userFavoriteLinksDAO.getFavoroteLinksForPublicProfileStreeming(userId, todate, fromDate);
	   if(faviroteLiksList != null && faviroteLiksList.size() > 0)
	   {
		   fillFaviroteLinksVO(faviroteLiksList,returnList);
	   }
	   Collections.sort(returnList,PublicProfileStreemVOSort);
	   return returnList;
   }
   
   public static Comparator<PublicProfileStreemVO> PublicProfileStreemVOSort = new Comparator<PublicProfileStreemVO>()
			{
						  
		      public int compare (PublicProfileStreemVO m1, PublicProfileStreemVO m2){
               return m2.getDate().compareTo(m1.getDate());
              }
			};
   public void fillSubscriptionsVO(List<Object[]> fillData ,List<PublicProfileStreemVO> returnList,String type,String title)
   {
	   for (Object[] parms : fillData) {
		   PublicProfileStreemVO publicProfileStreemVO = new PublicProfileStreemVO();  
		    String name = "";
		    if(parms[2] != null && parms[3] != null)
			{
				name = "" +parms[2]+ " " +parms[3]+ "";
			}
			publicProfileStreemVO.setCommentedBy(name != null ? name : "");
		    publicProfileStreemVO.setUserImg(parms[4] != null ? parms[4].toString() :"");
		    if(title != "Constituency")
		    {
		    	publicProfileStreemVO.setImg(parms[5] != null ? parms[5].toString() :"");
		    }
		    else
		    {
		    	publicProfileStreemVO.setImg("");
		    }
		    String url = "";
		    if(title.equalsIgnoreCase("Politician"))
		    {
		    	url = "candidateElectionResultsAction.action?candidateId=" +parms[6]+ "";
		    }
		    else if(title.equalsIgnoreCase("Party"))
		    {
		    	url = "partyPageAction.action?partyId="+parms[6]+ "";
		    }
		    else if(title.equalsIgnoreCase("Special Page"))
		    {
		    	url = "specialPageAction.action?specialPageId="+parms[6]+ "";
		    }
		    else if(title.equalsIgnoreCase("Constituency"))
		    {
		    	url = "constituencyPageAction.action?constituencyId="+parms[5]+ "";
		    }
		    String description = "";
		    if(parms[0] != null)
		    {
		    	description = "Subscribed  to "   +parms[0]+ " " +title+ " page  " ;
		    }
		    publicProfileStreemVO.setUrl(url);
		    publicProfileStreemVO.setDescription(description != null ? description :"");
		    publicProfileStreemVO.setDate(parms[1] != null ? df.format(parms[1]).toString() :"");
		    publicProfileStreemVO.setTitle(type);
		    publicProfileStreemVO.setSubTitle(title);
		    returnList.add(publicProfileStreemVO);
	}
   }
   
   public void fillFrindsListVO(List<Object[]> fillData ,List<PublicProfileStreemVO> returnList)
   {
	     for (Object[] parms : fillData) {
			   PublicProfileStreemVO publicProfileStreemVO = new PublicProfileStreemVO();
			   String targetName = "";
			   String sourceName = "";
			   if(parms[0] != null && parms[1] != null)
			   {
				   targetName = "" +parms[0]+ " " +parms[1]+ "";
			   }
			   if(parms[4] != null && parms[5] != null)
			   {
				   sourceName = "" +parms[4]+ " " +parms[5]+ "";
			   }
			   String url = "";
			   if(parms[7] != null)
			   {
				   url = "userProfile.action?profileId=" +parms[7]+ "";
			   }
			   publicProfileStreemVO.setUrl(url);
			   String description = "" +targetName+ " and " +sourceName+ " now friends each other";
			   publicProfileStreemVO.setDate(parms[3] != null ? df.format(parms[3]).toString() :"");
			   publicProfileStreemVO.setImg(parms[2] != null ? parms[2].toString() :"");
			   publicProfileStreemVO.setDescription(description);
			   publicProfileStreemVO.setUserImg(parms[6] != null ? parms[6].toString() :"");
			   publicProfileStreemVO.setName(sourceName);
			   publicProfileStreemVO.setTitle("Friends");
			   returnList.add(publicProfileStreemVO); 
		   }
	  
   }
   public void fillFaviroteLinksVO(List<Object[]> faviroteLiksList,List<PublicProfileStreemVO> returnList)
   {
	   for (Object[] parms : faviroteLiksList) {
		   PublicProfileStreemVO publicProfileStreemVO = new PublicProfileStreemVO();
			publicProfileStreemVO.setDate(parms[2] != null ? df.format(parms[2]).toString() : "");
			publicProfileStreemVO.setTitle("Favirote Links");
			 String userName = "";
			    if(parms[3] != null && parms[4] != null)
				{
			    	userName = "" +parms[3]+ " " +parms[4]+ "";
				}
				publicProfileStreemVO.setCommentedBy(userName != null ? userName : "");
			    publicProfileStreemVO.setUserImg(parms[5] != null ? parms[5].toString() :"");
			String url = parms[1].toString();
			publicProfileStreemVO.setUrl(url);
			Long id = Long.valueOf(url.replaceAll("\\D+",""));
			if(parms[0].toString().equalsIgnoreCase("constituency"))
			{
				String name = constituencyDAO.get(id).getName();
				String description = "" +name+ " constituency page was added  as a favourite link";
				publicProfileStreemVO.setDescription(description);
			}
			else if(parms[0].toString().equalsIgnoreCase("district"))
			{
				String name = districtDAO.get(id).getDistrictName();
				String description = "" +name+ " district page was added  as a favourite link";
				publicProfileStreemVO.setDescription(description);
			}
			else if(parms[0].toString().equalsIgnoreCase("state"))
			{
				String name = stateDAO.get(id).getStateName();
				String description = "" +name+ " state page was added  as a favourite link";
				publicProfileStreemVO.setDescription(description);
			}
			else if(parms[0].toString().equalsIgnoreCase("specialpage"))
			{
				String name = specialPageDAO.get(id).getTitle();
				String description = "" +name+ " special page was added  as a favourite link";
				publicProfileStreemVO.setDescription(description);
			}
			returnList.add(publicProfileStreemVO);
		}
   }
   
   public void fillAcssenPoliticanVO(List<Object[]> assecPoliticanDetails,List<PublicProfileStreemVO> returnList)
   {
	   for (Object[] parms : assecPoliticanDetails) {
		   PublicProfileStreemVO publicProfileStreemVO = new PublicProfileStreemVO();  
		    publicProfileStreemVO.setCommentedBy(parms[0] != null ? parms[0].toString() : "");
		    String asscessPoliticanDiscription = "Posted a political reason for " +parms[1]+ " "  +parms[2]+ " The " +parms[3]+ " constituency " ;
		    publicProfileStreemVO.setDescription(asscessPoliticanDiscription);
		    publicProfileStreemVO.setImg(parms[4] != null ? parms[4].toString() : "");
		    publicProfileStreemVO.setPoliticalReasion(parms[5] != null ? parms[5].toString() : "");
		    publicProfileStreemVO.setPoliticalDescription(parms[6] != null ? parms[6].toString() : "");
		    publicProfileStreemVO.setDate(parms[7] != null ? df.format(parms[7]).toString() : "");
		    publicProfileStreemVO.setUserImg(parms[8] != null ? parms[8].toString() : "");
		    publicProfileStreemVO.setTitle("Asses Politician");
		    returnList.add(publicProfileStreemVO);
	   }
   }
   
   public void fillproblemDetailsVO(List<Object[]> problemDetails,List<PublicProfileStreemVO> returnList)
   {
	   for (Object[] parms : problemDetails) {
		   PublicProfileStreemVO publicProfileStreemVO = new PublicProfileStreemVO();  
			publicProfileStreemVO.setUserImg(parms[5] != null ? parms[5].toString() :"");
			String name = "";
			if(parms[3] != null && parms[4] != null)
			{
				name = "" +parms[3]+ " " +parms[4]+ "";
			}
			publicProfileStreemVO.setCommentedBy(name != null ? name : "");
			publicProfileStreemVO.setProblemTitle(parms[2] != null ? parms[2].toString() : "");
			publicProfileStreemVO.setDescription(parms[0] != null ? parms[0].toString() : "");
			publicProfileStreemVO.setExistinFrom(parms[6] != null ? parms[6].toString() : "");
			publicProfileStreemVO.setDate(parms[1] != null ? df.format(parms[1]).toString() : "");
			String url = "";
			if(parms[9] != null)
			{
				url = "completeProblemDetailsAction.action?problemId=" +parms[9]+ "";
			}
			String reportName = candidateDetailsService.getLocationDetails((Long)parms[7],(Long)parms[8]);
			String ProblemDiscription = "";
			if(reportName != null)
			{
				ProblemDiscription = "" +reportName+ " "  +parms[10]+ " Problem Posted By " +name+ "";
			}
			publicProfileStreemVO.setPoliticalDescription(ProblemDiscription);
			publicProfileStreemVO.setUrl(url);
		    publicProfileStreemVO.setTitle("Problem");
		    returnList.add(publicProfileStreemVO);
		}
   }
   
   public Long getUserAcessViw(Long userId)
   {
	   Long viewId = userPrivacySettingsDAO.getUserPrivicyViewDetails(userId);
	   return viewId;
   }
   
   public Long checkWeaterUserConnectedOrNot(Long sourcrId,Long targetId)
   {
	   Long userId = userConnectedtoDAO.getFrindsConnectdOrNot(sourcrId,targetId);
	   return userId;
   }
   
   public Long getUserTypeAcessViw(Long userId)
   {
	   Long viewId = userRolesDAO.checkForUserPublicStreeming(userId);
	   return viewId;
   }
}
