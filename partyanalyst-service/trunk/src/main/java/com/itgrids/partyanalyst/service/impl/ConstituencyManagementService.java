package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.HamletBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.HamletsListWithBoothsAndVotersVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupsInfoVO;
import com.itgrids.partyanalyst.dto.MPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.UserGroupBasicInfoVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ConstituencyManagementService implements IConstituencyManagementService{
	
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionDAO electionDAO;
	private IPersonalUserGroupDAO personalUserGroupDAO;

	private static final Logger log = Logger.getLogger(ConstituencyManagementService.class);
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public IBoothConstituencyElectionVoterDAO getBoothConstituencyElectionVoterDAO() {
		return boothConstituencyElectionVoterDAO;
	}

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}

	public List<VoterVO> getVoterInfo(Long hamletId, String year, Long voterId, Integer maxRecords, Boolean isPrev){
		List voters = null;
		if(isPrev)
			voters = boothConstituencyElectionVoterDAO.findPrevVotersFromViterIdByHamletAndElectionYear(hamletId, year, voterId, maxRecords);
		else
			voters = boothConstituencyElectionVoterDAO.findNextVotersFromViterIdByHamletAndElectionYear(hamletId, year, voterId, maxRecords);
		List<VoterVO> voterVOs = new ArrayList<VoterVO>();
		VoterVO voterVO = null;
		Long count = 1l;
		for(Object[] voter:(List<Object[]>)voters){
			voterVO = new VoterVO();
			voterVO.setSNO((count++)+"");
			voterVO.setVoterFirstName(voter[0].toString()+ voter[1].toString());
			voterVO.setHouseNo(voter[2].toString());
			voterVO.setAge(Long.parseLong(voter[3].toString()));
			voterVO.setCast(voter[4].toString());
			voterVO.setCastCatagery(voter[5].toString()+" "+voter[6].toString());
			voterVO.setGender(voter[7].toString());
			voterVO.setRelativeFirstName(voter[8].toString()+" "+voter[9].toString());
			voterVO.setRelationshipType(voter[10].toString());
			voterVOs.add(voterVO);
		}
		voters = null;
		return voterVOs;
	}

	public VoterCastInfoVO getVotersCastInfoForHamlet(Long hamletId, String year){		
		return caluculatePercentage(boothConstituencyElectionVoterDAO.findVotersCastInfoByHamletAndElectionYear(hamletId, year));
	}
	
	/**
	 * @param	parms
	 * @return  constituencyManagementVO  to the corresponding calling method.
	 */

	public VoterCastInfoVO caluculatePercentage(List parms){	
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		List<CastVO> castVOs = new ArrayList<CastVO>();
		Set<String> casts = new HashSet<String>(); 	
		CastVO castVO = null;
		
		Long totalVoters=0L;	
		Long maleVoters=0L;
		Long femaleVoters=0L;
		Long castCount = 0L;
		
		String prevCast = "";
		String cast = "";
		
		for(int i=0;i < parms.size();i++){
			Object[] voterInfo  = (Object[]) parms.get(i);
			totalVoters = totalVoters + (Long)voterInfo[0];			
			String gender = (String)voterInfo[1];
			prevCast = cast;
			cast = (String)voterInfo[2];
			if(gender.equalsIgnoreCase("m"))
				maleVoters = maleVoters + (Long)voterInfo[0];
			if(casts.add(cast)&& (!prevCast.equals(""))){
				castVO = new CastVO();
				castVO.setCastName(prevCast);
				castVO.setCastCount(castCount);
				castVOs.add(castVO);
				castCount = 0L;
			}
			castCount = castCount + (Long)voterInfo[0];
			
			//Adding the Last Cast Info To the List
			if(i == parms.size() - 1){
				castVO = new CastVO();
				castVO.setCastName(prevCast);
				castVO.setCastCount(castCount--);
				castVOs.add(castVO);
			}
		}
		
		femaleVoters = totalVoters-maleVoters;
		
		//Cast Percentage Calculation
		for(int i=0; i < castVOs.size(); i++){
			String castPercent = new BigDecimal((castVOs.get(i).getCastCount() * 100.0)/totalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			castVOs.get(i).setCastPercentage(castPercent);
		}
					
		voterCastInfoVO.setTotalVoters(totalVoters);
		voterCastInfoVO.setMaleVoters(maleVoters);
		voterCastInfoVO.setFemaleVoters(femaleVoters);
		voterCastInfoVO.setCastVOs(castVOs);
				
		return voterCastInfoVO;				
	}
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId,String year, Long voterId, Integer maxRecords, Boolean isPrev) {	
		List voters = null;
		if(isPrev)
			voters = boothConstituencyElectionVoterDAO.findPrevVotersFromViterIdByHamletAndElectionYear(hamletId, year, voterId, maxRecords);
		else
			voters = boothConstituencyElectionVoterDAO.findNextVotersFromViterIdByHamletAndElectionYear(hamletId, year, voterId, maxRecords);
			
		Map<String, List<VoterVO>> voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		for(Object[] voter : (List<Object[]>)voters){
			houseNo = voter[2].toString();
			voterVO = new VoterVO();
			voterVO.setVoterFirstName(voter[0].toString());
			voterVO.setVoterLastName(voter[1].toString());
			voterVO.setAge((Long)voter[3]);
			voterVO.setCast(voter[4].toString());
			voterVOs = voterByHouseNoMap.get(houseNo);
			if(voterVOs ==null)
				voterVOs = new ArrayList<VoterVO>();
			voterVOs.add(voterVO);
			voterByHouseNoMap.put(houseNo, voterVOs);
			
		}
		for(Map.Entry<String, List<VoterVO>> entry:voterByHouseNoMap.entrySet()){
			voterHouseInfoVO = new VoterHouseInfoVO();
			voterVOs = entry.getValue();
			if(voterVOs.size() == 0)
				continue;
			voterHouseInfoVO.setHouseNo(entry.getKey());
			voterHouseInfoVO.setCast(voterVOs.get(0).getCast());
			voterHouseInfoVO.setYounger(voterVOs.get(0).getVoterFirstName());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getVoterFirstName());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		
		return voterHouseInfoVOs;
	}
	/**
	 * retrieving the mptc election candidate results.
	 * @param mandalID
	 * @return
	 */
	public TotalMPTCMandalLeaderVO getMPTCElectionResultForMandal(Long mandalID){
		log.debug(" getMPTCElectionResultForMandal() start....");
		List result = candidateResultDAO.getMPTCElectionResultForMandal(mandalID);
		TotalMPTCMandalLeaderVO totalMPTCMandalLeaderVO = new TotalMPTCMandalLeaderVO();
		
		List<MPTCMandalLeaderVO> totalLeaders = totalMPTCMandalLeaderVO.getTotalLeaders();
		List<MPTCMandalLeaderVO> winners = totalMPTCMandalLeaderVO.getWinners();
		for(int i=0; i<result.size(); i++){
			Object[] obj = (Object[]) result.get(i);
			MPTCMandalLeaderVO mptcMandalLeaderVO = new MPTCMandalLeaderVO();
			mptcMandalLeaderVO.setElectionYear(obj[0].toString()); 
			mptcMandalLeaderVO.setRank(new Long(obj[1].toString()));

			mptcMandalLeaderVO.setMptcName(obj[2].toString());
			mptcMandalLeaderVO.setParty(obj[3].toString());
			mptcMandalLeaderVO.setCandidateName(obj[4].toString());
			mptcMandalLeaderVO.setCandidateEarnedVotes(formatedData(obj[5]));
			mptcMandalLeaderVO.setValidVotes(formatedData(obj[6]));
			
			totalLeaders.add(mptcMandalLeaderVO);
			if(mptcMandalLeaderVO.getRank().equals(new Long(1))){
				winners.add(mptcMandalLeaderVO);
			}
		}
		
		return totalMPTCMandalLeaderVO;
	}
	
	private Double formatedData(Object obj){
		Double result = new Double(0);
		try{
			result = new Double(obj.toString().trim());
		}catch(NumberFormatException ex){
			result = new Double(0);
		}
		return result;
	}
	
	/**
	 * retrieving all hamlets and corresponding booths and voters for specific revenue village and election
	 * @param revenueVillageID
	 * @param year
	 * @param electionType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HamletsListWithBoothsAndVotersVO getAllHamletBoothInfoForRevenueVillage(Long revenueVillageID, String year, String electionType){

		log.debug("ConstituencyManagementService.getAllHamletBoothInfoForRevenueVillage() started22...");
		HamletsListWithBoothsAndVotersVO hamletsListWithBoothsAndVotersVO = new HamletsListWithBoothsAndVotersVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			List years =electionDAO.findLatestElectionYear(electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				hamletsListWithBoothsAndVotersVO.setExceptionEncountered(ex);
				return hamletsListWithBoothsAndVotersVO;
			}/*
			Object[] columns = (Object[])years.get(0);
			year = columns[0].toString();*/
			year =(String)years.get(0);
		}
		List<HamletBoothsAndVotersVO> hamletsListWithBoothsAndVotersList = new ArrayList<HamletBoothsAndVotersVO>();
		log.debug("Total Hamlet Size="+hamletsListWithBoothsAndVotersList.size());
		List listHamletsVoters = boothConstituencyElectionVoterDAO.findTotalVotersForHamlet(revenueVillageID, year, electionType);
		log.debug("Total Hamlet Size="+listHamletsVoters.size());
		List listHamletBooths = boothConstituencyElectionVoterDAO.findHamletBoothsForRevenueVillage(revenueVillageID, year, electionType);
		log.debug("Total Booths including duplicate Size="+listHamletBooths.size());
		Map<String, StringBuilder> hamletBooths = new HashMap<String, StringBuilder>();
		int size = listHamletBooths.size();

		for(int i=0; i<size; i++){
			Object[] obj = (Object[]) listHamletBooths.get(i);
			String hamletName = obj[0].toString();
			String boothPartNo = obj[1].toString();
			String boothId = obj[2].toString();
			StringBuilder value = hamletBooths.get(hamletName);
			if(value==null){
				value= new StringBuilder();
			}
			value.append(IConstants.COMMA).append("<a href='boothReport.action?partNo="+boothPartNo+"&boothID="+boothId+"'>"+boothPartNo+"</a>");
			hamletBooths.put(hamletName, value);
		}
		
		size = listHamletsVoters.size();
		for(int i=0; i<size; i++){
			HamletBoothsAndVotersVO hamletBoothsAndVotersVO = new HamletBoothsAndVotersVO();
			Object[] obj = (Object[]) listHamletsVoters.get(i);
			String hamletId = obj[0].toString();
			String hamletName = obj[1].toString();
			String voters = obj[2].toString();
			hamletBoothsAndVotersVO.setHamletID(new Long(hamletId));
			hamletBoothsAndVotersVO.setHamletName(hamletName);
			hamletBoothsAndVotersVO.setHamletNameURL("<a href='hamletReport.action?hamletID="+hamletId+"&hamletName="+hamletName+"'>"+hamletName+"</a>");
			hamletBoothsAndVotersVO.setTotalVoters(new Long(voters));
			StringBuilder boothsLink = hamletBooths.get(hamletName);
			if(boothsLink!=null){
				hamletBoothsAndVotersVO.setBoothPartNos(boothsLink.substring(1));
			}
			hamletsListWithBoothsAndVotersList.add(hamletBoothsAndVotersVO);
		}
		log.debug("total size of hamlets::::"+hamletsListWithBoothsAndVotersList.size());
		hamletsListWithBoothsAndVotersVO.setHamletsListWithBoothsAndVoters(hamletsListWithBoothsAndVotersList);
		return hamletsListWithBoothsAndVotersVO;
	}
	
	public List<LocalUserGroupsInfoVO> getLocalUserGroupsCandidatesByAccesstypeAndAccessValues(Long userId, String accessType, String accessValue){
		String compareLocationInfo = "";
		List rawData = null;
		List<LocalUserGroupsInfoVO> allCategoriesInfo = new ArrayList<LocalUserGroupsInfoVO>();
		LocalUserGroupsInfoVO localUserGroupsInfoVO = null;
		Map<Long, List<UserGroupBasicInfoVO>> groupInfoMapByCategories = new HashMap<Long, List<UserGroupBasicInfoVO>>();
		if(IConstants.STATE.equalsIgnoreCase(accessType))
			compareLocationInfo = "model.localGroupRegion.state.stateId";
		else if(IConstants.DISTRICT.equalsIgnoreCase(accessType))
			compareLocationInfo = "model.localGroupRegion.district.districtId";
		else if(IConstants.MLA.equalsIgnoreCase(accessType) || 
				IConstants.MP.equalsIgnoreCase(accessType))
			compareLocationInfo = "model.localGroupRegion.constituency.constituencyId";
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.hamlet.hamletId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Villages");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.ward.constituencyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Wards");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.tehsil.tehsilId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Tehsils");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.localBody.localElectionBodyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Towns/Cities");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.constituency.constituencyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Constituencies");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.district.districtId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Districts");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.state.stateId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "States");
		
		Long categoryGroupsCount = 0l;
		for(Map.Entry<Long, List<UserGroupBasicInfoVO>> entry:groupInfoMapByCategories.entrySet()){
			localUserGroupsInfoVO = new LocalUserGroupsInfoVO();
			localUserGroupsInfoVO.setCategoryId(entry.getKey());
			localUserGroupsInfoVO.setDescription(entry.getValue().get(0).getCategoryName());
			
			for(UserGroupBasicInfoVO basicInfoVO:entry.getValue())
				categoryGroupsCount +=	basicInfoVO.getGroupsCount();
			
			localUserGroupsInfoVO.setGroupsCount(categoryGroupsCount);
			allCategoriesInfo.add(localUserGroupsInfoVO);
		}
		
		return allCategoriesInfo;
	}

	private void consolidatedMapForCategories(List rawData,
			Map<Long, List<UserGroupBasicInfoVO>> groupInfoMapByCategories,
			String areaType) {
		UserGroupBasicInfoVO basicInfoVO = null;
		List<UserGroupBasicInfoVO> groupsInfo = null;
		for(Object[] values:(List<Object[]>)rawData){
			basicInfoVO = new UserGroupBasicInfoVO();
			basicInfoVO.setAreaType(areaType);
			basicInfoVO.setCategoryName(values[1].toString());
			basicInfoVO.setGroupsCount(Long.parseLong(values[3].toString()));
			basicInfoVO.setLocationsCount(Long.parseLong(values[2].toString()));
			groupsInfo = groupInfoMapByCategories.get(values[0]);
			if(groupsInfo == null)
				groupsInfo = new ArrayList<UserGroupBasicInfoVO>();
			groupsInfo.add(basicInfoVO);
			groupInfoMapByCategories.put(Long.parseLong(values[0].toString()), groupsInfo);
		}
	}
	
}
