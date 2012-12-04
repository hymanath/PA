package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;



public class VotersAnalysisService implements IVotersAnalysisService{
	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);

	
	private IBoothDAO boothDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
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

	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName) {

		if (log.isDebugEnabled())
			log.debug("Excecuting getVoterDetails() method in RegionServiceDataImp service");

		List<VoterVO> voters = null;
		List<Voter> votersList = null;
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
				 
				 totalCount = (Long) boothPublicationVoterDAO.getVotersCountForPanchayatByPublicationId(panchayatId,publicationDateId).get(0);
				
			}
	
				if (votersList != null && votersList.size() > 0)
					voters = new ArrayList<VoterVO>();
	
				Long count = new Long(startIndex);
				
				for (Voter voter : votersList) {
	
					VoterVO voterVO = new VoterVO();
					
					voterVO.setVoterId((++count)+"");
					voterVO.setFirstName(voter.getFirstName());
					voterVO.setAge(voter.getAge());
					voterVO.setGender(voter.getGender());
					voterVO.setHouseNo(voter.getHouseNo());
					voterVO.setRelativeFirstName(voter.getRelativeFirstName());
					voterVO.setCast(voter.getCast());
					voterVO.setCastCatagery(voter.getCastCatagery());					
	
				}
				
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
	 */
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId)
	{
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(); 
			
		SelectOptionVO selectOptionVO = null;
		List<Object[]> publicationDetails = boothDAO.getPublicationDetailsBasedOnConstituency(constituencyId);
		
		if(publicationDetails != null && publicationDetails.size() > 0)
		{
			for(Object[] publicationDetail : publicationDetails)
			{
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)publicationDetail[0]);
				selectOptionVO.setName((String)publicationDetail[1]);
				selectOptionVOList.add(selectOptionVO);
			}
		}
		return selectOptionVOList;
	}
	
}
