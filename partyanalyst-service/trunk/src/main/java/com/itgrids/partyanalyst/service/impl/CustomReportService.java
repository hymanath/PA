package com.itgrids.partyanalyst.service.impl;

//import static com.itgrids.partyanalyst.service.impl.AlertService.LOG;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICustomReportProgramDAO;
import com.itgrids.partyanalyst.dao.hibernate.CustomReportDAO;
import com.itgrids.partyanalyst.dao.hibernate.CustomReportFileDAO;
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
	 private CustomReportFileDAO customReportFileDAO;
	
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ICustomReportProgramDAO customReportProgramDAO;

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

	public CustomReportFileDAO getCustomReportFileDAO() {
		return customReportFileDAO;
	}

	public void setCustomReportFileDAO(CustomReportFileDAO customReportFileDAO) {
		this.customReportFileDAO = customReportFileDAO;
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
	
	 
	

}