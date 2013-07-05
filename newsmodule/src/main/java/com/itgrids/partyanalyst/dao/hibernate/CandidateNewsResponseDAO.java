package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateNewsResponseDAO;
import com.itgrids.partyanalyst.model.CandidateNewsResponse;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateNewsResponseDAO extends GenericDaoHibernate<CandidateNewsResponse, Long> implements  ICandidateNewsResponseDAO{
	public CandidateNewsResponseDAO() {
		super(CandidateNewsResponse.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getResponsefileGallaryIds(Long fileGallaryId, Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery(" select distinct CNR.responseFileGallary.fileGallaryId,CRN.candidate.candidateId " +
				" from CandidateNewsResponse CNR, CandidateRealatedNews CRN where CNR.responseFileGallary.fileGallaryId = CRN.fileGallary.fileGallaryId " +
				"  and CNR.fileGallary.fileGallaryId =:fileGallaryId ");
		
		query.setParameter("fileGallaryId", fileGallaryId);
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileGallaryIdsByResponseGallaryId(Long fileGallaryId, Integer startIndex, Integer maxIndex)
	{
		Query query = getSession().createQuery(" select distinct CNR.fileGallary.fileGallaryId,CRN.candidate.candidateId " +
				" from CandidateNewsResponse CNR, CandidateRealatedNews CRN where CNR.fileGallary.fileGallaryId = CRN.fileGallary.fileGallaryId " +
				"  and CNR.responseFileGallary.fileGallaryId =:fileGallaryId  ");
		
		query.setParameter("fileGallaryId", fileGallaryId);
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
		
	}
	
	public List<Long> getFileGallaryIdsByResponseGallaryId(Long responseFileGallaryId)
	{
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId from CandidateNewsResponse model " +
				" where model.responseFileGallary.fileGallaryId = :responseFileGallaryId ");
		
		query.setParameter("responseFileGallaryId", responseFileGallaryId);
		
		return query.list();
		
		
	}
	
	public List<Long> getResponseFileGallaryidForANews(List<Long> fileGallaryIds)
	{
		
		Query query = getSession().createQuery("select model.responseFileGallary.fileGallaryId from CandidateNewsResponse model" +
				" where model.fileGallary.fileGallaryId in( :fileGallaryIds )");
		
		query.setParameterList("fileGallaryIds", fileGallaryIds);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getFileGalleryIdByResponseGalleryId(Long fileGalleryId)
	{
		Query query = getSession().createQuery(" select model.responseFileGallary.fileGallaryId from CandidateNewsResponse model " +
				" where model.fileGallary.fileGallaryId =:fileGallaryId ");
		query.setParameter("fileGallaryId", fileGalleryId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getResponseNewsCount(List<Long> fileGalleryIdsList)
	{
		Query query = getSession().createQuery(" select distinct model.fileGallary.fileGallaryId from CandidateNewsResponse model,PartyGallery model2,Nomination model3,CandidateRealatedNews model4 where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId  " +
				" and model.fileGallary.fileGallaryId = model4.fileGallary.fileGallaryId and model3.candidate.candidateId = model4.candidate.candidateId  and model.fileGallary.isPrivate = 'false' and model.fileGallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' " +
				" and model.fileGallary.gallary.isDelete = 'false' and model.responseFileGallary.fileGallaryId in (:fileGalleryIdsList) and model3.party.partyId != :partyId");
		
		query.setParameterList("fileGalleryIdsList", fileGalleryIdsList);
		query.setParameter("partyId", 872L);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getResponseNewsCountForAParty(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model3.party.partyId, count(distinct model.fileGallary.fileGallaryId) from CandidateNewsResponse model,CandidateRealatedNews model2,PartyGallery model3 ");
		str.append(" where model.fileGallary.fileGallaryId = model2.fileGallary.fileGallaryId and model.fileGallary.gallary.gallaryId = model3.gallery.gallaryId and model3.isDelete = 'false' and ");
		str.append(" model3.isPrivate = 'false' and model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		str.append(" and model.fileGallary.gallary.contentType.contentType =:contentType ");
		if(fromDate != null)
			str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		  if(toDate != null)
			str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
		  
		  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
		   str.append(" and model3.party.partyId =:partyId ");
		  
		  if(categoryIdsList != null && categoryIdsList.size() > 0)
		   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
		  if(galleryIdsList != null && galleryIdsList.size() > 0)
		   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
		  
		  if(locationScopeId != null && locationScopeId > 0)
		   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
		  if(locationIdsList != null && locationIdsList.size() > 0)
		   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
		  
		  str.append(" group by model3.party.partyId");
		  
		  Query query = getSession().createQuery(str.toString());
		  query.setParameter("contentType", IConstants.NEWS_GALLARY);
		  if(fromDate != null)
		   query.setParameter("fromDate", fromDate);
		  if(toDate != null)
		   query.setParameter("toDate", toDate);
		  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
			query.setParameter("partyId", partyId);
		  if(categoryIdsList != null && categoryIdsList.size() > 0)
			 query.setParameterList("categoryIdsList", categoryIdsList);
		  if(galleryIdsList != null && galleryIdsList.size() > 0)
			  query.setParameterList("galleryIdsList", galleryIdsList);
		  if(locationScopeId != null && locationScopeId > 0)
			query.setParameter("locationScopeId", locationScopeId);
		  if(locationIdsList != null && locationIdsList.size() > 0)
			  query.setParameterList("locationIdsList", locationIdsList);
		  return query.list();
		
	}
	
}
