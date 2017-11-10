package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;

public class JanmabhoomiCommitteeService implements IJanmabhoomiCommitteeService{

	
	private final static Logger LOG = Logger.getLogger(JanmabhoomiCommitteeService.class);
	private IJbCommitteeDAO jbCommitteeDAO;
	
	public IJbCommitteeDAO getJbCommitteeDAO() {
		return jbCommitteeDAO;
	}
	public void setJbCommitteeDAO(IJbCommitteeDAO jbCommitteeDAO) {
		this.jbCommitteeDAO = jbCommitteeDAO;
	}

	public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>(); 
		try{
			
			//List<Object[]> list = jbCommitteeDAO.
		}catch (Exception e) {
			LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	

}

