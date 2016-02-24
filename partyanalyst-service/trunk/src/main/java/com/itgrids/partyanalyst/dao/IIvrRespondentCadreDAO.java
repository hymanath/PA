package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrRespondentCadre;

public interface IIvrRespondentCadreDAO extends GenericDao<IvrRespondentCadre, Long>{
	
	public List<Long> getIvrRespndantDetails(Long tdpCadreId);

}
