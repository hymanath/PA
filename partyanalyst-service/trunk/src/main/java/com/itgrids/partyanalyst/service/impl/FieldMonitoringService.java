package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.FieldMonitoringIssueVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreRegIssue;
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
	//Setters
	public void setFieldVendorLocationDAO(
			IFieldVendorLocationDAO fieldVendorLocationDAO) {
		this.fieldVendorLocationDAO = fieldVendorLocationDAO;
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
		        	   
		        	   cadreRegIssue.setCadreRegIssueTypeId(1L);
		        	   cadreRegIssue.setDescription(inputVO.getDescription());
		        	   
		        	   
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
    
    
    
    
    
}
