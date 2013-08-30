package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DemoRequest;

public interface IDemoRequestDAO extends GenericDao<DemoRequest, Long>{
	
	public List<DemoRequest> getDemoRequestList();
	
	public DemoRequest getDemoRequestByDemoRequestId(Long demoRequestId);
	
	public Integer deleteDemoRequestByDemoRequestId(Long demoRequestId);
	

}
