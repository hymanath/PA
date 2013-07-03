package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateRelatedNewsDAO extends GenericDaoHibernate<CandidateRealatedNews, Long> implements 
ICandidateRelatedNewsDAO {
	
	
	public CandidateRelatedNewsDAO() {
		super(CandidateRealatedNews.class);
	}
	
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId  ,Date fromDate  ,Date toDate)
	{
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId , model.fileGallary.file.fileTitle , model.fileGallary.file " +
				"from CandidateRealatedNews model where model.candidate.candidateId = :candidateId and " +
				" date(model.fileGallary.file.fileDate) >= :fromDate and date(model.fileGallary.file.fileDate) <= :toDate" +
				" and model.fileGallary.isDelete = :isDelete " +
				"order by model.fileGallary.file.fileTitle");
		
		query.setParameter("candidateId", candidateId);
		query.setParameter("isDelete", "false");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.list();
		
		
	}
	
	public List<Object[]> getNewsForAParty(Long partyId  ,Date fromDate  ,Date toDate)
	{
		
		Query query = getSession().createQuery("select model.fileGallaryId , model.file.fileTitle, model.fileGallary.file " +
				" from model FileGallary model where model.gallary.gallaryId " +
				"in(select model1.gallery.galleryId from PartyGallary model1 where model1.party.partyId = :partyId)" );
		
		query.setParameter("partyId", partyId);
		
		return query.list();
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidates(){
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.lastname " +
				"from CandidateRealatedNews model order by model.candidate.lastname ");
		return queryObj.list();
	}
	
	public List<Object[]> getCandidatesContainsNews()
	{
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.firstname,model.candidate.lastname,model.candidate.lastname " +
				"from CandidateRealatedNews model order by model.candidate.lastname");
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getFileGallaryListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> gallaryIdsList,List<Long> categoryIdsList)
    {
		StringBuilder str = new StringBuilder();
		str.append(" select model.fileGallary from CandidateRealatedNews model where model.candidate.candidateId =:candidateId and ");
		str.append(" model.fileGallary.gallary.isDelete='false' and model.fileGallary.gallary.contentType.contentType= :type and model.fileGallary.isDelete = 'false' ");
		if(queryType.equals("Public"))
		  str.append(" and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='false' ");
				
		else if(queryType.equals("Private"))
		  str.append("  and ( (model.fileGallary.gallary.isPrivate='true') or(model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='true') ) ");
		
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
			
		if(gallaryIdsList != null && gallaryIdsList.size() > 0)
		 str.append(" and model.fileGallary.gallary.gallaryId in (:gallaryIdsList) ");
		
		if(categoryIdsList != null && categoryIdsList.size() > 0)
		 str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
		
		 str.append(" order by model.fileGallary.file.fileDate desc ");
		Query query = getSession().createQuery(str.toString());
			 
		 query.setLong("candidateId", candidateId);
		 query.setString("type", IConstants.NEWS_GALLARY);
		 if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		 if(toDate != null)
		  query.setParameter("toDate", toDate);
		 
		 if(gallaryIdsList !=null && gallaryIdsList.size() > 0)
		  query.setParameterList("gallaryIdsList", gallaryIdsList);
		 if(categoryIdsList != null && categoryIdsList.size() > 0)
		  query.setParameterList("categoryIdsList", categoryIdsList);
		 
		 if(firstResult != null)
		 query.setFirstResult(firstResult);
		 if(maxResult != null)
		 query.setMaxResults(maxResult);
		 return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesWithCount(){
		Query queryObj=getSession().createQuery("select count(distinct model.fileGallary.fileGallaryId), model.candidate.candidateId,model.candidate.lastname " +
				"from CandidateRealatedNews model where model.fileGallary.isPrivate='false' and model.fileGallary.isDelete ='false' and model.fileGallary.gallary.isDelete ='false' " +
				" and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.file.fileId is not null group by model.candidate.candidateId order by model.candidate.lastname ");
		return queryObj.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateRelatedNewsByGallaryId(Long candidateId,Long gallaryId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallary.file.fileId,model.fileGallary.file.fileTitle,model.fileGallary.file from CandidateRealatedNews model ");
		str.append(" where model.candidate.candidateId =:candidateId and model.fileGallary.gallary.gallaryId =:gallaryId and model.fileGallary.isDelete ='false' ");
		str.append(" and model.fileGallary.gallary.isDelete ='false' and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate='false' ");
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate");
		str.append(" order by model.fileGallary.file.fileTitle ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("candidateId", candidateId);
		query.setParameter("gallaryId", gallaryId);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsForACandidateByCategoryId(Long candidateId,Long categoryId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallary.file.fileId,model.fileGallary.file.fileTitle,model.fileGallary.file from CandidateRealatedNews model ");
		str.append(" where model.candidate.candidateId =:candidateId and model.fileGallary.file.category.categoryId =:categoryId and model.fileGallary.isDelete ='false' ");
		str.append(" and model.fileGallary.gallary.isDelete ='false' and model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate='false' ");
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate");
		str.append(" order by model.fileGallary.file.fileTitle ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("candidateId", candidateId);
		query.setParameter("categoryId", categoryId);
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getFileGalleryIdByCandidateId(Long candidateId)
	{
		Query query = getSession().createQuery(" select model.fileGallary.fileGallaryId from CandidateRealatedNews model where model.candidate.candidateId =:candidateId");
		query.setParameter("candidateId", candidateId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsCountForACandidate(Long partyId, Date fromDate, Date toDate,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.fileGallary.fileGallaryId),model.candidate.candidateId, model.candidate.lastname,model.fileGallary.file.regionScopes.regionScopesId from CandidateRealatedNews model,PartyGallery model2 ");
		str.append(" where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId and model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' ");
		str.append(" and model2.isDelete = 'false' and model2.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		str.append(" and model2.party.partyId =:partyId and model.fileGallary.file.fileId is not null ");
		
		if(categoryIdsList != null && categoryIdsList.size() > 0 )
		 str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
		if(galleryIdsList != null && galleryIdsList.size() > 0)
		 str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
		
		if(locationScopeId != null && locationScopeId > 0)
		 str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
		
		if(locationIdsList != null && locationIdsList.size() > 0)
		 str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
		
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
		
		str.append(" group by model.fileGallary.file.regionScopes.regionScopesId ,model.candidate.candidateId order by model.candidate.lastname  ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("partyId", partyId);
		
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
		
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
	
	
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getLocationWiseFileGalleryList(Long candidateId,Date fromDate,Date toDate,Long locationScopeId,Integer startIndex,Integer maxIndex,List<Long> galleryIdsList,List<Long> categoryIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallary from CandidateRealatedNews model where model.fileGallary.isPrivate ='false' and model.fileGallary.isDelete ='false' ");
		str.append(" and model.fileGallary.gallary.isPrivate ='false' and model.fileGallary.gallary.isDelete ='false' and model.fileGallary.file.regionScopes.regionScopesId =:regionScopesId ");
		if(candidateId != null && candidateId >0)
		 str.append(" and model.candidate.candidateId =:candidateId ");
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate");
		
		if(categoryIdsList != null && categoryIdsList.size() > 0)
		 str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
		if(galleryIdsList != null && galleryIdsList.size() >0)
		 str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
		
		str.append(" order by model.fileGallary.file.fileDate desc");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("regionScopesId", locationScopeId);
		if(candidateId != null && candidateId >0)
		 query.setParameter("candidateId", candidateId);
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
		if(categoryIdsList != null && categoryIdsList.size() > 0)
		 query.setParameterList("categoryIdsList", categoryIdsList);
		
		if(galleryIdsList != null && galleryIdsList.size() >0)
			query.setParameterList("galleryIdsList", galleryIdsList);
		
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
	}
	

}
