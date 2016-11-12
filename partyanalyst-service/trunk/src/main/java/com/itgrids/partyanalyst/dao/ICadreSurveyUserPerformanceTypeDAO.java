package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformanceType;

public interface ICadreSurveyUserPerformanceTypeDAO extends GenericDao<CadreSurveyUserPerformanceType, Long>{
	
	public List<Object[]> getCadrePerformanceTypeList();

}
