package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.model.TrainingCampProgram;

public class TrainingCampProgramDAO extends GenericDaoHibernate<TrainingCampProgram, Long> implements ITrainingCampProgramDAO{

	public TrainingCampProgramDAO() {
		super(TrainingCampProgram.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getPrograms()
	{
	 Query query = getSession().createQuery("select distinct model.trainingCampProgramId,model.programName from TrainingCampProgram model order by model.programName asc");
	 return query.list();
	}
	
	public List<Object[]> getDistrictsByProgramId(Long programId){
		
		Query query=getSession().createQuery(" select model1.district.districtId,model1.district.districtName,model1.trainingCampId " +
	    " from TrainingCampSchedule model,TrainingCampDistrict model1" +
	    " where model.trainingCampId=model1.trainingCampId and model.trainingCampProgramId=:programId" +
	    " order by model1.trainingCampId,model1.district.districtId");
		query.setParameter("programId",programId);
		return query.list();
		
	}
	public List<Object[]> getAllTrainingPrograms(){
		
		Query query=getSession().createQuery(" select model.trainingCampProgramId,model.programName from TrainingCampProgram model ");
		
		return query.list();
	}
	
	public List<TrainingCampProgram> getAllRecordsByProgramId(Long programId)
	{
		Query query = getSession().createQuery(" select model from TrainingCampProgram model where model.trainingCampProgramId > :programId order by model.trainingCampProgramId ");
		
		query.setParameter("programId", programId);
		
		return query.list();
	}
	
	public List<Object[]> getConstsByProgramId(Long programId){
		
		Query query=getSession().createQuery(" select c.constituencyId,c.name,model1.trainingCampId " +
	    " from TrainingCampSchedule model,TrainingCampDistrict model1,Constituency c" +
	    " where model.trainingCampId=model1.trainingCampId and model.trainingCampProgramId=:programId and model1.districtId=c.district.districtId " +
	    "and c.electionScope.electionScopeId=2 and c.deformDate is null " +
	    " order by model1.trainingCampId,c.constituencyId");
		query.setParameter("programId",programId);
		return query.list();
		
	}
}
