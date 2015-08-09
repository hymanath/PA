package com.itgrids.partyanalyst.dao.hibernate;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.ITrainingCampDistrictDAO;
import com.itgrids.partyanalyst.model.TrainingCampDistrict;

public class TrainingCampDistrictDAO extends GenericDaoHibernate<TrainingCampDistrict, Long> implements ITrainingCampDistrictDAO{

	public TrainingCampDistrictDAO() {
		super(TrainingCampDistrict.class);
	}
	
	public List<Object[]> getCampDetailsByDistrictIds(List<Long> districtIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.trainingCamp.trainingCampId, model.trainingCamp.campName from TrainingCampDistrict model ");
		if(districtIdsList != null && districtIdsList.size()>0)
			queryStr.append(" where model.district.districtId in (:districtIdsList)");
		queryStr.append(" order by model.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(districtIdsList != null && districtIdsList.size()>0)
			query.setParameterList("districtIdsList", districtIdsList);
		return query.list();
	}
}
