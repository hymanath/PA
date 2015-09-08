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
	

}
