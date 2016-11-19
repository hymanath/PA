package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssuePersonDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserPerformanceDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserPerformanceTypeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabUserEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringVerificationVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.GISIssuesVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserPerformanceVO;
import com.itgrids.partyanalyst.model.CadreRegIssue;
import com.itgrids.partyanalyst.model.CadreRegIssuePerson;
import com.itgrids.partyanalyst.model.CadreRegIssueStatus;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;
import com.itgrids.partyanalyst.model.CadreRegIssueType;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformance;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class FieldMonitoringService implements IFieldMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	//Attributes
	private IFieldVendorLocationDAO fieldVendorLocationDAO;
	private IFieldVendorTabUserDAO  fieldVendorTabUserDAO;
	private TransactionTemplate transactionTemplate;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO     districtDAO;
	private IStateDAO   stateDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IUserAddressDAO userAddressDAO;
	private ICadreRegIssueTypeDAO  cadreRegIssueTypeDAO;
	
	private DateUtilService dateUtilService;
	private ICadreRegIssueDAO cadreRegIssueDAO;
	private ICadreRegIssueTrackDAO cadreRegIssueTrackDAO;
	private ICadreRegIssueStatusDAO cadreRegIssueStatusDAO;
	private ICadreRegUserDAO cadreRegUserDAO;
	private ICadreRegUserTabUserDAO cadreRegUserTabUserDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO;
	private ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO;
	private ICadreRegIssuePersonDAO cadreRegIssuePersonDAO;
	private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
	private ICadreSurveyUserPerformanceDAO cadreSurveyUserPerformanceDAO;
	private ICadreSurveyUserPerformanceTypeDAO cadreSurveyUserPerformanceTypeDAO;
	private ITdpCadreTargetCountDAO tdpCadreTargetCountDAO;
	private ITdpCadreDataVerificationDAO tdpCadreDataVerificationDAO;
	
	//Setters
	
	public ITdpCadreLocationInfoDAO getTdpCadreLocationInfoDAO() {
		return tdpCadreLocationInfoDAO;
	}
	public void setTdpCadreLocationInfoDAO(
			ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO) {
		this.tdpCadreLocationInfoDAO = tdpCadreLocationInfoDAO;
	}
	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}
	public ITdpCadreUserHourRegInfo getTdpCadreUserHourRegInfoDAO() {
		return tdpCadreUserHourRegInfoDAO;
	}
	public void setTdpCadreUserHourRegInfoDAO(
			ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO) {
		this.tdpCadreUserHourRegInfoDAO = tdpCadreUserHourRegInfoDAO;
	}
	public ITabUserEnrollmentInfoDAO getTabUserEnrollmentInfoDAO() {
		return tabUserEnrollmentInfoDAO;
	}
	public void setTabUserEnrollmentInfoDAO(
			ITabUserEnrollmentInfoDAO tabUserEnrollmentInfoDAO) {
		this.tabUserEnrollmentInfoDAO = tabUserEnrollmentInfoDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	public void setFieldVendorLocationDAO(
			IFieldVendorLocationDAO fieldVendorLocationDAO) {
		this.fieldVendorLocationDAO = fieldVendorLocationDAO;
	}
	public ICadreRegUserTabUserDAO getCadreRegUserTabUserDAO() {
		return cadreRegUserTabUserDAO;
	}
	public void setCadreRegUserTabUserDAO(
			ICadreRegUserTabUserDAO cadreRegUserTabUserDAO) {
		this.cadreRegUserTabUserDAO = cadreRegUserTabUserDAO;
	}
	public ICadreRegUserDAO getCadreRegUserDAO() {
		return cadreRegUserDAO;
	}
	public void setCadreRegUserDAO(ICadreRegUserDAO cadreRegUserDAO) {
		this.cadreRegUserDAO = cadreRegUserDAO;
	}
	public ICadreRegIssueStatusDAO getCadreRegIssueStatusDAO() {
		return cadreRegIssueStatusDAO;
	}
	public void setCadreRegIssueStatusDAO(
			ICadreRegIssueStatusDAO cadreRegIssueStatusDAO) {
		this.cadreRegIssueStatusDAO = cadreRegIssueStatusDAO;
	}
	public void setFieldVendorTabUserDAO(
			IFieldVendorTabUserDAO fieldVendorTabUserDAO) {
		this.fieldVendorTabUserDAO = fieldVendorTabUserDAO;
	}
	
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
    
    public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	
	public void setCadreRegIssueTypeDAO(ICadreRegIssueTypeDAO cadreRegIssueTypeDAO) {
		this.cadreRegIssueTypeDAO = cadreRegIssueTypeDAO;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	public void setCadreRegIssueDAO(ICadreRegIssueDAO cadreRegIssueDAO) {
		this.cadreRegIssueDAO = cadreRegIssueDAO;
	}
	
	public void setCadreRegIssueTrackDAO(
			ICadreRegIssueTrackDAO cadreRegIssueTrackDAO) {
		this.cadreRegIssueTrackDAO = cadreRegIssueTrackDAO;
	}
	
	public ICadreRegIssuePersonDAO getCadreRegIssuePersonDAO() {
		return cadreRegIssuePersonDAO;
	}
	public void setCadreRegIssuePersonDAO(ICadreRegIssuePersonDAO cadreRegIssuePersonDAO) {
		this.cadreRegIssuePersonDAO = cadreRegIssuePersonDAO;
	}
	
	public ICadreSurveyUserPerformanceDAO getCadreSurveyUserPerformanceDAO() {
		return cadreSurveyUserPerformanceDAO;
	}
	public void setCadreSurveyUserPerformanceDAO(ICadreSurveyUserPerformanceDAO cadreSurveyUserPerformanceDAO) {
		this.cadreSurveyUserPerformanceDAO = cadreSurveyUserPerformanceDAO;
	}
	
	public ICadreSurveyUserPerformanceTypeDAO getCadreSurveyUserPerformanceTypeDAO() {
		return cadreSurveyUserPerformanceTypeDAO;
	}
	public void setCadreSurveyUserPerformanceTypeDAO(ICadreSurveyUserPerformanceTypeDAO cadreSurveyUserPerformanceTypeDAO) {
		this.cadreSurveyUserPerformanceTypeDAO = cadreSurveyUserPerformanceTypeDAO;
	}
	
	public ITdpCadreTargetCountDAO getTdpCadreTargetCountDAO() {
		return tdpCadreTargetCountDAO;
	}
	public void setTdpCadreTargetCountDAO(ITdpCadreTargetCountDAO tdpCadreTargetCountDAO) {
		this.tdpCadreTargetCountDAO = tdpCadreTargetCountDAO;
	}
	
	public ITdpCadreDataVerificationDAO getTdpCadreDataVerificationDAO() {
		return tdpCadreDataVerificationDAO;
	}
	public void setTdpCadreDataVerificationDAO(ITdpCadreDataVerificationDAO tdpCadreDataVerificationDAO) {
		this.tdpCadreDataVerificationDAO = tdpCadreDataVerificationDAO;
	}
	//business method.
	public List<IdAndNameVO> getVendors(Long stateId){
    	List<IdAndNameVO> returnList = null;
    	try{
    		
			List<Object[]> list =   fieldVendorLocationDAO.getVendors(stateId);
    		
			returnList = setDataToIdNameVOList(list);
			
		}catch(Exception e) {
			LOG.error("Exception occurred at getVendors() of FieldMonitoringService", e);
		}
    	return returnList;
    }
	
    public List<IdAndNameVO> setDataToIdNameVOList(List<Object[]> list){
    	
    	List<IdAndNameVO> returnList = null;
    	if( list != null && list.size() > 0){
    		
    		returnList = new ArrayList<IdAndNameVO>();
    		
    		for(Object[] obj : list){
    			IdAndNameVO VO = new IdAndNameVO();
    			VO.setId(obj[0]!= null ?(Long)obj[0]:0l);
    			VO.setName(obj[1]!= null ?obj[1].toString():"");
    			returnList.add(VO);
    		}
    	}
    	return returnList;
    }
    
    public List<IdAndNameVO> getVendorDistricts(Long stateId,Long vendorId){
    	List<IdAndNameVO> returnList = null;
    	try{
    		
			List<Object[]> list =   fieldVendorLocationDAO.getVendorDistricts(stateId, vendorId);
    		
			returnList = setDataToIdNameVOList(list);
			
		}catch(Exception e) {
			LOG.error("Exception occurred at getVendorDistricts() of FieldMonitoringService", e);
		}
    	return returnList;
    }
    
    public List<IdAndNameVO> getVendorConstituencies(Long vendorId,Long districtId){
    	List<IdAndNameVO> returnList = null;
    	try{
    		
			List<Object[]> list =   fieldVendorLocationDAO.getVendorConstituencies(vendorId,districtId);
    		
			returnList = setDataToIdNameVOList(list);
			
		}catch(Exception e) {
			LOG.error("Exception occurred at getVendorConstituencies() of FieldMonitoringService", e);
		}
    	return returnList;
    }
    
    public List<IdAndNameVO> getCadreRegIssueType() {
		List<IdAndNameVO> regIssueType = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> issueType = cadreRegIssueTypeDAO.getCadreRegIssueType();
			if (issueType != null && issueType.size() > 0) {
				for (Object[] objects : issueType) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					regIssueType.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getCadreRegIssueType() in FieldMonitoringService class", e);
		}
		return regIssueType;
	} 
    
    public FieldMonitoringVO getTabUsersDetailsByVendorAndLocation(Long vendorId,String fromDateStr,String toDateStr,String locationType,Long locationVal){
    	FieldMonitoringVO returnVO = new FieldMonitoringVO();
		
    	try {
    		List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
			Date startDate = null;
			Date endDate = null;
			Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> list = cadreRegIssueDAO.getTabUsersDetailsByVendorAndLocation(vendorId, startDate, endDate, locationType, locationVal);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					
					vo.setCadreSurveyUserId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setUserName(obj[1] != null ? obj[1].toString():"");
					vo.setTabUserId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
					vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
					 if(obj[5]!=null){
	    					Date date = (Date)obj[5];
	    					vo.setFirstRecord(returnTime.format(date));
	    				}
					 if(obj[6]!=null){
	    					Date date = (Date)obj[6];
	    					vo.setRecentRecord(returnTime.format(date));
	    				}
					//vo.setFirstRecord(obj[5] != null ? obj[5].toString():"");
					//vo.setRecentRecord(obj[6] != null ? obj[6].toString():"");
					vo.setTotalCount(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					
					returnList.add(vo);
				}
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			//LastHour Counts
			List<Object[]> list1 = cadreRegIssueDAO.getLastHourCounts(vendorId, lastOneHourTime, today, locationType, locationVal);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
					if(vo != null)
						vo.setLastHourCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
				}
			}
			
			//Issues Counts
			List<Object[]> list2 = cadreRegIssueDAO.getcadreRegIssuesCounts(vendorId, locationType, locationVal, startDate, endDate);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
					if(vo != null){
						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						if(statusId.longValue() == 1l)
							vo.setOpenIssues(count);
						else if(statusId.longValue() == 2l)
							vo.setFixedIssues(count);
						else if(statusId.longValue() == 3l)
							vo.setClosedIssues(count);
					}
				}
			}
			
			returnVO = getDataCollectorsCounts(vendorId, fromDateStr, toDateStr, locationType, locationVal);
			returnVO.setSubList(returnList);
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getTabUsersDetailsByVendorAndLocation() of FieldMonitoringService", e);
		}
    	return returnVO;
    }
    
    public FieldMonitoringVO getTabUsersDetailsByVendorAndLocationNew(Long loginUserId,Long constituencyId,Long cadreSurveyUserId,String fromDateStr,String toDateStr,Long districtId){
    	FieldMonitoringVO returnVO = new FieldMonitoringVO();
		
    	try {
    		
    		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId);
    		Long usersCount = cadreRegUserTabUserDAO.getAssignedUsersCountForRegUser(cadreRegUserId);
    		if(usersCount == null || usersCount == 0l)
    			cadreRegUserId = null;
    		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
    			List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
    			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    			SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
    			Date startDate = null;
    			Date endDate = null;
    			Date today = new Date();
    			if(fromDateStr != null && toDateStr != null){
    				startDate = sdf.parse(fromDateStr);
    				endDate = sdf.parse(toDateStr);
    			}
    			
    			Map<Long,FieldMonitoringVO> templMap = new LinkedHashMap<Long, FieldMonitoringVO>();
    			List<Long> startedUsers = new ArrayList<Long>();
    			
    			List<Object[]> templist = cadreRegIssueDAO.getTotalTabUsersDetailsByVendorAndLocationNew(cadreRegUserId, null, null, constituencyId, cadreSurveyUserId, districtId,null);
    			/*if(templist == null || templist.isEmpty())
    				templist = cadreRegIssueDAO.getTotalTabUsersDetailsByVendorAndLocationNew(null, startDate, endDate, constituencyId, cadreSurveyUserId, districtId);*/
    			if(templist == null || templist.isEmpty())
    				templist = cadreRegIssueDAO.getTotalTabUsersDetailsByVendorAndLocationNew(null, null, null, constituencyId, cadreSurveyUserId, districtId,null);
    			if(templist != null && !templist.isEmpty()){
    				for (Object[] obj : templist) {
						Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						FieldMonitoringVO vo = new FieldMonitoringVO();
						vo.setCadreSurveyUserId(userId);
						vo.setUserName(obj[1] != null ? obj[1].toString():"");
						vo.setTabUserId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
						vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
						
						returnList.add(vo);
						//templMap.put(userId, vo);
					}
    			}
    			
    			List<Object[]> list = cadreRegIssueDAO.getTabUsersDetailsByVendorAndLocationNew(cadreRegUserId, startDate, endDate, constituencyId, cadreSurveyUserId, districtId,null);
    			/*if(list == null || list.isEmpty())
    				list = cadreRegIssueDAO.getTabUsersDetailsByVendorAndLocationNew(null, startDate, endDate, constituencyId, cadreSurveyUserId, districtId);*/
    			if(list == null || list.isEmpty())
    				list = cadreRegIssueDAO.getTabUsersDetailsByVendorAndLocationNew(null, startDate, endDate, constituencyId, cadreSurveyUserId, districtId,null);
    			if(list != null && !list.isEmpty()){
    				for (Object[] obj : list) {
    					
    					Long cadreUserId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
    					Long tabUserId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
    					
    					/*if(startedUsers != null && !startedUsers.contains(cadreUserId))
    						startedUsers.add(cadreUserId);*/
    					
    					//FieldMonitoringVO vo = templMap.get(cadreUserId);
    					FieldMonitoringVO vo = getMatchedVOByList(cadreUserId, tabUserId, returnList);
    					if(vo != null){
    						//if(vo.getTabUserId().longValue() == 0l){
    							vo.setCadreSurveyUserId(cadreUserId);
    	    					vo.setUserName(obj[1] != null ? obj[1].toString():"");
    	    					vo.setTabUserId(tabUserId);
    	    					vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
    	    					vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
    	    					 if(obj[5]!=null){
    	    	    					Date date = (Date)obj[5];
    	    	    					vo.setFirstRecord(returnTime.format(date));
    	    	    				}
    	    					 if(obj[6]!=null){
    	    	    					Date date = (Date)obj[6];
    	    	    					vo.setRecentRecord(returnTime.format(date));
    	    	    				}
    	    					//vo.setFirstRecord(obj[5] != null ? obj[5].toString():"");
    	    					//vo.setRecentRecord(obj[6] != null ? obj[6].toString():"");
    	    					vo.setTotalCount(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
    	    					vo.setConstituencyId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
    	    					vo.setImagePath(obj[9] != null ? obj[9].toString():"");
    	    					
    	    					//returnList.add(vo);
    						/*}
    						else{
    							vo = new FieldMonitoringVO();
    							
    							vo.setCadreSurveyUserId(cadreUserId);
    	    					vo.setUserName(obj[1] != null ? obj[1].toString():"");
    	    					vo.setTabUserId(tabUserId);
    	    					vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
    	    					vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
    	    					 if(obj[5]!=null){
    	    	    					Date date = (Date)obj[5];
    	    	    					vo.setFirstRecord(returnTime.format(date));
    	    	    				}
    	    					 if(obj[6]!=null){
    	    	    					Date date = (Date)obj[6];
    	    	    					vo.setRecentRecord(returnTime.format(date));
    	    	    				}
    	    					//vo.setFirstRecord(obj[5] != null ? obj[5].toString():"");
    	    					//vo.setRecentRecord(obj[6] != null ? obj[6].toString():"");
    	    					vo.setTotalCount(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
    	    					vo.setConstituencyId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
    	    					vo.setImagePath(obj[9] != null ? obj[9].toString():"");
    	    					
    	    					returnList.add(vo);
    						}*/
    					}
    					
    				}
    			}
    			
    			/*if(templMap != null){
    				for (Map.Entry<Long, FieldMonitoringVO> entry : templMap.entrySet()){
    					Long cadreUser = entry.getKey();
    					if(startedUsers != null && !startedUsers.contains(cadreUser))
    						returnList.add(entry.getValue());
    				}
    			}*/
    			
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(today);
    			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
    			Date lastOneHourTime = cal.getTime();
    			
    			//LastHour Counts
    			List<Object[]> list1 = cadreRegIssueDAO.getLastHourCountsNew(cadreRegUserId, lastOneHourTime, today, constituencyId, cadreSurveyUserId, districtId,null);
    			/*if(list1 == null || list1.isEmpty())
    				list1 = cadreRegIssueDAO.getLastHourCountsNew(null, lastOneHourTime, today, constituencyId, cadreSurveyUserId, districtId);*/
    			if(list1 == null || list1.isEmpty())
    				list1 = cadreRegIssueDAO.getLastHourCountsNew(null, lastOneHourTime, today, constituencyId, cadreSurveyUserId, districtId,null);
    			if(list1 != null && !list1.isEmpty()){
    				for (Object[] obj : list1) {
    					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
    					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
    					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
    					if(vo != null)
    						vo.setLastHourCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
    				}
    			}
    			
    			//Issues Counts
    			List<Object[]> list2 = cadreRegIssueDAO.getcadreRegIssuesCountsNew(cadreRegUserId, constituencyId, cadreSurveyUserId, startDate, endDate, districtId,null);
    			/*if(list2 == null || list2.isEmpty())
    				list2 = cadreRegIssueDAO.getcadreRegIssuesCountsNew(null, constituencyId, cadreSurveyUserId, startDate, endDate, districtId);*/
    			if(list2 == null || list2.isEmpty())
    				list2 = cadreRegIssueDAO.getcadreRegIssuesCountsNew(null, constituencyId, cadreSurveyUserId, startDate, endDate, districtId,null);
    			if(list2 != null && !list2.isEmpty()){
    				for (Object[] obj : list2) {
    					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
    					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
    					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
    					if(vo != null){
    						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
    						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
    						if(statusId.longValue() == 1l)
    							vo.setOpenIssues(count);
    						else if(statusId.longValue() == 2l)
    							vo.setFixedIssues(count);
    						else if(statusId.longValue() == 3l)
    							vo.setClosedIssues(count);
    					}
    				}
    			}
    			
    			returnVO = getDataCollectorsCountsNew(cadreRegUserId, constituencyId, cadreSurveyUserId, fromDateStr, toDateStr, districtId);
    			returnVO.setSubList(returnList);
    		//}
    		
		} catch (Exception e) {
			LOG.error("Exception occurred at getTabUsersDetailsByVendorAndLocation() of FieldMonitoringService", e);
		}
    	return returnVO;
    }
    
    public FieldMonitoringVO getMatchedVOByList(Long userId,Long tabUserId,List<FieldMonitoringVO> list){
    	FieldMonitoringVO returnvo = null;
    	try {
			if(list != null && !list.isEmpty()){
				for (FieldMonitoringVO vo : list) {
					if(vo.getCadreSurveyUserId().longValue() == userId.longValue()){
						if(vo.getTabUserId().longValue() == tabUserId.longValue()){
							return vo;
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOByList() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    public FieldMonitoringVO getMatchdVOByList(Long userId,Long tabUserId,Long constituencyId,List<FieldMonitoringVO> list){
    	FieldMonitoringVO returnvo = null;
    	try {
			if(list != null && !list.isEmpty()){
				for (FieldMonitoringVO vo : list) {
					if(vo.getCadreSurveyUserId().longValue() == userId.longValue()){
						if(vo.getTabUserId().longValue() == tabUserId.longValue()){
							if(vo.getConstituencyId().longValue() == constituencyId.longValue())
								return vo;
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchdVOByList() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    public FieldMonitoringVO getDataCollectorsCountsNew(Long cadreRegUserId,Long constituencyId,Long userId,String fromDateStr,String toDateStr,Long districtId){
    	FieldMonitoringVO returnvo = new FieldMonitoringVO();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			Long usersCnt = cadreRegIssueDAO.getCadreRegUsersId(cadreRegUserId);
			
			Long totalCount = cadreRegIssueDAO.getTotalDataCollectorsCountsVendorAndLocationNew(cadreRegUserId, constituencyId, userId, startDate, endDate, districtId);
			/*if(totalCount == null || totalCount == 0l)
				totalCount = cadreRegIssueDAO.getTotalDataCollectorsCountsVendorAndLocationNew(null, constituencyId, userId, startDate, endDate, districtId);*/
			Long activeCount = cadreRegIssueDAO.getActiveDataCollectorsCountsVendorAndLocationNew(cadreRegUserId, constituencyId, userId, lastOneHourTime, today, districtId);
			/*if((activeCount == null || activeCount == 0l) && (usersCnt == null || usersCnt == 0l))
				activeCount = cadreRegIssueDAO.getActiveDataCollectorsCountsVendorAndLocationNew(null, constituencyId, userId, lastOneHourTime, today, districtId);*/
			Long passiveCount = 0l;
			if(totalCount == null)
				totalCount = 0l;
			if(activeCount == null)
				activeCount = 0l;
			if(totalCount > 0l)
				passiveCount = totalCount - activeCount;
			
			returnvo.setTotalDataCollectors(totalCount);
			returnvo.setActiveUsers(activeCount);
			returnvo.setPassiveUsers(passiveCount);
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDataCollectorsCounts() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    public FieldMonitoringVO getDataCollectorsCounts(Long vendorId,String fromDateStr,String toDateStr,String locationType,Long locationVal){
    	FieldMonitoringVO returnvo = new FieldMonitoringVO();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			Long totalCount = cadreRegIssueDAO.getTotalDataCollectorsCountsVendorAndLocation(vendorId, startDate, endDate, locationType, locationVal);
			Long activeCount = cadreRegIssueDAO.getActiveDataCollectorsCountsVendorAndLocation(vendorId, lastOneHourTime, today, locationType, locationVal);
			Long passiveCount = 0l;
			if(totalCount == null)
				totalCount = 0l;
			if(activeCount == null)
				activeCount = 0l;
			if(totalCount > 0l)
				passiveCount = totalCount - activeCount;
			
			returnvo.setTotalDataCollectors(totalCount);
			returnvo.setActiveUsers(activeCount);
			returnvo.setPassiveUsers(passiveCount);
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDataCollectorsCounts() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This service is used store an issue to a tab user.
	  *  @since 18-OCTOBER-2016
	  */
    public ResultStatus saveFieldIssue(final FieldMonitoringIssueVO inputVO){
		final ResultStatus rs = new ResultStatus();
		final DateUtilService dateUtilService = new DateUtilService();
		
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
		        	Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(inputVO.getLoginUserId());
		        	   CadreRegIssue cadreRegIssue = new CadreRegIssue();
		        	
		        	   cadreRegIssue.setCadreSurveyUserId(inputVO.getCadreSurveyUserId());
		        	   cadreRegIssue.setTabUserInfoId(inputVO.getTabUserInfoId());
		        	   
		        	   //location 
		        	   cadreRegIssue.setLocationScopeId(4l);
		        	   cadreRegIssue.setLocationValue(inputVO.getConstituencyId());
		        	   
		        	   UserAddress userAddress = new UserAddress(); 
		        	   Constituency constituency = null;
		        	   Constituency parliamentConstituency = null;
		        	   if( inputVO.getConstituencyId() != null && inputVO.getConstituencyId() > 0){
		        		   constituency = constituencyDAO.get(inputVO.getConstituencyId());
		        		   List<Constituency> parliamentList = delimitationConstituencyAssemblyDetailsDAO.getPCCompleteDetailsByAcId(inputVO.getConstituencyId());
		        		   if(parliamentList!= null && parliamentList.size() > 0){
		        			   parliamentConstituency = parliamentList.get(0);
		        		   }
		        	   }
		        	   District district = null;
		        	   if( constituency != null && constituency.getDistrict() != null && constituency.getDistrict().getDistrictId() != null && constituency.getDistrict().getDistrictId() > 0l){
		        		   district = districtDAO.get(constituency.getDistrict().getDistrictId());
		        	   }
		        	   State state = null;
		        	   if(district != null && district.getState() != null && district.getState().getStateId() != null && district.getState().getStateId() > 0l){
		        		   state = stateDAO.get(district.getState().getStateId());
		        	   }
		        	   userAddress.setState(state);
					   userAddress.setDistrict(district);
		        	   userAddress.setConstituency(constituency);
		        	   userAddress.setParliamentConstituency(parliamentConstituency);
		        	   userAddress = userAddressDAO.save(userAddress);
		        	   
		        	   cadreRegIssue.setUserAddressId(userAddress.getUserAddressId());
		        	   
		        	   cadreRegIssue.setCadreRegIssueTypeId(inputVO.getIssueTypeId());
		        	   cadreRegIssue.setDescription(inputVO.getDescription());
		        	   cadreRegIssue.setCadreRegIssueStatusId(1L);//open
		        	   Date currentDate = dateUtilService.getCurrentDateAndTime();
		        	   cadreRegIssue.setInsertedTime(currentDate);
		        	   cadreRegIssue.setUpdatedTime(currentDate);
		        	   cadreRegIssue.setCreatedBy(cadreRegUserId);
		        	   cadreRegIssue.setUpdatedBy(cadreRegUserId);
		        	   
		        	   cadreRegIssue = cadreRegIssueDAO.save(cadreRegIssue);
		        	   
		        	   if(inputVO.getIssueTypeId() == 1L){
		        	   CadreRegIssuePerson  cadreRegIssuePerson = new CadreRegIssuePerson();
		        	   cadreRegIssuePerson.setCadreRegIssueId(cadreRegIssue.getCadreRegIssueId());
		        	   cadreRegIssuePerson.setMandal(inputVO.getMandalId());
		        	   cadreRegIssuePerson.setName(inputVO.getFirstname());
		        	   cadreRegIssuePerson.setMobileNo(inputVO.getMobileNo());
		        	   cadreRegIssuePerson.setInsetedTime(dateUtilService.getCurrentDateAndTime());
		        	   cadreRegIssuePerson.setIsDeleted("N");
		        	   cadreRegIssuePerson = cadreRegIssuePersonDAO.save(cadreRegIssuePerson);
		        	   }
		        	   //tracking 
		        	   CadreRegIssueTrack cadreRegIssueTrack = new CadreRegIssueTrack();
		        	   cadreRegIssueTrack.setCadreRegIssueId(cadreRegIssue.getCadreRegIssueId());
		        	   cadreRegIssueTrack.setCadreRegIssueTypeId(inputVO.getIssueTypeId());
		        	   cadreRegIssueTrack.setDescription(inputVO.getDescription());
		        	   cadreRegIssueTrack.setCadreRegIssueStatusId(1L);//open
		        	   cadreRegIssueTrack.setInsertedBy(cadreRegUserId);
		        	   cadreRegIssueTrack.setInsertedTime(currentDate);
		        	   cadreRegIssueTrackDAO.save(cadreRegIssueTrack);
		        	   
			           rs.setResultCode(1);
			           rs.setMessage("success");
		         }
		    });
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveFieldIssue", e);
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
		return rs;
	}
    
    /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This service is used to get the issues for a particulat tab user of a cadre survey user by issue status.
	  *  @since 18-OCTOBER-2016
	  */
    public List<FieldMonitoringIssueVO> getIssuesForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long issueStatusId,Long vendorId,String locationType,Long locationVal){
    	
    	List<FieldMonitoringIssueVO> returnList = null;
    	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
		
		Date startDate = null;
		Date endDate = null;
    	try{
    		
    		if(fromDateStr != null && toDateStr != null){
    			startDate = sdf.parse(fromDateStr);
    			endDate = sdf.parse(toDateStr);
    		}
    		
    		List<Object[]> issueDetails = cadreRegIssueDAO.getIssuesForATabUserByStatus(cadreSurveyUserId, tabUserInfoId, startDate, endDate, issueStatusId, vendorId, locationType, locationVal);
    		
    		if( issueDetails != null && issueDetails.size() > 0)
    		{
    			returnList = new ArrayList<FieldMonitoringIssueVO>();
    			
    			for(Object[] obj : issueDetails)
    			{
    				FieldMonitoringIssueVO issueVO = new FieldMonitoringIssueVO();
    				
    				issueVO.setCadreRegIssueId( obj[0]!= null ? (Long)obj[0] : 0l);
    				issueVO.setDescription(obj[1]!=null ? obj[1].toString() :"");
    				
    				if(obj[2]!=null){
    					Date date = (Date)obj[2];
    					issueVO.setDateStr(returnTime.format(date));
    				}
    				
    				issueVO.setIssueTypeId(obj[3]!= null ? (Long)obj[3] : 0l);
    				issueVO.setIssueType(obj[4]!= null ? obj[4].toString() :"");
    				
    				issueVO.setIssueStatusId(obj[5]!=null ? (Long)obj[5]:0l);
    				issueVO.setIssueStatus(obj[6]!= null ? obj[6].toString() :"");
    			   if(obj[7]!=null){
    					Date date = (Date)obj[7];
    					issueVO.setUpdatedTime(returnTime.format(date));
    				}
    				
    				returnList.add(issueVO);
    			}
    		}
    		
		}catch(Exception e){
			LOG.error("Exception raised at getIssuesForATabUser", e);
		}
    	return returnList;
    }
    
public List<FieldMonitoringIssueVO> getIssuesForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long issueStatusId,Long loginUserId,Long stateId){
    	
    	List<FieldMonitoringIssueVO> returnList = null;
    	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
		
		Date startDate = null;
		Date endDate = null;
    	try{
    		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId);
    		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
    			if(fromDateStr != null && toDateStr != null){
        			startDate = sdf.parse(fromDateStr);
        			endDate = sdf.parse(toDateStr);
        		}
        		
        		List<Object[]> issueDetails = cadreRegIssueDAO.getIssuesForATabUserByStatusNew(cadreSurveyUserId, tabUserInfoId, startDate, endDate, issueStatusId, cadreRegUserId,stateId);
        		
        		if( issueDetails != null && issueDetails.size() > 0)
        		{
        			returnList = new ArrayList<FieldMonitoringIssueVO>();
        			
        			for(Object[] obj : issueDetails)
        			{
        				FieldMonitoringIssueVO issueVO = new FieldMonitoringIssueVO();
        				
        				issueVO.setCadreRegIssueId( obj[0]!= null ? (Long)obj[0] : 0l);
        				issueVO.setDescription(obj[1]!=null ? obj[1].toString() :"");
        				
        				if(obj[2]!=null){
        					Date date = (Date)obj[2];
        					issueVO.setDateStr(returnTime.format(date));
        				}
        				
        				issueVO.setIssueTypeId(obj[3]!= null ? (Long)obj[3] : 0l);
        				issueVO.setIssueType(obj[4]!= null ? obj[4].toString() :"");
        				
        				issueVO.setIssueStatusId(obj[5]!=null ? (Long)obj[5]:0l);
        				issueVO.setIssueStatus(obj[6]!= null ? obj[6].toString() :"");
        			   if(obj[7]!=null){
        					Date date = (Date)obj[7];
        					issueVO.setUpdatedTime(returnTime.format(date));
        				}
        				
        				returnList.add(issueVO);
        			}
        		}
    		//}
    		
		}catch(Exception e){
			LOG.error("Exception raised at getIssuesForATabUser", e);
		}
    	return returnList;
    }
    
public List<FieldMonitoringIssueVO> getIssuesCountsForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long loginUserId,Long stateId){
	List<FieldMonitoringIssueVO> returnList = new ArrayList<FieldMonitoringIssueVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId);
		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Long totalCount = 0l;
			
			Date startDate = null;
			Date endDate = null;
	    	if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
	    	}
	    	
	    	//Adding TotalCountVO
	    	FieldMonitoringIssueVO totalvo = new FieldMonitoringIssueVO();
	    	totalvo.setIssueStatus("Total");
	    	totalvo.setCount(0l);
	    	returnList.add(totalvo);
	    	
	    	List<Object[]> list = cadreRegIssueDAO.getIssuesCountsForATabUserByStatusNew(cadreSurveyUserId, tabUserInfoId, startDate, endDate, cadreRegUserId,stateId);
	    	if(list != null && !list.isEmpty()){
	    		for (Object[] obj : list) {
					FieldMonitoringIssueVO vo = new FieldMonitoringIssueVO();
					vo.setIssueStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setIssueStatus(obj[1] != null ? obj[1].toString():"");
					vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					totalCount = totalCount + Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					returnList.add(vo);
				}
	    	}
	    	
	    	if(returnList != null && returnList.size() > 0){
	    		for (FieldMonitoringIssueVO vo : returnList) {
					if(vo.getIssueStatus().equalsIgnoreCase("Total"))
						vo.setCount(totalCount);
				}
	    	}
		//}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return returnList;
}

    public List<FieldMonitoringIssueVO> getIssuesCountsForATabUserByStatus(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long vendorId,String locationType,Long locationVal){
    	List<FieldMonitoringIssueVO> returnList = new ArrayList<FieldMonitoringIssueVO>();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		Long totalCount = 0l;
    		
    		Date startDate = null;
    		Date endDate = null;
        	if(fromDateStr != null && toDateStr != null){
    			startDate = sdf.parse(fromDateStr);
    			endDate = sdf.parse(toDateStr);
        	}
        	
        	//Adding TotalCountVO
        	FieldMonitoringIssueVO totalvo = new FieldMonitoringIssueVO();
        	totalvo.setIssueStatus("Total");
        	totalvo.setCount(0l);
        	returnList.add(totalvo);
        	
        	List<Object[]> list = cadreRegIssueDAO.getIssuesCountsForATabUserByStatus(cadreSurveyUserId, tabUserInfoId, startDate, endDate, vendorId, locationType, locationVal);
        	if(list != null && !list.isEmpty()){
        		for (Object[] obj : list) {
					FieldMonitoringIssueVO vo = new FieldMonitoringIssueVO();
					vo.setIssueStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setIssueStatus(obj[1] != null ? obj[1].toString():"");
					vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					totalCount = totalCount + Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					returnList.add(vo);
				}
        	}
        	
        	if(returnList != null && returnList.size() > 0){
        		for (FieldMonitoringIssueVO vo : returnList) {
					if(vo.getIssueStatus().equalsIgnoreCase("Total"))
						vo.setCount(totalCount);
				}
        	}
        	
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return returnList;
    }
    
    /**
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  This service is used to update description and status to a particular issue.
	  *  @since 18-OCTOBER-2016
	  */
    public ResultStatus updateStatusToACadreRegIssue(final Long cadreRegIssueId,final String description,final Long newStatusId,final Long loginUserId){
    	
    	final ResultStatus rs = new ResultStatus();
    	final DateUtilService dateUtilService = new DateUtilService();
    	
    	try{
			
    		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId);
		        	Date currentDate = dateUtilService.getCurrentDateAndTime();
		        	
		        	CadreRegIssue cadreRegIssue = cadreRegIssueDAO.get(cadreRegIssueId);
		    		
		    		if(cadreRegIssue != null){
		    			
		    			//updation
		    			if(description != null && !description.trim().isEmpty()){
		    				cadreRegIssue.setDescription(description.trim());
		    			}
		    			
		    			if(newStatusId != null && newStatusId> 0l){
		    				cadreRegIssue.setCadreRegIssueStatusId(newStatusId);
		    			}
		    			cadreRegIssue.setUpdatedBy(cadreRegUserId);
		    			cadreRegIssue.setUpdatedTime(currentDate);
		    			
		    			cadreRegIssue = cadreRegIssueDAO.save(cadreRegIssue);
		    			
		    			
		    			//tracking
		    			
			        	CadreRegIssueTrack cadreRegIssueTrack = new CadreRegIssueTrack();
			        	cadreRegIssueTrack.setCadreRegIssueId(cadreRegIssue.getCadreRegIssueId());
			        	cadreRegIssueTrack.setCadreRegIssueTypeId(cadreRegIssue.getCadreRegIssueTypeId());
			        	cadreRegIssueTrack.setDescription(cadreRegIssue.getDescription());
			        	cadreRegIssueTrack.setCadreRegIssueStatusId(cadreRegIssue.getCadreRegIssueStatusId());
			        	cadreRegIssueTrack.setInsertedBy(cadreRegUserId);
			        	cadreRegIssueTrack.setInsertedTime(currentDate);
			        	cadreRegIssueTrackDAO.save(cadreRegIssueTrack);
		    			
		    		}
		    		
		    		 rs.setResultCode(1);
			         rs.setMessage("success");
		        }
    		});
    		
		}catch(Exception e){
			LOG.error("Exception raised at updateStatusToACadreRegIssue", e);
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
    	return rs;
    }
    
    /**
	  * 
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  Getting Track for a Particular issue. 
	  *  @since 18-OCTOBER-2016
	  */
    public List<FieldMonitoringIssueVO> trackingRegIssueByRegIssueId(Long cadreRegIssueId){
    	
    	List<FieldMonitoringIssueVO> finalList = null;
    	SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
    	try{
    		
    		List<Object[]> trackList = cadreRegIssueTrackDAO.trackingRegIssueByRegIssueId(cadreRegIssueId);
    		if(trackList != null && trackList.size() > 0)
    		{
    			finalList = new ArrayList<FieldMonitoringIssueVO>();
    			for(Object[] obj : trackList)
    			{
    				FieldMonitoringIssueVO VO = new FieldMonitoringIssueVO();
    				
    				VO.setIssueTypeId(obj[0] != null ? (Long)obj[0] : 0l);
    				VO.setIssueType(obj[1] != null ? obj[1].toString() : "");
    				VO.setDescription(obj[2] != null ? obj[2].toString() : "");
    				VO.setIssueStatusId(obj[3] != null ? (Long)obj[3] : 0l);
    				VO.setIssueStatus(obj[4] != null ? obj[4].toString() : "");
    				VO.setLoginUserId(obj[5] != null ? (Long)obj[5] : 0l);
    				VO.setFirstname(obj[6] != null ? obj[6].toString() : "");
    				VO.setLastname(obj[7] != null ? obj[7].toString() : "");
    				VO.setUserName(obj[9] != null ? obj[9].toString():"");
    				
    				if(obj[8]!=null){
    					Date date = (Date)obj[8];
    					VO.setDateStr(returnTime.format(date));
    				}
    				
    				finalList.add(VO);
    			}
    		}
    		
		}catch(Exception e){
			LOG.error("Exception raised at trackingRegIssueByRegIssueId", e);
		}
    	return finalList;
    }
    
    
    /**
	* @param  
	* @return  List<IdAndNameVO>
	* @author Hymavathi 
	* @Description : 
	*  @since 17-October-2016
	*/
   public List<IdAndNameVO> getAllIssueStatus(){
	   
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   List<CadreRegIssueStatus> statusList = cadreRegIssueStatusDAO.getAll();
		   
		   if(statusList != null && statusList.size() >0){
			 for(CadreRegIssueStatus model : statusList){
				 IdAndNameVO vo = new IdAndNameVO(); 
				 vo.setId(model.getCadreRegIssueStatusId() != null ? model.getCadreRegIssueStatusId().longValue() : 0l);
				 vo.setName(model.getStatus() != null ? model.getStatus().toString() : "");
				 vo.setIssueTypes(getAllIssueTypes());
				 vo.setInviteeCount(0l);
				 returnList.add(vo);
			 }
			   
		   }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getAllIssueStatus", e);
	   }
	   return returnList;
	   
   }
   
 public List<IdAndNameVO> getAllIssueStatusTemplate(List<CadreRegIssueStatus> statusList){
	   
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   
		   
		   if(statusList != null && statusList.size() >0){
			 for(CadreRegIssueStatus model : statusList){
				 IdAndNameVO vo = new IdAndNameVO(); 
				 vo.setId(model.getCadreRegIssueStatusId() != null ? model.getCadreRegIssueStatusId().longValue() : 0l);
				 vo.setName(model.getStatus() != null ? model.getStatus().toString() : "");
				 //vo.setIssueTypes(getAllIssueTypes());
				 vo.setInviteeCount(0l);
				 returnList.add(vo);
			 }
			   
		   }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getAllIssueStatusTemplate", e);
	   }
	   return returnList;
	   
   }
   
   /**
	* @param  
	* @return  List<IdAndNameVO>
	* @author Hymavathi 
	* @Description : 
	*  @since 17-October-2016
	*/
   public List<IdAndNameVO> getIssueStatusWiseCounts(String fromDateStr,String toDateStr,String task,Long stateId){
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			if(task.equalsIgnoreCase("dataMonitoringDashboard")){
				   Long activeUrsCount = cadreRegIssueDAO.getActiveUsersCount();
				   if(activeUrsCount != null && activeUrsCount.longValue() > 0l){
					   IdAndNameVO vo1 = new IdAndNameVO(); 
					   vo1.setName("DataMoniDashBrd");
					   vo1.setInviteeCount(activeUrsCount);
					   returnList.add(vo1);
				   }
				   
			   }
		   returnList.addAll(getAllIssueStatus());
		   List<Object[]> list = cadreRegIssueDAO.getIssueStatusWiseCounts(startDate,endDate,stateId);
		   if(list != null && list.size() >0){
				 for(Object[] obj : list){
					 IdAndNameVO vo = getMatchVO(returnList, (Long)obj[1]);	
					 if(vo != null){
						vo.setInviteeCount(obj[0] != null ? (Long)obj[0] : 0l) ;
					 }
					 
				   }
				 }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getIssueStatusWiseCounts", e);
	   }
	   return returnList;
   }
    
   /**
	* @param  
	* @return  List<IdAndNameVO>
	* @author Hymavathi 
	* @Description : 
	*  @since 17-October-2016
	*/
   public IdAndNameVO getMatchVO(List<IdAndNameVO> returnList, Long id) {
		if (returnList == null || returnList.size() == 0)
			return null;
		for (IdAndNameVO vo : returnList) {
			if (vo.getId() != null && vo.getId().longValue() == id) {
				return vo;
			}
		}
		return null;
	}
   
   /**
  	* @param  
  	* @return  List<IdAndNameVO>
  	* @author Hymavathi 
  	* @Description : 
  	*  @since 17-October-2016
  	*/
   public List<IdAndNameVO> getIssueTypeWiseCounts(String fromDateStr,String toDateStr,Long stateId) {
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		   Date startDate = null;
		   Date endDate = null;
		   if(fromDateStr != null && toDateStr != null){
			   startDate = sdf.parse(fromDateStr);
			   endDate = sdf.parse(toDateStr);
		   }
		   returnList.addAll(getAllIssueStatus());
		   
		   List<Object[]> list = cadreRegIssueDAO.getIssueTypeWiseCounts(startDate,endDate, stateId);
		   
		   if(list != null && list.size() >0){
			 for(Object[] obj : list){
				 Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				 Long typeId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
				 Long count = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				 IdAndNameVO statusVO = getMatchVO(returnList, statusId);
				 if(statusVO != null){
					 statusVO.setInviteeCount(statusVO.getInviteeCount()+count);
					 IdAndNameVO typeVO = getMatchVO(statusVO.getIssueTypes(), typeId);
					 if(typeVO != null){
						 typeVO.setInviteeCount(count);
					 }
				 }
				 
				 /*IdAndNameVO statusVO = getMatchVO(returnList, (Long)obj[1]);	
				 	if(statusVO != null){ 
				 		statusVO.setIssueTypes(getCadreRegIssueType());
					 	IdAndNameVO issueTypeVO = getMatchVO(statusVO.getIssueTypes(), (Long)obj[3]);
						 	if(issueTypeVO != null){ 
						 		issueTypeVO.setInviteeCount(obj[0] != null ? (Long)obj[0] : 0l);
						 	}
				 	}*/
				}
			 }
		   
		   if(returnList != null && !returnList.isEmpty()){
			   for (IdAndNameVO statusVO : returnList) {
				   Long totalCount = statusVO.getInviteeCount();
				   for (IdAndNameVO typeVO : statusVO.getIssueTypes()) {
					String percentage = "0.00";
					if(typeVO.getInviteeCount() > 0)
						percentage = (new BigDecimal((typeVO.getInviteeCount() * 100.0)/totalCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
					typeVO.setMobileNumber(percentage);
				}
			}
		   }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getStatusWiseIssueTypeCount", e);
	   }
	   return returnList;
	   
   }
   
   public List<IdAndNameVO> getAllIssueTypes(){
	   
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   List<CadreRegIssueType> typesList = cadreRegIssueTypeDAO.getAll();
		   
		   if(typesList != null && typesList.size() >0){
			 for(CadreRegIssueType model : typesList){
				 IdAndNameVO vo = new IdAndNameVO(); 
				 vo.setId(model.getCadreRegIssueTypeId());
				 vo.setName(model.getIssueType());
				 vo.setInviteeCount(0l);
				 returnList.add(vo);
			 }
			   
		   }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getAllIssueTypes", e);
	   }
	   return returnList;
	   
   }
   
public List<IdAndNameVO> getAllIssueTypesTemplate(List<CadreRegIssueType> typesList){
	   
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   
		   
		   if(typesList != null && typesList.size() >0){
			 for(CadreRegIssueType model : typesList){
				 IdAndNameVO vo = new IdAndNameVO(); 
				 vo.setId(model.getCadreRegIssueTypeId());
				 vo.setName(model.getIssueType());
				 vo.setInviteeCount(0l);
				 returnList.add(vo);
			 }
			   
		   }
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getAllIssueTypes", e);
	   }
	   return returnList;
	   
   }

   public List<IdAndNameVO> getConstituencyByVendor(Long fieldVendorId) {
		List<IdAndNameVO> constituencyByVendor = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> issueType = fieldVendorLocationDAO.getConstituencyByVendor(fieldVendorId);
			if (issueType != null && issueType.size() > 0) {
				for (Object[] objects : issueType) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					constituencyByVendor.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getConstituencyByVendor() in FieldMonitoringService class", e);
		}
		return constituencyByVendor;
	} 
    
   public FieldMonitoringVO getOverAllDataCollectorsDetails(String fromDateStr,String toDateStr,Long stateId){
	   FieldMonitoringVO returnVO = new FieldMonitoringVO();
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Date currentDate = dateUtilService.getCurrentDateAndTime();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			
			//Long totalCount = fieldVendorTabUserDAO.getTotalDataCollectorsCount(currentDate, currentDate,stateId);
			Long totalCount = fieldVendorTabUserDAO.getOverAllTotalDataCollectorsCount(stateId);
			Long activeCount = fieldVendorTabUserDAO.getActiveDataCollectorsCount(lastOneHourTime, today,stateId);
			Long passiveCount = fieldVendorTabUserDAO.getPassiveDataCollectorsCount(today, stateId);
			Long notYetStartedCount = 0l;
			if(totalCount == null)
				totalCount = 0l;
			if(activeCount == null)
				activeCount = 0l;
			if(passiveCount > 0l && passiveCount != null)
				passiveCount =passiveCount - activeCount ;
			else
				passiveCount = 0l;
			if(totalCount > 0l)
				notYetStartedCount = totalCount - activeCount -passiveCount;
			returnVO.setTotalDataCollectors(totalCount);
			returnVO.setActiveUsers(activeCount);
			returnVO.setPassiveUsers(passiveCount);
			returnVO.setNotYetStartedUsers(notYetStartedCount);
			
	} catch (Exception e) {
		LOG.error("Exception occurred at getOverAllDataCollectorsDetails() of FieldMonitoringService", e);
	}
	   return returnVO;
   }
   
   public List<FieldMonitoringVO> getStatusWiseIssuesDetails(String fromDateStr,String toDateStr,Long issueTypeId,Long statusTypeId,Long stateId){
	   List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		   SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
		   Date today = dateUtilService.getCurrentDateAndTime();
			Date startDate = null;
			Date endDate = null;
			//Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int presentHour = cal.get(Calendar.HOUR_OF_DAY);
			Long lastOneHour = Long.valueOf(presentHour-1);
			
			List<Long> cadreSurveyUserIds = new ArrayList<Long>();
			List<Object[]> list = fieldVendorTabUserDAO.getStatusWiseIssuesDetailsNew(issueTypeId, statusTypeId, startDate, endDate,stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					Long cadreSurveyUserId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setCadreSurveyUserId(cadreSurveyUserId);
					vo.setUserName(obj[1] != null ? obj[1].toString():"");
					vo.setTabUserId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
					vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
					vo.setStateId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setDistrictId(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
					if(vo.getDistrictId() > 10l)
						vo.setStateName("AP");
					else if(vo.getDistrictId() < 11l)
						vo.setStateName("TS");
					vo.setDistrictName(obj[7] != null ? obj[7].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
					vo.setConstituencyName(obj[9] != null ? obj[9].toString():"");
					vo.setFieldMonitrngName(obj[10] != null ? obj[10].toString():"");
					//vo.setVendorId(Long.valueOf(obj[10] != null ? obj[10].toString():"0"));
					//vo.setVendorName(obj[11] != null ? obj[11].toString():"");
					vo.setId(Long.valueOf(obj[11] != null ? obj[11].toString():"0"));//cadreRegIssueId
					cadreSurveyUserIds.add(cadreSurveyUserId);
					
					//String vendorName = fieldVendorTabUserDAO.getVendorNameByCadreSurveyUserId(cadreSurveyUserId);
					//vo.setVendorName(vendorName);
					returnList.add(vo);
				}
			}
			
			Map<Long,String> nameMap = new HashMap<Long, String>();
						
			List<Object[]> objList = fieldVendorTabUserDAO.getVendorNameByCadreSurveyUserId(cadreSurveyUserIds);
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {					
					nameMap.put((Long)obj[0], obj[1] !=null ? obj[1].toString():"");					
				}
			}
			
			//Vendor Names
			if(returnList !=null && returnList.size()>0){
				for (FieldMonitoringVO objects : returnList) {					
					String name = nameMap.get(objects.getCadreSurveyUserId());
					if(name !=null){
						objects.setVendorName(name);
					}
				}
			}
			List<Long> tabUserInfoIds = new ArrayList<Long>();
			List<Object[]> list3 = tabUserEnrollmentInfoDAO.getTabUserFirstLastRecordNew(null, null, null, null, stateId);
			if(list3 != null && !list3.isEmpty()){
				for (Object[] obj : list3) {
					
					Long cadreUserId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long tabUserId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					//FieldMonitoringVO vo = getMatchedVOByList(cadreUserId, tabUserId, returnList);
					FieldMonitoringVO vo = getMatchedFieldMonitrnVOById(cadreUserId, returnList);
					if(vo != null){
							vo.setTabUserId(tabUserId);
							vo.setTabUserName(obj[5] != null ? obj[5].toString():"");
							if(obj[0]!=null){
	    	    					Date date = (Date)obj[0];
	    	    					vo.setFirstRecord(returnTime.format(date));
	    	    				}
	    					 if(obj[1]!=null){
	    	    					Date date = (Date)obj[1];
	    	    					vo.setRecentRecord(returnTime.format(date));
	    	    				}
	    					vo.setTotalCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
	    					//Float totalPerc = (float) (vo.getTotalCount()*100.0/uptoTarget);
	    					//vo.setCountPerc(String.format("%.2f", totalPerc));
	    					
					}
					tabUserInfoIds.add(tabUserId);
				}
			}
			
			
			List<Object[]> list2 = tdpCadreUserHourRegInfoDAO.getTabUserLastOneHourData(lastOneHour,tabUserInfoIds);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long tabUserId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
					if(vo != null){
						vo.setLastHourCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
				}
			}
			
			//IssuesCounts
			List<Object[]> list1 = fieldVendorTabUserDAO.getUserWiseIssuesCounts(startDate, endDate,issueTypeId, statusTypeId);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long constitId = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					FieldMonitoringVO vo = getMatchdVOByList(userId, tabUserId, constitId, returnList);
					if(vo != null){
						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						if(statusId.longValue() == 1l)
							vo.setOpenIssues(count);
						else if(statusId.longValue() == 2l)
							vo.setFixedIssues(count);
						else if(statusId.longValue() == 3l)
							vo.setClosedIssues(count);
					}
				}
			}
			
			if(issueTypeId == 0l || issueTypeId == 1l){
				Map<Long,IdAndNameVO> leaderMap = new LinkedHashMap<Long, IdAndNameVO>();
				List<Object[]> cadreIssueList = cadreRegIssuePersonDAO.getCadreRegIssuePersonDetails(startDate, endDate);
					if(cadreIssueList != null && !cadreIssueList.isEmpty()){
						for (Object[] obj : cadreIssueList) {
							IdAndNameVO vo = new IdAndNameVO();
							Long id = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
							vo.setId(id);
							vo.setActualMobNumber(obj[2] != null ? obj[2].toString():"");
							vo.setName(obj[3] != null ? obj[3].toString():"");
							vo.setMobileNumber(obj[4] != null ? obj[4].toString():"");
							leaderMap.put(id, vo);
						}
					}
				
					if(returnList != null && !returnList.isEmpty()){
						for (FieldMonitoringVO vo : returnList) {
							Long id = vo.getId();
							IdAndNameVO leadervo = leaderMap.get(id);
							if(leadervo != null){
								vo.setLeaderMandal(leadervo.getActualMobNumber());
								vo.setLeaderName(leadervo.getName());
								vo.setLeadreMobile(leadervo.getMobileNumber());
							}
						}
					}
			}
			
	} catch (Exception e) {
		LOG.error("Exception occurred at getStatusWiseIssuesDetails() of FieldMonitoringService", e);
	}
	   return returnList;
   }
   
   public List<FieldMonitoringVO> getConstituencyIssueWiseOverAllDetails(String fromDateStr,String toDateStr,Long issueTypeId,Long statusTypeId,Long stateId){
	   List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
	   try {
		   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			//Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Object[]> list = fieldVendorTabUserDAO.getConstituencyIssueWiseOverAllDetails(issueTypeId, statusTypeId, startDate, endDate,stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					Long cadreSurveyUserId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setCadreSurveyUserId(cadreSurveyUserId);
					vo.setUserName(obj[1] != null ? obj[1].toString():"");
					vo.setTabUserId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
					vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
					vo.setStateId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setDistrictId(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
					if(vo.getDistrictId() > 10l)
						vo.setStateName("AP");
					else if(vo.getDistrictId() < 11l)
						vo.setStateName("TS");
					vo.setDistrictName(obj[7] != null ? obj[7].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
					vo.setConstituencyName(obj[9] != null ? obj[9].toString():"");
					vo.setFieldMonitrngName(obj[10] != null ? obj[10].toString():"");
					vo.setIssueType(obj[12] != null ? obj[12].toString():"");
					vo.setIssueStatus(obj[14] != null ? obj[14].toString():"");
					vo.setDescription(obj[15] != null ? obj[15].toString():"");
					vo.setIssueTime(obj[16] != null ? obj[16].toString():"");
					vo.setId(Long.valueOf(obj[17] != null ? obj[17].toString():"0"));//cadreRegIssueId
					
					returnList.add(vo);
				}
			}
			
			List<Object[]> list1 = fieldVendorTabUserDAO.getDistrictWiseIssuesCount(issueTypeId, statusTypeId, stateId, startDate, endDate);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long count = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long districtId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByDistrict(districtId, returnList);
					if(vo != null)
						vo.setDistrictCount(count);
				}
			}
			
			List<Object[]> list2 = fieldVendorTabUserDAO.getConstituencyWiseIssuesCount(issueTypeId, statusTypeId, stateId, startDate, endDate);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long count = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long constituencyId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long districtId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByConstituencyIdAndDistrict(constituencyId, districtId, returnList);
					if(vo != null)
						vo.setConstituencyCount(count);
				}
			}
			
			
		if(issueTypeId == 0l || issueTypeId == 1l){
			Map<Long,IdAndNameVO> leaderMap = new LinkedHashMap<Long, IdAndNameVO>();
			List<Object[]> cadreIssueList = cadreRegIssuePersonDAO.getCadreRegIssuePersonDetails(startDate, endDate);
				if(cadreIssueList != null && !cadreIssueList.isEmpty()){
					for (Object[] obj : cadreIssueList) {
						IdAndNameVO vo = new IdAndNameVO();
						Long id = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						vo.setId(id);
						vo.setActualMobNumber(obj[2] != null ? obj[2].toString():"");
						vo.setName(obj[3] != null ? obj[3].toString():"");
						vo.setMobileNumber(obj[4] != null ? obj[4].toString():"");
						leaderMap.put(id, vo);
					}
				}
			
				if(returnList != null && !returnList.isEmpty()){
					for (FieldMonitoringVO vo : returnList) {
						Long id = vo.getId();
						IdAndNameVO leadervo = leaderMap.get(id);
						if(leadervo != null){
							vo.setLeaderMandal(leadervo.getActualMobNumber());
							vo.setLeaderName(leadervo.getName());
							vo.setLeadreMobile(leadervo.getMobileNumber());
						}
					}
				}
		}	
	} catch (Exception e) {
		LOG.error("Exception occurred at getConstituencyIssueWiseOverAllDetails() of FieldMonitoringService", e);
	}
	   return returnList;
   }
   
   public FieldMonitoringVO getMatchedVOByConstituencyIdAndDistrict(Long constituencyId,Long districtId,List<FieldMonitoringVO> list){
   	FieldMonitoringVO returnvo = null;
   	try {
			if(list != null && !list.isEmpty()){
				for (FieldMonitoringVO vo : list) {
					if(vo.getDistrictId().longValue() == districtId.longValue()){
						if(vo.getConstituencyId().longValue() == constituencyId.longValue()){
							return vo;
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOByUserIdAndDistrict() of FieldMonitoringService", e);
		}
   	return returnvo;
   }
   
   public FieldMonitoringVO getMatchedVOByDistrict(Long districtId,List<FieldMonitoringVO> list){
	   	FieldMonitoringVO returnvo = null;
	   	try {
				if(list != null && !list.isEmpty()){
					for (FieldMonitoringVO vo : list) {
						if(vo.getDistrictId().longValue() == districtId.longValue()){
							return vo;
						}
					}
				}
				return null;
			} catch (Exception e) {
				LOG.error("Exception occurred at getMatchedVOByUserIdAndDistrict() of FieldMonitoringService", e);
			}
	   	return returnvo;
	   }
   
   public List<IdAndNameVO> getCadreRegIssueStatusType() {
		List<IdAndNameVO> regIssueStatusType = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> issueStatusType = cadreRegIssueStatusDAO.getCadreRegIssueStatusType();
			if (issueStatusType != null && issueStatusType.size() > 0) {
				for (Object[] objects : issueStatusType) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					regIssueStatusType.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getCadreRegIssueStatusType() in FieldMonitoringService class", e);
		}
		return regIssueStatusType;
	} 
   
   /**
  	* @param  
  	* @return  List<IdAndNameVO>
  	* @author Hymavathi 
  	* @Description : 
  	*  @since 20-October-2016
  	*/
   public List<IdAndNameVO> getDistrictWiseIssueTypesCount(String fromDateStr,String toDateStr,Long statusTypeId,Long stateId){
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   
	   try {
		   
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			//Date today = new Date();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> distWiseIssTyps = cadreRegIssueDAO.getDistrictWiseIssueTypesCount(startDate,endDate,statusTypeId,stateId);
				if (distWiseIssTyps != null && distWiseIssTyps.size() > 0) {
					setDistrictWiseIssueTypesCount(distWiseIssTyps,returnList);
				}
					
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getDistrictWiseIssueTypesCount() in FieldMonitoringService class", e);
		}
		return returnList;
	} 
   
   /**
  	* @param  
  	* @return  List<IdAndNameVO>
  	* @author Hymavathi 
  	* @Description : 
  	*  @since 20-October-2016
  	*/
   public void setDistrictWiseIssueTypesCount(List<Object[]> distWiseIssTyps,List<IdAndNameVO> returnList){
	   Map<List<Long>,IdAndNameVO> stateMap = new LinkedHashMap<List<Long>,IdAndNameVO>();
	   try {
			List<Long> apDist = districtDAO.getDistrictsInAState(1l);
			List<Long> tsDist = districtDAO.getDistrictsInAState(36l);
			IdAndNameVO apVO = new IdAndNameVO();
			apVO.setName("Andhra Pradesh");
			apVO.setDistList(setDistricts(1l));
			IdAndNameVO tsVO = new IdAndNameVO();
			tsVO.setName("Telangana");
			tsVO.setDistList(setDistricts(36l));
			stateMap.put(apDist, apVO);
			stateMap.put(tsDist, tsVO);
			
			Set<List<Long>> avaiableDistricts = new HashSet<List<Long>>(0);
			for (List<Long> distList : stateMap.keySet()) {
		   if (distWiseIssTyps != null && distWiseIssTyps.size() > 0) {
				for (Object[] objects : distWiseIssTyps) {
					
						if(distList.contains((Long)objects[4])){
							avaiableDistricts.add(distList);
							IdAndNameVO stateVO = stateMap.get(distList);
							
							if(stateVO != null){
								
								IdAndNameVO districtVO = getMatchVO(stateVO.getDistList(),(Long)objects[4]);
								if(districtVO != null){
									//districtVO.setIssueTypes(getAllIssueTypes());
									IdAndNameVO issueTypeVO = getMatchVO(districtVO.getIssueTypes(),(Long)objects[3]);
									if(issueTypeVO != null){
										issueTypeVO.setInviteeCount(objects[0] != null ?issueTypeVO.getInviteeCount() + (Long)objects[0] : 0l);
									}
								}
								
							}
						}
				}
		   	  }
			}
			for (List<Long> distList : avaiableDistricts) {
				IdAndNameVO stateVO = stateMap.get(distList);
				returnList.add(stateVO);
			}
		   
	   }catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in setDistrictWiseIssueTypesCount() in FieldMonitoringService class", e);
		}
   }
   
   /**
	* @param  
	* @return  List<IdAndNameVO>
	* @author Hymavathi 
	* @Description : 
	*  @since 20-October-2016
	*/
public List<IdAndNameVO> setDistricts(Long stateId){
	List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	try{
		List<Object[]> districts = districtDAO.getDistrictsWithNewSplitted(stateId); 
		
		if (districts != null && districts.size() > 0) {
			for (Object[] objects : districts) {
				IdAndNameVO vo = new IdAndNameVO();
				vo.setId(objects[0] != null ?(Long)objects[0] : 0l);
				vo.setName(objects[1] != null ?objects[1].toString() : "");
				vo.setIssueTypes(getAllIssueTypes());
				returnList.add(vo);	
			    }
			}
	}catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception raised in getCadreRegIssueStatusType() in FieldMonitoringService class", e);
	}
	return returnList;
}

public List<DataMonitoringVerificationVO> getLocationWiseDetailedOverViewDetails(String fromDateStr,String toDateStr,String locationType,Long locationVal){
	List<DataMonitoringVerificationVO> returnList = new ArrayList<DataMonitoringVerificationVO>();
	try {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;
		Date endDate = null;
		if(fromDateStr != null && toDateStr != null){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		
		List<Object[]> list = cadreRegIssueDAO.getLocationWiseDetailedOverViewDetails(startDate, endDate, locationType, locationVal);
		if(list != null && !list.isEmpty()){
			for (Object[] obj : list) {
				DataMonitoringVerificationVO vo = new DataMonitoringVerificationVO();
				
				vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setName(obj[1] != null ? obj[1].toString():"");
				if(locationType != null && locationType.equalsIgnoreCase("constituency")){
					if(vo.getId() == 0){
						vo.setId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						vo.setName(obj[3] != null ? obj[3].toString():"");
						vo.setName(vo.getName()+" Muncipality");
					}
					else
						vo.setName(vo.getName()+" Mandal");
				}
				vo.setCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
				
				returnList.add(vo);
			}
		}
		
		List<Object[]> list1 = cadreRegIssueDAO.getLocationWiseDataVerifiedCounts(startDate, endDate, locationType, locationVal);
		if(list1 != null && !list1.isEmpty()){
			for (Object[] obj : list1) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				if(locationType != null && locationType.equalsIgnoreCase("constituency"))
					if(id.longValue() == 0l)
						id = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				DataMonitoringVerificationVO vo = getMatchDataVerificationVO(returnList, id);
				if(vo != null)
					vo.setVerifiedCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
			}
		}
		
		if(returnList != null && !returnList.isEmpty()){
			for (DataMonitoringVerificationVO vo : returnList) {
				vo.setNotVerifiedCount(vo.getCount() - vo.getVerifiedCount());
			}
		}
		
		if(locationType != null && (locationType.equalsIgnoreCase("state") || locationType.equalsIgnoreCase("district"))){
			//IssuesCounts.
			List<Object[]> list2 = cadreRegIssueDAO.getLocationWiseIssuesCounts(startDate, endDate, locationType, locationVal);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					
					DataMonitoringVerificationVO vo = getMatchDataVerificationVO(returnList, id);
					if(vo != null){
						Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						if(statusId.longValue() == 1l)
							vo.setOpenIssues(count);
						else if(statusId.longValue() == 2l)
							vo.setFixedIssues(count);
						else if(statusId.longValue() == 3l)
							vo.setClosedIssues(count);
					}
				}
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getLocationWiseDetailedOverViewDetails() of FieldMonitoringService", e);
	}
	return returnList;
}

public DataMonitoringVerificationVO getMatchDataVerificationVO(List<DataMonitoringVerificationVO> returnList, Long id) {
	if (returnList == null || returnList.size() == 0)
		return null;
	for (DataMonitoringVerificationVO vo : returnList) {
		if (vo.getId().longValue() == id) {
			return vo;
		}
	}
	return null;
}

public List<CadreRegUserVO> getCadreRegUserAssignedDistricts(Long userId,String userType){
	List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(userId);
		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedDistricts(cadreRegUserId);
			if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
				list = cadreRegUserTabUserDAO.getAllAssignedDistricts("FM");
			}
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					CadreRegUserVO vo = new CadreRegUserVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		//}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getCadreRegUserAssignedDistricts() of FieldMonitoringService", e);
	}
	return returnList;
}

public List<CadreRegUserVO> getCadreRegUserAssignedConstituencies(Long userId,String userType,Long districtId){
	List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(userId);
		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedConstituencies(cadreRegUserId,districtId);
			if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
				list = cadreRegUserTabUserDAO.getAllAssignedConstituencies(districtId,"FM");
			}
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					CadreRegUserVO vo = new CadreRegUserVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		//}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
	}
	return returnList;
}

public List<CadreRegUserVO> getCadreRegUserAssignedUsers(Long userId,Long constituencyId,String userType){
	List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(userId);
		//if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedUsers(cadreRegUserId,constituencyId);
			if((list == null || list.isEmpty()) && userType.equalsIgnoreCase("dashboard")){
				list = cadreRegUserTabUserDAO.getAllUserAssignedUsers(constituencyId,"FM");
			}
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					CadreRegUserVO vo = new CadreRegUserVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		//}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getCadreRegUserAssignedUsers() of FieldMonitoringService", e);
	}
	return returnList;
}

public List<UserPerformanceVO> getUserPerformanceDetailsByUser(Long cadreSurveyUserId,Long tabUserId){
	List<UserPerformanceVO> returnList = new ArrayList<UserPerformanceVO>();
	try {
		Map<String,UserPerformanceVO> dayWiseMap = new LinkedHashMap<String, UserPerformanceVO>();
		
		Date today = dateUtilService.getCurrentDateAndTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -2);
		
		Date dayBfrYes =  cal.getTime();
		int presentHour = cal.get(Calendar.HOUR_OF_DAY);
		
		List<Object[]> list = tdpCadreEnrollmentYearDAO.getHourWiseUserPerformanceInfo(cadreSurveyUserId, tabUserId, dayBfrYes, today);
		if(list != null && !list.isEmpty()){
			for (Object[] obj : list) {
				String dateStr = obj[0] != null ? obj[0].toString().trim():"";
				Long hourId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
				Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				
				UserPerformanceVO vo = dayWiseMap.get(dateStr);
				if(vo == null){
					vo = new UserPerformanceVO();
					vo.setName(dateStr);
					vo.setId(new Long(presentHour));
					if(hourId <= presentHour){
						//vo.setId(vo.getId()+1l);
						vo.setUptoNowCount(vo.getUptoNowCount()+count);
					}
						if(hourId == 8l){
							vo.setFrom8to9Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 9l){
							vo.setFrom9to10Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 10l){
							vo.setFrom10to11Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 11l){
							vo.setFrom11to12Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 12l){
							vo.setFrom12to1Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 13l){
							vo.setFrom1to2Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 14l){
							vo.setFrom2to3Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 15l){
							vo.setFrom3to4Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 16l){
							vo.setFrom4to5Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 17l){
							vo.setFrom5to6Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 18l){
							vo.setFrom6to7Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 19l){
							vo.setFrom7to8Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else{
							vo.setFrom8pmto8amCount(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}
					/*}
					else{
						if(hourId == 8l){
							vo.setFrom8to9Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 9l){
							vo.setFrom9to10Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 10l){
							vo.setFrom10to11Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 11l){
							vo.setFrom11to12Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 12l){
							vo.setFrom12to1Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 13l){
							vo.setFrom1to2Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 14l){
							vo.setFrom2to3Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 15l){
							vo.setFrom3to4Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 16l){
							vo.setFrom4to5Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 17l){
							vo.setFrom5to6Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 18l){
							vo.setFrom6to7Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 19l){
							vo.setFrom7to8Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else{
							vo.setFrom8pmto8amCount(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}
					}*/
					
					dayWiseMap.put(dateStr, vo);
				}
				else{
					if(hourId <= presentHour){
						//vo.setId(vo.getId()+1l);
						vo.setUptoNowCount(vo.getUptoNowCount()+count);
					}
						if(hourId == 8l){
							vo.setFrom8to9Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 9l){
							vo.setFrom9to10Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 10l){
							vo.setFrom10to11Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 11l){
							vo.setFrom11to12Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 12l){
							vo.setFrom12to1Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 13l){
							vo.setFrom1to2Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 14l){
							vo.setFrom2to3Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 15l){
							vo.setFrom3to4Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 16l){
							vo.setFrom4to5Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 17l){
							vo.setFrom5to6Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 18l){
							vo.setFrom6to7Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 19l){
							vo.setFrom7to8Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else{
							vo.setFrom8pmto8amCount(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}
					/*}
					else{
						if(hourId == 8l){
							vo.setFrom8to9Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 9l){
							vo.setFrom9to10Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 10l){
							vo.setFrom10to11Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 11l){
							vo.setFrom11to12Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 12l){
							vo.setFrom12to1Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 13l){
							vo.setFrom1to2Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 14l){
							vo.setFrom2to3Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 15l){
							vo.setFrom3to4Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 16l){
							vo.setFrom4to5Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 17l){
							vo.setFrom5to6Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 18l){
							vo.setFrom6to7Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else if(hourId == 19l){
							vo.setFrom7to8Count(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}else{
							vo.setFrom8pmto8amCount(count);
							vo.setOverAllCount(vo.getOverAllCount()+count);
						}
					}*/
				}
			}
		}
		
		if(dayWiseMap != null)
			returnList = new ArrayList<UserPerformanceVO>(dayWiseMap.values());
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getUserPerformanceDetailsByUser() of FieldMonitoringService", e);
	}
	return returnList;
}


public FieldMonitoringVO getAllDataCollectorsDetails(Long loginUserId,Long stateId,Long districtId,Long constituencyId,Long userId, String fromDateStr,String toDateStr){
	FieldMonitoringVO returnVO = new FieldMonitoringVO();
	try{
		Long cadreRegUserId=cadreRegUserDAO.getCadreRegId(loginUserId);
		Long usersCount = fieldVendorTabUserDAO.getAssignedUsersCountForRegUser(cadreRegUserId);
		if(usersCount == null || usersCount.longValue() == 0l){
			cadreRegUserId = null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;	
		Date endDate = null;
		Date today = dateUtilService.getCurrentDateAndTime();
		if(fromDateStr != null && toDateStr != null){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		//Date currentDate = dateUtilService.getCurrentDateAndTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
		Date lastOneHourTime = cal.getTime();
		
		Long totalDataCollectorsCount = fieldVendorTabUserDAO.getTotalDataCollectorsCountFrMnrtg(stateId, districtId, constituencyId, cadreRegUserId, userId);
		Long registeredCount = fieldVendorTabUserDAO.getTotalRegisteredCount(stateId, districtId, constituencyId, cadreRegUserId, userId);
	    Long todayActMbersCount = fieldVendorTabUserDAO.getTodayActiveMbrsCount(stateId, districtId, constituencyId, cadreRegUserId, userId, today);
	    Long oneHrActMbersCount = fieldVendorTabUserDAO.getOneHourActiveUsersCount(stateId, districtId, constituencyId, cadreRegUserId, userId, lastOneHourTime, today);
	    Long passiveCount = 0l;
	    Long notYetStarted = 0l;
	    
	    if(totalDataCollectorsCount == null)
	    	totalDataCollectorsCount = 0l;
	    if(registeredCount == null)
	    	registeredCount = 0l;
	    if(todayActMbersCount == null)
	    	todayActMbersCount = 0l;
	    if(oneHrActMbersCount == null)
	    	oneHrActMbersCount = 0l;
	    passiveCount = todayActMbersCount - oneHrActMbersCount;
	    notYetStarted = registeredCount - todayActMbersCount;
	   
	    
	    returnVO.setTotalDataCollectors(totalDataCollectorsCount);
	    returnVO.setTodayRegCount(registeredCount);
	    returnVO.setTodayActiveUsers(todayActMbersCount);
	    returnVO.setLastOneHrActUsers(oneHrActMbersCount);
	    returnVO.setPassiveUsers(passiveCount);
	    returnVO.setNotYetStartedUsers(notYetStarted);
	    
	    
}catch(Exception e){
	LOG.error("Exception occurred at getAllDataCollectorsDetails() of FieldMonitoringService", e);
}
return returnVO;
}		
public List<IdAndNameVO> getDistrictByStateId(Long stateId, Long stateTypeId){
	List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	try{
		List<Object[]> districts = districtDAO.getDistrictIdAndNameByStateForStateTypeId(stateId,stateTypeId);
	
		returnList = setDataToIdNameVOList(districts);

	} catch (Exception e) {
		LOG.error("Exception occurred at getDistrictByStateId() of FieldMonitoringService", e);
	}
	return returnList;
}

public FieldMonitoringVO getMatchedFieldMonitrnVOById(Long cadreSurveyUserId,List<FieldMonitoringVO> list){
	FieldMonitoringVO returnvo = null;
	try {
		if(list != null && !list.isEmpty()){
			for (FieldMonitoringVO vo : list) {
				if(vo.getCadreSurveyUserId().longValue() == cadreSurveyUserId.longValue()){
					return vo;
				}
			}
		}
		return null;
	} catch (Exception e) {
		LOG.error("Exception occurred at getMatchedFieldMonitrnVOById() of FieldMonitoringService", e);
	}
	return returnvo;
}

/**
* @param  Long loginUserId  
* @param  Long districtId
* @param Long stateId
* @return FieldMonitoringVO
* @author Hymavathi G 
* @Description :This method for showing DataCollectors Performance Details 
*  @since 02-Nov-2016
*/
public FieldMonitoringVO getDataCollectorsPerformanceDetails(Long loginUserId,Long districtId,Long stateId,Long constituencyId,Long cadreSurveyUserId,String fromDateStr,String toDateStr){
	FieldMonitoringVO returnVO = new FieldMonitoringVO();
	
	try {
			List<Long> tabUserInfoIds = new ArrayList<Long>();
			List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
			Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId);
    		Long usersCount = cadreRegUserTabUserDAO.getAssignedUsersCountForRegUser(cadreRegUserId);
    		if(usersCount == null || usersCount == 0l)
    			cadreRegUserId = null;
		
			SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			   Date startDate = null;
			   Date endDate = null;
			   if(fromDateStr != null && toDateStr != null){
				   startDate = sdf.parse(fromDateStr);
				   endDate = sdf.parse(toDateStr);
			   }
			//Date startDate = dateUtilService.getCurrentDateAndTime();
			//Date endDate = dateUtilService.getCurrentDateAndTime();
			Date today = dateUtilService.getCurrentDateAndTime();
			Long todayTarget = 200l;
			Long totalWorkingHours = 10l;
			Long eachHourTarget = todayTarget/totalWorkingHours;
			
			List<Object[]> overAllTempList = cadreRegIssueDAO.getTotalCadreSurveyUsersTemplate(cadreRegUserId, constituencyId, cadreSurveyUserId, districtId, stateId);
			if(overAllTempList != null && !overAllTempList.isEmpty()){
				for (Object[] obj : overAllTempList) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					vo.setCadreSurveyUserId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setUserName(obj[1] != null ? obj[1].toString():"");
					vo.setFieldMonitrngName(obj[2] != null ? obj[2].toString():"");
					vo.setDistrictId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
					vo.setDistrictName(obj[4] != null ? obj[4].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setConstituencyName(obj[6] != null ? obj[6].toString():"");
					vo.setTabUserId(0l);
					returnList.add(vo);
				}
			}
			
			
			
			List<Object[]> templist = cadreRegIssueDAO.getTotalTabUsersDetailsByVendorAndLocationNew(cadreRegUserId, null, null, constituencyId, cadreSurveyUserId, districtId,stateId);
			if(templist != null && !templist.isEmpty()){
				for (Object[] obj : templist) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					//Long tabUserId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					FieldMonitoringVO vo = getMatchedFieldMonitrnVOById(userId, returnList);
					if(vo != null){
						if(vo.getTabUserId() == 0l){
							vo.setCadreSurveyUserId(userId);
							vo.setUserName(obj[1] != null ? obj[1].toString():"");
							vo.setTabUserId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
							vo.setTabUserName(obj[3] != null ? obj[3].toString():"");
							vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
							vo.setDistrictId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
							vo.setDistrictName(obj[6] != null ? obj[6].toString():"");
							vo.setConstituencyId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
							vo.setConstituencyName(obj[8] != null ? obj[8].toString():"");
							vo.setImagePath(obj[9] != null ? obj[9].toString():"");
							vo.setTodayTarget(todayTarget.toString());
							vo.setFieldMonitrngName(obj[11] != null ? obj[11].toString():"");
							tabUserInfoIds.add(vo.getTabUserId());
							//returnList.add(vo);
						}
					}
				}
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int presentHour = cal.get(Calendar.HOUR_OF_DAY);
			Long lastOneHour = Long.valueOf(presentHour-1);
			Long workingHrs = 0l;
			if(presentHour > 8){
				workingHrs = Long.valueOf(presentHour - 8);
			}
			Long  uptoTarget = eachHourTarget*workingHrs;
			
			//List<Object[]> list = tabUserEnrollmentInfoDAO.getTabUserFirstLastRecord(tabUserInfoIds);
			List<Object[]> list = tabUserEnrollmentInfoDAO.getTabUserFirstLastRecordNew(cadreRegUserId, constituencyId, cadreSurveyUserId, districtId, stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					
					Long cadreUserId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long tabUserId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					//FieldMonitoringVO vo = getMatchedVOByList(cadreUserId, tabUserId, returnList);
					FieldMonitoringVO vo = getMatchedFieldMonitrnVOById(cadreUserId, returnList);
					if(vo != null){
							vo.setTabUserId(tabUserId);
							vo.setTabUserName(obj[5] != null ? obj[5].toString():"");
							vo.setMobileNo(obj[6] != null ? obj[6].toString():"");
							if(obj[0]!=null){
	    	    					Date date = (Date)obj[0];
	    	    					vo.setFirstRecord(returnTime.format(date));
	    	    				}
	    					 if(obj[1]!=null){
	    	    					Date date = (Date)obj[1];
	    	    					vo.setRecentRecord(returnTime.format(date));
	    	    				}
	    					vo.setTotalCount(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
	    					Float totalPerc = (float) (vo.getTotalCount()*100.0/uptoTarget);
	    					vo.setCountPerc(String.format("%.2f", totalPerc));
	    					
					}
					if(!tabUserInfoIds.contains(tabUserId))
						tabUserInfoIds.add(tabUserId);
				}
			}
			
			List<Object[]> perfomanceList = cadreSurveyUserPerformanceDAO.getCadreSurveyUserId(today);
			if(perfomanceList != null && !perfomanceList.isEmpty()){
				for (Object[] obj : perfomanceList) {
					Long cadreSurUserId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long typeId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					FieldMonitoringVO vo = getMatchedFieldMonitrnVOById(cadreSurUserId, returnList);
					if(vo != null){
						if(typeId != null && typeId.longValue() == 1l)
							vo.setSlowPerformer("true");
						else if(typeId != null && typeId.longValue() == 2l)
							vo.setBetterPerformer("true");
					}
				}
			}
			/*if(returnList != null && !returnList.isEmpty()){
				for (FieldMonitoringVO vo : returnList) {
					Long existcadreId= vo.getCadreSurveyUserId();
					if(cadreIds.contains(existcadreId)){
						vo.setDescription("true");
					}
				}
			}*/
			
			List<Object[]> list1 = tdpCadreUserHourRegInfoDAO.getTabUserLastOneHourData(lastOneHour,tabUserInfoIds);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long tabUserId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
					if(vo != null){
						vo.setLastHourCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
				}
			}
			
			List<Object[]> list2 = cadreRegIssueDAO.getcadreRegIssuesCountsNew(cadreRegUserId, constituencyId, cadreSurveyUserId, startDate, endDate, districtId,stateId);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long userId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long tabUserId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByList(userId, tabUserId, returnList);
					if(vo != null){
						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						if(statusId.longValue() == 1l)
							vo.setOpenIssues(count);
						else if(statusId.longValue() == 2l)
							vo.setFixedIssues(count);
						else if(statusId.longValue() == 3l)
							vo.setClosedIssues(count);
					}
				}
			}
			
			if(returnList != null && returnList.size() > 0){
				  Collections.sort(returnList, tabUserInfoTotalRegisCountAsc);
			 }
			if(returnList != null && returnList.size() > 0){
			returnVO.setSubList(returnList);
			}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getDataCollectorsPerformanceDetails() of FieldMonitoringService", e);
	}
	return returnVO;
}
public static Comparator<FieldMonitoringVO> tabUserInfoTotalRegisCountAsc = new Comparator<FieldMonitoringVO>() {
	public int compare(FieldMonitoringVO tabUserInfo2, FieldMonitoringVO tabUserInfo1) {
	Long count2 = tabUserInfo2.getTotalCount();
	Long count1 = tabUserInfo1.getTotalCount();
	//Descending order of completed registrations.
		return count1.compareTo(count2); 
	}
}; 
	
	public List<FieldMonitoringVO> getFieldMonitoringUserWiseDetails(GISVisualizationParameterVO inputVO){
		List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
		try {
			List<CadreRegIssueType> typesList = cadreRegIssueTypeDAO.getAll();
			List<CadreRegIssueStatus> statusList = cadreRegIssueStatusDAO.getAll();
			
			List<Object[]> list = cadreRegUserTabUserDAO.getFieldMonitoringUserWiseDetails(inputVO);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setDistrictId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setDistrictName(obj[3] != null ? obj[3].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
					vo.setConstituencyName(obj[5] != null ? obj[5].toString():"");
					vo.setIdnameList(getAllIssueTypesTemplate(typesList));
					vo.setIdnameList1(getAllIssueStatusTemplate(statusList));
					vo.setTotalDataCollectors(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
					
					returnList.add(vo);
				}
			}
			
			List<Object[]> registeredList = cadreRegUserTabUserDAO.getTotalRegisteredUsers(inputVO);
			if(registeredList != null && !registeredList.isEmpty()){
				for (Object[] obj : registeredList) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(userId, constId, returnList);
					if(vo != null)
						vo.setTodayRegCount(count);							// Total Registered.
				}
			}
			
			List<Object[]> totalStarted = cadreRegUserTabUserDAO.getTodayStartedUsersOfFMUser(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(totalStarted != null && !totalStarted.isEmpty()){
				for (Object[] obj : totalStarted) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(userId, constId, returnList);
					if(vo != null){
						Long notStartedCnt = vo.getTotalDataCollectors()-count;
						vo.setNotYetStartedUsers(notStartedCnt);//Not Started Users Of FM User
						vo.setStartedUsers(count);
					}
				}
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateUtilService.getCurrentDateAndTime());
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			List<Object[]> lastHourUsers = cadreRegUserTabUserDAO.getLastOneHourUsersOfFMUser(lastOneHourTime, dateUtilService.getCurrentDateAndTime(),inputVO);
			if(lastHourUsers != null && !lastHourUsers.isEmpty()){
				for (Object[] obj : lastHourUsers) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(userId, constId, returnList);
					if(vo != null){
						vo.setLastOneHrActUsers(count);
					}
				}
			}
			
			if(returnList != null && !returnList.isEmpty()){
				for (FieldMonitoringVO vo : returnList) {
					vo.setLastHourInActive(vo.getTotalDataCollectors() - vo.getLastOneHrActUsers());
				}
			}
			
			List<Object[]> totalIssues = cadreRegUserTabUserDAO.getTodayTotalIssues(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(totalIssues != null && !totalIssues.isEmpty()){
				for (Object[] obj : totalIssues) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(userId, constId, returnList);
					if(vo != null){
						vo.setOpenIssues(count);//Today Total Open Issues
					}
				}
			}
			
			List<Object[]> todayTotalStartedIssues = cadreRegUserTabUserDAO.getTodayTotalStartedIssues(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(todayTotalStartedIssues != null && !todayTotalStartedIssues.isEmpty()){
				for (Object[] obj : todayTotalStartedIssues) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long userId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(userId, constId, returnList);
					if(vo != null){
						Long notStartedIssuesCnt = vo.getOpenIssues()-count;
						vo.setNotYetStartedIssues(notStartedIssuesCnt);//Today Total Not Started Issues
						vo.setStartedIssues(count);//Today Total Started Issues
					}
				}
			}
			
			List<Object[]> list1 = cadreRegUserTabUserDAO.getIssueTypeWiseCountsForFieldMonrUsers(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					//Long districtId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long typeId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Long constId = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(id, constId, returnList);
					if(vo != null){
						List<IdAndNameVO> typsList = vo.getIdnameList();
						IdAndNameVO typeVO = getMatchedVOById(typeId, typsList);
						if(typeVO != null)
							typeVO.setAttenteeCount(count);  //totalOpen Issues
					}
				}
			}
			
			List<Object[]> startedUsersIssues = cadreRegUserTabUserDAO.getStartedUsersIssueTypeWiseCountsForFieldMonrUsers(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(startedUsersIssues != null && !startedUsersIssues.isEmpty()){
				for (Object[] obj : startedUsersIssues) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					//Long districtId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long typeId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Long constId = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(id, constId, returnList);
					if(vo != null){
						List<IdAndNameVO> typsList = vo.getIdnameList();
						IdAndNameVO typeVO = getMatchedVOById(typeId, typsList);
						if(typeVO != null){
							typeVO.setInviteeAttendeeCnt(count);  //StartedUsers Issues
							//typeVO.setInviteeCount(typeVO.getAttenteeCount() - count);  //NotStartedUsers Issues
						}
					}
				}
			}
			
			if(returnList != null && !returnList.isEmpty()){
				for (FieldMonitoringVO vo : returnList) {
					List<IdAndNameVO> voList = vo.getIdnameList();
					if(voList != null && !voList.isEmpty()){
						for (IdAndNameVO vo1 : voList) {
							vo1.setInviteeCount(vo1.getAttenteeCount() - vo1.getInviteeAttendeeCnt());
						}
					}
				}
			}
			
			List<Object[]> list2 = cadreRegUserTabUserDAO.getIssueStatusWiseCountsForFieldMonrUsers(dateUtilService.getCurrentDateAndTime(),inputVO);
			if(list2 != null && !list2.isEmpty()){
				for (Object[] obj : list2) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long districtId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Long constId = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOByUserIdAndConstituency(id, constId, returnList);
					if(vo != null){
						List<IdAndNameVO> statsList = vo.getIdnameList1();
						IdAndNameVO stutsVO = getMatchedVOById(statusId, statsList);
						if(stutsVO != null)
							stutsVO.setAttenteeCount(count);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occurred at getFieldMonitoringUserWiseDetails() of FieldMonitoringService", e);
		}
		return returnList;
	}
	
	public FieldMonitoringVO getMatchedVOByUserIdAndConstituency(Long userId,Long constituencyId,List<FieldMonitoringVO> list){
    	FieldMonitoringVO returnvo = null;
    	try {
			if(list != null && !list.isEmpty()){
				for (FieldMonitoringVO vo : list) {
					if(vo.getId().longValue() == userId.longValue()){
						if(vo.getConstituencyId().longValue() == constituencyId.longValue()){
							return vo;
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOByUserIdAndConstituency() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
	
	public IdAndNameVO getMatchedVOById(Long id,List<IdAndNameVO> list){
		IdAndNameVO returnvo = null;
    	try {
			if(list != null && !list.isEmpty()){
				for (IdAndNameVO vo : list) {
					if(vo.getId().longValue() == id.longValue()){
						return vo;
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOById() of FieldMonitoringService", e);
		}
    	return returnvo;
    }

	/**
	* @param  Long loginUserId  
	* @param  Long districtId
	* @param Long stateId
	* @return FieldMonitoringVO
	* @author Hymavathi G 
	* @Description : 
	*  @since 05-Nov-2016
	*/
	public List<FieldMonitoringVO> getDataCollectorsConstituencyWiseCount(){
		List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>();
		
		List<Long> cadreSurveyuserIds = new ArrayList<Long>();
		List<Long> tabUserInfoIds = new ArrayList<Long>();
		Map<Long,List<Long>> fmUserWiseTotReg = new HashMap<Long,List<Long>>();
		try {
			
			List<Object[]> fmUsersList = cadreRegUserTabUserDAO.getConstituencyWiseFMUsersDetails();
			if(fmUsersList != null && !fmUsersList.isEmpty()){
				for (Object[] obj : fmUsersList) {
					FieldMonitoringVO vo = new FieldMonitoringVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setDistrictId(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					vo.setDistrictName(obj[3] != null ? obj[3].toString():"");
					vo.setConstituencyId(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
					vo.setConstituencyName(obj[5] != null ? obj[5].toString():"");
					
					returnList.add(vo);
				}
			}
			
			List<Object[]> usersForFMUser = cadreRegUserTabUserDAO.getTotalUsersCountForFMUsers();
			if(usersForFMUser != null && !usersForFMUser.isEmpty()){
				
				for (Object[] obj : usersForFMUser) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOConstituencyId(constId,returnList);
					if(vo != null){
						vo.setTotalCount(count);// total Users Of FM
					}
				}
			}
			
			List<Object[]> totalRegForFM = cadreRegUserTabUserDAO.getTotalRegisteredUsers(null);
			if(totalRegForFM != null && !totalRegForFM.isEmpty()){
				for (Object[] obj : totalRegForFM) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOConstituencyId(constId,returnList);
					if(vo != null){
						vo.setTodayRegCount(count);//total Registered users Of FM
					}
				}
			}
			
			List<Object[]> totalStarted = cadreRegUserTabUserDAO.getTodayStartedUsersOfFMUser(dateUtilService.getCurrentDateAndTime(),null);
			if(totalStarted != null && !totalStarted.isEmpty()){
				for (Object[] obj : totalStarted) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOConstituencyId(constId,returnList);
					if(vo != null){
						Long notStartedCnt = vo.getTodayRegCount()-count;
						vo.setNotYetStartedUsers(notStartedCnt);//Not Started Users Of FM User
					}
				}
			}
			
			List<Object[]> totalIssues = cadreRegUserTabUserDAO.getTodayTotalIssues(dateUtilService.getCurrentDateAndTime(),null);
			if(totalIssues != null && !totalIssues.isEmpty()){
				for (Object[] obj : totalIssues) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOConstituencyId(constId,returnList);
					if(vo != null){
						vo.setOpenIssues(count);//Today Total Open Issues
					}
				}
			}
			
			List<Object[]> todayTotalStartedIssues = cadreRegUserTabUserDAO.getTodayTotalStartedIssues(dateUtilService.getCurrentDateAndTime(),null);
			if(todayTotalStartedIssues != null && !todayTotalStartedIssues.isEmpty()){
				for (Object[] obj : todayTotalStartedIssues) {
					Long constId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					
					FieldMonitoringVO vo = getMatchedVOConstituencyId(constId,returnList);
					if(vo != null){
						Long notStartedIssuesCnt = vo.getOpenIssues()-count;
						vo.setNotYetStartedIssues(notStartedIssuesCnt);//Today Total Not Started Issues
						vo.setStartedIssues(count);//Today Total Started Issues
					}
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDataCollectorsConstituencyWiseCountAction() of FieldMonitoringService", e);
		}
		return returnList;
	}
	
	public FieldMonitoringVO getMatchedVOConstituencyId(Long constituencyId,List<FieldMonitoringVO> list){
    	FieldMonitoringVO returnvo = null;
    	try {
			if(list != null && !list.isEmpty()){
				for (FieldMonitoringVO vo : list) {
					if(vo.getConstituencyId().longValue() == constituencyId.longValue()){
						return vo;
					}
				}
			}
			return null;
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOConstituencyId() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
	
	public List<IdAndNameVO> getConstituencyWiseTodayAndOverAllCounts(String type,Long stateId,String sortType){
		 Map<Long,Long> targetMap = new LinkedHashMap<Long, Long>();
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = tdpCadreLocationInfoDAO.getConstituencyWiseTodayAndOverAllCounts(type, stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setAttenteeCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					returnList.add(vo);
				}
			}
			List<Object[]> cnstincyTrgetList = tdpCadreTargetCountDAO.getConstitencysTargetCountForTodayAndOverAll(stateId);
			if(cnstincyTrgetList != null && !cnstincyTrgetList.isEmpty()){
				for (Object[] obj : cnstincyTrgetList) {
					Long locaId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					targetMap.put(locaId, count);
				}
			}
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO vo : returnList) {
					vo.setInviteeCount(targetMap.get(vo.getId()));//TargetCoount
					
				}
			}
			if(type.trim().equalsIgnoreCase("Today")){
				if(returnList != null && !returnList.isEmpty()){
					for (IdAndNameVO vo : returnList) {
						vo.setInviteeCount(vo.getInviteeCount()/IConstants.CADRE_REGISTRATION_2016_DAYS);//TargetCoount
						
					}
				}
			}
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO vo : returnList) {
					vo.setPer2016(Double.valueOf(new BigDecimal(vo.getAttenteeCount()*(100.0)/vo.getInviteeCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}
			}
			
			if(sortType != null && sortType.equalsIgnoreCase("percentage")){
				if(returnList != null && !returnList.isEmpty()){
					 Collections.sort(returnList, registrationsPercCountAsc);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getConstituencyWiseTodayAndOverAllCounts() of FieldMonitoringService", e);
		}
		return returnList;
	}
	
	public List<IdAndNameVO> getConstituenciesByStateForStateTypeId(Long stateId,Long stateTypeId,Long districtId){
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try{
			List<Object[]> constitList = constituencyDAO.getConstituenciesByStateForStateTypeId(stateId, stateTypeId, districtId);
		
			returnList = setDataToIdNameVOList(constitList);

		} catch (Exception e) {
			LOG.error("Exception occurred at getConstituenciesByStateForStateTypeId() of FieldMonitoringService", e);
		}
		return returnList;
	}
	
	 public ResultStatus saveCaderSurveyUserPerformanceDetails(Long loginUserId,Long cadreSurveyUserId,Long performanceTypeId,String comment){
		 ResultStatus status = new ResultStatus();
		 try{
			 Date currentDate = dateUtilService.getCurrentDateAndTime();
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 /*List<CadreSurveyUserPerformance> existingUserPerformance = cadreSurveyUserPerformanceDAO.getCadreSurveyUserPerformanceDetails(cadreSurveyUserId,currentDate);
			 if(existingUserPerformance != null && existingUserPerformance.size() > 0){
				 	CadreSurveyUserPerformance cadreSurveyUserPerformance = existingUserPerformance.get(0);
				 	//cadreSurveyUserPerformance.setCadreSurveyUserPerformanceTypeId(performanceTypeId);
				 	//cadreSurveyUserPerformance.setUpdatedBy(loginUserId);
				 	//cadreSurveyUserPerformance.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				 	cadreSurveyUserPerformance.setIsDeleted("true");
				 	cadreSurveyUserPerformanceDAO.save(cadreSurveyUserPerformance);
			 	}*/
			 		CadreSurveyUserPerformance cadreSurveyUserPerformance = new CadreSurveyUserPerformance();
			 		cadreSurveyUserPerformance.setCadreSurveyUserId(cadreSurveyUserId);
			 		cadreSurveyUserPerformance.setSurveyTime(sdf.parse(dateUtilService.getCurrentDateInStringFormat()));
			 		cadreSurveyUserPerformance.setCadreSurveyUserPerformanceTypeId(performanceTypeId);
			 		cadreSurveyUserPerformance.setIsDeleted("false");
			 		cadreSurveyUserPerformance.setCreatedBy(loginUserId);
			 		cadreSurveyUserPerformance.setUpdatedBy(loginUserId);
			 		cadreSurveyUserPerformance.setComment(comment);
			 		cadreSurveyUserPerformance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			 		cadreSurveyUserPerformance.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			 		cadreSurveyUserPerformanceDAO.save(cadreSurveyUserPerformance);
			 	
			 	status.setMessage("success");
		 	}catch(Exception e){
			 LOG.error("Exception occurred at saveCaderSurveyUserPerformanceDetails() of FieldMonitoringService", e); 
			 status.setMessage("failure");
		 }
		 return status;
	 }
   public List<IdAndNameVO> getcadrePerformnanceTypeList(){
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   IdAndNameVO vo = null;
		   List<Object[]> perfrmnaceList=cadreSurveyUserPerformanceTypeDAO.getCadrePerformanceTypeList();
		   for (Object[] objects : perfrmnaceList) {
			   vo = new IdAndNameVO();
			   vo.setId(Long.valueOf(objects[1]!= null ? objects[1].toString():""));
			   vo.setName(objects[0] != null ? objects[0].toString():"");
			   returnList.add(vo);
		   }
	   }catch(Exception e){
		   LOG.error("Exception occurred at getcadrePerformnanceTypeList() of FieldMonitoringService", e);  
	   }
	   return returnList;
   }
  public List<IdAndNameVO> getcadrePerfrmanceList(Long cadreSurveyUserId){
       List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	  try{
		  Date today = dateUtilService.getCurrentDateAndTime();
		  Map<Long,IdAndNameVO> perfMap = new LinkedHashMap<Long, IdAndNameVO>();
		  List<Object[]> perfomList = cadreSurveyUserPerformanceDAO.getUserWisePerformanceByDate(cadreSurveyUserId,today);
		  if(perfomList != null && !perfomList.isEmpty()){
			for (Object[] obj : perfomList) {
				Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				String comment = obj[1] != null ? obj[1].toString():"";
				IdAndNameVO vo = perfMap.get(id);
				if(vo == null){
					vo = new IdAndNameVO();
					vo.setId(id);
					vo.setName(obj[2] != null ? obj[2].toString():"");
					vo.setMobileNumber(comment);//comment
					perfMap.put(id, vo);
				}
				else{
					vo.setMobileNumber(vo.getMobileNumber()+"<br>"+comment);//comment
				}
				//returnList.add(vo);
			}
		}
		  if(perfMap!= null){
			  returnList = new ArrayList<IdAndNameVO>(perfMap.values());
		  }
  }
  catch(Exception e){
	  LOG.error("Exception occurred at getcadrePerfrmanceList() of FieldMonitoringService", e);  
  }
	return returnList;  
}
  public List<IdAndNameVO> getDistrictWiseTodayAndOverAllCounts(String type,Long stateId,String sortType){
	  Map<Long,Long> targetMap = new LinkedHashMap<Long, Long>();
		List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> list = tdpCadreLocationInfoDAO.getDistrictWiseTodayAndOverAllCounts(type, stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setAttenteeCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					returnList.add(vo);
				}
			}
			
			List<Object[]> dstrictTrgetList = tdpCadreTargetCountDAO.getDistrictsTargetCountForTodayAndOverAll(stateId);
			if(dstrictTrgetList != null && !dstrictTrgetList.isEmpty()){
				for (Object[] obj : dstrictTrgetList) {
					Long locaId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					targetMap.put(locaId, count);
				}
			}
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO vo : returnList) {
					vo.setInviteeCount(targetMap.get(vo.getId()));//TargetCoount
					
				}
			}
			if(type.trim().equalsIgnoreCase("Today")){
				if(returnList != null && !returnList.isEmpty()){
					for (IdAndNameVO vo : returnList) {
						vo.setInviteeCount(vo.getInviteeCount()/IConstants.CADRE_REGISTRATION_2016_DAYS);//TargetCoount
						
					}
				}
			}
			if(returnList != null && !returnList.isEmpty()){
				for (IdAndNameVO vo : returnList) {
					vo.setPer2016(Double.valueOf(new BigDecimal(vo.getAttenteeCount()*(100.0)/vo.getInviteeCount()).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
				}
			}
			
			if(sortType != null && sortType.equalsIgnoreCase("percentage")){
				if(returnList != null && !returnList.isEmpty()){
					 Collections.sort(returnList, registrationsPercCountAsc);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDistrictWiseTodayAndOverAllCounts() of FieldMonitoringService", e);
		}
		return returnList;
	}
  
  public static Comparator<IdAndNameVO> registrationsPercCountAsc = new Comparator<IdAndNameVO>() {
		public int compare(IdAndNameVO vo2, IdAndNameVO vo1) {
			 //return (cstVO1.getScalePerc()) - (cstVO2.getScalePerc());
            Double perc2 = vo1.getPer2016();
            Double perc1 = vo2.getPer2016();
            return perc2.compareTo(perc1);
		}
	}; 
	public GISIssuesVO getMatchedLocationVO(Long id,List<GISIssuesVO> list){
		  GISIssuesVO returnvo = null;
	  	try {
				if(list != null && !list.isEmpty()){
					for (GISIssuesVO vo : list) {
						if(vo.getId().longValue() == id.longValue()){
							return vo;
						}
					}
				}
				return null;
			} catch (Exception e) {
				LOG.error("Exception occurred at getMatchedLocationVO() of FieldMonitoringService", e);
			}
	  	return returnvo;
	  }
	  
	  public List<GISIssuesVO> getLocationWiseIssueStatus(GISVisualizationParameterVO inputVO){
			List<GISIssuesVO> returnList = new ArrayList<GISIssuesVO>();
			try {
				
				List<CadreRegIssueStatus> statusList = cadreRegIssueStatusDAO.getAll();
				
				
				 
				List<Object[]> locationsList = null;
				if(inputVO.getParentLocationType() != null)
				{
					if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
						locationsList = districtDAO.getDistrictForState(inputVO.getStateId());
					}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
						locationsList = constituencyDAO.getConstituenciesByStateId(1l,inputVO.getStateId());
					}
				}
				
				if(locationsList != null && locationsList.size() > 0){
					for(Object[] obj : locationsList){
						GISIssuesVO vo1 = new GISIssuesVO();
						List<GISIssuesVO> issuesStatusList = new ArrayList<GISIssuesVO>();
						
						vo1.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo1.setName(obj[1] != null ? obj[1].toString():"");
						
						 if(statusList != null && statusList.size() >0){
							 for(CadreRegIssueStatus model : statusList){
								 GISIssuesVO vo = new GISIssuesVO(); 
								 vo.setId(model.getCadreRegIssueStatusId() != null ? model.getCadreRegIssueStatusId().longValue() : 0l);
								 vo.setName(model.getStatus() != null ? model.getStatus().toString() : "");
								 issuesStatusList.add(vo);
							 }
						}
						 vo1.setIssuesList(issuesStatusList);
						 
						returnList.add(vo1);	
					}
				}
				List<Object[]> list2 = cadreRegUserTabUserDAO.getLocationWiseIssueStatus(inputVO);
				if(list2 != null && !list2.isEmpty()){
					for (Object[] obj : list2) {
						Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						
						GISIssuesVO vo = getMatchedLocationVO(id, returnList);
						if(vo != null){
							List<GISIssuesVO> statsList = vo.getIssuesList();
							GISIssuesVO stutsVO = getMatchedLocationVO(statusId, statsList);
							if(stutsVO != null){
								stutsVO.setCount(count);
								vo.setTotalIssues(vo.getTotalIssues()+count);//location wise total issues
							}
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception occurred at getLocationWiseIssueStatus() of FieldMonitoringService", e);
			}
			return returnList;
		}
	
 public FieldMonitoringVO getVerificationCountList(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId, String fromDateStr,String toDateStr){
			FieldMonitoringVO returnVO = new FieldMonitoringVO();
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date fromDate = null;	
				Date toDate = null;
				if(fromDateStr != null && toDateStr != null){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				Long totalRegisteredCount = tdpCadreDataVerificationDAO.getTotalRegistered(stateId,districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
				Long passedCount = tdpCadreDataVerificationDAO.getVerifiedPassedCount(stateId,districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
			    Long rejectedCount = tdpCadreDataVerificationDAO.getVerifiedRejectedCount(stateId,districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
			   
			    Long pendingCount = 0l;
			    
			    if(totalRegisteredCount == null)
			    	totalRegisteredCount = 0l;
			    if(passedCount == null)
			    	passedCount = 0l;
			    if(rejectedCount == null)
			    	rejectedCount = 0l;
			 
			    pendingCount = totalRegisteredCount - passedCount - rejectedCount;
			    
			    returnVO.setTodayRegCount(totalRegisteredCount);
			    returnVO.setPassedcount(passedCount);
			    returnVO.setRejectedCount(rejectedCount);
			    returnVO.setPendingCount(pendingCount);
			    
			    
		}catch(Exception e){
			LOG.error("Exception occurred at getVerificationCountList() of FieldMonitoringService", e);
		}
		return returnVO;
	}	
 
 public List<FieldMonitoringVO> getVerfiedCadreSurveyUserDetails(Long stateId,Long districtId,Long constituencyId,Long cadreSurveyUserId, String fromDateStr,String toDateStr){
	List<FieldMonitoringVO> returnList = new ArrayList<FieldMonitoringVO>(); 
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date fromDate = null;	
		Date toDate = null;
		if(fromDateStr != null && toDateStr != null){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<Object[]> cadreDetailsList=tdpCadreDataVerificationDAO.getCadreSurveyUserDetails(stateId, districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
		if(cadreDetailsList != null && !cadreDetailsList.isEmpty()){
		for (Object[] objects : cadreDetailsList) {
			FieldMonitoringVO vo = new FieldMonitoringVO();
			vo.setCadreSurveyUserId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
			vo.setUserName(objects[2] != null ? objects[2].toString():"");
			vo.setTabUserName(objects[3] != null ? objects[3].toString():"");
			vo.setMobileNo(objects[1] != null ? objects[1].toString():"");
			vo.setTotalCount(Long.valueOf(objects[4] != null ? objects[4].toString():"0"));
			vo.setTabUserId(Long.valueOf(objects[5] != null ? objects[5].toString() :"0"));
			returnList.add(vo);
		}	
	}
		
		List<Object[]> cadreVerPassedList = tdpCadreDataVerificationDAO.getCadreVerfPassedDetails(stateId, districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
		if(cadreVerPassedList != null && !cadreVerPassedList.isEmpty()){
			for (Object[] objects : cadreVerPassedList) {
				Long cadreUserId = Long.valueOf(objects[0] != null ? objects[0].toString():"0");
				Long passedCount = Long.valueOf(objects[1] != null ? objects[1].toString():"0");
				Long tabUserId = Long.valueOf(objects[2] != null ? objects[2].toString():"0");
				FieldMonitoringVO vo = getMatchedVOByList(cadreUserId, tabUserId, returnList);
				if(vo != null)
					vo.setPassedcount(passedCount);
			}
		}
		
		List<Object[]> cadreVerRejList =tdpCadreDataVerificationDAO.getCadreVerfRejectedDetails(stateId, districtId, constituencyId, cadreSurveyUserId, fromDate, toDate);
		if(cadreVerRejList != null && !cadreVerRejList.isEmpty()){
			for (Object[] objects : cadreVerPassedList) {
			Long cadreUserId = Long.valueOf(objects[0] != null ? objects[0].toString():"0");
			Long rejectedCount = Long.valueOf(objects[1] != null ? objects[1].toString():"0");
			Long tabUserId = Long.valueOf(objects[2] != null ? objects[2].toString():"0");
			FieldMonitoringVO vo = getMatchedVOByList(cadreUserId, tabUserId, returnList);
			if(vo != null)
				vo.setRejectedCount(rejectedCount);
		}
	  }
		
		if(returnList != null && !returnList.isEmpty()){
			for(FieldMonitoringVO vo : returnList){
				vo.setPendingCount(vo.getTotalCount()-vo.getPassedcount()-vo.getRejectedCount());
			}
		}
		
		
	}catch(Exception e){
		LOG.error("Exception occurred at getVerfiedCadreSurveyUserDetails() of FieldMonitoringService", e);
	}
	return returnList;
 }
 
}
