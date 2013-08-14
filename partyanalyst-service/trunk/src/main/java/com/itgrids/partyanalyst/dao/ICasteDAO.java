package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Caste;

public interface ICasteDAO extends GenericDao<Caste,Long>{
	 public List<Object[]> getCasteNamesByCasteId(String electionType, Long stateId, Long districtId,  Long constituencyId, String status);
		public List<Object[]> getCasteNames();
	 
	 /*public Object getCasteNamesByCasteId(Long casteId);*/
	//public List getCasteNames(Long casteStateId);
			
		public Caste getCasteByCastName(String casteName);
		
		public List<Caste> getCasteList();
}
