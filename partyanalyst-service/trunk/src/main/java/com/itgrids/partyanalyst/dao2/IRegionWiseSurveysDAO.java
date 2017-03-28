package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegionWiseSurveys;

public interface IRegionWiseSurveysDAO  extends GenericDao<RegionWiseSurveys,Long>{
	
	public List<Object[]> getSurveyDetailsByRegion(Long regionId);


}
