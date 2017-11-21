package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreIVREnquiryDAO;
import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.dao.ICadreRegistrationInfoDAO;
import com.itgrids.partyanalyst.dao.ICallCenterFeedbackDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IIvrCampaignDAO;
import com.itgrids.partyanalyst.dao.IIvrCampaignOptionsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalNameConstantDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterCommentDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterFeedbackDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreImagesValidDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreSmsStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerConstituencyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreVolunteerDateDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreIVRResponseVO;
import com.itgrids.partyanalyst.dto.CadreIVRVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ImageCheckVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.dto.TdpCadreSmsStatusVO;
import com.itgrids.partyanalyst.dto.TdpCadreVolunteerVO;
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;
import com.itgrids.partyanalyst.model.CadreIVREnquiry;
import com.itgrids.partyanalyst.model.LocalNameConstant;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterComment;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterFeedback;
import com.itgrids.partyanalyst.model.TdpCadreImagesValid;
import com.itgrids.partyanalyst.model.TdpCadreSmsStatus;
import com.itgrids.partyanalyst.model.TdpCadreVolunteer;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerConstituency;
import com.itgrids.partyanalyst.model.TdpCadreVolunteerDate;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.ZebraPrintDetails;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreReportService implements ITdpCadreReportService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private IBoothDAO boothDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ICadreDashBoardService cadreDashBoardService;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private ILocalNameConstantDAO localNameConstantDAO;
	private IUserAddressDAO userAddressDAO;
	private ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private ITdpCadreCallCenterFeedbackDAO tdpCadreCallCenterFeedbackDAO;
	private TransactionTemplate transactionTemplate = null;
	private ITdpCadreCallCenterCommentDAO tdpCadreCallCenterCommentDAO;
	private ICallCenterFeedbackDAO callCenterFeedbackDAO;
	private ITdpCadreSmsStatusDAO tdpCadreSmsStatusDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ITdpCadreVolunteerDAO tdpCadreVolunteerDAO;
	private ITdpCadreVolunteerConstituencyDAO tdpCadreVolunteerConstituencyDAO;
	private ITdpCadreVolunteerDateDAO tdpCadreVolunteerDateDAO;
	private ICadreIvrResponseDAO cadreIvrResponseDAO;
	private ICadreRegistrationInfoDAO cadreRegistrationInfoDAO ;
	private ICadreIVREnquiryDAO cadreIVREnquiryDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ITdpCadreImagesValidDAO tdpCadreImagesValidDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private IIvrCampaignDAO ivrCampaignDAO;
	private IIvrCampaignOptionsDAO ivrCampaignOptionsDAO;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	
	
	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	public IIvrCampaignOptionsDAO getIvrCampaignOptionsDAO() {
		return ivrCampaignOptionsDAO;
	}
	public void setIvrCampaignOptionsDAO(
			IIvrCampaignOptionsDAO ivrCampaignOptionsDAO) {
		this.ivrCampaignOptionsDAO = ivrCampaignOptionsDAO;
	}
	public IIvrCampaignDAO getIvrCampaignDAO() {
		return ivrCampaignDAO;
	}
	public void setIvrCampaignDAO(IIvrCampaignDAO ivrCampaignDAO) {
		this.ivrCampaignDAO = ivrCampaignDAO;
	}
	public ITdpCadreInfoDAO getTdpCadreInfoDAO() {
		return tdpCadreInfoDAO;
	}
	public void setTdpCadreInfoDAO(ITdpCadreInfoDAO tdpCadreInfoDAO) {
		this.tdpCadreInfoDAO = tdpCadreInfoDAO;
	}
	public void setTdpCadreImagesValidDAO(
			ITdpCadreImagesValidDAO tdpCadreImagesValidDAO) {
		this.tdpCadreImagesValidDAO = tdpCadreImagesValidDAO;
	}
	public ICadreRegistrationInfoDAO getCadreRegistrationInfoDAO() {
		return cadreRegistrationInfoDAO;
	}
	public void setCadreRegistrationInfoDAO(
			ICadreRegistrationInfoDAO cadreRegistrationInfoDAO) {
		this.cadreRegistrationInfoDAO = cadreRegistrationInfoDAO;
	}
	public ICadreIvrResponseDAO getCadreIvrResponseDAO() {
		return cadreIvrResponseDAO;
	}
	public void setCadreIvrResponseDAO(ICadreIvrResponseDAO cadreIvrResponseDAO) {
		this.cadreIvrResponseDAO = cadreIvrResponseDAO;
	}
	public ITdpCadreVolunteerConstituencyDAO getTdpCadreVolunteerConstituencyDAO() {
		return tdpCadreVolunteerConstituencyDAO;
	}
	public void setTdpCadreVolunteerConstituencyDAO(
			ITdpCadreVolunteerConstituencyDAO tdpCadreVolunteerConstituencyDAO) {
		this.tdpCadreVolunteerConstituencyDAO = tdpCadreVolunteerConstituencyDAO;
	}
	public ITdpCadreVolunteerDateDAO getTdpCadreVolunteerDateDAO() {
		return tdpCadreVolunteerDateDAO;
	}
	public void setTdpCadreVolunteerDateDAO(
			ITdpCadreVolunteerDateDAO tdpCadreVolunteerDateDAO) {
		this.tdpCadreVolunteerDateDAO = tdpCadreVolunteerDateDAO;
	}
	public ITdpCadreVolunteerDAO getTdpCadreVolunteerDAO() {
		return tdpCadreVolunteerDAO;
	}
	public void setTdpCadreVolunteerDAO(ITdpCadreVolunteerDAO tdpCadreVolunteerDAO) {
		this.tdpCadreVolunteerDAO = tdpCadreVolunteerDAO;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public ITdpCadreSmsStatusDAO getTdpCadreSmsStatusDAO() {
		return tdpCadreSmsStatusDAO;
	}
	public void setTdpCadreSmsStatusDAO(ITdpCadreSmsStatusDAO tdpCadreSmsStatusDAO) {
		this.tdpCadreSmsStatusDAO = tdpCadreSmsStatusDAO;
	}
	public void setLocalNameConstantDAO(ILocalNameConstantDAO localNameConstantDAO) {
		this.localNameConstantDAO = localNameConstantDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}
	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}


	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}


	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}


	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	
	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}
	
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	
	public ITdpCadreTeluguNamesDAO getTdpCadreTeluguNamesDAO() {
		return tdpCadreTeluguNamesDAO;
	}
	public void setTdpCadreTeluguNamesDAO(
			ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO) {
		this.tdpCadreTeluguNamesDAO = tdpCadreTeluguNamesDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IZebraPrintDetailsDAO getZebraPrintDetailsDAO() {
		return zebraPrintDetailsDAO;
	}
	
	public ITdpCadreCallCenterFeedbackDAO getTdpCadreCallCenterFeedbackDAO() {
		return tdpCadreCallCenterFeedbackDAO;
	}
	public void setTdpCadreCallCenterFeedbackDAO(
			ITdpCadreCallCenterFeedbackDAO tdpCadreCallCenterFeedbackDAO) {
		this.tdpCadreCallCenterFeedbackDAO = tdpCadreCallCenterFeedbackDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ICallCenterFeedbackDAO getCallCenterFeedbackDAO() {
		return callCenterFeedbackDAO;
	}
	public void setCallCenterFeedbackDAO(
			ICallCenterFeedbackDAO callCenterFeedbackDAO) {
		this.callCenterFeedbackDAO = callCenterFeedbackDAO;
	}

	public ITdpCadreCallCenterCommentDAO getTdpCadreCallCenterCommentDAO() {
		return tdpCadreCallCenterCommentDAO;
	}
	public void setTdpCadreCallCenterCommentDAO(
			ITdpCadreCallCenterCommentDAO tdpCadreCallCenterCommentDAO) {
		this.tdpCadreCallCenterCommentDAO = tdpCadreCallCenterCommentDAO;
	}
	
	public ICadreIVREnquiryDAO getCadreIVREnquiryDAO() {
		return cadreIVREnquiryDAO;
	}
	public void setCadreIVREnquiryDAO(ICadreIVREnquiryDAO cadreIVREnquiryDAO) {
		this.cadreIVREnquiryDAO = cadreIVREnquiryDAO;
	}
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public TdpCadreLocationWiseReportVO generateExcelReportForTdpCadre(List<Long> constituencyIdsList)
	{
		TdpCadreLocationWiseReportVO returnVO = new TdpCadreLocationWiseReportVO();
		ZipOutputStream zos = null;
		FileOutputStream fos = null;
		try {
			
			int randomNumber = -100;
			while(randomNumber < 0)
			{
				randomNumber = new Random().nextInt()*10000000;
			}
			
			//String path = "D:/apache-tomcat-6.0.37/webapps/PartyAnalyst/Reports";
			String path = IConstants.STATIC_CONTENT_FOLDER_URL+"VMR"+"/";
			FileOutputStream fileOut = null;
			String filename = "";
			

			fos  = new FileOutputStream(path+"/"+randomNumber+"_Reports" +".zip");
			zos = new ZipOutputStream(fos);
			
			
			if(constituencyIdsList != null && constituencyIdsList.size()>0)
			{
				for (Long constituencyId : constituencyIdsList)
				{
					List<Long> constituencyIds = new ArrayList<Long>();
					constituencyIds.add(constituencyId);
					
					List<TdpCadreLocationWiseReportVO> locationWiseReportsList = getLocationWiseReportDetailsForExcel(constituencyIds);
					List<TdpCadreLocationWiseReportVO> ageWiseReportList  = ageWiseDetailsForConstituencies(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					List<TdpCadreLocationWiseReportVO> genderWiseReportList  = genderWiseDetailsForConstituencies(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID); 
					
					List<CadreRegisterInfo> casteGroupList = cadreDashBoardService.getCastGroupWiseCadreCount( constituencyId, "constituency");
					
					if(locationWiseReportsList != null && locationWiseReportsList.size()>0)
					{
						TdpCadreLocationWiseReportVO vo = locationWiseReportsList.get(0);
						
						returnVO.setId(vo.getId());
						returnVO.setName(vo.getName());
						returnVO.setTotalVoters(vo.getTotalVoters());
						returnVO.setRegisteredCadre(vo.getRegisteredCadre());
						returnVO.setTargetedCadre(vo.getTargetedCadre());
						returnVO.setPercentage(vo.getPercentage());
						
					}
					returnVO.setTdpCadreLocationWiseReportVOList(locationWiseReportsList);
					returnVO.setPanchayatWiseList(genderWiseReportList);
					returnVO.setTehsilWiseList(ageWiseReportList);
					returnVO.setCasteGroupList(casteGroupList);
					
					String constituencyName = locationWiseReportsList.get(0).getConstituencyNo().toString();
					
					filename= path+"/"+constituencyName+ ".xls";
					String FILE = filename;
					File file  = new File(FILE);
					file.createNewFile();
					fileOut =  new FileOutputStream(FILE);
					HSSFWorkbook workbook=new HSSFWorkbook();
					HSSFSheet constituencyWiseSheet = workbook.createSheet(IConstants.CONSTITUENCY);
					genereteOriginalExcelReport(returnVO,workbook,constituencyWiseSheet);
					
					 workbook.write(fileOut);
					    
					 addToZipFile(filename, zos);
				}
			}			
		} catch (Exception e) {
			LOG.error(" exception occured in generateExcelReportForTdpCadre () at TdpCadreReportService ",e);
		}finally{
			try {
				if(zos != null)
				{
					zos.close();
				}
				if(fos != null)
				{
					fos.close();	
				}
				 
			} catch (Exception e2) {
			}
		}
		
		return returnVO;
	}
	 public  void addToZipFile(String fileName, ZipOutputStream zos)  {

			//System.out.println("Writing '" + fileName + "' to zip file");
			try {
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				ZipEntry zipEntry = new ZipEntry(fileName);
				zos.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) {
					zos.write(bytes, 0, length);
				}

				zos.closeEntry();
				fis.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
	public List<TdpCadreLocationWiseReportVO> getLocationWiseReportDetailsForExcel(List<Long> constituencyIds){
		List<TdpCadreLocationWiseReportVO> resultList = new ArrayList<TdpCadreLocationWiseReportVO>();
		try{
		
			List<Object[]> constiCadreCount = null;			
			List<Object[]> constiVoterCount = null;
			List<Object[]> boothCadreCount = null;			
			List<Object[]> boothVoterCount = null;
			List<Object[]> tehsilCadreCount = null;			
			List<Object[]> tehsilVoterCount = null;
			List<Object[]> localCadreCount = null;			
			List<Object[]> localVoterCount = null;
			List<Object[]> constiCadreCount2012 = null;
			
			List<Long> tehsilIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			//List<Long> panchayatIds = new ArrayList<Long>();
			List<Long> boothIds = new ArrayList<Long>();
			Map<Long,String> boothLocationMap = new HashMap<Long, String>();			
				
				constiCadreCount = tdpCadreDAO.getLocationWiseCount(constituencyIds,IConstants.CONSTITUENCY);
				constiCadreCount2012 = tdpCadreDAO.getRegisteredCadreCountIn2012(constituencyIds);
				constiVoterCount = voterInfoDAO.getVotersCountInConstituencies(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				List<Object[]> constiNos = delimitationConstituencyDAO.getConstituencyNo(constituencyIds,IConstants.DELIMITATION_YEAR);
				
				if(constiVoterCount != null && constiVoterCount.size() > 0){
					for(Object[] params : constiVoterCount){					
						TdpCadreLocationWiseReportVO vo = new TdpCadreLocationWiseReportVO();
						vo.setId((Long)params[0]);						
						vo.setName(params[1] != null ? params[1].toString() : "");					
						vo.setTotalVoters((Long)params[2]);
						vo.setTargetedCadre((vo.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);							
						resultList.add(vo);
					}				
				}
				
				if(constiCadreCount != null && constiCadreCount.size() > 0){
					for(Object[] params : constiCadreCount){					
						TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[1]);
						  if(vo != null)
						  {
							  vo.setRegisteredCadre(params[0] != null ? (Long)params[0] : 0);	//2837						
							  String percentage ="";
							  percentage = (new BigDecimal(vo.getRegisteredCadre()*(100.0)/vo.getTotalVoters().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							  vo.setPercentage(percentage);							  
						  }
					}				
				}
				
				if(constiCadreCount2012 != null && constiCadreCount2012.size() > 0){
					for(Object[] params : constiCadreCount2012){					
						TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[1]);
						  if(vo != null){
							  vo.setCadresCount(params[0] != null ? (Long)params[0] : 0);							 					  
						  }
					}				
				}
				
				if(constiNos != null && constiNos.size() > 0){
					for(Object[] params : constiNos){					
						TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[0]);
						  if(vo != null){
							  vo.setConstituencyNo(params[1] != null ? (Long)params[1] : 0);							 					  
						  }
					}				
				}

				localbodyIds = boothDAO.getLocalbodiesByConstituencyIds(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);		 
				tehsilIds = 	boothDAO.getTehsilsByConstituencyIds(constituencyIds, IConstants.VOTER_DATA_PUBLICATION_ID);					
	    	  	
				if(tehsilIds != null && tehsilIds.size() > 0){									   
					tehsilCadreCount = tdpCadreDAO.getLocationWiseCount(tehsilIds,IConstants.TEHSIL);
					tehsilVoterCount = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					setData(tehsilVoterCount,tehsilCadreCount,IConstants.TEHSIL,resultList,boothLocationMap);
				    
				 }
				if(localbodyIds != null && localbodyIds.size() > 0){				
					localCadreCount = tdpCadreDAO.getLocationWiseCount(localbodyIds,IConstants.LOCAL_ELECTION_BODY);
					localVoterCount = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					setData(localVoterCount,localCadreCount,IConstants.LOCAL_ELECTION_BODY,resultList,boothLocationMap);
				}
				
				boothIds = boothDAO.getBoothIdsByConstituencyIdsAndPublicationId(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				
								
				List<Object[]> list1 = boothDAO.getBooths(boothIds);
				List<Object[]> list2 = boothDAO.getBoothsInAMuncipality(boothIds);
				for(Object[] params : list1){
						boothLocationMap.put((Long)params[0], params[1] != null ?  params[1].toString() : "");
				}
				for(Object[] params : list2){
						boothLocationMap.put((Long)params[0], params[1] != null ?  params[1].toString() : "");
				}
				
				boothCadreCount = tdpCadreDAO.getTotalRecords1(boothIds,IConstants.BOOTH);
				boothVoterCount = voterInfoDAO.getVotersCountInBoothsList(boothIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				setData(boothVoterCount,boothCadreCount,IConstants.BOOTH,resultList,boothLocationMap);
				
			/*	panchayatIds = boothDAO.getPanchayatsByConstituencyIds(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);				
				panchayatCadreCount = tdpCadreDAO.getTotalRecords1(panchayatIds,IConstants.PANCHAYAT);
				panchayatVoterCount = voterInfoDAO.getVotersCountInPanchayatList(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				setData(panchayatVoterCount,panchayatCadreCount,IConstants.PANCHAYAT,resultList);*/	
						
		}catch(Exception e){
			LOG.error("Exception rised in getLocationWiseAgeRangeAndGenderCount",e);
		}
		return resultList;
	}
	
	public TdpCadreLocationWiseReportVO getMatchedVO(List<TdpCadreLocationWiseReportVO> list,Long id)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(TdpCadreLocationWiseReportVO vo :list)
			{
				if(vo.getId().longValue() == id)
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}	
	
	
	public void setData(List<Object[]> voterList,List<Object[]> cadreList,String type,List<TdpCadreLocationWiseReportVO> resultList,Map<Long,String> boothMap)
	{
		
		if(voterList != null && voterList.size() > 0){
			for(Object[] params : voterList){	
				TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[6]);
				if(vo != null){
					List<TdpCadreLocationWiseReportVO> list = new ArrayList<TdpCadreLocationWiseReportVO>();
					if(type.equals(IConstants.BOOTH)){
						list = vo.getPanchayatWiseList();
					}
					else if(type.equals(IConstants.TEHSIL) || type.equals(IConstants.LOCAL_ELECTION_BODY)){
						list = vo.getTehsilWiseList();
					}
					TdpCadreLocationWiseReportVO vo1 = new TdpCadreLocationWiseReportVO();
					vo1.setId((Long)params[0]);
					if(type.equals(IConstants.BOOTH)){
						vo1.setName(params[1] != null ? "Booth-"+params[1].toString() : "");					
						String panchayatName = boothMap.get((Long)params[0]);
						vo1.setLocationType(panchayatName);
					}
					else{
						if(type.equals(IConstants.LOCAL_ELECTION_BODY)){
							vo1.setName(params[1] != null ? params[1].toString()+" Muncipality" : "");
						}else{
							vo1.setName(params[1] != null ? params[1].toString() : "");
						}
					}
					vo1.setTotalVoters((Long)params[2]);
					vo1.setTargetedCadre((vo1.getTotalVoters() * IConstants.TARGET_CADRE_AP) / IConstants.AP_VOTERS_2014);	
					list.add(vo1);
					
				}
			}				
		}
		
		if(cadreList != null && cadreList.size() > 0){
			for(Object[] params : cadreList){					
				TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[2]);
				  if(vo != null)
				  {
					  List<TdpCadreLocationWiseReportVO> list = new ArrayList<TdpCadreLocationWiseReportVO>();
					 
						  if(type.equals(IConstants.BOOTH)){
							  list = vo.getPanchayatWiseList();
							}
							else if(type.equals(IConstants.TEHSIL) || type.equals(IConstants.LOCAL_ELECTION_BODY)){
								list = vo.getTehsilWiseList();
							}					 						 
							  TdpCadreLocationWiseReportVO subVO = getMatchedVO(list,(Long)params[1]);
							  if(subVO != null){
								  subVO.setRegisteredCadre(params[0] != null ? (Long)params[0] : 0);
								  String percentage ="";
								  percentage = (new BigDecimal(subVO.getRegisteredCadre()*(100.0)/vo.getRegisteredCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
								  subVO.setPercentage(percentage);
							  }
						
				  }
			}				
		}

	}
	public List<TdpCadreLocationWiseReportVO> genderWiseDetailsForConstituencies(List<Long> constituencyIds,Long publicationId)
	{
		List<TdpCadreLocationWiseReportVO> resultList = null ;
	  try
	  {     List<Object[]> registeredCadreCountList=tdpCadreDAO.gettingRegisteredVotersForConstituencys(constituencyIds);
		    List<Object[]> votersList=voterAgeInfoDAO.getGenderWiseVoterDetailsByConstituency(constituencyIds,publicationId);//mc fc cname
			List<Object[]> votersList1=tdpCadreDAO.getTdpCadregenderWiseByConstituency(constituencyIds);//name,gender,count
		
			
			resultList=settingtDataGenderWise(votersList,"voterInfo");
			List<TdpCadreLocationWiseReportVO> resultList1=settingtDataGenderWise(votersList1,"tdpCadre");
			
			
			if(registeredCadreCountList!=null && registeredCadreCountList.size()>0)
			{
				for(Object[] obj:registeredCadreCountList)
				{
					
					 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList,obj[1].toString()); 
					 constituencyVO.setRegisteredCadre(obj[0]!=null?(Long)obj[0]:0l);
				}
			}
			
			//comparing two lists.
			if(resultList!=null && resultList.size()>0)
			{
				 for(TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO:resultList1)
				 {
					 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,tdpCadreLocationWiseReportVO.getName()); 
					 List<TdpCadreLocationWiseReportVO> gendersList=tdpCadreLocationWiseReportVO.getTdpCadreLocationWiseReportVOList();
					 if(gendersList!=null && gendersList.size()>0)
					 {
						 for(TdpCadreLocationWiseReportVO tdpCadreVO:gendersList){
						  TdpCadreLocationWiseReportVO genderVO=getMatchedVOByName(constituencyVO.getTehsilWiseList(),tdpCadreVO.getGender()); 
						    genderVO.setCadresCount(tdpCadreVO.getTotalVoters()); 
						  
						  Double agePerc=(genderVO.getCadresCount()*100.0)/(constituencyVO.getRegisteredCadre());
						  //Round the double value to 2 decimal values.
						   DecimalFormat df2 = new DecimalFormat("###.##");
						   genderVO.setGenderPerc( (Double.valueOf(df2.format(agePerc))));

						 }
					 }
					 
				  }
			 }
	  }catch(Exception e)
	  {
		  LOG.error(" exception occured in genderWiseDetailsForConstituencies () at TdpCadreReportService ",e);
	  }
		
	  return resultList;
	}
	public List<TdpCadreLocationWiseReportVO> settingtDataGenderWise(List<Object[]> votersList,String type )
	{
		List<TdpCadreLocationWiseReportVO> resultList=null;
		try{
		    if(type.equalsIgnoreCase("voterInfo")){
		
			
			if(votersList!=null && votersList.size()>0){//mc fc cname
				resultList=new ArrayList<TdpCadreLocationWiseReportVO>();
				for (Object[] objects : votersList){
				   TdpCadreLocationWiseReportVO constituencyVO=new TdpCadreLocationWiseReportVO();
				   constituencyVO.setName(objects[2]!=null?objects[2].toString():"");
				      List<TdpCadreLocationWiseReportVO> genderList=constituencyVO.getTehsilWiseList();
				      TdpCadreLocationWiseReportVO maleVO=new TdpCadreLocationWiseReportVO();
				      TdpCadreLocationWiseReportVO femaleVO=new TdpCadreLocationWiseReportVO();
				      TdpCadreLocationWiseReportVO othersVO=new TdpCadreLocationWiseReportVO();
				      
				      maleVO.setTotalVoters(objects[0]!=null?(Long)objects[0]:0l);
				      maleVO.setName("Male");
				      
				      femaleVO.setTotalVoters(objects[1]!=null?(Long)objects[1]:0l);
				      femaleVO.setName("Female");
				      
				      othersVO.setTotalVoters(0l);
				      othersVO.setName("Others");
				      
				      genderList.add(maleVO);
				      genderList.add(femaleVO);
				      genderList.add(othersVO);
				      
				      resultList.add(constituencyVO);
				  }
			 }
			
         }
		 else if(type.equalsIgnoreCase("tdpCadre")){//name,gender,count
			 resultList=new ArrayList<TdpCadreLocationWiseReportVO>();
			 Set<String> constituenciesSet = new TreeSet<String>();
			 Set<String>   genderssSet    = new TreeSet<String>();
			 
			 if(votersList!=null&&votersList.size()>0){
				  for(Object[] obj:votersList)
				 {   
					 constituenciesSet.add(obj[0].toString());
					
				 }
				 genderssSet.add("Male");
				 genderssSet.add("Female");
				 genderssSet.add("Others");
			 }	
             
			 if(constituenciesSet!=null && constituenciesSet.size()>0){
			 for(String constituencyName:constituenciesSet)
			 {
				 TdpCadreLocationWiseReportVO constituencyVO = new TdpCadreLocationWiseReportVO();
				 constituencyVO.setName(constituencyName);
				 if(genderssSet!=null && genderssSet.size()>0){
				 for(String gender:genderssSet)
				 {
					 TdpCadreLocationWiseReportVO genderVO = new TdpCadreLocationWiseReportVO();
					 genderVO.setGender(gender);
					 constituencyVO.getTdpCadreLocationWiseReportVOList().add(genderVO);
				 }
				 }
				 
				 resultList.add(constituencyVO);
			 }	
			 }
			 
		//iterrate and set.
			    
				 if(votersList!=null && votersList.size()>0){ //name,gender,count. 
			 
					  for(Object[] obj:votersList)
					 {
						 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,obj[0].toString());
						 
						 TdpCadreLocationWiseReportVO genderVO     =    getMatchedVOByName1(constituencyVO.getTdpCadreLocationWiseReportVOList(),obj[1]!=null?obj[1].toString():"Others");
						  genderVO.setTotalVoters(genderVO.getTotalVoters()==null?(Long)obj[2]:genderVO.getTotalVoters()+(Long)obj[2]);
						
					 }
			      }
		 
		 }
		}catch(Exception e)
		{LOG.error(" exception occured in settingtDataGenderWise () at TdpCadreReportService ",e);
			return null;
			
		}
		return resultList;
	}
	
	
	public List<TdpCadreLocationWiseReportVO> ageWiseDetailsForConstituencies(List<Long> constituencyIds,Long publicationId)
	{
		List<TdpCadreLocationWiseReportVO> resultList = null;
		try
		{
	    List<Object[]> registeredCadreCountList=tdpCadreDAO.gettingRegisteredVotersForConstituencys(constituencyIds);
		List<Object[]> votersList=voterAgeInfoDAO.getTotalVotersBasedOnAConstituency(constituencyIds,publicationId);//0-->agerangeId,1-->totalvoters,2-->cname.
		List<Object[]> votersList1=tdpCadreDAO.getTdpCadreAgeRangeByConstituency(constituencyIds);//age,dob,name
		
		
		resultList=settingtheData(votersList,"voterInfo");
		List<TdpCadreLocationWiseReportVO> resultList1=settingtheData(votersList1,"tdpCadre");
		
		//setting registeredcadre count for matched constituency.
		if(registeredCadreCountList!=null && registeredCadreCountList.size()>0)
		{
			for(Object[] obj:registeredCadreCountList)
			{
				
				 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList,obj[1].toString()); 
				 constituencyVO.setRegisteredCadre(obj[0]!=null?(Long)obj[0]:0l);
			}
		}
		
		//comparing two lists.
		if(resultList!=null && resultList.size()>0)
		{
			 for(TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO:resultList1)
			 {
				 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,tdpCadreLocationWiseReportVO.getName());
				 List<TdpCadreLocationWiseReportVO> ageRangeVoList=tdpCadreLocationWiseReportVO.getTdpCadreLocationWiseReportVOList();
				 if(ageRangeVoList!=null && ageRangeVoList.size()>0)
				 {
					 for(TdpCadreLocationWiseReportVO tdpCadreVO:ageRangeVoList){
					  TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),tdpCadreVO.getAgeRangeId()); 
					  ageRangeVO.setCadresInAge(tdpCadreVO.getTotalVoters());
					 
					  if(ageRangeVO.getCadresInAge() != null && ageRangeVO.getRegisteredCadre() != null)
					  {
						  Double agePerc=(ageRangeVO.getCadresInAge()*100.0)/(constituencyVO.getRegisteredCadre());
						  //Round the double value to 2 decimal values.
						   DecimalFormat df2 = new DecimalFormat("###.##");
						   ageRangeVO.setAgePerc(Double.valueOf(df2.format(agePerc)));
					  }


					 }
				 }
				 
			  }
		 }
	  }catch(Exception e)
	  {
		  LOG.error(" exception occured in ageWiseDetailsForConstituencies () at TdpCadreReportService ",e);
	  }
		
		return resultList;
	} 
	public List<TdpCadreLocationWiseReportVO> settingtheData(List<Object[]> votersList,String type)
	{

	    
		
		
		List<TdpCadreLocationWiseReportVO> resultList=new ArrayList<TdpCadreLocationWiseReportVO>();
		try{////0-->agerangeId,1-->totalvoters,2-->cname.,3-->ageRange
		 Set<String> constituenciesSet = new TreeSet<String>();
		 Set<Long>   ageRangeIdsSet    = new TreeSet<Long>();
		
		 for(Object[] obj:votersList)
		 {   
			 constituenciesSet.add(obj[2].toString());
			 if(type.equalsIgnoreCase("voterInfo"))
			    ageRangeIdsSet.add((Long)obj[0]);
			
		 }
		 if(type.equalsIgnoreCase("tdpCadre"))
		 {
			 ageRangeIdsSet.add(2l);
			 ageRangeIdsSet.add(3l);
			 ageRangeIdsSet.add(4l);
			 ageRangeIdsSet.add(5l);
			 ageRangeIdsSet.add(6l);
			
		 }
		 
		
		 for(String constituencyName:constituenciesSet)
		 {
			 TdpCadreLocationWiseReportVO constituencyVO = new TdpCadreLocationWiseReportVO();
			 constituencyVO.setName(constituencyName);
			 
			 for(Long ageRangeId:ageRangeIdsSet)
			 {
				 TdpCadreLocationWiseReportVO ageRangeVO = new TdpCadreLocationWiseReportVO();
				 ageRangeVO.setAgeRangeId(ageRangeId);
				 constituencyVO.getTdpCadreLocationWiseReportVOList().add(ageRangeVO);
			 }
			 
			 resultList.add(constituencyVO);
		 }
		 
	
		   if(type.equalsIgnoreCase("voterInfo")){
			 if(votersList!=null && votersList.size()>0){  
		 
				  for(Object[] obj:votersList)
				 {
					 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,obj[2].toString());
					 TdpCadreLocationWiseReportVO ageRangeVO     =  getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),(Long)obj[0]);
					 ageRangeVO.setTotalVoters((Long)obj[1]);
					 ageRangeVO.setAgeRange(obj[3].toString());
					
				 }
		      }
	       }
		   else if(type.equalsIgnoreCase("tdpCadre")){
			     Long age18To25=0l;
			 	 Long age26To35=0l;
			 	 Long age36To45=0l;
			 	 Long age46To60=0l;
			 	 Long above60=0l;
			 	 Long ageRangeId=1l;
			 	 if(votersList!=null && votersList.size()>0){  
				 for(Object[] obj:votersList)
				 {
					 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,obj[2].toString());
					 if(obj[0]!=null)
					 {
						 Long age1=(Long)obj[0];
						 if(age1>=18 && age1<=25)
						 {
							 age18To25=age18To25+1;
							 ageRangeId=2l;
							 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
							 ageRangeVO.setTotalVoters(age18To25);
						 }
						 else if(age1>=26 && age1<=35)
						 {
							 age26To35=age26To35+1;
							 ageRangeId=3l;
							 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
							 ageRangeVO.setTotalVoters(age26To35);				 
						 }
						 if(age1>=36 && age1<=45)
						 {
							 age36To45=age36To45+1;
							 ageRangeId=4l;
							 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
							 ageRangeVO.setTotalVoters(age36To45);			
						 }
						 else if(age1>=46 && age1<=60)
						 {
							 age46To60=age46To60+1;
							 ageRangeId=5l;
							 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
							 ageRangeVO.setTotalVoters(age46To60);		
						 }
						 else if(age1>=61)
						 {
							 above60=above60+1;
							 ageRangeId=6l;
							 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
							 ageRangeVO.setTotalVoters(above60);	
						 }
					 
					}
					else
					{  
						if(obj[1]!=null){//age,dob,name
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						String dob=sdf.format(obj[1]);
						
						if(dob!=null && dob.toString().trim()!="") 
						{
							String dobYearOfPerson=dob.toString().split("-")[0];
						    if(dobYearOfPerson != null && dobYearOfPerson.trim().length()>0 && !dobYearOfPerson.equalsIgnoreCase("0")){
							 Long dobYear=Long.parseLong(dobYearOfPerson);
							 Long currentYear=2014l;
							 if( currentYear-dobYear>=18 && currentYear-dobYear<=25)
						     {
								 age18To25=age18To25+1;
								 ageRangeId=2l;
								 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
								 ageRangeVO.setTotalVoters(age18To25);
							 }
							 else if(currentYear-dobYear>=26 &&  currentYear-dobYear<=35)
							 {
								 age26To35=age26To35+1;
								 ageRangeId=3l;
								 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
								 ageRangeVO.setTotalVoters(age26To35);				 
							 }
							 if( currentYear-dobYear>=36 &&  currentYear-dobYear<=45)
							 {
								 age36To45=age36To45+1;
								 ageRangeId=4l;
								 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
								 ageRangeVO.setTotalVoters(age36To45);			
							 }
							 else if( currentYear-dobYear>=46 &&  currentYear-dobYear<=60)
							 {
								 age46To60=age46To60+1;
								 ageRangeId=5l;
								 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
								 ageRangeVO.setTotalVoters(age46To60);		
							 }
							 else if( currentYear-dobYear>=60)
							 {
								 above60=above60+1;
								 ageRangeId=6l;
								 TdpCadreLocationWiseReportVO ageRangeVO=getMatchedLocationVO(constituencyVO.getTdpCadreLocationWiseReportVOList(),ageRangeId);
								 ageRangeVO.setTotalVoters(above60);	
							 }
							
						    }
				
					      }
			
			            } 
					 }
				    }
			 	 }//if
		       }//tdpCadre

	}
	catch(Exception e)
	{
	  LOG.error(" exception occured in settingtheData () at TdpCadreReportService ",e);	
	  return null;
	}
	return  resultList;
   }
	
	
	
	 public TdpCadreLocationWiseReportVO getMatchedVOByName(List<TdpCadreLocationWiseReportVO> list,String name)
	 {
	 		  if(list!=null && list.size()>0){
	 		  for(TdpCadreLocationWiseReportVO constituency:list)
	 			  if(constituency.getName().equalsIgnoreCase(name))
	 				  return constituency;
	 		  }
	 		  return null;
	 }
	 public TdpCadreLocationWiseReportVO getMatchedLocationVO(List<TdpCadreLocationWiseReportVO> ageRangeDetailsList , Long ageRangeId)
	 {
		  if(ageRangeDetailsList!=null && ageRangeDetailsList.size()>0){
	 	  for(TdpCadreLocationWiseReportVO ageRange:ageRangeDetailsList)
	 	  {
	 		  if(ageRange.getAgeRangeId().longValue() == ageRangeId.longValue())
	 			  return ageRange;
	 	  }
		  }  
	 	  return null;
	 	  
	 }
	
	 public TdpCadreLocationWiseReportVO getMatchedVOByName1(List<TdpCadreLocationWiseReportVO> list,String name)
	 {
	 		  if(list!=null && list.size()>0){
	 		  for(TdpCadreLocationWiseReportVO gender:list){
	 			 if( name.equalsIgnoreCase("M")||name.equalsIgnoreCase("Male") ){
	 				 if(gender.getGender().equalsIgnoreCase("male")) {
	 					return gender;
	 					 
	 				 }
	 		      }
	 			 else if( name.equalsIgnoreCase("F")||name.equalsIgnoreCase("Female") ){
 				     if(gender.getGender().equalsIgnoreCase("Female")) {
 					   return gender;
 					 
 				      }
 				  }
	 			 else if(name.equalsIgnoreCase("others")){
	 				if(gender.getGender().equalsIgnoreCase("others")) {
	 					   return gender;
	 					 
	 				      } 
	 			
	 			 }
	 		 }//for
	 		}  
	 		  return null;
	
	 }


	
	public TdpCadreLocationWiseReportVO genereteOriginalExcelReport(TdpCadreLocationWiseReportVO constituencyReportVO1,HSSFWorkbook workbook,HSSFSheet constituencyWiseSheet)
	{
		try {
	
			
			DecimalFormat df2 = new DecimalFormat("###.##");
			
			HSSFFont hSSFFont = workbook.createFont();
	        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
	        hSSFFont.setFontHeightInPoints((short) 9);
	        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        hSSFFont.setColor(HSSFColor.BLACK.index);
			hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			
			Font font = workbook.createFont();
		    font.setFontName("Calibri");
		    font.setFontHeightInPoints((short)12);
		    
		    Font font1 = workbook.createFont();
		    font1.setFontName("Calibri");
		    font1.setFontHeightInPoints((short)10);
		    
		    //for heading.
		    Font font2 = workbook.createFont();
		    font2.setFontName("Calibri");
		    font2.setFontHeightInPoints((short)10);
		   
		    
		    CellStyle style1 = workbook.createCellStyle();
		    style1.setFont(font1);
		    style1.setFillForegroundColor(HSSFColor.YELLOW.index);
		    style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style1.setAlignment(CellStyle.ALIGN_CENTER);
		    style1.setFont(hSSFFont);
		   
		    CellStyle style2 = workbook.createCellStyle();
		    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		    style2.setAlignment(CellStyle.ALIGN_CENTER);
		    style2.setFont(font2);
		    
		    CellStyle style5 = workbook.createCellStyle();
		    style5.setFont(hSSFFont);
		    
		    
		    CellStyle style = workbook.createCellStyle();
		    style.setAlignment(CellStyle.ALIGN_CENTER);
		    style.setFont(font);
		   
		    String constituencyName = constituencyReportVO1.getName();
	
			Long totalRegistered = 0l;
			Long totalVoters = 0l;
		
			constituencyWiseSheet.addMergedRegion(new CellRangeAddress(2,2,1,5 )); // TOTAL OVERVIEW  HEADING
			
			HSSFRow row2 = constituencyWiseSheet.createRow((short)2);							
		    Cell cell1 = row2.createCell((short)1);
		    cell1.setCellValue(" KRIYASEELA SABHYATVAM 2014 STATUS  - "+" "+constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getConstituencyNo()+" - "+constituencyName);
		    cell1.setCellStyle(style5);
		    
		    HSSFRow row4 = constituencyWiseSheet.createRow((short)4);							
		    Cell cell2 = row4.createCell((short)1);
		    cell2.setCellValue(" 2012 CADRE COUNT ");
		    cell2.setCellStyle(style5);
		    
		    cell2 = row4.createCell((short)2);
		    cell2.setCellValue(constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getCadresCount() != null ? constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getCadresCount().toString():"");
		    
		    cell2 = row4.createCell((short)4);
		    cell2.setCellValue(" 2014 CADRE COUNT ");
		    cell2.setCellStyle(style5);
		    
		    cell2 = row4.createCell((short)5);
		    cell2.setCellValue(constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getRegisteredCadre() != null ? constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getRegisteredCadre().toString() :"");
	
		    
		    HSSFRow row5 = constituencyWiseSheet.createRow((short)6);
		    Cell cell5 = row5.createCell((short)1);
		    cell5.setCellValue(IConstants.CONSTITUENCY);
		    cell5.setCellStyle(style1);   
			    
		    cell5 = row5.createCell((short)2);
		    cell5.setCellValue(" TOTAL VOTERS ");
		    cell5.setCellStyle(style1);
		   
		    cell5 = row5.createCell((short)3);
		    cell5.setCellValue(" TARGET ");
		    cell5.setCellStyle(style1);
		    
		    cell5 = row5.createCell((short)4);
		    cell5.setCellValue("  ACHIEVED ");
		    cell5.setCellStyle(style1);
		    
		    cell5 = row5.createCell((short)5);
		    cell5.setCellValue(" ACHIEVED %  ");
		    cell5.setCellStyle(style1);
		    
		    HSSFRow row6 = constituencyWiseSheet.createRow((short)7);
		    Cell cell6 = row6.createCell((short)1);
		    cell6.setCellValue(constituencyReportVO1.getName() != null ? constituencyReportVO1.getName() : "");
		    cell6.setCellStyle(style2);
		    
		    cell6 = row6.createCell((short)2);
		    cell6.setCellValue(constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L);
		    cell6.setCellStyle(style2);
		    
		    totalVoters = constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L;
		    
		    cell6 = row6.createCell((short)3);
		    cell6.setCellValue(constituencyReportVO1.getTargetedCadre() != null ? constituencyReportVO1.getTargetedCadre() :0L);
		    cell6.setCellStyle(style2);
		    			    
		    cell6 = row6.createCell((short)4);
		    cell6.setCellValue(constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L);
		    cell6.setCellStyle(style2);
		    
		    totalRegistered = constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L;
		    Double percentage = constituencyReportVO1.getRegisteredCadre() != null ? (constituencyReportVO1.getRegisteredCadre() * 100.0 / constituencyReportVO1.getTargetedCadre())  :0.00d;
		    cell6 = row6.createCell((short)5);
		    cell6.setCellValue(percentage > 0 ? Double.valueOf(df2.format(percentage)).toString():" -- ");
		    cell6.setCellStyle(style2);
		    
		   constituencyWiseSheet.addMergedRegion(new CellRangeAddress(9,9,1,4 )); //GENDER OVERVIEW HEADING
		    
		    HSSFRow row8 = constituencyWiseSheet.createRow(9);							
		    Cell cell8 = row8.createCell((short)1);
		    cell8.setCellValue(" GENDER WISE OVERVIEW  ");
		    cell8.setCellStyle(style5);
		    
		    HSSFRow row9 = constituencyWiseSheet.createRow((short)10);
		    Cell cell9 = row9.createCell((short)1);
		    cell9.setCellValue(" GENDER ");
		    cell9.setCellStyle(style1);   
			    
		    cell9 = row9.createCell((short)2);
		    cell9.setCellValue(" TOTAL ");
		    cell9.setCellStyle(style1); 
		    
		    cell9 = row9.createCell((short)3);
		    cell9.setCellValue(" ACHIEVED ");
		    cell9.setCellStyle(style1); 
		    
		    cell9 = row9.createCell((short)4);
		    cell9.setCellValue(" ACHIEVED % ");
		    cell9.setCellStyle(style1); 
		    
		    HSSFRow row10 = constituencyWiseSheet.createRow((short)11);
		    Cell cell10 = row10.createCell((short)1);
		    cell10.setCellValue(" MALE ");
		    cell10.setCellStyle(style1);
		   
		    cell10 = row10.createCell((short)2);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() :0L);
		    cell10.setCellStyle(style2);
		    
		    Long genderCount = constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters();
		    
		    cell10 = row10.createCell((short)3);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount():0L);
		    cell10.setCellStyle(style2);
		    
		    cell10 = row10.createCell((short)4);
		    cell10.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc():0.0d);
		    cell10.setCellStyle(style2);
		    
		    
		    HSSFRow row11 = constituencyWiseSheet.createRow(12);
		    Cell cell11 = row11.createCell((short)1);
		    cell11.setCellValue(" FEMALE ");
		    cell11.setCellStyle(style1);
		   
		    cell11 = row11.createCell((short)2);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() :0L);
		    cell11.setCellStyle(style2);
		    
		    genderCount = genderCount + constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters();
		    
		    cell11 = row11.createCell((short)3);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() :0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row11.createCell((short)4);
		    cell11.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() :0.0d);
		    cell11.setCellStyle(style2);
		    
		   /* 
		    HSSFRow row12 = constituencyWiseSheet.createRow(13);
		    Cell cell12 = row12.createCell((short)1);
		    cell12.setCellValue(" OTHERS ");
		    cell12.setCellStyle(style1);
		    
		   /* cell12 = row12.createCell((short)2);
		    
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getTotalVoters() :0L);
		    cell12.setCellStyle(style2);*/
		    /*
		    cell12 = row12.createCell((short)2);
		    cell12.setCellValue( totalVoters - genderCount);
		    cell12.setCellStyle(style2);
		    
		    cell12 = row12.createCell((short)3);
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getCadresCount() :0L);
		    cell12.setCellStyle(style2);
		    
		    cell12 = row12.createCell((short)4);
		    cell12.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(2).getGenderPerc() :0.0d);
		    cell12.setCellStyle(style2);
		    */
		    
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(15,15,1,4 )); // CASTE OVER VIEW HEADING
		    
		    HSSFRow row13 = constituencyWiseSheet.createRow((short)15);
		    Cell cell13 = row13.createCell((short)1);
		    cell13.setCellValue(" CASTE CATEGORY WISE OVERVIEW  ");
		    cell13.setCellStyle(style5);
		   
		    HSSFRow row14 = constituencyWiseSheet.createRow(16);
		    Cell cell14 = row14.createCell((short)1);
		    cell14.setCellValue(" CASTE  CATEGORY ");
		    cell14.setCellStyle(style1);
		    
		    cell14 = row14.createCell((short)2);
		    cell14.setCellValue("ACHIEVED");
		    cell14.setCellStyle(style1);
		    
		    cell14 = row14.createCell((short)3);
		    cell14.setCellValue("ACHIEVED %");
		    cell14.setCellStyle(style1);
		    
		    Integer rowNo = 17;
		    for( CadreRegisterInfo vo : constituencyReportVO1.getCasteGroupList())
		    {
		    	if(vo != null)
		    	{
		    		HSSFRow row = constituencyWiseSheet.createRow(rowNo);
				    Cell cellForCaste = row.createCell((short)1);
				    cellForCaste.setCellValue(vo.getName() != null ? vo.getName().toString() : "");
				    cellForCaste.setCellStyle(style1);
				    
				    cellForCaste = row.createCell((short)2);
				    cellForCaste.setCellValue(vo.getApCount() != null ? vo.getApCount().toString() : "");
				    cellForCaste.setCellStyle(style2);
				    
				    cellForCaste = row.createCell((short)3);
				    cellForCaste.setCellValue(vo.getPercentStr() != null ? vo.getPercentStr().toString() : "");
				    cellForCaste.setCellStyle(style2);
				    rowNo++;
		    	}
		    	    
		    }
		    
		    rowNo = rowNo + 2;
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,4 )); // AGE OVER VIEW HEADING
		    HSSFRow rowForAgeHead = constituencyWiseSheet.createRow(rowNo);
		    Cell cellForAgeHead = rowForAgeHead.createCell((short)1);
		    cellForAgeHead.setCellValue(" AGE WISE OVERVIEW  ");
		    cellForAgeHead.setCellStyle(style5);
		    
		    rowNo = rowNo + 1;
		    HSSFRow rowForAge = constituencyWiseSheet.createRow(rowNo);
		    Cell cellHeadForAge = rowForAge.createCell((short)1);
		    cellHeadForAge.setCellValue("  AGE RANGE  ");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)2);
		    cellHeadForAge.setCellValue("TOTAL VOTERS");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)3);
		    cellHeadForAge.setCellValue("TOTAL %");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)4);
		    cellHeadForAge.setCellValue("ACHIEVED");
		    cellHeadForAge.setCellStyle(style1);
		    
		    cellHeadForAge = rowForAge.createCell((short)5);
		    cellHeadForAge.setCellValue("ACHIEVED %");
		    cellHeadForAge.setCellStyle(style1);
		    
		    rowNo = rowNo + 1;
		    for(TdpCadreLocationWiseReportVO vo : constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList())
		    {
		    	    HSSFRow rowForAgeCell = constituencyWiseSheet.createRow(rowNo);
				    Cell cellForAge = rowForAgeCell.createCell((short)1);
				    cellForAge.setCellValue(vo.getAgeRange()  != null ? vo.getAgeRange().toString() : "");
				    cellForAge.setCellStyle(style1);
				    
				    cellForAge = rowForAgeCell.createCell((short)2);
				    cellForAge.setCellValue(vo.getTotalVoters()  != null ? vo.getTotalVoters().toString() : "");
				    cellForAge.setCellStyle(style2);
				  
				    if(totalVoters > 0)
				    {
				    	cellForAge = rowForAgeCell.createCell((short)3);
					    cellForAge.setCellValue(new BigDecimal((vo.getTotalVoters() .doubleValue() / totalVoters.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString() );
					    cellForAge.setCellStyle(style2);
					    
				    }
				    else
				    {
				    	cellForAge = rowForAgeCell.createCell((short)3);
					    cellForAge.setCellValue("0.00" );
					    cellForAge.setCellStyle(style2);
					    
				    }
				    
				    
				    cellForAge = rowForAgeCell.createCell((short)4);
				    cellForAge.setCellValue(vo.getCadresInAge()  != null ? vo.getCadresInAge().toString() : "");
				    cellForAge.setCellStyle(style2);
				    
				    if(totalRegistered > 0)
				    {
				    	 cellForAge = rowForAgeCell.createCell((short)5);
						 cellForAge.setCellValue(vo.getCadresInAge() != null ? new BigDecimal((vo.getCadresInAge() .doubleValue() / totalRegistered.doubleValue()) * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString():"") ;
						 cellForAge.setCellStyle(style2);
				    }
				    else
				    {
				    	 cellForAge = rowForAgeCell.createCell((short)5);
						 cellForAge.setCellValue("0.00") ;
						 cellForAge.setCellStyle(style2);
				    }
				    rowNo++;
		    }
		    
		    rowNo = rowNo+2;
		   
		    List<TdpCadreLocationWiseReportVO> mandalsList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getTehsilWiseList();
	
		    if(mandalsList != null && mandalsList.size()>0)
		    {
		    	constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,4 )); // AGE OVER VIEW HEADING
		 	    HSSFRow row17 = constituencyWiseSheet.createRow(rowNo);
		 	    Cell cell17 = row17.createCell((short)1);
		 	    cell17.setCellValue(" MANDAL WISE OVERVIEW  ");
		 	    cell17.setCellStyle(style5);
		 	    
		 	    rowNo = rowNo+1;
		 	    HSSFRow tehsilrow = constituencyWiseSheet.createRow(rowNo);
		 	    Cell tehsilCell = tehsilrow.createCell((short)1);
		 	   tehsilCell.setCellValue(IConstants.TEHSIL);
		 	   tehsilCell.setCellStyle(style1);   
		 		    
		 	   tehsilCell = tehsilrow.createCell((short)2);
		 	   tehsilCell.setCellValue(" TOTAL VOTERS ");
		 	   tehsilCell.setCellStyle(style1);
		 	   
		 	   tehsilCell = tehsilrow.createCell((short)3);
		 	   tehsilCell.setCellValue(" TARGET ");
		 	   tehsilCell.setCellStyle(style1);
		 	    
		 	   tehsilCell = tehsilrow.createCell((short)4);
		 	   tehsilCell.setCellValue(" ACHIEVED ");
		 	   tehsilCell.setCellStyle(style1);
		 	    
		 	   tehsilCell = tehsilrow.createCell((short)5);
		 	   tehsilCell.setCellValue(" ACHIEVED % ");
		 	   tehsilCell.setCellStyle(style1);
		 	   
		 	    rowNo = rowNo+1;
		 	    
		    	for (TdpCadreLocationWiseReportVO mandalsVo : mandalsList) 
		    	{
		    		HSSFRow tehsilRow = constituencyWiseSheet.createRow(rowNo);
		    		 Cell tehsilCells = tehsilRow.createCell((short)1);
		    		 tehsilCells.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
		    		 tehsilCells.setCellStyle(style2);
		    		 
		    		 tehsilCells = tehsilRow.createCell((short)2);
		    		 tehsilCells.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
		    		 tehsilCells.setCellStyle(style2);
				    
		    		 tehsilCells = tehsilRow.createCell((short)3);
		    		 tehsilCells.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
		    		 tehsilCells.setCellStyle(style2);
				    			    
		    		 tehsilCells = tehsilRow.createCell((short)4);
		    		 tehsilCells.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString():" -- ");
		    		 tehsilCells.setCellStyle(style2);
		    		 
		    		 Double mandalPerc =  mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre() * 100.0 / mandalsVo.getTargetedCadre() :0.00d;
	
		    		 tehsilCells = tehsilRow.createCell((short)5);//mandal wise percentage
		    		 tehsilCells.setCellValue(mandalPerc > 0 ? Double.valueOf(df2.format(mandalPerc)).toString():" -- ");
		    		 tehsilCells.setCellStyle(style2);
					 
				     rowNo = rowNo+1;
				}
		    }
		    
		    /*
		    rowNo = rowNo+2;
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(rowNo,rowNo,1,6 )); // PANCHAYAT WISE OVER VIEW 
		    HSSFRow row = constituencyWiseSheet.createRow(rowNo);
		    Cell cell = row.createCell((short)1);
		    cell.setCellValue(" BOOTH WISE OVERVIEW  ");
		    cell.setCellStyle(style5);
		    
		    rowNo = rowNo + 1;
		    
		    HSSFRow textRow = constituencyWiseSheet.createRow(rowNo);
		    Cell textCell = textRow.createCell((short)1);
		    textCell.setCellValue(IConstants.BOOTH);
		    textCell.setCellStyle(style1);   
			    
		    textCell = textRow.createCell((short)2);
		    textCell.setCellValue(" PANCHAYAT / AREA COVERED ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)3);
		    textCell.setCellValue(" TOTAL VOTERS ");
		    textCell.setCellStyle(style1);
		   
		    textCell = textRow.createCell((short)4);
		    textCell.setCellValue(" TARGET ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)5);
		    textCell.setCellValue(" ACHIEVED ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)6);
		    textCell.setCellValue(" ACHIEVED % ");
		    textCell.setCellStyle(style1);
		    
		    rowNo = rowNo + 1;
		    List<TdpCadreLocationWiseReportVO> panchayatList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getPanchayatWiseList();
		    
		    if(panchayatList != null && panchayatList.size()>0)
		    {
		    	for (TdpCadreLocationWiseReportVO mandalsVo : panchayatList) 
		    	{
		    		HSSFRow panchaytrow = constituencyWiseSheet.createRow(rowNo);
		    		 Cell panchaytCell = panchaytrow.createCell((short)1);
		    		 panchaytCell.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
		    		 panchaytCell.setCellStyle(style2);
		    		 
		    		 panchaytCell = panchaytrow.createCell((short)2);
		    		 panchaytCell.setCellValue(mandalsVo.getLocationType() != null ? mandalsVo.getLocationType() : "");
		    		 panchaytCell.setCellStyle(style2);
		    		 
		    		 panchaytCell = panchaytrow.createCell((short)3);
		    		 panchaytCell.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
		    		 panchaytCell.setCellStyle(style2);
				    
		    		 panchaytCell = panchaytrow.createCell((short)4);
		    		 panchaytCell.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
		    		 panchaytCell.setCellStyle(style2);
				    			    
		    		 panchaytCell = panchaytrow.createCell((short)5);
		    		 panchaytCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString()  :" -- ");
		    		 panchaytCell.setCellStyle(style2);
		    		 
		    		 Double boothPerc =  mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre() * 100.0 / mandalsVo.getTargetedCadre() :0.00d;
	
		    		 panchaytCell = panchaytrow.createCell((short)6);
		    		 panchaytCell.setCellValue(boothPerc > 0 ? Double.valueOf(df2.format(boothPerc)).toString() :" -- ") ;
		    		 panchaytCell.setCellStyle(style2);
					 
		    		 rowNo = rowNo + 1;
				}
		    }
		     */
		    
		    System.out.println( constituencyName +" Constituency Excel Report generation completed. ");
		    LOG.info( constituencyName +" Constituency Excel Report generation completed. ");
		    
		    System.out.println("===================================================================================");
		    
		} catch (Exception e) {
			LOG.error(" exception occured in genereteOriginalExcelReport() @ TdpCadreReportService class.",e);
		}
		
		return constituencyReportVO1;
	}
	/*
	public SurveyTransactionVO getMemberShipCardPrintDetails(Long districtId,List<Long> constituencyIds,String fromDateStr,String toDateStr)
	{
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			if(constituencyIds != null && constituencyIds.size()>0)
			{
				@SuppressWarnings("unchecked")
				List<Object[]> parliamentInfo = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituencyIds);
				Map<Long,String> parliamentForAssemblyMap = new HashMap<Long, String>();
				
				if(parliamentInfo != null && parliamentInfo.size()>0)
				{
					for (Object[] parliament : parliamentInfo) 
					{
						parliamentForAssemblyMap.put(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L, parliament[2] != null ? parliament[2].toString().trim():"");
					}
				}
				
				
				Date fromDate = format.parse(fromDateStr);
				Date toDate = format.parse(toDateStr);
				
				List<Object[]> totalCountList = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, null);
				List<Object[]> successCountList = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, "printStatus");
				List<Object[]> failureCountList = zebraPrintDetailsDAO.getMemberShipCardPrintStatusByDate(constituencyIds, fromDate, toDate, "errorStatus");
				
				List<SurveyTransactionVO> printSatusReportList = null;
				if(totalCountList != null && totalCountList.size()>0)
				{
					printSatusReportList = new ArrayList<SurveyTransactionVO>(0);
					for (Object[] printStatus : totalCountList) 
					{
						SurveyTransactionVO reportVo = new SurveyTransactionVO();
						reportVo.setSurveyDate(format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")));
						reportVo.setLocationId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
						reportVo.setLocationName(printStatus[1] != null ? printStatus[1].toString().trim():"");
						reportVo.setName(printStatus[2] != null ? printStatus[2].toString().trim():"");
						reportVo.setTotalCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
						reportVo.setParliamentName(parliamentForAssemblyMap.get(reportVo.getLocationId()));
						
						reportVo.setSubmittedCount(0L);
						reportVo.setNotSubmittedCount(0L);
						
						printSatusReportList.add(reportVo);
					}
				}
				
				if(successCountList != null && successCountList.size()>0)
				{
					for (Object[] printStatus : successCountList) 
					{
						Long locationId = printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L;
						SurveyTransactionVO reportVO = getMatchedSurveyTransactionVOByLocationId(printSatusReportList,locationId);
						
						if(reportVO != null)
						{
							reportVO.setSubmittedCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
						}
					}
				}
				
				if(failureCountList != null && failureCountList.size()>0)
				{
					for (Object[] printStatus : failureCountList) 
					{
						Long locationId = printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L;
						SurveyTransactionVO reportVO = getMatchedSurveyTransactionVOByLocationId(printSatusReportList,locationId);
						
						if(reportVO != null)
						{
							reportVO.setNotSubmittedCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
						}
					}
				}
			
				if(printSatusReportList != null && printSatusReportList.size()>0)
				{
					returnVO.setSurveyTransactionVOList(printSatusReportList);
				}
				
			}
			
			
		}
		catch (Exception e)
		{
			LOG.error(" exception occured in getMemberShipCardDetails()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}

	public SurveyTransactionVO getMatchedSurveyTransactionVOByLocationId(List<SurveyTransactionVO> reportVOList,Long locationId)
	{
		SurveyTransactionVO returnVo = null;
		try {
			
			if(reportVOList != null && reportVOList.size()>0)
			{
				for (SurveyTransactionVO reportVO : reportVOList) 
				{
					if(reportVO.getLocationId().longValue() == locationId.longValue())
					{
						return reportVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getMatchedSurveyTransactionVOByLocationId()  @ TdpCadreReportService class.",e);
		}
		
		return returnVo;
	}
	*/
	public SurveyTransactionVO getConstituencyDetailsInDistricts(List<Long> districtIdList)
	{
		SurveyTransactionVO returnVO = new SurveyTransactionVO();
		
		try {
			if(districtIdList != null && districtIdList.size()>0)
			{
				getConstituencyDetailsForDistricts(districtIdList,returnVO);
				/*List<Object[]> constituencyList = constituencyDAO.getConstituencysByDistrictId(districtIdList);
				
				List<SurveyTransactionVO> returnList = null;
				if(constituencyList != null && constituencyList.size()>0)
				{
					returnList = new ArrayList<SurveyTransactionVO>(0);
					for (Object[] printStatus : constituencyList) 
					{
						SurveyTransactionVO reportVo = new SurveyTransactionVO();
						reportVo.setId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
						reportVo.setName(printStatus[1] != null ? printStatus[1].toString().trim():"");
						returnList.add(reportVo);
					}
				}
				
				if(returnList != null && returnList.size()>0)
				{
					returnVO.setSurveyTransactionVOList(returnList);
				}*/
			}else{
				getConstituencyDetailsForDistricts(districtIdList,returnVO);
			}
						
		} catch (Exception e) {
			LOG.error(" exception occured in getConstituencyDetailsInDistricts()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}
	
	public ZebraPrintDetailsVO getMemberShipCardPrintDetails(String searchType,Long stateTypeId,List<Long> selectedLocationIds,String fromDateStr,String toDateStr)
	{

		ZebraPrintDetailsVO returnVO = new ZebraPrintDetailsVO();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {

			if(searchType != null && (selectedLocationIds != null && selectedLocationIds.size()>0))
			{
				
				Date fromDate = format.parse(fromDateStr);
				Date toDate = format.parse(toDateStr);
				StringBuilder queryStrForPushData = new StringBuilder();
				StringBuilder queryStr = new StringBuilder();
				StringBuilder queryStrForCount = new StringBuilder();
				List<Object[]> printStatusDetailsList = null;
				List<ZebraPrintDetailsVO> printStatusList = null;
				
				Map<Long,String> parliamentForAssemblyMap = new TreeMap<Long, String>();
				List<Object[]> locationsList =  delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);							
					
				List<Long> locationIdList = new ArrayList<Long>(0);
				List<Long> assemblyIds = new ArrayList<Long>(0);
				
				if(locationsList != null && locationsList.size()>0)
				{
					for (Object[] param : locationsList)
					{
						locationIdList.add(param[0] != null ? Long.valueOf(param[0].toString()) :0L);
					}
				}
						
				if(locationIdList != null && locationIdList.size()>0)
				{
						locationsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesListForAListOfParliamentConstituency(locationIdList);
												
						if(locationsList != null && locationsList.size()>0)
						{
							for (Object[] param : locationsList)
							{
								assemblyIds.add(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L );
								parliamentForAssemblyMap.put(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L , param[2] != null ? param[2].toString().trim() :"");
							}
						}	
				}
				
				
				if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY) || searchType.equalsIgnoreCase(IConstants.STATE))
				{
					if( searchType.equalsIgnoreCase(IConstants.STATE))
					{
						List<Object[]> constituencyList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId, 1L, null);
						if(constituencyList != null && constituencyList.size()>0)
						{
							selectedLocationIds.clear();
							for (Object[] cosntituency : constituencyList)
							{
								selectedLocationIds.add(cosntituency[0] != null? Long.valueOf(cosntituency[0].toString().trim()):0L);
							}
						}
					}
					// start query for printing cards details
					queryStr.append(" select TD.userAddress.constituency.constituencyId, TD.userAddress.constituency.name, TD.userAddress.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId) ,  ");
					queryStr.append(" date(ZPD.updatedTime), ZPD.printStatus, count(ZPD.printStatus), ZPD.errorStatus, count(ZPD.errorStatus)  ");
					queryStr.append(" from ZebraPrintDetails ZPD, TdpCadre TD where ");
					
					queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and ZPD.printStatus is not null  and ");
					queryStr.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and TD.userAddress.constituency.constituencyId in (:locationIdList) ");
					
					if(fromDate != null && toDate != null)
					{
						queryStr.append(" and ( date(ZPD.updatedTime) >= :fromDate and date(ZPD.updatedTime) <= :toDate ) ");
					}
					
					queryStr.append(" group by TD.userAddress.constituency.constituencyId, date(ZPD.insertedTime),date(ZPD.updatedTime), ZPD.printStatus, ZPD.errorStatus  order by date(ZPD.updatedTime) asc ");
					
					// end query for printing cards details
					
					
					// start query for total cadre
					queryStrForCount.append(" select count(TC.tdpCadreId) from TdpCadre TC where TC.userAddress.constituency.constituencyId in (:locationIds) and TC.isDeleted='N' and TC.enrollmentYear = 2014  ");

					// end query for total cadre
					
					
					// start query for push data details for printing
					queryStrForPushData.append(" select TD.userAddress.constituency.constituencyId, TD.userAddress.constituency.name, TD.userAddress.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId) ");
					queryStrForPushData.append(" from ZebraPrintDetails ZPD, TdpCadre TD  where ");
					
					queryStrForPushData.append(" TD.tdpCadreId = ZPD.tdpCadreId and ");
					queryStrForPushData.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and TD.userAddress.constituency.constituencyId in (:locationIdList) ");
					
					if(fromDate != null && toDate != null)
					{
						queryStrForPushData.append(" and ( date(ZPD.insertedTime) >= :fromDate and date(ZPD.insertedTime) <= :toDate ) ");
					}
					
					queryStrForPushData.append(" group by date(ZPD.insertedTime),TD.userAddress.constituency.constituencyId order by date(ZPD.insertedTime) asc ");
					
					// end query for push data details for printing
				}
				else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
				{
					queryStr.append(" select UA.district.districtId,UA.district.districtName, UA.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId) ,  ");
					queryStr.append(" date(ZPD.updatedTime), ZPD.printStatus, count(ZPD.printStatus), ZPD.errorStatus, count(ZPD.errorStatus)  ");
					queryStr.append(" from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA where ");
					
					queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId and ((ZPD.printStatus !='' or ZPD.printStatus is null) and ( ZPD.errorStatus != '0' or ZPD.errorStatus is null )) and ");
					queryStr.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and UA.district.districtId in (:locationIdList) ");
					
					if(fromDate != null && toDate != null)
					{
						queryStr.append(" and ( date(ZPD.updatedTime) >= :fromDate and date(ZPD.updatedTime) <= :toDate ) ");
					}
					
					queryStr.append(" group by UA.district.districtId, date(ZPD.insertedTime),date(ZPD.updatedTime), ZPD.printStatus, ZPD.errorStatus  order by date(ZPD.updatedTime) asc ");
					
					queryStrForCount.append(" select count(TC.tdpCadreId) from TdpCadre TC where TC.userAddress.district.districtId in (:locationIds) and TC.isDeleted='N' and TC.enrollmentYear = 2014 ");
				
					
					// start query for push data details for printing
					queryStrForPushData.append(" select UA.district.districtId,UA.district.districtName, UA.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId)  ");
					queryStrForPushData.append(" from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA where ");
					
					queryStrForPushData.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId  and ");
					queryStrForPushData.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and TD.userAddress.district.districtId in (:locationIdList) ");
					
					if(fromDate != null && toDate != null)
					{
						queryStrForPushData.append(" and ( date(ZPD.insertedTime) >= :fromDate and date(ZPD.insertedTime) <= :toDate ) ");
					}
					
					queryStrForPushData.append(" group by date(ZPD.insertedTime),TD.userAddress.district.districtId order by date(ZPD.insertedTime) asc ");
					
					// end query for push data details for printing
					
				}
				else if(searchType.equalsIgnoreCase("parliament"))
				{
					queryStr.append(" select distinct model2.delimitationConstituency.constituency.constituencyId,model2.delimitationConstituency.constituency.name, model2.delimitationConstituency.constituency.district.districtName, date(ZPD.insertedTime), count(ZPD.zebraPrintDetailsId) ,  ");
					queryStr.append(" date(ZPD.updatedTime), ZPD.printStatus, count(ZPD.printStatus), ZPD.errorStatus, count(ZPD.errorStatus)  ");
					queryStr.append(" from ZebraPrintDetails ZPD, TdpCadre TD, UserAddress UA , DelimitationConstituencyAssemblyDetails model2 where ");
					queryStr.append(" UA.constituency.constituencyId = model2.constituency.constituencyId and model2.delimitationConstituency.year = 2009 and ");
					queryStr.append(" TD.tdpCadreId = ZPD.tdpCadreId and TD.userAddress.userAddressId = UA.userAddressId and ((ZPD.printStatus !='' or ZPD.printStatus is null) or ( ZPD.errorStatus !='' or ZPD.errorStatus is null )) and ");
					queryStr.append(" TD.isDeleted = 'N' and TD.enrollmentYear = 2014 and model2.delimitationConstituency.constituency.constituencyId in (:locationIdList) ");
					
					if(fromDate != null && toDate != null)
					{
						queryStr.append(" and ( date(ZPD.updatedTime) >= :fromDate and date(ZPD.updatedTime) <= :toDate ) ");
					}
					
					queryStr.append(" group by model2.delimitationConstituency.constituency.constituencyId ,date(ZPD.insertedTime), date(ZPD.updatedTime), ZPD.printStatus, ZPD.errorStatus  order by date(ZPD.updatedTime) asc ");
					
					queryStrForCount.append(" select count(TC.tdpCadreId) from TdpCadre TC ,DelimitationConstituencyAssemblyDetails model2  where " +
							" TC.userAddress.constituency.constituencyId = model2.constituency.constituencyId and model2.delimitationConstituency.year = 2009 and " +
							" model2.delimitationConstituency.constituency.constituencyId in (:locationIds) and TC.isDeleted='N' and TC.enrollmentYear = 2014");
				
				}
				ZebraPrintDetailsVO pushDataVO = getMemberShipCardDataPushDetails(parliamentForAssemblyMap,selectedLocationIds, fromDate, toDate,queryStrForPushData.toString());
				printStatusDetailsList = zebraPrintDetailsDAO.getMemberShipPrintCardStatusByDate(selectedLocationIds, fromDate, toDate,queryStr.toString());
				Long registeredCount = tdpCadreDAO.getTotalRegisteredCadreCountByLocation(selectedLocationIds, queryStrForCount.toString());
				Map<Long,ZebraPrintDetailsVO> zebraPrintMap = new HashMap<Long, ZebraPrintDetailsVO>(0);
				
				Map<String,ZebraPrintDetailsVO>  daywiseVOMap = new TreeMap<String, ZebraPrintDetailsVO>();
				if(printStatusDetailsList != null && printStatusDetailsList.size()>0)
				{
					for (Object[] printStatus : printStatusDetailsList) 
					{
						ZebraPrintDetailsVO locationVO = null;
						
						if(zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L) != null)
						{
							locationVO = zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
							printStatusList = locationVO.getZebraPrintDetailsVOList();
						}
						else
						{
							locationVO = new ZebraPrintDetailsVO();
							locationVO.setId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
							locationVO.setName(printStatus[1] != null ? printStatus[1].toString().trim():"");
							locationVO.setDistrict(printStatus[2] != null ? printStatus[2].toString().trim():"");
							locationVO.setParliament(parliamentForAssemblyMap.get(locationVO.getId()) != null ? parliamentForAssemblyMap.get(locationVO.getId()):"");
							locationVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
							printStatusList = new ArrayList<ZebraPrintDetailsVO>();
						}
						
						ZebraPrintDetailsVO reportVO = new ZebraPrintDetailsVO();
						
						reportVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
						reportVO.setTotalPushCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
						
						reportVO.setUpdatedDate(printStatus[5] != null ? format.format(dateFormat.parse(printStatus[5] != null ? printStatus[5].toString().trim():"")):null);
						reportVO.setPrintStatus(printStatus[6] != null ? printStatus[6].toString().trim():null);
						reportVO.setPrintStatusCount(printStatus[7] != null ? Long.valueOf(printStatus[7].toString().trim()):0L);
						
						reportVO.setErrorStatus(printStatus[8] != null ? printStatus[8].toString().trim():null);
						reportVO.setErrorStatusCount(printStatus[9] != null ? Long.valueOf(printStatus[9].toString().trim()):0L);
						
						if(reportVO.getPrintStatus() == null || reportVO.getPrintStatus().trim().length()>0)
						{
							printStatusList.add(reportVO);
							
							locationVO.setZebraPrintDetailsVOList(printStatusList);
							
							zebraPrintMap.put(locationVO.getId(), locationVO);
							
							if(reportVO.getUpdatedDate() != null)
							{
								daywiseVOMap.put(reportVO.getUpdatedDate(), null);
							}
							else
							{
								daywiseVOMap.put(reportVO.getDataPushDate(), null);
							}
						}
					}
				}
				
				List<ZebraPrintDetailsVO> finalReturnList = new ArrayList<ZebraPrintDetailsVO>();
				
				Long totalPrintedCount = 0L;
				Long totalErrorsCount = 0L;
				
				if(zebraPrintMap != null && zebraPrintMap.size()>0)
				{
					for (Long constituencyId : zebraPrintMap.keySet()) 
					{
						ZebraPrintDetailsVO constituencyVO = zebraPrintMap.get(constituencyId);
						printStatusList = new ArrayList<ZebraPrintDetailsVO>();
						Map<String,ZebraPrintDetailsVO>  daywiseCountMap = new TreeMap<String, ZebraPrintDetailsVO>();
								
						if(constituencyVO.getZebraPrintDetailsVOList() != null && constituencyVO.getZebraPrintDetailsVOList().size()>0)
						{
							Long totalCount = 0L;
							
							if(daywiseVOMap != null && daywiseVOMap.size()>0)
							{
								for (ZebraPrintDetailsVO totalCountVO : constituencyVO.getZebraPrintDetailsVOList()) 
								{
									totalCount = totalCount + totalCountVO.getTotalPushCount();
								}
								for (String date : daywiseVOMap.keySet()) 
								{
									ZebraPrintDetailsVO updatedVO = null;
									ZebraPrintDetailsVO filterVO  = getMatchedZebraPrintDetailsVOByDate(constituencyVO.getZebraPrintDetailsVOList(),date);

									Long printCount = 0L;
									Long errorCount = 0L;
									
									if(filterVO != null)
									{										
										
										if(filterVO != null && filterVO.getUpdatedDate() != null )
										{				
											if(daywiseCountMap.get(filterVO.getUpdatedDate().trim()) != null)
											{
												updatedVO = daywiseCountMap.get(filterVO.getUpdatedDate().trim());
												printCount = updatedVO.getPrintStatusCount();
												errorCount = updatedVO.getErrorStatusCount();
											}
											else
											{
												updatedVO = new ZebraPrintDetailsVO();
											}
											
											if(filterVO.getPrintStatus() != null)
											{
												printCount = printCount + filterVO.getPrintStatusCount();
											}
																					
											if(filterVO.getErrorStatus() != null)
											{
												errorCount = errorCount + filterVO.getErrorStatusCount();
											}
											
											updatedVO.setUpdatedDate(filterVO.getUpdatedDate());
											updatedVO.setPrintStatusCount(printCount);
											updatedVO.setErrorStatusCount(errorCount);
											
											daywiseCountMap.put(date.trim(), updatedVO);
											
										}
									}									
									else
									{
										updatedVO = new ZebraPrintDetailsVO();
										updatedVO.setUpdatedDate(" -- ");
										updatedVO.setPrintStatusCount(0L);
										updatedVO.setErrorStatusCount(0L);
										
										daywiseCountMap.put(date.trim(), updatedVO);
									}
								
									totalPrintedCount = totalPrintedCount + printCount;
									totalErrorsCount = totalErrorsCount + errorCount;
								}
							}
							
							if(daywiseCountMap != null && daywiseCountMap.size()>0)
							{
								for (String updatedDate : daywiseCountMap.keySet()) 
								{
									printStatusList.add(daywiseCountMap.get(updatedDate));
								}
							}
							
							
							
							constituencyVO.setTotalPushCount(totalCount);
							constituencyVO.setZebraPrintDetailsVOList(printStatusList);
						}
						
						

						finalReturnList.add(constituencyVO);
					}
				}
				
				System.out.println(finalReturnList.size());
				if(daywiseVOMap != null && daywiseVOMap.size()>0)
				{
					returnVO.getDatesList().addAll(daywiseVOMap.keySet());
				}
				returnVO.setZebraPrintDetailsVOList(finalReturnList);
				returnVO.setPrintStatusCount(totalPrintedCount);
				returnVO.setErrorStatusCount(totalErrorsCount);
				returnVO.setTotalPushCount(registeredCount);
				
				
				if(pushDataVO != null && pushDataVO.getZebraPrintDetailsVOList() != null && pushDataVO.getZebraPrintDetailsVOList().size()>0)
				{
					returnVO.setDataPushDetailsList(pushDataVO.getZebraPrintDetailsVOList());
					returnVO.getDataPushDatesList().addAll(pushDataVO.getDatesList());
				}
				
			}
		}
		catch (Exception e)
		{
			LOG.error(" exception occured in getMemberShipCardPrintStatusDetails()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}

	public ZebraPrintDetailsVO getMemberShipCardDataPushDetails(Map<Long,String> parliamentForAssemblyMap,List<Long> selectedLocationIds, Date fromDate,Date toDate,String queryStrForPushData)
	{							
		ZebraPrintDetailsVO returnVO = new ZebraPrintDetailsVO();
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<ZebraPrintDetailsVO> printStatusList = new ArrayList<ZebraPrintDetailsVO>();
			
			List<Object[]> printStatusDetailsList = zebraPrintDetailsDAO.getMemberShipCardPushDataStatusByDate(selectedLocationIds, fromDate, toDate,queryStrForPushData.toString());
			Map<Long,ZebraPrintDetailsVO> zebraPrintMap = new HashMap<Long, ZebraPrintDetailsVO>(0);
			Map<String,ZebraPrintDetailsVO>  daywiseVOMap = new TreeMap<String, ZebraPrintDetailsVO>();					
			if(printStatusDetailsList != null && printStatusDetailsList.size()>0)
			{
				for (Object[] printStatus : printStatusDetailsList) 
				{
					ZebraPrintDetailsVO locationVO = null;
					
					if(zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L) != null)
					{
						locationVO = zebraPrintMap.get(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
						printStatusList = locationVO.getZebraPrintDetailsVOList();
					}
					else
					{
						locationVO = new ZebraPrintDetailsVO();
						locationVO.setId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
						locationVO.setName(printStatus[1] != null ? printStatus[1].toString().trim():"");
						locationVO.setDistrict(printStatus[2] != null ? printStatus[2].toString().trim():"");
						locationVO.setParliament(parliamentForAssemblyMap.get(locationVO.getId()) != null ? parliamentForAssemblyMap.get(locationVO.getId()):"");
						locationVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
						printStatusList = new ArrayList<ZebraPrintDetailsVO>();
					}
					
					ZebraPrintDetailsVO reportVO = new ZebraPrintDetailsVO();
					
					reportVO.setDataPushDate(printStatus[3] != null ? format.format(dateFormat.parse(printStatus[3] != null ? printStatus[3].toString().trim():"")):null);
					reportVO.setTotalPushCount(printStatus[4] != null ? Long.valueOf(printStatus[4].toString().trim()):0L);
					
											
					printStatusList.add(reportVO);
					
					locationVO.setZebraPrintDetailsVOList(printStatusList);
					
					zebraPrintMap.put(locationVO.getId(), locationVO);
					
					if(reportVO.getDataPushDate() != null)
					{
						daywiseVOMap.put(reportVO.getDataPushDate(), null);
					}
				}
			}
			
			List<ZebraPrintDetailsVO> finalReturnList = new ArrayList<ZebraPrintDetailsVO>();
			if(zebraPrintMap != null && zebraPrintMap.size()>0)
			{
				for (Long constituencyId : zebraPrintMap.keySet()) 
				{
					ZebraPrintDetailsVO constituencyVO = zebraPrintMap.get(constituencyId);
					printStatusList = new ArrayList<ZebraPrintDetailsVO>();
					Map<String,ZebraPrintDetailsVO>  daywiseCountMap = new TreeMap<String, ZebraPrintDetailsVO>();									
					if(constituencyVO.getZebraPrintDetailsVOList() != null && constituencyVO.getZebraPrintDetailsVOList().size()>0)
					{
						Long totalCount = 0L;
						
						if(daywiseVOMap != null && daywiseVOMap.size()>0)
						{
							for (String date : daywiseVOMap.keySet()) 
							{
								ZebraPrintDetailsVO updatedVO = null;
								ZebraPrintDetailsVO filterVO  = getMatchedZebraPrintDetailsVOByPushDate(constituencyVO.getZebraPrintDetailsVOList(),date);
								
								if(filterVO != null)
								{
									totalCount = totalCount + filterVO.getTotalPushCount();
									daywiseCountMap.put(date.trim(), filterVO);
								}									
								else
								{
									updatedVO = new ZebraPrintDetailsVO();
									updatedVO.setDataPushDate(date);
									updatedVO.setTotalPushCount(0L);											
									daywiseCountMap.put(date.trim(), updatedVO);
								}
							
							}
						}
						
						if(daywiseCountMap != null && daywiseCountMap.size()>0)
						{
							for (String updatedDate : daywiseCountMap.keySet()) 
							{
								printStatusList.add(daywiseCountMap.get(updatedDate));
							}
						}
						
						constituencyVO.setTotalPushCount(totalCount);
						constituencyVO.setZebraPrintDetailsVOList(printStatusList);
					}
					
					

					finalReturnList.add(constituencyVO);
				}
			}
			
			System.out.println(finalReturnList.size());
			if(daywiseVOMap != null && daywiseVOMap.size()>0)
			{
				returnVO.getDatesList().addAll(daywiseVOMap.keySet());
			}
			returnVO.setZebraPrintDetailsVOList(finalReturnList);
			
		}
		catch (Exception e)
		{
			LOG.error(" exception occured in getMemberShipCardDataPushDetails()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}

	public ZebraPrintDetailsVO getMatchedZebraPrintDetailsVOByDate(List<ZebraPrintDetailsVO> reportVOList,String dateStr)
	{
		ZebraPrintDetailsVO returnVo = null;
		try {
			
			if(reportVOList != null && reportVOList.size()>0)
			{
				for (ZebraPrintDetailsVO reportVO : reportVOList) 
				{
					if(reportVO.getUpdatedDate() != null)
					{
						if(reportVO.getUpdatedDate().trim().equalsIgnoreCase(dateStr.trim()))
						{
							return reportVO;
						}
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getMatchedZebraPrintDetailsVOByDate()  @ TdpCadreReportService class.",e);
		}
		
		return returnVo;
	}
	
	
	public ZebraPrintDetailsVO getMatchedZebraPrintDetailsVOByPushDate(List<ZebraPrintDetailsVO> reportVOList,String dateStr)
	{
		ZebraPrintDetailsVO returnVo = null;
		try {
			
			if(reportVOList != null && reportVOList.size()>0)
			{
				for (ZebraPrintDetailsVO reportVO : reportVOList) 
				{
					if(reportVO.getDataPushDate() != null)
					{
						if(reportVO.getDataPushDate().trim().equalsIgnoreCase(dateStr.trim()))
						{
							return reportVO;
						}
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getMatchedZebraPrintDetailsVOByDate()  @ TdpCadreReportService class.",e);
		}
		
		return returnVo;
	}
	
	
	public String updatePrintingStatusInTdpCadreTable()
	{
		String status = "";
		Long count = 0L;
		try {
			
			List<Object[]> printedCadreList = zebraPrintDetailsDAO.getPrintedCardsDetails();
			
			if(printedCadreList != null && printedCadreList.size()>0)
			{
				for (Object[] cadre : printedCadreList) 
				{
					if(cadre[0] != null)
					{
						TdpCadre tdpCadre = tdpCadreDAO.get(Long.valueOf(cadre[0].toString().trim()));
						tdpCadre.setCardNumber(cadre[1] != null ? cadre[1].toString().trim():null);
						tdpCadre.setDispatchStatus("dispatched");
						
						tdpCadreDAO.save(tdpCadre);
						
						count = count+1;
					}
				}
				status = ""+count+" records are Successfully updated.";
			}
			else
			{
				status = "no records are updated";
			}
			
		} catch (Exception e) {
			if(count != 0)
			{
				status = ""+count+" records are Successfully updated.";
				status = status + "But some records are not updated. ";
			}
			
			LOG.error(" exception occured in updatePrintingStatusInTdpCadreTable()  @ TdpCadreReportService class.",e);
		}
		
		return status;
	}
	
	public ZebraPrintDetailsVO createDashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId,String searchType, Long selectedLocationId,String statusType)
	{
		ZebraPrintDetailsVO returnVO = new ZebraPrintDetailsVO();
		try {
			List<ZebraPrintDetailsVO> locationWiseInfoList = null;
			List<Long> selectedLocationIds = new ArrayList<Long>();
			List<Object[]> constituencyList =null;
			
			
			if(selectedLocationId != 0L)
			{
				selectedLocationIds.add(selectedLocationId);
			}
			else
			{
				if(accessType.equals(IConstants.STATE))
				{
					if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						constituencyList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(stateTypeId, 1L, null);
					}
					else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
					{
						constituencyList = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L, stateTypeId);
					}
					else if(searchType.equalsIgnoreCase(IConstants.MP))
					{
						constituencyList = delimitationConstituencyAssemblyDetailsDAO.getPcListByRegion(stateTypeId);
					}
				}
				else if(accessType.equalsIgnoreCase(IConstants.DISTRICT))
				{
					if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						constituencyList = constituencyDAO.getConstituenciesByDistrictId(Long.valueOf(accessValue));
					}
					else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
					{
						constituencyList = districtDAO.getDistrictDetailsById(Long.valueOf(accessValue));
					}
					else if(searchType.equalsIgnoreCase(IConstants.MP))
					{
						constituencyList = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(Long.valueOf(accessValue),2009L);
					}
				}
				else if(accessType.equalsIgnoreCase(IConstants.MP))
				{
					if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						constituencyList = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(Long.valueOf(accessValue));
					}				
					else if(searchType.equalsIgnoreCase(IConstants.MP))
					{
						constituencyList =  delimitationConstituencyAssemblyDetailsDAO.findParliamentDetailsByParliamentId(Long.valueOf(accessValue));
					}
				}
				else if(accessType.equalsIgnoreCase(IConstants.MLA))
				{
					selectedLocationIds.add(Long.valueOf(accessValue));
				}
				
				if(!accessType.equalsIgnoreCase(IConstants.MLA))
				{
					if(constituencyList != null && constituencyList.size()>0)
					{
						for (Object[] cosntituency : constituencyList)
						{
							selectedLocationIds.add(cosntituency[0] != null? Long.valueOf(cosntituency[0].toString().trim()):0L);
						}     
					}
				}
			}
			
			List<Object[]> printedCountList 	= null;
			List<Object[]> errorCountList		= null;
			List<Object[]> totalPushedCountList = null;
			List<Object[]>  registeredCountList = null;
			List<Long> statusSeacrhLoctaionIds = new ArrayList<Long>();
			List<Object[]> searchList = null;
			if(!statusType.equalsIgnoreCase(""))
			{
				if(statusType.equalsIgnoreCase("PRINTED"))
				{
					if(!searchType.equalsIgnoreCase(IConstants.MP))
					searchList = zebraPrintDetailsDAO.getPrintedCountDetailsByStatusTypeSeacrh(selectedLocationIds,searchType,"printStatus");
					else
						searchList = zebraPrintDetailsDAO.getParliamentWiseResultsByStatusType(selectedLocationIds,"printStatus");	
				}
				else if(statusType.equalsIgnoreCase("ERROR"))
				{
					if(!searchType.equalsIgnoreCase(IConstants.MP))
					searchList = zebraPrintDetailsDAO.getPrintedCountDetailsByStatusTypeSeacrh(selectedLocationIds,searchType,"errorStatus");
					else
					searchList = zebraPrintDetailsDAO.getParliamentWiseResultsByStatusType(selectedLocationIds,"errorStatus");	
				}
				else if(statusType.equalsIgnoreCase("SENT"))
				{
					if(!searchType.equalsIgnoreCase(IConstants.MP))
					searchList = zebraPrintDetailsDAO.getPrintedCountDetailsByStatusTypeSeacrh(selectedLocationIds,searchType,"totalCount");
					else
					searchList = zebraPrintDetailsDAO.getParliamentWiseResultsByStatusType(selectedLocationIds,"totalCount");	
				}
				if(searchList != null && searchList.size() > 0)
				{
					for(Object[] params : searchList)
					{
						statusSeacrhLoctaionIds.add((Long)params[0]);
					}
				}
				else if(statusType.equalsIgnoreCase("PENDING"))
				{
					List<Object[]> sentList  = null;
					List<Object[]> printList = null;
					if(!searchType.equalsIgnoreCase(IConstants.MP))
					{
					 sentList = zebraPrintDetailsDAO.getPrintedCountDetailsByStatusTypeSeacrh(selectedLocationIds,searchType,"totalCount");
					 printList = zebraPrintDetailsDAO.getPrintedCountDetailsByStatusTypeSeacrh(selectedLocationIds,searchType,"printStatus");
					}
					else
					{
						 sentList = zebraPrintDetailsDAO.getParliamentWiseResultsByStatusType(selectedLocationIds,"totalCount");
						 printList = zebraPrintDetailsDAO.getParliamentWiseResultsByStatusType(selectedLocationIds,"printStatus");
					}
					Map<Long,Long> printCntMap = new HashMap<Long, Long>();
					if(printList != null && printList.size() > 0)
					{
						for(Object[] params : printList)
						{
							printCntMap.put((Long)params[0], (Long)params[1]);
						}
					}
					if(sentList != null && sentList.size() > 0)
					{
						for(Object[] params : sentList)
						{
							Long printCnt = printCntMap.get((Long)params[0]);
							if(printCnt == null)
							{
								statusSeacrhLoctaionIds.add((Long)params[0]);
							}
							else
							{
								Long sentCnt = (Long)params[1];
								 if((sentCnt - printCnt) > 0)
								 {
									 if(!statusSeacrhLoctaionIds.contains((Long)params[0]))
									 statusSeacrhLoctaionIds.add((Long)params[0]);	 
								 }
									 
							}
							
						}
					}
					
				}
				
			}
			else
			{
				statusSeacrhLoctaionIds = selectedLocationIds;
			}
			if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				 registeredCountList    = tdpCadreDAO.gettingRegisteredVotersForConstituencys(statusSeacrhLoctaionIds);
				 printedCountList 		= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"printStatus");
				 errorCountList			= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"errorStatus");
				 totalPushedCountList 	= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"totalCount");
			}
			else if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				 registeredCountList    = tdpCadreDAO.gettingRegisteredVotersForDistricts(statusSeacrhLoctaionIds);
				 printedCountList 		= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"printStatus");
				 errorCountList			= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"errorStatus");
				 totalPushedCountList 	= zebraPrintDetailsDAO.getPrintedCountByLocationWise(statusSeacrhLoctaionIds, searchType,"totalCount");
				 
			}
			else if(searchType.equalsIgnoreCase(IConstants.MP))
			{
				 if(accessType.equalsIgnoreCase("STATE"))
				 {
					 registeredCountList    = tdpCadreDAO.gettingRegisteredVotersForParliaments(statusSeacrhLoctaionIds);
					 printedCountList 		= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"printStatus");
					 errorCountList			= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"errorStatus");
					 totalPushedCountList 	= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"totalCount");
				 }
				 else
				 {
					 registeredCountList    = tdpCadreDAO.gettingRegisteredVotersForParliaments(statusSeacrhLoctaionIds);
					 printedCountList 		= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"printStatus");
					 errorCountList			= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"errorStatus");
					 totalPushedCountList 	= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"totalCount");
				 }
			}
			
			
			
			Map<Long,ZebraPrintDetailsVO> locationWiseMap = new LinkedHashMap<Long, ZebraPrintDetailsVO>(0);
			Long totalRegisteredCount = 0L;
			Long totalPrintCount = 0L;
			Long totalErrorCount = 0L;
			Long totalPushCount = 0L;
			
			if(registeredCountList != null && registeredCountList.size()>0)
			{
				for (Object[] tdpCadre : registeredCountList) 
				{
					Long constiteuncyId  = tdpCadre[2] != null ? Long.valueOf(tdpCadre[2].toString().trim()):0L;
					ZebraPrintDetailsVO zebraPrintDetailsVO = null;
					if(locationWiseMap.get(constiteuncyId) != null)
					{
						zebraPrintDetailsVO = locationWiseMap.get(constiteuncyId);
					}
					else
					{
						zebraPrintDetailsVO = new ZebraPrintDetailsVO();
					}
					zebraPrintDetailsVO.setId(tdpCadre[2] != null ? Long.valueOf(tdpCadre[2].toString().trim()):0L);
					zebraPrintDetailsVO.setName(tdpCadre[1] != null ? tdpCadre[1].toString().trim():"");
					
					zebraPrintDetailsVO.setPrintStatusCount(0L);
					zebraPrintDetailsVO.setErrorStatusCount(0L);
					zebraPrintDetailsVO.setRemainingCount(0L);
					zebraPrintDetailsVO.setTotalPushCount(0L);
					
					zebraPrintDetailsVO.setRowCount(tdpCadre[0] != null ? Long.valueOf(tdpCadre[0].toString().trim()):0L);
					locationWiseMap.put(constiteuncyId, zebraPrintDetailsVO);
					
					totalRegisteredCount = totalRegisteredCount + zebraPrintDetailsVO.getRowCount();
				}
			}
			

			if(totalPushedCountList != null && totalPushedCountList.size()>0)
			{
				for (Object[] zebraPrint3 : totalPushedCountList) 
				{
					Long constiteuncyId  = zebraPrint3[0] != null ? Long.valueOf(zebraPrint3[0].toString().trim()):0L;
					ZebraPrintDetailsVO zebraPrintDetailsVO = null;
					if(locationWiseMap.get(constiteuncyId) != null)
					{
						zebraPrintDetailsVO = locationWiseMap.get(constiteuncyId);
					}
					else
					{
						zebraPrintDetailsVO = new ZebraPrintDetailsVO();
					}
					
					zebraPrintDetailsVO.setTotalPushCount(zebraPrint3[2] != null ? Long.valueOf(zebraPrint3[2].toString().trim()):0L);
					locationWiseMap.put(constiteuncyId, zebraPrintDetailsVO);
					
					totalPushCount = totalPushCount + zebraPrintDetailsVO.getTotalPushCount();
				}
			}
			
			if(printedCountList != null && printedCountList.size()>0)
			{
				for (Object[] zebraPrint1 : printedCountList) 
				{
					Long constiteuncyId  = zebraPrint1[0] != null ? Long.valueOf(zebraPrint1[0].toString().trim()):0L;
					ZebraPrintDetailsVO zebraPrintDetailsVO = null;
					if(locationWiseMap.get(constiteuncyId) != null)
					{
						zebraPrintDetailsVO = locationWiseMap.get(constiteuncyId);
					}
					else
					{
						zebraPrintDetailsVO = new ZebraPrintDetailsVO();
					}
					
					zebraPrintDetailsVO.setPrintStatusCount(zebraPrint1[2] != null ? Long.valueOf(zebraPrint1[2].toString().trim()):0L);
					locationWiseMap.put(constiteuncyId, zebraPrintDetailsVO);
					
					totalPrintCount = totalPrintCount + zebraPrintDetailsVO.getPrintStatusCount();
				}
			}
			
			if(errorCountList != null && errorCountList.size()>0)
			{
				for (Object[] zebraPrint2 : errorCountList) 
				{
					Long constiteuncyId  = zebraPrint2[0] != null ? Long.valueOf(zebraPrint2[0].toString().trim()):0L;
					ZebraPrintDetailsVO zebraPrintDetailsVO = null;
					if(locationWiseMap.get(constiteuncyId) != null)
					{
						zebraPrintDetailsVO = locationWiseMap.get(constiteuncyId);
					}
					else
					{
						zebraPrintDetailsVO = new ZebraPrintDetailsVO();
					}
					
					zebraPrintDetailsVO.setErrorStatusCount(zebraPrint2[2] != null ? Long.valueOf(zebraPrint2[2].toString().trim()):0L);
					locationWiseMap.put(constiteuncyId, zebraPrintDetailsVO);
					
					totalErrorCount = totalErrorCount + zebraPrintDetailsVO.getErrorStatusCount();
				}
			}
			DecimalFormat decimalPlaces = new DecimalFormat("##.##");
			if(locationWiseMap != null && locationWiseMap.size()>0)
			{
				locationWiseInfoList = new ArrayList<ZebraPrintDetailsVO>(0);
				for (Long constiteuncyId : locationWiseMap.keySet()) 
				{
					ZebraPrintDetailsVO zebraPrintDetailsVO = locationWiseMap.get(constiteuncyId);
					Long printCount = zebraPrintDetailsVO.getPrintStatusCount() != null ? zebraPrintDetailsVO.getPrintStatusCount() :0L;
					Long errorCount = zebraPrintDetailsVO.getErrorStatusCount() != null ? zebraPrintDetailsVO.getErrorStatusCount() :0L;
					if(zebraPrintDetailsVO.getTotalPushCount() != null)
					{
						
						Long remainingCount =zebraPrintDetailsVO.getTotalPushCount() - printCount;
						zebraPrintDetailsVO.setRemainingCount(remainingCount);
						
						if(printCount != 0L)
						{
							Double printPerc  = (printCount * 100.00 )/zebraPrintDetailsVO.getTotalPushCount();
							zebraPrintDetailsVO.setPrintPerc(Double.valueOf(decimalPlaces.format(printPerc)));
						}
						else
						{
							zebraPrintDetailsVO.setPrintPerc(0.00);
						}
						
						if(errorCount != 0L)
						{
							Double errorPerc  = (errorCount * 100.00 )/zebraPrintDetailsVO.getTotalPushCount();
							zebraPrintDetailsVO.setErroPerc(Double.valueOf(decimalPlaces.format(errorPerc)));
						}
						else
						{
							zebraPrintDetailsVO.setErroPerc(0.00);
						}
						
						if(remainingCount != 0L)
						{
							Double remainingPerc  = (remainingCount * 100.00 )/zebraPrintDetailsVO.getTotalPushCount();
							zebraPrintDetailsVO.setPendingPerc(Double.valueOf(decimalPlaces.format(remainingPerc)));
						}
						else
						{
							zebraPrintDetailsVO.setPendingPerc(0.00);
						}
					}
						
					
					
					locationWiseInfoList.add(zebraPrintDetailsVO);
				}
			}
			
			returnVO.setRowCount(totalRegisteredCount);
			returnVO.setPrintStatusCount(totalPrintCount);
			returnVO.setErrorStatusCount(totalErrorCount);
			returnVO.setTotalPushCount(totalPushCount);
			returnVO.setZebraPrintDetailsVOList(locationWiseInfoList);
			if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
			{
				Double registeredPerc = 0.00;
				if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
				{
					if(stateTypeId == 1)
					registeredPerc  = (returnVO.getRowCount() * 100.00 )/IConstants.TARGET_CADRE_AP;
					else if(stateTypeId == 2)
						registeredPerc  = (returnVO.getRowCount() * 100.00 )/IConstants.TARGET_CADRE_TG;
					else if(stateTypeId == 0)
					{
						int APTSTARGET = IConstants.TARGET_CADRE_AP + IConstants.TARGET_CADRE_TG;
						registeredPerc  = (returnVO.getRowCount() * 100.00 )/APTSTARGET;
					}
				}
				returnVO.setRegisteredPerc(Double.valueOf(decimalPlaces.format(registeredPerc)));
			}
			
			if(returnVO.getTotalPushCount() != null )
			{
				
				Double sentPerc = 0.00;
				if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
				sentPerc  = (returnVO.getTotalPushCount() * 100.00 )/returnVO.getRowCount();
				returnVO.setSentPerc(Double.valueOf(decimalPlaces.format(sentPerc)));
				Long printCount = returnVO.getPrintStatusCount() != null ? returnVO.getPrintStatusCount() :0L;
				Long errorCount = returnVO.getErrorStatusCount() != null ? returnVO.getErrorStatusCount() :0L;
				Long remainingCount =returnVO.getTotalPushCount() - printCount;
				returnVO.setRemainingCount(remainingCount);
				
				if(printCount != 0L)
				{
					Double printPerc  = (printCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setPrintPerc(Double.valueOf(decimalPlaces.format(printPerc)));
				}
				else
				{
					returnVO.setPrintPerc(0.00);
				}
				
				if(errorCount != 0L)
				{
					Double errorPerc  = (errorCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setErroPerc(Double.valueOf(decimalPlaces.format(errorPerc)));
				}
				else
				{
					returnVO.setErroPerc(0.00);
				}
				
				if(remainingCount != 0L)
				{
					Double remainingPerc  = (remainingCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setPendingPerc(Double.valueOf(decimalPlaces.format(remainingPerc)));
				}
				else
				{
					returnVO.setPendingPerc(0.00);
				}
				
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured in createDashBoardForPrintingCardsDetails()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}
	
	public ZebraPrintDetailsVO dashBoardForPrintingCardsDetails(String accessType,String accessValue,Long stateTypeId)
	{
		ZebraPrintDetailsVO returnVO = new ZebraPrintDetailsVO();
		DecimalFormat decimalPlaces = new DecimalFormat("##.##");
		try {
		
			List<Long> selectedLocationIds = new ArrayList<Long>();
			StringBuilder queryStrForCount = new StringBuilder();
			List<Object[]> constituencyList =null;
			
			if(accessType.equals(IConstants.STATE))
			{
				constituencyList = constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(0L, 1L, null);
				
			}
			else if(accessType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				constituencyList = constituencyDAO.getConstituenciesByDistrictId(Long.valueOf(accessValue));
			}
			else if(accessType.equalsIgnoreCase(IConstants.MP))
			{
				constituencyList = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(Long.valueOf(accessValue));
			}
			else if(accessType.equalsIgnoreCase(IConstants.MLA))
			{
				selectedLocationIds.add(Long.valueOf(accessValue));
			}
			
			if(!accessType.equalsIgnoreCase(IConstants.MLA))
			{
				if(constituencyList != null && constituencyList.size()>0)
				{
					for (Object[] cosntituency : constituencyList)
					{
						selectedLocationIds.add(cosntituency[0] != null? Long.valueOf(cosntituency[0].toString().trim()):0L);
					}     
				}
			}
			
			queryStrForCount.append(" select count(TC.tdpCadreId) from TdpCadre TC where TC.userAddress.constituency.constituencyId in (:locationIds) and TC.isDeleted='N' and TC.enrollmentYear = 2014  ");

			Long totalRegisteredCount = tdpCadreDAO.getRegisteredVotersForConstituencys(selectedLocationIds);
			Long totalPrintCount = zebraPrintDetailsDAO.getTotalPrintStatusCount(selectedLocationIds, accessType,"printStatus");
			Long totalErrorCount = zebraPrintDetailsDAO.getTotalPrintStatusCount(selectedLocationIds, accessType,"errorStatus");
			Long totalPushCount =  zebraPrintDetailsDAO.getTotalPrintStatusCount(selectedLocationIds, accessType,"totalCount");
		
			returnVO.setRowCount(totalRegisteredCount);
			returnVO.setPrintStatusCount(totalPrintCount);
			returnVO.setErrorStatusCount(totalErrorCount);
			returnVO.setTotalPushCount(totalPushCount);
			if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
			{
				Double registeredPerc = 0.00;
				if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
				{
					if(stateTypeId == 1)
					registeredPerc  = (returnVO.getRowCount() * 100.00 )/IConstants.TARGET_CADRE_AP;
					else if(stateTypeId == 2)
						registeredPerc  = (returnVO.getRowCount() * 100.00 )/IConstants.TARGET_CADRE_TG;
					else if(stateTypeId == 0)
					{
						int APTSTARGET = IConstants.TARGET_CADRE_AP + IConstants.TARGET_CADRE_TG;
						registeredPerc  = (returnVO.getRowCount() * 100.00 )/APTSTARGET;
					}
				}
				returnVO.setRegisteredPerc(Double.valueOf(decimalPlaces.format(registeredPerc)));
			}
			
			
			if(returnVO.getTotalPushCount() != null )
			{
				Double sentPerc = 0.00;
				if(returnVO.getRowCount() != null && returnVO.getRowCount() > 0)
				sentPerc  = (returnVO.getTotalPushCount() * 100.00 )/returnVO.getRowCount();
				returnVO.setSentPerc(Double.valueOf(decimalPlaces.format(sentPerc)));
				Long printCount = returnVO.getPrintStatusCount() != null ? returnVO.getPrintStatusCount() :0L;
				Long errorCount = returnVO.getErrorStatusCount() != null ? returnVO.getErrorStatusCount() :0L;
				Long remainingCount =returnVO.getTotalPushCount() - printCount;
				returnVO.setRemainingCount(remainingCount);
				
				if(printCount != 0L)
				{
					Double printPerc  = (printCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setPrintPerc(Double.valueOf(decimalPlaces.format(printPerc)));
				}
				else
				{
					returnVO.setPrintPerc(0.00);
				}
				
				if(errorCount != 0L)
				{
					Double errorPerc  = (errorCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setErroPerc(Double.valueOf(decimalPlaces.format(errorPerc)));
				}
				else
				{
					returnVO.setErroPerc(0.00);
				}
				
				if(remainingCount != 0L)
				{
					Double remainingPerc  = (remainingCount * 100.00 )/returnVO.getTotalPushCount();
					returnVO.setPendingPerc(Double.valueOf(decimalPlaces.format(remainingPerc)));
				}
				else
				{
					returnVO.setPendingPerc(0.00);
				}
				
				
				
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured in dashBoardPageForPrintingCardsDetails()  @ TdpCadreReportService class.",e);
		}
		
		return returnVO;
	}
	
	public ZebraPrintDetailsVO getCadreDetailsByStatus(Long constituencyId,String status)
	{
		ZebraPrintDetailsVO returnVo = new ZebraPrintDetailsVO();
		try{
			
			List<Object[]> list = zebraPrintDetailsDAO.getCadreDetailsByStatus(constituencyId, status);
			if(list !=null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					ZebraPrintDetailsVO vo = new ZebraPrintDetailsVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1] != null ? params[1].toString() :"" +" " + params[2] != null ? params[2].toString() :"" );
					vo.setRelativeName(params[3] != null ? params[3].toString() : "");
					vo.setMobileNo(params[4] != null ? params[4].toString() : "");
					if(params[5] != null){
					  //vo.setMembershipNo(params[5] != null ? params[5].toString() : "");
					    if(params[5].toString().trim().length() > 8){
						  vo.setMembershipNo(params[5].toString().trim().substring(params[5].toString().trim().length()-8));
						}else{
							vo.setMembershipNo(params[5].toString());
						}
					}else{
						vo.setMembershipNo("");
					}
					vo.setPrintStatus(params[6] != null ? params[6].toString() : "");
					returnVo.getZebraPrintDetailsVOList().add(vo);
					
				}
				returnVo.setId(constituencyId);
				List<Long>constituencyIds = new ArrayList<Long>();
				constituencyIds.add(constituencyId);
					List<Object[]> list1 = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentsAndDistrictForAssembly(constituencyIds);
					if(list != null && list.size() > 0)
					{
						for(Object[] params : list1)
						{
							returnVo.setParliament(params[2] != null ? params[2].toString() : "");
							returnVo.setDistrict(params[4] != null ? params[4].toString() : "");
						}
					}
				
			}
		}
		catch(Exception e)
		{
			LOG.error(" exception occured in getCadreDetailsByStatus()  @ TdpCadreReportService class.",e);	
		}
		return returnVo;
		
	}
	
	public List<CadreRegistrationVO> getCadreDetailsInTeluguByMembershipId(String membershipId)
	{
		List<CadreRegistrationVO> returnList = null;
		try {
			List<LocalNameConstant> localNamesList = localNameConstantDAO.getAll();
			Map<String,String> areaInTeluguFontMap = new HashMap<String, String>();
			
			if(localNamesList != null && localNamesList.size()>0)
			{
				for (LocalNameConstant localNameConstant : localNamesList)
				{
					areaInTeluguFontMap.put(localNameConstant.getName().trim(), StringEscapeUtils.unescapeJava(localNameConstant.getLocalName()));
				}
			}
			
			List<ZebraPrintDetails> resultList = zebraPrintDetailsDAO.getCadreDetailsByMembershipId(membershipId);			
			if(resultList != null && resultList.size()>0)
			{
				returnList = new ArrayList<CadreRegistrationVO>();
				for (ZebraPrintDetails zebraPrintDetails : resultList)
				{
					CadreRegistrationVO registrationVO = new CadreRegistrationVO();
					
					if(zebraPrintDetails != null)
					{
						//registrationVO.setImageBase64String(zebraPrintDetails.getImage());
						
						//registrationVO.setVoterName(StringEscapeUtils.unescapeJava(zebraPrintDetails.getVoterName()));					
						//registrationVO.setConstituencyId(StringEscapeUtils.unescapeJava(zebraPrintDetails.getConsiteuncyName())+" "+"( "+areaInTeluguFontMap.get("constituency")+" )");
						registrationVO.setAddress(StringEscapeUtils.unescapeJava(zebraPrintDetails.getDistrictName())+" "+"( "+areaInTeluguFontMap.get("district")+" )");
						//registrationVO.setPreviousEnrollmentNumber(zebraPrintDetails.getMemberShipNumber());
						
						if(zebraPrintDetails.getConstituencyType().trim().equalsIgnoreCase("R"))
						{
							registrationVO.setPanchayatId(StringEscapeUtils.unescapeJava(zebraPrintDetails.getPanchayatName()+" "+"( "+areaInTeluguFontMap.get("village")+" )"));
							registrationVO.setMandalId(StringEscapeUtils.unescapeJava(zebraPrintDetails.getMandalName())+" "+"( "+areaInTeluguFontMap.get("mandal")+" )");
						}
						else if(zebraPrintDetails.getConstituencyType().trim().equalsIgnoreCase("RU"))
						{
							registrationVO.setMuncipalityId(StringEscapeUtils.unescapeJava(zebraPrintDetails.getMuncipalityName())+" "+"( "+areaInTeluguFontMap.get("municipality")+" )");
						}
						
						returnList.add(registrationVO);
					}
				}
				
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getCadreDetailsByMembershipId()  @ TdpCadreReportService class.",e);	
		}
		
		return returnList;
	}
	
	public List<ZebraPrintDetailsVO> getDayWiseCardPrintedCountInfo(String type,String status,Long Id,Long stateId)
	{
		List<ZebraPrintDetailsVO> resultList = new ArrayList<ZebraPrintDetailsVO>();
		try{
			List<Object[]> list = null;
			List<Object[]> printedList = null;
			List<Object[]> errorList = null;
			
				// printedCountList 		= zebraPrintDetailsDAO.getPrintedCountByLocationWise(selectedLocationIds, searchType,"printStatus");
				// errorCountList			= zebraPrintDetailsDAO.getPrintedCountByLocationWise(selectedLocationIds, searchType,"errorStatus");
			if(status.equalsIgnoreCase("ERROR"))
			list 	= zebraPrintDetailsDAO.getPrintedCountByLocationWise(Id, type,"errorStatus");
			else if(status.equalsIgnoreCase("SENT"))
			{
			list 	= zebraPrintDetailsDAO.getPrintedCountByLocationWise(Id, type,"totalCount");
			printedList = zebraPrintDetailsDAO.getPrintedCountByInsertedTime(Id, type,"printStatus");
			errorList = zebraPrintDetailsDAO.getPrintedCountByInsertedTime(Id, type,"errorStatus");
			}
			else if(status.equalsIgnoreCase("PRINTED"))
			list 	= zebraPrintDetailsDAO.getPrintedCountByLocationWise(Id, type,"printStatus");
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					ZebraPrintDetailsVO vo = new ZebraPrintDetailsVO();
					vo.setUpdatedDate(params[1] != null ? params[1].toString() : "");
					vo.setTotalPushCount((Long)params[0]);
					resultList.add(vo);
				}
			}
			if(printedList != null && printedList.size() > 0)
			{
				for(Object[] params : printedList)
				{
					ZebraPrintDetailsVO vo = getMatchedDate(resultList,params[1].toString());
					if(vo != null)
					{
						ZebraPrintDetailsVO updateDateVo = new ZebraPrintDetailsVO();
						updateDateVo.setUpdatedDate(params[2] != null ? params[2].toString() : "");
						updateDateVo.setPrintStatusCount((Long)params[0]);
						vo.getDataPushDetailsList().add(updateDateVo);
					}
					
				}
			}
			
			if(errorList != null && errorList.size() > 0)
			{
				for(Object[] params : errorList)
				{
					ZebraPrintDetailsVO vo = getMatchedDate(resultList,params[1].toString());
					if(vo != null)
					{
						vo.setErrorStatusCount((Long)params[0]);
					}
				}
			}
			for(ZebraPrintDetailsVO vo : resultList)
			{
				Long pendingCount=0L;
				for(ZebraPrintDetailsVO vo1 : vo.getDataPushDetailsList())
				{
					pendingCount= pendingCount+vo1.getPrintStatusCount();
				}
				vo.setRemainingCount(vo.getTotalPushCount() - pendingCount);
				
			}
			
			
		}
		catch(Exception e)
		{
			LOG.error(" exception occured in getDayWiseCardPrintedCountInfo()  @ TdpCadreReportService class.",e);	
		}
		return resultList;
		
	}
	
	
	public List<ZebraPrintDetailsVO> getDayWiseCardPrintedCountInfoForParlment(String status,Long Id,Long stateId)
	{
		List<ZebraPrintDetailsVO> resultList = new ArrayList<ZebraPrintDetailsVO>();
		try{
			List<Object[]> list = null;
			List<Object[]> printedList = null;
			List<Object[]> errorList = null;
				// printedCountList 		= zebraPrintDetailsDAO.getPrintedCountByLocationWise(selectedLocationIds, searchType,"printStatus");
				// errorCountList			= zebraPrintDetailsDAO.getPrintedCountByLocationWise(selectedLocationIds, searchType,"errorStatus");
			if(status.equalsIgnoreCase("ERROR"))
			list 	= zebraPrintDetailsDAO.getPrintedCountByParliamentise(Id,"errorStatus");
			else if(status.equalsIgnoreCase("SENT"))
			{
			list 	= zebraPrintDetailsDAO.getPrintedCountByParliamentise(Id,"totalCount");
			printedList = zebraPrintDetailsDAO.getPrintedCountByParlmentInsertedTime(Id,"printStatus");
			errorList = zebraPrintDetailsDAO.getPrintedCountByParlmentInsertedTime(Id,"errorStatus");
			}
			else if(status.equalsIgnoreCase("PRINTED"))
			list 	= zebraPrintDetailsDAO.getPrintedCountByParliamentise(Id,"printStatus");
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					ZebraPrintDetailsVO vo = new ZebraPrintDetailsVO();
					vo.setUpdatedDate(params[1] != null ? params[1].toString() : "");
					vo.setTotalPushCount((Long)params[0]);
					resultList.add(vo);
				}
			}
			if(printedList != null && printedList.size() > 0)
			{
				for(Object[] params : printedList)
				{
					ZebraPrintDetailsVO vo = getMatchedDate(resultList,params[1].toString());
					if(vo != null)
					{
						ZebraPrintDetailsVO updateDateVo = new ZebraPrintDetailsVO();
						updateDateVo.setUpdatedDate(params[2] != null ? params[2].toString() : "");
						updateDateVo.setPrintStatusCount((Long)params[0]);
						vo.getDataPushDetailsList().add(updateDateVo);
					}
					
				}
			}
			
			if(errorList != null && errorList.size() > 0)
			{
				for(Object[] params : errorList)
				{
					ZebraPrintDetailsVO vo = getMatchedDate(resultList,params[1].toString());
					if(vo != null)
					{
						vo.setErrorStatusCount((Long)params[0]);
					}
				}
			}
			
			for(ZebraPrintDetailsVO vo : resultList)
			{
				Long pendingCount=0L;
				for(ZebraPrintDetailsVO vo1 : vo.getDataPushDetailsList())
				{
					pendingCount= pendingCount+vo1.getPrintStatusCount();
				}
				vo.setRemainingCount(vo.getTotalPushCount() - pendingCount);
				
			}
		}
		catch(Exception e)
		{
			LOG.error(" exception occured in getDayWiseCardPrintedCountInfo()  @ TdpCadreReportService class.",e);	
		}
		return resultList;
		
	}
	public ZebraPrintDetailsVO getMatchedDate(List<ZebraPrintDetailsVO> list,String date)
	{
		try{
			if(list == null || list.size() == 0)
				return null;
			for(ZebraPrintDetailsVO vo :list)
			{
				if(vo.getUpdatedDate().toString().equalsIgnoreCase(date.toString()))
					return vo;
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}	
	
	public List<ZebraPrintDetailsVO> getJobCodesByLocationWise(String type,Long Id)
	{
		
		List<Object[]> jobCodes = null; 
		List<ZebraPrintDetailsVO> returnList = new ArrayList<ZebraPrintDetailsVO>();
		try{
			
			if(type.equalsIgnoreCase("Parliament"))
			jobCodes = zebraPrintDetailsDAO.getJobCodesByParliament(Id,type);
			else
			jobCodes = zebraPrintDetailsDAO.getJobCodesByLocationWise(Id,type);
			Map<String,List<String>> bachCodeMap = new HashMap<String, List<String>>();
			if(jobCodes !=null && jobCodes.size() > 0)
			{
			if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				for(Object[] params : jobCodes)
				{
					ZebraPrintDetailsVO vo =new ZebraPrintDetailsVO();
					vo.setName(params[0].toString());
					returnList.add(vo);
				}
				
			}
			else
			{
				for(Object[] params : jobCodes)
				{
					List<String> names = bachCodeMap.get(params[0].toString());
					if(names == null)
					{
						names = new ArrayList<String>();
						bachCodeMap.put(params[0].toString(), names);
					}
					if(!names.contains(params[1].toString()))
						names.add(params[1].toString());
				}
				for(String key : bachCodeMap.keySet())
				{
					ZebraPrintDetailsVO vo =new ZebraPrintDetailsVO();
					vo.setName(key);
					vo.setDatesList(bachCodeMap.get(key));
					returnList.add(vo);	
				}
				
			}
			}
			
		}
		catch(Exception e)
		{
			LOG.error(" exception occured in getJobCodesByLocationWise()  @ TdpCadreReportService class.",e);	
		}
		return returnList;
		
	}
	
	public List<CadreRegistrationVO> getMembershipCardDetailsForCallCenter(String mobileNo,String trNumber,String membership)
	{
		List<CadreRegistrationVO> returnList = null;
		try {
			
			List<LocalNameConstant> localNamesList = localNameConstantDAO.getAll();
			Map<String,String> areaInTeluguFontMap = new HashMap<String, String>();
			
			if(localNamesList != null && localNamesList.size()>0){
				for (LocalNameConstant localNameConstant : localNamesList){
					areaInTeluguFontMap.put(localNameConstant.getName().trim(), StringEscapeUtils.unescapeJava(localNameConstant.getLocalName()));
				}
			}
			
				
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select model.tdpCadreId,model.image,model.userAddress.userAddressId,model.memberShipNo,model.mobileNo,model.firstname,model.cardNumber,model.constituencyId " +
					"   from  TdpCadre model where model.isDeleted='N' and model.enrollmentYear = 2014 ");
			
			if(membership != null && !membership.isEmpty())
				queryStr.append(" and (model.memberShipNo = '"+membership+"' or model.memberShipNo = 'AP14"+membership+"'  or model.memberShipNo = 'TS14"+membership+"')");
		    if(mobileNo != null && !mobileNo.isEmpty())
				queryStr.append(" and model.mobileNo = '" +mobileNo+"'");
			if(trNumber != null && !trNumber.isEmpty())
				queryStr.append(" and model.refNo = '"+trNumber+"'");
			List<Object[]> resultList = tdpCadreDAO.getCadreDetails(queryStr.toString());
			Map<Long,String> teluguNames = new HashMap<Long, String>();
			List<Long> tdpCadreIds= new ArrayList<Long>();
			
			if(resultList != null && resultList.size()>0){			
				for(Object[] zebraPrintDetails : resultList){
					if(!tdpCadreIds.contains((Long)zebraPrintDetails[0]))
						tdpCadreIds.add((Long)zebraPrintDetails[0]);					
				}
				
				if(tdpCadreIds.size()>0){
					List<Object[]> names = tdpCadreDAO.getTeluguVoterNames(tdpCadreIds);
					if( names!= null && names.size()>0){
						for (Object[] name : names){
							teluguNames.put((Long)name[0], StringEscapeUtils.unescapeJava(name[1].toString()));
						}
					}
					else{
						List<Object[]> names1 = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreIds(tdpCadreIds);
						if( names1!= null && names1.size()>0){
							for (Object[] name1 : names1){
								teluguNames.put((Long)name1[0], StringEscapeUtils.unescapeJava(name1[1].toString()));
							}
						}
					}
				}
		
				returnList = new ArrayList<CadreRegistrationVO>();
				for (Object[] zebraPrintDetails : resultList)
				{
					CadreRegistrationVO vo = new CadreRegistrationVO();					
					if(zebraPrintDetails != null){
						vo.setAge(zebraPrintDetails[2] != null ? (Long)zebraPrintDetails[2] :0l);						
						UserAddress userAddress = userAddressDAO.get((Long)zebraPrintDetails[2]);
						if(userAddress != null){
							if(userAddress.getConstituency() != null)
								vo.setConstituencyId(userAddress.getConstituency().getLocalName()+" "+"( "+areaInTeluguFontMap.get("constituency")+" )");
							if(userAddress.getDistrict() != null)
								vo.setAddress(userAddress.getDistrict().getLocalName()+" "+"( "+areaInTeluguFontMap.get("district")+" )");
							if(userAddress.getConstituency().getAreaType().equalsIgnoreCase("RURAL")){
								if(userAddress.getPanchayat() != null)
									vo.setPanchayatId(userAddress.getPanchayat().getLocalName()+" "+"( "+areaInTeluguFontMap.get("village")+" )");
								if(userAddress.getTehsil() != null)
									vo.setMandalId(userAddress.getTehsil().getLocalName()+" "+"( "+areaInTeluguFontMap.get("mandal")+" )");
							}
							if(userAddress.getConstituency().getAreaType().equalsIgnoreCase(IConstants.AREA_TYPE_RURAL_URBAN)){	
								if(userAddress.getLocalElectionBody() != null)
									vo.setMuncipalityId(userAddress.getLocalElectionBody().getNameLocal()+" "+"( "+areaInTeluguFontMap.get("municipality")+" )");
							}
						}
						vo.setCadreId((Long)zebraPrintDetails[0]);
						vo.setVoterName(teluguNames.get((Long)zebraPrintDetails[0]));
						vo.setImageBase64String(zebraPrintDetails[1] != null ? zebraPrintDetails[1].toString(): "");
						if(zebraPrintDetails[3] != null){
							if(zebraPrintDetails[3].toString().trim().length() > 8){
								vo.setPreviousEnrollmentNumber(zebraPrintDetails[3].toString().trim().substring(zebraPrintDetails[3].toString().trim().length()-8));
							}else{
								vo.setPreviousEnrollmentNumber(zebraPrintDetails[3].toString());
							}
						}else{
							vo.setPreviousEnrollmentNumber("");
						}
						//vo.setPreviousEnrollmentNumber(zebraPrintDetails[3] != null ? zebraPrintDetails[3].toString().substring(4) : "");
						vo.setMobileNumber(zebraPrintDetails[4] != null ? zebraPrintDetails[4].toString(): "");
						vo.setNameType(zebraPrintDetails[5] != null ? zebraPrintDetails[5].toString() :"");
						if(zebraPrintDetails[6] != null ){
							if(!zebraPrintDetails[6].toString().isEmpty())
								vo.setShipAddress("Card Printing Completed");
						}
						else if(zebraPrintDetails[6] == null && zebraPrintDetails[7] == null)
							vo.setShipAddress("Card Printing Not Started");
						else if(zebraPrintDetails[6] == null && zebraPrintDetails[7] != null)
							vo.setShipAddress("Card Printing is in Progress ");
						returnList.add(vo);
					}
				}				
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getMembershipCardDetailsForCallCenter()",e);	
		}
		
		return returnList;
	}
	
	
	public ResultStatus saveCallCenterFeedbackForCardStatus(final TdpCadreLocationWiseReportVO vo)
	{
		ResultStatus rs = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
				try
				{	
					TdpCadreCallCenterFeedback tdpCadreCallCenterFeedback = null;
					Long feedbackId = tdpCadreCallCenterFeedbackDAO.getFeebackIdByTdpCadre(vo.getId());
					if(feedbackId != null){
						tdpCadreCallCenterFeedback = tdpCadreCallCenterFeedbackDAO.get(feedbackId);
						int status1 = tdpCadreCallCenterCommentDAO.updateComments(feedbackId);
					}
					
					if(feedbackId == null){
					tdpCadreCallCenterFeedback = new TdpCadreCallCenterFeedback();
					tdpCadreCallCenterFeedback.setTdpCadreId(vo.getId());
					tdpCadreCallCenterFeedback.setRemarks(vo.getRemarks());
					tdpCadreCallCenterFeedback.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					tdpCadreCallCenterFeedback.setUserName(vo.getName());			
					tdpCadreCallCenterFeedback = tdpCadreCallCenterFeedbackDAO.save(tdpCadreCallCenterFeedback);
					}
							
					if(vo.getComments().size()>0){
						for(Long comment : vo.getComments()){
							if(comment != null){
									TdpCadreCallCenterComment tdpCadreCallCenterComment = new TdpCadreCallCenterComment();
									tdpCadreCallCenterComment.setCallCenterFeedbackId(comment);
									tdpCadreCallCenterComment.setIsDelete("N");
									tdpCadreCallCenterComment.setTdpCadreCallCenterFeedbackId(tdpCadreCallCenterFeedback.getTdpCadreCallCenterFeedbackId());
									tdpCadreCallCenterComment = tdpCadreCallCenterCommentDAO.save(tdpCadreCallCenterComment);
								}
						}
					}
				rs.setResultCode(ResultCodeMapper.SUCCESS);
			}
			catch(Exception e)
			{
				rs.setResultCode(ResultCodeMapper.FAILURE);
				e.printStackTrace();
				LOG.debug(e);
			}
				return rs;
			} });
		return rs;
	}
	
	
	public List<BasicVO> getfeedbackDetails()
	{
		List<BasicVO> returnList = null;
		try 
		{			
			List<Object[]> feedbackList =  callCenterFeedbackDAO.getAllFeedbackData();
			if(feedbackList!=null && feedbackList.size()>0){
				returnList = new ArrayList<BasicVO>();
				for(Object[] feedback:feedbackList){
					BasicVO vo = new BasicVO();
					vo.setId((Long)feedback[0]);
					vo.setName(feedback[1].toString());			
					returnList.add(vo);
				}
			}	
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		return returnList;
	}

	
	public ResultStatus insertTdpCadreSmsStatusFromExcel(String filePath)
	{
		ResultStatus result = new ResultStatus();
		try{
			
			FileInputStream fileInputStream = new FileInputStream(filePath);
			HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
			HSSFSheet sheet=wb.getSheetAt(0);
			int totalRows = sheet.getLastRowNum();
			TdpCadreSmsStatusVO tdpCadreSmsStatusVO = null;
			List<TdpCadreSmsStatusVO> resultList = new ArrayList<TdpCadreSmsStatusVO>();
			
			for(int index = 1;index<=totalRows;index++)
			{
				try{
					tdpCadreSmsStatusVO = new TdpCadreSmsStatusVO();
					HSSFRow row = sheet.getRow(index);
					String message = row.getCell(0).toString();
					tdpCadreSmsStatusVO.setTrNo(message.substring(message.lastIndexOf("TR-")));
					String mobileNo = row.getCell(2).toString().replace(".", "").replace("E11", "");

					if(mobileNo.length() == 9)
					{
					mobileNo = mobileNo+0;
					}
					if(mobileNo.length() == 8)
					{
					mobileNo = mobileNo+0+0;
					}
					if(mobileNo.length() == 7)
					{
					mobileNo = mobileNo+0+0+0;
					}
					if(mobileNo.length() == 6)
					{
					mobileNo = mobileNo+0+0+0+0;
					}
					if(mobileNo.length() == 5)
					{
					mobileNo = mobileNo+0+0+0+0+0;
					}
					String mobile = mobileNo;

					tdpCadreSmsStatusVO.setMobileNo(mobileNo);
					
					tdpCadreSmsStatusVO.setStatus(row.getCell(4).toString());
					resultList.add(tdpCadreSmsStatusVO);
					
				}
				catch(Exception e)
				{
					LOG.error(e);
				}
			}
			if(resultList != null && resultList.size() > 0)
			result = saveTdpCadreSMSStatus(resultList);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in insertTdpCadreSmsStatusFromExcel Method - ",e);
			
		}
		return result;
	}
	public ResultStatus saveTdpCadreSMSStatus(final List<TdpCadreSmsStatusVO> resultList)
	{
		  ResultStatus rs = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
				try
				{	
				for(TdpCadreSmsStatusVO vo : resultList)
				{
					TdpCadreSmsStatus tdpCadreSmsStatus = new TdpCadreSmsStatus();
					tdpCadreSmsStatus.setMobileNo(vo.getMobileNo());
					tdpCadreSmsStatus.setSmsStatus(vo.getStatus());
					tdpCadreSmsStatus.setTrNumber(vo.getTrNo());
					tdpCadreSmsStatusDAO.save(tdpCadreSmsStatus);
					rs.setResultCode(ResultCodeMapper.SUCCESS);
				}
				}
		
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveTdpCadreSMSStatus Method - ",e);
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
				return rs;
			} });
		 List<Object[]> list = tdpCadreSmsStatusDAO.getTdpCadreIds();
		if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 tdpCadreSmsStatusDAO.updateTdpCadre((Long)params[0],(Long)params[1]);
				 }
		}
		return rs;
	}
	public List<GenericVO> getGHMCConstituencies()
	{
		List<GenericVO> resulList = new ArrayList<GenericVO>();
		try{
			List<Object[]> list = assemblyLocalElectionBodyDAO.getGHMCConstituencies();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					GenericVO vo =new GenericVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1].toString());
					resulList.add(vo);
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getGHMCConstituencies()", e);
		}
		return resulList;
	}
	public ResultStatus saveCadreRegistration(final TdpCadreVolunteerVO inputVO)
	{
		ResultStatus rs = new ResultStatus();
		try {
				if(isExistUSerByMobileAndEmail(inputVO.getMobileNo(),inputVO.getEmail()))
				{
					rs.setResultCode(ResultCodeMapper.FAILURE);
					return rs;
				}
				else
				{
					 rs = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
	
							public Object doInTransaction(TransactionStatus status)
							{
								ResultStatus rs = new ResultStatus();
								try
								{
									DateUtilService dateService = new DateUtilService();
									String[] constituencyIds = inputVO.getConstituencyId().toString().split(",");
									String[] dates = inputVO.getDate().toString().split(",");
									TdpCadreVolunteer tdpCadreVolunteer = new TdpCadreVolunteer();
									tdpCadreVolunteer.setName(inputVO.getName());
									tdpCadreVolunteer.setMobileNo(inputVO.getMobileNo());
									tdpCadreVolunteer.setEmail(inputVO.getEmail());
									tdpCadreVolunteer.setAddress(inputVO.getAddress());
									tdpCadreVolunteer.setLaptop(inputVO.getLapTop());
									tdpCadreVolunteer.setInternet(inputVO.getInternet());
									if(inputVO.getSmartPhone() != null)
									{
										if(inputVO.getSmartPhone().equalsIgnoreCase("2G"))
										tdpCadreVolunteer.setSmartPhone2G("Y");
										else
											tdpCadreVolunteer.setSmartPhone2G("N");	
										if(inputVO.getSmartPhone().equalsIgnoreCase("3G"))
										tdpCadreVolunteer.setSmartPhone3G("Y");
										else
										tdpCadreVolunteer.setSmartPhone3G("N");	
										if(inputVO.getSmartPhone().equalsIgnoreCase("no"))
										{
										tdpCadreVolunteer.setSmartPhone2G("N");
										tdpCadreVolunteer.setSmartPhone3G("N");
										}
									}
									
									if(inputVO.getTablet() != null)
									{
										if(inputVO.getTablet().equalsIgnoreCase("tab2G"))
										tdpCadreVolunteer.setTablet2G("Y");
										else
											tdpCadreVolunteer.setTablet2G("N");
										if(inputVO.getTablet().equalsIgnoreCase("tab3G") )
										tdpCadreVolunteer.setTablet3G("Y");
										else
										tdpCadreVolunteer.setTablet3G("N");
										if(inputVO.getTablet().equalsIgnoreCase("ipad2G"))
										tdpCadreVolunteer.setIpad2G("Y");
										else
								        tdpCadreVolunteer.setIpad2G("N");	
									     if(inputVO.getTablet().equalsIgnoreCase("ipad3G"))
									     tdpCadreVolunteer.setIpad3G("Y");
									     else
										tdpCadreVolunteer.setIpad3G("N");	
										if(inputVO.getTablet().equalsIgnoreCase("no") )
										{
											tdpCadreVolunteer.setIpad2G("N");
											tdpCadreVolunteer.setIpad3G("N");
											tdpCadreVolunteer.setTablet2G("N");
											tdpCadreVolunteer.setTablet3G("N");
										}
									}
						
								tdpCadreVolunteer.setIsDeleted("N");
								tdpCadreVolunteer.setInsertedTime(dateService.getCurrentDateAndTime());
								tdpCadreVolunteer.setUpdateTime(dateService.getCurrentDateAndTime());
								tdpCadreVolunteer = tdpCadreVolunteerDAO.save(tdpCadreVolunteer);
								TdpCadreVolunteerConstituency tdpCadreVolunteerConstituency = null;
								for(String constituency : constituencyIds)
								{
									tdpCadreVolunteerConstituency = new TdpCadreVolunteerConstituency();
									tdpCadreVolunteerConstituency.setConstituencyId(Long.parseLong(constituency.trim().toString()));
									tdpCadreVolunteerConstituency.setTdpCadreVolunteerId(tdpCadreVolunteer.getTdpCadreVolunteerId());
									tdpCadreVolunteerConstituencyDAO.save(tdpCadreVolunteerConstituency);
								}
								 TdpCadreVolunteerDate tdpCadreVolunteerDate = null;
								 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
								for(String date : dates)
								{
									
									tdpCadreVolunteerDate = new TdpCadreVolunteerDate();
									tdpCadreVolunteerDate.setDate(format.parse(date));
									tdpCadreVolunteerDate.setTdpCadreVolunteerId(tdpCadreVolunteer.getTdpCadreVolunteerId());
									tdpCadreVolunteerDateDAO.save(tdpCadreVolunteerDate);
								}
								rs.setResultCode(ResultCodeMapper.SUCCESS);
							}catch(Exception e){
									rs.setResultCode(ResultCodeMapper.FAILURE);
									e.printStackTrace();
									LOG.debug(e);
							}
									return rs;
					} 
					 });
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
	
	public Boolean isExistUSerByMobileAndEmail(String mobileNo, String emailId)
	{
		Boolean isExist = false;
		try {
			List<Long> existingIds = tdpCadreVolunteerDAO.checkVolunteerByMobileOrEmail(mobileNo,emailId);
			if(existingIds != null && existingIds.size()>0)
			{
				isExist = true;
			}
		} catch (Exception e) {
			LOG.error(" exception occured at isExistUSerByMobileAndEmail() in TdpCadreReportService service class. ", e);
		}
		return isExist ;
	}
	public TdpCadreVolunteerVO getConstituencyWiseVolunteerInfo(Long constituencyId, String searchType)
	{
		TdpCadreVolunteerVO returnVO = new	TdpCadreVolunteerVO();
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			
			List<Object[]> volunteerInfoList = tdpCadreVolunteerConstituencyDAO.getVolunteerInfoByLocation(constituencyId,searchType);
			Map<Long,TdpCadreVolunteerVO> volunteersMap = new HashMap<Long,TdpCadreVolunteerVO>();
			
			List<TdpCadreVolunteerVO> returnList = null;
			if(volunteerInfoList != null && volunteerInfoList.size()>0)
			{
				for (Object[] volunteer: volunteerInfoList) 
				{
					TdpCadreVolunteerVO volunteerVO = new TdpCadreVolunteerVO();					
					Long volunteerId = volunteer[0] != null ? Long.valueOf(volunteer[0].toString().trim()):0L;
					if(volunteersMap.get(volunteerId) != null)
					{
						volunteerVO = volunteersMap.get(volunteerId);
					}
					else
					{
						

						
						volunteerVO.setId(volunteerId);
						volunteerVO.setName(volunteer[1] != null ? volunteer[1].toString().trim():"");
						volunteerVO.setMobileNo(volunteer[2] != null ? volunteer[2].toString().trim():"");
						volunteerVO.setAddress(volunteer[3] != null ? volunteer[3].toString().trim():"");
						volunteerVO.setLapTop(volunteer[4] != null ? volunteer[4].toString().trim():"");
						volunteerVO.setInternet(volunteer[5] != null ? volunteer[5].toString().trim():"");
						
						if(volunteer[6] != null)
						{
							if(volunteer[6].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setTablet("TAB with 2G");
							}							
							else if(volunteer[7] != null)
							{
								if(volunteer[7].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setTablet("TAB with 3G");
								}
								else
								{
									volunteerVO.setTablet(" - ");
								}
							}
						}
								
						if(volunteer[8] != null)
						{
							if(volunteer[8].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setTablet("iPad with 2G");
							}
							else if(volunteer[9] != null)
							{
								if(volunteer[9].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setTablet("iPad with 3G");
								}
							}
						}
						
						if(volunteer[10] != null)
						{
							if(volunteer[10].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setSmartPhone("3G");
							}
							else if(volunteer[11] != null)
							{
								if(volunteer[11].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setSmartPhone("2G");
								}
								else
								{
									volunteerVO.setSmartPhone(" - ");
								}
							}
							
						}
						
						volunteerVO.setConstituencyId(volunteer[12] != null ? volunteer[12].toString().trim():"");
					}
					
					volunteersMap.put(volunteerId, volunteerVO);
				}
			}
			
			if(volunteersMap != null && volunteersMap.size()>0)
			{
				Set<Long> volunteersIds = volunteersMap.keySet();
				List<Long> valenteriesIdList = new ArrayList<Long>();
				valenteriesIdList.addAll(volunteersIds);
				
				List<Object[]> constituencyList = tdpCadreVolunteerConstituencyDAO.getconsituencyListById(valenteriesIdList);
				Map<Long, List<TdpCadreVolunteerVO>> consituencyMap = new HashMap<Long, List<TdpCadreVolunteerVO>>(0);
				
				if(constituencyList != null && constituencyList.size()>0)
				{
					for (Object[] volenteer : constituencyList) 
					{
						List<TdpCadreVolunteerVO>  valenteerConstituencyList = new ArrayList<TdpCadreVolunteerVO>();
						Long volunteerId = volenteer[0] != null ? Long.valueOf(volenteer[0].toString().trim()):0L;
						
						if(consituencyMap.get(volunteerId) != null)
						{
							valenteerConstituencyList = consituencyMap.get(volunteerId);
						}
						
						TdpCadreVolunteerVO constituencyVO = new TdpCadreVolunteerVO();
						constituencyVO.setId( volenteer[1] != null ? Long.valueOf(volenteer[1].toString().trim()):0L);
						constituencyVO.setName(volenteer[2] != null ? volenteer[2].toString().trim():"");
						valenteerConstituencyList.add(constituencyVO);
						
						consituencyMap.put(volunteerId, valenteerConstituencyList);
					}
				}
				
				List<Object[]> avaibleDatesList = tdpCadreVolunteerDateDAO.getAvailableDatesForVolunteers(valenteriesIdList);
				Map<Long, List<String>> datesListMap = new HashMap<Long, List<String>>(0);
				
				if(avaibleDatesList != null && avaibleDatesList.size()>0)
				{
					for (Object[] volenteer : avaibleDatesList) 
					{
						List<String>  datesList = new ArrayList<String>();
						Long volunteerId = volenteer[0] != null ? Long.valueOf(volenteer[0].toString().trim()):0L;
						
						if(datesListMap.get(volunteerId) != null)
						{
							datesList = datesListMap.get(volunteerId);
						}
						
						datesList.add(volenteer[1] != null ? format.format(dbFormat.parse(volenteer[1].toString().trim())):"");						
						datesListMap.put(volunteerId, datesList);
					}
				}
				
				returnList = new ArrayList<TdpCadreVolunteerVO>(0);
				for (Long volunteerId : volunteersMap.keySet()) 
				{
					TdpCadreVolunteerVO finalVO = volunteersMap.get(volunteerId);
					if(finalVO != null)
					{
						finalVO.setTdpCadreVolunteerVOList(consituencyMap.get(volunteerId));
						finalVO.setDatesList(datesListMap.get(volunteerId));
						
						returnList.add(finalVO);
					}
				}
				
				if(returnList != null && returnList.size()>0)
				{
					returnVO.setTdpCadreVolunteerVOList(returnList);
				}
				
				returnVO.setNoTab(tdpCadreVolunteerDAO.getDeviceTotalCount("All",constituencyId,searchType).toString());
				returnVO.setLapTop(tdpCadreVolunteerDAO.getDeviceTotalCount("laptop",constituencyId,searchType).toString());
				returnVO.setInternet(tdpCadreVolunteerDAO.getDeviceTotalCount("internet",constituencyId,searchType).toString());
				
				returnVO.setTablet(tdpCadreVolunteerDAO.getDeviceTotalCount("tablet2G",constituencyId,searchType).toString());
				returnVO.setTablet3G(tdpCadreVolunteerDAO.getDeviceTotalCount("tablet3G",constituencyId,searchType).toString());
				
				returnVO.setSmartPhone(tdpCadreVolunteerDAO.getDeviceTotalCount("smartPhone2G",constituencyId,searchType).toString());
				returnVO.setSmartPhone3G(tdpCadreVolunteerDAO.getDeviceTotalCount("smartPhone3G",constituencyId,searchType).toString());
				
				returnVO.setIpad(tdpCadreVolunteerDAO.getDeviceTotalCount("ipad2G",constituencyId,searchType).toString());
				returnVO.setIpad3G(tdpCadreVolunteerDAO.getDeviceTotalCount("ipad3G",constituencyId,searchType).toString());
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getConstituencyWiseVolunteerInfo() in TdpCadreReportService service class. ", e);
		}
		
		return returnVO;
	}
	public TdpCadreVolunteerVO getConstituencyWiseVolunteerInfoByDevice(String deviceType,Long constituencyId, String searchType)
	{
		TdpCadreVolunteerVO returnVO = new	TdpCadreVolunteerVO();
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			
			//List<Object[]> volunteerInfoList = tdpCadreVolunteerConstituencyDAO.getVolunteerInfoByLocation(constituencyId,searchType);
			List<Object[]> volunteerInfoList =  tdpCadreVolunteerConstituencyDAO.getDeviceInfo(deviceType,constituencyId,searchType);
			Map<Long,TdpCadreVolunteerVO> volunteersMap = new HashMap<Long,TdpCadreVolunteerVO>();
			
			List<TdpCadreVolunteerVO> returnList = null;
			if(volunteerInfoList != null && volunteerInfoList.size()>0)
			{
				for (Object[] volunteer: volunteerInfoList) 
				{
					TdpCadreVolunteerVO volunteerVO = new TdpCadreVolunteerVO();					
					Long volunteerId = volunteer[0] != null ? Long.valueOf(volunteer[0].toString().trim()):0L;
					if(volunteersMap.get(volunteerId) != null)
					{
						volunteerVO = volunteersMap.get(volunteerId);
					}
					else
					{
						volunteerVO.setId(volunteerId);
						volunteerVO.setName(volunteer[1] != null ? volunteer[1].toString().trim():"");
						volunteerVO.setMobileNo(volunteer[2] != null ? volunteer[2].toString().trim():"");
						volunteerVO.setAddress(volunteer[3] != null ? volunteer[3].toString().trim():"");
						volunteerVO.setLapTop(volunteer[4] != null ? volunteer[4].toString().trim():"");
						volunteerVO.setInternet(volunteer[5] != null ? volunteer[5].toString().trim():"");
						
						if(volunteer[6] != null)
						{
							if(volunteer[6].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setTablet("TAB with 2G");
							}							
							else if(volunteer[7] != null)
							{
								if(volunteer[7].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setTablet("TAB with 3G");
								}
								else
								{
									volunteerVO.setTablet(" - ");
								}
							}
						}
								
						if(volunteer[8] != null)
						{
							if(volunteer[8].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setTablet("iPad with 2G");
							}
							else if(volunteer[9] != null)
							{
								if(volunteer[9].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setTablet("iPad with 3G");
								}
							}
						}
						
						if(volunteer[10] != null)
						{
							if(volunteer[10].toString().trim().equalsIgnoreCase("Y"))
							{
								volunteerVO.setSmartPhone("3G");
							}
							else if(volunteer[11] != null)
							{
								if(volunteer[11].toString().trim().equalsIgnoreCase("Y"))
								{
									volunteerVO.setSmartPhone("2G");
								}
								else
								{
									volunteerVO.setSmartPhone(" - ");
								}
							}
							
						}
						
						volunteerVO.setConstituencyId(volunteer[12] != null ? volunteer[12].toString().trim():"");
					}
					
					volunteersMap.put(volunteerId, volunteerVO);
				}
			}
			
			if(volunteersMap != null && volunteersMap.size()>0)
			{
				Set<Long> volunteersIds = volunteersMap.keySet();
				List<Long> valenteriesIdList = new ArrayList<Long>();
				valenteriesIdList.addAll(volunteersIds);
				
				List<Object[]> constituencyList = tdpCadreVolunteerConstituencyDAO.getconsituencyListById(valenteriesIdList);
				Map<Long, List<TdpCadreVolunteerVO>> consituencyMap = new HashMap<Long, List<TdpCadreVolunteerVO>>(0);
				
				if(constituencyList != null && constituencyList.size()>0)
				{
					for (Object[] volenteer : constituencyList) 
					{
						List<TdpCadreVolunteerVO>  valenteerConstituencyList = new ArrayList<TdpCadreVolunteerVO>();
						Long volunteerId = volenteer[0] != null ? Long.valueOf(volenteer[0].toString().trim()):0L;
						
						if(consituencyMap.get(volunteerId) != null)
						{
							valenteerConstituencyList = consituencyMap.get(volunteerId);
						}
						
						TdpCadreVolunteerVO constituencyVO = new TdpCadreVolunteerVO();
						constituencyVO.setId( volenteer[1] != null ? Long.valueOf(volenteer[1].toString().trim()):0L);
						constituencyVO.setName(volenteer[2] != null ? volenteer[2].toString().trim():"");
						valenteerConstituencyList.add(constituencyVO);
						
						consituencyMap.put(volunteerId, valenteerConstituencyList);
					}
				}
				
				List<Object[]> avaibleDatesList = tdpCadreVolunteerDateDAO.getAvailableDatesForVolunteers(valenteriesIdList);
				Map<Long, List<String>> datesListMap = new HashMap<Long, List<String>>(0);
				
				if(avaibleDatesList != null && avaibleDatesList.size()>0)
				{
					for (Object[] volenteer : avaibleDatesList) 
					{
						List<String>  datesList = new ArrayList<String>();
						Long volunteerId = volenteer[0] != null ? Long.valueOf(volenteer[0].toString().trim()):0L;
						
						if(datesListMap.get(volunteerId) != null)
						{
							datesList = datesListMap.get(volunteerId);
						}
						
						datesList.add(volenteer[1] != null ? format.format(dbFormat.parse(volenteer[1].toString().trim())):"");						
						datesListMap.put(volunteerId, datesList);
					}
				}
				
				returnList = new ArrayList<TdpCadreVolunteerVO>(0);
				for (Long volunteerId : volunteersMap.keySet()) 
				{
					TdpCadreVolunteerVO finalVO = volunteersMap.get(volunteerId);
					if(finalVO != null)
					{
						finalVO.setTdpCadreVolunteerVOList(consituencyMap.get(volunteerId));
						finalVO.setDatesList(datesListMap.get(volunteerId));
						
						returnList.add(finalVO);
					}
				}
				
				if(returnList != null && returnList.size()>0)
				{
					returnVO.setTdpCadreVolunteerVOList(returnList);
				}
				
				/*returnVO.setNoTab(tdpCadreVolunteerDAO.getDeviceTotalCount("All",constituencyId,searchType).toString());
				returnVO.setLapTop(tdpCadreVolunteerDAO.getDeviceTotalCount("laptop",constituencyId,searchType).toString());
				returnVO.setInternet(tdpCadreVolunteerDAO.getDeviceTotalCount("internet",constituencyId,searchType).toString());
				
				returnVO.setTablet(tdpCadreVolunteerDAO.getDeviceTotalCount("tablet2G",constituencyId,searchType).toString());
				returnVO.setTablet3G(tdpCadreVolunteerDAO.getDeviceTotalCount("tablet3G",constituencyId,searchType).toString());
				
				returnVO.setSmartPhone(tdpCadreVolunteerDAO.getDeviceTotalCount("smartPhone2G",constituencyId,searchType).toString());
				returnVO.setSmartPhone3G(tdpCadreVolunteerDAO.getDeviceTotalCount("smartPhone3G",constituencyId,searchType).toString());
				
				returnVO.setIpad(tdpCadreVolunteerDAO.getDeviceTotalCount("ipad2G",constituencyId,searchType).toString());
				returnVO.setIpad3G(tdpCadreVolunteerDAO.getDeviceTotalCount("ipad3G",constituencyId,searchType).toString());*/
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured at getConstituencyWiseVolunteerInfoByDevice() in TdpCadreReportService service class. ", e);
		}
		
		return returnVO;
	}
	public ResultStatus assignConstiteuncyForValeenteer(Long consituencyId, Long valeenteerId)
	{
		ResultStatus status = new ResultStatus();
		try {
			
			if((valeenteerId != null  && valeenteerId.longValue() > 0L) && (consituencyId != null && consituencyId.longValue()  > 0L))
			{
				TdpCadreVolunteer tdpCadreVolunteer = tdpCadreVolunteerDAO.get(valeenteerId);
				tdpCadreVolunteer.setAssignedConstituencyId(consituencyId);
				tdpCadreVolunteerDAO.save(tdpCadreVolunteer);
				
				status.setResultCode(0);
				status.setMessage("success");
				
			}
			else
			{
				status.setResultCode(1);
				status.setMessage("failure");
			}
			
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("failure");
			LOG.error(" exception occured at assignConstiteuncyForValeenteer() in TdpCadreReportService service class. ", e);
		}
		
		return status;
	}
	public List<String> getIvrDates()
	{
		List<String> datesArr = new ArrayList<String>();
		try{
			List<Date> dates = cadreIvrResponseDAO.getDates();
			if(dates != null && dates.size() > 0)
		 for(Date str:dates)
			 datesArr.add(str.toString());
		}
		catch(Exception e)
		{
			LOG.error(" exception occured at getDates() in TdpCadreReportService service class. ", e);	
		}
		return datesArr;
	}
	public CadreIVRVO getCadreIvrCount(String date,Long Id)
	{
		CadreIVRVO returnVo = new CadreIVRVO();
		Date convertedDate = null;
		
		try{
			if(!date.equalsIgnoreCase(""))
			{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			convertedDate = sdf.parse(date);
			Id = null;
			}
			else
			{
				convertedDate = null;	
			}
			
			Long totalCnt = cadreIvrResponseDAO.getIvrStatusCount(convertedDate,Id,"total");
			Long receivedCnt = cadreIvrResponseDAO.getIvrStatusCount(convertedDate,Id,IConstants.Received);
			Long notReceived = cadreIvrResponseDAO.getIvrStatusCount(convertedDate,Id,IConstants.NotReceived);
			Long notRegistred = cadreIvrResponseDAO.getIvrStatusCount(convertedDate,Id,IConstants.NotRegistered);
			
			returnVo.setTotal(totalCnt != null ? totalCnt : 0l);
			returnVo.setReceived(receivedCnt != null ? receivedCnt : 0l);
			returnVo.setNotReceived(notReceived != null ? notReceived : 0l);
			returnVo.setNotRegistered(notRegistred != null ? notRegistred : 0l);
			returnVo.setAnsweredCnt(returnVo.getReceived() + returnVo.getNotReceived() + returnVo.getNotRegistered());
			
		}
		catch(Exception e)
		{
			LOG.error(" exception occured at getCadreIvrCount() in TdpCadreReportService service class. ", e);	
		}
		return returnVo;
	}
	public CadreIVRVO getCadreIvrReport(String date,Long Id,Integer startIndex,Integer maxIndex,String searchType)
	{
		CadreIVRVO returnVo = new CadreIVRVO();
		Date convertedDate = null;
		List<CadreIVRVO> resultList = new ArrayList<CadreIVRVO>();
		List<Object[]> list = null;
		try{
			if(!date.equalsIgnoreCase(""))
			{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			convertedDate = sdf.parse(date);
			Id = null;
			}
			else
			{
				convertedDate = null;	
			}
			List<Object[]> list1 = null;
			list = cadreIvrResponseDAO.getIvrCadreDetails(convertedDate,Id,searchType,startIndex,maxIndex);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CadreIVRVO cadreIVRVO = new CadreIVRVO();
					cadreIVRVO.setName(params[0].toString());
				
					cadreIVRVO.setMobileNo(params[1].toString());
					cadreIVRVO.setCurrentStatus(params[2] != null ? params[2].toString() : "");
					cadreIVRVO.setConstituencyName(params[3] != null ? params[3].toString() : "");
					cadreIVRVO.setLocationName(params[4] != null ? params[4].toString() : "");
					cadreIVRVO.setPanchayatName(params[5] != null ? params[5].toString() : "");
					cadreIVRVO.setLocalbodyName(params[6] != null ? params[6].toString()+" Muncipality" : "");
					resultList.add(cadreIVRVO);
				}
			}
			returnVo.setSubList(resultList); 
		}
		catch(Exception e)
		{
			LOG.error(" exception occured at getCadreIvrCount() in TdpCadreReportService service class. ", e);	
		}
		return returnVo;
	}
	/*public CadreIVRVO getIvrDashBoardBasicInfo(){
		CadreIVRVO returnVo  = new CadreIVRVO();
		try{
		DateUtilService date = new DateUtilService();
	    Calendar calender = Calendar.getInstance();
	    calender.setTime(date.getCurrentDateAndTime());
	    calender.set(Calendar.DAY_OF_MONTH,  calender.get(Calendar.DAY_OF_MONTH)-7);
	    
		CadreRegisterInfo info =  cadreDashBoardService.getTotalRegisterCadreInfo();
		returnVo.setApCount(info.getApCount());
		returnVo.setTgCount(info.getTgCount());
		
		List<Object[]> ivrCompleted = cadreIvrResponseDAO.getTotalIvrCount();
		getPrintingInfo(ivrCompleted,"ivrComplete",returnVo);
		
		List<Object[]> printingCompletedCnt = zebraPrintDetailsDAO.getPrintingCompletedCount();
		getPrintingInfo(printingCompletedCnt,"printComplete",returnVo);
		
		List<Object[]> IvrReadyCount = zebraPrintDetailsDAO.getIvrReadyCount(calender.getTime()) ;
		getPrintingInfo(IvrReadyCount,"ivrReady",returnVo);
		 // IVR Ready count calculation
		returnVo.setIvrReady(returnVo.getIvrReady() - returnVo.getTotal());
		returnVo.setTgivrReady(returnVo.getTgivrReady()  - returnVo.getTgtotal());
		 
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in getIvrDashBoardBasicInfo", e);
		}
		return returnVo;
		 
	}
	public void getPrintingInfo(List<Object[]>list,String type,CadreIVRVO returnVo){
	
		Long tgCount = 0l;
		Long apCount = 0l;
		try{
			if(list != null && list.size() > 0)
			for(Object[] districtCount:list){
				if(((Long)districtCount[1]).longValue() > 10l){
					apCount = apCount+(Long)districtCount[0];
				}else{
					tgCount = tgCount+(Long)districtCount[0];
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getPrintingInfo", e);
		}
		if(type.equalsIgnoreCase("ivrComplete"))
		{
			returnVo.setTotal(apCount);
			returnVo.setTgtotal(tgCount);
		}
		else if(type.equalsIgnoreCase("printComplete"))
		{
			returnVo.setPrintingCompleted(apCount);
			returnVo.setTgprintingCompleted(tgCount);	
		}
		else if(type.equalsIgnoreCase("ivrReady"))
		{
			returnVo.setIvrReady(apCount);
			returnVo.setTgivrReady(tgCount);	
		}
		
		
	}*/
	
	public CadreIVRVO getIvrDashBoardBasicInfo(String state,String accessType,Long accessValue){
		CadreIVRVO returnVo  = new CadreIVRVO();
		try{
			List<Long> accessLocationIds = new ArrayList<Long>();
		DateUtilService date = new DateUtilService();
	    Calendar calender = Calendar.getInstance();
	    calender.setTime(date.getCurrentDateAndTime());
	    calender.set(Calendar.DAY_OF_MONTH,  calender.get(Calendar.DAY_OF_MONTH)-7);
	    getAccessLocationValues(accessLocationIds,accessType,accessValue);
	    Long count =  getTotalRegisterCadreInfoByState(state,accessLocationIds);
		returnVo.setCount(count != null ? count : 0l);
		
		Long ivrCompleted = cadreIvrResponseDAO.getTotalIvrCount(state,accessLocationIds);
		returnVo.setTotal(ivrCompleted != null ? ivrCompleted : 0l);
		
		Long totalPushCnt = zebraPrintDetailsDAO.getPrintingCompletedCount(state,"totalCount",accessLocationIds);
		Long printingCompletedCnt = zebraPrintDetailsDAO.getPrintingCompletedCount(state,"printStatus",accessLocationIds);
		returnVo.setPrintingCompleted(printingCompletedCnt != null ? printingCompletedCnt :0l);
		Long errorCnt = zebraPrintDetailsDAO.getPrintingCompletedCount(state,"errorStatus",accessLocationIds);
		returnVo.setTotalError(errorCnt != null ? errorCnt : 0l);
		returnVo.setTgCount(totalPushCnt != null ? totalPushCnt : 0l);
		Long IvrReadyCount = zebraPrintDetailsDAO.getIvrReadyCount(calender.getTime(),state,accessLocationIds) ;
		returnVo.setIvrReady(IvrReadyCount != null ? IvrReadyCount : 0l);
		 // IVR Ready count calculation
		returnVo.setIvrReady(returnVo.getIvrReady() - returnVo.getTotal());
		 
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in getIvrDashBoardBasicInfo", e);
		}
		return returnVo;
		 
	}

		
	

	
	public List<CadreIVRVO> getConstituencyWiseIVR()
	{
		List<CadreIVRVO> returnList = new ArrayList<CadreIVRVO>();
		Map<Long,Map<Long,CadreIVRVO>> districtMap = new HashMap<Long, Map<Long,CadreIVRVO>>();
		Map<Long,String> districtName = new HashMap<Long, String>();
		List<Long> constituencyIds = new ArrayList<Long>();
		List<Long> districtIds = new ArrayList<Long>();
		try{
			List<Object[]> list = cadreIvrResponseDAO.getConstituencyWiseIvrCount();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
					{
						Map<Long,CadreIVRVO> constituencyMap = districtMap.get((Long)params[3]);
						if(constituencyMap == null)
						{
							constituencyMap = new HashMap<Long, CadreIVRVO>();
							districtMap.put((Long)params[3], constituencyMap);
							districtName.put((Long)params[3], params[4].toString());
							districtIds.add((Long)params[3]);
						}
						CadreIVRVO vo = constituencyMap.get((Long)params[1]);
							if(vo == null)
							{
								vo = new CadreIVRVO();
								vo.setId((Long)params[1]);
								vo.setName(params[2].toString());
								constituencyMap.put((Long)params[1], vo);
								constituencyIds.add((Long)params[1]);
							}
						vo.setTotal((Long)params[0] + vo.getTotal());
						if(params[5] != null &&params[5].toString().equalsIgnoreCase("1"))
						vo.setReceived((Long)params[0] + vo.getReceived());
						else if(params[5] != null &&params[5].toString().equalsIgnoreCase("2"))
						vo.setNotReceived((Long)params[0] + vo.getNotReceived());
						else if(params[5] != null &&params[5].toString().equalsIgnoreCase("3"))
						vo.setNotRegistered((Long)params[0] + vo.getNotRegistered());
						
					}
			}
			Map<Long,Long> districtRegCntMap = new HashMap<Long, Long>();
			Map<Long,Long> constiRegCntMap = new HashMap<Long, Long>();
			if(districtIds != null &&  districtIds.size() > 0)
			{
				 List<Object[]> distCnt = cadreRegistrationInfoDAO.getCountByReportLevel(3l);
				 if(distCnt != null && distCnt.size() > 0)
				 {
					 for(Object[] params : distCnt)
						 districtRegCntMap.put((Long)params[0], params[1] != null ? (Long)params[1] : 0) ;
				 }
			}
			if(constituencyIds != null &&  constituencyIds.size() > 0)
			{
				 List<Object[]> constCnt = cadreRegistrationInfoDAO.getCountByReportLevel(4l);
				 if(constCnt != null && constCnt.size() > 0)
				 {
					 for(Object[] params : constCnt)
						 constiRegCntMap.put((Long)params[0], params[1] != null ? (Long)params[1] : 0) ;
				 }
			}
			if(districtMap != null && !districtMap.isEmpty())
			for(Long districtId : districtMap.keySet())
			{
				CadreIVRVO districtVo = new CadreIVRVO();
				districtVo.setId(districtId);
				districtVo.setName(districtName.get(districtId));
				districtVo.setCount(districtRegCntMap.get(districtId));
				Map<Long,CadreIVRVO> constituencyMap = districtMap.get(districtId);
				List<CadreIVRVO> constituencyList = new ArrayList<CadreIVRVO>();
				if(constituencyMap != null && !constituencyMap.isEmpty())
					for(Long id : constituencyMap.keySet())
					{
						CadreIVRVO vo = constituencyMap.get(id);
						
						vo.setAnsweredCnt(vo.getReceived() + vo.getNotRegistered() + vo.getNotReceived());
						vo.setCount(constiRegCntMap.get(id));
						if(vo.getAnsweredCnt() > 0)
						{
							vo.setReceivedPerc(new BigDecimal((vo.getReceived()*100/vo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							vo.setNotReceivedPerc(new BigDecimal((vo.getNotReceived()*100/vo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							vo.setNotMemberPerc(new BigDecimal((vo.getNotRegistered()*100/vo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						if(vo.getTotal() > 0)
						vo.setAnsweredPerc(new BigDecimal((vo.getAnsweredCnt()*100/vo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVo.setTotal(districtVo.getTotal() + vo.getTotal());
						districtVo.setReceived(districtVo.getReceived() + vo.getReceived());
						districtVo.setNotReceived(districtVo.getNotReceived() + vo.getNotReceived());
						districtVo.setNotRegistered(districtVo.getNotRegistered() + vo.getNotRegistered());
						constituencyList.add(vo);
					}
				
				
					districtVo.setSubList(constituencyList);
					districtVo.setAnsweredCnt(districtVo.getReceived() + districtVo.getNotRegistered() + districtVo.getNotReceived());
					if(districtVo.getAnsweredCnt() > 0)
					{
						districtVo.setReceivedPerc(new BigDecimal((districtVo.getReceived()*100/districtVo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVo.setNotReceivedPerc(new BigDecimal((districtVo.getNotReceived()*100/districtVo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVo.setNotMemberPerc(new BigDecimal((districtVo.getNotRegistered()*100/districtVo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					if(districtVo.getTotal() > 0)
					districtVo.setAnsweredPerc(new BigDecimal((districtVo.getAnsweredCnt()*100/districtVo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				returnList.add(districtVo);
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception rised in getConstituencyWiseIVR", e);		
		}
		
		return returnList;
	}

		
	public CadreIVRResponseVO getTehsilWiseCadreDispatchStatus(Long range){
		
		CadreIVRResponseVO responseVo = new CadreIVRResponseVO();
		try{
			//0 districtId,1constituencyId,2constituencyName,3tehsilId,4tehsilName,5count,6response key,7districtName
			List<Object[]> tehsilWiseOptionsCountList = cadreIvrResponseDAO.getTehsilWiseIVRInfo();
			
			//0 id,1count
			List<Object[]> tehsilWiseCountList = cadreIvrResponseDAO.getTehsilWiseIVRTotalCountInfo();
			
			//0 districtId,1constituencyId,2constituencyName,localElectionBodyId,4localElectionBodyName,5count,6response key,7districtName
			List<Object[]> localBdyWiseOptionsCountList = cadreIvrResponseDAO.getLocalBodyWiseIVRInfo();
			
			//0 constituencyId,1 localBodyId,2 count
			List<Object[]> localBdyWiseCountList = cadreIvrResponseDAO.getLocalBodyWiseIVRTotalCountInfo();
			
			Map<Long,Long> tehsilWiseCountMap = new HashMap<Long,Long>();//Map<tehsilId,totalCount>
			Map<Long,Map<Long,Long>> localBdyWiseCountMap = new HashMap<Long,Map<Long,Long>>();//Map<Long,Map<Long,Long>>
			
			Map<Long,CadreIVRResponseVO> tehsilWiseAPOptionsCountMap = new HashMap<Long,CadreIVRResponseVO>();
			Map<Long,Map<Long,CadreIVRResponseVO>> localBdyWiseAPOptionsCountMap = new HashMap<Long,Map<Long,CadreIVRResponseVO>>();
			
			Map<Long,CadreIVRResponseVO> tehsilWiseTGOptionsCountMap = new HashMap<Long,CadreIVRResponseVO>();
			Map<Long,Map<Long,CadreIVRResponseVO>> localBdyWiseTGOptionsCountMap = new HashMap<Long,Map<Long,CadreIVRResponseVO>>();
			
			List<CadreIVRResponseVO> apList =new ArrayList<CadreIVRResponseVO>();
			List<CadreIVRResponseVO> tgList =new ArrayList<CadreIVRResponseVO>();
			
			List<CadreIVRResponseVO> apListNew =new ArrayList<CadreIVRResponseVO>();
			List<CadreIVRResponseVO> tgListNew =new ArrayList<CadreIVRResponseVO>();
			
			for(Object[] count:tehsilWiseCountList){
				tehsilWiseCountMap.put((Long)count[0], (Long)count[1]);
			}
			
			for(Object[] count:localBdyWiseCountList){
				Map<Long,Long> constituencyMap = localBdyWiseCountMap.get((Long)count[0]);
				if(constituencyMap == null){
					constituencyMap = new HashMap<Long,Long>();
					localBdyWiseCountMap.put((Long)count[0], constituencyMap);
				}
				constituencyMap.put((Long)count[1], (Long)count[2]);
			}
			
			populateTehsilData(tehsilWiseOptionsCountList,tehsilWiseAPOptionsCountMap,tehsilWiseTGOptionsCountMap,apList,tgList,tehsilWiseCountMap);
			
			populateLocalBdyData(localBdyWiseOptionsCountList,localBdyWiseAPOptionsCountMap,localBdyWiseTGOptionsCountMap,apList,tgList,localBdyWiseCountMap);
			
			calculatePerc(apList,range,apListNew);
			
			calculatePerc(tgList,range,tgListNew);
			Collections.sort(apListNew, sort);
			Collections.sort(tgListNew, sort);
			responseVo.setApList(apListNew);
			responseVo.setTgList(tgListNew);
		}catch(Exception e){
			LOG.error("Exception rised in getTehsilWiseCadreDispatchStatus() ",e);
		}
		return responseVo;
	}
	
	public void populateTehsilData(List<Object[]> tehsilWiseOptionsCountList,Map<Long,CadreIVRResponseVO> tehsilWiseAPOptionsCountMap,
			Map<Long,CadreIVRResponseVO> tehsilWiseTGOptionsCountMap,List<CadreIVRResponseVO> apList,List<CadreIVRResponseVO> tgList,
			Map<Long,Long> tehsilWiseCountMap){
		for(Object[] count:tehsilWiseOptionsCountList){
			Map<Long,CadreIVRResponseVO> reqMap = null;
			List<CadreIVRResponseVO> reqList = null;
			if(((Long)count[0]).longValue() >10){
				reqMap = tehsilWiseAPOptionsCountMap;
				reqList = apList;
			}else{
				reqMap = tehsilWiseTGOptionsCountMap;
				reqList = tgList;
			}
			CadreIVRResponseVO tehsilResponseVo = reqMap.get((Long)count[3]);
			if(tehsilResponseVo == null){
				tehsilResponseVo = new CadreIVRResponseVO();
				reqMap.put((Long)count[3],tehsilResponseVo);
				reqList.add(tehsilResponseVo);
				tehsilResponseVo.setReceived(0l);
				tehsilResponseVo.setReceivedPerc(0l);
				tehsilResponseVo.setNotReceived(0l);
				tehsilResponseVo.setNotReceivedPerc(0l);
				tehsilResponseVo.setNotMember(0l);
				tehsilResponseVo.setNotMemberPerc(0l);
				tehsilResponseVo.setName(count[4].toString());
				tehsilResponseVo.setLocationName(count[2].toString());
				tehsilResponseVo.setAreaName(count[7].toString());
				tehsilResponseVo.setTotalCalls(tehsilWiseCountMap.get((Long)count[3]));
			}
			if(count[6].toString().trim().equalsIgnoreCase("1")){
				tehsilResponseVo.setReceived((Long)count[5]);
			}else if(count[6].toString().trim().equalsIgnoreCase("2")){
				tehsilResponseVo.setNotReceived((Long)count[5]);
			}else if(count[6].toString().trim().equalsIgnoreCase("3")){
				tehsilResponseVo.setNotMember((Long)count[5]);
			}
		}
	}
	
	public void populateLocalBdyData(List<Object[]> localBdyWiseOptionsCountList,Map<Long,Map<Long,CadreIVRResponseVO>> localBdyWiseAPOptionsCountMap,
			Map<Long,Map<Long,CadreIVRResponseVO>> localBdyWiseTGOptionsCountMap,List<CadreIVRResponseVO> apList,List<CadreIVRResponseVO> tgList,
			Map<Long,Map<Long,Long>> localBdyWiseCountMap){
		for(Object[] count:localBdyWiseOptionsCountList){
			Map<Long,Map<Long,CadreIVRResponseVO>> reqMap = null;
			List<CadreIVRResponseVO> reqList = null;
			if(((Long)count[0]).longValue() >10){
				reqMap = localBdyWiseAPOptionsCountMap;
				reqList = apList;
			}else{
				reqMap = localBdyWiseTGOptionsCountMap;
				reqList = tgList;
			}
			Map<Long,CadreIVRResponseVO> localBdyResponseMap = reqMap.get((Long)count[1]);
			if(localBdyResponseMap == null){
				localBdyResponseMap = new HashMap<Long,CadreIVRResponseVO>();
				reqMap.put((Long)count[1],localBdyResponseMap);
			}
			CadreIVRResponseVO localBdyResponseVo = localBdyResponseMap.get((Long)count[3]);
			if(localBdyResponseVo == null){
				localBdyResponseVo = new CadreIVRResponseVO();
				localBdyResponseMap.put((Long)count[3],localBdyResponseVo);
				reqList.add(localBdyResponseVo);
				localBdyResponseVo.setReceived(0l);
				localBdyResponseVo.setReceivedPerc(0l);
				localBdyResponseVo.setNotReceived(0l);
				localBdyResponseVo.setNotReceivedPerc(0l);
				localBdyResponseVo.setNotMember(0l);
				localBdyResponseVo.setNotMemberPerc(0l);
				localBdyResponseVo.setName(count[4].toString());
				localBdyResponseVo.setLocationName(count[2].toString());
				localBdyResponseVo.setAreaName(count[7].toString());
				Map<Long,Long> localBdyWiseCount = localBdyWiseCountMap.get((Long)count[1]);
				if(localBdyWiseCount != null){
				   localBdyResponseVo.setTotalCalls(localBdyWiseCount.get((Long)count[3]));
				}
			}
			if(count[6].toString().trim().equalsIgnoreCase("1")){
				localBdyResponseVo.setReceived((Long)count[5]);
			}else if(count[6].toString().trim().equalsIgnoreCase("2")){
				localBdyResponseVo.setNotReceived((Long)count[5]);
			}else if(count[6].toString().trim().equalsIgnoreCase("3")){
				localBdyResponseVo.setNotMember((Long)count[5]);
			}
		}
	}
	
	public void calculatePerc(List<CadreIVRResponseVO> responseList,Long perc,List<CadreIVRResponseVO> newList){
		for(CadreIVRResponseVO responseVO:responseList){
			Long total = responseVO.getReceived()+responseVO.getNotReceived()+responseVO.getNotMember();
			
			if(total.longValue() > 0){
				responseVO.setReceivedPerc((responseVO.getReceived()*100)/total);
				responseVO.setNotReceivedPerc((responseVO.getNotReceived()*100)/total);
				responseVO.setNotMemberPerc((responseVO.getNotMember()*100)/total);
			}
			if(perc != null){
				if(responseVO.getNotReceivedPerc().longValue() >= perc.longValue()){
					newList.add(responseVO);
				}
			}else{
				newList.add(responseVO);
			}
		}
	}
	
	public CadreIVRResponseVO getPanchayatWiseCadreDispatchStatus(Long range,String state){
		CadreIVRResponseVO responseVo = new CadreIVRResponseVO();
		try{
			//0panchayatId,1panchayatName,2districtName,3constituencyName,4count,5responseKey
			List<Object[]> panchayatWiseOptionsCountList = cadreIvrResponseDAO.getPanchayatWiseIVRInfo(state);
			
			////0panchayatId,1count
			List<Object[]> panchayatWiseCountList = cadreIvrResponseDAO.getPanchayatWiseIVRCountInfo(state);
			
			Map<Long,CadreIVRResponseVO> panchayatOptionsCountMap = new HashMap<Long,CadreIVRResponseVO>();
			Map<Long,Long> panchayatWiseCountMap = new HashMap<Long,Long>();
			List<CadreIVRResponseVO> responseList = new ArrayList<CadreIVRResponseVO>();
			List<CadreIVRResponseVO> newList = new ArrayList<CadreIVRResponseVO>();
			
			for(Object[] count:panchayatWiseCountList){
				panchayatWiseCountMap.put((Long)count[0], (Long)count[1]);
			}
			
			for(Object[] count:panchayatWiseOptionsCountList){
				
				CadreIVRResponseVO panchayatResponseVo = panchayatOptionsCountMap.get((Long)count[0]);
				if(panchayatResponseVo == null){
					panchayatResponseVo = new CadreIVRResponseVO();
					panchayatOptionsCountMap.put((Long)count[0],panchayatResponseVo);
					responseList.add(panchayatResponseVo);
					panchayatResponseVo.setReceived(0l);
					panchayatResponseVo.setReceivedPerc(0l);
					panchayatResponseVo.setNotReceived(0l);
					panchayatResponseVo.setNotReceivedPerc(0l);
					panchayatResponseVo.setNotMember(0l);
					panchayatResponseVo.setNotMemberPerc(0l);
					panchayatResponseVo.setName(count[1].toString());
					panchayatResponseVo.setLocationName(count[3].toString());
					panchayatResponseVo.setAreaName(count[2].toString());
					panchayatResponseVo.setTotalCalls(panchayatWiseCountMap.get((Long)count[0]));
				}
				if(count[5].toString().trim().equalsIgnoreCase("1")){
					panchayatResponseVo.setReceived((Long)count[4]);
				}else if(count[5].toString().trim().equalsIgnoreCase("2")){
					panchayatResponseVo.setNotReceived((Long)count[4]);
				}else if(count[5].toString().trim().equalsIgnoreCase("3")){
					panchayatResponseVo.setNotMember((Long)count[4]);
				}
			}
			
			calculatePerc(responseList,range,newList);
			responseVo.setApList(newList);
			Collections.sort(newList, sort);
		}catch(Exception e){
			LOG.error("Exception rised in getPanchayatWiseCadreDispatchStatus() ",e);
		}
		return responseVo;
	}
	
	public  Comparator<CadreIVRResponseVO> sort = new Comparator<CadreIVRResponseVO>()
			 {
					  
				  public int compare(CadreIVRResponseVO loc1, CadreIVRResponseVO loc2)
					{
					   return (loc2.getNotReceivedPerc().compareTo(loc1.getNotReceivedPerc()));
					}
			 };
			
			 
			 public Long getTotalRegisterCadreInfoByState(String state,List<Long> accessLocationIds){
				
				 Long count =0l;
					try{
					count = tdpCadreDAO.getTotalRegisterCadreInfoByState(state,accessLocationIds);
				
					}catch(Exception e){
						LOG.error("Exception rised in getTotalRegisterCadreInfo", e);
					}
					
					return count;
				}
			 
			 	public List<CadreIVRVO> getIvrCountForDashBoard(){
				
						List<CadreIVRVO> returnResult = new ArrayList<CadreIVRVO>();
						try{
						DateUtilService dateService = new DateUtilService();
						Date currentDate = dateService.getCurrentDateAndTime();
						CadreIVRVO ivrInfo = new CadreIVRVO();
						List<Object[]> list = cadreIvrResponseDAO.getIvrCountForAPAndTS();
						if(list != null && list.size() > 0)
						{
							for(Object[] params : list)
							{
							ivrInfo.setTotal(ivrInfo.getTotal() + (Long)params[0]);
							if(params[1] != null && params[1].toString().equalsIgnoreCase("1")) // response key is 1 //received
								ivrInfo.setReceived((Long)params[0]);
							else if(params[1] != null && params[1].toString().toString().equalsIgnoreCase("2"))
								ivrInfo.setNotReceived((Long)params[0]);
							else if(params[1] != null && params[1].toString().toString().equalsIgnoreCase("3"))
								ivrInfo.setNotRegistered((Long)params[0]);
								ivrInfo.setAnsweredCnt(ivrInfo.getReceived() + ivrInfo.getNotReceived() + ivrInfo.getNotRegistered());
								if(ivrInfo.getAnsweredCnt() > 0)
								{
									ivrInfo.setReceivedPerc(new BigDecimal((ivrInfo.getReceived()*100/ivrInfo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
									ivrInfo.setNotReceivedPerc(new BigDecimal((ivrInfo.getNotReceived()*100/ivrInfo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
									ivrInfo.setNotMemberPerc(new BigDecimal((ivrInfo.getNotRegistered()*100/ivrInfo.getAnsweredCnt())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
								}
							}
						}
						returnResult.add(ivrInfo);
						}
						catch(Exception e)
						{
							LOG.error("Exception rised in getIvrDashBoardCounts", e);	
						}
					     return returnResult;
					}
				
			 	public List<CadreIVRVO> getIvrDashBoardCountsByDate(String fromDate,String toDate,String state,String accessType,Long accessValue,Long campaignId)
				 {
					 List<CadreIVRVO> returnList = new  ArrayList<CadreIVRVO>();
					 CadreIVRVO cadreInfo = new CadreIVRVO();
					 Date strDate = null;
					 Date endDate = null;
					 try{
						 SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
						 if(!fromDate.isEmpty())
						 strDate = format.parse(fromDate.trim());
						 if(!toDate.isEmpty())
						 endDate = format.parse(toDate.trim());
						 cadreInfo = setIVRBasicInfo(strDate,endDate,state,accessType,accessValue,campaignId); 
						 returnList.add(cadreInfo);
					 }
					 catch(Exception e)
					 {
						 LOG.error("Exception rised in getIvrDashBoardCountsByDate() ",e);	 
					 }
					return returnList;
				 }
			 	public CadreIVRVO setIVRBasicInfo(Date fromDate,Date toDate,String state,String accessType,Long accessValue,Long campaignId)
				{
					CadreIVRVO returnVo = new CadreIVRVO();
					List<Long> accessLocationIds = new ArrayList<Long>();
					Long ivrCount = 0l;
					Long receivedCount = 0l;
					Long notReceivedCount = 0l;
					Long notregisteredCount = 0l;
					Long wrongOptionCount =0l;
					Long noOptionCount =0l;
					Long userBusyCount =0l;
					Long noAnswerCount =0l;
					Long switchCongestion = 0l;
					Long otherError = 0l;
					Long callRejectedCount = 0l;
					Long newtworkError = 0l;
					Long unallocatedNumbers = 0l;
					Long interworkingCount = 0l;
					Long selectedOptionCnt = 0l;
					List<String> successNos =new ArrayList<String>();
					successNos.add("1");successNos.add("2");successNos.add("3");
					try{
						Map<Long,String> optionNames = new HashMap<Long,String>();
						List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(campaignId);
						if(optionsList != null && optionsList.size() >0){
							for(Object[] option:optionsList){
								optionNames.put((Long)option[0], option[1].toString());
							}
						}
						
						getAccessLocationValues(accessLocationIds,accessType,accessValue);
						List<Object[]> list = cadreIvrResponseDAO.getIvrCountByDate(fromDate,toDate,state,accessLocationIds,campaignId);
						if(list != null && list.size() > 0)
						{
							Map<Long,CadreIVRVO> ivrResponceMap = new HashMap<Long, CadreIVRVO>();
							//Long totalResponces = 0l;
							for(Object[] ivrCountInfo:list){
								ivrCount = ivrCount+(Long)ivrCountInfo[0];
								/* IVR Success Calls Group*/
								if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
								{
									if(ivrCountInfo[3] != null)
									{
										CadreIVRVO responceVO = ivrResponceMap.get(Long.valueOf(ivrCountInfo[3].toString().trim()));
										if(responceVO == null)
										{
											responceVO = new CadreIVRVO();
											responceVO.setId((Long)ivrCountInfo[3]);//options id
											responceVO.setName(optionNames.get((Long)ivrCountInfo[3]));//option name
											responceVO.setCount((Long)ivrCountInfo[0]);//count
											ivrResponceMap.put((Long)(ivrCountInfo[3]), responceVO);
										}
										else
										{
											responceVO.setCount(responceVO.getCount() + (Long)ivrCountInfo[0]);//count
										}
										
										selectedOptionCnt = selectedOptionCnt + responceVO.getCount();		
									}
									else if(ivrCountInfo[3] == null){
										   noOptionCount = noOptionCount +  (Long)ivrCountInfo[0];
									}
										
									/*
									if(ivrCountInfo[3] !=null && ivrCountInfo[3].toString().equalsIgnoreCase("1")){ // response key is 1 //received
										receivedCount = receivedCount +  (Long)ivrCountInfo[0];
										returnVo.setOption1(optionNames.get((Long)ivrCountInfo[3]));
									}
									else if(ivrCountInfo[3] !=null && ivrCountInfo[3].toString().equalsIgnoreCase("2")){
										notReceivedCount = notReceivedCount + 	 (Long)ivrCountInfo[0];
										returnVo.setOption2(optionNames.get((Long)ivrCountInfo[3]));
										
									}
									else if(ivrCountInfo[3] !=null && ivrCountInfo[3].toString().equalsIgnoreCase("3")){
										notregisteredCount = notregisteredCount + (Long)ivrCountInfo[0];
										returnVo.setOption3(optionNames.get((Long)ivrCountInfo[3]));
									}
									else if(ivrCountInfo[3] !=null && !successNos.contains(ivrCountInfo[3].toString())){
										wrongOptionCount = wrongOptionCount + (Long)ivrCountInfo[0];
										returnVo.setOption4(optionNames.get((Long)ivrCountInfo[3]));
									}
									else if(ivrCountInfo[3] == null){
										   noOptionCount = noOptionCount +  (Long)ivrCountInfo[0];
									}
								*/
									
									
								}
								else
								{
									if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.USER_BUSY))
										 userBusyCount = userBusyCount + (Long)ivrCountInfo[0];
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.CALL_REJECTED))
										 callRejectedCount = callRejectedCount + (Long)ivrCountInfo[0];
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NO_ANSWER) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NO_USER_RESPONSE))
										 noAnswerCount = noAnswerCount + (Long)ivrCountInfo[0];
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.SWITCH_CONGESTION) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CIRCUIT_CONGESTION))
										switchCongestion = switchCongestion + (Long)ivrCountInfo[0];
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_TEMPORARY_FAILURE) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.DESTINATION_OUT_OF_ORDER) 
											 || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NETWORK_OUT_OF_ORDER) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.SUBSCRIBER_ABSENT))
										newtworkError = newtworkError + (Long)ivrCountInfo[0];
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.UNALLOCATED_NUMBER))
										unallocatedNumbers = unallocatedNumbers + (Long)ivrCountInfo[0];	
									else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.INTERWORKING))
										interworkingCount = interworkingCount + (Long)ivrCountInfo[0];	
									else 
										otherError = otherError + (Long)ivrCountInfo[0];
								}	
							}
							if(optionNames != null && optionNames.size() > 0)
							{
								List<CadreIVRVO> subList = new ArrayList<CadreIVRVO>();
								for (Long responceOptionId : optionNames.keySet()) 
								{
									if(ivrResponceMap != null && ivrResponceMap.size() > 0)
									{
										CadreIVRVO responceVO = ivrResponceMap.get(responceOptionId);
										if(responceVO != null)
										{
											responceVO.setReceivedPerc(new BigDecimal((responceVO.getCount()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
										}
										else
										{
											responceVO = new CadreIVRVO();
											responceVO.setId(responceOptionId);//options id
											responceVO.setName(optionNames.get(responceOptionId));//option name
											responceVO.setCount(0l);//count
											responceVO.setReceivedPerc(0.00);
										}
										subList.add(responceVO);
									}
									
								}
								returnVo.setSubListForResponces(subList);
							}
						}
						returnVo.setTotal(ivrCount);
						//returnVo.setReceived(receivedCount);
						//returnVo.setNotReceived(notReceivedCount);
						//returnVo.setNotRegistered(notregisteredCount);
						//returnVo.setWrongOption(wrongOptionCount);
						returnVo.setNoOption(noOptionCount);
						//Long selectedOptionCnt = returnVo.getReceived() + returnVo.getNotReceived() + returnVo.getNotRegistered() + returnVo.getWrongOption();
						
						/*if(selectedOptionCnt > 0)
						{
							returnVo.setReceivedPerc(new BigDecimal((returnVo.getReceived()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNotReceivedPerc(new BigDecimal((returnVo.getNotReceived()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNotMemberPerc(new BigDecimal((returnVo.getNotRegistered()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setWrongOptionPerc(new BigDecimal((returnVo.getWrongOption()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						Long answerTotal= selectedOptionCnt + returnVo.getNoOption();
						if(answerTotal > 0)
						{
						returnVo.setNoOptionPerc(new BigDecimal((returnVo.getNoOption()*100.0/answerTotal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						returnVo.setAnsweredCnt(answerTotal);
						returnVo.setAnsweredPerc(new BigDecimal((returnVo.getAnsweredCnt()*100.0/returnVo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}*/
						returnVo.setUserBusy(userBusyCount);
						returnVo.setNoAnswer(noAnswerCount);
						returnVo.setSwitchCongestion(switchCongestion);
						returnVo.setOtherError(otherError);
						returnVo.setCallRejectedCount(callRejectedCount);
						returnVo.setNewtworkError(newtworkError);
						returnVo.setUnallocatedNumbers(unallocatedNumbers);
						returnVo.setInterworkingCount(interworkingCount);					
						returnVo.setTotalError(switchCongestion+otherError+newtworkError+unallocatedNumbers+interworkingCount );
						returnVo.setTotalUnAnswered(userBusyCount+noAnswerCount+callRejectedCount);
						/*
						
						*/
						if(returnVo.getTotalError() > 0)
						{
						//	returnVo.setUserBusyPerc(new BigDecimal((returnVo.getUserBusy()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						//	returnVo.setNoAnswerPerc(new BigDecimal((returnVo.getNoAnswer()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setSwitchCongestionPerc(new BigDecimal((returnVo.getSwitchCongestion()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setOtherErrorPerc(new BigDecimal((otherError*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							
						//	returnVo.setCallRejectedPerc(new BigDecimal((returnVo.getCallRejectedCount()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNewtworkErrorPerc(new BigDecimal((returnVo.getNewtworkError()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setUnallocatedNumbersPerc(new BigDecimal((returnVo.getUnallocatedNumbers()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setInterworkingCountPerc(new BigDecimal((returnVo.getInterworkingCount()*100.0/returnVo.getTotalError())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setTotalErrorPerc(new BigDecimal((returnVo.getTotalError()*100.0/returnVo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						if(returnVo.getTotalUnAnswered() >0)
						{
							returnVo.setUserBusyPerc(new BigDecimal((returnVo.getUserBusy()*100.0/returnVo.getTotalUnAnswered())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNoAnswerPerc(new BigDecimal((returnVo.getNoAnswer()*100.0/returnVo.getTotalUnAnswered())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setCallRejectedPerc(new BigDecimal((returnVo.getCallRejectedCount()*100.0/returnVo.getTotalUnAnswered())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setTotalUnAnsweredPerc(new BigDecimal((returnVo.getTotalUnAnswered()*100.0/returnVo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						
						/*if(selectedOptionCnt > 0)
						{
							returnVo.setReceivedPerc(new BigDecimal((returnVo.getReceived()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNotReceivedPerc(new BigDecimal((returnVo.getNotReceived()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setNotMemberPerc(new BigDecimal((returnVo.getNotRegistered()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							returnVo.setWrongOptionPerc(new BigDecimal((returnVo.getWrongOption()*100.0/selectedOptionCnt)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							
						}*/
						Long answerTotal= selectedOptionCnt + returnVo.getNoOption();
						if(answerTotal > 0)
						{
						returnVo.setSelectedOptionCntPerc(new BigDecimal((selectedOptionCnt*100.0/answerTotal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						returnVo.setNoOptionPerc(new BigDecimal((returnVo.getNoOption()*100.0/answerTotal)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						returnVo.setAnsweredCnt(answerTotal);
						returnVo.setAnsweredPerc(new BigDecimal((returnVo.getAnsweredCnt()*100.0/returnVo.getTotal())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						}
						
					}
					catch(Exception e)
					{
						LOG.error("Exception rised in setIVRBasicInfo", e);	
					}
					return returnVo;
				}
			 
			 public CadreIVRResponseVO getLocationWiseIVRInfo(Map<Long,String> locationNames,String locationType,Date startDate,Date endDate,String accessType,Long accessValue){
				 
				 CadreIVRResponseVO responseVO = new CadreIVRResponseVO();
				 List<Long> accessLocationIds = new ArrayList<Long>();
				 responseVO.setTotalCalls(0l);//setting very good count  <=10%
				 responseVO.setTotalCallsPerc(0l);//setting good count   >10 <=20%
				 responseVO.setReceived(0l);//setting ok count           >20 <=40
				 responseVO.setReceivedPerc(0l);//setting  poor count    >40 <=60
				 responseVO.setNotReceived(0l);//setting very poor count >60
				 Map<Long,CadreIVRResponseVO> locationOptionsCountMap = new HashMap<Long,CadreIVRResponseVO>();//Map<locationId,LocationInfo>
				 getAccessLocationValues(accessLocationIds,accessType,accessValue);				 
				//0locationId,1count,2responseKey
				 List<Object[]> locationWiseCountsInfoList = new ArrayList<Object[]>();
				 
				 if(locationNames.size() > 0){
				   locationWiseCountsInfoList = cadreIvrResponseDAO.getLocationWiseIVRInfo(locationNames.keySet(),locationType,startDate,endDate,accessLocationIds);
				 }
				 
				 populateLocationCounts(locationNames,locationWiseCountsInfoList,locationOptionsCountMap);
				 
				 responseVO.setApList(new ArrayList<CadreIVRResponseVO>(locationOptionsCountMap.values()));
				 
				 if(responseVO.getApList().size() > 0){
				    calculateNotReceivedPercentages(responseVO);
				 
				    Collections.sort(responseVO.getApList(), sort);
				    
				 }
				 if(locationType.equalsIgnoreCase("constituency")){
				    	for(Long id:locationNames.keySet()){
				    		if(locationOptionsCountMap.get(id) == null){
				    			if(accessLocationIds.size() > 0){
				    			 if(accessLocationIds.contains(id)){
					    			CadreIVRResponseVO vo = new CadreIVRResponseVO();
					    			vo.setName(locationNames.get(id));
					    			vo.setId(id);
					    			vo.setReceived(0l);
					    			vo.setNotReceived(0l);
					    			vo.setNotReceivedPerc(0l);
					    			vo.setNotMember(0l);
					    			responseVO.getApList().add(vo);
				    			 }
				    			}else{
				    				CadreIVRResponseVO vo = new CadreIVRResponseVO();
					    			vo.setName(locationNames.get(id));
					    			vo.setId(id);
					    			vo.setReceived(0l);
					    			vo.setNotReceived(0l);
					    			vo.setNotReceivedPerc(0l);
					    			vo.setNotMember(0l);
					    			responseVO.getApList().add(vo);
				    			}
				    		}
				    	}
				    }
				 return responseVO;
			 }
			 
			 public void populateLocationCounts(Map<Long,String> locationNames,List<Object[]> locationWiseCountsInfoList, 
					 Map<Long,CadreIVRResponseVO> locationOptionsCountMap){
				 
				//0locationId,1count,2responseKey
				 for(Object[] count:locationWiseCountsInfoList){
						
						CadreIVRResponseVO locationResponseVo = locationOptionsCountMap.get((Long)count[0]);
						if(locationResponseVo == null){
							locationResponseVo = new CadreIVRResponseVO();
							locationOptionsCountMap.put((Long)count[0],locationResponseVo);
							locationResponseVo.setName(locationNames.get((Long)count[0]));
							locationResponseVo.setId((Long)count[0]);
							locationResponseVo.setReceived(0l);
							locationResponseVo.setNotReceived(0l);
							locationResponseVo.setNotReceivedPerc(0l);
							locationResponseVo.setNotMember(0l);
						}
						if(count[2].toString().trim().equalsIgnoreCase("1")){
							locationResponseVo.setReceived((Long)count[1]);
						}else if(count[2].toString().trim().equalsIgnoreCase("2")){
							locationResponseVo.setNotReceived((Long)count[1]);
						}else if(count[2].toString().trim().equalsIgnoreCase("3")){
							locationResponseVo.setNotMember((Long)count[1]);
						}
					}
			 }
			 
			 public void calculateNotReceivedPercentages(CadreIVRResponseVO resVO){				 
				 
				 
				 for(CadreIVRResponseVO responseVO:resVO.getApList()){
						Long total = responseVO.getReceived()+responseVO.getNotReceived()+responseVO.getNotMember();
						
						if(total.longValue() > 0){
							responseVO.setNotReceivedPerc((responseVO.getNotReceived()*100)/total);
						}
						Long perc = responseVO.getNotReceivedPerc();
						if(perc.longValue() <= 10l){
							  resVO.setTotalCalls(resVO.getTotalCalls()+1);
						}else if(perc.longValue() > 10l && perc.longValue() <= 20l ){
							  resVO.setTotalCallsPerc(resVO.getTotalCallsPerc()+1);
						}else if(perc.longValue() > 20l && perc.longValue() <= 40l ){
							  resVO.setReceived(resVO.getReceived()+1);
						}else if(perc.longValue() > 40l && perc.longValue() <= 60l ){
							  resVO.setReceivedPerc(resVO.getReceivedPerc()+1);
						}else if(perc.longValue() > 60l){
							  resVO.setNotReceived(resVO.getNotReceived()+1);
						}
					}
			 }
			 
          public CadreIVRResponseVO getLocationWiseIVRDetailedInfo(Map<Long,String> locationNames,String locationType,Date startDate,Date endDate,Long constituencyId,Long campaignId){
				 
				 CadreIVRResponseVO responseVO = new CadreIVRResponseVO();
				 try{
					 Map<Long,CadreIVRResponseVO> responseMap = new HashMap<Long,CadreIVRResponseVO>();
					 
					 getNoOfMembersRegInfo(locationNames,locationType,responseMap,constituencyId,campaignId);
					 getNoOfCardsPrintedAndJobInfo(locationType,responseMap,constituencyId);
					 getResponseInfo(locationType,responseMap,startDate,endDate,constituencyId,campaignId);
					 responseVO.setApList(new ArrayList<CadreIVRResponseVO>(responseMap.values()));
					 calculateAllPercs(responseVO.getApList());
					// Collections.sort(responseVO.getApList(), sort);
				 }catch(Exception e){
					 LOG.error("Exception rised in getLocationWiseIVRDetailedInfo ",e);
				 }
				 return responseVO;
			 }
          
          public void getNoOfMembersRegInfo(Map<Long,String> locationNames,String locationType,Map<Long,CadreIVRResponseVO> responseMap,Long constituencyId,Long campaignId){
        	//0locationId,1count
        	  List<Object[]> registeredCountList = new ArrayList<Object[]>();
        	  if(locationType.equalsIgnoreCase("district") || locationType.equalsIgnoreCase("constituency")){
        	   registeredCountList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationNames.keySet(), locationType,constituencyId,"Registered",3L);//srishailam and teja
        	  }else{
        		  registeredCountList = tdpCadreDAO.getLocationWiseCadreRegisterInfo(locationNames.keySet(), locationType,constituencyId); 
        	  }
        	  Map<Long,String> optionNames = new HashMap<Long,String>();	
        	  List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(campaignId);
        	  
        	  for(Object[] registeredCount:registeredCountList){
        		  CadreIVRResponseVO response = responseMap.get((Long)registeredCount[0]);
        		  if(response == null){
        			  response = new CadreIVRResponseVO();
        			  response.setName(locationNames.get((Long)registeredCount[0]));
        			  response.setTotalCalls(0l);
        			  response.setTotalCallsPerc(0l);
        			  response.setReceived(0l);
        			  response.setReceivedPerc(0l);
        			  response.setNotReceived(0l);
        			  response.setNotReceivedPerc(0l);
        			  response.setNotMember(0l);
        			  response.setNotMemberPerc(0l);
        			  response.setRegisteredCount(0l);
        			  response.setPrintedCount(0l);
        			  response.setJobCode("");
        			  response.setTotalIvrCalls(0l);
        			  response.setTotalAnswerdCalls(0l);
        			  response.setTotalAnswerdPerc(0l);
        			  response.setErrorCalls(0l);
        			  response.setErrorCallsPerc(0l);
        			  response.setWrongOptionSel(0l);
        			  response.setWrongOptionSelPerc(0l);
        			  response.setNoOptionSel(0l);
        			  response.setNoOptionSelPerc(0l);     				  
        			  response.setId((Long)registeredCount[0]);
        			  if(optionsList != null && optionsList.size() >0){
        					for(Object[] option:optionsList){
        						CadreIVRResponseVO response1 = new CadreIVRResponseVO();
        						response1.setId((Long)option[0]);
        						response1.setName(option[1].toString());
        						response1.setCount(0l);
        						response.getSubList().add(response1);
        					}
        			  }
        			  responseMap.put((Long)registeredCount[0],response);
        		  }
        		  response.setRegisteredCount((Long)registeredCount[1]);
        	  }
          }
          
          public void getNoOfCardsPrintedAndJobInfo(String locationType,Map<Long,CadreIVRResponseVO> responseMap,Long constituencyId){
        	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	   //0locationId,1count,2jobids
        	  
        	   List<Object[]> cardsPrintedAndJobInfo = new ArrayList<Object[]>();
         	  if(locationType.equalsIgnoreCase("district") || locationType.equalsIgnoreCase("constituency")){
         		 cardsPrintedAndJobInfo = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(responseMap.keySet(),locationType,constituencyId,"Printed",3L);//srishailam and teja
         	  }else{
         		 cardsPrintedAndJobInfo = tdpCadreDAO.getLocationWiseCadrePrintedCount(responseMap.keySet(),locationType,constituencyId); 
         	  }
        	   
     
        	   for(Object[] cardsPrintedInfo:cardsPrintedAndJobInfo){
         		  CadreIVRResponseVO response = responseMap.get((Long)cardsPrintedInfo[0]);
         		  if(response != null){
         			 response.setPrintedCount(response.getPrintedCount()+(Long)cardsPrintedInfo[1]);
         			/* if(response.getJobCode().length() > 0){
         			    response.setJobCode(response.getJobCode()+" , "+sdf.format((Date)cardsPrintedInfo[2]));
         			 }else{
         				response.setJobCode(sdf.format((Date)cardsPrintedInfo[2]));
         			 }*/
         		  }
         		 
         	  }
          }
          
          public void getResponseInfo(String locationType,Map<Long,CadreIVRResponseVO> responseMap,Date startDate,Date endDate,Long constituencyId,Long campaignId){
        	//0locationId,1count,2callStatus,3responseKey
        	 
			  Map<Long,CadreIVRResponseVO> ivrResponceMap = new HashMap<Long, CadreIVRResponseVO>();
				
        	  List<Object[]> responseInfoList = cadreIvrResponseDAO.getLocationWiseIVRCountsInfo(responseMap.keySet(), locationType, startDate, endDate,constituencyId,campaignId);
        	  for(Object[] response:responseInfoList){
        		 
        		  CadreIVRResponseVO locationResponseVo = responseMap.get((Long)response[0]);
        		  if(locationResponseVo != null){
        			
        			    locationResponseVo.setTotalIvrCalls(locationResponseVo.getTotalIvrCalls()+(Long)response[1]);
        			    if(response[2] != null && response[2].toString().trim().equalsIgnoreCase("NORMAL_CLEARING")){
						    locationResponseVo.setTotalAnswerdCalls(locationResponseVo.getTotalAnswerdCalls()+(Long)response[1]);
						   if(response[4] == null){ 
						     locationResponseVo.setNoOptionSel(locationResponseVo.getNoOptionSel()+(Long)response[1]);
						   }
						   else if(response[4] != null){
							   CadreIVRResponseVO responceVO =  getMatchedOptions(locationResponseVo.getSubList(),Long.valueOf(response[4].toString().trim()));
								if(responceVO != null){									
									responceVO.setCount(responceVO.getCount() + (Long)response[1]);//count									
								}						
							}
        			    }else{
        			    	locationResponseVo.setErrorCalls(locationResponseVo.getErrorCalls()+(Long)response[1]);
        			    }
        			    
        			   /* if(response[4] != null){
	        			    if(response[4].toString().trim().equalsIgnoreCase("1")){
								locationResponseVo.setReceived(locationResponseVo.getReceived()+(Long)response[1]);
								locationResponseVo.setOption1(optionNames.get((response[4])));								
							}else if(response[4].toString().trim().equalsIgnoreCase("2")){
								locationResponseVo.setNotReceived(locationResponseVo.getNotReceived()+(Long)response[1]);
								locationResponseVo.setOption2(optionNames.get((response[4])));
							}else if(response[4].toString().trim().equalsIgnoreCase("3")){
								locationResponseVo.setNotMember(locationResponseVo.getNotMember()+(Long)response[1]);
								locationResponseVo.setOption3(optionNames.get((response[4])));
							}else{
								locationResponseVo.setWrongOptionSel(locationResponseVo.getWrongOptionSel()+(Long)response[1]);
								locationResponseVo.setOption4(optionNames.get((response[4])));
							}
        			    }*/
        			    
        			  
						/* IVR Success Calls Group*/
        		  }	
        			
        	  }     	  
          }
          
          public void calculateAllPercs(List<CadreIVRResponseVO> responseList){
        	  for(CadreIVRResponseVO responseVO:responseList){
        		  
        		  if(responseVO.getTotalIvrCalls().longValue() > 0){
						responseVO.setErrorCallsPerc((responseVO.getErrorCalls()*100)/responseVO.getTotalIvrCalls());
						responseVO.setTotalAnswerdPerc((responseVO.getTotalAnswerdCalls()*100)/responseVO.getTotalIvrCalls());
					}
        		  if(responseVO.getTotalAnswerdCalls().longValue() > 0){
        		        responseVO.setNoOptionSelPerc((responseVO.getNoOptionSel()*100)/responseVO.getTotalAnswerdCalls());
        		  }
        		  
        		//  Long total = responseVO.getReceived`()+responseVO.getNotReceived()+responseVO.getNotMember()+responseVO.getWrongOptionSel();
      			
      			/*if(total.longValue() > 0){
      				responseVO.setReceivedPerc((responseVO.getReceived()*100)/total);
      				responseVO.setNotReceivedPerc((responseVO.getNotReceived()*100)/total);
      				responseVO.setNotMemberPerc((responseVO.getNotMember()*100)/total);
      				responseVO.setWrongOptionSelPerc((responseVO.getWrongOptionSel()*100)/total);
      			}*/
        	  }
          }
          
          public CadreIVRResponseVO getLocationWisePercInfo(String locationType,List<Long> locationIds,Date startDate,Date endDate,String accessType,Long accessValue,Long campaignId){
        	  List<Object[]> locations = new ArrayList<Object[]>();
        	  if(locationType.equalsIgnoreCase("district")){
        	      locations = districtDAO.getDistrictDetailsByDistrictIds(locationIds);
        	  }else{
        		  locations = constituencyDAO.getParliamentConstituencyInfoByConstituencyIds(locationIds);
        	  }
        	  Map<Long,String> locationNames = new HashMap<Long,String>();
        	  for(Object[] location:locations){
        		  locationNames.put((Long)location[0], location[1].toString());
        	  }
        	  return getLocationWiseIVRDetailedInfo(locationNames,locationType,startDate,endDate,null,campaignId);
          }
          
          public CadreIVRResponseVO  getLocationWisePercInfoErrorInfo(String locationType,Long constituencyId,Date startDate,Date endDate,String accessType,Long accessValue,Long campaignId){
        	  Map<Long,String> locationNames = new HashMap<Long,String>();
        	  if(locationType.equalsIgnoreCase("all") || locationType.equalsIgnoreCase("AP") || locationType.equalsIgnoreCase("TS")){
        		  List<Object[]> assemblyList = new ArrayList<Object[]>();
        		  List<Long> accessLocationIds = new ArrayList<Long>();
        		  getAccessLocationValues(accessLocationIds,accessType,accessValue);
        		  if(accessLocationIds.size() > 0){
        			  assemblyList = constituencyDAO.getParliamentConstituencyInfoByConstituencyIds(accessLocationIds);
        		  }else if(locationType.equalsIgnoreCase("all")){
        			  assemblyList = constituencyDAO.getConstituenciesForRegion("");
        		  }else if(locationType.equalsIgnoreCase("AP")){
        			  assemblyList = constituencyDAO.getConstituenciesForRegion("Andhra Pradesh");
          		  }else{
          			assemblyList = constituencyDAO.getConstituenciesForRegion("Telangana");
          		  }
        		  for(Object[] location:assemblyList){
            		  locationNames.put((Long)location[0], location[1].toString());
            	  }
        		  return getLocationWiseIVRDetailedInfo(locationNames,"Constituency",startDate,endDate,null,campaignId);
        	  }else if(locationType.equalsIgnoreCase("mandal")){
        		  List<CadreIVRResponseVO> infoList = new ArrayList<CadreIVRResponseVO>();
        		  Map<Long,String> localBodyNames = new HashMap<Long,String>();
        		  List<Object[]> localBodyIds = boothDAO.getAllLocalBodies(constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
        		  for(Object[] location:localBodyIds){
        			  localBodyNames.put((Long)location[0], location[1].toString());
            	  }
        		  if(localBodyIds.size() > 0){
        			  CadreIVRResponseVO localBodyVO =  getLocationWiseIVRDetailedInfo(localBodyNames,"localBody",startDate,endDate,constituencyId,campaignId);
	        		  if(localBodyVO != null && localBodyVO.getApList() != null ){
	        			  for(CadreIVRResponseVO info:localBodyVO.getApList()){
	        				  info.setId(Long.valueOf("1"+info.getId()));
	        			  }
	        		      infoList.addAll(localBodyVO.getApList());
	        		  }
        		  }
        		  List<Object[]> tehsilIds =  boothDAO.getAllTehsilsDetailsInAConstituency(constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
        		  for(Object[] location:tehsilIds){
        			  locationNames.put((Long)location[0], location[1].toString()+" Mandal");
            	  }
        		  if(locationNames.size() > 0){
        			  CadreIVRResponseVO mandalVO =  getLocationWiseIVRDetailedInfo(locationNames,"mandal",startDate,endDate,constituencyId,campaignId);
	        		  if(mandalVO != null && mandalVO.getApList() != null ){
	        			  for(CadreIVRResponseVO info:mandalVO.getApList()){
	        				  info.setId(Long.valueOf("2"+info.getId()));
	        			  }
	        		      infoList.addAll(mandalVO.getApList());
	        		  }
        		  }
        		  Collections.sort(infoList, sort);
        		  CadreIVRResponseVO returnVo = new CadreIVRResponseVO();
        		  returnVo.setApList(infoList);
        		  return returnVo;
        	  }else if(locationType.equalsIgnoreCase("panchayat")){
        		  List<Object[]> panchayatList = boothDAO.getPanchayatsByConstituencyAndPublication(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
        		  for(Object[] location:panchayatList){
            		  locationNames.put((Long)location[0], location[1].toString());
            	  }
        		  return getLocationWiseIVRDetailedInfo(locationNames,"panchayat",startDate,endDate,null,campaignId);
        	  }else{
        		  List<Object[]> boothsList = boothDAO.getBoothsInAConstituency(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
        		  for(Object[] location:boothsList){
            		  locationNames.put((Long)location[0], location[1].toString());
            	  }
        		  return getLocationWiseIVRDetailedInfo(locationNames,"booth",startDate,endDate,null,campaignId);
        	  }
          }
          
          public void getAccessLocationValues(List<Long> accessLocationIds,String accessType,Long accessValue){
        	 if(accessType.equalsIgnoreCase("MLA")){
        		   accessLocationIds.add(accessValue);
        	  }else if(accessType.equalsIgnoreCase("MP")){
        		  List<Long> parlmentIds = new ArrayList<Long>();
        		  parlmentIds.add(accessValue);
        		  accessLocationIds.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parlmentIds));
        	  }else if(accessType.equalsIgnoreCase("DISTRICT")){
        		  List<Object[]> assemblies = constituencyDAO.getDistrictConstituencies(accessValue);
        		  for(Object[] assembly:assemblies){
        			  accessLocationIds.add((Long)assembly[0]);
        		  }
        	  }
        	  
          }
          
          public String  saveEnquiryInfo(CadreIVRResponseVO status){
        	  try{
	        	  CadreIVREnquiry cadreIVREnquiry = new CadreIVREnquiry();
	 
	        	  cadreIVREnquiry.setUserId(status.getTotalCalls());
	        	  
	        	  if(status.getLocationName().equalsIgnoreCase("constituency")){
	        		  cadreIVREnquiry.setLocationTypeId(1l);
	        		  cadreIVREnquiry.setLocationValue(status.getId());
	        	  }else if(status.getLocationName().equalsIgnoreCase("mandal")){
	        		  if(status.getId().toString().substring(0,1).trim().equalsIgnoreCase("1")){
	        			  cadreIVREnquiry.setLocationTypeId(5l);
	            		  cadreIVREnquiry.setLocationValue(new Long(status.getId().toString().substring(1)));
	        		  }else{
	        			  cadreIVREnquiry.setLocationTypeId(2l);
	            		  cadreIVREnquiry.setLocationValue(new Long(status.getId().toString().substring(1)));
	        		  }
	        	  }else if(status.getLocationName().equalsIgnoreCase("ward")){
	        		  cadreIVREnquiry.setLocationTypeId(6l);
	        		  cadreIVREnquiry.setLocationValue(status.getId());
	        	  }
	        	  cadreIVREnquiry.setDetails(status.getName());
	        	  cadreIVREnquiry.setDesignation(status.getDesignation());
	        	  cadreIVREnquiry.setMobile(status.getJobCode());
	        	  cadreIVREnquiry.setReceived(status.getReceived());
	        	  cadreIVREnquiry.setDelivered(status.getNotReceived());
	        	  cadreIVREnquiry.setInsertedDate(dateUtilService.getCurrentDateAndTime());
	        	  cadreIVREnquiry.setCallStatus(status.getAreaName());
	        	  cadreIVREnquiry.setIsDeleted("N");
	        	  cadreIVREnquiry.setConstituencyId(status.getRegisteredCount());
	        	  cadreIVREnquiryDAO.save(cadreIVREnquiry);
	        	  return "success";
        	  }catch(Exception e){
        		  LOG.error("Exception rised in saveEnquiryInfo ",e);
        	  }
        	  	return "error";
          }
          
          public CadreIVRResponseVO getLocationWiseEnquiryInfo(String locationLvl,Long locationValue,Long userId,String resultType){
        	  CadreIVRResponseVO returnVo = new CadreIVRResponseVO();
        	  SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        	  List<CadreIVRResponseVO> allResults = new ArrayList<CadreIVRResponseVO>();
        	  returnVo.setApList(allResults);
        	  try{
	        	  CadreIVRResponseVO vo = null;
	        	  Map<Long,Map<Long,String>> locationNames = new HashMap<Long,Map<Long,String>>();
	        	//0 locationTypeId,1locationValue,2details,3mobile,4received,5delivered,6 insertedDate,7callStatus
	        	  List<Object[]> locationInfoList = cadreIVREnquiryDAO.getLocationWiseEnquiryInfo(locationLvl, locationValue, userId,resultType);
	        	  for(Object[] locationInfo:locationInfoList){
	        		  Map<Long,String> locationDetails = locationNames.get((Long)locationInfo[0]);
	        		  if(locationDetails == null){
	        			  locationDetails = new HashMap<Long,String>();
	        			  locationNames.put((Long)locationInfo[0],locationDetails);
	        		  }
	        		  locationDetails.put((Long)locationInfo[1], "");
	        		   vo = new CadreIVRResponseVO();
	        		   vo.setTotalIvrCalls((Long)locationInfo[0]);//0 locationTypeId
	        		   vo.setId((Long)locationInfo[1]);  //1locationValue      		   
	        		   if(locationInfo[2] != null){
	        		       vo.setName(locationInfo[2].toString());//2details
	        		   }else{
	        			   vo.setName("");
	        		   }
	        		   if(locationInfo[3] != null){
	        		       vo.setJobCode(locationInfo[3].toString());//3mobile
	        		   }else{
	        			   vo.setJobCode("");
	        		   }
	        		   vo.setReceived((Long)locationInfo[4]);//4received
	        		   vo.setNotReceived((Long)locationInfo[5]);//5delivered
	        		   vo.setDate(sdf.format((Date)locationInfo[6]));//6 insertedDate
	        		   if(locationInfo[7] != null){
	        		       vo.setAreaName(locationInfo[7].toString());//7callStatus
	        		   }else{
	        			   vo.setAreaName("");
	        		   }
	        		  
	        		   allResults.add(vo);
	        	  }
	        	  getLocationNames(locationNames);
	        	  for(CadreIVRResponseVO location:allResults){
	        		  Map<Long,String> locationDetails = locationNames.get(location.getTotalIvrCalls());
	        		  if(locationDetails != null){
	        			  location.setLocationName(locationDetails.get(location.getId()));
	        		  }
	        	  }
        	  }catch(Exception e){
        		  LOG.error("Exception rised in getLocationWiseEnquiryInfo ",e);
        	  }
        	  return returnVo;
          }
          
          public void getLocationNames( Map<Long,Map<Long,String>> locationNames){ 
        	  
        	  for(Long locationLevelId:locationNames.keySet()){
        		  List<Object[]> locations = new ArrayList<Object[]>();
        		  Set<Long> locationIds = locationNames.get(locationLevelId).keySet();
        		  Map<Long,String> locationMap = locationNames.get(locationLevelId);
        		  if(locationLevelId.longValue() == 1){
        			  locations = constituencyDAO.getParliamentConstituencyInfoByConstituencyIds(new ArrayList<Long>(locationIds));
        			  for(Object[] location:locations){
            			  locationMap.put((Long)location[0], location[1].toString()+" Assembly");
            		  }
        		  }else if(locationLevelId.longValue() == 2){
        			  locations = tehsilDAO.getTehsilNameByTehsilIdsList(new ArrayList<Long>(locationIds));
        			  for(Object[] location:locations){
            			  locationMap.put((Long)location[0], location[1].toString()+" Mandal");
            		  }
        		  }else if(locationLevelId.longValue() == 5){
        			  locations =localElectionBodyDAO.getLocalElectionBodyNames(new ArrayList<Long>(locationIds));
        			  for(Object[] location:locations){
            			  locationMap.put((Long)location[0], location[1].toString());
            		  }
        		  }else if(locationLevelId.longValue() == 6){
        			  locations = constituencyDAO.getParliamentConstituencyInfoByConstituencyIds(new ArrayList<Long>(locationIds));
        			  for(Object[] location:locations){
            			  locationMap.put((Long)location[0], location[1].toString());
            		  }
        		  }
        		  
        	  }
          }
          
          public List<BasicVO> getAccessLocationValues(String accessType,Long accessValue){
        	  List<BasicVO> resultList = new ArrayList<BasicVO>();
        	 
        	  try{
        		  List<Long> accessLocationIds = new ArrayList<Long>();
        		  if(accessType.equalsIgnoreCase("STATE"))
            		  accessLocationIds = constituencyDAO.getAllAssemblyConstituencyIdsByStateId(1l);
            	  else if(accessType.equalsIgnoreCase("MLA")){
            		   accessLocationIds.add(accessValue);
            	  }else if(accessType.equalsIgnoreCase("MP")){
            		  List<Long> parlmentIds = new ArrayList<Long>();
            		  parlmentIds.add(accessValue);
            		  accessLocationIds.addAll(delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parlmentIds));
            	  }else if(accessType.equalsIgnoreCase("DISTRICT")){
            		  List<Object[]> assemblies = constituencyDAO.getDistrictConstituencies(accessValue);
            		  for(Object[] assembly:assemblies){
            			  accessLocationIds.add((Long)assembly[0]);
            		  }
            	  }
        		  if(accessLocationIds != null && accessLocationIds.size() > 0)
        		  {
	        	  List<Object[]> list = constituencyDAO.getConstituencyNameByConstituencyIdsList(accessLocationIds);
		        	  if(list != null && list.size() > 0)
		        	  {
		        		  for(Object[] params : list)
		        		  {
		        			  BasicVO vo = new BasicVO();
		        			  vo.setId((Long)params[0]);
		        			  vo.setName(params[1].toString());
		        			  vo.setDescription(params[2] != null ? params[2].toString():"");
		        			  resultList.add(vo);
		        		  }
		        	  }
        		  }
        	 }
        	  catch(Exception e)
        	  {
        		  e.printStackTrace();
        	  }
			return resultList;
          }
          
          public CadreIVRResponseVO getIvrPreviousCallBasicInfo(Date startDate,Date endDate,Long stateTypeId)
          {
        	  CadreIVRResponseVO returnVo = new CadreIVRResponseVO();
        	  try{
        		  List<Long> locationTypeIds = new ArrayList<Long>();
        		
        		 
        		  locationTypeIds.add(1l);
        		  List<Long> mandalTypeIds = new ArrayList<Long>();
        		  mandalTypeIds.add(2l);
        		  mandalTypeIds.add(5l);
        		  
        		  List<Long> constiIds = new ArrayList<Long>();
        		  List<Long> mandalIds = new ArrayList<Long>();
        		  List<Long> localbodyIds = new ArrayList<Long>();
        		
        		  
        			 List<Object[]> stateConstiIds = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
        			 List<Long> stateConstituencyIds = new ArrayList<Long>();
        			 if(stateConstiIds != null && stateConstiIds.size() > 0){
        				 for(Object[] params : stateConstiIds){					
        					if(!stateConstituencyIds.contains((Long)params[0])){
        						stateConstituencyIds.add((Long)params[0]);							
        					}				
        				}      			 
        			} 
        		  
        		  
        		List<Object[]> constituencyCnts =cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(locationTypeIds,startDate,endDate,stateConstituencyIds);       		
        		for(Object[] ids :constituencyCnts){
        			 if(!constiIds.contains((Long)ids[1]));
        			 	constiIds.add((Long)ids[1]);
        		}
        		       		
        		List<Object[]> mandalCnts =cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(mandalTypeIds,startDate,endDate,stateConstituencyIds);
        		for(Object[] ids :mandalCnts){   			
        			if(Long.valueOf(ids[2].toString()).longValue() == 2L){
        				if(!mandalIds.contains((Long)ids[1])){
     			 			mandalIds.add((Long)ids[1]);
        				}
        			}else if(Long.valueOf(ids[2].toString()).longValue() == 5L){
        				if(!localbodyIds.contains((Long)ids[1])){
        					localbodyIds.add((Long)ids[1]);
        				}
        			}
        			
        		}
        		Long constiPrintedCnt = 0l;
        		Long mandalPrintedCnt = 0l;
        		Long LocalbodyPrintedCnt = 0l;
        		
        		if(constiIds != null && constiIds.size()>0)
        			constiPrintedCnt = zebraPrintDetailsDAO.getPrintedCount(constiIds,IConstants.CONSTITUENCY);
        		if(mandalIds != null && mandalIds.size()>0)
          			mandalPrintedCnt = zebraPrintDetailsDAO.getPrintedCount(mandalIds,IConstants.TEHSIL);
        		if(localbodyIds != null && localbodyIds.size()>0)
          			LocalbodyPrintedCnt = zebraPrintDetailsDAO.getPrintedCount(localbodyIds,IConstants.LOCAL_ELECTION_BODY);
        		
          		//Long constituencyCnt =cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(locationTypeIds,startDate,endDate);
          		//Long mandalCnt =cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(mandalTypeIds,startDate,endDate);
        		
        	    BigDecimal constituencyReceivedCount = cadreIVREnquiryDAO.getTotalReceivedCount(startDate,endDate,locationTypeIds,stateConstituencyIds);
        		returnVo.setReceived(constituencyReceivedCount != null ? constituencyReceivedCount.longValue() : 0l);
        		BigDecimal constituencyDeliveredCount = cadreIVREnquiryDAO.getTotalDeliveredCount(startDate,endDate,locationTypeIds,stateConstituencyIds);
        		returnVo.setNotReceived(constituencyDeliveredCount != null ? constituencyDeliveredCount.longValue() : 0l);
        		
        		BigDecimal mandalReceivedCount = cadreIVREnquiryDAO.getTotalReceivedCount(startDate,endDate,mandalTypeIds,stateConstituencyIds);
         		returnVo.setTotalAnswerdCalls(mandalReceivedCount != null ? mandalReceivedCount.longValue() : 0l);
         		BigDecimal mandalDeliveredCount = cadreIVREnquiryDAO.getTotalDeliveredCount(startDate,endDate,mandalTypeIds,stateConstituencyIds);
         		returnVo.setNotMember(mandalDeliveredCount != null ? mandalDeliveredCount.longValue() : 0l);
         		
        		returnVo.setTotalCalls(mandalCnts != null ? Long.valueOf(constituencyCnts.size()) : 0l);
        		returnVo.setTotalIvrCalls(mandalCnts != null ? Long.valueOf(mandalCnts.size()) : 0l);
        		
        		returnVo.setPrintedCount(constiPrintedCnt);
        		returnVo.setMandalPrintedCnt(mandalPrintedCnt+ LocalbodyPrintedCnt);
        	 
        	  }
        	  catch(Exception e)
        	  {
        		 e.printStackTrace(); 
        	  }
			return returnVo;
          }
         public List<String> getPreviousPollsAvailableDates()
         {
        	 List<String> dates = new ArrayList<String>();
        	 try{
        	  List<Date> list = cadreIVREnquiryDAO.getAvailableDates();
        	  if(list != null && list.size() > 0)
        	  {
        		  for(Date date : list)
        		  dates.add(date.toString()); 
        	  }
        	 
        	 }
        	 catch(Exception e)
        	 {
        		 e.printStackTrace();
        	 }
			return dates;
        	 
         }
         
         public CadreIVRResponseVO getIvrPreviousCallInfo(String type,Date startDate,Date toDate,Long stateTypeId)
         {
        	 CadreIVRResponseVO returnVo = new CadreIVRResponseVO(); 
        	 List<CadreIVRResponseVO> resultList = new ArrayList<CadreIVRResponseVO>();
        	 try{
        		
        		 List<Object[]> stateConstiIds = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
        		 List<Long> stateConstituencyIds = new ArrayList<Long>();
        		 if(stateConstiIds != null && stateConstiIds.size() > 0){
        			 for(Object[] params : stateConstiIds){
 	 					
 						if(!stateConstituencyIds.contains((Long)params[0])){
 							stateConstituencyIds.add((Long)params[0]);							
 						}				
 	 				}      			 
	 			 } 
        		 
        		 List<Long> constituencyIds = new ArrayList<Long>();
        		 List<Long> mandalIds = new ArrayList<Long>();
        		 List<Long> localbodyIds = new ArrayList<Long>();
	        	 List<Long> locationTypeIds = new ArrayList<Long>();
	        	  if(type.equalsIgnoreCase("Constituency"))
	        		  locationTypeIds.add(1l);
	        	  else if(type.equalsIgnoreCase("Mandal"))
	        	  {
	        		  locationTypeIds.add(2l);
	        		  locationTypeIds.add(5l);
	        	  }
	        	  
        		 List<Object[]> list1 = cadreIVREnquiryDAO.getLocationIdsByTypeId(locationTypeIds,startDate,toDate,stateConstituencyIds);
 
        		 if(list1 != null && list1.size() > 0)
        		 {
        			 for(Object[] params : list1)
        			 {
        				 if(Long.valueOf(params[1].toString()).longValue() == 1l) 
        					 constituencyIds.add(Long.valueOf(params[0].toString()).longValue());
        				 else  if(Long.valueOf(params[1].toString()).longValue()  == 2l)
        					 mandalIds.add(Long.valueOf(params[0].toString()).longValue());
        				 else  if(Long.valueOf(params[1].toString()).longValue() == 5l)
        					 localbodyIds.add(Long.valueOf(params[0].toString()).longValue());
        			 }
        		 }
        		 
        		 
        		if(type.equalsIgnoreCase("Constituency")){
        			if(constituencyIds != null && constituencyIds.size()>0)
        				setIvrData(constituencyIds,IConstants.CONSTITUENCY,resultList,startDate,toDate);
        		}
        		if(type.equalsIgnoreCase("Mandal"))
        		{
        			if(mandalIds != null && mandalIds.size()>0)
        				setIvrData(mandalIds,IConstants.TEHSIL,resultList,startDate,toDate);
        			if(localbodyIds != null && localbodyIds.size()>0)
        				setIvrData(localbodyIds,IConstants.LOCAL_ELECTION_BODY,resultList,startDate,toDate);
        		}
        		
        		 returnVo.setApList(resultList);
        		
        		 
        	 }
        	 catch(Exception e)
        	 {
        		 e.printStackTrace(); 
        	 }
			return returnVo;
         }
         
         public void setIvrData(List<Long> locationIds,String type,List<CadreIVRResponseVO> resultList,Date startDate,Date toDate)
         {
        	 try{
        		 Map<Long,CadreIVRResponseVO> resultMap = new HashMap<Long, CadreIVRResponseVO>();
        		 List<Object[]> list = cadreIvrResponseDAO.getIvrCountByLocationIdsAndType(locationIds,type);
        		
        		 List<Object[]> printedCountDetails = zebraPrintDetailsDAO.getLocationWisePrintedCountDetails(locationIds, type);
        		 Map<Long,CadreIVRResponseVO> ivrCntMap = new HashMap<Long, CadreIVRResponseVO>();
        		 Long locationTypeId  = 0L;
        		  if(type.equalsIgnoreCase("Constituency"))
        			  locationTypeId = 1L;
        		  else if(type.equalsIgnoreCase(IConstants.TEHSIL))
        		  {
        			  locationTypeId = 2l;
    	        		
        		  }
        		  else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
        		  {
        			  locationTypeId = 5l;
        		  }  
        		 List<Object[]> list1 = cadreIVREnquiryDAO.getDeliveredAndReceivedCount(locationIds,startDate,toDate,locationTypeId);
        		 
        		 
        		
        		 List<String> successNos =new ArrayList<String>();
					 successNos.add("1");successNos.add("2");successNos.add("3");
				 if(list != null && list.size() > 0)
	 			 {
        			 for(Object[] ivrCountInfo : list)
 	 				{
 	 					
        				CadreIVRResponseVO vo = ivrCntMap.get((Long)ivrCountInfo[3]);
 						if(vo == null)
 						{
 							    vo = new CadreIVRResponseVO();
 								ivrCntMap.put((Long)ivrCountInfo[3], vo);
 						}
 						
 						if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
						{
							if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("1")) // response key is 1 //received
								vo.setReceived(vo.getReceived() + (Long)ivrCountInfo[0]);
							else if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("2"))
								vo.setNotReceived(vo.getNotReceived() + (Long)ivrCountInfo[0]);
							else if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("3"))
								vo.setNotMember(vo.getNotMember()  + (Long)ivrCountInfo[0]);
							else if(ivrCountInfo[1] !=null && !successNos.contains(ivrCountInfo[1].toString()))
								vo.setNoOptionSel(vo.getNoOptionSel() + (Long)ivrCountInfo[0]);
						}
 						
 	 				}
        			 
        			
	 			 }
        		 
        		List<Object[]> constiDetails = boothDAO.getConstituenciesNameByType(locationIds,type);
					
        		 Map<Long,String> constiDetailsMap = new HashMap<Long, String>();
        		 if(constiDetails != null && constiDetails.size() > 0)
	 			 {
        			 for(Object[] params : constiDetails)
 	 				{
 	 					String name = constiDetailsMap.get((Long)params[0]);
 						if(name == null)
 						{						
 								constiDetailsMap.put((Long)params[0], params[1].toString() );
 						}
 	 				}
        			 
	 			 }	

	        	 if(list1 != null && list1.size() > 0)
	 			 {
	 				for(Object[] params : list1)
	 				{
	 					CadreIVRResponseVO vo = resultMap.get(Long.valueOf(params[2].toString()));
						if(vo == null)
						{
 						
							vo = new CadreIVRResponseVO();
							vo.setId(Long.valueOf(params[2].toString()));
						
							if(Long.valueOf(params[3].toString()) == 1l){
								vo.setName(constituencyDAO.get(Long.valueOf(params[2].toString())).getName());
								
							}
							else if(Long.valueOf(params[3].toString()) == 2l){
								vo.setName(tehsilDAO.get(Long.valueOf(params[2].toString())).getTehsilName());
								
							}
							else if(Long.valueOf(params[3].toString()) == 5l){
								vo.setName(localElectionBodyDAO.get(Long.valueOf(params[2].toString())).getName() +" Muncipality" );
								
							}
							resultMap.put(Long.valueOf(params[2].toString()), vo);
						}
	
						vo.setLocationName(constiDetailsMap.get(Long.valueOf(params[2].toString())));
						vo.setIvrEnqReceived(Long.valueOf(params[1].toString()));
 						vo.setIvrEnqDelivered(Long.valueOf(params[0].toString()));
 						if(ivrCntMap != null)
 						{
 							CadreIVRResponseVO ivrVo = ivrCntMap.get(Long.valueOf(params[2].toString()));
 							if( ivrVo != null){
 							Long total = ivrVo.getReceived() + ivrVo.getNotReceived();
 							if(total  > 0)
 							{
 								vo.setTotalAnswerdCalls(total);
 								vo.setNotReceived(ivrVo.getNotReceived());
	 							vo.setReceivedPerc((ivrVo.getReceived()*100)/total);
	 							vo.setReceived(ivrVo.getReceived());
	 		      				vo.setNotReceivedPerc((ivrVo.getNotReceived()*100)/total);
 							}
 							}

 						}
 						//vo.setReceived(ivrReceivedMap.get(Long.valueOf(params[2].toString())) != null ? ivrReceivedMap.get(Long.valueOf(params[2].toString())) : 0l);
 						
	 				}
 
	 				if(printedCountDetails != null && printedCountDetails.size() > 0)
	        		{
	        			 for(Object[] params : printedCountDetails)
	        			 {
	        				 if(resultMap.get(Long.valueOf(params[1].toString())) != null){
	        				  if(Long.valueOf(params[1].toString()).longValue() == resultMap.get(Long.valueOf(params[1].toString())).getId().longValue()){ 
	        					
	        					 resultMap.get(Long.valueOf(params[1].toString())).setPrintedCount(Long.valueOf(params[0].toString()));
	        				
	        					
	        				  }	        			
	        				 }
	        			 }
	        		}	
	 				
	 				if(type.equalsIgnoreCase("Constituency")){
	 				List<Object[]> mandalTotalCount = cadreIVREnquiryDAO.getMandalRecievedCountConstituency(locationIds);
	 				if(mandalTotalCount != null && mandalTotalCount.size() > 0)
	        		{
	        			 for(Object[] params : mandalTotalCount)
	        			 {
	        				 if(resultMap.get(Long.valueOf(params[1].toString())) != null){	        				
	        					 resultMap.get(Long.valueOf(params[1].toString())).setTotal(Long.valueOf(params[0].toString()));
	        	        					
	        				  }	        			
	        			 }
	        		}	
	 				}
	 				
	 				for(Long key : resultMap.keySet())
	 				{
	 					CadreIVRResponseVO vo = resultMap.get(key);
	 					resultList.add(vo);
	 				} 
	 			}
        	 }
        	 catch(Exception e)
        	 {
        		 e.printStackTrace();
        	 }
			
         }
         
         /**
          * This Service is used for checking the images is weater good are not for card printing
          * @author Prasad Thiragabathina
          * @param dId  DistrictId
          * @param cId  ConstituencyId
          * @return List<ImageCheckVO>
          */
         public List<ImageCheckVO> getAllNewImagesForChecking(Long dId,Long cId)
         {
        	 LOG.info("Entered into getAllNewImagesForChecking service in TdpCadreReportService");
        	 List<ImageCheckVO> returnList = null;
        	 try 
        	 {
				if(dId != null && dId > 0)
				{
					List<Object[]> reqValues = tdpCadreDAO.getReqDetailsForIMageChecking(dId,cId);
					if(reqValues != null && reqValues.size() > 0)
					{
						returnList = new ArrayList<ImageCheckVO>();
						for (Object[] parms : reqValues) 
						{
							ImageCheckVO imageCheckVO = new ImageCheckVO();
							if(parms[0] != null)
							{
								imageCheckVO.setTdpCadreId((Long)parms[0]);
								imageCheckVO.setImage(parms[1].toString());
								imageCheckVO.setName(parms[2] != null ? parms[2].toString():"");
								returnList.add(imageCheckVO);
							}
							
						}
					}
				}
			 }
        	 catch (Exception e) 
        	 {
				LOG.error("Exception Raised in getAllNewImagesForChecking service in TdpCadreReportService", e);
			 }
        	 return returnList;
         }
         
         /**
          * This Service is used for getting Valid or In Valid images 
          * @author Prasad Thiragabathina
          * @param dId
          * @param cId
          * @param type
          * @return List<ImageCheckVO>
          */
         public List<ImageCheckVO> getValidOrInValidImages(Long dId,Long cId,String type)
         {
        	 LOG.info("Entered into getAllNewImagesForChecking service in TdpCadreReportService");
        	 List<ImageCheckVO> returnList = null;
        	 try 
        	 {
				if(dId != null && dId > 0)
				{
					List<Object[]> reqValues = tdpCadreImagesValidDAO.getValidOrInValidDetails(dId,cId,type);
					if(reqValues != null && reqValues.size() > 0)
					{
						returnList = new ArrayList<ImageCheckVO>();
						for (Object[] parms : reqValues) 
						{
							ImageCheckVO imageCheckVO = new ImageCheckVO();
							if(parms[0] != null)
							{
								imageCheckVO.setTdpCadreId((Long)parms[0]);
								imageCheckVO.setImage(parms[1].toString());
								imageCheckVO.setName(parms[2] != null ? parms[2].toString():"");
								returnList.add(imageCheckVO);
							}
							
						}
					}
				}
			 }
        	 catch (Exception e) 
        	 {
				LOG.error("Exception Raised in getAllNewImagesForChecking service in TdpCadreReportService", e);
			 }
        	 return returnList;
         }
         /**
          * This Service is used for saving checkg images weather valied or not
          * @author Prasad Thiragabathina
          * @param inputsList
          * @return String
          */
         public String saveCheckedImages(List<ImageCheckVO> inputsList)
         {
        	 LOG.info("Entered into saveCheckedImages service in TdpCadreReportService");
        	 String status = null;
        	 try 
        	 {
				if(inputsList != null && inputsList.size() > 0)
				{
					for (ImageCheckVO imageCheckVO : inputsList) 
					{
						if(imageCheckVO.getTdpCadreId() != null && imageCheckVO.getName() != null)
						{
							 List<TdpCadreImagesValid> tdpCadreImagesValidList = tdpCadreImagesValidDAO.checkForExists(imageCheckVO.getTdpCadreId());
							if(tdpCadreImagesValidList != null && tdpCadreImagesValidList.size() > 0)
							{
								TdpCadreImagesValid tdpCadreImagesValid = tdpCadreImagesValidList.get(0);
								tdpCadreImagesValid.setStatus(imageCheckVO.getName());
								tdpCadreImagesValidDAO.save(tdpCadreImagesValid);
							}
							else
							{
								TdpCadreImagesValid tdpCadreImagesValid = new TdpCadreImagesValid();
								tdpCadreImagesValid.setTdpCadreId(imageCheckVO.getTdpCadreId());
								tdpCadreImagesValid.setStatus(imageCheckVO.getName());
								tdpCadreImagesValidDAO.save(tdpCadreImagesValid);
							}
								
							
							
						}
						status = "SUCCESS";
					}
				}
			 } 
        	 catch (Exception e)
        	 {
        		 status = "EXCEPTION";
        		 LOG.error("Exception Raised in saveCheckedImages service in TdpCadreReportService", e);
			 }
        	 return status;
         }         
         
         
         public CadreIVRResponseVO getTotalIvrPreviousCallBasicInfo(Long stateTypeId)
         {
       	  CadreIVRResponseVO returnVo = new CadreIVRResponseVO();
       	  try{
       		  List<Long> locationTypeIds = new ArrayList<Long>();
            		 
       		  locationTypeIds.add(1l);
       		  List<Long> mandalTypeIds = new ArrayList<Long>();
       		  mandalTypeIds.add(2l);
       		  mandalTypeIds.add(5l);
       		 
       		  List<Long> localTypeIdsTemp = new ArrayList<Long>();
       		  localTypeIdsTemp.add(5l);
       		  List<Long> mandalIdsTemp = new ArrayList<Long>();
       		  mandalIdsTemp.add(2l);
       		  
       		 List<Object[]> stateConstiIds = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
			 List<Long> stateConstituencyIds = new ArrayList<Long>();
			 if(stateConstiIds != null && stateConstiIds.size() > 0){
				 for(Object[] params : stateConstiIds){					
					if(!stateConstituencyIds.contains((Long)params[0])){
						stateConstituencyIds.add((Long)params[0]);							
					}				
				}      			 
			} 

       		//List<Object[]> constituencyCnts = cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(locationTypeIds,null,null,stateConstituencyIds);
       		  
       		//List<Object[]> mandalCnts =cadreIVREnquiryDAO.getNoOfLocationCountByTypeId(mandalTypeIds,null,null,stateConstituencyIds);
	
 	
       		Long totalPrintDtls= zebraPrintDetailsDAO.getTotalPrintingCountByState(stateTypeId);
       
       		
       		returnVo.setPrintedCount(totalPrintDtls != null ? totalPrintDtls :0L);       			

       	    BigDecimal constituencyReceivedCount = cadreIVREnquiryDAO.getTotalReceivedCount(null,null,locationTypeIds,stateConstituencyIds);
       		returnVo.setReceived(constituencyReceivedCount != null ? constituencyReceivedCount.longValue() : 0l);
       		BigDecimal constituencyDeliveredCount = cadreIVREnquiryDAO.getTotalDeliveredCount(null,null,locationTypeIds,stateConstituencyIds);
       		returnVo.setNotReceived(constituencyDeliveredCount != null ? constituencyDeliveredCount.longValue() : 0l);
       		
       		BigDecimal mandalReceivedCount = cadreIVREnquiryDAO.getTotalReceivedCount(null,null,mandalIdsTemp,stateConstituencyIds);
        	returnVo.setTotalAnswerdCalls(mandalReceivedCount != null ? mandalReceivedCount.longValue() : 0l);
        	BigDecimal mandalDeliveredCount = cadreIVREnquiryDAO.getTotalDeliveredCount(null,null,mandalTypeIds,stateConstituencyIds);
        	returnVo.setNotMember(mandalDeliveredCount != null ? mandalDeliveredCount.longValue() : 0l);
        	
        	BigDecimal localbodyReceivedCount = cadreIVREnquiryDAO.getTotalReceivedCount(null,null,localTypeIdsTemp,stateConstituencyIds);
        	returnVo.setLocalbodyReceivedCount(localbodyReceivedCount != null ? localbodyReceivedCount.longValue() : 0l);
        	
        		
       		//returnVo.setTotalCalls(Long.valueOf(constituencyCnts.size()));
       		//returnVo.setTotalIvrCalls(Long.valueOf( mandalCnts.size()));
       		
       		returnVo.setConstiReceivedPerc(new BigDecimal((returnVo.getReceived()*100.0/returnVo.getPrintedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			returnVo.setConstiDeliveredPerc(new BigDecimal((returnVo.getNotReceived()*100.0/returnVo.getPrintedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			returnVo.setUrbanPerc(new BigDecimal((returnVo.getLocalbodyReceivedCount()*100.0/returnVo.getPrintedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			returnVo.setMandalPerc(new BigDecimal((returnVo.getTotalAnswerdCalls()*100.0/returnVo.getPrintedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			returnVo.setDeliveredPerc(new BigDecimal((returnVo.getNotMember()*100.0/returnVo.getPrintedCount())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
       	 
       	  } catch(Exception e)
       	  {
       		 e.printStackTrace(); 
       	  }
			return returnVo;
         }
          
         
         
         
         public CadreIVRResponseVO getMandalInfoManagerRecievedCountByConstituency(Long constituencyId)
         {
        	 CadreIVRResponseVO returnVo = new CadreIVRResponseVO(); 
        	 List<CadreIVRResponseVO> resultList = new ArrayList<CadreIVRResponseVO>();
        	 try{
 
        		 List<Long> constituencyIds = new ArrayList<Long>();
	        	 List<Long> locationTypeIds = new ArrayList<Long>();
	        	 List<Long> mandalIds = new ArrayList<Long>();
        		 List<Long> localbodyIds = new ArrayList<Long>();
        		 locationTypeIds.add(2l);
        		 locationTypeIds.add(5l);
        		 constituencyIds.add(constituencyId);
        		
        		 List<Object[]> list1 = cadreIVREnquiryDAO.getLocationIdsByTypeId(locationTypeIds,null,null,constituencyIds);
        		 
        		 if(list1 != null && list1.size() > 0)
        		 {
        			 for(Object[] params : list1)
        			 {
        				
        				 if(Long.valueOf(params[1].toString()).longValue()  == 2l)
        					 mandalIds.add(Long.valueOf(params[0].toString()).longValue());
        				 else  if(Long.valueOf(params[1].toString()).longValue() == 5l)
        					 localbodyIds.add(Long.valueOf(params[0].toString()).longValue());
        			 }
        		 }

    			if(mandalIds != null && mandalIds.size()>0)
    				setIvrDataByConstiId(mandalIds,IConstants.TEHSIL,resultList);
    			if(localbodyIds != null && localbodyIds.size()>0)
    				setIvrDataByConstiId(localbodyIds,IConstants.LOCAL_ELECTION_BODY,resultList);
        		
        		 returnVo.setApList(resultList);
        		
        		 
        	 }
        	 catch(Exception e)
        	 {
        		 e.printStackTrace(); 
        	 }
			return returnVo;
         }
         
         public void setIvrDataByConstiId(List<Long> locationIds,String type,List<CadreIVRResponseVO> resultList)
         {
        	 try{
        		 Map<Long,CadreIVRResponseVO> resultMap = new HashMap<Long, CadreIVRResponseVO>();
        		 List<Object[]> list = cadreIvrResponseDAO.getIvrCountByLocationIdsAndType(locationIds,type);
        		 Map<Long,CadreIVRResponseVO> ivrCntMap = new HashMap<Long, CadreIVRResponseVO>();
        		 List<String> successNos =new ArrayList<String>();
				 successNos.add("1");successNos.add("2");successNos.add("3");
				 if(list != null && list.size() > 0)
	 			 {
	    			 for(Object[] ivrCountInfo : list)
		 				{
		 					
	    				 	CadreIVRResponseVO vo = ivrCntMap.get((Long)ivrCountInfo[3]);
							if(vo == null)
							{
								    vo = new CadreIVRResponseVO();
									ivrCntMap.put((Long)ivrCountInfo[3], vo);
							}
							
							if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
							{
								if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("1")) // response key is 1 //received
									vo.setReceived(vo.getReceived() + (Long)ivrCountInfo[0]);
								else if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("2"))
									vo.setNotReceived(vo.getNotReceived() + (Long)ivrCountInfo[0]);
								else if(ivrCountInfo[1] !=null && ivrCountInfo[1].toString().equalsIgnoreCase("3"))
									vo.setNotMember(vo.getNotMember()  + (Long)ivrCountInfo[0]);
								else if(ivrCountInfo[1] !=null && !successNos.contains(ivrCountInfo[1].toString()))
									vo.setNoOptionSel(vo.getNoOptionSel() + (Long)ivrCountInfo[0]);
							}
		 				}
	 			 }

        		 List<Object[]> printedCountDetails = zebraPrintDetailsDAO.getLocationWisePrintedCountDetails(locationIds, type);
        		 
        		 Long locationTypeId  = 0L;
        		 if(type.equalsIgnoreCase(IConstants.TEHSIL)){
        			  locationTypeId = 2l;        		
        		 }
        		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY)){
        			  locationTypeId = 5l;
        		 }  
        		
        		 List<Object[]> list1 = cadreIVREnquiryDAO.getDeliveredAndReceivedCount(locationIds,null,null,locationTypeId);
		 
        
	        	 if(list1 != null && list1.size() > 0) {
	 				for(Object[] params : list1){
	 					CadreIVRResponseVO vo = resultMap.get(Long.valueOf(params[2].toString()));
						if(vo == null){
 						
							vo = new CadreIVRResponseVO();
							vo.setId(Long.valueOf(params[2].toString()));
							if(Long.valueOf(params[3].toString()) == 2l){
								vo.setName(tehsilDAO.get(Long.valueOf(params[2].toString())).getTehsilName());	
							}
							else if(Long.valueOf(params[3].toString()) == 5l){
								vo.setName(localElectionBodyDAO.get(Long.valueOf(params[2].toString())).getName() +" Muncipality" );	
							}
							resultMap.put(Long.valueOf(params[2].toString()), vo);
						}

						vo.setIvrEnqReceived(Long.valueOf(params[1].toString()));
 						vo.setIvrEnqDelivered(Long.valueOf(params[0].toString()));
 						
 						if(ivrCntMap != null)
 						{
 							CadreIVRResponseVO ivrVo = ivrCntMap.get(Long.valueOf(params[2].toString()));
 							if(ivrVo != null){
 							Long total = ivrVo.getReceived() + ivrVo.getNotReceived();
 							if(total  > 0)
 							{
 								vo.setTotalAnswerdCalls(total);
 								vo.setNotReceived(ivrVo.getNotReceived());
	 							vo.setReceivedPerc((ivrVo.getReceived()*100)/total);
	 							vo.setReceived(ivrVo.getReceived());
	 		      				vo.setNotReceivedPerc((ivrVo.getNotReceived()*100)/total);
 							}
 							}
 						}
	 				}
 
	 				if(printedCountDetails != null && printedCountDetails.size() > 0){
	        			 for(Object[] params : printedCountDetails){
	        				 if(resultMap.get(Long.valueOf(params[1].toString())) != null){
	        				  if(Long.valueOf(params[1].toString()).longValue() == resultMap.get(Long.valueOf(params[1].toString())).getId().longValue()){ 
	        					 resultMap.get(Long.valueOf(params[1].toString())).setPrintedCount(Long.valueOf(params[0].toString()));
	        				  }	        			
	        				 }
	        			 }
	        		}	

	 				for(Long key : resultMap.keySet()){
	 					CadreIVRResponseVO vo = resultMap.get(key);
	 					resultList.add(vo);
	 				} 
	 			}
        	 }
        	 catch(Exception e)
        	 {
        		 e.printStackTrace();
        	 }
			
         }
     	public List<BasicVO> getAllCampaigns(){
    		List<BasicVO> returnList = new ArrayList<BasicVO>();
    		
    		try{
    		    List<Object[]> campaignsList = ivrCampaignDAO.getAllCampaigns();
    			for(Object[] campaign:campaignsList){
    				BasicVO info = new BasicVO();
    				info.setId((Long)campaign[0]);
    				info.setName(campaign[1].toString());
    				returnList.add(info);
    			}
            }catch(Exception e){
            	LOG.error("Exception rised in getAllCampaigns",e);
    		}
    		return returnList;
    	}
       
    	public List<BasicVO> getAllIVROptions(Long campaignId){
    		List<BasicVO> returnList = new ArrayList<BasicVO>();
    		
    		try{
    		    List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(campaignId);
    			for(Object[] options:optionsList){
    				BasicVO info = new BasicVO();
    				info.setId((Long)options[0]);
    				info.setName(options[1].toString());
    				returnList.add(info);
    			}
            }catch(Exception e){
            	LOG.error("Exception rised in getAllIVROptions",e);
    		}
    		return returnList;
    	}
    	
    	public CadreIVRResponseVO getMatchedOptions(List<CadreIVRResponseVO> list,Long id)
    	{
    		CadreIVRResponseVO returnVO = null;
    		try {
    			
    			if(list != null && list.size()>0)
    			{
    				for (CadreIVRResponseVO vo : list)
    				{
    					if(vo.getId().longValue() == id.longValue())
    					{
    						return vo;
    					}
    				}
    			}
    		} catch (Exception e) {
    			LOG.error("Exception raised in getMatchedOptions", e);
    		}
    		return returnVO;
    	}
    	public List<BasicVO> getTdpCommitteeMandalByConstituency(Long constituencyId,Long enrollmentId,String committeeType){
    		List<BasicVO> returnList = new ArrayList<BasicVO>();
    		BasicVO vo = null;
    		try{
    		    List<Object[]> mandalList = tdpCommitteeDAO.getTdpCommitteeMandalByConstituency(constituencyId,enrollmentId,committeeType);
    		    List<Object[]> localBodiesList = tdpCommitteeDAO.getTdpCommitteeLocalBodiesByConstituency(constituencyId, enrollmentId,committeeType);
    			if(mandalList != null && mandalList.size() > 0){
    				 for(Object[] obj:mandalList){
    	    				vo = new BasicVO();
    	    				vo.setId(Long.valueOf("2"+obj[0].toString()));
    	    				vo.setName(obj[1].toString()+" Mandal");
    	    				returnList.add(vo);
    	    			}
    			}
    			if(localBodiesList != null && localBodiesList.size() > 0){
    				for (Object[] objects : localBodiesList) {
    					vo = new BasicVO();
    		        	vo.setId(Long.valueOf("1"+objects[0].toString()));
    		        	vo.setName(objects[1].toString()+" Municipality ");
    		        	returnList.add(vo);
					}
    			}
            }catch(Exception e){
            	LOG.error("Exception rised in getTdpCommitteeMandalByConstituency",e);
    		}
    		return returnList;
    	}
    	public void getConstituencyDetailsForDistricts(List<Long> districtIdList,SurveyTransactionVO returnVO ){
    		List<Object[]> constituencyList = constituencyDAO.getConstituencysByDistrictId(districtIdList);
    		
    		List<SurveyTransactionVO> returnList = null;
    		if(constituencyList != null && constituencyList.size()>0)
    		{
    			returnList = new ArrayList<SurveyTransactionVO>(0);
    			for (Object[] printStatus : constituencyList) 
    			{
    				SurveyTransactionVO reportVo = new SurveyTransactionVO();
    				reportVo.setId(printStatus[0] != null ? Long.valueOf(printStatus[0].toString().trim()):0L);
    				reportVo.setName(printStatus[1] != null ? printStatus[1].toString().trim():"");
    				returnList.add(reportVo);
    			}
    		}
    		
    		if(returnList != null && returnList.size()>0)
    		{
    			returnVO.setSurveyTransactionVOList(returnList);
    		}
    	}
}

