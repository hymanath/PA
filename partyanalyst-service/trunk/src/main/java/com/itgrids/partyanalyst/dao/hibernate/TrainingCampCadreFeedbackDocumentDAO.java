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
}
