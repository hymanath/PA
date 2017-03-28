package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageGallery;

public interface ISpecialPageGalleryDAO extends GenericDao<SpecialPageGallery, Long> {
	
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType);
	
	public List<Object[]> getSpecialPageGallaryDetails(Long specialPageId,String contentType);

	public List<Object[]> getSpecialPageGallaryDetail(Long specialPageId,
			int firstRecord, int maxRecord, String type);
	
	public List<File> getGalleryBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType);
	
	public List<SpecialPage> getSpecialPageByGalleryId(Long gallaryId);
		
	public List<Object[]> getSpecialPageGallaryId(Long specialPageId,int firstRecord, int maxRecord, String contentType);
	
	public List<Object> getOtherGalleries(Long specialPageId,List<Long> gallaryIds,String contentType);

	public List<Long> getGalleryCountBasedOnSpecialPageId(Long specialPageId, String contentType);
	
	public List<Object[]> getGalleriesBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType);
	
	        //dao calls for youtube videos
	 public List<Object[]> getExpiredVideosList( Date from, Date to ,String contentType);
	
	 public List<Object> getFileSourceLanguageIds(List<String> filePaths);
	 public List<Object> getFileIds(List<Object> languageIds);
	 public int[] deleteYoutubeVideoRecords(List<String> filePaths ,List<Object> fileIds, List<Object> fileSourceLanguageIds, List<Long> languageIds);
	
	 public int   deleteRecordsFromFile(List<Object> fileIds);
	 public int  deleteRecordsFromFileGallary(List<Object> fileIds);
	 public int  deleteRecordsFromFileSourceLanguage(List<Object> fileSourceLanguageIds);
	 public int  deleteRecordsFromFilePath(List<String> filePaths);
	 public int  updateLastUpdateDateInFilePaths(List<Long> languageIds);
	 
	 public List<Object[]> checkGalleryExistForASpecialPage(Long specialPageId, String gallaryName, String contentType);

}
