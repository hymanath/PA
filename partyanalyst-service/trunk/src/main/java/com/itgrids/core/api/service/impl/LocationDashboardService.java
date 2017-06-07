package com.itgrids.core.api.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.CandidateInfoForConstituencyVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationDashboardService implements ILocationDashboardService{
	private final static Logger LOG =  Logger.getLogger(LocationDashboardService.class);
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private IVoterCastInfoDAO voterCastInfoDAO; 
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	
	
	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}
	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}
	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}
	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
	@SuppressWarnings("unchecked")
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId)
	{	
		String electionType = "";
		String deformDate = "";
		String isnew = "false";
		CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituencyTypesVO = new CandidateDetailsForConstituencyTypesVO ();
		
		/**
		 * DAO method call to get the election type and delimitation info.If delimitation info is null returning null.
		 */
		List constituencyTypeDetails = constituencyDAO.getConstituencyTypeAndDelimitationInfoByConstituencyId(constituencyId);
		if(constituencyTypeDetails != null && constituencyTypeDetails.size()>0)
		{
			Object[] obj = (Object[])constituencyTypeDetails.get(0);
			electionType = (String)obj[0];
			if(obj[1]!=null)
			 deformDate = obj[1].toString();
		}
		LOG.info(" Election Type :" + electionType);
		LOG.info(" Deform Date :" + deformDate);
		if(!deformDate.equalsIgnoreCase("") || deformDate == null)
			return null;
		
		//---------------
		List candidateList = null;
		if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
		    candidateList = nominationDAO.getCandidateAndPartyInfoForParliament(constituencyId,electionType, 1L);
		  List<String> yearList = nominationDAO.getCandidateAndPartyInfoForParliamentYear(constituencyId,electionType);
		  if(yearList.size() > 0 && candidateList.size()>0)
		  {
				  Object[] values = (Object[]) candidateList.get(0);
				  if(!(yearList.get(0).equalsIgnoreCase(values[11].toString()))){
					  isnew = "true";
				  }
		  }
		}else{
			/*Long stateID = consti.getElectionScope().getState().getStateId();
			candidateList = nominationDAO.getCandidateNPartyInfo(constituencyId.toString(), electionType, 1L, IConstants.ELECTION_SUBTYPE_MAIN, stateID);*/
			candidateList = nominationDAO.getCandidateAndPartyInfo(constituencyId, electionType,1L);
			List<String> yearList = nominationDAO.getCandidateAndPartyInfoYear(constituencyId,electionType);
			if(yearList.size() > 0 && candidateList.size()>0)
			  {
					  Object[] values = (Object[]) candidateList.get(0);
					  if(!(yearList.get(0).equalsIgnoreCase(values[11].toString()))){
						  isnew = "true";
					  }
			  }
		}
		
		
		LOG.info("Nomination List :" + candidateList.size());
		if(candidateList.size() == 0)
			return null;
		List<CandidateInfoForConstituencyVO> candidateInfoList = extractCandidateNPartyDataFromList(candidateList);
		LOG.info("Candidate Info :" + candidateInfoList.size());
		 
		if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(candidateInfoList);
				
			List list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
			if(list != null && list.size() > 0){
			Object[] listData = (Object[]) list.get(0);
			Long asemblyId = (Long) listData[0];
			List result = nominationDAO.getParliamentCandidateNPartyInfo(asemblyId, IConstants.PARLIAMENT_ELECTION_TYPE, 1L);
				if(result.size()!=0){
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(extractCandidateNPartyDataFromList(result).get(0));
				}else{
					LOG.error("Assembly candidate data for this constituency is not present");
					candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
				}			
			}else{
				candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(null);
			}
			
		}
		else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			candidateDetailsForConstituencyTypesVO.setParliamentCandidateInfo(candidateInfoList.get(0));
			
			List assembliesData = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(constituencyId);
			/*Constituency assemConsti = null;*/
			
			if(assembliesData != null && assembliesData.size() > 0)
			{
				/*StringBuilder idString = new StringBuilder();
				for(int j = 0 ; j < assembliesData.size() ; j++)
				{
					Object[] ids = (Object[]) assembliesData.get(j);
					idString.append(IConstants.COMMA).append((Long)ids[0]);
					
					if(j == 0)
						assemConsti = constituencyDAO.get((Long)ids[0]);				
				}	
				
				if(idString.length() > 0)
				{
					Long stateId = 0L;
					if(assemConsti != null)
					stateId = assemConsti.getElectionScope().getState().getStateId();
					List result = nominationDAO.getCandidateNPartyInfo(idString.substring(1), IConstants.ASSEMBLY_ELECTION_TYPE, 1L, IConstants.ELECTION_SUBTYPE_MAIN,stateId);*/
					
					List<Object[]> result = new ArrayList<Object[]>(0);
					for(int j = 0 ; j < assembliesData.size() ; j++)
					{
						try{
						 Long constId = (Long)((Object[])assembliesData.get(j))[0];
						 result.add(nominationDAO.getCandidateAndPartyInfo(constId,IConstants.ASSEMBLY_ELECTION_TYPE, 1L).get(0));
						}catch(Exception e){
							//e.printStackTrace();
						}
					}
					if(result.size()!=0){
						candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(extractCandidateNPartyDataFromList(result));
					}else{
						LOG.error("Parliament candidate data for this constituency is not present");
						candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
					}			    
				/*}*/
			}else{
				candidateDetailsForConstituencyTypesVO.setAssemblyCandidateInfo(null);
			}
		}
		candidateDetailsForConstituencyTypesVO.setIspartial(isnew);
		return candidateDetailsForConstituencyTypesVO;
	}
	public List<CandidateInfoForConstituencyVO> extractCandidateNPartyDataFromList(List candidateList)
	{
		List<CandidateInfoForConstituencyVO> candidateInfoList = new ArrayList<CandidateInfoForConstituencyVO>();
		
		for(int i=0;i<candidateList.size();i++)
		{
			String candidateFullName = "";
			CandidateInfoForConstituencyVO candidateInfo1 = new CandidateInfoForConstituencyVO();
			Object[] values = (Object[]) candidateList.get(i);	
			candidateInfo1.setConstituencyId((Long) values[0]);
			candidateInfo1.setConstituencyName(values[1].toString());
			candidateInfo1.setCandidateId((Long) values[2]);
			
			if(!StringUtils.isBlank((String)values[3]))
				candidateFullName = candidateFullName + ((String)values[3]) + " ";
			if(!StringUtils.isBlank((String)values[4]))
				candidateFullName = candidateFullName + ((String)values[4]) + " ";
			if(!StringUtils.isBlank((String)values[5]))
				candidateFullName = candidateFullName + ((String)values[5]);
			
			candidateInfo1.setCandidateName(candidateFullName);
			candidateInfo1.setPartyId((Long) values[6]);
			candidateInfo1.setParty(values[7].toString());
			candidateInfo1.setConstituencyType(values[9].toString());
			if(values[8] == null || values[8].toString().length() == 0)
				candidateInfo1.setDeformDate("");
			else
				candidateInfo1.setDeformDate(values[9].toString());
			if(values[10] != null){
				candidateInfo1.setPartyFlag(values[10].toString());
			}
			candidateInfo1.setLatestElecYear(values[11].toString());
			candidateInfoList.add(candidateInfo1);
		}
		
		return candidateInfoList;
	}
	
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long constituencyId,Long publicationDateId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<String,LocationVotersVO> map = new LinkedHashMap<String, LocationVotersVO>();
			List<Object[]> votersObjList = voterAgeInfoDAO.getVotersAgeWiseCount(constituencyId,publicationDateId);
			if(votersObjList != null && votersObjList.size() > 0){
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId((Long)objects[0]);
					vo.setAgeRange(objects[1].toString());
					vo.setTotalVoters(objects[2] != null ? (Long)objects[2]:0l);
					vo.setTotalVotersPerc(objects[3] != null ? objects[3].toString()+" %":"");
					vo.setMaleVoters(objects[4] != null?(Long)objects[4]:0l);
					vo.setMaleVotersPerc(objects[5] != null ? objects[5].toString()+" %":"");
					vo.setFemaleVoters(objects[6] != null?(Long)objects[6]:0l);
					vo.setFemaleVotersPerc(objects[7] != null?objects[7].toString()+" %":"");
					map.put(objects[1].toString(), vo);
				}
			}
			
			List<Object[]> cadreObjList =  tdpCadreEnrollmentYearDAO.getGenderAndAgeGroupWiseCadreCount(constituencyId);
			if(cadreObjList != null && cadreObjList.size() > 0){
				for (Object[] objects : cadreObjList) {
					if(map.get(objects[1].toString()) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(objects[1].toString(), inVO);
					}
					
					if(objects[2].toString().trim().equalsIgnoreCase("M")){
						map.get(objects[1].toString()).setMaleCadres((Long)objects[3]);
					}else if(objects[2].toString().trim().equalsIgnoreCase("F")){
						map.get(objects[1].toString()).setFemaleCadres((Long)objects[3]);
					}
				}
			}
			
			if(map != null && map.size() > 0){
				LocationVotersVO voForTotalCounts = new LocationVotersVO();
				Long totalCadres=0l,maleTotalCadres=0l,femaleTotalCadres=0l;
				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalCadres(entry.getValue().getMaleCadres()+entry.getValue().getFemaleCadres());
					totalCadres = totalCadres+entry.getValue().getMaleCadres()+entry.getValue().getFemaleCadres();
					maleTotalCadres = maleTotalCadres+entry.getValue().getMaleCadres();
					femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
				}
				
				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					if(totalCadres > 0l)
						entry.getValue().setTotalCadrePerc(((entry.getValue().getTotalCadres()*100)/totalCadres)+"");
					if(maleTotalCadres > 0l)
						entry.getValue().setMaleCadrePerc(((entry.getValue().getMaleCadres()*100)/maleTotalCadres)+"");
					if(femaleTotalCadres > 0l)
						entry.getValue().setFemaleCadrePerc(((entry.getValue().getFemaleCadres()*100)/femaleTotalCadres)+"");
					
					voForTotalCounts.setTotalVoters(voForTotalCounts.getTotalVoters()+entry.getValue().getTotalVoters());
					voForTotalCounts.setTotalCadres(voForTotalCounts.getTotalCadres()+entry.getValue().getTotalCadres());
					voForTotalCounts.setMaleVoters(voForTotalCounts.getMaleVoters()+entry.getValue().getMaleVoters());
					voForTotalCounts.setMaleCadres(voForTotalCounts.getMaleCadres()+entry.getValue().getMaleCadres());
					voForTotalCounts.setFemaleVoters(voForTotalCounts.getFemaleVoters()+entry.getValue().getFemaleVoters());
					voForTotalCounts.setFemaleCadres(voForTotalCounts.getFemaleCadres()+entry.getValue().getFemaleCadres());
				}
				
				voList.addAll(map.values());
				voList.add(voList.size(),voForTotalCounts);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		return voList;
	}
	
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(String type,Long constituencyId,Long publicationDateId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			if(type.equalsIgnoreCase("voter")){
				voList = getVotersCasteWiseCount(constituencyId,publicationDateId);
			}else if(type.equalsIgnoreCase("cadre")){
				voList = getCadreCasteWiseCount(constituencyId);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersAndCadreCasteWiseCount", e);
		}
		return voList;
	}
	
	public List<LocationVotersVO> getVotersCasteWiseCount(Long constituencyId,Long publicationId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			//0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId,publicationId);
			
			if(votersObjList != null && votersObjList.size() > 0){
				Map<String,LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String,LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				for (Object[] objects : votersObjList) {
					if(casteGroupMap.get(objects[1].toString()) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(),inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters()+(Long)objects[4]);
					matchedVO.setTotalVotersPerc(matchedVO.getTotalVotersPerc() != ""?(Float.parseFloat(matchedVO.getTotalVotersPerc())+Float.parseFloat(objects[5].toString()))+"":objects[5].toString());
					
					if(casteMap.get(objects[3].toString()) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(),inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters()+(Long)objects[4]);
					matchedVO1.setTotalVotersPerc(matchedVO1.getTotalVotersPerc()!=""?(Float.parseFloat(matchedVO1.getTotalVotersPerc())+Float.parseFloat(objects[5].toString()))+"":objects[5].toString());
					
				}
				voList.addAll(casteGroupMap.values());
				voList.addAll(casteMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getVotersCasteWiseCount", e);
		}
		return voList;
	}
	
	public List<LocationVotersVO> getCadreCasteWiseCount(Long constituencyId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			//0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-cadreCount
			List<Object[]> objList = tdpCadreEnrollmentYearDAO.getCasteWiseCadreCounts(constituencyId);
			
			if(objList != null && objList.size() > 0){
				Map<String,LocationVotersVO> casteGroupMap = new LinkedHashMap<String, LocationVotersVO>();
				Map<String,LocationVotersVO> casteMap = new LinkedHashMap<String, LocationVotersVO>();
				Long totalCadreCount = 0l;
				for (Object[] objects : objList) {
					totalCadreCount = totalCadreCount+(Long)objects[4];
					if(casteGroupMap.get(objects[1].toString()) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[0]);
						inVO.setAgeRange(objects[1].toString());
						inVO.setMaleVotersPerc("casteGroup");
						casteGroupMap.put(objects[1].toString(),inVO);
					}
					LocationVotersVO matchedVO = casteGroupMap.get(objects[1].toString());
					matchedVO.setTotalVoters(matchedVO.getTotalVoters()+(Long)objects[4]);
					
					if(casteMap.get(objects[3].toString()) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[2]);
						inVO.setAgeRange(objects[3].toString());
						inVO.setMaleCadrePerc("caste");
						casteMap.put(objects[3].toString(),inVO);
					}
					LocationVotersVO matchedVO1 = casteMap.get(objects[3].toString());
					matchedVO1.setTotalVoters(matchedVO.getTotalVoters()+(Long)objects[4]);
					
				}
				
				if(casteGroupMap != null && casteGroupMap.size() > 0 && totalCadreCount > 0){
					for (Entry<String, LocationVotersVO> entry : casteGroupMap.entrySet()) {
						entry.getValue().setTotalVotersPerc(((entry.getValue().getTotalVoters()*100)/totalCadreCount)+"");
					}
					voList.addAll(casteGroupMap.values());
				}
				
				if(casteMap != null && casteMap.size() > 0 && totalCadreCount > 0){
					for (Entry<String, LocationVotersVO> entry : casteMap.entrySet()) {
						entry.getValue().setTotalVotersPerc(((entry.getValue().getTotalVoters()*100)/totalCadreCount)+"");
					}
					voList.addAll(casteMap.values());
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreCasteWiseCount", e);
		}
		return voList;
	}
	
	public List<LocationVotersVO> getCasteGroupNAgeWiseVoterNCadreCounts(Long constituencyId,Long publicationDateId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			//0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyId,publicationDateId);
			
			if(votersObjList != null && votersObjList.size() > 0){
				for (Object[] objects : votersObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList,(Long)objects[0]);
					if(matchedCGVO == null){
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long)objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());
						
						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long)objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long)objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long)objects[6]);
						casteVO.setFemaleVoters((Long)objects[7]);
						
						matchedCGVO.getLocationVotersVOList().add(casteVO);
						
						voList.add(matchedCGVO);
					}else{
						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long)objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						casteVO.setTotalVoters((Long)objects[4]);
						casteVO.setTotalVotersPerc(objects[5].toString());
						casteVO.setMaleVoters((Long)objects[6]);
						casteVO.setFemaleVoters((Long)objects[7]);
						
						matchedCGVO.getLocationVotersVOList().add(casteVO);
					}
				}
			}
			
			//0-casteCategoryId,1-casteCategory,2-casteId,3-caste,4-gender,5-cadreCount
			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCasteNGenderWiseCadreCounts(constituencyId);
			if(cadresObjList != null && cadresObjList.size() > 0){
				for (Object[] objects : cadresObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(voList,(Long)objects[0]);
					if(matchedCGVO == null){
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId((Long)objects[0]);
						matchedCGVO.setAgeRange(objects[1].toString());
						
						LocationVotersVO casteVO = new LocationVotersVO();
						casteVO.setAgeRangeId((Long)objects[2]);
						casteVO.setAgeRange(objects[3].toString());
						if(objects[4].toString().equalsIgnoreCase("M")){
							casteVO.setMaleCadres((Long)objects[5]);
						}else if(objects[4].toString().equalsIgnoreCase("F")){
							casteVO.setFemaleCadres((Long)objects[5]);
						}
						
						matchedCGVO.getLocationVotersVOList().add(casteVO);
						
						voList.add(matchedCGVO);
					}else{
						LocationVotersVO matchedCVO = getMatchedVO(matchedCGVO.getLocationVotersVOList(),(Long)objects[2]);
						if(matchedCVO == null){
							matchedCVO = new LocationVotersVO();
							matchedCVO.setAgeRangeId((Long)objects[2]);
							matchedCVO.setAgeRange(objects[3].toString());
							if(objects[4].toString().equalsIgnoreCase("M")){
								matchedCVO.setMaleCadres((Long)objects[5]);
							}else if(objects[4].toString().equalsIgnoreCase("F")){
								matchedCVO.setFemaleCadres((Long)objects[5]);
							}
							matchedCGVO.getLocationVotersVOList().add(matchedCVO);
						}else{
							if(objects[4].toString().equalsIgnoreCase("M")){
								matchedCVO.setMaleCadres((Long)objects[5]);
							}else if(objects[4].toString().equalsIgnoreCase("F")){
								matchedCVO.setFemaleCadres((Long)objects[5]);
							}
						}
					}
				}
			}
			
			//calculating totals and %'s
			if(voList != null && voList.size() > 0){
				for (LocationVotersVO casteGroupVO : voList) {
					if(casteGroupVO.getLocationVotersVOList() != null && casteGroupVO.getLocationVotersVOList().size() > 0){
						Long maleTotalVoters = 0l,femaleTotalVoters = 0l,totalCadres=0l,maleTotalCadres=0l,femaleTotalCadres=0l;
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							maleTotalVoters = maleTotalVoters + casteVO.getMaleVoters();
							femaleTotalVoters = femaleTotalVoters + casteVO.getFemaleVoters();
							maleTotalCadres = maleTotalCadres + casteVO.getMaleCadres();
							femaleTotalCadres = femaleTotalCadres + casteVO.getFemaleCadres();
							totalCadres = totalCadres+casteVO.getMaleCadres()+casteVO.getFemaleCadres();
						}
						
						for (LocationVotersVO casteVO : casteGroupVO.getLocationVotersVOList()) {
							casteVO.setMaleVotersPerc(((casteVO.getMaleVoters()*100)/maleTotalVoters)+" %");
							casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters()*100)/femaleTotalVoters)+" %");
							casteVO.setTotalCadres(casteVO.getMaleCadres()+casteVO.getFemaleCadres());
							casteVO.setTotalCadrePerc(((casteVO.getTotalCadres()*100)/totalCadres)+" %");
							casteVO.setMaleCadrePerc(((casteVO.getMaleCadres()*100)/maleTotalCadres)+" %");
							casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres()*100)/femaleTotalCadres)+" &%");
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts",e);
		}
		return voList;
	}
	
	public LocationVotersVO getMatchedVO(List<LocationVotersVO> voList,Long id){
		if(voList != null && voList.size() > 0 && id != null && id > 0l){
			for (LocationVotersVO locationVotersVO : voList) {
				if(locationVotersVO.getAgeRangeId().equals(id))
					return locationVotersVO;
			}
		}
		return null;
	}
	
	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long constituencyId,Long publicationDateId,Long casteGroupId,Long casteId){
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Map<Long,LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();
			
			//0-ageRangeId,1-ageRange,2-gender,3-votersCount
			List<Object[]> votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCount(casteGroupId,casteId,constituencyId,publicationDateId);
			
			if(votersObjList != null && votersObjList.size() > 0){
				for (Object[] objects : votersObjList) {
					if(map.get((Long)objects[0]) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long)objects[0], inVO);
					}
					
					if(objects[2].toString().equalsIgnoreCase("M")){
						map.get((Long)objects[0]).setMaleVoters((Long)objects[3]);
					}else if(objects[2].toString().equalsIgnoreCase("F")){
						map.get((Long)objects[0]).setFemaleVoters((Long)objects[3]);
					}
					
				}
			}
			
			List<Object[]> cadresObjList = tdpCadreEnrollmentYearDAO.getCadresCasteNAgeGroupWiseCounts(casteGroupId,casteId,constituencyId);
			
			if(cadresObjList != null && cadresObjList.size() > 0){
				for (Object[] objects : cadresObjList) {
					if(map.get((Long)objects[0]) == null){
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long)objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long)objects[0], inVO);
					}
					
					if(objects[2].toString().equalsIgnoreCase("M")){
						map.get((Long)objects[0]).setMaleCadres((Long)objects[3]);
					}else if(objects[2].toString().equalsIgnoreCase("F")){
						map.get((Long)objects[0]).setFemaleCadres((Long)objects[3]);
					}
				}
			}
			
			if(map != null && map.size() > 0){
				Long totalVoters=0l,totalMaleVoters=0l,totalFemaleVoters=0l,totalCadres=0l,totalMaleCadres=0l,totalFemaleCadres=0l;
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalVoters(entry.getValue().getMaleVoters()+entry.getValue().getFemaleVoters());
					entry.getValue().setTotalCadres(entry.getValue().getMaleCadres()+entry.getValue().getFemaleCadres());
					
					totalVoters = totalVoters + entry.getValue().getMaleVoters()+entry.getValue().getFemaleVoters();
					totalMaleVoters = totalMaleVoters+entry.getValue().getMaleVoters();
					totalFemaleVoters = totalFemaleVoters+entry.getValue().getFemaleVoters();
					totalCadres = totalCadres+entry.getValue().getMaleCadres()+entry.getValue().getFemaleCadres();
					totalMaleCadres = totalMaleCadres+entry.getValue().getMaleCadres();
					totalFemaleCadres = totalFemaleCadres+entry.getValue().getFemaleCadres();
				}
				
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalVotersPerc(((entry.getValue().getTotalVoters()*100.00)/totalVoters)+" %");
					entry.getValue().setMaleVotersPerc(((entry.getValue().getMaleVoters()*100.00)/totalMaleVoters)+" %");
					entry.getValue().setFemaleVotersPerc(((entry.getValue().getFemaleVoters()*100.00)/totalFemaleVoters)+" %");
					entry.getValue().setTotalCadrePerc(((entry.getValue().getTotalCadres()*100.00)/totalCadres)+" %");
					entry.getValue().setMaleCadrePerc(((entry.getValue().getMaleCadres()*100.00)/totalMaleCadres)+" %");
					entry.getValue().setFemaleCadrePerc(((entry.getValue().getFemaleCadres()*100.00)/totalFemaleCadres)+" %");
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}
}