package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICensusDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IVillageBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CastTotalVotersVO;
import com.itgrids.partyanalyst.dto.CastWiseElectionVotersVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.GenderAgeWiseVotersVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersHeaderDataVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersListVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.VillageCensusInfoVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.enums.DelimitationConstituencyType;
import com.itgrids.partyanalyst.model.Census;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.DelimitationConstituencyMandal;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DelimitationConstituencyMandalService implements IDelimitationConstituencyMandalService{

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO constituencyDAO;
	private ICensusDAO censusDAO;
	private ITownshipDAO townshipDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IElectionDAO electionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IVoterDAO voterDAO;
	private IVillageBoothElectionDAO villageBoothElectionDAO;
	private IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO;
	private static final Logger log = Logger.getLogger(DelimitationConstituencyMandalService.class);

	public void setBoothConstituencyElectionVoterDAO(
			IBoothConstituencyElectionVoterDAO boothConstituencyElectionVoterDAO) {
		this.boothConstituencyElectionVoterDAO = boothConstituencyElectionVoterDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setCensusDAO(ICensusDAO censusDAO){
		this.censusDAO = censusDAO;
	}
 
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	
	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}
	
	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IVillageBoothElectionDAO getVillageBoothElectionDAO() {
		return villageBoothElectionDAO;
	}

	public void setVillageBoothElectionDAO(
			IVillageBoothElectionDAO villageBoothElectionDAO) {
		this.villageBoothElectionDAO = villageBoothElectionDAO;
	}

	public DelimitationConstituencyMandalResultVO getMandalsForDelConstituency(Long constituencyID){
		if(log.isDebugEnabled())
			log.debug("DelimitationConstituencyMandalService.java--getMandalsForDelConstituency() ID::"+constituencyID);
		DelimitationConstituencyMandalResultVO resultVO = new DelimitationConstituencyMandalResultVO();
		try{
			resultVO.setConstituencyType(DelimitationConstituencyType.EXISTING_CONSTITUENCY);
			Constituency constituency = constituencyDAO.get(constituencyID);
			
			List<DelimitationConstituency> delimitationConstituencies = delimitationConstituencyDAO
					.findDelimitationConstituencyByConstituencyID(constituencyID);
			if(delimitationConstituencies.size()==0){
				if(log.isEnabledFor(Level.WARN))
					log.warn(constituency.getName() +" Constituency is not available in the delimitation_constituency table");
					resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					resultVO.setResultPartial(true);
					return resultVO;
			}
			DelimitationConstituency delimitationConstituency1 = delimitationConstituencies.get(0);
			Long dc1 = delimitationConstituency1.getDelimitationConstituencyID();
			resultVO.setDelimitationYear(delimitationConstituency1.getYear());
			
			List<DelimitationConstituencyMandal>  results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc1);
			if(results.size()==0){
				if(log.isEnabledFor(Level.WARN))
					log.warn(dc1 +" delimitationConstituencyID is not available in the delimitation_constituency_mandal_details table");
					resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
					resultVO.setResultPartial(true);
					return resultVO;
			}
			List<MandalInfoVO> currentResult = getMandalsForDelimitationConstituency(results);
			resultVO.setPresentMandals(currentResult);
			if(delimitationConstituencies.size()>1){
				resultVO.setConstituencyType(DelimitationConstituencyType.CHANGE_CONSTITUENCY);
				DelimitationConstituency delimitationConstituency2 = delimitationConstituencies.get(1);
				Long dc2 = delimitationConstituency2.getDelimitationConstituencyID();
				resultVO.setBeforeDelimitationYear(delimitationConstituency2.getYear());
				
				results = delimitationConstituencyMandalDAO.findDelConstMandalByDelConstID(dc2);
				if(results.size()==0){
					if(log.isEnabledFor(Level.WARN))
						log.warn(dc2 +" delimitationConstituencyID is not available in the delimitation_constituency_mandal_details table");
						resultVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
						resultVO.setResultPartial(true);
						return resultVO;
				}
				currentResult = getMandalsForDelimitationConstituency(results);
				if(constituency.getDeformDate()!=null){
					resultVO.setConstituencyType(DelimitationConstituencyType.NON_EXISTING_CONSTITUENCY);
				}
				resultVO.setMandalsBeforeDelimitationConstituency(currentResult);
			}
		}catch(Exception ex){
			resultVO.setExceptionEncountered(ex);
			resultVO.setResultCode(ResultCodeMapper.FAILURE);
			resultVO.setResultPartial(true);
		}
		return resultVO;
	}
	
	public List<MandalInfoVO> getMandalsForDelimitationConstituency(
			List<DelimitationConstituencyMandal> delimitationConstituencyMandalList){
		String array[] = {"YES","NO"};
		Map<Long, String> mandalNames = new HashMap<Long, String>();
		StringBuilder mandalIDs = new StringBuilder();
		for(DelimitationConstituencyMandal delimitationConstituencyMandal :
												delimitationConstituencyMandalList){
			Tehsil mandal = delimitationConstituencyMandal.getTehsil();
			mandalNames.put(mandal.getTehsilId(), mandal.getTehsilName()+"-"+array[new Integer(delimitationConstituencyMandal.getIsPartial())]);
			mandalIDs.append(",").append(mandal.getTehsilId());
			
		}
		String mandalsStr = mandalIDs.toString().substring(1);
		List<MandalInfoVO> mandalInfoList = getCensusInfoForMandals(mandalsStr);
		for(MandalInfoVO voObject : mandalInfoList){
			String[] arrayData =mandalNames.get(voObject.getMandalID()).split("-");
			//String name = mandalNames.get(voObject.getMandalID());
			voObject.setMandalName(arrayData[0]);
			voObject.setIsPartial(arrayData[1]);
		}
		return mandalInfoList;
	}
	
	public List<MandalInfoVO> getCensusInfoForMandals(String mandalIDs){
		if(log.isDebugEnabled())
			log.debug("service.getCensusInfoForMandals() started..........................");
		List<Census> mandalsCensus = censusDAO.findByYearAndTehsilIDs(IConstants.CENSUS_YEAR, mandalIDs);
		List<MandalInfoVO> mandalInfoList = new ArrayList<MandalInfoVO>();
		for(Census mandalCensus : mandalsCensus){
			MandalInfoVO obj = new MandalInfoVO();
			convertCensusTOMandalInfo(mandalCensus, obj);
			mandalInfoList.add(obj);
		}
		if(log.isDebugEnabled())
			log.debug("service.getCensusInfoForMandals() end.........................."+mandalInfoList.size());
		return mandalInfoList;
	}
	
	public MandalInfoVO convertCensusTOMandalInfo(Census mandalCensus, MandalInfoVO obj){
		Long tehsilID = mandalCensus.getTehsilId();
		
		obj.setMandalID(tehsilID);
		
		obj.setTotalPersons(mandalCensus.getTotalPopulation());
		obj.setTotalMalePersons(mandalCensus.getTotalMalePopulation());
		obj.setTotalFemalePersons(mandalCensus.getTotalFemalePopulation());
		
		obj.setTotalSCPersons(mandalCensus.getPopulationSC());
		obj.setTotalSCFemalePersons(mandalCensus.getFemaleSC());
		obj.setTotalSCMalePersons(mandalCensus.getMaleSC());

		obj.setTotalSTPersons(mandalCensus.getPopulationST());
		obj.setTotalSTFemalePersons(mandalCensus.getFemaleST());
		obj.setTotalSTMalePersons(mandalCensus.getMaleST());

		obj.setTotalLiteratePersons(mandalCensus.getPopulationLiterates());
		obj.setTotalLiterateFemalePersons(mandalCensus.getFemaleLiterates());
		obj.setTotalLiterateMalePersons(mandalCensus.getMaleLiterates());

		obj.setTotalIlliteratePersons(mandalCensus.getPopulationIlliterates());
		obj.setTotalIlliterateFemalePersons(mandalCensus.getFemaleIlliterates());
		obj.setTotalIlliterateMalePersons(mandalCensus.getMaleIlliterates());


		obj.setTotalWorkingPersons(mandalCensus.getWorkingPopulation());
		obj.setTotalWorkingFemalePersons(mandalCensus.getWorkingFemale());
		obj.setTotalWorkingMalePersons(mandalCensus.getWorkingMale());
		return obj;
	}
	
	public VillageDetailsVO getVillagesFormMandal(Long mandalID){
		VillageDetailsVO villageDetailsVO = new VillageDetailsVO();
		List<VillageCensusInfoVO> villageCensusList = new ArrayList<VillageCensusInfoVO>();
		VillageCensusInfoVO villageCensusInfoVO = null;
		try{
			List villagesInfo = censusDAO.findAllRevenueVillagesInfoInMandal(IConstants.CENSUS_YEAR, mandalID, 
										"'"+IConstants.CENSUS_VILLAGE_LEVEL+"','"+IConstants.CENSUS_WARD_LEVEL+"'");
			
			for(Object[] values:(List<Object[]>)villagesInfo){
				
				villageCensusInfoVO = new VillageCensusInfoVO();
				villageCensusInfoVO.setTownshipID((Long)values[0]);
				villageCensusInfoVO.setTownshipName(values[1].toString());			
				villageCensusInfoVO.setTotalPersons((Long)values[2]);
				villageCensusInfoVO.setTotalMalePersons((Long)values[3]);
				villageCensusInfoVO.setTotalFemalePersons((Long)values[4]);				
				villageCensusInfoVO.setTotalSCPersons((Long)values[5]);
				villageCensusInfoVO.setTotalSTPersons((Long)values[6]);
				villageCensusInfoVO.setTotalLiteratePersons((Long)values[7]);				
				villageCensusInfoVO.setTotalIlliteratePersons((Long)values[8]);
				villageCensusInfoVO.setTotalWorkingPersons((Long)values[9]);
				villageCensusList.add(villageCensusInfoVO);
				villageCensusInfoVO.setTownshipNameURL("<a href='revenueVillageReport.action?revenueVillageName=" +
						""+villageCensusInfoVO.getTownshipName()+"&revenueVillageID="
						+villageCensusInfoVO.getTownshipID()+"'>"+villageCensusInfoVO.getTownshipName()+"</a>");
			}
			
			villageDetailsVO.setVillageCensusList(villageCensusList);
		}catch(Exception ex){
			villageDetailsVO.setExceptionEncountered(ex);
			villageDetailsVO.setResultCode(ResultCodeMapper.FAILURE);
			villageDetailsVO.setResultPartial(true);
		}
		
		return villageDetailsVO;
	}
	
	/**
	 * to retrieves party, election wise voters for a mandal/revenue village
	 * @param mandalID
	 * @return
	 */
	public PartyElectionVotersHeaderDataVO getPartyElectionVotersForMandal(Long id, String typeFlag){
		log.debug("DelimitationConstituencyMandalService.getPartyElectionVotersForMandal() started...");
		Set<String> header = new TreeSet<String>();
		List<PartyElectionVotersListVO> partyElectionVotersListVOs = new ArrayList<PartyElectionVotersListVO>();
		Map<String, Set<PartyElectionVotersVO>> partyElectionVotersMap = new LinkedHashMap<String, Set<PartyElectionVotersVO>>();
		PartyElectionVotersHeaderDataVO partyElectionVotersHeaderDataVO = new PartyElectionVotersHeaderDataVO();
		
		List<ElectionType> types = electionTypeDAO.getAll();
		StringBuilder electionTypeIDs = new StringBuilder();
		for(ElectionType type: types){
			electionTypeIDs.append(",").append(type.getElectionTypeId());
		}
		if(electionTypeIDs.length()<1){
			Exception exception = new Exception("Election Types are not available in the DB");
			partyElectionVotersHeaderDataVO.setExceptionEncountered(exception);
			return partyElectionVotersHeaderDataVO;
			
		}
		List<String> years = electionDAO.listOfYears();
		StringBuilder electionYears = new StringBuilder();
		for(String year:years){
			electionYears.append(",").append(year);
		}
		if(electionYears.length()<1){
			Exception exception = new Exception("Election Years are not available in the DB");
			partyElectionVotersHeaderDataVO.setExceptionEncountered(exception);
			return partyElectionVotersHeaderDataVO;
			
		}
		List partyElectionVoters = null;
		if(IConstants.MANDAL.equals(typeFlag)){
			partyElectionVoters = candidateBoothResultDAO.findPartyElectionResultForMandal(id, electionTypeIDs.substring(1),electionYears.substring(1));
		} else{
			partyElectionVoters = candidateBoothResultDAO.findPartyElectionResultForRevenueVillage(id, electionTypeIDs.substring(1),electionYears.substring(1));
		}
		int dbRows = partyElectionVoters.size();
		log.debug("Party Election voters for the mandal:::::::");
		for(int i=0; i < dbRows; i++){
			Object[] rowData = (Object[]) partyElectionVoters.get(i);
			log.debug(rowData[0].toString()+"-"+rowData[1].toString()+"-"+rowData[2].toString()+"-"+rowData[3].toString());
			PartyElectionVotersVO electionVoters = new PartyElectionVotersVO();
			
			electionVoters.setParty(rowData[0].toString());
			electionVoters.setElectionType(rowData[1].toString());
			int year =0;
			try{
				year = Integer.parseInt(rowData[2].toString().trim());
			}catch(Exception e){
				continue;
			}
			electionVoters.setElectionYear(year);
			header.add(electionVoters.getElectionYear()+IConstants.SPACE+electionVoters.getElectionType());
			long totalVoters = 0;
			try{
				totalVoters = Long.parseLong(rowData[3].toString().trim());
			}catch(Exception e){
				continue;
			}
			electionVoters.setVoterSize(totalVoters);
			Set<PartyElectionVotersVO> formattedDataList = partyElectionVotersMap.get(electionVoters.getParty());
			if(formattedDataList==null){
				formattedDataList = new TreeSet<PartyElectionVotersVO>();
				partyElectionVotersMap.put(electionVoters.getParty(), formattedDataList);
			}
			formattedDataList.add(electionVoters);
		}
	
		Set<Entry<String,Set<PartyElectionVotersVO>>> parties = partyElectionVotersMap.entrySet();
		Iterator<Entry<String,Set<PartyElectionVotersVO>>> partiesIterator = parties.iterator();
		
		while(partiesIterator.hasNext()){
			Iterator<String> headerNames = header.iterator();
			
			Entry<String,Set<PartyElectionVotersVO>> entry = partiesIterator.next();
			String party = entry.getKey();
			Set<PartyElectionVotersVO> partyVotersInfoList = entry.getValue();
			
			PartyElectionVotersListVO partyElectionVotersListVO = new PartyElectionVotersListVO();
			partyElectionVotersListVO.setPartyName(party);
			List<String> electionVoters = new ArrayList<String>();
			while(headerNames.hasNext()){
				String data = getVotersForElection(headerNames.next(), partyVotersInfoList);
				electionVoters.add(data);
			}
			partyElectionVotersListVO.setPartyElectionVotersList1(electionVoters);
			
			
			partyElectionVotersListVOs.add(partyElectionVotersListVO);
		}
		partyElectionVotersHeaderDataVO.setHeader(header);
		log.debug("typeFlag:"+typeFlag);
		log.debug("DelimitationConstituencyMandalService.header="+header.size());
		log.debug("DelimitationConstituencyMandalService.data="+partyElectionVotersListVOs.size());
		partyElectionVotersHeaderDataVO.setData(partyElectionVotersListVOs);
		return partyElectionVotersHeaderDataVO;
	}
	

	private String getVotersForElection(String columnName, Set<PartyElectionVotersVO> partyVotersInfoList){
		String data = IConstants.NOT_APPLICABLE;
		Iterator<PartyElectionVotersVO> iterator = partyVotersInfoList.iterator();
		while(iterator.hasNext()){
			PartyElectionVotersVO obj = iterator.next();
			if(columnName.startsWith(obj.getElectionYear().toString()) && columnName.endsWith(obj.getElectionType())){
				data = obj.getVoterSize().toString();
				break;
			}
		}
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public CastWiseElectionVotersVO findCastWiseVotersForMandal(Long mandalID){
		log.debug("DelimitationConstituencyMandalService.findCastWiseVotersForMandal() stated....");
		CastWiseElectionVotersVO castWiseElectionVoters = new CastWiseElectionVotersVO();

		List list = voterDAO.findCastWiseVotersForMandal(mandalID);
		
		int size = list.size();
		if(size==0){
			List censusList = censusDAO.findCastWiseVotersForMandal(mandalID);
			if(censusList.size()==1){
				//sc, st
				Object[] obj = (Object[])censusList.get(0);
				Object[] obj1 ={"SC", obj[0]};
				Object[] obj2 ={"ST", obj[1]};
				list.add(obj1); list.add(obj2);
			}
			size = list.size();
			log.debug("cast data from census table :::sie="+list.size());
		}
		long mandalTotalVoters =0;
		List<CastTotalVotersVO> castTotalVotersVOList = new ArrayList<CastTotalVotersVO>();
		for(int i=0; i<size; i++){
			CastTotalVotersVO castTotalVotersVO = new CastTotalVotersVO();
			try{
				Object[] obj = (Object[]) list.get(i);
				castTotalVotersVO.setCasteName(obj[0].toString());
				Long  voters = new Long(obj[1].toString());
				mandalTotalVoters += voters;
				castTotalVotersVO.setTotalVoters(voters);
			}catch(Exception e){
				StringBuilder message = new StringBuilder("Problem while reading voters:::");
				message.append(e.getMessage());
				Exception ex = new Exception(message.toString());
				castWiseElectionVoters.setExceptionEncountered(ex);
				return castWiseElectionVoters;
			}
			castTotalVotersVOList.add(castTotalVotersVO);
			
		}
		if(size!=0 && mandalTotalVoters==0){
			Exception ex = new Exception("No voters available in the Mandal");
			castWiseElectionVoters.setExceptionEncountered(ex);
			return castWiseElectionVoters;
		}
		for(CastTotalVotersVO cast : castTotalVotersVOList){
			String name = cast.getCasteName();
			if(name==null || name.length()==0)
				cast.setCasteName(IConstants.NOT_APPLICABLE);
			String voterPercentage = new BigDecimal((cast.getTotalVoters()*100)/mandalTotalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			cast.setVoterPercentage(voterPercentage);
		}

		log.debug("final cast data sie="+castTotalVotersVOList.size());
		castWiseElectionVoters.setCasteVoters(castTotalVotersVOList);
		return castWiseElectionVoters;
	}
	
	public GenderAgeWiseVotersVO findGenderAgeWiseVotersForMandal(Long mandalID){
		Long minAge = 18L;
		Long maxAge = 23L;
		Long maleTotalVoters = 0L;
		Long femaleTotalVoters = 0L;
		Long grandTotalVoters = 0L;
		GenderAgeWiseVotersVO genderAgeWiseVoters = new GenderAgeWiseVotersVO();
		List<VoterAgeRangeVO> voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>();
		genderAgeWiseVoters.setVoterAgeRangeVOList(voterAgeRangeVOList);
		/*
		List<Long> maleVotersAgeWise = new ArrayList<Long>();
		List<Long> femaleVotersAgeWise = new ArrayList<Long>();
		List<Long> totalVotersAgeWise = new ArrayList<Long>();
		
		genderAgeWiseVoters.setMaleVotersAgeWise(maleVotersAgeWise);
		genderAgeWiseVoters.setFemaleVotersAgeWise(femaleVotersAgeWise);
		genderAgeWiseVoters.setTotalVotersAgeWise(totalVotersAgeWise);*/
		
		List voters = voterDAO.findGenderAgeWiseVotersForMandal(mandalID, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "18-23");
		minAge = 23L; maxAge = 35L;
		voters = voterDAO.findGenderAgeWiseVotersForMandal(mandalID, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "23-35");
		minAge = 35L; maxAge = 50L;
		voters = voterDAO.findGenderAgeWiseVotersForMandal(mandalID, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "35-50");
		minAge = 50L; maxAge = 65L;
		voters = voterDAO.findGenderAgeWiseVotersForMandal(mandalID, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "50-65");
		minAge = 65L; maxAge = 200L;
		voters = voterDAO.findGenderAgeWiseVotersForMandal(mandalID, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "65 And Above");
		addTotal(voterAgeRangeVOList);
		return genderAgeWiseVoters;
	}
	private void addTotal(List<VoterAgeRangeVO> list){
		Long total = 0L;
		Long totalMale = 0L;
		Long totalFemale = 0L;
		for(VoterAgeRangeVO voterAgeRangeVO : list){
			total +=voterAgeRangeVO.getTotalVoters();
			totalMale += voterAgeRangeVO.getMaleVoters();
			 totalFemale += voterAgeRangeVO.getFemaleVoters();
		}
		VoterAgeRangeVO obj = new VoterAgeRangeVO();
		obj.setAgeRange("Total");
		obj.setMaleVoters(totalMale);
		obj.setFemaleVoters(totalFemale);
		obj.setTotalVoters(total);
		list.add(obj);
		if(total!=0){
			for(VoterAgeRangeVO voterAgeRangeVO : list){
				Long totalVoters =voterAgeRangeVO.getTotalVoters();
				String percentage = new BigDecimal((totalVoters * 100)/total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				voterAgeRangeVO.setPercentage(percentage);
			}	
		}
	}
	private VoterAgeRangeVO formatVoterGenderAgeWise(List<VoterAgeRangeVO> voterAgeRangeVOList, List voters, String ageRange){
		log.debug("ageRange::::::::::::::::"+ageRange);
		VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
		voterAgeRangeVO.setAgeRange(ageRange);
		Long maleSize = 0L;
		Long femaleSize = 0L;
		if(voters.size()==2){
			Object[] obj1 = (Object[])voters.get(0);
			Object[] obj2 = (Object[])voters.get(1);
			String gender = obj1[0].toString();
			log.debug("gender Value:"+gender);
			if("M".equals(gender)){
				maleSize = new Long(obj1[1].toString());
				femaleSize = new Long(obj2[1].toString());
			}else{
				maleSize = new Long(obj2[1].toString());
				femaleSize = new Long(obj1[1].toString());
			}
				
		} else if(voters.size()==1){
			Object[] obj1 = (Object[])voters.get(0);
			String gender = obj1[0].toString();
			if("M".equals(gender)){
				maleSize = new Long(obj1[1].toString());
			}else{
				femaleSize = new Long(obj1[1].toString());
			}
			
		}
		voterAgeRangeVO.setMaleVoters(maleSize);
		voterAgeRangeVO.setFemaleVoters(femaleSize);
		voterAgeRangeVO.setTotalVoters(maleSize+femaleSize);
		voterAgeRangeVOList.add(voterAgeRangeVO);
		log.debug("maleSize:"+maleSize);
		log.debug("femaleSize:"+femaleSize);
		log.debug("totalVoters:"+voterAgeRangeVO.getTotalVoters());
		return voterAgeRangeVO;
	}
	
	public CastWiseElectionVotersVO findCastWiseVoterForRevenueVillage(Long revenueVillageID, String year, String electionType){
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			//List years =electionDAO.findLatestElectionYear(electionType);
			List<Object> years = villageBoothElectionDAO.findLatestElectionYearInARevenueVillageForElectionType(revenueVillageID, electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				//hamletsListWithBoothsAndVotersVO.setExceptionEncountered(ex);
				//return hamletsListWithBoothsAndVotersVO;
			}
			year =(String)years.get(0);
		}
		CastWiseElectionVotersVO castWiseElectionVoters = new CastWiseElectionVotersVO();
		List<CastTotalVotersVO> castTotalVotersVOList = new ArrayList<CastTotalVotersVO>();
		List list = boothConstituencyElectionVoterDAO.findCastWiseVoterForRevenueVillage(revenueVillageID, year, electionType);
		//cast, count
		long mandalTotalVoters =0;
		//List<CastTotalVotersVO> castTotalVotersVOList = new ArrayList<CastTotalVotersVO>();

		int size = list.size();
		for(int i=0; i<size; i++){
			CastTotalVotersVO castTotalVotersVO = new CastTotalVotersVO();
			try{
				Object[] obj = (Object[]) list.get(i);
				castTotalVotersVO.setCasteName(obj[0].toString());
				Long  voters = new Long(obj[1].toString());
				mandalTotalVoters += voters;
				castTotalVotersVO.setTotalVoters(voters);
			}catch(Exception e){
				StringBuilder message = new StringBuilder("Problem while reading voters for revenue village id:::"+ revenueVillageID);
				message.append(e.getMessage());
				Exception ex = new Exception(message.toString());
				castWiseElectionVoters.setExceptionEncountered(ex);
				return castWiseElectionVoters;
			}
			castTotalVotersVOList.add(castTotalVotersVO);
			
		}
		if(size!=0 && mandalTotalVoters==0){
			Exception ex = new Exception("No voters available in the Revenue Village id::"+revenueVillageID);
			castWiseElectionVoters.setExceptionEncountered(ex);
			return castWiseElectionVoters;
		}
		for(CastTotalVotersVO cast : castTotalVotersVOList){
			String name = cast.getCasteName();
			if(name==null || name.length()==0)
				cast.setCasteName(IConstants.NOT_APPLICABLE);
			String voterPercentage = new BigDecimal((cast.getTotalVoters()*100)/mandalTotalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			cast.setVoterPercentage(voterPercentage);
		}

		log.debug("final cast data size in Revenue village="+revenueVillageID+" size is:"+castTotalVotersVOList.size());
		castWiseElectionVoters.setCasteVoters(castTotalVotersVOList);
		return castWiseElectionVoters;
	}
	

	public GenderAgeWiseVotersVO findAgeWiseVotersForRevenueVillage(Long revenueVillageID, String year, String electionType){
		GenderAgeWiseVotersVO genderAgeWiseVoters = new GenderAgeWiseVotersVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			//List years =electionDAO.findLatestElectionYear(electionType);
			List<Object> years = villageBoothElectionDAO.findLatestElectionYearInARevenueVillageForElectionType(revenueVillageID, electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				genderAgeWiseVoters.setExceptionEncountered(ex);
				return genderAgeWiseVoters;
			}
			year =(String)years.get(0);
		}
		Long minAge = 18L;
		Long maxAge = 23L;
		Long maleTotalVoters = 0L;
		Long femaleTotalVoters = 0L;
		Long grandTotalVoters = 0L;
		List<VoterAgeRangeVO> voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>();
		genderAgeWiseVoters.setVoterAgeRangeVOList(voterAgeRangeVOList);
		/*
		List<Long> maleVotersAgeWise = new ArrayList<Long>();
		List<Long> femaleVotersAgeWise = new ArrayList<Long>();
		List<Long> totalVotersAgeWise = new ArrayList<Long>();
		
		genderAgeWiseVoters.setMaleVotersAgeWise(maleVotersAgeWise);
		genderAgeWiseVoters.setFemaleVotersAgeWise(femaleVotersAgeWise);
		genderAgeWiseVoters.setTotalVotersAgeWise(totalVotersAgeWise);*/
		
		List voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForRevenueVillage( revenueVillageID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "18-23");
		minAge = 23L; maxAge = 35L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForRevenueVillage(revenueVillageID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "23-35");
		minAge = 35L; maxAge = 50L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForRevenueVillage(revenueVillageID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "35-50");
		minAge = 50L; maxAge = 65L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForRevenueVillage(revenueVillageID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "50-65");
		minAge = 65L; maxAge = 200L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForRevenueVillage(revenueVillageID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "65 And Above");
		addTotal(voterAgeRangeVOList);
		return genderAgeWiseVoters;
	}
	
	public CastWiseElectionVotersVO findCastWiseVoterForHamlet(Long hamletID, String year, String electionType){
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			List years =electionDAO.findLatestElectionYear(electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				//hamletsListWithBoothsAndVotersVO.setExceptionEncountered(ex);
				//return hamletsListWithBoothsAndVotersVO;
			}
			year =(String)years.get(0);
		}
		CastWiseElectionVotersVO castWiseElectionVoters = new CastWiseElectionVotersVO();
		List<CastTotalVotersVO> castTotalVotersVOList = new ArrayList<CastTotalVotersVO>();
		List list = boothConstituencyElectionVoterDAO.findCastWiseVoterForHamlet(hamletID, year, electionType);
		//cast, count
		long mandalTotalVoters =0;
		//List<CastTotalVotersVO> castTotalVotersVOList = new ArrayList<CastTotalVotersVO>();

		int size = list.size();
		for(int i=0; i<size; i++){
			CastTotalVotersVO castTotalVotersVO = new CastTotalVotersVO();
			try{
				Object[] obj = (Object[]) list.get(i);
				castTotalVotersVO.setCasteName(obj[0].toString());
				Long  voters = new Long(obj[1].toString());
				mandalTotalVoters += voters;
				castTotalVotersVO.setTotalVoters(voters);
			}catch(Exception e){
				StringBuilder message = new StringBuilder("Problem while reading voters for hamlet ID:::"+ hamletID);
				message.append(e.getMessage());
				Exception ex = new Exception(message.toString());
				castWiseElectionVoters.setExceptionEncountered(ex);
				return castWiseElectionVoters;
			}
			castTotalVotersVOList.add(castTotalVotersVO);
			
		}
		if(size!=0 && mandalTotalVoters==0){
			Exception ex = new Exception("No voters available in the hamlet ID::"+hamletID);
			castWiseElectionVoters.setExceptionEncountered(ex);
			return castWiseElectionVoters;
		}
		for(CastTotalVotersVO cast : castTotalVotersVOList){
			String name = cast.getCasteName();
			if(name==null || name.length()==0)
				cast.setCasteName(IConstants.NOT_APPLICABLE);
			String voterPercentage = new BigDecimal((cast.getTotalVoters()*100)/mandalTotalVoters).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			cast.setVoterPercentage(voterPercentage);
		}

		log.debug("final cast data size in hamlet id="+hamletID+" size is:"+castTotalVotersVOList.size());
		castWiseElectionVoters.setCasteVoters(castTotalVotersVOList);
		return castWiseElectionVoters;
	}
	
	public GenderAgeWiseVotersVO findAgeWiseVotersForHamlet(Long hamletID, String year, String electionType){
		GenderAgeWiseVotersVO genderAgeWiseVoters = new GenderAgeWiseVotersVO();
		if(year==null){
			electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			List years =electionDAO.findLatestElectionYear(electionType);
			if(years==null || years.size()==0){
				Exception ex = new Exception("No Elections available in DB");
				genderAgeWiseVoters.setExceptionEncountered(ex);
				return genderAgeWiseVoters;
			}
			year =(String)years.get(0);
		}
		Long minAge = 18L;
		Long maxAge = 23L;
		Long maleTotalVoters = 0L;
		Long femaleTotalVoters = 0L;
		Long grandTotalVoters = 0L;
		List<VoterAgeRangeVO> voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>();
		genderAgeWiseVoters.setVoterAgeRangeVOList(voterAgeRangeVOList);
		/*
		List<Long> maleVotersAgeWise = new ArrayList<Long>();
		List<Long> femaleVotersAgeWise = new ArrayList<Long>();
		List<Long> totalVotersAgeWise = new ArrayList<Long>();
		
		genderAgeWiseVoters.setMaleVotersAgeWise(maleVotersAgeWise);
		genderAgeWiseVoters.setFemaleVotersAgeWise(femaleVotersAgeWise);
		genderAgeWiseVoters.setTotalVotersAgeWise(totalVotersAgeWise);*/
		
		List voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForHamlet( hamletID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "18-23");
		minAge = 23L; maxAge = 35L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForHamlet(hamletID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "23-35");
		minAge = 35L; maxAge = 50L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForHamlet(hamletID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "35-50");
		minAge = 50L; maxAge = 65L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForHamlet(hamletID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "50-65");
		minAge = 65L; maxAge = 200L;
		voters = boothConstituencyElectionVoterDAO.findAgeWiseVotersForHamlet(hamletID, year, electionType, minAge, maxAge);
		formatVoterGenderAgeWise(voterAgeRangeVOList, voters, "65 And Above");
		addTotal(voterAgeRangeVOList);
		return genderAgeWiseVoters;
	}
}
