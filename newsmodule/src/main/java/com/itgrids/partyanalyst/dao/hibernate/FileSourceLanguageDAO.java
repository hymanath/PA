package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.model.FileSourceLanguage;

public class FileSourceLanguageDAO extends GenericDaoHibernate<FileSourceLanguage,Long> implements IFileSourceLanguageDAO {

	 public FileSourceLanguageDAO(){
		 super(FileSourceLanguage.class);
	 }

	 @SuppressWarnings("unchecked")
	public List<FileSourceLanguage> getFileSourceLanguageObject(Long fileId , Long sourceId ,Long languageId)
	 {
		 Object[] params = {fileId,sourceId , languageId};
		return getHibernateTemplate().find("select model from FileSourceLanguage model where model.file.fileId = ? and model.source.sourceId = ? " +
		 		" and model.language.languageId =?",params );
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	public  List<Object> getFileLanguage(Long fileId)
	 {
		 return getHibernateTemplate().find("select model.language.language from FileSourceLanguage model where model.file.fileId=?",fileId);
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<Long> getFileSourceIdsBasedOnFile(Long fileId)
	 {
		 return getHibernateTemplate().find("select model.fileSourceLanguageId from FileSourceLanguage model " +
		 		" where model.file.fileId = ? ",fileId);
	 }
	 
	 
	 public List<Object[]> getSourceDetailsByFileIds(Set<Long> fileIds)
	 {
		 Query query = getSession().createQuery("select distinct model.file.fileId,model.source.source from FileSourceLanguage model where model.file.fileId in(:fileIds) ");
		 
		 query.setParameterList("fileIds", fileIds);
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getNewsForReport(List<Long> fileIds){
         Query query = getSession().createQuery("select distinct model.file.fileId,model.file.title,model.source.source from FileSourceLanguage model where model.file.fileId in(:fileIds) ");
		 
		 query.setParameterList("fileIds", fileIds);
		 
		 return query.list();
	 }
	 
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileSourceType(List<Long> fileIds)
	{
		Query query = getSession().createQuery("select model.file.fileId ," +
				" model.source.source from FileSourceLanguage model where model.file.fileId in (:fileIds)");
		query.setParameterList("fileIds", fileIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getEditionPartNoDetailsByFileIds(Set<Long> fileIds){
				
		Query query = getSession().createQuery("select distinct model.fileSourceLanguage.file.fileId," +
				"model.fileSourceLanguage.source.sourceId,model.fileSourceLanguage.source.source,model.edition,model.pageNo from FilePaths model where " +
				"model.fileSourceLanguage.file.fileId in (:fileIds) ");
		query.setParameterList("fileIds", fileIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateNamesByFileIds(Long fileId){
		Query query = getSession().createQuery("select model.sourceCandidate.candidateId,model.sourceCandidate.lastname from CandidatePartyFile model where " +
				"model.file.fileId = :fileId ");
		query.setParameter("fileId", fileId);
		return query.list();
	}
	
}
