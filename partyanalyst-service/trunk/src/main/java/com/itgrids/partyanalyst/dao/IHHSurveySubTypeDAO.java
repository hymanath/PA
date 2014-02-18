package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveySubType;

public interface IHHSurveySubTypeDAO extends GenericDao<HHSurveySubType, Long>{
	
	public List<Object[]> getSubSurveyTypesByMainTypeId(Long mainTypeId);
	public List<Object[]> getMainSurveyTypes();
	public List<Object[]> getSubSurveyTypes();


}
