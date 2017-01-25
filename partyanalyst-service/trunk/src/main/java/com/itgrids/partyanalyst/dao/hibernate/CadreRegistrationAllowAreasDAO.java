package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegistrationAllowAreasDAO;
import com.itgrids.partyanalyst.model.CadreRegistrationAllowAreas;

public class CadreRegistrationAllowAreasDAO extends GenericDaoHibernate<CadreRegistrationAllowAreas, Long> implements ICadreRegistrationAllowAreasDAO{

	public CadreRegistrationAllowAreasDAO(){
		super(CadreRegistrationAllowAreas.class);
	}

	public List<Long> getAssignedBoothDetailsInAssemblyList(List<Long> constituencyIdsList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.boothId from CadreRegistrationAllowAreas model where model.isDeleted ='false' ");
		if(constituencyIdsList != null && constituencyIdsList.size()>0)
			queryStr.append(" and model.booth.constituency.constituencyId in (:constituencyIdsList) ");
		Query query = getSession().createQuery(queryStr.toString());
	
		if(constituencyIdsList != null && constituencyIdsList.size()>0)
			query.setParameterList("constituencyIdsList", constituencyIdsList);
		return query.list();
	}
}
