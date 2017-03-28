package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserPerformanceTypeDAO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.model.CadreSurveyUserPerformanceType;

public class CadreSurveyUserPerformanceTypeDAO  extends GenericDaoHibernate<CadreSurveyUserPerformanceType, Long> implements ICadreSurveyUserPerformanceTypeDAO{

	public CadreSurveyUserPerformanceTypeDAO() {
		super(CadreSurveyUserPerformanceType.class);
	}
	
	public List<Object[]> getCadrePerformanceTypeList(){
		Query query = getSession().createQuery("select model.type," +
				" model.cadreSurveyUserPerformanceTypeId" +
				" from CadreSurveyUserPerformanceType model" +
				" where model.isDeleted = 'false'" +
				" order by model.orderNo");
	    return query.list();
	}

}
