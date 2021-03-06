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
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.util.IConstants;
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
	
	
	@SuppressWarnings("unchecked")
	public List<FileGallary> getRecentlyUploadedGallaries(Integer startIndex,Integer maxResults)
	{
		Query query = getSession().createQuery("select  model from FileGallary model where model.gallary.contentType.contentType ='Photo Gallary'" +
				" and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' and model.isPrivate = 'false' and model.isDelete = 'false' group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();	
	}
	
	
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
  		 query.append("select  count(*),model1.source.source,model1.source.sourceId from FileGallary model,FileSourceLanguage model1  where model1.file.fileId=model.file.fileId and model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
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
  		 query.append("select  count(*),model1.language.language,model1.language.languageId from FileGallary model,FileSourceLanguage model1  where model1.file.fileId=model.file.fileId and  model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
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
  		 query.append("select  count(*),model.file.newsImportance.importance,model.file.newsImportance.newsImportanceId from FileGallary model  where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId!= 0 " );
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
  		 query.append("select  count(*),model.file.regionScopes.scope,model.file.regionScopes.regionScopesId from FileGallary model where model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId!= 0 ");
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
			Long panchayatScopeId, List<Long> panchayatIdsList,Date fromdDate,Date toDate) {
		
		
		StringBuffer queryStr = new StringBuffer();
		
		
			queryStr.append("select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) and");
					
            if(tehsilScopeId != null && tehsilIdsList != null)		
            	   queryStr.append(" (model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
				    " model.file.locationValue in( :tehsilIdsList))");
		
		
		if(panchayatIdsList != null || panchayatIdsList != null)			
			queryStr.append("or (model.file.regionScopes.regionScopesId = :lowLevelLocationId and " +
				    " model.file.locationValue in( :panchayatIdsList))");
		
		if(panchayatIdsList != null && panchayatIdsList != null){
			
			 if(tehsilScopeId != null && tehsilIdsList != null)
				 queryStr.append(" or ");
			
			queryStr.append("(model.file.regionScopes.regionScopesId = :panchayatScopeId and " +
				    " model.file.locationValue in( :panchayatIdsList))");
		}
		
		if(fromdDate != null)
		 queryStr.append(" and date(model.file.fileDate) >= :fromDate");
		
		if(toDate != null)
		 queryStr.append(" and date(model.file.fileDate) <= :toDate ");	
		
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
		
		if(fromdDate != null)
		 query.setParameter("fromDate", fromdDate);
		if(toDate != null)
		 query.setParameter("toDate", toDate);
			
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
			List<Long> candidateIds ,Long muncipalityScopeId ,List<Long> muncipalityValuesList,Date fromDate,Date toDate){

		
		
		StringBuilder str = new StringBuilder();
		str.append(" select model.file.category.categoryType,count(*)," +
					"model.file.category.categoryId from FileGallary model " +
					" where model.gallary.candidate.candidateId in(:candidateIds) " +
					" and model.file.regionScopes.regionScopesId =:muncipalityScopeId and" +
					" model.file.locationValue in( :muncipalityValuesList) ");
		
		
		if(fromDate != null)
		 str.append(" and date(model.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.file.fileDate) <= :toDate ");	
			
		str.append(" group by model.file.category.categoryId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameterList("candidateIds", candidateIds);
		query.setParameter("muncipalityScopeId", muncipalityScopeId);
		query.setParameterList("muncipalityValuesList", muncipalityValuesList);
		if(fromDate != null)
		 query.setParameter("fromDate",fromDate);
		if(toDate != null)
		 query.setParameter("toDate",toDate);	
		
		return query.list();
		
		
	}
	
	
	public List<Object[]> getNewsCountForMuncipalityWithWards(NewsCountVO newsCountVO,Date fromDate,Date toDate){
		
		StringBuilder str = new StringBuilder();
		str.append("select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and (( model.file.regionScopes.regionScopesId =:muncipalityScopeId and" +
				" model.file.locationValue in( :muncipalityValuesList) " +
				" or "+
				" model.file.regionScopes.regionScopesId =:wardScopeId and" +
				" model.file.locationValue in( :wardIds))) ");
		
		if(fromDate != null)
		 str.append(" and date(model.file.fileDate) >= :fromDate ");
		
		if(toDate != null)
		 str.append(" and date(model.file.fileDate) <= :toDate ");
		
		str.append(" group by model.file.category.categoryId ");
	
		Query query = getSession().createQuery(str.toString());
		
		query.setParameterList("candidateIds", newsCountVO.getCandidateIds());
		query.setParameter("muncipalityScopeId", newsCountVO.getMuncipalityScopeId());
		query.setParameterList("muncipalityValuesList", newsCountVO.getMuncipalityValuesList());
		query.setParameter("wardScopeId", newsCountVO.getWardScopeId());
		query.setParameterList("wardIds",newsCountVO.getWardIdsList());
	    
		if(fromDate != null)
		 query.setParameter("fromDate",fromDate);
			
		if(toDate != null)
		 query.setParameter("toDate",toDate);
			
	    return query.list();
		
	}
	
	
	public List<Object[]> getNewsCountForWards(List<Long> candidateIds,
			Long wardScopeId, List<Long> wardValuesList,Date fromDate,Date toDate) {
		
	
		StringBuilder str = new StringBuilder();
		str.append(" select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and model.file.regionScopes.regionScopesId =:wardScopeId and" +
				" model.file.locationValue in( :wartdIds) ");
		if(fromDate != null)
		 str.append(" and date(model.file.fileDate) >= :fromDate ");
		if(toDate != null)
		 str.append(" and date(model.file.fileDate) <= :toDate ");
		
		str.append(" group by model.file.category.categoryId" );
		
	Query query = getSession().createQuery(str.toString());
	query.setParameterList("candidateIds", candidateIds);
	query.setParameter("wardScopeId", wardScopeId);
	query.setParameterList("wartdIds", wardValuesList);
	if(fromDate != null)
	 query.setParameter("fromDate",fromDate);
	if(toDate != null)
	 query.setParameter("toDate",toDate);
	
	return query.list();
		
		
	}
	
	
	public List<Object[]> getNewsCountForHamlets(List<Long> candidateIds,
			Long hamletScopeId, List<Long> hamletIds,Date fromDate,Date toDate)
	{
		
		
	StringBuilder str = new StringBuilder();
	str.append(" select model.file.category.categoryType,count(*)," +
				"model.file.category.categoryId from FileGallary model " +
				" where model.gallary.candidate.candidateId in(:candidateIds) " +
				" and model.file.regionScopes.regionScopesId =:hamletScopeId and" +
				" model.file.locationValue in( :hamletIds) ");
	if(fromDate != null)
	 str.append(" and date(model.file.fileDate) >= :fromDate ");
	if(toDate != null)
	 str.append(" and date(model.file.fileDate) <= :toDate ");
	
	str.append(" group by model.file.category.categoryId ");
	Query query = getSession().createQuery(str.toString());
	query.setParameterList("candidateIds", candidateIds);
	query.setParameter("hamletScopeId", hamletScopeId);
	query.setParameterList("hamletIds", hamletIds);
	
	if(fromDate != null)
	 query.setParameter("fromDate",fromDate);
	if(toDate != null)
	 query.setParameter("toDate",toDate);
	
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
	public List<File> getRecentlyUploadedNewsDetails(Integer starIndex, Integer maxResults)
	{
		Query query = getSession().createQuery("select distinct model from File model where model.isDeleted != 'Y' and model.isPrivate != 'Y' order by model.fileDate desc,model.updatedDate desc");
		
		if(starIndex != null)
		 query.setFirstResult(starIndex);
		if(maxResults != null)
		query.setMaxResults(maxResults);
		
		return query.list();
		
	}
	public Long getRecentlyUploadedNewsDetailsCount()
	{
		Query query = getSession().createQuery("select count(distinct model.fileId) from File model where model.isDeleted != 'Y'  and model.isPrivate != 'Y' ");
		
		
		return (Long)query.uniqueResult();
		
	}
	 public List<Object[]> getRecentlyUploadedNews(Integer starIndex, Integer maxResults,String contentType,Long partyId,String newsType)
	    {
	        StringBuilder queryObject = new StringBuilder();
	        queryObject.append(" select model.fileId,model.fileTitle,model.font.fontId from File model" );
	        queryObject.append(" where model.isDeleted != 'Y' and model.isPrivate != 'Y' order by model.fileDate desc,model.updatedDate desc");
	        Query query = getSession().createQuery(queryObject.toString());
	          query.setFirstResult(starIndex);
	         query.setMaxResults(maxResults);
	        return query.list();
	       
	    }
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getRecentlyUploadedNews(Integer starIndex, Integer maxResults,String contentType,Long partyId,String newsType)
	{
		StringBuilder queryObject = new StringBuilder();
		queryObject.append(" select model.fileGallaryId,model.file.fileTitle,fs.source.source from FileGallary model, PartyGallery model2,FileSourceLanguage fs " );
		queryObject.append(" where model.gallary.gallaryId = model2.gallery.gallaryId and model.file.fileId = fs.file.fileId and model2.party.partyId =:partyId and model.gallary.contentType.contentType =:contentType ");
		queryObject.append(" and model.isDelete = 'false' and model.gallary.isDelete = 'false' ");
		if(!newsType.equalsIgnoreCase(""))
			queryObject.append(" and model.isPrivate = 'false' and model.gallary.isPrivate = 'false' ");
		queryObject.append(" group by model.file.fileId order by model.updateddate desc ");
		Query query = getSession().createQuery(queryObject.toString());
		 query.setParameter("contentType", contentType);
		 query.setParameter("partyId", partyId);
		 query.setFirstResult(starIndex);
		 query.setMaxResults(maxResults);
		return query.list();
		
	}*/
	
	public FileGallary getSelectedNewsDetails(Long fileGallaryId)
	{
		Query query = getSession().createQuery(" select model from FileGallary model where model.fileGallaryId =:fileGallaryId ");
		query.setParameter("fileGallaryId", fileGallaryId);
		return (FileGallary) query.uniqueResult();
	}
	
	 @SuppressWarnings("unchecked")
     public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList , int startIndex  , int endIndex,String newsType)
     {
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList) and " );
		 queryObject.append(" model.isDelete = 'false' and model.gallary.isDelete = 'false' ");
		 if(!newsType.equalsIgnoreCase(""))
			 queryObject.append(" and model.isPrivate = 'false' and model.gallary.isPrivate = 'false' ");
		 queryObject.append(" group by model.file.fileId ");
		 Query query = getSession().createQuery(queryObject.toString());
    	 query.setParameterList("gallaryIdsList",gallaryIdsList);
    	 
    	 query.setFirstResult(startIndex);
    	 query.setMaxResults(endIndex);
    	 return query.list();
     }
	 
	 @SuppressWarnings("unchecked")
		public List<Long> getAllRecordCountInGallary(Long gallaryId){
			
			Query query = getSession().createQuery("select count(*) from FileGallary model where" +
					" model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? )");
			
			query.setParameter(0,gallaryId);
			query.setParameter(1,"false");
			query.setParameter(2,"false");				
			return query.list(); 
		}
	 
		@SuppressWarnings("unchecked")
		public List<Object[]> getGalleryIdForSelectedParty(Long partyId){
			
			Query query = getSession().createQuery("select model.file,model.gallary.name,model.fileGallaryId" +
					"  from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
					" order by model.file.fileId asc ");
			
			query.setParameter(0,partyId);
			query.setParameter(1,"false");
			query.setParameter(2,"false");				
			return query.list(); 
		}
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getAllVideoFilesOfInGallaries(int startIndex , int endIndex,String queryType)
		{
			StringBuilder query = new StringBuilder();
			 query.append("select  fs.file.fileId,fs.file.fileDescription,fps.filePath from FileGallary model , PartyGallery model2 , FileSourceLanguage fs,FilePaths fps where "+
			 " model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId and model2.party.partyId = :partyId "+
			 " and model2.gallery.contentType.contentType= :type and model.isDelete = :isDelete and fs.fileSourceLanguageId=fps.fileSourceLanguage.fileSourceLanguageId ");
			 
			 if(queryType.equalsIgnoreCase("Public"))
			 query.append(" and model.isPrivate = :isPrivate and model.gallary.isPrivate='false' and model.isPrivate ='false' ");

			 if(queryType.equalsIgnoreCase("Private"))
			 query.append(" and model.isPrivate = :isPrivate and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			 //query.append(" and model.file.locationValue = :locationValue and model.file.regionScopes.regionScopesId = :locId ");
			 query.append(" order by model.updateddate desc ");
			 Query queryObject = getSession().createQuery(query.toString());

			 queryObject.setLong("partyId", IConstants.TDPID);
			 queryObject.setString("type", IConstants.VIDEO_GALLARY);
			 queryObject.setString("isDelete", "false");
			 if(!queryType.equalsIgnoreCase(""))
				 queryObject.setString("isPrivate", "false");
			 queryObject.setFirstResult(startIndex);
			 queryObject.setMaxResults(endIndex);
			 
		return queryObject.list();
		}
		
		@SuppressWarnings("unchecked")
		public List<FileGallary> getAllVodeosForSelectedFile(Long fileId)
		{
			Query query = getSession().createQuery("select model from FileGallary model " +
					" where model.file.fileId = :fileId");
			query.setParameter("fileId", fileId);
			return query.list();
		}
		
		public List<Object[]> getVideoFileSourcesIdList(Long fileId)
		{
			Query query = getSession().createQuery("");
			
			return query.list();
		}
	 
	 public List<?> getPartyWiseAllNewsDetailsInLocation(List<Long> partyId, Long districtScopeId, List<Long> districtIds, Long constituencyScopeId, List<Long> constituencyVal,
				Long tehsilScopeId, List<Long> tehsilIds, Long hamletScopeId,
				List<Long> hamletIds,Long muncipalityScopeId ,List<Long> localElectionBodyIds,Long 
				wardScopeId,List<Long> wardIdsList,String queryType,int firstResult,int maxResult,String type,Date fromDate,Date toDate){
			StringBuilder query = new StringBuilder();
			if(type.equalsIgnoreCase("count"))
			{
				query.append("select count(distinct model.file.fileId) from FileGallary model , PartyGallery model2 , FileSourceLanguage fs where "+
						" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId and model2.party.partyId in (:partyId) "+
						" and model2.gallery.contentType.contentType= :type and model.isDelete = :isDelete ");
			}
			else 
			{
				query.append("select distinct model.file,model.fileGallaryId,model.file.fileDate,fs.source.source,model2.party.partyFlag,model2.party.partyId from FileGallary model , PartyGallery model2 , FileSourceLanguage fs where "+
						" model2.gallery.gallaryId=model.gallary.gallaryId and fs.file.fileId=model.file.fileId and model2.party.partyId in (:partyId) "+
						" and model2.gallery.contentType.contentType= :type and model.isDelete = :isDelete ");
			}
			
			if(fromDate != null)
			 query.append(" and date(model.file.fileDate) >= :fromDate ");
			if(toDate != null)
			 query.append(" and date(model.file.fileDate) <= :toDate ");
			
			if(queryType.equalsIgnoreCase("Public"))
					query.append(" and model.isPrivate = :isPrivate  and model.gallary.isPrivate='false' and model.isPrivate ='false' ");

			if(queryType.equalsIgnoreCase("Private"))
					query.append(" and model.isPrivate = :isPrivate  and ( (model.gallary.isPrivate='true') or(model.gallary.isPrivate='false' and model.isPrivate ='true') ) ");
			
			query.append(" and ( ");
			if(districtIds!= null)
				query.append(" (model.file.regionScopes.regionScopesId = :districtScopeId and " +
						" model.file.locationValue in (:districtIds)) or ");
			if(constituencyVal!= null)
				query.append(" (model.file.regionScopes.regionScopesId = :constituencyScopeId and " +
						" model.file.locationValue in (:constituencyVal))  ");
			if(tehsilIds!=null && tehsilIds.size()>0)
				query.append(" or (model.file.regionScopes.regionScopesId = :tehsilScopeId and " +
						" model.file.locationValue in ( :tehsilIds)) ");
			if(localElectionBodyIds!=null && localElectionBodyIds.size()>0)
				query.append(" or (model.file.regionScopes.regionScopesId = :muncipalityScopeId and " +
						" model.file.locationValue in ( :localElectionBodyIds)) ");
			if(wardIdsList !=null && wardIdsList.size()>0)
				query.append(" or (model.file.regionScopes.regionScopesId = :wardScopeId and " +
						" model.file.locationValue in ( :wardIdsList)) ");
			if(hamletIds !=null && (hamletIds.size() > 0 && hamletIds.size() <= 5000))
				query.append(" or (model.file.regionScopes.regionScopesId = :hamletScopeId and " +
						" model.file.locationValue in (:hamletIds)) ");
			if(!type.equalsIgnoreCase("count"))	
				query.append(" ) group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc  ");
			if(type.equalsIgnoreCase("count"))	
				query.append(" )");
			Query queryObject = getSession().createQuery(query.toString());

			queryObject.setParameterList("partyId", partyId);
			if(districtIds!= null){
				queryObject.setParameterList("districtIds",districtIds);
				queryObject.setLong("districtScopeId", districtScopeId);				
			}
			if(constituencyVal!= null){
				queryObject.setParameterList("constituencyVal",constituencyVal);
				queryObject.setLong("constituencyScopeId", constituencyScopeId);
			}
			if(tehsilIds!=null && tehsilIds.size()>0){
				queryObject.setLong("tehsilScopeId",tehsilScopeId);
				queryObject.setParameterList("tehsilIds", tehsilIds);
			}
			if(localElectionBodyIds!=null && localElectionBodyIds.size()>0){
				queryObject.setLong("muncipalityScopeId",muncipalityScopeId);
				queryObject.setParameterList("localElectionBodyIds", localElectionBodyIds);
			}
			if(wardIdsList !=null && wardIdsList.size()>0){
				queryObject.setLong("wardScopeId",wardScopeId);
				queryObject.setParameterList("wardIdsList", wardIdsList);
			}
			if(hamletIds !=null && (hamletIds.size() > 0 && hamletIds.size() <= 5000)){
				queryObject.setLong("hamletScopeId",hamletScopeId);
				queryObject.setParameterList("hamletIds", hamletIds);
			}

			queryObject.setString("type", IConstants.NEWS_GALLARY);
			queryObject.setString("isDelete", "false");
			if(!queryType.equals(""))
			queryObject.setString("isPrivate", "false");
			if(!type.equalsIgnoreCase("count"))
			{
				queryObject.setFirstResult(firstResult);
				queryObject.setMaxResults(maxResult);
			}
			
			if(fromDate != null)
			 queryObject.setParameter("fromDate", fromDate);
			
			if(toDate != null)
			 queryObject.setParameter("toDate", toDate);
			
			return queryObject.list();
			}
	
	 @SuppressWarnings("unchecked")
     public List<FileGallary> getFilesOfInGallaries(List<Long> gallaryIdsList)
     {
    	 Query query = getSession().createQuery("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList) and model.isPrivate = 'false' and model.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' " +
    	 		" group by model.file.fileId");
    	 query.setParameterList("gallaryIdsList",gallaryIdsList);
    	 return query.list();
     }
	 
	 public List<Object[]> getVideosOfGalleryId(Long gallaryId,int maxRcrd,int firstRcrd){
			
		 Query query = getSession().createQuery("select model.filePath,model1.file.fileId,model1.file.fileTitle,model1.file.fileDescription from FilePaths model,FileGallary model1 where model.fileSourceLanguage.file.fileId "+
					" = model1.file.fileId and model1.gallary.gallaryId = ? and model1.isDelete = ? and model1.isPrivate = ? group by model.filePathsId ");
			query.setParameter(0,gallaryId);
			query.setParameter(1,"false");
			query.setParameter(2,"false");
			query.setFirstResult(firstRcrd);
			query.setMaxResults(maxRcrd);
			return query.list(); 
	}
	 public int getVideosCountOfGalleryId(Long galleryId){
		 Query query = getSession().createQuery("select count(model.filePath) from FilePaths model,FileGallary model1 where model.fileSourceLanguage.file.fileId "+
					" = model1.file.fileId and model1.gallary.gallaryId = ? and model1.isDelete = ? and model1.isPrivate = ? ");
			query.setParameter(0,galleryId);
			query.setParameter(1,"false");
			query.setParameter(2,"false");				
			return ((Long)query.uniqueResult()).intValue(); 
	 }
	 public Long getVideosCountIntheGallary(Long galleryId,String queryType){
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append(" select count(model.fileSourceLanguage.fileSourceLanguageId) from FilePaths model,FileGallary model1 where " );
		 queryObject.append(" model.fileSourceLanguage.file.fileId  = model1.file.fileId and model1.gallary.gallaryId = ? and model1.isDelete = ? ");
		 if(!queryType.equalsIgnoreCase(""))
			 queryObject.append(" and model1.isPrivate = ? ");
		 queryObject.append(" group by model1.gallary.gallaryId ");
		 Query query = getSession().createQuery(queryObject.toString());
		    query.setParameter(0,galleryId);
			query.setParameter(1,"false");
			if(!queryType.equalsIgnoreCase(""))
				query.setParameter(2,"false");				
			return (Long)query.uniqueResult();
	 }
	
	 
	 public List<Object[]> getAllTheNewsForAUser(Long userId,Date fromDate,Date toDate)
	 {
		 StringBuilder str = new StringBuilder();
		 str.append("select model.fileGallaryId ,model.file,model.isPrivate,model.gallary.gallaryId, model.gallary.name from FileGallary model  where model.isDelete = :isDelete ");
		 str.append(" and model.file.fileId in(select distinct model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0  and model1.file.user.userId = :userId) and model.file.user.userId = :userId ");
		 
		 if(fromDate != null)
		  str.append(" and date(model.file.fileDate) >= :fromDate ");
		 if(toDate != null)
		  str.append(" and date(model.file.fileDate) <= :toDate ");
		 
		 str.append(" order by model.file.fileDate desc ");
		 Query query = getSession().createQuery(str.toString());
		 
		 query.setParameter("userId", userId);
		 query.setParameter("isDelete", "false");
		 if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		 if(toDate != null)
		  query.setParameter("toDate", toDate);
		 
		 query.setFirstResult(0);
		 query.setMaxResults(300);
		 
		 return query.list();
		 
	 }
	 
	@SuppressWarnings("unchecked")
	public List<FileGallary> getFileGallariesByFileGallaryIdsList(List<Long> fileGallaryIdsList)
	{
		Query query = getSession().createQuery(" select model from FileGallary model where model.fileGallaryId in(:fileGallaryIdsList) order by model.file.fileDate desc ");
		query.setParameterList("fileGallaryIdsList", fileGallaryIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<FileGallary> getFilesOfGallaries(List<Long> gallaryIdsList , int startIndex  , int endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor)
    {
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append("select model from FileGallary model,CandidateNewsResponse model1 where model.gallary.gallaryId in(:gallaryIdsList) and " );
		 queryObject.append(" model.isDelete = 'false' and model.gallary.isDelete = 'false' ");
		 if(categoryId != 0)
			 queryObject.append(" and model.file.category.categoryId = :categoryId ");
		 if(!newsType.equalsIgnoreCase(""))
			 queryObject.append(" and model.isPrivate = 'false' and model.gallary.isPrivate = 'false' ");
		 
		 if(!requestFor.equalsIgnoreCase("null")){
			 queryObject.append(" and model.fileGallaryId= model1.fileGallary.fileGallaryId ");
		 }
		 
		 if(fromDate != null)
			  queryObject.append(" and date(model.file.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
		      queryObject.append(" and date(model.file.fileDate) <= :toDate ");
				 
			 
		 queryObject.append(" group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc");
		 Query query = getSession().createQuery(queryObject.toString());
   	 query.setParameterList("gallaryIdsList",gallaryIdsList);
   	 if(categoryId != 0)
   		 query.setParameter("categoryId", categoryId);
   	 
   	if(fromDate != null)
   		query.setParameter("fromDate",fromDate);
		 
	if(toDate != null)
	 query.setParameter("toDate",toDate);
   	 
   	 query.setFirstResult(startIndex);
   	 query.setMaxResults(endIndex);
   	 return query.list();
    }
	
	 @SuppressWarnings("unchecked")
		public List<Long> getAllRecordsCountInGallary(Long gallaryId,String newsType,Long categoryId,Date fromDate,Date toDate){
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append("select count(*) from FileGallary model where model.gallary.gallaryId = :gallaryId " +
		 		"and model.isDelete = :isDelete ");
		 if(categoryId != 0)
			 queryObject.append(" and model.file.category.categoryId = :categoryId ");
		 if(!newsType.equalsIgnoreCase(""))
			 queryObject.append(" and model.isPrivate = :isPrivate ");
		
		 if(fromDate != null)
		  queryObject.append(" and date(model.file.fileDate) >= :fromDate ");
		 
		 if(toDate != null)
		  queryObject.append(" and date(model.file.fileDate) <= :toDate ");
		 
		 Query query = getSession().createQuery(queryObject.toString());
		 query.setParameter("gallaryId",gallaryId);
		 query.setParameter("isDelete","false");
		 if(categoryId != 0)
			 query.setParameter("categoryId",categoryId);
		 if(!newsType.equalsIgnoreCase(""))
			 query.setParameter("isPrivate","false");
		 
		 if(fromDate != null)
		  query.setParameter("fromDate",fromDate);
			 
		if(toDate != null)
		 query.setParameter("toDate",toDate);
		 
		return query.list(); 
		}
	 
	 
	 @SuppressWarnings("unchecked")
	 public List<File> getNewsDetailsBetweenSelectedDates(Date fromDate,Date toDate, Integer starIndex, Integer maxResults, Long scopeId,Long stateId)

	 {

	 StringBuilder stringBuilder = new StringBuilder();

	 stringBuilder.append("select distinct model.file from UserAddress model where model.file.isDeleted != 'Y' and model.file.isPrivate != 'Y' ");

	 if(fromDate != null)
	 stringBuilder.append(" and date(model.file.fileDate) >= :fromDate ");
	 if(toDate != null)
	 stringBuilder.append(" and date(model.file.fileDate) <= :toDate ");
	 if(scopeId > 0)
	 stringBuilder.append(" and model.file.regionScopes.regionScopesId = :scopeId ");
	 if(stateId>0)
	 stringBuilder.append(" and model.state.stateId=:stateId");

	 stringBuilder.append(" order by date(model.file.fileDate) desc,model.file.updatedDate desc");

	 Query query = getSession().createQuery(stringBuilder.toString());

	 if(fromDate != null)
	 query.setParameter("fromDate", fromDate);

	 if(toDate != null)
	 query.setParameter("toDate", toDate);

	 if(starIndex != null)
	 query.setFirstResult(starIndex);
	 if(maxResults != null)
	 query.setMaxResults(maxResults);
	 if(scopeId > 0)
	 query.setLong("scopeId", scopeId);
	 if(stateId>0)
	 query.setParameter("stateId", stateId);
	 return query.list();

	 }

	 
	 public Long getNewsDetailsBetweenSelectedDatesCount(Date fromDate,Date toDate, long scopeId,Long stateId)
	 {
	 StringBuilder stringBuilder = new StringBuilder();

	 stringBuilder.append("select distinct count(model.file.fileId) from UserAddress model where model.file.isDeleted != 'Y' and model.file.isPrivate != 'Y' ");

	 if(fromDate != null)
	 stringBuilder.append(" and date(model.file.fileDate) >= :fromDate ");
	 if(toDate != null)
	 stringBuilder.append(" and date(model.file.fileDate) <= :toDate ");
	 if(scopeId > 0)
	 stringBuilder.append(" and model.file.regionScopes.regionScopesId = :scopeId ");
	 if(stateId>0)
	 stringBuilder.append(" and model.state.stateId = :stateId ");

	 Query query = getSession().createQuery(stringBuilder.toString());

	 if(fromDate != null)
	 query.setParameter("fromDate", fromDate);

	 if(toDate != null)
	 query.setParameter("toDate", toDate);
	 if(scopeId > 0)
	 query.setLong("scopeId", scopeId);
	 if(stateId>0)
	 query.setParameter("stateId", stateId);

	 return (Long)query.uniqueResult();

	 }
	@SuppressWarnings("unchecked")
	public List<Object[]> getGalleriesByCategoryIds(List<Long> categoryIdsList,Long partyId,Long candidateId)
	 {
		 Query query = getSession().createQuery(" select distinct model.fileGallary.gallary.gallaryId,model.fileGallary.gallary.name from CandidateRealatedNews model, PartyGallery model2 " +
		 		" where model.fileGallary.gallary.gallaryId = model2.gallery.gallaryId and model.candidate.candidateId =:candidateId and model2.party.partyId =:partyId  " +
		 		"  and model.fileGallary.file.category.categoryId in (:categoryIdsList) and model.fileGallary.gallary.contentType.contentType =:contentType " +
		 		"  and model.fileGallary.isDelete ='false' and model.fileGallary.gallary.isDelete ='false' and model.fileGallary.isPrivate ='false' and " +
		 		"  model.fileGallary.gallary.isPrivate ='false' order by model.fileGallary.gallary.name ");
		 
		 query.setParameterList("categoryIdsList", categoryIdsList);
		 query.setParameter("contentType", IConstants.NEWS_GALLARY);
		 query.setParameter("partyId", partyId);
		 query.setParameter("candidateId", candidateId);
		 
		 return query.list();
	 }
	
	public List<Object[]> getAllFilesOfAGallaryByGallaryId(Long gallaryId , Date fromDate , Date toDate)
	 {
		 Query query = getSession().createQuery("select model.file.fileId , model.file.fileTitle , model.file from FileGallary model " +
		 		" where model.gallary.gallaryId = :gallaryId and date(model.file.fileDate) >= :fromDate and date(model.file.fileDate) <= :toDate");
		 
		 query.setParameter("gallaryId", gallaryId);
		 query.setParameter("fromDate", fromDate);
		 query.setParameter("toDate", toDate);
		 
		 return query.list();
		 
	 }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getnewsListByGalleryId(Long galleryId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.fileGallaryId,model.file.fileTitle,model.file from FileGallary model where model.gallary.gallaryId =:gallaryId ");
		str.append(" and model.isPrivate = 'false' and model.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false' ");
		if(fromDate != null)
		 str.append(" and model.file.fileDate >= :fromDate ");
		if(toDate != null)
		 str.append(" and model.file.fileDate <= :toDate ");
		str.append(" order by model.file.fileTitle ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("gallaryId", galleryId);
		if(fromDate != null)
		  query.setParameter("fromDate", fromDate);
		if(toDate != null)
		  query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getFileGallaryIdsListByGallaryId(Long gallaryId)
	{
		Query query = getSession().createQuery(" select distinct model.fileGallaryId from FileGallary model where model.gallary.gallaryId =:gallaryId ");
		query.setParameter("gallaryId", gallaryId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getLocationValuesByRegionScopeId(Long regionScopeId, String queryType,Long partyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.file.locationValue from FileGallary model, PartyGallery model2 where model.gallary.gallaryId = model2.gallery.gallaryId and model.file.regionScopes.regionScopesId =:regionScopesId ");
		str.append(" and model2.party.partyId =:partyId and model.gallary.contentType.contentType =:contentType and model.isDelete = 'false' and model.gallary.isDelete = 'false' ");
		if(queryType != null && queryType.equalsIgnoreCase("public"))
		 str.append(" and model.isPrivate = 'false' and model.gallary.isPrivate = 'false' ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("regionScopesId", regionScopeId);
		query.setParameter("partyId", partyId);
		query.setParameter("contentType", IConstants.NEWS_GALLARY);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
    public List<FileGallary> getFilesByGalleryIdsList(List<Long> gallaryIdsList , Integer startIndex  , Integer endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor)
    {
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList) and " );
		 queryObject.append(" model.isDelete = 'false' and model.gallary.isDelete = 'false' ");
		 if(categoryId != null && categoryId > 0)
			 queryObject.append(" and model.file.category.categoryId = :categoryId ");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 queryObject.append(" and model.isPrivate = 'false' and model.gallary.isPrivate = 'false' ");
		 
		 /*if(!requestFor.equalsIgnoreCase("null")){
			 queryObject.append(" and model.fileGallaryId= model1.fileGallary.fileGallaryId ");
		 }*/
		 
		 if(fromDate != null)
			  queryObject.append(" and date(model.file.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
		      queryObject.append(" and date(model.file.fileDate) <= :toDate ");
				 
			 
		 queryObject.append(" group by model.file.fileId order by model.file.fileDate desc, model.updateddate desc");
		 Query query = getSession().createQuery(queryObject.toString());
   	 query.setParameterList("gallaryIdsList",gallaryIdsList);
   	 if(categoryId != 0)
   		 query.setParameter("categoryId", categoryId);
   	 
   	if(fromDate != null)
   		query.setParameter("fromDate",fromDate);
		 
	if(toDate != null)
	 query.setParameter("toDate",toDate);
   	 
	if(startIndex != null)
   	 query.setFirstResult(startIndex);
	if(endIndex != null)
   	 query.setMaxResults(endIndex);
   	 return query.list();
    }
	
	
	@SuppressWarnings("unchecked")
    public List<FileGallary> getFileGallaryByGalleryIdsList(List<Long> gallaryIdsList , Integer startIndex  , Integer endIndex,String newsType,Long categoryId,Date fromDate,Date toDate,String requestFor)
    {
		 StringBuffer queryObject = new StringBuffer();
		 queryObject.append("select model.fileGallary from CandidateNewsResponse model where model.fileGallary.gallary.gallaryId in(:gallaryIdsList) and " );
		 queryObject.append(" model.fileGallary.isDelete = 'false' and model.fileGallary.gallary.isDelete = 'false' ");
		 if(categoryId != null && categoryId > 0)
			 queryObject.append(" and model.fileGallary.file.category.categoryId = :categoryId ");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 queryObject.append(" and model.fileGallary.isPrivate = 'false' and model.fileGallary.gallary.isPrivate = 'false' ");
		 
		 if(fromDate != null)
			  queryObject.append(" and date(model.fileGallary.file.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
		      queryObject.append(" and date(model.fileGallary.file.fileDate) <= :toDate ");
				 
			 
		 queryObject.append(" group by model.fileGallary.file.fileId order by model.fileGallary.file.fileDate desc, model.fileGallary.updateddate desc");
		 Query query = getSession().createQuery(queryObject.toString());
   	 query.setParameterList("gallaryIdsList",gallaryIdsList);
   	 if(categoryId != 0)
   		 query.setParameter("categoryId", categoryId);
   	 
   	if(fromDate != null)
   		query.setParameter("fromDate",fromDate);
		 
	if(toDate != null)
	 query.setParameter("toDate",toDate);
   	 
	if(startIndex != null)
   	 query.setFirstResult(startIndex);
	if(endIndex != null)
   	 query.setMaxResults(endIndex);
   	 return query.list();
    }
	
	 public List<Object[]> getAllTheNewsForAUserBasedOnUserAddressId(Long userId,Date fromDate,Date toDate,Long regionLevel,Long importanceId)
	 {
	 StringBuilder str = new StringBuilder();
	 str.append("select model.file,model.fileGallaryId from FileGallary model where model.isDelete ='false' and model.file.user.userId =:userId ");
	 //str.append("and model.file.fileId in(select model1.file.fileId from FileSourceLanguage model1 where model1.file.fileId != 0 and model1.file.user.userId = :userId) and model.file.user.userId = :userId ");
	 str.append("and model.file.regionScopes.regionScopesId = :regionLevel and model.file.newsImportance.newsImportanceId = :importanceId ");
	 if(fromDate != null)
	 str.append("and date(model.file.fileDate) >= :fromDate ");
	 if(toDate != null)
	 str.append("and date(model.file.fileDate) <= :toDate ");
	 
	 str.append("order by model.file.fileDate desc ");
	 Query query = getSession().createQuery(str.toString());
	 query.setParameter("userId", userId);
	 if(fromDate != null)
	  query.setParameter("fromDate", fromDate);
	 if(toDate != null)
	  query.setParameter("toDate", toDate);
	 query.setParameter("regionLevel", regionLevel);
	 query.setParameter("importanceId", importanceId);
	 query.setFirstResult(0);
	 query.setMaxResults(300);
	 return query.list();

	 }
	 public List<Object[]> getNewsByLocationWise(Long locationValue,Long locationScope,Long userId)
		{
			Query query = getSession().createQuery("select model.file,model.fileGallaryId from FileGallary model where model.file.regionScopes.regionScopesId = :locationScope " +
					"and model.file.locationValue =:locationValue and model.file.user.userId = :userId and model.file.userAddress.userAddressId != null ");
			query.setParameter("locationScope", locationScope);
			query.setParameter("locationValue", locationValue);
			query.setParameter("userId", userId);
			return query.list();
			
		}
		
		 
	 
	 @SuppressWarnings("unchecked")
	 public List<FileGallary> getNewsForSelectedKeyWord(String keyWord,Long partyId,String newsType,Integer startIndex,Integer maxIndex)
	 {
		  StringBuilder str = new StringBuilder();
		  str.append(" select model from FileGallary model, PartyGallery model2 where model.gallary.gallaryId = model2.gallery.gallaryId and " +
		  		" model2.party.partyId =:partyId and model.file.keywords like '%"+keyWord+"%' and model.isDelete = 'false' and model2.isDelete = 'false' " +
		  				" and model.gallary.isDelete = 'false' and model2.gallery.isDelete = 'false' ");
		  if(!newsType.equalsIgnoreCase("") || newsType.equalsIgnoreCase("Public"))
		   str.append(" and model.isPrivate = 'false' and model2.isPrivate = 'false' and model.gallary.isPrivate = 'false' and model2.gallery.isPrivate = 'false' ");
		  
		  Query query = getSession().createQuery(str.toString());
		  query.setParameter("partyId", partyId);
		 
		  if(startIndex != null)
		    query.setFirstResult(startIndex);
		  if(maxIndex != null)
		    query.setMaxResults(maxIndex);
		  
		  return query.list();
	}
	 public Integer deleteDefaultGallaries(List<Long> fileIds)
	 {
		 Query query = getSession().createQuery("delete from FileGallary model where model.file.fileId in (:fileIds) and model.gallary.gallaryId = 1 ");
		 query.setParameterList("fileIds", fileIds);
		return query.executeUpdate();
	 }
	 
	 public Long checkFileGallaryExist(Long gallaryId,Long fileId)
	 {

		 Query query = getSession().createQuery("select model.fileGallaryId from FileGallary model where model.gallary.gallaryId =:gallaryId and model.file.fileId=:fileId ");
		 query.setParameter("gallaryId", gallaryId); 
		 query.setParameter("fileId", fileId);
		return (Long) query.uniqueResult(); 
	 }
 
	 @SuppressWarnings("unchecked")
		public List<Long> getLocationValuesByRegionScopeId1(Long regionScopeId, String queryType,Long partyId)
		{
		 StringBuilder str = new StringBuilder();
			str.append(" select distinct model.userAddress.district.districtId from File model where " +
					"model.regionScopes.regionScopesId =:regionScopesId and model.isDeleted != 'Y' and " +
					"model.isPrivate != 'Y' ");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("regionScopesId", regionScopeId);
			return query.list();
		}
	 
	 @SuppressWarnings("unchecked")
		public List<Long> getLocationValuesByRegionScopeId2(Long regionScopeId, String queryType,Long partyId)
		{
		 StringBuilder str = new StringBuilder();
			str.append("select distinct model.userAddress.constituency.constituencyId from File model where " +
					"model.regionScopes.regionScopesId =:regionScopesId and model.isDeleted != 'Y' and " +
					"model.isPrivate != 'Y' ");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("regionScopesId", regionScopeId);
			return query.list();
		}
	 
	/*@SuppressWarnings("unchecked")
	public List<File> getNewsCountForALocation1(Long locationId,Integer startRecord,Integer maxRecord,String queryType,Date fromDateStr,Date toDateStr)
	 {
		 StringBuilder str = new StringBuilder();
		  	str.append("select model from File model where  model.isDeleted != 'Y' ");
		 if(queryType.equalsIgnoreCase("District"))
			 str.append(" and model.userAddress.district.districtId = :locationId ");
		 if(queryType.equalsIgnoreCase("Constituency"))
			 str.append(" and model.userAddress.constituency.constituencyId = :locationId "); 
		 if(fromDateStr != null)
			 str.append(" and date(model.fileDate) >= :fromDateStr");
	 	 if(toDateStr != null)
	 		 str.append(" and date(model.fileDate) <= :toDateStr");
	 	 
	 	 str.append(" order by model.fileDate desc");
	 	 Query query = getSession().createQuery(str.toString());
		 query.setParameter("locationId", locationId);
		 if(fromDateStr != null)
			 query.setParameter("fromDateStr", fromDateStr);
	 	 if(toDateStr != null)
	 		query.setParameter("toDateStr", toDateStr);
	 	 
		    query.setFirstResult(startRecord);
		    query.setMaxResults(maxRecord);
		  
		 return query.list();
	 }*/
	 
	 @SuppressWarnings("unchecked")
		public Long getNewsTotalCountForALocation1(Long locationId,Integer startRecord,Integer maxRecord,String queryType,Date fromDateStr,Date toDateStr)
		 {
			 StringBuilder str = new StringBuilder();
				 str.append("select count(*) from UserAddress model where  model.file.isDeleted != 'Y' ");
			 if(queryType.equalsIgnoreCase("District"))
				 str.append(" and model.district.districtId = :locationId ");
			 if(queryType.equalsIgnoreCase("Constituency"))
				 str.append(" and model.constituency.constituencyId = :locationId "); 
			 if(fromDateStr != null)
				 str.append(" and date(model.file.fileDate) >= :fromDateStr");
		 	 if(toDateStr != null)
		 		 str.append(" and date(model.file.fileDate) <= :toDateStr");
		 	 
		 	 str.append(" order by model.file.fileDate desc");
		 	 Query query = getSession().createQuery(str.toString());
			 query.setParameter("locationId", locationId);
			 if(fromDateStr != null)
				 query.setParameter("fromDateStr", fromDateStr);
		 	 if(toDateStr != null)
		 		query.setParameter("toDateStr", toDateStr);
		 	 
			 return (Long)query.uniqueResult();
		 }
	 
	 @SuppressWarnings("unchecked")
		public List<Object[]> getLocationValuesCountByRegionScopeId2(Long regionScopeId, String queryType,Long partyId)
		{
		 StringBuilder str = new StringBuilder();
			str.append("select  count(distinct model.file.fileId),model.constituency.constituencyId from UserAddress model where " +
					" model.file.isDeleted != 'Y' and " +
					"model.file.isPrivate != 'Y' group by model.constituency.constituencyId");
			Query query = getSession().createQuery(str.toString());
			//query.setParameter("regionScopesId", regionScopeId);
			return query.list();
		}
		 @SuppressWarnings("unchecked")
			public List<Object[]> getLocationValuesCountByRegionScopeId1(Long regionScopeId, String queryType,Long partyId)
			{
			 StringBuilder str = new StringBuilder();
				str.append(" select  count(distinct model.file.fileId),model.district.districtId from UserAddress model where " +
						"  model.file.isDeleted != 'Y' and " +
						"model.file.isPrivate != 'Y' group by model.district.districtId");
				Query query = getSession().createQuery(str.toString());
				//query.setParameter("regionScopesId", regionScopeId);
				return query.list();
			}
			@SuppressWarnings("unchecked")
				public List<UserAddress> getNewsCountForALocation1(Long locationId,Integer startRecord,Integer maxRecord,String queryType,Date fromDateStr,Date toDateStr)
				 {
					 StringBuilder str = new StringBuilder();
					  	str.append("select model from UserAddress model where  model.file.isDeleted != 'Y' ");
					 if(queryType.equalsIgnoreCase("District"))
						 str.append(" and model.district.districtId = :locationId ");
					 if(queryType.equalsIgnoreCase("Constituency"))
						 str.append(" and model.constituency.constituencyId = :locationId "); 
					 if(fromDateStr != null)
						 str.append(" and date(model.file.fileDate) >= :fromDateStr");
				 	 if(toDateStr != null)
				 		 str.append(" and date(model.file.fileDate) <= :toDateStr");
				 	 
				 	 str.append(" order by model.file.fileDate desc");
				 	 Query query = getSession().createQuery(str.toString());
					 query.setParameter("locationId", locationId);
					 if(fromDateStr != null)
						 query.setParameter("fromDateStr", fromDateStr);
				 	 if(toDateStr != null)
				 		query.setParameter("toDateStr", toDateStr);
				 	 
					    query.setFirstResult(startRecord);
					    query.setMaxResults(maxRecord);
					  
					 return query.list();
				 }
	 
}
