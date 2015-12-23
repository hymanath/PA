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

	
	public List<Object[]> getFeedbackDocuments(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select model.filePath,model.trainingCampCadreFeedbackDocumentId from TrainingCampCadreFeedbackDocument model" +
				" where model.tdpCadreId =:tdpCadreId and model.isDeleted = 'N' and model.trainingCampBatch.attendeeTypeId=1 ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
		
	}
	public List<Object[]> getDocumentsCountForCadreWise(List<Long> tdpCadreIds){
		Query query = getSession().createQuery(" select count(model.filePath),model.tdpCadreId " +
				" from TrainingCampCadreFeedbackDocument model " +
				" where model.tdpCadreId in (:tdpCadreIds) and model.isDeleted='N'" +
				"  and model.trainingCampBatch.attendeeTypeId=1  group by model.tdpCadreId ");
		query.setParameterList("tdpCadreIds",tdpCadreIds);
		return query.list();
	}
	
	public List<Object[]> getFeedBackDocumentsCountProgramWise(Date startDate,Date endDate){
		
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
		 str.append("group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
		 		" model.trainingCampBatch.trainingCampSchedule.trainingCamp.trainingCampId ");
				
			Query query = getSession().createQuery(str.toString());
			
		 
			 if(startDate !=null && endDate !=null){
				 query.setParameter("startDate", startDate);
				 query.setParameter("endDate", endDate);
			 }
		
		return query.list();
	}
}
