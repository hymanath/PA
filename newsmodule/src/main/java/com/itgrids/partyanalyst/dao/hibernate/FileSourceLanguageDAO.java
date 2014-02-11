package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
	public List<Object[]> getFileSourceByFileIds(Set<Long> fileIds)
	{
		Query query = getSession().createQuery("select fs.file.fileId,fs.source.sourceId,fs.source.source from " +
				"FileSourceLanguage fs where fs.file.fileId in (:fileIds) and fs.file.isDeleted != 'Y'" );
		query.setParameterList("fileIds", fileIds);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateDetailsByFileIds(Set<Long> fileIds){
		Query query = getSession().createQuery("select model.file.fileId,model.sourceCandidate.candidateId," +
				"model.destinationCandidate.candidateId from CandidatePartyFile model " +
				"where model.file.fileId in (:fileIds) and model.file.isDeleted != 'Y' and " +
				"(model.sourceCandidate.candidateId is not null or model.destinationCandidate.candidateId is not null)");
		
		query.setParameterList("fileIds", fileIds);
		return query.list();
	}
	
	public void deleteFileSourceLanguage(Long fileSourceLangId){
		Query query = getSession().createQuery(" delete from FileSourceLanguage model where model.fileSourceLanguageId =:fileSourceLangId");
		query.setParameter("fileSourceLangId", fileSourceLangId);
		query.executeUpdate();
	}
	
	public List<Object[]> getDistrictWiseUploadedNewsCount(Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct fsl.fileSourceLanguageId),district.districtId,district.districtName from FileSourceLanguage fsl,District district where fsl.file.isDeleted != 'Y' " +
				" and fsl.file.user.accessType ='DISTRICT' and fsl.file.user.userAccessType ='SubUser' and fsl.file.user.accessValue = district.districtId ");
		if(fromDate != null)
		  queryStr.append(" and date(fsl.file.createdDate) >= :fromDate ");
		if(toDate != null)
		  queryStr.append(" and date(fsl.file.createdDate) <= :toDate ");
		
		queryStr.append(" group by district.districtId order by district.districtId asc");
		Query query = getSession().createQuery(queryStr.toString());
		    if(fromDate != null)
		    	query.setDate("fromDate", fromDate);
			if(toDate != null)
				query.setDate("toDate", toDate);
			
		return query.list();
	}
}
