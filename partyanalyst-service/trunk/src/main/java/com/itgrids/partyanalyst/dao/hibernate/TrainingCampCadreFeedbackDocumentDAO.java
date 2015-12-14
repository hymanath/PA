package com.itgrids.partyanalyst.dao.hibernate;

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
				" where model.tdpCadreId =:tdpCadreId and model.isDeleted = 'N' ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
		
	}
	
	public List<Object[]> getDocumentsCountForCadreWise(List<Long> tdpCadreIds){
		Query query = getSession().createQuery(" select count(model.filePath),model.tdpCadreId " +
				" from TrainingCampCadreFeedbackDocument model " +
				" where model.tdpCadreId in (:tdpCadreIds) and model.isDeleted='N' group by model.tdpCadreId ");
		query.setParameterList("tdpCadreIds",tdpCadreIds);
		return query.list();
	}
}
