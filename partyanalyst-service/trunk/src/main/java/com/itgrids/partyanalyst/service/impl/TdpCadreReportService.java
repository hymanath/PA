package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
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


	public void generateExcelReportForTdpCadre()
	{
		try {
			List<TdpCadreLocationWiseReportVO> constituncyReportList = new ArrayList<TdpCadreLocationWiseReportVO>();
			
			if(constituncyReportList != null && constituncyReportList.size() >0)
			{
				for (TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO : constituncyReportList) 
				{
					
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in generateExcelReportForTdpCadre () at TdpCadreReportService ",e);
		}
	}
	
	
	public List<TdpCadreLocationWiseReportVO> getLocationWiseReportDetailsForExcel(List<Long> constituencyIds){
		List<TdpCadreLocationWiseReportVO> resultList = new ArrayList<TdpCadreLocationWiseReportVO>();
		try{
		
			List<Object[]> constiCadreCount = null;			
			List<Object[]> constiVoterCount = null;
			List<Object[]> panchayatCadreCount = null;			
			List<Object[]> panchayatVoterCount = null;
			List<Object[]> tehsilCadreCount = null;			
			List<Object[]> tehsilVoterCount = null;
			List<Object[]> localCadreCount = null;			
			List<Object[]> localVoterCount = null;
			
			List<Long> tehsilIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			List<Long> panchayatIds = new ArrayList<Long>();
			
						
				
				constiCadreCount = tdpCadreDAO.getLocationWiseCount(constituencyIds,IConstants.CONSTITUENCY);
				
				constiVoterCount = voterInfoDAO.getVotersCountInConstituencies(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				
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
							  vo.setRegisteredCadre(params[0] != null ? (Long)params[0] : 0);							
							  String percentage ="";
							  percentage = (new BigDecimal(vo.getRegisteredCadre()*(100.0)/vo.getTargetedCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							  vo.setPercentage(percentage);							  
						  }
					}				
				}

				localbodyIds = boothDAO.getLocalbodiesByConstituencyIds(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);		 
				tehsilIds = 	boothDAO.getTehsilsByConstituencyIds(constituencyIds, IConstants.VOTER_DATA_PUBLICATION_ID);					
	    	  	
				if(tehsilIds != null && tehsilIds.size() > 0){									   
					tehsilCadreCount = tdpCadreDAO.getLocationWiseCount(tehsilIds,IConstants.TEHSIL);
					tehsilVoterCount = voterInfoDAO.getVotersCountInATehsilList(tehsilIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					setData(tehsilVoterCount,tehsilCadreCount,IConstants.TEHSIL,resultList);
				    
				 }
				if(localbodyIds != null && localbodyIds.size() > 0){				
					localCadreCount = tdpCadreDAO.getLocationWiseCount(localbodyIds,IConstants.LOCAL_ELECTION_BODY);
					localVoterCount = voterInfoDAO.getVotersCountInALocalBodyList(localbodyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					setData(localVoterCount,localCadreCount,IConstants.LOCAL_ELECTION_BODY,resultList);
				}
				panchayatIds = boothDAO.getPanchayatsByConstituencyIds(constituencyIds,IConstants.VOTER_DATA_PUBLICATION_ID);				
				panchayatCadreCount = tdpCadreDAO.getTotalRecords1(panchayatIds,IConstants.PANCHAYAT);
				panchayatVoterCount = voterInfoDAO.getVotersCountInPanchayatList(panchayatIds,IConstants.VOTER_DATA_PUBLICATION_ID);
				setData(panchayatVoterCount,panchayatCadreCount,IConstants.PANCHAYAT,resultList);	
						
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
	
	
	public void setData(List<Object[]> voterList,List<Object[]> cadreList,String type,List<TdpCadreLocationWiseReportVO> resultList)
	{
		
		if(voterList != null && voterList.size() > 0){
			for(Object[] params : voterList){	
				TdpCadreLocationWiseReportVO vo = getMatchedVO(resultList,(Long)params[6]);
				if(vo != null){
					List<TdpCadreLocationWiseReportVO> list = new ArrayList<TdpCadreLocationWiseReportVO>();
					if(type.equals(IConstants.PANCHAYAT)){
						list = vo.getPanchayatWiseList();
					}
					else if(type.equals(IConstants.TEHSIL) || type.equals(IConstants.LOCAL_ELECTION_BODY)){
						list = vo.getTehsilWiseList();
					}
					TdpCadreLocationWiseReportVO vo1 = new TdpCadreLocationWiseReportVO();
					vo1.setId((Long)params[0]);						
					vo1.setName(params[1] != null ? params[1].toString() : "");					
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
					 
						  if(type.equals(IConstants.PANCHAYAT)){
							  list = vo.getPanchayatWiseList();
							}
							else if(type.equals(IConstants.TEHSIL) || type.equals(IConstants.LOCAL_ELECTION_BODY)){
								list = vo.getTehsilWiseList();
							}					 
						  for(TdpCadreLocationWiseReportVO data : list){
							  data.setRegisteredCadre(params[0] != null ? (Long)params[0] : 0);
							  String percentage ="";
							  percentage = (new BigDecimal(data.getRegisteredCadre()*(100.0)/data.getTargetedCadre().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							  data.setPercentage(percentage);
						  }						  
					
				  }
			}				
		}

	}
	public void genderWiseDetailsForConstituencies(List<Long> constituencyIds,Long publicationId)
	{
	  try
	  {
		    List<Object[]> votersList=voterAgeInfoDAO.getGenderWiseVoterDetailsByConstituency(constituencyIds,publicationId);//mc fc cname
			List<Object[]> votersList1=tdpCadreDAO.getTdpCadregenderWiseByConstituency(constituencyIds);//name,gender,count
		
			
			List<TdpCadreLocationWiseReportVO> resultList=settingtDataGenderWise(votersList,"voterInfo");
			List<TdpCadreLocationWiseReportVO> resultList1=settingtDataGenderWise(votersList1,"tdpCadre");
			
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
						  
						  Double agePerc=(genderVO.getCadresCount()*100.0)/(genderVO.getTotalVoters());
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
				      maleVO.setTotalVoters(objects[0]!=null?(Long)objects[0]:0l);
				      maleVO.setName("male");
				      femaleVO.setTotalVoters(objects[1]!=null?(Long)objects[1]:0l);
				      femaleVO.setName("female");
				      genderList.add(maleVO);
				      genderList.add(femaleVO);
				      
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
				 genderssSet.add("male");
				 genderssSet.add("female");
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
			 
		           if(votersList!=null && votersList.size()>0){  
			 
					  for(Object[] obj:votersList)
					 {
						 TdpCadreLocationWiseReportVO constituencyVO =  getMatchedVOByName(resultList ,obj[0].toString());
						 
						 TdpCadreLocationWiseReportVO genderVO     =    getMatchedVOByName1(constituencyVO.getTdpCadreLocationWiseReportVOList(),obj[1].toString());
						  genderVO.setTotalVoters(genderVO.getTargetedCadre()==null?(Long)obj[2]:genderVO.getTargetedCadre()+(Long)obj[2]);
						
					 }
			      }
		 
		 }
		}catch(Exception e)
		{
			LOG.error(" exception occured in settingtDataGenderWise () at TdpCadreReportService ",e);
			return null;
			
		}
		return resultList;
	}
	
	public void ageWiseDetailsForConstituencies(List<Long> constituencyIds,Long publicationId)
	{
		try
		{
		List<Object[]> votersList=voterAgeInfoDAO.getTotalVotersBasedOnAConstituency(constituencyIds,publicationId);//0-->agerangeId,1-->totalvoters,2-->cname.
		List<Object[]> votersList1=tdpCadreDAO.getTdpCadreAgeRangeByConstituency(constituencyIds);//age,dob,name
		
		List<TdpCadreLocationWiseReportVO> resultList=settingtheData(votersList,"voterInfo");
		List<TdpCadreLocationWiseReportVO> resultList1=settingtheData(votersList1,"tdpCadre");
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
					 
					  Double agePerc=(ageRangeVO.getCadresInAge()*100.0)/(ageRangeVO.getTotalVoters());
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
		 }//for
		}  
		  return null;

}	
}
