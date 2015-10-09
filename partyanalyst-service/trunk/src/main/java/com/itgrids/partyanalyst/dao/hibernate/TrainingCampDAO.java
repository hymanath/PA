package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.model.TrainingCamp;

public class TrainingCampDAO extends GenericDaoHibernate<TrainingCamp, Long> implements ITrainingCampDAO{

	public TrainingCampDAO() {
		super(TrainingCamp.class);
	}
    
	public List<Object[]> getAllCamps()
	{
 	  Query query = getSession().createQuery("select distinct model.trainingCampId,model.campName from TrainingCamp model order by model.campName asc");
	  return query.list();
	}
	
    public List<Object[]> getCampDistrictsByCampId(Long campId){
		
		Query query=getSession().createQuery(" select model1.district.districtId,model1.district.districtName" +
		" from TrainingCamp model,TrainingCampDistrict model1" +
		" where model.trainingCampId =model1.trainingCampId and model.trainingCampId=:trainingCampId " +
		" order by model1.district.districtId asc");
		query.setParameter("trainingCampId",campId);
		return query.list();
	}
    public List<Object[]> getAllTrainingCamps(){
    	
    	Query query=getSession().createQuery(" select model.trainingCampId,model.campName from TrainingCamp model");
    	
    	return query.list();
    }
    
    public List<TrainingCamp> getAllRecordsByCampId(Long campId)
    {
    	Query query = getSession().createQuery(" select model from TrainingCamp model where model.trainingCampId > :campId order by model.trainingCampId ");
    	
    	query.setParameter("campId", campId);
    	
    	return query.list();
    }
	
    public List<Object[]> getCampConstsByCampId(Long campId){
		
		Query query=getSession().createQuery(" select c.constituencyId,c.name " +
		" from TrainingCamp model,TrainingCampDistrict model1,Constituency c " +
		" where model.trainingCampId =model1.trainingCampId and model.trainingCampId=:trainingCampId and model1.district.districtId=c.district.districtId " +
		" and c.electionScope.electionScopeId=2 and c.deformDate is null " +
		" order by c.constituencyId asc");
		query.setParameter("trainingCampId",campId);
		return query.list();
	}
}
