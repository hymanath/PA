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
	public List<Object[]> getNewsCountForACandidate(Date fromDate, Date toDate,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar, Long partyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.fileGallary.fileGallaryId),model.candidate.candidateId, model.candidate.lastname,model.fileGallary.file.regionScopes.regionScopesId from CandidateRealatedNews model,PartyGallery model2,Nomination model3 ");
		str.append(" where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId and model3.candidate.candidateId = model.candidate.candidateId and model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' ");
		str.append(" and model2.isDelete = 'false' and model2.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		str.append(" and model.fileGallary.file.fileId is not null ");
		
		str.append(" and model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
					"Nomination model4 where model4.candidate.candidateId = model.candidate.candidateId " +
					"and model4.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2))");
		
		if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
		 str.append(" and ( model3.party.partyId =:partyId )");
		
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
		
		if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
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
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getLocationValuesByLocationScopeId(Long locationScopeId,Date fromDate, Date toDate)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.file.locationValue from CandidateRealatedNews model where model.fileGallary.isPrivate = 'false' ");
	  str.append("  and model.fileGallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' ");
	  str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId and model.fileGallary.gallary.contentType.contentType =:contentType and model.candidate.candidateId is not null ");
	  if(fromDate != null)
	   str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("locationScopeId", locationScopeId);
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  if(fromDate != null)
	   query.setParameter("fromDate", fromDate);
	  if(toDate != null)
		 query.setParameter("toDate", toDate);
	  return query.list();
	  
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllGallariesListForParty(Date fromDate,Date toDate,Long locationScopeId,List<Long> locationIdsList)
	{
		
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallary.gallary.gallaryId , model.fileGallary.gallary.name from CandidateRealatedNews model where ");
		str.append(" model.fileGallary.gallary.contentType.contentType = :contentType and model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' ");
		str.append(" and model.fileGallary.gallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
		
		if(locationScopeId >0)
		 str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
		if(locationIdsList != null && locationIdsList.size() > 0)
		 str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
		
		str.append(" and model.candidate.candidateId is not null order by model.fileGallary.gallary.name ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("contentType", IConstants.NEWS_GALLARY);
		
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
		if(locationScopeId >0)
		 query.setParameter("locationScopeId", locationScopeId);
		if(locationIdsList != null && locationIdsList.size() > 0)
		 query.setParameterList("locationIdsList", locationIdsList);
		
		return query.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoryList(Date fromDate, Date toDate,Long locationScopeId,List<Long> locationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallary.file.category.categoryId,model.fileGallary.file.category.categoryType from CandidateRealatedNews model where ");
		str.append(" model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' ");
		str.append(" and model.fileGallary.gallary.contentType.contentType =:contentType ");
		if(fromDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
		if(locationScopeId > 0)
		 str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
		if(locationIdsList != null && locationIdsList.size() > 0)
		 str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");	
		
		str.append(" and model.candidate.candidateId is not null order by model.fileGallary.file.category.categoryType ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("contentType", IConstants.NEWS_GALLARY);
		if(fromDate != null)
		 query.setParameter("fromDate", fromDate);	
		if(toDate != null)
		 query.setParameter("toDate", toDate);	
		if(locationScopeId >0)
			query.setParameter("locationScopeId", locationScopeId);
		if(locationIdsList != null && locationIdsList.size() > 0)
		  query.setParameterList("locationIdsList", locationIdsList);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGalleryListForSelectedCategory(List<Long> categoryIdsList)
	{
		Query query = getSession().createQuery(" select distinct model.fileGallary.gallary.gallaryId, model.fileGallary.gallary.name from CandidateRealatedNews model where model.fileGallary.isPrivate = 'false' " +
				" and model.fileGallary.isDelete ='false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete ='false' and model.fileGallary.file.category.categoryId in (:categoryIdsList)" +
				" and model.candidate.candidateId is not null order by model.fileGallary.gallary.name ");
		
		query.setParameterList("categoryIdsList", categoryIdsList);
		return query.list();
	}
	
	public List<Object[]> getCandidateByFileGallaryId(List<Long> gallaryIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.fileGallary.fileGallaryId,model.candidate.lastname from CandidateRealatedNews model where model.fileGallary.fileGallaryId  in(:gallaryIdsList) ");
		str.append(" order by model.fileGallary.file.fileDate desc ");
		Query query = getSession().createQuery(str.toString());	
		
		if(gallaryIdsList !=null && gallaryIdsList.size() > 0)
			query.setParameterList("gallaryIdsList", gallaryIdsList);
		
	return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.fileGallaryId from CandidateRealatedNews model,PartyGallery model2,Nomination model3  where model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and model3.party.partyId =:partyId");
	  
	  //get selected Party fileGallery Ids
	  if(selectedPartyId != null && selectedPartyId > 0)
	    str.append(" model3.party.partyId =:selectedPartyId ");
	  
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  if(startIndex != null)
		query.setFirstResult(startIndex);
	  if(maxIndex != null)
		query.setMaxResults(maxIndex);
	  
	  return query.list();
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalNewsCountForAParty(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar)
	{
		StringBuilder str = new StringBuilder();
		  str.append(" select model2.party.partyId,model2.party.shortName,count(distinct model.fileGallary.fileGallaryId) from CandidateRealatedNews model,PartyGallery model2 where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
		  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
		  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType ");
		  if(fromDate != null)
			str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
		  if(toDate != null)
			str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
		  
		  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
		   str.append(" and model2.party.partyId =:partyId ");
		  
		  if(categoryIdsList != null && categoryIdsList.size() > 0)
		   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
		  if(galleryIdsList != null && galleryIdsList.size() > 0)
		   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
		  
		  if(locationScopeId != null && locationScopeId > 0)
		   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
		  if(locationIdsList != null && locationIdsList.size() > 0)
		   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
		  
		  str.append(" group by model2.party.partyId order by model2.party.shortName");
		  
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
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getNotRespondFileGalleryIds(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,List<Long> respondFileGalleryIds)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.fileGallaryId from CandidateRealatedNews model,PartyGallery model2,Nomination model3 where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append(" and model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType ");
	  str.append(" and model3.party.partyId != :partyId ");
	  
	  if(respondFileGalleryIds != null && respondFileGalleryIds.size() > 0)
	   str.append(" and model.fileGallary.fileGallaryId not in (:respondFileGalleryIds)");
	  
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
	  
	  if(respondFileGalleryIds != null && respondFileGalleryIds.size() > 0)
	   query.setParameterList("respondFileGalleryIds", respondFileGalleryIds);
	  
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
	public List<Object[]> getRespondNewsPartyDetails(List<Long> respondFileGalleryIdsList)
	{
	  Query query = getSession().createQuery(" select count(distinct model.fileGallary.fileGallaryId),model3.party.shortName,model3.party.partyId from CandidateRealatedNews model,PartyGallery model2,Nomination model3 where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId " +
	  		" and model.candidate.candidateId = model3.candidate.candidateId and  model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' " +
	  		" and model.fileGallary.gallary.isDelete = 'false'and model3.party.partyId !=:partyId and model2.isDelete ='false' and model2.isPrivate = 'false' " +
	  		" and model.fileGallary.fileGallaryId in (:respondFileGalleryIdsList) group by model3.party.partyId order by model3.party.shortName ");
	        
	  query.setParameterList("respondFileGalleryIdsList", respondFileGalleryIdsList);
	  query.setParameter("partyId", 872l);

	  return query.list();
	}
	public List<Object[]> getRespondNewsPartyDetailsCustom(List<Long> respondFileGalleryIdsList)
	{
	  Query query = getSession().createQuery(" select count(distinct model.fileGallary.fileGallaryId),model3.party.shortName,model3.party.partyId from CandidateRealatedNews model,PartyGallery model2,CandidateParty model3 where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId " +
	  		" and model.candidate.candidateId = model3.candidate.candidateId and  model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' " +
	  		" and model.fileGallary.gallary.isDelete = 'false'and model3.party.partyId !=:partyId and model2.isDelete ='false' and model2.isPrivate = 'false' " +
	  		" and model.fileGallary.fileGallaryId in (:respondFileGalleryIdsList) group by model3.party.partyId order by model3.party.shortName ");
	        
	  query.setParameterList("respondFileGalleryIdsList", respondFileGalleryIdsList);
	  query.setParameter("partyId", 872l);

	  return query.list();
	}
	
 // start anil methods to retrieve data for response count 
	
	public List<Long> getResponseCountBasedTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct cn.fileGallary.fileGallaryId from CandidateNewsResponse cn, " +
	  		"CandidateRealatedNews model,PartyGallery model2,Nomination model3 " +
	  	//	"CandidateParty model4 " +
	  	//	"  where model3.party.partyId = model4.party.partyId " +
	  		"where  model.fileGallary.fileGallaryId = cn.responseFileGallary.fileGallaryId " +
	  		"and model.candidate.candidateId = model3.candidate.candidateId " +
	  		"and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId   ");
	  
	  //get FileGallery Ids For Selected Party
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId = :selectedPartyId ");
	  
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and ( model.candidate.party.partyId =:partyId or model4.party.partyId =:partyId )   ");
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
	  str.append(" group by model3.candidate.candidateId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  if(startIndex != null)
		  query.setFirstResult(startIndex);
	  if(maxIndex != null)
		  query.setMaxResults(maxIndex);
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  return query.list();
				
	}
	public List<?> getNotResponseCountBasedTotalNewsCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.fileGallaryId " +          //,model.candidate.candidateId ,model3.candidate.candidateId ,model3.party.partyId 
	  		"  from CandidateRealatedNews model,CandidateNewsResponse cn, PartyGallery model2,Nomination model3  where  " +
	  		" model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append("and  model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
				"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
				"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	 str.append(" and model3.party.partyId !=:partyId and model.fileGallary.fileGallaryId != cn.fileGallary.fileGallaryId ");
	 
	  //get Selected Party fileGalleryIds other then TDP
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId =:selectedPartyId ");
	  
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
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
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
	   query.setParameter("selectedPartyId", selectedPartyId);
	  if(startIndex != null)
		query.setFirstResult(startIndex);
	  if(maxIndex != null)
		 query.setMaxResults(maxIndex);
	  
	  return query.list();
				
	}
	
	public List<Long> getTotalNewsCountCustom(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.fileGallaryId from CandidateRealatedNews model,PartyGallery model2,CandidateParty model3  where model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  str.append(" and model3.party.partyId =:partyId");
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
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
	
	
	public List<Long> getResponseCountBasedTotalNewsCountCustom(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct cn.fileGallary.fileGallaryId from CandidateNewsResponse cn, CandidateRealatedNews model,PartyGallery model2,CandidateParty model3  where model.fileGallary.fileGallaryId = cn.responseFileGallary.fileGallaryId and model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  str.append(" and model3.party.partyId =:partyId");
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
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
	
	public List<Long> getResponseForParty(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct cn.fileGallary.fileGallaryId from CandidateNewsResponse cn, " +
	  		"CandidateRealatedNews model,PartyGallery model2,Nomination model3" +	  	
	  		"  where  model.fileGallary.fileGallaryId = cn.fileGallary.fileGallaryId and " +	  		
	  		"  model.candidate.candidateId = model3.candidate.candidateId " +
	  		"and cn.fileGallary.gallary.gallaryId = model2.gallery.gallaryId " +
	  		"and model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
					"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
					"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2))" );
	  
	  //get FileGallery Ids For Selected Party
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId = :selectedPartyId ");
	  
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and ( model.candidate.party.partyId =:partyId or model4.party.partyId =:partyId )   ");
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
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  if(startIndex != null)
		  query.setFirstResult(startIndex);
	  if(maxIndex != null)
		  query.setMaxResults(maxIndex);
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  return query.list();
				
	}
	// responce table query
	
	public List<Object[]> getRespondNewsPartyDetails(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct count(model5.fileGallary.fileGallaryId),model6.party.shortName ,model6.party.partyId,model5.candidate.candidateId ,model6.candidate.candidateId  " +   //,model.candidate.candidateId ,model3.candidate.candidateId ,model3.party.partyId
	  		" from CandidateNewsResponse cn, " +
	  		" CandidateRealatedNews model,PartyGallery model2,Nomination model3,CandidateRealatedNews model5 , Nomination model6 " +

	  		"where  model.fileGallary.fileGallaryId = cn.responseFileGallary.fileGallaryId and cn.fileGallary.fileGallaryId = model5.fileGallary.fileGallaryId " +
	  		"and model5.candidate.candidateId = model6.candidate.candidateId " +
	  		"and model.candidate.candidateId = model3.candidate.candidateId " +
	  		"and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId  " +
	  		"and model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
					"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
					"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) "+
					"and model6.constituencyElection.election.electionDate in(select max(model7.constituencyElection.election.electionDate) from " +
					"Nomination model7 where model7.candidate.candidateId = model6.candidate.candidateId " +
					"and model6.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) "
					);
	  
	  //get FileGallery Ids For Selected Party
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId = :selectedPartyId ");
	  
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and ( model.candidate.party.partyId =:partyId or model4.party.partyId =:partyId )   ");
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
	 str.append(" group by model6.party.partyId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  if(startIndex != null)
		  query.setFirstResult(startIndex);
	  if(maxIndex != null)
		  query.setMaxResults(maxIndex);
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  return query.list();
				
	}
	//forIDs
	public List<Object[]> getRespondNewsIds(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model5.fileGallary.fileGallaryId,model6.party.shortName ,model6.party.partyId,model5.candidate.candidateId ,model6.candidate.candidateId  " +   //,model.candidate.candidateId ,model3.candidate.candidateId ,model3.party.partyId
	  		" from CandidateNewsResponse cn, " +
	  		" CandidateRealatedNews model,PartyGallery model2,Nomination model3,CandidateRealatedNews model5 , Nomination model6 " +

	  		"where  model.fileGallary.fileGallaryId = cn.responseFileGallary.fileGallaryId and cn.fileGallary.fileGallaryId = model5.fileGallary.fileGallaryId " +
	  		"and model5.candidate.candidateId = model6.candidate.candidateId " +
	  		"and model.candidate.candidateId = model3.candidate.candidateId " +
	  		"and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId  " +
	  		"and model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
					"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
					"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) "+
					"and model6.constituencyElection.election.electionDate in(select max(model7.constituencyElection.election.electionDate) from " +
					"Nomination model7 where model7.candidate.candidateId = model6.candidate.candidateId " +
					"and model6.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) "
					);
	  
	  //get FileGallery Ids For Selected Party
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId = :selectedPartyId ");
	  
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and ( model.candidate.party.partyId =:partyId or model4.party.partyId =:partyId )   ");
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
	// str.append(" group by model6.party.partyId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  if(startIndex != null)
		  query.setFirstResult(startIndex);
	  if(maxIndex != null)
		  query.setMaxResults(maxIndex);
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  return query.list();
				
	}
	
	public List<Object[]> getNotResponseCount(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select count(distinct model.fileGallary.fileGallaryId) ,model3.party.shortName ,model3.party.partyId,model.candidate.candidateId ,model3.candidate.candidateId  "+
	  		"  from CandidateRealatedNews model,CandidateNewsResponse cn, PartyGallery model2,Nomination model3  where  " +
	  		" model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append("and  model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
				"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
				"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	 str.append(" and model3.party.partyId !=:partyId and model.fileGallary.fileGallaryId  not in (select cn1.fileGallary.fileGallaryId from CandidateNewsResponse cn1) ");
	 
	  //get Selected Party fileGalleryIds other then TDP
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId =:selectedPartyId ");
	  
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  str.append(" group by model3.party.partyId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
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
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
	   query.setParameter("selectedPartyId", selectedPartyId);
	  if(startIndex != null)
		query.setFirstResult(startIndex);
	  if(maxIndex != null)
		 query.setMaxResults(maxIndex);
	  
	  return query.list();
				
	}
	public List<Object[]> getNotResponseCountPerfect(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.fileGallary.fileGallaryId ,model3.party.shortName ,model3.party.partyId,model.candidate.candidateId ,model3.candidate.candidateId,model3.candidate.lastname  "+
	  		"  from CandidateRealatedNews model,CandidateNewsResponse cn, PartyGallery model2,Nomination model3  where  " +
	  		" model.candidate.candidateId = model3.candidate.candidateId and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId ");
	  str.append("and  model3.constituencyElection.election.electionDate in(select max(model4.constituencyElection.election.electionDate) from " +
				"Nomination model4 where model4.candidate.candidateId = model3.candidate.candidateId " +
				"and model3.constituencyElection.election.electionScope.electionType.electionTypeId in(1,2)) ");
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	 str.append(" and model3.party.partyId !=:partyId and model.fileGallary.fileGallaryId not in (select cn1.fileGallary.fileGallaryId from CandidateNewsResponse cn1) and model3.party.partyId is not null");
	 
	  //get Selected Party fileGalleryIds other then TDP
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId =:selectedPartyId ");
	  
	  if(fromDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
	  if(toDate != null)
		str.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
	  
	  if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
	   str.append(" and model2.party.partyId =:partyId ");
	  
	  if(categoryIdsList != null && categoryIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.category.categoryId in (:categoryIdsList) ");
	  if(galleryIdsList != null && galleryIdsList.size() > 0)
	   str.append(" and model.fileGallary.gallary.gallaryId in (:galleryIdsList) ");
	  
	  if(locationScopeId != null && locationScopeId > 0)
	   str.append(" and model.fileGallary.file.regionScopes.regionScopesId =:locationScopeId ");
	  if(locationIdsList != null && locationIdsList.size() > 0)
	   str.append(" and model.fileGallary.file.locationValue in (:locationIdsList) ");
	  //str.append(" group by model3.party.partyId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  query.setParameter("partyId", partyId);
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
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
	   query.setParameter("selectedPartyId", selectedPartyId);
	  if(startIndex != null)
		query.setFirstResult(startIndex);
	  if(maxIndex != null)
		 query.setMaxResults(maxIndex);
	  
	  return query.list();
				
	}
	public List<Object[]> getRespondNewsPartyDetailsForCandidateTable(Date fromDate,Date toDate,Long partyId,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar,Integer startIndex,Integer maxIndex,Long selectedPartyId)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct count(model5.fileGallary.fileGallaryId),model6.party.shortName ,model6.party.partyId,model5.candidate.candidateId ,model6.candidate.candidateId  " +   //,model.candidate.candidateId ,model3.candidate.candidateId ,model3.party.partyId
	  		" from CandidateNewsResponse cn, " +
	  		" CandidateRealatedNews model,PartyGallery model2,CandidateParty model3,CandidateRealatedNews model5 , CandidateParty model6 " +

	  		"where  model.fileGallary.fileGallaryId = cn.responseFileGallary.fileGallaryId and cn.fileGallary.fileGallaryId = model5.fileGallary.fileGallaryId " +
	  		"and model5.candidate.candidateId = model6.candidate.candidateId " +
	  		"and model.candidate.candidateId = model3.candidate.candidateId " +
	  		"and model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId  " +
	  		"and model3.partyId = :partyId and  model6.partyId != :partyId "
					
					);
	  
	  //get FileGallery Ids For Selected Party
	  if(selectedPartyId != null && selectedPartyId > 0)
	   str.append(" and model3.party.partyId = :selectedPartyId ");
	  
	  str.append(" and model.fileGallary.isDelete ='false' and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false'");
	  str.append(" and model2.isDelete ='false' and model2.isPrivate = 'false' and model.fileGallary.gallary.contentType.contentType =:contentType");
	  
	  //str.append(" and ( model.candidate.party.partyId =:partyId or model4.party.partyId =:partyId )   ");
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
	 str.append(" group by model6.party.partyId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("contentType", IConstants.NEWS_GALLARY);
	  //query.setParameter("partyId", partyId);
	  
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
	  if(startIndex != null)
		  query.setFirstResult(startIndex);
	  if(maxIndex != null)
		  query.setMaxResults(maxIndex);
	  
	  if(selectedPartyId != null && selectedPartyId > 0)
		query.setParameter("selectedPartyId", selectedPartyId);
	  
	  return query.list();
				
	}
	
	
	//NewsCountForACandidate From CandidateParty table
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsCountForACandidateFromCandidateParty(Date fromDate, Date toDate,List<Long> categoryIdsList,List<Long> galleryIdsList,List<Long> locationIdsList,Long locationScopeId,String tempVar, Long partyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.fileGallary.fileGallaryId),model.candidate.candidateId, model.candidate.lastname,model.fileGallary.file.regionScopes.regionScopesId from CandidateRealatedNews model,PartyGallery model2,CandidateParty model3 ");
		str.append(" where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId and model3.candidate.candidateId = model.candidate.candidateId and model.fileGallary.isDelete = 'false' and model.fileGallary.isPrivate = 'false' ");
		str.append(" and model2.isDelete = 'false' and model2.isPrivate = 'false' and model.fileGallary.gallary.isDelete = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		str.append(" and model.fileGallary.file.fileId is not null ");
		
		
		if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
		 str.append(" and ( model3.party.partyId =:partyId )");
		
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
		
		if(tempVar != null && (tempVar.equalsIgnoreCase("all") || tempVar.equalsIgnoreCase("byDate")))
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
}
