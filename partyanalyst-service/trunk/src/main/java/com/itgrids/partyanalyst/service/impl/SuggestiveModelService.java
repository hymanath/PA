package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyImpactVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.YouthLeaderSelectionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.SuggestiveRange;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SuggestiveModelService implements ISuggestiveModelService {
	
	private static final Logger LOG = Logger.getLogger(SuggestiveModelService.class);
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IElectionDAO electionDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private ISuggestiveRangeDAO suggestiveRangeDAO;
	private IPanchayatDAO panchayatDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicationDateDAO publicationDateDAO;
	private ITehsilDAO tehsilDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private IRegionServiceData regionServiceDataImp;
	
	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}


	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}
	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}	

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	
	public ISuggestiveRangeDAO getSuggestiveRangeDAO() {
		return suggestiveRangeDAO;
	}

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	 public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
			return voterCastBasicInfoDAO;
		}

		public void setVoterCastBasicInfoDAO(
				IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
			this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
		}
		
		public IVoterCastInfoDAO getVoterCastInfoDAO() {
			return voterCastInfoDAO;
		}

		public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
			this.voterCastInfoDAO = voterCastInfoDAO;
		}
		
		public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
			return userVoterDetailsDAO;
		}

		public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
			this.userVoterDetailsDAO = userVoterDetailsDAO;
		}
		
		public IPublicationDateDAO getPublicationDateDAO() {
			return publicationDateDAO;
		}

		public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
			this.publicationDateDAO = publicationDateDAO;
		}
		
		public ITehsilDAO getTehsilDAO() {
			return tehsilDAO;
		}

		public void setTehsilDAO(ITehsilDAO tehsilDAO) {
			this.tehsilDAO = tehsilDAO;
		}
	public List<PanchayatVO> getVotersGroupDetails(List<SelectOptionVO> groupVos,Long constituencyId,Long locationId,String type,Long electionId,Long userId,List<Long> casteIds){

		Long publicationId = 8l;
		 List<Long> publicationIds = new ArrayList<Long>();
		 List<Long> panchayatsList=new ArrayList<Long>();
		 Map<Long,String> panchayatsMap=new HashMap<Long, String>();
		 List<PanchayatVO> panchayatVOs=null;
		 List<PanchayatVO> boothsVO=null;
		 List<PanchayatVO> panchayatVOList=new ArrayList<PanchayatVO>();
		 List<Object[]> panchayats=null;
		 publicationIds.add(publicationId);
		 List<Object[]> boothsList=null;
		 List<Long> boothIdsList=null;
		 Map<Long,String> boothsMap=new HashMap<Long, String>();
		 List<Object[]> muncipalitiesList=null;
		 Map<String,List<PanchayatVO>> MuncipalBoothsMap=null;
		 DecimalFormat df = new DecimalFormat("#.##");
		 
		 List<Object[]> constituencyType=constituencyDAO.getConstituencyType(constituencyId);
		 String constAreaType=constituencyType.get(0)[1].toString();
				 
		 if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
			 
		 }
		 else if(constAreaType.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
			  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
		 }
		 else{
			  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
			 muncipalitiesList=boothDAO.getMuncipalitiesListNamesByConstituencyId(constituencyId, publicationId);
			 
			/* for(Object[] param:muncipalitiesList){
				 List<Object[]> booths=boothDAO.getAllBoothsInAMuncipality(Long.valueOf(param[0].toString()), publicationId);
				 boothsList.addAll(booths);
			 }*/
		 }
		//  panchayats=boothDAO.getPanchayatsNamesListByConstituencyId(constituencyId, publicationId);
		// List<Object[]> panchayats=boothDAO.getPanchayatsListByTehsilId(locationId, publicationId);
		 for(Object[] list:panchayats){
			 panchayatsList.add(Long.valueOf(list[0].toString()));
			 panchayatsMap.put(Long.valueOf(list[0].toString()), list[1].toString());
			//System.out.println(list[0]+":"+list[1]);
		 }
		
		 
		 
		 	 for(SelectOptionVO group : groupVos){
				 panchayatVOs=new ArrayList<PanchayatVO>();
				 PanchayatVO pnchytVO1 =new PanchayatVO();
				 String ageRange=group.getId()+"> & <"+group.getPopulateId();
				 pnchytVO1.setAgeRange(ageRange);
				 boothsVO=new ArrayList<PanchayatVO>();
				 MuncipalBoothsMap=new HashMap<String, List<PanchayatVO>>();
				 
				 if(muncipalitiesList!=null){
				 for(Object[] param:muncipalitiesList){
					 
					 List<Object[]> booths=boothDAO.getAllBoothsInAMuncipality(Long.valueOf(param[0].toString()), publicationId);
					 boothsList=new ArrayList<Object[]>();
					 boothIdsList=new ArrayList<Long>();
					 boothsList.addAll(booths);
					 if(boothsList!=null){
						 for(Object[] list:boothsList){
							 boothIdsList.add(Long.valueOf(list[0].toString()));
							 boothsMap.put(Long.valueOf(list[0].toString()), "Booth-"+list[1].toString());
						 }
					 }
				 if(boothIdsList!=null){
					 for(Long boothId:boothIdsList){
						 List li=boothPublicationVoterDAO.getVotersCountForBooths(boothId, publicationId);
						 int totalVotersInBooth=Integer.parseInt((li.get(0)).toString());
						 
						 List<Object[]> castesInBooths=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeInBooth(group.getId(), group.getPopulateId(), boothId, userId);
						 List<Object[]> slctdCastesInBooths=new ArrayList<Object[]>();
						 if(casteIds.size()!=0){
							 slctdCastesInBooths=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeInBoothForSelectedCastes(group.getId(), group.getPopulateId(), boothId, userId, casteIds);
						 }
						 List<CastVO> top3CstsListInBooth=new ArrayList<CastVO>();
						 top3CstsListInBooth=getTopCastes(castesInBooths,"top3castes");
						 
						 List<CastVO> slctedCstsList=new ArrayList<CastVO>();
						 slctedCstsList=getTopCastes(slctdCastesInBooths,"");
						 
						 List<Object[]> votersCount = boothPublicationVoterDAO.getVotersCountAgeWiseInBooth(group.getId(), group.getPopulateId(), boothId);
						 int maleVotersInBooth=0;
						 int femaleVotersInBooth=0;
						 int totalVoters=0;
						 int totalMales=0;
						 int totalFemales=0;
						 
						 for(Object[] param1:votersCount){
							 if((param1[1].toString()).equalsIgnoreCase("F")){
								 femaleVotersInBooth=Integer.parseInt(param1[0].toString());
								 totalFemales=totalFemales+femaleVotersInBooth;
							 }else{
								 maleVotersInBooth=Integer.parseInt(param1[0].toString());
								 totalMales=totalMales+maleVotersInBooth;
							 }
							 totalVoters=totalFemales+totalMales;
						 }
						 
						 PanchayatVO pnchytVO=new PanchayatVO();
						 pnchytVO.setPanchayatId(boothId);
						 pnchytVO.setAgeRange(ageRange);
						 pnchytVO.setPanchayatName(boothsMap.get(boothId));
						 pnchytVO.setMaleVoters(totalMales);
						 pnchytVO.setFemaleVoters(totalFemales);
						 pnchytVO.setTotalVoters(totalVoters);
						// float percentage=(totalVoters*100)/totalVotersInBooth;
						 Double percentage= Double.valueOf(df.format(Long.valueOf(totalVoters)*100/(float)totalVotersInBooth));
						 pnchytVO.setPercentage(percentage.toString());
						 pnchytVO.setTotalPanchayatVoters(totalVotersInBooth);
						 pnchytVO.setTopCastes(top3CstsListInBooth);
						 pnchytVO.setSelectedCastes(slctedCstsList);
						 
						 boothsVO.add(pnchytVO);
					 }
				 	}
				 MuncipalBoothsMap.put(param[1].toString()+" "+param[2].toString(), boothsVO);
				 }
				 }
				 
				 
				 if(panchayatsList!=null){
				 for(Long panchayathId:panchayatsList){
				 if("panchayat".equalsIgnoreCase(type)){
					 List li=boothPublicationVoterDAO.getVotersCountForPanchayat(panchayathId, publicationId);
					 int totalVotersInPanchayat=Integer.parseInt((li.get(0)).toString());
					 List<Long> boothIDs = boothDAO.getBoothIdsByLocalValuesList("panchayat", panchayathId, constituencyId, publicationIds);
					 
					 //Getting Castes and Selecting top 3 Castes of a Panchayat
					 List<Object[]> castes=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRange(group.getId(), group.getPopulateId(), boothIDs, userId);
					 
					 List<Object[]> castesSelected=new ArrayList<Object[]>();
					 if(casteIds.size()!=0){
						castesSelected=boothPublicationVoterDAO.getVotersCasteDetailsForAgeRangeForSelectedCastes(group.getId(), group.getPopulateId(), boothIDs, userId, casteIds);
					 }
					 List<CastVO> top3CstsList=new ArrayList<CastVO>();
					 top3CstsList=getTopCastes(castes,"top3castes");
					 
					 List<CastVO> slctedCstsList=new ArrayList<CastVO>();
					 slctedCstsList=getTopCastes(castesSelected,"");
					 
					 if(boothIDs != null && boothIDs.size() > 0){
						 int maleVotersInBooth=0;
						 int femaleVotersInBooth=0;
						 int totalVoters=0;
						 int totalMales=0;
						 int totalFemales=0;
						 
						 List<Object[]> votersCount = boothPublicationVoterDAO.getVotersCountAgeWiseForPanchayat(group.getId(), group.getPopulateId(), boothIDs);
						 
						 for(Object[] param:votersCount){
							 if((param[1].toString()).equalsIgnoreCase("F")){
								 femaleVotersInBooth=Integer.parseInt(param[0].toString());
								 totalFemales=totalFemales+femaleVotersInBooth;
							 }else{
								 maleVotersInBooth=Integer.parseInt(param[0].toString());
								 totalMales=totalMales+maleVotersInBooth;
							 }
							 totalVoters=totalFemales+totalMales;
						 }
						 PanchayatVO pnchytVO=new PanchayatVO();
						 
						 pnchytVO.setPanchayatId(panchayathId);
						 pnchytVO.setPanchayatName(panchayatsMap.get(panchayathId));
						 pnchytVO.setMaleVoters(totalMales);
						 pnchytVO.setFemaleVoters(totalFemales);
						 pnchytVO.setTotalVoters(totalVoters);
						 //float percentage=(totalVoters*100)/totalVotersInPanchayat;
						 Double percentage= Double.valueOf(df.format(Long.valueOf(totalVoters)*100/(float)totalVotersInPanchayat));
						 pnchytVO.setPercentage(percentage.toString());
						 pnchytVO.setTotalPanchayatVoters(totalVotersInPanchayat);
						 pnchytVO.setTopCastes(top3CstsList);
						 pnchytVO.setSelectedCastes(slctedCstsList);
						 panchayatVOs.add(pnchytVO);
						 //System.out.println(panchayathId+":tot-"+totalVoters+":female-"+totalFemales+":male-"+totalMales);
					 }
				 }
				
			 }
				 }
			 pnchytVO1.setPanchayatList(panchayatVOs);
			 pnchytVO1.setBoothsList(boothsVO);
			 pnchytVO1.setMunicipalitesBoothsMap(MuncipalBoothsMap);
			 panchayatVOList.add(pnchytVO1);
		 }
		 return panchayatVOList;
	}
	
	public List<CastVO> getTopCastes(List<Object[]> castes,String type){
		Map<Long,CastVO> castesMap= new HashMap<Long, CastVO>();
		 
		 for(Object[] param:castes){
			 CastVO value=castesMap.get(Long.valueOf(param[2].toString()));
			 if(value!=null){
				 Long count=value.getCastCount();
				 count=count+Long.valueOf(param[0].toString());
				 value.setCastCount(count);
				 
				 castesMap.put(Long.valueOf(param[2].toString()), value);
			 }
			 else{
				 CastVO castVO=new CastVO();
				 castVO.setCastName(param[1].toString());
				 castVO.setCastCount(Long.valueOf(param[0].toString()));
				 castVO.setCastStateId(Long.valueOf(param[2].toString()));
				 castesMap.put(Long.valueOf(param[2].toString()), castVO);
			 }
		 }
		 List<CastVO> cstVOList=new ArrayList<CastVO>(castesMap.values());
		 Collections.sort(cstVOList,sourceSort);
		 Collections.reverse(cstVOList);
		 List<CastVO> top3CstsList=new ArrayList<CastVO>();
		 if(type.equalsIgnoreCase("top3castes")){
		 int length=0;
		 if(cstVOList.size()>=3){
			 length=3;
		 }else{
			 length=cstVOList.size();
		 }
		 
		 for(int i=0;i<length;i++){
			 if(cstVOList.get(i)!=null){
				 top3CstsList.add(cstVOList.get(i));
			 }
		 }
		 }
		 else{
			 top3CstsList=cstVOList;
		 }
		 return top3CstsList;
	} 
	 public static Comparator<CastVO> sourceSort = new Comparator<CastVO>()
				{
					  
				  public int compare(CastVO cstVO1, CastVO cstVO2)
					{
					   return (cstVO1.getCastCount().intValue()) - (cstVO2.getCastCount().intValue());
					}
			  };
	
	public OptionVO getPartyPerformantForSelectedConstituency(Long constituencyId,Long electionId,Long partyId)
	{
		 OptionVO optionVO = null;
		 try {
			 LOG.debug("Enterd Into getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ");
			 Long electionYear = Long.valueOf(electionDAO.get(electionId).getElectionYear());
			 String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
			 List<SelectOptionVO> mandals = null;
			 List<SelectOptionVO> wards = null;
			// List<SelectOptionVO> booths = null;
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Object[]> tehsilsList = delimitationConstituencyMandalDAO.getMandalIdsByConstituencyId(constituencyId,electionYear);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					mandals = new ArrayList<SelectOptionVO>();
					mandals = processSelectOptionVO(tehsilsList);
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> tehsilsList =  localElectionBodyDAO.getTehsilsByLocalBody(localBodyId);
				 if(tehsilsList != null && tehsilsList.size() > 0)
				 {
					 mandals = new ArrayList<SelectOptionVO>();
					 mandals = processSelectOptionVO(tehsilsList);
				 }
				 
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 Long localBodyId = assemblyLocalElectionBodyDAO.getLocalBodyIdBasedOnConstituencyId(constituencyId);
				 List<Object[]> wardsList = constituencyDAO.getWardsInALocalBody(localBodyId);
				 if(wardsList != null && wardsList.size() > 0)
				 {
					 wards = new ArrayList<SelectOptionVO>();
					 wards =  processSelectOptionVO(wardsList);
				 }
			 }
			 
			 if(constituencyType.equalsIgnoreCase(IConstants.RURAL) || constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
			 {
				 List<Long> tehsilIds = new ArrayList<Long>();
				 if(mandals != null && mandals.size() > 0)
				 {
					 for (SelectOptionVO selectOptionVO : mandals) {
						 Long tehsilId = selectOptionVO.getId();
						 tehsilIds.add(tehsilId);
					 }
				 }
				 if(tehsilIds != null && tehsilIds.size() > 0)
				 {
					List<Long> boothIds = new ArrayList<Long>();
					for (Long tehsilId : tehsilIds) {
						List<Long> boothsList =  boothDAO.getboothsByTehsilId(tehsilId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,tehsilId); 
						 }
					} 
				 }
			 }
			 else if(constituencyType.equalsIgnoreCase(IConstants.URBAN))
			 {
				 List<Long> wardIds = new ArrayList<Long>();
				 for (SelectOptionVO selectOptionVO : wards) {
					Long wardId = selectOptionVO.getId();
					wardIds.add(wardId);
				}
				if(wardIds != null && wardIds.size() > 0)
				{
					
					for (Long wardId : wardIds) {
						List<Long> boothIds = new ArrayList<Long>();
						List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
						if(boothsList != null && boothsList.size() > 0)
						{
						  for (Long boothId : boothsList) {
						  boothIds.add(boothId);
						  }
						}
						List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
						 if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
						 {
							 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
						 }
					}
				  }
				 }
				
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedConstituency() method in SuggestiveModelService Class ", e);
		}
		return optionVO;
	}
	
	public OptionVO getPartyPerformantForSelectedWard(Long constituencyId,Long wardId,Long electionId,Long partyId)
	{
		OptionVO optionVO = null;
		try {
			LOG.debug("Enterd Into getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ");
			List<Long> boothIds = new ArrayList<Long>();
			List<Long> boothsList =  boothDAO.getboothsByWardId(wardId);
			if(boothsList != null && boothsList.size() > 0)
			{
			  for (Long boothId : boothsList)
			  {
			     boothIds.add(boothId);
			  }
			}
		   List<Object[]> partyWiseVotesList =  candidateBoothResultDAO.getVotesEarnedByParyInEachBooth(constituencyId,electionId,boothIds);
		   if(partyWiseVotesList != null && partyWiseVotesList.size() > 0)
			{
			 optionVO =  processOptionVO(partyWiseVotesList,partyId,wardId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyPerformantForSelectedWard() method in SuggestiveModelService Class ", e);
		}	
		return optionVO;
	}
	public List<SelectOptionVO> processSelectOptionVO(List<Object[]> values)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		for (Object[] parms : values) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)parms[0]);
			selectOptionVO.setName(parms[1].toString());
			returnList.add(selectOptionVO);
			}
		return returnList;
	}
	
	public OptionVO processOptionVO(List<Object[]> values,Long partyId,Long id)
	{
		OptionVO optionVO = new OptionVO();
		try {
			LOG.debug("Enterd Into processOptionVO() method in SuggestiveModelService Class ");
			Long totalVoters = 0l;
			Long SelectedpartyVoters = 0l;
			Long otherPartyVoters = 0l;
			Long goodBoothCount = 0L;
		    Long veryGoodBoothCount = 0L;
		    Long badBoothCount = 0L;
		    Long veryBadBoothCount =0L;
		    Long averageBoothCount = 0L;
		      
		    List<Long> goodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> veryGoodBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> badBoothIdsList = new ArrayList<Long>(0);
		    List<Long> veryBadBoothIdsList = new ArrayList<Long>(0);
		  	List<Long> averageBoothIdsList = new ArrayList<Long>(0);
			boolean tempVar = false;
			for (Object[] parms : values) {
				if(partyId.equals((Long)parms[1]))
				{
					SelectedpartyVoters = (Long)parms[0];
				}
				totalVoters = totalVoters + (Long)parms[0];
			}
			for (Object[] parms : values) {
				if(!partyId.equals((Long)parms[1]))
				{
					otherPartyVoters = (Long)parms[0];
					break;
				}
			}
			if(values.get(0)[1].equals(partyId))
			{
				tempVar = true;
			}
			double selectedPartyTotalPercent =  new BigDecimal((SelectedpartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double comparePartyTotalPercent =  new BigDecimal((otherPartyVoters*100/totalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			double difference = 0.00;
			if(tempVar)
			{
				difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			else
			{
				 difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			}
			if(tempVar)
	    	{
	    	   if(difference <= IConstants.VERY_GOOD)
	    	   {
	    	    veryGoodBoothCount +=1;
	    	    veryGoodBoothIdsList.add(id);
	    	   }
	    	 
	    	   else if(difference <= IConstants.GOOD)
	    	   {
	    		  goodBoothCount +=1;
	    		  goodBoothIdsList.add(id);
	    	   }
	    	   else if(difference <= IConstants.AVERAGE)
	    	   {
		         averageBoothCount += 1;
		         averageBoothIdsList.add(id);
	    	   }
	    	}
	    	else
	    	{
	    	   
	    	   if(difference <= IConstants.BAD)
	    	   {
	    	    badBoothCount += 1;
	    	    badBoothIdsList.add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    veryBadBoothCount +=1;
	    	    veryBadBoothIdsList.add(id);
	    	   }
	    	}
		     optionVO.setGoodBoothCount(goodBoothCount);
		   	 optionVO.setVeryGoodBoothCount(veryGoodBoothCount);
		   	 optionVO.setVeryBadBoothCount(veryBadBoothCount);
		   	 optionVO.setBadBoothCount(badBoothCount);
		   	 optionVO.setAverageBoothCount(averageBoothCount); 
		   	 optionVO.setVeryGoodBoothIdsList(veryGoodBoothIdsList);
		   	 optionVO.setVeryBadBoothIdsList(veryBadBoothIdsList);
		   	 optionVO.setGoodBoothIdsList(goodBoothIdsList);
		   	 optionVO.setBadBoothIdsList(badBoothIdsList);
		   	 optionVO.setAverageBoothIdsList(averageBoothIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised in processOptionVO() method in SuggestiveModelService Class ", e);
		}
		 return optionVO;
	}
	public void getPartyPerformanceForlocation(Map<Long,Map<Long,Long>> resultMap,OptionVO optionVO, Long selectedpartyId)
	{
		try{
			
		 for(Long id:resultMap.keySet())
		 {
			boolean tempVar = false;
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   if(selectedPartyTotal > comparePartyTotal)
			tempVar = true;
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	 double difference = 0.00;
	    	 
	    	 if(tempVar)
	    	  difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 else
	    	  difference = new BigDecimal(comparePartyTotalPercent - selectedPartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	    	 
	    	if(tempVar)
	    	{
	    	   if(difference >= IConstants.VERY_GOOD)
	    	   {
	    		optionVO.setVeryGoodBoothCount(optionVO.getVeryGoodBoothCount()+1);
	    	    optionVO.getVeryGoodBoothIdsList().add(id);
	    	   }
	    	 
	    	   else if(difference >= IConstants.GOOD)
	    	   {
	    		  optionVO.setGoodBoothCount(optionVO.getGoodBoothCount()+1);
	    		  optionVO.getGoodBoothIdsList().add(id);
	    	   }
	    	   else if(difference >= IConstants.AVERAGE)
	    	   {
		         optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
		         optionVO.getAverageBoothIdsList().add(id);
	    	   }
	    	}
	    	else
	    	{
	    	  if(difference <= IConstants.AVERAGE)
		      {
			       optionVO.setAverageBoothCount(optionVO.getAverageBoothCount()+1);
			       optionVO.getAverageBoothIdsList().add(id);
		      }
	    		else if(difference <= IConstants.BAD)
	    	   {
	    	    optionVO.setBadBoothCount(optionVO.getBadBoothCount()+1);
	    	    optionVO.getBadBoothIdsList().add(id);
	    	   }
	    	 
	    	   else
	    	   {
	    	    optionVO.setVeryBadBoothCount(optionVO.getVeryBadBoothCount()+1);
	    	    optionVO.getVeryBadBoothIdsList().add(id);
	    	   }
	    	}
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	
	
	//All ELection Years
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,List<Long> electionIds,String tempVar)
	{
		List<PartyPositionVO> resultList = null;
		try{
		List<Long> constituencyIdsList = new ArrayList<Long>(0);
		constituencyIdsList.add(constituencyId);
		List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
		List<Object[]> electionList = null;
		String tempLocationName = "";
		
		if(electionIds != null && electionIds.size()> 0)
		{
			electionList = new ArrayList<Object[]>();
			for (Long eleId : electionIds) {
				Object[] eleIds = {eleId};
				 electionList.add(eleIds);
			}
		}
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
		{
		  if(locationId.toString().substring(0,1).equalsIgnoreCase("2"))
		  {
		   locationId = new Long(locationId.toString().substring(1));
		   if(tempVar != null && tempVar.equalsIgnoreCase("all"))
		    electionList = hamletBoothElectionDAO.findAllElectionsHappendInAMandal(locationId,constituencyIdsList); 
		  
		   locationType = IConstants.MANDAL;
		   tempLocationName = IConstants.PANCHAYAT;
		  }
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  if(tempVar != null && tempVar.equalsIgnoreCase("all"))
			electionList = hamletBoothElectionDAO.findAllElectionsHappendInAPanchayat(locationId);
		  tempLocationName = IConstants.BOOTH;
		}
		
		if(electionList != null && electionList.size() > 0)
		{
		   for(Object[] params:electionList)
		   {
			   String electionType = electionDAO.get((Long)params[0]).getElectionScope().getElectionType().getElectionType();
			  if(electionType.equalsIgnoreCase("Assembly"))
			  {
				if(!assemblyEleIdsList.contains((Long)params[0]))
				assemblyEleIdsList.add((Long)params[0]);
			  }
		   } 	
		}
		
		
		
		if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
		{
			assemblyEleIdsList = electionDAO.getSortedElectionIds(assemblyEleIdsList);
			
			resultList = new ArrayList<PartyPositionVO>(0);
			  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
				  
			  PartyPositionVO partyPositionVO = null;
			  for(Long eleId :assemblyEleIdsList)
			  {
				Election election = electionDAO.get(eleId);
				partyPositionVO = new PartyPositionVO();
				List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
				
				PartyPositionVO range = null;
				for(SuggestiveRange suggestiveRange:suggestiveRangeList)
				  {
					range = new PartyPositionVO();
					range.setName(suggestiveRange.getName());
					range.setMinValue(suggestiveRange.getMinValue());
					range.setMaxValue(suggestiveRange.getMaxValue());
					rangeList.add(range);
				  }
				partyPositionVO.setPartyPositionVOList(rangeList);
				partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
				partyPositionVO.setId(eleId);
				partyPositionVO.setConstituencyId(constituencyId);
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
				 getMandalWisePartyPerformanceReport(constituencyId, locationId, eleId, partyPositionVO, partyId);
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 getPanchayatWisePartyPerformance(constituencyId, locationId, eleId, partyPositionVO, partyId);
				
				resultList.add(partyPositionVO);
			  }
		}
		
		if(resultList != null && resultList.size() == 2){
		  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
		  resultList.get(0).setSuggestedLocations(suggestedLocations);
		}	

		//For PollingPercentage Panchayats
		if(resultList != null && resultList.size() > 0){
			getPollingPercentageForALocation(resultList.get(0),tempLocationName,constituencyId);
			List<PartyPositionVO>  panchayatVos = getMoreVotersAddedLocDetailsWherePartyIsPoor(resultList.get(0).getPartyPositionVOList());
			resultList.get(0).setAddedVoterDetails(panchayatVos);
			
		//Percentage
		Map<Long,Map<String,Long>> map = new HashMap<Long, Map<String,Long>>(0);//<electionId,Map<strong,totalValidVotes>>
		for(PartyPositionVO partyPositionVO1:resultList)
		{
		   Map<String,Long> map2 = map.get(partyPositionVO1.getId());
		   if(map2 == null)
		   {
			  map2 = new HashMap<String, Long>(0);
			  map.put(partyPositionVO1.getId(), map2);
		   }
		   if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
		   {
			 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
			 {
				 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
				 {
					for(PartyPositionVO partyPositionVO3:partyPositionVO2.getPartyPositionVOList())
					{
					  Long total = map2.get(partyPositionVO2.getName());
					   if(total == null)
						 map2.put(partyPositionVO2.getName(), partyPositionVO3.getTotalValidVotes());
					   else
						map2.put(partyPositionVO2.getName(), total+partyPositionVO3.getTotalValidVotes());
					}
				 }
			 }
		   }
		}
		
		for(PartyPositionVO partyPositionVO1:resultList)
		{
		  Long totalVotes = candidateBoothResultDAO.getConstituencyTotalVotes(constituencyId, partyPositionVO1.getId());
		  
		  Map<String, Long> totalVotesMap = map.get(partyPositionVO1.getId());
		  
		  if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
		   {
			 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
			 {
				 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
				 {
					  double percentage =  new BigDecimal((totalVotesMap.get(partyPositionVO2.getName())*100.0/totalVotes)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					  partyPositionVO2.setRangePercentage(percentage);
				 }
			 }
		   }
		}
		
		
		
		
		}
		 return resultList;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-"+e);
		 return resultList;
		}
	}
	
	
  public void getPollingPercentageForALocation(PartyPositionVO partyPositionVO,String locationType,Long constituencyId)
  {
	try{
	List<Long> weakLocationIdsList = new ArrayList<Long>(0);//<electionId,PanchayatIdsList>
	List<Long> strongLocationIdsList = new ArrayList<Long>(0);//<electionId,panchayatIdsList
	List<Long> totalLocationIdsList = new ArrayList<Long>(0); 
	
	   List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	   if(partyPositionVOList != null && partyPositionVOList.size() > 0)
	   {
		   for(PartyPositionVO positionVO :partyPositionVOList)
		   {
			  if(positionVO.getMinValue() < 0)
			  {
			    if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			    {
				   for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				    if(!weakLocationIdsList.contains(positionVO2.getId()))
				     weakLocationIdsList.add(positionVO2.getId());
			     }
			   }
			   else if(positionVO.getMinValue() > 0)
			   {
			     if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
				 {
					for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
					 if(!strongLocationIdsList.contains(positionVO2.getId()))
					  strongLocationIdsList.add(positionVO2.getId());
				  }
			   }
			  
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
				for(PartyPositionVO positionVO2 :positionVO.getPartyPositionVOList())
				 if(!totalLocationIdsList.contains(positionVO2.getId()))
					 totalLocationIdsList.add(positionVO2.getId());
			  }
			  
			 }
		   }
	   if(strongLocationIdsList != null && strongLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,strongLocationIdsList,partyPositionVO.getId(),partyPositionVO,"strongLocations",totalLocationIdsList);
	   
	   if(weakLocationIdsList != null && weakLocationIdsList.size() > 0)
		 getPollingPercentage(locationType,constituencyId,weakLocationIdsList,partyPositionVO.getId(),partyPositionVO,"weakLocations",totalLocationIdsList);  
	   
	 
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getPollingPercentageForALocation() method, Exception - "+e);
			
		}
	}
  
  
  
  
  public void getPollingPercentage(String locationType,Long constituencyId,List<Long> locationIdsList,Long electionId,PartyPositionVO partyPositionVO,String tempVar,List<Long> totalLocationIdsList)
  {
	try{
		
		Map<Long,Double> resultMap = new HashMap<Long, Double>(0);//locationId,PollingPercentage
		Map<Long,Double> partyPercentage = new HashMap<Long, Double>(0);//locationId,selectedPartyPercentage
		Map<Long,String> locationNameMap = new HashMap<Long, String>(0);//locationId,locationName
		
		Map<Long,List<Long>> locationIdsMap = new HashMap<Long, List<Long>>(0);//Map<PanchayatId,List<BoothIds>>
		Map<Long,Long> totalVotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,totalvotes
		Map<Long,Long> validvotesMap = new HashMap<Long, Long>(0);//Map<panchayatId,validvotes
		Map<Long,PartyPositionVO> partyPositionMap = new HashMap<Long, PartyPositionVO>(0);
		if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(locationIdsList, electionId);
		  if(list != null && list.size() > 0)
		  {
			for(Object[] params:list)
			{
				 List<Long> boothList = locationIdsMap.get((Long)params[0]);
				 if(boothList == null)
				 {
					 boothList = new ArrayList<Long>(0);
					 locationIdsMap.put((Long)params[0], boothList);
				 }
				  if(!boothList.contains((Long)params[1]))
					  boothList.add((Long)params[1]);
			 }
		  }
		 }
		
		
		if(locationType.equalsIgnoreCase(IConstants.BOOTH) && locationIdsList != null && locationIdsList.size() > 0)
		{
		   for(Long boothId : locationIdsList)
		   {
			   List<Long> boothIds = new ArrayList<Long>(0);
			   boothIds.add(boothId);
			   locationIdsMap.put(boothId, boothIds);
		   }
		}
		
		if(locationIdsMap != null && locationIdsMap.size() > 0)
		{
		  for(Long locationId : locationIdsMap.keySet())
		  {
			 List<Long> boothIdList = locationIdsMap.get(locationId);
			 Long totalVotes = boothDAO.getTotalVotesByBoothIdsList(boothIdList);
			 totalVotesMap.put(locationId, totalVotes);
		  }
		}
		
		if(partyPositionVO.getPartyPositionVOList() != null && partyPositionVO.getPartyPositionVOList().size() > 0)
		{
		  for(PartyPositionVO positionVO:partyPositionVO.getPartyPositionVOList())
		  {
			  if(positionVO.getPartyPositionVOList() != null && positionVO.getPartyPositionVOList().size() > 0)
			  {
			   for(PartyPositionVO locationVO:positionVO.getPartyPositionVOList())
			   {
			     partyPercentage.put(locationVO.getId(), locationVO.getPartyPercentage());
			     locationNameMap.put(locationVO.getId(), locationVO.getName());
			     validvotesMap.put(locationVO.getId(), locationVO.getTotalValidVotes());
			     
			     
			     PartyPositionVO positionVO2 = partyPositionMap.get(locationVO.getId());
			     if(positionVO2 == null)
			     {
			    	 positionVO2 = new PartyPositionVO();
			    	 positionVO2.setId(locationVO.getId());
			    	 positionVO2.setName(locationVO.getName());
			    	 positionVO2.setMinValue(positionVO.getMinValue());
			    	 positionVO2.setMaxValue(positionVO.getMaxValue());
			    	 positionVO2.setPartyPercentage(locationVO.getPartyPercentage());
			    	 positionVO2.setTotalValidVotes(locationVO.getTotalValidVotes());
			    	 positionVO2.setTempVar(positionVO.getName());
			    	 partyPositionMap.put(locationVO.getId(), positionVO2);
			    	 
			     }
			     
			   }
			  }
		  }
		}
		  		  
		if(totalVotesMap != null && totalVotesMap.size() > 0)
		{
		  for(Long id : totalVotesMap.keySet())
		  {
			double difference =  new BigDecimal((validvotesMap.get(id)*100/totalVotesMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations")) 
			{
			 if(difference < IConstants.LOW_VOTING_PERCENTAGE_IN_STRONG_LOCATIONS) 
			   resultMap.put(id, difference);
			}
			else{
			 if(difference > IConstants.HIGH_VOTING_PERCENTAGE_IN_WEEK_LOCATIONS) 
				resultMap.put(id, difference);	
			}
		  }
		}
	if(resultMap != null && resultMap.size() > 0)
		{
			List<PartyPositionVO> pollingPercentageVOList = new ArrayList<PartyPositionVO>(0);
			
			for(Long locationId:resultMap.keySet())
			{
				PartyPositionVO resultVO = partyPositionMap.get(locationId);
				
				PartyPositionVO positionVO = checkPartyPositionVOExist(resultVO.getMinValue(), resultVO.getMaxValue(), pollingPercentageVOList);
				if(positionVO == null)
				{
					positionVO = new PartyPositionVO();
					positionVO.setName(resultVO.getTempVar());
					positionVO.setMinValue(resultVO.getMinValue());
					positionVO.setMaxValue(resultVO.getMaxValue());
					pollingPercentageVOList.add(positionVO);
				}
				List<PartyPositionVO> partyPositionVOList = positionVO.getPartyPositionVOList();
				if(partyPositionVOList == null)
					partyPositionVOList = new ArrayList<PartyPositionVO>(0);
				
				PartyPositionVO locationVO = checkPartyPositionVOExist(locationId, partyPositionVOList);
				if(locationVO == null)
				{
					locationVO = new PartyPositionVO();
					locationVO.setId(locationId);
					locationVO.setName(resultVO.getName());
					locationVO.setPartyPercentage(resultVO.getPartyPercentage());
					locationVO.setPollingPercentage(resultMap.get(locationId));
					partyPositionVOList.add(locationVO);
				}
				
				positionVO.setPartyPositionVOList(partyPositionVOList);
				
			}
			if(tempVar != null && tempVar.equalsIgnoreCase("strongLocations"))
			 partyPositionVO.setStrongPollingPercentVOList(pollingPercentageVOList);
			else
				partyPositionVO.setWeakPollingPercentVOList(pollingPercentageVOList);
			  
		  }
		  
	}catch (Exception e) {
	 e.printStackTrace();
	 LOG.error(" Exception Occured in getPollingPercentage method, Exception - "+e);
	}
  }
  
  
  public PartyPositionVO checkPartyPositionVOExist(Double minValue,Double maxValue,List<PartyPositionVO> list)
  {
	 try{
		if(list == null || list.size() == 0)
		 return null;
		for(PartyPositionVO partyPositionVO : list)
		 if(partyPositionVO.getMinValue().equals(minValue) && partyPositionVO.getMaxValue().equals(maxValue))
		  return partyPositionVO;
		 
	   return null;
	 }catch (Exception e) {
      e.printStackTrace();
      LOG.error(" Exception Occured in checkPartyPositionVOExist() method, Exception - "+e);
      return null;
	 }
  }
	
	public void getPanchayatWisePartyPerformance(Long constituencyId,Long panchayatId, Long electionId,PartyPositionVO partyPositionVO, Long partyId)
	{
		try{
			Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<boothId,Map<partyId,votesEarned>>
			List<Long> boothIdsList = hamletBoothElectionDAO.getBoothIdsByPanchayatId(panchayatId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0)
				{
				  List<Object[]> list = candidateBoothResultDAO.getVotesEarnedByBoothIdsList(constituencyId, electionId, boothIdsList);
				  if(list != null && list.size() > 0)
				  {
					  Map<Long,Long> partyMap = null;
					  for(Object[] params:list)
					  {
						  partyMap = resultMap.get((Long)params[0]);
						  if(partyMap == null)
						  {
							  partyMap = new HashMap<Long, Long>(0);
							  resultMap.put((Long)params[0], partyMap);
						  }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1], (Long)params[2]);
						 else
						  partyMap.put((Long)params[1], (Long)params[2]+votesEarned);
					  }
				  }
				}
				
				if(resultMap != null && resultMap.size() > 0)
					getPartyPerformance(resultMap,partyPositionVO, partyId,"booth");	
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("ExceptionOccured in getPanchayatWisePartyPerformance() method, Exception - "+e);
		}
	}
	
	
	public void getMandalWisePartyPerformanceReport(Long constituencyId,Long locationId,Long electionId,PartyPositionVO partyPositionVO,Long partyId)
	{
		try{
		Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
		Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
		
		
		List<Long> panchaytIdsList = hamletBoothElectionDAO.getPanchayatIdsByTehsilIdAndElectionId(locationId, electionId);
		
		if(panchaytIdsList != null && panchaytIdsList.size() > 0)
		{
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params:list)
			  {
				  List<Long> panchayatIds = boothIds.get((Long)params[1]);
				  if(panchayatIds == null)
				  {
				   panchayatIds = new ArrayList<Long>(0);
				   boothIds.put((Long)params[1], panchayatIds);
				  }
				  if(!panchayatIds.contains((Long)params[0]))
				   panchayatIds.add((Long)params[0]);
			  }
			  
			  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
			  if(resultList != null && resultList.size() > 0)
			  {
				 for(Object[] params:resultList)
				 {
				   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
				   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				   {
					 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
					 for(Long panchayatId :panchayatIdsList)
					 {
						 partyMap = resultMap.get(panchayatId);
						 if(partyMap == null)
						 {
							 partyMap = new HashMap<Long, Long>(0);
							 resultMap.put(panchayatId, partyMap);
						 }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1],(Long)params[2]);
						 else
						  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
					 }
				   }
				   
				 }
			  }
			  
		  }
		}
		if(resultMap != null && resultMap.size() > 0)
			getPartyPerformance(resultMap,partyPositionVO, partyId,"panchayat"); 
		
		
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
		  }
	}
	
	public void getPartyPerformance(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId,String locationType)
	{
		try{
		
			//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
			
			Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
			Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
			
			if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
			{
				List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
			  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
			  if(boothList != null && boothList.size() > 0)
			  {
				  for(Object[] params:boothList)
				  {
					  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
					  if(boothIdsList == null)
					  {
						boothIdsList = new ArrayList<Long>(0);
						boothIdsMap.put((Long)params[0], boothIdsList);  
					  }
					  if(!boothIdsList.contains((Long)params[1]))
						  boothIdsList.add((Long)params[1]);  
				  }
				  
			  }
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			{
				for(Long id:resultMap.keySet())
				 {
					List<Long> boothIdsList = boothIdsMap.get(id);
					if(boothIdsList == null)
					{
					  boothIdsList = new ArrayList<Long>(0);
					  boothIdsMap.put(id, boothIdsList);
					}
					if(!boothIdsList.contains(id))
						boothIdsList.add(id);
				 }
			}
			
		if(boothIdsMap != null && boothIdsMap.size() > 0)
		{
		  for(Long id : boothIdsMap.keySet())
			panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
		}
			
		 for(Long id:resultMap.keySet())
		 {
			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			Long comparePartyTotal = 0L;
				 
			  for(Long partysId:partyMap.keySet())
			  {
			    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
				 comparePartyTotal = partyMap.get(partysId);
				  
			  }
		   
			     
		  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      
	      double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	      
	    	
	      String locationName = "";
	      if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
	    	 locationName = panchayatDAO.getPanchayatNameById(id); 
	      else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
	    	 locationName = boothDAO.getBoothPartNoByBoothId(id);
	      
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(id,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(id);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
		}
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
		}
	}
	
	public PartyPositionVO checkPartyPositionVOExist(Long locationId,List<PartyPositionVO> list)
	{
		try{
		if(list == null)
		 return null;
		for(PartyPositionVO positionVO:list)
		 if(positionVO.equals(locationId))
		  return positionVO;
			
		 return null;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - "+e);
		 return null;
		}
	}
	
	
	
	 @SuppressWarnings("unchecked")
		public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(List<SelectOptionVO> ConstituenciesForUserAccessed,Long electionId,Long electionYear)
		{
		 List<SelectOptionVO> constituencyList = new ArrayList<SelectOptionVO>(0);;
		try{
			List<Long> constituencyIds =  voterInfoDAO.getNONURBANConstituencyIds(electionId,electionYear,1L);
			if(ConstituenciesForUserAccessed != null && ConstituenciesForUserAccessed.size() > 0 && constituencyIds != null)
			{
				for(SelectOptionVO selectOptionVO : ConstituenciesForUserAccessed)
				{
					if(constituencyIds.contains(selectOptionVO.getId()))
						constituencyList.add(selectOptionVO);
				}
			}
			
			return constituencyList;
		}catch (Exception e) {
			e.printStackTrace();
			return constituencyList;
		}
		}
	 public List<SelectOptionVO> getPartyDetailsByMandal(Long tehsilId){
		 List<SelectOptionVO> nominatedPartiesLists = null;
		 try{
			 List<Object[]> partyList= hamletBoothElectionDAO.getParticipatedPartiesByEleIdNTehsilId(tehsilId);
			 nominatedPartiesLists = new ArrayList<SelectOptionVO>();
				if(partyList !=null && partyList.size()>0)
					for (Object[] parms : partyList) {
						nominatedPartiesLists.add(new SelectOptionVO(Long.valueOf(parms[0].toString()),parms[1].toString()));
					}
			 return nominatedPartiesLists;
		 }catch(Exception e){
			 e.printStackTrace();
		 return null;
		}			
	}	
	 
	 public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId){
		 List<SelectOptionVO> electionYearslist;
			List elections;
			try {
				electionYearslist = new ArrayList<SelectOptionVO>();
				if(electionScope !=null && electionScope.size()>0){
					for (Long scopeId : electionScope) {
						elections = nominationDAO.findByPartyIdAndTehsilId(scopeId,partyId,tehsilId);
						for (int i = 0; i < elections.size(); i++) {
							Object[] parms = (Object[]) elections.get(i);
							electionYearslist.add(new SelectOptionVO(Long.parseLong(parms[0].toString()), parms[1].toString().concat("("+parms[2].toString()+")")));
						}						
					}					
				}				
			return electionYearslist;
			} catch (Exception e) {
				e.printStackTrace();
			return null;
			}

	 }
 
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevelForMincipality(Long userId,Long constituencyId,List<Long> casteIdsList){
		 List<YouthLeaderSelectionVO> returnList = new ArrayList<YouthLeaderSelectionVO>();
		 List<SelectOptionVO> booths= null;
		 List<Long> boothIds= null;
		 List<BasicVO> basicVOListForBooth = null;
		 DecimalFormat deciamlFormat = new DecimalFormat("#.##");
		 Long publicationId = 0L;
		 YouthLeaderSelectionVO boothyouthSelectionVO  = null;
		 List<YouthLeaderSelectionVO> botthDetailsList = null;
		 List<YouthLeaderSelectionVO> botthLevelList = null;
		 Map<Long,Long> totalVotersInBooth = new HashMap<Long, Long>(); //Map<id,totalVoters>
		 Map<Long,List<BasicVO>> casteMapForBooth = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
		 Long boothTotalVoters = 0L;
		 List<YouthLeaderSelectionVO> topCasteList = null;
		 List<YouthLeaderSelectionVO> topCastesListInTotalMuncipality = null;
		 List<BasicVO> selectedCastesinBooths = null;

		 try{
			 publicationId = publicationDateDAO.getLatestPublicationId();
			 List<Long> list = assemblyLocalElectionBodyDAO.getLocalEleBodyIdsListByConstituencyId(constituencyId, publicationId);
			 Tehsil tehsilDetails = localElectionBodyDAO.get(list.get(0)).getTehsil();
			 List<Booth> boothIdsList = boothDAO.getboothsDetailsByTehsilId(list.get(0),publicationId);			 
			if(boothIdsList != null && boothIdsList.size()>0){
				booths = new ArrayList<SelectOptionVO>();
		 		boothIds = new ArrayList<Long>();
				 
		 		for (Booth booth : boothIdsList) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
			 		selectOptionVO.setId(booth.getBoothId());
			 		selectOptionVO.setName(booth.getPartNo());
			 		booths.add(selectOptionVO);
			 		boothIds.add(selectOptionVO.getId());
				}	
		 		
		 		if(boothIds != null && boothIds.size() > 0)
				{
					for (Long boothId : boothIds) {
						Long totalVoter = boothPublicationVoterDAO.getTotalVoters(boothId);
						List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothId(boothId,publicationId,userId);
						boothTotalVoters = boothTotalVoters + totalVoter;
						totalVotersInBooth.put(boothId, totalVoter);
						int count = 0;
						basicVOListForBooth = new ArrayList<BasicVO>();
						selectedCastesinBooths = new ArrayList<BasicVO>();
						if(casteDetails != null && casteDetails.size() >0){
						for (Object[] parms : casteDetails) {
							
							
							if(IConstants.MAX_LEVEL > count)
							{
								BasicVO basicVO = new BasicVO();
								basicVO.setId(boothId);
								basicVO.setCount((Long)parms[1]);
								basicVO.setName(parms[0].toString());
							
								basicVO.setPerc(Double.valueOf(deciamlFormat.format((Long)parms[1]*100/totalVoter.floatValue())));
								
								basicVOListForBooth.add(basicVO);
							}
							
							if(casteIdsList.contains(parms[2])){
								BasicVO basicVO1 = new BasicVO();
								basicVO1.setId(boothId);
								basicVO1.setCount((Long)parms[1]);
								basicVO1.setName(parms[0].toString());
								basicVO1.setPerc(Double.valueOf(deciamlFormat.format((Long) parms[1]*100/totalVoter.floatValue())));
								
								selectedCastesinBooths.add(basicVO1);
							}
									
							count ++;
						}
						basicVOListForBooth.addAll(selectedCastesinBooths);
					}
						casteMapForBooth.put(boothId, basicVOListForBooth);
					}
				}
		 		
		 		botthDetailsList = new ArrayList<YouthLeaderSelectionVO>();
				YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
				for (Long boothId : boothIds) {
					boothyouthSelectionVO = new YouthLeaderSelectionVO();
					
					List<BasicVO> boothCasteDate = casteMapForBooth.get(boothId);
					if(boothCasteDate != null && boothCasteDate.size() > 0)
					{
						botthLevelList = new ArrayList<YouthLeaderSelectionVO>();
						for (BasicVO basicVO : boothCasteDate) {
							YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
							youthSelectionVO.setCasteName(basicVO.getName());
							youthSelectionVO.setCasteVoters(basicVO.getCount());
							youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
							botthLevelList.add(youthSelectionVO);
						}
						
					}
					boothyouthSelectionVO.setBoothId(boothId);
					boothyouthSelectionVO.setBoothName(boothDAO.get(boothId).getPartNo());
					boothyouthSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
					boothyouthSelectionVO.setBoothLevelLeadersList(botthLevelList);
					botthDetailsList.add(boothyouthSelectionVO);
					
				}
				
				List<VoterCastInfo> tehsilCastDetails = voterCastInfoDAO.getVotersCastInfo(5l,list.get(0),constituencyId,publicationId,userId);
				
					if(tehsilCastDetails != null && tehsilCastDetails.size()>0){
						int count =0;
						topCasteList = new ArrayList<YouthLeaderSelectionVO>();
						topCastesListInTotalMuncipality = new ArrayList<YouthLeaderSelectionVO>();
						for (VoterCastInfo parms : tehsilCastDetails) {
							
							if(IConstants.MAX_LEVEL > count){
								
								YouthLeaderSelectionVO youthLeaderSelectionVO1 = new YouthLeaderSelectionVO();
								youthLeaderSelectionVO1.setCasteName(parms.getCasteState().getCaste().getCasteName());
								youthLeaderSelectionVO1.setCasteVotersPerc(parms.getCastePercentage());
								topCasteList.add(youthLeaderSelectionVO1);
							}
							
							if(casteIdsList.contains(parms.getCasteState().getCaste().getCasteId())){

								YouthLeaderSelectionVO youthLeaderSelectionVO1 = new YouthLeaderSelectionVO();
								youthLeaderSelectionVO1.setCasteName(parms.getCasteState().getCaste().getCasteName());
								youthLeaderSelectionVO1.setCasteVotersPerc(parms.getCastePercentage());
								topCastesListInTotalMuncipality.add(youthLeaderSelectionVO1);
							}
						}
					}
				youthLeaderSelectionVO.setBoothLevelLeadersList(botthDetailsList);
				youthLeaderSelectionVO.setBoothTotalVoters(boothTotalVoters);
				youthLeaderSelectionVO.setMandalId(tehsilDetails.getTehsilId());
				youthLeaderSelectionVO.setMandalName(tehsilDetails.getTehsilName());
				youthLeaderSelectionVO.setPanchayatLevelLeadersList(topCasteList);
				youthLeaderSelectionVO.setAreaWiseCasteList(topCastesListInTotalMuncipality);
				returnList.add(youthLeaderSelectionVO);
			}

		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return returnList;		 
	 }
	 
	 public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long userid,Long constituencyId,List<Long> casteIdsList)
	 {
		 List<YouthLeaderSelectionVO> returnList = new ArrayList<YouthLeaderSelectionVO>();
		 try {
			 LOG.debug("Enterd Into findingBoothInchargesForBoothLevel() method in SuggestiveModelService Class ");
				List<SelectOptionVO> panchayats = new ArrayList<SelectOptionVO>();
				List<Long> panchayaIds = new ArrayList<Long>();
				List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
				List<Long> boothIds = new ArrayList<Long>();
				Map<Long, Long> totalVotersInPanchayat = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long, Long> totalVotersInBooth = new HashMap<Long, Long>();//Map<id,totalVoters>
				Map<Long,List<BasicVO>> casteMapForPanchayat = new HashMap<Long, List<BasicVO>>();//Map<panchayatid,catseDetails>
				Map<Long,List<BasicVO>> casteMapForBooth = new HashMap<Long, List<BasicVO>>();//Map<booyhid,catseDetails>
				Long publicationId = 0l;
				List<BasicVO> basicVOListForPanchayat = null;
				List<BasicVO> basicVOListForBooth = null;
				List<YouthLeaderSelectionVO> botthLevelList = null;
				List<YouthLeaderSelectionVO> panchayatLevelList = null;
				YouthLeaderSelectionVO boothyouthSelectionVO  = null;
				List<YouthLeaderSelectionVO> botthDetailsList = null;
				 DecimalFormat df = new DecimalFormat("#.##");
				publicationId = publicationDateDAO.getLatestPublicationId();
				List<Long> tehsilIds = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
				//List<BasicVO> selectedCastesinBooths = null;
				if(tehsilIds != null && tehsilIds.size() > 0)
				{
					for (Long mandalId : tehsilIds) {
						List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByTehsilId(mandalId);
						if(panchayatsList != null && panchayatsList.size() > 0)
						{
							panchayats = new ArrayList<SelectOptionVO>();
							panchayaIds = new ArrayList<Long>();
							for (Object[] parms : panchayatsList) {
								SelectOptionVO selectOptionVO = new SelectOptionVO();	
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								panchayats.add(selectOptionVO);
								panchayaIds.add(selectOptionVO.getId());
							}
						}
						
						if(panchayaIds != null && panchayaIds.size() > 0)
						{
							List<Object[]> panchaytVotersCount = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(panchayaIds,userid,publicationId,3l,constituencyId);
							if(panchaytVotersCount != null && panchaytVotersCount.size() > 0)
							{
								for (Object[] parms : panchaytVotersCount) {
									Long total = ((Long)parms[1] + (Long)parms[2]);
									totalVotersInPanchayat.put((Long)parms[0], total);
								}
							}
							for (Long panchayatId : panchayaIds) {
								List<Object[]> boothsList = boothDAO.getBoothsByPanchayat(panchayatId,publicationId);
								if(boothsList != null && boothsList.size() > 0)
								{
									basicVOListForPanchayat = new ArrayList<BasicVO>();
									for (Object[] parms : boothsList) {
										SelectOptionVO selectOptionVO = new SelectOptionVO();	
										selectOptionVO.setId((Long)parms[0]);
										selectOptionVO.setName(parms[1].toString());
										booths.add(selectOptionVO);
										boothIds.add(selectOptionVO.getId());
									}
									
								}
								List<Object[]> casteDetails = voterCastInfoDAO.getTopThreeCasteFoeSelctedLevel(panchayatId,3l,publicationId,userid);
								if(casteDetails != null && casteDetails.size() > 0)
								{
									int count = 0;
									List<BasicVO> selectedCastesinBooths = new ArrayList<BasicVO>();
									for (Object[] parms : casteDetails) {
										if(IConstants.MAX_LEVEL > count)
										{
											BasicVO basicVO = new BasicVO();
											basicVO.setId((Long)parms[0]);
											basicVO.setCount((Long)parms[2]);
											basicVO.setName(parms[1].toString());
											basicVO.setPerc((Double)parms[3]);
											basicVOListForPanchayat.add(basicVO);
											
										}
										
										if(casteIdsList.contains(parms[4])){
											BasicVO basicVO1 = new BasicVO();
											basicVO1.setId((Long)parms[0]);
											basicVO1.setCount((Long)parms[2]);
											basicVO1.setName(parms[1].toString());
											basicVO1.setPerc((Double)parms[3]);
											
											selectedCastesinBooths.add(basicVO1);
										}
										count ++;
									}
									basicVOListForPanchayat.addAll(selectedCastesinBooths);
								}
								casteMapForPanchayat.put(panchayatId, basicVOListForPanchayat);
							}
							
							
						}
						if(boothIds != null && boothIds.size() > 0)
						{
							for (Long boothId : boothIds) {
								Long totalVoter = boothPublicationVoterDAO.getTotalVoters(boothId);
								List<Object[]> casteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothId(boothId,publicationId,userid);
								totalVotersInBooth.put(boothId, totalVoter);
								int count = 0;
								basicVOListForBooth = new ArrayList<BasicVO>();
								List<BasicVO> selectedCastesinBooths = new ArrayList<BasicVO>();
								for (Object[] parms : casteDetails) {
									if(IConstants.MAX_LEVEL > count)
									{
										BasicVO basicVO = new BasicVO();
										basicVO.setId(boothId);
										basicVO.setCount((Long)parms[1]);
										basicVO.setName(parms[0].toString());
										
										basicVO.setPerc(Double.valueOf(df.format((Long)parms[1]*100/totalVoter.floatValue())));
										basicVOListForBooth.add(basicVO);
										
									}
									if(casteIdsList.contains(parms[2])){
										BasicVO basicVO1 = new BasicVO();
										basicVO1.setId((Long)boothId);
										basicVO1.setCount((Long)parms[1]);
										basicVO1.setName(parms[0].toString());
										basicVO1.setPerc(Double.valueOf(df.format((Long)parms[1]*100/totalVoter.floatValue())));
										
										selectedCastesinBooths.add(basicVO1);
									}
									count ++;
									
								}
								basicVOListForBooth.addAll(selectedCastesinBooths);
								casteMapForBooth.put(boothId, basicVOListForBooth);
							}
							
						}
						if(panchayaIds != null && panchayaIds.size() > 0)
						{
							for (Long panchayatid : panchayaIds) {
								YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
								 List<BasicVO> panchayatDetails = casteMapForPanchayat.get(panchayatid);
								 Long panchayatTotalVoters = totalVotersInPanchayat.get(panchayatid);
								 panchayatLevelList = new ArrayList<YouthLeaderSelectionVO>();
								 if(panchayatDetails != null && panchayatDetails.size() > 0)
								 {
									 for (BasicVO basicVO : panchayatDetails) {
										 YouthLeaderSelectionVO youthLeaderSelection = new YouthLeaderSelectionVO();
										 youthLeaderSelection.setCasteName(basicVO.getName());
										 youthLeaderSelection.setCasteVoters(basicVO.getCount());
										 youthLeaderSelection.setCasteVotersPerc(basicVO.getPerc());
										 panchayatLevelList.add(youthLeaderSelection);
									}
								 }
								List<Long> boothList = boothDAO.getBoothsByPanchayatId(panchayatid,publicationId);
								if(boothList != null && boothList.size() > 0)
								{
									botthDetailsList = new ArrayList<YouthLeaderSelectionVO>();
									for (Long boothId : boothList) {
										boothyouthSelectionVO = new YouthLeaderSelectionVO();
										
										List<BasicVO> boothCasteDate = casteMapForBooth.get(boothId);
										if(boothCasteDate != null && boothCasteDate.size() > 0)
										{
											botthLevelList = new ArrayList<YouthLeaderSelectionVO>();
											for (BasicVO basicVO : boothCasteDate) {
												YouthLeaderSelectionVO youthSelectionVO = new YouthLeaderSelectionVO();										
												youthSelectionVO.setCasteName(basicVO.getName());
												youthSelectionVO.setCasteVoters(basicVO.getCount());
												youthSelectionVO.setCasteVotersPerc(basicVO.getPerc());
												botthLevelList.add(youthSelectionVO);
											}
											
										}
										boothyouthSelectionVO.setBoothId(boothId);
										boothyouthSelectionVO.setBoothName(boothDAO.get(boothId).getPartNo());
										boothyouthSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
										boothyouthSelectionVO.setBoothLevelLeadersList(botthLevelList);
										botthDetailsList.add(boothyouthSelectionVO);
										
									}
									
									youthLeaderSelectionVO.setBoothLevelLeadersList(botthDetailsList);
								}
								youthLeaderSelectionVO.setPanchayatTotalVoters(panchayatTotalVoters);
								youthLeaderSelectionVO.setPanchayatId(panchayatid);
								youthLeaderSelectionVO.setMandalName(tehsilDAO.get(mandalId).getTehsilName());
								youthLeaderSelectionVO.setPanchayatName(panchayatDAO.get(panchayatid).getPanchayatName());
								youthLeaderSelectionVO.setPanchayatLevelLeadersList(panchayatLevelList);
								returnList.add(youthLeaderSelectionVO);
							}
						}
					}
				}
				
				
			} catch (Exception e) {
				LOG.error(" ExceptionOccured in findingBoothInchargesForBoothLevel() method, Exception - "+e);
			}
			
		return returnList;
	}
	 
	/* public List<YouthLeaderSelectionVO> findingBoothInchargesForBoothLevel(Long tehsilId,Long userid,Long constituencyId)
	 {
		 List<YouthLeaderSelectionVO>  returnList = new ArrayList<YouthLeaderSelectionVO>();
		 try {
			 LOG.debug("Enterd Into findingBoothInchargesForBoothLevel() method in SuggestiveModelService Class ");
				
				DecimalFormat df = new DecimalFormat("#.##");
				Long publicationId = 0l;
				Map<Long,Long> totalVotersInPanchayat = new HashMap<Long, Long>();//Map<panchayatId,totalVoters>
				Map<Long,List<BasicVO>> panchayatCasteMap = new HashMap<Long, List<BasicVO>>();//Map<panchayatId,CasteDetails>
				//Map<Long,Map<String,List<BasicVO>>> panchayatMap = new HashMap<Long, Map<String,List<BasicVO>>>();//Map<panchayatId,<Map<casteName,CasteDetails>
				Map<Long,List<BasicVO>> boothCasteMap = new HashMap<Long, List<BasicVO>>();//Map<boothId,<Map<casteName,CasteDetails>
				Map<Long,Map<Long,List<BasicVO>>> panchayatDataMap = new HashMap<Long, Map<Long,List<BasicVO>>>();//Map<panchayatId,Map<boothId,<Map<casteName,CasteDetails>
				Map<Long,Long> totalVotersInBooth = new HashMap<Long, Long>();//Map<boothId,totalVoters>
				//Map<String,List<BasicVO>> boothCasteMap = new HashMap<String, List<BasicVO>>();//Map<casteName,CasteDetails>
				List<Long> panchayatIds = new ArrayList<Long>();
				List<Long> boothIds = new ArrayList<Long>();
				List<Long> tehsilIds = new ArrayList<Long>();
				List<BasicVO> basicVOListForPanchayat = null;
				List<BasicVO> basicVOListForBooth =null;
				List<SelectOptionVO> SelectOptionVOList = null;
				Map<Long,String> panchayatNamesMap = new HashMap<Long, String>();//Map<panchayatId,Panchayat nane>
				Map<Long,String> boothNamesMap = new HashMap<Long, String>();//Map<boothId,boothName>
				Map<Long,String> tehsilNamesMap = new HashMap<Long, String>();//Map<panchayatId,tehsilName>
				List<YouthLeaderSelectionVO> panchayatTopCasteList = new ArrayList<YouthLeaderSelectionVO>();
				List<YouthLeaderSelectionVO> boothTopCasteList = new ArrayList<YouthLeaderSelectionVO>();
				publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> tehsilList = boothDAO.getTehsildByConstituency(constituencyId,publicationId);
				if(tehsilList != null && tehsilList.size() > 0)
				{
					SelectOptionVOList = new ArrayList<SelectOptionVO>();
					for (Object[] parms : tehsilList) {
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						SelectOptionVOList.add(selectOptionVO);
					}
				}
				if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
				{
					for (SelectOptionVO selOptionVO : SelectOptionVOList) {
						Long tehId = selOptionVO.getId();
						tehsilIds.add(tehId);
						//tehsilNamesMap.put(tehId, selOptionVO.getName());
					}
				}
				if(tehsilIds != null && tehsilIds.size() > 0)
				{
					List<Object[]> panchayatsList= panchayatDAO.getPanchaytsForConstituencyList(tehsilIds);
					if(panchayatsList != null && panchayatsList.size() > 0)
					{
						SelectOptionVOList = new ArrayList<SelectOptionVO>();
						for (Object[] parms : panchayatsList) {
							SelectOptionVO selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)parms[0]);
							selectOptionVO.setName(parms[1].toString());
							selectOptionVO.setLocation(parms[2].toString());
							SelectOptionVOList.add(selectOptionVO);
						}
					}
					if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
					{
						for (SelectOptionVO selOptionVO : SelectOptionVOList) {
							Long panchayatId = selOptionVO.getId();
							panchayatIds.add(panchayatId);
							panchayatNamesMap.put(panchayatId, selOptionVO.getName());
							tehsilNamesMap.put(panchayatId, selOptionVO.getLocation());
						}
					}
					if(panchayatIds != null && panchayatIds.size() > 0)
					{
						List<Object[]> panchaytVotersCount = voterCastBasicInfoDAO.getToatlVotersForSelectedLevl(panchayatIds,userid,publicationId,3l,constituencyId);
						if(panchaytVotersCount != null && panchaytVotersCount.size() > 0)
						{
							for (Object[] parms : panchaytVotersCount) {
								Long total = ((Long)parms[1] + (Long)parms[2]);
								totalVotersInPanchayat.put((Long)parms[0], total);
							}
						}
						List<Object[]> panchayatWisecasteDetails = voterCastInfoDAO.getTopCasteFoeSelctedLevel(panchayatIds,3l,publicationId,userid);
						if(panchayatWisecasteDetails != null && panchayatWisecasteDetails.size() > 0)
						{
							for (Object[] parms : panchayatWisecasteDetails) {
								BasicVO basicVO = new BasicVO();
								basicVO.setId((Long)parms[0]);
								basicVO.setCount((Long)parms[2]);
								basicVO.setName(parms[1].toString());
								basicVO.setPerc((Double)parms[3]);
								
								basicVOListForPanchayat = panchayatCasteMap.get((Long)parms[0]);
								if(basicVOListForPanchayat == null)
								{
									basicVOListForPanchayat = new ArrayList<BasicVO>();
									basicVOListForPanchayat.add(basicVO);
									panchayatCasteMap.put((Long)parms[0], basicVOListForPanchayat);
								}
								else
								{
									basicVOListForPanchayat.add(basicVO);
								}
							}
							
						}
						for (Long panchayatId : panchayatIds) {
							 List<Object[]> boothsList= boothDAO.getBoothsByPanchayat(panchayatId,publicationId);
							 if(boothsList != null && boothsList.size() > 0)
							 {
								 SelectOptionVOList = new ArrayList<SelectOptionVO>();
								 for (Object[] parms : boothsList) {
									SelectOptionVO selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									SelectOptionVOList.add(selectOptionVO);
								}
							 }
							 if(SelectOptionVOList != null && SelectOptionVOList.size() > 0)
							 {
								 for (SelectOptionVO seleVo : SelectOptionVOList) {
									 Long boothId = seleVo.getId();
									 boothIds.add(boothId);
									 boothNamesMap.put(boothId, seleVo.getName());
								}
								 
							 }
							if(boothIds != null && boothIds.size() > 0)
							{
								List<Object[]> boothWiseCount = boothPublicationVoterDAO.getTotalVotersByBooths(boothIds);
								if(boothWiseCount != null && boothWiseCount.size() > 0)
								{
									for (Object[] parms : boothWiseCount) {
										totalVotersInBooth.put((Long)parms[0], (Long)parms[1]);
									}
								}
								List<Object[]> boothWiseCasteDetails = userVoterDetailsDAO.getCasteDetailsOfVoterByBooths(boothIds,publicationId,userid);
								if(boothWiseCasteDetails != null && boothWiseCasteDetails.size() > 0)
								{
									for (Object[] parms : boothWiseCasteDetails) {
										BasicVO basicVO = new BasicVO();
										basicVO.setId((Long)parms[2]);
										basicVO.setCount((Long)parms[1]);
										basicVO.setName(parms[0].toString());
										Long totalVoter = totalVotersInBooth.get((Long)parms[2]);
										basicVO.setPerc(Double.valueOf(df.format((Long)parms[1]*100/totalVoter.floatValue())));
										basicVOListForBooth = boothCasteMap.get((Long)parms[2]);
										if(basicVOListForBooth == null)
										{
											basicVOListForBooth = new ArrayList<BasicVO>();
											basicVOListForBooth.add(basicVO);
											boothCasteMap.put((Long)parms[2], basicVOListForBooth);
										}
										else
										{
											basicVOListForBooth.add(basicVO);
										}
									}
									
								}
							}
							panchayatDataMap.put(panchayatId, boothCasteMap);
						}
						
					}
					
				}
				if(panchayatCasteMap != null && panchayatCasteMap.size() > 0)
				{
					Set<Long> panchayatIdsSet = panchayatCasteMap.keySet();
					if(panchayatIdsSet != null && panchayatIdsSet.size() > 0)
					{
						for (Long panchayatId : panchayatIdsSet) {
							List<BasicVO> panchayatCasteList = panchayatCasteMap.get(panchayatId);
							processBasicVO(panchayatId,panchayatCasteList,panchayatCasteMap);
						}
					}
				}
				if(boothCasteMap != null && boothCasteMap.size() > 0)
				{
					Set<Long> boothIdsSet = boothCasteMap.keySet();
					if(boothIdsSet != null && boothIdsSet.size() > 0)
					{
						for (Long boothId : boothIdsSet) {
							List<BasicVO> boothCasteList = boothCasteMap.get(boothId);
							processBasicVO(boothId,boothCasteList,boothCasteMap);
						}
					}
				}
				if(panchayatDataMap != null && panchayatDataMap.size() >0)
				{
					Set<Long> panIds = panchayatDataMap.keySet();
					if(panIds != null && panIds.size() > 0)
					{
						for (Long panchayatId : panIds)
						{
							YouthLeaderSelectionVO youthLeaderSelectionVO = new YouthLeaderSelectionVO();
							youthLeaderSelectionVO.setMandalName(tehsilNamesMap.get(panchayatId));
							youthLeaderSelectionVO.setPanchayatName(panchayatNamesMap.get(panchayatId));
							youthLeaderSelectionVO.setPanchayatTotalVoters(totalVotersInPanchayat.get(panchayatId));
							List<BasicVO> panCasteList = panchayatCasteMap.get(panchayatId);
							if(panCasteList != null && panCasteList.size() > 0)
							{
								for (BasicVO basicVO2 : panCasteList) {
									YouthLeaderSelectionVO panchayatLeaderSelectionVO = new YouthLeaderSelectionVO();
									panchayatLeaderSelectionVO.setCasteName(basicVO2.getName());
									panchayatLeaderSelectionVO.setCasteVoters(basicVO2.getCount());
									panchayatLeaderSelectionVO.setCasteVotersPerc(basicVO2.getPerc());
									panchayatTopCasteList.add(panchayatLeaderSelectionVO);
								}
								
							}
							boothCasteMap = panchayatDataMap.get(panchayatId);
							if(boothCasteMap != null && boothCasteMap.size() > 0)
							{
								Set<Long> booths = boothCasteMap.keySet();
								if(booths != null && booths.size() > 0)
								{
									for (Long boothId : booths) {
										List<BasicVO> boothCasteList = boothCasteMap.get(boothId);
										if(boothCasteList != null && boothCasteList.size() > 0)
										{
											for (BasicVO basicVO : boothCasteList) {
												YouthLeaderSelectionVO boothLeaderSelectionVO = new YouthLeaderSelectionVO();
												boothLeaderSelectionVO.setBoothName(boothNamesMap.get(boothId));
												boothLeaderSelectionVO.setBoothTotalVoters(totalVotersInBooth.get(boothId));
												boothLeaderSelectionVO.setCasteName(basicVO.getName());
												boothLeaderSelectionVO.setCasteVoters(basicVO.getCount());
												boothLeaderSelectionVO.setCasteVotersPerc(basicVO.getPerc());
												boothTopCasteList.add(boothLeaderSelectionVO);
											}
										}
										
									}
								}
								
							}
							youthLeaderSelectionVO.setPanchayatLevelLeadersList(panchayatTopCasteList);
							youthLeaderSelectionVO.setBoothLevelLeadersList(boothTopCasteList);
							returnList.add(youthLeaderSelectionVO);
						}
					}
				}
		 }
		 catch (Exception e) {
				LOG.error(" ExceptionOccured in findingBoothInchargesForBoothLevel() method, Exception - "+e);
			}
				
		 return returnList;
	 }
	 
	 public List<BasicVO> processBasicVO(Long id,List<BasicVO> casteList ,Map<Long,List<BasicVO>> casteMap)
	 {
		 List<BasicVO> returnList = new ArrayList<BasicVO>();
		 int count = 0;
		 if(casteList != null && casteList.size() > 0)
		 {
			 for (BasicVO basicVO : casteList) {
				 if(IConstants.MAX_LEVEL > count)
					{
						BasicVO basicVO1 = new BasicVO();
						basicVO1.setId(basicVO.getId());
						basicVO1.setCount(basicVO.getCount());
						basicVO1.setName(basicVO.getName());
						
						basicVO1.setPerc(basicVO.getPerc());
						returnList.add(basicVO1);
						
					}
					else
					{
						break;
					}
					count ++;
			}
		 }
		 casteMap.put(id, returnList);
		 return returnList;
	 }*/
	 public List<PartyPositionVO> getMoreVotersAddedLocDetailsWherePartyIsPoor(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnVal = new ArrayList<PartyPositionVO>();
		 Map<Long,PartyPositionVO> panchatayats = new HashMap<Long,PartyPositionVO>();
		 Map<Long,PartyPositionVO> suggestions = new HashMap<Long,PartyPositionVO>();
		 for(PartyPositionVO positionVo:partyPositions){
			 if(positionVo.getMinValue() < 0 && positionVo.getPartyPositionVOList() != null && positionVo.getPartyPositionVOList().size() >0){
				 returnVal.add(positionVo);
				 for(PartyPositionVO panchayat:positionVo.getPartyPositionVOList()){
					 panchatayats.put(panchayat.getId(), panchayat);
					 suggestions.put(panchayat.getId(), positionVo);
				 }
			 }
		 }
		 getAddedVoterInfo(panchatayats,suggestions);
		 return returnVal;
	 }
	 
	 public void getAddedVoterInfo(Map<Long,PartyPositionVO> panchatayats,Map<Long,PartyPositionVO> suggestions)
	 {
		 PartyPositionVO vo = null;
		try{
			
				Long publicationId = publicationDateDAO.getLatestPublicationId();
				List<Object[]> deletedVoters = voterModificationInfoDAO.getAddedVotersByPanchayats(new ArrayList<Long>(panchatayats.keySet()),publicationId);
				if(deletedVoters != null && deletedVoters.size() > 0)
				{
					for(Object[] params : deletedVoters)
					{
					  if(params[0] != null && ((Long)params[0]).longValue() >= IConstants.MIN_ADDED_VOTERS){
						vo = panchatayats.get((Long)params[1]);
						vo.setAddedVotersCount((Long)params[0]);
						PartyPositionVO suggestion = suggestions.get((Long)params[1]);
						if(suggestion != null){
							suggestion.setAddedVotersPresent(true);
						}
					  }
					}
				}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	 }
		
	 public List<PartyPositionVO> getSuggestiveLocationsForAParty(List<PartyPositionVO> partyPositions){
		 
		   /* List<String> ids = new ArrayList<String>();
			ids.add("WOREST");
			ids.add("VERY POOR");
			ids.add("POOR");
			ids.add("OK");
			ids.add("STRONG");
			ids.add("VERY STRONG");
		    int z = 1;
		     for(z = 1;z<ids.size();z++){
			  for(int j = 0;j<z;j++){//2009
					for(int i = ids.size()-z+j;i>=ids.size()-z+j;i--)
					{//2004
						System.out.println(ids.get(j)+","+ids.get(i)+","+z);
					}
				}
			  }
		    z = z-1;
		    System.out.println("-----------------");
		    for(int y = 2;y<=ids.size()-1;y++){
			  for(int j = y-2;j<y;j++){
				  z++;
				  System.out.println(ids.get(y-1)+","+ids.get(j)+","+z);
					for(int i = j,l=y;i>0&&l<ids.size()-1;i--,l++)
					{
						System.out.println(ids.get(l)+","+ids.get(i-1)+","+z);
					}
				}
			  } 
		    */
		 List<PartyPositionVO> returnValues = new ArrayList<PartyPositionVO>(); 
	    try{
		 if(partyPositions.size() == 2){
			 List<PartyPositionVO> latestElec =  partyPositions.get(0).getPartyPositionVOList();
			 List<PartyPositionVO> prevElec =  partyPositions.get(1).getPartyPositionVOList();
			 Collections.reverse(latestElec);
			 Collections.reverse(prevElec);
			 int z = 1;
			 for(z = 1;z<latestElec.size();z++){
				  for(int j = 0;j<z;j++){//2009
					  List<PartyPositionVO> panchayaties = latestElec.get(j).getPartyPositionVOList();
					  if(panchayaties != null && panchayaties.size() >0){	
						for(int i = prevElec.size()-z+j;i>=prevElec.size()-z+j;i--)
						{//2004
							List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
							if(prevPanchayaties != null && prevPanchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues,z);
							}
						}
					  }
					}
				  }
			 z = z-1;
			    for(int y = 2;y<=latestElec.size()-1;y++){
				  for(int j = y-2;j<y;j++){
					  z++;
					  List<PartyPositionVO> latestPanchayaties = latestElec.get(y-1).getPartyPositionVOList();
					  List<PartyPositionVO> prevPanchayaties = prevElec.get(j).getPartyPositionVOList();
					  populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						for(int i = j,l=y;i>0&&l<latestElec.size()-1;i--,l++)
						{   latestPanchayaties = latestElec.get(l).getPartyPositionVOList();
						    prevPanchayaties = prevElec.get(i-1).getPartyPositionVOList();
						    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						}
					}
				  } 
			     Collections.reverse(latestElec);
				 Collections.reverse(prevElec);
		 }
	    }catch(Exception e){
		  
	    }
	    return returnValues;
	 }
	 
	 public void populateMachedValues(List<PartyPositionVO> panchayaties,List<PartyPositionVO> prevPanchayaties,List<PartyPositionVO> returnValues,int priorityOrder){
		if(prevPanchayaties != null && prevPanchayaties.size() > 0){
			 for(PartyPositionVO prev:prevPanchayaties){
				  if(panchayaties != null && panchayaties.size() > 0){ 
					    for(PartyPositionVO current:panchayaties){
						   if(current.getId().longValue() == prev.getId().longValue()){
							   current.setPriorityOrder(priorityOrder);
							  returnValues.add(current);
							}
						}
				  }
			 }
	    }
	 }
	 
	 	
		
		 public List<SelectOptionVO> getUserAssignedVotersCasteDetailsByConstId(Long constituencyId,Long userId){
			 
			 List<SelectOptionVO> casteList = null;
			 List castes;
			 try{
				 if(constituencyId !=null){
					 casteList = new ArrayList<SelectOptionVO>();
					 castes = userVoterDetailsDAO.getUserAssignedVotersCastesByCosntiId(constituencyId,userId);
					 
					 if(castes != null && castes.size() >0){
						 for (Object param : castes) {
							 Object[] caste = (Object[]) param;
							casteList.add(new SelectOptionVO((Long) caste[0],caste[1].toString()));
						}
					 }
				 }
				return casteList;	 
			 }catch(Exception e){
				 e.printStackTrace();
				 return null;
			 } 
		 }
		 /**
		  * This method will get  the election results by panchayat in a constituency for 
		  * present and previous elections
		  * @param Long constituencyId 
		  * @return Map<String ,Map<String,PartyImpactVO>>
		  */
		 public Map<String ,Map<String,PartyImpactVO>> getElectionResultsForSelectedElectionsForAConsttituency(Long constituencyId)
		 {	
			 
			 LOG.debug("Entered into the getElectionResultsForSelectedElectionsForAConsttituency service method");
			 
			Map<String ,Map<String,PartyImpactVO>> resultMap = new LinkedHashMap<String, Map<String,PartyImpactVO>>();

			Set<String> totalParties = new HashSet<String>();
			List<Object[]> panchayatResultsList = new ArrayList<Object[]>();

			 try
			 {	
				 //Here we are considering present and previous assembly elections
				 List<Long> elections = new ArrayList<Long>();
				 elections.add(Long.parseLong(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID));
				 elections.add(Long.parseLong(IConstants.PRESENT_ASSEMBLY_ELECTION_ID));
				 
				 List<Long> tehsilIds = new ArrayList<Long>();			
				 
				List<Object[]> tehsilsList = delimitationConstituencyMandalDAO
						.getMandalIdsByConstituencyId(constituencyId,
								Long.parseLong(IConstants.PRESENT_ELECTION_YEAR));
				
				 for(Object[] obj:tehsilsList)
					 tehsilIds.add((Long)obj[0]);

				 
				  getAllPanchayatWiseVotersDetailsForPartyAndElectionWise(tehsilIds,elections,panchayatResultsList);
				  
				  //panchayatResultsList contains the details in the form of
				  
				  // 0 - votes earned , 1 - panchayat-id , 2 - election-id , 3 - party-id , 4 - party name , 5 - panchayat- name
				 
				 Map<String , Long> votersCountMap = new HashMap<String, Long>();

				 //this method will set all the voter details 
				setAlltheVotersDetaiulsForPresentAndPreviousElections(resultMap,panchayatResultsList,totalParties ,votersCountMap);
				 
				List<String> partiesList = new ArrayList<String>(totalParties);
				Collections.sort(partiesList);
				
				//this method will set all the voters details in percent
				processTheResultsToConvertFromVotesToPercent(resultMap , votersCountMap,partiesList);
				
				//this method will calculate and set the difference in voters percent in present and previous elections
				calculateTheDifferenceBetweenpresentAndPreviousElection(resultMap ,partiesList);
			
				
		 }catch(Exception e)
		 {
			 e.printStackTrace();		 
			 LOG.error("Exception raised getElectionResultsForSelectedElectionsForAConsttituency service method");
			 return resultMap;
		  }
			 return resultMap;
		 }
		 
		 /**
		  * This method will get all parties gained votes details for a constituency by panchayat
		  * @param tehsilIds
		  * @param elections
		  * @param panchayatResultsList
		  */
		 
		public void getAllPanchayatWiseVotersDetailsForPartyAndElectionWise(
				List<Long> tehsilIds, List<Long> elections,
				List<Object[]> panchayatResultsList)	 
		{
			LOG.debug("Entered into getAllPanchayatWiseVotersDetailsForPartyAndElectionWise service method");
			
			try
			{
			
			 for(Long electionId:elections)			 
				 for(Long tehsilId:tehsilIds)
				 {
			 
				    List<Object[]> panchayatBoothsDetails = hamletBoothElectionDAO.getPanchayatWiseBoothDetails(tehsilId , electionId);
				 			 
				    Map<Long , List<Long>> map = new HashMap<Long, List<Long>>();//contains panchayatId as key and booths in that panchayat as value
				 
					 for(Object[] obj:panchayatBoothsDetails)
					 {
						 List<Long> booths = null;
						 if(map.get((Long)obj[2]) != null)
						 {
							 booths = map.get((Long)obj[2]);
							 booths.add((Long)obj[6]);
							 
						 }else
						 {
							 booths = new ArrayList<Long>();
							 booths.add((Long)obj[6]);
							 map.put((Long)obj[2], booths);						 
						 }				 
					 }
					 
					 for(Entry<Long,List<Long>> entryForBooths:map.entrySet())
					 {
						List<Object[]> list = candidateBoothResultDAO
								.findBoothResultsForBoothsAndElection(entryForBooths.getValue(),
										electionId);
						
						for(Object[] obj:list)
						{
							Object[] newObj = new Object[6]; 
							newObj[0] = obj[2];                    // no of voters
							newObj[1] = entryForBooths.getKey();   // panchayat id
							newObj[2] = electionId;                //election id
							newObj[3] = obj[0];                    //party id  
							newObj[4] = obj[1];                    //party name
							
							newObj[5] = panchayatDAO.getPanchayatNameById(entryForBooths.getKey()); // panchayat name
							
							panchayatResultsList.add(newObj);
							
						}
						
					 }
				 }
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.debug("Exception raised in  getAllPanchayatWiseVotersDetailsForPartyAndElectionWise service method");

			}
		 }
		
		/**
		 * This method will set all the voter details of panchayitis in a constituency
		 * @param resultMap
		 * @param panchayatResultsList
		 * @param totalParties
		 * @param votersCountMap
		 */
		public void setAlltheVotersDetaiulsForPresentAndPreviousElections(Map<String ,Map<String,PartyImpactVO>> resultMap,List<Object[]> panchayatResultsList,Set<String> totalParties , Map<String , Long> votersCountMap)
		{
			LOG.debug("Entered into the setAlltheVotersDetaiulsForPresentAndPreviousElections service method");
			try
			{
				for(Object[] obj:panchayatResultsList){
					totalParties.add(obj[4].toString());
					
					if(votersCountMap.get(obj[5].toString()+obj[2].toString()) != null)
					{
						Long voters = votersCountMap.get(obj[5].toString()+obj[2].toString()) ;
						votersCountMap.put(obj[5].toString()+obj[2].toString(), voters + (Long)obj[0]);
						
					}else
						votersCountMap.put(obj[5].toString()+obj[2].toString() , (Long)obj[0]);
			
					Map<String,PartyImpactVO> map = null;
					if(resultMap.get(obj[5].toString()) != null)
					{
						
						map = resultMap.get(obj[5].toString());
		                  
						if(map.get(obj[4].toString()) != null)
						{
							PartyImpactVO vo = map.get(obj[4].toString());
							
							if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))						
									vo.setPreviousElectionVotesPercent(obj[0].toString());							
							else 
								vo.setPresentElectionVotesPercent(obj[0].toString());
							
						}else
						{
							PartyImpactVO vo = new PartyImpactVO();
							vo.setPartyName(obj[4].toString());
							
							if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))
								vo.setPreviousElectionVotesPercent(obj[0].toString());
							else 
								vo.setPresentElectionVotesPercent(obj[0].toString());
							
							map.put(obj[4].toString(), vo);
						}
						
					}else
					{
						map = new HashMap<String, PartyImpactVO>();
						
						PartyImpactVO vo = new PartyImpactVO();
						vo.setPartyName(obj[4].toString());
						
						if(obj[2].toString().equalsIgnoreCase(IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID))
							vo.setPreviousElectionVotesPercent(obj[0].toString());
						else 
							vo.setPresentElectionVotesPercent(obj[0].toString());
						map.put(obj[4].toString(),vo );
						
						resultMap.put(obj[5].toString(), map);					
					}
					
					resultMap.put(obj[5].toString(), map);
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.debug("Exception raised in   setAlltheVotersDetaiulsForPresentAndPreviousElections service method");
				
			}
		}
		/**
		 * This method will set all the details to the as percent from total voters
		 * @param resultMap
		 * @param votersCountMap
		 * @param partiesList
		 */
		public void processTheResultsToConvertFromVotesToPercent(Map<String ,Map<String,PartyImpactVO>> resultMap, Map<String,Long> votersCountMap, List<String> partiesList)
		{
			LOG.debug("Entered into processTheResultsToConvertFromVotesToPercent service method");
			try
			{
				for(Entry<String ,Map<String,PartyImpactVO>> entry:resultMap.entrySet())
				{
					Map<String,PartyImpactVO> map = entry.getValue();
					
					for(Entry<String,PartyImpactVO> party:map.entrySet())
					{
						//if party participated in  previous and present elections
						if(party.getValue().getPreviousElectionVotesPercent() != null && party.getValue().getPresentElectionVotesPercent() != null)
						{		
							 Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID);
							 Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
							
							 Long previousVotes=  Long.parseLong(party.getValue().getPreviousElectionVotesPercent());
							 Float previousVotesInFloat  = previousVotes.floatValue();
							 
							 party.getValue().setPreviousElectionVotesPercent(roundTo2DigitsFloatValue((previousVotesInFloat/votersInPanchayatInFloat)*100));
			
								Long votersInPanchayat1 = votersCountMap.get(entry.getKey()+IConstants.PRESENT_ASSEMBLY_ELECTION_ID);
								Float votersInPanchayatInFloat1 = votersInPanchayat1.floatValue();
							 
							 Long presentVotes=  Long.parseLong(party.getValue().getPresentElectionVotesPercent());
							 Float presentVotesInFloat  = presentVotes.floatValue();
							 
							 party.getValue().setPresentElectionVotesPercent(roundTo2DigitsFloatValue((presentVotesInFloat/votersInPanchayatInFloat1)*100));
							
						}//if party participated in  previous election only
						else if(party.getValue().getPreviousElectionVotesPercent() != null)
						{
							Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PREVIOUS_ASSEMBLY_ELECTION_ID);
							Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
			
							 Long previousVotes=  Long.parseLong(party.getValue().getPreviousElectionVotesPercent());
							 Float previousVotesInFloat  = previousVotes.floatValue();
							 
							 party.getValue().setPreviousElectionVotesPercent(roundTo2DigitsFloatValue((previousVotesInFloat/votersInPanchayatInFloat)*100));
							
						}//if party participated in  present election only
						else if(party.getValue().getPresentElectionVotesPercent() != null)
						{
							Long votersInPanchayat = votersCountMap.get(entry.getKey()+IConstants.PRESENT_ASSEMBLY_ELECTION_ID);
							Float votersInPanchayatInFloat = votersInPanchayat.floatValue();
							 
							 Long presentVotes=  Long.parseLong(party.getValue().getPresentElectionVotesPercent());
							 Float presentVotesInFloat  = presentVotes.floatValue();
							 
							 party.getValue().setPresentElectionVotesPercent(roundTo2DigitsFloatValue((presentVotesInFloat/votersInPanchayatInFloat)*100));
							
						}
							
						party.getValue().setConsiderableParties(partiesList);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception raised in processTheResultsToConvertFromVotesToPercent service method");
			}
		}
		
		/**
		 * This method will calculate and set the difference in voters percent
		 * between present and previous elections
		 * @param resultMap
		 * @param partiesList
		 */
		public void calculateTheDifferenceBetweenpresentAndPreviousElection(Map<String ,Map<String,PartyImpactVO>> resultMap, List<String> partiesList)
		{
			LOG.debug("Entered into the calculateTheDifferenceBetweenpresentAndPreviousElection service method");
			try
			{
				for(Entry<String ,Map<String,PartyImpactVO>> entry:resultMap.entrySet())
				{
					
					Map<String,PartyImpactVO> map = entry.getValue();
					
					for(Entry<String,PartyImpactVO> party:map.entrySet()){
						
						//if party participated in  present election only
						if(party.getValue().getPreviousElectionVotesPercent() == null && party.getValue().getPresentElectionVotesPercent() != null)
						{						
							party.getValue().setPreviousElectionVotesPercent("--");
							party.getValue().setDifference(party.getValue().getPresentElectionVotesPercent());
							
						}//if party participated in  previous election only
						else if(party.getValue().getPreviousElectionVotesPercent() != null && party.getValue().getPresentElectionVotesPercent() == null)
						{
							party.getValue().setPresentElectionVotesPercent("--");
							party.getValue().setDifference("--");
						}//if party participated in  previous and present elections
						else{
							Float diff = Float.parseFloat(party.getValue().getPresentElectionVotesPercent()) - Float.parseFloat(party.getValue().getPreviousElectionVotesPercent());
							party.getValue().setDifference(diff.toString());
						}
							
						party.getValue().setConsiderableParties(partiesList);
					}
						
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception raised in  calculateTheDifferenceBetweenpresentAndPreviousElection service method");

				
			}
		}
		 
		 public String roundTo2DigitsFloatValue(Float number){
				
				NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
				f.setMaximumFractionDigits(2);  
				f.setMinimumFractionDigits(2);
				
				return f.format(number);
				
			}
		 
		//All ELection Years
			
			public List<PartyPositionVO> getPartyPerformenceReport1(Long constituencyId,Long partyId,List<Long> electionIds,String tempVar)
			{
				List<PartyPositionVO> resultList = null;
				try{
				List<Long> constituencyIdsList = new ArrayList<Long>(0);
				constituencyIdsList.add(constituencyId);
				List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localbodyIds = new ArrayList<Long>();
				List<Object[]> electionList = null;
				String tempLocationName = "";
				List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
				if(mandalsList != null && mandalsList.size() > 0)
				for(SelectOptionVO vo : mandalsList)
				if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2"))
				mandalIds.add(new Long(vo.getId().toString().substring(1)));
				else
					localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));
				if(electionIds != null && electionIds.size()> 0)
				{
					electionList = new ArrayList<Object[]>();
					for (Long eleId : electionIds) {
						Object[] eleIds = {eleId};
						 electionList.add(eleIds);
					}
				}
				
				Constituency constituency = constituencyDAO.get(constituencyId);
				
				if(electionList != null && electionList.size() > 0)
				{
				   for(Object[] params:electionList)
				   {
					   String electionType = electionDAO.get((Long)params[0]).getElectionScope().getElectionType().getElectionType();
					  if(electionType.equalsIgnoreCase("Assembly"))
					  {
						if(!assemblyEleIdsList.contains((Long)params[0]))
						assemblyEleIdsList.add((Long)params[0]);
					  }
				   } 	
				}
				
				if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
				{
					resultList = new ArrayList<PartyPositionVO>(0);
					  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
						  
					  PartyPositionVO partyPositionVO = null;
					  for(Long eleId :assemblyEleIdsList)
					  {
						Election election = electionDAO.get(eleId);
						partyPositionVO = new PartyPositionVO();
						List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
						List<PartyPositionVO> rangeList1 = new ArrayList<PartyPositionVO>(0);
						
						PartyPositionVO range = null;
						PartyPositionVO newRange = null;
						for(SuggestiveRange suggestiveRange:suggestiveRangeList)
						  {
							range = new PartyPositionVO();
							range.setName(suggestiveRange.getName());
							range.setMinValue(suggestiveRange.getMinValue());
							range.setMaxValue(suggestiveRange.getMaxValue());
							newRange = new PartyPositionVO();
							newRange.setName(suggestiveRange.getName());
							newRange.setMinValue(suggestiveRange.getMinValue());
							newRange.setMaxValue(suggestiveRange.getMaxValue());
							rangeList.add(range);
							rangeList1.add(newRange);
							
						  }
						
						partyPositionVO.setPartyPositionVOList(rangeList);
						
						if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN) || constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
						{
						 if(localbodyIds != null && localbodyIds.size() > 0)
						 {
						 List<PartyPositionVO> localelectionList = new ArrayList<PartyPositionVO>(0);
						 
						 for(Long localBody : localbodyIds)
						 {
							 String name =localElectionBodyDAO.getLocalElectionBodyName(localBody);
								if(name != null)
								{
							 PartyPositionVO localElection = new PartyPositionVO();
							 localElection.setId(localBody);
							 localElection.setName(name);
							 localElection.setBoothwisePartyPositionVOList(rangeList1);
							 localelectionList.add(localElection);
								}
						 }
						 List<Object[]> boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
						 if(boothIdsList != null && boothIdsList.size() > 0)
						 partyPositionVO.setBoothwisePartyPositionVOList(localelectionList);
						 }
						}
						partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
						partyPositionVO.setId(eleId);
						partyPositionVO.setConstituencyId(constituencyId);
						
						if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
						{
							List<Long> panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
							if(panchayatIds != null && panchayatIds.size() > 0)
								getMandalWisePartyPerformanceReport1(constituencyId,eleId, partyPositionVO, partyId,panchayatIds);	
							
						}
						else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
						{
							
							List<Object[]> boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
							
							if(boothIdsList != null && boothIdsList.size() > 0)
								getPanchayatWisePartyPerformance1(constituencyId,eleId, partyPositionVO, partyId,boothIdsList);
							
						}
						else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
						{
							
							List<Long> panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdAndMandalIdsList(mandalIds,eleId);
							if(panchayatIds != null && panchayatIds.size() > 0)
								getMandalWisePartyPerformanceReport1(constituencyId,eleId, partyPositionVO, partyId,panchayatIds);
							
							List<Object[]> boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
							
							if(boothIdsList != null && boothIdsList.size() > 0)
								getPanchayatWisePartyPerformance1(constituencyId,eleId, partyPositionVO, partyId,boothIdsList);
							
						}
						resultList.add(partyPositionVO);
					  }
				}
				
				if(resultList != null && resultList.size() == 2){
				  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
				  resultList.get(0).setSuggestedLocations(suggestedLocations);
				}	

				//For PollingPercentage Panchayats
				if(resultList != null && resultList.size() > 0){
					getPollingPercentageForALocation(resultList.get(0),tempLocationName,constituencyId);
					List<PartyPositionVO>  panchayatVos = getMoreVotersAddedLocDetailsWherePartyIsPoor(resultList.get(0).getPartyPositionVOList());
					resultList.get(0).setAddedVoterDetails(panchayatVos);
				}
				
				//Percentage
				Map<Long,Map<String,Long>> map = new HashMap<Long, Map<String,Long>>(0);//<electionId,Map<strong,totalValidVotes>>
				for(PartyPositionVO partyPositionVO1:resultList)
				{
				   Map<String,Long> map2 = map.get(partyPositionVO1.getId());
				   if(map2 == null)
				   {
					  map2 = new HashMap<String, Long>(0);
					  map.put(partyPositionVO1.getId(), map2);
				   }
				   if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
				   {
					 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
					 {
						 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
						 {
							for(PartyPositionVO partyPositionVO3:partyPositionVO2.getPartyPositionVOList())
							{
							  Long total = map2.get(partyPositionVO2.getName());
							   if(total == null)
								 map2.put(partyPositionVO2.getName(), partyPositionVO3.getTotalValidVotes());
							   else
								map2.put(partyPositionVO2.getName(), total+partyPositionVO3.getTotalValidVotes());
							}
						 }
					 }
				   }
				}
				
				for(PartyPositionVO partyPositionVO1:resultList)
				{
				  Long totalVotes = candidateBoothResultDAO.getConstituencyTotalVotes(constituencyId, partyPositionVO1.getId());
				  
				  Map<String, Long> totalVotesMap = map.get(partyPositionVO1.getId());
				  
				  if(partyPositionVO1.getPartyPositionVOList() != null && partyPositionVO1.getPartyPositionVOList().size() > 0)
				   {
					 for(PartyPositionVO partyPositionVO2:partyPositionVO1.getPartyPositionVOList())
					 {
						 if(partyPositionVO2.getPartyPositionVOList() != null && partyPositionVO2.getPartyPositionVOList().size() > 0)
						 {
							  double percentage =  new BigDecimal((totalVotesMap.get(partyPositionVO2.getName())*100.0/totalVotes)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
							  partyPositionVO2.setRangePercentage(percentage);
						 }
					 }
				   }
				}

				 return resultList;
				}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformenceReportForAllElections() method, Exception-"+e);
				 return resultList;
				}
			}
			
			public void getPanchayatWisePartyPerformance1(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO, Long partyId,List<Object[]> boothIdsList)
			{
				try{
					Map<String,Map<Long,Map<Long,Long>>> resultMap = new HashMap<String, Map<Long,Map<Long,Long>>>(0);//<localbodyName,<boothId,<partyId,votesEarned>>
					Map<String,List<Long>> boothIdMap = new HashMap<String, List<Long>>(0);//<localBodyName,<boothIds>
					
					for(Object[] params: boothIdsList)
					{
							List<Long> boothIds = boothIdMap.get(params[1].toString());
							if(boothIds == null)
							{
							boothIds = new ArrayList<Long>(0);	
							boothIdMap.put(params[1].toString(), boothIds);
							}
							if(!boothIds.contains((Long)params[0]))
							boothIds.add((Long)params[0]);
							
					}
						for(String localbodyName : boothIdMap.keySet())	
						{
						 List<Object[]> list = candidateBoothResultDAO.getVotesEarnedByBoothIdsList(constituencyId, electionId, boothIdMap.get(localbodyName));
						  if(list != null && list.size() > 0)
						  {
							    Map<Long,Map<Long,Long>> boothMap = resultMap.get(localbodyName.toString());	
								 if(boothMap == null)
								 {
									 boothMap = new HashMap<Long, Map<Long,Long>>(0); 
									resultMap.put(localbodyName, boothMap);
									
								 }
								 for(Object[] params:list)
								  {
								Map<Long,Long> partyMap = boothMap.get((Long)params[0]);
								 if(partyMap == null)
								 {
									 partyMap = new HashMap<Long, Long>(0); 
									  boothMap.put((Long)params[0], partyMap);
								  }
								Long votesEarned = partyMap.get(params[1]);
								if(votesEarned == null)
									  partyMap.put((Long)params[1], (Long)params[2]);
								 else
									  partyMap.put((Long)params[1], (Long)params[2]+votesEarned);
								  }
								 
							 
						 }
						}
						
						if(resultMap != null && resultMap.size() > 0)
							//getPartyPerformanceForBooth(partyPositionVO, partyId,boothMap);	
							getPartyPerformanceForLocalBodyBooth(partyPositionVO, partyId,resultMap);	
				
			
				}catch (Exception e) {
					e.printStackTrace();
					LOG.error("ExceptionOccured in getPanchayatWisePartyPerformance() method, Exception - "+e);
				}
			}
			public PartyPositionVO checkPartyPositionVOExists(String name,List<PartyPositionVO> localbodyList)
			{
				try{
					if(localbodyList == null || localbodyList.size() == 0)
						return null;
					for(PartyPositionVO vo : localbodyList)
						if(vo.getName().equalsIgnoreCase(name))
							return vo;
					return null;
				}
				catch(Exception e)
				{
					return null;	
				}
			}
			public void getPartyPerformanceForLocalBodyBooth(PartyPositionVO partyPositionVO, Long selectedpartyId,Map<String,Map<Long,Map<Long,Long>>> resultMap)
			{
				try{
					for(String localbodyName : resultMap.keySet())
					{
				 List<PartyPositionVO> localbodyList = partyPositionVO.getBoothwisePartyPositionVOList();
				 partyPositionVO = checkPartyPositionVOExists(localbodyName,partyPositionVO.getBoothwisePartyPositionVOList());
				 for(PartyPositionVO localbodyVo : localbodyList)
				 {
				 Map<Long,Map<Long,Long>> boothMap = resultMap.get(localbodyName);
				 for(Long id:boothMap.keySet())
				 {
					Map<Long,Long> partyMap = boothMap.get(id);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap.keySet())
					  totalVotes += partyMap.get(partysId); 
						 
					Long selectedPartyTotal = partyMap.get(selectedpartyId);
					Long comparePartyTotal = 0L;
						 
					  for(Long partysId:partyMap.keySet())
					  {
					    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
						 comparePartyTotal = partyMap.get(partysId);
						  
					  }
				   
				   
				  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			      double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			    	
			      String locationName = "";
			      locationName = boothDAO.getBoothPartNoByBoothId(id);
			  
			    	  List<PartyPositionVO> boothPositionVOList = partyPositionVO.getBoothwisePartyPositionVOList();
			    	  for(PartyPositionVO positionVO :boothPositionVOList)
				      {
				    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
				    	{
				    	 PartyPositionVO locationVO = null;
				    	
				    	 List<PartyPositionVO> boothwiselocationList = positionVO.getPartyPositionVOList(); 
				    	
				    	 if(boothwiselocationList == null || boothwiselocationList.size() == 0)
				    		 boothwiselocationList = new ArrayList<PartyPositionVO>(0);
				    	 
				    	 locationVO = checkPartyPositionVOExist(id,boothwiselocationList);
				    	 if(locationVO == null)
				    	 {
				    		 locationVO = new PartyPositionVO();
				    		 locationVO.setId(id);
				    		 locationVO.setName(locationName != null?locationName:" ");
				    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
				    		 locationVO.setTotalValidVotes(totalVotes);
				    		  boothwiselocationList.add(locationVO);
				    		 positionVO.setPartyPositionVOList(boothwiselocationList);
				    		
				    	 }
				    	 
				    	}
				    	
				      }
				 }
			      }
				
				 
					}
			     }catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
				}
			}
			
			public void getMandalWisePartyPerformanceReport1(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO,Long partyId,List<Long> panchaytIdsList)
			{
				try{
				Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
				Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
				if(panchaytIdsList != null && panchaytIdsList.size() > 0)
				{
				  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
				  if(list != null && list.size() > 0)
				  {
					  for(Object[] params:list)
					  {
						  List<Long> panchayatIds = boothIds.get((Long)params[1]);
						  if(panchayatIds == null)
						  {
						   panchayatIds = new ArrayList<Long>(0);
						   boothIds.put((Long)params[1], panchayatIds);
						  }
						  if(!panchayatIds.contains((Long)params[0]))
						   panchayatIds.add((Long)params[0]);
					  }
					  
					  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
					  if(resultList != null && resultList.size() > 0)
					  {
						 for(Object[] params:resultList)
						 {
						   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
						   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
						   {
							 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
							 for(Long panchayatId :panchayatIdsList)
							 {
								 partyMap = resultMap.get(panchayatId);
								 if(partyMap == null)
								 {
									 partyMap = new HashMap<Long, Long>(0);
									 resultMap.put(panchayatId, partyMap);
								 }
								 Long votesEarned = partyMap.get((Long)params[1]);
								 if(votesEarned == null)
								  partyMap.put((Long)params[1],(Long)params[2]);
								 else
								  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
							 }
						   }
						   
						 }
					  }
					  
				  }
				}
				if(resultMap != null && resultMap.size() > 0)
					getPartyPerformanceForPanchayat1(resultMap,partyPositionVO, partyId); 
				
				
				}catch (Exception e) {
					e.printStackTrace();
					LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - "+e);
				  }
			}
			public void getPartyPerformanceForPanchayat1(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId)
			{
				try{
					
					//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
					
					Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
					Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
					
					
						List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
					  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
					  if(boothList != null && boothList.size() > 0)
					  {
						  for(Object[] params:boothList)
						  {
							  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
							  if(boothIdsList == null)
							  {
								boothIdsList = new ArrayList<Long>(0);
								boothIdsMap.put((Long)params[0], boothIdsList);  
							  }
							  if(!boothIdsList.contains((Long)params[1]))
								  boothIdsList.add((Long)params[1]);  
						  }
						  
					  }
					
					
					
				if(boothIdsMap != null && boothIdsMap.size() > 0)
				{
				  for(Long id : boothIdsMap.keySet())
					panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
				}
					
				 for(Long id:resultMap.keySet())
				 {
					Map<Long,Long> partyMap = resultMap.get(id);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap.keySet())
					  totalVotes += partyMap.get(partysId); 
						 
					Long selectedPartyTotal = partyMap.get(selectedpartyId);
					Long comparePartyTotal = 0L;
						 
					  for(Long partysId:partyMap.keySet())
					  {
					    if(!partysId.equals(selectedpartyId) && comparePartyTotal < partyMap.get(partysId))
						 comparePartyTotal = partyMap.get(partysId);
						  
					  }
				   
					     
				  double selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      double comparePartyTotalPercent =  new BigDecimal((comparePartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			      double difference = new BigDecimal(selectedPartyTotalPercent - comparePartyTotalPercent).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			      
			    	
			      String locationName = "";
			      
			    	 locationName = panchayatDAO.getPanchayatNameById(id); 
			     /* else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			    	 locationName = boothDAO.getBoothPartNoByBoothId(id);*/
			      
			      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			      for(PartyPositionVO positionVO :partyPositionVOList)
			      {
			    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
			    	{
			    	 PartyPositionVO locationVO = null;
			    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
			    	 if(locationList == null || locationList.size() == 0)
			    		locationList = new ArrayList<PartyPositionVO>(0);
			    	 
			    	 locationVO = checkPartyPositionVOExist(id,locationList);
			    	 if(locationVO == null)
			    	 {
			    		 locationVO = new PartyPositionVO();
			    		 locationVO.setId(id);
			    		 locationVO.setName(locationName != null?locationName:" ");
			    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
			    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
			    		 locationVO.setTotalValidVotes(totalVotes);
			    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
			    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		 locationVO.setMargin(difference);
			    		 
			    		 locationList.add(locationVO);
			    		 positionVO.setPartyPositionVOList(locationList);
			    		 
			    	 }
			    	 
			    	}
			      }
			    	
				}
				}catch (Exception e) {
				 e.printStackTrace();
				 LOG.error(" Exception Occured in getPartyPerformanceForSelectedlocation() method, Exception - "+e);
				}
			}
					
}
