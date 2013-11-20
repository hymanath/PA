package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.model.NewsResponse;


public class NewsResponseDAO extends GenericDaoHibernate<NewsResponse,Long> implements INewsResponseDAO {

	public NewsResponseDAO(){
		super(NewsResponse.class);
	}
	
	public List<Long> getCandidateNewsResponseFileIdsByFileID(Long fileId)
	{
		
		return getHibernateTemplate().find("select distinct(model.candidatePartyFile.candidatePartyFileId) from NewsResponse model where model.file.fileId = ?",fileId);
	}
	public List<Long> getCandidateNewsResponseFileIds(Long fileId)
	{
		
		return getHibernateTemplate().find("select distinct(model.candidatePartyFile.file.fileId) from NewsResponse model where model.file.fileId = ?",fileId);
	}
	 public List<Object[]> getLatestNewsResponses(){
		 Query queryObj=getSession().createQuery("select distinct(model.file.fileId),model.file.fileTitle,model.file.fileDate,model.file.font.fontId" +
		 		" from NewsResponse model where (model.candidatePartyFile.file.isPrivate !='Y') or (model.candidatePartyFile.file.isPrivate is null)" +
		 		" and (model.file.isDeleted !='Y') or (model.file.isDeleted is null)" +
		 		" group by model.file.fileId order by  model.file.fileId desc"); 
		 queryObj.setFirstResult(0);
		 queryObj.setMaxResults(5);
		 
		 return queryObj.list();
		 
	 }
	
	
}
