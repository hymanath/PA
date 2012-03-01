package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;


public class PartyGalleryDAO extends GenericDaoHibernate<PartyGallery,Long> implements IPartyGalleryDAO{
	
	public PartyGalleryDAO()
	{
		super(PartyGallery.class);
	}

	public List<Object[]> getFirstFourNewsToDisplay(Long partyId,int firstResult,int maxResult,String queryType)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.file.fileDate,model.gallary.candidate.candidateId  " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and  model.gallary.isDelete='false' and model.gallary.contentType.contentType= :type   and model.isDelete = :isDelete   ");
		
		if(queryType.equals("Public"))
		   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
		
		if(queryType.equals("Private"))
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
				"model.gallery.contentType.contentType= :type  and model.gallery.isDelete = :isDelete and model.gallery.isPrivate = :isPrivate order by model.gallery.createdDate desc");
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
		
		if(queryType.equals("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equals("Private"))
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
				" and model2.gallery.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate)");
		
		query.setLong("partyId", partyId);
		query.setString("type", IConstants.NEWS_GALLARY);
		
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		return query.list(); 
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription,  " +
				" model.gallary.name  from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
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
}
