package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.model.TrainingCampProgram;
import com.itgrids.partyanalyst.utils.IConstants;

public class TrainingCampProgramDAO extends GenericDaoHibernate<TrainingCampProgram, Long> implements ITrainingCampProgramDAO{

	public TrainingCampProgramDAO() {
		super(TrainingCampProgram.class);
	}

	public List<Object[]> getPrograms()
	{
		Query query = getSession().createQuery("select distinct model.trainingCampProgramId,model.programName from TrainingCampProgram model " +
				" where model.isDeleted='N'  order by model.programName asc");
		return query.list();
	}

	public List<Object[]> getDistrictsByProgramId(List<Long> programIds,List<Long> enrollmentYrIds){
		StringBuilder sb=new StringBuilder();
		sb.append(" select model1.district.districtId,model1.district.districtName,model1.trainingCampId " +
				" from TrainingCampSchedule model,TrainingCampDistrict model1" +
				" where model.trainingCampId=model1.trainingCampId " );
		if(programIds != null && programIds.size()>0)
			sb.append(" and model.trainingCampProgramId in (:programIds) " );
		if(enrollmentYrIds != null && enrollmentYrIds.size()>0){
			sb.append(" and model.enrollmentYear.enrollmentYearId in (:enrollmentYrIds) " );
		}
		sb.append(" and model.trainingCampProgram.isDeleted='N' " );
		sb.append(" order by model1.trainingCampId,model1.district.districtId ");
		Query query=getSession().createQuery(sb.toString());
		if(programIds != null && programIds.size()>0)
		   query.setParameterList("programIds",programIds);
        if(enrollmentYrIds != null && enrollmentYrIds.size()>0){
        	query.setParameterList("enrollmentYrIds",enrollmentYrIds);
		}
		return query.list();

	}
	public List<Object[]> getAllTrainingPrograms(){

		Query query=getSession().createQuery(" select model.trainingCampProgramId,model.programName from TrainingCampProgram model " +
				" where model.isDeleted='N' ");

		return query.list();
	}

	public List<TrainingCampProgram> getAllRecordsByProgramId(Long programId)
	{
		Query query = getSession().createQuery(" select model from TrainingCampProgram model where model.trainingCampProgramId > :programId " +
				" and model.isDeleted='N' order by model.trainingCampProgramId ");

		query.setParameter("programId", programId);

		return query.list();
	}

	public List<Object[]> getConstsByProgramId(List<Long> programIds,List<Long> enrollmentYrIds){
          StringBuilder sb =new StringBuilder();
          sb.append(" select c.constituencyId,c.name,model1.trainingCampId " +
				" from TrainingCampSchedule model,TrainingCampDistrict model1,Constituency c" +
				" where model.trainingCampId=model1.trainingCampId " );
          if(programIds != null && programIds.size()>0)
           sb.append(" and model.trainingCampProgramId in (:programIds) " );
          if(enrollmentYrIds != null && enrollmentYrIds.size()>0)
        	  sb.append(" and model.enrollmentYear.enrollmentYearId in (:enrollmentYrIds) " ); 
          sb.append(" and model1.districtId=c.district.districtId " +
				" and c.electionScope.electionScopeId=2 and c.deformDate is null " +
				" and model.trainingCampProgram.isDeleted='N' " );
          sb.append(" order by model1.trainingCampId,c.constituencyId");
          Query query =getSession().createQuery(sb.toString());
          if(programIds != null && programIds.size()>0)
		     query.setParameterList("programIds",programIds);
          if(enrollmentYrIds != null && enrollmentYrIds.size()>0)
        	  query.setParameterList("enrollmentYrIds",enrollmentYrIds);
		return query.list();

	}
	public List<Object[]> getTrainingProgramDetailsByProgramIds(List<Long> programIds){
        StringBuilder sb =new StringBuilder();
        sb.append("select distinct model.trainingCampProgramId,model.programName "); 
        sb.append(" from TrainingCampProgram model  where model.isDeleted='N' "); 
        sb.append(" and model.trainingCampProgramId in (:programIds)");
      
        Query query =getSession().createQuery(sb.toString());
  	  query.setParameterList("programIds",programIds);

		return query.list();

	}
	
	public List<Long> getProgramIdsList(){
		StringBuilder sb =new StringBuilder();
        sb.append("select model.trainingCampProgramId "); 
        sb.append(" from TrainingCampProgram model  where model.isDeleted='N' "); 
        sb.append(" and model.trainingCampProgramId in (:programIds)");
      
        Query query =getSession().createQuery(sb.toString());
  	    query.setParameterList("programIds",IConstants.leaderShipSkillsProgramId);

		return query.list();
	}
}
