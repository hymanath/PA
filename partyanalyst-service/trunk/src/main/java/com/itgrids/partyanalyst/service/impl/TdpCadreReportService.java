package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreReportService implements ITdpCadreReportService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	
	private ITdpCadreReportService tdpCadreReportService;
	private ITdpCadreDAO tdpCadreDAO;
	private IBoothDAO boothDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ICadreDashBoardService cadreDashBoardService;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IConstituencyDAO constituencyDAO;
	
	
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
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
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
}
