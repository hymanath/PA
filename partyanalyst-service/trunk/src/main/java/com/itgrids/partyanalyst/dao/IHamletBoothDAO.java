package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HamletBooth;

public interface IHamletBoothDAO extends GenericDao<HamletBooth, Long>{

	public Integer deleteHamletBoothsByBoothIdsList(List<Long> boothIdsList);
	
	public List<Object[]> getVotersCountForHamletBooth(Long userId,Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getFamiliesCountByHamletBoothIdsList(Long userId,Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getAgeWiseHamletBoothList(Long userId,Long constituencyId,Long publicationDateId,Long ageFrom,Long ageTo);
	
	public List<Object[]> getVoterFamilyInfoForHamletBooth(Long userId,Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getCasteWiseVoterDetailsForHamletBooth(Long constituencyId,Long publicationDateId,Long userId);
	
	public List<Object[]> getCastesForHamletBooth(Long constituencyId,Long publicationDateId,Long userId);
	
}
