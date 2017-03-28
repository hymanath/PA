package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

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
			 
	public List<Object[]> getNominatedPostDocumentDetails(Set<Long> applicationIds){
		Query query = getSession().createQuery("select model.nominationPostCandidateId," +
												" model.filePath, model.nominatedPostApplicationId" +
												" from ApplicationDocument model" +
												" where model.nominatedPostApplicationId in (:applicationIds)" +												
												" and model.isDeleted = 'N'" +
												" and model.nominatedPostApplication.isDeleted='N'" );
		query.setParameterList("applicationIds", applicationIds);
		return query.list();
	}
	public List<Object[]> getApplicationDocuments(Long tdpCadreId,String searchType,Long nominateCandId,Long applicationId){
		StringBuilder str = new StringBuilder();
		
		if(nominateCandId != null && nominateCandId.longValue()>0L)
			searchType="Not Cadre";
		
		str.append(" select model.applicationDocumentId,model.filePath,date(model.insertedDate),model.nominatedPostApplication.nominatedPostApplicationId,model.nominationPostCandidate.nominationPostCandidateId from ApplicationDocument model  where model.isDeleted = 'N' and model.nominationPostCandidate.isDeleted = 'N'" +
				" and model.nominatedPostApplication.isExpired ='N' and model.nominatedPostApplication.isDeleted ='N' ");
	        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
	        	str.append(" and model.nominationPostCandidate.tdpCadre.tdpCadreId = :tdpCadreId ");
	        }
	        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
	        	str.append(" and model.nominationPostCandidate.nominationPostCandidateId = :nominateCandId ");
	        }
	        
	        if(applicationId != null && applicationId.longValue() >0l)
	        	str.append(" and model.nominatedPostApplication.nominatedPostApplicationId = :applicationId ");
	        
	        
	        //str.append( " order by model.postType.postTypeId ");
	        
	        Query query = getSession().createQuery(str.toString());
	        if(searchType !=null && searchType.equalsIgnoreCase("Cadre")){
	        	  query.setParameter("tdpCadreId", tdpCadreId);
	        }
	        else if(searchType !=null && searchType.equalsIgnoreCase("Not Cadre")){
	        	  query.setParameter("nominateCandId", nominateCandId);
	        }
	        
	        if(applicationId != null && applicationId.longValue() >0l)
	        	query.setParameter("applicationId", applicationId);
	      
	        return query.list();
	  }
}
