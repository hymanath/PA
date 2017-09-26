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
	public List<Object[]> getTrainingCampDetailsByCampIds(List<Long> trainingCampIdsList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.trainingCamp.trainingCampId,model.trainingCamp.campName,");
		queryStr.append("model.trainingCamp.location,model.district.districtName ");
		queryStr.append(" from TrainingCampDistrict model "); 
		queryStr.append(" left join model.trainingCamp trainingCamp ");
		queryStr.append(" where trainingCamp.trainingCampId in(:trainingCampIdsList) " +
				"order by model.trainingCamp.trainingCampId asc ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("trainingCampIdsList", trainingCampIdsList);
		return query.list();
	}
}
