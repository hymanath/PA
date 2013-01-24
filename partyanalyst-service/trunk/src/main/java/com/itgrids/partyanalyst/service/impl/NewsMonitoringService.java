package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.hibernate.FileDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author ITGRIDS
 *
 */
public class NewsMonitoringService implements INewsMonitoringService {
	
	private static final Logger log = Logger.getLogger(NewsMonitoringService.class);
    private IFileGallaryDAO fileGallaryDAO;
    private ICandidateDetailsService candidateDetailsService;
    private ISourceDAO sourceDAO;
    private ICategoryDAO categoryDAO;
    private ISourceLanguageDAO sourceLanguageDAO;
    private INewsImportanceDAO newsImportanceDAO;
    private FileDAO fileDAO;
    private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
    private IGallaryDAO gallaryDAO;

	private IFileSourceLanguageDAO fileSourceLanguageDAO;
    private IRegionScopesDAO regionScopesDAO;
    private IBoothDAO boothDAO;
    private IUserCandidateRelationDAO userCandidateRelationDAO;
    
    public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

    
    public IUserCandidateRelationDAO getUserCandidateRelationDAO() {
		return userCandidateRelationDAO;
	}

	public void setUserCandidateRelationDAO(
			IUserCandidateRelationDAO userCandidateRelationDAO) {
		this.userCandidateRelationDAO = userCandidateRelationDAO;
	}
	


	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}


    
    public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
	          return fileGallaryDAO;
      }

    public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
	        this.fileGallaryDAO = fileGallaryDAO;
	
      }      
      
    public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	  }

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	  }
	
	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public INewsImportanceDAO getNewsImportanceDAO() {
		return newsImportanceDAO;
	}

	public void setNewsImportanceDAO(INewsImportanceDAO newsImportanceDAO) {
		this.newsImportanceDAO = newsImportanceDAO;
	}

	public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}

	public FileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(FileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(
			IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}

	
	public List<FileVO> getNewsForRegisterUsers(FileVO inputs){
      log.debug("Enter into getNewsForRegisterUsers Method of NewsMonitoringService ");
       List<FileVO> fileVOList = new ArrayList<FileVO>();
    	try{  
    	  List<File> fileList = fileGallaryDAO.getNewsForRegisterUsers(inputs);
    	  for(File file:fileList){
    		  FileVO  fileVO = new FileVO();
    		  fileVO.setFileId(file.getFileId());
    		  fileVO.setName(file.getFileName());
    		  fileVO.setPath(file.getFilePath());
    		  fileVO.setFileTitle1(file.getFileTitle());
    		  fileVO.setDescription(file.getFileDescription());
    		  fileVO.setFileDate(file.getFileDate()!=null?file.getFileDate().toString():"");
    		  
    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
    		  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
    		  Set<String> sourceSet = new HashSet<String>();
 			  Set<String> languageSet = new HashSet<String>();
 			 StringBuilder sourceVal =new StringBuilder();
			 StringBuilder languageVal =new StringBuilder();
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 if(inputs.getSourceId() == null && inputs.getLanguegeId() == null)
					 {
						  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
						  sourceSet.add(fileSourceLanguage.getSource().getSource());
						  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
					 }
					 else if(inputs.getLanguegeId() != null){
						 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
						 {
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 else if(inputs.getSourceId() != null){
						 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
						 { 
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 
				 }
				 for(String source:sourceSet){
					 sourceVal.append(source);
					 sourceVal.append("-");
				 }
	             for(String language:languageSet){
	            	 languageVal.append(language);
	            	 languageVal.append("-");
				 }
	             sourceVal.deleteCharAt(sourceVal.length() - 1);
	             languageVal.deleteCharAt(languageVal.length() - 1);
			fileVO.setMultipleSource(fileVOSourceLanguageList.size());
			Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
			fileVO.setFileVOList(fileVOSourceLanguageList);
			
			
    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"");
    		  fileVO.setNewsImportanceId(file.getNewsImportance()!=null?file.getNewsImportance().getNewsImportanceId():null);
    		  fileVO.setImportance(file.getNewsImportance()!=null?file.getNewsImportance().getImportance():"");
    		  fileVO.setLocationScope(file.getRegionScopes()!=null?file.getRegionScopes().getRegionScopesId():null);
    		  fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
    		  fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
    		  if(file.getRegionScopes()!=null)
    		  fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
    		  
    		  fileVOList.add(fileVO);
    	  }
    	}
    	catch(Exception e){
    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
    		e.printStackTrace();
    	}
    	  return fileVOList;
      }
	
	public List<FileVO> getNewsForRegisterUsers1(FileVO inputs){
	      log.debug("Enter into getNewsForRegisterUsers Method of NewsMonitoringService ");
	       List<FileVO> fileVOList = new ArrayList<FileVO>();
	    	try{  
	    	  List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
	    	  for(Object[] obj:fileList){
	    		  
	    		  File file = (File)obj[1];
	    		  FileVO  fileVO = new FileVO();
	    		  
	    		  fileVO.setContentId((Long)obj[0]);
	    		  fileVO.setKeywords(file.getKeywords());
	    		  fileVO.setFileDate(file.getFileDate().toString());
	    		  
	    		  String dateString =file.getFileDate().getDate()+"/"+(file.getFileDate().getMonth()+1)+"/"+(file.getFileDate().getYear()+1900);
	    		  fileVO.setFileDateAsString(dateString);
	    		  fileVO.setComments(file.getComment());
	    		  
	    		  fileVO.setDisplayImagePath(file.getFilePath());
	    		  fileVO.setVisibility(obj[2].toString());
	    		  
	    		  if(file.getRegionScopes() != null)
	    		    fileVO.setLocationScope(file.getRegionScopes().getRegionScopesId());
	    		  
	    		  if(file.getLocationValue() != null)
	    		    fileVO.setLocationValue(file.getLocationValue().toString());
	    		  
	    		  fileVO.setFileId(file.getFileId());
	    		  fileVO.setName(file.getFileName());
	    		  fileVO.setPath(file.getFilePath());
	    		  fileVO.setFileTitle1(file.getFileTitle());
	    		  fileVO.setDescription(file.getFileDescription());
	    		  fileVO.setFileDate(file.getFileDate()!=null?file.getFileDate().toString():"");
	    		  fileVO.setFileGallaryId((Long)obj[3]);
	    		  fileVO.setGallaryName(obj[4].toString());
	    		  
	    		  Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
	    		  List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
	    		  Set<String> sourceSet = new HashSet<String>();
	 			  Set<String> languageSet = new HashSet<String>();
	 			 StringBuilder sourceVal =new StringBuilder();
				 StringBuilder languageVal =new StringBuilder();
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 if(inputs.getSourceId() == null && inputs.getLanguegeId() == null)
						 {
							  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(inputs.getLanguegeId() != null){
							 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(inputs.getSourceId() != null){
							 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 
					 }
					 for(String source:sourceSet){
						 sourceVal.append(source);
						 sourceVal.append("-");
					 }
		             for(String language:languageSet){
		            	 languageVal.append(language);
		            	 languageVal.append("-");
					 }
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             languageVal.deleteCharAt(languageVal.length() - 1);
				fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				fileVO.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
	    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
	    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"");
	    		  fileVO.setNewsImportanceId(file.getNewsImportance()!=null?file.getNewsImportance().getNewsImportanceId():null);
	    		  fileVO.setImportance(file.getNewsImportance()!=null?file.getNewsImportance().getImportance():"");
	    		  fileVO.setLocationScope(file.getRegionScopes()!=null?file.getRegionScopes().getRegionScopesId():null);
	    		  fileVO.setLocationScopeValue(file.getRegionScopes()!=null?file.getRegionScopes().getScope():"");
	    		  fileVO.setLocation(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  fileVO.setLocationVal(file.getLocationValue()!=null?file.getLocationValue():null);
	    		  if(file.getRegionScopes()!=null)
	    		  fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
	    		  
	    		  fileVOList.add(fileVO);
	    	  }
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getNewsForRegisterUsers Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return fileVOList;
	      }
	
	public List<FileVO> getAllCountDetails(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		log.debug("Enter into getAllCountDetails Method of NewsMonitoringService ");
	       List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	       FileVO fileVO = null;
	    	try{ 
	    		Long totalCount = 0L; 
	    		List<Object[]> category = fileGallaryDAO.getCountDetailsForCategory(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO categoryFileVO = new FileVO();
	    		List<FileVO> categoryFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:category){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setCategoryType(data[1].toString());
	    			fileVO.setCategoryId((Long)data[2]);
	    			categoryFileVOList.add(fileVO);
	    			totalCount += (Long)data[0];
	    		}
	    		categoryFileVO.setFileVOList(categoryFileVOList);
	    		returnFileVOList.add(categoryFileVO);
	    		
	    		
	    		List<Object[]> source = fileGallaryDAO.getCountDetailsForSource(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO sourceFileVO = new FileVO();
	    		List<FileVO> sourceFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:source){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setSource(data[1].toString());
	    			fileVO.setSourceId((Long)data[2]);
	    			sourceFileVOList.add(fileVO);
	    			
	    		}
	    		sourceFileVO.setFileVOList(sourceFileVOList);
	    		returnFileVOList.add(sourceFileVO);
	    		
	    		
	    		List<Object[]> language = fileGallaryDAO.getCountDetailsForLanguage(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO languageFileVO = new FileVO();
	    		List<FileVO> languageFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:language){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLanguage(data[1].toString());
	    			fileVO.setLanguegeId((Long)data[2]);
	    			languageFileVOList.add(fileVO);
	    		}
	    		languageFileVO.setFileVOList(languageFileVOList);
	    		returnFileVOList.add(languageFileVO);
	    		
	    		
	    		List<Object[]> newsImportance = fileGallaryDAO.getCountDetailsForNewsImportance(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO importanceFileVO = new FileVO();
	    		List<FileVO> importanceFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:newsImportance){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setImportance(data[1].toString());
	    			fileVO.setNewsImportanceId((Long)data[2]);
	    			importanceFileVOList.add(fileVO);
	    		}
	    		importanceFileVO.setFileVOList(importanceFileVOList);
	    		returnFileVOList.add(importanceFileVO);
	    		
	    		
	    		List<Object[]> locationScope = fileGallaryDAO.getCountDetailsForLocationScope(fromDate,toDate,fileType,regId,fileInputVO);
	    		FileVO scopeFileVO = new FileVO();
	    		List<FileVO> scopeFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:locationScope){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLocationScopeValue(data[1].toString());
	    			fileVO.setLocationScope((Long)data[2]);
	    			scopeFileVOList.add(fileVO);
	    		}
	    		scopeFileVO.setFileVOList(scopeFileVOList);
	    		returnFileVOList.add(scopeFileVO);
	    		
	    		
	    		/*List<Object[]> locationScopeValue = fileGallaryDAO.getCountDetailsForLocationScopeValue(fromDate,toDate,fileType,regId);
	    		FileVO valueFileVO = new FileVO();
	    		List<FileVO> valueFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:locationScopeValue){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setLocationScopeValue(data[1].toString());
	    			fileVO.setLocationScope((Long)data[2]);
	    			fileVO.setLocation(data[3]!=null?(Long)data[3]:null);
	    			fileVO.setLocationValue(candidateDetailsService.getLocationDetails((Long)data[2], data[3]!=null?(Long)data[3]:null));
	    			valueFileVOList.add(fileVO);
	    		}
	    		valueFileVO.setFileVOList(valueFileVOList);
	    		returnFileVOList.add(valueFileVO);*/
	    		
	    		if(returnFileVOList != null && returnFileVOList.size() > 0)
	    			returnFileVOList.get(0).setCount(totalCount.intValue());
	    	}
	    	catch(Exception e){
	    		log.error("Exception rised in  getAllCountDetails Method of NewsMonitoringService", e);
	    		e.printStackTrace();
	    	}
	    	  return returnFileVOList;
	}
	public List<FileVO> getAllSourceDetails(){
		log.debug("Enter into getAllSourceDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 	
		List<Object[]>  sourceList = sourceDAO.getSourceDetails();
		 for(Object[] source: sourceList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)source[0]);
			 fileVO.setNames(source[1].toString());
			 
			 returnFileVOList.add(fileVO);
		  }
	 }
 	 catch(Exception e){
 		log.error("Exception rised in  getAllSourceDetails Method of NewsMonitoringService", e);
 		e.printStackTrace();
 	 }
 	  return returnFileVOList;
		
  }
	public List<FileVO> getAllCategoryDetails(){
		log.debug("Enter into getAllCategoryDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 	
		List<Object[]>  categoryList = categoryDAO.getCategoryDetails();
		for(Object[] category: categoryList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)category[0]);
			 fileVO.setNames(category[1].toString());
			 
			 returnFileVOList.add(fileVO);
		 }
	  }
	 catch(Exception e){
	   log.error("Exception rised in  getAllCategoryDetails Method of NewsMonitoringService", e);
	   e.printStackTrace();
	 }
	 return returnFileVOList;
		
  }
	public List<FileVO> getAllSourceLanguageDetails(){
	   log.debug("Enter into getAllSourceLanguageDetails Method of NewsMonitoringService ");
	   List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	   FileVO fileVO = null;
	 try{ 	
	   List<Object[]>  sourceLanguageList = sourceLanguageDAO.getSourceLanguageDetails();
	   for(Object[] sourceLanguage: sourceLanguageList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)sourceLanguage[0]);
			 fileVO.setNames(sourceLanguage[1].toString());
			 
			 returnFileVOList.add(fileVO);
		 }
	 }
	 catch(Exception e){
	  log.error("Exception rised in  getAllSourceLanguageDetails Method of NewsMonitoringService", e);
	  e.printStackTrace();
	}
	return returnFileVOList;
		
  }
	public List<FileVO> getAllNewsImportanceDetails(){
		log.debug("Enter into getAllNewsImportanceDetails Method of NewsMonitoringService ");
	    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	    FileVO fileVO = null;
	 try{ 
		List<Object[]>  newsImportanceList = newsImportanceDAO.getNewsImportanceDetailsWithOutOrderBy();
		for(Object[] newsImportance: newsImportanceList){
			 fileVO = new FileVO();
			 fileVO.setIds((Long)newsImportance[0]);
			 fileVO.setNames(newsImportance[1].toString());
			 
			 returnFileVOList.add(fileVO);
		}
	  }
	 catch(Exception e){
	   log.error("Exception rised in  getAllNewsImportanceDetails Method of NewsMonitoringService", e);
	   e.printStackTrace();
	 }
	return returnFileVOList;
  }
	 public List<FileVO> getCategoryCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getCategoryCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Category> catgryList = categoryDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  cateDetailsList = fileGallaryDAO.getDetailsForCategory(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] categRecord: cateDetailsList)
		    {
			   if(categRecord[0] != null)
			    {
				   if(completeData.get(categRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(categRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)categRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(Category categ: catgryList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(categ.getCategoryId());
						   fileVO.setCandidateName(categ.getCategoryType());
						   singleCateg.put(categ.getCategoryId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)categRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(categRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getCategoryCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 private void convertMapToList(Map<String,Map<Long,FileVO>> completeData,List<FileVO> returnVal){
		 Set<String> mapKeys = completeData.keySet();
		 FileVO retVO = null;
		 List<FileVO> fileVOList = null;
		 
		 for(String mapKey: mapKeys)
		 {
			 retVO = new FileVO();
			 fileVOList = new ArrayList<FileVO>();
			 Map<Long,FileVO> catObjs = completeData.get(mapKey);
			 Set<Long> catObjsKeys = catObjs.keySet();
			 for(Long catObjsKey: catObjsKeys)
			 {
				 FileVO fileVO = catObjs.get(catObjsKey);
				 fileVOList.add(fileVO);
			 }
			 retVO.setFileVOList(fileVOList);
			 retVO.setFileDate(mapKey);
			 returnVal.add(retVO);
		 }
		 
		 
	 }
	 public List<FileVO> getSourceCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getSourceCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Source> sourceList = sourceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  sourceDetailsList = fileGallaryDAO.getDetailsForSource(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] sourceRecord: sourceDetailsList)
		    {
			   if(sourceRecord[0] != null)
			    {
				   if(completeData.get(sourceRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(sourceRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)sourceRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(Source source: sourceList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(source.getSourceId());
						   fileVO.setCandidateName(source.getSource());
						   singleCateg.put(source.getSourceId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)sourceRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(sourceRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getSourceCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getLanguageCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getLanguageCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<SourceLanguage> langugeList = sourceLanguageDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  langugeDetailsList = fileGallaryDAO.getDetailsForLanguage(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] langugeRecord: langugeDetailsList)
		    {
			   if(langugeRecord[0] != null)
			    {
				   if(completeData.get(langugeRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(langugeRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)langugeRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(SourceLanguage languge: langugeList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(languge.getLanguageId());
						   fileVO.setCandidateName(languge.getLanguage());
						   singleCateg.put(languge.getLanguageId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)langugeRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(langugeRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getLanguageCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getNewsImpCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId,FileVO fileInputVO){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getNewsImpCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<NewsImportance> newsImpList = newsImportanceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  newsImpDetailsList = fileGallaryDAO.getDetailsForNewsImportance(fromDate,toDate,fileType,regId,fileInputVO);
		  for(Object[] newsImpRecord: newsImpDetailsList)
		    {
			   if(newsImpRecord[0] != null)
			    {
				   if(completeData.get(newsImpRecord[0].toString()) != null)
				    {
					   Map<Long,FileVO> singleCateg = completeData.get(newsImpRecord[0].toString());
					   FileVO fileVO = singleCateg.get((Long)newsImpRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
				    }
				   else
				    {
					   Map<Long,FileVO> singleCateg = new LinkedHashMap<Long,FileVO>();
					   FileVO fileVO = null;
					   for(NewsImportance newsImp: newsImpList)
					    {
						   fileVO = new FileVO();
						   fileVO.setCategoryId(newsImp.getNewsImportanceId());
						   fileVO.setCandidateName(newsImp.getImportance());
						   singleCateg.put(newsImp.getNewsImportanceId(), fileVO);
					    }
					   fileVO = singleCateg.get((Long)newsImpRecord[2]);
					   fileVO.setCount(fileVO.getCount()+1);
					   completeData.put(newsImpRecord[0].toString(), singleCateg);
				    }
			   }
		   }
		   convertMapToList(completeData,returnVal);
	   }
	   catch(Exception e){
		   log.error("Exception rised in getNewsImpCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public ResultStatus updateDeleteNews(FileVO fileVO,String task,List<FileVO> sourceIds,List<FileVO> languageIds){
		 if(log.isDebugEnabled())
			 log.debug("Enter into updateDeleteNews Method of NewsMonitoringService ");
		 ResultStatus resultStatus = new ResultStatus();
	  try{ 
		 if(task.equalsIgnoreCase("Update")){
			 File file = fileDAO.get(fileVO.getFileId());
			 Category category = categoryDAO.get(fileVO.getCategoryId());
			 
			 NewsImportance newsImportance = newsImportanceDAO.get(fileVO.getNewsImportanceId());
			 
			 file.setFileTitle(fileVO.getTitle());
			 file.setFileDescription(fileVO.getDescription());
			 file.setCategory(category);
			 file.setNewsImportance(newsImportance);
			 
			 file.setKeywords(fileVO.getKeywords());
			 
			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
				//Date fileDate = (Date)formatter.parse(fileVO.getFileDate());
			// file.setFileDate(fileDate);	
			 
			 String[] dateArray = fileVO.getFileDate().split("/");
			 
			 Integer date = Integer.parseInt(dateArray[0]);
			 Integer month = Integer.parseInt(dateArray[1]) - 1;
			 Integer year = Integer.parseInt(dateArray[2]) - 1900;
			 
			 Date fileDate = new Date();
			 
			 fileDate.setDate(date);
			 fileDate.setMonth(month);
			 fileDate.setYear(year);
			 
			 file.setFileDate(fileDate);
			 
			 if(fileVO.getLocationScope() != 0)
			 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
			 file.setLocationValue(fileVO.getRegionValue());
			 
			 fileDAO.save(file);
			 
			 for(FileVO languageFile:languageIds){
				 SourceLanguage sourceLanguage = sourceLanguageDAO.get(languageFile.getLanguegeId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(languageFile.getFileSourceLanguageId());
				 fileSourceLanguage.setLanguage(sourceLanguage);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 for(FileVO sourceFile:sourceIds){
				 Source source = sourceDAO.get(sourceFile.getSourceId());
				 FileSourceLanguage	 fileSourceLanguage = fileSourceLanguageDAO.get(sourceFile.getFileSourceLanguageId());
				 fileSourceLanguage.setSource(source);
				 fileSourceLanguageDAO.save(fileSourceLanguage);
			 }
			 
			 DateUtilService dateUtilService = new DateUtilService();
			 
			List<FileGallary> fileGallaries = fileGallaryDAO.getFileGallariesByFileId(fileVO.getFileId());
			
			
			for(FileGallary fileGallary:fileGallaries){
				
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallaryDAO.save(fileGallary);
				
				
			}
			 
			 
			// fileGallaryDAO.updateFileDate(dateUtilService.getCurrentDateAndTime(),fileVO.getFileId());
			 
			 
          if(fileVO.getFileGallaryId() !=0 && fileVO.getGallaryId() != 0){
				 
				 FileGallary fileGallary = fileGallaryDAO.get(fileVO.getFileGallaryId());
				 fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				 
				 if(fileVO.getVisibility().equalsIgnoreCase("public"))
				    fileGallary.setIsPrivate("false");
				 else
				    fileGallary.setIsPrivate("true");
				 
				 fileGallaryDAO.save(fileGallary);
				 
				 
			 }
			 
			/* if(fileVO.getVisibility().equalsIgnoreCase("public")){
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"false");
				 
			 }else{
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"true");
				 
			 }
			 */
		 }
		 else if(task.equalsIgnoreCase("Delete")){
			 fileGallaryDAO.deleteFile(fileVO.getFileId());
		 }
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  }
	  catch(Exception e){
		  log.error("Exception rised in updateDeleteNews Method of NewsMonitoringService ",e);
		   e.printStackTrace();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	  }
	  
	  return resultStatus;
	 }
	 private void setSourceLanguageAndPaths(FileSourceLanguage fileSourceLanguage,List<FileVO> fileVOSourceLanguageList){
			 
		     FileVO fileVOSourceLanguage = new FileVO();
			 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():"");
			 if(fileSourceLanguage.getSource()!=null)
				
			 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
			 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"");
			 if(fileSourceLanguage.getLanguage()!=null)
				 
			 
			 fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
			 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
			 
			 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
			 
			 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			 fileVOSourceLanguage.setMultipleNews(filePathsSet.size());
			 
			 for(FilePaths filePath : filePathsSet){
				 FileVO fileVOPath = new FileVO();
				 fileVOPath.setPath(filePath.getFilePath());
				 fileVOPath.setOrderNo(filePath.getOrderNo());
				 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
				 fileVOPathsList.add(fileVOPath);
			 }
			 Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
			 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
			 fileVOSourceLanguageList.add(fileVOSourceLanguage);
			 
		}
	 
	 public List<FileVO> getAllRegionScopes(){
			log.debug("Enter into getAllRegionScopes Method of NewsMonitoringService ");
		    List<FileVO> returnFileVOList = new ArrayList<FileVO>();
		    FileVO fileVO = null;
		 try{ 
			List<Object[]>  regionScopesList = regionScopesDAO.getAllRegionScopesWithOutOrderBy();
			for(Object[] regionScope: regionScopesList){
				 fileVO = new FileVO();
				 fileVO.setIds((Long)regionScope[0]);
				 fileVO.setNames(regionScope[1].toString());
				 
				 returnFileVOList.add(fileVO);
			}
		  }
		 catch(Exception e){
		   log.error("Exception rised in  getAllRegionScopes Method of NewsMonitoringService", e);
		   e.printStackTrace();
		 }
		return returnFileVOList;
	  }
	 
	 
	
	
	public List<FileVO> getNewsCountForALocationByCategory(Long registrationId,
			Long locationValue, Long locationId, Long publicationId) {
		
		log.debug("Enterd into the getNewsCountForALocationByCategory service method");
		 
		 try{			 
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(registrationId);

            List<Long> locationValuesList = new ArrayList<Long>();			
            locationValuesList.add(locationValue);
			
			
           if(locationId == 3)	
				locationValuesList = getAllBoothsInPanchayat(locationValue,
						publicationId, locationValuesList);  
           
           
           if(locationId == 7){
	    	   
 	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
 	    	  
 	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
 	    		  
 	    		 locationValuesList = new ArrayList<Long>();	    		  
 	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
 	    		  
 	    	  }
 	    		  
 	       
        	   
        	   
        	   
           }
        	   
			List<Object[]> countByCategoryList = fileGallaryDAO
					.getNewsCountForALocationByCategoryForACandidate(
							candidateIds,locationId,
							locationValuesList);	
				
			List<FileVO> filesList = setCountNewsCountValuesToVO(
					countByCategoryList, candidateIds, locationId,
					locationValuesList);
				
		
			return filesList;
			
		 }catch(Exception e){
			 
		    log.error("Exception raised in getNewsCountForALocationByCategory service method:"+e);
			 e.printStackTrace();
			 return null;			 
		 }
	}
	
	
	public List<Long> getCandidateDetailsByRegistrationId(Long registrationId){		
		
		log.debug("Entered into the getCandidateDetailsByRegistrationId service method");
		
		 List<Long> candidateIds = new ArrayList<Long>();
		 
		 try{
		 
		 List<Object[]> candidateDetails = userCandidateRelationDAO
				.getCandidatesOfAUser(registrationId);
			
			for(Object[] obj:candidateDetails)
				candidateIds.add((Long)obj[0]);
		 }catch(Exception e){
			 e.printStackTrace();
			 log.debug("Exception raised in  getCandidateDetailsByRegistrationId service method:"+e);			 
		 }		
		return candidateIds;
		
	}
	
	public List<Long> getAllBoothsInPanchayat(Long panchayatId,
			Long publicationId, List<Long> locationValuesList) {
		
		log.debug("Entered into the getAllBoothsInPanchayat service method");

		try
		{
			//List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(
					//panchayatId, publicationId);
					
					List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(
							panchayatId, new Long(IConstants.PRESENT_ELECTION_YEAR));
			
			for(Object[] boothDtls:boothsList)					
				locationValuesList.add((Long)boothDtls[0]);
		}catch(Exception e){
			log.debug("Exception raised in getAllBoothsInPanchayat service method:"+e);
			e.printStackTrace();
			
		}
		return locationValuesList;
		
	}
	
	
	public List<FileVO> setCountNewsCountValuesToVO(List<Object[]> countByCategoryList,
			List<Long> candidateIds, Long locationId,
			List<Long> locationValuesList) {
		log.debug("Entered into the setCountNewsCountValuesToVO service method");
		
		List<FileVO> filesList = new ArrayList<FileVO>();
		
		try{		
			for(Object[] obj:countByCategoryList){				
			
			FileVO file = new FileVO();				
			Long categoryId = (Long)obj[2];				
			file.setCategoryName(obj[0].toString());	
			file.setCategoryId(categoryId);
			
			List<Object[]> importanceCountList = fileGallaryDAO
					.getNewsCountForALocationByCategoryAndImportanceForACandidate(
							candidateIds, categoryId , locationId , locationValuesList );
	
				for(Object[] importanceNews:importanceCountList){					
					Long importanceId = (Long)importanceNews[0];
					Long newsCount = (Long)importanceNews[2];
					
					if(importanceId == 1)							
						file.setLowImpactCount(newsCount);
					else if(importanceId == 2)
						file.setMediumImpactCount(newsCount);
					else if(importanceId == 3)
						file.setHighImpactCount(newsCount);
					
				}				
				filesList.add(file);
				
			}
		}catch(Exception e){
			log.error("Exception raised in setCountNewsCountValuesToVO service method");
			e.printStackTrace();
			return null;			
		}
		
		return filesList;	
		
	}
	
	
	public List<FileVO> getNewsByLocationAndCategory(FileVO fileVO){
		
		log.debug("Entered into the getNewsByLocationAndCategory service method");
		
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		
		try{
		
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
	
	        List<Long> locationValuesList = new ArrayList<Long>();			
	        locationValuesList.add(fileVO.getLocationVal());
			
			
	       if(fileVO.getLocationId() == 3){
	    	   
	       
				locationValuesList = getAllBoothsInPanchayat(fileVO.getLocationVal(),
						fileVO.getPublicationId(), locationValuesList);
				
				fileVO.setLocationId(9L);
	       }
	       
	       if(fileVO.getLocationId() == 7){
	    	   
	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
	    		  
	    		  
	    		  locationValuesList = new ArrayList<Long>();	    		  
	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
	    		  
	    	  }
	    		  
	       }
	       
	       
	       List<Object[]> filesList = fileGallaryDAO
					.getNewsByLocationAndCategory(
							candidateIds,fileVO,locationValuesList);
	       
	       
	       for(Object[] obj:filesList){
	    	   
	    	   File fileDetails = (File)obj[0];
	    	   
	    	   FileVO file = new FileVO();
	    	   
	    	   file.setTitle(fileDetails.getFileTitle());
	    	   file.setDescription(fileDetails.getFileDescription());
	    	   file.setContentId((Long)obj[1]);
	    	   
	    	   fileList.add(file);
	    	   
	       }
			
			return fileList;
		}catch(Exception e){
			
			log.error("Exception raised in  the getNewsByLocationAndCategory service method");
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
