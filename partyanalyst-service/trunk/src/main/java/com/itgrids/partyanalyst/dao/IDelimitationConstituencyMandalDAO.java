package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;


public interface IDelimitationConstituencyMandalDAO extends GenericDao<DelimitationConstituencyMandal, Long>{

	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(Long delimitationConstituencyID);
	public List<DelimitationConstituencyMandal> findDelConstMandalByConstID(Long constituencyID);
	
}
