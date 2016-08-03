package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IApplicationDocumentDAO;
import com.itgrids.partyanalyst.model.ApplicationDocument;

public class ApplicationDocumentDAO extends GenericDaoHibernate<ApplicationDocument, Long> implements IApplicationDocumentDAO{

	public ApplicationDocumentDAO() {
		super(ApplicationDocument.class);
		
	}
	public Integer deleteNominatedUploadedFile(List<Long> applicatnDocIdList)
	{
		Query query = getSession().createQuery(" update  ApplicationDocument model set model.isDeleted = 'Y' where model.applicationDocumentId in(:applicatnDocIdList) ");
		
		query.setParameterList("applicatnDocIdList", applicatnDocIdList);
		return query.executeUpdate();
	}

	public List<Object[]> getNominatedPostReport(Long tdpCadreId){
		Query query=getSession().createQuery(" select model.filePath,date(model.insertedDate) from ApplicationDocument model where model.nominationPostCandidate.tdpCadreId =:tdpCadreId" +
				                              " and model.isDeleted ='N' ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
			 
}
