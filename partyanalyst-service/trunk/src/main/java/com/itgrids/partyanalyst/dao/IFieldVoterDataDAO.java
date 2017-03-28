package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FieldVoterData;

public interface IFieldVoterDataDAO extends GenericDao<FieldVoterData, Long>{
	
	public List<Object[]> getFieldVotersDataByBoothId(Long boothId);

}
