package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
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
}
