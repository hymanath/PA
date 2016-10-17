package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreRegIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;

public class FieldMonitoringService implements IFieldMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	//Attributes
	private IFieldVendorLocationDAO fieldVendorLocationDAO;
	private IFieldVendorTabUserDAO  fieldVendorTabUserDAO;        
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
	
    public ICadreRegIssueTypeDAO getCadreRegIssueTypeDAO() {
		return cadreRegIssueTypeDAO;
	}
	public void setCadreRegIssueTypeDAO(ICadreRegIssueTypeDAO cadreRegIssueTypeDAO) {
		this.cadreRegIssueTypeDAO = cadreRegIssueTypeDAO;
	}
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
    
}
