package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.itgrids.partyanalyst.dao.ICadreRegIssueStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringVerificationVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegIssue;
import com.itgrids.partyanalyst.model.CadreRegIssueStatus;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;
import com.itgrids.partyanalyst.model.CadreRegIssueType;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
	//Setters
	
	
	
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
    
    public FieldMonitoringVO getTabUsersDetailsByVendorAndLocationNew(Long loginUserId,Long constituencyId,Long cadreSurveyUserId,String fromDateStr,String toDateStr){
    	FieldMonitoringVO returnVO = new FieldMonitoringVO();
		
    	try {
    		
    		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId,0l);
    		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
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
    			
    			List<Object[]> list = cadreRegIssueDAO.getTabUsersDetailsByVendorAndLocationNew(cadreRegUserId, startDate, endDate, constituencyId, cadreSurveyUserId);
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
    					vo.setConstituencyId(Long.valueOf(obj[8] != null ? obj[8].toString():"0"));
    					vo.setImagePath(obj[9] != null ? obj[9].toString():"");
    					
    					returnList.add(vo);
    				}
    			}
    			
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(today);
    			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
    			Date lastOneHourTime = cal.getTime();
    			
    			//LastHour Counts
    			List<Object[]> list1 = cadreRegIssueDAO.getLastHourCountsNew(cadreRegUserId, lastOneHourTime, today, constituencyId, cadreSurveyUserId);
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
    			List<Object[]> list2 = cadreRegIssueDAO.getcadreRegIssuesCountsNew(cadreRegUserId, constituencyId, cadreSurveyUserId, startDate, endDate);
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
    			
    			returnVO = getDataCollectorsCountsNew(cadreRegUserId, constituencyId, cadreSurveyUserId, fromDateStr, toDateStr);
    			returnVO.setSubList(returnList);
    		}
    		
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
    
    public FieldMonitoringVO getDataCollectorsCountsNew(Long cadreRegUserId,Long constituencyId,Long userId,String fromDateStr,String toDateStr){
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
			
			Long totalCount = cadreRegIssueDAO.getTotalDataCollectorsCountsVendorAndLocationNew(cadreRegUserId, constituencyId, userId, startDate, endDate);
			Long activeCount = cadreRegIssueDAO.getActiveDataCollectorsCountsVendorAndLocationNew(cadreRegUserId, constituencyId, userId, lastOneHourTime, today);
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
		        	
		        	Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(inputVO.getLoginUserId(),0l);
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
    		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId,stateId);
    		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
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
    		}
    		
		}catch(Exception e){
			LOG.error("Exception raised at getIssuesForATabUser", e);
		}
    	return returnList;
    }
    
public List<FieldMonitoringIssueVO> getIssuesCountsForATabUserByStatusNew(Long cadreSurveyUserId,Long tabUserInfoId,String fromDateStr,String toDateStr,Long loginUserId,Long stateId){
	List<FieldMonitoringIssueVO> returnList = new ArrayList<FieldMonitoringIssueVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId,stateId);
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
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
		}
		
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
		        	Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(loginUserId,0l);
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
    public List<FieldMonitoringIssueVO> trackingRegIssueByRegIssueId(Long cadreRegIssueId,Long stateId){
    	
    	List<FieldMonitoringIssueVO> finalList = null;
    	SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
    	try{
    		
    		List<Object[]> trackList = cadreRegIssueTrackDAO.trackingRegIssueByRegIssueId(cadreRegIssueId,stateId);
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
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) - 1);
			Date lastOneHourTime = cal.getTime();
			
			Long totalCount = fieldVendorTabUserDAO.getTotalDataCollectorsCount(startDate, endDate,stateId);
			Long activeCount = fieldVendorTabUserDAO.getActiveDataCollectorsCount(lastOneHourTime, today,stateId);
			Long passiveCount = 0l;
			if(totalCount == null)
				totalCount = 0l;
			if(activeCount == null)
				activeCount = 0l;
			if(totalCount > 0l)
				passiveCount = totalCount - activeCount;
			
			returnVO.setTotalDataCollectors(totalCount);
			returnVO.setActiveUsers(activeCount);
			returnVO.setPassiveUsers(passiveCount);
			
	} catch (Exception e) {
		LOG.error("Exception occurred at getOverAllDataCollectorsDetails() of FieldMonitoringService", e);
	}
	   return returnVO;
   }
   
   public List<FieldMonitoringVO> getStatusWiseIssuesDetails(String fromDateStr,String toDateStr,Long issueTypeId,Long statusTypeId,Long stateId){
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
					//vo.setVendorId(Long.valueOf(obj[10] != null ? obj[10].toString():"0"));
					//vo.setVendorName(obj[11] != null ? obj[11].toString():"");
					String vendorName = fieldVendorTabUserDAO.getVendorNameByCadreSurveyUserId(cadreSurveyUserId);
					vo.setVendorName(vendorName);
					returnList.add(vo);
				}
			}
			
			//IssuesCounts
			List<Object[]> list1 = fieldVendorTabUserDAO.getUserWiseIssuesCounts(startDate, endDate);
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
			
	} catch (Exception e) {
		LOG.error("Exception occurred at getStatusWiseIssuesDetails() of FieldMonitoringService", e);
	}
	   return returnList;
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

public List<CadreRegUserVO> getCadreRegUserAssignedConstituencies(Long userId){
	List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(userId,0l);
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedConstituencies(cadreRegUserId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					CadreRegUserVO vo = new CadreRegUserVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
	}
	return returnList;
}

public List<CadreRegUserVO> getCadreRegUserAssignedUsers(Long userId,Long constituencyId){
	List<CadreRegUserVO> returnList = new ArrayList<CadreRegUserVO>();
	try {
		Long cadreRegUserId = cadreRegUserDAO.getCadreRegUserByUser(userId,0l);
		if(cadreRegUserId != null && cadreRegUserId.longValue() > 0l){
			List<Object[]> list = cadreRegUserTabUserDAO.getUserAssignedUsers(cadreRegUserId,constituencyId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					CadreRegUserVO vo = new CadreRegUserVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception occurred at getCadreRegUserAssignedConstituencies() of FieldMonitoringService", e);
	}
	return returnList;
}
}
