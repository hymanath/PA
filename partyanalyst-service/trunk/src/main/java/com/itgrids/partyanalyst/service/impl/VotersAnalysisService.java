package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.CrossVotedMandalVO;
import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.PartyVotesEarnedVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.HamletBoothPublication;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterCategory;
import com.itgrids.partyanalyst.model.UserVoterCategoryValue;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterAgeInfo;
import com.itgrids.partyanalyst.model.VoterCategoryValue;
import com.itgrids.partyanalyst.model.VoterFamilyInfo;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.model.VoterModification;
import com.itgrids.partyanalyst.model.VoterTemp;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
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
	private IElectionDAO electionDAO;
    private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IConstituencyPageService constituencyPageService;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
    private IVoterAgeRangeDAO voterAgeRangeDAO;
    private IVoterFamilyInfoDAO voterFamilyInfoDAO;
    private IVoterFamilyRangeDAO voterFamilyRangeDAO;
    private IVillageBoothElectionDAO villageBoothElectionDAO;
    private IPublicationDateDAO publicationDAO;
    private ICandidateBoothResultDAO candidateBoothResultDAO;
    private IUserRelationDAO userRelationDAO;
    private ILocalityDAO localityDAO;
    private IHamletDAO hamletDAO;
    private ILocalElectionBodyDAO localElectionBodyDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    private IBoothLocalBodyWardDAO boothLocalBodyWardDAO;
    private ILocalElectionBodyWardDAO localElectionBodyWardDAO;
    private IVoterModificationTempDAO voterModificationTempDAO;
    private IVoterModificationDAO voterModificationDAO;
    
   	public IVoterModificationDAO getVoterModificationDAO() {
		return voterModificationDAO;
	}

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}

	public IVoterModificationTempDAO getVoterModificationTempDAO() {
		return voterModificationTempDAO;
	}

	public void setVoterModificationTempDAO(
			IVoterModificationTempDAO voterModificationTempDAO) {
		this.voterModificationTempDAO = voterModificationTempDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ILocalityDAO getLocalityDAO() {
		return localityDAO;
	}

	public void setLocalityDAO(ILocalityDAO localityDAO) {
		this.localityDAO = localityDAO;
	}

	public IPublicationDateDAO getPublicationDAO() {
   		return publicationDAO;
   	}

   	public void setPublicationDAO(IPublicationDateDAO publicationDAO) {
   		this.publicationDAO = publicationDAO;
   	}
    
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

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	
	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IVoterReportLevelDAO getVoterReportLevelDAO() {
		return voterReportLevelDAO;
	}

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}

	public IVoterFamilyInfoDAO getVoterFamilyInfoDAO() {
		return voterFamilyInfoDAO;
	}

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public IVoterFamilyRangeDAO getVoterFamilyRangeDAO() {
		return voterFamilyRangeDAO;
	}

	public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
		this.voterFamilyRangeDAO = voterFamilyRangeDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	public IBoothLocalBodyWardDAO getBoothLocalBodyWardDAO() {
		return boothLocalBodyWardDAO;
	}

	public void setBoothLocalBodyWardDAO(
			IBoothLocalBodyWardDAO boothLocalBodyWardDAO) {
		this.boothLocalBodyWardDAO = boothLocalBodyWardDAO;
	}

	
	public IUserRelationDAO getUserRelationDAO() {
		return userRelationDAO;
	}

	public void setUserRelationDAO(IUserRelationDAO userRelationDAO) {
		this.userRelationDAO = userRelationDAO;
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
					voterVO.setVoterIds(voter.getVoterId());
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(voter.getName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeName());
					voterVO.setRelationshipType(voter.getRelationshipType());
					voterVO.setCast(voter.getCast());
					voterVO.setCastCatagery(voter.getCastCatagery());
					voterVO.setVoterIDCardNo(voter.getVoterIDCardNo());
					voterVO.setMobileNo(voter.getMobileNo()!=null ? voter.getMobileNo() :" ");
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
	
	/*public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId){
		try{
			List<Election> electionIds = getPrevElections(publicationDateId);
			if(type.equalsIgnoreCase("constituency")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForConstituency(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"constituency");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("mandal")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForMandal(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"mandal");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("booth")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForBooth(type,id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"booth");
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("panchayat")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForPanchayat(id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat");
				getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				return votersInfoForMandalVO;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("Exception rised in getVotersCount method : ",e);
		}
		VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		   votersInfoForMandalVO.setDatapresent(false);
		   return votersInfoForMandalVO;
	}*/
	
	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}

	public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId,Long constituencyId){
		try{
			List<Election> electionIds = getPrevElections(publicationDateId);
			if(type.equalsIgnoreCase("constituency")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForConstituency(type, id, publicationDateId,"main");
				if(!votersInfoForMandalVO.isDatapresent())
				  votersInfoForMandalVO = getVotersCountForConstituency(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"constituency",constituencyId);
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("mandal")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForMandal(type, id, publicationDateId, "main");
				if(!votersInfoForMandalVO.isDatapresent())
					 votersInfoForMandalVO = getVotersCountForMandal(type,id,publicationDateId);
				if(electionIds != null && electionIds.size() > 0)
					getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"mandal",constituencyId);
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("booth")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),"main");
				if(!votersInfoForMandalVO.isDatapresent())
				  votersInfoForMandalVO = getVotersCountForBooth(type,id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"booth",constituencyId);
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("panchayat")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForPanchayat(id, publicationDateId, "main");
				if(!votersInfoForMandalVO.isDatapresent())
				 votersInfoForMandalVO = getVotersCountForPanchayat(id,publicationDateId,"main");
				getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat",constituencyId);
				getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				return votersInfoForMandalVO;
			}
			else if(type.equalsIgnoreCase("ward")){
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForWard(id, publicationDateId, "main");
				if(!votersInfoForMandalVO.isDatapresent())
				 votersInfoForMandalVO = getVotersCountForWard(id,publicationDateId,"main");
				//getPrevElectVotersCount(electionIds,id,votersInfoForMandalVO,"panchayat");
				//getBoothsComparisionInfo(electionIds,id,publicationDateId,votersInfoForMandalVO);
				return votersInfoForMandalVO;
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
			Constituency constituency = constituencyDAO.get(id);
		  VotersInfoForMandalVO votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,id,constituency.getName(),"Constituency");
		   //getting  all mandals and muncipalities and ghmcs in constituency
		  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		  if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && mandalsList != null && mandalsList.size() == 1){
			  String localBodyId = mandalsList.get(0).getId().toString();
			  if(localBodyId.substring(0,1).trim().equalsIgnoreCase("1")){
				  getVotersCountForMultipleWards(new Long(localBodyId.substring(1)),publicationDateId,votersInfoForMandalVO);
			  }
			  return votersInfoForMandalVO;
		  }else{
			  //getting voters count for all mandals and muncipalities and ghmcs in constituency
			  getVotersCountForMultipleMandal(mandalsList,publicationDateId,votersInfoForMandalVO);
			  calculatePercentage(votersInfoForMandalVO);
			  return votersInfoForMandalVO;
		  }
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
		}else{
			 getVotersCountForMultipleWards(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO);
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
	
	public VotersInfoForMandalVO getVotersCountForWard(Long id,Long publicationDateId,String reqType ){
		 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
		 votersInfoForMandalVO.setDatapresent(false);

			List<Object[]> votersCountList = boothPublicationVoterDAO.getGenderWiseVotersCountForWard(id,publicationDateId);
			if(reqType.equalsIgnoreCase("main")){
			if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
			   votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"Ward");
			  List<Long> boothsList = boothDAO.getBoothIdsForWard(id,publicationDateId);
			   for(Long boothId:boothsList)
			     votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",boothId,publicationDateId,"sub")); 
			  calculatePercentage(votersInfoForMandalVO);
			  return votersInfoForMandalVO;
			}
		  }
			return populateDataToVotersInfoForMandalVO(votersCountList,id,constituencyDAO.get(id).getName(),"Ward");
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
	
	
	public VotersInfoForMandalVO populateDataToVotersInfoForMandalVOForBooth(List<Object[]> votersCountList,Long id,String name,String type){
		Long maleVotersCount = 0l;
		Long femaleVotersCount = 0l;
		Long totalCount = 0l;
		
		Object[] votersCount = votersCountList.get(0);
		
	   // for(Object[] votersCount : votersCountList){
	    	if(votersCount[0] != null){
	    		maleVotersCount = (Long)votersCount[0];
	    	}
	    	if(votersCount[1] != null){
	    		femaleVotersCount = (Long)votersCount[1];
	    	}
	    	if(votersCount[2] != null){
	    		totalCount = (Long)votersCount[2];
	    	}
	   // }
	    VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
	    votersInfoForMandalVO.setTotalMaleVoters(maleVotersCount.toString());
	    votersInfoForMandalVO.setTotalFemaleVoters(femaleVotersCount.toString());
	  //  votersInfoForMandalVO.setUnKnowVoters(unknownsCount.toString());
	    votersInfoForMandalVO.setId(id);
		votersInfoForMandalVO.setName(name);
		votersInfoForMandalVO.setType(type);
	  //  BigDecimal totalCount = new BigDecimal(maleVotersCount.longValue()+femaleVotersCount.longValue()+unknownsCount.longValue());
	    votersInfoForMandalVO.setTotVoters(new BigDecimal(totalCount.longValue()));
	    
	    return votersInfoForMandalVO;
	}
	
	public void getVotersCountForMultiplePanchayat(Long mandalId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(mandalId);
		for (Object[] panchayat : panchayaties){
			votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForPanchayat((Long)panchayat[0],publicationDateId,"sub"));
		}
	}
	
	public void getVotersCountForMultipleWards(Long assemblyLocalBodyId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalBodyId);
		List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
		for (Object[] ward : wardsList){
			votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForWard((Long)ward[0],publicationDateId,"sub"));
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
		String locationType1 = locationType;
		try{
			if(locationType.equalsIgnoreCase("mandal"))
			{
				String mandalId= locationId.toString();
				String id=mandalId.substring(1);
				locationId = new Long(id);
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					locationType = "mandal";
					locationType1 = "mandal";
				}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					locationType = "localElectionBody";
					locationType1 = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
					locationId = (Long) list.get(0);
				}
			}
			Long totalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(locationType1),locationId,publicationDateId);
			Long votesConsidered = 0L;
			
			
			voterCastInfoVO.setCastCategoryWiseVotersList(getCastCategoryWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId));
			voterCastInfoVO.setVoterCastInfoVOList(getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId));
			voterCastInfoVO.setTotalCasts(voterCastInfoVO.getVoterCastInfoVOList().size());
			voterCastInfoVO.setTotalVoters(totalVoters);
			
			for(VoterCastInfoVO castInfoVO : voterCastInfoVO.getVoterCastInfoVOList())
				votesConsidered = votesConsidered + castInfoVO.getTotalVoters();
			
			voterCastInfoVO.setMaleVoters(votesConsidered);
			voterCastInfoVO.setFemaleVoters(totalVoters - votesConsidered);
			
			//voterCastInfoVO.setCastVOs(getCastWisePartyCount(userId,locationType,locationId,publicationDateId));
			return voterCastInfoVO;
		}catch (Exception e) {
			log.error("Exception Occured in getVotersCastWiseDetailsInALocation() Method, Exception is - "+e);
			return voterCastInfoVO;
		}
	}
	
	public VoterCastInfoVO getVotersPartyDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		String locationType1 = locationType;
		try{
			if(locationType.equalsIgnoreCase("mandal"))
			{
				String mandalId= locationId.toString();
				String id=mandalId.substring(1);
				locationId = new Long(id);
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					locationType = "mandal";
					locationType1 = "mandal";
				}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					locationType = "localElectionBody";
					locationType1 = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
					locationId = (Long) list.get(0);
				}
			}
			Long partyWisevotesConsidered = 0L;
			Long totalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(locationType1),locationId,publicationDateId);
			
			voterCastInfoVO.setPartyWisevoterCastInfoVOList(getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId));
			for(VoterCastInfoVO partyWisecastInfoVO : voterCastInfoVO.getPartyWisevoterCastInfoVOList())
				partyWisevotesConsidered = partyWisevotesConsidered + partyWisecastInfoVO.getTotalVoters();
				voterCastInfoVO.setPartyWiseAssignedVoters(partyWisevotesConsidered);
				voterCastInfoVO.setPartyWiseNotAssignedVoters(totalVoters - partyWisevotesConsidered);
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
					if(params[4] != null)
					voterCastInfoVO.setCasteCategoryName(params[4].toString());
					if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
						voterCastInfoVO.setMaleVoters((Long)params[2]);
					else
						voterCastInfoVO.setFemaleVoters((Long)params[2]);
					
					voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters() + voterCastInfoVO.getFemaleVoters());
					totalVotes = totalVotes + (Long)params[2];
					voterCastInfoVO.setCasteStateId((Long)params[3]);
					
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
	
	
	public List<VoterCastInfoVO> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(Long userId,String locationType,Long locationId,Long publicationDateId)
	{
		List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
		try{
			List<Object[]> list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationId,publicationDateId);
			
			if(list != null && list.size() > 0)
			{
				VoterCastInfoVO voterCastInfoVO = null;
				Long totalVotes = 0L;
				
				for(Object[] params : list)
				{
					voterCastInfoVO = getPartyWiseCastInfoVOBasedOnPartyName(params[0].toString(),resultList);
					boolean isNew = false;
					if(voterCastInfoVO == null)
					{
						voterCastInfoVO = new VoterCastInfoVO();
						
						if(params[0] != null)
						voterCastInfoVO.setPartyName(params[0].toString());
						isNew = true;
					}
					
					String gender = params[1].toString();
					
					if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
						voterCastInfoVO.setMaleVoters((Long)params[2]);
					else
						voterCastInfoVO.setFemaleVoters((Long)params[2]);
					
					voterCastInfoVO.setTotalVoters(voterCastInfoVO.getMaleVoters() + voterCastInfoVO.getFemaleVoters());
					totalVotes = totalVotes + (Long)params[2];
					voterCastInfoVO.setPartyId((Long)params[3]);
					
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
	public VoterCastInfoVO getPartyWiseCastInfoVOBasedOnPartyName(String partyName,List<VoterCastInfoVO> list)
	{
		try{
			if(list != null && list.size() > 0)
			{
				for(VoterCastInfoVO voterCastInfoVO : list)
					if(voterCastInfoVO.getPartyName().equalsIgnoreCase(partyName))
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
			if(voterInfo[4]!= null)
			castvo.setCasteCategoryName(voterInfo[4].toString());
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
		   if(voterInfo[4]!= null)
		   castvo.setCasteCategoryName(voterInfo[4].toString());
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
				}else{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
					List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
					mandalCasts = getVotersCastInfoForMultipleWards(wardsList,publicationDateId,userId);
				}				
			}
			if(type.equalsIgnoreCase("panchayat"))
			{
				List<SelectOptionVO> booths = getBoothsByPanchayatId(id,publicationDateId);
				mandalCasts = getVotersCastInfoForMultipleBooths(booths,publicationDateId,userId);
				
			}
			if(type.equalsIgnoreCase("ward"))
			{
				List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
				List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
				SelectOptionVO option = null;
				for(Object[] booth:boothsList){
					option = new SelectOptionVO();
					option.setId((Long)booth[0]);
					option.setName(booth[1]!=null?booth[1].toString():"");
					booths.add(option);
				}
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
				hamlet.setLocation(panchayat[2] != null?panchayat[2].toString():"");
				hamlet.setVillageCovered(panchayat[3] != null?panchayat[3].toString():"");
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
					Long totalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("mandal"),new Long(id),publicationDateId);
					if(totalVoters == null)
						totalVoters = getVotersCountByPublicationIdInALocation("mandal",new Long(id),publicationDateId);
					voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForUserCast(mandalCastDetails,totalVoters));
					//voterCastInfoVO.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(new Long(id),publicationDateId)));
				}
				//Muncipality
				if(mandalId.substring(0, 1).toString().trim().equalsIgnoreCase("1")){
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id));
					
					List<Object[]> mandalCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"localElectionBody",(Long)list.get(0),publicationDateId);
					Long totalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId("localElectionBody"),new Long(id),publicationDateId);
					if(totalVoters == null)
						totalVoters = getVotersCountByPublicationIdInALocation("localElectionBody",new Long(id),publicationDateId);
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
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleWards(List<Object[]> wardList,Long publicationDateId,Long userId)
	{
		VoterCastInfoVO voterCastInfo = null;
		List<VoterCastInfoVO> wardsList = new ArrayList<VoterCastInfoVO>();
		if(wardList != null)
		{
			for(Object[] ward :wardList)
			{
				voterCastInfo = new VoterCastInfoVO();
				voterCastInfo.setMandalName(ward[1] != null?ward[1].toString():"");
				voterCastInfo.setLocationId((Long)ward[0]);
				List<Object[]> wardCastDetails = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,"ward",(Long)ward[0],publicationDateId);
				Long totalVoters = getVotersCountByPublicationIdInALocation("ward",(Long)ward[0],publicationDateId);
				voterCastInfo.setVoterCastInfoVO(calculatePercentageForUserCast(wardCastDetails,totalVoters));
				
				//voterCastInfo.setVoterCastInfoVO(calculatePercentageForCast(boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(new Long(panchayatId),publicationDateId)));
				wardsList.add(voterCastInfo);
			}
		}
		return wardsList;
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
	    	voterHouseInfoVO.setMobileNo(voter.getMobileNo()!=null ? voter.getMobileNo() : " ");
	    	voterHouseInfoVOList.add(voterHouseInfoVO);
	    	sno = sno+1;
	    }
		return voterHouseInfoVOList;
	} 
	
	/**
	 * This method will get all the voters information in a family
	 * 
	 * @author Samba Penugonda
	 * @param Long  boothId ,boothId of the house
	 * @param Long publicationDateId , the voters publication date Id
	 * @param Long houseNo ,houseNo  of the family
	 * @param Long userId , userId of logged in user
	 * @return the voter details of a family
	 * 
	 */

	public List<VoterHouseInfoVO> getFamilyInformation(Long boothId, Long publicationDateId,String houseNo,Long userId)
	{		
		log.debug("Entered into the getFamilyInformation method");
		
		List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();		
		
		try{
		
			VoterHouseInfoVO voterHouseInfoVO = null;
			List<Voter> votersInfoList = boothPublicationVoterDAO.findFamiliesInfo(boothId, publicationDateId, houseNo);
		    long sno = 1;
		    
		    List<Long> voterIds = new ArrayList<Long>();
		    for(Voter voter : votersInfoList)
		    	voterIds.add(voter.getVoterId());
		    
		    List<Object[]> votersCategoriesList = 
					 voterCategoryValueDAO.getVoterCategoryValuesForVoters(userId,voterIds);
			 
		    
		   Map<Long, VoterHouseInfoVO> voterCastePartyDetails =  getUserCasteAndSelectedPartyVoters(voterIds,userId);
		    	
			for(Voter voter : votersInfoList){
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	voterHouseInfoVO.setsNo(sno);
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	
		    	voterHouseInfoVO.setCast(voter.getCast());
		    	voterHouseInfoVO.setCastCategory(voter.getCastCatagery());
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId(boothId);
		    	voterHouseInfoVO.setMobileNo(voter.getMobileNo()!=null ? voter.getMobileNo() : " ");
		    	VoterHouseInfoVO voterCastPartyVO = voterCastePartyDetails.get(voter.getVoterId());
		    	
		    	setVotersCategories(votersCategoriesList,voter,voterHouseInfoVO);
		    	setCastePartyDetails(voterHouseInfoVO,voterCastPartyVO);
		    	
		    
		    	voterHouseInfoVOList.add(voterHouseInfoVO);
		    	//getUserCasteAndSelectedParty(voterHouseInfoVO , voter.getVoterId(),userId);
		    	
		    	
		    	sno = sno+1;
	    }
		
		}catch(Exception e){
			log.error("Exception raised in getFamilyInformation method");
			e.printStackTrace();
		}
		return voterHouseInfoVOList;
	}
	
	/**
	 * This method will set the voter selected categories values
	 * @author Samba Penugonda
	 * @param votersCategoriesList ,voters selected categories list
	 * @param voter , voter details
	 * @param voterHouseInfoVO , category values need to set to this vo
	 */
	public void setVotersCategories(List<Object[]> votersCategoriesList,
			Voter voter, VoterHouseInfoVO voterHouseInfoVO) throws Exception{
		
		try{
		
		 VoterHouseInfoVO category = null;
			
		 for(Object[] voterDetails:votersCategoriesList){
			 
			 Long voterId = (Long)voterDetails[0];
			 
			 if(voterId.longValue() == voter.getVoterId()){
				 
				 List<VoterHouseInfoVO> categoriesList1 = voterHouseInfoVO.getCategoriesList();
				 
				 if(categoriesList1 == null){
					 categoriesList1 = new ArrayList<VoterHouseInfoVO>();
					 voterHouseInfoVO.setCategoriesList(categoriesList1);
					 
				 }
				   category = new VoterHouseInfoVO();
	    		  categoriesList1.add(category);
	    		  category.setCategoryValuesId((Long)voterDetails[1]);
	    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
				 
			 }
	     }
		 
		}catch(Exception e){
			throw new Exception();			
		}
	}
	
	/**
	 * This method will set the voter's party and caste details
	 * @author Samba Penugonda
	 * @param voterHouseInfoVO , voters party and caste details need to set to this vo
	 * @param voterCastPartyVO , contains voter's party and caste details
	 */
	public void setCastePartyDetails(VoterHouseInfoVO voterHouseInfoVO,
			VoterHouseInfoVO voterCastPartyVO) throws Exception {
		try{
			if(voterCastPartyVO != null){
	    		
	    		if(voterCastPartyVO.getCast() !=null)
	    			voterHouseInfoVO.setCast(voterCastPartyVO.getCast());
	    		
	    		if(voterCastPartyVO.getParty() != null)
	    			voterHouseInfoVO.setParty(voterCastPartyVO.getParty());
	    		
	    	}
		}catch(Exception e){
			throw new Exception();
		}
		
	}
	
	/**
	 * This method will get the caste and party details of voters of logged in user
	 * @author Samba Penugonda
	 * @param voterIds , total voterIds to get the caste and party details
	 * @param userId , logged in user id
	 * @return caste and party details of voters as map having key as voter id
	 */
	 public Map<Long,VoterHouseInfoVO> getUserCasteAndSelectedPartyVoters(List<Long> voterIds,Long userId){

		  Map<Long,VoterHouseInfoVO> voterCasteDetailsMap = new HashMap<Long, VoterHouseInfoVO>();

		  List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDtlsVoterIds(voterIds,userId);
		  
		  
		  for(UserVoterDetails userVoterDetails:userVoterDetailsList){
			  VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
			  
			  if(userVoterDetails.getParty() != null)
				  voterHouseInfoVO.setParty(userVoterDetails.getParty().getShortName());
			  
			  if(userVoterDetails.getCasteState() != null)
				  voterHouseInfoVO.setCast(userVoterDetails.getCasteState().getCaste().getCasteName());
			  
			  
			  voterHouseInfoVO.setVoterId(userVoterDetails.getVoter().getVoterId());
			  
			  voterCasteDetailsMap.put(voterHouseInfoVO.getVoterId(), voterHouseInfoVO); 
		  }
		 
		  return voterCasteDetailsMap;
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
				voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
				voterHouseInfoVO.setMobileNo(voter.getMobileNo()!=null ? voter.getMobileNo() : "N/A");
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
			List<Long> boothsList = null;
			if(type.equalsIgnoreCase("ward")){
				 boothsList = boothDAO.getBoothIdsForWard(panchayatId,publicationDateId);
			}
	        getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);		
			getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);			
			getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);		
			getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);       
			getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,constituencyVotersList,type,boothsList);
     
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
	public List<VotersDetailsVO> getVotersDetailsByAgewiseNew(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type) {		

		List<VotersDetailsVO> constituencyVotersList = new ArrayList<VotersDetailsVO>();
		VotersDetailsVO vo = new VotersDetailsVO();
		try{ 
			if(type.equalsIgnoreCase("localElectionBody")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(tehsilId);
				tehsilId = (Long) list.get(0);
				List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(tehsilId.toString());
				  Object[] reqName = assemblyLocalElectionBodyName.get(0);
				  String name = reqName[0].toString()+" "+reqName[1].toString();
				  vo.setBoothName(name);
			}
			List<Long> boothsList = null;
			if(type.equalsIgnoreCase("ward")){
				 boothsList = boothDAO.getBoothIdsForWard(panchayatId,publicationDateId);
				 vo.setBoothName(constituencyDAO.get(panchayatId).getName());
			}
			
			
	        getDetailsOfVotersHasAgeBetween18And25(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);		
			getDetailsOfVotersHasAgeBetween26And35(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);			
			getDetailsOfVotersHasAgeBetween36And45(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);		
			getDetailsOfVotersHasAgeBetween46And60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);       
			getDetailsOfVotersHasAgeAbove60(constituencyId,tehsilId,panchayatId,boothId, publicationDateId,vo,type,boothsList);
			
			Long totalVoters = 0l;
			if(vo.getTotalVotersFor18To25() != null )
			 totalVoters = totalVoters+vo.getTotalVotersFor18To25();
			if(vo.getTotalVotersFor26To35() != null)
				 totalVoters = totalVoters+vo.getTotalVotersFor26To35();
			if(vo.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+vo.getTotalVotersFor36To45();
			if(vo.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+vo.getTotalVotersFor46To60();
			if(vo.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+vo.getTotalVotersForAbove60();
			
			vo.setTotalVoters(totalVoters);
			
			vo.setVotersPercentFor18To25(vo.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			vo.setVotersPercentFor26To35(vo.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			vo.setVotersPercentFor36To45(vo.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			vo.setVotersPercentFor46To60(vo.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)vo.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			vo.setVotersPercentForAbove60(vo.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)vo.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			
			constituencyVotersList.add(vo);
			
			
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
			List<VotersDetailsVO> constituencyVotersList,String type,List<Long> boothsList) {		
		
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
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,18L,25L);
					 
			
			
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
			Long panchayatId,Long boothId, Long publicationDateId, List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList) {		
		
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
						 tehsilId, publicationDateId,26L, 35L);
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,26L,35L);
			
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
			List<VotersDetailsVO> constituencyVotersList , String type,List<Long> boothsList){

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
				 else if(type.equalsIgnoreCase("ward"))
					 votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
							 boothsList, publicationDateId,36L,45L);
				
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
			List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList){
		
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
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,46L,60L);
			
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
			List<VotersDetailsVO> constituencyVotersList ,String type,List<Long> boothsList){
		

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
	       else if(type.equalsIgnoreCase("ward"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,46L,150L);
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
				
				getDetailsOfVotersHasAgeBetween18And25(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);
				getDetailsOfVotersHasAgeBetween26And35(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);			
				getDetailsOfVotersHasAgeBetween36And45(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);		
				getDetailsOfVotersHasAgeBetween46And60(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);       
				getDetailsOfVotersHasAgeAbove60(null,(Long)obj[0],null,null, publicationDateId,voterDetailsVO,"mandal",null);
				
				
			/*Long totalVoters = voterDetailsVO.getTotalVotersFor18To25()
					+ voterDetailsVO.getTotalVotersFor26To35()
					+ voterDetailsVO.getTotalVotersFor36To45()
					+ voterDetailsVO.getTotalVotersFor46To60()
					+ voterDetailsVO.getTotalVotersForAbove60();*/
						
				voterDetailsVO.setTehsilName(obj[1].toString());
				/*voterDetailsVO.setTotalVoters(totalVoters);*/
				Long totalVoters = 0l;
				if(voterDetailsVO.getTotalVotersFor18To25() != null )
				 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				if(voterDetailsVO.getTotalVotersFor26To35() != null)
					 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor26To35((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor36To45((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters);
				voterDetailsVO.setVotersPercentFor46To60((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters);
				voterDetailsVO.setVotersPercentForAbove60((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters);
			*/	
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
				mandalVotersList.add(voterDetailsVO);
			}
			
			return mandalVotersList;
	}
	
	public void getDetailsOfVotersHasAgeBetween18And25(
			Long constituencyId,Long tehsilId,Long panchayatId, Long boothId, Long publicationDateId,
			VotersDetailsVO votersDetailsVO,String type,List<Long> boothsList) {		
		
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
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf18To25 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,18L,25L);
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
			Long panchayatId,Long boothId, Long publicationDateId, VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList) {		
		
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
			 else if(type.equalsIgnoreCase("ward"))
				 votersListOf26To35 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,26L,35L);
			
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
			VotersDetailsVO votersDetailsVO , String type,List<Long> boothsList){

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
				else if(type.equalsIgnoreCase("ward"))
					votersListOf36To45 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
							 boothsList, publicationDateId,36L,45L);
					
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
			VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList){
		
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
			else if(type.equalsIgnoreCase("ward"))
			 votersListOf46To60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
					 boothsList, publicationDateId,46L,60L);
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
			VotersDetailsVO votersDetailsVO ,String type,List<Long> boothsList){

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
	       else if(type.equalsIgnoreCase("ward"))
	    	   votersListOfAbove60 = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForWardByPublicationDateId(
						 boothsList, publicationDateId,46L,150L);
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
			
			getDetailsOfVotersHasAgeBetween18And25(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);
			getDetailsOfVotersHasAgeBetween26And35(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);			
			getDetailsOfVotersHasAgeBetween36And45(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);		
			getDetailsOfVotersHasAgeBetween46And60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);       
			getDetailsOfVotersHasAgeAbove60(null,tehsilId,(Long)obj[0],null, publicationDateId,voterDetailsVO,"panchayat",null);
			
			voterDetailsVO.setPanchayatname(obj[1].toString());
			Long totalVoters = 0l;
			if(voterDetailsVO.getTotalVotersFor18To25() != null )
			 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
			if(voterDetailsVO.getTotalVotersFor26To35() != null)
				 totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
			if(voterDetailsVO.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
			if(voterDetailsVO.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
			if(voterDetailsVO.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			
*/
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			
			constituencyVotersList.add(voterDetailsVO);
		}
		
		return constituencyVotersList;
		
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId){
		
		List<Object[]> booths = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
		for(Object[] obj:booths){
			
			VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
			
			
			getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);
			getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);			
			getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);		
			getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);       
			getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,voterDetailsVO,"booth",null);
			
			voterDetailsVO.setBoothName(obj[1].toString());
			Long totalVoters = 0l;
			  if(voterDetailsVO.getTotalVotersFor18To25() != null)
			   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
			  if(voterDetailsVO.getTotalVotersFor26To35() != null)
			    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
			  if(voterDetailsVO.getTotalVotersFor36To45() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
			  if(voterDetailsVO.getTotalVotersFor46To60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
			  if(voterDetailsVO.getTotalVotersForAbove60() != null)
				totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
			
			voterDetailsVO.setTotalVoters(totalVoters);
			
			/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
			voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
			voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
			*/		
			
			
			voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
			voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
			voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			boothVotersList.add(voterDetailsVO);
			
		}
		return boothVotersList;
		
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long wardId,Long publicationDateId){
		List<Object[]> booths= boothDAO.getBoothsForWard(wardId,publicationDateId);
		List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
		
			
			for(Object[] obj:booths){
				
				VotersDetailsVO voterDetailsVO = new VotersDetailsVO();
				
				getDetailsOfVotersHasAgeBetween18And25(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null);
				getDetailsOfVotersHasAgeBetween26And35(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null);			
				getDetailsOfVotersHasAgeBetween36And45(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null);		
				getDetailsOfVotersHasAgeBetween46And60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null);       
				getDetailsOfVotersHasAgeAbove60(null,null,null,(Long)obj[0], publicationDateId,boothVotersList,"booth",null);
				
				voterDetailsVO.setBoothName(obj[1].toString());
				Long totalVoters = 0l;
				  if(voterDetailsVO.getTotalVotersFor18To25() != null)
				   totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor18To25();
				  if(voterDetailsVO.getTotalVotersFor26To35() != null)
				    totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor26To35();
				  if(voterDetailsVO.getTotalVotersFor36To45() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor36To45();
				  if(voterDetailsVO.getTotalVotersFor46To60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersFor46To60();
				  if(voterDetailsVO.getTotalVotersForAbove60() != null)
					totalVoters = totalVoters+voterDetailsVO.getTotalVotersForAbove60();
				
				voterDetailsVO.setTotalVoters(totalVoters);
				
				/*voterDetailsVO.setVotersPercentFor18To25(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor26To35(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor36To45(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters));
				voterDetailsVO.setVotersPercentFor46To60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters));
				voterDetailsVO.setVotersPercentForAbove60(roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters));
				*/	
				
				
				voterDetailsVO.setVotersPercentFor18To25(voterDetailsVO.getTotalVotersFor18To25() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentFor26To35(voterDetailsVO.getTotalVotersFor26To35() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor36To45(voterDetailsVO.getTotalVotersFor36To45() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
				voterDetailsVO.setVotersPercentFor46To60(voterDetailsVO.getTotalVotersFor46To60() != null ? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
				voterDetailsVO.setVotersPercentForAbove60(voterDetailsVO.getTotalVotersForAbove60() != null? roundTo2DigitsFloatValue((float)voterDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
			
				boothVotersList.add(voterDetailsVO);
				
			}
			
			return boothVotersList;
		
	}
	public List<VotersDetailsVO> getAgewiseVotersDetailsForWardsByLocalElectionBodyId(Long assemblyLocalBodyId,Long publicationDateId){
		List<VotersDetailsVO> wardWiseVotersDetails = new ArrayList<VotersDetailsVO>();
		 try{
			 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalBodyId);
			 List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
			for (Object[] ward : wardsList){
			  List<VotersDetailsVO> wareData = getVotersDetailsByAgewiseNew(null,null,(Long)ward[0],null,publicationDateId,"ward");
			  wardWiseVotersDetails.addAll(wareData);
			}
		 }catch(Exception e){
			 log.error("Exception rised in getAgewiseVotersDetailsForWardsByLocalElectionBodyId method : ",e);
		 }
		return wardWiseVotersDetails;
	}

			
	//Retrieving important families information
	/*public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId){	
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
	}*/
	
	//Retrieving important families information
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId){	
		try{
			if(type.equalsIgnoreCase("constituency")){
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImportantFamilyDetailsForConstituency(type, id, publicationDateId);
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo =  getImportantFamiliesForConstituency(type,id,publicationDateId);
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("mandal")){
				
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForMandal(type,id,publicationDateId,"main");
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForMandal(type,id,publicationDateId,"main");
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("booth")){
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForBooth(type,id,publicationDateId,"main");
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForBooth(type,id,publicationDateId,"main");
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("panchayat")){
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForPanchayat(id,publicationDateId,"","main");
				if(!importantFamiliesInfoVo.isDataPresent())
					importantFamiliesInfoVo = getImportantFamiliesForPanchayat(id,publicationDateId,"","main");
				return importantFamiliesInfoVo;
			}
			else if(type.equalsIgnoreCase("ward")){
				ImportantFamiliesInfoVo importantFamiliesInfoVo = getImpFamiliesForWard(id,publicationDateId,"main");
				if(!importantFamiliesInfoVo.isDataPresent())
				 importantFamiliesInfoVo = getImportantFamiliesForWard(id,publicationDateId,"main");
				return importantFamiliesInfoVo;
			}
		}catch(Exception e){
			log.error("Exception rised in getImportantFamiliesInfo method : ",e);
		}
		return null;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForConstituency(String type,Long id,Long publicationDateId){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		Constituency constituency = constituencyDAO.get(id);
		importantFamiliesInfoVo.setType("Constituency");
		importantFamiliesInfoVo.setName(constituency.getName());
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"constituency"));
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"","main",null);
		 
		 VotersInfoForMandalVO votersInfoForMandalVO = getVotersCountForConstituency(type, id, publicationDateId);
		 importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandalVO.getTotalMaleVoters());
		 importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandalVO.getTotalFemaleVoters());
		 importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandalVO.getUnKnowVoters());

		 if(importantFamiliesInfoVo.isDataPresent()){
		    List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
		    if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && mandalsList != null && mandalsList.size() == 1){
				  String localBodyId = mandalsList.get(0).getId().toString();
				  if(localBodyId.substring(0,1).trim().equalsIgnoreCase("1")){
					  List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(localBodyId.substring(1)));
					  List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long)list.get(0),publicationDateId);
						for (Object[] ward : wardsList){
							importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForWard((Long)ward[0],publicationDateId,"sub"));
						}
				  }
			  }else{
			    for(SelectOptionVO mandal : mandalsList){
					
			    	ImportantFamiliesInfoVo mandalList = getImportantFamiliesForMandal("mandal",mandal.getId(),publicationDateId,"sub");
			    	VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", mandal.getId(), publicationDateId);
			    	mandalList.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			    	mandalList.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			    	mandalList.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			    	importantFamiliesInfoVo.getSubList().add(mandalList);
			    }
			  }
		 }
		 return  importantFamiliesInfoVo;
	}
	
	public void getImpFamilesInfo(String type,Long id,Long publicationDateId,ImportantFamiliesInfoVo importantFamiliesInfoVo,String queryToexe,String exeType,List<Long> ids){
		
        String query = "";
        Long[] totalFamilies = getFamiliesCount(type,id,publicationDateId,null,queryToexe,exeType,ids);
		if(totalFamilies != null){
          importantFamiliesInfoVo.setTotalFamalies(totalFamilies[0]);
		}else{
			importantFamiliesInfoVo.setDataPresent(false);
			return;
		}
	     query = " having count(model.voter.voterId) <= 3 ";
	     Long[] count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids);
	     importantFamiliesInfoVo.setBelow3(count[0]);
	     importantFamiliesInfoVo.setBelow3Popul(count[1]);
	     
	     query = " having count(model.voter.voterId) > 3 and count(model.voter.voterId) <= 6 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids);
	     importantFamiliesInfoVo.setBetwn4to6(count[0]);
	     importantFamiliesInfoVo.setBetwn4to6Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 6 and count(model.voter.voterId) <= 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids);
	     importantFamiliesInfoVo.setBetwn7to10(count[0]);
	     importantFamiliesInfoVo.setBetwn7to10Popul(count[1]);
	    
	     query = " having count(model.voter.voterId) > 10 ";
	     count = getFamiliesCount(type,id,publicationDateId,query,queryToexe,exeType,ids);
	     importantFamiliesInfoVo.setAbove10(count[0]);
	     importantFamiliesInfoVo.setAbove10Popul(count[1]);
	     
	     if(importantFamiliesInfoVo.getTotalVoters() > 0)
	       calculatePercentage(importantFamiliesInfoVo);
	     
	}
	
	public Long[] getFamiliesCount(String type,Long id,Long publicationDateId,String query,String queryToexe,String exeType,List<Long> ids){
		
		Long[] count = {0l,0l};
		List<Object[]> totalFamiliesList = null;
		if(queryToexe.equalsIgnoreCase(""))
			totalFamiliesList = boothPublicationVoterDAO.findAllImpFamiles(id,publicationDateId,type,query);
		else if(queryToexe.equalsIgnoreCase("local"))
			totalFamiliesList = boothPublicationVoterDAO.getVotersImpFamilesForLocalElectionBody(id,publicationDateId,query);
		else if(queryToexe.equalsIgnoreCase("panchayat"))
			totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForPanchayatByPublicationId(id,publicationDateId,query);
		else if(queryToexe.equalsIgnoreCase("ward"))
			totalFamiliesList = boothPublicationVoterDAO.getImpFamilesForWard(ids,publicationDateId,query);
		
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
			
			VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", id, publicationDateId);
			importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			
			 getImpFamilesInfo(type,new Long(id.toString().substring(1).trim()),publicationDateId,importantFamiliesInfoVo,"",exeType,null);
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
			 getImpFamilesInfo(type,(Long) list.get(0),publicationDateId,importantFamiliesInfoVo,"local",exeType,null);
			 
			 VotersInfoForMandalVO votersInfoForMandal = getVotersCountForMandal("mandal", id, publicationDateId);
			 importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
			 importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
			 importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
			 if(exeType.equalsIgnoreCase("main") && importantFamiliesInfoVo.isDataPresent()){
					List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long)list.get(0),publicationDateId);
					for (Object[] ward : wardsList){
						importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForWard((Long)ward[0],publicationDateId,"sub"));
					}
			  }
			 return importantFamiliesInfoVo;
		}
		
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Booth");
		//importantFamiliesInfoVo.setName("booth-"+boothDAO.get(id).getPartNo());
		importantFamiliesInfoVo.setName(boothDAO.get(id).getPartNo());
		importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"booth"));
		
		VotersInfoForMandalVO votersInfoForBooth = getVotersCountForBooth(type, id, publicationDateId, exeType);
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
		
		 getImpFamilesInfo(type,id,publicationDateId,importantFamiliesInfoVo,"",exeType,null);
		 return importantFamiliesInfoVo;
	}

	public ImportantFamiliesInfoVo getImportantFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Panchayat");
		importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		importantFamiliesInfoVo.setTotalVoters((Long)boothPublicationVoterDAO.getVotersCountForPanchayat(id,publicationDateId).get(0));
		
		VotersInfoForMandalVO VotersInfoForPanchayat = getVotersCountForPanchayat(id, publicationDateId, "Panchayat");
		importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
		
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
	
	public ImportantFamiliesInfoVo getImportantFamiliesForWard(Long id,Long publicationDateId,String reqType){
		ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
		importantFamiliesInfoVo.setType("Ward");
		importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
		//importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
		List<Long> boothsList = boothDAO.getBoothIdsForWard(id,publicationDateId);
		/*List<Long> votersCountList = boothPublicationVoterDAO.getVotersCountForMultipleBooths(boothsList,publicationDateId);
		 if(votersCountList != null && votersCountList.size() >0){ 
		   importantFamiliesInfoVo.setTotalVoters((Long)votersCountList.get(0));
		 }*/
		VotersInfoForMandalVO votersInfoForWard = getVotersCountForWard(id, publicationDateId, "sub");
		importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForWard.getTotalMaleVoters());
		importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForWard.getTotalFemaleVoters());
		importantFamiliesInfoVo.setUnKnowVoters(votersInfoForWard.getUnKnowVoters());
		importantFamiliesInfoVo.setTotalVoters(votersInfoForWard.getTotVoters().longValue());
		 getImpFamilesInfo("",id,publicationDateId,importantFamiliesInfoVo,"ward",reqType,boothsList);
		 if(reqType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
		     for(Long booth : boothsList){
		    	 importantFamiliesInfoVo.getSubList().add(getImportantFamiliesForBooth("booth",booth,publicationDateId,"sub"));
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
	
	@SuppressWarnings("unchecked")
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long userId,Long id, Long publicationDateId,String checkedEle)
	{
		List voters = null;
		List<Long> voterIdsList = new ArrayList<Long>(0);
		List<SelectOptionVO> casteList = new ArrayList<SelectOptionVO>();
		SelectOptionVO caste = null;
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
		
		if(voters != null && voters.size() > 0)
		for(Object[] voter : (List<Object[]>)voters)
		voterIdsList.add((Long)voter[5]);
		
		if(voterIdsList.size() > 0)
		{
			List<Long> subList = null;
			int startIndex = 0;
			int nextIndex = 1000;
			for(;;)
			{
				try{
				if(startIndex >= voterIdsList.size()-1)
					break;
				if(nextIndex >= voterIdsList.size())
					nextIndex = voterIdsList.size() - 1;
				
				subList = voterIdsList.subList(startIndex,nextIndex);
				
			    List<Object[]> votercastList = userVoterDetailsDAO.getCasteByVoterId(userId,subList);
			    if(votercastList!= null)
		        for(Object[] params : votercastList)
				{
		        	if(params[1].toString() != null && params[1].toString()!= "")
					caste = new SelectOptionVO((Long)params[0],params[1].toString());	
					casteList.add(caste);
				}
			    startIndex = nextIndex;
			    nextIndex = nextIndex + 1000;
				}catch (Exception e) {
					log.error(e);
				}
			}
		}
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
			voterVO.setCast(getCasteNameByVoterID(casteList,(Long)voter[5]));
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
			//voterHouseInfoVO.setBoothName("Booth - "+voterVOs.get(0).getBoothName());
			voterHouseInfoVO.setBoothName(voterVOs.get(0).getBoothName());
			voterHouseInfoVO.setHouseNo(entry.getKey());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVO.setCast(voterVOs.get(voterVOs.size()-1).getCast() != null ? 
					voterVOs.get(voterVOs.size()-1).getCast() : "N/A");
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
	
	public String getCasteNameByVoterID(List<SelectOptionVO> casteList,Long voterId)
	{
		String casteName = null;
		try{
			if(casteList != null && casteList.size() > 0)
			for(SelectOptionVO params : casteList)
				if(params.getId().equals(voterId))
					casteName = params.getName().toString();
			return casteName;
		}catch (Exception e) {
			log.error("Exception Occured in getCasteNameByVoterID() Method, Exception is - "+e);
			return casteName;
		}
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
	
	   getVoterBasicInfo(voterHouseInfoVO,voterId);
	   
	   getPartiesAndCastsInVotersState(voterHouseInfoVO,voterId,userId,defaultSelectOptionVO);
	   
	   voterSelectedCastAndPartyDetails(voterHouseInfoVO,voterId,userId);
	   
    List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
	//List<UserVoterDetails> userVoterDetails=userVoterDetailsDAO.getUserVoterDetails(voterId, userId);
	List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
	getVoterSelectedCategoryValues(userCategoryValuesList,defaultSelectOptionVO,userId,voterId,categoriesList);
	voterHouseInfoVO.setCategoriesList(categoriesList);
  }catch(Exception e){
	  log.error("Exception rised in getVoterPersonalDetailsByVoterId",e);
  }
	return voterHouseInfoVO;
	}

  public VoterHouseInfoVO getVoterPersonalDetailsList(List<VoterHouseInfoVO> voterIds,Long userId){
	  VoterHouseInfoVO votersHouseInfoVO = new VoterHouseInfoVO();
	 
	  
	  Map<Long,Map<String, List<VoterHouseInfoVO>>> boothMap = new HashMap<Long,Map<String, List<VoterHouseInfoVO>>>();
		Map<String, List<VoterHouseInfoVO>> voterByHouseNoMap = null;
		
		List<VoterHouseInfoVO> voterVOs = null;
		
	  try{
		  if(voterIds != null && voterIds.size() >0){
			  
			
			
	 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
	 	    defaultSelectOptionVO.setId(0l);
	 	    defaultSelectOptionVO.setName("Select");
	 	
	 	    getPartiesAndCastsInVotersState(votersHouseInfoVO,voterIds.get(0).getVoterId(),userId,defaultSelectOptionVO);
	 	   
	 	    List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
	 	  
	 	   VoterHouseInfoVO voterHouseInfoVO = null;
	 	   
	 	    for(VoterHouseInfoVO voter : voterIds){
	 	    	
	 	     voterHouseInfoVO = new VoterHouseInfoVO();
	 	     
	 	     getVoterBasicInfo(voterHouseInfoVO,voter.getVoterId());
	 	   
	 	     
	 	    voterByHouseNoMap = boothMap.get(voter.getBoothId());
			if( voterByHouseNoMap == null){
				voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
				boothMap.put(voter.getBoothId(), voterByHouseNoMap);
			}
			voterVOs = voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
			if(voterVOs ==null){
				voterVOs = new ArrayList<VoterHouseInfoVO>();
				voterByHouseNoMap.put(voterHouseInfoVO.getHouseNo(), voterVOs);
			}
			voterVOs.add(voterHouseInfoVO);
	 	     
	 	     voterSelectedCastAndPartyDetails(voterHouseInfoVO,voter.getVoterId(),userId);
	 	   	       
	 	     List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
	 	   
	 	     getVoterSelectedCategoryValues(userCategoryValuesList,defaultSelectOptionVO,userId,voter.getVoterId(),categoriesList);
	 	   
	 	     voterHouseInfoVO.setCategoriesList(categoriesList);
	 	     
	 	     
	 	   }
	 	   List<VoterHouseInfoVO> boothsList = new ArrayList<VoterHouseInfoVO>();
	 	  List<VoterHouseInfoVO> familiesList = null;
	 	    for(Long key:boothMap.keySet()){
	 	    	VoterHouseInfoVO booth = new VoterHouseInfoVO();
	 	    	boothsList.add(booth);
	 	    	booth.setBoothId(key);
	 	    	VoterHouseInfoVO boothInfo = getBoothDetailsForVoter(key);
	 	    	booth.setBoothName(boothInfo.getBoothName());
	 	    	booth.setVilliageCovered(boothInfo.getVilliageCovered());
	 	    	booth.setPanchayatName(boothInfo.getPanchayatName());
	 	    	booth.setMobileNo(boothInfo.getMobileNo()!=null ? boothInfo.getMobileNo():" ");
	 	    	Map<String, List<VoterHouseInfoVO>> families = boothMap.get(key);
	 	    	familiesList = new ArrayList<VoterHouseInfoVO>();
	 	    	for(String familyKey:families.keySet()){
	 	    		VoterHouseInfoVO family = new VoterHouseInfoVO();
	 	    		familiesList.add(family);
	 	    		family.setHouseNo(familyKey);
	 	    		family.setVotersList(families.get(familyKey));
	 	    	}
	 	    	booth.setFamiliesList(familiesList);
	 	    }
	 	    
	 	   votersHouseInfoVO.setBoothsList(boothsList);
		  }
	   }catch(Exception e){
	 	  log.error("Exception rised in getVoterPersonalDetailsList ",e);
	   }
	 	return votersHouseInfoVO;
	  
  }
  
  public void getVoterBasicInfo(VoterHouseInfoVO voterHouseInfoVO,Long voterId){
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
	
		
	 voterHouseInfoVO.setMobileNo(voterInfo.getMobileNo()!=null ? voterInfo.getMobileNo() : " ");
	 
	 //voterHouseInfoVO.setCast(voterInfo.getCast());
	 
	 //List<SelectOptionVO> casteList = socialService.getAllCasteDetails();
	 
	
	 //voterHouseInfoVO.setCastCategory(voterInfo.getCastCatagery());
	 //voterHouseInfoVO.setUserId(userId);
	}
	
  }
  
  public void getPartiesAndCastsInVotersState(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId,SelectOptionVO defaultSelectOptionVO){
	  
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
  }

  public void voterSelectedCastAndPartyDetails(VoterHouseInfoVO voterHouseInfoVO,Long voterId,Long userId){
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
  }
  
  public void getVoterSelectedCategoryValues(List<Object[]> userCategoryValuesList,SelectOptionVO defaultSelectOptionVO,Long userId,Long voterId,List<VoterHouseInfoVO> categoriesList){
	  VoterHouseInfoVO category = null;
		for(Object[] userCategoryValue : userCategoryValuesList)
		{ 
			 category = new VoterHouseInfoVO();
			 getAllCategoryOptions(userCategoryValue,category,defaultSelectOptionVO,userId);
			 List<Long> idsList = voterCategoryValueDAO.getVoterCategoryValue(userId,voterId,(Long)userCategoryValue[0]);
			 if(idsList != null && idsList.size() > 0 && idsList.get(0) != null){
				 category.setCategoryValuesId(idsList.get(0));
			 }
			 
			 categoriesList.add(category);	 
		}	  
  }
  
  public void getAllCategoryOptions(Object[] userCategoryValue,VoterHouseInfoVO category,SelectOptionVO defaultSelectOptionVO,Long userId){
	  
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
	  
  }
public void updateVoterDetails(VoterHouseInfoVO voterHouseInfoVO,String partyCast){
	
   if(voterHouseInfoVO != null){
	try{
		if(voterHouseInfoVO.getVoterId() == null || voterHouseInfoVO.getUserId() == null )
			return ;
					
		Voter voter =  voterDAO.get(voterHouseInfoVO.getVoterId());
		User user =  userDAO.get(voterHouseInfoVO.getUserId());
		String mobileNo = voterHouseInfoVO.getMobileNo();
		voterDAO.updateVoterMobileNo(voterHouseInfoVO.getMobileNo(),voterHouseInfoVO.getVoterId());
		
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
		if(partyCast != null){
			List<UserVoterDetails> userVoterDetailsList = userVoterDetailsDAO.getUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId());
			Long partyId = voterHouseInfoVO.getPartyId();
			Long casteStateId = voterHouseInfoVO.getCasteStateId();
			if(partyId != null &&  partyId.longValue() == 0l)
				partyId = null;
			if(casteStateId != null &&  casteStateId.longValue() == 0l)
				casteStateId = null;
			if(userVoterDetailsList != null && userVoterDetailsList.size() > 0){
			   if(partyCast.equalsIgnoreCase("all"))
				   userVoterDetailsDAO.updateUserVoterDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);
			   else if(partyCast.equalsIgnoreCase("party")){
				   userVoterDetailsDAO.updateUserVoterPartyDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);  
			   }else if(partyCast.equalsIgnoreCase("cast")){
				   userVoterDetailsDAO.updateUserVoterCastDetails(voterHouseInfoVO.getVoterId(),voterHouseInfoVO.getUserId(),partyId,casteStateId);
			   }
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
	}
	catch (Exception e) {
		log.error("Exception rised in updateVoterDetails : ",e);
	}
   }
	
}

 public boolean updateMultipleVoterDetails(List<VoterHouseInfoVO> voterHouseInfoVOs,String partyCast){
   try{
	for(VoterHouseInfoVO voterHouseInfoVO : voterHouseInfoVOs){
		updateVoterDetails(voterHouseInfoVO,partyCast);
	}
   }catch(Exception e){
	   log.error("Exception rised in updateMultipleVoterDetails : ",e);
	   return false;
   }
	return true;
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
				Double diff = (double)(d2.getTime()-d1.getTime());
				System.out.println("1000 Records inserted in "+(diff)/(1000*60*60)+" Seconds");
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

public ResultStatus updateVoterData(Long constituencyId,Integer startIndex, Integer maxResults)
{
	ResultStatus resultStatus = new ResultStatus();
	try{
		Date d3 = new Date();
		int max = 1000;
		for(;;)
		{
			Date d1 = new Date(); 
			List<VoterTemp> voterTempData = voterTempDAO.getVotersInAConstituency(constituencyId,startIndex,max);
			
			if(voterTempData != null && voterTempData.size() > 0)
			{
				List<String> voterIdsList = new ArrayList<String>(0);
				
				for(VoterTemp voterTemp : voterTempData)
					voterIdsList.add(voterTemp.getVoterId());
				
				List<Object[]> voterIds = voterDAO.getVoterIdsByVoterIdCardNos(voterIdsList);
				
				int index = 1;
				for(Object[] voterId : voterIds)
				{
				try{
					VoterTemp voterTemp = getVoterTemp(voterTempData,voterId[1].toString());
					if(voterTemp != null)
					{   
						index++;
						final String name = voterTemp.getName();
						final String relativeName = voterTemp.getGuardianName();
						final Long votetId = (Long)voterId[0];
						final int j = index; 
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							public void doInTransactionWithoutResult(TransactionStatus status) {
						int result = voterDAO.updateVoterNameAndRelativeName(name,relativeName,votetId);
						System.out.println(j+") "+result +" Records Updated");
						log.warn(j+") "+result +" Records Updated");
						}});
					}
				    }catch (Exception e) {}
				}
				
				voterDAO.flushAndclearSession();
				Date d2 = new Date();
				Double diff = (double)(d2.getTime()-d1.getTime());
				System.out.println("1000 Records updated in "+(diff)/(1000)+" Seconds");
				log.warn("1000 Records updated in "+(diff)/(1000)+" Seconds");
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
		log.warn("Time Taken - "+d5+" Mins");
		return resultStatus;
	}catch (Exception e) {
		log.error("Exception Occured in updateVoterData() Method, Exception is - "+e);
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
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in saveCasteName() Method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
	}
	//save Locality details in Locality table
	public ResultStatus saveLocality(Long userId,Long hamletId,String name,Long localbody)
	{
	ResultStatus resultStatus = new ResultStatus();
	Locality locality = null;
	if(hamletId == 0)
		hamletId = null;
	if(localbody.toString().substring(0,1).trim().equalsIgnoreCase("2"))
		localbody = null;
	try{
		locality = new Locality();
		locality.setIsGlobal("false");
		if(hamletId != null)
		locality.setHamlet(hamletDAO.get(hamletId));
		if(localbody != null)
		{
		localbody = new Long(localbody.toString().substring(1));
		locality.setLocalElectionBody(localElectionBodyDAO.get(localbody));
		}
		locality.setUser(userDAO.get(userId));
		locality.setName(name);
		localityDAO.save(locality);
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
		}		
		catch(Exception e)
		{
		e.printStackTrace();
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
			 selectOptionVO.setName(booth[1]!=null?booth[1].toString():"");
			 selectOptionVO.setValue(booth[1].toString());
			 selectOptionVO.setLocation(booth[2] != null?booth[2].toString():"");
			 selectOptionVO.setVillageCovered(booth[3] != null?booth[3].toString():"");
			 list.add(selectOptionVO);
		 }
		 Collections.sort(list,arraySort);
		}catch(Exception e){
			log.error("Exception rised in getBoothsInMunicipality ",e);
		}
		 return list;
		 
	 }
	 
	 public VoterTemp getVoterTemp(List<VoterTemp> list,String voterID)
	 {
		 for(VoterTemp voterTemp : list)
			 if(voterID.equalsIgnoreCase(voterTemp.getVoterId()))
				 return voterTemp;
		 return null;
	 }
	 
	 public List<VoterHouseInfoVO> getMultipleFamiliesInfo(List<VoterHouseInfoVO> familiesList){
		 List<VoterHouseInfoVO> votersInfo = new ArrayList<VoterHouseInfoVO>();
		 for(VoterHouseInfoVO family : familiesList){
			 votersInfo.addAll(getFamilyInfo(family.getBoothId(),family.getPublicationId(),family.getHouseNo()));
		 }
		 for(int i =0; i<votersInfo.size();i++){
			 VoterHouseInfoVO voter = votersInfo.get(i);
			 voter.setsNo(new Long(i+1));
		 }
		 return votersInfo;
	 }
	 
	 public ResultStatus insertVotersInfoOfAConstituency()
	 {
		 try{
			 
		 }catch(Exception e)
		 {
			 
		 }
		 return null;
	 }
	 
	 public VoterHouseInfoVO getVotersInfoBySearchCriteria(VoterHouseInfoVO searchInfo,String type,Long id,List<Long> categories){
		 VoterHouseInfoVO returnValue = new VoterHouseInfoVO();
		 List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
		 returnValue.setVotersList(votersList);
		 try{
			  StringBuilder query = new StringBuilder();
			  
			    if(type.equalsIgnoreCase("constituency")){
			    	query.append(" and model.booth.constituency.constituencyId = :id ");
				}
				else if(type.equalsIgnoreCase("mandal")){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
						query.append(" and model.booth.localBody.localElectionBodyId = :id ");
						id = (Long)list.get(0);
					 }else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						 query.append(" and model.booth.tehsil.tehsilId = :id and model.booth.localBody is null ");
						 id = new Long(id.toString().substring(1).trim());
					 }
				}
				else if(type.equalsIgnoreCase("booth")){
					query.append(" and model.booth.boothId = :id ");
				}
				else if(type.equalsIgnoreCase("panchayat")){
					query.append(" and model.booth.panchayat.panchayatId = :id ");
				}
			    
			    if(searchInfo.getSetValue() != null && searchInfo.getSetValue().equalsIgnoreCase("or")){
			    	if(searchInfo.getVoterIdCardNo() != null)
			    		query.append(" or model.voter.voterIDCardNo = '"+searchInfo.getVoterIdCardNo()+"'");
			    	if(searchInfo.getName() != null){
			    	  if(searchInfo.getSetValue() != null && searchInfo.getSetValue().equalsIgnoreCase("start"))
			    		query.append(" or model.voter.name like '"+searchInfo.getName()+"%'");
			    	  else
			    		query.append(" or model.voter.name like '%"+searchInfo.getName()+"%'");
			    	}
			    	if(searchInfo.getGaurdian() != null){
			    		query.append(" or model.voter.relativeName like '%"+searchInfo.getGaurdian()+"%'");
			    	}
			    	if(searchInfo.getGender() != null){
			    		 query.append(" or model.voter.gender = '"+searchInfo.getGender()+"'");
			    	}
			    	if(searchInfo.getAge() != null){
			    		 query.append(" or model.voter.age >= "+searchInfo.getAge());
			    	}
			    	if(searchInfo.getToAge() != null){
			    		 query.append(" or model.voter.age <= "+searchInfo.getToAge());
			    	}
			    }else{
			    	if(searchInfo.getVoterIdCardNo() != null)
			    		query.append(" and model.voter.voterIDCardNo = '"+searchInfo.getVoterIdCardNo()+"'");
			    	if(searchInfo.getName() != null){
			    	  if(searchInfo.getSetValue() != null && searchInfo.getSetValue().equalsIgnoreCase("start"))
			    		query.append(" and model.voter.name like '"+searchInfo.getName()+"%'");
			    	  else
			    		query.append(" and model.voter.name like '%"+searchInfo.getName()+"%'");
			    	}
			    	if(searchInfo.getGaurdian() != null){
			    		query.append(" and model.voter.relativeName like '%"+searchInfo.getGaurdian()+"%'");
			    	}
			    	if(searchInfo.getGender() != null){
			    		 query.append(" and model.voter.gender = '"+searchInfo.getGender()+"'");
			    	}
			    	if(searchInfo.getAge() != null){
			    		 query.append(" and model.voter.age >= "+searchInfo.getAge());
			    	}
			    	if(searchInfo.getToAge() != null){
			    		 query.append(" and model.voter.age <= "+searchInfo.getToAge());
			    	}
			    	
			    }
			    if(searchInfo.getSortByColum().equalsIgnoreCase("name"))
			          query.append(" order by model.voter.name "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("voterIdCardNo"))
				      query.append(" order by model.voter.voterIDCardNo "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("houseNo"))
				      query.append(" order by model.voter.houseNo "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("boothName"))
				      query.append(" order by model.booth.partNo "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("gender"))
				      query.append(" order by model.voter.gender "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("age"))
				      query.append(" order by model.voter.age "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("gaurdian"))
				      query.append(" order by model.voter.relativeName "+searchInfo.getSortBy());
			    else if(searchInfo.getSortByColum().equalsIgnoreCase("relationship"))
				      query.append(" order by model.voter.relationshipType "+searchInfo.getSortBy());
			   /* else if(searchInfo.getSortByColum().trim().length() >1 && searchInfo.getSortIds() != null &&  searchInfo.getSortIds().length >0){			    	
			    	String arrayId = searchInfo.getSortByColum().substring(searchInfo.getSortByColum().length() - 1); 
			        String sortEle = searchInfo.getSortIds()[new Integer(arrayId)];
			        if(sortEle != null){
			        	searchInfo.setSortReq(true);
			        	searchInfo.setSortEle(sortEle);
			        }
			    }*/
			    List<Long> countList = boothPublicationVoterDAO.getVotersCountBySearchCriteria(searchInfo.getPublicationId(),id,query.toString());
			     if(countList != null && countList.get(0) != null && ((Long)countList.get(0)).longValue() > 0l){
			    	 returnValue.setTotalHousesCount((Long)countList.get(0));
			    	  List<Object[]> votersData = boothPublicationVoterDAO.getVotersDetailsBySearchCriteria(searchInfo.getPublicationId(),id,searchInfo.getStartIndex(),searchInfo.getMaxIndex(),query.toString());
			    	  populateVotersDataToVoForSearch(votersData,votersList,categories,searchInfo);
			     }
		 }catch(Exception e){
			 log.error("Exception rised in getVotersInfoBySearchCriteria ",e);
		 }
		 
		 return returnValue;
	 }
	 
	 public void populateVotersDataToVoForSearch(List<Object[]> votersData,List<VoterHouseInfoVO> votersList,List<Long> categories,VoterHouseInfoVO searchInfo){
		 VoterHouseInfoVO voterHouseInfoVO = null;
		 Map<Long,VoterHouseInfoVO> votersMap = new HashMap<Long,VoterHouseInfoVO>();
		 List<Long> voterIds = new ArrayList<Long>();
		 for(Object[] voters : votersData){
			    Voter voter = (Voter)voters[0];
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterIds.add(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId((Long)voters[1]);
		    	voterHouseInfoVO.setBoothName(voters[2]!=null?voters[2].toString():"");
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	votersMap.put(voter.getVoterId(),voterHouseInfoVO);
		    	votersList.add(voterHouseInfoVO);
		    	
		    }
			 if((searchInfo.isPartyPresent() || searchInfo.isCastPresent() || (categories != null && categories.size() > 0)) && voterIds.size() >0){
				 if(searchInfo.isPartyPresent() || searchInfo.isCastPresent()){
					 List<UserVoterDetails> votersPartyCastList = userVoterDetailsDAO.getAllUserVoterDetails(voterIds,searchInfo.getUserId());
				     for(UserVoterDetails voterDetails:votersPartyCastList){
				    	 VoterHouseInfoVO voterObj = votersMap.get(voterDetails.getVoter().getVoterId());
				    	 if(voterObj != null){
				    		 if(voterDetails.getCasteState()!= null && voterDetails.getCasteState().getCaste()!= null){
				    			 voterObj.setCast(voterDetails.getCasteState().getCaste().getCasteName());
				    		 }
				    		 if(voterDetails.getParty() != null){
				    			 voterObj.setParty(voterDetails.getParty().getShortName());
				    		 }
				    	 }
				     }
				 }
				 if(categories != null && categories.size() > 0){
					 VoterHouseInfoVO category = null;
					 List<Object[]> votersPartyCastList = voterCategoryValueDAO.getVoterCategoryValuesForVoters(searchInfo.getUserId(),voterIds);
				     for(Object[] voterDetails:votersPartyCastList){
				    	 VoterHouseInfoVO voterObj = votersMap.get((Long)voterDetails[0]);
				    	 if(voterObj != null){
				    		 List<VoterHouseInfoVO> categoriesList = voterObj.getCategoriesList();
				    		 if(categoriesList == null){
				    			 categoriesList = new ArrayList<VoterHouseInfoVO>();
				    			 voterObj.setCategoriesList(categoriesList);
				    		 }
				    		  category = new VoterHouseInfoVO();
				    		  categoriesList.add(category);
				    		  category.setCategoryValuesId((Long)voterDetails[1]);
				    		  category.setName(voterDetails[2]!=null?voterDetails[2].toString():"");
				    	 }
				     }
				 }
			 }
			/* if(searchInfo.isSortReq()){
				 if(searchInfo.getSortEle().equalsIgnoreCase("party")){
					 if(searchInfo.getSortBy().equalsIgnoreCase("asc"))
						Collections.sort(votersList,sortByPartyAsc);
					 else
						Collections.sort(votersList,sortByPartyDesc);
				 }
				 else if(searchInfo.getSortEle().equalsIgnoreCase("cast")){
					 if(searchInfo.getSortBy().equalsIgnoreCase("asc"))
						Collections.sort(votersList,sortByCastAsc);
					 else
						Collections.sort(votersList,sortByCastDesc);
				 }else{
					 
				 }
			 }*/
	 }
	  
	 public void populateVotersDataToVo(List<Object[]> votersData,List<VoterHouseInfoVO> votersList){
		 VoterHouseInfoVO voterHouseInfoVO = null;
		 for(Object[] voters : votersData){
			    Voter voter = (Voter)voters[0];
		    	voterHouseInfoVO = new VoterHouseInfoVO();
		    	//voterHouseInfoVO.setName(voter.getFirstName()+" "+voter.getLastName());
		    	voterHouseInfoVO.setName(voter.getName());
		    	voterHouseInfoVO.setGender(voter.getGender());
		    	voterHouseInfoVO.setAge(voter.getAge());
		    	voterHouseInfoVO.setHouseNo(voter.getHouseNo());
		    	//voterHouseInfoVO.setGaurdian(voter.getRelativeFirstName()+" "+voter.getRelativeLastName());
		    	voterHouseInfoVO.setGaurdian(voter.getRelativeName());
		    	voterHouseInfoVO.setRelationship(voter.getRelationshipType());
		    	
		    	voterHouseInfoVO.setVoterId(voter.getVoterId());
		    	voterHouseInfoVO.setBoothId((Long)voters[1]);
		    	voterHouseInfoVO.setBoothName(voters[2]!=null?voters[2].toString():"");
		    	voterHouseInfoVO.setVoterIdCardNo(voter.getVoterIDCardNo());
		    	votersList.add(voterHouseInfoVO);
		    	
		    }
	 }
	 
	 public List<SelectOptionVO> getElectionIdAndTypeByPublicationId(Long publicationDateId){
		 
		 List<SelectOptionVO> selectOptionVOs = null;  
		 try{
			   
		    PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
			List<Long> stateIds = boothDAO.getStateIdByPublicationId(publicationDateId);
			if(stateIds != null && stateIds.size() >0)
			{
				List<Object[]> list = electionDAO.getPreviousElectionsByStateIdYearAndDate(stateIds.get(0),publicationDate.getYear().toString(),publicationDate.getDate());
				if(list != null && list.size() > 0)
				{
					selectOptionVOs = new ArrayList<SelectOptionVO>(0);
					for(Object[] params : list)
						selectOptionVOs.add(new SelectOptionVO((Long)params[0],params[1].toString()));
					
				}
				return selectOptionVOs;
			}
			  
			else
				return selectOptionVOs;
			}catch(Exception e){
			   log.error("Exception rised in getElectionIdAndTypeByPublicationId ",e);
		   }
		   return selectOptionVOs;
	 }
	 
	 public List<Election> getPrevElections(Long publicationDateId){
		   try{
		    PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
			List<Long> stateIds = boothDAO.getStateIdByPublicationId(publicationDateId);
			if(stateIds != null && stateIds.size() >0){
			  return electionDAO.getPreviousElections(stateIds.get(0),publicationDate.getYear().toString(),publicationDate.getDate());  
			}else
				return new ArrayList<Election>();
			}catch(Exception e){
			   log.error("Exception rised in getPrevElections ",e);
		   }
		   return new ArrayList<Election>();
	 }
	 
	 public void getPrevElectVotersCount(List<Election> elections,Long id,VotersInfoForMandalVO votersInfoForMandalVO,String type,Long constituencyId){
		 List<VotersInfoForMandalVO> previousElectInfoList = new ArrayList<VotersInfoForMandalVO>();
		 votersInfoForMandalVO.setPreviousElectInfoList(previousElectInfoList);
		 
		  for(Election election : elections){
		   try{
			  List<Object[]> prevElecVotersInfoList = null;
			  if(type.equalsIgnoreCase("constituency")){
			    prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAConstituency(election.getElectionId(),id);
			  }else if(type.equalsIgnoreCase("mandal")){
				  if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					  List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
					  prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),(Long)list.get(0),"localElec",null,constituencyId);
				  }else{
				     prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),new Long(id.toString().substring(1).trim()),"mandal",null, null);
			  
				  }
			  }else if(type.equalsIgnoreCase("panchayat")){
			    prevElecVotersInfoList = hamletBoothElectionDAO.getVotersCountInAPanchayat(election.getElectionId(),id);
			  }else if(type.equalsIgnoreCase("booth")){
				    Booth booth = boothDAO.get(id);
				    prevElecVotersInfoList = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(election.getElectionId(),booth.getTehsil().getTehsilId(),"booth",booth.getPartNo(),null);
				  }
			   if(prevElecVotersInfoList != null && prevElecVotersInfoList.size() > 0){
				 Object[] prevElecVotersInfo = prevElecVotersInfoList.get(0);
				 if(prevElecVotersInfo[2] != null && ((Long)prevElecVotersInfo[2]).longValue() > 0){
					 VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
					 votersInfo.setTotalMaleVoters(((Long)prevElecVotersInfo[0]).toString());
					 votersInfo.setTotalFemaleVoters(((Long)prevElecVotersInfo[1]).toString());
					 votersInfo.setTotalVoters(((Long)prevElecVotersInfo[2]).toString());
					 votersInfo.setElectionYear(election.getElectionYear());
					 votersInfo.setType(election.getElecSubtype()+"-ELECTION");
					 Long totalVoters = 0l;
					 if(votersInfoForMandalVO.getTotalMaleVoters() != null){
						 Long voters =  new Long(votersInfoForMandalVO.getTotalMaleVoters());
						 totalVoters = totalVoters+voters;
						 votersInfo.setMaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[0]).longValue()));
						
						 votersInfoForMandalVO.setMaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[0]).longValue()));
					 }
					 if(votersInfoForMandalVO.getTotalFemaleVoters() != null){
						 Long voters =  new Long(votersInfoForMandalVO.getTotalFemaleVoters());
						 totalVoters = totalVoters+voters;
						 votersInfo.setFemaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[1]).longValue()));
						
						 votersInfoForMandalVO.setFemaleVotersDiff((voters.longValue() -((Long)prevElecVotersInfo[1]).longValue()));
					 }
					 if(totalVoters != null && totalVoters.longValue() > 0){
						 votersInfo.setTotalVotersDiff((totalVoters.longValue() -((Long)prevElecVotersInfo[2]).longValue()));
						 
						 votersInfoForMandalVO.setTotalVotersDiff((totalVoters.longValue() -((Long)prevElecVotersInfo[2]).longValue()));
					 }
					 previousElectInfoList.add(votersInfo);
					
				 }
			 }
		    }catch(Exception e){
				 log.error("Exception rised in getPrevElectVotersCountForConstituencyForMandal ",e);
			}
		  }
		
	 }
	 
	 public VoterCastInfoVO getCastWisePartyCount(Long userId,String locationType,Long locationId,Long publicationDateId)
		{
		    VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
			List<CastVO> resultList = null;
			try{
				if(locationType.equalsIgnoreCase("mandal"))
				{
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
				List<Object[]> castList = boothPublicationVoterDAO.getCastWiseCount(userId,locationType,locationId,publicationDateId);
				List<Object[]> partiesList = boothPublicationVoterDAO.getPartyWiseCount(userId,locationType,locationId,publicationDateId);
				List<Object[]> parties = boothPublicationVoterDAO.getParties(userId,locationType,locationId,publicationDateId);
				Map<String,CastVO> castsMap = new HashMap<String,CastVO>();
				CastVO castVO = null;
				CastVO partyVO = null;
				for(Object[] castInfo : castList){
					if(castsMap.get(castInfo[0].toString()) != null){
						castVO = castsMap.get(castInfo[0].toString());
					}else{
						castVO = new CastVO();
						Map<String,CastVO> partiesMap = new HashMap<String,CastVO>();
						for(Object[] party:parties){
							partyVO = new CastVO();
							partyVO.setPartyId((Long)party[1]);
							partyVO.setPartyName(party[0].toString());
							partiesMap.put(party[0].toString(),partyVO);
						}
						castVO.setPartiesMap(partiesMap);
						castsMap.put(castInfo[0].toString(), castVO);
					}
					castVO.setCastName(castInfo[0].toString());
					castVO.setCastCount((Long)castInfo[1]);
					castVO.setPartyNotAssigCount((Long)castInfo[1]);
					castVO.setCastStateId((Long)castInfo[2]);
				}
				
				for(Object[] party : partiesList){
					CastVO cast = castsMap.get(party[0].toString());
					if(cast != null){
						CastVO partyVo = cast.getPartiesMap().get(party[1].toString());
						if(partyVo != null){
							partyVo.setPartyCount((Long)party[2]);
							cast.setPartyCount(cast.getPartyCount()+(Long)party[2]);
							cast.setPartyNotAssigCount(cast.getPartyNotAssigCount()-(Long)party[2]);
						}
					}
				}
				resultList = new ArrayList<CastVO>(castsMap.values());
				if(resultList != null && resultList.size() >0){
					for(CastVO result:resultList){
						result.setPartiesList(new ArrayList<CastVO>(result.getPartiesMap().values()));
						Collections.sort(result.getPartiesList(),castVOSort);
					}
				}
				voterCastInfoVO.setCastVOs(resultList);
				return voterCastInfoVO;
			}catch (Exception e) {
				log.error("Exception Occured in getCastWisePartyCount() Method, Exception is - ",e);
				return voterCastInfoVO;
			}
		     
		 }
	     public static Comparator<CastVO> castVOSort = new Comparator<CastVO>()
				{
							  
			      public int compare (CastVO m1, CastVO m2){
	                return m1.getPartyName().compareTo(m2.getPartyName());
	               }
				};
				
				 public List<SelectOptionVO> getElectionIdAndTypeInAConstituencyByPublicationId(Long publicationDateId, Long constituencyId)
				 {
					 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
					 try{
						 PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
						 List<Long> mainElectionId = constituencyElectionDAO.getNearestPreviousMainElectionIdFromADateInAConstituency(constituencyId, publicationDate.getDate());
						 
						 List<Object[]> list = constituencyElectionDAO.getElectionIdAndSubTypeByConstituencyIdAndDate(constituencyId, electionDAO.get(mainElectionId.get(0)).getElectionDate(), publicationDate.getDate());
						 if(list != null && list.size() > 0)
						 {
							 for(Object[] params : list)
								 resultList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
							 
						 }
						 
						 return resultList;
					 }catch (Exception e) {
						 log.error("Exception Occured in getElectionIdAndTypeInAConstituencyByPublicationId() Method - "+e);
						 return resultList;
					}
				 }
				 
				 public Long getReportLevelId(String type)
				 {
					 Long reportLevelId = 0l;
					 try{
						 reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
						 return reportLevelId;
					 }catch (Exception e) {
						e.printStackTrace();
						log.error("Exception Occured in getReportLevelId() Method, Exception - "+e);
						return reportLevelId;
					}
				 }
				 
				 public List<VotersDetailsVO> getCountList(Long publicationDateId,Long id,String type)
				 {
					 List<SelectOptionVO> namesList = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> mandalList = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> muncipalityList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> panchayatiesList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
					 List<SelectOptionVO> panchayatList1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> boothsList1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> localbody1 = new ArrayList<SelectOptionVO>(0);
					 List<SelectOptionVO> localbody2 = new ArrayList<SelectOptionVO>(0);
					 SelectOptionVO panchayat;
					 SelectOptionVO booth = null;
					 VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					 List<VotersDetailsVO> resultlist = new ArrayList<VotersDetailsVO>();
					 SelectOptionVO panchayat1;
					 String mandalName = "";
					 if(type.equalsIgnoreCase("constituency"))
					   votersDetailsVO.setAreaType(constituencyDAO.get(id).getAreaType());
					
				 try
					 {
					 if(type.equalsIgnoreCase("mandal"))
					 	{
							if(id.toString().substring(0,1).trim().equalsIgnoreCase("2"))
							{
								type = "mandal";	
								id = new Long(id.toString().substring(1));
							}
							else if(id.toString().substring(0,1).trim().equalsIgnoreCase("1"))
							{
								
								List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
								id =(Long)listId.get(0); 
								type = "localElectionBody";
							}
						}
					 if(type.equalsIgnoreCase("constituency"))
					 {
								namesList = regionServiceDataImp.getSubRegionsInConstituency(id, IConstants.PRESENT_YEAR, null);
								mandalList = getMandals(namesList);
								muncipalityList = getMuncipalities(namesList);
							 	if(mandalList != null && mandalList.size() > 0)
								{
									votersDetailsVO.setMandalList(mandalList);
									votersDetailsVO.setTotalmandals(new Long(mandalList.size()));
									//each Mandal PanchayatList
								for(SelectOptionVO mandals: mandalList)
								{
									
									panchayatList1= staticDataService.getPanchayatiesByMandalId(new Long(mandals.getId().toString().substring(1)));
									if(panchayatList1 != null && panchayatList1.size() > 0)
									{
									mandals.setSelectOptionsList(panchayatList1);
									mandals.setValue(new Long(panchayatList1.size()).toString());
									}
									else
									{
										mandals.setValue("0");
									}
									//each panchayat boothList
									if(panchayatList1!=null && panchayatList1.size() > 0)
									for(SelectOptionVO panchayats : panchayatList1)
									{
										 boothsList1 = getBoothsByPanchayatId((Long)panchayats.getId(),publicationDateId);
										 if(boothsList1 != null && boothsList1.size() > 0)
										 { 
										 panchayats.setSelectOptionsList(boothsList1);	
										 panchayats.setValue(new Long(boothsList1.size()).toString());
										 }
										 else
											 panchayats.setValue("0");
									 }
									
									
								}
						}
							 	//each Muncipality boothList
								if(muncipalityList != null && muncipalityList.size() > 0){
									int totalWards=0;
								for(SelectOptionVO localbody : muncipalityList)
								{
									localbody1 = getWardsMunicipality(new Long(localbody.getId().toString().substring(1)),publicationDateId);
									localbody2 = getBoothsInMunicipality(new Long(localbody.getId().toString().substring(1)), publicationDateId);
									if(localbody1!= null && localbody1.size() > 0)
									{
									localbody.setSelectOptionsList(localbody1);	
									totalWards = totalWards +localbody1.size();
									localbody.setValue(new Long(localbody1.size()).toString());
									}
									else if(localbody2!= null && localbody2.size() > 0){
										localbody.setSelectOptionsList1(localbody2);
									 localbody.setValue(new Long(localbody2.size()).toString()); }
									else
										localbody.setValue("0");
									
								 }if(totalWards>0)
								votersDetailsVO.setTotalNoOfWards(totalWards);
								}
								
				 }
					 if(!type.equalsIgnoreCase("panchayat") && !type.equalsIgnoreCase("localElectionBody") && !type.equalsIgnoreCase("ward"))
					 {
						    List<Object[]> panchayatiesList1 = null;
						    try{
							 panchayatiesList1 = panchayatDAO.getPanchayatiesCount(id,type,publicationDateId);
						    }catch(Exception e){
						    	log.error("Exception Occured in getting panchayaties  - ",e);
						    }
							if(panchayatiesList1 !=null && panchayatiesList1.size() > 0)
							{
							 for(Object[] params :panchayatiesList1)
							 {
							 panchayat = new SelectOptionVO((Long)params[0],params[1].toString()+" Panchayat");
							 panchayatiesList.add(panchayat);
							 }
							 for(SelectOptionVO panchayats : panchayatiesList)
								{
									 boothsList1 = getBoothsByPanchayatId((Long)panchayats.getId(),publicationDateId);
									
									 panchayats.setSelectOptionsList(boothsList1);	
								
								 }
							 votersDetailsVO.setPanchayatList(panchayatiesList);
							 votersDetailsVO.setTotalPanchayats(new Long(panchayatiesList.size()));
							}else{
								votersDetailsVO.setPanchayatList(panchayatiesList);
								votersDetailsVO.setTotalPanchayats(new Long(panchayatiesList.size()));
							}
						
					 }
					List<Object[]> booths = boothDAO.getBoothsCount(id,publicationDateId,type);
					if(booths != null && booths.size() > 0)
					{
						for(Object[] params :booths)
						{
						booth = new SelectOptionVO((Long)params[0],params[1].toString());
						booth.setLocation(params[2] != null?params[2].toString():"");
						booth.setVillageCovered(params[3] != null?params[3].toString():"");
						boothsList.add(booth);
						}
						votersDetailsVO.setTotalBooths(new Long(boothsList.size()));
						votersDetailsVO.setBoothsList(boothsList);
					}
					if(type.equalsIgnoreCase("localElectionBody"))
					{
						//getCount for wards
						List<Object> count = boothDAO.getNoOfWardsInMuncipality(id, publicationDateId);
						 if(count != null && count.size()>0 )
						votersDetailsVO.setTotalNoOfWards(((Long)count.get(0)).intValue());
					}
					if(muncipalityList != null && muncipalityList.size() > 0)
					{
						votersDetailsVO.setNoOfLocalBodies(new Long(muncipalityList.size()));
						votersDetailsVO.setLocalbodiesList(muncipalityList);
					}
					resultlist.add(votersDetailsVO);
						
					return resultlist;
					}
					 
					
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getCountList() method - ",e);
						return resultlist;
					}
					
			}
					public List<SelectOptionVO> getMandals(List<SelectOptionVO> list)
					{
					List<SelectOptionVO> resultlist = new ArrayList<SelectOptionVO>();
					try{
					if(list != null && list.size() > 0)
					{
					for(SelectOptionVO params : list)
					{
						if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
							resultlist.add(new SelectOptionVO(params.getId(),params.getName().toString()));	
					}
					}
					return resultlist;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getMandals() - "+e);
						return resultlist;
					}
					
					}
					public List<SelectOptionVO> getMuncipalities(List<SelectOptionVO> list)
					{
					List<SelectOptionVO> resultlist = new ArrayList<SelectOptionVO>();
					try{
					if(list != null && list.size() > 0)
					{
					for(SelectOptionVO params : list)
					{
						if(params.getId().toString().substring(0,1).trim().equalsIgnoreCase("1"))
							resultlist.add(new SelectOptionVO(params.getId(),params.getName().toString()));	
					}
					}
					return resultlist;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						log.error("Exception Occured in getMuncipalities() - "+e);
						return resultlist;
					}
					
					}
					
					public List<PartyVotesEarnedVO> getPreviousElectionVotingTrends(Long id, Long publicationDateId,Long constituencyId, String type)
					{
						List<PartyVotesEarnedVO> partyVotesEarnedVOList = new ArrayList<PartyVotesEarnedVO>(0);
						try{
							List<Object[]> list = null;
							String type2 = null;
							
							if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
							{
								List<Long> constituencyIdsList = new ArrayList<Long>(0);
								
								List parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(id);
								if(parliamentList != null && parliamentList.size() > 0)
								{
									for(Object[] params : (List<Object[]>)parliamentList)
									{
										if(!constituencyIdsList.contains(params[0]))
											constituencyIdsList.add((Long)params[0]);
									}
								}
								if(!constituencyIdsList.contains(id))
									constituencyIdsList.add(id);
								if(constituencyIdsList != null && constituencyIdsList.size() > 0)
									list = constituencyElectionDAO.findAllEleHappendInAConstituency(constituencyIdsList);
								
							}
							else if(type.equalsIgnoreCase(IConstants.MANDAL))
							{
								if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
								{
									id = new Long(id.toString().trim().substring(1));
									list = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(id);
									type2 = IConstants.RURAL;
								}
								else if(id.toString().trim().substring(0, 1).equalsIgnoreCase("1"))
								{
									List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
									id = (Long)list2.get(0);
									list = hamletBoothElectionDAO.findAllElectionsHappendInALocalElectionBody(id);
									type2 = IConstants.URBAN;
								}
								
								
							}
							else if(type.equalsIgnoreCase("ward")){
								list = constituencyElectionDAO.findAllElectionsHappendInAConstituency(id);
								
							}else if(type.equalsIgnoreCase("panchayat"))
							 list = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(id);
							
								
							
							if(list != null && list.size() > 0)
							{
								PartyVotesEarnedVO partyVotesEarnedVO = null;
								List<String> partiesList = new ArrayList<String>(0);
								List<Long> totalVoters = new ArrayList<Long>(0); 
								for(Object[] params : list)
								{
									Long polledVotes = 0l;
									partyVotesEarnedVO = new PartyVotesEarnedVO();
									Election electionObj = electionDAO.get((Long)params[0]);
									partyVotesEarnedVO.setElectionYear(electionObj.getElectionYear());
									partyVotesEarnedVO.setElectionType(params[1].toString());
									String elecType ="";
									if(electionObj.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase("Parliament")){
										elecType ="Parliament";
									}else{
										elecType ="Assembly";
									}
									if(params[1].toString().equalsIgnoreCase("BYE"))
										partyVotesEarnedVO.setReqType(elecType+" (Bye)");
									else
									    partyVotesEarnedVO.setReqType(elecType+"");
									String boothIdStr = "";
									
									List<Long> boothIdsList = null;
									if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
										boothIdsList =  boothConstituencyElectionDAO.getBoothIdsByConstituencyId(id, (Long)params[0]);
									
									else if(type.equalsIgnoreCase(IConstants.MANDAL))
									{
										if(type2.equalsIgnoreCase(IConstants.RURAL))
										 boothIdsList = hamletBoothElectionDAO.getBoothIdsByMandalId(id, (Long)params[0]);
										else if(type2.equalsIgnoreCase(IConstants.URBAN))
											boothIdsList = boothConstituencyElectionDAO.getBoothIdsByLocalEleBodyId(id, (Long)params[0],constituencyId);
									}
									
									else if(type.equalsIgnoreCase("panchayat"))
										boothIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(id, (Long)params[0]);
									
									else if(type.equalsIgnoreCase("booth"))
									{
										boothIdStr = id.toString();
										List<Long> boothId = new ArrayList<Long>(0);
										boothId.add(id);
										totalVoters = boothDAO.getTotalaVotesByBoothIds(boothId);
									}
									else if(type.equalsIgnoreCase("ward"))
									{   boothIdsList =  boothConstituencyElectionDAO.getBoothIdsByConstituencyId(id, (Long)params[0]);
									}
									 if(boothIdsList != null && boothIdsList.size() > 0)
									{
										 partyVotesEarnedVO.setTotalBooths(boothIdsList.size());
											for(Long boothId :boothIdsList)
												boothIdStr = boothIdStr + boothId.toString()+",";
											boothIdStr = boothIdStr.substring(0,boothIdStr.length()-1);
											totalVoters = boothDAO.getTotalaVotesByBoothIds(boothIdsList);
									}
									
									if(boothIdStr.isEmpty())
										continue;
									
									if(totalVoters != null && totalVoters.size() > 0 && !totalVoters.isEmpty())
										partyVotesEarnedVO.setTotalVotes(totalVoters.get(0));
											
									List<PartyVotesEarnedVO> votesEarnedVOs = constituencyPageService.getPanchayatWiseElectionsForTehsil(boothIdStr,(Long)params[0]);
									for(PartyVotesEarnedVO partyVoters : votesEarnedVOs)
									{
										if(!partiesList.contains(partyVoters.getPartyName()))
											partiesList.add(partyVoters.getPartyName());
									}
									partyVotesEarnedVO.setPartyVotesEarnedVOs(votesEarnedVOs);
									partyVotesEarnedVOList.add(partyVotesEarnedVO);
									
									for(PartyVotesEarnedVO partyVoters : votesEarnedVOs)
										polledVotes += partyVoters.getVotesEarned();
									
									partyVotesEarnedVO.setPolledVotes(polledVotes);
								}
								Collections.sort(partiesList);
								for(PartyVotesEarnedVO  votesEarnedVO : partyVotesEarnedVOList)
									votesEarnedVO.setPartiesList(partiesList);
								
								for(PartyVotesEarnedVO  votesEarnedVO : partyVotesEarnedVOList)
								{
									List<PartyVotesEarnedVO> resultList = new ArrayList<PartyVotesEarnedVO>(0);
									for(String partyInList :partiesList)
									{
										PartyVotesEarnedVO partyResult = new PartyVotesEarnedVO();
										partyResult.setPartyName(partyInList);
										partyResult.setVotesEarned(0L);
										for(PartyVotesEarnedVO  partiesResult : votesEarnedVO.getPartyVotesEarnedVOs())
										{
											if(partiesResult.getPartyName().equalsIgnoreCase(partyInList))
												partyResult.setVotesEarned(partiesResult.getVotesEarned()); 
										}
										resultList.add(partyResult);
									}
									votesEarnedVO.setPartyVotesEarnedVOs(resultList);
								}
							}
							
							return partyVotesEarnedVOList;
						}catch (Exception e) {
							e.printStackTrace();
							log.error("Exception Occured in getPreviousElectionVotingTrends() Method, Exception - "+e);
							return null;
						}
						
					}				
	 public void getBoothsComparisionInfo(List<Election> electionIds,Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
		try{ 
		 List<Object[]> presentBoothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
		 Panchayat panchayat = panchayatDAO.get(panchayatId);
		 PublicationDate publicationDate = publicationDateDAO.get(publicationDateId);
		 
		 List<Object[]> previousBoothsList = null;
		 Election electionObj = null;
		 Map<Long,String> boothsAdded = new HashMap<Long,String>();
		 Map<Long,String> boothsRemoved = new HashMap<Long,String>();
		 List<String> newlyAdded = new ArrayList<String>();
		 List<String> completelyRemoved = new ArrayList<String>();
		 List<String> otherComment = new ArrayList<String>();
		 Integer presentBoothsCount = presentBoothsList.size();
		 Integer prevBoothsCount = null;
		 String presentBooths = "";
		 String prevBooths = "";
		 String prevElecYear = null;

		 for(Election election:electionIds){
			 if("MAIN".equalsIgnoreCase(election.getElecSubtype())){
				 electionObj = election;
				 prevElecYear = election.getElectionYear();
				 previousBoothsList = hamletBoothElectionDAO.getBoothsInAPanchayat(election.getElectionId(),panchayatId);
			 }
			 
		 }
		 if(previousBoothsList == null || previousBoothsList.size() == 0)
			 return;
		 votersInfoForMandalVO.setPanchayatInfoPresent(true);
		  prevBoothsCount = previousBoothsList.size();
		  
		  for(Object[] previousBooth:previousBoothsList){
			   boolean boothRemoved = true;
			  for(Object[] presentBooth:presentBoothsList){
				  if(presentBooth[1].toString().trim().equalsIgnoreCase(previousBooth[1].toString().trim()))
					  boothRemoved = false;
			  }
			  if(boothRemoved)
				  boothsRemoved.put((Long)previousBooth[0], previousBooth[1].toString().trim());
			  prevBooths = prevBooths+" "+previousBooth[1].toString();
		  }
		  
		  for(Object[] presentBooth:presentBoothsList){
			   boolean boothAdded = true;
			  for(Object[] previousBooth:previousBoothsList){
				  if(presentBooth[1].toString().trim().equalsIgnoreCase(previousBooth[1].toString().trim()))
					  boothAdded = false;
			  }
			  if(boothAdded)
				  boothsAdded.put((Long)presentBooth[0], presentBooth[1].toString().trim());
			  presentBooths = presentBooths+" "+presentBooth[1].toString();
		  }
		  for(Long key:boothsRemoved.keySet()){
			  String partNo = boothsRemoved.get(key);
			  Booth booth = boothDAO.get(key);
			   List<Object[]> boothInfo = boothDAO.getBoothInfo(publicationDateId,booth.getConstituency().getConstituencyId(),partNo);
			   if(boothInfo != null && boothInfo.size() >0)
				   otherComment.add("In "+publicationDate.getYear()+" booth-"+partNo+" is moved from "+panchayat.getPanchayatName()+" Panchayat to "+boothInfo.get(0)[1].toString()+" Panchayat");
			   else
				   completelyRemoved.add(partNo); 
		  }
		  for(Long key:boothsAdded.keySet()){
			  String partNo = boothsAdded.get(key);
			  Booth booth = boothDAO.get(key);
			   List<Object[]> boothInfo = hamletBoothElectionDAO.getPanchayatByBoothElec(electionObj.getElectionId(),partNo,booth.getConstituency().getConstituencyId());
			   if(boothInfo != null && boothInfo.size() >0)
				   otherComment.add("In "+publicationDate.getYear()+" booth-"+partNo+" is moved from "+boothInfo.get(0)[1].toString()+" Panchayat to "+panchayat.getPanchayatName()+" Panchayat");
			   else
				   newlyAdded.add(partNo); 
		  }
		  votersInfoForMandalVO.setNewlyAdded(newlyAdded);
		  votersInfoForMandalVO.setCompletelyRemoved(completelyRemoved);
		  votersInfoForMandalVO.setOtherComment(otherComment);
		  votersInfoForMandalVO.setPresentBoothsCount(presentBoothsCount);
		  votersInfoForMandalVO.setPrevBoothsCount(prevBoothsCount);
		  votersInfoForMandalVO.setPresentBooths(presentBooths);
		  votersInfoForMandalVO.setPrevBooths(prevBooths);
		  votersInfoForMandalVO.setElectionYear(electionObj.getElectionYear());
		}catch(Exception e){
			log.error("Exception Occured in getBoothsComparisionInfo Method, Exception is - ",e);
		}
	 }
	 
	 
	 public List<SelectOptionVO> getConstituenciesFromBoothPublicationVoter()
	 {
		 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>(0);
		 
		 SelectOptionVO list = null;
		 try{
			List<Object[]> constituencies = boothPublicationVoterDAO.getConstituencies();
			if(constituencies!=null && constituencies.size() > 0)
			{
				for(Object[] params : constituencies)
				{
					list = new SelectOptionVO((Long)params[0],params[1].toString());
					resultList.add(list);
				}
			}
			return resultList;
		 }
		 catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getConstituenciesFromBoothPublicationVoter() method -"+e);
			return resultList;
		}
		
	 }
	 
	 public VoterHouseInfoVO getUserVoterCategories(Long userId){
		 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
		  try{
			 List<SelectOptionVO> categoriesList = new ArrayList<SelectOptionVO>();
			 voterHouseInfoVO.setCategory(categoriesList);
			 List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(userId);
			 SelectOptionVO selectOptionVO = null;
			 for(Object[] category:userCategoryValuesList){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)category[0]);
				 selectOptionVO.setName(category[1]!=null?category[1].toString():"");
				 categoriesList.add(selectOptionVO);
			 }
		  }catch(Exception e){
			  log.error("Exception Occured in getUserVoterCategories Method, Exception is - ",e);
		  }
		 return voterHouseInfoVO;
	 }
	 
	 public VoterHouseInfoVO getSelectedCategoryOptions(VoterHouseInfoVO parameters){
		  VoterHouseInfoVO votersHouseInfoVO = parameters;
		  try{
		 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
		 	    defaultSelectOptionVO.setId(0l);
		 	    defaultSelectOptionVO.setName("Select");
		 	     if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll())
		 	       getPartiesAndCastsInVotersState(votersHouseInfoVO,parameters.getVoterId(),parameters.getUserId(),defaultSelectOptionVO);
		 	     if(parameters.isAll() || parameters.getIds().size() > 0){
		 	       List<Object[]> userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(parameters.getUserId());
		 	       VoterHouseInfoVO category = null;
		 	       List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
		 	       for(Object[] userCategoryValue : userCategoryValuesList)
		 			{ 
		 	    	     if(parameters.isAll() || parameters.getIds().contains((Long)userCategoryValue[0])){
			 				 category = new VoterHouseInfoVO();
			 				 getAllCategoryOptions(userCategoryValue,category,defaultSelectOptionVO,parameters.getUserId());
			 				 categoriesList.add(category);	
		 	    	     }
		 			}	
		 	      votersHouseInfoVO.setCategoriesList(categoriesList);
		 	     }
		   }catch(Exception e){
		 	  log.error("Exception rised in getSelectedCategoryOptions ",e);
		   }
		 	return votersHouseInfoVO;
		  
	  }
	 
	
	  public VoterHouseInfoVO getSelectedCategoryOptionsForIndividual(List<VoterHouseInfoVO> voterIds,VoterHouseInfoVO parameters){
		  VoterHouseInfoVO votersHouseInfoVO = parameters;
		 
		  
		  Map<Long,Map<String, List<VoterHouseInfoVO>>> boothMap = new HashMap<Long,Map<String, List<VoterHouseInfoVO>>>();
			Map<String, List<VoterHouseInfoVO>> voterByHouseNoMap = null;
			
			List<VoterHouseInfoVO> voterVOs = null;
			
		  try{
			  if(voterIds != null && voterIds.size() >0){

		 	    SelectOptionVO defaultSelectOptionVO = new SelectOptionVO();
		 	    defaultSelectOptionVO.setId(0l);
		 	    defaultSelectOptionVO.setName("Select");
		 	   if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll())
		 	    getPartiesAndCastsInVotersState(votersHouseInfoVO,voterIds.get(0).getVoterId(),parameters.getUserId(),defaultSelectOptionVO);
		 	    List<Object[]> userCategoryValuesList = null;
		 	   List<Object[]> selectedUserCategoryValuesList = new ArrayList<Object[]>();
		 	   if(parameters.isAll() || parameters.getIds().size() > 0){
		 	     userCategoryValuesList = userVoterCategoryDAO.getCategoryValuesList(parameters.getUserId());
			 	   if(parameters.isAll()){
			 		  selectedUserCategoryValuesList = userCategoryValuesList;
			 	   }else{
		 	        for(Long catId : parameters.getIds()){
			 	    	 for(Object[] userCategoryValue:userCategoryValuesList){
			 	    		 if(catId.longValue() == ((Long)userCategoryValue[0]).longValue()){
			 	    			selectedUserCategoryValuesList.add(userCategoryValue);
			 	    		 }
			 	    	 }
			 	     }
			 	   }
		 	   }
		 	   VoterHouseInfoVO voterHouseInfoVO = null;
		 	   
		 	    for(VoterHouseInfoVO voter : voterIds){
		 	    	
		 	     voterHouseInfoVO = new VoterHouseInfoVO();
		 	     
		 	     getVoterBasicInfo(voterHouseInfoVO,voter.getVoterId());
		 	   
		 	     
		 	    voterByHouseNoMap = boothMap.get(voter.getBoothId());
				if( voterByHouseNoMap == null){
					voterByHouseNoMap = new HashMap<String, List<VoterHouseInfoVO>>();
					boothMap.put(voter.getBoothId(), voterByHouseNoMap);
				}
				voterVOs = voterByHouseNoMap.get(voterHouseInfoVO.getHouseNo());
				if(voterVOs ==null){
					voterVOs = new ArrayList<VoterHouseInfoVO>();
					voterByHouseNoMap.put(voterHouseInfoVO.getHouseNo(), voterVOs);
				}
				voterVOs.add(voterHouseInfoVO);
				if(parameters.isPartyPresent() || parameters.isCastPresent() || parameters.isAll())
		 	       voterSelectedCastAndPartyDetails(voterHouseInfoVO,voter.getVoterId(),parameters.getUserId());
				if(parameters.isAll() || selectedUserCategoryValuesList.size() > 0){    
			 	     List<VoterHouseInfoVO> categoriesList = new ArrayList<VoterHouseInfoVO>();
			 	   
			 	     getVoterSelectedCategoryValues(selectedUserCategoryValuesList,defaultSelectOptionVO,parameters.getUserId(),voter.getVoterId(),categoriesList);
			 	   
			 	     voterHouseInfoVO.setCategoriesList(categoriesList);
				}
		 	     
		 	   }
		 	   List<VoterHouseInfoVO> boothsList = new ArrayList<VoterHouseInfoVO>();
		 	  List<VoterHouseInfoVO> familiesList = null;
		 	    for(Long key:boothMap.keySet()){
		 	    	VoterHouseInfoVO booth = new VoterHouseInfoVO();
		 	    	boothsList.add(booth);
		 	    	booth.setBoothId(key);
		 	    	VoterHouseInfoVO boothInfo = getBoothDetailsForVoter(key);
		 	    	booth.setBoothName(boothInfo.getBoothName());
		 	    	booth.setVilliageCovered(boothInfo.getVilliageCovered());
		 	    	booth.setPanchayatName(boothInfo.getPanchayatName());
		 	    	Map<String, List<VoterHouseInfoVO>> families = boothMap.get(key);
		 	    	familiesList = new ArrayList<VoterHouseInfoVO>();
		 	    	for(String familyKey:families.keySet()){
		 	    		VoterHouseInfoVO family = new VoterHouseInfoVO();
		 	    		familiesList.add(family);
		 	    		family.setHouseNo(familyKey);
		 	    		family.setVotersList(families.get(familyKey));
		 	    	}
		 	    	booth.setFamiliesList(familiesList);
		 	    }
		 	    
		 	   votersHouseInfoVO.setBoothsList(boothsList);
			  }
		   }catch(Exception e){
		 	  log.error("Exception rised in getSelectedCategoryOptionsForIndividual ",e);
		   }
		 	return votersHouseInfoVO;
		  
	  }
	  
	  public void updateSelectedFieldsForAllVoters(VoterHouseInfoVO voterHouseInfoVO,String[] voterIds,String partyCast){
		try{ 
		  for(String voterId:voterIds){ 
			 voterHouseInfoVO.setVoterId(new Long(voterId));
		    updateVoterDetails(voterHouseInfoVO,partyCast);
		  }
		}catch(Exception e){
			log.error("Exception rised in updateSelectedFieldsForAllVoters ",e);
		}
	  }
	  
	  public Double calculateMaleAndFemalePercentage(Long totalVoters, Long MaleOrFemaleVoters)
		{
			try{
				if(totalVoters != null && totalVoters != 0)
					return (MaleOrFemaleVoters.doubleValue()*100.0)/totalVoters.doubleValue();
				else
					return 0.00;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in calculateMaleAndFemalePercentage() Method, Exception - "+e);
				return 0.00;
			}
			
		}
	  
	  public Long getVoterFamilyRangeIdByFamilyRange(String familyRange)
		{
			try{
				return voterFamilyRangeDAO.getVoterFamilyRangeIdByFamilyRange(familyRange);
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterFamilyRangeIdByFamilyRange() Method, Exception - "+e);
				return 0l;
			}
		}
	  
	  public Long getVoterAgeRangeId(String ageRange)
		 {
			 try{
				 return voterAgeRangeDAO.getVoterAgeRangeIdByType(ageRange);
			 }catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterAgeRangeId() Method, Exception - "+e);
				return 0l;
			}
		 }
	  
	  
	  public VotersInfoForMandalVO getVotersDetailsByVoterReportLevelId(Long reportLevelId, Long reportLevelValue, Long publicationDateId, String name, String type)
		 {
			VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
			try{
				
				List<VoterInfo> list = voterInfoDAO.getVotersCount(reportLevelId, reportLevelValue, publicationDateId);
				if(list == null || list.size() == 0)
				{
					votersInfoForMandalVO.setDatapresent(false);
					return votersInfoForMandalVO;
				}
				if(list != null && list.size() > 0)
				{
					for(VoterInfo voterDetails : list)
					{
						BigDecimal unknowCount = new BigDecimal(voterDetails.getTotalVoters().longValue()-(voterDetails.getMaleVoters().longValue() + voterDetails.getFemaleVoters().longValue()));
						votersInfoForMandalVO.setTotVoters(voterDetails.getTotalVoters() != null ? new BigDecimal(voterDetails.getTotalVoters()):new BigDecimal(0.00));
						votersInfoForMandalVO.setTotalVoters(voterDetails.getTotalVoters() != null ? voterDetails.getTotalVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalMaleVoters(voterDetails.getMaleVoters() != null ?voterDetails.getMaleVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalFemaleVoters(voterDetails.getFemaleVoters() != null?voterDetails.getFemaleVoters().toString():"0.00");
					    votersInfoForMandalVO.setTotalVotersPercentage(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalFemalePercentage(voterDetails.getFemaleVotersPercentage() != null? voterDetails.getFemaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalMalePercentage(voterDetails.getMaleVotersPercentage() != null ? voterDetails.getMaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setUnKnowVoters(unknowCount != null ? unknowCount.toString(): "0.00");
						votersInfoForMandalVO.setPercent(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setType(type);
						votersInfoForMandalVO.setName(name);
						votersInfoForMandalVO.setDatapresent(true);
					}
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersCount() Method, Exception - "+e);
				return null;
			}
		 } 
	  
	  public ResultStatus insertVotersDataInIntermediateTables(Long reportLevelValue, Long publicationDateId)
	  {
		  log.info(" Entered into insertVotersDataInIntermediateTables() Method, with Values - Report Level Value - "+reportLevelValue+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  List<Long> mandalIdsList = new ArrayList<Long>(0);
			  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
			  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
			  List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(0);
			  List<Long>panchayatIdsList = new ArrayList<Long>(0);
			  List<Long> localBodiesList = new ArrayList<Long>(0);
			  List<Long> boothIdsList = new ArrayList<Long>(0);
			  
			  calculateAndInsertVoterInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,null,publicationDateId);
			  calculateAndInsertVoterFamilyInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId);
			  calculateAndInsertVoterAgeInfoForALocation(IConstants.CONSTITUENCY, reportLevelValue, publicationDateId);
			  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
			  
			  if(mandalsList == null || mandalsList.size() == 0)
				  return null;
			  
			  for(SelectOptionVO selectOptionVO : mandalsList)
			  {
				  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
					  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
				  else
					  localBodiesList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
			  }
			  List<Object[]> list = null;
			  if(mandalIdsList != null && mandalIdsList.size() >0){ 
				   list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
			  }
			 if(list != null && list.size() > 0)
			  {
				 for(Object[] params : list)
				  panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			  }
			 
			  for(Long mandalId : mandalIdsList)
			  {
				  calculateAndInsertVoterInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId);
				  calculateAndInsertVoterAgeInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId);
			  }
			  
			  for(SelectOptionVO selectOptionVO : panchayatsList)
			  {
				  calculateAndInsertVoterInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId);
				  calculateAndInsertVoterAgeInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),publicationDateId);
				  panchayatIdsList.add(selectOptionVO.getId());
			  }
			  
			  List<Object[]> list2 = null;
			  if(panchayatIdsList.size() > 0)
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
			  
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			  }
			  
			  
			 // List<Long> wardsList = new ArrayList<Long>();
			  if(localBodiesList != null && localBodiesList.size() >0){
				  
				List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
						localBodiesList, publicationDateId);
				
				if(wards != null && wards.size() >0){
					
					for(Object[] ward:wards)
					if(ward[0] != null){
						wardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
					}		
				}
				  
			  }
			  
			  
			  
			 
			  if(localBodiesList.size() > 0)
			  {
				  for(Long localBodyId : localBodiesList)
				  {
					  calculateAndInsertVoterInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,reportLevelValue, publicationDateId);
					  calculateAndInsertVoterFamilyInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,publicationDateId);
					  calculateAndInsertVoterAgeInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,publicationDateId);
				  }
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
						  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
				  }
				  
			  }
               for(SelectOptionVO selectOptionVO:wardsList){
				  
				  calculateAndInsertVoterInfoForALocation(
						  IConstants.WARD,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId);
				  calculateAndInsertVoterAgeInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId);
			 
  
				  
			  }
			  for(SelectOptionVO selectOptionVO : boothsList)
				  if(!boothIdsList.contains(selectOptionVO.getId()))
					  boothIdsList.add(selectOptionVO.getId());
			  
			  for(Long boothId :boothIdsList)
			  {
				  SelectOptionVO selectOptionVO = null;
				  for(SelectOptionVO optionVO : boothsList)
				  if(optionVO.getId().equals(boothId))
				  {
					  selectOptionVO = optionVO;
					  break;
				  }
				  calculateAndInsertVoterInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId);
				  calculateAndInsertVoterFamilyInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId);
				  calculateAndInsertVoterAgeInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId);
			  }
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error("Exception Occured in insertVoterInfoDataToIntermediateTables(), Exception is -"+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	  }
	  
	  public ResultStatus calculateAndInsertVoterInfoForALocation(String locationType, Long locationValue, Long parentLocationId, Long publicationDateId)
	  {
		  log.info(" Entered into calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  List<Object[]> resultList = boothPublicationVoterDAO.findVotersGenderWiseCountByPublicationIdInALocation(locationType,locationValue, publicationDateId);
			  
			  if(resultList != null && resultList.size() > 0)
			  {
				  VotersInfoForMandalVO votersInfo = new VotersInfoForMandalVO();
				  for(Object[] params : resultList)
					  if(params[1].toString().equalsIgnoreCase(IConstants.MALE))
						  votersInfo.setTotalMaleVoters(params[0].toString());
					  else
						  votersInfo.setTotalFemaleVoters(params[0].toString());
				  Long totalVoters = (Long)resultList.get(0)[0]+(Long)resultList.get(1)[0];
				  votersInfo.setReportLevelId(getReportLevelId(locationType));
				  votersInfo.setReportLevelValue(locationValue);
				  votersInfo.setPublicationDateId(publicationDateId);
				  votersInfo.setTotalVoters(totalVoters.toString());
				  votersInfo.setTotalMalePercentage(new BigDecimal((new Double(votersInfo.getTotalMaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  votersInfo.setTotalFemalePercentage(new BigDecimal((new Double(votersInfo.getTotalFemaleVoters())*100)/totalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  votersInfo.setTotalFamilies(getFamiliesCountInALocation(locationType,locationValue,publicationDateId));
				  
				  if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				  {
					  votersInfo.setTotalVotersPercentage(null);
					  votersInfo.setTotalFamilyPercentage(null);
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.MANDAL) || 
						  locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.CONSTITUENCY),parentLocationId,publicationDateId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.CONSTITUENCY), parentLocationId, publicationDateId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.MANDAL),parentLocationId,publicationDateId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.MANDAL), parentLocationId, publicationDateId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.WARD))
				  {
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(IConstants.LOCALELECTIONBODY),parentLocationId,publicationDateId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(IConstants.LOCALELECTIONBODY), parentLocationId, publicationDateId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				  {
					  List<Long> boothIds = boothDAO.checkForUrbanBooth(locationValue,publicationDateId);
					  String lType = "";
					  if(boothIds.size() > 0)
						  lType = IConstants.LOCALELECTIONBODY;
					  else
						  lType = IConstants.PANCHAYAT;
					  
					  Long parentTotalVoters = voterInfoDAO.getVotersCountInALocation(getReportLevelId(lType),parentLocationId,publicationDateId);
					  votersInfo.setTotalVotersPercentage(new BigDecimal((new Double(votersInfo.getTotalVoters())*100)/parentTotalVoters).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					  Long parentTotalfamilies = voterInfoDAO.getFamiliesCountInALocation(getReportLevelId(lType), parentLocationId, publicationDateId);
					  votersInfo.setTotalFamilyPercentage(new BigDecimal((new Double(votersInfo.getTotalFamilies())*100)/parentTotalfamilies).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				  }
				  saveVotersDataInVoterInfoTable(votersInfo);
			  }
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error(" Exception Occured in calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
			  log.error(" Exception is -"+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	  }
	  
	  public Long getFamiliesCountInALocation(String locationType, Long locationValue,  Long publicationDateId)
	  {
		  log.info("Entered into getFamiliesCountInALocation method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
		  Long familiesCount = 0L;
		  try{
			  List<Long> familiesCountList = boothPublicationVoterDAO.findFamiliesCountByPublicationIdInALocation(locationType,locationValue,publicationDateId);
			  for(Long fCount : familiesCountList)
				  familiesCount = familiesCount + fCount;
			  return familiesCount;
		  }catch (Exception e) {
			  log.error("Exception Occured in getFamiliesCountInALocation() method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
			  log.error("Exception is - "+e);
			  return familiesCount;
		  }
	  }
	  
	  public ResultStatus saveVotersDataInVoterInfoTable(final VotersInfoForMandalVO votersInfo)
	  {
		  log.info("Entered into saveVotersDataInVoterInfoTable() Method...");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) 
			{
				VoterInfo voterInfo = new VoterInfo();
				voterInfo.setTotalVoters(new Long(votersInfo.getTotalVoters()));
				voterInfo.setMaleVoters(new Long(votersInfo.getTotalMaleVoters()));
				voterInfo.setFemaleVoters(new Long(votersInfo.getTotalFemaleVoters()));
				voterInfo.setTotalFamilies(votersInfo.getTotalFamilies());
				voterInfo.setTotalVotersPercentage((votersInfo.getTotalFamilyPercentage() == null ||
						votersInfo.getTotalFamilyPercentage().equalsIgnoreCase(""))? null : new Double(votersInfo.getTotalFamilyPercentage()));
				voterInfo.setMaleVotersPercentage(new Double(votersInfo.getTotalMalePercentage()));
				voterInfo.setFemaleVotersPercentage(new Double(votersInfo.getTotalFemalePercentage()));
				voterInfo.setFamiliesPercentage((votersInfo.getTotalFamilyPercentage() == null ||
						votersInfo.getTotalFamilyPercentage().equalsIgnoreCase(""))? new Double(0.00) : new Double(votersInfo.getTotalFamilyPercentage()));
				voterInfo.setReportLevelValue(votersInfo.getReportLevelValue());
				voterInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
				voterInfo.setPublicationDate(publicationDateDAO.get(votersInfo.getPublicationDateId()));
				voterInfoDAO.save(voterInfo);
			}});
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  voterDAO.flushAndclearSession();
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveVotersDataInVoterInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  /** -- Voter Family Info --**/
	  
	  public ResultStatus calculateAndInsertVoterFamilyInfoForALocation(String locationType, Long locationValue,  Long publicationDateId)
	  {
		  log.info("Entered into calculateAndInsertVoterFamilyInfoForALocation method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			  
			  importantFamiliesInfoVo.setReportLevelId(getReportLevelId(locationType));
			  importantFamiliesInfoVo.setReportLevelValue(locationValue);
			  importantFamiliesInfoVo.setPublicationDateId(publicationDateId);
			  
			  List<Long> list = boothPublicationVoterDAO.getAllImpFamilesCount(locationType, locationValue, publicationDateId);
			  
			  if(list == null || list.size() == 0)
				  return null;
			  
			  Long totalFamilies = new Long(list.size());
			  Long lessThan3Count = 0l;
			  Long between4To6Count = 0l;
			  Long between7To10Count = 0l;
			  Long above10Count = 0l;
			  
			  for(Long familyCount : list)
			  {
				  if(familyCount.longValue() <=3)
					  lessThan3Count++;
				  else if(familyCount.longValue() >=4 && familyCount.longValue() <= 6)
					  between4To6Count++;
				  else if(familyCount.longValue() >=7 && familyCount.longValue() <= 10)
					  between7To10Count++;
				  else  if(familyCount.longValue() >10)
					  above10Count++;
			  }
			  
			  importantFamiliesInfoVo.setTotalFamalies(lessThan3Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("0-3"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(lessThan3Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(between4To6Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("4-6"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(between4To6Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(between7To10Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("7-10"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(between7To10Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  importantFamiliesInfoVo.setTotalFamalies(above10Count);
			  importantFamiliesInfoVo.setTypeId(getVoterFamilyRangeIdByFamilyRange("10-Above"));
			  importantFamiliesInfoVo.setTotalPercentage(new BigDecimal(above10Count.doubleValue()*100/totalFamilies.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			  saveVotersDataInVoterFamilyInfoTable(importantFamiliesInfoVo);
			  
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			log.error("Exception Occured in calculateAndInsertVoterFamilyInfoForALocation() method with Values, Location Type - "+locationType+" - Location Value - "+locationValue+" and Publicarion Date Id - "+publicationDateId);
			log.error("Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	  }
	  
	  public ResultStatus saveVotersDataInVoterFamilyInfoTable(final ImportantFamiliesInfoVo importantFamiliesInfoVo)
	  {
		  log.info("Entered into saveVotersDataInVoterFamilyInfoTable() Method");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						VoterFamilyInfo familyInfo = new VoterFamilyInfo();
						familyInfo.setVoterReportLevel(voterReportLevelDAO.get(importantFamiliesInfoVo.getReportLevelId()));
						familyInfo.setReportLevelValue(importantFamiliesInfoVo.getReportLevelValue());
						familyInfo.setVoterFamilyRange(voterFamilyRangeDAO.get(importantFamiliesInfoVo.getTypeId()));
						familyInfo.setTotalFamilies(importantFamiliesInfoVo.getTotalFamalies());
						familyInfo.setFamiliesPercentage(importantFamiliesInfoVo.getTotalPercentage());
						familyInfo.setPublicationDate(publicationDateDAO.get(importantFamiliesInfoVo.getPublicationDateId()));
						
						voterFamilyInfoDAO.save(familyInfo);
					}});
			  voterDAO.flushAndclearSession();
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  e.printStackTrace();
			  log.error("Exception Occured in saveVotersDataInVoterFamilyInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  public ResultStatus calculateAndInsertVoterAgeInfoForALocation(String locationType, Long locationValue,  Long publicationDateId)
	  {
		  log.info("Entered into calculateAndInsertVoterAgeInfoTable Method with locationType - "+locationType+" locationValue - "+locationValue+" publicationDateId - "+publicationDateId);
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  Long totalVoters = voterInfoDAO.getTotalVotersByReportLevelValue(getReportLevelId(locationType),locationValue, publicationDateId);
			  
			  if(totalVoters == null || totalVoters.longValue() == 0)
				  return null;
			  VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
			  voterAgeRangeVO.setReportLevel(locationType);
			  voterAgeRangeVO.setReportLevelId(getReportLevelId(locationType));
			  voterAgeRangeVO.setReportLevelValue(locationValue);
			  voterAgeRangeVO.setPublicationDateId(publicationDateId);
			  voterAgeRangeVO.setTotalVoters(totalVoters);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"18-25",18l,25l);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"26-35",26l,35l);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"36-45",36l,45l);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"46-60",46l,60l);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  voterAgeRangeVO = getVoterAgeInfoForAPerticularRange(voterAgeRangeVO,"60-Above",60l,null);
			  saveVotersDataInVoterAgeInfoTable(voterAgeRangeVO);
			  
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  	log.error("Exception Occured in calculateAndInsertVoterAgeInfoForALocation() Method with locationType - "+locationType+" locationValue - "+locationValue+" publicationDateId - "+publicationDateId);
				log.error(" Exception is - ",e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	}
	  
	  public ResultStatus saveVotersDataInVoterAgeInfoTable(final VoterAgeRangeVO voterAgeRangeVO)
	  {
		  log.info("Entred into saveVotersDataInVoterAgeInfoTable() Method ");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						VoterAgeInfo voterAgeInfo = new VoterAgeInfo();
						voterAgeInfo.setVoterReportLevel(voterReportLevelDAO.get(voterAgeRangeVO.getReportLevelId()));
						voterAgeInfo.setReportLevelValue(voterAgeRangeVO.getReportLevelValue());
						voterAgeInfo.setVoterAgeRange(voterAgeRangeDAO.get(voterAgeRangeVO.getAgeRangeId()));
						voterAgeInfo.setPublicationDate(publicationDateDAO.get(voterAgeRangeVO.getPublicationDateId()));
						voterAgeInfo.setTotalVoters(voterAgeRangeVO.getTotalVotersInARange());
						voterAgeInfo.setTotalVotersPercentage(new Double(voterAgeRangeVO.getPercentage()));
						voterAgeInfo.setMaleVoters(voterAgeRangeVO.getMaleVoters());
						voterAgeInfo.setMaleVotersPercentage(voterAgeRangeVO.getMalePercentage());
						voterAgeInfo.setFemaleVoters(voterAgeRangeVO.getFemaleVoters());
						voterAgeInfo.setFemaleVotersPercentage(voterAgeRangeVO.getFemalePercentage());
						voterAgeInfoDAO.save(voterAgeInfo);
					}});
			  voterDAO.flushAndclearSession();
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  log.error("Exception Occured in saveVotersDataInVoterAgeInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	  }
	  
	  public VoterAgeRangeVO getVoterAgeInfoForAPerticularRange(VoterAgeRangeVO voterAgeRangeVO, String range,Long ageFrom,Long ageTo)
	  {
		  log.info("Entered into getVoterAgeInfoForAPerticularRange()");
		  try{
			  voterAgeRangeVO.setAgeRangeId(getVoterAgeRangeId(range));
			  voterAgeRangeVO.setTotalVotersInARange(boothPublicationVoterDAO.getVotersCountInAAgeRange(voterAgeRangeVO.getReportLevel(), voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),ageFrom,ageTo,null));
			  voterAgeRangeVO.setPercentage(new BigDecimal((voterAgeRangeVO.getTotalVotersInARange().doubleValue()*100)/voterAgeRangeVO.getTotalVoters().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
			  voterAgeRangeVO.setMaleVoters(boothPublicationVoterDAO.getVotersCountInAAgeRange(voterAgeRangeVO.getReportLevel(), voterAgeRangeVO.getReportLevelValue(), voterAgeRangeVO.getPublicationDateId(),ageFrom,ageTo,IConstants.MALE));
			  voterAgeRangeVO.setFemaleVoters(voterAgeRangeVO.getTotalVotersInARange()-voterAgeRangeVO.getMaleVoters());
			  voterAgeRangeVO.setMalePercentage(new BigDecimal((voterAgeRangeVO.getMaleVoters().doubleValue()*100)/voterAgeRangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			  voterAgeRangeVO.setFemalePercentage(new BigDecimal((voterAgeRangeVO.getFemaleVoters().doubleValue()*100)/voterAgeRangeVO.getTotalVotersInARange().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			  return voterAgeRangeVO;  
		  }catch (Exception e) {
			log.error("Exception Occured in getVoterAgeInfoForAPerticularRange() Method, Exception is - "+e);
			return voterAgeRangeVO;
		}
	  }
	  
		public List<SelectOptionVO> getConstituencyList(List<SelectOptionVO> userAccessConstituencyList)
		{
			List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
			try{
				List<Long> constituencyIds = boothPublicationVoterDAO.getConstituenciesIds(); 
				if(userAccessConstituencyList != null && userAccessConstituencyList.size() > 0 && constituencyIds != null)
				{
					for(SelectOptionVO selectOptionVO : userAccessConstituencyList)
					{
						if(constituencyIds.contains(selectOptionVO.getId()))
							constituencyList.add(selectOptionVO);
					}
				}
				
				return constituencyList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getConstituencyList() Method, Exception - "+e);
				return constituencyList;
			}
		}
		
		public List<SelectOptionVO> getAllElectionsInAConsti(Long electionTypeId,Long constiId){
			List<SelectOptionVO> options = new ArrayList<SelectOptionVO>();
			SelectOptionVO selectOptionVO = null;
			try{
			List<Object[]> electionsList = villageBoothElectionDAO.findElectionsForElectionTypeConstiId( electionTypeId, constiId);
			if(electionsList != null && electionsList.size() > 0){
				for(Object[] year:electionsList){
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)year[0]);
					selectOptionVO.setName(year[1]!=null?year[1].toString():"");
					options.add(selectOptionVO);
				}
					
			}
			}catch(Exception e){
				log.error("Exception Occured in getAllElectionsInAConsti() Method, Exception - ",e);
			}
			return options;
		}
		
		/* Voters Info */
		
		
		public VotersInfoForMandalVO getVotersBasicInfoForConstituency(String type,Long id,Long publicationDateId,String reqType){
			
			try{
				Constituency constituency = constituencyDAO.get(id);
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(type), id, publicationDateId, constituency.getName(),"Constituency");
				if(votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main"))
				{
					List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
					//getVotersBasicInfoForMultipleMandal(mandalsList,publicationDateId,votersInfoForMandalVO);
					 //calculatePercentage(votersInfoForMandalVO);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<Long> urban = new ArrayList<Long>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							 localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
							 if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
								 urban.add(new Long(list.get(0).toString()));
						}
						
					}
					if(mandalIds.size() > 0)
						votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.MANDAL),mandalIds, publicationDateId,"Mandal"));
					if(localBodyIds.size() > 0)
					{
						if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
							List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId);
							
							Map<Long,String> wardIds = new HashMap<Long,String>();
							for (Object[] ward : wardsList){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							if(wardIds.size() > 0)
								votersInfoForMandalVO.getVotersInfoForMandalVOList().addAll(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward"));
							
						}else{
							List<VotersInfoForMandalVO> localElec = getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.LOCALELECTIONBODY),localBodyIds, publicationDateId,IConstants.LOCALELECTIONBODY);
							if(localElec != null && localElec.size() > 0)
							votersInfoForMandalVO.getVotersInfoForMandalVOList().addAll(localElec);
						}
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForConstituency() Method, Exception - "+e);
				return null;
			}
			 
		}	
				
		public void getVotersBasicInfoForMultipleMandal(List<SelectOptionVO> mandalsList, Long publicationDateId, VotersInfoForMandalVO votersInfoForMandalVO)
		{
			try{
				if(mandalsList != null && mandalsList.size() > 0)
				{
					for(SelectOptionVO mandal : mandalsList)
					{
						String id = mandal.getId().toString();
						votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersBasicInfoForSelectedMandal(IConstants.MANDAL, id, publicationDateId, mandal.getName(), "sub"));
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception occured in getVotersBasicInfoForMultipleMandal() Method, Exception - "+e);
			}
		}
		
		public VotersInfoForMandalVO getVotersBasicInfoForSelectedMandal(String type,String id,Long publicationDateId,String name,String reqType)
		{
			try{
				String typeVar = IConstants.MANDAL;
				if(id.toString().substring(0,1).equalsIgnoreCase("2"))
					id = id.toString().trim().substring(1);
					
				else
				{
					typeVar = IConstants.LOCALELECTIONBODY;
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1)));
					 id = list.get(0).toString();
				}
				VotersInfoForMandalVO mandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(typeVar), new Long(id), publicationDateId, name, typeVar);
				if(reqType.equalsIgnoreCase("main") && !mandalVO.isDatapresent()){
						 VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
						 votersInfoForMandalVO.setDatapresent(false);
						 return votersInfoForMandalVO;
					 }
				return mandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForSelectedMandal() Method, Exception - "+e);
				return null;
			}
		}
			
		public VotersInfoForMandalVO getVotersBasicInfoForMandal(String type,Long id,Long publicationDateId,String reqType){
			String name = "";
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
			  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
			  Object[] reqName = assemblyLocalElectionBodyName.get(0);
			  name = reqName[0].toString()+" "+reqName[1].toString();
			}
			else{
				name = tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
			}
			VotersInfoForMandalVO votersInfoForMandalVO = getVotersBasicInfoForSelectedMandal("mandal",id.toString(),publicationDateId,name,"main");
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("2") && votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main")){
				//getting voters count for all panchayats in mandal
			    // getVotersCountForMultiplePanchayat(new Long(id.toString().substring(01)),publicationDateId,votersInfoForMandalVO);
			    // calculatePercentage(votersInfoForMandalVO);
				List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(01)));
				Map<Long,String> panchayatIds = new HashMap<Long,String>();
				for (Object[] panchayat : panchayatiesList){
					panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
				}
				if(panchayatIds.size() > 0)
					votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Panchayat"), panchayatIds, publicationDateId,"Panchayat"));
			}
			if(id.toString().substring(0,1).trim().equalsIgnoreCase("1") && votersInfoForMandalVO.isDatapresent() && reqType.equalsIgnoreCase("main")){
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(01)));
				List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
				
				Map<Long,String> wardIds = new HashMap<Long,String>();
				for (Object[] ward : wardsList){
					wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
				}
				if(wardIds.size() > 0)
					votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward"));
			}
			if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
				votersInfoForMandalVO.setSubLevelExists(true);
			return votersInfoForMandalVO;
		}
		
		
		public void getVotersBasicInfoForMultiplePanchayat(Long mandalId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
			try{
				List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(mandalId);
				if(panchayaties != null && panchayaties.size() > 0)
				{
					for (Object[] panchayat : panchayaties){
						votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForPanchayat((Long)panchayat[0],publicationDateId,"sub"));
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForMultiplePanchayat() Method, Exception - "+e);
			}
			
		}
		
		
		public VotersInfoForMandalVO getVotersBasicInfoForPanchayat(Long id,Long publicationDateId,String reqType ){
			try{
				
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"), id, publicationDateId, panchayatDAO.get(id).getPanchayatName(), "Panchayat");
				if(reqType.equalsIgnoreCase("main")){
					if(votersInfoForMandalVO.isDatapresent())
					{
					  //getVotersCountForMultipleBooths(id,publicationDateId,votersInfoForMandalVO);
					  //calculatePercentage(votersInfoForMandalVO);
						List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
						Map<Long,String> boothIds = new HashMap<Long,String>();
						for (Object[] booth : boothsList){
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						}
						if(boothIds.size() > 0)
							votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"booth"));
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public VotersInfoForMandalVO getVotersBasicInfoForWard(Long id,Long publicationDateId,String reqType){
           try{
				
				VotersInfoForMandalVO votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Ward"), id, publicationDateId, constituencyDAO.get(id).getName(), "Ward");
				if(reqType.equalsIgnoreCase("main")){
					if(votersInfoForMandalVO.isDatapresent())
					{
						List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
						Map<Long,String> boothIds = new HashMap<Long,String>();
						for (Object[] booth : boothsList){
							boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
						}
						if(boothIds.size() > 0)
							votersInfoForMandalVO.setVotersInfoForMandalVOList(getVotersDetailsByVoterMultipleReportLevelIds(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"booth"));
					}
					if(votersInfoForMandalVO != null && votersInfoForMandalVO.getVotersInfoForMandalVOList() != null && votersInfoForMandalVO.getVotersInfoForMandalVOList().size() > 0)
						votersInfoForMandalVO.setSubLevelExists(true);
					return votersInfoForMandalVO;
				}
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersBasicInfoForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public void getVotersBasicInfoForMultipleBooths(Long panchayatId,Long publicationDateId,VotersInfoForMandalVO votersInfoForMandalVO){
			List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId,publicationDateId);
		     for(Object[] booth : boothsList){
		    	 votersInfoForMandalVO.getVotersInfoForMandalVOList().add(getVotersCountForBooth("booth",(Long)booth[0],publicationDateId,"sub")); 
		     }
		}
		
		
		/*public VotersInfoForMandalVO getVotersBasicInfoForBooth(String type,Long id,Long publicationDateId,String reqType){
			VotersInfoForMandalVO votersInfoForBooth = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),reqType);
			if(votersInfoForBooth.isDatapresent() || reqType.equalsIgnoreCase("sub"))
				   return votersInfoForBooth;
			else{
				   VotersInfoForMandalVO votersInfoForMandalVO = new VotersInfoForMandalVO();
				   votersInfoForMandalVO.setDatapresent(false);
				   return votersInfoForMandalVO;
			   }
		}*/
		
	/* Voters Info */	
	 
	 /* Age wise Details */
		public List<VotersDetailsVO> getVoterAgeWiseDetails(Long constituencyId, Long mandalId,
				 Long panchayatId , Long boothId, Long publicationDateId, String type)
		 {
			 try{
				 if(type.equalsIgnoreCase("constituency"))
					 return getAgeWiseVoterDetails(type, constituencyId, publicationDateId);
				 else if(type.equalsIgnoreCase("mandal") || type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
					 return getAgeWiseVoterDetails(type, mandalId, publicationDateId);
				 else if(type.equalsIgnoreCase("panchayat"))
					 return getAgeWiseVoterDetails(type, panchayatId, publicationDateId);
				 else if(type.equalsIgnoreCase("booth"))
					 return getAgeWiseVoterDetails(type, boothId, publicationDateId);
				 else if(type.equalsIgnoreCase("ward"))
					 return getAgeWiseVoterDetails(type, panchayatId, publicationDateId);
				 else
				 return null;
			 }catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVoterAgeWiseDetails() Method, Exception - "+e);
				return null;
			}
		 }
	 
	 public List<VotersDetailsVO> getAgeWiseVoterDetails(String type, Long reportLevelValue, Long publicationDateId)
	 {
	 	List<VotersDetailsVO> votersDetailsVOList = null;
	 	try{
	 		List<VoterAgeInfo> voterAgeInfoList = new ArrayList<VoterAgeInfo>(0);
	 		
	 		if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
	 		{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(reportLevelValue);
					reportLevelValue = (Long) list.get(0);
					type = IConstants.LOCALELECTIONBODY;
	 		}
	 		 voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoByPublicationDateAndReportLevelId(getReportLevelId(type), reportLevelValue, publicationDateId);
	 		
	 		if(voterAgeInfoList != null && voterAgeInfoList.size() > 0)
	 		{
	 			votersDetailsVOList = new ArrayList<VotersDetailsVO>(0);
	 			for(VoterAgeInfo ageInfo : voterAgeInfoList)
	 			{
	 				VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
	 				if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(1l))
	 					votersDetailsVO.setAgeRange("18-25");
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(2l))
	 					votersDetailsVO.setAgeRange("26-35");
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(3l))
	 					votersDetailsVO.setAgeRange("36-45");
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(4l))
	 					votersDetailsVO.setAgeRange("46-60");
	 				else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().equals(5l))
	 					votersDetailsVO.setAgeRange("60-Above");
	 					votersDetailsVO.setTotalVoters(ageInfo.getTotalVoters());
	 					votersDetailsVO.setTotalVotersPercent(ageInfo.getTotalVotersPercentage() != null ? new Float(ageInfo.getTotalVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalMaleVotersPercent(ageInfo.getMaleVotersPercentage() != null ?new Float(ageInfo.getMaleVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalFemaleVoters(ageInfo.getFemaleVoters());
	 					votersDetailsVO.setTotalFemaleVotersPercent(ageInfo.getFemaleVotersPercentage() != null ?new Float(ageInfo.getFemaleVotersPercentage()):0.0f);
	 					votersDetailsVO.setTotalMaleVoters(ageInfo.getMaleVoters());
	 					
	 					votersDetailsVOList.add(votersDetailsVO);
	 					
	 			}
	 		}
	 		return votersDetailsVOList;
	 	}catch (Exception e) {
	 		e.printStackTrace();
	 		log.error("Exception Occured in getAgeWiseVoterDetails() Method, Exception - "+e);
	 		return votersDetailsVOList;
	 	}
	 }
	 
	 


	public void getDetailsOfVotersHasAgeBetween18To25(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("18-25"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("18-25");
					votersDetailsVO.setTotalMaleVotesFor18To25(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() :0l);
					votersDetailsVO.setMaleVotersPercentFor18To25(ageInfo.getMaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor18To25(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters():0l);
					votersDetailsVO.setFemaleVotersPercentFor18To25(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor18To25(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() :0l);
					votersDetailsVO.setVotersPercentFor18To25(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor18To25()-(votersDetailsVO.getTotalMaleVotesFor18To25()+votersDetailsVO.getTotalFemaleVotersFor18To25());
					votersDetailsVO.setTotalUnknownVotersFor18To25(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor18To25 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween18To25() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween26To35(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("26-35"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("26-35");
					votersDetailsVO.setTotalMaleVotersFor26To35(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters() : 0l);
					votersDetailsVO.setMaleVotersPercentFor26To35(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor26To35(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() : 0L);
					votersDetailsVO.setFemaleVotersPercentFor26To35(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor26To35(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);
					votersDetailsVO.setVotersPercentFor26To35(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor26To35()-(votersDetailsVO.getTotalMaleVotersFor26To35()+votersDetailsVO.getTotalFemaleVotersFor26To35());
					votersDetailsVO.setTotalUnknownVotersFor26To35(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor26To35 ()) : 0.0f);
					
					
				}
				
			}
			
			
			 
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween26To35() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetailsOfVotersHasAgeBetween36To45(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("36-45"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("36-45");
					votersDetailsVO.setTotalMaleVotersFor36To45(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() : 0l);
					votersDetailsVO.setMaleVotersPercentFor36To45(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor36To45(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters() : 0l);
					votersDetailsVO.setFemaleVotersPercentFor36To45(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) :"0.00");
					votersDetailsVO.setTotalVotersFor36To45(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);					 
					votersDetailsVO.setVotersPercentFor36To45(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersFor36To45()-(votersDetailsVO.getTotalMaleVotersFor36To45()+votersDetailsVO.getTotalFemaleVotersFor36To45());
					votersDetailsVO.setTotalUnknownVotersFor36To45(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor36To45 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	public void getDetailsOfVotersHasAgeBetween46To60(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("46-60"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("46-60");
					votersDetailsVO.setTotalMaleVotersFor46To60(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters():0l);
					votersDetailsVO.setMaleVotersPercentFor46To60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalFemaleVotersFor46To60(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() :0l);						
					votersDetailsVO.setFemaleVotersPercentFor46To60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
					votersDetailsVO.setTotalVotersFor46To60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
				    votersDetailsVO.setVotersPercentFor46To60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
				    Long unKnowVoters = votersDetailsVO.getTotalVotersFor46To60()-(votersDetailsVO.getTotalMaleVotersFor46To60()+votersDetailsVO.getTotalFemaleVotersFor46To60());
					votersDetailsVO.setTotalUnknownVotersFor46To60(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersFor46To60 ()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	
	public void getDetaOfVotersHasAgeAbove60(Long reportLevelId, Long reportLevelValue, Long publicationDateId,VotersDetailsVO votersDetailsVO)
	{
		try{
			
			List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInSpecifiedRangeByAgeRangeId(reportLevelId, reportLevelValue, publicationDateId, getVoterAgeRangeId("60-Above"));
			if(ageInfoList != null && ageInfoList.size() > 0)
			{
				for(VoterAgeInfo ageInfo : ageInfoList)
				{
					votersDetailsVO.setAgeRange("60-Above");
					votersDetailsVO.setTotalMaleVotersForAbove60(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters():0l);
					votersDetailsVO.setMaleVotersPercentForAbove60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())): "0.00");
					votersDetailsVO.setTotalFemaleVotersForAbove60(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters():0l);
					votersDetailsVO.setFemaleVotersPercentForAbove60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())):"0.00");
					votersDetailsVO.setTotalVotersForAbove60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
					votersDetailsVO.setVotersPercentForAbove60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
					Long unKnowVoters = votersDetailsVO.getTotalVotersForAbove60()-(votersDetailsVO.getTotalMaleVotersForAbove60()+votersDetailsVO.getTotalFemaleVotersForAbove60());
					votersDetailsVO.setTotalUnknownVotersForAbove60(unKnowVoters != null ?unKnowVoters : 0l);
					//votersDetailsVO.setTotalUnknownVotersPercent(unKnowVoters != null ? (float) (unKnowVoters.floatValue() *100f / votersDetailsVO.getTotalVotersForAbove60()) : 0.0f);
					
				}
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getDetailsOfVotersHasAgeBetween36To45() Method, Exception - "+e);
			
		}
	}
	
	public List<VotersDetailsVO> getAllAgesWiseVotersDetails(Long reportLevelId, Map<Long,String> reportLevelValues, Long publicationDateId,String type){
		Map<Long,VotersDetailsVO> ageWiseMap = new HashMap<Long,VotersDetailsVO>();
		Long age18to25 = getVoterAgeRangeId("18-25");
		Long age26to35 = getVoterAgeRangeId("26-35");
		Long age36to45 = getVoterAgeRangeId("36-45");
		Long age46to60 = getVoterAgeRangeId("46-60");
		Long above60 = getVoterAgeRangeId("60-Above");
		List<VoterAgeInfo> ageInfoList = voterAgeInfoDAO.getAgewiseVoterDetailsInAllRange(reportLevelId, reportLevelValues.keySet(), publicationDateId);
		if(ageInfoList != null && ageInfoList.size() > 0){
			VotersDetailsVO  votersDetailsVO = new VotersDetailsVO();
			  for(Long key : reportLevelValues.keySet()){
				  votersDetailsVO = new VotersDetailsVO();
				  votersDetailsVO.setTotalVoters(0l);
				  if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward")){
				      votersDetailsVO.setBoothName(reportLevelValues.get(key));
				      votersDetailsVO.setTehsilName(reportLevelValues.get(key));
				  }else if(type.equalsIgnoreCase("panchayat")){
					  votersDetailsVO.setPanchayatname(reportLevelValues.get(key));
				  }else if(type.equalsIgnoreCase("mandal") || type.equalsIgnoreCase("localElec" )|| type.equalsIgnoreCase("ward")){
					  votersDetailsVO.setTehsilName(reportLevelValues.get(key));
				  }
				  ageWiseMap.put(key, votersDetailsVO);
			  }
			  
			  for(VoterAgeInfo ageInfo:ageInfoList){
				  votersDetailsVO = ageWiseMap.get(ageInfo.getReportLevelValue());
				  if(votersDetailsVO != null){
					  if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age18to25.longValue()){
						     votersDetailsVO.setAgeRange("18-25");
							votersDetailsVO.setTotalMaleVotesFor18To25(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() :0l);
							votersDetailsVO.setMaleVotersPercentFor18To25(ageInfo.getMaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor18To25(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters():0l);
							votersDetailsVO.setFemaleVotersPercentFor18To25(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor18To25(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() :0l);
							votersDetailsVO.setVotersPercentFor18To25(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor18To25()-(votersDetailsVO.getTotalMaleVotesFor18To25()+votersDetailsVO.getTotalFemaleVotersFor18To25());
							votersDetailsVO.setTotalUnknownVotersFor18To25(unKnowVoters != null ?unKnowVoters : 0l); 
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age26to35.longValue()){
						  votersDetailsVO.setAgeRange("26-35");
							votersDetailsVO.setTotalMaleVotersFor26To35(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters() : 0l);
							votersDetailsVO.setMaleVotersPercentFor26To35(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor26To35(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() : 0L);
							votersDetailsVO.setFemaleVotersPercentFor26To35(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor26To35(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);
							votersDetailsVO.setVotersPercentFor26To35(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor26To35()-(votersDetailsVO.getTotalMaleVotersFor26To35()+votersDetailsVO.getTotalFemaleVotersFor26To35());
							votersDetailsVO.setTotalUnknownVotersFor26To35(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age36to45.longValue()){
						    votersDetailsVO.setAgeRange("36-45");
							votersDetailsVO.setTotalMaleVotersFor36To45(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters() : 0l);
							votersDetailsVO.setMaleVotersPercentFor36To45(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor36To45(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters() : 0l);
							votersDetailsVO.setFemaleVotersPercentFor36To45(ageInfo.getFemaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) :"0.00");
							votersDetailsVO.setTotalVotersFor36To45(ageInfo.getTotalVoters() != null ?ageInfo.getTotalVoters() : 0l);					 
							votersDetailsVO.setVotersPercentFor36To45(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersFor36To45()-(votersDetailsVO.getTotalMaleVotersFor36To45()+votersDetailsVO.getTotalFemaleVotersFor36To45());
							votersDetailsVO.setTotalUnknownVotersFor36To45(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null) 
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == age46to60.longValue()){
						    votersDetailsVO.setAgeRange("46-60");
							votersDetailsVO.setTotalMaleVotersFor46To60(ageInfo.getMaleVoters() != null ?ageInfo.getMaleVoters():0l);
							votersDetailsVO.setMaleVotersPercentFor46To60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalFemaleVotersFor46To60(ageInfo.getFemaleVoters() != null ? ageInfo.getFemaleVoters() :0l);						
							votersDetailsVO.setFemaleVotersPercentFor46To60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())) : "0.00");
							votersDetailsVO.setTotalVotersFor46To60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
						    votersDetailsVO.setVotersPercentFor46To60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
						    Long unKnowVoters = votersDetailsVO.getTotalVotersFor46To60()-(votersDetailsVO.getTotalMaleVotersFor46To60()+votersDetailsVO.getTotalFemaleVotersFor46To60());
							votersDetailsVO.setTotalUnknownVotersFor46To60(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }else if(ageInfo.getVoterAgeRange().getVoterAgeRangeId().longValue() == above60.longValue()){
						    votersDetailsVO.setAgeRange("60-Above");
							votersDetailsVO.setTotalMaleVotersForAbove60(ageInfo.getMaleVoters() != null ? ageInfo.getMaleVoters():0l);
							votersDetailsVO.setMaleVotersPercentForAbove60(ageInfo.getMaleVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getMaleVotersPercentage())): "0.00");
							votersDetailsVO.setTotalFemaleVotersForAbove60(ageInfo.getFemaleVoters() != null ?ageInfo.getFemaleVoters():0l);
							votersDetailsVO.setFemaleVotersPercentForAbove60(ageInfo.getFemaleVotersPercentage() != null ?roundTo2DigitsFloatValue(new Float(ageInfo.getFemaleVotersPercentage())):"0.00");
							votersDetailsVO.setTotalVotersForAbove60(ageInfo.getTotalVoters() != null ? ageInfo.getTotalVoters() : 0l);
							votersDetailsVO.setVotersPercentForAbove60(ageInfo.getTotalVotersPercentage() != null ? roundTo2DigitsFloatValue(new Float(ageInfo.getTotalVotersPercentage())) : "0.00");
							Long unKnowVoters = votersDetailsVO.getTotalVotersForAbove60()-(votersDetailsVO.getTotalMaleVotersForAbove60()+votersDetailsVO.getTotalFemaleVotersForAbove60());
							votersDetailsVO.setTotalUnknownVotersForAbove60(unKnowVoters != null ?unKnowVoters : 0l);
							if(ageInfo.getTotalVoters() != null)
							 votersDetailsVO.setTotalVoters(votersDetailsVO.getTotalVoters()+ageInfo.getTotalVoters());
					  }
				  }
			  }
		}
		return new ArrayList<VotersDetailsVO>(ageWiseMap.values());
	}
	
	public List<VotersDetailsVO> getAgewiseVotersDetForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId, String type){
		List<VotersDetailsVO> votersDetailsVOList = new ArrayList<VotersDetailsVO>();
		try{
			List<Object[]> mandalsList = tehsilDAO.findAllTehsilsByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
			if(mandalsList != null && mandalsList.size() > 0)
			{
				
				/*votersDetailsVOList = new ArrayList<VotersDetailsVO>(0);
				for(Object[] params : tehsilIds)
				{
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId(IConstants.MANDAL), (Long)params[0], publicationDateId, votersDetailsVO);
					votersDetailsVO.setTehsilName(params[1].toString());
					
					Long totalVoters = votersDetailsVO.getTotalVotersFor18To25()+ votersDetailsVO.getTotalVotersFor26To35()+ votersDetailsVO.getTotalVotersFor36To45()+ votersDetailsVO.getTotalVotersFor46To60()+ votersDetailsVO.getTotalVotersForAbove60();
					votersDetailsVO.setTotalVoters(totalVoters);
					
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					
					votersDetailsVOList.add(votersDetailsVO);
				}*/
				
				Map<Long,String> mandalIds = new HashMap<Long,String>();
				
				for (Object[] mandal : mandalsList){
					mandalIds.put((Long)mandal[0],mandal[1]!=null?mandal[1].toString():"");
				}
				  if(mandalIds.size() > 0){
					  votersDetailsVOList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.MANDAL), mandalIds, publicationDateId,"Mandal");
				  }
					
			}
			List<Object[]> localBodyList = tehsilDAO.findAllLocalElecBodyByConstituencyIdAndPublicationDateId(constituencyId,publicationDateId);
			Constituency constituency = constituencyDAO.get(constituencyId);
			if(localBodyList != null && localBodyList.size() > 0)
			{
			  Map<Long,String> localBodyIds = new HashMap<Long,String>();
			  List<Long> urban = new ArrayList<Long>();
			  for (Object[] localBody : localBodyList){
				  localBodyIds.put((Long)localBody[0],localBody[1]!=null?localBody[1].toString()+" "+localBody[2].toString():"");
				  if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
						 urban.add((Long)localBody[0]);
			  }
				if(localBodyIds.size() > 0)
				{
					if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId);
						
						Map<Long,String> wardIds = new HashMap<Long,String>();
						for (Object[] ward : wardsList){
							wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
						}
						if(wardIds.size() > 0)
							votersDetailsVOList.addAll(getAllAgesWiseVotersDetails(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward"));
						
					}else{
						List<VotersDetailsVO> localElec = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.LOCALELECTIONBODY), localBodyIds, publicationDateId,"localElec");
						if(localElec != null && localElec.size() > 0)
							votersDetailsVOList.addAll(localElec);
					}
				}
			}
			return votersDetailsVOList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForTehsilsByConstituencyId() Method, Exception - "+e);
			return votersDetailsVOList;
		}
	}
			
	public List<VotersDetailsVO> getAgewiseVotersDetaForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId, String type){
		  try{
			 List<VotersDetailsVO> votersDetailsVOList = new ArrayList<VotersDetailsVO>();
			 List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
			 if(panchayatiesList != null && panchayatiesList.size() >0)
			 {
				/* votersDetailsVOList = new ArrayList<VotersDetailsVO>();
				for(Object[] params : panchayatis)
				{
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId("PANCHAYAT"), (Long)params[0], publicationDateId, votersDetailsVO);
					
					votersDetailsVO.setPanchayatname(params[1].toString());
					
						Long totalVoters = votersDetailsVO.getTotalVotersFor18To25()
							+ votersDetailsVO.getTotalVotersFor26To35()
							+ votersDetailsVO.getTotalVotersFor36To45()
							+ votersDetailsVO.getTotalVotersFor46To60()
							+ votersDetailsVO.getTotalVotersForAbove60();
					
					votersDetailsVO.setTotalVoters(totalVoters);
					
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					
					votersDetailsVOList.add(votersDetailsVO);
				}*/
				 Map<Long,String> panchayatIds = new HashMap<Long,String>();
					for (Object[] panchayat : panchayatiesList){
						panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
					}
					if(panchayatIds.size() > 0)
						votersDetailsVOList = getAllAgesWiseVotersDetails(getReportLevelId("Panchayat"), panchayatIds, publicationDateId,"Panchayat");
				
			 }
			 return votersDetailsVOList;
				  
			 }catch (Exception e) {
				e.printStackTrace();
				log.error(" Exception Occured in getAgewiseVotersDetaForPanchayatisByTehsilId() Method, Exception - "+e);
				return null;
				}
			}
		
	
	public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByPanchayatId(Long panchayatId,Long publicationDateId, String type){
		
		try{
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(panchayatId, publicationDateId);
			if(boothsList != null)
			{
				/*boothVotersList = new ArrayList<VotersDetailsVO>();
				for(Object[] params:booths){
					VotersDetailsVO votersDetailsVO = new VotersDetailsVO();
					getDetailsOfVotersHasAgeBetween18To25(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween26To35(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween36To45(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetailsOfVotersHasAgeBetween46To60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					getDetaOfVotersHasAgeAbove60(getReportLevelId(IConstants.BOOTH), (Long)params[0], publicationDateId, votersDetailsVO);
					Long totalVoters = 0l;
					votersDetailsVO.setBoothName(params[1].toString());
					if(votersDetailsVO.getTotalVotersFor18To25() != null && votersDetailsVO.getTotalVotersFor26To35()  != null && votersDetailsVO.getTotalVotersFor36To45() != null && votersDetailsVO.getTotalVotersFor46To60() != null && votersDetailsVO.getTotalVotersForAbove60() != null)
					{	 totalVoters = votersDetailsVO.getTotalVotersFor18To25()
						+ votersDetailsVO.getTotalVotersFor26To35()
						+ votersDetailsVO.getTotalVotersFor36To45()
						+ votersDetailsVO.getTotalVotersFor46To60()
						+ votersDetailsVO.getTotalVotersForAbove60();
				
					votersDetailsVO.setTotalVoters(totalVoters);
					}else{
						return null;
					}
					votersDetailsVO.setVotersPercentFor18To25(votersDetailsVO.getTotalVotersFor18To25() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor18To25()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentFor26To35(votersDetailsVO.getTotalVotersFor26To35() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor26To35()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor36To45(votersDetailsVO.getTotalVotersFor36To45() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor36To45()*100f/totalVoters):"0.00");
					votersDetailsVO.setVotersPercentFor46To60(votersDetailsVO.getTotalVotersFor46To60() != 0 ? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersFor46To60()*100f/totalVoters) :"0.00");
					votersDetailsVO.setVotersPercentForAbove60(votersDetailsVO.getTotalVotersForAbove60() != 0? roundTo2DigitsFloatValue((float)votersDetailsVO.getTotalVotersForAbove60()*100f/totalVoters):"0.00");
					boothVotersList.add(votersDetailsVO);
				}*/
				Map<Long,String> boothIds = new HashMap<Long,String>();
				for (Object[] booth : boothsList){
					boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
				}
				if(boothIds.size() > 0)
					boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.BOOTH), boothIds, publicationDateId,"Booth");
			
			}
		  return boothVotersList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForBoothsByPanchayatId() Method, Exception - "+e);
			return null;
		}
	}
public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByWardId(Long id,Long publicationDateId){
		
		try{
			List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
			List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
			if(boothsList != null)
			{
				Map<Long,String> boothIds = new HashMap<Long,String>();
				for (Object[] booth : boothsList){
					boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
				}
				if(boothIds.size() > 0)
					boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId(IConstants.BOOTH), boothIds, publicationDateId,"Booth");
			
			}
		  return boothVotersList;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgewiseVotersDetForBoothsByPanchayatId() Method, Exception - "+e);
			return null;
		}
	}	
		public List<VotersDetailsVO> getAgewiseVotersDetForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId, String type){
			
			try{
				List<VotersDetailsVO> boothVotersList = new ArrayList<VotersDetailsVO>();
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(localElectionBodyId);
				List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
				if(wardsList != null && wardsList.size() >0)
				{
					
					Map<Long,String> wardIds = new HashMap<Long,String>();
					for (Object[] ward : wardsList){
						wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
					}
					if(wardIds.size() > 0)
						boothVotersList = getAllAgesWiseVotersDetails(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward");
				
				}
				return boothVotersList;
				}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getAgewiseVotersDetForBoothsByLocalElectionBodyId() Method, Exception - "+e);
				return null;
			}
		}
		
	/* Age wise Details */	
		
	/* Important Family */
		
		public ImportantFamiliesInfoVo getImportantFamilyInfo(Long reportLevelId, Long reportLevelValue, Long publicationDateId, String exeType)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();;
			try{
				List<VoterFamilyInfo> familyInfoList = voterFamilyInfoDAO.getVoterFamilyDetails(reportLevelId, reportLevelValue, publicationDateId);
				if(familyInfoList == null || familyInfoList.size() == 0)
				{
					importantFamiliesInfoVo.setDataPresent(false);
					return importantFamiliesInfoVo;
				}
				if(familyInfoList != null && familyInfoList.size() > 0)
				{
					Long totalFamilies = 0l;
					for(VoterFamilyInfo familyInfo : familyInfoList)
					{
						
						if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(1l))
						{
							importantFamiliesInfoVo.setBelow3(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies() : 0l);
							importantFamiliesInfoVo.setBelow3perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies += importantFamiliesInfoVo.getBelow3();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(2l))
						{
							importantFamiliesInfoVo.setBetwn4to6(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn4to6perc(familyInfo.getFamiliesPercentage()!= null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getBetwn4to6();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(3l))
						{
							importantFamiliesInfoVo.setBetwn7to10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn7to10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getBetwn7to10();
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(4l))
						{
							importantFamiliesInfoVo.setAbove10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setAbove10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							totalFamilies +=importantFamiliesInfoVo.getAbove10();
							importantFamiliesInfoVo.setDataPresent(true);
						}
					}
					
					importantFamiliesInfoVo.setTotalFamalies(totalFamilies);
				}
					
				
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyInfo() Method, Exception -"+e);
				return importantFamiliesInfoVo;
			} 
		}
		
		public List<ImportantFamiliesInfoVo> getImportantFamilyInfoForMultiple(Long reportLevelId, Map<Long,String> reportLevelValues, Long publicationDateId,String type)
		{
			List<ImportantFamiliesInfoVo> importantFamiliesInfoVoList = new ArrayList<ImportantFamiliesInfoVo>();
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				List<VoterFamilyInfo> familyInfoList = voterFamilyInfoDAO.getMultipleVoterFamilyDetails(reportLevelId, reportLevelValues.keySet(), publicationDateId);
				
				if(familyInfoList == null || familyInfoList.size() == 0)
				{
					//importantFamiliesInfoVo.setDataPresent(false);
					return importantFamiliesInfoVoList;
				}
				List<VotersInfoForMandalVO> votersInfoList = getVotersDetailsByVoterMultipleReportLevelIds(reportLevelId,reportLevelValues, publicationDateId,"");
				Map<Long,ImportantFamiliesInfoVo> importantFamiliesInfoVoMap = new HashMap<Long,ImportantFamiliesInfoVo>();
				 for(Long key : reportLevelValues.keySet()){ 
					 for(VotersInfoForMandalVO votersInfo : votersInfoList){
						  if(votersInfo.getId().longValue() == key.longValue()){
							  importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
							  importantFamiliesInfoVo.setTotalMaleVoters(votersInfo.getTotalMaleVoters());
							  importantFamiliesInfoVo.setTotalFemaleVoters(votersInfo.getTotalFemaleVoters());
							  importantFamiliesInfoVo.setUnKnowVoters(votersInfo.getUnKnowVoters());
							  importantFamiliesInfoVo.setTotalFamalies(0l);
							  importantFamiliesInfoVo.setType(type);
							  importantFamiliesInfoVo.setDataPresent(false);
							  if(votersInfo.getTotalVoters() != null){
								  try{
								   importantFamiliesInfoVo.setTotalVoters(new Long(votersInfo.getTotalVoters()));
								  }catch(Exception e){
									  importantFamiliesInfoVo.setTotalVoters(0l);
								  }
							  }else{
								  importantFamiliesInfoVo.setTotalVoters(0l);
							  }
							  importantFamiliesInfoVo.setName(reportLevelValues.get(key).toString());
							  importantFamiliesInfoVoMap.put(key,importantFamiliesInfoVo);
						  }
					 }
					 
					 
				 }
				if(familyInfoList != null && familyInfoList.size() > 0)
				{
					for(VoterFamilyInfo familyInfo : familyInfoList)
					{
						importantFamiliesInfoVo = importantFamiliesInfoVoMap.get(familyInfo.getReportLevelValue());
						if(importantFamiliesInfoVo == null)
							continue;
						if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(1l))
						{
							importantFamiliesInfoVo.setBelow3(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies() : 0l);
							importantFamiliesInfoVo.setBelow3perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(2l))
						{
							importantFamiliesInfoVo.setBetwn4to6(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn4to6perc(familyInfo.getFamiliesPercentage()!= null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(3l))
						{
							importantFamiliesInfoVo.setBetwn7to10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setBetwn7to10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
						else if(familyInfo.getVoterFamilyRange().getVoterFamilyRangeId().equals(4l))
						{
							importantFamiliesInfoVo.setAbove10(familyInfo.getTotalFamilies() != null ? familyInfo.getTotalFamilies():0l);
							importantFamiliesInfoVo.setAbove10perc(familyInfo.getFamiliesPercentage() != null ?familyInfo.getFamiliesPercentage(): 0.00);
							if(importantFamiliesInfoVo.getTotalFamalies() != null && familyInfo.getTotalFamilies() != null)
							importantFamiliesInfoVo.setTotalFamalies(importantFamiliesInfoVo.getTotalFamalies()+familyInfo.getTotalFamilies());
							importantFamiliesInfoVo.setDataPresent(true);
						}
					}
					
					
				}
					
				
				return new ArrayList<ImportantFamiliesInfoVo>(importantFamiliesInfoVoMap.values());
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyInfoForMultiple() Method, Exception -",e);
				return importantFamiliesInfoVoList;
			} 
		}
		public ImportantFamiliesInfoVo getImportantFamilyDetailsForConstituency(String type, Long id, Long publicationDateId)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
				importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.CONSTITUENCY), id, publicationDateId,"main");
				Constituency constituency = constituencyDAO.get(id);
				importantFamiliesInfoVo.setType("Constituency");
				importantFamiliesInfoVo.setName(constituency.getName());
				importantFamiliesInfoVo.setTotalVoters(boothPublicationVoterDAO.getTotalVotersCount(id,publicationDateId,"constituency"));
				VotersInfoForMandalVO votersInfoForConstituency = getVotersBasicInfoForConstituency(type, id, publicationDateId,"sub");
				importantFamiliesInfoVo.setTotalVoters(votersInfoForConstituency.getTotVoters() != null ? votersInfoForConstituency.getTotVoters().longValue() : 0l);
				importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForConstituency.getTotalMaleVoters());
				importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForConstituency.getTotalFemaleVoters());
				importantFamiliesInfoVo.setUnKnowVoters(votersInfoForConstituency.getUnKnowVoters());
				if(importantFamiliesInfoVo.isDataPresent())
				{
					List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(id,IConstants.PRESENT_YEAR, null);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<Long> urban = new ArrayList<Long>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							 localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
							 if("URBAN".equalsIgnoreCase(constituency.getAreaType()))
								 urban.add(new Long(list.get(0).toString()));
						}
						
					}
					if(mandalIds.size() > 0)
						
						importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.MANDAL),mandalIds, publicationDateId,"Mandal"));
					if(localBodyIds.size() > 0)
					{
						if("URBAN".equalsIgnoreCase(constituency.getAreaType()) && urban != null && urban.size() == 1){
							List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId(urban.get(0),publicationDateId);
							
							Map<Long,String> wardIds = new HashMap<Long,String>();
							for (Object[] ward : wardsList){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							if(wardIds.size() > 0)
								importantFamiliesInfoVo.getSubList().addAll(getImportantFamilyInfoForMultiple(getReportLevelId("Ward"), wardIds, publicationDateId,"Ward"));
							
						}else{
							List<ImportantFamiliesInfoVo> localElec = getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.LOCALELECTIONBODY),localBodyIds, publicationDateId,"Mandal");
							if(localElec != null && localElec.size() > 0)
								importantFamiliesInfoVo.getSubList().addAll(localElec);
						}
					}
				}
				
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImportantFamilyDetailsForConstituency() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForMandal(String type,Long id,Long publicationDateId, String exeType)
		{
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				if(id.toString().substring(0, 1).trim().equalsIgnoreCase("2"))
				{
					
					
					importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
					importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.MANDAL), new Long(id.toString().trim().substring(1)), publicationDateId,exeType);
					importantFamiliesInfoVo.setType("Mandal/Tehsil");
					importantFamiliesInfoVo.setName(tehsilDAO.get(new Long(id.toString().substring(1))).getTehsilName());
					VotersInfoForMandalVO votersInfoForMandal = getVotersBasicInfoForMandal("mandal", id, publicationDateId,"sub");
					importantFamiliesInfoVo.setTotalVoters(votersInfoForMandal.getTotVoters() != null ? votersInfoForMandal.getTotVoters().longValue():0l);
					importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
					importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
					importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
					
					if(exeType.equalsIgnoreCase("main")&& importantFamiliesInfoVo.isDataPresent())
					{
						List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(1).trim()));
						Map<Long,String> panchayatIds = new HashMap<Long,String>();
						for (Object[] panchayat : panchayaties){
							panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
						}
						if(panchayatIds.size() > 0)
							importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId("Panchayat"),panchayatIds, publicationDateId,"Panchayat"));

					}
					return importantFamiliesInfoVo;
				}
				else{
					
					importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
					List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().trim().substring(1));
					Object[] reqName = assemblyLocalElectionBodyName.get(0);
					importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.LOCALELECTIONBODY), (Long)list.get(0), publicationDateId,exeType);
				    String name = reqName[0].toString()+" "+reqName[1].toString();
					importantFamiliesInfoVo.setName(name);
					importantFamiliesInfoVo.setType("Muncipality/Corporation");
					VotersInfoForMandalVO votersInfoForMandal = getVotersBasicInfoForMandal("mandal", id, publicationDateId,"sub");
					importantFamiliesInfoVo.setTotalVoters(votersInfoForMandal.getTotVoters()!= null ? votersInfoForMandal.getTotVoters().longValue():0l);
					importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForMandal.getTotalMaleVoters());
					importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForMandal.getTotalFemaleVoters());
					importantFamiliesInfoVo.setUnKnowVoters(votersInfoForMandal.getUnKnowVoters());
					
					if(exeType.equalsIgnoreCase("main")&& importantFamiliesInfoVo.isDataPresent())
					{
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId);
					
						Map<Long,String> wardIds = new HashMap<Long,String>();
						for (Object[] ward : wardsList){
							wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
						}
						if(wardIds.size() > 0)
							importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId("Ward"),wardIds, publicationDateId,"Ward"));

					}
					return importantFamiliesInfoVo;
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForMandal() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		
		public ImportantFamiliesInfoVo getImpFamiliesForPanchayat(Long id,Long publicationDateId,String reqType,String exeType){
			try{
				
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId("PANCHAYAT"), id, publicationDateId, exeType);
			importantFamiliesInfoVo.setType("Panchayat");
			importantFamiliesInfoVo.setName(panchayatDAO.get(id).getPanchayatName());
			VotersInfoForMandalVO VotersInfoForPanchayat = getVotersBasicInfoForPanchayat(id, publicationDateId, "sub");
			importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
			importantFamiliesInfoVo.setTotalVoters(VotersInfoForPanchayat.getTotVoters() != null?VotersInfoForPanchayat.getTotVoters().longValue():0l);
			
			 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
				 /*List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
			     for(Object[] booth : boothsList){
			    	 importantFamiliesInfoVo.getSubList().add(getImpFamiliesForBooth("booth",(Long)booth[0],publicationDateId,"sub"));
			     }*/
			     
			     List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0)
					importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"Booth"));
			 }
			 return importantFamiliesInfoVo;
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForWard(Long id,Long publicationDateId,String exeType){
			try{
				
			ImportantFamiliesInfoVo importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
			importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId("Ward"), id, publicationDateId, exeType);
			importantFamiliesInfoVo.setType("Ward");
			importantFamiliesInfoVo.setName(constituencyDAO.get(id).getName());
			VotersInfoForMandalVO VotersInfoForPanchayat = getVotersBasicInfoForWard(id, publicationDateId, "sub");
			importantFamiliesInfoVo.setTotalMaleVoters(VotersInfoForPanchayat.getTotalMaleVoters());
			importantFamiliesInfoVo.setTotalFemaleVoters(VotersInfoForPanchayat.getTotalFemaleVoters());
			importantFamiliesInfoVo.setUnKnowVoters(VotersInfoForPanchayat.getUnKnowVoters());
			importantFamiliesInfoVo.setTotalVoters(VotersInfoForPanchayat.getTotVoters() != null?VotersInfoForPanchayat.getTotVoters().longValue():0l);
			
			 if(exeType.equalsIgnoreCase("main")  && importantFamiliesInfoVo.isDataPresent()){
			     List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0)
					importantFamiliesInfoVo.setSubList(getImportantFamilyInfoForMultiple(getReportLevelId(IConstants.BOOTH),boothIds, publicationDateId,"Booth"));
			 }
			 return importantFamiliesInfoVo;
			}
			catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForPanchayat() Method, Exception - "+e);
				return null;
			}
		}
		
		public ImportantFamiliesInfoVo getImpFamiliesForBooth(String type,Long id,Long publicationDateId,String exeType){
			ImportantFamiliesInfoVo importantFamiliesInfoVo = null;
			try{
				importantFamiliesInfoVo = new ImportantFamiliesInfoVo();
				importantFamiliesInfoVo = getImportantFamilyInfo(getReportLevelId(IConstants.BOOTH), id, publicationDateId, exeType);
				importantFamiliesInfoVo.setType("Booth");
				importantFamiliesInfoVo.setName(boothDAO.get(id).getPartNo());
				VotersInfoForMandalVO votersInfoForBooth = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH), id, publicationDateId,"booth-"+boothDAO.get(id).getPartNo(),"main");
				importantFamiliesInfoVo.setTotalMaleVoters(votersInfoForBooth.getTotalMaleVoters());
				importantFamiliesInfoVo.setTotalFemaleVoters(votersInfoForBooth.getTotalFemaleVoters());
				importantFamiliesInfoVo.setUnKnowVoters(votersInfoForBooth.getUnKnowVoters());
				importantFamiliesInfoVo.setTotalVoters(votersInfoForBooth.getTotVoters() != null ?votersInfoForBooth.getTotVoters().longValue():0l);
				return importantFamiliesInfoVo;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getImpFamiliesForBooth() Method, Exception - "+e);
				return importantFamiliesInfoVo;
			}
		}
		
		
	/* Important Family */	
		
		
		
		
public List<VotersInfoForMandalVO> getPreviousVotersCountDetailsForAllLevels(
				 Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type) {
			
			log.debug("Entered into the getVotersCountDetailsForAllLevels service method");	
			
			List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			
			try{
				List<VoterVO> previousDetailsList = null;
				
				/*List<VoterVO> previousDetailsList = 
						getAllElectionAndPublicationsForConstituencyId(constituencyId);*/
				
				if(type.equalsIgnoreCase("constituency"))
				  previousDetailsList = getAllElectionAndPublications(constituencyId , type,constituencyId);
				else if(type.equalsIgnoreCase("mandal"))
					  previousDetailsList = getAllElectionAndPublications(mandalId , type,constituencyId);
				else if(type.equalsIgnoreCase("panchayat"))
					  previousDetailsList = getAllElectionAndPublications(panchayatId , type , constituencyId);
				else if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward"))
					  previousDetailsList = getAllElectionAndPublications(boothId , type,constituencyId);
				
				for(VoterVO voterVO:previousDetailsList){
					
					VotersInfoForMandalVO votersInfoForMandalVO = null;
					
					if(voterVO.getType().equalsIgnoreCase("Election"))
						votersInfoForMandalVO = getVotersDetailsByElectionBasis(
								constituencyId,mandalId, panchayatId, boothId , type,voterVO);
				    else if(voterVO.getType().equalsIgnoreCase("Publication")){
				    	votersInfoForMandalVO = getVotersDetailsByPublicationBasis(
				   			  constituencyId, mandalId,  panchayatId,  boothId , type,voterVO);
				    }
					if(votersInfoForMandalVOList.size() > 0){
						
						VotersInfoForMandalVO previousResults = votersInfoForMandalVOList.get(votersInfoForMandalVOList.size()-1);
						VotersInfoForMandalVO currentResult = votersInfoForMandalVO;
						
						if(currentResult.getTotalMaleVoters() != null){							
							votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(currentResult.getTotalMaleVoters()) - Long.parseLong(previousResults.getTotalMaleVoters()));
						}else{
							if(previousResults.getTotalMaleVoters() != null)
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalMaleVoters()));
							else
							 votersInfoForMandalVO.setMaleVotersDiff(Long.parseLong(previousResults.getTotalMaleVoters()));
                        }
						
						if(currentResult.getTotalFemaleVoters() != null){							
							votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(currentResult.getTotalFemaleVoters()) - Long.parseLong(previousResults.getTotalFemaleVoters()));
						}else{
							
							if(previousResults.getTotalFemaleVoters() != null)
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong("0") - Long.parseLong(previousResults.getTotalFemaleVoters()));
							else
							  votersInfoForMandalVO.setFemaleVotersDiff(Long.parseLong(previousResults.getTotalFemaleVoters()));
								
                        }
						
						if(votersInfoForMandalVO.getTotVoters() != null){
							votersInfoForMandalVO.setTotalVotersDiff(currentResult.getTotVoters().longValue() - previousResults.getTotVoters().longValue());
						}else{
							if(previousResults.getTotVoters() != null)
							  votersInfoForMandalVO.setTotalVotersDiff(Long.parseLong("0") - previousResults.getTotVoters().longValue());
							else
							  votersInfoForMandalVO.setTotalVotersDiff(previousResults.getTotVoters().longValue());


						}
					}
					if(votersInfoForMandalVO.isDatapresent())				
					  votersInfoForMandalVOList.add(votersInfoForMandalVO);
					
				}
				
				
				
			}catch(Exception e){
				log.error("Exception raised in getVotersCountDetailsForAllLevels service method");
				e.printStackTrace();
				
			}
			
			return votersInfoForMandalVOList;
		}

    public List<VoterVO> getAllElectionAndPublications(Long id , String type , Long constituencyId){
    	log.debug("Entered into the getAllElectionAndPublications service method");
    	List<VoterVO> previousDetailsList = new ArrayList<VoterVO>();
    	
    	
    	try{
    		List<Object[]> list = null;
    		String type2 = null;
    		
    		if(type.equalsIgnoreCase("constituency")){
    			list  = constituencyElectionDAO.findAllElectionsHappendInAConstituency(id);
    			
    		}else if(type.equalsIgnoreCase("mandal")){
    			if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
    			{
    				id = new Long(id.toString().trim().substring(1));
    				list = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(id);
    				type2 = IConstants.RURAL;
    			}
    			else if(id.toString().trim().substring(0, 1).equalsIgnoreCase("1"))
    			{
    				List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
    				id = (Long)list2.get(0);
    				list = hamletBoothElectionDAO.findAllElectionsHappendInALocalElectionBody(id);
    				type2 = IConstants.URBAN;
    			}
    			
    		}else if(type.equalsIgnoreCase("panchayat")){
    			 list = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(id);
    		}else if(type.equalsIgnoreCase("booth") || type.equalsIgnoreCase("ward")){
    			
    			/*List<Long> constElectionIds  = constituencyElectionDAO.
    					getConstituencyElectionsForConstituency(constituencyId);
    			
    			list = boothConstituencyElectionDAO.findAllElectionsHappenedInABooth1(id,constElectionIds);
    			*/
    			//list = boothConstituencyElectionDAO.findAllElectionsHappenedInABooth(id);
    			list  = constituencyElectionDAO.findAllElectionsHappendInAConstituency(constituencyId);
    		}
    		
    		List<Long> electionIds = new ArrayList<Long>();
    		Map<Date,Election> map = new HashMap<Date, Election>();
    		List<Election> newElectionList = new ArrayList<Election>();
    	
    		
    		for(Object[] obj:list)
    			electionIds.add((Long)obj[0]);
    		
    		List<Election> electionList = electionDAO.getElectionDetailsForElections(electionIds);
    		
    		for(Election election:electionList){    			
    			map.put(election.getElectionDate(),election);
    		}
    		
    		for (Map.Entry<Date, Election> entry : map.entrySet()) {
    			newElectionList.add(entry.getValue());
    		}
    		
    		for(Election election:newElectionList){
				
				VoterVO voterVO = new VoterVO();				
				voterVO.setElectionId(election.getElectionId());
				voterVO.setElectionDate(election.getElectionDate());
				voterVO.setType("Election");				
				previousDetailsList.add(voterVO);			
			}
    		
    		List<Long> publicationIdsList = boothDAO
					.getAllPublicationDetailsForConstituency(constituencyId);			
			
			for(Long publicationId:publicationIdsList){
				
				PublicationDate publicationDate = publicationDAO.get(publicationId);
				
				VoterVO voterVO = new VoterVO();				
				voterVO.setPublicationDateId(publicationDate.getPublicationDateId());
				voterVO.setElectionDate(publicationDate.getDate());
				voterVO.setPublicationDate(publicationDate.getDate());
			    voterVO.setType("Publication");				 
				previousDetailsList.add(voterVO);			
				
			}
			
			Collections.sort(previousDetailsList);
    		
    		
    	}catch(Exception e){
    		
    		log.error("Exception raised in getAllElectionAndPublications service method");
    		e.printStackTrace();
    		
    	}
    	return previousDetailsList;
    	
    } 
		
	public List<VoterVO> getAllElectionAndPublicationsForConstituencyId(Long constituencyId){
			
			log.debug("Entered into the getAllElectionAndPublicationsForConstituencyId service method");
			List<VoterVO> previousDetailsList = new ArrayList<VoterVO>();
			
			try{
				List<Object[]> electionIdsList = constituencyElectionDAO
						.getAllElectionIdsForConstituency(constituencyId);			
				
				for(Object[]obj:electionIdsList){
					
					VoterVO voterVO = new VoterVO();				
					voterVO.setElectionId((Long)obj[0]);
					voterVO.setElectionDate((Date)obj[1]);
					voterVO.setType("Election");				
					previousDetailsList.add(voterVO);			
				}
				
				List<Long> publicationIdsList = boothDAO
						.getAllPublicationDetailsForConstituency(constituencyId);			
				
				for(Long publicationId:publicationIdsList){
					
					PublicationDate publicationDate = publicationDAO.get(publicationId);
					
					VoterVO voterVO = new VoterVO();				
					voterVO.setPublicationDateId(publicationDate.getPublicationDateId());
					voterVO.setElectionDate(publicationDate.getDate());
					voterVO.setPublicationDate(publicationDate.getDate());
				    voterVO.setType("Publication");				 
					previousDetailsList.add(voterVO);			
					
				}
				
				Collections.sort(previousDetailsList);
				
				//getVotersCountDetailsForAllLevels(previousDetailsList);
				
			}catch(Exception e){
				log.error("Exception rised in getAllElectionAndPublicationsForConstituencyId method : ",e);
				e.printStackTrace();
				return null;
			}
			
			return previousDetailsList;
		}
		
		
		public VotersInfoForMandalVO getVotersDetailsByPublicationBasis(
				 Long constituencyId,Long mandalId, Long panchayatId, Long boothId , String type,VoterVO voterVO){
			
	            log.debug("Entered into the getVotersDetailsByPublicationBasis service method");
	            
	            VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	            try{
					if(type.equalsIgnoreCase("constituency")){
						votersInfoForMandalVO = getConstituencyWiseVotersDetailsByPublicationBasis(type,constituencyId,voterVO.getPublicationDateId());
					}else if(type.equalsIgnoreCase("booth")){
						votersInfoForMandalVO = getBoothWiseVotersDetailsByPublicationBasis(boothId,voterVO.getPublicationDateId());
				    }else if(type.equalsIgnoreCase("panchayat")){
				    	votersInfoForMandalVO = getPanchayatWiseVotersDetailsByPublicationBasis(type,panchayatId,voterVO.getPublicationDateId());
					}else if(type.equalsIgnoreCase("mandal")){
						votersInfoForMandalVO = getMandalVotersDetailsByPublicationBasis(type,mandalId,voterVO.getPublicationDateId());
					}else if(type.equalsIgnoreCase("ward")){
						votersInfoForMandalVO = getWardWiseVotersDetailsByPublicationBasis(boothId,voterVO.getPublicationDateId());
				    }
					String maleVoters = votersInfoForMandalVO.getTotalMaleVoters();
					String femaleVoters = votersInfoForMandalVO.getTotalFemaleVoters();
					
					//if(maleVoters == null || femaleVoters == null || Long.parseLong(maleVoters) == 0 || Long.parseLong(femaleVoters) == 0)
					if(maleVoters == null || femaleVoters == null ||(maleVoters.equalsIgnoreCase("0") && femaleVoters.equalsIgnoreCase("0")))
						votersInfoForMandalVO.setDatapresent(false);
					
					votersInfoForMandalVO.setIsPublication("true");
					votersInfoForMandalVO.setPublicationDate(voterVO.getPublicationDate().toString());
					votersInfoForMandalVO.setElectinYear(voterVO.getPublicationDate().getYear()+1900);
			
	            }catch(Exception e){
	            	
	            	log.error("Exception raised in getVotersDetailsByPublicationBasis service method");
	            	e.printStackTrace();
	            	
	            }
	            
	            return votersInfoForMandalVO;
			
		}
		
		public VotersInfoForMandalVO getConstituencyWiseVotersDetailsByPublicationBasis(String type,Long constituencyId,Long publicationId){
			 VotersInfoForMandalVO votersInfoForMandalVO = null;
	         Long totalBooths = 0l;
	         
	         //getting total booths count
			List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId(type,constituencyId,publicationId);
			if(boothsCount != null &&  boothsCount.size() >0)
				totalBooths = (Long)boothsCount.get(0);
			
			//getting data from intermediate table
			votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.CONSTITUENCY),constituencyId,publicationId, "", "");
			
			//if data not present in intermediate table calculating data
			if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{	
				List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId(type,constituencyId,publicationId);
			    votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,constituencyId,constituencyDAO.get(constituencyId).getName(),"Constituency");
			}
			
			if(votersInfoForMandalVO != null)
			   votersInfoForMandalVO.setTotalBooths(totalBooths);
			
			return votersInfoForMandalVO;
		}
		
        public VotersInfoForMandalVO getPanchayatWiseVotersDetailsByPublicationBasis(String type,Long panchayatId,Long publicationId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	          //getting total booths count
        	List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId(type,panchayatId,publicationId);
			if(boothsCount != null &&  boothsCount.size() >0)
				totalBooths = (Long)boothsCount.get(0);
	    	
			//getting data from intermediate table
			votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Panchayat"),panchayatId,publicationId, "", "");
			
			//if data not present in intermediate table calculating data
			if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{
				List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(panchayatId,publicationId);
				if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
					votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,panchayatId,panchayatDAO.get(panchayatId).getPanchayatName(),"Panchayat");						
					
				}else{
					  votersInfoForMandalVO = new VotersInfoForMandalVO();
					  votersInfoForMandalVO.setDatapresent(false);						
				 }
			}
			if(votersInfoForMandalVO != null)
				votersInfoForMandalVO.setTotalBooths(totalBooths);
				
			return votersInfoForMandalVO;
		}
        
        public VotersInfoForMandalVO getBoothWiseVotersDetailsByPublicationBasis(Long boothId,Long publicationId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            
	          //getting data from intermediate table    
        	votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.BOOTH),boothId,publicationId, "", "");
			
        	 //if data not present in intermediate table calculating data
        	if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{
			 Booth booth = boothDAO.get(boothId);
			 List<Object[]> votersCountList =  boothPublicationVoterDAO.getVotersDetailsForBoothForPublication(publicationId,booth.getPartNo(),booth.getTehsil().getTehsilId());				
			 votersInfoForMandalVO = populateDataToVotersInfoForMandalVOForBooth(votersCountList,boothId,"booth-"+boothDAO.get(boothId).getPartNo(),"Booth");
			}
        	
			return votersInfoForMandalVO;
		}
        
        public VotersInfoForMandalVO getWardWiseVotersDetailsByPublicationBasis(Long boothId,Long publicationId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	          //getting total booths count
	            List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("ward",boothId,publicationId);
				if(boothsCount != null &&  boothsCount.size() >0)
					totalBooths = (Long)boothsCount.get(0);
				
	         //getting data from intermediate table     
        	votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId("Ward"),boothId,publicationId, "", "");
			
        	//if data not present in intermediate table calculating data
        	if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{
			   List<Object[]> votersCountList = boothPublicationVoterDAO.getVotersCountByPublicationId("ward",boothId,publicationId);
			   votersInfoForMandalVO =  populateDataToVotersInfoForMandalVO(votersCountList,boothId,constituencyDAO.get(boothId).getName(),"Ward");
			}
        	if(votersInfoForMandalVO != null)
        		votersInfoForMandalVO.setTotalBooths(totalBooths);
        	
			return votersInfoForMandalVO;
		}
        
        public VotersInfoForMandalVO getMandalVotersDetailsByPublicationBasis(String type,Long mandalId,Long publicationId){
        	  VotersInfoForMandalVO votersInfoForMandalVO = null;
	            Long totalBooths = 0l;
	            
	            //getting data from intermediate table      
	            //for local body
        	if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				 List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandalId.toString().substring(1).trim()));
				 if(!list.isEmpty()){
				    votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.LOCALELECTIONBODY),(Long) list.get(0),publicationId, "", "");
				  
				  //getting total booths count
				    List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("localBody",(Long) list.get(0),publicationId);
					if(boothsCount != null &&  boothsCount.size() >0)
					 totalBooths = (Long)boothsCount.get(0);
				 }
			}else{
				  //for mandal
				  votersInfoForMandalVO = getVotersDetailsByVoterReportLevelId(getReportLevelId(IConstants.MANDAL),new Long(mandalId.toString().substring(1).trim()),publicationId, "", "");	
				  //getting total booths count
				    List<Long> boothsCount = boothDAO.getBoothsCountByPublicationId("mandal",new Long(mandalId.toString().substring(1).trim()),publicationId);
					if(boothsCount != null &&  boothsCount.size() >0)
					 totalBooths = (Long)boothsCount.get(0);
			}
        	
        	//if data not present in intermediate table calculating data
			if(votersInfoForMandalVO == null || !votersInfoForMandalVO.isDatapresent())
			{	
				String name="";
				
				
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						  List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(mandalId.toString().substring(1));
						  Object[] reqName = assemblyLocalElectionBodyName.get(0);
						  name = reqName[0].toString()+" "+reqName[1].toString();
				}
				else{
					      
					      name = tehsilDAO.get(new Long(mandalId.toString().substring(1))).getTehsilName()+" Mandal/Tehsil";
				}
					
				List<Object[]> votersCountList = null;
				 if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){								
					//for local body
					 votersCountList =  boothPublicationVoterDAO.getVotersCountFromLocalElectionBody(new Long(mandalId.toString().substring(1).trim()),publicationId);
				 }else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					//for mandal
					 votersCountList =  boothPublicationVoterDAO.getVotersCountByPublicationId("mandal",new Long(mandalId.toString().substring(1).trim()),publicationId);
				 }
				 
				 
				 if(!votersCountList.isEmpty() && votersCountList.get(0)[1] != null){
					  votersInfoForMandalVO = populateDataToVotersInfoForMandalVO(votersCountList,new Long(mandalId),name,"Mandal");
				 }else{
					 votersInfoForMandalVO = new VotersInfoForMandalVO();
					 votersInfoForMandalVO.setDatapresent(false);
				 }
			}
			if(votersInfoForMandalVO != null)
				   votersInfoForMandalVO.setTotalBooths(totalBooths);
			return votersInfoForMandalVO;
		}
		public VotersInfoForMandalVO getVotersDetailsByElectionBasis(Long constituencyId,Long mandalId,Long  panchayatId,Long  boothId ,String type,VoterVO voterVO){
			
			 log.debug("Entered into the getVotersDetailsByElectionBasis service method");
			 VotersInfoForMandalVO votersInfoForMandalVO  = null;
			 List<Object[]> votersCountDetails = null;
			
	         try{
					if(type.equalsIgnoreCase("constituency")){					
						votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAConstituency(voterVO.getElectionId(), constituencyId);
					}else if(type.equalsIgnoreCase("mandal")){
						
						if (mandalId.toString().substring(0, 1).trim()
								.equalsIgnoreCase("1")) {
							
							List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(
									new Long(mandalId.toString().substring(1).trim()));
							
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
											voterVO.getElectionId(),(Long) list.get(0), "localElec",null,constituencyId);
						}else{
							votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(voterVO.getElectionId(), new Long(mandalId
											.toString().substring(1).trim()),"mandal", null, null);
					  
						  }
					}else if(type.equalsIgnoreCase("panchayat"))						
						votersCountDetails =  hamletBoothElectionDAO.getVotersCountInAPanchayat(
							 voterVO.getElectionId(),panchayatId);
					else if(type.equalsIgnoreCase("booth")){						
						 Booth booth = boothDAO.get(boothId);
						 votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
								voterVO.getElectionId(), booth.getTehsil().getTehsilId(), "booth", booth.getPartNo(), null);
			         }
					else if(type.equalsIgnoreCase("ward")){						
						 votersCountDetails = boothConstituencyElectionDAO.getVotersCountInAMandalBooth(
								voterVO.getElectionId(), boothId, "ward", null, null);
			         }
					 if(votersCountDetails != null && votersCountDetails.size() >0){
						 votersInfoForMandalVO = new VotersInfoForMandalVO();
						 
						 Object[] voterDtls = votersCountDetails.get(0);
						 
						 if(voterDtls[0] == null && voterDtls[1] == null && voterDtls[2] == null){
							 votersInfoForMandalVO.setDatapresent(false);
						 
						 }else{
							 votersInfoForMandalVO.setTotalMaleVoters(voterDtls[0].toString());
							 votersInfoForMandalVO.setTotalFemaleVoters(voterDtls[1].toString());
							 votersInfoForMandalVO.setTotalBooths((Long)voterDtls[3]);
						     BigDecimal totalCount = new BigDecimal(((Long)voterDtls[2]).longValue());

							 votersInfoForMandalVO.setTotVoters(totalCount);
							 votersInfoForMandalVO.setIsPublication("false");
							 votersInfoForMandalVO.setElectionDate(voterVO.getElectionDate().toString());
							 votersInfoForMandalVO.setElectinYear(voterVO.getElectionDate().getYear()+1900);
						 }
						 
					 }else{
						 votersInfoForMandalVO = new VotersInfoForMandalVO();
						 votersInfoForMandalVO.setDatapresent(false);
					 }
	         }catch(Exception e){
	        	 log.error("Exception raised in getVotersDetailsByElectionBasis service method");
	        	 e.printStackTrace();        	 
	         }
	         return votersInfoForMandalVO;
		}
		
		
		 
		public List<VotersInfoForMandalVO> getVotersDetailsByVoterMultipleReportLevelIds(Long reportLevelId, Map<Long,String> reportLevelValueIds, Long publicationDateId,String type)
		 {
		  List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
			try{
				VotersInfoForMandalVO votersInfoForMandalVO = null;
				List<VoterInfo> list = voterInfoDAO.getVotersMultipleCount(reportLevelId, reportLevelValueIds.keySet(), publicationDateId);
				if(list == null || list.size() == 0)
				{
					return votersInfoForMandalVOList;
				}
				if(list != null && list.size() > 0)
				{
					for(VoterInfo voterDetails : list)
					{   votersInfoForMandalVO = new VotersInfoForMandalVO();
						BigDecimal unknowCount = new BigDecimal(voterDetails.getTotalVoters().longValue()-(voterDetails.getMaleVoters().longValue() + voterDetails.getFemaleVoters().longValue()));
						votersInfoForMandalVO.setTotVoters(voterDetails.getTotalVoters() != null ? new BigDecimal(voterDetails.getTotalVoters()):new BigDecimal(0.00));
						votersInfoForMandalVO.setTotalVoters(voterDetails.getTotalVoters() != null ? voterDetails.getTotalVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalMaleVoters(voterDetails.getMaleVoters() != null ?voterDetails.getMaleVoters().toString():"0.00");
						votersInfoForMandalVO.setTotalFemaleVoters(voterDetails.getFemaleVoters() != null?voterDetails.getFemaleVoters().toString():"0.00");
					    votersInfoForMandalVO.setTotalVotersPercentage(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalFemalePercentage(voterDetails.getFemaleVotersPercentage() != null? voterDetails.getFemaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setTotalMalePercentage(voterDetails.getMaleVotersPercentage() != null ? voterDetails.getMaleVotersPercentage().toString():"0.00");
						votersInfoForMandalVO.setUnKnowVoters(unknowCount != null ? unknowCount.toString(): "0.00");
						votersInfoForMandalVO.setType(type);
						votersInfoForMandalVO.setName(reportLevelValueIds.get(voterDetails.getReportLevelValue()));
						votersInfoForMandalVO.setId(voterDetails.getReportLevelValue());
						votersInfoForMandalVO.setPercent(voterDetails.getTotalVotersPercentage() != null?voterDetails.getTotalVotersPercentage().toString():"0.00");
						votersInfoForMandalVOList.add(votersInfoForMandalVO);
					}
				}
				return votersInfoForMandalVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getVotersCount() Method, Exception - "+e);
				return null;
			}
		 }

		/**
		 * This method is used to get the all booth details based on constituencyId and publicationId
		 * @author Prasad Thiragabathina
		 * @param constituencyId
		 * @param publicationId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getBoothsForConstituencyAndPublication(
				Long constituencyId, Long publicationId) {
			List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>();
			
			log.debug("Entered into the getBoothsForConstituencyAndPublication service method");
			if(constituencyId != 0 && publicationId!=0)
			{
				List<Object[]> booths = boothDAO.getBoothsBasedOnConstituencyAndPublicationDate(constituencyId,publicationId);
				for (Object[] booth : booths) {
					SelectOptionVO selectOptions = new SelectOptionVO();
					selectOptions.setId((Long) booth[0]);
					selectOptions.setName((String) booth[1].toString());
					boothsList.add(selectOptions);
				}
			}
			return boothsList;
		}

		/**
		 * This method is used to store the voter details into the voter table and booth_voter_publication table
		 * @author Prasad Thiragabathina
		 * @param name
		 * @param voterCardNo
		 * @param houseNo
		 * @param gaurdian
		 * @param relationShip
		 * @param gender
		 * @param age
		 * @param boothId
		 * @return ResultStatus
		 */
		public ResultStatus saveVoters(final String name, final String voterCardNo,
				final String houseNo, final String gaurdian, final String relationShip,
				final String gender, final String mobileNo, final Long age,final Long boothId) {
				
			  log.info("Entered into saveVoters Method...");
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				List<Voter> voters = voterDAO.getVoterByVoterCardNo(voterCardNo);
					if(voters != null && voters.size() > 0)
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						return resultStatus;
					}
					else
					{
					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						protected void doInTransactionWithoutResult(TransactionStatus status) 
						{
							Voter voter = new Voter();
							voter.setName(name);
							voter.setVoterIDCardNo(voterCardNo);
							voter.setHouseNo(houseNo);
							voter.setGender(gaurdian);
							voter.setRelationshipType(relationShip);
							voter.setGender(gender);
							voter.setAge(age);
							voter.setMobileNo(mobileNo);
							//voterDAO.save(voter);
							BoothPublicationVoter boothPublicationVoter = new BoothPublicationVoter();
							if(boothId != null)
							{
							boothPublicationVoter.setBoothId(boothId);
							boothPublicationVoter.setVoter(voter);
							boothPublicationVoterDAO.save(boothPublicationVoter);
							}
							
						}});
					}
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						return resultStatus;	
					
					
			  }catch (Exception e) {
				  e.printStackTrace();
				  log.error("Exception Occured in saveVoters Method, Exception - "+e);
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			}
		  
			
		} 
		
		public List<SelectOptionVO> getElectionYearsByMandalId(String type,Long id)
		{
			List<SelectOptionVO> electionYearsList = new ArrayList<SelectOptionVO>(0);
			try{
				List<Object> list = null;
				if(type.equalsIgnoreCase(IConstants.MANDAL))
				{
					if(id.toString().trim().substring(0, 1).equalsIgnoreCase("2"))
					{
						id = new Long(id.toString().trim().substring(1));
						type = IConstants.MANDAL;
					}
					else
					{
						List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
						if(list2 != null)
							id = (Long)list2.get(0);
							type = IConstants.LOCALELECTIONBODY;
					}
					
				 }
				list = boothConstituencyElectionDAO.getElectionYearsByMandalId(type, id);
				
				if(list != null && list.size() > 0)
				{
					for(Object params : list)
						electionYearsList.add(new SelectOptionVO(new Long(params.toString()), params.toString()));
				}
				return electionYearsList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getElectionYearsByMandalId() Method, Exception - "+e);
				return electionYearsList;
			}
		}
		
		public CrossVotingConsolidateVO getCrossVotingReportByMandalIdAndEleYear(String type, Long id, String year, String includeAliance)
		{
			CrossVotingConsolidateVO consolidateVO = new CrossVotingConsolidateVO();
			List<CrossVotedMandalVO> crossVotedMandalVOList = new ArrayList<CrossVotedMandalVO>(0);
			try{
			    List<Long> partyIdsList = null;
			    Long assemblyConValidVotes = 0l;
			    Long parliamentConValidVotes = 0l;
			   			    
			    if(type.equalsIgnoreCase(IConstants.MANDAL))
			    {
			    	if(id.toString().trim().substring(0,1).equalsIgnoreCase("2"))
				  	{
			    		id = new Long(id.toString().trim().substring(1));
			    		type = IConstants.MANDAL;
				  	}
					
				    else
				    {
					   List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
					   if(list2 != null)
						   id = (Long)list2.get(0);
					   type = IConstants.LOCALELECTIONBODY;
				    }	
			    }
			   List<Object[]> assemblyIdsList = boothConstituencyElectionDAO.getConstituencyIdAndElectionYearByElectionType(type, id, year, IConstants.ASSEMBLY_ELECTION_TYPE);
			   List<Object[]> parliamentIdsList = boothConstituencyElectionDAO.getConstituencyIdAndElectionYearByElectionType(type, id, year, IConstants.PARLIAMENT_ELECTION_TYPE);
			   
			   if(assemblyIdsList != null && assemblyIdsList.size() >0 && parliamentIdsList != null && parliamentIdsList.size() > 0)
			   {
				   
					  partyIdsList = candidateBoothResultDAO.getPartyIdsListByEleIdAndYearAndConstId((Long)assemblyIdsList.get(0)[0], (Long)assemblyIdsList.get(0)[1], year);
							
					  if(partyIdsList != null && partyIdsList.size() >0)
					  {
						  List<Object[]> acList = null;
						  List<Object[]> pcList = null;
						  List acValidVotesList = null;
						  List pcValidVotesList = null;
					
						  acValidVotesList = candidateBoothResultDAO.getValidVotesByEleTypeAndConstituencyId(type,id, (Long)assemblyIdsList.get(0)[0], year);
						  pcValidVotesList = candidateBoothResultDAO.getValidVotesByEleTypeAndConstituencyId(type,id, (Long)parliamentIdsList.get(0)[0], year);
					
						  acList  = candidateBoothResultDAO.getAllPartiesCrossVotingReportByEleYearAndConstituencyId(type,id, (Long)assemblyIdsList.get(0)[0], year, partyIdsList);
						  pcList = candidateBoothResultDAO.getAllPartiesCrossVotingReportByEleYearAndConstituencyId(type, id, (Long)parliamentIdsList.get(0)[0], year, partyIdsList);
						
						  if(acValidVotesList != null && acValidVotesList.size() > 0)
							  assemblyConValidVotes = ((Double)acValidVotesList.get(0)).longValue();
						  if(pcValidVotesList != null && pcValidVotesList.size() > 0)
							  parliamentConValidVotes = ((Double)pcValidVotesList.get(0)).longValue();
						
						   getCrossVotingPartyWiseDetails(acList, pcList, assemblyConValidVotes, parliamentConValidVotes, crossVotedMandalVOList);
						   consolidateVO.setMandals(crossVotedMandalVOList);
					   
				   }
				   
							
			}
				  
				return consolidateVO;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCrossVotingReportByMandalIdAndEleYear() Method, Exception - "+e);
				return null;
			}
			
		}

		
		public List<CrossVotedMandalVO> getCrossVotingPartyWiseDetails(List<Object[]> acList, List<Object[]> pcList, Long acValidVotes, Long pcValidVotes,List<CrossVotedMandalVO> crossVotedMandalVOList)
		{
			try{
				
				List<String> partyList = new ArrayList<String>(0);
				
				if(acList != null && acList.size() >0)
				{
					for(Object[] params : acList)
					{
						if(!partyList.contains(params[1]))
						  partyList.add(params[1].toString());
					}
				}
				if(pcList != null && pcList.size() > 0)
				{
					for(Object[] params : pcList)
					{
					  if(!partyList.contains(params[1]))
							partyList.add(params[1].toString());
					}
				}
				Collections.sort(partyList);
				
				for(String partyInList : partyList)
				{
					String partyName = partyInList;
					Long acVotesEarned = 0l;
					Long pcVotesEarned = 0l;
					Double acPercentageDiff = 0.00;
					Double pcPercentageDiff = 0.00;
					Double percentageImpactOnConstituency = 0.00;
					
					CrossVotedMandalVO crossVotedMandalVO = new CrossVotedMandalVO();
					crossVotedMandalVO.setPartyName(partyInList);
					
					for(Object[] objects : acList)
					{
						if(objects[1].toString().equalsIgnoreCase(partyName))
						  acVotesEarned += (Long)objects[3];
					}
					
					for(Object[] obj : pcList)
					{
						if(obj[1].toString().equalsIgnoreCase(partyName))
							pcVotesEarned += (Long)obj[3];
							
					}
					
					crossVotedMandalVO.setPolledVotes(acValidVotes);
					
					if(acValidVotes != null && acValidVotes > 0)
						acPercentageDiff = (acVotesEarned.doubleValue()*100.0)/acValidVotes.doubleValue();
					
					if(pcValidVotes != null && pcValidVotes > 0)
					  pcPercentageDiff = (pcVotesEarned.doubleValue()*100.0)/pcValidVotes.doubleValue();
					
					percentageImpactOnConstituency = acPercentageDiff-pcPercentageDiff;
					
					crossVotedMandalVO.setAcPercentageInMandal(acPercentageDiff != null ? new BigDecimal(acPercentageDiff).setScale(2, BigDecimal.ROUND_HALF_UP).toString() :"0.00");
					crossVotedMandalVO.setPcPercentageInMandal(pcPercentageDiff != null ? new BigDecimal(pcPercentageDiff).setScale(2, BigDecimal.ROUND_HALF_UP).toString() :"0.00");
					crossVotedMandalVO.setPercentageDifferenceInMandal(percentageImpactOnConstituency != null? new BigDecimal(percentageImpactOnConstituency).setScale(2, BigDecimal.ROUND_HALF_UP).toString():"0.00");
					
					if(acValidVotes != null && acValidVotes > 0)
					{
						crossVotedMandalVO.setPercentageImpactOnConstituency(new BigDecimal(percentageImpactOnConstituency*100.0/acValidVotes).
							setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					}
					else
					 crossVotedMandalVO.setPercentageImpactOnConstituency("0.00");
					
					System.out.println(percentageImpactOnConstituency +" "+ acValidVotes);
					
					crossVotedMandalVOList.add(crossVotedMandalVO);
					
				}
				
				return crossVotedMandalVOList;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getCrossVotingPartyWiseDetails() Method, Exception - "+e);
				return null;
			}
		
		}	
		
		public static Comparator<SelectOptionVO> arraySort = new Comparator<SelectOptionVO>()
				{	  
						  public int compare(SelectOptionVO arg1,SelectOptionVO arg2)
							{
							   return (new Integer(arg1.getValue()).intValue()) - (new Integer(arg2.getValue()).intValue());
							}
				};
				public static Comparator<SelectOptionVO> arraySort1 = new Comparator<SelectOptionVO>()
						{	  
								  public int compare(SelectOptionVO arg1,SelectOptionVO arg2)
									{
									  return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
									}
						};
				
		public ResultStatus deleteVotersDataFromIntermediateTables(Long reportLevelValue, Long publicationDateId)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				
				List<Long> constituencyIdsList = new ArrayList<Long>(0);
				List<Long> mandalIdsList = new ArrayList<Long>(0);
				List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
				List<Long> panchayatIdsList = new ArrayList<Long>(0);
				List<Long> boothIdsList = new ArrayList<Long>(0);
				List<Long> wardIdsList = new ArrayList<Long>(0);
				
				constituencyIdsList.add(reportLevelValue);
				
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue ,IConstants.PRESENT_YEAR, null);
				if(mandalsList == null || mandalsList.size() == 0)
					return null;
				
				for(SelectOptionVO selectOptionVO : mandalsList)
				{
					if(selectOptionVO.getId().toString().substring(0, 1).equalsIgnoreCase(IConstants.RURAL_TYPE))
						mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
					else
						localEleBodyIdsList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
				}
				
				/*if(mandalIdsList == null || mandalIdsList.size() == 0)
					return null;*/
						
				if(mandalIdsList != null && mandalIdsList.size() > 0)
					panchayatIdsList = panchayatDAO.getPanchayatIdsBytehsilIdsList(mandalIdsList);
				
				if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					boothIdsList = boothDAO.getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(IConstants.PANCHAYAT, publicationDateId, panchayatIdsList);
				
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
				{
					List<Long> boothIds = boothDAO.getBoothIdsByPanchayatIdsListOrLocalEleBodyIdsList(IConstants.LOCALELECTIONBODY, publicationDateId, localEleBodyIdsList);
					if(boothIds != null && boothIds.size() > 0)
					{
						for(Long boothId : boothIds)
						{
							if(!boothIdsList.contains(boothId))
								boothIdsList.add(boothId);	
						}
					}
				
				}
				
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() >0)
					wardIdsList = boothDAO.getWardIdsByLocalEleBodyIdsList(localEleBodyIdsList, publicationDateId);
					
				if(constituencyIdsList != null && constituencyIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.CONSTITUENCY, publicationDateId, constituencyIdsList);
				if(mandalIdsList != null && mandalIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.MANDAL, publicationDateId, mandalIdsList);
				if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.LOCALELECTIONBODY, publicationDateId, localEleBodyIdsList);
				if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.PANCHAYAT, publicationDateId, panchayatIdsList);
				if(boothIdsList != null && boothIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.BOOTH, publicationDateId, boothIdsList);
				if(wardIdsList != null && wardIdsList.size() > 0)
					deleteVoterInfoFromIntermediateTables(IConstants.WARD, publicationDateId, wardIdsList);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
				
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		
		public ResultStatus deleteVoterInfoFromIntermediateTables(String type, Long publicationDateId,List<Long> reportLevelValue)
		{
			ResultStatus resultStatus = new ResultStatus();
			try{
				
				if(reportLevelValue != null && reportLevelValue.size() > 0)
				{
					voterInfoDAO.deleteVotersInfoByReportLevelValue(getReportLevelId(type), reportLevelValue, publicationDateId);
					voterFamilyInfoDAO.deleteVoterFamilyDetByReportLevelValAndVoterAgeRange(getReportLevelId(type), reportLevelValue, publicationDateId);
					voterAgeInfoDAO.deleteVoterAgeInfoByReportLevelIdAndReportLevelValue(getReportLevelId(type), reportLevelValue, publicationDateId);
				}
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in deleteVoterInfoFromIntermediateTables() Method, Exception - "+e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		/**
		 * 	This method is used to get the all the details of the voter based on voterId and 
		 * 	This voter is added eaithe cader or a influencing prople
		 * @author Prasad Thiragabathina
		 * @param Long voterId
		 * @param Long userId
		 * @return InfluencingPeopleBeanVO
		 */

		public InfluencingPeopleBeanVO getDetailsByVoterId(Long voterId ,Long userId) {
			
			try {
				log.info("Entered into getDetailsByVoterId Method...");
				InfluencingPeopleBeanVO influencingPeopleVO = new InfluencingPeopleBeanVO();
				influencingPeopleVO.setInfluencingRange("0");
				List<Voter> voters = new ArrayList<Voter>();
				
				voters = voterDAO.findVoterDetailsBasedOnVoterId(voterId);
					if(voters != null && voters.size() > 0)
					{
						Voter voter = voters.get(0);
							influencingPeopleVO.setFirstName(voter.getName());
							influencingPeopleVO.setHouseNo(voter.getHouseNo());
							influencingPeopleVO.setFatherOrSpouseName(voter.getRelativeName());
							if(voter.getGender() != null)
							{
								String gender = voter.getGender();
								if(voter.getGender().equalsIgnoreCase("m"))
									 gender = "Male";
									else
									 gender = "Female";
									
									influencingPeopleVO.setGender(gender);
							}
							else
							{
								influencingPeopleVO.setGender("Male");
							}
							influencingPeopleVO.setMobile(voter.getMobileNo());
							
					}
					
					List<BoothPublicationVoter> voterContactDetails= boothPublicationVoterDAO.findVoterContactDetails(voterId);
					if(voterContactDetails != null & voterContactDetails.size() > 0)
					{
						BoothPublicationVoter boothPublicationVoter = voterContactDetails.get(0);
							if(boothPublicationVoter.getBooth().getConstituency().getState() != null)
							{
							influencingPeopleVO.setState(boothPublicationVoter.getBooth().getConstituency().getState().getStateId().toString());
							influencingPeopleVO.setStateName(boothPublicationVoter.getBooth().getConstituency().getState().getStateName());
							}
							if(boothPublicationVoter.getBooth().getConstituency().getDistrict() != null)
							{
							influencingPeopleVO.setDistrict(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictId().toString());
							influencingPeopleVO.setDistrictName(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictName());
							}
							if(boothPublicationVoter.getBooth().getConstituency() != null)
							{
							influencingPeopleVO.setConstituency(boothPublicationVoter.getBooth().getConstituency().getConstituencyId().toString());
							influencingPeopleVO.setConstituencyName(boothPublicationVoter.getBooth().getConstituency().getName());
							}
							if(boothPublicationVoter.getBooth().getTehsil() != null)
							{
							  if(boothPublicationVoter.getBooth().getLocalBody() == null)
								{
								influencingPeopleVO.setMandal(IConstants.RURAL_TYPE+boothPublicationVoter.getBooth().getTehsil().getTehsilId().toString());
								influencingPeopleVO.setMandalName(boothPublicationVoter.getBooth().getTehsil().getTehsilName());
								}
							  else
							  	{
								 Long localElectionBodyId = boothPublicationVoter.getBooth().getLocalBody().getLocalElectionBodyId();
								 List assemblyLocalElectionBodyId = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(localElectionBodyId);
								 if(assemblyLocalElectionBodyId != null && assemblyLocalElectionBodyId.size() > 0)
								 {
								 Long assemblyLocalElectionBodyIds = (Long)assemblyLocalElectionBodyId.get(0);
								 influencingPeopleVO.setMandal(IConstants.URBAN_TYPE+assemblyLocalElectionBodyIds.toString());
								 }
							   }
							  if(boothPublicationVoter.getBooth() != null)
							  {
								  influencingPeopleVO.setBooth(boothPublicationVoter.getBooth().getPartNo().toString());
								  influencingPeopleVO.setBoothName(boothPublicationVoter.getBooth().getPartNo());
							  }
							}
							Long boothId = (Long) boothPublicationVoter.getBooth().getBoothId();
							List<HamletBoothPublication> hamletBoothPublicationList = hamletBoothPublicationDAO.getHameletDetailsByBoothId(boothId);
							if(hamletBoothPublicationList != null & hamletBoothPublicationList .size() > 0)
							{
								HamletBoothPublication hamletBoothPublication = hamletBoothPublicationList.get(0);
								if(hamletBoothPublication.getBooth().getLocalBody() == null)
								{
									influencingPeopleVO.setWardOrHamlet(IConstants.RURAL_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
									influencingPeopleVO.setWardOrHamletName(hamletBoothPublication.getHamlet().getHamletName().toString());
								}
								else
								{
									influencingPeopleVO.setWardOrHamlet(IConstants.URBAN_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
									influencingPeopleVO.setWardOrHamletName(hamletBoothPublication.getHamlet().getHamletName().toString());
								}
							}
						}
							List<Object[]> voterPartyAndCaste = userVoterDetailsDAO.getPartyAndCasteDetails(voterId,userId);
							if(voterPartyAndCaste != null && voterPartyAndCaste.size() > 0)
							{
								Object[] 	voterPartyAndCasteDetails = voterPartyAndCaste.get(0);
								influencingPeopleVO.setParty(voterPartyAndCasteDetails[2].toString());
								influencingPeopleVO.setPartyName(voterPartyAndCasteDetails[3].toString());
								influencingPeopleVO.setCast(voterPartyAndCasteDetails[0].toString());
								influencingPeopleVO.setCastType(voterPartyAndCasteDetails[1].toString());
							}
				return influencingPeopleVO;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception raised in getDetailsByVoterId Method...");
				return null;
			}
			
		}
		/**
		 * This method is used to get all the details of the voter based on voter id and set all 
		 * these details as cadre details
		 * @author Prasad Thiragabathina
		 * @param  Long voterId
		 * @return CadreInfo
		 */
		public CadreInfo getCadreDetailsByVoterId(Long voterId) {
			try {
				log.info("Entered into getCadreDetailsByVoterId Method...");
				CadreInfo cadreInfo = new CadreInfo();
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				List<Voter> voters = new ArrayList<Voter>();
				voters = voterDAO.findVoterDetailsBasedOnVoterId(voterId);
				if(voters != null && voters.size() > 0)
				{
					Voter voter = voters.get(0);
					cadreInfo.setFirstName(voter.getName());
					cadreInfo.setHouseNo(voter.getHouseNo());
					cadreInfo.setFatherOrSpouseName(voter.getRelativeName());
					String gender = voter.getGender();
					if(gender.equalsIgnoreCase("m"))
					gender= "Male";
					else
					gender = "Female";
					cadreInfo.setGender(gender);
					cadreInfo.setMobile(voter.getMobileNo());
					//cadreInfo.setDateOfBirth(sdf.format(dob));
					Long age = voter.getAge();
					Date today = new Date();
					Long year = (long) today.getYear();
					Long dobYear = year-age;
					cadreInfo.setDateOfBirth(sdf.format(dobYear));
					cadreInfo.setAge(voter.getAge().toString());
					cadreInfo.setDobOption("Age");
				}
				List<BoothPublicationVoter> voterContactDetails= boothPublicationVoterDAO.findVoterContactDetails(voterId);
				if(voterContactDetails != null & voterContactDetails.size() > 0)
				{
					BoothPublicationVoter boothPublicationVoter = voterContactDetails.get(0);
						if(boothPublicationVoter.getBooth().getConstituency().getState() != null)
						{
							cadreInfo.setState(boothPublicationVoter.getBooth().getConstituency().getState().getStateId().toString());
							cadreInfo.setStateName(boothPublicationVoter.getBooth().getConstituency().getState().getStateName());
						}
						if(boothPublicationVoter.getBooth().getConstituency().getDistrict() != null)
						{
							cadreInfo.setDistrict(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictId().toString());
							cadreInfo.setDistrictName(boothPublicationVoter.getBooth().getConstituency().getDistrict().getDistrictName());
						}
						if(boothPublicationVoter.getBooth().getConstituency() != null)
						{
							cadreInfo.setConstituencyID(boothPublicationVoter.getBooth().getConstituency().getConstituencyId());
							cadreInfo.setConstituencyName(boothPublicationVoter.getBooth().getConstituency().getName());
						}
						if(boothPublicationVoter.getBooth().getTehsil() != null)
						{
						  if(boothPublicationVoter.getBooth().getLocalBody() == null)
							{
							  cadreInfo.setMandal(IConstants.RURAL_TYPE+boothPublicationVoter.getBooth().getTehsil().getTehsilId().toString());
							  cadreInfo.setMandalName(boothPublicationVoter.getBooth().getTehsil().getTehsilName());
							}
						  else
						  	{
							 Long localElectionBodyId = boothPublicationVoter.getBooth().getLocalBody().getLocalElectionBodyId();
							 List assemblyLocalElectionBodyId = assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(localElectionBodyId);
							 if(assemblyLocalElectionBodyId != null && assemblyLocalElectionBodyId.size() > 0)
							 {
							 Long assemblyLocalElectionBodyIds = (Long)assemblyLocalElectionBodyId.get(0);
							 cadreInfo.setMandal(IConstants.URBAN_TYPE+assemblyLocalElectionBodyIds.toString());
							 }
						   }
						}
						Long boothId = (Long) boothPublicationVoter.getBooth().getBoothId();
						List<HamletBoothPublication> hamletBoothPublicationList = hamletBoothPublicationDAO.getHameletDetailsByBoothId(boothId);
						if(hamletBoothPublicationList != null & hamletBoothPublicationList .size() > 0)
						{
							HamletBoothPublication hamletBoothPublication = hamletBoothPublicationList.get(0);
							if(hamletBoothPublication.getBooth().getLocalBody() == null)
							{
								cadreInfo.setVillage(IConstants.RURAL_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
								cadreInfo.setVillageName(hamletBoothPublication.getHamlet().getHamletName().toString());
							}
							else
							{
								cadreInfo.setVillage(IConstants.URBAN_TYPE+hamletBoothPublication.getHamlet().getHamletId().toString());
								cadreInfo.setVillageName(hamletBoothPublication.getHamlet().getHamletName().toString());
							}
						}
						List<Long> famileyMemberCount = boothPublicationVoterDAO.getFamilyMemberCount(cadreInfo.getHouseNo(), boothPublicationVoter.getBooth().getBoothId());
						cadreInfo.setNoOfFamilyMembers(famileyMemberCount.get(0).toString());
						cadreInfo.setNoOfVoters(famileyMemberCount.get(0).toString());
						List<Object[]> famileyMembersDetails = boothPublicationVoterDAO.getFamileyMembersDetailsForHouseNo(cadreInfo.getHouseNo(), boothPublicationVoter.getBooth().getBoothId(),voterId);
						int count = 0;
						if(famileyMembersDetails != null && famileyMembersDetails.size() > 0)
						{
							for (Object[] objects : famileyMembersDetails) {
								
								if(count == 0)
								{
									cadreInfo.setFirstFamilyMemberName(objects[1].toString());
									//cadreInfo.setFirstFamilyMemberRelation(objects[2].toString());
									//List<Long> relationId = userRelationDAO.getRelationId( objects[2].toString());
									//cadreInfo.setFirstFamilyMemberRelationId(relationId.toString());
								}
								if(count == 1)
								{
									cadreInfo.setSecondFamilyMemberName(objects[1].toString());
									//cadreInfo.setSecondFamilyMemberRelation(objects[2].toString());
									//List<Long> relationId = userRelationDAO.getRelationId(objects[2].toString());
									//cadreInfo.setSecondFamilyMemberRelationId(relationId.toString());
								}	
								if(count == 2)
								{
									cadreInfo.setThirdFamilyMemberName(objects[1].toString());
									//cadreInfo.setThirdFamilyMemberRelation(objects[2].toString());	
									//List<Long> relationId = userRelationDAO.getRelationId(objects[2].toString());
									//cadreInfo.setThirdFamilyMemberRelationId(relationId.toString());
								}
								if(count > 2)
								{
									break;
								}
								count++;
							}
						}
						
					}
						
						cadreInfo.setSameAsCA(true);
				return cadreInfo;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception raised in getCadreDetailsByVoterId Method...");
				return null;
			}
			
		}

		public Long getParliamentConstituencyId(String type, Long id, Long year)
		{
			Long parliamentConId = null;
			try{
				if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					List pcsInfoList = delimitationConstituencyAssemblyDetailsDAO.findParliamentByAssemblyIdAndElectionYear(id,year);
					
					List<Object[]> pcsInfo = (List<Object[]>)pcsInfoList;
					if(pcsInfo != null && pcsInfo.size() >0)
						parliamentConId = (Long)pcsInfo.get(0)[0];
				}
				return parliamentConId;
			}catch (Exception e) {
				e.printStackTrace();
				log.error("Exception Occured in getParliamentConstituencyId() Method, Exception - "+e);
				return parliamentConId;
			}
		}

		public List<SelectOptionVO> getWardsMunicipality(Long lclElecBodyId,Long publicationDateId){
			 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
			try{
				List<Object> listId = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(lclElecBodyId);
			//List<Object[]> wards =  constituencyDAO.getWardsInMuncipality(lclElecBodyId);
			 List<Object[]> wards = boothDAO.getWardsInMuncipality(lclElecBodyId, publicationDateId);
			 SelectOptionVO selectOptionVO = null;
			 for(Object[] ward:wards){
				 selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setId((Long)ward[0]);
				 if(listId.get(0) != null ){
				List<Object> name	=  localElectionBodyWardDAO.findWardName((Long)ward[0]);
				    if(name.size()>0){
				String wardName =(String) name.get(0);
				           
				 selectOptionVO.setName(ward[1]!=null? wardName!=null? wardName+"("+ward[1].toString()+")" : ward[1].toString() : "");
				    }
				 else
					 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				    }else
				 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				 
				 selectOptionVO.setValue(ward[1].toString());
			//List<SelectOptionVO>	allbooths= getBoothsInMunicipality(lclElecBodyId, publicationDateId);
			 List<SelectOptionVO> list2=	getBoothForWard((Long)ward[0],publicationDateId);
			if(list2 != null && list2.size()>0)
				selectOptionVO.setSelectOptionsList(list2);
			
				 list.add(selectOptionVO);
			 }
			 Collections.sort(list,arraySort1);
			}catch(Exception e){
				log.error("Exception rised in getwardsInMunicipality ",e);
			}
			 return list;
			 
		 }
		 
		 public List<SelectOptionVO> getBoothForWard(Long wardId, Long publicationDateId)
		 {    List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
			 List<Object[]> booths = boothDAO.getBoothInWard(wardId, publicationDateId);
			   SelectOptionVO selectOptionVO = null;
				 for(Object[] booth:booths){
					 selectOptionVO = new SelectOptionVO();
					 selectOptionVO.setId((Long)booth[0]);
					 selectOptionVO.setName(booth[1]!=null?booth[1].toString():"");
					 selectOptionVO.setValue(booth[1].toString());
					 selectOptionVO.setLocation(booth[2] != null?booth[2].toString():"");
					 selectOptionVO.setVillageCovered(booth[3] != null?booth[3].toString():"");
					 list.add(selectOptionVO);
				 }
				 Collections.sort(list,arraySort);
			
				 return list;
			 
		 }
		 
		 /**
		 * This method move the Voters Modification Data from Temporary Table(voter_modification_temp) 
		 * to Main Table(voter_modification)
		 * 
		 * @author Kamalakar Dandu
		 * @param Long Constituency Id
		 * @param Long Publication Date Id
		 * @return {@link ResultStatus}
		 * 
		 */
		 public ResultStatus moveVotersModificationDataFromTempToMainTable(Long constituencyId,Long publicationDateId)
		 {
			 log.debug("Entered into moveVotersModificationDataFromTempToMainTable() Method");
			 ResultStatus resultStatus = new ResultStatus();
			 try{
				 
				 List<Object[]> result = voterModificationTempDAO.getVoterIDAndStatusFromVoterModificationTempByConstituencyId(constituencyId);
				 
				 if(result != null && result.size() > 0)
				 {
					 VoterVO voterVO = null;
					 List<String> voterIdCardNosList = new ArrayList<String>(0);
					 List<VoterVO> votersList = new ArrayList<VoterVO>(0);
					 for(Object[] params : result)
					 {
						 voterVO = new VoterVO();
						 voterVO.setVoterIDCardNo(params[0].toString());
						 voterVO.setStatus(params[1].toString());
						 votersList.add(voterVO);
						 voterIdCardNosList.add(params[0].toString());
					 }
					 
					 List<SelectOptionVO> voterIdsList = getVoterIdsByCardNosList(voterIdCardNosList);
					 
					 if(voterIdsList != null && voterIdsList.size() > 0)
					 {
						 for(VoterVO voterVO2 : votersList)
						 {
							 Long voterId = getVoterIdByVoterIdCardNo(voterVO2.getVoterIDCardNo(),voterIdsList);
							 if(voterId != null)
							 {
								 VoterModification voterModification = new VoterModification();
								 voterModification.setVoterId(voterId);
								 voterModification.setPublicationDateId(publicationDateId);
								 voterModification.setStatus(voterVO2.getStatus());
								 voterModificationDAO.save(voterModification);
							 }
						 }
						 voterDAO.flushAndclearSession();
					 }
				 }
				 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				 return resultStatus;
				 
			 }catch (Exception e) {
				 log.error("Exception Occured in moveVotersModificationDataFromTempToMainTable() Method");
				 log.error("Exception is - "+e);
				 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				 resultStatus.setExceptionEncountered(e);
				 return resultStatus;
			 }
		 }
		 
		 /**
		 * This method will return Voter Ids and their Voter Id Card Numbers, when we pass 
		 * Voter Id Card Numbers List as Parameter
		 * 
		 * @author Kamalakar Dandu
		 * @param List<String> voterIdCardNosList
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public List<SelectOptionVO> getVoterIdsByCardNosList(List<String> voterIdCardNosList)
		 {
			 log.debug("Entered into getVoterIdsByCardNosList() Method");
			 List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
			 try{
				 List<Object[]> list = new ArrayList<Object[]>(0);
				 List<Object[]> list2 = new ArrayList<Object[]>(0);
				 
				 int startindex = 0;
				 int maxResults = 1000;
				 
				 for(;;)
				 {
					 if(voterIdCardNosList.size() <= 1000)
					 {
						 list2 = voterDAO.getVoterIdsByCardNos(voterIdCardNosList);
						 list.addAll(list2);
						 break;
					 }
					 else
					 {
						 list2 = voterDAO.getVoterIdsByCardNos(voterIdCardNosList.subList(startindex, maxResults));
						 list.addAll(list2);
						 for(int delIndex=0;delIndex<maxResults;delIndex++)
							 voterIdCardNosList.remove(0);
					 }
				 }
				 
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
						 resultList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 }
				 return resultList;
			 }catch (Exception e) {
				 log.error("Exception Occured in getVoterIdsByCardNosList() Method");
				 log.error("Exception is - "+e);
				 return resultList;
			 }
		 }
		 
		 /**
		 * This method will return Voter Id, when we pass Voter Id Card Number as Parameter
		 * 
		 * @author Kamalakar Dandu
		 * @param String voterIdCardNo
		 * @param List<String> voterIdsList
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public Long getVoterIdByVoterIdCardNo(String voterIdCardNo,List<SelectOptionVO> voterIdsList)
		 {
			 try{
				 for(SelectOptionVO optionVO : voterIdsList)
					 if(optionVO.getName().equalsIgnoreCase(voterIdCardNo))
						 return optionVO.getId();
				 return null;
			 }catch (Exception e) {
				 log.error("Exception Occured in getVoterIdByVoterIdCardNo() Method");
				 log.error("Exception is - "+e);
				 return null;
			 }
		 }
		 
		 /**
		 * This method will return List of Constituencies, which are to be the mapped for
		 * Modified Voter data
		 * 
		 * @author Kamalakar Dandu
		 * @return List<{@link SelectOptionVO}>
		 * 
		 */
		 public List<SelectOptionVO> getConstituenciesToBeMappedForVoterChanges()
		 {
			 log.debug("Entered into getConstituenciesToBeMappedForVoterChanges() Method");
			 List<SelectOptionVO> result = new ArrayList<SelectOptionVO>(0);
			 try{
				 List<Object[]> list = voterModificationTempDAO.getConstituenciesToBeMappedForVoterChanges();
				 
				 if(list != null && list.size() > 0)
					 for(Object[] params : list)
						 result.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 return result;
			 }catch (Exception e) {
				 log.error("Exception Occured in getConstituenciesToBeMappedForVoterChanges() Method");
				 log.error("Exception is - "+e);
				 return result;
			 }
		 }
		 
		 
			
	 /**This method will return multiple families information to edit
	  * @author Samba Penugonda
	  * @param familiesList , families information
	  * @param userId , logged in user id
	  * @return all the existing voters details of selected families 
	  */
	public List<VoterHouseInfoVO> getMultipleFamiliesInformation(
			List<VoterHouseInfoVO> familiesList, Long userId) {
		log.debug("Entered into the getMultipleFamiliesInformation service method");
		 List<VoterHouseInfoVO> votersInfo = new ArrayList<VoterHouseInfoVO>();
		 
		 try{
			 for(VoterHouseInfoVO family : familiesList){
				 votersInfo.addAll(getFamilyInformation(family.getBoothId(),family.getPublicationId(),family.getHouseNo(),userId));
			 }
			 for(int i =0; i<votersInfo.size();i++){
				 VoterHouseInfoVO voter = votersInfo.get(i);
				 voter.setsNo(new Long(i+1));
			 }
		 }catch(Exception e){			 
			 log.error("Exception raised in  getMultipleFamiliesInformation service method");
			 e.printStackTrace();
		 }
		 return votersInfo;
	 }
		 
		 
	/**
	 * This method will return all the categories exist for logged in user
	 * @author Samba Penugonda
	 * @param userId , logged in user id
	 * @return all the categories of logged in user	 
	 */
	 public List<SelectOptionVO> getUserCategoryValuesByUserId(Long userId){
			log.debug("Entered into the getUserCategoryValuesByUserId service method");
			List<SelectOptionVO> optionsList = new ArrayList<SelectOptionVO>();
			
			try{
				List<UserVoterCategory> categories =userVoterCategoryDAO.getUserCategoriesByUserId(userId);
				
				for(UserVoterCategory userVoterCategory:categories){
					SelectOptionVO optionVO = new SelectOptionVO();
					
					optionVO.setId(userVoterCategory.getUserVoterCategoryId());
					optionVO.setName(userVoterCategory.getCategoryName());
					optionsList.add(optionVO);
				}
				
			}catch(Exception e){
			  log.debug("Exception raised in  getUserCategoryValuesByUserId service method");
			   e.printStackTrace();
			}
			
			return optionsList;
		}
	 
}
