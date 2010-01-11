package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyWinnerInfoVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IDistrictPageService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import org.apache.log4j.Logger;

public class DistrictPageService implements IDistrictPageService {
	
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IRegionServiceData regionServiceData;
	private ITehsilDAO tehsilDAO;
	private INominationDAO nominationDAO;
	private IConstituencyDAO constituencyDAO;
	

	private final Logger log = Logger.getLogger(DistrictPageService.class);
	
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

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IRegionServiceData getRegionServiceData() {
		return regionServiceData;
	}

	public void setRegionServiceData(IRegionServiceData regionServiceData) {
		this.regionServiceData = regionServiceData;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	
	
	/*
	 * This method takes District Id as input and displays all the Assembly Candidates
	 * that are present in that District.
	 */	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId){
		log.debug("DistrictPageService.getConstituenciesWinnerInfo()...started");
		List delimitationYear = delimitationConstituencyDAO.getLatestDelimitationYear();

		Long electionYear = new Long(delimitationYear.get(0).toString()) ;

		log.debug("DistrictPageService.getConstituenciesWinnerInfo() delimitationYear:"+electionYear);
		ConstituenciesStatusVO constituenciesStatusVO = getConstituenciesForDistrict(districtId, electionYear);
		List<SelectOptionVO> constituencies = constituenciesStatusVO.getExistConstituencies();
		constituencies.addAll(constituenciesStatusVO.getNewConstituencies());
		List<ConstituencyWinnerInfoVO> constituencyWinnerInfoVOList = new ArrayList<ConstituencyWinnerInfoVO>();
		StringBuilder constituencyIDs = new StringBuilder();
		for(SelectOptionVO constituency : constituencies){
			constituencyIDs.append(",").append(constituency.getId());
		}
		log.debug("DistrictPageService.getConstituenciesWinnerInfo() constituencies:"+constituencyIDs);
		List candidates =  nominationDAO.findCandidateNamePartyByConstituencyAndElection(constituencyIDs.substring(1), electionYear.toString());

		log.debug("DistrictPageService.getConstituenciesWinnerInfo() total candidates:"+candidates.size());
		for(int i = 0; i<candidates.size(); i++){
			ConstituencyWinnerInfoVO constituencyWinnerInfoVO = new ConstituencyWinnerInfoVO();
			Object[] obj = (Object[]) candidates.get(i);
			constituencyWinnerInfoVO.setConstituencyName(obj[0].toString());
			constituencyWinnerInfoVO.setCandidateName(obj[1].toString());
			constituencyWinnerInfoVO.setPartyName(obj[2].toString());
			constituencyWinnerInfoVOList.add(constituencyWinnerInfoVO);
		}
		constituenciesStatusVO.setConstituencyWinnerInfoVO(constituencyWinnerInfoVOList);
		constituenciesStatusVO.setDelimitationYear(electionYear);
		return constituenciesStatusVO;
	}
	
	
	
	/*
	 * This method takes District Id as input and retrives all the constituencies that
	 * are present in that particular district. And it sets the constituencies names and 
	 * corresponding Id's in the Data transfer Object.
	 */	
	
	@SuppressWarnings("unchecked")
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear){
		ConstituenciesStatusVO constituencyVO = new ConstituenciesStatusVO();
	
		List result  = constituencyDAO.findConstituencyByDistrictId(districtId);
		List<SelectOptionVO> deleteList= constituencyVO.getDeletedConstituencies();
		List<SelectOptionVO> existList= constituencyVO.getExistConstituencies();
		List<SelectOptionVO> newList= constituencyVO.getNewConstituencies();

	
		for(int i=0;i<result.size();i++){
			Object[] parms = (Object[]) result.get(i);
			
			SelectOptionVO selectOptionVO= new SelectOptionVO();
			
			if(parms[2]!= null && parms[3] == null && parms[2].toString().equals(electionYear.toString())){
				selectOptionVO.setId(Long.parseLong(parms[0].toString()));
				selectOptionVO.setName(parms[1].toString());
				
				newList.add(selectOptionVO);			
			}else if(parms[3] == null){
				selectOptionVO.setId(Long.parseLong(parms[0].toString()));
				selectOptionVO.setName(parms[1].toString());
			
				existList.add(selectOptionVO);
				}
			else if(parms[3] != null && parms[3].toString().equals(electionYear.toString())){					
						selectOptionVO.setId(Long.parseLong(parms[0].toString()));
						selectOptionVO.setName(parms[1].toString());
						deleteList.add(selectOptionVO);				
			}			
		}		
		return constituencyVO;
	}
	
	
	
	/*
	 * This method takes District Id as input and retrives all the mandals that
	 * are present in that particular district. And it sets the constituencies names and 
	 * corresponding Id's in the Data transfer Object.
	 */	
	@SuppressWarnings("unchecked")
	public List<MandalVO> getMandalsForDistrict(Long districtId) {
		List tehsil =  tehsilDAO.findTehsilsByDistrict(districtId);
		List<MandalVO> mandal=new ArrayList<MandalVO>();
		if(log.isDebugEnabled())
			log.debug("Entered into getMandalsForDistrict method....");
		try{
			for(int i=0;i<tehsil.size();i++){
				Object[] result = (Object[])tehsil.get(i);
				MandalVO objVO = new MandalVO();
				if(log.isDebugEnabled())
					log.info("Mandal Name--->"+result[1].toString());
						objVO.setName(result[1].toString());	
			
				if(log.isDebugEnabled())
					log.info("Mandal Id--->"+(Long)result[0]);
					objVO.setId((Long)result[0]);
				mandal.add(objVO);
			}
		}catch(Exception ex){
			log.debug("Exception Raised -->" + ex);
			return null;
		}		
		return mandal;
	}
}
