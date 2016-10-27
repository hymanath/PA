package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.service.IToursService;

public class ToursService implements IToursService {
	 
	private final static Logger LOG = Logger.getLogger(ToursService.class);
	
	private ISelfAppraisalDesignationDAO selfAppraisalDesignationDAO;
	private ISelfAppraisalCandidateDAO selfAppraisalCandidateDAO;
	private ISelfAppraisalCandidateDetailsDAO selfAppraisalCandidateDetailsDAO;
	
	
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
   
	
}
