package com.itgrids.partyanalyst.service.impl;

//import static com.itgrids.partyanalyst.service.impl.AlertService.LOG;

import java.io.File;
import java.math.BigDecimal;
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

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICustomReportFileDAO;
import com.itgrids.partyanalyst.dao.ICustomReportImageDAO;
import com.itgrids.partyanalyst.dao.ICustomReportLocationDAO;
import com.itgrids.partyanalyst.dao.ICustomReportObserverDAO;
import com.itgrids.partyanalyst.dao.ICustomReportProgramDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.hibernate.CustomReportDAO;
import com.itgrids.partyanalyst.dto.CustomReportVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.CustomReport;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ICustomReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
    private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private IStateDAO stateDAO;
	private DateUtilService dateUtilService;
	
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
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

	
	public CustomReportVO getTotalExpectedReports(Long customReportProgramId) {
		CustomReportVO finalVo = new CustomReportVO();
		List<Object[]> reportDetails=null;
		
		try{
			reportDetails = customReportDAO.getTotalExpectedReports(customReportProgramId);
			if(reportDetails !=null && reportDetails.size() >0){
				for(Object[] objects:reportDetails)
				{
					CustomReportVO customReportVO=new CustomReportVO();
					customReportVO.setName(objects[0] !=null ? objects[0].toString():"");
					customReportVO.setCount(objects[1]!=null && (Long)objects[1]> 0l ?(Long)objects[1]:0l);
					finalVo.getLocationsList().add(customReportVO);					
					
				}
			}
			if(finalVo != null){
				Long totalCount = 0l;
				for (CustomReportVO vo : finalVo.getLocationsList()) {
					totalCount = totalCount+vo.getCount();
					finalVo.setTotalCount(totalCount);
				}
			}
			
		}catch(Exception e){
				 System.out.println("EXCEPTION RAISED IN  getTotalExpertedReports.");
				 e.printStackTrace();
		}
		return finalVo;
	}
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
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

	
	public ResultStatus saveCustomReportUploadFile(final Map<File, String> mapfiles, final Long userId,final String description,final Long reportId,final Long programId) {
		final ResultStatus resultStatus = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
		String folderName = folderCreation();
		CustomReportFile customReportFile = null;
		CustomReport customReport = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		
		 StringBuilder pathBuilder = new StringBuilder();
		 StringBuilder str ;
		 
		 CustomReport cusReport = customReportDAO.getmodelForCustomreportId(reportId);
		 cusReport.setDescription(description);
		 cusReport.setCustomReportProgramId(programId);
		 cusReport.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		 cusReport.setUpdatedBy(userId);
		 cusReport.setIsDeleted("N");
		 
		 if(cusReport.getIsSubmitted().equals("N")){
			 if(mapfiles != null && mapfiles.size() > 0)
				 cusReport.setIsSubmitted("Y");
			 else
				 cusReport.setIsSubmitted("N");
		 }
		 
		 cusReport = customReportDAO.save(cusReport);
			
		 if(mapfiles != null && mapfiles.size() > 0){
			 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
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
					customReportFile.setFileName(pathBuilder.toString());
					customReportFile.setPath("/nominated_post_documents/"+pathBuilder.toString());
					customReportFile.setIsDeleted("N");
					customReportFile.setCustomReportId(reportId);
					customReportFile = customReportFileDAO.save(customReportFile);
					
			 }
		 }
		
		 resultStatus.setResultCode(0);
		 resultStatus.setResultState(customReportFile.getCustomReportFileId());
		 resultStatus.setMessage("success");
				}
			});
		}catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setMessage("failure");
			LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
		}
		return resultStatus;
	}
	public List<CustomReportVO> getCustomReportPrograms(String startDateStr,String endDateStr){
		List<CustomReportVO> finalList = new ArrayList<CustomReportVO>(0);
		
		try {
			  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      Date fromDate = null,toDate = null;
		      
		      if(startDateStr != null && endDateStr != null){
		        fromDate = sdf.parse(startDateStr.trim());
		        toDate = sdf.parse(endDateStr.trim());
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
			//Map<Long,List<CustomReportVO>> imagesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> filesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			
			List<Object[]> reportsObj = customReportDAO.getReportsOfProgram(programId,"");
			if(reportsObj != null && reportsObj.size() > 0){
				for (Object[] objects : reportsObj) {
					CustomReportVO voIn = new CustomReportVO();
					voIn.setReportId((Long)objects[0]);
					voIn.setName(objects[1] != null? objects[1].toString():"");
					voIn.setLocationName(objects[2].toString());
					finalList.add(voIn);
				}
			}
			
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
						if((Long) objects[1] == 2l){
							State state = stateDAO.get((Long) objects[3]);
							 vo.setLocationName(state.getStateName());
						}else if((Long) objects[1] == 3l){
						  District dist = districtDAO.get((Long) objects[3]);
						    vo.setLocationName(dist.getDistrictName());
						}else if((Long) objects[1] == 4l){
							Constituency cons = constituencyDAO.get((Long) objects[3]);
							vo.setLocationName(cons.getName()+" Constituency ");
						}else if((Long) objects[1] == 5l){
							Tehsil tehsil = tehsilDAO.get((Long) objects[3]);
							vo.setLocationName(tehsil.getTehsilName()+" Mandal ");
						}else if((Long) objects[1] == 6l){
							Panchayat panc = panchayatDAO.get((Long) objects[3]);
							 vo.setLocationName(panc.getPanchayatName()+" Panchayat ");
						}else if((Long) objects[1] == 7l){
							LocalElectionBody leb = localElectionBodyDAO.get((Long) objects[3]);
							vo.setLocationName(leb.getName()+" Municipality ");
						}else if((Long) objects[1] == 8l){
							Constituency ward = constituencyDAO.get((Long) objects[3]);
							 vo.setLocationName(ward.getName()+" Ward ");
						}else if((Long) objects[1] == 9l){
							Booth booth = boothDAO.get((Long) objects[3]);
							  vo.setLocationName(booth.getPartName()+ " Booth ");
						}else if((Long) objects[1] == 9l){
							Constituency parliamentCons = constituencyDAO.get((Long) objects[3]);
							  vo.setLocationName(parliamentCons.getName());
						}
						vo.setLocationValue((Long)objects[3]);
						newLocationVoList.add(vo);
						locationsMap.put((Long)objects[0], newLocationVoList);						
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setScope(objects[2].toString());
						if((Long) objects[1] == 2l){
							State state = stateDAO.get((Long) objects[3]);
							 vo.setLocationName(state.getStateName());
						}else if((Long) objects[1] == 3l){
						  District dist = districtDAO.get((Long) objects[3]);
						    vo.setLocationName(dist.getDistrictName());
						}else if((Long) objects[1] == 4l){
							Constituency cons = constituencyDAO.get((Long) objects[3]);
							vo.setLocationName(cons.getName()+" Constituency ");
						}else if((Long) objects[1] == 5l){
							Tehsil tehsil = tehsilDAO.get((Long) objects[3]);
							vo.setLocationName(tehsil.getTehsilName()+" Mandal ");
						}else if((Long) objects[1] == 6l){
							Panchayat panc = panchayatDAO.get((Long) objects[3]);
							 vo.setLocationName(panc.getPanchayatName()+" Panchayat ");
						}else if((Long) objects[1] == 7l){
							LocalElectionBody leb = localElectionBodyDAO.get((Long) objects[3]);
							vo.setLocationName(leb.getName()+" Municipality ");
						}else if((Long) objects[1] == 8l){
							Constituency ward = constituencyDAO.get((Long) objects[3]);
							 vo.setLocationName(ward.getName()+" Ward ");
						}else if((Long) objects[1] == 9l){
							Booth booth = boothDAO.get((Long) objects[3]);
							  vo.setLocationName(booth.getPartName()+ " Booth ");
						}else if((Long) objects[1] == 9l){
							Constituency parliamentCons = constituencyDAO.get((Long) objects[3]);
							  vo.setLocationName(parliamentCons.getName());
						}
						vo.setLocationValue((Long)objects[3]);
						locationsMap.get((Long)objects[0]).add(vo);
					}
				}				
			}
			//get image details for program
			//0-customReportId,1-imageName,2-path,3-customReportimageId
			/*List<Object[]> imagesObjList = customReportImageDAO.getImageDetails(programId);
			if (imagesObjList != null && imagesObjList.size() > 0) {
				for (Object[] objects : imagesObjList) {
					if (imagesMap.get((Long)objects[0]) == null){
						List<CustomReportVO> newImageVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2] != null ? "mytdp.com/images/cadre_images/"+objects[2].toString():"");
						newImageVoList.add(vo);
						imagesMap.put((Long)objects[0], newImageVoList);
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setName(objects[1].toString());
						vo.setPath(objects[2] != null ? "mytdp.com/images/cadre_images/"+objects[2].toString():"");
						imagesMap.get((Long)objects[0]).add(vo);
					}
				}				
			}*/
			
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
					CustomReportVO matchedReportVO = getMatchedVO(finalList,entry.getKey());
					if(matchedReportVO == null){
						matchedReportVO = new CustomReportVO();
						matchedReportVO.setReportId(entry.getKey());
						matchedReportVO.setObserversList(entry.getValue());
						finalList.add(matchedReportVO);
					}else{
						matchedReportVO.setObserversList(entry.getValue());
					}
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
			
			/*if(imagesMap != null && imagesMap.size() > 0){
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
			}*/
			
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
			//observer
			//0-tdpCadreId,1-membershipnum,2-firstname,3-image,4-voterIDCardNo,5-mobileNo
			List<Object[]> observersObjList = customReportObserverDAO.getObserversForAReport(reportId);
			
			if(observersObjList != null && observersObjList.size() > 0){
				for (Object[] objects : observersObjList) {
					CustomReportVO observerVO = new CustomReportVO();
					observerVO.setId((Long)objects[0]);
					observerVO.setMembershipNo(objects[1].toString());
					observerVO.setName(objects[2].toString());
					observerVO.setPath(objects[3] != null && !objects[3].toString().trim().isEmpty()? objects[3].toString():"");
					observerVO.setVoterNum(objects[4] != null ? objects[4].toString() : "");
					observerVO.setMobileNum(objects[5].toString());
					vo.getObserversList().add(observerVO);
				}
			}
			String description = customReportDAO.getDescriptionForReportId(reportId);
			vo.setName(description);
			/*
			  //image 
		     //0-customReportimageId,1-imageName,2-path 
			List<Object[]> imageObjList = customReportImageDAO.getImageForAReport(reportId);
			if(imageObjList != null && imageObjList.size() > 0){
				for (Object[] objects : imageObjList) {
					CustomReportVO imageVO = new CustomReportVO();
					imageVO.setId((Long)objects[0]);
					imageVO.setName(objects[1].toString());
					imageVO.setPath("/cadreImages/"+objects[2].toString());
					vo.getImagesList().add(imageVO);
				}
				
			}*/
			//File
			//0-customReportFileId,1-fileName,2-path
			List<Object[]> fileObjList = customReportFileDAO.getFileForAReport(reportId);
			if(fileObjList != null && fileObjList.size() > 0){
				for (Object[] objects : fileObjList) {
					CustomReportVO fileVO = new CustomReportVO();
					fileVO.setId((Long)objects[0]);
					fileVO.setName(objects[1].toString());
					fileVO.setPath("https://mytdp.com/"+objects[2].toString());
					vo.getFileList().add(fileVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getReportFullDetails(-) method, Exception - ",e);
		}
		return vo;
	}
	public List<CustomReportVO> getCustomReportProgramForreportId(Long programId,String type){
		List<CustomReportVO> finalList = new ArrayList<CustomReportVO>(0);
		try {
			Map<Long,List<CustomReportVO>> observersMap = new LinkedHashMap<Long, List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> locationsMap =new LinkedHashMap<Long, List<CustomReportVO>>(0);
			//Map<Long,List<CustomReportVO>> imagesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			Map<Long,List<CustomReportVO>> filesMap =new LinkedHashMap<Long,List<CustomReportVO>>(0);
			
			List<Object[]> reportsObj = customReportDAO.getReportsOfProgram(programId,type);
			if(reportsObj != null && reportsObj.size() > 0){
				for (Object[] objects : reportsObj) {
					CustomReportVO voIn = new CustomReportVO();
					voIn.setReportId((Long)objects[0]);
					voIn.setName(objects[1] != null? objects[1].toString():"");
					voIn.setLocationName(objects[2].toString());
					finalList.add(voIn);
				}
			}
			
			//get observer details for program
			//0-customReportId,1-tdpCadreId,2-firstname
			List<Object[]> obserersObjList = customReportObserverDAO.getObserverDetailsForReportId(programId,type);
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
			List<Object[]> locationsObjList = customReportLocationDAO.getLocationDetailsForReportId(programId,type);
			if (locationsObjList != null && locationsObjList.size() > 0) {
				for (Object[] objects : locationsObjList) {
					if (locationsMap.get((Long)objects[0]) == null){
						List<CustomReportVO> newLocationVoList = new ArrayList<CustomReportVO>(0);
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setScope(objects[2].toString());
						if((Long) objects[1] == 2l){
							State state = stateDAO.get((Long) objects[3]);
							 vo.setLocationName(state.getStateName());
						}else if((Long) objects[1] == 3l){
						  District dist = districtDAO.get((Long) objects[3]);
						    vo.setLocationName(dist.getDistrictName());
						}else if((Long) objects[1] == 4l){
							Constituency cons = constituencyDAO.get((Long) objects[3]);
							vo.setLocationName(cons.getName()+" Constituency ");
						}else if((Long) objects[1] == 5l){
							Tehsil tehsil = tehsilDAO.get((Long) objects[3]);
							vo.setLocationName(tehsil.getTehsilName()+" Mandal ");
						}else if((Long) objects[1] == 6l){
							Panchayat panc = panchayatDAO.get((Long) objects[3]);
							 vo.setLocationName(panc.getPanchayatName()+" Panchayat ");
						}else if((Long) objects[1] == 7l){
							LocalElectionBody leb = localElectionBodyDAO.get((Long) objects[3]);
							vo.setLocationName(leb.getName()+" Municipality ");
						}else if((Long) objects[1] == 8l){
							Constituency ward = constituencyDAO.get((Long) objects[3]);
							 vo.setLocationName(ward.getName()+" Ward ");
						}else if((Long) objects[1] == 9l){
							Booth booth = boothDAO.get((Long) objects[3]);
							  vo.setLocationName(booth.getPartName()+ " Booth ");
						}else if((Long) objects[1] == 9l){
							Constituency parliamentCons = constituencyDAO.get((Long) objects[3]);
							  vo.setLocationName(parliamentCons.getName());
						}
						vo.setLocationValue((Long)objects[3]);
						newLocationVoList.add(vo);
						locationsMap.put((Long)objects[0], newLocationVoList);						
					}else{
						CustomReportVO vo = new CustomReportVO();
						vo.setReportId((Long)objects[0]);
						vo.setId((Long)objects[1]);
						vo.setScope(objects[2].toString());
						if((Long) objects[1] == 2l){
							State state = stateDAO.get((Long) objects[3]);
							 vo.setLocationName(state.getStateName());
						}else if((Long) objects[1] == 3l){
						  District dist = districtDAO.get((Long) objects[3]);
						    vo.setLocationName(dist.getDistrictName());
						}else if((Long) objects[1] == 4l){
							Constituency cons = constituencyDAO.get((Long) objects[3]);
							vo.setLocationName(cons.getName()+" Constituency ");
						}else if((Long) objects[1] == 5l){
							Tehsil tehsil = tehsilDAO.get((Long) objects[3]);
							vo.setLocationName(tehsil.getTehsilName()+" Mandal ");
						}else if((Long) objects[1] == 6l){
							Panchayat panc = panchayatDAO.get((Long) objects[3]);
							 vo.setLocationName(panc.getPanchayatName()+" Panchayat ");
						}else if((Long) objects[1] == 7l){
							LocalElectionBody leb = localElectionBodyDAO.get((Long) objects[3]);
							vo.setLocationName(leb.getName()+" Municipality ");
						}else if((Long) objects[1] == 8l){
							Constituency ward = constituencyDAO.get((Long) objects[3]);
							 vo.setLocationName(ward.getName()+" Ward ");
						}else if((Long) objects[1] == 9l){
							Booth booth = boothDAO.get((Long) objects[3]);
							  vo.setLocationName(booth.getPartName()+ " Booth ");
						}else if((Long) objects[1] == 9l){
							Constituency parliamentCons = constituencyDAO.get((Long) objects[3]);
							  vo.setLocationName(parliamentCons.getName());
						}
						vo.setLocationValue((Long)objects[3]);
						locationsMap.get((Long)objects[0]).add(vo);
					}
				}				
			}
			/*//0-customReportId,1-imageName,2-path,3-customReportimageId
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
			*/
			//get file details for program
			//0-customReportId,1.fileName,2.path,3-customReportFileId
			List<Object[]> fileObjList = customReportFileDAO.getFileDetailsForReportId(programId, type);
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
					CustomReportVO matchedReportVO = getMatchedVO(finalList,entry.getKey());
					if(matchedReportVO == null){
						matchedReportVO = new CustomReportVO();
						matchedReportVO.setReportId(entry.getKey());
						matchedReportVO.setObserversList(entry.getValue());
						finalList.add(matchedReportVO);
					}else{
						matchedReportVO.setObserversList(entry.getValue());
					}
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
			
			/*if(imagesMap != null && imagesMap.size() > 0){
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
			}*/
			
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
			LOG.error("Exception Occured in getCustomReportProgramForreportId() method, Exception - ",e);
		}
		return finalList;
	} 
	public ResultStatus deleteCustomReportFileDetails(Long reportId,Long fileId){
		ResultStatus  resultStatus = new ResultStatus();
		try {
			 Integer count = customReportFileDAO.updateCustomReportFileDetails(fileId);
			 
			 if(count != null && count > 0){
				 List<Long> idsList = customReportFileDAO.getSubmittedCustomReports(reportId);
				 if(idsList == null || idsList.size() == 0){
					 customReportDAO.updateCustomReportStatus(reportId);
				 }
			 }
			 
			 resultStatus.setResultCode(0);
			 resultStatus.setMessage("Success");
		} catch (Exception e) {
			 resultStatus.setResultCode(0);
			 resultStatus.setMessage("Failed");
			LOG.error("Exception Occured in deleteCustomReportFileDetails() method, Exception - ",e);
		}
		return resultStatus;
	}
}