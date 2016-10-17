package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueStatusDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTrackDAO;
import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.FieldMonitoringVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegIssue;
import com.itgrids.partyanalyst.model.CadreRegIssueStatus;
import com.itgrids.partyanalyst.model.CadreRegIssueTrack;
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
	//Setters
	
	
	
	public void setFieldVendorLocationDAO(
			IFieldVendorLocationDAO fieldVendorLocationDAO) {
		this.fieldVendorLocationDAO = fieldVendorLocationDAO;
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
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-DD");
			Date startDate = null;
			Date endDate = null;
			Date currentTime = dateUtilService.getCurrentDateAndTime();
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
					vo.setFirstRecord(obj[5] != null ? obj[5].toString():"");
					vo.setRecentRecord(obj[6] != null ? obj[6].toString():"");
					vo.setTotalCount(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
					
					returnList.add(vo);
				}
			}
			
			//LastHour Counts
			List<Object[]> list1 = cadreRegIssueDAO.getLastHourCounts(vendorId, currentTime, locationType, locationVal);
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
			List<Object[]> list2 = cadreRegIssueDAO.getcadreRegIssuesCounts(vendorId, locationType, locationVal);
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
		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchedVOByList() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    public FieldMonitoringVO getDataCollectorsCounts(Long vendorId,String fromDateStr,String toDateStr,String locationType,Long locationVal){
    	FieldMonitoringVO returnvo = new FieldMonitoringVO();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-DD");
			Date startDate = null;
			Date endDate = null;
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			Long totalCount = cadreRegIssueDAO.getTotalDataCollectorsCountsVendorAndLocation(vendorId, startDate, endDate, locationType, locationVal);
			Long activeCount = cadreRegIssueDAO.getActiveDataCollectorsCountsVendorAndLocation(vendorId, currentTime, locationType, locationVal);
			Long passiveCount = 0l;
			if(totalCount == null)
				totalCount = 0l;
			if(activeCount == null)
				activeCount = 0l;
			passiveCount = totalCount - activeCount;
			
			returnvo.setTotalDataCollectors(totalCount);
			returnvo.setActiveUsers(activeCount);
			returnvo.setPassiveUsers(passiveCount);
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getDataCollectorsCounts() of FieldMonitoringService", e);
		}
    	return returnvo;
    }
    
    public ResultStatus saveFieldIssue(final FieldMonitoringIssueVO inputVO){
		final ResultStatus rs = new ResultStatus();
		final DateUtilService dateUtilService = new DateUtilService();
		
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
			        	
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
		        	   cadreRegIssue.setCreatedBy(inputVO.getLoginUserId());
		        	   cadreRegIssue.setUpdatedBy(inputVO.getLoginUserId());
		        	   
		        	   cadreRegIssue = cadreRegIssueDAO.save(cadreRegIssue);
		        	   
		        	   //tracking 
		        	   CadreRegIssueTrack cadreRegIssueTrack = new CadreRegIssueTrack();
		        	   cadreRegIssueTrack.setCadreRegIssueId(cadreRegIssue.getCadreRegIssueId());
		        	   cadreRegIssueTrack.setCadreRegIssueTypeId(inputVO.getIssueTypeId());
		        	   cadreRegIssueTrack.setDescription(inputVO.getDescription());
		        	   cadreRegIssueTrack.setCadreRegIssueStatusId(1L);//open
		        	   cadreRegIssueTrack.setInsertedBy(inputVO.getLoginUserId());
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
   public List<IdAndNameVO> getAllIssueStatusCount(String fromDateStr,String toDateStr){
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		   returnList.addAll(getAllIssueStatus());
		   List<Object[]> list = cadreRegIssueDAO.getCadreIssueStatusCount(startDate,endDate);
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
		   LOG.error("Exception raised at getAllIssueStatusCount", e);
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
			if (vo.getId().longValue() == id) {
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
   public List<IdAndNameVO> getStatusWiseIssueTypeCount(String fromDateStr,String toDateStr) {
	   List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	   try{
		   
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			if(fromDateStr != null && toDateStr != null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		   returnList.addAll(getAllIssueStatus());
		 
		   List<Object[]> list = cadreRegIssueDAO.getStatusWiseIssueTypeCount(startDate,endDate);
		   
		   if(list != null && list.size() >0){
				 for(Object[] obj : list){
					 IdAndNameVO statusVO = getMatchVO(returnList, (Long)obj[1]);	
					 	if(statusVO != null){ 
					 		statusVO.setIssueTypes(getCadreRegIssueType());
						 	IdAndNameVO issueTypeVO = getMatchVO(statusVO.getIssueTypes(), (Long)obj[3]);
							 	if(issueTypeVO != null){ 
							 		issueTypeVO.setInviteeCount(obj[0] != null ? (Long)obj[0] : 0l);
							 	}
					 	}
					}
				 }
		   
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   LOG.error("Exception raised at getStatusWiseIssueTypeCount", e);
	   }
	   return returnList;
	   
   }

	
    
}
