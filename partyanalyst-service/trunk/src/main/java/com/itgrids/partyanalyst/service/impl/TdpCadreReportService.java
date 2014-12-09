package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
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

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICallCenterFeedbackDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalNameConstantDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterFeedbackDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterCommentDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreSmsStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.dto.TdpCadreSmsStatusVO;
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;
import com.itgrids.partyanalyst.model.LocalNameConstant;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterFeedback;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterComment;
import com.itgrids.partyanalyst.model.TdpCadreSmsStatus;
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
					List<TdpCadreLocationWiseReportVO> ageWiseReportList  = ageWiseDetailsForConstituencies(constituencyIds,11L);
					List<TdpCadreLocationWiseReportVO> genderWiseReportList  = genderWiseDetailsForConstituencies(constituencyIds,11L); 
					
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
				 registeredCountList    = tdpCadreDAO.gettingRegisteredVotersForParliaments(statusSeacrhLoctaionIds);
				 printedCountList 		= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"printStatus");
				 errorCountList			= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"errorStatus");
				 totalPushedCountList 	= zebraPrintDetailsDAO.getParliamentWiseResults(statusSeacrhLoctaionIds,"totalCount"); 
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
					vo.setMembershipNo(params[5] != null ? params[5].toString() : "");
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
						registrationVO.setImageBase64String(zebraPrintDetails.getImage());
						
						registrationVO.setVoterName(StringEscapeUtils.unescapeJava(zebraPrintDetails.getVoterName()));					
						registrationVO.setConstituencyId(StringEscapeUtils.unescapeJava(zebraPrintDetails.getConsiteuncyName())+" "+"( "+areaInTeluguFontMap.get("constituency")+" )");
						registrationVO.setAddress(StringEscapeUtils.unescapeJava(zebraPrintDetails.getDistrictName())+" "+"( "+areaInTeluguFontMap.get("district")+" )");
						registrationVO.setPreviousEnrollmentNumber(zebraPrintDetails.getMemberShipNumber());
						
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
				queryStr.append(" and substring(model.memberShipNo,5) = '"+membership+"'");
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
						vo.setPreviousEnrollmentNumber(zebraPrintDetails[3] != null ? zebraPrintDetails[3].toString().substring(4) : "");
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
}
