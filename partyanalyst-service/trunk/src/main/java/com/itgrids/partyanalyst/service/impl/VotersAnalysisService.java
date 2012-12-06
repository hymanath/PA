package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VotersAnalysisService implements IVotersAnalysisService{
	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);

	
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IRegionServiceData regionServiceDataImp;
	private IPanchayatDAO panchayatDAO;
	private IHamletBoothPublicationDAO hamletBoothPublicationDAO;
	private IConstituencyDAO constituencyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName) {

		if (log.isDebugEnabled())
			log.debug("Excecuting getVoterDetails() method in RegionServiceDataImp service");

		List<VoterVO> voters = null;
		List<Voter> votersList = null;
		Long totalCount = 0L;

		try {   
			if(boothId != null && panchayatId == null){
				 votersList = boothPublicationVoterDAO
						.getVotersDetailsByBoothId( boothId ,startIndex, maxRecords, order, columnName);
				 
				 totalCount = (Long) boothPublicationVoterDAO.getVotersCountByBoothId(boothId).get(0);
				 
			}else if(boothId == null && panchayatId != null){
				 votersList = boothPublicationVoterDAO
						.getVotersDetailsForPanchayatByPublicationId(
								 panchayatId,  publicationDateId,  startIndex,
								 maxRecords,  order,  columnName);
				 
				 totalCount = (Long) boothPublicationVoterDAO.getVotersCountForPanchayat(panchayatId,publicationDateId).get(0);
				
			}
	
				if (votersList != null && votersList.size() > 0)
					voters = new ArrayList<VoterVO>();
	
				Long count = new Long(startIndex);
				
				for (Voter voter : votersList) {
	
					VoterVO voterVO = new VoterVO();
					
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(voter.getFirstName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeFirstName());
					voterVO.setCast(voter.getCast());
					voterVO.setCastCatagery(voter.getCastCatagery());					
	
				}
				
				if(voters.size() > 0)
					voters.get(0).setTotalVoters(totalCount);

		} catch (Exception e) {
			
			log.error("Exception Occured in getVoterDetails() method - " + e);
			return null;
		}
	
		return voters;
	}




	/**
	 * @return publicationDetails
	 * @author prasad
	 * @param constituencyId
	 */
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(); 
			
		SelectOptionVO selectOptionVO = null;
		List<Object[]> publicationDetails = boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(constituencyId);
		
		if(publicationDetails != null && publicationDetails.size() > 0)
		{
			for(Object[] publicationDetail : publicationDetails)
			{
				Date date = (Date)publicationDetail[1];
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)publicationDetail[0]);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
				
				selectOptionVOList.add(selectOptionVO);
			}
		}
		return selectOptionVOList;
	}
	
	/**
	 * 
	 * @param id
	 * @param publicationDateId
	 * @param name
	 * @return returnVal
	 * @author prasad
	 */
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name){
		List<Object[]>  impFamilesList = null;
		
		if(name.equalsIgnoreCase("constituency")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnConstituencyId(id, publicationDateId);
		}
		else if(name.equalsIgnoreCase("panchayat")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnBoothId(id, publicationDateId);
		}
		else if(name.equalsIgnoreCase("booth")){
			impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnPanchayat(id, publicationDateId);
		}
		Map<String,List<VoterVO>> resultMap = new LinkedHashMap<String,List<VoterVO>>();
		resultMap.put("Below-3", new ArrayList<VoterVO>(0));
		resultMap.put("7-5", new ArrayList<VoterVO>(0));
		resultMap.put("10-7", new ArrayList<VoterVO>(0));
		resultMap.put("Above-10", new ArrayList<VoterVO>(0));
	    List<Long> returnVal = new ArrayList<Long>();
		Long below3 = 0l;
		Long between4To6 = 0l;
		Long between7To10 = 0l;
		Long above10 = 0l;
		Long count = 0l;
		Long above10Count = 0l;
		Long between7T10Count = 0l;
		Long between4To6Count = 0l;
		Long below3Count = 0l;
		for (Object[] impFamiles : impFamilesList) {
			count = (Long) impFamiles[1];	
			if(count.longValue() > 10){
				above10 = above10+1;
				above10Count = count + above10Count;
			}
			else if(count.longValue() < 10 && count.longValue() >= 7){
				between7To10 = between7To10+1;
				between7T10Count = count + between7T10Count;
			}
			else if(count.longValue() < 7 && count.longValue() >=4){
				between4To6 = between4To6 + 1;
				between4To6Count = count + between4To6Count;	
			}
			else{
				below3 = below3 + 1;
				below3Count = count + below3Count;
			}
		}
		returnVal.add(above10);
		returnVal.add(above10Count);
		returnVal.add(below3);
		returnVal.add(below3Count);
		returnVal.add(between4To6);
		returnVal.add(between4To6Count);
		returnVal.add(between7To10);
		returnVal.add(between7T10Count);
		return returnVal;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IHamletBoothPublicationDAO getHamletBoothPublicationDAO() {
		return hamletBoothPublicationDAO;
	}

	public void setHamletBoothPublicationDAO(
			IHamletBoothPublicationDAO hamletBoothPublicationDAO) {
		this.hamletBoothPublicationDAO = hamletBoothPublicationDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	
	public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId){
		try{
		if(type.equalsIgnoreCase("constituency")){
			return getVotersCountForConstituency(type,id,publicationDateId);
			
		}
		else if(type.equalsIgnoreCase("mandal")){
			return getVotersCountForMandal(type,id,publicationDateId);
		}
		else if(type.equalsIgnoreCase("booth")){
			return getVotersCountForBooth(type,id,publicationDateId);
		}
		else if(type.equalsIgnoreCase("panchayat")){
			return getVotersCountForPanchayat(id,publicationDateId,"main");
		}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	}
	
	public VotersInfoForMandalVO getVotersCountForConstituency(String type,Long id,Long publicationDateId){
		
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId);
		if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
		  VotersInfoForMandalVO votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"Constituency");
		   //getting  all mandals and muncipalities and ghmcs in constituency
		  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		  //getting voters count for all mandals and muncipalities and ghmcs in constituency
		  getVotersCountForMultipleMandal(mandalsList,publicationDateId,votersInfoForMandalVO);
		  calculatePercentage(votersInfoForMandalVO);
		  return votersInfoForMandalVO;
	   }else{
		   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	   }
	}
	
	public VotersInfoForMandalVO getVotersCountForMandal(String type,Long id,Long publicationDateId){
		String name = "";
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
		  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(01));
		  Object[] reqName = assemblyLocalElectionBodyName.get(0);
		  name = reqName[0].toString()+" "+reqName[1].toString();
		}
		else{
			name = tehsilDAO.get(new Long(id.toString().substring(01))).getTehsilName()+" Mandal/Tehsil";
		}
		VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForSelectedMandal("mandal",id.toString(),publicationDateId,name,"main");
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("2") && votersInfoForMandalVO.isDatapresent()){
			//getting voters count for all panchayats in mandal
		     getVotersCountForMultiplePanchayat(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO);
		     calculatePercentage(votersInfoForMandalVO);
		}
		
		return votersInfoForMandalVO;
	}
	
	public VotersInfoForMandalVO getVotersCountForBooth(String type,Long id,Long publicationDateId){
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId);
		if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
		   return populateDataToVotersInfoForMandalVO(votersCountList,id,"booth-"+boothDAO.get(id).getPartNo(),"Booth");
		}else{
			   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			   votersInfoForMandalVO.setDatapresent(false);
			   return votersInfoForMandalVO;
		   }
	}
	
	public VotersInfoForMandalVO getVotersCountForPanchayat(Long id,Long publicationDateId,String reqType ){
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(id,publicationDateId);
		if(reqType.equalsIgnoreCase("main")){
		if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
		  VotersInfoForMandalVO votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,id,panchayatDAO.get(id).getPanchayatName(),"Panchayat");
		  getVotersCountForMultipleBooths(id,publicationDateId,votersInfoForMandalVO);
		  calculatePercentage(votersInfoForMandalVO);
		  return votersInfoForMandalVO;
		}else{
			 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			 votersInfoForMandalVO.setDatapresent(false);
			 return votersInfoForMandalVO;
		 }
	  }else{
		  return populateDataToVotersInfoForMandalVO(votersCountList,id,panchayatDAO.get(id).getPanchayatName(),"Panchayat");
	  }
	}
	
	public void getVotersCountForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		 for(SelectOptionVO mandal : mandalsList){
			
			 String id = mandal.getId().toString();
			 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForSelectedMandal("mandal",id,publicationDateId,mandal.getName(),"sub"));
		 }
	}
	
	public VotersInfoForMandalVO getVotersCountForSelectedMandal(String type,String id,Long publicationDateId,String name,String reqType){
		
			 List<Object[]> votersCountList = null;
			 if(id.substring(0,1).trim().equalsIgnoreCase("1")){
				 //getting voters count for muncipality and ghmc
				votersCountList =  boothPublicationVoterDAO.getVotersCountFromLocalElectionBody(new Long(id.substring(1).trim()),publicationDateId);
			 }else if(id.substring(0,1).trim().equalsIgnoreCase("2")){
				 //getting voters count for mandal
				 votersCountList =  boothPublicationVoterDAO.getVotersCountByPublicationId("mandal",new Long(id.substring(1).trim()),publicationDateId);
			 }
			 if(reqType.equalsIgnoreCase("main")){
				 if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
				   return populateDataToVotersInfoForMandalVO(votersCountList,new Long(id),name,"Mandal");
				 }else{
					 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
					 votersInfoForMandalVO.setDatapresent(false);
					 return votersInfoForMandalVO;
				 }
			 }else{
				 return populateDataToVotersInfoForMandalVO(votersCountList,new Long(id),name,"Mandal"); 
			 }
			
	}
	
	public void getVotersCountForMultipleBooths(Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		List<Object[]> boothsList = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(panchayatId,publicationDateId);
	     for(Object[] booth : boothsList){
	    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",(Long)booth[0],publicationDateId)); 
	     }
	}
	
	public VotersInfoForMandalVO populateDataToVotersInfoForMandalVO(List<Object[]> votersCountList,Long id,String name,String type){
		Long maleVotersCount = 0l;
		Long femaleVotersCount = 0l;
		Long unknownsCount = 0l;
	    for(Object[] votersCount : votersCountList){
	    	if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("M")){
	    		maleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("F")){
	    		femaleVotersCount = (Long)votersCount[0];
	    	}else if(votersCount[1] != null && votersCount[1].toString().trim().equalsIgnoreCase("")){
	    		unknownsCount = (Long)votersCount[0];
	    	}
	    }
	    VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
	    votersInfoForMandalVO.setTotalMaleVoters(maleVotersCount.toString());
	    votersInfoForMandalVO.setTotalFemaleVoters(femaleVotersCount.toString());
	    votersInfoForMandalVO.setUnKnowVoters(unknownsCount.toString());
	    votersInfoForMandalVO.setId(id);
		votersInfoForMandalVO.setName(name);
		votersInfoForMandalVO.setType(type);
	    BigDecimal totalCount = new BigDecimal(maleVotersCount.longValue()+femaleVotersCount.longValue()+unknownsCount.longValue());
	    votersInfoForMandalVO.setTotVoters(totalCount);
	    
	    return votersInfoForMandalVO;
	}
	
	public void getVotersCountForMultiplePanchayat(Long mandalId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(mandalId);
		for (Object[] panchayat : panchayaties){
			votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForPanchayat((Long)panchayat[0],publicationDateId,"sub"));
		}
	}
	
	public void calculatePercentage(VotersInfoForMandalVO votersInfoForMandalVO){
		if(!votersInfoForMandalVO.getVotersInfoForMandalVOList().isEmpty()){
			votersInfoForMandalVO.setSubLevelExists(true);
		  for(VotersInfoForMandalVO vo : votersInfoForMandalVO.getVotersInfoForMandalVOList()){ 
			  if(!(votersInfoForMandalVO.getTotVoters().compareTo( BigDecimal.ZERO) == 0)){
			    BigDecimal votersPerc = vo.getTotVoters().divide(votersInfoForMandalVO.getTotVoters(),2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
			    vo.setPercent(votersPerc.toPlainString());
			  }else{
				  vo.setPercent("0");  
			  }
		  }
		}
	}
	
	
	
	//get CastInfo For Constituency/Mandal/Panchayat/Booth
	
	public VoterCastInfoVO getVotersCastDetails(Long id,Long publicationDateId,String type)
	
	{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		if(type.equalsIgnoreCase("constituency"))
		{
			
		
			List genderList =boothPublicationVoterDAO.findVotersCastInfoByConstituencyAndPublicationDate(id,publicationDateId);
			return calculatePercentageForCast(boothPublicationVoterDAO.getGenderWiseCountInConstituency(id,publicationDateId));
			
		
		}
		else if(type.equalsIgnoreCase("mandal"))
		{
			
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(id,publicationDateId));	
		}
		
		else if(type.equalsIgnoreCase("panchayat"))
		{
			List genderList =boothPublicationVoterDAO.getGenderWiseCountInConstituency(id,publicationDateId);
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(id,publicationDateId));	
		}
		else if(type.equalsIgnoreCase("booth"))
		{
			List genderList =boothPublicationVoterDAO.getGenderWiseCountInConstituency(id,publicationDateId);
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByBoothIdAndPublicationDate(id,publicationDateId));	
		}
		else
			return voterCastInfoVO;
		
		
	}
	
	
		public VoterCastInfoVO calculatePercentageForCast(List params)
		{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		SortedMap<String,CastVO> castsMap = new TreeMap<String,CastVO>();
		
		 
		//Set<String> casts = new HashSet<String>();
		CastVO castvo = null;
		
		
		Long totalVoters = 0l;
		String cast ="";
		
		Long maleVoters=0L;
		Long femaleVoters=0L;
		
		for(int i=0;i<params.size();i++)
		{
			Object[] voterInfo =(Object[])params.get(i);
			
			totalVoters = totalVoters + (Long) voterInfo[0];
			
			String gender = (String)voterInfo[1];
			cast = (String) voterInfo[2];
			
			if(cast.equals(""))
			{
				
				cast = "N/A";
				
			}
			if(castsMap.get(cast) == null){
				castvo = new CastVO();
				castvo.setCastName(cast);
				castvo.setCastCount((Long) voterInfo[0]);
				//castvo.setGender(voterInfo[1].toString());
				if(gender.equalsIgnoreCase("m"))
				{
					castvo.setMalevoters((Long)voterInfo[0]);
				}
				else if(gender.equalsIgnoreCase("f"))
				{
					castvo.setFemalevoters((Long)voterInfo[0]);
				}
				castsMap.put(cast, castvo);
			}
			else{
			   castvo = castsMap.get(cast);
			   castvo.setCastName(cast);
			   castvo.setCastCount(castvo.getCastCount()+(Long) voterInfo[0]);
			   //castvo.setGender(voterInfo[1].toString());
			   if(gender.equalsIgnoreCase("m"))
			   {
				castvo.setMalevoters((Long)voterInfo[0]);
			   }
			   else if(gender.equalsIgnoreCase("f"))
			   {
				castvo.setFemalevoters((Long)voterInfo[0]);
			   }
			 }
			
			
		}
		
		List<CastVO> castVOs = new ArrayList<CastVO>(castsMap.values());
		
		//Collections.sort(castVOs);
		// Calculate Percentage
		for(int i=0;i<castVOs.size();i++)
		{
			String castPercentage = "0";
			if(totalVoters > 0)
			   castPercentage = new BigDecimal((castVOs.get(i).getCastCount()*100.0)/totalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			castVOs.get(i).setCastPercentage(castPercentage);
		}
		
		
		voterCastInfoVO.setTotalVoters(totalVoters);
		
		voterCastInfoVO.setCastVOs(castVOs);
		return voterCastInfoVO;
		
		
	}
	
		public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type)
		
		{
			VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
			List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
			if(type.equalsIgnoreCase("constituency"))
			{
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
				
				mandalCasts = getVotersCastInfoForMultipleMandal(mandalsList,publicationDateId);
				
			}
			
			return mandalCasts;
			
		}
		
		
	//getting All Mandals For Constituency
		
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalList,Long publicationDateId)
	{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
		if(mandalList != null && mandalList.size() > 0){
			for(SelectOptionVO mandals : mandalList)
			{
				String mandalId= mandals.getId().toString();
				String id=mandalId.substring(1);
				String mandalName = mandals.getName();
				voterCastInfoVO.setMandalName(mandalName);
				voterCastInfoVO.getCastVosList().add(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id),publicationDateId))) ; 
				voterCastInfoVO.getCastVosList().add(voterCastInfoVO);
				
		}
		}
		return voterCastInfoVO.getCastVosList();
		
		
	}
	
}
