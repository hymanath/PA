package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.INewsMonitoringService;

/**
 * @author ITGRIDS
 *
 */
public class NewsMonitoringService implements INewsMonitoringService {/*
	
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
    private IContentNotesDAO contentNotesDAO;

	
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
    private IRegionScopesDAO regionScopesDAO;
    private IBoothDAO boothDAO;
    private IUserCandidateRelationDAO userCandidateRelationDAO;
    private INewsFlagDAO newsFlagDAO;
    private IUserDAO userDAO;
	private TransactionTemplate transactionTemplate;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private IContentManagementService contentManagementService;
	private ContentDetailsVO contentDetailsVO;
	private IPanchayatDAO panchayatDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private INewsProblemDAO newsProblemDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public INewsProblemDAO getNewsProblemDAO() {
		return newsProblemDAO;
	}

	public void setNewsProblemDAO(INewsProblemDAO newsProblemDAO) {
		this.newsProblemDAO = newsProblemDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}

	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

    
    public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public INewsFlagDAO getNewsFlagDAO() {
		return newsFlagDAO;
	}

	public void setNewsFlagDAO(INewsFlagDAO newsFlagDAO) {
		this.newsFlagDAO = newsFlagDAO;
	}

	public IContentNotesDAO getContentNotesDAO() {
		return contentNotesDAO;
	}

	public void setContentNotesDAO(IContentNotesDAO contentNotesDAO) {
		this.contentNotesDAO = contentNotesDAO;
	}


    
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
    		  fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()));
    		  fileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()));
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
						  if(fileSourceLanguage.getSource() != null)
						  sourceSet.add(fileSourceLanguage.getSource().getSource());
						  if(fileSourceLanguage.getLanguage() != null)
						  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
					 }
					 else if(inputs.getLanguegeId() != null){
						 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
						 {
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 else if(inputs.getSourceId() != null){
						 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
						 { 
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
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
	             if(sourceVal != null && sourceVal.length() > 0)
	             sourceVal.deleteCharAt(sourceVal.length() - 1);
	             if(languageVal != null && languageVal.length() > 0)
	             languageVal.deleteCharAt(languageVal.length() - 1);
			fileVO.setMultipleSource(fileVOSourceLanguageList.size());
			Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
			fileVO.setFileVOList(fileVOSourceLanguageList);
			
			
    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"N/A");
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
	    		
	    		List<Long> contentIds = new ArrayList<Long>();
	    		Map<Long , Long> countMap = new HashMap<Long, Long>();
	    		Map<Long , Long> notesCountMap = new HashMap<Long, Long>();
	    		List<Object[]> countList = new ArrayList<Object[]>();
	    		List<Object[]> notesCountList = new ArrayList<Object[]>();
	    		
	    	  List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
	    	  
	    	  for(Object[] obj:fileList)	    		  
	    		  contentIds.add((Long)obj[0]);
	    	  
	    	  if(contentIds.size() >0)	    	  
	    	      countList= newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
	    	  
	    	  
	    	  //if(inputs.getCandidateId() != null){
	    	  
	    	  if(contentIds.size() > 0)
	    	        notesCountList =  contentNotesDAO.getContentNotesCountByContentIds(contentIds);
	    	    
	    	    for(Object[] obj:notesCountList)	    	    	
	    	    	notesCountMap.put((Long)obj[0], (Long)obj[1]);
	    	    	
	    	 // }
	    	  
	    	  
	    	  for(Object[] obj:countList)
	    		  countMap.put((Long)obj[0],(Long)obj[1]);	
	    		  
	    	  for(Object[] obj:fileList){
	    		  
	    		  File file = (File)obj[1];
	    		  FileVO  fileVO = new FileVO();
	    		  
                  if(fileVOList.size() == 0){
                	  fileVO.setTotalFlaggedNews(countList.size());
                	  fileVO.setTotalNotesNews(notesCountMap.size());
                  }
	    		  
	    		  if(countMap.get((Long)obj[0]) == null)
	    			  fileVO.setFlagSet("false");
	    		  else
	    			  fileVO.setFlagSet("true");
	    		  
	    		  if(notesCountMap.get((Long)obj[0]) != null){
	    			  
	    			  if(notesCountMap.get((Long)obj[0]).longValue() >0)
	    				  fileVO.setNotesExist("true");
	    			  else
	    				  fileVO.setNotesExist("false");
	    			  
	    		  }
	    		  
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
	    		  fileVO.setFileTitle1(CommonStringUtils.removeSpecialCharsFromAString(file.getFileTitle()));
	    		  fileVO.setDescription(CommonStringUtils.removeSpecialCharsFromAString(file.getFileDescription()));
	    		  String fileDate = file.getFileDate().toString();
	    		  String dateObj = fileDate.substring(8,10)+'-'+fileDate.substring(5,7)+'-'+fileDate.substring(0,4);
	    		  fileVO.setFileDate(dateObj!=null?dateObj:"");
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
							  if(fileSourceLanguage.getSource() != null)
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(inputs.getLanguegeId() != null){
							 if(inputs.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								 languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(inputs.getSourceId() != null){
							 if(inputs.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList); 
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
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
		             if(sourceVal != null && sourceVal.length() > 0)
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             if(languageVal != null && languageVal.length() > 0)
		             languageVal.deleteCharAt(languageVal.length() - 1);
				fileVO.setMultipleSource(fileVOSourceLanguageList.size());
				Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
				fileVO.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  fileVO.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  fileVO.setLanguage(languageVal!=null?languageVal.toString():"");
	    		  fileVO.setCategoryId(file.getCategory()!=null?file.getCategory().getCategoryId():null);
	    		  fileVO.setCategoryType(file.getCategory()!=null?file.getCategory().getCategoryType():"N/A");
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
	    			fileVO.setCategoryType(data[1].toString()!=null?data[1].toString():"N/A");
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
			 String fileDate = mapKey.substring(8,10)+'-'+mapKey.substring(5,7)+'-'+mapKey.substring(0,4);
			 retVO.setFileDate(fileDate);
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
			 
			 Category category  = null;
			 File file = fileDAO.get(fileVO.getFileId());
			 
			 if(fileVO.getCategoryId() != 0)
			  category = categoryDAO.get(fileVO.getCategoryId());
			 
			 NewsImportance newsImportance = newsImportanceDAO.get(fileVO.getNewsImportanceId());
			 
			 file.setFileTitle(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getTitle()));
			 file.setFileDescription(CommonStringUtils.removeSpecialCharsFromAString(fileVO.getDescription()));
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
			 
			 if(fileVO.getLocationScope() != 0){
				 
				 if(fileVO.getLocationScope().longValue() != 7){
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));				 
					 file.setLocationValue(fileVO.getRegionValue());
				 }else{
					 file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));	
					 List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getRegionValue());
					 
					 if(localElectionBodyList != null && localElectionBodyList.size() >0){
						 
						List<Long> locationValuesList = new ArrayList<Long>();	    		  
						 file.setLocationValue((Long)localElectionBodyList.get(0));
						 
						 
						 
					 }
					 
					 
				 }
			 }
			 
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
          
          if(fileVO.getFlagSet().equalsIgnoreCase("false"))        	  
        	  newsFlagDAO.removeFlagForNews(fileVO.getFileGallaryId());          
          else
        	  addFlagToNews(fileVO.getFileGallaryId(),fileVO.getUserId());
			 
			 if(fileVO.getVisibility().equalsIgnoreCase("public")){
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"false");
				 
			 }else{
				 
				 fileGallaryDAO.updateVisibility(fileVO.getFileId(),"true");
				 
			 }
			 
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
	 
	 List<Long> candidateIds = getCandidateDetailsByRegistrationId(registrationId);
	// List<Long> locationValuesList = new ArrayList<Long>();	
	 //List<Object[]> countByCategoryList = null ;
	 List<FileVO> filesList = null;
	 
	 try{
	 
	     if(locationId.longValue() == 5)
	    	 filesList =  getNewsCountForMandalLevel(candidateIds , locationId , locationValue);		    	 
	     else if(locationId.longValue() == 3)
	    	 filesList = getNewsCountDetailsByPanchayat(candidateIds , locationValue);	    	
	     else if(locationId.longValue() == 4)
	    	 filesList =  getNewsCountDetailsByConstituencyLevel(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 7)
	    	 filesList =  getNewsCountDetailsForMuncipality(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 8)
	    	 filesList =  getNewsCountDetailsForWard(candidateIds , locationId , locationValue);
	     else if(locationId.longValue() == 6)
	    	 filesList =  getNewsCountForAHamlet(candidateIds , locationId , locationValue);
	    	 
	 }catch(Exception e){
		 e.printStackTrace();			 
	 }
	 
	 return filesList;
 }
 
 
public  List<FileVO> getNewsCountForMandalLevel(List<Long> candidateIds ,Long  locationId ,Long locationValue){
        log.debug("Entered into the getNewsCountForMandalLevel service method");
        List<FileVO> filesList = null;

		try{
		
			List<Long> locationValuesList = new ArrayList<Long>();	
			List<Long> hamletIds = null;
			Long hamletScopeId = null;
			List<Object[]> countByCategoryList = null ;
				
		    locationValuesList.add(locationValue);
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(locationValue);
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
				hamletScopeId  = 6L;
			}				
			
			 countByCategoryList =	fileGallaryDAO.getNewsCountForMandalLevel(
					candidateIds,locationId,locationValuesList,hamletScopeId,hamletIds);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setTehsilScopeId(locationId);
			 newsCountVO.setTehsilIds(locationValuesList);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCandidateIds(candidateIds);
			 
			 filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "tehsil");
			 
			 
			 //setNewsCountValuesToFileVO(countByCategoryList,candidateIds,locationId,locationValuesList,hamletScopeId,hamletIds);
		}catch(Exception e){
			log.error("Exception raised in the getNewsCountForMandalLevel service method");
			e.printStackTrace();		
		}
		return filesList;

}

public List<FileVO> getNewsCountDetailsByPanchayat(List<Long> candidateIds ,Long  locationValue){

 log.debug("Entered into the getNewsCountDetailsByPanchayat service method");	
 List<FileVO> filesList = null;
 
		 try{
			 List<Object[]> countByCategoryList = null ;
			     List<Long> hamletIds = null;
			     Long hamletScopeId = 6L;
			
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(locationValue);
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
		
		    countByCategoryList =	fileGallaryDAO.getNewsCountForMandalLevel(
						candidateIds,null,null,hamletScopeId,hamletIds);
		    
		    NewsCountVO newsCountVO = new NewsCountVO();
		    
		    newsCountVO.setHamletScopeId(hamletScopeId);
		    newsCountVO.setHamletIds(hamletIds);
		    newsCountVO.setCandidateIds(candidateIds);
		    
		    filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "panchayat");
		    
		    //setNewsCountValuesToFileVO(countByCategoryList,candidateIds,null,null,hamletScopeId,hamletIds);
		   
		
			
		}catch(Exception e){
	      log.error("Exception raised in getNewsCountDetailsByPanchayat service method");
	     e.printStackTrace();	
         }
		 
       return filesList;

}

*//**
 * This method will get the news count details for a hamlet
 * @author Samba Penugonda
 * @param candidateIds  , list of candidate whose news can see the logged in user
 * @param locationId , this is the scopeId for hamlet
 * @param locationValue , this is the hamlet id which we want new count
 * @return news count of a hamlet by categorywise
 *//*
public List<FileVO> getNewsCountForAHamlet(
	      List<Long> candidateIds ,Long locationId ,Long locationValue)
{
	log.debug("Entered into  getNewsCountForAHamlet service method");
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = new ArrayList<Long>();
	 Long hamletScopeId = 6L;
	 List<Object[]> countByCategoryList = null;
	 
	 
	 try
	 {
		 locationValuesList.add(locationValue); 
		 countByCategoryList =  fileGallaryDAO.getNewsCountForHamlets(candidateIds,hamletScopeId,locationValuesList);
		 
		 NewsCountVO newsCountVO = new NewsCountVO();
		 
		 newsCountVO.setCandidateIds(candidateIds);
		 newsCountVO.setHamletScopeId(hamletScopeId);
		 newsCountVO.setHamletIds(locationValuesList);
		 
		 
		  filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "hamlet");
		 
		 
	 }catch(Exception e)
	 {
		 log.error("Exception raised in getNewsCountForAHamlet service method");
		 e.printStackTrace();
		 return null;
		 
	 }
	 return filesList;
	
}

public List<FileVO> getNewsCountDetailsForWard(
	      List<Long> candidateIds ,Long locationId ,Long locationValue){
	
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = new ArrayList<Long>();
	 Long wardScopeId = 8L;
	 List<Object[]> countByCategoryList = null;
	 
	 try{
		 
		 locationValuesList.add(locationValue);
		 
		 countByCategoryList = 	fileGallaryDAO.getNewsCountForWards(candidateIds,wardScopeId,locationValuesList);
		 
		 NewsCountVO newsCountVO = new NewsCountVO();
		 
		 newsCountVO.setCandidateIds(candidateIds);
		 newsCountVO.setWardScopeId(wardScopeId);
		 newsCountVO.setWardIdsList(locationValuesList);
		 
		 
		  filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "ward");
		 
	 }catch(Exception e){
		 e.printStackTrace();
		 return null;
		 
	 }
	 
	 return filesList;
	
	
}

public List<FileVO> getNewsCountDetailsForMuncipality(
		      List<Long> candidateIds ,Long locationId ,Long locationValue){
	 List<FileVO> filesList = null;
	 List<Long> locationValuesList = null;
	 List<Object[]> countByCategoryList = null;
	
	try{

 	   
   	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
   	  
   	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
   		  
   		 locationValuesList = new ArrayList<Long>();	    		  
   		  locationValuesList.add((Long)localElectionBodyList.get(0));
   		  
   	  }
   	  
   	 List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(locationValuesList);
   	  
   	  NewsCountVO newsCountVO = new NewsCountVO();
   	  
   	    newsCountVO.setCandidateIds(candidateIds);
   	    newsCountVO.setMuncipalityScopeId(7L);
    	newsCountVO.setMuncipalityValuesList(locationValuesList);
    	
    	
    	if(wardIds != null && wardIds.size() >0){
    		newsCountVO.setWardIdsList(wardIds);
        	newsCountVO.setWardScopeId(8L);
        	
    	 countByCategoryList = 	fileGallaryDAO.getNewsCountForMuncipalityWithWards(newsCountVO);
    	}
    	else
    	 countByCategoryList = fileGallaryDAO.getNewsCountForMuncipality(
  	    		   candidateIds ,locationId ,locationValuesList);
   	
    filesList  = setNewsCountValuesToFileVO1(countByCategoryList , newsCountVO , "muncipality");

  	   
     
		
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return filesList;
}

public List<FileVO> getNewsCountDetailsByConstituencyLevel(
		List<Long> candidateIds, Long locationId, Long locationValue) {	
	
	log.debug("Entered into the getNewsCountDetailsByConstituencyLevel service method");	
    List<FileVO> filesList = null;
 
 try
	 {
		 List<Object[]> countByCategoryList = null ;
		 List<Long> panchayitIdsList = new ArrayList<Long>();
		// Long constituencyScopeId = locationId;
		 Long constituencyScopeId = 4L;
		 Long constituencyVal = locationValue;
	
		 Long tehsilScopeId = 5L;
		 List<Long> tehsilIds = new ArrayList<Long>();
		 tehsilIds.add(0L);
	
		 Long hamletScopeId = 6L;
		 List<Long> hamletIds = new ArrayList<Long>();
		 hamletIds.add(0L);
		 
		 Long muncipalityScopeId = 7L;
		 Long wardScopeId = 8L;
		 List<Long> wardIds = new ArrayList<Long>();
		 wardIds.add(0L);
	
		 List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(constituencyVal);
	
		 for(Object[] obj:mandalsList)
			 tehsilIds.add((Long)obj[0]);
	
		 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
		 
		 
		 for(Object[] obj :panchayitisList)
			 panchayitIdsList.add((Long)obj[0]);
	
		 if(panchayitIdsList != null && panchayitIdsList.size() >0)
			 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
		 
		 
		List<Long> localElectionBodyIds = null;
				
		if(tehsilIds != null&& tehsilIds.size()  != 1)
			localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
		else
			localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(constituencyVal);
	
		 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
			 
			  wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);
			 
			 
			 if(wardIds != null && wardIds.size() >0)
				 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevelWithMuncipalityAndWards(
							candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
							tehsilIds,hamletScopeId,hamletIds,muncipalityScopeId , localElectionBodyIds,wardScopeId,wardIds);
			 else
			 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevelWithMuncipality(
						candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
						tehsilIds,hamletScopeId,hamletIds,muncipalityScopeId , localElectionBodyIds);
			 
			 
		
		 	
		 }else{
			 
			 countByCategoryList =	fileGallaryDAO.getNewsCountForConstituencyLevel(
						candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
						tehsilIds,hamletScopeId,hamletIds);
			 
			 
		 }
	 
	 
	 setNewsCountValuesToFileVO1(countByCategoryList,
				candidateIds,constituencyScopeId,constituencyVal,tehsilScopeId,
				tehsilIds,hamletScopeId,hamletIds);
	
	 
		 	NewsCountVO newsCountVO = new NewsCountVO();
	 
		 	newsCountVO.setConstituencyScopeId(constituencyScopeId);
		 	newsCountVO.setConstituencyValue(constituencyVal);
		 	newsCountVO.setTehsilScopeId(tehsilScopeId);
		 	newsCountVO.setTehsilIds(tehsilIds);
		 	newsCountVO.setHamletScopeId(hamletScopeId);
		 	newsCountVO.setHamletIds(hamletIds);
		 	newsCountVO.setCandidateIds(candidateIds);
		 	
		 	if(wardIds != null){
		 		
		 		newsCountVO.setWardScopeId(8L);
		 		newsCountVO.setWardIdsList(wardIds);
		 		
		 	}
		 		
		 	
		 	if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
		 	  newsCountVO.setMuncipalityScopeId(muncipalityScopeId);
		 	  newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
		 	}
	
		 	filesList  =  setNewsCountValuesToFileVO1
		 			(countByCategoryList , newsCountVO , "constituency");
		 	
	 }catch(Exception e){
	 
	 log.error("Exception raised in getNewsCountDetailsByConstituencyLevel service method");
	 e.printStackTrace();	 
     }
    return filesList;

   }
	 
	public List<FileVO> setNewsCountValuesToFileVO1(
			List<Object[]> countByCategoryList, NewsCountVO newsCountVO , String type) {
		 
		 List<FileVO> filesList = new ArrayList<FileVO>();
		 List<Object[]> importanceCountList = null;
		 try{
			 
			 for(Object[] obj:countByCategoryList){				
					
					FileVO file = new FileVO();				
					Long categoryId = (Long)obj[2];				
					file.setCategoryName(obj[0].toString());	
					file.setCategoryId(categoryId);
			
					
					if(type.equalsIgnoreCase("constituency")){
						
						if(newsCountVO.getMuncipalityScopeId() != null && newsCountVO.getMuncipalityValuesList() != null){
							
							
							if(newsCountVO.getWardScopeId() != null && newsCountVO.getWardIdsList() != null && newsCountVO.getWardIdsList().size() >0)
								importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipalityAndWards(
										 categoryId ,newsCountVO );
						
							else
							importanceCountList = fileGallaryDAO
									.getNewsCountForALocationByCategoryAndImportanceForConstituencyWithMuncipality(
											 categoryId ,newsCountVO );
							
						}else{
					        importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForConstituency(
									 categoryId ,newsCountVO );
						}
					 
					}else if(type.equalsIgnoreCase("tehsil")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForMandal(
										 categoryId ,newsCountVO );
						
					}else if(type.equalsIgnoreCase("panchayat")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForPanchayat(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("muncipality")){
						
						
						if(newsCountVO.getWardIdsList() != null &&newsCountVO.getWardIdsList().size()>0 && newsCountVO.getWardScopeId() != null)
							importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForMuncipalityWithWards(
									 categoryId ,newsCountVO );
						else							
						  importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForMuncipality(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("ward")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForWard(
										 categoryId ,newsCountVO );
					}else if(type.equalsIgnoreCase("hamlet")){
						
						importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportanceForHamlet(
										 categoryId ,newsCountVO );
					}
			
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
			 e.printStackTrace();			 
		 }
		 
		 return filesList;
		 
	 } 
	 
	 public List<FileVO> setNewsCountValuesToFileVO2(List<Object[]> countByCategoryList,
				List<Long> candidateIds,Long constituencyScopeId,Long constituencyVal,Long tehsilScopeId,
				List<Long> tehsilIds,Long hamletScopeId,List<Long> hamletIds){
		 
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
					
					List<Object[]> importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForConstituency(
									candidateIds, categoryId , constituencyScopeId,constituencyVal,
									tehsilScopeId,tehsilIds,hamletScopeId,hamletIds);
			
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
			 e.printStackTrace();
			 
		 }
		 
	 }
	 
	public List<FileVO> setNewsCountValuesToFileVO(List<Object[]> countByCategoryList,List<Long> candidateIds,
			Long locationId, List<Long> locationValuesList, Long hamletScopeId,
			List<Long> hamletIds) {
		 log.debug("Entered into the setNewsCountValuesToFileVO service method");
		 try{
			 
			 
			 for(Object[] obj:countByCategoryList){				
					
					FileVO file = new FileVO();				
					Long categoryId = (Long)obj[2];				
					file.setCategoryName(obj[0].toString());	
					file.setCategoryId(categoryId);
					
					List<Object[]> importanceCountList = fileGallaryDAO
							.getNewsCountForALocationByCategoryAndImportanceForACandidate(
									candidateIds, categoryId , locationId , locationValuesList );
					
					 List<File> importanceCountList = fileGallaryDAO
								.getNewsCountForALocationByCategoryAndImportance(
										candidateIds, categoryId , locationId , locationValuesList,hamletScopeId , hamletIds );
					 
					 
					 for(File file1:importanceCountList){
						 
						// file1.
						 
						 
						 
					 }
			
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
			 log.error("Exception raised in setNewsCountValuesToFileVO service method");
			 e.printStackTrace();
		}
		 return null;
	 }
	
	public List<FileVO> getNewsCountForALocationByCategory1(Long registrationId,
			Long locationValue, Long locationId, Long publicationId) {
		
		log.debug("Enterd into the getNewsCountForALocationByCategory service method");
		 
		 try{			 
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(registrationId);

            List<Long> locationValuesList = new ArrayList<Long>();			
            locationValuesList.add(locationValue);
			
			
           if(locationId == 3)	{
				//locationValuesList = getAllBoothsInPanchayat(locationValue,
					//	publicationId, locationValuesList); 
        	   
        	   locationValuesList = new ArrayList<Long>();
	    	   
 	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(locationValue);
 	    	  
                for(Object[] obj:hamletsList){
             	   locationValuesList.add((Long)obj[0]);
                }
                locationId = 6L;
           }
           
           
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
	
	
	public ContentDetailsVO getNewsByLocationAndCategoryInPopup(FileVO fileVO){
		
		//List<FileVO> fileList = new ArrayList<FileVO>();
		List<FileGallary> filesList =new ArrayList<FileGallary>();
		
		try{			
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
			
			if(fileVO.getLocationId().longValue() == 3)
				filesList = getNewsDetailsForPanchayat(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 5)
				filesList = getNewsDetailsForMandal(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 4)
				filesList = getNewsDetailsForConstituency(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 7)
				filesList = getNewsDetailsForMuncipality(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 8 )
				filesList = getNewsDetailsForWard(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 6 )
				filesList = getNewsDetailsForHamlet(candidateIds,fileVO);
			
		contentDetailsVO = contentManagementService.getSelectedContentAndRelatedGalleriesInPopup(
				fileVO.getContentId(),fileVO.getRequestFrom(),fileVO.getRequestPageId(),
				fileVO.getIsCustomer(),filesList);

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return contentDetailsVO;
	}
	
	public List<FileGallary> getNewsDetailsForPanchayat(List<Long> candidateIds , FileVO fileVO){
		List<FileGallary> fileGallaryList = null;
		
		try{
			
            List<Long> hamletIds = null;
            Long hamletScopeId = 6L;
           
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(fileVO.getLocationVal());
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 
			 fileGallaryList = fileGallaryDAO.getFilegallaryDetailsForPanchayat(newsCountVO);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fileGallaryList;
	}
	
	public List<FileGallary> getNewsDetailsForMandal(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		try{

			Long tehsilScopeId = 5L;
			Long hamletScopeId = 6L;
			List<Long> hamletIds = null;
			List<Long> tehsilIds = new ArrayList<Long>();
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(fileVO.getLocationVal());
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
			}
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			tehsilIds.add(fileVO.getLocationVal());
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setTehsilIds(tehsilIds);
			newsCountVO.setTehsilScopeId(tehsilScopeId);
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			fileGallaryList = fileGallaryDAO.getNewsDetailsByForMandal(newsCountVO);
			//filesList =  processAllFileDetails(fileVO,fileList);
	
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
	}
	
	*//**
	 * This method will get the file gallaries details which the news contains for given hamlet by a category 
	 * @param candidateIds , list of candidate ids who the logged in user has the privilise to see the news
	 * @param fileVO , this contains all the details related to category ,  hamletscopeId and hamletId
	 * @return all the fileGallaries of news related to the input category of a hamlet
	 *//*
	public List<FileGallary> getNewsDetailsForHamlet(List<Long> candidateIds , FileVO fileVO)
	{
		log.debug("Entered into the getNewsDetailsForHamlet service method");
		
		List<FileGallary> fileGallaryList = null;
		List<Long> hamletIds = new ArrayList<Long>();
		
		try{
			hamletIds.add(fileVO.getLocationVal());
			
			List<Long> wardsList = new ArrayList<Long>(); 
			wardsList.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setHamletScopeId(6L);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 fileGallaryList = fileGallaryDAO.getNewsDetailsByForHamlets(newsCountVO);
			
			
		}catch(Exception e){
			log.error("Exception raised in getNewsDetailsForHamlet service method");
			e.printStackTrace();
		}
		
		return fileGallaryList;	
	}
	
	
	public List<FileGallary> getNewsDetailsForWard(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		
		try{
			
			
			List<Long> wardsList = new ArrayList<Long>(); 
			wardsList.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardsList);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			
			 fileGallaryList = fileGallaryDAO.getNewsDetailsByForWards(newsCountVO);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;		
		
	}
	
	public List<FileGallary> getNewsDetailsForMuncipality(List<Long> candidateIds , FileVO fileVO){
		
		List<FileGallary> fileGallaryList = null;
		
		try{
			Long muncipalityScopeId = 7L;
			List<Long> muncipalityValues = null;
			
	       List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	  
	       if(localElectionBodyList != null && localElectionBodyList.size() >0){
		     muncipalityValues = new ArrayList<Long>();	    		  
		     muncipalityValues.add((Long)localElectionBodyList.get(0));	
		     
	       }
	       
	       List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(muncipalityValues);
	       
	      
		     NewsCountVO newsCountVO = new NewsCountVO();
		     
		     newsCountVO.setCandidateIds(candidateIds);
		     newsCountVO.setMuncipalityScopeId(muncipalityScopeId);
		     newsCountVO.setMuncipalityValuesList(muncipalityValues);
		     newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 newsCountVO.setWardScopeId(8L);
			 newsCountVO.setWardIdsList(wardIds);
			 
			 if(wardIds != null && wardIds.size() >0){
				 fileGallaryList = fileGallaryDAO.getNewsDetailsByForMuncipalityWithWards(newsCountVO);
		    	   
		     }else
	            fileGallaryList = fileGallaryDAO.getNewsDetailsByForMuncipality(newsCountVO);
	       
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
		
		
	}
	
	
	public List<FileGallary> getNewsDetailsForConstituency(List<Long> candidateIds , FileVO fileVO){
		List<FileGallary> fileGallaryList = null;
		
		try{
			Long constituencyScopeId = 4L;
			List<Long> constituencyValuesList = new ArrayList<Long>();
			Long tehsilScopeId = 5L;
			List<Long> tehsilIds = new ArrayList<Long>();
			Long hamletScopeId = 6L;
			List<Long> hamletIds = new ArrayList<Long>();
			List<Long> panchayitIdsList = new ArrayList<Long>();
			 List<Long> wardIds = new ArrayList<Long>();
			
			constituencyValuesList.add(fileVO.getLocationVal());
			
			List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(fileVO.getLocationVal());
			
			 for(Object[] obj:mandalsList)
				 tehsilIds.add((Long)obj[0]);
			 
			 tehsilIds.add(0L);
			 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
			 
			 
			 for(Object[] obj :panchayitisList)
				 panchayitIdsList.add((Long)obj[0]);
			 
			 panchayitIdsList.add(0L);
		
			 if(panchayitIdsList != null && panchayitIdsList.size() >0)
				 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			List<Long> localElectionBodyIds =  null;
			
			if(tehsilIds != null && tehsilIds.size() != 1)		
				localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
			else
			    localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(fileVO.getLocationVal());

					
			
			localElectionBodyIds.add(0L);

	       wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);

	       
			//	tehsilIds.add(0L);
				hamletIds.add(0L);
				wardIds.add(0L);
				//panchayitIdsList.add(0L);
			
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setConstituencyScopeId(constituencyScopeId);
			 newsCountVO.setConstituencyValuesList(constituencyValuesList);
			 newsCountVO.setTehsilScopeId(tehsilScopeId);
			 newsCountVO.setTehsilIds(tehsilIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			 if(wardIds != null && wardIds.size() >0){
				 
				 newsCountVO.setWardScopeId(8L);
				 newsCountVO.setWardIdsList(wardIds);
				 
			 }
			 
			 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
				 newsCountVO.setMuncipalityScopeId(7L);
				 newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
				 
				 if(wardIds != null && wardIds.size() >0)
					 fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituencyWithMuncipalityAndWards(newsCountVO);
				 else				 
				   fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituencyWithMuncipality(newsCountVO);

			 
			 }else{			 
			  fileGallaryList = fileGallaryDAO.getNewsDetailsForConstituency(newsCountVO);
			 }
			 
			// filesList =  processAllFileDetails(fileVO,fileList);
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return fileGallaryList;
	}
	
	//by sasi start
	public ContentDetailsVO getNewsByLocationAndCategoryInPopup1(FileVO fileVO){
		log.debug("Entered into the getNewsByLocationAndCategoryInPopup service method");
		
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		List<FileGallary> filesList =new ArrayList<FileGallary>();
		
		try{
		
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
	
	        List<Long> locationValuesList = new ArrayList<Long>();			
	        locationValuesList.add(fileVO.getLocationVal());
			
			
	       if(fileVO.getLocationId() == 3){
	    	   
	       
				//locationValuesList = getAllBoothsInPanchayat(fileVO.getLocationVal(),
						//fileVO.getPublicationId(), locationValuesList);
	    	   locationValuesList = new ArrayList<Long>();
	    	   
	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(fileVO.getLocationVal());
	    	  
               for(Object[] obj:hamletsList){
            	   locationValuesList.add((Long)obj[0]);
            	   
            	   
               }
               fileVO.setLocationId(6L);
				
				//fileVO.setLocationId(9L);
	       }
	       
	       if(fileVO.getLocationId() == 7){
	    	   
	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
	    		  
	    		  
	    		  locationValuesList = new ArrayList<Long>();	    		  
	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
	    		  
	    	  }
	    		  
	       }
	       filesList = fileGallaryDAO.getNewsByLocationAndCategoryInPopup(
					candidateIds,fileVO,locationValuesList); 
	       
	       
	       contentDetailsVO = contentManagementService.getSelectedContentAndRelatedGalleriesInPopup(fileVO.getContentId(),fileVO.getRequestFrom(),fileVO.getRequestPageId(),fileVO.getIsCustomer(),filesList);
	      
	       return contentDetailsVO;
			}catch(Exception e){
			
			log.error("Exception raised in  the getNewsByLocationAndCategory service method");
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public List<FileVO> getNewsByLocationAndCategory(FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
			
			if(fileVO.getLocationId().longValue() == 3)
				filesList = getNewsForPanchayat(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 5)
				filesList = getNewsForMandal(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 4)
				filesList = getNewsForConstituency(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 7)
				filesList = getNewsForMuncipality(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 8)
				filesList = getNewsForWard(candidateIds,fileVO);
			else if(fileVO.getLocationId().longValue() == 6)
				filesList = getNewsForHamlet(candidateIds,fileVO);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return filesList;
	}
	
	
	public List<FileVO> getNewsForPanchayat(List<Long> candidateIds , FileVO fileVO){
		 List<FileVO> filesList = null;
		
		try{
			
             List<Long> hamletIds = null;
             Long hamletScopeId = 6L;
            
			 List<Long> panchayitIdsList  = new ArrayList<Long>();
			 panchayitIdsList.add(fileVO.getLocationVal());
			
			 hamletIds =  panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			List<Object[]> fileList = fileGallaryDAO.getNewsByForPanchayat(newsCountVO);
			filesList = processAllFileDetails(fileVO,fileList);
		
			
		}catch(Exception e){
			e.printStackTrace();			
		}
		
		return filesList;
		
	}
	
	*//**
	 * This method will get the news details for a hamlet by selected category
	 * @param candidateIds  , list of candidate whose news can see the logged in user
	 * @param fileVO , this contains the details like selected category ,scopeId and hamlet values
	 * @return all the selected category news details for a hamlet
	 *//*
	public List<FileVO> getNewsForHamlet(List<Long> candidateIds , FileVO fileVO)
	{
		
		log.debug("Entered into the getNewsForHamlet service method");
		List<FileVO> filesList = null;
		Long hamletScopeId = 6L;
		List<Long> hamletIds = new ArrayList<Long>();
		try
		{
			hamletIds.add(fileVO.getLocationVal());
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
            newsCountVO.setCandidateIds(candidateIds);
			
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			
			List<Object[]> fileList = null;
			
			 fileList = fileGallaryDAO.getNewsByHamlet(newsCountVO);
			 
			 filesList =  processAllFileDetails(fileVO,fileList);
		
		}
		catch(Exception e)
		{
			log.debug("Exception raised in getNewsForHamlet service method");
			e.printStackTrace();
		}
		
		return filesList;
		
		
	}
	

	
	public List<FileVO> getNewsForWard(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			
			List<Long> wardIdsList = new ArrayList<Long>();
			wardIdsList.add(fileVO.getLocationVal());
			Long wardScopeId = 8L;
			
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardIdsList);
			
			List<Object[]> fileList = null;
			
				 fileList = fileGallaryDAO.getNewsByWard(newsCountVO);
			

			 
			
			  filesList =  processAllFileDetails(fileVO,fileList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
       return filesList;
		
	}
	
	public List<FileVO> getNewsForMuncipality(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			
			List<Long> muncipalityValuesList = new ArrayList<Long>();
			
			List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0)
	    		  muncipalityValuesList.add((Long)localElectionBodyList.get(0));
	    	
	    	  
	    	  List<Long> wardIds =  boothDAO.getWardIdsByLocalElectionBodyIds(muncipalityValuesList);
	    	  
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setMuncipalityScopeId(7L);
			newsCountVO.setMuncipalityValuesList(muncipalityValuesList);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			newsCountVO.setWardScopeId(8L);
			newsCountVO.setWardIdsList(wardIds);
			
			List<Object[]> fileList = null;
			
			if(wardIds != null && wardIds.size() >0)
				 fileList = fileGallaryDAO.getNewsByForMuncipalityWithWards(newsCountVO);
			else
				fileList = fileGallaryDAO.getNewsByForMuncipality(newsCountVO);

			 
			
			  filesList =  processAllFileDetails(fileVO,fileList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
       return filesList;
		
	}
	
	
	public List<FileVO> getNewsForMandal(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		
		try{
			Long tehsilScopeId = 5L;
			Long hamletScopeId = 6L;
			List<Long> hamletIds = null;
			List<Long> tehsilIds = new ArrayList<Long>();
			
			List<Long> panchayitIdsList = panchayatDAO.getPanchayatIdsByTehsilId(fileVO.getLocationVal());
			
			if(panchayitIdsList != null && panchayitIdsList.size() >0){
			    hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);					
			}
			
			
			NewsCountVO newsCountVO = new NewsCountVO();
			
			tehsilIds.add(fileVO.getLocationVal());
			
			newsCountVO.setCandidateIds(candidateIds);
			newsCountVO.setTehsilIds(tehsilIds);
			newsCountVO.setTehsilScopeId(tehsilScopeId);
			newsCountVO.setHamletScopeId(hamletScopeId);
			newsCountVO.setHamletIds(hamletIds);
			newsCountVO.setCategoryId(fileVO.getCategoryId());
			newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			newsCountVO.setStartIndex(fileVO.getStartIndex());
			newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			
			List<Object[]> fileList = fileGallaryDAO.getNewsByForMandal(newsCountVO);
			filesList =  processAllFileDetails(fileVO,fileList);

			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return filesList;
	}
	
	
	public List<FileVO> getNewsForConstituency(List<Long> candidateIds , FileVO fileVO){
		List<FileVO> filesList = null;
		try{
			Long constituencyScopeId = 4L;
			List<Long> constituencyValuesList = new ArrayList<Long>();
			Long tehsilScopeId = 5L;
			List<Long> tehsilIds = new ArrayList<Long>();			
			Long hamletScopeId = 6L;
			List<Long> hamletIds = new ArrayList<Long>();			
			List<Long> panchayitIdsList = new ArrayList<Long>();			
			List<Long> wardIds = new ArrayList<Long>();
			
			constituencyValuesList.add(fileVO.getLocationVal());
			
			List<Object[]> mandalsList = delimitationConstituencyMandalDAO.getMandalsOfConstituency(fileVO.getLocationVal());
			
			 for(Object[] obj:mandalsList)
				 tehsilIds.add((Long)obj[0]);
			 
			 tehsilIds.add(0L);
			 
			 List<Object[]> panchayitisList = panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
			 
			 
			 for(Object[] obj :panchayitisList)
				 panchayitIdsList.add((Long)obj[0]);
			 
			 panchayitIdsList.add(0L);
		
			 if(panchayitIdsList != null && panchayitIdsList.size() >0)
				 hamletIds = panchayatHamletDAO.getHamletsOfPanchayitis(panchayitIdsList);
			 
		List<Long> localElectionBodyIds  =  null;
		
		if(tehsilIds != null && tehsilIds.size() != 1)		
			localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
		else
		 localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituency(fileVO.getLocationVal());


		if(localElectionBodyIds != null && localElectionBodyIds.size() >0)
		  wardIds = boothDAO.getWardIdsByLocalElectionBodyIds(localElectionBodyIds);
			 
		  
			hamletIds.add(0L);
			
			wardIds.add(0L);
			
			 NewsCountVO newsCountVO = new NewsCountVO();
			 
			 newsCountVO.setCandidateIds(candidateIds);
			 newsCountVO.setConstituencyScopeId(constituencyScopeId);
			 newsCountVO.setConstituencyValuesList(constituencyValuesList);
			 newsCountVO.setTehsilScopeId(tehsilScopeId);
			 newsCountVO.setTehsilIds(tehsilIds);
			 newsCountVO.setHamletScopeId(hamletScopeId);
			 newsCountVO.setHamletIds(hamletIds);
			 newsCountVO.setCategoryId(fileVO.getCategoryId());
			 newsCountVO.setNewsImportanceId(fileVO.getImportanceId());
			 newsCountVO.setStartIndex(fileVO.getStartIndex());
			 newsCountVO.setMaxResults(fileVO.getMaxResult());
			 
			 if(wardIds != null && wardIds.size() >0){
				 
				 newsCountVO.setWardScopeId(8L);
				 newsCountVO.setWardIdsList(wardIds);
				 
				 
			 }
			   
			 
			 List<Object[]> fileList  = null;
			 
			 if(localElectionBodyIds != null && localElectionBodyIds.size() >0){
				 newsCountVO.setMuncipalityScopeId(7L);
				 newsCountVO.setMuncipalityValuesList(localElectionBodyIds);
				 
				 if(wardIds != null && wardIds.size() >0)
					fileList = fileGallaryDAO.getNewsByForConstituencyWithMuncipalityWithWards(newsCountVO);
				 else				 
				   fileList = fileGallaryDAO.getNewsByForConstituencyWithMuncipality(newsCountVO);
				 
			 }else{
			 
			 fileList = fileGallaryDAO.getNewsByForConstituency(newsCountVO);
			 }
			 
			 filesList =  processAllFileDetails(fileVO,fileList);
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return filesList;
		
	}
	
	
	public List<FileVO> processAllFileDetails(FileVO fileVO , List<Object[]> filesList){
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		try{
			List<Long> fileIdsList = new ArrayList<Long>();
			
			for(Object[] obj1:filesList)
				fileIdsList.add(((File)obj1[0]).getFileId());
			
			Map<Long ,Long> problemCountMap = new HashMap<Long, Long>();
			List<Object[]> problemCountList = newsProblemDAO.getProblemIdsByFileIds(fileIdsList);
			
			for(Object[] problemCount:problemCountList)
				problemCountMap.put((Long)problemCount[1], (Long)problemCount[0]);
			
			 for(Object[] obj:filesList){
		    	   
		    	   File fileDetails = (File)obj[0];
		    	   String visibility = obj[2].toString();
		    	   
		    	   FileVO file = new FileVO();
		    	   file.setIsPrivate(visibility);
		    	   
		    	   if(fileDetails.getRegionScopes() != null)
		    		   file.setLocationScopeValue(fileDetails.getRegionScopes().getScope());
		    	   
		    	   
		    	   
		    	   if(problemCountMap.get(fileDetails.getFileId()) != null){
		    		   file.setIsProblem("true");
		    		   file.setProblemId(problemCountMap.get(fileDetails.getFileId()));
		    	   }
		    	   else
		    		   file.setIsProblem("false");
		    	   
		    	   
		    	     Set<FileSourceLanguage> fileSourceLanguageSet = fileDetails.getFileSourceLanguage();
		    		 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
		    		 Set<String> sourceSet = new HashSet<String>();
		 			 Set<String> languageSet = new HashSet<String>();
		 			 StringBuilder sourceVal =new StringBuilder();
					 StringBuilder languageVal =new StringBuilder();
					 
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 if(fileVO.getSourceId() == null && fileVO.getLanguegeId() == null)
						 {
							  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							  if(fileSourceLanguage.getSource() != null)
							  sourceSet.add(fileSourceLanguage.getSource().getSource());
							  if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
						 else if(fileVO.getLanguegeId() != null){
							 if(fileVO.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
							 {
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
								  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
							 }
						 }
						 else if(fileVO.getSourceId() != null){
							 if(fileVO.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
							 { 
								 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
								 if(fileSourceLanguage.getSource() != null)
								 sourceSet.add(fileSourceLanguage.getSource().getSource());
								 if(fileSourceLanguage.getLanguage() != null)
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
		             if(sourceVal != null && sourceVal.length() > 0)
		             sourceVal.deleteCharAt(sourceVal.length() - 1);
		             if(languageVal != null && languageVal.length() > 0)
		             languageVal.deleteCharAt(languageVal.length() - 1);
		             file.setMultipleSource(fileVOSourceLanguageList.size());
		             Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
		             file.setFileVOList(fileVOSourceLanguageList);
				
				
	    		  file.setSource(sourceVal!=null?sourceVal.toString():"");
	    		  file.setLanguage(languageVal!=null?languageVal.toString():"");
		    	  file.setTitle(fileDetails.getFileTitle());
		    	  file.setDescription(fileDetails.getFileDescription());
		    	  file.setContentId((Long)obj[1]);
		    	  file.setFileDate(fileDetails.getFileDate()!=null?fileDetails.getFileDate().toString():"");
		    	   
		    	  if(fileDetails.getRegionScopes() != null && fileDetails.getLocationValue() != null)
		    	  file.setLocationName(candidateDetailsService.getLocationDetails(fileDetails.getRegionScopes().getRegionScopesId() ,fileDetails.getLocationValue()));
		    	  fileList.add(file);
		    	   
		       }
			
		}catch(Exception e){e.printStackTrace();
			
		}
		return fileList;
		
	}
	
	
	public List<FileVO> getNewsByLocationAndCategory1(FileVO fileVO){
		
		log.debug("Entered into the getNewsByLocationAndCategory service method");
		
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		List<Object[]> filesList =new ArrayList<Object[]>();
		
		try{
		
			List<Long> candidateIds = getCandidateDetailsByRegistrationId(fileVO.getUserId());
	
	        List<Long> locationValuesList = new ArrayList<Long>();			
	        locationValuesList.add(fileVO.getLocationVal());
			
			
	       if(fileVO.getLocationId() == 3){
	    	   
	       
				//locationValuesList = getAllBoothsInPanchayat(fileVO.getLocationVal(),
						//fileVO.getPublicationId(), locationValuesList);
	    	   locationValuesList = new ArrayList<Long>();
	    	   
	    	  List<Object[]> hamletsList =  panchayatHamletDAO.getHamletsOfAPanchayat(fileVO.getLocationVal());
	    	  
               for(Object[] obj:hamletsList){
            	   locationValuesList.add((Long)obj[0]);
            	   
            	   
               }
               fileVO.setLocationId(6L);
				
				//fileVO.setLocationId(9L);
	       }
	       
	       if(fileVO.getLocationId() == 7){
	    	   
	    	  List<Long> localElectionBodyList =  assemblyLocalElectionBodyDAO.getLocalElectionBodyId(fileVO.getLocationVal());
	    	  
	    	  if(localElectionBodyList != null && localElectionBodyList.size() >0){
	    		  
	    		  
	    		  locationValuesList = new ArrayList<Long>();	    		  
	    		  locationValuesList.add((Long)localElectionBodyList.get(0));
	    		  
	    	  }
	    		  
	       }
	       
	       
	       filesList = fileGallaryDAO
					.getNewsByLocationAndCategory(
							candidateIds,fileVO,locationValuesList);
	       
	       
	       for(Object[] obj:filesList){
	    	   
	    	   File fileDetails = (File)obj[0];
	    	   
	    	   FileVO file = new FileVO();
	    	   
	    	   
	    	     Set<FileSourceLanguage> fileSourceLanguageSet = fileDetails.getFileSourceLanguage();
	    		 List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>(); 
	    		 Set<String> sourceSet = new HashSet<String>();
	 			 Set<String> languageSet = new HashSet<String>();
	 			 StringBuilder sourceVal =new StringBuilder();
				 StringBuilder languageVal =new StringBuilder();
				 
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
					 if(fileVO.getSourceId() == null && fileVO.getLanguegeId() == null)
					 {
						  setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
						  if(fileSourceLanguage.getSource() != null)
						  sourceSet.add(fileSourceLanguage.getSource().getSource());
						  if(fileSourceLanguage.getLanguage() != null)
						  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
					 }
					 else if(fileVO.getLanguegeId() != null){
						 if(fileVO.getLanguegeId().intValue() == fileSourceLanguage.getLanguage().getLanguageId().intValue())
						 {
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
							  languageSet.add(fileSourceLanguage.getLanguage().getLanguage());
						 }
					 }
					 else if(fileVO.getSourceId() != null){
						 if(fileVO.getSourceId().intValue() == fileSourceLanguage.getSource().getSourceId().intValue())
						 { 
							 setSourceLanguageAndPaths(fileSourceLanguage,fileVOSourceLanguageList);
							 if(fileSourceLanguage.getSource() != null)
							 sourceSet.add(fileSourceLanguage.getSource().getSource());
							 if(fileSourceLanguage.getLanguage() != null)
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
	             if(sourceVal != null && sourceVal.length() > 0)
	             sourceVal.deleteCharAt(sourceVal.length() - 1);
	             if(languageVal != null && languageVal.length() > 0)
	             languageVal.deleteCharAt(languageVal.length() - 1);
	             file.setMultipleSource(fileVOSourceLanguageList.size());
	             Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
	             file.setFileVOList(fileVOSourceLanguageList);
			
			
    		  file.setSource(sourceVal!=null?sourceVal.toString():"");
    		  file.setLanguage(languageVal!=null?languageVal.toString():"");
	    	  file.setTitle(fileDetails.getFileTitle());
	    	  file.setDescription(fileDetails.getFileDescription());
	    	  file.setContentId((Long)obj[1]);
	    	  file.setFileDate(fileDetails.getFileDate()!=null?fileDetails.getFileDate().toString():"");
	    	   
	    	  fileList.add(file);
	    	   
	       }
			
			return fileList;
		}catch(Exception e){
			
			log.error("Exception raised in  the getNewsByLocationAndCategory service method");
			e.printStackTrace();
			return null;
			
		}		
	}
	
	
public Long saveContentNotesByContentId(final Long contentId ,final  String commentText){
		
		log.debug("Entered into the saveCommentByContentId service method");
		 Long savedId = null;
		
		try{
			 savedId = (Long)transactionTemplate.execute(new TransactionCallback() {
				public Long doInTransaction(TransactionStatus status) {
			ContentNotes contentNotes = new ContentNotes();
			//contentNotes.setContentId(contentId);
			contentNotes.setFileGallary(fileGallaryDAO.get(contentId));
			contentNotes.setIsDelete("false");
			contentNotes.setNotes(commentText);
			contentNotes.setInsertedTime(new Date());
			contentNotes.setUpdatedTime(new Date());
			
			contentNotes = contentNotesDAO.save(contentNotes);
			
			return contentNotes.getContentNotesId();
				}
			});
			
		}catch(Exception e){
			log.error("Exception raised in saveCommentByContentId method :"+e);
			e.printStackTrace();
		}
		
		return savedId;
		
	}
	
	
	public List<CommentVO> getContentNotesByContentId(Long contentId,
			Long registrationId) {
		
		log.debug("Entered into the getCommnetsByContentId service method");
		List<CommentVO> commentsList = new ArrayList<CommentVO>();
		
		try{			
			List<Object[]> comments = contentNotesDAO
					.getContentNotesByContentId(contentId);		
			
			for(Object[] obj:comments){				
				CommentVO commentVO = new CommentVO();				
				commentVO.setCommentId((Long)obj[0]);
				commentVO.setComment(obj[1].toString());
				commentsList.add(commentVO);			
			}
			
		}catch(Exception e){
			log.error("Exception raised in getCommnetsByContentId method :"+e);
			e.printStackTrace();	
			return null;
		}
		return commentsList;
	}
	
	
	public String removeContentNotes(Long contentNotesId){
		log.debug("Entered into the removeContentNotes method");
		
		try{
			//contentNotesDAO.remove(contentNotesId);
			
			ContentNotes contentNews = contentNotesDAO.get(contentNotesId);
			
			contentNews.setIsDelete(IConstants.TRUE);
			
			contentNews = contentNotesDAO.save(contentNews);
			
		}catch(Exception e){
			log.error("Exception raised in the removeContentNotes method :" +e);
		 e.printStackTrace();			
		}
		return IConstants.SUCCESS;		
	}
	
	public String addFlagToNews(Long contentId , Long userId){
		
		log.debug("Entered into the addFlagToNews service method");
		
		try{
			Long count = 0L;
			
			List<Long> flagCountList = newsFlagDAO.getCountForFlagByContentId(contentId);
			
			if(flagCountList != null && flagCountList.size() >0)
				count = flagCountList.get(0);
			
			if(count.longValue() == 0 ){
				
				NewsFlag newsFlag = new NewsFlag();
				
				newsFlag.setFileGallary(fileGallaryDAO.get(contentId));
				newsFlag.setUser(userDAO.get(userId));			
				newsFlagDAO.save(newsFlag);
			}
						
		}catch(Exception e){			
			log.debug("Exception raised in addFlagToNews service method");			
			e.printStackTrace();
		}
		return IConstants.SUCCESS;
		
	}
	
	public String checkForFlag(Long contentId){
		
		log.debug("Entered into the checkForFlag service method");
		
		try{
		
		List<Long> contentIds = new ArrayList<Long>();		
		
		contentIds.add(contentId);
		
		List<Object[]> countList= newsFlagDAO.getCountForFlagByFileGallaryId(contentIds);
		
		if(countList != null && countList.size() >0){
		 Object[] obj = countList.get(0);
		 
		 Long count =(Long) obj[1];
		 
		 if( count.longValue() == 0)
			 return "false";
		 else
			 return "true";
		 
		}else
			return "false";
		}catch(Exception e){
			log.error("Exception raised in checkForFlag service method");
			e.printStackTrace();
			return null;
			
		}
	}
	
	
	public String removeFlagForNews(Long contentId){
		
		log.debug("Entered into the removeFlagForNews service method");
		
		try{
			
			newsFlagDAO.removeFlagForNews(contentId);
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in removeFlagForNews service method");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public String updateVisibility(Long contentId , String visibility){
		
		log.debug("Entered into the updateVisibility service method");
		
		try{
		
			FileGallary fileGallary = fileGallaryDAO.get(contentId);
			
			fileGallary.setIsPrivate(visibility);
			if(visibility.equalsIgnoreCase("false"))
			{
				
				Gallary gallary = gallaryDAO.get(fileGallary.getGallary().getGallaryId());
				gallary.setIsPrivate(visibility);
			}
			fileGallaryDAO.save(fileGallary);
			
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in updateVisibility service method");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String checkForVisibilityStatus(Long contentId){
		
		log.debug("Entered into the checkForVisibilityStatus service method");
		
		try{
			
			List<String> visibilityList = fileGallaryDAO.checkForVisibilityStatus(contentId);
			
			if(visibilityList != null && visibilityList.size() >0){
				
				String visibility = visibilityList.get(0);
				
				if(visibility.equalsIgnoreCase("false"))
					return IConstants.PUBLIC;
				else
					return IConstants.PRIVATE;				
				
			}
				
		
			
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception raised in checkForVisibilityStatus service method");
			e.printStackTrace();
			return null;
		}
		
	}

	*//**
	 * This Method is uesd for get the flged news and noted news 
	 * @param FileVO inputs
	 * @return List<FileVO>
	 * @date 01-04-2013
	 *//*
	public List<FileVO> getNewsForFlagedAndNoted(FileVO inputs) {
		List<FileVO> newsList = new ArrayList<FileVO>();
		try {
			log.debug("Entered into the getNewsForFlagedAndNOted service method in NewsMonitoringService");
			List<Object[]> fileList = fileGallaryDAO.getNewsForRegisterUsers1(inputs);
			List<Long> contentIds = new ArrayList<Long>();
			FileVO fileVO = null;
			List<FileGallary> newsFile = null; 
			Long flaggedCount = null;
			Long notedCount = null;
	  	    for(Object[] obj:fileList)
	  	    {
	  		  contentIds.add((Long)obj[0]);
	  		  
	  	    }
	  	    if(inputs.getTitle().equalsIgnoreCase("flagedNews"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		newsFile= newsFlagDAO.getFlagedMews(contentIds);
	  	    	}
	  	    }	
	  	    else if(inputs.getTitle().equalsIgnoreCase("flagedCount"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		flaggedCount= newsFlagDAO.getFlaggedNewsCount(inputs,contentIds);
	  	    		fileVO = new FileVO();
	  	    		fileVO.setTotalFlaggedNews(flaggedCount.intValue());
	  	    		newsList.add(fileVO);
	  	    	}
	  	    	
	  	    }
	  	    else if(inputs.getTitle().equalsIgnoreCase("notedCount"))
	  	    {
	  	    	if(contentIds.size() >0)
	  	    	{
	  	    		notedCount= contentNotesDAO.getNotedNewsCount(inputs,contentIds);
	  	    		fileVO = new FileVO();
	  	    		fileVO.setTotalFlaggedNews(notedCount.intValue());
	  	    		newsList.add(fileVO);
	  	    	}
	  	    	
	  	    }
	  	    else 
		    {
		    	if(contentIds.size() >0)
		    	{
		    		newsFile = contentNotesDAO.getNotedNews(contentIds);
		    	}
		    }
	  	    
	  	    if(newsFile != null && newsFile.size() > 0 )
	  	    {
	  	    		for (FileGallary news : newsFile)
	  	    		{
	  	    			fileVO = new FileVO();
	  	    			File file = news.getFile();
	  	    			Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
	  	    			fileVO.setTitle(file.getFileTitle());
	  	    			fileVO.setFileId(file.getFileId());
	  	    			fileVO.setVisibility(news.getIsPrivate());
	  	    			fileVO.setDescription(file.getFileDescription());
	  	    			fileVO.setFileDate(file.getFileDate().toString());
	  	    			fileVO.setLocationScopeValue(file.getRegionScopes().getScope());
	  	    			fileVO.setGallaryName(news.getGallary().getName());
	  	    			if(file.getCategory() != null)
	  	    			fileVO.setCategoryName(file.getCategory().getCategoryType());
	  	    			fileVO.setGallaryId(news.getFileGallaryId());
	  	    			for (FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet) {
	  	    				fileVO.setSource(fileSourceLanguage.getSource().getSource());
						}
	  	    			fileVO.setLocationValue(candidateDetailsService.getLocationDetails(file.getRegionScopes().getRegionScopesId(), file.getLocationValue()));
	  	    			newsList.add(fileVO);
					}
	  	    }
	  	    	
			} 
		    catch (Exception e) 
		    {
		    	log.error("Exception raised in the getNewsForFlagedAndNOted service method in NewsMonitoringService", e);
			}
		
		return newsList;
	}
	
*/}
