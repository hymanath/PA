package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.Tehsil;


public interface IDelimitationConstituencyMandalDAO extends GenericDao<DelimitationConstituencyMandal, Long>{

	public List<DelimitationConstituencyMandal> findDelConstMandalByDelConstID(Long delimitationConstituencyID);
	
	public List<Tehsil> getTehsilsByDelimitationConstituencyID(Long delimitationConstituencyID);
	
	public List getStateDistConstituencyMandalByMandalID(Long mandalID);
}
