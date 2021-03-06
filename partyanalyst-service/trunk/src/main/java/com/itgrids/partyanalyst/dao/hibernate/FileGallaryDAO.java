package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.dto.PdfGenerationVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.utils.IConstants;
public class FileGallaryDAO extends GenericDaoHibernate<FileGallary, Long> implements IFileGallaryDAO{
	
	public FileGallaryDAO(){
		super(FileGallary.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getStartingRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file  from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? " +
				" order by model.file.fileDate desc");
				query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");
		query.setMaxResults(1);
		
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file,model.gallary.name,model.fileGallaryId" +
				"  from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllRecordCountInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select count(*) from FilePaths model where model.fileSourceLanguage.file.fileId in(" +
				" select distinct model1.file.fileId from FileGallary model1 where model1.gallary.gallaryId = ? and model1.isDelete = ? and model1.isPrivate = ? )");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");				
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsRecordsBySearchCriteria(FileVO fileVO,String type){
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.regionScopes.scope,model.file.regionScopes.regionScopesId ,model.file.locationValue " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate ");
		if(fileVO.getLocationScope()!= null)
			query.append("  and model.file.regionScopes.regionScopesId = :locationScope");
		if(fileVO.getLocation()!= null)
			query.append("  and model.file.locationValue = :location");	
		if(fileVO.getKeywords()!= null && fileVO.getKeywords().trim().length()>0)
			query.append("  and model.file.keywords like '%"+fileVO.getKeywords()+"%' ");	
		query.append("  order by model.file.fileId asc");
		
        Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("candidateId", fileVO.getCandidateId());
		queryObject.setString("type", type);
		queryObject.setString("isDelete", "false");
		queryObject.setString("isPrivate", "false");
		if(fileVO.getLocationScope()!= null)
		   queryObject.setLong("locationScope", fileVO.getLocationScope());
		if(fileVO.getLocation()!= null)
		   queryObject.setLong("location", fileVO.getLocation());
		 return	queryObject.list();
	}
	
	public List<Object[]> getFirstFourNewsToDisplay(Long candidateId,int firstResult,int maxResult,String queryType)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.file.fileDate,model.gallary.candidate.candidateId , model.file.newsImportance.newsImportanceId , model.file.newsImportance.importance  " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and  model.gallary.isDelete='false' and model.gallary.contentType.contentType= :type   and model.isDelete = :isDelete   ");
		
		if(queryType.equals("Public"))
		   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
		
		if(queryType.equals("Private"))
		  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		query.append(" order by model.file.fileDate desc ");
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("candidateId", candidateId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		queryObject.setString("isDelete", "false");
		queryObject.setFirstResult(firstResult);
		queryObject.setMaxResults(maxResult);
			
						
		return queryObject.list(); 
	}
	public List<Object[]> getAllNewsToDisplay(Long candidateId)
	{
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.createdDate,model.gallary.candidate.candidateId  " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate  order by model.createdDate desc ");
		
		query.setLong("candidateId", candidateId);
		query.setString("type", IConstants.NEWS_GALLARY);
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		
		return query.list(); 
	}
	
	public List<Object[]> getFirstThreeImagesToDisplay(Long candidateId,int firstResult,int maxResult)
	{
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.source ,model.createdDate " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate  order by model.file.fileId asc ");
		
		query.setLong("candidateId", candidateId);
		query.setString("type", IConstants.PHOTO_GALLARY);
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
			
						
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getCandidateLatestVideos(Long candidateId,Integer startIndex, Integer maxResults)
	{
		Query query = getSession().createQuery("select model.file from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				" and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate order by model.file.fileDate desc");
		
		query.setLong("candidateId", candidateId);
		query.setString("type", IConstants.VIDEO_GALLARY);
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list(); 
	}
	public List<Long> getNewsCountByScope(Long candidateId,Long scopeType,String queryType)
	{
		StringBuilder query = new StringBuilder();
		
		query.append("select count(*) from FileGallary model where model.gallary.candidate.candidateId =:candidateId and model.gallary.contentType.contentType =:type and model.gallary.isDelete = 'false' " +
				"  and model.isDelete = 'false' ");
		
		if(scopeType!=null)
			query.append("   and model.file.regionScopes.regionScopesId =:scopeType   ");
			
		if(queryType.equals("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equals("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("candidateId", candidateId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		if(scopeType!=null)
		queryObject.setLong("scopeType", scopeType);
		
		return queryObject.list(); 
	}
	public List<Long> getTotalIndividualSources(Long candidateId,String queryType,String sourceStr , String languageStr,String categoryStr,String newsImportance )
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(*) from FileGallary model where model.gallary.candidate.candidateId =:candidateId and model.gallary.contentType.contentType =:type and model.gallary.isDelete = 'false' and  model.isDelete = 'false' ");
		if(sourceStr!=null)
			query.append("   and model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.source.source = :spScope) ");
		if(languageStr!=null)
			query.append("     and model.file.fileId in(select distinct model2.file.fileId from FileSourceLanguage model2 where model2.language.language = :spScopeLang) ");
		if(categoryStr!=null)
			query.append("   and model.file.category.categoryType =:categoryStr");
		if(newsImportance!=null)
			query.append("   and model.file.newsImportance.importance =:newsImportance");

		if(queryType.equals("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equals("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		if(sourceStr!=null)
			queryObject.setString("spScope", sourceStr);
		if(languageStr!=null)
		    queryObject.setString("spScopeLang", languageStr);
		if(categoryStr!=null)
			queryObject.setString("categoryStr", categoryStr);
		if(newsImportance!=null)
			queryObject.setString("newsImportance", newsImportance);
		
		queryObject.setLong("candidateId", candidateId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		return queryObject.list();
	}
	public List<Object[]> getNewsByScope(Long candidateId,Long scopeType,int startIndex,int maxResults,String queryType , String sourceStr , String languageStr,String categoryStr,String newsImportance)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.file,model.fileGallaryId,model.createdDate " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = 'false'  " +
				" and model.gallary.isDelete = 'false'  ");

		if(scopeType!=null)
			query.append("   and model.file.regionScopes.regionScopesId =:scopeType   ");
		
		if(sourceStr!=null)
			query.append("   and model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.source.source = :spScope) ");
		
		if(languageStr!=null)
			query.append("   and model.file.fileId in(select distinct model2.file.fileId from FileSourceLanguage model2 where model2.language.language = :spScopeLang) ");
		
		if(categoryStr!=null)
			query.append("   and model.file.category.categoryType =:categoryStr");
		
		if(newsImportance!=null)
			query.append("   and model.file.newsImportance.importance =:newsImportance");
		
		if(queryType.equals("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equals("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		query.append("   order by model.createdDate desc   ");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("candidateId", candidateId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		if(scopeType!=null)
		queryObject.setLong("scopeType", scopeType);
		
		if(sourceStr!=null)
		queryObject.setString("spScope", sourceStr);
		
		if(languageStr!=null)
		queryObject.setString("spScopeLang", languageStr);
		
		if(categoryStr!=null)
			queryObject.setString("categoryStr", categoryStr);
		
		if(newsImportance!=null)
			queryObject.setString("newsImportance", newsImportance);
		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResults);	
						
		return queryObject.list(); 
	}
	
	public List<Object[]> getOtherNews(Long candidateId,int startIndex,int maxResults,String queryType)
	{
		StringBuilder query = new StringBuilder();
		query.append("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.createdDate,model.gallary.candidate.candidateId  " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = 'false'  " +
				" and model.gallary.isDelete = 'false' and model.file.regionScopes.regionScopesId is null ");
		
		if(queryType.equals("Public"))
			query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(queryType.equals("Private"))
			query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
		
		query.append("   order by model.createdDate desc   ");
		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("candidateId", candidateId);
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(maxResults);	
						
		return queryObject.list(); 
	}
	
	public Integer deleteFilesAndPhotos(Long fileId , Long gallaryId)
	{
		StringBuilder query = new StringBuilder();
		query.append("update FileGallary model set model.isDelete = 'true' where model.file.fileId = ? and model.gallary.gallaryId = ?");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, fileId);
		queryObject.setParameter(1, gallaryId);
		return queryObject.executeUpdate();	
	}

	public List<Object[]> getPhotoAndFileDescForUpdate(Long gallaryId , Long fileId)
	{
		Query query = getSession().createQuery("select model.file.fileId,model.gallary.gallaryId,model.gallary.name,model.file.fileTitle," +
				"model.file.fileDescription,model.file.filePath,model.isPrivate,model.fileGallaryId from FileGallary model  where model.file.fileId=:fileId and  " +
				"model.gallary.gallaryId = :gallaryId");
		   query.setLong("fileId", fileId);
		   query.setLong("gallaryId", gallaryId);			
		   return query.list(); 
		
	}
	 
	@SuppressWarnings("unchecked")
	public List<File> getPartyLatestVideos(Long partyId,Integer startIndex, Integer maxResults)
	{
		Query query = getSession().createQuery("select model.file from FileGallary model where model.gallary.gallaryId in "+
				"(select model2.gallery.gallaryId from PartyGallery model2 where model2.party.partyId = :partyId"+
				" and model2.gallery.contentType.contentType= :type) and model.isDelete = :isDelete and model.isPrivate = :isPrivate order by model.file.fileDate desc");
		
		query.setLong("partyId", partyId);
		query.setString("type", IConstants.VIDEO_GALLARY);
		
		query.setString("isDelete", "false");
		query.setString("isPrivate", "false");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getRecentlyUploadedFiles(Integer startIndex , Integer maxResults , String queryStr){
		
		Query query = getSession().createQuery("from FileGallary model "+queryStr+" and model.gallary.isPrivate = 'false' " +
				" and model.isPrivate = 'false' and model.isDelete = 'false' order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getRecentlyUploadedPhotos(Integer startIndex,Integer maxResults)
	{
		
		Query query = getSession().createQuery("from FileGallary model where model.gallary.contentType.contentType ='Photo Gallary'" +
				" and model.gallary.isPrivate = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' order by model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getRecentlyUploadedFileIds(Integer startIndex , Integer maxResults , String queryStr){
		
		Query query = getSession().createQuery("select distinct model.file.fileId from FileGallary model "+queryStr+" and model.gallary.isPrivate = 'false' " +
				" and model.isPrivate = 'false' and model.isDelete = 'false' order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		return query.list();
	}
	
public List<FileGallary> getRecentlyUploadedNewsFileIds(Integer startIndex , Integer maxResults , String queryStr){
		
		Query query = getSession().createQuery("select  model from FileGallary model "+queryStr+" and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' " +
				" and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId,model.gallary.gallaryId order by date(model.file.fileDate) desc,timestamp(model.updateddate) desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getRecentlyUploadedPhotoIds(Integer startIndex,Integer maxResults)
	{
		
		Query query = getSession().createQuery("select distinct model.file.fileId from FileGallary model where model.gallary.contentType.contentType ='Photo Gallary'" +
				" and model.gallary.isPrivate = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' order by model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();
	}
	/*@SuppressWarnings("unchecked")
	public List<Long> getRecentlyUploadedGallaries(Integer startIndex,Integer maxResults)
	{
		Query query = getSession().createQuery("select distinct model.gallary.gallaryId from FileGallary model where model.gallary.contentType.contentType ='Photo Gallary'" +
				" and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();	
	}*/
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getRecentlyUploadedGallaries(Integer startIndex,Integer maxResults)
	{
		Query query = getSession().createQuery("select  model from FileGallary model where model.gallary.contentType.contentType ='Photo Gallary'" +
				" and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();	
	}
	
	
	/*@SuppressWarnings("unchecked")
	public List<Long> getRecentlyUploadedVedioGallaryIds(Integer startIndex,Integer maxResults,String queryStr2)
	{
	Query query = getSession().createQuery("select distinct model.gallary.gallaryId from FileGallary model "+queryStr2+" and " +
			"model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
	query.setFirstResult(startIndex);
	query.setMaxResults(maxResults);
	return query.list();
	}*/
	@SuppressWarnings("unchecked")
	public List<FileGallary> getRecentlyUploadedVedioGallaryIds(Integer startIndex,Integer maxResults,String queryStr2)
	{
	Query query = getSession().createQuery("select  model from FileGallary model "+queryStr2+" and " +
			"model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
	query.setFirstResult(startIndex);
	query.setMaxResults(maxResults);
	return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getRecentlyUploadedNewsGallaryIds(Integer startIndex , Integer maxResults,String queryStr3)
	{
		Query query = getSession().createQuery("select  model.gallary.gallaryId from FileGallary model "+queryStr3+"  and " +
			"model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
	query.setFirstResult(startIndex);
	query.setMaxResults(maxResults);
	return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<FileGallary> getFileGallaryByFileIdsList(List<Long> fileIdsList)
	{
		Query query = getSession().createQuery("select model from FileGallary model where model.file.fileId in (:fileIdsList) and model.isDelete = 'false' " +
				"and model.isPrivate = 'false' order by model.file.fileDate desc,model.updateddate desc");
		query.setParameterList("fileIdsList",fileIdsList);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<FileGallary> getStartingRecordInGallaries(Long gallaryId)
	{
		Query query = getSession().createQuery("select model from FileGallary  model where model.gallary.gallaryId = ? and model.isDelete = 'false' " +
				"and model.isPrivate = 'false' order by model.file.fileDate desc,model.updateddate desc");
		query.setParameter(0,gallaryId);
		
		return query.list();
		
		
	}
	@SuppressWarnings("unchecked")
	public List<FileGallary> getStartingRecordInNewsGallaries(Long gallaryId)
	{
		Query query = getSession().createQuery("select model from FileGallary  model where model.gallary.gallaryId = ? and model.isDelete = 'false' " +
				"and model.isPrivate = 'false' and model.file.regionScopes.regionScopesId < 4 order by date(model.file.fileDate) desc,timestamp(model.updateddate) desc");
		query.setParameter(0,gallaryId);
		
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getFileGallaryByFileIdsListForNews(List<Long> fileIdsList)
	{
		Query query = getSession().createQuery("select model from FileGallary model where model.file.fileId in (:fileIdsList) and model.isDelete = 'false' " +
				"and model.isPrivate = 'false' and model.file.regionScopes.regionScopesId < 4 order by model.file.fileDate desc,model.updateddate desc");
		query.setParameterList("fileIdsList",fileIdsList);
		return query.list();
	}
	
	public List<FileGallary> getHomePageNewsDetails(Integer startIndex , Integer maxResults ){
		Query query = getSession().createQuery("select distinct model from FileGallary model where model.gallary.contentType.contentType = 'News Gallary' and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' " +
				" and model.isPrivate = 'false' and model.isDelete = 'false'  and model.file.regionScopes.regionScopesId < 4 order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsByGalleryId(List galleryIds){
		 Query query = getSession().createQuery("SELECT model.file.fileId ,model.file.fileName,model.file.filePath," +
		 		"model.file.fileTitle,model.file.fileDescription,model.file.fileType.type,model.file.regionScopes.scope," +
		 		"model.createdDate,model.file.sourceObj.source,model.file.language.language,model.file.regionScopes.regionScopesId ," +
		 		"model.file.locationValue  " +
		 		"FROM FileGallary model WHERE model.gallary.gallaryId in(:galleryIds)");
		 query.setParameterList("galleryIds", galleryIds);
		 //query.setParameter("galleryIds", galleryIds);
		 
		 return query.list();
	}
     @SuppressWarnings("unchecked")
	public List<File> getNewsForRegisterUsers(FileVO fileVO){
		
    	 StringBuilder query = new StringBuilder();
 		query.append("select model.file from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
 		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId");
 		
 		if(fileVO.getLanguegeId() != null)
			query.append(" and model1.language.languageId = :languageId");
 		
 		query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
 				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
 		
 		if(fileVO.getExistingFrom() != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(fileVO.getIdentifiedOn() != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId");
 			
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId");
 			
 		if(fileVO.getLocationScope() != null)
 			query.append(" and model.file.regionScopes.regionScopesId = :locScop");	
 			
 		if(fileVO.getLocation() != null)
			query.append(" and model.file.locationValue =:locScopVal");		
		
		if(fileVO.getFileType().trim().equalsIgnoreCase("Public"))
			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(fileVO.getFileType().trim().equalsIgnoreCase("Private"))
			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
		
		query.append(" order by model.file.fileDate desc   ");
 		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("userId", fileVO.getCandidateId());
		
		queryObject.setString("type", IConstants.NEWS_GALLARY);
		
		if(fileVO.getExistingFrom() != null)
		    queryObject.setDate("fromDate", fileVO.getExistingFrom());
		
		if(fileVO.getIdentifiedOn() != null)
			queryObject.setDate("toDate",fileVO.getIdentifiedOn());
 			
 		if(fileVO.getSourceId() != null)
 			queryObject.setLong("sourceId",fileVO.getSourceId());
 		
 		if(fileVO.getLanguegeId() != null)
 			queryObject.setLong("languageId",fileVO.getLanguegeId());
 		
 		if(fileVO.getCategoryId() != null)
 			queryObject.setLong("categoryId",fileVO.getCategoryId());
 			
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId",fileVO.getNewsImportanceId());
 			
 		if(fileVO.getLocationScope() != null)
 			queryObject.setLong("locScop",fileVO.getLocationScope());	
 			
 		if(fileVO.getLocation() != null)
 			queryObject.setLong("locScopVal",fileVO.getLocation());		
		 
		return queryObject.list();
	}
     
     @SuppressWarnings("unchecked")
 	public List<Object[]> getNewsForRegisterUsers1(FileVO fileVO){
 		
     	 StringBuilder query = new StringBuilder();
  		query.append("select model.fileGallaryId ,model.file,model.isPrivate,model.gallary.gallaryId,model.gallary.name " +
  				"from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
  		if(fileVO.getSourceId() != null)
  			query.append(" and model1.source.sourceId = :sourceId");
  		
  		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId");
  		
  		query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
  		
  		if(fileVO.getExistingFrom() != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(fileVO.getIdentifiedOn() != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileVO.getCategoryId() != null)
  			query.append(" and model.file.category.categoryId = :categoryId");
  			
  		if(fileVO.getNewsImportanceId() != null)
  			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId");
  			
  		if(fileVO.getLocationScope() != null)
  			query.append(" and model.file.regionScopes.regionScopesId = :locScop");	
  			
  		if(fileVO.getLocation() != null)
 			query.append(" and model.file.locationValue =:locScopVal");		
 		
 		if(fileVO.getFileType().trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileVO.getFileType().trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		//query.append(" order by model.file.fileDate desc ,model.updateddate");
 		
 		query.append(" order by model.file.fileDate desc ,model.createdDate desc");
  		
 		Query queryObject = getSession().createQuery(query.toString());
 		
 		queryObject.setLong("userId", fileVO.getCandidateId());
 		
 		queryObject.setString("type", IConstants.NEWS_GALLARY);
 		
 		if(fileVO.getExistingFrom() != null)
 		    queryObject.setDate("fromDate", fileVO.getExistingFrom());
 		
 		if(fileVO.getIdentifiedOn() != null)
 			queryObject.setDate("toDate",fileVO.getIdentifiedOn());
  			
  		if(fileVO.getSourceId() != null)
  			queryObject.setLong("sourceId",fileVO.getSourceId());
  		
  		if(fileVO.getLanguegeId() != null)
  			queryObject.setLong("languageId",fileVO.getLanguegeId());
  		
  		if(fileVO.getCategoryId() != null)
  			queryObject.setLong("categoryId",fileVO.getCategoryId());
  			
  		if(fileVO.getNewsImportanceId() != null)
  			queryObject.setLong("newsImportanceId",fileVO.getNewsImportanceId());
  			
  		if(fileVO.getLocationScope() != null)
  			queryObject.setLong("locScop",fileVO.getLocationScope());	
  			
  		if(fileVO.getLocation() != null)
  			queryObject.setLong("locScopVal",fileVO.getLocation());		
 		 
 		return queryObject.list();
 	}
     
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForCategory(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model.file.category.categoryType,model.file.category.categoryId from FileGallary model where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
  		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId ");	
 		
  		 query.append(") and  model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
  		
  		if(fromDate != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(toDate != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 		
 		if(fileType.trim().equalsIgnoreCase("Public"))
			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(fileType.trim().equalsIgnoreCase("Private"))
			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 			
  		query.append(" group by  model.file.category.categoryType   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForSource(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model1.source.source,model1.source.sourceId from FileGallary model,FileSourceLanguage model1,Category model2  where model1.file.fileId=model.file.fileId and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false' and model2.categoryId = model1.file.category.categoryId  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model1.source.source   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForLanguage(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model1.language.language,model1.language.languageId from FileGallary model,FileSourceLanguage model1,Category model2  where model1.file.fileId=model.file.fileId and  model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  and model2.categoryId = model1.file.category.categoryId  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model1.language.language  ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForNewsImportance(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model.file.newsImportance.importance,model.file.newsImportance.newsImportanceId from FileGallary model,Category model2  where model.file.category.categoryId = model2.categoryId   and  model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId!= 0 " );
  		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId ");	
  		 query.append(") and  model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.newsImportance.importance   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForLocationScope(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	
    	if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model.file.regionScopes.scope,model.file.regionScopes.regionScopesId from FileGallary model, Category model2  where model.file.category.categoryId = model2.categoryId and model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId!= 0 ");
  		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model1.language.languageId = :languageId ");
  		 query.append(") and  model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.regionScopes.regionScopesId   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getCountDetailsForLocationScopeValue(Date fromDate,Date toDate,String fileType,Long regId){
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  count(*),model.file.regionScopes.scope,model.file.regionScopes.regionScopesId,model.file.locationValue from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
  		 		
  		query.append(" group by  model.file.regionScopes.regionScopesId,model.file.locationValue   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getDetailsForCategory(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  date(model.file.fileDate),model.file.category.categoryType,model.file.category.categoryId from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
 		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId");
 		
 		if(fileVO.getLanguegeId() != null)
			query.append(" and model1.language.languageId = :languageId");
  				query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
  		
  		if(fromDate != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(toDate != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 		
 		if(fileType.trim().equalsIgnoreCase("Public"))
			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(fileType.trim().equalsIgnoreCase("Private"))
			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     public List<Object[]> getDetailsForSource(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select date(model.file.fileDate),model2.source.source,model2.source.sourceId from FileGallary model,FileSourceLanguage model2  where model2.file.fileId = model.file.fileId and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model2.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model2.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     @SuppressWarnings("unchecked")
	public List<Object[]> getDetailsForLanguage(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	 
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  date(model.file.fileDate),model2.language.language,model2.language.languageId from FileGallary model,FileSourceLanguage model2  where model.file.fileId = model2.file.fileId and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model2.source.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model2.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     public List<Object[]> getDetailsForNewsImportance(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileVO){
    	
    	 if(fileVO == null)
    		 fileVO = new FileVO();
    	 
    	 StringBuilder query = new StringBuilder();
  		 query.append("select  date(model.file.fileDate),model.file.newsImportance.importance,model.file.newsImportance.newsImportanceId from FileGallary model where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0  " );
 		if(fileVO.getSourceId() != null)
 			query.append(" and model1.source.sourceId = :sourceId");
 		
 		if(fileVO.getLanguegeId() != null)
			query.append(" and model1.language.languageId = :languageId");
 		
  				query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("userId", regId);
  		queryObject.setString("type", IConstants.NEWS_GALLARY);
  		
  		if(fromDate != null)
		    queryObject.setDate("fromDate", fromDate);
		
		if(toDate != null)
			queryObject.setDate("toDate",toDate);
		
		if(fileVO.getSourceId() != null)
			queryObject.setLong("sourceId", fileVO.getSourceId());
		
		if(fileVO.getLanguegeId() != null)
			queryObject.setLong("languageId", fileVO.getLanguegeId());
			
		if(fileVO.getCategoryId() != null)
			queryObject.setLong("categoryId", fileVO.getCategoryId());
 		
 		if(fileVO.getNewsImportanceId() != null)
 			queryObject.setLong("newsImportanceId", fileVO.getNewsImportanceId());
		
  		return queryObject.list(); 
     }
     public void updateFileDate(Date updateDt,Long fileId){
    	 Query queryObject = getSession().createQuery("update FileGallary model set model.updateddate = :updateDt where model.file.fileId =:fileId ");
 		  queryObject.setDate("updateDt", updateDt);
 		  queryObject.setLong("fileId", fileId);
 		
 		queryObject.executeUpdate();
     }
     
     public List<FileGallary> getFileGallariesByFileId(Long fileId){
    	
    	 Query query = getSession().createQuery("select model from FileGallary model where model.file.fileId = :fileId and model.isDelete = :delInd");
    	 
    	 query.setParameter("fileId", fileId);
    	 query.setParameter("delInd", "false");
    	 
    	 return query.list();
    	 
     }
     
     public void deleteFile(Long fileId){
    	 Query queryObject = getSession().createQuery("update FileGallary model set model.isDelete = :delInd where model.file.fileId =:fileId ");
 		  queryObject.setString("delInd", "true");
 		  queryObject.setLong("fileId", fileId);
 		
 		queryObject.executeUpdate();
     }
     
     public Object getFileIdByFileGallaryId(Long fileGallaryId)
     {
    	 Query query = getSession().createQuery("select model.file.fileId from FileGallary model where model.fileGallaryId = ?");
    	 query.setParameter(0,fileGallaryId);
    	 return query.uniqueResult();
     }
     
     @SuppressWarnings("unchecked")
     public List<Object> getFileGallaryIdByFileId(Long fileId)
     {
    	 Query query = getSession().createQuery("select model.fileGallaryId from FileGallary model where model.file.fileId = ?");
    	 query.setParameter(0,fileId);
    	 return query.list();
     }
     
     @SuppressWarnings("unchecked")
     public List<Object> getGalleryIdsOfAFile(Long fileId)
     {
    	 return getHibernateTemplate().find("select model.gallary.gallaryId from FileGallary model where model.file.fileId=?",fileId);
     }
     
     @SuppressWarnings("unchecked")
     public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList)
     {
    	 Query query = getSession().createQuery("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList) and model.isPrivate = 'false' and model.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' " +
    	 		" group by model.file.fileId");
    	 query.setParameterList("gallaryIdsList",gallaryIdsList);
    	 return query.list();
     }
     
     @SuppressWarnings("unchecked")
     public List<FileGallary> getFilesOfInGallariesForCustomer(List<Long> gallaryIdsList)
     {
    	 Query query = getSession().createQuery("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList)  and model.isDelete = 'false' and model.gallary.isDelete = 'false' " +
    	 		" group by model.file.fileId");
    	 query.setParameterList("gallaryIdsList",gallaryIdsList);
    	 return query.list();
     }
     
     @SuppressWarnings("unchecked")
     public List<Object[]> getFirstFileAndGallaryInfo(Long gallaryId,String queryString)
     {
    	 Query query = getSession().createQuery("select model.gallary.name,model.gallary.description,"+queryString+", " +
    	 		"model.fileGallaryId,model1.filePath from FileGallary model,FilePaths model1 where model.gallary.gallaryId = ? and " +
    	 		"model.file.fileId = model1.fileSourceLanguage.file.fileId and model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
    	 		"model.isPrivate = 'false' group by model.gallary.gallaryId order by model.updateddate desc,model1.fileSourceLanguage.fileSourceLanguageId,model1.orderNo");
    	 query.setMaxResults(1);
    	 query.setParameter(0,gallaryId);
    	 return query.list();
     }
     
     
     public List<Object[]> getFirstFileAndGallaryInfoForCustomer(Long gallaryId,String queryString){
    	 
    	 Query query = getSession().createQuery("select model.gallary.name,model.gallary.description,"+queryString+", " +
     	 		"model.fileGallaryId,model1.filePath from FileGallary model,FilePaths model1 where model.gallary.gallaryId = ? and " +
     	 		"model.file.fileId = model1.fileSourceLanguage.file.fileId and model.gallary.isDelete = 'false' and  model.isDelete = 'false'  " +
     	 		"group by model.gallary.gallaryId order by model.updateddate desc,model1.fileSourceLanguage.fileSourceLanguageId,model1.orderNo");
     	 query.setMaxResults(1);
     	 query.setParameter(0,gallaryId);
     	 return query.list();
    	 
    	 
     }
     
     public List<FileGallary> getCandidateGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> candidateIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    		 
    	 query.append("select model from FileGallary model where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate and date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate  and model.gallary.candidate.candidateId in (:candidateIds) ");  	 
    	 
    	 if(type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	   
    	 else if(type.equalsIgnoreCase("news"))    	 
    	   query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	    	 
    	 else if(type.equalsIgnoreCase("videos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.file.fileDate desc,model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("candidateIds",candidateIds);
    	 
    	 return queryObj.list();
     }
     
     public List<FileGallary> getCandidateGallaryDetailsForProfilePageStreaming(Date fromDate,Date toDate,Set<Long> candidateIds)
     {
    	 StringBuilder query = new StringBuilder();
    		 
    	 query.append("select model from FileGallary model where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate and date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate  and model.gallary.candidate.candidateId in (:candidateIds) "); 
    	 
    	 query.append(" order by model.file.fileDate desc,model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("candidateIds",candidateIds);
    	 
    	 return queryObj.list();
     }
     
     public List<Object[]> getPartyGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> partyIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    	 
    	 query.append("select  model,model1.party.partyId,model1.party.shortName,model1.party.partyFlag ");
	 
    	 query.append(" from FileGallary model,PartyGallery model1 where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate  and date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate  " +
     	 		" and model.gallary.gallaryId = model1.gallery.gallaryId and model1.isDelete = 'false' and model1.isPrivate = 'false' and model1.party.partyId in (:partyIds) ");
     	 
    	 if(type != null && type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	 
    	 else if(type != null && type.equalsIgnoreCase("news"))    	 
    	 query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	 
    	 else if(type != null && type.equalsIgnoreCase("videos"))
    	 query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.file.fileDate desc,model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("partyIds",partyIds);
    	 
    	 return queryObj.list();
     }
     
     public List<Object[]> getSpecialPageGallaryDetailsForSubscribers(Date fromDate,Date toDate,Set<Long> specialPageIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    	 
    	 query.append("select model,model1.specialPage.specialPageId,model1.specialPage.name,model1.specialPage.profileImgPath ");
    		 
    	 query.append(" from FileGallary model,SpecialPageGallery model1 where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		" model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate   and date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate " +
     	 		" and model.gallary.gallaryId = model1.gallary.gallaryId and model1.isDelect = 'false' and model1.specialPage.isDelete = 'false' and model1.specialPage.specialPageId in (:specialPageIds) ");
     	 
    	 if(type!= null && type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	 
    	 else if(type!= null && type.equalsIgnoreCase("news"))    	 
    	 query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	 
    	 else if(type!= null && type.equalsIgnoreCase("videos"))
    	 query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.file.fileDate desc,model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("specialPageIds",specialPageIds);
    	 
    	 return queryObj.list();
     }
   public List<Object[]> getAllNewsDetails(Long candidateId,int firstResult,int maxResult,String queryType){
	   
	     StringBuilder query = new StringBuilder();
			query.append("select model.file,model.fileGallaryId,model.file.fileDate from FileGallary model where model.gallary.candidate.candidateId =:candidateId and  model.gallary.isDelete = 'false'  and model.gallary.contentType.contentType= :type and model.isDelete = 'false' ");
			
			if(queryType.equals("Public"))
			   query.append("  and  model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
			if(queryType.equals("Private"))
			  query.append("  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			
			query.append(" order by model.file.fileDate desc ");
			Query queryObject = getSession().createQuery(query.toString());
			
			queryObject.setLong("candidateId", candidateId);
			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setFirstResult(firstResult);
			queryObject.setMaxResults(maxResult);
				
							
			return queryObject.list(); 
   }
   
   public FileGallary getFileGallary(Long fileGallaryId){
	   Query query = getSession().createQuery("select model from FileGallary model where model.fileGallaryId = :fileGallaryId");
            query.setParameter("fileGallaryId", fileGallaryId);
            
            return (FileGallary)query.uniqueResult();
   }
   
   
   public void updateVisibility(Long fileId,String visibility){
	   
		Query queryObject = getSession()
				.createQuery(
						"update FileGallary model set model.isPrivate = :visibility where model.file.fileId =:fileId ");
		  queryObject.setString("visibility", visibility);
		  queryObject.setLong("fileId", fileId);
		
		queryObject.executeUpdate();
	   
	   
   }
   
   
   public List<File> getAllFilesInAGallry(String queryString,PdfGenerationVO pdfGenerationVO){
	   
	   
	   Query query = getSession().createQuery("select model.file from FileGallary model " +
	   		"where "+queryString+ " model.gallary.gallaryId = :gallaryId ");	   
	   
	   
	   //if( pdfGenerationVO.getSourceId() != 0L)
		//   query.setParameter("sourceId",pdfGenerationVO.getSourceId());
	   
	   
	   if(!pdfGenerationVO.getAllFiles().equalsIgnoreCase("true")){
		   
	 
		   if(pdfGenerationVO.getLanguageId() != 0L)
			   query.setParameter("languageId", pdfGenerationVO.getLanguageId());
		   
		  // if(pdfGenerationVO.getCategoryId() != 0L) 
			  // query.setParameter("categoryId", pdfGenerationVO.getCategoryId());
		   
		   if(pdfGenerationVO.getImportanceId() != 0L)
			   query.setParameter("newsImportanceId", pdfGenerationVO.getImportanceId());
		   
		   if(pdfGenerationVO.getImpactLevelId() != 0L)
			   query.setParameter("regionScopesId", pdfGenerationVO.getImpactLevelId());
		   
		   
		   if(pdfGenerationVO.getBetweenDates().equalsIgnoreCase("true")){
			   
			   if(!pdfGenerationVO.getStartDate().equalsIgnoreCase(""))
				   query.setParameter("startDate", pdfGenerationVO.getStartDateInDateFormat());
			   
			   if(!pdfGenerationVO.getEndDate().equalsIgnoreCase(""))
				   query.setParameter("endDate", pdfGenerationVO.getEndDateInDateFormat());
			   
		   }
	   }
	   query.setParameter("gallaryId", pdfGenerationVO.getGallaryId());	   
	   query.setParameter("deleteInd", "false");
	   query.setParameter("categoryId", pdfGenerationVO.getCategoryId());
	   
	   return query.list();
	   
   }
   
   
   public List<Object[]> getCandidateGallariesByCategory(List<Long> candidateds , Long categoryId){	   
	   
	   String queryString = "select distinct model.gallary.gallaryId , trim(model.gallary.name),model.gallary.candidate.lastname from FileGallary model" +
	   		" where model.gallary.candidate.candidateId in (:candidateds) and model.isDelete = :delInd " +
	   		" and model.file.category.categoryId = :categoryId order by  model.gallary.name";
	   
	   Query query = null;
	   
	    query = getSession().createQuery(queryString);
	   
	   query.setParameterList("candidateds",candidateds);
	   query.setParameter("delInd", "false");
	   query.setParameter("categoryId", categoryId);	   
	 
	   
	   return query.list();	   
	   
   }
   
   
	
	
	public List<Object[]> getNewsCountForALocationByCategoryForACandidate(List<Long> candidateIds,
			Long locationScopeId,List<Long> locationValuesList){
		
		Query query = getSession()
				.createQuery(
						"select model.file.category.categoryType,count(*),model.file.category.categoryId" +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) " +						
					    " and model.file.regionScopes.regionScopesId = :locationScopeId and " +
					    " model.file.locationValue in( :locationValuesList)" +
					    " group by model.file.category.categoryId");
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameterList("locationValuesList", locationValuesList);
		
		return query.list();
		
	}
	
	
	public List<FileGallary> getFilegallaryDetailsForPanchayat(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						" select model from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) " +						
					    " and model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		
		
		return query.list();
		
		
	}
	
	public List<Object[]> getNewsByForPanchayat(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate" +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) " +						
					    " and model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId " +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
		return query.list();
		
		
	}
	
	public List<FileGallary> getNewsDetailsByForMuncipality(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId " +
						"in(:candidateIds) and" +
						" model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityValues)"+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValues", newsCountVO.getMuncipalityValuesList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
	
		return query.list();	
		
		
	}
	
   public List<FileGallary> getNewsDetailsByForWards(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId " +
						"in(:candidateIds) and" +
						" ( model.file.regionScopes.regionScopesId = :wardScopeId and " +
						" model.file.locationValue in( :wardIdsList))"+						
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
	
		return query.list();	
		
		
	}
	
	
	public List<FileGallary> getNewsDetailsByForMuncipalityWithWards(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId " +
						"in(:candidateIds) and" +
						" (( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityValues))"+
						" or ( model.file.regionScopes.regionScopesId = :wardScopeId and " +
						" model.file.locationValue in( :wardIdsList)))"+						
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValues", newsCountVO.getMuncipalityValuesList());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
	
		return query.list();	
		
		
	}
	
	public List<FileGallary> getNewsDetailsByForMandal(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
	
		return query.list();	
		
		
		
	}
	
	
	public List<Object[]> getNewsByForMuncipality(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						" model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityValues)"+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValues", newsCountVO.getMuncipalityValuesList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
	
		return query.list();	
		
		
	}
	
	
	public List<Object[]> getNewsByForMuncipalityWithWards(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate from FileGallary model where " +
						" model.gallary.candidate.candidateId in(:candidateIds) and" +
						" ((model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityValues)) or" +
						" (model.file.regionScopes.regionScopesId = :wardScopeId and " +
						" model.file.locationValue in( :wardIds)))"+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValues", newsCountVO.getMuncipalityValuesList());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIds", newsCountVO.getWardIdsList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
	
		return query.list();	
		
		
	}
	
public List<Object[]> getNewsByWard(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate from FileGallary model where " +
						" model.gallary.candidate.candidateId in(:candidateIds) and" +
						" (model.file.regionScopes.regionScopesId = :wardScopeId and " +
						" model.file.locationValue in( :wardIds))"+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIds", newsCountVO.getWardIdsList());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
	
		return query.list();	
		
		
	}
	
	public List<Object[]> getNewsByForMandal(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate" +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
		return query.list();	
		
		
		
	}
	
	
	public List<FileGallary> getNewsDetailsForConstituency(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
						" model.file.locationValue in( :constituencyIds)) or "+
						"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
		query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		
		return query.list();	
		
		
	}
	
	
public List<FileGallary> getNewsDetailsForConstituencyWithMuncipality(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
						" model.file.locationValue in( :constituencyIds)) or "+
						"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
						"( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityIds)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
		query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityIds", newsCountVO.getMuncipalityValuesList());
		
		
		return query.list();	
		
		
	}

public List<FileGallary> getNewsDetailsForConstituencyWithMuncipalityAndWards(NewsCountVO newsCountVO){
	
	Query query = getSession()
			.createQuery(
					"select model from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
					"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
					" model.file.locationValue in( :constituencyIds)) or "+
					"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
					" model.file.locationValue in( :tehsilIds)) or "+
					"( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
					" model.file.locationValue in( :muncipalityIds)) or "+
					"( model.file.regionScopes.regionScopesId = :wardScopeId and " +
					" model.file.locationValue in( :wardIds)) or "+
				    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				    " model.file.locationValue in( :hamletIds))) "+
				    " and model.file.category.categoryId = :categoryId " +
				    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
				    " order by model.createdDate desc");
	
	
	query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
	query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
	query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
	query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
	query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
	query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
	query.setParameterList("wardIds", newsCountVO.getWardIdsList());
	query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
	query.setParameterList("hamletIds", newsCountVO.getHamletIds());
	query.setParameter("categoryId", newsCountVO.getCategoryId());
	query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
	query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
	query.setParameterList("muncipalityIds", newsCountVO.getMuncipalityValuesList());
	
	
	return query.list();	
	
	
}

	
	
	public List<Object[]> getNewsByForConstituency(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate " +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
						" model.file.locationValue in( :constituencyIds)) or "+
						"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
		query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
		return query.list();	
		
		
	}
	
public List<Object[]> getNewsByForConstituencyWithMuncipality(NewsCountVO newsCountVO){
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate " +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
						"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
						" model.file.locationValue in( :constituencyIds)) or "+
						"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in( :tehsilIds)) or "+
						"( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in( :muncipalityValuesList)) or "+
					    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))) "+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
		query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
		return query.list();	
		
		
	}

public List<Object[]> getNewsByForConstituencyWithMuncipalityWithWards(NewsCountVO newsCountVO){
	
	Query query = getSession()
			.createQuery(
					"select model.file,model.fileGallaryId,model.isPrivate " +
					" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) and" +
					"(( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
					" model.file.locationValue in( :constituencyIds)) or "+
					"( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
					" model.file.locationValue in( :tehsilIds)) or "+
					"( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
					" model.file.locationValue in( :muncipalityValuesList)) or "+
					"( model.file.regionScopes.regionScopesId = :wardScopeId and " +
					" model.file.locationValue in( :wardIdsList)) or "+
				    " (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				    " model.file.locationValue in( :hamletIds))) "+
				    " and model.file.category.categoryId = :categoryId " +
				    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
				    " order by model.createdDate desc");
	
	
	query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
	query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
	query.setParameterList("constituencyIds", newsCountVO.getConstituencyValuesList());		
	query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
	query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
	
	query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
	query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
	
	query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
	query.setParameterList("hamletIds", newsCountVO.getHamletIds());
	query.setParameter("categoryId", newsCountVO.getCategoryId());
	query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
	query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
	query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
	
	query.setFirstResult(newsCountVO.getStartIndex());
	query.setMaxResults(newsCountVO.getMaxResults());
	
	return query.list();	
	
	
}
	
	
	public List<Object[]> getNewsByLocationAndCategory(List<Long> candidateIds,
			FileVO fileVO, List<Long> locationValuesList) {
		
		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId" +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) " +						
					    " and model.file.regionScopes.regionScopesId = :locationScopeId and " +
					    " model.file.locationValue in( :locationValuesList) "+
					    " and model.file.category.categoryId = :categoryId " +
					    "and model.file.newsImportance.newsImportanceId = :newsImportanceId");
					   
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("locationScopeId", fileVO.getLocationId());
		query.setParameterList("locationValuesList", locationValuesList);
		query.setParameter("categoryId", fileVO.getCategoryId());
		query.setParameter("newsImportanceId", fileVO.getImportanceId());
		
		query.setFirstResult(fileVO.getStartIndex());
		query.setMaxResults(fileVO.getMaxResult());
		
		return query.list();
		
	}
	
	public List<FileGallary> getNewsByLocationAndCategoryInPopup(List<Long> candidateIds,
			FileVO fileVO, List<Long> locationValuesList) {
		
		
		Query query = getSession()
				.createQuery(
						"select model" +
						" from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds) " +						
					    " and model.file.regionScopes.regionScopesId = :locationScopeId and " +
					    " model.file.locationValue in( :locationValuesList) "+
					    " and model.file.category.categoryId = :categoryId " +
					    "and model.file.newsImportance.newsImportanceId = :newsImportanceId");
					   
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("locationScopeId", fileVO.getLocationId());
		query.setParameterList("locationValuesList", locationValuesList);
		query.setParameter("categoryId", fileVO.getCategoryId());
		query.setParameter("newsImportanceId", fileVO.getImportanceId());
		
		return query.list();
		
	} 
	
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForACandidate(
			List<Long> candidateIds, Long categoryId, Long locationId,
			List<Long> locationValuesList) {

		Query query = getSession()
				.createQuery(
						" select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
						" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
						" and model.file.regionScopes.regionScopesId = :locationScopeId and " +
					    " model.file.locationValue in( :locationValuesList)" +								
					    " and model.file.category.categoryId = :categoryId group by model.file.newsImportance.newsImportanceId");
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("categoryId", categoryId);
		query.setParameter("locationScopeId", locationId);
		query.setParameterList("locationValuesList", locationValuesList);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituency(
			                              Long categoryId , NewsCountVO newsCountVO){
		
		Query query = getSession().createQuery(
				" select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
				" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
				" and (( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
			    " model.file.locationValue = :constituencyValue) "+
				" or "+
			    " ( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
			    " model.file.locationValue in( :tehsilIds))"+
				" or "+
			    " ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
			    " model.file.locationValue in( :hamletIds)))"+
                " and model.file.category.categoryId = :categoryId " +
                " group by model.file.newsImportance.newsImportanceId");

		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
		query.setParameter("constituencyValue", newsCountVO.getConstituencyValue());
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", categoryId);
		
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipality(
            Long categoryId , NewsCountVO newsCountVO){
		
		

			Query query = getSession().createQuery(
			" select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
			" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
			" and (( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
			" model.file.locationValue = :constituencyValue) "+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
			" model.file.locationValue in( :tehsilIds))"+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
			" model.file.locationValue in( :localElectionBodyIds))"+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
			" model.file.locationValue in( :hamletIds)))"+
			" and model.file.category.categoryId = :categoryId " +
			" group by model.file.newsImportance.newsImportanceId");
			
			
			query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
			query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
			query.setParameter("constituencyValue", newsCountVO.getConstituencyValue());
			query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
			query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
			query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
			query.setParameterList("hamletIds", newsCountVO.getHamletIds());
			query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
			query.setParameterList("localElectionBodyIds", newsCountVO.getMuncipalityValuesList());
			query.setParameter("categoryId", categoryId);
			
			if(newsCountVO.getWardIdsList() != null && newsCountVO.getWardIdsList().size() >0){
				query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
				query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
				
			}
			
			return query.list();

}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipalityAndWards(
            Long categoryId , NewsCountVO newsCountVO){
		
		

			Query query = getSession().createQuery(
			" select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
			" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
			" and (( model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
			" model.file.locationValue = :constituencyValue) "+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
			" model.file.locationValue in( :tehsilIds))"+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
			" model.file.locationValue in( :localElectionBodyIds))"+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :wardScopeId and " +
			" model.file.locationValue in( :wardIdsList))"+
			" or "+
			" ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
			" model.file.locationValue in( :hamletIds)))"+
			" and model.file.category.categoryId = :categoryId " +
			" group by model.file.newsImportance.newsImportanceId");
			
			
			query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
			query.setParameter("constituencyScopeId", newsCountVO.getConstituencyScopeId());
			query.setParameter("constituencyValue", newsCountVO.getConstituencyValue());
			query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
			query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
			query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
			query.setParameterList("hamletIds", newsCountVO.getHamletIds());
			query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
			query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
			query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
			query.setParameterList("localElectionBodyIds", newsCountVO.getMuncipalityValuesList());
			query.setParameter("categoryId", categoryId);
			
			if(newsCountVO.getWardIdsList() != null && newsCountVO.getWardIdsList().size() >0){
				query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
				query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
				
			}
			
			return query.list();

}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForWard(
            Long categoryId , NewsCountVO newsCountVO){
		
		Query query = getSession().createQuery(
				        " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
						" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
					    " and "+
					    " ( model.file.regionScopes.regionScopesId = :wardScopeId and " +
					    " model.file.locationValue in( :wardIds))"+
		                " and model.file.category.categoryId = :categoryId " +
		                " group by model.file.newsImportance.newsImportanceId");
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIds", newsCountVO.getWardIdsList());		
		query.setParameter("categoryId", categoryId);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMandal(
			                           Long categoryId , NewsCountVO newsCountVO){
		
		Query query = getSession().createQuery(
				        " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
						" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
					    " and (( model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
					    " model.file.locationValue = :tehsilIds)"+
						" or "+
					    " ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds)))"+
		                " and model.file.category.categoryId = :categoryId " +
		                " group by model.file.newsImportance.newsImportanceId");
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("tehsilScopeId", newsCountVO.getTehsilScopeId());
		query.setParameterList("tehsilIds", newsCountVO.getTehsilIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", categoryId);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForPanchayat(
			                               Long categoryId , NewsCountVO newsCountVO){
      
      Query query = getSession().createQuery(
    		            " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
						" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
					    " and  model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in(:hamletIds) "+
					    " and model.file.category.categoryId = :categoryId " +
                        " group by model.file.newsImportance.newsImportanceId");
      
	  	query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", categoryId);
		
		return query.list();
	
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMuncipality(
			Long categoryId , NewsCountVO newsCountVO){
		
		 Query query = getSession().createQuery(
		            " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
					" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
				    " and  model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
				    " model.file.locationValue in(:muncipalityValuesList) "+
				    " and model.file.category.categoryId = :categoryId " +
                 " group by model.file.newsImportance.newsImportanceId");

	query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
	query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
	query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
	query.setParameter("categoryId", categoryId);
	
	return query.list();
		
		
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForMuncipalityWithWards(
			Long categoryId , NewsCountVO newsCountVO){
		
		 Query query = getSession().createQuery(
		            " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
					" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
				    " and  ((model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
				    " model.file.locationValue in(:muncipalityValuesList))" +
				    "or (model.file.regionScopes.regionScopesId = :wardScopeId and " +
				    " model.file.locationValue in(:wardIdsList))) "+
				    " and model.file.category.categoryId = :categoryId " +
                 " group by model.file.newsImportance.newsImportanceId");

	query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
	query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
	query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
	query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
	query.setParameterList("wardIdsList", newsCountVO.getWardIdsList());
	query.setParameter("categoryId", categoryId);
	
	return query.list();
		
		
	}
	
	public List<File> getNewsCountForALocationByCategoryAndImportance(
			List<Long> candidateIds,Long categoryId ,Long locationScopeId ,List<Long> locationValuesList,Long hamletScopeId ,
			List<Long> hamletIds ){
		
		Query query = getSession().createQuery("select model.file from FileGallary model where  " +
				"model.gallary.candidate.candidateId in(:candidateIds) and " +
				"((model.file.regionScopes.regionScopesId = :locationScopeId and model.file.locationValue in( :locationValuesList))" +
				"or " +
				"(model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				"model.file.locationValue in( :hamletIds)))");
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("locationScopeId", locationScopeId);
		query.setParameterList("locationValuesList", locationValuesList);

		query.setParameter("hamletScopeId", hamletScopeId);
		query.setParameter("hamletIds", hamletIds);
		
		return query.list();
		
	}
   
	
	public List<String> checkForVisibilityStatus(Long contentId){
		
		Query query = getSession().createQuery("select model.isPrivate from FileGallary model where" +
				" model.fileGallaryId = ?");
		
		query.setParameter(0, contentId);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForMandalLevel(List<Long> candidateIds,
			Long tehsilScopeId, List<Long> tehsilIdsList,
			Long panchayatScopeId, List<Long> panchayatIdsList) {
		
		
		StringBuffer queryStr = new StringBuffer();
		
		
			queryStr.append("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) and");
					
            if(tehsilScopeId != null && tehsilIdsList != null)		
            	   queryStr.append(" (model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
				    " model.file.locationValue in( :tehsilIdsList))");
		
		
		/*if(panchayatIdsList != null || panchayatIdsList != null)			
			queryStr.append("or (model.file.regionScopes.regionScopesId = :lowLevelLocationId and " +
				    " model.file.locationValue in( :panchayatIdsList))");*/
		
		if(panchayatIdsList != null && panchayatIdsList != null){
			
			 if(tehsilScopeId != null && tehsilIdsList != null)
				 queryStr.append(" or ");
			
			queryStr.append("(model.file.regionScopes.regionScopesId = :panchayatScopeId and " +
				    " model.file.locationValue in( :panchayatIdsList))");
		}
		
		queryStr.append(" group by model.file.category.categoryId");

		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameterList("candidateIds", candidateIds);
		
		if(tehsilScopeId != null && tehsilIdsList != null){			
		
		  query.setParameter("tehsilScopeId", tehsilScopeId);
		  query.setParameterList("tehsilIdsList", tehsilIdsList);
		 
		}
		
		if(panchayatIdsList != null && panchayatIdsList != null){
			query.setParameter("panchayatScopeId", panchayatScopeId);
			query.setParameterList("panchayatIdsList", panchayatIdsList);
		}
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForConstituencyLevel(
			List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
			Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
			List<Long> hamletIds) {	
		
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) " +
					"and ((model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
				    " model.file.locationValue = :constituencyVal) " +
					"or"+
				    "(model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
				    " model.file.locationValue in( :tehsilIds))"+
					"or"+
				    "(model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				    " model.file.locationValue in(:hamletIds))) group by model.file.category.categoryId");
		 
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("constituencyScopeId", constituencyScopeId);
		query.setParameter("constituencyVal", constituencyVal);
		query.setParameter("tehsilScopeId", tehsilScopeId);
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("hamletScopeId", hamletScopeId);
		query.setParameterList("hamletIds", hamletIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForConstituencyLevelWithMuncipality(
			List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
			Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
			List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds) {
		
		
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) " +
					"and ((model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
				    " model.file.locationValue = :constituencyVal) " +
					"or"+
				    "(model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
				    " model.file.locationValue in( :tehsilIds))"+
				    "or"+
				    "(model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
				    " model.file.locationValue in( :localElectionBodyIds))"+
					"or"+
				    "(model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				    " model.file.locationValue in(:hamletIds))) group by model.file.category.categoryId");
		 
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("constituencyScopeId", constituencyScopeId);
		query.setParameter("constituencyVal", constituencyVal);
		query.setParameter("tehsilScopeId", tehsilScopeId);
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("hamletScopeId", hamletScopeId);
		query.setParameterList("hamletIds", hamletIds);
		query.setParameter("muncipalityScopeId", muncipalityScopeId);
		query.setParameterList("localElectionBodyIds", localElectionBodyIds);
		
		return query.list();
		
	}
	
	public List<Object[]> getNewsCountForConstituencyLevelWithMuncipalityAndWards(
			List<Long> candidateIds, Long constituencyScopeId, Long constituencyVal,
			Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
			List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds,Long wardScopeId,List<Long> wardIdsList) {
		 
		
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) " +
					"and ((model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
				    " model.file.locationValue = :constituencyVal) " +
					"or"+
				    "(model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
				    " model.file.locationValue in( :tehsilIds))"+
				    "or"+
				    "(model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
				    " model.file.locationValue in( :localElectionBodyIds))"+
				    "or"+
				    "(model.file.regionScopes.regionScopesId = :wardScopeId and " +
				    " model.file.locationValue in( :wardIdsList))"+
					"or"+
				    "(model.file.regionScopes.regionScopesId = :hamletScopeId and " +
				    " model.file.locationValue in(:hamletIds))) group by model.file.category.categoryId");
		 
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("constituencyScopeId", constituencyScopeId);
		query.setParameter("constituencyVal", constituencyVal);
		query.setParameter("tehsilScopeId", tehsilScopeId);
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("hamletScopeId", hamletScopeId);
		query.setParameterList("hamletIds", hamletIds);
		query.setParameter("muncipalityScopeId", muncipalityScopeId);
		query.setParameterList("localElectionBodyIds", localElectionBodyIds);
		query.setParameter("wardScopeId", wardScopeId);
		query.setParameterList("wardIdsList", wardIdsList);
		
		return query.list();
		
	}
	
	
	
	
	public List<Object[]> getNewsCountForMuncipality(
			List<Long> candidateIds ,Long muncipalityScopeId ,List<Long> muncipalityValuesList){
		
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) " +
					" and model.file.regionScopes.regionScopesId =:muncipalityScopeId and" +
					" model.file.locationValue in( :muncipalityValuesList) group by model.file.category.categoryId");
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("muncipalityScopeId", muncipalityScopeId);
		query.setParameterList("muncipalityValuesList", muncipalityValuesList);
		
		return query.list();
		
		
	}
	
	
	public List<Object[]> getNewsCountForMuncipalityWithWards(NewsCountVO newsCountVO){
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and (( model.file.regionScopes.regionScopesId =:muncipalityScopeId and" +
				" model.file.locationValue in( :muncipalityValuesList) " +
				" or "+
				" model.file.regionScopes.regionScopesId =:wardScopeId and" +
				" model.file.locationValue in( :wardIds))) " +
				"group by model.file.category.categoryId");
		
	
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIds",newsCountVO.getWardIdsList());
	
	    return query.list();
		
	}
	
	
	public List<Object[]> getNewsCountForWards(List<Long> candidateIds,
			Long wardScopeId, List<Long> wardValuesList) {
		
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and model.file.regionScopes.regionScopesId =:wardScopeId and" +
				" model.file.locationValue in( :wartdIds) group by model.file.category.categoryId");
	
	query.setParameterList("candidateIds", candidateIds);
	query.setParameter("wardScopeId", wardScopeId);
	query.setParameterList("wartdIds", wardValuesList);
	
	return query.list();
		
		
	}
	
	
	public List<Object[]> getNewsCountForHamlets(List<Long> candidateIds,
			Long hamletScopeId, List<Long> hamletIds)
	{
		Query query = getSession().createQuery("select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and model.file.regionScopes.regionScopesId =:hamletScopeId and" +
				" model.file.locationValue in( :hamletIds) group by model.file.category.categoryId");
	
	query.setParameterList("candidateIds", candidateIds);
	query.setParameter("hamletScopeId", hamletScopeId);
	query.setParameterList("hamletIds", hamletIds);
	
	return query.list();
		
		
	}
	

	public List<FileGallary> getNewsDetailsByForHamlets(NewsCountVO newsCountVO)
	{

		
		Query query = getSession()
				.createQuery(
						"select model from FileGallary model where model.gallary.candidate.candidateId " +
						"in(:candidateIds) and" +
						" ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
						" model.file.locationValue in( :hamletIds))"+						
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());		
	
		return query.list();
	}
	
	public List<Object[]> getNewsByHamlet(NewsCountVO newsCountVO)
	{
		

		
		Query query = getSession()
				.createQuery(
						"select model.file,model.fileGallaryId,model.isPrivate from FileGallary model where " +
						" model.gallary.candidate.candidateId in(:candidateIds) and" +
						" (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
						" model.file.locationValue in( :hamletIds))"+
					    " and model.file.category.categoryId = :categoryId " +
					    " and model.file.newsImportance.newsImportanceId = :newsImportanceId" +
					    " order by model.createdDate desc");
		
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());
		query.setParameter("categoryId", newsCountVO.getCategoryId());
		query.setParameter("newsImportanceId", newsCountVO.getNewsImportanceId());
		
		query.setFirstResult(newsCountVO.getStartIndex());
		query.setMaxResults(newsCountVO.getMaxResults());
		
	
		return query.list();
	}
	
	public List<Object[]> getNewsCountForALocationByCategoryAndImportanceForHamlet( Long categoryId , NewsCountVO newsCountVO)
	{
		Query query = getSession().createQuery(
				        " select model.file.newsImportance.newsImportanceId,model.file.newsImportance.importance," +
						" count(*) from FileGallary model where model.gallary.candidate.candidateId in(:candidateIds)" +
					    " and "+
					    " ( model.file.regionScopes.regionScopesId = :hamletScopeId and " +
					    " model.file.locationValue in( :hamletIds))"+
		                " and model.file.category.categoryId = :categoryId " +
		                " group by model.file.newsImportance.newsImportanceId");
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("hamletScopeId", newsCountVO.getHamletScopeId());
		query.setParameterList("hamletIds", newsCountVO.getHamletIds());		
		query.setParameter("categoryId", categoryId);
		
		return query.list();
		
	
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getNewsForRegisterUsers2(FileVO fileVO,String direction , String columnName , Integer startIndex,Integer resultsCount){
		 StringBuilder query = new StringBuilder();
	  		query.append("select model.fileGallaryId ,model.file,model.isPrivate,model.gallary.gallaryId,model.gallary.name " +
	  				"from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
	  		if(fileVO.getSourceId() != null)
	  			query.append(" and model1.source.sourceId = :sourceId");
	  		
	  		if(fileVO.getLanguegeId() != null)
	 			query.append(" and model1.language.languageId = :languageId");
	  		
	  		query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
	  				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
	  		
	  		if(fileVO.getExistingFrom() != null)
	  			query.append(" and date(model.file.fileDate) >= :fromDate");
	  			
	  		if(fileVO.getIdentifiedOn() != null)
	  			query.append(" and date(model.file.fileDate) <= :toDate");
	  		
	  		if(fileVO.getCategoryId() != null)
	  			query.append(" and model.file.category.categoryId = :categoryId");
	  			
	  		if(fileVO.getNewsImportanceId() != null)
	  			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId");
	  			
	  		if(fileVO.getLocationScope() != null)
	  			query.append(" and model.file.regionScopes.regionScopesId = :locScop");	
	  			
	  		if(fileVO.getLocation() != null)
	 			query.append(" and model.file.locationValue =:locScopVal");		
	 		
	 		if(fileVO.getFileType().trim().equalsIgnoreCase("Public"))
	 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
	 			
	 		if(fileVO.getFileType().trim().equalsIgnoreCase("Private"))
	 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
	 		
	 		//query.append(" order by model.file.fileDate desc ,model.updateddate");
	 		
	 		if(columnName.equalsIgnoreCase("categoryType"))
	 			query.append(" order by model.file.category.categoryType "+direction);
	 		else if(columnName.equalsIgnoreCase("gallaryName"))
	 			query.append(" order by  trim(model.gallary.name) "+direction);
	 		else if(columnName.equalsIgnoreCase("source"))
	 			query.append(" order by  trim(model1.source.source)  "+direction);
	 		else if(columnName.equalsIgnoreCase("fileTitle1"))
	 			query.append(" order by  trim(model.file.fileTitle)  "+direction);
	 		else if(columnName.equalsIgnoreCase("description"))
	 			query.append(" order by  trim(model.file.fileDescription)  "+direction);
	 		else if(columnName.equalsIgnoreCase("locationScopeValue"))
	 			query.append(" order by  trim(model.file.regionScopes.scope)  "+direction);
	 		else if(columnName.equalsIgnoreCase("fileDate"))
	 			//query.append(" order by model.file.fileDate desc ,model.createdDate desc ");	
	 			query.append(" order by model.createdDate  "+direction);	
	 		else	 		
	 		//query.append(" order by model.file.fileDate desc ,model.createdDate desc");
	 			query.append(" order by model.file.fileDate "+direction);
	 		
	 		query.append(" , model.createdDate desc");
	  		
	 		Query queryObject = getSession().createQuery(query.toString());
	 		
	 		queryObject.setLong("userId", fileVO.getCandidateId());
	 		
	 		queryObject.setString("type", IConstants.NEWS_GALLARY);
	 		
	 		if(fileVO.getExistingFrom() != null)
	 		    queryObject.setDate("fromDate", fileVO.getExistingFrom());
	 		
	 		if(fileVO.getIdentifiedOn() != null)
	 			queryObject.setDate("toDate",fileVO.getIdentifiedOn());
	  			
	  		if(fileVO.getSourceId() != null)
	  			queryObject.setLong("sourceId",fileVO.getSourceId());
	  		
	  		if(fileVO.getLanguegeId() != null)
	  			queryObject.setLong("languageId",fileVO.getLanguegeId());
	  		
	  		if(fileVO.getCategoryId() != null)
	  			queryObject.setLong("categoryId",fileVO.getCategoryId());
	  			
	  		if(fileVO.getNewsImportanceId() != null)
	  			queryObject.setLong("newsImportanceId",fileVO.getNewsImportanceId());
	  			
	  		if(fileVO.getLocationScope() != null)
	  			queryObject.setLong("locScop",fileVO.getLocationScope());	
	  			
	  		if(fileVO.getLocation() != null)
	  			queryObject.setLong("locScopVal",fileVO.getLocation());		
	 		 
	  		queryObject.setFirstResult(startIndex);
	  		queryObject.setMaxResults(resultsCount);
	 		return queryObject.list();
	}
	
	 @SuppressWarnings("unchecked")
	  	public List<Long> getNewsCountForRegisterUsers(FileVO fileVO,String columnName , String direction){
	  		
	      	 StringBuilder query = new StringBuilder();
	   		query.append("select count(model.fileGallaryId)" +
	   				"from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 " );
	   		if(fileVO.getSourceId() != null)
	   			query.append(" and model1.source.sourceId = :sourceId");
	   		
	   		if(fileVO.getLanguegeId() != null)
	  			query.append(" and model1.language.languageId = :languageId");
	   		
	   		query.append(") and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
	   				" where model1.user.userId = :userId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
	   		
	   		if(fileVO.getExistingFrom() != null)
	   			query.append(" and date(model.file.fileDate) >= :fromDate");
	   			
	   		if(fileVO.getIdentifiedOn() != null)
	   			query.append(" and date(model.file.fileDate) <= :toDate");
	   		
	   		if(fileVO.getCategoryId() != null)
	   			query.append(" and model.file.category.categoryId = :categoryId");
	   			
	   		if(fileVO.getNewsImportanceId() != null)
	   			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId");
	   			
	   		if(fileVO.getLocationScope() != null)
	   			query.append(" and model.file.regionScopes.regionScopesId = :locScop");	
	   			
	   		if(fileVO.getLocation() != null)
	  			query.append(" and model.file.locationValue =:locScopVal");		
	  		
	  		if(fileVO.getFileType().trim().equalsIgnoreCase("Public"))
	  			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
	  			
	  		if(fileVO.getFileType().trim().equalsIgnoreCase("Private"))
	  			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
	  		
	  		if(columnName.equalsIgnoreCase("categoryType"))
	 			query.append(" order by model.file.category.categoryType "+direction);
	 		else if(columnName.equalsIgnoreCase("gallaryName"))
	 			query.append(" order by  trim(model.gallary.name) "+direction);
	 		else if(columnName.equalsIgnoreCase("source"))
	 			query.append(" order by  trim(model1.source.source)  "+direction);
	 		else if(columnName.equalsIgnoreCase("fileTitle1"))
	 			query.append(" order by  trim(model.file.fileTitle)  "+direction);
	 		else if(columnName.equalsIgnoreCase("description"))
	 			query.append(" order by  trim(model.file.fileDescription)  "+direction);
	 		else if(columnName.equalsIgnoreCase("locationScopeValue"))
	 			query.append(" order by  trim(model.file.regionScopes.scope)  "+direction);
	 		else if(columnName.equalsIgnoreCase("fileDate"))
	 			//query.append(" order by model.file.fileDate desc ,model.createdDate desc ");	
	 			query.append(" order by model.createdDate  "+direction);	
	 		else	 		
	 		//query.append(" order by model.file.fileDate desc ,model.createdDate desc");
	 			query.append(" order by model.file.fileDate "+direction);
	 		
	 		query.append(" , model.createdDate desc");
	  		
	  		//query.append(" order by model.file.fileDate desc ,model.updateddate");
	  		
	  		//query.append(" order by model.file.fileDate desc ,model.createdDate desc");
	   		
	  		Query queryObject = getSession().createQuery(query.toString());
	  		
	  		queryObject.setLong("userId", fileVO.getCandidateId());
	  		
	  		queryObject.setString("type", IConstants.NEWS_GALLARY);
	  		
	  		if(fileVO.getExistingFrom() != null)
	  		    queryObject.setDate("fromDate", fileVO.getExistingFrom());
	  		
	  		if(fileVO.getIdentifiedOn() != null)
	  			queryObject.setDate("toDate",fileVO.getIdentifiedOn());
	   			
	   		if(fileVO.getSourceId() != null)
	   			queryObject.setLong("sourceId",fileVO.getSourceId());
	   		
	   		if(fileVO.getLanguegeId() != null)
	   			queryObject.setLong("languageId",fileVO.getLanguegeId());
	   		
	   		if(fileVO.getCategoryId() != null)
	   			queryObject.setLong("categoryId",fileVO.getCategoryId());
	   			
	   		if(fileVO.getNewsImportanceId() != null)
	   			queryObject.setLong("newsImportanceId",fileVO.getNewsImportanceId());
	   			
	   		if(fileVO.getLocationScope() != null)
	   			queryObject.setLong("locScop",fileVO.getLocationScope());	
	   			
	   		if(fileVO.getLocation() != null)
	   			queryObject.setLong("locScopVal",fileVO.getLocation());		
	  		 
	  		return queryObject.list();
	  	}
	     
     
}
