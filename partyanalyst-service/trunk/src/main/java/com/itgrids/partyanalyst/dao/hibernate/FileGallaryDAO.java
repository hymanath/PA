package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.Request;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.utils.IConstants;
public class FileGallaryDAO extends GenericDaoHibernate<FileGallary, Long> implements IFileGallaryDAO{
	
	public FileGallaryDAO(){
		super(FileGallary.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartingRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath ,model.file.fileTitle " +
				" from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
				" order by model.file.fileId asc ");
		
		query.setParameter(0,gallaryId);
		query.setParameter(1,"false");
		query.setParameter(2,"false");
		query.setMaxResults(1);
		
		return query.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllRecordInGallary(Long gallaryId){
		
		Query query = getSession().createQuery("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription,  " +
				" model.gallary.name,model.fileGallaryId from FileGallary model where model.gallary.gallaryId = ? and model.isDelete = ? and model.isPrivate = ? "+
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
				" and model.gallary.contentType.contentType= :type  and model.isDelete = :isDelete and model.isPrivate = :isPrivate order by model.createdDate desc");
		
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
			query.append("   and model.file.sourceObj.source =:spScope");
		if(languageStr!=null)
			query.append("   and model.file.language.language =:spScopeLang");
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
		query.append("select model.file.fileId,model.file.fileName,model.file.filePath,model.file.fileTitle,model.file.fileDescription , " +
				" model.file.sourceObj.source ,model.file.language.language ,model.createdDate,model.gallary.candidate.candidateId , model.file.newsImportance.newsImportanceId , model.file.newsImportance.importance    " +
				" from FileGallary model where model.gallary.candidate.candidateId =:candidateId "+
				"  and model.gallary.contentType.contentType= :type  and model.isDelete = 'false'  " +
				" and model.gallary.isDelete = 'false'  ");

		if(scopeType!=null)
			query.append("   and model.file.regionScopes.regionScopesId =:scopeType   ");
		
		if(sourceStr!=null)
			query.append("   and model.file.sourceObj.source =:spScope");
		
		if(languageStr!=null)
			query.append("   and model.file.language.language =:spScopeLang");
		
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
				" and model2.gallery.contentType.contentType= :type) and model.isDelete = :isDelete and model.isPrivate = :isPrivate order by model.updateddate desc");
		
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
	public List<FileGallary> getFileGallaryByFileIdsList(List<Long> fileIdsList)
	{
		Query query = getSession().createQuery("select model from FileGallary model where model.file.fileId in (:fileIdsList) and model.isDelete = 'false' " +
				"and model.isPrivate = 'false' order by model.file.fileDate desc,model.updateddate desc");
		query.setParameterList("fileIdsList",fileIdsList);
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
 		query.append("select model.file from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
 				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
 		
 		if(fileVO.getExistingFrom() != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(fileVO.getIdentifiedOn() != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 			
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId");
 		
 		if(fileVO.getLanguegeId() != null)
			query.append(" and model.file.language.languageId = :languageId");
 		
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
		
		queryObject.setLong("registrationId", fileVO.getCandidateId());
		
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
  		 query.append("select  count(*),model.file.category.categoryType,model.file.category.categoryId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
  		
  		if(fromDate != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(toDate != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 		
 		if(fileType.trim().equalsIgnoreCase("Public"))
			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(fileType.trim().equalsIgnoreCase("Private"))
			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 			
  		query.append(" group by  model.file.category.categoryType   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  count(*),model.file.sourceObj.source,model.file.sourceObj.sourceId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.sourceObj.source   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  count(*),model.file.language.language,model.file.language.languageId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.language.language   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  count(*),model.file.newsImportance.importance,model.file.newsImportance.newsImportanceId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.newsImportance.importance   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  count(*),model.file.regionScopes.scope,model.file.regionScopes.regionScopesId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" group by  model.file.regionScopes.regionScopesId   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
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
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  date(model.file.fileDate),model.file.category.categoryType,model.file.category.categoryId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
  		
  		if(fromDate != null)
 			query.append(" and date(model.file.fileDate) >= :fromDate");
 			
 		if(toDate != null)
 			query.append(" and date(model.file.fileDate) <= :toDate");
 		
 		if(fileType.trim().equalsIgnoreCase("Public"))
			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
			
		if(fileType.trim().equalsIgnoreCase("Private"))
			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
		
		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
  		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select date(model.file.fileDate),model.file.sourceObj.source,model.file.sourceObj.sourceId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  date(model.file.fileDate),model.file.language.language,model.file.language.languageId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
  		 query.append("select  date(model.file.fileDate),model.file.newsImportance.importance,model.file.newsImportance.newsImportanceId from FileGallary model  where model.gallary.candidate.candidateId in(select model1.candidate.candidateId from UserCandidateRelation model1 " +
  				" where model1.registration.registrationId = :registrationId) and model.gallary.contentType.contentType= :type and model.isDelete = 'false' and model.gallary.isDelete = 'false'  ");
   		
   		if(fromDate != null)
  			query.append(" and date(model.file.fileDate) >= :fromDate");
  			
  		if(toDate != null)
  			query.append(" and date(model.file.fileDate) <= :toDate");
  		
  		if(fileType.trim().equalsIgnoreCase("Public"))
 			query.append(" and model.gallary.isPrivate='false' and model.isPrivate ='false'  ");
 			
 		if(fileType.trim().equalsIgnoreCase("Private"))
 			query.append(" and ((model.gallary.isPrivate='true') or (model.gallary.isPrivate='false' and model.isPrivate ='true'))");
 		
 		if(fileVO.getSourceId() != null)
 			query.append(" and model.file.sourceObj.sourceId = :sourceId ");
 		
 		if(fileVO.getLanguegeId() != null)
 			query.append(" and model.file.language.languageId = :languageId ");
 		
 		if(fileVO.getCategoryId() != null)
 			query.append(" and model.file.category.categoryId = :categoryId ");
 		
 		if(fileVO.getNewsImportanceId() != null)
 			query.append(" and model.file.newsImportance.newsImportanceId = :newsImportanceId ");
 		
 		query.append(" order by  model.file.fileDate   ");
  		Query queryObject = getSession().createQuery(query.toString());
  		queryObject.setLong("registrationId", regId);
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
    	 Query query = getSession().createQuery("select model from FileGallary model where model.gallary.gallaryId in(:gallaryIdsList) and model.isPrivate = 'false' and model.isDelete = 'false'");
    	 query.setParameterList("gallaryIdsList",gallaryIdsList);
    	 return query.list();
     }
     
     @SuppressWarnings("unchecked")
     public List<Object[]> getFirstFileAndGallaryInfo(Long gallaryId)
     {
    	 Query query = getSession().createQuery("select model.gallary.name,model.gallary.description,count(model.fileGallaryId), " +
    	 		"model.fileGallaryId,model.file.filePath from FileGallary model where model.gallary.gallaryId = ? and " +
    	 		"model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
    	 		"model.isPrivate = 'false' group by model.gallary.gallaryId order by updateddate desc");
    	 query.setMaxResults(1);
    	 query.setParameter(0,gallaryId);
    	 return query.list();
     }
     
     public List<Object[]> getCandidateGallaryDetailsForSubscribers(Date fromDate,Date toDate,List<Long> candidateIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    	 
    	 query.append("select model.fileGallaryId,model.gallary.name,model.file.fileTitle,model.gallary.candidate.candidateId,model.gallary.candidate.lastname,model.file.fileDescription");
    	 
    	 if(!type.equalsIgnoreCase("photos"))
    		query.append(",model.file.sourceObj.source,model.file.language.language ");
    		 
    	 query.append(" from FileGallary model where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate and model.gallary.candidate.candidateId in (:candidateIds) ");
     	 
    	 if(type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	 
    	 else if(type.equalsIgnoreCase("news"))    	 
    	 query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	 
    	 else if(type.equalsIgnoreCase("videos"))
    	 query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("candidateIds",candidateIds);
    	 
    	 return queryObj.list();
     }
     
     public List<Object[]> getPartyGallaryDetailsForSubscribers(Date fromDate,Date toDate,List<Long> partyIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    	 
    	 query.append("select model.fileGallaryId,model.gallary.name,model.file.fileTitle,model1.party.partyId,model1.party.shortName,model.file.fileDescription");
    	 
    	 if(!type.equalsIgnoreCase("photos"))
    		query.append(",model.file.sourceObj.source,model.file.language.language ");
    		 
    	 query.append(" from FileGallary model,PartyGallery model1 where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate and model.gallary.gallaryId = model1.gallery.gallaryId and model1.isDelete = 'false' and model1.isPrivate = 'false' and model1.party.partyId in (:partyIds) ");
     	 
    	 if(type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	 
    	 else if(type.equalsIgnoreCase("news"))    	 
    	 query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	 
    	 else if(type.equalsIgnoreCase("videos"))
    	 query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("partyIds",partyIds);
    	 
    	 return queryObj.list();
     }
     
     public List<Object[]> getSpecialPageGallaryDetailsForSubscribers(Date fromDate,Date toDate,List<Long> specialPageIds,String type)
     {
    	 StringBuilder query = new StringBuilder();
    	 
    	 query.append("select model.fileGallaryId,model.gallary.name,model.file.fileTitle,model1.specialPage.specialPageId,model1.specialPage.heading,model.file.fileDescription");
    	 
    	 if(!type.equalsIgnoreCase("photos"))
    		query.append(",model.file.sourceObj.source,model.file.language.language ");
    		 
    	 query.append(" from FileGallary model,SpecialPageGallery model1 where model.gallary.isDelete = 'false' and model.gallary.isPrivate = 'false' and model.isDelete = 'false' and " +
     	 		"model.isPrivate = 'false' and model.createdDate >= :fromDate and model.createdDate <= :toDate and model.gallary.gallaryId = model1.gallary.gallaryId and model1.isDelect = 'false' and model1.specialPage.isDelete = 'false' and model1.specialPage.specialPageId in (:specialPageIds) ");
     	 
    	 if(type.equalsIgnoreCase("photos"))
    	   query.append(" and model.gallary.contentType.contentTypeId = 1 ");
    	 
    	 else if(type.equalsIgnoreCase("news"))    	 
    	 query.append(" and model.gallary.contentType.contentTypeId = 2 ");
    	 
    	 else if(type.equalsIgnoreCase("videos"))
    	 query.append(" and model.gallary.contentType.contentTypeId = 4 ");
    	 
    	 query.append(" order by model.createdDate desc");
    	 
    	 Query queryObj = getSession().createQuery(query.toString());
    	 
    	 queryObj.setTimestamp("fromDate",fromDate);
    	 queryObj.setTimestamp("toDate",toDate);
    	 queryObj.setParameterList("specialPageIds",specialPageIds);
    	 
    	 return queryObj.list();
     }
}
