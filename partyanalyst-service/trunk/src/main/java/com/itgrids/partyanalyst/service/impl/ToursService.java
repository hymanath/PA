package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursInputVO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;
import com.itgrids.partyanalyst.service.IToursService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

public class ToursService implements IToursService {
	 
	private final static Logger LOG = Logger.getLogger(ToursService.class);
	
	private ISelfAppraisalDesignationDAO selfAppraisalDesignationDAO;
	private ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO;
	private ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO;
	private ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO;
	private IConstituencyDAO constituencyDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private CommonMethodsUtilService commonMethodsUtilService ;
	private TransactionTemplate transactionTemplate;
	
	public void setSelfAppraisalDesignationDAO(
			ISelfAppraisalDesignationDAO selfAppraisalDesignationDAO) {
		this.selfAppraisalDesignationDAO = selfAppraisalDesignationDAO;
	}
	public void setSelfAppraisalCandidateDAO(
			ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO) {
		this.selfAppraisalCandidateDAO = selfAppraisalCandidateDAO;
	}
	public void setSelfAppraisalCandidateDetailsDAO(
			ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO) {
		this.selfAppraisalCandidateDetailsDAO = selfAppraisalCandidateDetailsDAO;
	}
    public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
    public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
    public void setSelfAppraisalCandidateLocationDAO(ISelfAppraisalCandidateLocationDAO selfAppraisalCandidateLocationDAO) {
		this.selfAppraisalCandidateLocationDAO = selfAppraisalCandidateLocationDAO;
	}
    public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public ResultStatus saveTourDtls(ToursInputVO toursInputVO,Long userId, Map<File,String> mapfiles){  
		LOG.info("Entered into saveTourDtls() of ToursService{}");
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			DateUtilService dateUtilService = new DateUtilService();
			String destPath = saveUploadFile(mapfiles);  
			SelfAppraisalCandidateDetails selfAppraisalCandidateDetails = new SelfAppraisalCandidateDetails();
			selfAppraisalCandidateDetails.setSelfAppraisalCandidateId(toursInputVO.getCandidateId());
			selfAppraisalCandidateDetails.setMonth(toursInputVO.getMonth());
			selfAppraisalCandidateDetails.setYear(toursInputVO.getYear());
			if(toursInputVO.getOwnLocationScopeId() != null){
				selfAppraisalCandidateDetails.setOwnLocationScopeId(toursInputVO.getOwnLocationScopeId());
			}
			if(toursInputVO.getOwnLocationId() != null){
				selfAppraisalCandidateDetails.setOwnLocationValue(toursInputVO.getOwnLocationId());
			}
			if(toursInputVO.getOwnTours() != null){ 
				selfAppraisalCandidateDetails.setOwnTours(toursInputVO.getOwnTours());
			}
			if(toursInputVO.getInchargeLocationScopeId() != null){
				selfAppraisalCandidateDetails.setInchargeLocationScopeId(toursInputVO.getInchargeLocationScopeId());
			}
			if(toursInputVO.getInchargeLocationId() != null){
				selfAppraisalCandidateDetails.setInchargeLocationValue(toursInputVO.getInchargeLocationId());
			}
			if(toursInputVO.getInchargeTours() != null){
				selfAppraisalCandidateDetails.setInchargeTours(toursInputVO.getInchargeTours());
			}
			selfAppraisalCandidateDetails.setRemarks(toursInputVO.getRemarks());      
			selfAppraisalCandidateDetails.setReportPath(destPath);         
			selfAppraisalCandidateDetails.setInsertedBy(userId);
			selfAppraisalCandidateDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			selfAppraisalCandidateDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			selfAppraisalCandidateDetailsDAO.save(selfAppraisalCandidateDetails); 
			resultStatus.setResultCode(1);
			resultStatus.setMessage("Saved");  
			return resultStatus;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in saveTourDtls() of ToursService{}", e);
			resultStatus.setResultCode(0);
			resultStatus.setMessage("Failed");
			return resultStatus;
		}
	}
	public String saveUploadFile(Map<File,String> mapfiles){
		try{
			String destPath = new String();
			StringBuilder returnPath = new StringBuilder();
			String folderName = folderCreation();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);

			int temp = month+1;
			String monthText = getMonthForInt(temp);
			
			StringBuilder pathBuilder = null;
			StringBuilder str ;
			int i = 0;
			for (Map.Entry<File, String> entry : mapfiles.entrySet()){
				pathBuilder = new StringBuilder();
				str = new StringBuilder();
				Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				destPath = folderName+"\\"+randomNumber+"."+entry.getValue();           
				pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".").append(entry.getValue());
				if(i == 0){
					returnPath.append(destPath);
				}else{
					returnPath.append(","+destPath);
				}
				i++;
				str.append(randomNumber).append(".").append(entry.getValue());
				ActivityService activityService = new ActivityService();  
				String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
				 
				if(fileCpyStts.equalsIgnoreCase("error")){
					LOG.error(" Exception Raise in copying file");  
						throw new ArithmeticException();
				}
			}  
			return returnPath.toString(); 
		}catch(Exception e){
			e.printStackTrace();  
		}
		return null;
	}
	public static String folderCreation(){
		try {
			LOG.debug(" in FolderForArticle ");
	  		
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			
			String staticPath = IConstants.TOUR_DOCUMENTS;
			String tourDir = ActivityService.createFolder("E:/"+staticPath);  
			tourDir = ActivityService.createFolder("E:/"+staticPath+"/"+IConstants.TOUR_DOCUMENTS);
			 
			String yr = String.valueOf(year); // YEAR YYYY
			
			StringBuilder str = new StringBuilder();
			int temp = month+1;
			String monthText = getMonthForInt(temp);
			str.append(monthText).append("-").append(yr);  
			 
			 
			String mnthYr = str.toString();
			String mnthYrDir = staticPath+"/"+IConstants.TOUR_DOCUMENTS+"/"+mnthYr;
			String mnthDirSts = ActivityService.createFolder("E:/"+mnthYrDir);      
			if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				return "FAILED";
			}
			 
			return "E:\\"+staticPath+"\\"+IConstants.TOUR_DOCUMENTS+"\\"+mnthYr;  
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");  
			return "FAILED";
		}
	}
	public static String getMonthForInt(int num) {    
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11 ) {
			month = months[num-1];
		}
		return month;  
    }
	
    
	public List<ToursBasicVO> getDesigationList(){
		List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
		try{
			List<Object[]> rtrnDsgntnObjLst = selfAppraisalDesignationDAO.getDesiganationList();
			 if(rtrnDsgntnObjLst != null && !rtrnDsgntnObjLst.isEmpty()){
				 for(Object[] param:rtrnDsgntnObjLst){
					 resultList.add(new ToursBasicVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));	 
				 }
			 }
		}catch(Exception e){
			LOG.error("Error Occured at getDesigationList() in ToursService class",e);	
		}
		return resultList;
	}	
    public List<ToursBasicVO> getConstituenciesList(Long stateId){
    	List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
    	try{
			List<Object[]> rtrnCnsttuncyObjLst = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			 if(rtrnCnsttuncyObjLst != null && !rtrnCnsttuncyObjLst.isEmpty()){
				 for(Object[] param:rtrnCnsttuncyObjLst)
					 resultList.add(new ToursBasicVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]))); 
			 }
		}catch(Exception e) {
			LOG.error("Error Occured at getConstituenciesList() in ToursService class",e);	
		}
    	return resultList;
    }
    public List<ToursBasicVO> getCandidateList(Long designationId){
    	List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
    	try{
    		List<Object[]> rtrnCandiateObjLst = selfAppraisalCandidateDAO.getCandiateList(designationId);
    			if(rtrnCandiateObjLst != null && !rtrnCandiateObjLst.isEmpty()){
    				for(Object[] param:rtrnCandiateObjLst){
    					ToursBasicVO candidateVO = new ToursBasicVO();
    					candidateVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    					candidateVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[1]));
    					candidateVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
    					resultList.add(candidateVO);
    				}
    			}
    	}catch(Exception e){
    		LOG.error("Error Occured at getCandidateList() in ToursService class",e);
    	}
    	return resultList;
    }
    public ToursBasicVO getCandiateDetails(Long candidateId){
    	 ToursBasicVO resultVO = new ToursBasicVO();
    	
    	 try{
    	   Object[] candiatObj = selfAppraisalCandidateDAO.getCandiateDetailsByCandidateId(candidateId);
    	  	if(candiatObj != null && candiatObj.length > 0){
    		   resultVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(candiatObj[0]));
    		   resultVO.setName(commonMethodsUtilService.getStringValueForObject(candiatObj[1]));
    		   resultVO.setVoaterId(commonMethodsUtilService.getLongValueForObject(candiatObj[2]));
    		   resultVO.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(candiatObj[3]));
    		   resultVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(candiatObj[4]));
    		   resultVO.setImage(commonMethodsUtilService.getStringValueForObject(candiatObj[5]));
    	  	}
    	   List<Object[]> rtrnCandiateLocationObjList = selfAppraisalCandidateLocationDAO.getCandiateLocationScopeIdAndValues(candidateId);	
    	  	if(rtrnCandiateLocationObjList != null && !rtrnCandiateLocationObjList.isEmpty()){
    	  		List<ToursBasicVO> locationList = new ArrayList<ToursBasicVO>();
    	  		for(Object[] param:rtrnCandiateLocationObjList){
    	  			ToursBasicVO locationVO = new ToursBasicVO();
    	  			locationVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[0]));
    	  			locationVO.setLocationValue(commonMethodsUtilService.getLongValueForObject(param[1]));
    	  			locationVO.setType(commonMethodsUtilService.getStringValueForObject(param[2]));
    	  			locationList.add(locationVO);
    	  		}
    	  		resultVO.getSubList().addAll(locationList);
    	  	}
    	}catch(Exception e){
    		LOG.error("Error Occured at getCandiateDetails() in ToursService class",e);	
    	}
    	return resultVO;
    }
    @SuppressWarnings("unchecked")
	public List<ToursBasicVO> getSearchMembersDetails(final Long locationId,String searchType,String searchValue,final Long designationId){
    	
    	final Map<Long,ToursBasicVO> membersMap = new HashMap<Long, ToursBasicVO>(0);
    	List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
    	try{
    		
    	   List<Object[]> objMembersDtlsLst = tdpCadreDAO.getTdpMembersDetaislBasedOnSearchCriteria(locationId,searchType, searchValue);
    		  if(objMembersDtlsLst != null && objMembersDtlsLst.size() > 0){
    			  for(Object[] param:objMembersDtlsLst){
    				  ToursBasicVO memberVO = new ToursBasicVO();
    				  memberVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				  memberVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
    				  memberVO.setVoaterId(commonMethodsUtilService.getLongValueForObject(param[2]));
    				  memberVO.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[3]));
    				  memberVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[4]));
    				  memberVO.setImage(commonMethodsUtilService.getStringValueForObject(param[5]));
    				  membersMap.put(memberVO.getTdpCadreId(), memberVO);
    			  }
    		  }
    		  resultList = (List<ToursBasicVO>)transactionTemplate.execute(new TransactionCallback() {
    				public Object doInTransaction(TransactionStatus arg0) {
    					
		    		  List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
		    		  
		    		 if(membersMap != null && !membersMap.isEmpty()){
		    			 for(Entry<Long, ToursBasicVO> entry:membersMap.entrySet()){
		    				 List<ToursBasicVO> locationList = new ArrayList<ToursBasicVO>();
		 		    		 Long candidateId = selfAppraisalCandidateDAO.getCandidateId(entry.getKey(),designationId);
		    				 if(candidateId == null ){
		    					 
		    					 SelfAppraisalCandidate selfAppraisalCandidate = new SelfAppraisalCandidate();
		    					 
		    					 selfAppraisalCandidate.setTdpCadreId(entry.getValue().getTdpCadreId());
		    					 selfAppraisalCandidate.setSelfAppraisalDesignationId(8l);//other designation
		    					 selfAppraisalCandidate.setIsActive("Y");
		    					 selfAppraisalCandidate = selfAppraisalCandidateDAO.save(selfAppraisalCandidate);
		    					 
		    					 SelfAppraisalCandidateLocation selfAppraisalCandidateLocation = new SelfAppraisalCandidateLocation();
		    					 
		    					 selfAppraisalCandidateLocation.setSelfAppraisalCandidateId(selfAppraisalCandidate.getSelfAppraisalCandidateId());
		    					 selfAppraisalCandidateLocation.setSelfAppraisalCandidateLocationId(3l);
		    					 selfAppraisalCandidateLocation.setLocationValue(locationId);
		    					 selfAppraisalCandidateLocation.setType("Own");
		    					 selfAppraisalCandidateLocationDAO.save(selfAppraisalCandidateLocation);
		    					 
		    					 ToursBasicVO locationVO = new ToursBasicVO();
		    					 
		    					 locationVO.setLocationScopeId(3l);
		    					 locationVO.setLocationValue(locationId);
		    					 locationVO.setType("Own");
		    					 locationList.add(locationVO);
		    					 
		    					 entry.getValue().getSubList().addAll(locationList);
		    				 }else{
		    					List<Object[]> rtrnCandiateLocationObjList = selfAppraisalCandidateLocationDAO.getCandiateLocationScopeIdAndValues(candidateId);
		    					 if(rtrnCandiateLocationObjList != null && !rtrnCandiateLocationObjList.isEmpty()){
		    						for(Object[] param:rtrnCandiateLocationObjList) {
		    							ToursBasicVO locationVO = new ToursBasicVO();
		    							locationVO.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[0]));
		    		    	  			locationVO.setLocationValue(commonMethodsUtilService.getLongValueForObject(param[1]));
		    		    	  			locationVO.setType(commonMethodsUtilService.getStringValueForObject(param[2]));
		    		    	  			locationList.add(locationVO);
		    						}
		    						entry.getValue().getSubList().addAll(locationList);
		    					 }
		    				 }
		    			 }
		    		}
	    		 if(membersMap != null && !membersMap.isEmpty() ){
	    			 resultList.addAll(membersMap.values());
	    			 membersMap.clear();
	    		 }
    		 return resultList;
    		}
    		});
    	}catch(Exception e){
    		LOG.error("Error Occured at getSearchMembersDetails() in ToursService class",e);	
    	}
    	return resultList;
    }
    public ToursBasicVO getDesignationDtls(Long desigId, String startDateStr, String endDateStr){
    	LOG.info("Entered into getDesignationDtls() of ToursService{}");
    	try{
    		ToursBasicVO toursBasicVO = new ToursBasicVO();
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date startDate = null;
    		Date endDate = null;
    		if(startDateStr != null && startDateStr.length() > 0 && endDateStr != null && endDateStr.length() > 0){
    			startDate = sdf.parse(startDateStr);
    			endDate = sdf.parse(endDateStr);  
    		}
    		List<Long> desigIdList = new ArrayList<Long>();
    		desigIdList.add(desigId);
    		List<Object[]> desigDtls = selfAppraisalCandidateDAO.getTotalLeadersDesignationBy(desigIdList);
    		
    		if(desigDtls != null && desigDtls.size() > 0){
    			toursBasicVO.setCandidateCount(desigDtls.get(0)[2] != null ? (Long)desigDtls.get(0)[2] : 0l);
    		}
    		List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursLeadersDetails(startDate,endDate,desigIdList);
    		if(memDtlsList != null && memDtlsList.size() > 0){
    			toursBasicVO.setSelectedCandCount(memDtlsList.get(0)[1] != null ? (Long)memDtlsList.get(0)[1] : 0l);
    			toursBasicVO.setTotalTour((memDtlsList.get(0)[2] != null ? (Long)memDtlsList.get(0)[2] : 0l) + (memDtlsList.get(0)[3] != null ? (Long)memDtlsList.get(0)[3] : 0l));
    		}    
    		return toursBasicVO; 
    		
    	}catch(Exception e){  
    		e.printStackTrace();
    		LOG.error("Error Occured at getDesignationDtls() in ToursService class",e);
    	}
    	return null;  
    }
    public List<ToursBasicVO> getMemDtls(Long desigId, String startDateStr, String endDateStr){
    	LOG.info("Entered into getDesignationDtls() of ToursService{}");
    	try{
    		ToursBasicVO toursBasicVO = new ToursBasicVO();
    		List<ToursBasicVO> basicVOs = new ArrayList<ToursBasicVO>();
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date startDate = null;
    		Date endDate = null;
    		if(startDateStr != null && startDateStr.length() > 0 && endDateStr != null && endDateStr.length() > 0){
    			startDate = sdf.parse(startDateStr);
    			endDate = sdf.parse(endDateStr);  
    		}
    		
    		//get total candidate in the same designation.
    		List<Long> candidateList = selfAppraisalCandidateDAO.getCandidateList(desigId);
    		//get candidate dtls.
    		StringBuilder builder = new StringBuilder();
    		if(candidateList != null && candidateList.size() > 0){
    			List<Object[]> candidateDtlsList = selfAppraisalCandidateDetailsDAO.getCandidateDtlsList(startDate,endDate,candidateList);
    			if(candidateDtlsList != null && candidateDtlsList.size() > 0){
    				for(Object[] param : candidateDtlsList){
    					toursBasicVO = new ToursBasicVO();  
    					toursBasicVO.setId(param[0] != null ? (Long)param[0] : 0l);
    					toursBasicVO.setName(param[2] != null ? param[2].toString() : "");
    					toursBasicVO.setTotalTour((param[5] != null ? (Long)param[5] : 0l) + (param[6] != null ? (Long)param[6] : 0l));
    					toursBasicVO.setYear(param[4] != null ? (Long)param[4] : 0l);
    					toursBasicVO.setMonth(param[3] != null ? param[3].toString() : "");
    					toursBasicVO.setComment(param[7] != null ? param[7].toString() : "");
    					builder = new StringBuilder();  
    					if(param[8] != null){    
    						if(param[8].toString().trim().length() > 0){
    							toursBasicVO.setFilePath(param[8].toString().trim());
    							String strArr[] = param[8].toString().trim().split(",");
        						for(int i = 0 ; i < strArr.length ; i++){
        							if(i == 0){
        								builder.append((strArr[i]).split("\\.")[1]);
        							}else{
        								builder.append(","+strArr[i].split("\\.")[1]);  
        							}
        						}
        						toursBasicVO.setType(builder.toString());
    						}
    					}
    					basicVOs.add(toursBasicVO);
    				}
    			}  
    		}  
    		return basicVOs;
    	}catch(Exception e){  
    		e.printStackTrace();
    		LOG.error("Error Occured at getDesignationDtls() in ToursService class",e);
    	}
    	return null;
    }
    public List<ToursBasicVO> getToursDetailsOverview(String fromDateStr,String toDateStr){
    	List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
    	Map<Long,ToursBasicVO> leadersDetailsMap = new HashMap<Long, ToursBasicVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		Date fromDate=null;
    	try{
    		if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
				 fromDate = sdf.parse(fromDateStr);
			 }
    		 List<Object[]> rtrnLeadersDtlsObjLst = selfAppraisalCandidateDAO.getTotalLeadersDesignationBy(null);
    		 	if(rtrnLeadersDtlsObjLst != null && !rtrnLeadersDtlsObjLst.isEmpty()){
    		 		for(Object[] param:rtrnLeadersDtlsObjLst){
    				  ToursBasicVO leaderVO = new ToursBasicVO();
    				  leaderVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				  leaderVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
    				  leaderVO.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
    				  leadersDetailsMap.put(leaderVO.getId(), leaderVO);
    		 		}
    		 }
    		List<Object[]> rtrnLdrsTrsSbmttdDtlsObjLst = selfAppraisalCandidateDetailsDAO.getSubmittedToursLeadersDetails(fromDate, toDate,null);
    		 if(rtrnLdrsTrsSbmttdDtlsObjLst != null && !rtrnLdrsTrsSbmttdDtlsObjLst.isEmpty()){
    			 for(Object[] param:rtrnLdrsTrsSbmttdDtlsObjLst){
    				 ToursBasicVO leaderVO = leadersDetailsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    				   if(leaderVO != null ){
    					   leaderVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
    					   leaderVO.setNotSubmitedLeaserCnt(leaderVO.getNoOfLeaderCnt()-leaderVO.getSubmitedLeaderCnt());
    					   leaderVO.setOwnToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
    					   leaderVO.setInchargerToursCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
    					   leaderVO.setTotalSubmittedToursCnt(leaderVO.getOwnToursCnt()+leaderVO.getInchargerToursCnt());
    					   Double averageTours = leaderVO.getTotalSubmittedToursCnt().doubleValue()/leaderVO.getSubmitedLeaderCnt().doubleValue();
    					   leaderVO.setAverageTours(averageTours);
    				   }
    			 }
    		 }
    		 if(leadersDetailsMap != null && leadersDetailsMap.size() > 0){
    			 resultList.addAll(leadersDetailsMap.values());
    			 leadersDetailsMap.clear();
    		 }
   	}catch(Exception e) {
			LOG.error("Error Occured at getToursDetailsOverview() in ToursService class",e);	
		}
    	return resultList;
    }
    public ToursBasicVO getUniqueMemDtls(Long candidateDtlsId){
    	LOG.info("Entered into getUniqueMemDtls() of ToursService{}");
    	try{
    		StringBuilder builder = new StringBuilder();  
    		ToursBasicVO basicVO = new ToursBasicVO();
    		List<Object[]> memberDtls = selfAppraisalCandidateDetailsDAO.getMemberDtls(candidateDtlsId);
    		if(memberDtls != null && memberDtls.size() > 0){
    			for(Object[] param : memberDtls){
    				basicVO.setId(param[0] != null ? (Long)param[0] : 0l);
    				basicVO.setMonth(param[1] != null ? param[1].toString() : "");
    				basicVO.setYear(param[2] != null ? (Long)param[2] : 0l);
    				basicVO.setOwnToursCnt(param[5] != null ? (Long)param[5] : 0l);
    				basicVO.setInchargerToursCnt(param[8] != null ? (Long)param[8] : 0l);
    				basicVO.setComment(param[9] != null ? param[9].toString() : "");
    				if(param[10] != null){    
						if(param[10].toString().trim().length() > 0){
							basicVO.setFilePath(param[10].toString().trim());
							String strArr[] = param[10].toString().trim().split(",");
    						for(int i = 0 ; i < strArr.length ; i++){
    							if(i == 0){
    								builder.append((strArr[i]).split("\\.")[1]);
    							}else{
    								builder.append(","+strArr[i].split("\\.")[1]);  
    							}
    						}    
    						basicVO.setType(builder.toString()); 
						}
					}
    				if(param[4] != null){
    					if(((Long)param[4]).longValue() == 1l){
    						basicVO.setLocationScope("District");
    					}else if(((Long)param[4]).longValue() == 2l){
    						basicVO.setLocationScope("Parliament");
    					}else if(((Long)param[4]).longValue() == 3l){
    						basicVO.setLocationScope("Assembly");
    					}else{
    						basicVO.setLocationScope("Other");
    					}
    					
    				}
    				if(param[7] != null){   
    					if(((Long)param[7]).longValue() == 1l){
    						basicVO.setLocationScope("District");
    					}else if(((Long)param[7]).longValue() == 2l){
    						basicVO.setLocationScope("Parliament");
    					}else if(((Long)param[7]).longValue() == 3l){
    						basicVO.setLocationScope("Assembly");
    					}else{
    						basicVO.setLocationScope("Other");    
    					}
    				}
    			}
    		}      
    	return basicVO;    
    	}catch(Exception e){
    		e.printStackTrace();
    		LOG.error("Error Occured at getUniqueMemDtls() in ToursService class",e);
    	}
    	return null;
    }
};
