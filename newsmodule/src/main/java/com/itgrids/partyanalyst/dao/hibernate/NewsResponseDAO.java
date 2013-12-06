package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.NewsResponse;


public class NewsResponseDAO extends GenericDaoHibernate<NewsResponse,Long> implements INewsResponseDAO {

	public NewsResponseDAO(){
		super(NewsResponse.class);
	}
	
	public List<Long> getCandidateNewsResponseFileIdsByFileID(Long fileId)
	{
		
		return getHibernateTemplate().find("select distinct model.candidatePartyFile.candidatePartyFileId from NewsResponse model where model.file.fileId = ? and  model.file.isDeleted != 'Y' and  model.candidatePartyFile.file.isDeleted != 'Y'",fileId);
	}
	public List<Long> getCandidateNewsResponseFileIds(Long fileId)
	{
		
		return getHibernateTemplate().find("select distinct(model.candidatePartyFile.file.fileId) from NewsResponse model where model.file.fileId = ?",fileId);
	}
	 public List<Object[]> getLatestNewsResponses(){
		 Query queryObj=getSession().createQuery("select distinct(model.file.fileId),model.file.fileTitle,model.file.fileDate,model.file.font.fontId" +
		 		" from NewsResponse model where (model.candidatePartyFile.file.isPrivate !='Y' or model.candidatePartyFile.file.isPrivate is null)" +
		 		" and (model.file.isDeleted !='Y' or model.file.isDeleted is null) and (model.candidatePartyFile.file.isDeleted !='Y' or model.candidatePartyFile.file.isDeleted is null)" +
		 		" group by model.file.fileId order by  model.file.fileId desc"); 
		 queryObj.setFirstResult(0);
		 queryObj.setMaxResults(5);
		 
		 return queryObj.list();
		 
	 }
	 
	 public List<File> getLatestFileDetails(int startIndex,int maxIndex)
	 {
		 Query query = getSession().createQuery("select distinct model.file from NewsResponse model");
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 
	 public List<File> getLatestFileDetailsForBtDates(int startIndex,int maxIndex,Date fromDate,Date toDate)
	 {
		 Query query = getSession().createQuery("select distinct model.file from NewsResponse model " +
		 		" where date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate");
		 query.setParameter("fromDate", fromDate);
		 query.setParameter("toDate", toDate);
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 public Long getCountForNesResponce()
	 {
		 Query query = getSession().createQuery("select count(distinct model.file.fileId) from NewsResponse model ");
		 
		 
		 return (Long)query.uniqueResult();
	 }
	 
	 public List<Long> getNewsResponceIds(int startIndex,int maxIndex)
	 {
		 Query query = getSession().createQuery("select distinct model.file.fileId from NewsResponse model");
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 
	 public List<Long> getNewsResponceIdsForBtDates(int startIndex,int maxIndex,Date fromDate,Date toDate)
	 {
		 Query query = getSession().createQuery("select distinct model.file.fileId from NewsResponse model " +
		 		" where date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate ");
		 query.setParameter("fromDate", fromDate);
		 query.setParameter("toDate", toDate);
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 
	 public List<Object[]> getResponceCountForFiles(List<Long> fileIds)
	 {
		 Query query = getSession().createQuery("select  model.file.fileId,count(model.file.fileId) from NewsResponse model where model.file.fileId in (:fileIds) and model.file.isDeleted != 'Y' group by model.file.fileId");
		 query.setParameterList("fileIds", fileIds);
		 
		 return  query.list();
		 
	 }
	 
	
	
	
}
