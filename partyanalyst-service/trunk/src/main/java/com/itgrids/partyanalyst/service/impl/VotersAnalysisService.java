package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterCategory;
import com.itgrids.partyanalyst.model.UserVoterCategoryValue;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterCategoryValue;
import com.itgrids.partyanalyst.model.VoterTemp;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VotersAnalysisService implements IVotersAnalysisService{
	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);

	private IVoterCategoryValueDAO voterCategoryValueDAO;
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IRegionServiceData regionServiceDataImp;
	private IPanchayatDAO panchayatDAO;
	private IHamletBoothPublicationDAO hamletBoothPublicationDAO;
	private IConstituencyDAO constituencyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	private IVoterDAO voterDAO;
	private IStaticDataService staticDataService;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPartyDAO partyDAO;
	private IUserDAO userDAO;
	private IVoterTempDAO voterTempDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private ISocialService socialService;
	private IUserVoterCategoryDAO userVoterCategoryDAO;
	private IUserVoterCategoryValueDAO userVoterCategoryValueDAO;
	private TransactionTemplate transactionTemplate = null;
	private ICasteStateDAO casteStateDAO;
	//private IVoterCategoryValues voterCategoryValues;
	private IPublicationDateDAO publicationDateDAO;
	private IUserStateAccessInfoDAO userStateAccessInfoDAO;
	private ICasteCategoryGroupDAO casteCategoryGroupDAO;
	private ICasteDAO casteDAO;
	private IStateDAO stateDAO;

	public IVoterCategoryValueDAO getVoterCategoryValueDAO() {
		return voterCategoryValueDAO;
	}

	public void setVoterCategoryValueDAO(
			IVoterCategoryValueDAO voterCategoryValueDAO) {
		this.voterCategoryValueDAO = voterCategoryValueDAO;
	}

	public IUserVoterCategoryDAO getUserVoterCategoryDAO() {
		return userVoterCategoryDAO;
	}

	public void setUserVoterCategoryDAO(IUserVoterCategoryDAO userVoterCategoryDAO) {
		this.userVoterCategoryDAO = userVoterCategoryDAO;
	}

	public IUserVoterCategoryValueDAO getUserVoterCategoryValueDAO() {
		return userVoterCategoryValueDAO;
	}

	public void setUserVoterCategoryValueDAO(
			IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
		this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
	}

	public ISocialService getSocialService() {
		return socialService;
	}

	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}

	public IVoterTempDAO getVoterTempDAO() {
		return voterTempDAO;
	}

	public void setVoterTempDAO(IVoterTempDAO voterTempDAO) {
		this.voterTempDAO = voterTempDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

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

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	
	public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
		return userStateAccessInfoDAO;
	}
	public void setUserStateAccessInfoDAO(
			IUserStateAccessInfoDAO userStateAccessInfoDAO) {
		this.userStateAccessInfoDAO = userStateAccessInfoDAO;
	}
	public ICasteCategoryGroupDAO getCasteCategoryGroupDAO() {
		return casteCategoryGroupDAO;
	}
	public void setCasteCategoryGroupDAO(
			ICasteCategoryGroupDAO casteCategoryGroupDAO) {
		this.casteCategoryGroupDAO = casteCategoryGroupDAO;
	}
	
	public ICasteDAO getCasteDAO() {
		return casteDAO;
	}

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName) {

		if (log.isDebugEnabled())
			log.debug("Excecuting getVoterDetails() method in RegionServiceDataImp service");

		List<VoterVO> voters = new ArrayList<VoterVO>();
		List<Voter> votersList = new ArrayList<Voter>();;
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
	
				
				Long count = new Long(startIndex);
				
				for (Voter voter : votersList) {
	
					VoterVO voterVO = new VoterVO();
					
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(voter.getName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeName());
					voterVO.setRelationshipType(voter.getRelationshipType());
					voterVO.setCast(voter.getCast());
					voterVO.setCastCatagery(voter.getCastCatagery());	
					voters.add(voterVO);
	
				}
				
				if(voters != null)
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
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}
	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
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
				 //List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.substring(1).trim()));
					
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
		List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
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
	
	public void calculatePercentage(VotersInfoForMandalVO votersInfoForMandalVO)
	{
		try{
			if(!votersInfoForMandalVO.getVotersInfoForMandalVOList().isEmpty())
			{
				votersInfoForMandalVO.setSubLevelExists(true);
				
				for(VotersInfoForMandalVO vo : votersInfoForMandalVO.getVotersInfoForMandalVOList())
				{ 
				  if(!(votersInfoForMandalVO.getTotVoters().compareTo( BigDecimal.ZERO) == 0))
				  {
					  Double votes = Double.valueOf(vo.getTotVoters().toString());
					  Double totalVotes = Double.valueOf(votersInfoForMandalVO.getTotVoters().toString());
					  vo.setPercent(new BigDecimal(votes*(100.0)/totalVotes).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else{
					  vo.setPercent("0");  
				  }
			  }
			}
		}catch (Exception e) {
			log.error("Exception Occured in calculatePercentage() Method, Exception is - "+e);
		}
	}
	
	
	public VoterCastInfoVO getVotersCastWiseDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		try{
			if(locationType.equalsIgnoreCase("mandal")){
				String mandalId= locationId.toString();
				String id=mandalId.substring(1);
				locationId = new Long(id);
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					locationType = "mandal";
				}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					locationType = "localElectionBody";
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
					locationId = (Long) list.get(0);
				}
			}
			Long totalVoters = getVotersCountByPublicationIdInALocation(locationType,locationId,publicationDateId);
			Long votesConsidered = 0L;
			voterCastInfoVO.setCastCategoryWiseVotersList(getCastCategoryWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId));
			voterCastInfoVO.setVoterCastInfoVOList(getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId));
			voterCastInfoVO.setTotalCasts(voterCastInfoVO.getVoterCastInfoVOList().size());
			voterCastInfoVO.setTotalVoters(totalVoters);
			
			for(VoterCastInfoVO castInfoVO : voterCastInfoVO.getVoterCastInfoVOList())
				votesConsidered = votesConsidered + castInfoVO.getTotalVoters();
			
			voterCastInfoVO.setMaleVoters(votesConsidered);
			voterCastInfoVO.setFemaleVoters(totalVoters - votesConsidered);
			return voterCastInfoVO;
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCastWiseDetailsInALocation() Method, Exception is - "+e);
			return voterCastInfoVO;
		}
	}
	
	public Long getVotersCountByPublicationIdInALocation(String locationType,Long locationId,Long publicationDateId)
	{
		try{
			return boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation(locationType, locationId, publicationDateId);
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCountByPublicationIdInALocation() method, Exception is - "+e);
			return 0L;
		}
	}
	
	public List<SelectOptionVO> getCastCategoryWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		List<SelectOptionVO> castCategoryWiseList = new ArrayList<SelectOptionVO>(0);
		try{
			List<Object[]> list = boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdInALocation(userId, locationType, locationId, publicationDateId);
			
			if(list != null && list.size() > 0)
				for(Object[] params : list)
					castCategoryWiseList.add(new SelectOptionVO((Long)params[1],params[0].toString()));
			
			return castCategoryWiseList;
		}catch (Exception e) {
			log.error("Exception Ocuured in getCastCategoryWiseVotersCountByPublicationIdInALocation() Method, Exception is - "+e);
			return castCategoryWiseList;
		}
	}
	
	
	public List<VoterCastInfoVO> getCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
		try{
			List<Object[]> list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId);
			
			if(list != null && list.size() > 0)
			{
				VoterCastInfoVO voterCastInfoVO = null;
				Long totalVotes = 0L;
				
				for(Object[] params : list)
				{
					voterCastInfoVO = getVoterCastInfoVOBasedOnCastName(params[0].toString(),resultList);
					boolean isNew = false;
					if(voterCastInfoVO == null)
					{
						voterCastInfoVO = new VoterCastInfoVO();
						voterCastInfoVO.setCastName(params[0].toString());
						isNew = true;
					}
					
					String gender = params[1].toString();
					if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
						voterCastInfoVO.setMaleVoters((Long)params[2]);
					else
						voterCastInfoVO.setFemaleVoters((Long)params[2]);
					
					voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters() + voterCastInfoVO.getFemaleVoters());
					totalVotes = totalVotes + (Long)params[2];
					
					if(isNew)
						resultList.add(voterCastInfoVO);
				}
				
				for(VoterCastInfoVO castInfoVO : resultList)
				{
					String percentage = "0.00";
					try{
						percentage = (new BigDecimal(castInfoVO.getTotalVoters()*(100.0)/totalVotes.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					}catch (Exception e) {}
					finally{
						castInfoVO.setVotesPercent(percentage);
					}
				}
			}
			return resultList;
		}catch (Exception e) {
			log.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - "+e);
			return resultList;
		}
	}
	
	public VoterCastInfoVO getVoterCastInfoVOBasedOnCastName(String casteName,List<VoterCastInfoVO> list)
	{
		try{
			if(list != null && list.size() > 0)
			{
				for(VoterCastInfoVO voterCastInfoVO : list)
					if(voterCastInfoVO.getCastName().equalsIgnoreCase(casteName))
						return voterCastInfoVO;
				return null;
			}
			
			else 
				return null;
		}catch (Exception e) {
			log.error("Exception Occured in getVoterCastInfoVOBasedOnCastName(), Exception is - "+e);
			return null;
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
			
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
			{
			return calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id.toString().substring(1)),publicationDateId));
			}
			else
			{
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
				
			return calculatePercentageForCast(boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody((Long) list.get(0),publicationDateId));
			}
			
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
	

	public VoterCastInfoVO calculatePercentageForUserCast(List params,Long totalcount)
	{
	VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
	SortedMap<String,CastVO> castsMap = new TreeMap<String,CastVO>();
	
	 
	//Set<String> casts = new HashSet<String>();
	CastVO castvo = null;
	
	
	Long totalVoters = 0l;
	String cast ="";
	
	int TotalCasts =0;
	for(int i=0;i<params.size();i++)
	{
		Object[] voterInfo =(Object[])params.get(i);
		
		totalVoters = totalVoters + (Long) voterInfo[2];
		
		String gender = (String)voterInfo[1];
		cast = (String) voterInfo[0];
		
		if(cast.equals(""))
		{
			
			cast = "N/A";
			
		}
		if(castsMap.get(cast) == null){
			castvo = new CastVO();
			castvo.setCastName(cast);
			castvo.setCastStateId((Long) voterInfo[3]);
			castvo.setCastCount((Long) voterInfo[2]);
			//castvo.setGender(voterInfo[1].toString());
			if(gender.equalsIgnoreCase("m"))
			{
				castvo.setMalevoters((Long)voterInfo[2]);
			}
			else if(gender.equalsIgnoreCase("f"))
			{
				castvo.setFemalevoters((Long)voterInfo[2]);
			}
			
			castsMap.put(cast, castvo);
		}
		else{
		   castvo = castsMap.get(cast);
		   castvo.setCastName(cast);
		   castvo.setCastCount(castvo.getCastCount()+(Long) voterInfo[2]);
		   //castvo.setGender(voterInfo[1].toString());
		   if(gender.equalsIgnoreCase("m"))
		   {
			castvo.setMalevoters((Long)voterInfo[2]);
		   }
		   else if(gender.equalsIgnoreCase("f"))
		   {
			castvo.setFemalevoters((Long)voterInfo[2]);
		   }
		 }
		
		
	}
	
	List<CastVO> castVOs = new ArrayList<CastVO>(castsMap.values());
	List<CastVO> castVOs1 = new ArrayList<CastVO>(castsMap.values());
	//Collections.sort(castVOs);
	// Calculate Percentage
	for(int i=0;i<castVOs.size();i++)
	{
		String castPercentage = "0";
		if(totalcount > 0)
		   castPercentage = new BigDecimal((castVOs.get(i).getCastCount()*100.0)/totalcount).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
		castVOs.get(i).setCastPercentage(castPercentage);
		
		
	}
	voterCastInfoVO.setCastVOs(castVOs);
	
	TotalCasts = removeCastNoneElements(castVOs1);
	voterCastInfoVO.setTotalCasts(TotalCasts);
	voterCastInfoVO.setTotalVoters(totalcount);
	voterCastInfoVO.setTotalCastKnownVoters(totalVoters);
	
	
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
		int TotalCasts =0;
		for(int i=0;i<params.size();i++)
		{
			Object[] voterInfo =(Object[])params.get(i);
			
			totalVoters = totalVoters + (Long) voterInfo[0];
			
			String gender = (String)voterInfo[1];
			cast = (String) voterInfo[2];
			if(cast != null)
			{
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
		}
		
		List<CastVO> castVOs = new ArrayList<CastVO>(castsMap.values());
		List<CastVO> castVOs1 = new ArrayList<CastVO>(castsMap.values());
		//Collections.sort(castVOs);
		// Calculate Percentage
		for(int i=0;i<castVOs.size();i++)
		{
			String castPercentage = "0";
			if(totalVoters > 0)
			   castPercentage = new BigDecimal((castVOs.get(i).getCastCount()*100.0)/totalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			castVOs.get(i).setCastPercentage(castPercentage);
			
			
		}
		voterCastInfoVO.setCastVOs(castVOs);
		
		TotalCasts = removeCastNoneElements(castVOs1);
		voterCastInfoVO.setTotalCasts(TotalCasts);
		voterCastInfoVO.setTotalVoters(totalVoters);
		
		
		
		return voterCastInfoVO;
		
		
	}
	
		
	public int removeCastNoneElements(List<CastVO> list)
		{
			int totalCasts = 0;
			
				for(int i=0;i<list.size();i++)
				{
				if(list.get(i).getCastName().equals("N/A"))
				{
					list.remove(i);
				}
					
					
				}
				
				totalCasts = list.size();
			return totalCasts;
			
		}
		public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type,Long userId)
		
		{
			VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
			SelectOptionVO selectOptionVO = null;
			List<SelectOptionVO> booths1 = new ArrayList<SelectOptionVO>();
			List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
			if(type.equalsIgnoreCase("constituency"))
			{
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
				
				mandalCasts = getVotersCastInfoForMultipleMandal(mandalsList,publicationDateId,userId);
				
			}
			
			if(type.equalsIgnoreCase("mandal"))
			{
				if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
				{
				List<SelectOptionVO> panchayatList= staticDataService.getPanchayatiesByMandalId(new Long(id.toString().substring(1)));
				mandalCasts = getVotersCastInfoForMultiplePanchayats(panchayatList,publicationDateId,userId);
				}
				
				
			}
			
			if(type.equalsIgnoreCase("panchayat"))
			{
				List<SelectOptionVO> booths = getBoothsByPanchayatId(id,publicationDateId);
				mandalCasts = getVotersCastInfoForMultipleBooths(booths,publicationDateId,userId);
				
			}
			return mandalCasts;
			
		}
		
		public List<SelectOptionVO> getBoothsByPanchayatId(Long id,Long publicationDateId)
		{
			List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
			//List<Object[]> PollingBooths = hamletBoothPublicationDAO.getBoothsInPanchayatByPublicationId(id,publicationDateId);
			List<Object[]> PollingBooths =  boothDAO.getBoothsInAPanchayat(id,publicationDateId);
			SelectOptionVO hamlet = null;
			for (Object[] panchayat : PollingBooths) {
				hamlet = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
				booths.add(hamlet);
			}
			return booths;	
		}
	//getting All Mandals For Constituency
		
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalList,Long publicationDateId,Long userId)
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
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					List<Object[]> mandalCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"mandal",new Long(id),publicationDateId);
					Long totalVoters = getVotersCountByPublicationIdInALocation("mandal",new Long(id),publicationDateId);
					voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForUserCast(mandalCastDetails,totalVoters));
					//voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id),publicationDateId)));
				}
				//Muncipality
				if(mandalId.substring(0, 1).toString().trim().equalsIgnoreCase("1")){
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id));
					
					List<Object[]> mandalCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"localElectionBody",(Long)list.get(0),publicationDateId);
				Long totalVoters = getVotersCountByPublicationIdInALocation("localElectionBody",new Long(id),publicationDateId);
				voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForUserCast(mandalCastDetails,totalVoters));
				//voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(new Long(id),publicationDateId))); 
				}
				mandalCasts.add(voterCastInfoVO);
				
		}
		}
		return mandalCasts;
		
		
	}
	
//getting All Panchayaties For Mandal
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultiplePanchayats(List<SelectOptionVO> panchayatList,Long publicationDateId,Long userId)
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
				voterCastInfo.setLocationId(panchayatId);
				List<Object[]> panchayatCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"panchayat",panchayatId,publicationDateId);
				Long totalVoters = getVotersCountByPublicationIdInALocation("panchayat",panchayatId,publicationDateId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(panchayatCastDetails,totalVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(new Long(panchayatId),publicationDateId)));
				panchayatsList.add(voterCastInfo);
			}
		}
		return panchayatsList;
	}

	//getting SubLevel Data For Booths
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleBooths(List<SelectOptionVO> boothsList,Long publicationDateId,Long userId)
	{
		VoterCastInfoVO voterCastInfo = null;
		
		List<VoterCastInfoVO> boothInfo = new ArrayList<VoterCastInfoVO>();
		if(boothsList != null)
		{
			for(SelectOptionVO booths :boothsList)
			{
				voterCastInfo = new VoterCastInfoVO();
				Long boothId=booths.getId();
				//String boothPartNo = booths.getId().toString();
				List<Object[]> boothCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"booth",boothId,publicationDateId);
				Long totalVoters = getVotersCountByPublicationIdInALocation("booth",boothId,publicationDateId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(boothCastDetails,totalVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByBoothIdAndPublicationDate(new Long(boothId),publicationDateId)));
				voterCastInfo.setMandalName(booths.getName());
				voterCastInfo.setLocationId(boothId);
				boothInfo.add(voterCastInfo);
			}
		}
		return boothInfo;
		
	}
	
	public List<VoterHouseInfoVO> getFamilyInfo(Long boothId, Long publicationDateId,String houseNo)
	{
		List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfo(boothId, publicationDateId, houseNo);
	    long sno = 1;
		for(Voter voter : votersInfoList){
	    	voterHouseInfoVO = new VoterHouseInfoVO();
	    	voterHouseInfoVO.setsNo(sno);
	    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
	    	voterHouseInfoVO.setName(voter.getName());
	    	voterHouseInfoVO.setGender(voter.getGender());
	    	voterHouseInfoVO.setAge(voter.getAge());
	    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
	    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
	    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
	    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
	    	
	    	voterHouseInfoVO.setCast(voter.getCast());
	    	voterHouseInfoVO.setCastCategory(voter.getCastCatagery());
	    	voterHouseInfoVO.setVoterId(voter.getVoterId());
	    	voterHouseInfoVO.setBoothId(boothId);
	    	voterHouseInfoVOList.add(voterHouseInfoVO);
	    	sno = sno+1;
	    }
		return voterHouseInfoVOList;
	} 
	public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,Long casteStateId,String type)
	{
		
		List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
		List<Voter> list = null;
		//List<Voter> list = boothPublicationVoterDAO.getVoterDetailsByCaste(id, publicationDateId, caste);
		if(type.equalsIgnoreCase("booth"))
		{
		list = boothPublicationVoterDAO.getVoterDetailsByCasteStateForBooth(id,publicationDateId,casteStateId);
		}
		else
		{
		list = boothPublicationVoterDAO.getVoterDetailsByCasteStateForPanchayat(id,publicationDateId,casteStateId);
		}
		VoterHouseInfoVO voterHouseInfoVO = null;
		long sno = 1;
		if(list != null && list.size() > 0)
		{
			for(Voter voter : list)
			{
				voterHouseInfoVO = new VoterHouseInfoVO();
				voterHouseInfoVO.setBoothId(id);
				voterHouseInfoVO.setVoterId(voter.getVoterId());
				voterHouseInfoVO.setsNo(sno);
				//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
				voterHouseInfoVO.setName(voter.getName());
				voterHouseInfoVO.setGender(voter.getGender());
				voterHouseInfoVO.setAge(voter.getAge());
				voterHouseInfoVO.setHouseNo(voter.getHouseNo());
				//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
				voterHouseInfoVO.setGaurdian(voter.getRelativeName());
				voterHouseInfoVO.setRelationship(voter.getRelationshipType());
				voterHouseInfoVO.setCast(voter.getCast());
				voterHouseInfoVO.setCastCategory(voter.getCastCatagery());
				votersList.add(voterHouseInfoVO);
				sno = sno + 1;
			}
		}
		return votersList;
	}
	/**
	 * This method will get overview voters details for a constituency or for a mandal
	 */
	
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type) {		

		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		
		try{ 
			if(type.equalsIgnoreCase("localElectionBody")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(tehsilId);
				tehsilId = (Long) list.get(0);
			}
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
				if(totalVoters != 0){					
				votersDetailsVO.setTotalVotersPercent((float)votersDetailsVO.getTotalVoters()*100f/totalVoters);
				}
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
			
			
			voterDetailsForAgeBetween26To35.setTotalMaleVoters(maleVotersBetween26To35);
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
						.getAgewiseVoterDetailsInSpecifiedRangeByGenderAndConstituncyId(
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
	
	
	//retrieve all the low level stage voters details
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId){
		
		List<Object[]> tehsilIds = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
		List<VotersDetailsVO> mandalVotersList = new ArrayList<VotersDetailsVO>();
		
		
			for(Object[] obj:tehsilIds){
				
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				getDetailsOfVotersHasAgeBetween18And25(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal");
				getDetailsOfVotersHasAgeBetween26And35(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal");			
				getDetailsOfVotersHasAgeBetween36And45(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal");		
				getDetailsOfVotersHasAgeBetween46And60(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal");       
				getDetailsOfVotersHasAgeAbove60(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal");
				
				
			Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
					+ voterDetailsVO.getTotalVotersFor26To35()
					+ voterDetailsVO.getTotalVotersFor36To45()
					+ voterDetailsVO.getTotalVotersFor46To60()
					+ voterDetailsVO.getTotalVotersForAbove60();
						
				voterDetailsVO.setTehsilName(obj[1].toString());
				voterDetailsVO.setTotalVoters(totalVoters);
				
				
				/*voterDetailsVO.setVotersPercentFor18To25((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor26To35((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor36To45((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor46To60((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters);
				voterDetailsVO.setVotersPercentForAbove60((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters);
			*/	
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				mandalVotersList.add(voterDetailsVO);
			}
			
			return mandalVotersList;
	}
	
	public void getDetailsOfVotersHasAgeBetween18And25(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO,String type) {		
		
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
			
			 votersDetailsVO.setTotalMaleVotesFor18To25(maleVotersBetween18To25);
			 votersDetailsVO.setTotalFemaleVotersFor18To25(femaleVotersBetween18To25);
			 votersDetailsVO.setTotalUnknownVotersFor18To25(unKnownVotersBetween18To25);
			 votersDetailsVO.setTotalVotersFor18To25(totalVotersBetween18To25);
			 
			  if(totalVotersBetween18To25 != 0){
				votersDetailsVO.setMaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float) (maleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
				votersDetailsVO.setFemaleVotersPercentFor18To25(roundTo2DigitsFloatValue((float)(femaleVotersBetween18To25 *100f/ totalVotersBetween18To25 )));
			  }else{
				votersDetailsVO.setMaleVotersPercentFor18To25("0.00");
				votersDetailsVO.setFemaleVotersPercentFor18To25("0.00");
			}
		 
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween26And35(Long constituencyId,Long tehsilId,
			Long panchayatId,Long boothId, Long publicationDateId, VotersDetailsVO votersDetailsVO ,String type) {		
		
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
			
			votersDetailsVO.setTotalMaleVotersFor26To35(maleVotersBetween26To35);
			votersDetailsVO.setTotalFemaleVotersFor26To35(femaleVotersBetween26To35);
			votersDetailsVO.setTotalUnknownVotersFor26To35(unKnownVotersBetween26To35);
			votersDetailsVO.setTotalVotersFor26To35(totalVotersBetween26To35);
			
				if(totalVotersBetween26To35 != 0){
					votersDetailsVO.setMaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float) (maleVotersBetween26To35 *100f/ totalVotersBetween26To35 )));
					votersDetailsVO.setFemaleVotersPercentFor26To35(roundTo2DigitsFloatValue((float)(femaleVotersBetween26To35 *100f/ totalVotersBetween26To35 )));
				}else{
					votersDetailsVO.setMaleVotersPercentFor26To35("0.00");
					votersDetailsVO.setFemaleVotersPercentFor26To35("0.00");
			    }
		
       }catch(Exception e){    	   
    	   e.printStackTrace();    	   
       }		
	}
	
	public void getDetailsOfVotersHasAgeBetween36And45(
			Long constituencyId,Long tehsilId,Long panchayatId ,Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO , String type){

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
			
			
					votersDetailsVO.setTotalMaleVotersFor36To45(maleVotersBetween36To45);
					votersDetailsVO.setTotalFemaleVotersFor36To45(femaleVotersBetween36To45);
					votersDetailsVO.setTotalUnknownVotersFor36To45(unKnownVotersBetween36To45);
					votersDetailsVO.setTotalVotersFor36To45(totalVotersBetween36To45);

			if(totalVotersBetween36To45 != 0){
				votersDetailsVO.setMaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float) (maleVotersBetween36To45 *100f/ totalVotersBetween36To45 )));
				votersDetailsVO.setFemaleVotersPercentFor36To45(roundTo2DigitsFloatValue((float)(femaleVotersBetween36To45 *100f/ totalVotersBetween36To45)));
			}else{
				votersDetailsVO.setMaleVotersPercentFor36To45("0.00");
				votersDetailsVO.setFemaleVotersPercentFor36To45("0.00");
			}
			
		
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	
	public void  getDetailsOfVotersHasAgeBetween46And60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO ,String type){
		
		    List<Object[]> votersListOf46To60 = null;
		    
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
	   
	       votersDetailsVO.setTotalMaleVotersFor46To60(maleVotersBetween46To60);
	       votersDetailsVO.setTotalFemaleVotersFor46To60(femaleVotersBetween46To60);
	       votersDetailsVO.setTotalUnknownVotersFor46To60(unKnownVotersBetween46To60);
	       votersDetailsVO.setTotalVotersFor46To60(totalVotersBetween46To60);
       
	       if(totalVotersBetween46To60 != 0){
				votersDetailsVO.setMaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float) (maleVotersBetween46To60 *100f/ totalVotersBetween46To60 )));
				votersDetailsVO.setFemaleVotersPercentFor46To60(roundTo2DigitsFloatValue((float)(femaleVotersBetween46To60 *100f/ totalVotersBetween46To60 )));
			}else{
				votersDetailsVO.setMaleVotersPercentFor46To60("0.00");
				votersDetailsVO.setFemaleVotersPercentFor46To60("0.00");
			}    
				       
       
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public void  getDetailsOfVotersHasAgeAbove60(
			Long constituencyId,Long tehsilId,Long panchayatId,Long boothId,Long  publicationDateId,
			VotersDetailsVO votersDetailsVO ,String type){

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
	          
	          
	          votersDetailsVO.setTotalMaleVotersForAbove60(maleVotersAbove60);
	          votersDetailsVO.setTotalFemaleVotersForAbove60(femaleVotersAbove60);
	          votersDetailsVO.setTotalUnknownVotersForAbove60(unKnownVotersAbove60);
	          votersDetailsVO.setTotalVotersForAbove60(totalVotersAbove60);	 
	        //  votersDetailsVO.setAgeRange("60-Above");
	          if(totalVotersAbove60 != 0){
	        		votersDetailsVO.setMaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float) (maleVotersAbove60 *100f/ totalVotersAbove60 )));
					votersDetailsVO.setFemaleVotersPercentForAbove60(roundTo2DigitsFloatValue((float)(femaleVotersAbove60 *100f/ totalVotersAbove60 )));	        	  
	          }else{
	        	    votersDetailsVO.setMaleVotersPercentForAbove60("0.00");
					votersDetailsVO.setFemaleVotersPercentForAbove60("0.00");	        	  
	          }

	   		
	         // constituencyVotersList.add(voterDetailsForAgeAbove60);
	          
	    }catch(Exception e){
	    	
	    	e.printStackTrace();	    	
	    }
	}
	
	
	
	public List<VotersDetailsVO>   getAgewiseVotersDetailsForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId){
		
		
		List<Object[]> panchayatis = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		for(Object[] obj:panchayatis){

            VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
			
			getDetailsOfVotersHasAgeBetween18And25(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat");
			getDetailsOfVotersHasAgeBetween26And35(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat");			
			getDetailsOfVotersHasAgeBetween36And45(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat");		
			getDetailsOfVotersHasAgeBetween46And60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat");       
			getDetailsOfVotersHasAgeAbove60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat");
			
			voterDetailsVO.setPanchayatname(obj[1].toString());
			
			Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
					+ voterDetailsVO.getTotalVotersFor26To35()
					+ voterDetailsVO.getTotalVotersFor36To45()
					+ voterDetailsVO.getTotalVotersFor46To60()
					+ voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			
*/
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			
			constituencyVotersList.add(voterDetailsVO);
		}
		
		return constituencyVotersList;
		
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId){
		
		List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
		for(Object[] obj:booths){
			
			VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
			
			
			getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth");
			getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth");			
			getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth");		
			getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth");       
			getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth");
			
			voterDetailsVO.setBoothName(obj[1].toString());
			
			Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
					+ voterDetailsVO.getTotalVotersFor26To35()
					+ voterDetailsVO.getTotalVotersFor36To45()
					+ voterDetailsVO.getTotalVotersFor46To60()
					+ voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			*/		
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			boothVotersList.add(voterDetailsVO);
			
		}
		return boothVotersList;
		
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId){
		List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(localElectionBodyId);
		
		List<Object[]> booths= boothDAO.findBoothsInLocalElectionBodyByPublicationDateId((Long) list.get(0),publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
			
			for(Object[] obj:booths){
				
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth");
				getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth");			
				getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth");		
				getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth");       
				getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth");
				
				voterDetailsVO.setBoothName(obj[1].toString());
				
				Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
						+ voterDetailsVO.getTotalVotersFor26To35()
						+ voterDetailsVO.getTotalVotersFor36To45()
						+ voterDetailsVO.getTotalVotersFor46To60()
						+ voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
				voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
				*/	
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			
				boothVotersList.add(voterDetailsVO);
				
			}
			
			return boothVotersList;
		
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
		importantFamiliesInfoVo.setType("Constituency");
		importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
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
			importantFamiliesInfoVo.setType("Mandal/Tehsil");
			importantFamiliesInfoVo.setName(tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName());
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
			importantFamiliesInfoVo.setType("Muncipality/Corporation");
			List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
			List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
			  Object[] reqName = assemblyLocalElectionBodyName.get(0);
			  String name = reqName[0].toString()+" "+reqName[1].toString();
			importantFamiliesInfoVo.setName(name);
			importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getVotersCountForLocalElectionBody((Long) list.get(0),publicationDateId));
			 getImpFamilesInfo(type,(Long) list.get(0),publicationDateId,importantFamiliesInfoVo,"local",exeType);
			 return importantFamiliesInfoVo;
		}
		
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Booth");
		importantFamiliesInfoVo.setName("booth-"+boothDAO.get(id).getPartNo());
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"booth"));
	
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"",exeType);
		 return importantFamiliesInfoVo;
	}

	public ImportantFamiliesInfoVo getImportantFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Panchayat");
		importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		importantFamiliesInfoVo.setTotalVoters((Long)boothPublicationVoterDAO.getVotersCountForPanchayat(id,publicationDateId).get(0));
		 //getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"panchayat",exeType);
		getImpFamilesForPanchayat(id,publicationDateId,importantFamiliesInfoVo);
		 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			 List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
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
	
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long id, Long publicationDateId,String checkedEle)
	{
		List voters = null;
		if(checkedEle.equalsIgnoreCase("panchayat"))
		{
			voters = boothPublicationVoterDAO.findFamiliesVotersInfoForPanchayat(id,publicationDateId);
		}
		if(checkedEle.equalsIgnoreCase("pollingstation"))
		{
			voters = boothPublicationVoterDAO.findFamiliesVotersInfoForBooth(id,publicationDateId);
		}
		Map<Long,Map<String, List<VoterVO>>> boothMap = new HashMap<Long,Map<String, List<VoterVO>>>();
		Map<String, List<VoterVO>> voterByHouseNoMap = null;
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		if(voters != null)
		for(Object[] voter : (List<Object[]>)voters){
			houseNo = voter[1].toString();
			voterVO = new VoterVO();
			voterVO.setFirstName(voter[0].toString());
			voterVO.setAge((Long)voter[2]);
			voterVO.setCast(voter[3] != null ? voter[3].toString() : "");
			voterVO.setBoothNo((Long)voter[4]);
			voterVO.setVoterId(voter[5].toString());
			voterVO.setGender(voter[6].toString());
			voterVO.setAge(voter[7] != null ? (Long)voter[7]:18l);
			voterVO.setBoothName(voter[8].toString());
			
			voterByHouseNoMap = boothMap.get((Long)voter[4]);
			if( voterByHouseNoMap == null){
				voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
				boothMap.put((Long)voter[4], voterByHouseNoMap);
			}
			voterVOs = voterByHouseNoMap.get(houseNo);
			if(voterVOs ==null){
				voterVOs = new ArrayList<VoterVO>();
				voterByHouseNoMap.put(houseNo, voterVOs);
			}
			voterVOs.add(voterVO);
			//voterByHouseNoMap.put(houseNo, voterVOs);
			
		}
		Set<Long> keys = boothMap.keySet();
		for(Long key:keys){
			voterByHouseNoMap = boothMap.get(key);
		for(Map.Entry<String, List<VoterVO>> entry:voterByHouseNoMap.entrySet()){
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterVOs = entry.getValue();
			if(voterVOs.size() == 0)
				continue;
			Collections.sort(voterVOs,sortData);
			voterHouseInfoVO.setBoothId(voterVOs.get(0).getBoothNo());
			voterHouseInfoVO.setBoothName("Booth - "+voterVOs.get(0).getBoothName());
			voterHouseInfoVO.setHouseNo(entry.getKey());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVO.setCast(voterVOs.get(0).getCast());
			voterHouseInfoVO.setElderGender(voterVOs.get(voterVOs.size()-1).getGender());
			voterHouseInfoVO.setElderAge(voterVOs.get(voterVOs.size()-1).getAge());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getFirstName());
			
			VoterVO younger = null;
			
			younger = voterVOs.get(0);
			
			if(!younger.getGender().equalsIgnoreCase(IConstants.MALE))
			{
				for(int i=1;i<(voterVOs.size()-1);i++)
					if(voterVOs.get(i).getGender().equalsIgnoreCase(IConstants.MALE))
							younger = voterVOs.get(i);
			}
			voterHouseInfoVO.setYounger(younger.getFirstName());
			voterHouseInfoVO.setYoungerAge(younger.getAge());
			voterHouseInfoVO.setYoungerGender(younger.getGender());
			
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		}
		
	/*	if(voterHouseInfoVOs.size() > 0)
			voterHouseInfoVOs.get(0).setTotalHousesCount(totalRecords);*/
		return voterHouseInfoVOs;
	}
	
	
	public static Comparator<VoterVO> sortData = new Comparator<VoterVO>()
		    {
		   
		        public int compare(VoterVO voterVO1, VoterVO voterVO2)
		        {
		            return (voterVO1.getAge().intValue()) - (voterVO2.getAge().intValue());
		        }
		    };
		    
		    public void getImpFamilesForPanchayat(Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo){
				List<Object[]>  impFamilesList = null;
				
				/*if(name.equalsIgnoreCase("constituency")){
					impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnConstituencyId(id, publicationDateId);
				}*/
				//else if(name.equalsIgnoreCase("panchayat")){
					impFamilesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationId(id,publicationDateId,null);
				//}
				/*else if(name.equalsIgnoreCase("booth")){
					impFamilesList = boothPublicationVoterDAO.findImpFamilesBasedOnPanchayat(id, publicationDateId);
				}*/
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
					count = (Long) impFamiles[0];	
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
				
				importantFamiliesInfoVo.setAbove10(above10);
				importantFamiliesInfoVo.setAbove10Popul(above10Count);
				importantFamiliesInfoVo.setBetwn7to10(between7To10);
				importantFamiliesInfoVo.setBetwn7to10Popul(between7T10Count);
				importantFamiliesInfoVo.setBetwn4to6Popul(between4To6Count);
				importantFamiliesInfoVo.setBetwn4to6(between4To6);
				importantFamiliesInfoVo.setBelow3(below3);
				importantFamiliesInfoVo.setBelow3Popul(below3Count);
				importantFamiliesInfoVo.setTotalFamalies(above10+between7To10+between4To6+below3);
				importantFamiliesInfoVo.setTotalVoters(above10Count+between7T10Count+between4To6Count+below3Count);
				calculatePercentage(importantFamiliesInfoVo);
			}
		    
		    
public String roundTo2DigitsFloatValue(Float number){
	
	NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
	f.setMaximumFractionDigits(2);  
	f.setMinimumFractionDigits(2);
	
	return f.format(number);
	
}


public VoterHouseInfoVO getVoterPersonalDetailsByVoterId(Long voterId,Long userId){
	
 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
 try{
	   SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
	   defaultSelectOptionVO.setId(0l);
	   defaultSelectOptionVO.setName("Select");
	List<Voter> voterDetails = voterDAO.getVoterPersonalDetailsByVoterId(voterId);
	if(voterDetails != null && voterDetails.size() >0)
	{
		Voter voterInfo = voterDetails.get(0);
	 voterHouseInfoVO.setName(voterInfo.getName());
	 voterHouseInfoVO.setVoterId(voterInfo.getVoterId());
	 voterHouseInfoVO.setGender(voterInfo.getGender());
	 voterHouseInfoVO.setAge(voterInfo.getAge());
	 voterHouseInfoVO.setHouseNo(voterInfo.getHouseNo());
	 voterHouseInfoVO.setGaurdian(voterInfo.getRelativeName());
	 voterHouseInfoVO.setRelationship(voterInfo.getRelationshipType());
	 
	 voterHouseInfoVO.setCast(voterInfo.getCast());
	 
	 //List<SelectOptionVO> casteList = socialService.getAllCasteDetails();
	 
	
	 voterHouseInfoVO.setCastCategory(voterInfo.getCastCatagery());
	 voterHouseInfoVO.setUserId(userId);
	}
	List<Long> stateIdsList = boothPublicationVoterDAO.getVoterStateId(voterId);
    if(stateIdsList != null && stateIdsList.size() > 0 ){
	  List<SelectOptionVO> partiesList = staticDataService.getStaticPartiesListForAState(stateIdsList.get(0));
	  if(partiesList != null){
		  partiesList.add(0, defaultSelectOptionVO);
	     voterHouseInfoVO.setParties(partiesList);
	  }else{
		  List<SelectOptionVO> parties = new ArrayList<SelectOptionVO>();
		  parties.add(defaultSelectOptionVO);
		  voterHouseInfoVO.setParties(parties); ;
	  }
	   List<SelectOptionVO> castsVo = new ArrayList<SelectOptionVO>();
	   SelectOptionVO selectOptionVO = null;
	     
	     castsVo.add(defaultSelectOptionVO);
	   //List<Object[]> castsList = casteStateDAO.getAllCasteDetailsForVoters(stateIdsList.get(0));
	     List<Object[]> castsList = casteStateDAO.getAllCastesForVoters(stateIdsList.get(0), userId);
	   for(Object[] casts:castsList){
		   selectOptionVO = new SelectOptionVO();
		   selectOptionVO.setId((Long)casts[0]);
		   selectOptionVO.setName(casts[1]!=null?casts[1].toString():"");
		   castsVo.add(selectOptionVO);
	   }
	   voterHouseInfoVO.setCasteGroupNameList(castsVo);
    }else{
    	
    	List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
    	SelectOptionVO  party = new SelectOptionVO();
    	party.setId(0l);
    	party.setName("Select");
    	partiesList.add(party);
    	voterHouseInfoVO.setParties(partiesList);
    	voterHouseInfoVO.setCasteGroupNameList(partiesList);
    }
    List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterId,userId);
	if(userVoterDetailsList != null && userVoterDetailsList.size() >0){
		UserVoterDetails userVoterDetails = userVoterDetailsList.get(0);
		if(userVoterDetails.getParty() != null){
			voterHouseInfoVO.setPartyId(userVoterDetails.getParty().getPartyId());
		}else{
			voterHouseInfoVO.setPartyId(0l);
		}
		if(userVoterDetails.getCasteState() != null){
			voterHouseInfoVO.setCasteStateId(userVoterDetails.getCasteState().getCasteStateId());
		}else{
			voterHouseInfoVO.setCasteStateId(0l);
		}
		
	}else{
		
		voterHouseInfoVO.setPartyId(0l);
		voterHouseInfoVO.setCasteStateId(0l);
	}
	
    List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
	//List<UserVoterDetails> userVoterDetails=userVoterDetailsDAO.getUserVoterDetails(voterId, userId);
	List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
	VoterHouseInfoVO category = null;
	for(Object[] userCategoryValue : userCategoryValuesList)
	{ 
		 category = new VoterHouseInfoVO();
				
		 category.setUserCategoryValueId((Long)userCategoryValue[0]);
		 category.setUserCategoryValueName(userCategoryValue[1]!=null?userCategoryValue[1].toString():"");
		 List<Object[]> categoryValuesList =  userVoterCategoryValueDAO.getCategoryValuesByUserIdCategId(userId,(Long)userCategoryValue[0]);
		 List<SelectOptionVO> categoryOptionsList = new ArrayList<SelectOptionVO>();
		 categoryOptionsList.add(defaultSelectOptionVO);
		 SelectOptionVO categoryOption = null;
		 for(Object[] categoryValue : categoryValuesList){
			 categoryOption = new SelectOptionVO();
			 categoryOption.setId((Long)categoryValue[0]);
			 categoryOption.setName(categoryValue[1]!=null?categoryValue[1].toString():"");
			 categoryOptionsList.add(categoryOption);
	     }
		 category.setCategory(categoryOptionsList);
		 List<Long> idsList = voterCategoryValueDAO.getVoterCategoryValue(userId,voterId,(Long)userCategoryValue[0]);
		 if(idsList != null && idsList.size() > 0 && idsList.get(0) != null){
			 category.setCategoryValuesId(idsList.get(0));
		 }
		 
		 categoriesList.add(category);	 
	}
	voterHouseInfoVO.setCategoriesList(categoriesList);
  }catch(Exception e){
	  log.error("Exception rised in getVoterPersonalDetailsByVoterId",e);
  }
		/*for(UserVoterDetails voters : userVoterDetails)
		{ 
			voterHouseInfoVO.setUserVoterDetailsId(voters.getUserVoterDetailsId());
		voterHouseInfoVO.setPartyId(voters.getParty().getPartyId());
		voterHouseInfoVO.setUserId(voters.getUser().getUserId());
		voterHouseInfoVO.setVoterId(voters.getVoter().getVoterId());
		}
		
		
	
		
		List<CategoryValues> categoryValues=userVoterCategoryValueDAO.getCategoryValues();
		
		for(CategoryValues categoryValue : categoryValues)
		{ 
			addressVO = new AddressVO();
		voterHouseInfoVO.setCategoryValuesId(categoryValue.getCategoryValuesId());
		voterHouseInfoVO.setUserCategoryValuesId1(categoryValue.getUserCategoryValues().getUserCategoryValuesId());
		
		addressVO.setCategoryValue(categoryValue.getCategoryValue());
		categories.add(addressVO);
		voterHouseInfoVO.setCategories(categories);
		//voterHouseInfoVO.setCategoryValue(categoryValue.getCategoryValue());
		}
		
		List<VoterCategoryValues> voterCategoryValues= voterCategoryValueDAO.getVoterCategoryValues1();
		
		if(voterCategoryValues!=null)
		for(VoterCategoryValues voterCategoryValue : voterCategoryValues)
		{ 
		voterHouseInfoVO.setVoterCategoryValuesId(voterCategoryValue.getVoterCategoryValuesId());
		voterHouseInfoVO.setCategoryValuesId(voterCategoryValue.getCategoryValues().getCategoryValuesId());
		voterHouseInfoVO.setVoterCategoryValuesName(voterCategoryValue.getCategoryValues().getUserCategoryValues().getUserCategoryName());
		}
			*/
	return voterHouseInfoVO;
	}

public void updateVoterDetails(VoterHouseInfoVO voterHouseInfoVO){
	
	ResultStatus resultStatus = new ResultStatus();
   if(voterHouseInfoVO != null){
	try{
		if(voterHouseInfoVO.getVoterId() == null || voterHouseInfoVO.getUserId() == null )
			return ;
					
		Voter voter =  voterDAO.get(voterHouseInfoVO.getVoterId());
		User user =  userDAO.get(voterHouseInfoVO.getUserId());
		if(voterHouseInfoVO.getCategoriesList() != null && voterHouseInfoVO.getCategoriesList().size() >0){
			for(VoterHouseInfoVO category : voterHouseInfoVO.getCategoriesList()){
				
				  Long userCategoryValueId = category.getUserCategoryValueId();
				  UserVoterCategoryValue userVoterCategoryValue = null;
				    if(category.getCategoryValuesId() != null && category.getCategoryValuesId().longValue() >0l)
				   userVoterCategoryValue = userVoterCategoryValueDAO.get(category.getCategoryValuesId());
				   List<VoterCategoryValue> categoryValuesIds = voterCategoryValueDAO.getVoterCategoryValues(voterHouseInfoVO.getUserId(),voterHouseInfoVO.getVoterId(),userCategoryValueId);
				   VoterCategoryValue voterCategoryVal = null;
				   if(categoryValuesIds != null && categoryValuesIds.size() > 0 && categoryValuesIds.get(0) != null){
					   voterCategoryVal = categoryValuesIds.get(0);
					   voterCategoryVal.setUser(user);
						  voterCategoryVal.setVoter(voter);
						  voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValue);
						  voterCategoryValueDAO.save(voterCategoryVal);
				  }else if(userVoterCategoryValue != null){
					  voterCategoryVal = new VoterCategoryValue();
					  voterCategoryVal.setUser(user);
					  voterCategoryVal.setVoter(voter);
					  voterCategoryVal.setUserVoterCategoryValue(userVoterCategoryValue);
					  voterCategoryValueDAO.save(voterCategoryVal);
				  }
				      
				
			}
		}
		List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId());
		Long partyId = voterHouseInfoVO.getPartyId();
		Long casteStateId = voterHouseInfoVO.getCasteStateId();
		if(partyId != null &&  partyId.longValue() == 0l)
			partyId = null;
		if(casteStateId != null &&  casteStateId.longValue() == 0l)
			casteStateId = null;
		if(userVoterDetailsList != null && userVoterDetailsList.size() > 0){
			userVoterDetailsDAO.updateUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);
		}else{
			if(casteStateId != null || partyId != null){
			 UserVoterDetails userVoterDtls = new UserVoterDetails();
			 userVoterDtls.setUser(user);
			 userVoterDtls.setVoter(voter);
			 if(casteStateId != null)
				 userVoterDtls.setCasteState(casteStateDAO.get(casteStateId));
			 if(partyId != null)
				 userVoterDtls.setParty(partyDAO.get(partyId));
			 userVoterDetailsDAO.save(userVoterDtls);
			}
		}
	 
	}
	catch (Exception e) {
		log.error("Exception rised in updateVoterDetails : ",e);
	}
   }
	
}

public VoterHouseInfoVO getVoterFullInformation(Long voterId){
	VoterHouseInfoVO voterHouseInfoVO=new VoterHouseInfoVO();
	
	try{
		
		getVoterDetails(voterId,voterHouseInfoVO);	
		
	}catch (Exception e) {
		System.out.println(e);
	}
	
	return voterHouseInfoVO;
}

public void saveVoterDetails(VoterHouseInfoVO voterHouseInfoVO,Voter voter,UserVoterDetails userVoterDetails,VoterCategoryValue voterCategoryValues,UserVoterCategoryValue categoryValues){
	// userVoterDetailsDAO = null;
	if(voterHouseInfoVO.getCast() != null && !voterHouseInfoVO.getCast().equalsIgnoreCase("")){
		voter.setCast(voterHouseInfoVO.getCast());
	}
	if(voterHouseInfoVO.getPartyId() != null && !voterHouseInfoVO.getPartyId().equals(0)){
		
		userVoterDetails.setParty(partyDAO.get(voterHouseInfoVO.getPartyId()));
		userVoterDetails.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
		userVoterDetails.setVoter(voterDAO.get(voterHouseInfoVO.getVoterId()));
		userVoterDetailsDAO.save(userVoterDetails);
	}
	
	if(voterHouseInfoVO.getCategory() != null){
		for(int i=0;i<voterHouseInfoVO.getCategory().size();i++){
			
			if(voterHouseInfoVO.getCategoryValuesId() !=null && !voterHouseInfoVO.getCategoryValuesId().equals(0)){
				
				categoryValues.setUserVoterCategory(userVoterCategoryDAO.get(voterHouseInfoVO.getUserCategoryValuesId1()));
				categoryValues.setCategoryValue(voterHouseInfoVO.getCategory().get(i).getValue());
				categoryValues.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
				userVoterCategoryValueDAO.save(categoryValues);
			}
			if(voterHouseInfoVO.getCategory().get(i).getValue() != null && !voterHouseInfoVO.getCategory().get(i).getValue().equalsIgnoreCase("")){
				voterCategoryValues.setUserVoterCategoryValue(userVoterCategoryValueDAO.get(voterHouseInfoVO.getCategoryValuesId()));
				voterCategoryValues.setVoter(voterDAO.get(voterHouseInfoVO.getVoterId()));
				voterCategoryValues.setUser(userDAO.get(voterHouseInfoVO.getUserId()));
				voterCategoryValueDAO.save(voterCategoryValues);
			}
	}
	}
	//if(voterHouseInfoVO.getCategory() != null)
	//for(int i=0;i<voterHouseInfoVO.getCategory().size();i++){
		/*
	if(voterHouseInfoVO.getCategoryValuesId() !=null && !voterHouseInfoVO.getCategoryValuesId().equals(0)){
		
		categoryValues.setUserCategoryValues(userVoterCategoryDAO.get(voterHouseInfoVO.getUserCategoryValuesId()));
		categoryValues.setCategoryValue(voterHouseInfoVO.getCategory().get(i).getValue());
		userVoterCategoryValueDAO.save(categoryValues);
	}*/
	
	//if(voterHouseInfoVO.getCategory() != null)
	//if(voterHouseInfoVO.getCategory().get(i).getValue() != null && !voterHouseInfoVO.getCategory().get(i).getValue().equalsIgnoreCase("")){
		//userVoterCategoryValueDAO.get(voterHouseInfoVO.getCategoryValuesId())
		//		voterDAO.get(voterHouseInfoVO.getVoterId())
		//voterCategoryValues.setCategoryValues(userVoterCategoryValueDAO.get(1l));
		//voterCategoryValues.setVoter(voterDAO.get(1l));
		//voterCategoryValueDAO.save(voterCategoryValues);
		//voterCategoryValueDAO.flushAndclearSession();
		//}
	//}
}

public void getVoterDetails(Long voterId,VoterHouseInfoVO voterHouseInfoVO){
	
	List<Voter> voterDetails=voterDAO.getVoterPersonalDetailsByVoterId(voterId);
	if(voterDetails.size()>0){
		for(Voter voterInfo:voterDetails){
			voterHouseInfoVO.setCast(voterInfo.getCast());
		}	
	}	
	
}

public ResultStatus insertVoterData(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	try{
		Date d3 = new Date();
		int max = 1000;
		Map<Long,Long> boothsMap = getBoothsMapInAConstituency(constituencyId,publicationDateId);
		for(;;)
		{
			Date d1 = new Date(); 
			List<VoterTemp> voterTempData = voterTempDAO.getVotersInAConstituency(constituencyId,startIndex,max);
			
			if(voterTempData != null && voterTempData.size() > 0)
			{
				Voter voter = null;
				BoothPublicationVoter boothPublicationVoter = null;

				for(VoterTemp voterTemp : voterTempData)
				{
					try{
					voter = new Voter();
					boothPublicationVoter = new BoothPublicationVoter();
					voter.setVoterIDCardNo(voterTemp.getVoterId());
					voter.setName(voterTemp.getName());
					voter.setHouseNo(voterTemp.getHouseNo());
					voter.setRelativeName(voterTemp.getGuardianName());
					voter.setRelationshipType(voterTemp.getRelationShip());
					voter.setGender(voterTemp.getSex().equalsIgnoreCase("Male") ? IConstants.MALE : IConstants.FEMALE);
					voter.setAge(Long.parseLong(voterTemp.getAge().trim()));
					voter.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					
					voter = voterDAO.save(voter);
					
					boothPublicationVoter.setVoter(voter);
					boothPublicationVoter.setBoothId(boothsMap.get(voterTemp.getPartNo()));
					boothPublicationVoterDAO.save(boothPublicationVoter);
					}catch (Exception e) {}
				}
				voterDAO.flushAndclearSession();
				Date d2 = new Date();
				System.out.println("1000 Records inserted in "+(d2.getTime()-d1.getTime())/(1000*60*60)+" Seconds");
				maxResults = maxResults - 1000;
				startIndex = startIndex + 1000;
				if(maxResults <= 0)
					break;
				if(maxResults <= 1000)
					max = maxResults;
			}
		}
		
		Date d4 = new Date();
		Double d5 = (new Double(d4.getTime() - d3.getTime()))/(1000*60);
		System.out.println("Time Taken - "+d5+" Mins");
		return resultStatus;
	}catch (Exception e) {
		log.error("Exception Occured in insertVoterData() Method, Exception is - "+e);
		return resultStatus;
	}
}
public VoterHouseInfoVO getBoothDetailsForVoter(Long boothId){
	List<Booth> boothDetails=boothDAO.getBoothDetailsByBoothId(boothId);
	VoterHouseInfoVO voterHouseInfoVO = null;
	
		for(Booth booth : boothDetails)
		{
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterHouseInfoVO.setBoothName(booth.getLocation());
			voterHouseInfoVO.setVilliageCovered(booth.getVillagesCovered());
			if(booth.getPanchayat() != null)
			  voterHouseInfoVO.setPanchayatName(booth.getPanchayat().getPanchayatName());
			else
				voterHouseInfoVO.setPanchayatName("");
		}
	
	return voterHouseInfoVO;
}

public List<VoterHouseInfoVO> getUserCategoryValues(){
	List<VoterHouseInfoVO> voterHouseInfoValues = new ArrayList<VoterHouseInfoVO>();
	VoterHouseInfoVO voterHouseInfoVO = null;
	List<UserVoterCategory> categoryValues=userVoterCategoryDAO.getUserCategoryValues();
	if(categoryValues != null && categoryValues.size()>0)
		for(UserVoterCategory categoryValue : categoryValues){
			if(categoryValue.getUserVoterCategoryId() != null){
				voterHouseInfoVO = new VoterHouseInfoVO();
			voterHouseInfoVO.setsNo(categoryValue.getUserVoterCategoryId());
			voterHouseInfoVO.setName(categoryValue.getCategoryName());
			voterHouseInfoValues.add(voterHouseInfoVO);
			}
		}
	return voterHouseInfoValues;
	
}

public List<SelectOptionVO> getVoterCategoryValues(Long voterCategoryId,String letters){
		
	List<SelectOptionVO> voterCategoryValues = new ArrayList<SelectOptionVO>();
	List<Object[]> categoryValues =userVoterCategoryValueDAO.getVoterCategoryValues(voterCategoryId,letters);
	
	for(Object[] categoryValue:categoryValues){
		   if(categoryValue[0]!= null && categoryValue[1] != null)
			   voterCategoryValues.add(new SelectOptionVO((Long)categoryValue[0], categoryValue[1].toString().trim().toUpperCase()));
	}
	return voterCategoryValues;	
}


public Map<Long,Long> getBoothsMapInAConstituency(Long constituencyId,Long publicationDateId)
{
	Map<Long,Long> boothsMap = new HashMap<Long, Long>(0);
	try{
		List<Object[]> list = boothDAO.getBoothsInAConstituencyByPublication(constituencyId,publicationDateId);
		
		if(list != null && list.size() > 0)
			for(Object[] params : list)
			{
				try{
				boothsMap.put(Long.parseLong(params[1].toString().trim()),(Long)params[0]);
				}catch (Exception e) {
					log.error("Exception Occured - "+e);
				}
			}
				
		return boothsMap;
	}catch (Exception e) {
		log.error("Exception occured in getBoothsMapInAConstituency(), Exception is - "+e);
		return boothsMap;
	}
}
public SelectOptionVO storeGroupName(final Long userId ,final String name)
{
	
	SelectOptionVO selectOptionVO = new SelectOptionVO();
	
	UserVoterCategory userCategoryValues = (UserVoterCategory)transactionTemplate.execute(new TransactionCallback() {
		
	public Object doInTransaction(TransactionStatus status) {
	List<Long> count = userVoterCategoryDAO.checkCategoryExist(userId, name);
	if(count != null && count.size()> 0 && ((Long)count.get(0))==0){
		UserVoterCategory userCategoryValues = new UserVoterCategory();
	userCategoryValues.setCategoryName(name);
	userCategoryValues.setUser(userDAO.get(userId));
	userCategoryValues = userVoterCategoryDAO.save(userCategoryValues);
	return userCategoryValues;
	}
	else
	 return null;	
		}
		});
	if(userCategoryValues != null){
	selectOptionVO.setId(userCategoryValues.getUserVoterCategoryId());
	selectOptionVO.setName(userCategoryValues.getCategoryName());
	}
	return  selectOptionVO;
	
}

@SuppressWarnings("null")
public List<SelectOptionVO> findVoterCategoryValues(Long UserId)
{
	List<SelectOptionVO> voterCategoryValues = new ArrayList<SelectOptionVO>();
	try{
		log.debug("enter into the voterCategoryValues method of VoterAnalysisService");
	SelectOptionVO selectOptionVO = null;
	List<Object[]> categoryValues = userVoterCategoryDAO.getCategoryValuesList(UserId);
	if(categoryValues !=null && categoryValues.size()> 0)
	{
		for (Object[] categoryValue : categoryValues)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long) categoryValue[0]);
			selectOptionVO.setName((String)categoryValue[1]);
			voterCategoryValues.add(selectOptionVO);
		}
	}
	}catch (Exception e) {
		log.error("exception raised in voterCategoryValues method of VoterAnalysisService" +e );
	}
	return voterCategoryValues;
	
}

public SelectOptionVO storeCategoryVakues(final Long userId, final String name, final Long id)
{
	SelectOptionVO selectOptionVO = new SelectOptionVO();
	
				List<Long> count = userVoterCategoryValueDAO.checkCategoryExist(userId, name,id);
				if(count != null && count.size()> 0 && ((Long)count.get(0))==0){
				 UserVoterCategoryValue categoryValues = new UserVoterCategoryValue();
				 UserVoterCategory userCategoryValues = new UserVoterCategory();
				categoryValues.setUser(userDAO.get(userId));
				categoryValues.setCategoryValue(name);
				categoryValues.setUserVoterCategory(userVoterCategoryDAO.get(id));
				categoryValues = userVoterCategoryValueDAO.save(categoryValues);
				
				}
				
				return selectOptionVO;
}


	public List<SelectOptionVO> getAllPublicationDates()
	{
		List<SelectOptionVO> publicationDateslist =  new ArrayList<SelectOptionVO>(0);
		SelectOptionVO selectOptionVO = null;
		try{
			
			List<Object[]> list = publicationDateDAO.getAllPublicationDates();
			if(list != null && list.size() > 0)
			{
				
				for(Object[] params : list)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					publicationDateslist.add(selectOptionVO);
				}
			}
			return publicationDateslist;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAllPublicationDates() Method, Exception - "+e);
			return publicationDateslist;
		}
	}
	
	public List<SelectOptionVO> getConstituenciesList()
	{
		List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
		SelectOptionVO selectOptionVO = null;
		try{
			
			List<Object> list = voterTempDAO.getconstituencyNames();
			
			if(list != null && list.size() > 0)
			{
				List<String> constituencyNamesList = new ArrayList<String>(0);
				for(Object param : list)
					constituencyNamesList.add(param.toString());
				
				List<Object[]> list2 = voterTempDAO.getConstituencies(constituencyNamesList);
				for(Object[] params : list2)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					constituencyList.add(selectOptionVO);
				}
			}
			return constituencyList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error(" Exception Occured in getConstituenciesList() Method, Exception - "+e);
			return constituencyList;
		}
	}
	
		
	public ResultStatus saveCasteName(Long userId, Long stateId, Long casteCategoryGroupId, String casteName)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			CasteState casteState = null;
			Caste caste = casteDAO.getCasteByCastName(casteName);
			
			if(caste == null)
			{
				caste = new Caste();
				caste.setCasteName(casteName);
				caste = casteDAO.save(caste);
			}
			CasteState casteState2 = casteStateDAO.getCasteStateByCasteId(userId, stateId, caste.getCasteId(), casteCategoryGroupId);
			if(casteState2 == null)
			{
				casteState = new CasteState();
				casteState.setUser(userDAO.get(userId));
				casteState.setCasteCategoryGroup(casteCategoryGroupDAO.get(casteCategoryGroupId));
				casteState.setIsGlobal(IConstants.FALSE);
				casteState.setState(stateDAO.get(stateId));
				casteState.setCaste(caste);
				casteStateDAO.save(casteState);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveCasteName() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	
		
	public List<SelectOptionVO> getcastCategoryGroups()
	{
		List<SelectOptionVO> castCategoryList = new ArrayList<SelectOptionVO>(0);
		try{
			List<Object[]> list = casteCategoryGroupDAO.getAllCasteCategoryGroupInfoDetails();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					castCategoryList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				}
			}
			return castCategoryList;
		}catch (Exception e) {
			log.error("Exception Occured in getcastCategoryGroups() Method, Exception - "+e);
			return castCategoryList;
		}
	}

	 public List<SelectOptionVO> getBoothsInMunicipality(Long lclElecBodyId,Long publicationDateId){
		 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		try{
			List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(lclElecBodyId);
			
		 List<Object[]> booths = boothDAO.getBoothsInAMunicipality((Long)listId.get(0),publicationDateId);
		 SelectOptionVO selectOptionVO = null;
		 
		 for(Object[] booth:booths){
			 selectOptionVO = new SelectOptionVO();
			 selectOptionVO.setId((Long)booth[0]);
			 selectOptionVO.setName(booth[1]!=null?"Booth No- "+booth[1].toString():"");
			 list.add(selectOptionVO);
		 }
		}catch(Exception e){
			log.error("Exception rised in getBoothsInMunicipality ",e);
		}
		 return list;
		 
	 }
}
