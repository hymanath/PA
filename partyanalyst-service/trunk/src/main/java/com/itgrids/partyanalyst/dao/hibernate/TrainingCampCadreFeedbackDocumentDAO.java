package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreFeedbackDocumentDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreFeedbackDocument;

public class TrainingCampCadreFeedbackDocumentDAO extends GenericDaoHibernate<TrainingCampCadreFeedbackDocument, Long> implements ITrainingCampCadreFeedbackDocumentDAO{

	public TrainingCampCadreFeedbackDocumentDAO() {
		super(TrainingCampCadreFeedbackDocument.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> getFeedbackDocuments(Long tdpCadreId,Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("");
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
		 
		Query query = getSession().createQuery("select model.filePath,model.trainingCampCadreFeedbackDocumentId from TrainingCampCadreFeedbackDocument model" +
				" where model.tdpCadreId =:tdpCadreId and model.isDeleted = 'N' and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" ");
		query.setParameter("tdpCadreId", tdpCadreId);
		if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
		return query.list();
		
	}
	public List<Object[]> getDocumentsCountForCadreWise(List<Long> tdpCadreIds,Long enrollmentYearId){
		 StringBuilder queryStr = new StringBuilder();
			queryStr.append("");
			 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
				 queryStr.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYearId =:enrollmentYearId ");
			 
		Query query = getSession().createQuery(" select count(model.filePath),model.tdpCadreId " +
				" from TrainingCampCadreFeedbackDocument model " +
				" where model.tdpCadreId in (:tdpCadreIds) and model.isDeleted='N'" +
				"  and model.trainingCampBatch.attendeeTypeId=1 "+queryStr.toString()+" group by model.tdpCadreId ");
		query.setParameterList("tdpCadreIds",tdpCadreIds);
		 if(enrollmentYearId != null && enrollmentYearId.longValue()>0L)
			   query.setParameter("enrollmentYearId",enrollmentYearId);
		return query.list();
	}
	
	public List<Object[]> getFeedBackDocumentsCountProgramWise(Date startDate,Date endDate,List<Long> enrollmentYearIds,List<Long> programYearIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
				" model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName," +
				" model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId," +
				" model.trainingCampBatch.trainingCampSchedule.trainingCamp.campName,count(model.filePath ) " +				
				" from " +
				" TrainingCampCadreFeedbackDocument model " +
				" where model.trainingCampBatch.isCancelled = 'false' " +
				"  and model.isDeleted ='N' and model.tdpCadre.isDeleted='N' " +
				"  and model.trainingCampBatch.attendeeTypeId=1 " );
		
		 if(startDate !=null && endDate !=null){
			 str.append(" and date(model.insertedTime) between :startDate and :endDate ");
		 }
		 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
			 str.append(" and model.trainingCampBatch.trainingCampSchedule.enrollmentYear.enrollmentYearId in (:enrollmentYearIds)");
   	        }
		 if(programYearIds != null && programYearIds.size()>0){
			 str.append(" and model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId in(:programYearIds)");
		 }
		 str.append(" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
		 		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
				
			Query query = getSession().createQuery(str.toString());
			
		 
			 if(startDate !=null && endDate !=null){
				 query.setParameter("startDate", startDate);
				 query.setParameter("endDate", endDate);
			 }
			 if(enrollmentYearIds != null && enrollmentYearIds.size()>0){
				 query.setParameterList("enrollmentYearIds", enrollmentYearIds);
			 }
			 if(programYearIds != null && programYearIds.size()>0){
				 query.setParameterList("programYearIds", programYearIds);
			 }
		return query.list();
	}
}
