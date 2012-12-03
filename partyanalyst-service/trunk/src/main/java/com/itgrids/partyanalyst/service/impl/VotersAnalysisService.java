package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;



public class VotersAnalysisService implements IVotersAnalysisService{
	
	private IBoothDAO boothDAO;
	
	
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}



	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
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
