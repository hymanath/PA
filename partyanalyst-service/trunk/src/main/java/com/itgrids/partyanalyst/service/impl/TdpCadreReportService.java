package com.itgrids.partyanalyst.service.impl;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreReportService implements ITdpCadreReportService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	
	private ITdpCadreReportService tdpCadreReportService;
	private ITdpCadreDAO tdpCadreDAO;
	private IBoothDAO boothDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	
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

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public TdpCadreLocationWiseReportVO generateExcelReportForTdpCadre(List<Long> constituencyIdsList)
	{
		TdpCadreLocationWiseReportVO returnVO = new TdpCadreLocationWiseReportVO();
		try {
			if(constituencyIdsList != null && constituencyIdsList.size()>0)
			{
				for (Long constituencyId : constituencyIdsList)
				{
					List<Long> constituencyIds = new ArrayList<Long>();
					constituencyIds.add(constituencyId);
					
					List<TdpCadreLocationWiseReportVO> locationWiseReportsList = getLocationWiseReportDetailsForExcel(constituencyIds);
					List<TdpCadreLocationWiseReportVO> ageWiseReportList  = ageWiseDetailsForConstituencies(constituencyIds,10L);
					List<TdpCadreLocationWiseReportVO> genderWiseReportList  = genderWiseDetailsForConstituencies(constituencyIds,10L);
					
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
					
					//genereteOriginalExcelReport(returnVO);
				}
			}			
		} catch (Exception e) {
			LOG.error(" exception occured in generateExcelReportForTdpCadre () at TdpCadreReportService ",e);
		}
		return returnVO;
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
						  genderVO.setTotalVoters(genderVO.getTargetedCadre()==null?(Long)obj[2]:genderVO.getTargetedCadre()+(Long)obj[2]);
						
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
					 
					  Double agePerc=(ageRangeVO.getCadresInAge()*100.0)/(ageRangeVO.getRegisteredCadre());
					  //Round the double value to 2 decimal values.
					   DecimalFormat df2 = new DecimalFormat("###.##");
					   ageRangeVO.setAgePerc(Double.valueOf(df2.format(agePerc)));

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



public TdpCadreLocationWiseReportVO genereteOriginalExcelReport(TdpCadreLocationWiseReportVO constituencyReportVO1)
{
	try {
		HSSFWorkbook workbook = new HSSFWorkbook(); 
		
		DecimalFormat df = new DecimalFormat("##.##");
	    Font font = workbook.createFont();
	    font.setFontName("Calibri");
	    font.setFontHeightInPoints((short)12);
	    
	    CellStyle style5 = workbook.createCellStyle();
	    style5.setFont(font);
	    style5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style5.setAlignment(CellStyle.ALIGN_CENTER);
	    
	    
	    CellStyle style = workbook.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);
	    style.setFont(font);
	    
	    //for data.
	    Font font1 = workbook.createFont();
	    font1.setFontName("Calibri");
	    font1.setFontHeightInPoints((short)10);
	    
	    CellStyle style1 = workbook.createCellStyle();
	    style1.setFont(font1);
	    style1.setFillForegroundColor(HSSFColor.YELLOW.index);
	    style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style1.setAlignment(CellStyle.ALIGN_CENTER);
	    
	    //for heading.
	    Font font2 = workbook.createFont();
	    font2.setFontName("Calibri");
	    font2.setFontHeightInPoints((short)10);
	    
	    CellStyle style2 = workbook.createCellStyle();
	    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style2.setAlignment(CellStyle.ALIGN_CENTER);
	    style2.setFont(font2);

	    String constituencyName = constituencyReportVO1.getName();
	    
	    System.out.println("===================================================================================");
		System.out.println(constituencyName +" Constituency Excel Report generating...");
		LOG.info( constituencyName +" Constituency Excel Report generating... ");
		
		HSSFSheet constituencyWiseSheet  ;
		int randomNumber = -100;
		while(randomNumber < 0)
		{
			randomNumber = new Random().nextInt()*10000000;
		}
	
		String filename="D:/apache-tomcat-6.0.37/webapps/PartyAnalyst/Reports"+"/"+constituencyName+"_"+randomNumber+".xls";
		FileOutputStream 	out=new FileOutputStream(filename);
	    
	    constituencyWiseSheet = workbook.createSheet(IConstants.CONSTITUENCY);
	   	    
			constituencyWiseSheet.addMergedRegion(new CellRangeAddress(1,1,1,5 )); // TOTAL OVERVIEW  HEADING
			constituencyWiseSheet.addMergedRegion(new CellRangeAddress(5,5,1,4 )); //GENDER OVERVIEW HEADING
			constituencyWiseSheet.addMergedRegion(new CellRangeAddress(10,10,1,6 )); // AGE OVER VIEW HEADING
			
			HSSFRow row1 = constituencyWiseSheet.createRow((short)1);							
		    Cell cell1 = row1.createCell((short)1);
		    cell1.setCellValue(" CONSTITUENCY WISE OVER VIEW  ");
		    cell1.setCellStyle(style);
		    
		    HSSFRow row2 = constituencyWiseSheet.createRow((short)2);
		    Cell cell2 = row2.createCell((short)1);
		    cell2.setCellValue(IConstants.CONSTITUENCY);
		    cell2.setCellStyle(style1);   
			    
		    cell2 = row2.createCell((short)2);
		    cell2.setCellValue(" TOTAL VOTERS ");
		    cell2.setCellStyle(style1);
		   
		    cell2 = row2.createCell((short)3);
		    cell2.setCellValue(" TARGETED CADRE ");
		    cell2.setCellStyle(style1);
		    
		    cell2 = row2.createCell((short)4);
		    cell2.setCellValue(" REGISTRIED CADRE ");
		    cell2.setCellStyle(style1);
		    
		    cell2 = row2.createCell((short)5);
		    cell2.setCellValue(" REGISTERED CADRE PERCENTAGE ");
		    cell2.setCellStyle(style1);
		    
		    HSSFRow row3 = constituencyWiseSheet.createRow((short)3);
		    Cell cell3 = row3.createCell((short)1);
		    cell3.setCellValue(constituencyReportVO1.getName() != null ? constituencyReportVO1.getName() : "");
		    cell3.setCellStyle(style2);
		    
		    cell3 = row3.createCell((short)2);
		    cell3.setCellValue(constituencyReportVO1.getTotalVoters() != null ?constituencyReportVO1.getTotalVoters():0L);
		    cell3.setCellStyle(style2);
		    
		    cell3 = row3.createCell((short)3);
		    cell3.setCellValue(constituencyReportVO1.getTargetedCadre() != null ? constituencyReportVO1.getTargetedCadre() :0L);
		    cell3.setCellStyle(style2);
		    			    
		    cell3 = row3.createCell((short)4);
		    cell3.setCellValue(constituencyReportVO1.getRegisteredCadre() != null ? constituencyReportVO1.getRegisteredCadre() :0L);
		    cell3.setCellStyle(style2);
		    
		    cell3 = row3.createCell((short)5);
		    cell3.setCellValue(constituencyReportVO1.getPercentage() != null ? constituencyReportVO1.getPercentage().toString() :"");
		    cell3.setCellStyle(style2);
		    
		    HSSFRow row5 = constituencyWiseSheet.createRow(5);							
		    Cell cell5 = row5.createCell((short)1);
		    cell5.setCellValue(" GENDER WISE OVER VIEW  ");
		    cell5.setCellStyle(style);
		    
		    HSSFRow row6 = constituencyWiseSheet.createRow((short)6);
		    Cell cell6 = row6.createCell((short)1);
		    cell6.setCellValue(" GENDER ");
		    cell6.setCellStyle(style1);   
			    
		    cell6 = row6.createCell((short)2);
		    cell6.setCellValue(" TOTAL ");
		    cell6.setCellStyle(style1); 
		    
		    cell6 = row6.createCell((short)3);
		    cell6.setCellValue(" REGISTERED ");
		    cell6.setCellStyle(style1); 
		    
		    cell6 = row6.createCell((short)4);
		    cell6.setCellValue(" REGISTERED PERCENTAGE ");
		    cell6.setCellStyle(style1); 
		    
		    HSSFRow row7 = constituencyWiseSheet.createRow(7);
		    Cell cell7 = row7.createCell((short)1);
		    cell7.setCellValue(" MALE ");
		    cell7.setCellStyle(style1);
		   
		    cell7 = row7.createCell((short)2);
		    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getTotalVoters() :0L);
		    cell7.setCellStyle(style2);
		    
		    cell7 = row7.createCell((short)3);
		    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getCadresCount():0L);
		    cell7.setCellStyle(style2);
		    
		    cell7 = row7.createCell(4);
		    cell7.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(0).getGenderPerc():0.0d);
		    cell7.setCellStyle(style2);
		    
		    HSSFRow row8 = constituencyWiseSheet.createRow(8);
		    Cell cell8 = row8.createCell((short)1);
		    cell8.setCellValue(" FEMALE ");
		    cell8.setCellStyle(style1);
		    
		    cell8 = row8.createCell((short)2);
		    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getTotalVoters() :0L);
		    cell8.setCellStyle(style2);
		    
		    cell8 = row8.createCell((short)3);
		    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getCadresCount() :0L);
		    cell8.setCellStyle(style2);
		    
		    cell8 = row8.createCell((short)4);
		    cell8.setCellValue(constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() != null ? constituencyReportVO1.getPanchayatWiseList().get(0).getTehsilWiseList().get(1).getGenderPerc() :0.0d);
		    cell8.setCellStyle(style2);
		    
		    HSSFRow row10 = constituencyWiseSheet.createRow(10);
		    Cell cell10 = row10.createCell((short)1);
		    cell10.setCellValue(" AGE WISE OVER VIEW  ");
		    cell10.setCellStyle(style);
		    
		    HSSFRow row11 = constituencyWiseSheet.createRow(11);
		    
		    Cell cell11 = row11.createCell((short)1);
		    cell11.setCellValue(" AGE RANGE ");
		    cell11.setCellStyle(style1);
		    
		    cell11 = row11.createCell((short)2);
		    cell11.setCellValue(" 18-25 ");
		    cell11.setCellStyle(style1);
		    
		    cell11 = row11.createCell((short)3);
		    cell11.setCellValue(" 26-35 ");
		    cell11.setCellStyle(style1);
		    
		    cell11 = row11.createCell((short)4);
		    cell11.setCellValue(" 36-45 ");
		    cell11.setCellStyle(style1);
		    
		    cell11 = row11.createCell((short)5);
		    cell11.setCellValue(" 46-60 ");
		    cell11.setCellStyle(style1);
		    
		    cell11 = row11.createCell((short)6);
		    cell11.setCellValue(" Above 60 ");
		    cell11.setCellStyle(style1);
		    constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList();
		    HSSFRow row12 = constituencyWiseSheet.createRow(12);
		    
		    Cell cell12 = row12.createCell((short)1);
		    cell12.setCellValue(" TOTAL ");
		    cell12.setCellStyle(style1);
		    
		    cell11 = row12.createCell((short)2);
		    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getTotalVoters():0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row12.createCell((short)3);
		    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getTotalVoters():0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row12.createCell((short)4);
		    cell11.setCellValue( constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getTotalVoters() != null ?  constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getTotalVoters():0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row12.createCell((short)5);
		    cell11.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getTotalVoters() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getTotalVoters():0L);
		    cell11.setCellStyle(style2);
		    
		    cell11 = row12.createCell((short)6);
		    cell11.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getTotalVoters() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getTotalVoters():0L);
		    cell11.setCellStyle(style2);
		    
		    
		    HSSFRow row13 = constituencyWiseSheet.createRow(13);
		    
		    Cell cell13 = row13.createCell((short)1);
		    cell13.setCellValue(" REGISTERED ");
		    cell13.setCellStyle(style1);
		    
		    cell13 = row13.createCell((short)2);
		    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getCadresInAge():0L);
		    cell13.setCellStyle(style2);
		    
		    cell13 = row13.createCell((short)3);
		    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getCadresInAge():0L);
		    cell13.setCellStyle(style2);
		    
		    cell13 = row13.createCell((short)4);
		    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getCadresInAge():0L);
		    cell13.setCellStyle(style2);
		    
		    cell13 = row13.createCell((short)5);
		    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getCadresInAge():0L);
		    cell13.setCellStyle(style2);
		    
		    cell13 = row13.createCell((short)6);
		    cell13.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getCadresInAge() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getCadresInAge():0L);
		    cell13.setCellStyle(style2);
		    
		    HSSFRow row14 = constituencyWiseSheet.createRow(14);
		    
		    Cell cell14 = row14.createCell((short)1);
		    cell14.setCellValue(" PERCENTAGE ");
		    cell14.setCellStyle(style1);
		    
		    cell14 = row14.createCell((short)2);
		    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(0).getAgePerc():0L);
		    cell14.setCellStyle(style2);
		    
		    cell14 = row14.createCell((short)3);
		    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(1).getAgePerc():0L);
		    cell14.setCellStyle(style2);
		    
		    cell14 = row14.createCell((short)4);
		    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(2).getAgePerc():0L);
		    cell14.setCellStyle(style2);
		    
		    cell14 = row14.createCell((short)5);
		    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(3).getAgePerc():0L);
		    cell14.setCellStyle(style2);
		    
		    cell14 = row14.createCell((short)6);
		    cell14.setCellValue(constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getAgePerc() != null ? constituencyReportVO1.getTehsilWiseList().get(0).getTdpCadreLocationWiseReportVOList().get(4).getAgePerc():0L);
		    cell14.setCellStyle(style2);
		    
		    
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(15,15,1,6 )); // MANDAL WISE OVER VIEW 
		    
		    HSSFRow row15 = constituencyWiseSheet.createRow((short)15);
		    Cell cell15 = row15.createCell((short)1);
		    cell15.setCellValue(" MANDAL WISE OVER VIEW  ");
		    cell15.setCellStyle(style);
		    
		    HSSFRow row16 = constituencyWiseSheet.createRow((short)16);
		    Cell cell16 = row16.createCell((short)1);
		    cell16.setCellValue(IConstants.TEHSIL);
		    cell16.setCellStyle(style1);   
			    
		    cell16 = row16.createCell((short)2);
		    cell16.setCellValue(" TOTAL VOTERS ");
		    cell16.setCellStyle(style1);
		   
		    cell16 = row16.createCell((short)3);
		    cell16.setCellValue(" TARGETED CADRE ");
		    cell16.setCellStyle(style1);
		    
		    cell16 = row16.createCell((short)4);
		    cell16.setCellValue(" REGISTRIED CADRE ");
		    cell16.setCellStyle(style1);
		    
		    cell16 = row16.createCell((short)5);
		    cell16.setCellValue(" REGISTRIED CADRE PERCENTAGE ");
		    cell16.setCellStyle(style1);
		    List<TdpCadreLocationWiseReportVO> mandalsList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getTehsilWiseList();
		    short count = 17;
		    if(mandalsList != null && mandalsList.size()>0)
		    {
		    	for (TdpCadreLocationWiseReportVO mandalsVo : mandalsList) 
		    	{
		    		HSSFRow tehsilRow = constituencyWiseSheet.createRow((short)count);
		    		 Cell tehsilCell = tehsilRow.createCell((short)1);
		    		 tehsilCell.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
		    		 tehsilCell.setCellStyle(style2);
		    		 
		    		 tehsilCell = tehsilRow.createCell((short)2);
		    		 tehsilCell.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
		    		 tehsilCell.setCellStyle(style2);
				    
		    		 tehsilCell = tehsilRow.createCell((short)3);
		    		 tehsilCell.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
		    		 tehsilCell.setCellStyle(style2);
				    			    
		    		 tehsilCell = tehsilRow.createCell((short)4);
		    		 tehsilCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString() :" -- ");
		    		 tehsilCell.setCellStyle(style2);
				    
		    		 tehsilCell = tehsilRow.createCell((short)5);
				     tehsilCell.setCellValue(mandalsVo.getPercentage() != null ? mandalsVo.getPercentage().toString() :" -- ");
				     tehsilCell.setCellStyle(style2);
					 
				     count = (short) (count+1);
				}
		    }
		   
		    count = (short) (count+1);
		    constituencyWiseSheet.addMergedRegion(new CellRangeAddress(count,count,1,6 )); // PANCHAYAT WISE OVER VIEW 
		    HSSFRow row = constituencyWiseSheet.createRow((short)count);
		    Cell cell = row.createCell((short)1);
		    cell.setCellValue(" PANCHAYAT WISE OVER VIEW  ");
		    cell.setCellStyle(style);
		    
		    count = (short) (count+1);
		    
		    HSSFRow textRow = constituencyWiseSheet.createRow((short)count);
		    Cell textCell = textRow.createCell((short)1);
		    textCell.setCellValue(IConstants.PANCHAYAT);
		    textCell.setCellStyle(style1);   
			    
		    textCell = textRow.createCell((short)2);
		    textCell.setCellValue(" TOTAL VOTERS ");
		    textCell.setCellStyle(style1);
		   
		    textCell = textRow.createCell((short)3);
		    textCell.setCellValue(" TARGETED CADRE ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)4);
		    textCell.setCellValue(" REGISTRIED CADRE ");
		    textCell.setCellStyle(style1);
		    
		    textCell = textRow.createCell((short)5);
		    textCell.setCellValue(" REGISTRIED CADRE PERCENTAGE ");
		    textCell.setCellStyle(style1);
		    
		    count = (short) (count+1);
		    List<TdpCadreLocationWiseReportVO> panchayatList = constituencyReportVO1.getTdpCadreLocationWiseReportVOList().get(0).getPanchayatWiseList();
		    
		    if(panchayatList != null && panchayatList.size()>0)
		    {
		    	for (TdpCadreLocationWiseReportVO mandalsVo : panchayatList) 
		    	{
		    		HSSFRow panchaytrow = constituencyWiseSheet.createRow((short)count);
		    		 Cell panchaytCell = panchaytrow.createCell((short)1);
		    		 panchaytCell.setCellValue(mandalsVo.getName() != null ? mandalsVo.getName() : "");
		    		 panchaytCell.setCellStyle(style2);
		    		 
		    		 panchaytCell = panchaytrow.createCell((short)2);
		    		 panchaytCell.setCellValue(mandalsVo.getTotalVoters() != null ?mandalsVo.getTotalVoters().toString():" -- ");
		    		 panchaytCell.setCellStyle(style2);
				    
		    		 panchaytCell = panchaytrow.createCell((short)3);
		    		 panchaytCell.setCellValue(mandalsVo.getTargetedCadre() != null ? mandalsVo.getTargetedCadre().toString() :" -- ");
		    		 panchaytCell.setCellStyle(style2);
				    			    
		    		 panchaytCell = panchaytrow.createCell((short)4);
		    		 panchaytCell.setCellValue(mandalsVo.getRegisteredCadre() != null ? mandalsVo.getRegisteredCadre().toString() :" -- ");
		    		 panchaytCell.setCellStyle(style2);
				    
		    		 panchaytCell = panchaytrow.createCell((short)5);
		    		 panchaytCell.setCellValue(mandalsVo.getPercentage() != null ? mandalsVo.getPercentage().toString() :" -- ");
		    		 panchaytCell.setCellStyle(style2);
					 
				     count = (short) (count+1);
				}
		    }
		    
	    workbook.write(out);
	    
	    System.out.println( constituencyName +" Constituency Excel Report generation completed. ");
	    LOG.info( constituencyName +" Constituency Excel Report generation completed. ");
	    
	    System.out.println("===================================================================================");
	    
	} catch (Exception e) {
		LOG.error(" exception occured in genereteOriginalExcelReport() @ TdpCadreReportService class.",e);
	}
	
	return constituencyReportVO1;
}
}
