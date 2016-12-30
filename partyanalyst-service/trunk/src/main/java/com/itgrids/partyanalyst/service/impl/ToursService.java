package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDayTourDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDocumentDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateLocationNewDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationTargetDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalTourCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITourTypeDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.PMMinuteVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursInputVO;
import com.itgrids.partyanalyst.dto.ToursVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDayTour;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDocument;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateLocation;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
	
	private ITourTypeDAO tourTypeDAO; 
	private ISelfAppraisalTourCategoryDAO selfAppraisalTourCategoryDAO; 
	private ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO;   
	private IHamletDAO hamletDAO;
	private ISelfAppraisalCandidateDayTourDAO selfAppraisalCandidateDayTourDAO;
	private ISelfAppraisalCandidateDocumentDAO selfAppraisalCandidateDocumentDAO;
	
	private IUserAddressDAO userAddressDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private ICadreCommitteeService cadreCommitteeService;
	public IRegionScopesDAO regionScopesDAO;
	
	private ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO;
	
	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}
	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	public void setSelfAppraisalDesignationTargetDAO(
			ISelfAppraisalDesignationTargetDAO selfAppraisalDesignationTargetDAO) {
		this.selfAppraisalDesignationTargetDAO = selfAppraisalDesignationTargetDAO;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public void setSelfAppraisalCandidateDocumentDAO(
			ISelfAppraisalCandidateDocumentDAO selfAppraisalCandidateDocumentDAO) {
		this.selfAppraisalCandidateDocumentDAO = selfAppraisalCandidateDocumentDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public void setSelfAppraisalCandidateDayTourDAO(
			ISelfAppraisalCandidateDayTourDAO selfAppraisalCandidateDayTourDAO) {
		this.selfAppraisalCandidateDayTourDAO = selfAppraisalCandidateDayTourDAO;
	}
	public void setTourTypeDAO(ITourTypeDAO tourTypeDAO) {
		this.tourTypeDAO = tourTypeDAO;
	}
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
    
	public void setSelfAppraisalTourCategoryDAO(
			ISelfAppraisalTourCategoryDAO selfAppraisalTourCategoryDAO) {
		this.selfAppraisalTourCategoryDAO = selfAppraisalTourCategoryDAO;
	}
	
	public ISelfAppraisalCandidateLocationNewDAO getSelfAppraisalCandidateLocationNewDAO() {
		return selfAppraisalCandidateLocationNewDAO;
	}
	public void setSelfAppraisalCandidateLocationNewDAO(
			ISelfAppraisalCandidateLocationNewDAO selfAppraisalCandidateLocationNewDAO) {
		this.selfAppraisalCandidateLocationNewDAO = selfAppraisalCandidateLocationNewDAO;
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
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
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
			String yr = toursInputVO.getYear().toString();
			String mnth = "";
			if(toursInputVO.getMonth().equalsIgnoreCase("January")){
				mnth = "01";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("February")){
				mnth = "02";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("March")){
				mnth = "03";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("April")){
				mnth = "04";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("May")){
				mnth = "05";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("June")){
				mnth = "06";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("July")){
				mnth = "07";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("August")){
				mnth = "08";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("September")){
				mnth = "09";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("October")){
				mnth = "10";
			}else if(toursInputVO.getMonth().equalsIgnoreCase("November")){
				mnth = "11";
			}else{
				mnth = "12";
			}
			String day = "01";
			String dt = yr+"-"+mnth+"-"+day;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = sdf.parse(dt);
			selfAppraisalCandidateDetails.setTourDate(today);  
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
			if(destPath != null && destPath.trim().length() > 1){
				selfAppraisalCandidateDetails.setReportPath(destPath);       
			}
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
	public ResultStatus updateTourDtls(ToursInputVO toursInputVO,Long userId, Map<File,String> mapfiles){  
		LOG.info("Entered into updateTourDtls() of ToursService{}"); 
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			DateUtilService dateUtilService = new DateUtilService();
			String destPath = saveUploadFile(mapfiles);  
			SelfAppraisalCandidateDetails selfAppraisalCandidateDetails = new SelfAppraisalCandidateDetails();
			selfAppraisalCandidateDetails = selfAppraisalCandidateDetailsDAO.get(toursInputVO.getCandidateDtlsId());
			if(selfAppraisalCandidateDetails != null ){
				selfAppraisalCandidateDetails.setMonth(toursInputVO.getMonth());
				selfAppraisalCandidateDetails.setYear(toursInputVO.getYear());
				selfAppraisalCandidateDetails.setUpdatedBy(userId);
				selfAppraisalCandidateDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());      
				if(toursInputVO.getOwnTours() != null){ 
					selfAppraisalCandidateDetails.setOwnTours(toursInputVO.getOwnTours());
				}
				if(toursInputVO.getInchargeTours() != null){
					selfAppraisalCandidateDetails.setInchargeTours(toursInputVO.getInchargeTours());  
				}
				if(toursInputVO.getRemarks() != null){
					selfAppraisalCandidateDetails.setRemarks(toursInputVO.getRemarks().trim());
				}
				if(toursInputVO.getOldFileStatus().longValue() == 1){
					String oldPath = selfAppraisalCandidateDetails.getReportPath();
					if(oldPath != null){
						if(oldPath.trim().length() > 2){
							selfAppraisalCandidateDetails.setReportPath(oldPath);      
						}else{
							if(destPath != null && destPath.trim().length() > 1){    
								selfAppraisalCandidateDetails.setReportPath(destPath);
							}
						}
					}else{
						if(destPath != null && destPath.trim().length() > 1){    
							selfAppraisalCandidateDetails.setReportPath(destPath);
						}   
					}
				}else{
					if(destPath != null && destPath.trim().length() > 1){      
						selfAppraisalCandidateDetails.setReportPath(destPath);
					}  
				}
				if(toursInputVO.getOldFileStatus().longValue() == 2 && destPath != null && destPath.trim().length() < 2){     
					selfAppraisalCandidateDetails.setReportPath("");  
				}
			}else{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("Failed");
				return resultStatus;
			}
			selfAppraisalCandidateDetailsDAO.save(selfAppraisalCandidateDetails); 
			resultStatus.setResultCode(1);
			resultStatus.setMessage("Updated");  
			return resultStatus;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised in updateTourDtls() of ToursService{}", e);
			resultStatus.setResultCode(0);
			resultStatus.setMessage("Failed");
			return resultStatus;
		}    
	}
	public String saveUploadFile(Map<File,String> mapfiles){
		try{
			
			String destPath = new String();
			String localPath = new String();
			StringBuilder returnPath = new StringBuilder();
			String folderName = folderCreation();
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			
			StringBuilder folderStr = new StringBuilder();
			int temp = month+1;
			String yr = String.valueOf(year); // YEAR YYYY
			String monthText = getMonthForInt(temp);
			folderStr.append(monthText).append(yr); 
			String mnthYr = folderStr.toString();//November-2016
			
			calendar.setTime(new DateUtilService().getCurrentDateAndTime());  
			int i = 0;
			for (Map.Entry<File, String> entry : mapfiles.entrySet()){
				Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				//DB path    
				destPath = mnthYr+"/"+randomNumber+"."+entry.getValue();
				//local sys path
				localPath = folderName+"/"+randomNumber+"."+entry.getValue();  
				if(i == 0){
					returnPath.append(destPath);      
				}else{
					returnPath.append(","+destPath);  
				}
				i++;
				ActivityService activityService = new ActivityService(); 
				//saving the file here...
				String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),localPath);
				 
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
	
	
	public static String folderCreation()
	{
		try {
			LOG.debug(" in FolderForDocument ");
	  		
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new DateUtilService().getCurrentDateAndTime());  
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			
			String targetDirpath = IConstants.STATIC_CONTENT_FOLDER_URL+"Reports/"+IConstants.TOUR_DOCUMENTS+"/"+getMonthForInt(month+1)+year;
			
			File requriredDir = new File(targetDirpath);
			
			if(!requriredDir.exists())
				requriredDir.mkdirs();
			
			return requriredDir.getAbsolutePath();
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");  
			return "FAILED";
		}
	}
	
	public static String getMonthForInt(int num) {    
		String month = "";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 1 && num <= 12 ) {
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
    		/*List<Object[]> desigDtls = selfAppraisalCandidateDAO.getTotalLeadersDesignationBy(desigIdList);
    		
    		if(desigDtls != null && desigDtls.size() > 0){
    			toursBasicVO.setCandidateCount(desigDtls.get(0)[2] != null ? (Long)desigDtls.get(0)[2] : 0l);
    		}
    		List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursLeadersDetails(startDate,endDate,desigIdList);
    		if(memDtlsList != null && memDtlsList.size() > 0){
    			toursBasicVO.setSelectedCandCount(memDtlsList.get(0)[1] != null ? (Long)memDtlsList.get(0)[1] : 0l);
    			toursBasicVO.setTotalTour((memDtlsList.get(0)[2] != null ? (Long)memDtlsList.get(0)[2] : 0l) + (memDtlsList.get(0)[3] != null ? (Long)memDtlsList.get(0)[3] : 0l));
    		}    
    		return toursBasicVO;*/ 
    		List<Long> CandidateIds = selfAppraisalCandidateLocationDAO.getCandiateIdList(null,null,desigIdList);
    		if(CandidateIds != null && CandidateIds.size() > 0){
    			toursBasicVO.setCandidateCount(Long.parseLong(Integer.toString(CandidateIds.size())));
    		}
    		List<Object[]> memDtlsList= selfAppraisalCandidateDetailsDAO.getSubmittedToursDetails(startDate,endDate,CandidateIds);
    		Long selectedCandCount = 0l;    
    		Long totalTours = 0l;
    		if(memDtlsList != null && memDtlsList.size() > 0){   
    			for(Object[] param : memDtlsList){
    				selectedCandCount = selectedCandCount + 1;
    				totalTours = totalTours + ((param[1] != null ? (Long)param[1] : 0l) + (param[2] != null ? (Long)param[2] : 0l));
    			}       
    			toursBasicVO.setSelectedCandCount(selectedCandCount);
    			toursBasicVO.setTotalTour(totalTours);
    		}
    		
    		Long totalUniqueTour = selfAppraisalCandidateDetailsDAO.geTtotalUniqueTour(CandidateIds,startDate,endDate);
    		if(totalUniqueTour != null){
    			toursBasicVO.setInchargerToursCnt(totalUniqueTour);//total unique tours
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
    				  leaderVO.setNotSubmitedLeaserCnt(leaderVO.getNoOfLeaderCnt());
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
    				basicVO.setCandDtlsId(candidateDtlsId);  
    				basicVO.setMonth(param[1] != null ? param[1].toString() : "");
    				basicVO.setYear(param[2] != null ? (Long)param[2] : 0l);
    				if(param[5] != null){
    					basicVO.setOwnTours((Long)param[5]);    
    				}  
    				if(param[8] != null){  
    					basicVO.setInchargerTours((Long)param[8]);      
    				}  
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
//    
    public List<IdNameVO> getAllTourTypes(){
    	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
    	try{    		
    		List<Object[]> objectList = tourTypeDAO.getAllTourTypes();
	    		if(objectList !=null && objectList.size()>0){
	    			for (Object[] obj : objectList) 
		    			finalList.add(new IdNameVO(obj[0] !=null ? (Long)obj[0]:null,obj[1] !=null ? obj[1].toString():null));
	    		}    		
    	}catch(Exception e){
    		LOG.error("Error Occured at getAllTourTypes() in ToursService class",e);
    	}
    	return finalList;
    }
    
    public List<IdNameVO> getAllTourCategorys(Long cadreId){
    	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
    	try{    		
    		List<Object[]> objectList = selfAppraisalTourCategoryDAO.getAllTourCategorys(cadreId);
	    		if(objectList !=null && objectList.size()>0){
	    			for (Object[] obj : objectList) 
		    			finalList.add(new IdNameVO(obj[0] !=null ? (Long)obj[0]:null,obj[1] !=null ? obj[1].toString():null));
	    		}    		
    	}catch(Exception e){
    		LOG.error("Error Occured at getAllTourCategorys() in ToursService class",e);
    	}
    	return finalList;
    }
    
    
    public List<ToursBasicVO> getToursDetailsOverviewForNew(String fromDateStr,String toDateStr){
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
    				  ToursBasicVO desigVo = new ToursBasicVO();
    				  desigVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				  desigVo.setDesignation(commonMethodsUtilService.getStringValueForObject(param[1]));
    				  desigVo.setNoOfLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
    				  desigVo.setNotSubmitedLeaserCnt(desigVo.getNoOfLeaderCnt());
    				  leadersDetailsMap.put(desigVo.getId(), desigVo);
    		 		}
    		 }
    		 	//0.selfAppraisalDesignationId,1.submitted Leaders Count,2.submitted tours
    		List<Object[]> rtrnLdrsTrsSbmttdDtlsObjLst = selfAppraisalCandidateDayTourDAO.getSubmittedToursLeadersDetails(fromDate, toDate,null);
    		 if(rtrnLdrsTrsSbmttdDtlsObjLst != null && !rtrnLdrsTrsSbmttdDtlsObjLst.isEmpty()){
    			 for(Object[] param:rtrnLdrsTrsSbmttdDtlsObjLst){
    				 ToursBasicVO desigVo = leadersDetailsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    				   if(desigVo != null ){    					
    					   desigVo.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[1]));
    					   desigVo.setNotSubmitedLeaserCnt(desigVo.getNoOfLeaderCnt()-desigVo.getSubmitedLeaderCnt());
    					   desigVo.setTotalSubmittedToursCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
    				   }
    			 }
    		 }    		
    		 
    		/* int monthSize=0;*/
    		/*List<Date> monthDates = getBetWeenMonthsOfDates(fromDate, toDate);
    		if(monthDates !=null ){
    			monthSize= monthDates.size();
    		}*/
    		 
    		 int monthSize = getMonthsDifference(fromDate, toDate);
    		 
    		 //0.designationId,1.targetDays (categoryWise)
    		List<Object[]> objList = selfAppraisalDesignationTargetDAO.getTotalTargetOfDesignation(fromDate,toDate,null,"Category");
    		 
    		if(objList !=null && objList.size()>0){
    			for (Object[] obj : objList) {					
    				ToursBasicVO desigVo = leadersDetailsMap.get(obj[0] !=null ? (Long)obj[0]: null);
    				if(desigVo !=null){
    					desigVo.setCategoryTargetDays(monthSize * ( obj[1] !=null ? (Long)obj[1]:0l));
    				}    				
				}
    		}
    		
    		List<Object[]> objTourTypeList = selfAppraisalDesignationTargetDAO.getTotalTargetOfDesignation(fromDate,toDate,null,"tourType");
    		
    		if(objList !=null && objList.size()>0){
    			for (Object[] obj : objList) {					
    				ToursBasicVO desigVo = leadersDetailsMap.get(obj[0] !=null ? (Long)obj[0]: null);
    				if(desigVo !=null){
    					desigVo.setTourTypeTargetDays(monthSize * ( obj[1] !=null ? (Long)obj[1]:0l));
    				}    				
				}
    		}
    		
    		    		
    		//0.selfAppraisalDesignationId,1.selfAppraisalCandidateId,2.tourDays Of candidate
    		List<Object[]> candidateDesigObj = selfAppraisalCandidateDayTourDAO.getCandidateWiseTargetCompletedDays(fromDate,toDate,null);
    		
    		if(candidateDesigObj !=null && candidateDesigObj.size()>0){
    			for (Object[] desig : candidateDesigObj) {   				
    				
    				ToursBasicVO desigVo = leadersDetailsMap.get(desig[0] !=null ? (Long)desig[0]:null);     				
    				if(desigVo !=null){    				
    					Long achievedDays= desig[2] !=null ? (Long)desig[2]:0l; //Candidate Achieved Days
    					
    					if( desigVo.getCategoryTargetDays() <= achievedDays){    					
        					desigVo.setComplainceCnt(desigVo.getComplainceCnt()+1);
        				}
    				}    
    			}
    		}
    		
    		if(leadersDetailsMap !=null){
    			resultList = new ArrayList<ToursBasicVO>(leadersDetailsMap.values());
    		}
    		
    		
   	}catch(Exception e) {
			LOG.error("Error Occured at getToursDetailsOverview() in ToursService class",e);	
		}
    	return resultList;
    }
    
    //New Saving Scenario
    
    public ResultStatus saveNewTourDetails(final ToursVO toursVo,final Map<File, String> documentMap){
    	ResultStatus status = null; 
    	try{
    		
    		status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				@SuppressWarnings("null")
				public Object doInTransaction(TransactionStatus arg0) {
					
					ResultStatus transStatus=new ResultStatus();
					
					if(toursVo.getTourId() !=null && toursVo.getTourId() >0l)//Update
					{
						updateNewTourDetails(toursVo,documentMap);
					}else{						
						if(toursVo.getCandidateId() !=null && toursVo.getCandidateId()>0l //here candidateId means tdpCadreId
								&& toursVo.getDesignationId() !=null && toursVo.getDesignationId()>0l){							
							List<Long> candidates = selfAppraisalCandidateDAO.getCandidateIdOfCadre(toursVo.getCandidateId(),toursVo.getDesignationId());
							
							if(candidates !=null && candidates.size()>0){
								toursVo.setSelfAppraisalCandidateId(candidates.get(0));
							}							
						}
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");						
						DateUtilService dateUtilService = new DateUtilService();
						
						if(toursVo !=null && toursVo.getToursVoList() !=null && toursVo.getToursVoList().size()>0){
							for (ToursVO innerTourVo : toursVo.getToursVoList()) {
								
								SelfAppraisalCandidateDayTour selfAppraisalCandidateDayTour = new SelfAppraisalCandidateDayTour();
								
								selfAppraisalCandidateDayTour.setSelfAppraisalCandidateId(toursVo.getSelfAppraisalCandidateId());								
								selfAppraisalCandidateDayTour.setSelfAppraisalDesignationId(toursVo.getDesignationId());								
								selfAppraisalCandidateDayTour.setSelfAppraisalTourCategoryId(innerTourVo.getTourCategoryId() !=null ? innerTourVo.getTourCategoryId():null);
								
								try {
									selfAppraisalCandidateDayTour.setTourDate(sdf.parse(innerTourVo.getTourDateId()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								
								//Address Saving								
								Long addressId = saveUserAddressDetailsOfTour(innerTourVo,selfAppraisalCandidateDayTour);								
								
								selfAppraisalCandidateDayTour.setAddressId(addressId !=null ? addressId:null);
								if(innerTourVo.getTourTypeId() !=null && innerTourVo.getTourTypeId()>0l){
									selfAppraisalCandidateDayTour.setTourTypeId(innerTourVo.getTourTypeId());
								}
								
								if(innerTourVo.getDescription() !=null && !innerTourVo.getDescription().isEmpty()){
									selfAppraisalCandidateDayTour.setComment(innerTourVo.getDescription().toString());
								}
								
								selfAppraisalCandidateDayTour.setIsDeleted("N");
								
								selfAppraisalCandidateDayTour.setInsertedBy(toursVo.getUserId());
								selfAppraisalCandidateDayTour.setUpdatedBy(toursVo.getUserId());
								selfAppraisalCandidateDayTour.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								selfAppraisalCandidateDayTour.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								
								selfAppraisalCandidateDayTourDAO.save(selfAppraisalCandidateDayTour);
								
							}
						}												
								//Documents Saving						
								saveApplicationDocuments(toursVo,documentMap);												
					}
					
					transStatus.setMessage("success");
					transStatus.setResultCode(1);
					
					return transStatus;
					
				}
				
    		});
    		
    	}catch(Exception e){
    		status.setMessage("failure");
    		status.setResultCode(0);
    		LOG.error("Exception Occured in saveNewTourDetails() in ToursService class ", e);
    	}
    	return status;
    }
    
    public ResultStatus updateNewTourDetails( final ToursVO toursVo,final Map<File, String> documentMap){
    	ResultStatus result = new ResultStatus();
    	try{    		
    		
    		result = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
    		
					ResultStatus status=new ResultStatus();
					
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");						
				DateUtilService dateUtilService = new DateUtilService();
				
				if(toursVo !=null && toursVo.getToursVoList() !=null && toursVo.getToursVoList().size()>0){
					for (ToursVO innerTourVo : toursVo.getToursVoList()) {
						
						SelfAppraisalCandidateDayTour selfAppraisalCandidateDayTour = selfAppraisalCandidateDayTourDAO.get(innerTourVo.getTourId());
						
						if(selfAppraisalCandidateDayTour !=null){
														
							//selfAppraisalCandidateDayTour.setSelfAppraisalCandidateId(toursVo.getCandidateId());								
							//selfAppraisalCandidateDayTour.setSelfAppraisalDesignationId(toursVo.getDesignationId());								
							selfAppraisalCandidateDayTour.setSelfAppraisalTourCategoryId(innerTourVo.getTourCategoryId() !=null ? innerTourVo.getTourCategoryId():null);
							
							try {
								selfAppraisalCandidateDayTour.setTourDate(sdf.parse(innerTourVo.getTourDateId()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
							//Address Saving								
							Long addressId = saveUserAddressDetailsOfTour(innerTourVo,selfAppraisalCandidateDayTour);								
							
							selfAppraisalCandidateDayTour.setAddressId(addressId !=null ? addressId:null);
							if(innerTourVo.getTourTypeId() !=null && innerTourVo.getTourTypeId()>0l){
								selfAppraisalCandidateDayTour.setTourTypeId(innerTourVo.getTourTypeId());
							}else{
								selfAppraisalCandidateDayTour.setTourTypeId(null);
							}
							
							if(innerTourVo.getDescription() !=null && !innerTourVo.getDescription().isEmpty()){
								selfAppraisalCandidateDayTour.setComment(innerTourVo.getDescription().toString());
							}else{
								selfAppraisalCandidateDayTour.setComment(null);
							}
							
							selfAppraisalCandidateDayTour.setIsDeleted("N");
							
							selfAppraisalCandidateDayTour.setUpdatedBy(toursVo.getUserId());
							selfAppraisalCandidateDayTour.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							
							selfAppraisalCandidateDayTourDAO.save(selfAppraisalCandidateDayTour);
							
						}	
												
						//Documents Saving						
						saveApplicationDocuments(toursVo,documentMap);	
					}    			
    			}
				
				status.setMessage("success");
				status.setResultCode(1);
				
				return status;
				
				}
    		});
    		
    	}catch(Exception e){
    		result.setMessage("failure");
    		result.setResultCode(0);
    		LOG.error("Exception Occured in updateNewTourDetails() in ToursService", e);
    	}
    	return result;
    }
    
    public Long saveUserAddressDetailsOfTour(final ToursVO innerTourVo,final SelfAppraisalCandidateDayTour selfAppraisalCandidateDayTour){
    	Long userAddress = null;
    	try{ 
    		
    		userAddress = (Long)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {

					Long userAddressId=null;
					
					//LocationId and Location Value Setting
					if(innerTourVo.getPanchayatWardId() !=null && innerTourVo.getPanchayatWardId()>0l){
						
						if(Long.parseLong(innerTourVo.getPanchayatWardId().toString().charAt(0)+"")==7l){//panchayat
							selfAppraisalCandidateDayTour.setLocationScopeId(6l);
							selfAppraisalCandidateDayTour.setLocationValue(Long.valueOf(innerTourVo.getPanchayatWardId().toString().substring(1)));							
						}else if(Long.parseLong(innerTourVo.getPanchayatWardId().toString().charAt(0)+"")==8l){ //ward
							selfAppraisalCandidateDayTour.setLocationScopeId(8l);
							selfAppraisalCandidateDayTour.setLocationValue(Long.valueOf(innerTourVo.getPanchayatWardId().toString().substring(1)));	
						}
						
					}else if(innerTourVo.getLocalBodyId() !=null && innerTourVo.getLocalBodyId()>0l){
						
						if(Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==4l){//Tehsil
							selfAppraisalCandidateDayTour.setLocationScopeId(5l);
							selfAppraisalCandidateDayTour.setLocationValue(Long.valueOf(innerTourVo.getLocalBodyId().toString().substring(1)));							
						}else if(Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==5l || Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==6l){//Municipality
							selfAppraisalCandidateDayTour.setLocationScopeId(7l);
							selfAppraisalCandidateDayTour.setLocationValue(Long.valueOf(innerTourVo.getLocalBodyId().toString().substring(1)));	
						}
						
					}else if(innerTourVo.getConstituencyId() !=null && innerTourVo.getConstituencyId()>0l){
						selfAppraisalCandidateDayTour.setLocationScopeId(4l);
						selfAppraisalCandidateDayTour.setLocationValue(innerTourVo.getConstituencyId());
					}else if(innerTourVo.getDistrictId() !=null && innerTourVo.getDistrictId()>0l){
						selfAppraisalCandidateDayTour.setLocationScopeId(3l);
						selfAppraisalCandidateDayTour.setLocationValue(innerTourVo.getDistrictId());
					}else if(innerTourVo.getStateId() !=null && innerTourVo.getStateId()>0l){
						selfAppraisalCandidateDayTour.setLocationScopeId(2l);
						selfAppraisalCandidateDayTour.setLocationValue(innerTourVo.getStateId());
					}
					
					
					UserAddress UA = new UserAddress();
					
					if(innerTourVo.getStateId() !=null && innerTourVo.getStateId() >0){
						UA.setState(stateDAO.get(innerTourVo.getStateId()));
					}
					if(innerTourVo.getDistrictId()!=null && innerTourVo.getDistrictId()>0){
						UA.setDistrict(districtDAO.get(innerTourVo.getDistrictId()));
					}
					if(innerTourVo.getConstituencyId() !=null && innerTourVo.getConstituencyId() >0){
						UA.setConstituency(constituencyDAO.get(innerTourVo.getConstituencyId()));
					}
					
					
					
					if(innerTourVo.getLocalBodyId() !=null && innerTourVo.getLocalBodyId()>0l){	
						if(Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==4l){//Tehsil
							UA.setTehsil(innerTourVo.getLocalBodyId() !=null ? tehsilDAO.get(Long.valueOf(innerTourVo.getLocalBodyId().toString().substring(1))):null);
						}else if(Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==5l || Long.parseLong(innerTourVo.getLocalBodyId().toString().charAt(0)+"")==6l){
							UA.setLocalElectionBody(innerTourVo.getLocalBodyId() !=null ? 
									localElectionBodyDAO.get(Long.valueOf(innerTourVo.getLocalBodyId().toString().substring(1))):null);	
						}						
					}
					
					if(innerTourVo.getPanchayatWardId() !=null && innerTourVo.getPanchayatWardId()>0l){						
						if(Long.parseLong(innerTourVo.getPanchayatWardId().toString().charAt(0)+"")==7l){//panchayat
							UA.setPanchayatId(innerTourVo.getPanchayatWardId() !=null ? Long.valueOf(innerTourVo.getPanchayatWardId().toString().substring(1)):null);
						}else if(Long.parseLong(innerTourVo.getPanchayatWardId().toString().charAt(0)+"")==8l){
							UA.setWard(innerTourVo.getPanchayatWardId() !=null ? constituencyDAO.get(Long.valueOf(innerTourVo.getPanchayatWardId().toString().substring(1))):null);
						}												
					}
										
					UserAddress userAddress = userAddressDAO.save(UA);
					
					userAddressId = userAddress !=null ? userAddress.getUserAddressId():null;
					
					return userAddressId;
					
				}
    		});
    		
    	}catch(Exception e){
    		LOG.error("Exception Occured in saveUserAddressDetailsOfTour() in ToursService", e);
    	}
    	
    	return userAddress;
    }
    
    public void saveApplicationDocuments(ToursVO toursVO,final Map<File,String> documentMap){
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateUtilService dt = new DateUtilService();
			
			String folderName = folderCreation();
			SelfAppraisalCandidateDocument selfAppraisalCandidateDocument = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 //int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			
			 StringBuilder pathBuilder = null;
			 StringBuilder str ;
			 
			
			 for (Map.Entry<File, String> entry : documentMap.entrySet())
			 {
				 pathBuilder = new StringBuilder();
				 str = new StringBuilder();
				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
					
				 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
				 .append(entry.getValue());
				 str.append(randomNumber).append(".").append(entry.getValue());
				String fileCpyStts = copyFile(entry.getKey().getAbsolutePath(),destPath);
				 
					if(fileCpyStts.equalsIgnoreCase("error")){
						LOG.error(" Exception Raise in copying file in ToursService ");
						throw new ArithmeticException();
					}
					
					selfAppraisalCandidateDocument = new SelfAppraisalCandidateDocument();
					selfAppraisalCandidateDocument.setDocumentPath(pathBuilder.toString());				
					selfAppraisalCandidateDocument.setSelfAppraisalCandidateId(toursVO.getSelfAppraisalCandidateId());
					
					if(toursVO.getToursVoList() !=null && toursVO.getToursVoList().size()>0){
						selfAppraisalCandidateDocument.setTourDate(toursVO.getToursVoList().get(0).getTourDateId() !=null 
								?  sdf.parse(toursVO.getToursVoList().get(0).getTourDateId().toString()):null);
					}
					
					
					selfAppraisalCandidateDocument.setInsertedTime(dt.getCurrentDateAndTime());
					selfAppraisalCandidateDocument.setIsDeleted("N");
					selfAppraisalCandidateDocument.setInsertedBy(toursVO.getUserId());
					selfAppraisalCandidateDocument = selfAppraisalCandidateDocumentDAO.save(selfAppraisalCandidateDocument);
					
			 }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in saveApplicationDocuments() in ToursService", e);
		}
		
	}

	public String copyFile(String sourcePath,String destinationPath){
	 try{
		File destFile = new File(destinationPath);
		 if (!destFile.exists()) 
			 destFile.createNewFile();
		 File file = new File(sourcePath);
		if(file.exists()){
			FileUtils.copyFile(file,destFile);
			LOG.error("Copy Success");
			return "success";
		}
	  }catch(Exception e){
		  LOG.error("Exception raised in copyFile in ToursService ", e);
		  LOG.error("Copy Error");
		  return "error";
	  }
	 return "failure";
	}
    
	public PMMinuteVO getAllCandidateLocations(Long cadreId,Long categoryId){
    	PMMinuteVO vo = new PMMinuteVO();
    	try{    		
    		Object[] obj = selfAppraisalCandidateLocationNewDAO.getAllCandidateLocations(cadreId,categoryId);
    		
    	if(obj !=null){	
    		if( obj[0] != null && (Long)obj[0] > 0l){
    			Long addressId = (Long)obj[0];
  				  List<Object[]> userDetailsList = userAddressDAO.getUserAddressDetailsByMinuteId(addressId);
  				  if(userDetailsList != null && userDetailsList.size() > 0){
  					  Object[] objects2 = userDetailsList.get(0);
  					  vo.setStateId(commonMethodsUtilService.getLongValueForObject(objects2[0]));
  					  if(vo.getStateId() == 1l){
  						  KeyValueVO vo1 = new KeyValueVO();
  						  vo1.setId(1l);
    					  vo1.setName("AndhraPradesh");
    					  vo.getStatesList().add(vo1);
  					  }
  					  if(vo.getStateId() == 2l){
  						  KeyValueVO vo2 = new KeyValueVO();
  						  vo2.setId(36l);
  						  vo2.setName("Telangana");
  						  vo.getStatesList().add(vo2);
  					  }
 
  					 
  					  if(objects2[1] != null && (Long)objects2[1] > 0l){
  						  vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects2[1]));
  						  District distObj = districtDAO.get((Long)objects2[1]);
  						  if(distObj != null){
  							  KeyValueVO distVO = new KeyValueVO();
  							  distVO.setId(distObj.getDistrictId());
  							  distVO.setName(distObj.getDistrictName());
  							  vo.getDistList().add(distVO);
  						  }
  					  }else if(objects2[0] != null && (Long)objects2[0] > 0l){
  						  List<Object[]> objList = districtDAO.getDistrictsWithNewSplitted((Long)objects2[0]);
						  vo.setDistList(setValuesTOVOList(objList));
  					  }
  					 	  
  					  if(objects2[2] != null && (Long)objects2[2] > 0l){
  						 vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects2[2]));
  						 Constituency constObj = constituencyDAO.get((Long)objects2[2]);
  						 if(constObj != null){
  							 KeyValueVO constVO = new KeyValueVO();
  							 constVO.setId(constObj.getConstituencyId());
  							 constVO.setName(constObj.getName());
  							 vo.getConstList().add(constVO);
  						 }
  					  }else if(vo.getDistrictId() != null && vo.getDistrictId() > 0l){
  						  List<LocationWiseBoothDetailsVO> lwbdvoList = cadreCommitteeService.getConstituencyOfDistrict(vo.getStateId(),Arrays.asList(vo.getDistrictId()));
  						  vo.setConstList(setResultTOLocationWiseBoothDetailsVO(lwbdvoList));
  					  }
  						  
  					  if(objects2[2] != null && (Long)objects2[2] > 0l){
  						  List<LocationWiseBoothDetailsVO> lwbdvoList = cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),"0",4l);
  						  vo.setManTowDivList(setResultTOLocationWiseBoothDetailsVO(lwbdvoList));
  					  }
  						  
  					  vo.setTehsilId(objects2[3] != null && (Long)objects2[3] > 0l ? Long.parseLong("4"+objects2[3].toString()):0l);	    						  
  					  vo.setLocalElectionBodyId(objects2[4] != null && (Long)objects2[4] > 0l ? Long.parseLong("5"+objects2[4].toString()):0l);
  					  if(vo.getLocalElectionBodyId() !=null && vo.getLocalElectionBodyId()>0 && objects2[5] !=null && (Long)objects2[5]>0){
  						  vo.setWardId(objects2[5] != null && (Long)objects2[5] > 0l ? Long.parseLong("8"+objects2[5].toString()):0l);
  					  }else{
  						  vo.setDivisionId(objects2[5] != null && (Long)objects2[5] > 0l ? Long.parseLong("6"+objects2[5].toString()):0l);
  					  }	    						  	    						 
  					
  					  vo.setPanchayatId(objects2[6] != null && (Long)objects2[6] > 0l ? Long.parseLong("7"+objects2[6].toString()):0l);
  					  if((objects2[3] !=null && (Long)objects2[3]>0l)){
  						  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getTehsilId().toString(),5l);	    							  
  						  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  					  }
  					  if(objects2[4] != null && (Long)objects2[4] > 0l){
  						  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getLocalElectionBodyId().toString(),5l);	    							  
  						  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  					  }
  					  if(vo.getDivisionId() !=null && vo.getDivisionId() >0l){
  						  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getDivisionId().toString(),5l);	    							  
  						  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  					  }
  				  }
    		}
    		
    		vo.setLocationLevel(obj[1] != null && (Long)obj[1] > 0l?(Long)obj[1]:0l);
    		vo.setLocationValue(obj[2] != null && (Long)obj[2] > 0l?(Long)obj[2]:0l);
    		
    	}
    	}catch(Exception e){
    		LOG.error("Error Occured at getAllCandidateLocations() in ToursService class",e);
    	}
    	return vo;
    }
	
	public PMMinuteVO getNewTourRetrivalDetails(Long candidateDayTourId){
		PMMinuteVO vo = new PMMinuteVO();
		try{
			
			SelfAppraisalCandidateDayTour dayTour = selfAppraisalCandidateDayTourDAO.get(candidateDayTourId);			
			
			if(dayTour !=null){
				
				vo.setTourDate(dayTour.getTourDate() !=null ? dayTour.getTourDate().toString():null);
				vo.setTdpCadreId(dayTour.getSelfAppraisalCandidate().getTdpCadreId());
				vo.setTourCategoryId(dayTour.getSelfAppraisalTourCategoryId());
				vo.setTourTypeId(dayTour.getTourTypeId() !=null ? dayTour.getTourTypeId():null);
				vo.setLocationScopeId(dayTour.getLocationScopeId());
				vo.setLocationValue(dayTour.getLocationValue());
				vo.setUserAddressId(dayTour.getAddressId() !=null ? dayTour.getAddressId():null);
				vo.setSelfApraisalCandidateId(dayTour.getSelfAppraisalCandidateId());
				vo.setName(dayTour.getComment());//description
				
				if(vo.getTdpCadreId() !=null){
					vo.setCategoryList(getAllTourCategorys(vo.getTdpCadreId()));
				}
				if(vo.getTourTypeId() !=null){
					vo.setTourTypeList(getAllTourTypes());
				}
				
				if(vo.getUserAddressId() != null && vo.getUserAddressId() > 0l){
  				  List<Object[]> userDetailsList = userAddressDAO.getUserAddressDetailsByMinuteId(vo.getUserAddressId());
  				  if(userDetailsList != null && userDetailsList.size() > 0){
  					  for (Object[] objects2 : userDetailsList) {
  						  vo.setStateId(commonMethodsUtilService.getLongValueForObject(objects2[0]));
  						  if(objects2[0] != null && (Long)objects2[0] > 0l){
  							  List<Object[]> objList = districtDAO.getDistrictsWithNewSplitted((Long)objects2[0]);
  							  vo.setDistList(setValuesTOVOList(objList));
  						  }
  						  
  						  vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects2[1]));
  						  if(objects2[1] != null && (Long)objects2[1] > 0l){
  							  List<LocationWiseBoothDetailsVO> lwbdvoList = cadreCommitteeService.getConstituencyOfDistrict(vo.getStateId(),Arrays.asList(vo.getDistrictId()));
  							  vo.setConstList(setResultTOLocationWiseBoothDetailsVO(lwbdvoList));
  							  
  						  }
  						  
  						  vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects2[2]));
  						  if(objects2[2] != null && (Long)objects2[2] > 0l){
  							  List<LocationWiseBoothDetailsVO> lwbdvoList = cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),"0",4l);
  							  vo.setManTowDivList(setResultTOLocationWiseBoothDetailsVO(lwbdvoList));
  						  }
  						  
  						  vo.setTehsilId(objects2[3] != null && (Long)objects2[3] > 0l ? Long.parseLong("4"+objects2[3].toString()):0l);	    						  
  						  vo.setLocalElectionBodyId(objects2[4] != null && (Long)objects2[4] > 0l ? Long.parseLong("5"+objects2[4].toString()):0l);
  						  
  						  if(vo.getLocalElectionBodyId() !=null && vo.getLocalElectionBodyId()>0 && objects2[5] !=null && (Long)objects2[5]>0){
  							  vo.setWardId(objects2[5] != null && (Long)objects2[5] > 0l ? Long.parseLong("8"+objects2[5].toString()):0l);
  						  }else{
  							  vo.setDivisionId(objects2[5] != null && (Long)objects2[5] > 0l ? Long.parseLong("6"+objects2[5].toString()):0l);
  						  }	    						  	    						 
  						  vo.setPanchayatId(objects2[6] != null && (Long)objects2[6] > 0l ? Long.parseLong("7"+objects2[6].toString()):0l);
  						  
  						  if((objects2[3] !=null && (Long)objects2[3]>0l)){
  							  
  							  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getTehsilId().toString(),5l);	    							  
  							  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  							  
  						  }
  						  if(objects2[4] != null && (Long)objects2[4] > 0l){
  							  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getLocalElectionBodyId().toString(),5l);	    							  
  							  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  						  }
  						  if(vo.getDivisionId() !=null && vo.getDivisionId() >0l)
  						  {
  							  List<LocationWiseBoothDetailsVO> tehsiVoList =  cadreCommitteeService.getLocationsOfSublevelConstituencyMandal(0l,Arrays.asList(vo.getDistrictId()),Arrays.asList(vo.getConstituencyId()),vo.getDivisionId().toString(),5l);	    							  
  							  vo.setPanWardList(setResultTOLocationWiseBoothDetailsVO(tehsiVoList));
  						  }
  					  }
  				  }
  			  }
				
				
				// Retriving Documents Of Month && Year Of Candidate
								
				if(vo.getTourDate() !=null &&  vo.getSelfApraisalCandidateId() !=null && vo.getSelfApraisalCandidateId()>0l){					
					Long year = Long.valueOf(vo.getTourDate().split("-")[0]);
					Long month = Long.valueOf(vo.getTourDate().split("-")[1]);
					
					List<Object[]> documentList = selfAppraisalCandidateDocumentDAO.getSelfAppraisalDocumentDetails(vo.getSelfApraisalCandidateId(),year,month);
					
					vo.setDocumentList(setValuesTOVOList(documentList));					
				}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return vo;
	}
	
	public List<KeyValueVO> setValuesTOVOList(List<Object[]> objList){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			if(objList != null && objList.size() > 0){
				for (Object[] objects3 : objList) {
					KeyValueVO kkvo = new KeyValueVO();
					kkvo.setId(objects3[0] != null && (Long)objects3[0] > 0l ? (Long)objects3[0]:0l);
					kkvo.setName(objects3[1]!=null && !objects3[1].toString().isEmpty() ? objects3[1].toString():"");
					voList.add(kkvo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at setValuesTOVOList", e);
		}
		return voList;
	}
	public List<KeyValueVO> setResultTOLocationWiseBoothDetailsVO(List<LocationWiseBoothDetailsVO> lwbdvoList){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			if(lwbdvoList != null && lwbdvoList.size() > 0){
				  for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : lwbdvoList) {
					KeyValueVO vo1 = new KeyValueVO();
					vo1.setId(locationWiseBoothDetailsVO.getLocationId());
					vo1.setName(locationWiseBoothDetailsVO.getLocationName());
					voList.add(vo1);
				  }
			  }
		} catch (Exception e) {
			LOG.error("Exception raised at setResultTOLocationWiseBoothDetailsVO", e);
		}
		return voList;
	}
	
	public ToursBasicVO getCandidateDetailedReport(Long candidateId,String fromDateStr,String toDateStr){
		ToursBasicVO resultVO = new ToursBasicVO();
		 List<ToursBasicVO> dateWiseTourDtlsList = new ArrayList<ToursBasicVO>(0);
		 try{
			 	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 	Date fromDate=null,toDate = null;
			 	if(fromDateStr != null && toDateStr != null){
			 		fromDate=sdf.parse(fromDateStr);
			 		toDate=sdf.parse(toDateStr); 
			 	} 
			   
			   List<Object[]> objList = selfAppraisalCandidateDayTourDAO.getDateWiseTourSubmittedDetails(fromDate, toDate, candidateId);

				  if(objList != null && objList.size() > 0){
					  for(Object[] param:objList){
						  ToursBasicVO VO = new ToursBasicVO();
						  if(param[0] != null){
							  VO.setTourDate(sdf.format(param[0]));  
						  }
						  VO.setTourCategoryId(commonMethodsUtilService.getLongValueForObject(param[1]));
						  VO.setTourCategory(commonMethodsUtilService.getStringValueForObject(param[2]));
						  VO.setTourTypeId(commonMethodsUtilService.getLongValueForObject(param[3]));
						  VO.setTourType(commonMethodsUtilService.getStringValueForObject(param[4]));
						  VO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[5]));
						  VO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[6]));
						  VO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[7]));
						  VO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[8]));
						  VO.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
						  dateWiseTourDtlsList.add(VO);
					  }
				  }
			    resultVO.getSubList2().addAll(dateWiseTourDtlsList);
		 }catch(Exception e){
			 LOG.error("Exception Occured in getCandidateDetailedReport() in CoreDashboardToursService  : ",e);	 
		 }
		 return resultVO;
	}
	
	//View For Submitted Leaders
	public List<ToursBasicVO> getMemberDetailsByDesignationWise(String fromDateStr,String toDateStr,Long designationId){
		
		 List<ToursBasicVO> resultList = new ArrayList<ToursBasicVO>();
		
		try{
			
			//candidate,categoryList
			Map<Long,List<ToursBasicVO>> candidateTargetMap = new HashMap<Long,List<ToursBasicVO>>();
			Map<Long,ToursBasicVO> submittedCandidatesMap = new HashMap<Long,ToursBasicVO>();
			
			 Date fromDate=null;
			 Date toDate=null;
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 int monthSize=0;
			 
			if(fromDateStr !=null && !fromDateStr.trim().isEmpty() && toDateStr !=null && !toDateStr.trim().isEmpty()){			
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);				
				  if(fromDate !=null && toDate !=null){					 
					  monthSize = getMonthsDifference(fromDate,toDate);
				  }					
			}
				  
			  List<Long> designationIds = new ArrayList<Long>();			  
			  designationIds.add(designationId);
			  
			  //0.candidateId,1.categoryId,2.category,3.Target Days
			  List<Object[]> categoryTargetObj = selfAppraisalDesignationTargetDAO.getDesignationAndCategoryWiseCandidatesTarget(fromDate,toDate,"Category",designationIds);
			  setCandiateWiseTarget(categoryTargetObj,candidateTargetMap,"Category",monthSize);
			  List<Object[]> tourTypeTargetObj = selfAppraisalDesignationTargetDAO.getDesignationAndCategoryWiseCandidatesTarget(fromDate,toDate,"Govt",designationIds);
			  setCandiateWiseTarget(tourTypeTargetObj,candidateTargetMap,"Govt",monthSize);
			  
			  //0.candidateId,1.categoryId,2.tourDates
			  List<Object[]> rtrnDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getCandidateComplainceCntCategoryWise(fromDate, toDate, "Category",designationIds);
			  setComplainceDtls(rtrnDaysToursObjLst,candidateTargetMap,"Category");
			  List<Object[]> rtrnGovtDaysToursObjLst = selfAppraisalCandidateDayTourDAO.getCandidateComplainceCntCategoryWise(fromDate, toDate, "Govt",designationIds);
			  setComplainceDtls(rtrnGovtDaysToursObjLst,candidateTargetMap,"Govt");
			  
			  
			  List<Object[]> rtrnMemberDtlsObjLst = selfAppraisalCandidateDayTourDAO.getTourSubmitteedCandidates(fromDate, toDate, designationIds);
			  setTourSubmitteedMembers(rtrnMemberDtlsObjLst,submittedCandidatesMap,candidateTargetMap);
			  
			  if(submittedCandidatesMap !=null && submittedCandidatesMap.size()>0){
				  resultList = new ArrayList<ToursBasicVO>(submittedCandidatesMap.values());
			  }
			  
			 /* Set<Long> candidateIds = new HashSet<Long>(0);
			  if(resultList !=null && resultList.size()>0){
				  for (ToursBasicVO obj : resultList) {										 
					  if(obj.getId() !=null)
						  candidateIds.add(obj.getId());					  
				  }
			  }	*/	
			
		}catch(Exception e){
			LOG.error("Exception raised at getMemberDetailsByDesignationWise in ToursService Class ", e);
		}
		return resultList;
		
	}
	 
	 public List<Date> getBetWeenMonthsOfDates(Date fromDate,Date toDate){
		 List<Date> finalDates = new ArrayList<Date>();
		 try{				
				Calendar cal = Calendar.getInstance();
				cal.setTime(fromDate);
				cal.add(Calendar.MONTH, -1);
				
				while (cal.getTime().before(toDate)) {
				    cal.add(Calendar.MONTH, 1);
				    finalDates.add(cal.getTime());
				}
				
		 }catch(Exception e){
			 LOG.error("Exception raised at getBetWeenMonthsOfDates in ToursService Class ", e);
		 }
		 return finalDates;
	 }
	 
	 public void setCandiateWiseTarget(List<Object[]> targetObj,Map<Long,List<ToursBasicVO>> candidateTargetMap,String type,int monthSize){
		 try{
			 
			 if(targetObj !=null && targetObj.size()>0){
				 for (Object[] obj: targetObj) {
					
					 List<ToursBasicVO> categoryList = candidateTargetMap.get((Long)obj[0]);
					 
					 if(categoryList == null){
						 categoryList = new ArrayList<ToursBasicVO>();						 
						 candidateTargetMap.put((Long)obj[0], categoryList);
					 }
					 
					 String idStr = commonMethodsUtilService.getStringValueForObject(obj[1]);
					 
					 if(type !=null && type.trim().equalsIgnoreCase("Govt")){
						 idStr="0"+idStr;
					 }
					 ToursBasicVO categoryVO = new ToursBasicVO();
					 categoryVO.setIdStr(idStr);//category or Govt Id
					 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//category or Govt Name
					 categoryVO.setTargetDays(monthSize * commonMethodsUtilService.getLongValueForObject(obj[3]));
					 categoryList.add(categoryVO);
					 
				}
			 }			
		 }catch(Exception e){
			 LOG.error("Exception raised at setCandiateWiseTarget in ToursService Class ", e);
		 }
	 }
	 
	 //0.candidateId,1.categoryId,2.daysOfTours
	 public void setComplainceDtls(List<Object[]> objList,Map<Long,List<ToursBasicVO>> candidateTargetMap,String type){
		 try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 Long candidateId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 String ids = commonMethodsUtilService.getStringValueForObject(param[1]);
					 if(type.equalsIgnoreCase("Govt")){
						 ids = "0"+ids;
					 }
					 Long noOfTours = commonMethodsUtilService.getLongValueForObject(param[2]);
					 if(candidateTargetMap.get(candidateId) != null && candidateTargetMap.get(candidateId).size() > 0){
						 ToursBasicVO categoryVO = getCategoryMatchVO(candidateTargetMap.get(candidateId), ids);
						 if(categoryVO != null){
							 categoryVO.setComplainceDays(noOfTours);
						 }
					 }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception Occured in setComplainceDtls() in ToursService class  : ",e);
		 }
	 }
	 public ToursBasicVO getCategoryMatchVO(List<ToursBasicVO> categoryList,String id){
		 try{
			 if(categoryList == null || categoryList.size() == 0)
				 return null;
			 for(ToursBasicVO vo:categoryList){
				 if(vo.getIdStr().equalsIgnoreCase(id.trim())){
					 return vo;
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception Occured in getCategoryMatchVO() in ToursService class   : ",e);	 
		 }
		 return null;
	 }
	 
	 public void setTourSubmitteedMembers(List<Object[]> memberObjList,Map<Long,ToursBasicVO> submittedCandidatesMap,Map<Long,List<ToursBasicVO>> candidateTargetMap) {
		 try{
			 if(memberObjList != null && memberObjList.size() > 0){
				 for(Object[] param:memberObjList){
					 Long candidateId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 ToursBasicVO candiateVO = submittedCandidatesMap.get(candidateId);
					 	if(candiateVO == null ){
					 		candiateVO = new  ToursBasicVO();
					 		candiateVO.setId(candidateId);
					 		candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 		submittedCandidatesMap.put( commonMethodsUtilService.getLongValueForObject(param[0]), candiateVO);
					 	}					 	
					 	
					 	
				 		if(candidateTargetMap.get(candidateId) != null && candidateTargetMap.get(candidateId).size() > 0){
				 			candiateVO.setSubList3(new ArrayList<ToursBasicVO>(candidateTargetMap.get(candidateId)));	
				 		}
					 	
				 		submittedCandidatesMap.put(candiateVO.getId(), candiateVO);
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception Occured in setComplainceDtls() in CoreDashboardToursService  : ",e); 
		 }
	 }
	 
	 public static final int getMonthsDifference(Date date1, Date date2) {
		    if(date1 == null || date2 == null)
		    	return 0;
		    @SuppressWarnings("deprecation")
			int m1 = date1.getYear() * 12 + date1.getMonth();
		    @SuppressWarnings("deprecation")
			int m2 = date2.getYear() * 12 + date2.getMonth();
		    return m2 - m1 + 1;
	 }
	 
	 /*public ToursBasicVO getToursBasicOverviewDtls(String fromDateStr,String toDateStr,Long designationId){
		  
		  ToursBasicVO resultVO = new ToursBasicVO();
		  Map<Long,List<ToursBasicVO>> categoryWiseMap = new HashMap<Long, List<ToursBasicVO>>(0);
		  Map<Long,List<ToursBasicVO>> designationMonthTarget = new HashMap<Long, List<ToursBasicVO>>();
		  Map<Long,Map<Long,ToursBasicVO>> candiateDtlsMap = new HashMap<Long, Map<Long,ToursBasicVO>>();
		  Set<Long> candidateIdSet = new HashSet<Long>();
		  Map<Long,ToursBasicVO> designationMap = new HashMap<Long, ToursBasicVO>();

		  try{
			  Date fromDate=null;
				 Date toDate=null;
				 
				 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				 int monthSize=0;
				 
				if(fromDateStr !=null && !fromDateStr.trim().isEmpty() && toDateStr !=null && !toDateStr.trim().isEmpty()){			
						fromDate = sdf.parse(fromDateStr);
						toDate = sdf.parse(toDateStr);				
					  if(fromDate !=null && toDate !=null){					 
						  monthSize = getMonthsDifference(fromDate,toDate);
					  }					
				}

			 int noOfMonth = getMonthsDifference(fromDate,toDate);	 
			 
			 List<Long> designationIds = new ArrayList<Long>(); 
			 designationIds.add(designationId);
			 
			 List<Object[]> rtrnobjCtgryWseTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCntDesignation(fromDate, toDate,"Category",designationIds);
			 setCategroyWiseTarget(rtrnobjCtgryWseTargetLst,categoryWiseMap,"Category",noOfMonth);
			 List<Object[]> rtrnobjGovtTargetLst = selfAppraisalDesignationTargetDAO.getTourCategoryWiseTargetCntDesignation(fromDate, toDate,"Govt",designationIds);
			 setCategroyWiseTarget(rtrnobjGovtTargetLst,categoryWiseMap,"Govt",noOfMonth);
		
			  List<Object[]> rtrnCategoryWiseSubmittedLdrs = selfAppraisalCandidateDayTourDAO.getCategoryWiseTourSubmittedLeaderDesignation(fromDate, toDate, "Category",designationIds);
			  setCategoryWiseTourSubmittedLeader(rtrnCategoryWiseSubmittedLdrs,categoryWiseMap,"Category");
			  List<Object[]> rtrnGovtSubmittedLdrs = selfAppraisalCandidateDayTourDAO.getCategoryWiseTourSubmittedLeaderDesignation(fromDate, toDate, "Govt",designationIds);
			  setCategoryWiseTourSubmittedLeader(rtrnGovtSubmittedLdrs,categoryWiseMap,"Govt");
			  
			  
			 List<Object[]> rtrnDsgntnWseLderObjLst = selfAppraisalCandidateLocationNewDAO.getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, userTypeId,"overAll"); 
			 setDesignationWiseLeaders(rtrnDsgntnWseLderObjLst,designationMap,categoryWiseMap);
			 
			 List<Object[]> rtrnDsgntnWseCandiateObjLst = selfAppraisalCandidateLocationNewDAO.getNoOfLdrsCntDesignationByBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, userTypeId,"Candiate"); 
			 setCandidateIds(rtrnDsgntnWseCandiateObjLst,candidateIdSet);
			 
			 List<Object[]> rtrnSubmittedLdrObjLst = selfAppraisalCandidateDayTourDAO.getToursSubmittedLeaderCntDesignationBy(fromDate, toDate,candidateIdSet);
			 setTourSubmitteedLdrCnt(rtrnSubmittedLdrObjLst,designationMap);
			
			 List<Object[]> rtrnCategoryWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Category",null,candidateIdSet);
			 prepareCandiateWiseDtlsToCalculateComplainceCandiate(rtrnCategoryWiseComplainceOblLst,candiateDtlsMap,categoryWiseMap,"category");
			 setCategroyWiseComplainceCandidateCnt(rtrnCategoryWiseComplainceOblLst,designationMap,"Category");
			 List<Object[]> rtrnGovtWorkWiseComplainceOblLst = selfAppraisalCandidateDayTourDAO.getLeaderComplainceCntCategoryWise(fromDate, toDate, "Govt",null,candidateIdSet);
			 prepareCandiateWiseDtlsToCalculateComplainceCandiate(rtrnGovtWorkWiseComplainceOblLst,candiateDtlsMap,categoryWiseMap,"Govt");
			 setCategroyWiseComplainceCandidateCnt(rtrnGovtWorkWiseComplainceOblLst,designationMap,"Govt");
			
			 
			 if(designationMap != null && designationMap.size() > 0){
				 for(Entry<Long, ToursBasicVO> entry:designationMap.entrySet()){
					 if(designationMonthTarget.get(entry.getKey()) != null && designationMonthTarget.get(entry.getKey()).size() > 0){
						 entry.getValue().getSubList().addAll(designationMonthTarget.get(entry.getKey()));	 
					 }
				 }
			 }
			 
			 //calculating Complaince Member Designation Wise
			 if(candiateDtlsMap != null && candiateDtlsMap.size() > 0){
				 
				 for(Entry<Long,Map<Long,ToursBasicVO>> designationEntry:candiateDtlsMap.entrySet()){
					 
					 if(designationEntry.getValue() != null && designationEntry.getValue().size() > 0l){
						 
						 for(Entry<Long,ToursBasicVO> entry:designationEntry.getValue().entrySet()){
							 
							 List<ToursBasicVO> categoryList = entry.getValue().getSubList3();
							 
							   if(categoryList != null && categoryList.size() > 0){
									   Double totalPer= 0.0d;
									   for(ToursBasicVO VO:categoryList){
										   totalPer = totalPer+VO.getComplaincePer();
									   }
									   
									   Integer totalCount =0;
									   if(categoryList != null && categoryList.size() > 0){
										    totalCount = categoryList.size() * 100;   
									   }
									   
									   ToursBasicVO candiateVO = designationMap.get(designationEntry.getKey());
									   if(candiateVO != null){
										   Double percentage = calculatePercantageBasedOnDouble(totalPer,totalCount.doubleValue());
										     if(percentage >=100d){
											    candiateVO.setComplainceCnt(candiateVO.getComplainceCnt()+1);
												if(candiateVO.getComplaincandidateIdsSet() == null){
													 candiateVO.setComplaincandidateIdsSet(new HashSet<Long>()); 
												}
												candiateVO.getComplaincandidateIdsSet().add(entry.getKey());
											 }else{
												 candiateVO.setNonComplainceCnt(candiateVO.getNonComplainceCnt()+1); 
												if(candiateVO.getNoNComplaincandidateIdsSet() == null){
													candiateVO.setNoNComplaincandidateIdsSet(new HashSet<Long>(0));
												}
												candiateVO.getNoNComplaincandidateIdsSet().add(entry.getKey());
											 }     
									   }
									
								  }
							   }
						 }
					 }
				 }
			 
			  if(designationMap != null && designationMap.size() > 0){
				   resultVO.getSubList().addAll(new ArrayList<ToursBasicVO>(designationMap.values()));   
				   designationMap.clear();  
			   }
				  
		  }catch(Exception e){
			  LOG.error("Exception Occured in getToursBasicOverviewDtls() in CoreDashboardToursService  : ",e); 
		  }
		  return resultVO;
	  }*/
	 
	 public void setCategroyWiseTarget(List<Object[]> objLst,Map<Long,List<ToursBasicVO>> categoryWiseMap,String type,int noOfMonth){
		 try{
			 if(objLst != null && objLst.size() > 0){
				 for(Object[] param:objLst){
					 List<ToursBasicVO> categoryList = categoryWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 	if(categoryList == null){
					 		categoryList = new ArrayList<ToursBasicVO>();
					 		categoryWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryList);
					 	}
					 	ToursBasicVO categoryVO = new ToursBasicVO();
					 	 String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
						 if(type.equalsIgnoreCase("Govt")){
							 idStr = "0"+idStr;
						 }
					 	categoryVO.setIdStr(idStr);
					 	categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					 	categoryVO.setTargetDays(commonMethodsUtilService.getLongValueForObject(param[4])*noOfMonth);
					 	categoryList.add(categoryVO);
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception Occured in setCategroyWiseTarget() in CoreDashboardToursService  : ",e);
		 }
	 }
	 
	 public void setCategoryWiseTourSubmittedLeader(List<Object[]> objList,Map<Long,List<ToursBasicVO>> categoryWiseMap,String type){
		 try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 Long designationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 String idStr = commonMethodsUtilService.getStringValueForObject(param[2]);
					 if(type.equalsIgnoreCase("Govt")){
						 idStr = "0"+idStr;
					 }
					 ToursBasicVO categoryVO = getCategoryMatchVO(categoryWiseMap.get(designationId),idStr);
					  if(categoryVO != null){
						  categoryVO.setSubmitedLeaderCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
					  }
				 }
			 }
		 }catch(Exception e){
			 LOG.error("Exception Occured in setCategoryWiseTourSubmittedLeader() in CoreDashboardToursService  : ",e);
		 }
	 }
    
}
