package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IProblemLocationDAO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	private IProblemLocationDAO problemLocationDAO;
	private IProblemHistoryDAO problemHistoryDAO;
	
	public IProblemLocationDAO getProblemLocationDAO() {
		return problemLocationDAO;
	}
	
	public void setProblemLocationDAO(IProblemLocationDAO problemLocationDAO) {
		this.problemLocationDAO = problemLocationDAO;
	}
	
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}
	
	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}
	
	public void createProblem(){
		
	}
	

}
