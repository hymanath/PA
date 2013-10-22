package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.utils.IConstants;


public class PartyGalleryDAO extends GenericDaoHibernate<PartyGallery,Long> implements IPartyGalleryDAO{
	
	public PartyGalleryDAO()
	{
		super(PartyGallery.class);
	}
	
	public List<Object[]> getPartyGallaryDetail(Long partyId,String type , int startIndex , int maxRecords) {
	 	 
		Query query = getSession().createQuery("select model.gallery.gallaryId,model.gallery.name,model.gallery.description," +
				"model.gallery.createdDate,model.gallery.updateddate from PartyGallery model " +
				" where model.party.partyId=:partyId and  model.gallery.contentType.contentType= :type  " +
				" and model.gallery.isDelete = :isDelete and model.gallery.isPrivate = :isPrivate " +
				" and (select count(*)  from FileGallary model1 where model1.gallary.gallaryId = model.gallery.gallaryId and model1.isDelete = :isDelete and model1.isPrivate = :isPrivate ) > 0 " +
				" order by model.gallery.createdDate desc");
		
		query.setLong("partyId", partyId);
		query.setString("type", type);
		query.setString("isDelete", "false");
		query.setString("isPrivate","false" );
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
						
		return query.list(); 
		
	}
	
	public List<Object[]> getNewsCountDetailsForGallaries(List<Long> gallaryIds)
	{
		Query query = getSession().createQuery("select model.gallary.gallaryId , count( model.gallary.gallaryId) " +
				" from FileGallary model where model.gallary.gallaryId in(:gallaryIds) and model.isDelete = 'false' " +
				" and  model.gallary.isDelete='false' group by model.gallary.gallaryId");
		
		query.setParameterList("gallaryIds", gallaryIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getRespondedFilesCount(List<Long> gallaryIds,Long categoryId){
		StringBuilder query = new StringBuilder();
		
		query.append("select model1.fileGallary.gallary.gallaryId," +
				" count( distinct model1.fileGallary.file.fileId) from CandidateNewsResponse model1," +
				"FileGallary model where model1.fileGallary.fileGallaryId=model.fileGallaryId");
		if(categoryId!=null && categoryId!=0){
			query.append(" and model1.fileGallary.file.category.categoryId= :categoryId ");
		}
		query.append(" and model1.fileGallary.gallary.gallaryId in (:gallaryIds) and model.isDelete='false' group by model1.fileGallary.gallary.gallaryId ");
		
		Query queryObject = getSession().createQuery(query.toString());
		/*Query query=getSession().createQuery("select model1.fileGallary.gallary.gallaryId," +
				" count( distinct model1.fileGallary.file.fileId) from CandidateNewsResponse model1," +
				"FileGallary model where model1.fileGallary.fileGallaryId=model.fileGallaryId " +
				"and model1.fileGallary.gallary.gallaryId in (:gallaryIds) and model.isDelete='false' group by model1.fileGallary.gallary.gallaryId ");*/
		if(categoryId!=null && categoryId!=0){
			queryObject.setParameter("categoryId", categoryId);
		}
		queryObject.setParameterList("gallaryIds", gallaryIds);
		return queryObject.list();
	}
	

	public List<Object[]> getFirstFourNewsToDisplay(Long partyId,int firstResult,int maxResult,String queryType)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.file.fileDate,model.gallary.candidate.candidateId  " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and  model.gallary.isDelete='false' and model.gallary.contentType.contentType= :type   and model.isDelete = :isDelete   ");
		
		if(queryType.equalsIgnoreCase("Public"))
		   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
		
		if(queryType.equalsIgnoreCase("Private"))
		  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		query.append(" order by model.file.fileDate desc ");
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("candidateId", partyId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		queryObject.setString("isDelete", "false");
		queryObject.setFirstResult(firstResult);
		queryObject.setMaxResults(maxResult);
			
						
		return queryObject.list(); 
	}
	
	public List<Object[]> getPartyGallaryDetail(Long partyId,int firstResult,int maxResult,String type) {
	 	 
		Query query = getSession().createQuery("select model.gallery.gallaryId,model.gallery.name,model.gallery.description,model.gallery.createdDate," +
				"model.gallery.updateddate from PartyGallery model  where model.party.partyId=:partyId and  " +
				"model.gallery.contentType.contentType= :type  and model.gallery.isDelete = :isDelete and model.gallery.isPrivate = :isPrivate " +
				" and (select count(*)  from FileGallary model1 where model1.gallary.gallaryId = model.gallery.gallaryId and model1.isDelete = :isDelete and model1.isPrivate = :isPrivate ) > 0 " +
				" order by model.gallery.createdDate desc");
		query.setLong("partyId", partyId);
		query.setString("type", type);
		query.setString("isDelete", "false");
		query.setString("isPrivate","false" );
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
			
						
		return query.list(); 
		
	}
	
	
	
	public List<Object[]> getNewsByScope(Long partyId,Long scopeType,int startIndex,int maxResults,String queryType , String sourceStr , String languageStr)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.fileId,model.fileName,model.filePath,model.fileTitle,model.fileDescription , " +
				" model.sourceObj.source ,model.language.language ,model.fileDate " +
				" from File model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = 'false'  " +
				" and model.gallary.isDelete = 'false'  ");

		if(scopeType!=null)
			query.append("   and model.file.regionScopes.regionScopesId =:scopeType   ");
		
		if(sourceStr!=null)
			query.append("   and model.file.sourceObj.source =:spScope");
		
		if(languageStr!=null)
			query.append("   and model.file.language.language =:spScopeLang");
		
		if(queryType.equalsIgnoreCase("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equalsIgnoreCase("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		query.append("   order by model.file.fileDate desc   ");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("partyId", partyId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		if(scopeType!=null)
		queryObject.setLong("scopeType", scopeType);
		
		if(sourceStr!=null)
		queryObject.setString("spScope", sourceStr);
		
		if(languageStr!=null)
		queryObject.setString("spScopeLang", languageStr);
		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResults);	
						
		return queryObject.list(); 
	}
	
	public List<File> getFirstFourNewsForParty(Long partyId,int firstResult,int maxResult,String queryType)
	{
		Query query = getSession().createQuery("select model.file from FileGallary model where model.gallary.gallaryId in "+
				"(select model2.gallery.gallaryId from PartyGallery model2 where model2.party.partyId = :partyId"+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate) order by model.file.fileDate desc");
		
		query.setLong("partyId", partyId);
		query.setString("type", IConstants.NEWS_GALLARY);
		
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list(); 
	 }
	
	public List<Object[]> getNewsForParty(Long partyId,int firstResult,int maxResult,String queryType)
	{
		Query query = getSession().createQuery("select model.file,model.fileGallaryId from FileGallary model where model.gallary.gallaryId in "+
				"(select model2.gallery.gallaryId from PartyGallery model2 where model2.party.partyId = :partyId"+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate) group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
		
		query.setLong("partyId", partyId);
		query.setString("type", IConstants.NEWS_GALLARY);
		
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list(); 
	 }
	
	public List<Long> getNewsCountByScope(Long partyId,Long scopeType,String queryType)
	{
		StringBuilder query = new StringBuilder();
		
		query.append("select count(model.file.fileId) from FileGallary model where model.gallary.gallaryId in "+
				"(select model2.gallery.gallaryId from PartyGallery model2 where model2.party.partyId = :partyId"+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate) ");
		
		if(scopeType!=null)
			query.append("   and model.file.regionScopes.regionScopesId =:scopeType ");
			
		if(queryType.equalsIgnoreCase("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false' ");
			
		if(queryType.equalsIgnoreCase("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		query.append(" group by model.file.fileId ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setString("isDelete", "false");
		queryObject.setString("isPrivate", "false");
		queryObject.setLong("partyId", partyId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		if(scopeType!=null)
		queryObject.setLong("scopeType", scopeType);
		
		return queryObject.list(); 
	}
	public List<Object[]> getAllNewsDetails(Long partyId,int firstResult,int maxResult,String queryType){
		   
	     StringBuilder query = new StringBuilder();
			query.append("select model.file,model.fileGallaryId,model.file.fileDate from FileGallary model where model.gallary.gallaryId in "+
				"(select model2.gallery.gallaryId from PartyGallery model2 where model2.party.partyId = :partyId"+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate) ");
				if(queryType.equalsIgnoreCase("Public"))
			   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
			  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			
			query.append("group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
				
							
			return queryObject.list(); 
  }
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription,  " +
				" model.gallary.name from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallariesByPartyId(Long partyId,String contentType)
	{
		Object[] params = {partyId,contentType};
		return getHibernateTemplate().find("select model.gallery.gallaryId,model.gallery.name from PartyGallery model where model.party.partyId = ? and model.gallery.contentType.contentType = ? and model.isDelete = 'false' order by model.gallery.name asc",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyGalleriesDescForUpdate(Long gallaryId,Long partyId){
		
	   Object[] params = {gallaryId,partyId};
	   return getHibernateTemplate().find("select model.gallery.gallaryId,model.gallery.name,model.gallery.description,model.isPrivate from PartyGallery model where model.gallery.gallaryId = ? and model.party.partyId =?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> getPartyByGalleryId(Long gallaryId)
	{
		return getHibernateTemplate().find("select model.party from PartyGallery model where model.gallery.gallaryId = ?",gallaryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getPartyVisibility(Long gallaryId)
	{
		return getHibernateTemplate().find("select model.isPrivate from PartyGallery model where model.gallery.gallaryId = ?",gallaryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getOtherGalleries(Long partyId,List<Long>gallaryIds,String contentType)
	{
		Query query = getSession().createQuery("select model.gallery.gallaryId from PartyGallery model where model.gallery.contentType.contentType = ? and "+
				" model.party.partyId = ? and model.gallery.gallaryId not in(:gallaryIds) and model.gallery.isPrivate = 'false' and model.gallery.isDelete = 'false'");
		query.setParameter(0, contentType);
		query.setParameter(1, partyId);
		query.setParameterList("gallaryIds", gallaryIds);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public Integer deletePartyGallary(Long gallaryId)
	{
		StringBuilder query = new StringBuilder();
		query.append("update PartyGallery model set model.isDelete='true' where model.gallery.gallaryId = ?");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, gallaryId);
		return queryObject.executeUpdate();
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyGallaryByPartyId(Long partyId, String contentType, String gallaryName)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model.gallery.gallaryId, model.gallery.name from PartyGallery model ");
		stringBuilder.append(" where model.party.partyId =:partyId and model.gallery.contentType.contentType = :contentType ");
		stringBuilder.append(" and model.gallery.name = :gallaryName ");
		stringBuilder.append("and model.isDelete = :isDelete ");
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		queryObj.setParameter("partyId", partyId);
		queryObj.setParameter("contentType", contentType);
		queryObj.setParameter("gallaryName", gallaryName);
		queryObj.setParameter("isDelete", IConstants.FALSE);
		
		return queryObj.list();
	}
	public List<Object[]> getAllNewsDetailsForDistrict(Long partyId,int firstResult,int maxResult,String queryType,long stateId ,List<Long> districtIds){
		   
	     StringBuilder query = new StringBuilder();
				query.append("select model.file,model.fileGallaryId,model.file.fileDate,fs.source.source,model2.party.partyFlag,model2.party.partyId from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
					" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
					" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete ");
			if(queryType.equalsIgnoreCase("Public"))
			   query.append(" and model.isPrivate = :isPrivate and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
			  query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			  query.append(" and model.file.locationValue in (:locId)  and model.file.regionScopes.regionScopesId = :locationValue ");
            //query.append("or  (model.file.regionScopes.regionScopesId = :constituencyScopeId and " );
			//query.append(" model.file.locationValue = :constituencyVal) " );
			query.append("group by model.file.fileId order by model.file.fileDate desc ");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setParameterList("locId", districtIds);

			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equals(""))
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
				
							
			return queryObject.list(); 
}
	public List<Object[]> getAllVideosDetailsForDistrict(Long partyId,int firstResult,int maxResult,String queryType,long stateId ,List<Long> districtIds){
		   
	     StringBuilder query = new StringBuilder();
				query.append("select model.file,model.fileGallaryId,model.file.fileDate,fs.source.source,model2.party.partyFlag,model2.party.partyId from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
					" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
					" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate  ");
				
				if(queryType.equalsIgnoreCase("Public"))
			   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
			  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			  query.append(" and model.file.locationValue in (:locId)  and model.file.regionScopes.regionScopesId = :locationValue ");
           //query.append("or  (model.file.regionScopes.regionScopesId = :constituencyScopeId and " );
			//query.append(" model.file.locationValue = :constituencyVal) " );
			query.append("group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setParameterList("locId", districtIds);

			queryObject.setString("type", IConstants.VIDEO_GALLARY);
			queryObject.setString("isDelete", "false");
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(1);
			queryObject.setMaxResults(3);
			return queryObject.list(); 
}
	public int getCountOfNewsFilesForDistrict(Long partyId,int firstResult,int maxResult,String queryType,long stateId ,List<Long> districtIds){
		   
	     StringBuilder query = new StringBuilder();
				query.append("select count(model.file.fileId) from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
					" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
					" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete ");
				
			if(queryType.equalsIgnoreCase("Public"))
			   query.append(" and model.isPrivate = :isPrivate and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
			  query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			  query.append(" and model.file.locationValue in (:locId)  and model.file.regionScopes.regionScopesId = :locationValue ");
          //query.append("or  (model.file.regionScopes.regionScopesId = :constituencyScopeId and " );
			//query.append(" model.file.locationValue = :constituencyVal) " );
			query.append(" group by model2.party.partyId ");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setParameterList("locId", districtIds);

			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equalsIgnoreCase(""))
			queryObject.setString("isPrivate", "false");
							
			return ((Long)queryObject.uniqueResult()).intValue(); 
}



	public List<Object[]> getGallariesByPartyIdForState(Long partyId,String contentType,int firstResult,int maxResult)
	{
		Object[] params = {partyId,contentType};
		Query queryObject = getSession().createQuery("select model.gallery.gallaryId,model.gallery.name from PartyGallery model where model.party.partyId = ? and model.gallery.contentType.contentType = ? and model.isDelete = 'false' and model.isPrivate = 'false' order by model.updatedDate desc");
		queryObject.setLong(0, partyId);
		queryObject.setString(1, contentType);
		queryObject.setFirstResult(firstResult);
		queryObject.setMaxResults(maxResult);
		
		return queryObject.list();
		
	}
	
	public List<Object[]> getAllNewsDetailsForState(Long partyId,int firstResult,int maxResult,String queryType,long stateId , long scopeId){
		   
	     StringBuilder query = new StringBuilder();
			query.append("select model.file,model.fileGallaryId,model.file.fileDate,fs.source.source,model2.party.partyFlag,model2.party.partyId  from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
				" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete ");
				
			if(queryType.equalsIgnoreCase("Public"))
					query.append("  and model.isPrivate = :isPrivate and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
					query.append("  and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			query.append("group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc ");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setLong("locId", scopeId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equals(""))
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);									
			return queryObject.list(); 
}
	public List<Object[]> getAllVideosDetailsForState(Long partyId,int firstResult,int maxResult,String queryType,long stateId , long scopeId){
		   
	     StringBuilder query = new StringBuilder();
			query.append("select model.file,model.fileGallaryId,model.file.fileDate,fs.source.source,model2.party.partyFlag,model2.party.partyId  from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
				" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate  ");
				if(queryType.equalsIgnoreCase("Public"))
			   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
			  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			query.append("group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setLong("locId", scopeId);
			queryObject.setString("type", IConstants.VIDEO_GALLARY);
			queryObject.setString("isDelete", "false");
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(1);
			queryObject.setMaxResults(3);									
			return queryObject.list(); 
}
	public int getCountOfNewsFiles(Long partyId,int firstResult,int maxResult,String queryType,long stateId , long scopeId){
		 StringBuilder query = new StringBuilder();
			query.append("select count(distinct model.file.fileId) from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
				" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete  ");
				query.append(" ");
				
			if(queryType.equalsIgnoreCase("Public"))
				query.append(" and model.isPrivate = :isPrivate and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
						
			else if(queryType.equalsIgnoreCase("Private"))
				query.append("  and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			query.append(" group by model2.party.partyId ");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("locationValue", stateId);
			queryObject.setLong("locId", scopeId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equals(""))
			queryObject.setString("isPrivate", "false");
			
		return ((Long)queryObject.uniqueResult()).intValue();
	}
	public List<Object[]> getCategoryIdsForParty(Long partyId,int firstResult,int maxResult,String queryType){
		   
	     StringBuilder query = new StringBuilder();
			query.append("select distinct model.file.category.categoryId,model.file.category.categoryType,model2.party.partyId  from FileGallary model , PartyGallery model2 , FileSourceLanguage fs  where "+
				" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId  and  model2.party.partyId = :partyId "+
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete ");
				
			if(queryType.equalsIgnoreCase("Public"))
				query.append(" and model.isPrivate = :isPrivate and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
				query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			query.append("group by model.file.category.categoryId order by model.file.fileDate desc, model.updateddate desc");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setLong("partyId", partyId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equalsIgnoreCase(""))
			queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);									
			return queryObject.list(); 
}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCategories(Long partyId,String queryType)
	{
	  StringBuilder str = new StringBuilder();
	  str.append("select model.file.category.categoryId,model.file.category.categoryType,count(distinct model.gallary.gallaryId) from " +
	  		" FileGallary model, PartyGallery model2 where model2.gallery.gallaryId=model.gallary.gallaryId and model2.party.partyId = :partyId " +
	  		" and model2.gallery.contentType.contentType= :type and model.isDelete = 'false' and model2.isDelete = 'false' " +
	  		" and model.gallary.isDelete = 'false' and model2.gallery.isDelete = 'false' ");
	  
	  str.append(" and model.isPrivate = 'false' and model2.isPrivate = 'false' and model.gallary.isPrivate = 'false' and model2.gallery.isPrivate = 'false' ");
	  str.append(" group by model.file.category.categoryId order by model.file.fileDate desc, model.updateddate desc");
		
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("partyId", partyId);
	  query.setParameter("type", IConstants.NEWS_GALLARY);
	  
	  return query.list();
	  
	}
	
	//	List<Object[]> list = partyGalleryDAO.getGalleriesForCategories(872l, 0, 30, "public", 3l);

	public List<Object[]> getGalleriesForCategories(long partyId,int firstResult,int maxResult,String queryType,Long catId){
		   
	     StringBuilder query = new StringBuilder();
			query.append("select  model.gallary.gallaryId ,model.gallary.name,model.gallary.description,model.gallary.createdDate,model.gallary.updateddate,SUM(CASE  WHEN  model.file.category.categoryId = :categoryId and model2.party.partyId = :partyId THEN 1  ELSE 0 END ) from FileGallary model , PartyGallery model2   where "+
				" model2.gallery.gallaryId=model.gallary.gallaryId   and  model2.party.partyId = :partyId "+
				" and model2.gallery.contentType.contentType= :type  and model.file.category.categoryId = :categoryId  and  model.isDelete = :isDelete ");
			if(queryType.equalsIgnoreCase("Public"))
				query.append(" and model.isPrivate = :isPrivate  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equalsIgnoreCase("Private"))
				query.append(" and model.isPrivate = :isPrivate  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true' ) ) ");
			query.append(" group by  model.gallary.gallaryId order by model.file.fileDate desc, model.updateddate desc ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setLong("partyId", partyId);
			queryObject.setLong("categoryId", catId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equalsIgnoreCase(""))
				queryObject.setString("isPrivate", "false");
			queryObject.setFirstResult(firstResult*2);
			queryObject.setMaxResults(maxResult);									
			return queryObject.list(); 
	}
		
		@SuppressWarnings("unchecked")
		public List<Long> getGalleryIdsForSelectedParty(Long partyId)
		{
			Query query = getSession().createQuery("select model.gallery.gallaryId from PartyGallery model " +
					" where model.party.partyId = :partyId and model.gallery.contentType.contentTypeId = 4");
			
			query.setParameter("partyId", partyId);
			return query.list();
		}
		
		 public List<Object[]> getAllVideosOfParty(Long partyId,int firstResult,int maxResult,String queryType){

			 StringBuilder query = new StringBuilder();
			 query.append("select model2.gallery.gallaryId, model2.gallery.name, model2.gallery.description,fps.filePath,model2.gallery.createdDate from FileGallary model , PartyGallery model2 , FileSourceLanguage fs,FilePaths fps where "+
			 " model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId and model2.party.partyId = :partyId "+
			 " and model2.gallery.contentType.contentType= :type and model.isDelete = :isDelete and fs.fileSourceLanguageId=fps.fileSourceLanguage.fileSourceLanguageId ");
			 
			 if(queryType.equalsIgnoreCase("Public"))
			 query.append(" and model.isPrivate = :isPrivate and model.gallary.isPrivate='false' and model.isPrivate ='false' ");

			 if(queryType.equalsIgnoreCase("Private"))
			 query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			 //query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			 query.append(" group by model2.gallery.gallaryId order by model2.gallery.updateddate desc ");
			 Query queryObject = getSession().createQuery(query.toString());

			 queryObject.setLong("partyId", partyId);
			 queryObject.setString("type", IConstants.VIDEO_GALLARY);
			 queryObject.setString("isDelete", "false");
			 if(!queryType.equalsIgnoreCase(""))
				 queryObject.setString("isPrivate", "false");
			 queryObject.setFirstResult(firstResult);
			 queryObject.setMaxResults(maxResult);


			 return queryObject.list();
		 }
		 
		 public int getAllVideosOfPartyCount(Long partyId,int firstResult,int maxResult,String queryType){

			 StringBuilder query = new StringBuilder();
			 query.append("select count(distinct model.gallary.gallaryId) " +
			 		"  from FileGallary model , PartyGallery model2 , FileSourceLanguage fs where "+
			 " model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId and model2.party.partyId = :partyId "+
			 " and model2.gallery.contentType.contentType= :type and model.isDelete = :isDelete ");
			 
			 if(queryType.equalsIgnoreCase("Public"))
			 query.append(" and model.isPrivate = :isPrivate and model.gallary.isPrivate='false' and model.isPrivate ='false' ");

			 if(queryType.equalsIgnoreCase("Private"))
			 query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			 //query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			 query.append(" group by model2.party.partyId order by model2.gallery.updateddate desc ");
			 Query queryObject = getSession().createQuery(query.toString());

			 queryObject.setLong("partyId", partyId);
			 queryObject.setString("type", IConstants.VIDEO_GALLARY);
			 queryObject.setString("isDelete", "false");
			 if(!queryType.equalsIgnoreCase(""))
				 queryObject.setString("isPrivate", "false");
			 

			 return ((Long)queryObject.uniqueResult()).intValue();
		 }
		 
		 public List<Object[]> getNewsOfCandidate(Long candidateId,int firstResult,int maxResult,String queryType)
			{
				StringBuilder query = new StringBuilder();
				query.append("select model2.filePath,model.fileGallary.fileGallaryId,model.fileGallary.file.fileTitle,model.fileGallary.file.fileDescription,model1.source.source " +
						" from CandidateRealatedNews model,FileSourceLanguage model1,FilePaths model2 where model.candidate.candidateId = :candidateId "+
						"  and  model.fileGallary.gallary.isDelete='false' and model.fileGallary.gallary.contentType.contentType= :type   and model.fileGallary.isDelete = :isDelete " +
						" and  model.fileGallary.file.fileId=model1.file.fileId and model1.fileSourceLanguageId=model2.fileSourceLanguage.fileSourceLanguageId");
				
				if(queryType.equals("Public"))
				   query.append("  and  model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='false'  ");
				
				if(queryType.equals("Private"))
				  query.append("  and ( (model.fileGallary.gallary.isPrivate='true') or(model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='true') ) ");
				
				query.append(" order by model.fileGallary.file.fileDate desc ");
				Query queryObject = getSession().createQuery(query.toString());
				
				queryObject.setLong("candidateId", candidateId);
				queryObject.setString("type", IConstants.NEWS_GALLARY);
				queryObject.setString("isDelete", "false");
				queryObject.setFirstResult(firstResult);
				queryObject.setMaxResults(maxResult);
					
								
				return queryObject.list(); 
			}
		 
		 public int getCountOfNewsOfCandidate(Long candidateId,int firstResult,int maxResult,String queryType)
			{
				StringBuilder query = new StringBuilder();
				query.append("select count(model.fileGallary.file.fileId) " +
						" from CandidateRealatedNews model,FileSourceLanguage model1,FilePaths model2 where model.candidate.candidateId = :candidateId "+
						"  and  model.fileGallary.gallary.isDelete='false' and model.fileGallary.gallary.contentType.contentType= :type   and model.fileGallary.isDelete = :isDelete " +
						" and  model.fileGallary.file.fileId=model1.file.fileId and model1.fileSourceLanguageId=model2.fileSourceLanguage.fileSourceLanguageId");
				
				if(queryType.equals("Public"))
				   query.append("  and  model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='false'  ");
				
				if(queryType.equals("Private"))
				  query.append("  and ( (model.fileGallary.gallary.isPrivate='true') or(model.fileGallary.gallary.isPrivate='false' and model.fileGallary.isPrivate ='true') ) ");
				
				query.append(" group by model.candidate.candidateId ");
				Query queryObject = getSession().createQuery(query.toString());
				
				queryObject.setLong("candidateId", candidateId);
				queryObject.setString("type", IConstants.NEWS_GALLARY);
				queryObject.setString("isDelete", "false");
												
				return ((Long)queryObject.uniqueResult()).intValue(); 
			}
		 public int getResponseNewsCountOfCandidate(Long candidateId,String queryType,Long fileGallaryId)
			{
				StringBuilder query = new StringBuilder();
				query.append("select count(model1.fileGallary.fileGallaryId)  " +
						" from CandidateRealatedNews model,CandidateNewsResponse model1 where model1.fileGallary.fileGallaryId=model.fileGallary.fileGallaryId and model.candidate.candidateId = :candidateId "+
						" and model1.fileGallary.fileGallaryId=:fileGallaryId");
				
				query.append(" group by model1.fileGallary.fileGallaryId ");
				Query queryObject = getSession().createQuery(query.toString());
				
				queryObject.setLong("candidateId", candidateId);
				queryObject.setLong("fileGallaryId", fileGallaryId);
										
				Long res=(Long)queryObject.uniqueResult();
				
				if(res!=null)
					return res.intValue();
				
				else
					return 0;
			}
		 public List<Object[]> getLatestNewsResponses(){
			 Query queryObj=getSession().createQuery("select model.responseFileGallary.fileGallaryId,model.responseFileGallary.file.fileTitle,model.responseFileGallary.file.fileDate,fs.source.source " +
			 		" from CandidateNewsResponse model,FileSourceLanguage fs  where model.responseFileGallary.isPrivate='false' and " +
			 		" model.responseFileGallary.isDelete='false' and model.responseFileGallary.gallary.isPrivate='false' and " +
			 		" model.responseFileGallary.gallary.isDelete='false' and model.responseFileGallary.file.fileId = fs.file.fileId " +
			 		" group by model.responseFileGallary.file.fileId order by model.responseFileGallary.updateddate desc"); 
			 queryObj.setFirstResult(0);
			 queryObj.setMaxResults(5);
			 
			 return queryObj.list();
			 
		 }
		 public List<FileGallary> getLatestNewsResPonses(Integer startIndex, Integer maxIndex)
		 {
			 Query queryObj=getSession().createQuery("select model.responseFileGallary" +
			 		" from CandidateNewsResponse model,FileSourceLanguage fs  where model.responseFileGallary.isPrivate='false' and " +
				 		" model.responseFileGallary.isDelete='false' and model.responseFileGallary.gallary.isPrivate='false' and " +
				 		" model.responseFileGallary.gallary.isDelete='false' and model.responseFileGallary.file.fileId = fs.file.fileId order by model.responseFileGallary.file.fileDate desc, model.responseFileGallary.updateddate desc");
			 
			 	if(startIndex != null)
				 queryObj.setFirstResult(startIndex);
				if(maxIndex != null)
					queryObj.setMaxResults(maxIndex);
				
				return queryObj.list(); 
		 }
		 
		@SuppressWarnings("unchecked")
		public List<Object[]> getCandidateRelatedGallaries(Long candidateId,Long partyId,Date fromDate, Date toDate,String newsType)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append(" select distinct model.gallery.gallaryId,model.gallery.name from PartyGallery model,CandidateRealatedNews model2  where model.gallery.gallaryId = model2.fileGallary.gallary.gallaryId ");
			 str.append(" and model.party.partyId =:partyId and model.gallery.contentType.contentType =:contentType and model2.candidate.candidateId =:candidateId ");
			 str.append(" and model.gallery.isDelete = 'false' ");
			 if(!newsType.equals(""))
				str.append(" and model.gallery.isPrivate = 'false' "); 
			 
			 if(fromDate != null)
				 str.append(" and model2.fileGallary.file.fileDate >= :fromDate ");
			 if(toDate != null)
				 str.append(" and model2.fileGallary.file.fileDate <= :toDate ");
			 
			 str.append(" order by model.gallery.name ");
			 Query query = getSession().createQuery(str.toString());
			 
			 query.setParameter("candidateId", candidateId);
			 query.setParameter("partyId", partyId);
			 if(fromDate != null)
			  query.setParameter("fromDate", fromDate);
			 if(toDate != null)
			  query.setParameter("toDate", toDate);
			 query.setParameter("contentType", IConstants.NEWS_GALLARY);
			 
			 return query.list();
		 }
		
		
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getCandidateRelatedCategories(Long candidateId,Long partyId,Date fromDate,Date toDate)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select distinct model2.fileGallary.file.category.categoryId, model2.fileGallary.file.category.categoryType from PartyGallery model, CandidateRealatedNews model2 where ");
			str.append(" model.gallery.gallaryId = model2.fileGallary.gallary.gallaryId and model2.fileGallary.isDelete = 'false' and model2.fileGallary.gallary.isDelete = 'false' and ");
			str.append(" model.party.partyId =:partyId and model2.candidate.candidateId =:candidateId and model.gallery.contentType.contentType =:contentType ");
			if(fromDate != null)
			 str.append(" and model2.fileGallary.file.fileDate >= :fromDate ");
			if(toDate != null)
			 str.append(" and model2.fileGallary.file.fileDate <= :toDate ");
			
			str.append(" order by model2.fileGallary.file.category.categoryType ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameter("candidateId", candidateId);
			query.setParameter("partyId", partyId);
			query.setParameter("contentType", IConstants.NEWS_GALLARY);
			if(fromDate != null)
			 query.setParameter("fromDate", fromDate);
			if(toDate != null)
			 query.setParameter("toDate", toDate);
			
			return query.list();
		}

	}
