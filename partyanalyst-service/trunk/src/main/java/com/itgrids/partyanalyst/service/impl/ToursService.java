package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.DateFormatSymbols;
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
		try{
			DateUtilService dateUtilService = new DateUtilService();
			String destPath = saveUploadFile(mapfiles);  
			SelfAppraisalCandidateDetails selfAppraisalCandidateDetails = new SelfAppraisalCandidateDetails();
			selfAppraisalCandidateDetails.setSelfAppraisalCandidateId(toursInputVO.getCandidateId());
			selfAppraisalCandidateDetails.setMonth(toursInputVO.getMonth());
			selfAppraisalCandidateDetails.setYear(toursInputVO.getYear());
			selfAppraisalCandidateDetails.setOwnLocationScopeId(toursInputVO.getOwnLocationScopeId());
			selfAppraisalCandidateDetails.setOwnLocationId(toursInputVO.getOwnLocationId());
			selfAppraisalCandidateDetails.setOwnTours(toursInputVO.getOwnTours());
			selfAppraisalCandidateDetails.setInchargeLocationScopeId(toursInputVO.getInchargeLocationScopeId());
			selfAppraisalCandidateDetails.setInchargeLocationId(toursInputVO.getInchargeLocationId());
			selfAppraisalCandidateDetails.setInchargeTours(toursInputVO.getInchargeTours());
			selfAppraisalCandidateDetails.setRemarks(toursInputVO.getRemarks());
			selfAppraisalCandidateDetails.setReportPath(destPath);     
			selfAppraisalCandidateDetails.setInsertedBy(userId);
			selfAppraisalCandidateDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());  
			selfAppraisalCandidateDetailsDAO.save(selfAppraisalCandidateDetails);  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in saveTourDtls() of ToursService{}", e);
		}
		return null;
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
			String tourDir = ActivityService.createFolder("D:/"+staticPath);  
			tourDir = ActivityService.createFolder("D:/"+staticPath+"/"+IConstants.TOUR_DOCUMENTS);
			 
			String yr = String.valueOf(year); // YEAR YYYY
			
			StringBuilder str = new StringBuilder();
			int temp = month+1;
			String monthText = getMonthForInt(temp);
			str.append(monthText).append("-").append(yr);  
			 
			 
			String mnthYr = str.toString();
			String mnthYrDir = staticPath+"/"+IConstants.TOUR_DOCUMENTS+"/"+mnthYr;
			String mnthDirSts = ActivityService.createFolder("D:/"+mnthYrDir);      
			if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				return "FAILED";
			}
			 
			return "D:\\"+staticPath+"\\"+IConstants.TOUR_DOCUMENTS+"\\"+mnthYr;  
			 
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
    	  		resultVO.setSubList(locationList);
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
		    					 
		    					 entry.getValue().setSubList(locationList);
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
		    						entry.getValue().setSubList(locationList);
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
};
