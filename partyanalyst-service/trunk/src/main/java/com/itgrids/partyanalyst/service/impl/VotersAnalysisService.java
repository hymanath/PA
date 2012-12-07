package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
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
	
	private IStaticDataService staticDataService;
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

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
			return getVotersCountForBooth(type,id,publicationDateId,"main");
		}
		else if(type.equalsIgnoreCase("panchayat")){
			return getVotersCountForPanchayat(id,publicationDateId,"main");
		}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception rised in getVotersCount method : ",e);
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
		  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
		  Object[] reqName = assemblyLocalElectionBodyName.get(0);
		  name = reqName[0].toString()+" "+reqName[1].toString();
		}
		else{
			name = tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
		}
		VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForSelectedMandal("mandal",id.toString(),publicationDateId,name,"main");
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("2") && votersInfoForMandalVO.isDatapresent()){
			//getting voters count for all panchayats in mandal
		     getVotersCountForMultiplePanchayat(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO);
		     calculatePercentage(votersInfoForMandalVO);
		}
		
		return votersInfoForMandalVO;
	}
	
	public VotersInfoForMandalVO getVotersCountForBooth(String type,Long id,Long publicationDateId,String reqType){
		List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,id,publicationDateId);
		if((!votersCountList.isEmpty() && votersCountList.get(0)[1] != null) || reqType.equalsIgnoreCase("sub")){
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
	    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",(Long)booth[0],publicationDateId,"sub")); 
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
			
		
			return calculatePercentageForCast(boothPublicationVoterDAO.getGenderWiseCountInConstituency(id,publicationDateId));
			
		
		}
		else if(type.equalsIgnoreCase("mandal"))
		{
			
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(id,publicationDateId));	
		}
		
		else if(type.equalsIgnoreCase("panchayat"))
		{
			
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(id,publicationDateId));	
		}
		else if(type.equalsIgnoreCase("booth"))
		{
			
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
			SelectOptionVO selectOptionVO = null;
			List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
			if(type.equalsIgnoreCase("constituency"))
			{
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
				
				mandalCasts = getVotersCastInfoForMultipleMandal(mandalsList,publicationDateId);
				
			}
			
			if(type.equalsIgnoreCase("mandal"))
			{
				List<SelectOptionVO> panchayatList= staticDataService.getPanchayatiesByMandalId(new Long(id));
				
				mandalCasts = getVotersCastInfoForMultiplePanchayats(panchayatList,publicationDateId);
			}
			
			/*if(type.equalsIgnoreCase("panchayat"))
			{
				 List<Object[]> boothsList = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
				 for(Object[] params : boothsList)
				 {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long) params[0]);
				 }
				
				
			}*/
			return mandalCasts;
			
		}
		
		
	//getting All Mandals For Constituency
		
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalList,Long publicationDateId)
	{
		VoterCastInfoVO voterCastInfoVO = null;
		List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
		if(mandalList != null && mandalList.size() > 0){
			for(SelectOptionVO mandals : mandalList)
			{
				voterCastInfoVO = new VoterCastInfoVO();
				String mandalId= mandals.getId().toString();
				String id=mandalId.substring(1);
				String mandalName = mandals.getName();
				voterCastInfoVO.setMandalName(mandalName);
				// For Mandal
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id),publicationDateId)));
				//Muncipality
				if(mandalId.substring(0, 1).toString().trim().equalsIgnoreCase("1"))
				voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(new Long(id),publicationDateId))); 
				mandalCasts.add(voterCastInfoVO);
				
		}
		}
		return mandalCasts;
		
		
	}
	
//getting All Panchayaties For Mandal
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultiplePanchayats(List<SelectOptionVO> panchayatList,Long publicationDateId)
	{
		VoterCastInfoVO voterCastInfo = null;
		List<VoterCastInfoVO> panchayatsList = new ArrayList<VoterCastInfoVO>();
		if(panchayatList != null)
		{
			for(SelectOptionVO pancahyats :panchayatList)
			{
				voterCastInfo = new VoterCastInfoVO();
				Long panchayatId = pancahyats.getId();
				String panchayatName = pancahyats.getName();
				voterCastInfo.setMandalName(panchayatName);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(new Long(panchayatId),publicationDateId)));
				panchayatsList.add(voterCastInfo);
			}
		}
		return panchayatsList;
	}

	
	/**
	 * This method will get overview voters details for a constituency or for a mandal
	 */
	
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type) {		

		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		
		try{ 
			
	        getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type);		
			getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type);			
			getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type);		
			getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type);       
			getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type);
      
			Long totalVoters = 0L;
			for(VotersDetailsVO vtersDetailsVO:constituencyVotersList){				
				totalVoters +=vtersDetailsVO.getTotalVoters();
			}
			
			for(VotersDetailsVO votersDetailsVO:constituencyVotersList){
				if(totalVoters != 0)
				votersDetailsVO.setTotalVotersPercent((float)votersDetailsVO.getTotalVoters()*100f/totalVoters);				
			}
			
		}catch(Exception e){			
			e.printStackTrace();
		}
		return constituencyVotersList;
	}
	
	/**
	 * This method will calculate the voters count who have age between 18 to 25
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	public void getDetailsOfVotersHasAgeBetween18And25(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList,String type) {		
		
		VotersDetailsVO voterDetailsForAgeBetween18To25 = new VotersDetailsVO();
		List<Object[]> votersListOf18To25 = null;
		
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			    votersListOf18To25 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,18L, 25L);
			 else if(type.equalsIgnoreCase("mandal"))
				 votersListOf18To25 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						 tehsilId, publicationDateId,18L, 25L);
			 else if(type.equalsIgnoreCase("panchayat"))
			     votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,18L,25L);
			 else if(type.equalsIgnoreCase("booth"))
			    votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,18L,25L);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 tehsilId, publicationDateId,18L,25L);
				 
			
			
			Long maleVotersBetween18To25 = 0L;
			Long femaleVotersBetween18To25 = 0L;
			Long unKnownVotersBetween18To25 = 0L;
			
			for(Object[] obj:votersListOf18To25){			
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween18To25 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween18To25 = (Long)obj[0];
				else 
					unKnownVotersBetween18To25 = (Long)obj[0];
				
			}
		
			 Long totalVotersBetween18To25 = maleVotersBetween18To25
					+ femaleVotersBetween18To25 + unKnownVotersBetween18To25;
			
			 voterDetailsForAgeBetween18To25.setTotalMaleVoters(maleVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalFemaleVoters(femaleVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalUnknownVoters(unKnownVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setTotalVoters(totalVotersBetween18To25);
			 voterDetailsForAgeBetween18To25.setAgeRange("18-25");
			 
			  if(totalVotersBetween18To25 != 0){
				 voterDetailsForAgeBetween18To25.setTotalMaleVotersPercent((float) (maleVotersBetween18To25 *100f/ totalVotersBetween18To25 ));
				 voterDetailsForAgeBetween18To25.setTotalFemaleVotersPercent((float)(femaleVotersBetween18To25 *100f / totalVotersBetween18To25 ));
				 voterDetailsForAgeBetween18To25.setTotalUnknownVotersPercent((float) (unKnownVotersBetween18To25 *100f / totalVotersBetween18To25 ));
			  }
				 
			
			 
			 constituencyVotersList.add(voterDetailsForAgeBetween18To25);
		 
		}catch(Exception e){
			e.printStackTrace();
			
		}
		 
		
	}
	
	/**
	 * This method will calculate the voters count who have age between 26 to 35
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	
	public void getDetailsOfVotersHasAgeBetween26And35(Long constituencyId,Long tehsilId,
			Long panchayatId,Long boothId, Long publicationDateId, List<VotersDetailsVO> constituencyVotersList ,String type) {		
		
       VotersDetailsVO voterDetailsForAgeBetween26To35 = new VotersDetailsVO();
       List<Object[]> votersListOf26To35 = null;
       
       try
       {
      
			if(type.equalsIgnoreCase("constituency"))
			 votersListOf26To35 = boothPublicationVoterDAO
					.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
							constituencyId, publicationDateId,26L, 35L);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf26To35 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,26L, 35L);
			 else if(type.equalsIgnoreCase("panchayat"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,26L, 35L);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,26L, 35L);
			 else if(type.equalsIgnoreCase("localElectionBody"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						 boothId, publicationDateId,26L, 35L);
			
			
			Long maleVotersBetween26To35 = 0L;
			Long femaleVotersBetween26To35 = 0L;
			Long unKnownVotersBetween26To35 = 0L;
			
			for(Object[] obj:votersListOf26To35){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween26To35 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween26To35 = (Long)obj[0];
				else 
					unKnownVotersBetween26To35 = (Long)obj[0];
			}
			
			
			Long totalVotersBetween26To35 = maleVotersBetween26To35
						+ femaleVotersBetween26To35 + unKnownVotersBetween26To35;
			
			
			voterDetailsForAgeBetween26To35.setTotalMaleVoters(femaleVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalFemaleVoters(femaleVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalUnknownVoters(unKnownVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setTotalVoters(totalVotersBetween26To35);
			voterDetailsForAgeBetween26To35.setAgeRange("26-35");
			
				if(totalVotersBetween26To35 != 0){
					voterDetailsForAgeBetween26To35.setTotalMaleVotersPercent((float) (maleVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					voterDetailsForAgeBetween26To35.setTotalFemaleVotersPercent((float)(femaleVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					voterDetailsForAgeBetween26To35.setTotalUnknownVotersPercent((float) (unKnownVotersBetween26To35 *100f/ totalVotersBetween26To35 ));
					}
			
			
			constituencyVotersList.add(voterDetailsForAgeBetween26To35);
		
       }catch(Exception e){    	   
    	   e.printStackTrace();    	   
       }		
	}
	
	
	/**
	 * This method will calculate the voters count who have age between 36 to 45
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	
	public void getDetailsOfVotersHasAgeBetween36And45(
			Long constituencyId,Long tehsilId,Long panchayatId ,Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList , String type){

		VotersDetailsVO voterDetailsForAgeBetween36To45 = new VotersDetailsVO();
		List<Object[]> votersListOf36To45 = null;
		
		try{
		
				if(type.equalsIgnoreCase("constituency"))
				 votersListOf36To45 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,36L, 45L);
				else if(type.equalsIgnoreCase("mandal"))
					votersListOf36To45 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
							tehsilId, publicationDateId,36L, 45L);
				 else if(type.equalsIgnoreCase("panchayat"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
						panchayatId, publicationDateId,36L, 45L);
				 else if(type.equalsIgnoreCase("booth"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
							boothId, publicationDateId,36L, 45L);
				else if(type.equalsIgnoreCase("localElectionBody"))
					votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
							tehsilId, publicationDateId,36L, 45L);
					
				Long maleVotersBetween36To45 = 0L;
				Long femaleVotersBetween36To45 = 0L;
				Long unKnownVotersBetween36To45 = 0L;
				
				
		        for(Object[] obj:votersListOf36To45){
					
					if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
						maleVotersBetween36To45 = (Long)obj[0];
					else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
						femaleVotersBetween36To45 = (Long)obj[0];
					else 
						unKnownVotersBetween36To45 = (Long)obj[0];
				}
		        
					Long totalVotersBetween36To45 = maleVotersBetween36To45
							+ femaleVotersBetween36To45 + unKnownVotersBetween36To45;
			
			
			voterDetailsForAgeBetween36To45.setTotalMaleVoters(maleVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalFemaleVoters(femaleVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalUnknownVoters(unKnownVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setTotalVoters(totalVotersBetween36To45);
			voterDetailsForAgeBetween36To45.setAgeRange("36-45");

			if(totalVotersBetween36To45 != 0){
				voterDetailsForAgeBetween36To45.setTotalMaleVotersPercent((float) (maleVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
				voterDetailsForAgeBetween36To45.setTotalFemaleVotersPercent((float)(femaleVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
				voterDetailsForAgeBetween36To45.setTotalUnknownVotersPercent((float) (unKnownVotersBetween36To45 *100f/ totalVotersBetween36To45 ));
			}
			constituencyVotersList.add(voterDetailsForAgeBetween36To45);
			
		
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	
	
	/**
	 * This method will calculate the voters count who have age between 46 to 60
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
	public void  getDetailsOfVotersHasAgeBetween46And60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId, Long publicationDateId,
			List<VotersDetailsVO> constituencyVotersList ,String type){
		
		    List<Object[]> votersListOf46To60 = null;
		    VotersDetailsVO voterDetailsForAgeBetween46To60 = new VotersDetailsVO();
		    
		try{
		
			if(type.equalsIgnoreCase("constituency"))
			   votersListOf46To60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
								constituencyId, publicationDateId,46L, 60L);
			else if(type.equalsIgnoreCase("mandal"))
				votersListOf46To60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
						tehsilId, publicationDateId,46L, 60L);
			else if(type.equalsIgnoreCase("panchayat"))
				votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,46L, 60L);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,46L, 60L);
			else if(type.equalsIgnoreCase("localElectionBody"))
				votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
						tehsilId, publicationDateId,46L, 60L);
			
			Long maleVotersBetween46To60 = 0L;
			Long femaleVotersBetween46To60 = 0L;
			Long unKnownVotersBetween46To60 = 0L;
			
	       for(Object[] obj:votersListOf46To60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersBetween46To60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersBetween46To60 = (Long)obj[0];
				else 
					unKnownVotersBetween46To60 = (Long)obj[0];
			}
	       
	       Long totalVotersBetween46To60 = maleVotersBetween46To60
					+ femaleVotersBetween46To60 + unKnownVotersBetween46To60;
	       
	   
	       voterDetailsForAgeBetween46To60.setTotalMaleVoters(maleVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalFemaleVoters(femaleVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalUnknownVoters(unKnownVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setTotalVoters(totalVotersBetween46To60);
	       voterDetailsForAgeBetween46To60.setAgeRange("46-60");
	       
	       if(totalVotersBetween46To60 != 0){
		       voterDetailsForAgeBetween46To60.setTotalMaleVotersPercent((float) (maleVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
		       voterDetailsForAgeBetween46To60.setTotalFemaleVotersPercent((float)(femaleVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
		       voterDetailsForAgeBetween46To60.setTotalUnknownVotersPercent((float) (unKnownVotersBetween46To60 *100f/ totalVotersBetween46To60 ));
	       }    
				       
	       constituencyVotersList.add(voterDetailsForAgeBetween46To60);
       
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	/**
	 * This method will calculate the voters count who have age above 60
	 * @param constituencyId
	 * @param tehsilId
	 * @param publicationDateId
	 * @param constituencyVotersList
	 * @param type 
	 * @return constituencyVotersList
	 */
	
		
	public void  getDetailsOfVotersHasAgeAbove60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId,Long  publicationDateId,
			List<VotersDetailsVO> constituencyVotersList ,String type){
		

		VotersDetailsVO voterDetailsForAgeAbove60 = new VotersDetailsVO();
	    List<Object[]> votersListOfAbove60  = null;
	    
	    try{
	       
	        if(type.equalsIgnoreCase("constituency"))
		         votersListOfAbove60 = boothPublicationVoterDAO
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
								constituencyId, publicationDateId,60L , 150L);
	       else if(type.equalsIgnoreCase("mandal"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndMandalId(
	    			   tehsilId, publicationDateId,60L ,150L);
	       else if(type.equalsIgnoreCase("panchayat"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForPanchayatByPublicationId(
					panchayatId, publicationDateId,60L ,150L);
			 else if(type.equalsIgnoreCase("booth"))
				 votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForBoothByPublicationDateId(
						boothId, publicationDateId,60L ,150L);
	       else if(type.equalsIgnoreCase("localElectionBody"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(
	    			   tehsilId, publicationDateId,46L, 150L);
	       
	        Long maleVotersAbove60 = 0L;
			Long femaleVotersAbove60 = 0L;
			Long unKnownVotersAbove60 = 0L;
			
			
	          for(Object[] obj:votersListOfAbove60){
				
				if(obj[1].toString().equalsIgnoreCase(IConstants.MALE))
					maleVotersAbove60 = (Long)obj[0];
				else if(obj[1].toString().equalsIgnoreCase(IConstants.FEMALE))
					femaleVotersAbove60 = (Long)obj[0];
				else 
					unKnownVotersAbove60 = (Long)obj[0];
			}
	          
	          Long totalVotersAbove60 = maleVotersAbove60
	  				+ femaleVotersAbove60 + unKnownVotersAbove60;
	          
	          
	          voterDetailsForAgeAbove60.setTotalMaleVoters(maleVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalFemaleVoters(femaleVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalUnknownVoters(unKnownVotersAbove60);
	          voterDetailsForAgeAbove60.setTotalVoters(totalVotersAbove60);	 
	          voterDetailsForAgeAbove60.setAgeRange("60-Above");
	          if(totalVotersAbove60 != 0){
		          voterDetailsForAgeAbove60.setTotalMaleVotersPercent((float) (maleVotersAbove60 *100f/ totalVotersAbove60 ));
		          voterDetailsForAgeAbove60.setTotalFemaleVotersPercent((float)(femaleVotersAbove60 *100f/ totalVotersAbove60 ));
		          voterDetailsForAgeAbove60.setTotalUnknownVotersPercent((float) (unKnownVotersAbove60 *100f/ totalVotersAbove60 ));
		       }

	   		
	          constituencyVotersList.add(voterDetailsForAgeAbove60);
	          
	    }catch(Exception e){
	    	
	    	e.printStackTrace();	    	
	    }
	}
			
	//Retrieving important families information
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId){	
		try{
			if(type.equalsIgnoreCase("constituency")){
				return getImportantFamiliesForConstituency(type,id,publicationDateId);
				
			}
			else if(type.equalsIgnoreCase("mandal")){
				return getImportantFamiliesForMandal(type,id,publicationDateId,"main");
			}
			else if(type.equalsIgnoreCase("booth")){
				return getImportantFamiliesForBooth(type,id,publicationDateId,"main");
			}
			else if(type.equalsIgnoreCase("panchayat")){
				return getImportantFamiliesForPanchayat(id,publicationDateId,"","main");
			}
		}catch(Exception e){
			log.error("Exception rised in getImportantFamiliesInfo method : ",e);
		}
		return null;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForConstituency(String type,Long id,Long publicationDateId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"constituency"));
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"","main");
		 
		 if(importantFamiliesInfoVo.isDataPresent()){
		    List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		    for(SelectOptionVO mandal : mandalsList){
				
			 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForMandal("mandal",mandal.getId(),publicationDateId,"sub"));
		    }
		 }
		 return  importantFamiliesInfoVo;
	}
	
	public void getImpFamilesInfo(String type,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,String queryToexe,String exeType){
		
        String query = "";
        Long[] totalFamilies = getFamiliesCount(type,id,publicationDateId,null,queryToexe,exeType);
		if(totalFamilies != null){
          importantFamiliesInfoVo.setTotalFamalies(totalFamilies[0]);
		}else{
			importantFamiliesInfoVo.setDataPresent(false);
			return;
		}
	     query = " having count(model.voter.voterId) <= 3 ";
	     Long[] count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType);
	     importantFamiliesInfoVo.setBelow3(count[0]);
	     importantFamiliesInfoVo.setBelow3Popul(count[1]);
	     
	     query = " having count(model.voter.voterId) > 3 and count(model.voter.voterId) <= 6 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType);
	     importantFamiliesInfoVo.setBetwn4to6(count[0]);
	     importantFamiliesInfoVo.setBetwn4to6Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 6 and count(model.voter.voterId) <= 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType);
	     importantFamiliesInfoVo.setBetwn7to10(count[0]);
	     importantFamiliesInfoVo.setBetwn7to10Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType);
	     importantFamiliesInfoVo.setAbove10(count[0]);
	     importantFamiliesInfoVo.setAbove10Popul(count[1]);
	     
	     if(importantFamiliesInfoVo.getTotalVoters() > 0)
	       calculatePercentage(importantFamiliesInfoVo);
	     
	}
	
	public Long[] getFamiliesCount(String type,Long id,Long publicationDateId,String query,String queryToexe,String exeType){
		
		Long[] count = {0l,0l};
		List<Object[]> totalFamiliesList = null;
		if(queryToexe.equalsIgnoreCase(""))
			totalFamiliesList = boothPublicationVoterDAO.findAllImpFamiles(id,publicationDateId,type,query);
		else if(queryToexe.equalsIgnoreCase("local"))
			totalFamiliesList = boothPublicationVoterDAO.getVotersImpFamilesForLocalElectionBody(id,publicationDateId,query);
		else if(queryToexe.equalsIgnoreCase("panchayat"))
			totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationId(id,publicationDateId,query);
		
		if(!totalFamiliesList.isEmpty() && totalFamiliesList.get(0)[1] != null){
	    	count[0] = new Long(totalFamiliesList.size());
	       if(query != null){
	    	Long totalVoters = 0l;
	    	for(Object[] family : totalFamiliesList){
	    		if(family[0] != null){
	    			totalVoters = totalVoters+(Long)family[0];
	    		}
	    	 }
	    	count[1] = totalVoters;
	       }
	    }
		else if(query == null && exeType.equalsIgnoreCase("main")){
			return null;
		}
	    return count;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForMandal(String type,Long id,Long publicationDateId,String exeType){
		if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(new Long(id.toString().substring(1).trim()),publicationDateId,"mandal"));
			 getImpFamilesInfo(type,new Long(id.toString().substring(1).trim()),publicationDateId,importantFamiliesInfoVo,"",exeType);
			 if(exeType.equalsIgnoreCase("main") && importantFamiliesInfoVo.isDataPresent()){
			 List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(1).trim()));
				for (Object[] panchayat : panchayaties){
					importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForPanchayat((Long)panchayat[0],publicationDateId,"","sub"));
				}
			 }
			 return importantFamiliesInfoVo;
		}else{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getVotersCountForLocalElectionBody(new Long(id.toString().substring(1).trim()),publicationDateId));
			 getImpFamilesInfo(type,new Long(id.toString().substring(1).trim()),publicationDateId,importantFamiliesInfoVo,"local",exeType);
			 return importantFamiliesInfoVo;
		}
		
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"booth"));
	
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"",exeType);
		 return importantFamiliesInfoVo;
	}

	public ImportantFamiliesInfoVo getImportantFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setTotalVoters((Long)boothPublicationVoterDAO.getVotersCountForPanchayat(id,publicationDateId).get(0));
		 getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"panchayat",exeType);
		 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			 List<Object[]> boothsList = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
		     for(Object[] booth : boothsList){
		    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",(Long)booth[0],publicationDateId,"sub"));
		     }
		 }
		 return importantFamiliesInfoVo;
	}
	
	public void calculatePercentage(ImportantFamiliesInfoVo importantFamiliesInfoVo){
		 DecimalFormat df = new DecimalFormat("#.##");
		if(importantFamiliesInfoVo.getTotalVoters() != null && importantFamiliesInfoVo.getTotalVoters() > 0){
		    importantFamiliesInfoVo.setBelow3perc(new Double(df.format(importantFamiliesInfoVo.getBelow3Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setBetwn4to6perc(new Double(df.format(importantFamiliesInfoVo.getBetwn4to6Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setBetwn7to10perc(new Double(df.format(importantFamiliesInfoVo.getBetwn7to10Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		    importantFamiliesInfoVo.setAbove10perc(new Double(df.format(importantFamiliesInfoVo.getAbove10Popul().doubleValue()/importantFamiliesInfoVo.getTotalVoters()*100)));
		 }else{
			    importantFamiliesInfoVo.setBelow3perc(0d);
			    importantFamiliesInfoVo.setBetwn4to6perc(0d);
			    importantFamiliesInfoVo.setBetwn7to10perc(0d);
			    importantFamiliesInfoVo.setAbove10perc(0d); 
		 }
	}
}
