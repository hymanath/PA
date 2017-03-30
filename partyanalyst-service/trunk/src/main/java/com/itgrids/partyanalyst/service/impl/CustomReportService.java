package com.itgrids.partyanalyst.service.impl;

//import static com.itgrids.partyanalyst.service.impl.AlertService.LOG;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICustomReportFileDAO;
import com.itgrids.partyanalyst.dao.ICustomReportImageDAO;
import com.itgrids.partyanalyst.dao.ICustomReportLocationDAO;
import com.itgrids.partyanalyst.dao.ICustomReportObserverDAO;
import com.itgrids.partyanalyst.dao.ICustomReportProgramDAO;
import com.itgrids.partyanalyst.dao.hibernate.CustomReportDAO;
import com.itgrids.partyanalyst.dto.CustomReportVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.service.ICustomReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

public class CustomReportService extends AlertService implements ICustomReportService{
	private final static Logger LOG =  Logger.getLogger(CustomReportService.class);
	 private CustomReportDAO customReportDAO;
	 private CustomReportVO customReportVO;
	 private ActivityService activityService;
	 
	
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ICustomReportProgramDAO customReportProgramDAO;
	private ICustomReportObserverDAO customReportObserverDAO;
    private ICustomReportLocationDAO customReportLocationDAO;
    private ICustomReportImageDAO customReportImageDAO;
    private ICustomReportFileDAO customReportFileDAO;
	
    
	
	public ICustomReportFileDAO getCustomReportFileDAO() {
		return customReportFileDAO;
	}
	public void setCustomReportFileDAO(ICustomReportFileDAO customReportFileDAO) {
		this.customReportFileDAO = customReportFileDAO;
	}
	public ICustomReportImageDAO getCustomReportImageDAO() {
		return customReportImageDAO;
	}
	public void setCustomReportImageDAO(ICustomReportImageDAO customReportImageDAO) {
		this.customReportImageDAO = customReportImageDAO;
	}
	
	public ICustomReportLocationDAO getCustomReportLocationDAO() {
		return customReportLocationDAO;
	}
	public void setCustomReportLocationDAO(
			ICustomReportLocationDAO customReportLocationDAO) {
		this.customReportLocationDAO = customReportLocationDAO;
	}
	public ICustomReportObserverDAO getCustomReportObserverDAO() {
		return customReportObserverDAO;
	}

	public void setCustomReportObserverDAO(
			ICustomReportObserverDAO customReportObserverDAO) {
		this.customReportObserverDAO = customReportObserverDAO;
	}

	public ActivityService getActivityService() {
			return activityService;
		}

		public void setActivityService(ActivityService activityService) {
			this.activityService = activityService;
		}

		public TransactionTemplate getTransactionTemplate() {
			return transactionTemplate;
		}
	public ICustomReportProgramDAO getCustomReportProgramDAO() {
		return customReportProgramDAO;
	}
	public void setCustomReportProgramDAO(
			ICustomReportProgramDAO customReportProgramDAO) {
		this.customReportProgramDAO = customReportProgramDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	private TransactionTemplate transactionTemplate;
		public CustomReportVO getCustomReportVO() {
		return customReportVO;
	}
	
	public void setCustomReportVO(CustomReportVO customReportVO) {
		this.customReportVO = customReportVO;
	}
	
		public CustomReportDAO getCustomReportDAO() {
		return customReportDAO;
	}
	
	public void setCustomReportDAO(CustomReportDAO customReportDAO) {
		this.customReportDAO = customReportDAO;
	}

	
	public List<CustomReportVO> getTotalExpectedReports(Long customReportProgramId) {
		List<CustomReportVO> customReportList=new ArrayList<CustomReportVO>();
		List<Object[]> reportDetails=null;
		
		try{
			reportDetails = customReportDAO.getTotalExpectedReports(customReportProgramId);
			if(reportDetails !=null && reportDetails.size() >0){
				for(Object[] objects:reportDetails)
				{
					CustomReportVO customReportVO=new CustomReportVO();
					customReportVO.setName(objects[0] !=null ? objects[0].toString():"");
					customReportVO.setCount(objects[1]!=null && (Long)objects[1]> 0l ?(Long)objects[1]:0l);
					customReportList.add(customReportVO);					
					
				}
			}
			
		}
			catch(Exception e){
				 System.out.println("EXCEPTION RAISED IN  getTotalExpertedReports.");
				 e.printStackTrace();
		}
		return customReportList;
	}
	public static String folderCreation(){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
	  		
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			
			String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String nominatedPostDir = ActivityService.createFolder(staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS);
			 
			 String yr = String.valueOf(year); // YEAR YYYY
			
			 StringBuilder str = new StringBuilder();
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			 str.append(monthText).append("-").append(yr);
			 String mnthYr = str.toString();
			 String mnthYrDir = staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS+"/"+mnthYr;
			 String mnthDirSts = ActivityService.createFolder(mnthYrDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/"+IConstants.NOMINATED_POST_DOCUMENTS+"/"+mnthYr;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}

	
	public ResultStatus saveCustomReportUploadFile(final Map<File, String> mapfiles, final Long userId) {
		final ResultStatus resultStatus = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
		String folderName = folderCreation();
		CustomReportFile customReportFile = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		
		 StringBuilder pathBuilder = new StringBuilder();
		 StringBuilder str ;
		 
			
		 for (Map.Entry<File, String> entry : mapfiles.entrySet())
		 {
			 str = new StringBuilder();
			 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
				
			 pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
			 .append(entry.getValue());
			 str.append(randomNumber).append(".").append(entry.getValue());
			 activityService = new ActivityService();
			String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
			 
				if(fileCpyStts.equalsIgnoreCase("error")){
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					LOG.error(" Exception Raise in copying file");
					throw new ArithmeticException();
				}
				
				customReportFile = new CustomReportFile();
				customReportFile.setPath(pathBuilder.toString());
				customReportFile.setIsDeleted("N");
				customReportFile = customReportFileDAO.save(customReportFile);
				
		 }
		 resultStatus.setResultCode(0);
		 resultStatus.setResultState(customReportFile.getCustomReportFileId());
		 
				}
			});
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
		}
		return resultStatus;
	}
	public List<CustomReportVO> getCustomReportPrograms(String startDateStr,String endDateStr){
		List<CustomReportVO> finalList = new ArrayList<CustomReportVO>(0);
		
		try {
			  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		      Date fromDate = null,toDate = null;
		      
		      if(startDateStr != null && endDateStr != null){
		        fromDate = sdf.parse(startDateStr);
		        toDate = sdf.parse(endDateStr);
		      }			
			List<Object[]> objList =customReportProgramDAO.getCustomReportPogram(fromDate,toDate);
			if(objList !=null && objList.size()>0){
				for (int i = 0; i < objList.size(); i++) {
					CustomReportVO vo=new CustomReportVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objList.get(i)[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objList.get(i)[1]));
					if(i==0)
						vo.setSelected("true");
					finalList.add(vo);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception Occured in getCustomReportProgram() method, Exception - ",e);
		}
		return finalList;
	}	
	
	public List<CustomReportVO> getProgramReportsDetails(Long programId){
		List<CustomReportVO> finalList = new ArrayList<CustomReportVO>(0);
		try {
			Map<Long,List<CustomReportVO>> observersMap = new LinkedHashMap<Long, List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> locationsMap =new LinkedHashMap<Long, List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> imagesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> filesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			
			//get observer details for program
			//0-customReportId,1-tdpCadreId,2-firstname
			List<Object[]> obserersObjList = customReportObserverDAO.getObserverDetails(programId);
			if(obserersObjList != null && obserersObjList.size() > 0){
				for (Object[] objects : obserersObjList) {
					if(observersMap.get((Long)objects[0]) == null){
						List<CustomReportVO> newObserverVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setName(objects[2].toString());
						newObserverVoList.add(vo);
						observersMap.put((Long)objects[0], newObserverVoList);
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setName(objects[2].toString());
						observersMap.get((Long)objects[0]).add(vo);
					}
				}
			}
			
			//get location details for program 
			//0-customReportId,1-locationScopeId,2-scope,3-locationValue,4-customReportLocationId 
			List<Object[]> locationsObjList = customReportLocationDAO.getLocationDetails(programId);
			if (locationsObjList != null && locationsObjList.size() > 0) {
				for (Object[] objects : locationsObjList) {
					if (locationsMap.get((Long)objects[0]) == null){
						List<CustomReportVO> newLocationVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setScope(objects[2].toString());
						vo.setLocationValue((Long)objects[3]);
						newLocationVoList.add(vo);
						locationsMap.put((Long)objects[0], newLocationVoList);						
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setScope(objects[2].toString());
						vo.setLocationValue((Long)objects[3]);
						locationsMap.get((Long)objects[0]).add(vo);
					}
				}				
			}
			//get image details for program
			//0-customReportId,1-imageName,2-path,3-customReportimageId
			List<Object[]> imagesObjList = customReportImageDAO.getImageDetails(programId);
			if (imagesObjList != null && imagesObjList.size() > 0) {
				for (Object[] objects : imagesObjList) {
					if (imagesMap.get((Long)objects[0]) == null){
						List<CustomReportVO> newImageVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2].toString());
						newImageVoList.add(vo);
						imagesMap.put((Long)objects[0], newImageVoList);
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2].toString());
						imagesMap.get((Long)objects[0]).add(vo);
					}
				}				
			}
			
			//get file details for program
			//0-customReportId,1.fileName,2.path,3-customReportFileId
			List<Object[]> fileObjList = customReportFileDAO.getFileDetails(programId);
			if(fileObjList !=null && fileObjList.size() > 0){
				for(Object[] objects :fileObjList){
					if(filesMap.get((Long)objects[0]) ==null){
						List<CustomReportVO> newFileVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2].toString());
						newFileVoList.add(vo);
						filesMap.put((Long)objects[0], newFileVoList);
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2].toString());
						filesMap.get((Long)objects[0]).add(vo);
					}
				}
			}
			
			if(observersMap != null && observersMap.size() > 0){
				for (Entry<Long, List<CustomReportVO>> entry : observersMap.entrySet()) {
					CustomReportVO vo = new CustomReportVO();
					vo.setReportId(entry.getKey());
					vo.setObserversList(entry.getValue());
					finalList.add(vo);
				}
			}
			
			if(locationsMap != null && locationsMap.size() > 0){
				for (Entry<Long, List<CustomReportVO>> entry : locationsMap.entrySet()) {
					CustomReportVO matchedReportVO = getMatchedVO(finalList,entry.getKey());
					if(matchedReportVO == null){
						matchedReportVO = new CustomReportVO();
						matchedReportVO.setReportId(entry.getKey());
						matchedReportVO.setLocationsList(entry.getValue());
						finalList.add(matchedReportVO);
					}else{
						matchedReportVO.setLocationsList(entry.getValue());
					}
				}
			}
			
			if(imagesMap != null && imagesMap.size() > 0){
				for (Entry<Long, List<CustomReportVO>> entry : imagesMap.entrySet()) {
					CustomReportVO matchedReportVO = getMatchedVO(finalList,entry.getKey());
					if(matchedReportVO == null){
						matchedReportVO = new CustomReportVO();
						matchedReportVO.setReportId(entry.getKey());
						matchedReportVO.setImagesList(entry.getValue());
						finalList.add(matchedReportVO);
					}else{
						matchedReportVO.setImagesList(entry.getValue());
					}
				}
			}
			
			if(filesMap != null && filesMap.size() > 0){
				for (Entry<Long, List<CustomReportVO>> entry : filesMap.entrySet()) {
					CustomReportVO matchedReportVO = getMatchedVO(finalList,entry.getKey());
					if(matchedReportVO == null){
						matchedReportVO = new CustomReportVO();
						matchedReportVO.setReportId(entry.getKey());
						matchedReportVO.setFileList(entry.getValue());
						finalList.add(matchedReportVO);
					}else{
						matchedReportVO.setFileList(entry.getValue());
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getCustomReportProgram() method, Exception - ",e);
		}
		return finalList;
	} 
	
	public CustomReportVO getMatchedVO(List<CustomReportVO> list,Long programId){
		if(list != null && list.size() > 0){
			for (CustomReportVO customReportVO : list) {
				if(customReportVO.getReportId().equals(programId))
					return customReportVO;
			}
		}
		return null;
	}
	
	public CustomReportVO getReportFullDetails(Long reportId){
		CustomReportVO vo = new CustomReportVO();
		try {
			//0-tdpCadreId,1-membershipnum,2-firstname,3-image,4-voterIDCardNo,5-mobileNo
			List<Object[]> observersObjList = customReportObserverDAO.getObserversForAReport(reportId);
			
			if(observersObjList != null && observersObjList.size() > 0){
				for (Object[] objects : observersObjList) {
					CustomReportVO observerVO = new CustomReportVO();
					observerVO.setId((Long)objects[0]);
					observerVO.setMembershipNo(objects[1].toString());
					observerVO.setName(objects[2].toString());
					observerVO.setPath("/cadreImages/"+objects[3].toString());
					observerVO.setVoterNum(objects[4] != null ? objects[4].toString() : "");
					observerVO.setMobileNum(objects[5].toString());
					vo.getObserversList().add(observerVO);
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getReportFullDetails(-) method, Exception - ",e);
		}
		return vo;
	}

}