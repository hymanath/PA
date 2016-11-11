package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserPerformanceTypeDAO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformanceType;

public class CadreSurveyUserPerformanceTypeDAO  extends GenericDaoHibernate<CadreSurveyUserPerformanceType, Long> implements ICadreSurveyUserPerformanceTypeDAO{

	public CadreSurveyUserPerformanceTypeDAO() {
		super(CadreSurveyUserPerformanceType.class);
	}

}
