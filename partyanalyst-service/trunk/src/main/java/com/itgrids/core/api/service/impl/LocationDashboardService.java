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
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
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
				}
				
				for (Entry<String, LocationVotersVO> entry : map.entrySet()) {
					voList.add(entry.getValue());
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		return voList;
	}
	
}