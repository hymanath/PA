package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IConstituencyManagementService;

public class ConstituencyManagementService implements IConstituencyManagementService{/*
	
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private IHamletDAO hamletDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionDAO electionDAO;
	private IPersonalUserGroupDAO personalUserGroupDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IProblemProgressDAO problemProgressDAO;
	private IConstituencySubscriptionsDAO constituencySubscriptionsDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IVoterDAO voterDAO;
	
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	private static final Logger log = Logger.getLogger(ConstituencyManagementService.class);
	
	
	public IConstituencySubscriptionsDAO getConstituencySubscriptionsDAO() {
		return constituencySubscriptionsDAO;
	}

	public void setConstituencySubscriptionsDAO(
			IConstituencySubscriptionsDAO constituencySubscriptionsDAO) {
		this.constituencySubscriptionsDAO = constituencySubscriptionsDAO;
	}

	public IProblemProgressDAO getProblemProgressDAO() {
		return problemProgressDAO;
	}

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}

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

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public List<VoterVO> getVoterInfo(Long hamletId,String year, Integer startIndex, Integer maxRecords, String order, String columnName) {	
		
		if(!"desc".equalsIgnoreCase(order))
			order = "";
		
		//Long partNo = null;
		
		List<Object[]> voters = boothConstituencyElectionVoterDAO.findVotersForPanchayatAndElectionYearByStartAndMaxResults(hamletId, year, 
				startIndex, maxRecords, order, columnName);
		
		//List<Object[]> votersForPollingStation = boothConstituencyElectionVoterDAO.findVotersForBoothAndElectionYearByStartAndMaxResults(hamletId, "2009", partNo, startIndex, maxRecords, order, columnName);
		//Hamlet
		//Long totalRecords = (Long)boothConstituencyElectionVoterDAO.findTotalVotersCountByHamletAndElectionYear(hamletId, year).get(0);
		Long totalRecords = (Long)boothConstituencyElectionVoterDAO.findTotalVotersCountByPanchayatAndElectionYear(hamletId, year).get(0);
		
		List<VoterVO> voterVOs = new ArrayList<VoterVO>();
		VoterVO voterVO = null;
		Long count = new Long(startIndex);
		for(Object[] voter:(List<Object[]>)voters){
			voterVO = new VoterVO();
			voterVO.setVoterId((++count)+"");
			//voterVO.setFirstName(voter[0].toString()+ voter[1].toString());
			voterVO.setFirstName(voter[0].toString());
			voterVO.setHouseNo(voter[1].toString());
			voterVO.setAge(Long.parseLong(voter[2].toString()));
			voterVO.setCast(voter[3].toString());
			voterVO.setCastCatagery(voter[4].toString()+" "+voter[5].toString());
			voterVO.setGender(voter[6].toString());
			//voterVO.setRelativeFirstName(voter[8].toString()+" "+voter[9].toString());
			voterVO.setRelativeFirstName(voter[7].toString());
			voterVO.setRelationshipType(voter[8].toString());
			voterVOs.add(voterVO);
		}

		if(voterVOs.size() > 0)
			voterVOs.get(0).setTotalVoters(totalRecords);
		return voterVOs;
	}
//VoterCastInfo for Hamlet
	public VoterCastInfoVO getVotersCastInfoForHamlet(Long hamletId, String year){		
		return caluculatePercentage(boothConstituencyElectionVoterDAO.findVotersCastInfoByHamletAndElectionYear(hamletId, year));
		
	}
	//Voter Cast Info for panchayat
	public VoterCastInfoVO getVotersCastInfoForPanchayat(Long panchayatId, String year){		
		return caluculatePercentage(boothConstituencyElectionVoterDAO.findVotersCastInfoByPanchayatAndElectionYear(panchayatId, year));
		
	}
	
	//Voter Cast Info for PollingStation
	public VoterCastInfoVO getVotersCastInfoForPollingStation(Long panchayatId, String year){		
		return caluculatePercentage(voterDAO.findVotersCastInfoByPollingStationAndElectionYear(panchayatId, year));
		
	}
	
	//Voter Cast Info for Constituency
	public VoterCastInfoVO getVotersCastInfoForAssembly(Long panchayatId, String year){		
		return caluculatePercentage(voterDAO.findVotersCastInfoByConstituencyAndElectionYear(panchayatId, year));
		
	}
	
	//voter Cast Info For Mandal
	public VoterCastInfoVO getVotersCastInfoForMandal(Long panchayatId, String year){		
		return caluculatePercentage(voterDAO.findVotersCastInfoByMandalAndElectionYear(panchayatId, year));
		
	}
	
	//voter Cast Info For Urban
		public VoterCastInfoVO getVotersCastInfoForUrban(Long assemblyLocalBodyId, String year){
			Long urbanId = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByassemblyLocalElectionBodyId(assemblyLocalBodyId);
			return caluculatePercentage(voterDAO.findVotersCastInfoByUrbanAndElectionYear(urbanId, year));
			
		}
	*//**
	 * @param	parms
	 * @return  constituencyManagementVO  to the corresponding calling method.
	 *//*

	public VoterCastInfoVO caluculatePercentage(List parms){	
		VoterCastInfoVO voterCastInfoVO = new VoterCastInfoVO();
		List<CastVO> castVOs = new ArrayList<CastVO>();
		Set<String> casts = new HashSet<String>(); 	
		CastVO castVO = null;
		
		Long totalVoters=0L;	
		Long maleVoters=0L;
		Long femaleVoters=0L;
		Long castCount = 0L;
		Long castnoneCount=0l;
		Long castNone=0l;
		
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
		
		

		for(int i=0;i < parms.size();i++){
			Object[] voterInfo  = (Object[]) parms.get(i);
			totalVoters = totalVoters + (Long)voterInfo[0];			
			String gender = (String)voterInfo[1];
			
			cast = (String)voterInfo[2];
			if(gender.equalsIgnoreCase("m"))
				maleVoters = maleVoters + (Long)voterInfo[0];
			if(cast.equals("") ){
				castVO = new CastVO();
				cast = "N/A";
				casts.add("N/A");
			}
			else{
			casts.add(cast);
			}
				castVO = new CastVO();
				
				castVO.setCastName(cast);
				castVO.setCastCount((Long) (voterInfo[0]));
				castVOs.add(castVO);
			
			
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
	public List<Long> getVoterAgeDetailsForPanchayat(Long panchayatId, String year,String checkedEle)
	{
		Long abovevotersCount = null;
		Long between18To25VotersCount = null;
		Long between25To35VotersCount = null;
		Long between35To50VotersCount = null;
		Long between50To60VotersCount = null;
		
		Long minAge =0l;
		Long maxAge =0l;
		List<Long> returnVal = new ArrayList<Long>();
		if(checkedEle.equalsIgnoreCase("panchayat"))
		{
			abovevotersCount = boothConstituencyElectionVoterDAO.getVotersAboveAgeInfoForPanchayatAndElectionYear(panchayatId, year);
			between18To25VotersCount=boothConstituencyElectionVoterDAO.getVotersAgeInfoForPanchayatAndElectionYear(panchayatId, year,18l,25l);
			between25To35VotersCount=boothConstituencyElectionVoterDAO.getVotersAgeInfoForPanchayatAndElectionYear(panchayatId, year,25l,35l);
			between35To50VotersCount=boothConstituencyElectionVoterDAO.getVotersAgeInfoForPanchayatAndElectionYear(panchayatId, year,35l,55l);
			between50To60VotersCount=boothConstituencyElectionVoterDAO.getVotersAgeInfoForPanchayatAndElectionYear(panchayatId, year,50l,60l);
		}
		
		if(checkedEle.equalsIgnoreCase("pollingstation"))
		{
			abovevotersCount = voterDAO.getVotersAboveAgeInfoForPollingstationAndElectionYear(panchayatId, year);
			between18To25VotersCount=voterDAO.getVotersAgeInfoForPollingstationAndElectionYear(panchayatId, year,18l,25l);
			between25To35VotersCount=voterDAO.getVotersAgeInfoForPollingstationAndElectionYear(panchayatId, year,25l,35l);
			between35To50VotersCount=voterDAO.getVotersAgeInfoForPollingstationAndElectionYear(panchayatId, year,35l,55l);
			between50To60VotersCount=voterDAO.getVotersAgeInfoForPollingstationAndElectionYear(panchayatId, year,50l,60l);
		}
		returnVal.add(abovevotersCount);
		returnVal.add(between18To25VotersCount);
		returnVal.add(between25To35VotersCount);
		returnVal.add(between35To50VotersCount);
		returnVal.add(between50To60VotersCount);
		
		
		return returnVal;
	}
	
	
	public List<Long> getVoterHouseDetailsForPanchayat(Long panchayatId, String year,String checkedEle)
	{
		List<Object[]> voters = null;
		if(checkedEle.equalsIgnoreCase("panchayat"))
		{
		voters = boothConstituencyElectionVoterDAO.getVotersInfoForPanchayatAndElectionYear(panchayatId, year);
		}
		if(checkedEle.equalsIgnoreCase("pollingstation"))
		{
		 voters = voterDAO.getVotersInfoForPollingStationAndElectionYear(panchayatId, year);
		}
		List<VoterVO> votersList = new ArrayList<VoterVO>();
		Map<String,List<VoterVO>> resultMap = new LinkedHashMap<String,List<VoterVO>>();
		List<VoterVO> aboveten = new ArrayList<VoterVO>();
		
		 Long totalMembers=0l;
		resultMap.put("Below-3", new ArrayList<VoterVO>(0));
		resultMap.put("7-5", new ArrayList<VoterVO>(0));
		resultMap.put("10-7", new ArrayList<VoterVO>(0));
		resultMap.put("Above-10", new ArrayList<VoterVO>(0));
	    List<Long> returnVal = new ArrayList<Long>();
		Long below3 = 0l;
		Long bt3to5 = 0l;
		Long bt5to7 = 0l;
		Long bt7to10 = 0l;
		Long above10 = 0l;
		
		Long above10Total = 0l;
		Long below3Total = 0l;
		Long bt3to5Total = 0l;
		Long bt5to7Total = 0l;
		Long bt7to10Total = 0l;
		
		String houseNo = "";
		for(Object[] count : voters)
		{
			
			totalMembers = (Long) count[1];
			if(totalMembers.longValue() >= 10)
			{
				
				above10 = above10+1;
				above10Total = totalMembers + above10Total;
			}
			else if(totalMembers.longValue()< 10 && totalMembers.longValue() >=7)
			{
				bt7to10 = bt7to10+1;
				bt7to10Total = totalMembers + bt7to10Total;
			}
			else if(totalMembers.longValue()< 7 && totalMembers.longValue() >=5)
			{
				bt5to7 = bt7to10+1;
				bt5to7Total = totalMembers + bt5to7Total;
			}
			else if(totalMembers.longValue()<5 && totalMembers.longValue() >=3)
			{
				bt3to5 = bt7to10+1;
				bt3to5Total = totalMembers + bt3to5Total;
			}
			else
			{
				below3 = below3+1;
				below3Total = totalMembers + below3Total;
			}
			
		}	
		
		returnVal.add(below3);
		returnVal.add(bt3to5);
		returnVal.add(bt5to7);
		returnVal.add(bt7to10);
		returnVal.add(above10);
		returnVal.add(above10Total);
		returnVal.add(bt7to10Total);
		returnVal.add(bt5to7Total);
		returnVal.add(bt3to5Total);
		returnVal.add(below3Total);
		return returnVal;
		
	}
	//House Info Details for Panchayat/Polling Station
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long hamletId, String year,String checkedEle)
	{
		List voters = null;
		if(checkedEle.equalsIgnoreCase("panchayat"))
		{
			voters = boothConstituencyElectionVoterDAO.findVotersInfoForPanchayatAndElectionYear(hamletId, year);
		}
		if(checkedEle.equalsIgnoreCase("pollingstation"))
		{
			voters = voterDAO.findVotersInfoForPollingStationAndElectionYear(hamletId, year);
		}
		Map<String, List<VoterVO>> voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		if(voters != null)
		for(Object[] voter : (List<Object[]>)voters){
			houseNo = voter[1].toString();
			voterVO = new VoterVO();
			//voterVO.setFirstName(voter[0].toString());
			//voterVO.setVoterLastName(voter[1].toString());
			voterVO.setFirstName(voter[0].toString());
			voterVO.setAge((Long)voter[2]);
			voterVO.setCast(voter[3].toString());
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
			voterHouseInfoVO.setYounger(voterVOs.get(0).getFirstName());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getFirstName());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		
		if(voterHouseInfoVOs.size() > 0)
			voterHouseInfoVOs.get(0).setTotalHousesCount(totalRecords);
		return voterHouseInfoVOs;
	}
	
	
	public List<VoterHouseInfoVO> getVoterHouseDetails(Long hamletId, String year) {	
		
		List voters = boothConstituencyElectionVoterDAO.findVotersInfoForHamletAndElectionYear(hamletId, year);
		
		//Long totalRecords = (Long)boothConstituencyElectionVoterDAO.findTotalVoterHousesCountByHamletAndElectionYear(hamletId, year).get(0);
		
		Map<String, List<VoterVO>> voterByHouseNoMap = new HashMap<String, List<VoterVO>>();
		List<VoterHouseInfoVO> voterHouseInfoVOs = new ArrayList<VoterHouseInfoVO>();
		VoterHouseInfoVO voterHouseInfoVO = null;
		List<VoterVO> voterVOs = null;
		VoterVO voterVO = null;
		String houseNo = "";
		for(Object[] voter : (List<Object[]>)voters){
			houseNo = voter[1].toString();
			voterVO = new VoterVO();
			//voterVO.setFirstName(voter[0].toString());
			//voterVO.setVoterLastName(voter[1].toString());
			voterVO.setFirstName(voter[0].toString());
			voterVO.setAge((Long)voter[2]);
			voterVO.setCast(voter[3].toString());
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
			voterHouseInfoVO.setYounger(voterVOs.get(0).getFirstName());
			voterHouseInfoVO.setElder(voterVOs.get(voterVOs.size()-1).getFirstName());
			voterHouseInfoVO.setNumberOfPeople(voterVOs.size());
			voterHouseInfoVOs.add(voterHouseInfoVO);
		}
		
		if(voterHouseInfoVOs.size() > 0)
			voterHouseInfoVOs.get(0).setTotalHousesCount(totalRecords);
		return voterHouseInfoVOs;
	}
	*//**
	 * retrieving the mptc election candidate results.
	 * @param mandalID
	 * @return
	 *//*
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
	
	*//**
	 * retrieving all hamlets and corresponding booths and voters for specific revenue village and election
	 * @param revenueVillageID
	 * @param year
	 * @param electionType
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public HamletsListWithBoothsAndVotersVO getAllHamletBoothInfoForRevenueVillage(Long revenueVillageID, String year, String electionType){

		log.debug("ConstituencyManagementService.getAllHamletBoothInfoForRevenueVillage() started22...");
		HamletsListWithBoothsAndVotersVO hamletsListWithBoothsAndVotersVO = new HamletsListWithBoothsAndVotersVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			//List years =electionDAO.findLatestElectionYear(electionType);
			List<Object> years = villageBoothElectionDAO.findLatestElectionYearInARevenueVillageForElectionType(revenueVillageID, electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				hamletsListWithBoothsAndVotersVO.setExceptionEncountered(ex);
				return hamletsListWithBoothsAndVotersVO;
			}
			year =years.get(0).toString();
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
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.state.stateId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "States");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.district.districtId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Districts");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.constituency.constituencyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Constituencies");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.localBody.localElectionBodyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Towns/Cities");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.tehsil.tehsilId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Tehsils");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.ward.constituencyId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Wards");
		
		rawData = personalUserGroupDAO.findAllGroupCategoriesInfoAndCountsOfLocationsByLocation("model.localGroupRegion.hamlet.hamletId", userId, 
				Long.parseLong(accessValue), compareLocationInfo);
		if(rawData.size() > 0)
			consolidatedMapForCategories(rawData, groupInfoMapByCategories, "Villages");
		
		
		for(Map.Entry<Long, List<UserGroupBasicInfoVO>> entry:groupInfoMapByCategories.entrySet()){
			localUserGroupsInfoVO = new LocalUserGroupsInfoVO();
			localUserGroupsInfoVO.setCategoryId(entry.getKey());
			localUserGroupsInfoVO.setGroupCategoryName(entry.getValue().get(0).getCategoryName());
			localUserGroupsInfoVO.setGroupsCount(new Long(personalUserGroupDAO.findGroupsInfoByCategoryAndUserId(userId, entry.getKey()).size()));
			localUserGroupsInfoVO.setLocationsWiseGroupInfo(entry.getValue());
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
			if(Long.parseLong(values[2].toString()) == 0)
				continue;
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
	
	public List<UserGroupDetailsVO> findUserGroupsByLocationCategoryAndUserId(Long userId, Long categoryId, String locationType){
		List<UserGroupDetailsVO> userGroups = new ArrayList<UserGroupDetailsVO>();
		List<PersonalUserGroup> groupModels = null;
		UserGroupDetailsVO groupDetailsVO = null;
		String regionInfo = "";
		if(IConstants.STATE.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.state.stateId";
		else if(IConstants.DISTRICT.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.district.districtId";
		else if(IConstants.CONSTITUENCY.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.constituency.constituencyId";
		else if(IConstants.TEHSIL.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.tehsil.tehsilId";
		else if(IConstants.WARD.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.ward.constituencyId";
		else if(IConstants.HAMLET.equalsIgnoreCase(locationType))
			regionInfo = "model.localGroupRegion.hamlet.hamletId";
		
		if(locationType.length() > 0 && regionInfo.length() > 0)
			groupModels = personalUserGroupDAO.findGroupsInfoByCategoryAndUserIdByRegion(userId, categoryId, regionInfo);
		else
			groupModels = personalUserGroupDAO.findGroupsInfoByCategoryAndUserId(userId, categoryId);
		
		for(PersonalUserGroup group:groupModels){
			groupDetailsVO = new UserGroupDetailsVO();
			groupDetailsVO.setGroupId(group.getPersonalUserGroupId());
			groupDetailsVO.setGroupName(group.getGroupName());
			groupDetailsVO.setGroupDesc(group.getDescription());
			if(group.getCreatedDate() != null)
				groupDetailsVO.setCreatedDate(group.getCreatedDate().toString());
			groupDetailsVO.setNoOfPersons(group.getStaticUserGroups().size()+"");
			if(group.getLocalGroupRegion().getHamlet() != null)
				groupDetailsVO.setLocationInfo(group.getLocalGroupRegion().getHamlet().getHamletName()+ " " +IConstants.VILLAGE);
			else if(group.getLocalGroupRegion().getWard() != null)
				groupDetailsVO.setLocationInfo(group.getLocalGroupRegion().getWard().getLocalElectionBody().getName()+ " " +
						group.getLocalGroupRegion().getWard().getLocalElectionBody().getElectionType().getElectionType()+" "+
						group.getLocalGroupRegion().getWard().getName());
			
			userGroups.add(groupDetailsVO);
		}
		
		return userGroups;
	}
	public Boolean getIsSubscribed(Long userId,Long constituencyId)
	{
		Boolean flag = false;
		try
		{
			Long count = constituencySubscriptionsDAO.getSubscriptionCount(userId,constituencyId);
			if(count.longValue() > 0)
				flag = true;
			return flag;
		}
			catch (Exception e) {
			log.error("Exception occured in getSubscriptionDetails() Method, Exception is - "+e);
			return flag;
		}
			
		
	}
	
	public ResultStatus subscriberDetails(Long id,Long userId)
	{
		log.debug("Entered Into subscriberDetails() Method of CandidateDetailsService");
		ResultStatus resultStatus = new ResultStatus();
		
		try{
			   List<ConstituencySubscriptions> constiuencyUpdatesEmails = constituencySubscriptionsDAO.getSubscriberDetails(id, userId);
			   
			    if(!(constiuencyUpdatesEmails.size() > 0))
			    {
			    	ConstituencySubscriptions constituencySubscriptions = new ConstituencySubscriptions();
					
			    	constituencySubscriptions.setConstituencyId(id);
			    	constituencySubscriptions.setUserId(userId);
			    	constituencySubscriptions.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			    	constituencySubscriptions.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			    	constituencySubscriptions = constituencySubscriptionsDAO.save(constituencySubscriptions);
			    }
			    return resultStatus;
			}
			catch(Exception e){
		
			log.error("Exception occured in subscriberDetails() Method, Exception is - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		  }
		}
		
		public ResultStatus unSubscriptionDetails(Long id,Long userId)
		{
			log.debug("Entered Into unSubscriptionDetails() Method of CandidateDetailsService");
			ResultStatus resultStatus = new ResultStatus();
			try{
				int flag=constituencySubscriptionsDAO.unSubscriptionDetails(id,userId);
				if (flag != 0)
					{
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					return resultStatus;
				    }
				else
					{
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
					}
							
				}
				catch(Exception e){
					
					log.error("Exception occured in unSubscriptionDetails() Method, Exception is - "+e);
					resultStatus.setExceptionEncountered(e);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}
	}
		

		public VotersInfoForMandalVO getBasicVotersInfo(Long constituencyId, String year,String checkedEle,Long flag)
		{
			VotersInfoForMandalVO votersInfoForMandalVO = null;
			List<Object[]> list = null;
			Long totalVotersCount = 0l;
			try{
				if(checkedEle !=null && checkedEle.equalsIgnoreCase("assembly"))
					list = voterDAO.getVotersBasicInfoByConstituencyId(constituencyId, year);
				else if(checkedEle !=null && checkedEle.equalsIgnoreCase("mandal") && flag == -1)
					list = voterDAO.getVotersBasicInfoByManadalId(constituencyId, year);
				else if(checkedEle !=null && checkedEle.equalsIgnoreCase("mandal") && flag != -1)
				{
					Long urbanId = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByassemblyLocalElectionBodyId(constituencyId);
					list = voterDAO.getVotersBasicInfoByUrbanId(urbanId, year);
				}
				else if(checkedEle !=null && checkedEle.equalsIgnoreCase("panchayat"))
					list = boothConstituencyElectionVoterDAO.getVotersBasicInfoByPanchayatId(constituencyId, year);
				else if(checkedEle !=null && checkedEle.equalsIgnoreCase("pollingStation"))
					list = voterDAO.getVotersBasicInfoByPollingStationId(constituencyId, year);
					
				if(list !=null && list.size() >0)
				{
					votersInfoForMandalVO = new VotersInfoForMandalVO(); 
					for(Object[] params : list)
					{
						if(params[0].toString() != null && params[1].toString() != null)
						{
							if(params[1].toString().equalsIgnoreCase("M"))
							{
								votersInfoForMandalVO.setTotalMaleVoters(params[0].toString());
								totalVotersCount += Long.parseLong(params[0].toString());
							}
							if(params[1].toString().equalsIgnoreCase("F"))
							{
								votersInfoForMandalVO.setTotalFemaleVoters(params[0].toString());
								totalVotersCount += Long.parseLong(params[0].toString());
							}
							if(params[1].toString().equalsIgnoreCase(""))
							{
								votersInfoForMandalVO.setUnKnowVoters(params[0].toString());
								totalVotersCount += Long.parseLong(params[0].toString());
							}
							votersInfoForMandalVO.setTotalVoters(totalVotersCount.toString());
						}
							
					}
				}
				
				return votersInfoForMandalVO;
			}catch (Exception e) {
				e.printStackTrace();
				return votersInfoForMandalVO;
			}
		}

	
	public Long getProblemVisibility(Long problemId){
		Long vsbltyId=0l;
			try{
				vsbltyId=problemProgressDAO.getVisibility(problemId);
				return vsbltyId;
			}
			catch(Exception e){
				log.error("Exception occured in getProblemVisibility() Method, Exception is - "+e);
				return vsbltyId;
			}
	}
		//voters Info for polling station
	
		public List<VoterVO> getVoterInfoForPollingStation(Long hamletId,String year,
				long partNo, Integer startIndex, Integer maxRecords,
				String order, String columnName) {
		
					
				 List<Object[]> voters = boothConstituencyElectionVoterDAO.findVotersForBoothAndElectionYearByStartAndMaxResults(hamletId, "2009",partNo, 
						startIndex, maxRecords, order, columnName);
				 
				 List<VoterVO> voterVOs = new ArrayList<VoterVO>();
					VoterVO voterVO = null;
					Long count = new Long(startIndex);
					for(Object[] voter:(List<Object[]>)voters){
						voterVO = new VoterVO();
						voterVO.setVoterId((++count)+"");
						voterVO.setFirstName(voter[0].toString()+ voter[1].toString());
						voterVO.setHouseNo(voter[2].toString());
						voterVO.setAge(Long.parseLong(voter[3].toString()));
						voterVO.setCast(voter[4].toString());
						voterVO.setCastCatagery(voter[5].toString()+" "+voter[6].toString());
						voterVO.setGender(voter[7].toString());
						voterVO.setRelativeFirstName(voter[8].toString()+" "+voter[9].toString());
						voterVO.setRelationshipType(voter[10].toString());
						voterVOs.add(voterVO);
					}

					
					return voterVOs;
				
			}
		
		public List<VoterVO> getVoterInfoForPollingStation(Long hamletId,String year,
				 Integer startIndex, Integer maxRecords,
				String order, String columnName) {
			
			if(!"desc".equalsIgnoreCase(order))
				order = "";
			
			//Long partNo = null;
			
			List<Object[]> voters = boothConstituencyElectionVoterDAO.findVotersForBoothAndElectionYearByStartAndMaxResults(hamletId, "2009", 
					startIndex, maxRecords, order, columnName);
			
			//List<Object[]> votersForPollingStation = boothConstituencyElectionVoterDAO.findVotersForBoothAndElectionYearByStartAndMaxResults(hamletId, "2009", partNo, startIndex, maxRecords, order, columnName);
			//Hamlet
			//Long totalRecords = (Long)boothConstituencyElectionVoterDAO.findTotalVotersCountByHamletAndElectionYear(hamletId, year).get(0);
			Long totalRecords = (Long) voterDAO.findTotalVotersCountByPollingStationAndElectionYear(hamletId, year).get(0);
			
			List<VoterVO> voterVOs = new ArrayList<VoterVO>();
			VoterVO voterVO = null;
			Long count = new Long(startIndex);
			for(Object[] voter:(List<Object[]>)voters){
				voterVO = new VoterVO();
				voterVO.setVoterId((++count)+"");
				//voterVO.setFirstName(voter[0].toString()+ voter[1].toString());
				voterVO.setFirstName(voter[0].toString());
				voterVO.setHouseNo(voter[1].toString());
				voterVO.setAge(Long.parseLong(voter[2].toString()));
				voterVO.setCast(voter[3].toString());
				voterVO.setCastCatagery(voter[4].toString()+" "+voter[5].toString());
				voterVO.setGender(voter[6].toString());
				//voterVO.setRelativeFirstName(voter[8].toString()+" "+voter[9].toString());
				voterVO.setRelativeFirstName(voter[7].toString());
				voterVO.setRelationshipType(voter[8].toString());
				voterVOs.add(voterVO);
			}

			if(voterVOs.size() > 0)
				voterVOs.get(0).setTotalVoters(totalRecords);
			return voterVOs;
		}
	
*/}
