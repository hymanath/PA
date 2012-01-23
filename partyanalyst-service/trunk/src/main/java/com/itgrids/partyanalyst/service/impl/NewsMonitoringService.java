package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.Category;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.NewsImportance;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;

public class NewsMonitoringService implements INewsMonitoringService {
	
	private static final Logger log = Logger.getLogger(NewsMonitoringService.class);
    private IFileGallaryDAO fileGallaryDAO;
    private ICandidateDetailsService candidateDetailsService;
    private ISourceDAO sourceDAO;
    private ICategoryDAO categoryDAO;
    private ISourceLanguageDAO sourceLanguageDAO;
    private INewsImportanceDAO newsImportanceDAO;
      
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
    		  fileVO.setSource(file.getSourceObj()!=null?file.getSourceObj().getSource():"");
    		  fileVO.setSourceId(file.getSourceObj()!=null?file.getSourceObj().getSourceId():null);
    		  fileVO.setLanguegeId(file.getLanguage()!=null?file.getLanguage().getLanguageId():null);
    		  fileVO.setLanguage(file.getLanguage()!=null?file.getLanguage().getLanguage():"");
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
	public List<FileVO> getAllCountDetails(Date fromDate,Date toDate,String fileType,Long regId){
		log.debug("Enter into getAllCountDetails Method of NewsMonitoringService ");
	       List<FileVO> returnFileVOList = new ArrayList<FileVO>();
	       FileVO fileVO = null;
	    	try{ 
	    		List<Object[]> category = fileGallaryDAO.getCountDetailsForCategory(fromDate,toDate,fileType,regId);
	    		FileVO categoryFileVO = new FileVO();
	    		List<FileVO> categoryFileVOList = new ArrayList<FileVO>();
	    		for(Object[] data:category){
	    			fileVO = new FileVO();
	    			fileVO.setSizeOfGallary((Long)data[0]);
	    			fileVO.setCategoryType(data[1].toString());
	    			fileVO.setCategoryId((Long)data[2]);
	    			categoryFileVOList.add(fileVO);
	    		}
	    		categoryFileVO.setFileVOList(categoryFileVOList);
	    		returnFileVOList.add(categoryFileVO);
	    		
	    		
	    		List<Object[]> source = fileGallaryDAO.getCountDetailsForSource(fromDate,toDate,fileType,regId);
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
	    		
	    		
	    		List<Object[]> language = fileGallaryDAO.getCountDetailsForLanguage(fromDate,toDate,fileType,regId);
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
	    		
	    		
	    		List<Object[]> newsImportance = fileGallaryDAO.getCountDetailsForNewsImportance(fromDate,toDate,fileType,regId);
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
	    		
	    		
	    		List<Object[]> locationScope = fileGallaryDAO.getCountDetailsForLocationScope(fromDate,toDate,fileType,regId);
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
	    		
	    		
	    		List<Object[]> locationScopeValue = fileGallaryDAO.getCountDetailsForLocationScopeValue(fromDate,toDate,fileType,regId);
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
	    		returnFileVOList.add(valueFileVO);
	    		
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
		List<Object[]>  newsImportanceList = newsImportanceDAO.getNewsImportanceDetails();
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
	 public List<FileVO> getCategoryCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getCategoryCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Category> catgryList = categoryDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  cateDetailsList = fileGallaryDAO.getDetailsForCategory(fromDate,toDate,fileType,regId);
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
		   log.debug("Exception rised in getCategoryCountDetailsForGraph Method of NewsMonitoringService ",e);
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
	 public List<FileVO> getSourceCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getSourceCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<Source> sourceList = sourceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  sourceDetailsList = fileGallaryDAO.getDetailsForSource(fromDate,toDate,fileType,regId);
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
		   log.debug("Exception rised in getSourceCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getLanguageCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getLanguageCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<SourceLanguage> langugeList = sourceLanguageDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  langugeDetailsList = fileGallaryDAO.getDetailsForLanguage(fromDate,toDate,fileType,regId);
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
		   log.debug("Exception rised in getLanguageCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
	 
	 public List<FileVO> getNewsImpCountDetailsForGraph(Date fromDate,Date toDate,String fileType,Long regId){
		  if(log.isDebugEnabled())
		    log.debug("Enter into getNewsImpCountDetailsForGraph Method of NewsMonitoringService ");
		  List<FileVO> returnVal = new ArrayList<FileVO>();
		  List<NewsImportance> newsImpList = newsImportanceDAO.getAll();
		  Map<String,Map<Long,FileVO>> completeData = new LinkedHashMap<String,Map<Long,FileVO>>();
	   try{
		  List<Object[]>  newsImpDetailsList = fileGallaryDAO.getDetailsForNewsImportance(fromDate,toDate,fileType,regId);
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
		   log.debug("Exception rised in getNewsImpCountDetailsForGraph Method of NewsMonitoringService ",e);
		   e.printStackTrace();
	   }
		 return returnVal;
	 }
}
