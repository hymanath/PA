package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IBoothPopulationService;

public class BoothPopulationService implements IBoothPopulationService{/*

	private ITehsilDAO tehsilDAO;
	private IConstituencyDAO constituencyDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private IBoothResultDAO boothResultDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothDAO boothDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IBoothVillageCensusDAO boothVillageCensusDAO;
	private INominationDAO nominationDAO;
	private IPublicationDateDAO publicationDateDAO;
	private static final Logger log = Logger.getLogger(DelimitationConstituencyMandalService.class);

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IBoothVillageCensusDAO getBoothVillageCensusDAO() {
		return boothVillageCensusDAO;
	}

	public void setBoothVillageCensusDAO(
			IBoothVillageCensusDAO boothVillageCensusDAO) {
		this.boothVillageCensusDAO = boothVillageCensusDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public IBoothResultDAO getBoothResultDAO() {
		return boothResultDAO;
	}

	public void setBoothResultDAO(IBoothResultDAO boothResultDAO) {
		this.boothResultDAO = boothResultDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}

	
	*//**
	 * Uploads Booth Data For an Election Year and For A Particular Election  
	 *//*
	public ResultWithExceptionVO readExcelAndPopulateBoothData(File filePath, String electionYear, 
			Long electionScopeId, Boolean isValidate,Long publicationDateId) {
		ResultWithExceptionVO resultWithExceptionVO = new ResultWithExceptionVO();
		List<ConstituencyInfoVO> finalResult = new ArrayList<ConstituencyInfoVO>();
		ConstituencyInfoVO constituencyVO = null;
		long start = System.currentTimeMillis();

		try{
			BoothDataExcelReader excelReader = new BoothDataExcelReader();
			excelReader.readExcelFile(filePath);
			List<BoothDataUploadVO> boothData = excelReader.getBoothDataUploadVOs();
			List<Constituency> constituencies = null;
			List<String> constituencyInfo = null;
			Constituency constituency = null;
			Long districtId = null;
			Long totalBooths = 0l;
			Long year = Long.parseLong(electionYear);
			String constituencyName = "";
			Long partNosCount = 0l;
			Long nullTehsilsCount = 0l;
			
			for(BoothDataUploadVO boothDataUploadVO:boothData){
				constituencyVO = new ConstituencyInfoVO();
				constituencyInfo = new ArrayList<String>();
				districtId = boothDataUploadVO.getDistrictId();
				constituencyName = boothDataUploadVO.getConstituencyName();
				constituencyVO.setConstituencyName(constituencyName);
				constituencyVO.setUploadInfo(new ArrayList<String>());
				
				constituencies = constituencyDAO.findByConstituencyNameElectionScopeAndDistrictId(constituencyName, districtId, electionScopeId);
				if(constituencies.size() != 1){
					constituencyInfo.add(constituencies.size()+" No Of Constituencies Exists with Constituency Name::"+constituencyName+
							" And District Id::"+districtId);
					finalResult.add(updateUploadInfo(constituencyVO, constituencyInfo, partNosCount, 
							Long.parseLong(boothDataUploadVO.getBooths().size()+""), nullTehsilsCount, 0l));
					continue;
				}
				constituency = constituencies.get(0);
				
					totalBooths = (Long)boothDAO.findByConstituencyAndElectionYearAndPubDtId(constituency.getConstituencyId(), year,publicationDateId).get(0);
			
				  if(totalBooths > 0){
					constituencyInfo.add(totalBooths+" No.of Booths Already Exists For ElectionYear:: "+electionYear+" Constituency Name:: "+constituencyName+" Publication Date Id :: "+publicationDateId);
					finalResult.add(updateUploadInfo(constituencyVO, constituencyInfo, partNosCount, 
							Long.parseLong(boothDataUploadVO.getBooths().size()+""), nullTehsilsCount, 0l));
					continue;
				}
			 
				constituencyVO.setUploadInfo(constituencyInfo);
				checkAndInsertBooth(constituency, boothDataUploadVO.getBooths(), districtId, 
						year, constituencyVO, isValidate, publicationDateId);
				constituencyVO.setBoothsIdentified(Long.parseLong(boothDataUploadVO.getBooths().size()+""));
				finalResult.add(constituencyVO);
				
				constituencyDAO.flushAndclearSession();
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Total Time Taken::"+(end - start)/1000);
		resultWithExceptionVO.setFinalResult(finalResult);
		return resultWithExceptionVO;
	}
	
	private ConstituencyInfoVO updateUploadInfo(ConstituencyInfoVO constituencyVO, List<String> constituencyInfo, 
			Long partNosCount, Long totalBooths, Long nullTehsils, Long totalCandidates){
		constituencyVO.setUploadInfo(constituencyInfo);
		constituencyVO.setBoothsInserted(partNosCount);
		constituencyVO.setBoothsIdentified(totalBooths);
		constituencyVO.setNullTehsilsCount(nullTehsils);
		constituencyVO.setTotalCandidates(totalCandidates);
		return constituencyVO;
	}
	
	private void checkAndInsertBooth(Constituency constituency, List<BoothInfo> boothRecords, Long districtId, 
			Long year, ConstituencyInfoVO constituencyVO, Boolean isValidate,Long publicationDateId)throws Exception{
		Booth booth = null;
		String tehsilName = "";
		String partNo = "";
		String partName = "";
		String location = "";
		String villagesCovered = "";
		String censusCode = "";
		Long maleVoters = 0l;
		Long femaleVoters = 0l;
		Long totalVoters = 0l;
		List<Tehsil> tehsils = null;
		Tehsil tehsil = null;
		Long boothsCount = 0l;
		Long nullTehsilsCount = 0l;
		Long partNosCount = 0l;
		
		for(BoothInfo boothRecord:boothRecords){
			tehsils = null;
			tehsilName = boothRecord.getMandalName().trim();
			partNo = boothRecord.getPartNo();
			partName = boothRecord.getPartName();
			location = boothRecord.getLocation();
			villagesCovered = boothRecord.getVillagesCovered();
			censusCode = boothRecord.getCensusCode();
			maleVoters = checkAndAssignLong(boothRecord.getMaleVoters(), constituencyVO.getUploadInfo(), partNo, "Male Voters");
			femaleVoters = checkAndAssignLong(boothRecord.getFemaleVoters(), constituencyVO.getUploadInfo(), partNo, "Female Voters");
			totalVoters = checkAndAssignLong(boothRecord.getTotalVoters(), constituencyVO.getUploadInfo(), partNo, "Total Voters");
		 
		  
					
					boothsCount = (Long)boothDAO.findByPartNoConstituencyIdAndYearAndPubDtId(constituency.getConstituencyId(), year, partNo,publicationDateId).get(0);
				
				if(boothsCount > 0){
					constituencyVO.getUploadInfo().add(boothsCount+" No of Booths Exists with Part No:"+partNo+" And Constitency Name:"+constituency.getName()+
	                        " In the Year"+year+" with Publication Date Id :"+publicationDateId);
					continue;
	            }
			  
			if(tehsilName.length() > 0){
				tehsils = tehsilDAO.findByTehsilNameAndDistrict(tehsilName, districtId);
				if(tehsils.size() != 1){
					constituencyVO.getUploadInfo().add(tehsils.size()+ " No. Of Tehsils Exist With Tehsil:"+tehsilName+" and District:"+districtId);
					continue;
				}
				tehsil = tehsils.get(0);
			}
			
			if(tehsil == null)
				nullTehsilsCount++;
			
			if(!isValidate){
			 
				booth = new Booth(partNo, partName, location, villagesCovered, tehsil, maleVoters, femaleVoters,
						totalVoters, constituency, year, null,null, null,null,publicationDateDAO.get(publicationDateId));
				booth = boothDAO.save(booth);
				partNosCount++;	 
				
			}
			
			checkAndInsertBoothVillageCensus(booth, censusCode, constituencyVO.getUploadInfo(), isValidate);
		}
		
		constituencyVO.setNullTehsilsCount(nullTehsilsCount);
		constituencyVO.setBoothsInserted(partNosCount);
		
	}
					
	private void checkAndInsertBoothVillageCensus(Booth booth,String censusCode, List<String> constituencyInfo, Boolean isValidate)throws Exception{

		if(censusCode.contains("\n")||censusCode.contains(",")||censusCode.contains(" ")){
			String [] censusCodes = StringUtils.split(censusCode, ",\n ");
			for(int i=0; i < censusCodes.length; i++){
				checkBoothVillageCensus(booth, censusCodes[i], constituencyInfo, isValidate);
			}
		}else
			checkBoothVillageCensus(booth, censusCode, constituencyInfo, isValidate);

	}

	private void checkBoothVillageCensus(Booth booth, String censusCode, List<String> constituencyInfo, Boolean isValidate)throws Exception{
		Long censusCodeL;
		
		if(StringUtils.isNumeric(censusCode))
			censusCodeL = Long.parseLong(censusCode);
		else{
			constituencyInfo.add("Booth Village Code : "+censusCode+" Is Not A Number");
			return;
		}
		
		if(isValidate)
			return;
		
		List<BoothVillageCensus> boothVillageCensuses = boothVillageCensusDAO.findByBoothAndCensusCode(booth.getBoothId(), censusCodeL);
		
		if(boothVillageCensuses.size() != 0){
			constituencyInfo.add("BoothVillageCensus already exists with id and censuscode:"+booth.getBoothId()+" "+censusCodeL);
			return;
		}
		
		
		BoothVillageCensus boothVillageCensus = new BoothVillageCensus(censusCodeL , booth);
		boothVillageCensusDAO.save(boothVillageCensus);
		

	}
	
	public Long checkAndAssignLong(String value, List<String> constituencyInfo, String partNo, String column){
		Long longVal = 0l;
		if(!StringUtils.isEmpty(value) ){
			if(StringUtils.isNumeric(value.trim()))
				longVal = new Long(value);
			else
				constituencyInfo.add(value+" Is not A Numaric Value For Part No:"+partNo+" And In Column:"+column);
		}
		
		return longVal;
	}

	*//**
	 * Upload Booth Results For Election Year and Scope
	 *//*
	public ResultWithExceptionVO readExcelAndPopulateBoothResult(String electionYear, Long electionScopeId, File filePath, Boolean isValidate){
		long start = System.currentTimeMillis();
		ExcelBoothResultReader excelBoothResultReader = new ExcelBoothResultReader();
		ResultWithExceptionVO resultVO = new ResultWithExceptionVO();
		Boolean isParliament = false;
		List<ConstituencyInfoVO> finalResult = new ArrayList<ConstituencyInfoVO>();
		ConstituencyInfoVO constituencyVO = null;
		Long boothsInserted = 0l;
		
		List<String> constituencyInfo = null;
		try{
			
			if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionScopeDAO.get(electionScopeId).getElectionType().getElectionType()))
				isParliament = true;
			
			List<ConstituencyBoothBlock> list = excelBoothResultReader.readExcel(filePath, isParliament);
			String pcName = ""; 
			String acName = "";
			Long districtId = null;
			Long stateId = null;
			Long booths = null;
			Long boothResults = null;
			List<ConstituencyElection> constituencyElections = null;
			List<CandidateBoothWiseResult> candidateBoothResults = null;
			List<BoothResultValueObject> boothresults = null;
			ConstituencyElection constiElecObj = null;
			
			for(ConstituencyBoothBlock constituencyBlock:list){
				constituencyVO = new ConstituencyInfoVO();
				constituencyInfo = new ArrayList<String>();
				acName = constituencyBlock.getConstituencyName();
				pcName = constituencyBlock.getParliamentConstituencyName();
				districtId = constituencyBlock.getDistrictId();
				stateId = constituencyBlock.getStateId();
				candidateBoothResults = constituencyBlock.getCandidateResults();
				boothresults = constituencyBlock.getBoothResults();
				
				if(isParliament)
					constituencyVO.setConstituencyName(acName+" Assembly For "+pcName+" Parliament");
				else
					constituencyVO.setConstituencyName(acName+" Assembly");
				
				booths = (Long)boothDAO.findbyConstituencyNameDistrictIdAndElectionYear(acName, districtId, Long.parseLong(electionYear)).get(0);
				
				if(booths == 0){
					constituencyInfo.add("Booth Data Doesn't Exists For ElectionYear::"+electionYear+" Constituency Name::"+acName);
					finalResult.add(updateUploadInfo(constituencyVO, constituencyInfo, boothsInserted, Long.parseLong(boothresults.size()+""), 0l, 
							Long.parseLong(constituencyBlock.getCandidateResults().size()+"")));
					continue;
				}
				
				constituencyVO.setBoothsInDB(booths);
				
				boothResults = (Long)boothResultDAO.getParliamentResultHappenedInAssembly(acName, districtId, electionScopeId, electionYear).get(0);
				
				if(boothResults > 0){
					constituencyInfo.add(boothResults+ " No. Of Booths Already Have Results For ElectionYear::"+electionYear+" Constituency Name::"+acName);
					finalResult.add(updateUploadInfo(constituencyVO, constituencyInfo, boothsInserted, Long.parseLong(boothresults.size()+""), 0l, 
								Long.parseLong(constituencyBlock.getCandidateResults().size()+"")));
					
					continue;
				}
				
				if(isParliament)
					constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndState(electionYear, pcName, electionScopeId, stateId);
				else
					constituencyElections = constituencyElectionDAO.findByConstituencyElectionAndDistrict(electionYear, acName, electionScopeId, districtId);
				
				if(constituencyElections.size() != 1){
					constituencyInfo.add(constituencyElections.size()+" No.Of ConstituencyElections Exists For Constituency And Election Year::"+electionYear+","+acName);
					finalResult.add(updateUploadInfo(constituencyVO, constituencyInfo, boothsInserted, Long.parseLong(boothresults.size()+""), 0l, 
							Long.parseLong(constituencyBlock.getCandidateResults().size()+"")));
					continue;
				}
				
				constiElecObj = constituencyElections.get(0);
				
				constituencyVO.setUploadInfo(constituencyInfo);
				constituencyVO.setTotalCandidates(Long.parseLong(constituencyBlock.getCandidateResults().size()+""));
				constituencyVO.setBoothsIdentified(Long.parseLong(boothresults.size()+""));
				
				checkAndInsertBoothResult(acName, districtId, electionYear, constiElecObj, candidateBoothResults, 
						boothresults, constituencyVO, isValidate);
				
				finalResult.add(constituencyVO);
				
				constituencyDAO.flushAndclearSession();
				
			}
			
		}catch(IndexOutOfBoundsException ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			resultVO.setResultPartial(true);
			ex.printStackTrace();
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
			ex.printStackTrace();
		}
		
		resultVO.setFinalResult(finalResult);
		long end = System.currentTimeMillis();
		System.out.println("Total Time Taken::"+(end - start)/1000);
		return resultVO;
	}
	
	public void checkAndInsertBoothResult(String acName, Long districtId, String electionYear, ConstituencyElection constiElecObj, 
			List<CandidateBoothWiseResult> candidateResults, List<BoothResultValueObject> boothresults, 
			ConstituencyInfoVO constituencyVO, Boolean isValidate){
		
		Map<String, BoothConstituencyElection> boothConstiPartNoMap = new HashMap<String, BoothConstituencyElection>();
		List<Nomination> nominations = null;
		Nomination nomination = null;
		Long votesEarned = 0l;
		checkAndInsertBoothConstituencyElectionAndResult(acName, districtId, electionYear, boothConstiPartNoMap, 
				constiElecObj, boothresults, constituencyVO, isValidate);
			
		if(boothConstiPartNoMap.size() > 0)
			for(CandidateBoothWiseResult candidateBoothWiseResult:candidateResults){
				nominations = nominationDAO.findByConstituencyElectionAndCandidate(candidateBoothWiseResult.getCandidateName() , 
						constiElecObj.getConstiElecId());
				
				if(nominations.size() != 1){
					constituencyVO.getUploadInfo().add(nominations.size()+" No.Of Nominations Exists for Candidate:"+candidateBoothWiseResult.getCandidateName()+
							" And With Constituency Election Id:"+constiElecObj.getConstiElecId());
					continue;
				}
				
				nomination = nominations.get(0);
				
				for(BoothResultExcelVO boothResultExcel:candidateBoothWiseResult.getBoothresults()){
					votesEarned = checkAndAssignLong(boothResultExcel.getVotesEarned(), constituencyVO.getUploadInfo(), 
							boothResultExcel.getPartNo(), candidateBoothWiseResult.getCandidateName());
					if(!isValidate && boothConstiPartNoMap.get(boothResultExcel.getPartNo()) != null)
						candidateBoothResultDAO.save(new CandidateBoothResult(votesEarned, 
								nomination, null, boothConstiPartNoMap.get(boothResultExcel.getPartNo())));
				}
					
					
			}		
	}
				
	private void checkAndInsertBoothConstituencyElectionAndResult(String acName, Long districtId, String electionYear,
			Map<String, BoothConstituencyElection> boothConstiPartNoMap, ConstituencyElection constiElecObj,
			List<BoothResultValueObject> boothresults, ConstituencyInfoVO constituencyVO, Boolean isValidate) {
		List<Booth> booths = null;
		Booth booth = null;
		BoothConstituencyElection boothConstituencyElection = null;
		Long boothsInserted = 0l;
		Long validVotes = 0l;
		Long tenderVotes = 0l;
		Long rejectedVotes = 0l;
		for(BoothResultValueObject boothResult:boothresults){
			booths = boothDAO.findbyConstituencyNameDistrictIdPartnoAndElectionYear(acName,
					 districtId, Long.parseLong(electionYear), boothResult.getPartNumber());
			
			if(booths.size() != 1){
				constituencyVO.getUploadInfo().add(booths.size()+" No. Of Booths Exists with Part No:"+boothResult.getPartNumber()+" And Constitency Name:"+acName+
                        " In the Year"+electionYear);
				continue;
			}
			
			booth = booths.get(0);
			validVotes = checkAndAssignLong(boothResult.getTotalNoOfValidVotes(), constituencyVO.getUploadInfo(), boothResult.getPartNumber(), "Total Valid Votes"); 
			rejectedVotes = checkAndAssignLong(boothResult.getRejectedVotes(), constituencyVO.getUploadInfo(), boothResult.getPartNumber(), "Total Rejected Votes");
			tenderVotes = checkAndAssignLong(boothResult.getTenderedVotes(), constituencyVO.getUploadInfo(), boothResult.getPartNumber(), "Total Tendered Votes");
		
			if(!isValidate){
				boothConstituencyElection = boothConstituencyElectionDAO.save(new BoothConstituencyElection(booth, constiElecObj, null, null, null));
				boothResultDAO.save(new BoothResult(boothConstituencyElection, validVotes, rejectedVotes, tenderVotes));
				boothsInserted++;	
			}

			boothConstiPartNoMap.put(boothResult.getPartNumber(), boothConstituencyElection);
		}
		
		constituencyVO.setBoothsInserted(boothsInserted);
		
	}
	public List<SelectOptionVO> getPublicationDates(){
		  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>();
		  SelectOptionVO selectOptionVO = new SelectOptionVO();
		   selectOptionVO.setId(0l);
		   selectOptionVO.setName("Select Publication Date");
		   selectOptionVOList.add(selectOptionVO);
		  List<PublicationDate> publicationDatesList = publicationDateDAO.getPublicationDates();
		  for(PublicationDate publicationDate : publicationDatesList){
			   selectOptionVO = new SelectOptionVO();
			   selectOptionVO.setId(publicationDate.getPublicationDateId());
			   Date date = publicationDate.getDate();
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date);
			   selectOptionVO.setName(calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));
			   selectOptionVOList.add(selectOptionVO);
		  }
		  return selectOptionVOList;
	}

	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}
	
*/}
