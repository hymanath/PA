package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public interface IPanchayatHamletDAO extends GenericDao<PanchayatHamlet,Long>{
	
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId);
	public List<Panchayat> getPanchayatByHamletId(Long hamletId);
	public List<Long> getHamletsOfPanchayitis(List<Long> panchayitIds);
	public List<Object> getHamletsCountOfAPanchayat(Long panchayatId);
	public List<Object[]> getHamletsByPanchayatsList(List<Long> panchayatsList);
	public List<Object> getAssignedVotersCountDetails(Long panchayatId);
	
	public List<Object[]> getHamletsListByConstituencyId(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getPanchayatsListByMandalIdsList(List<Long> mandalIdsList);
	
	public List<Long> getHamletsCountByLocationId(String type,Long constituencyId,Long id,Long publicationDateId);

	public List<Object[]> getHamletDetailsByPanchayatIds(List<Long> panchayatIds,Long publicationId,Long userId);
	
	public List<Object[]> getHamletsOfPanchayats(List<Long> panchayitIds);
	
	public List<Object[]> getHamletCount(List<Long> locationIdsList,String tempVar);
	
	public List<Object[]> gethamletsInAState(Long stateId);
	
	public List<Object[]> getHamletDetailsByPanchayatsList(List<Long> panchayatsList);
	
	public List<Object[]> getAllHamletsOfPanchayats(List<Long> panchayitIds);
	
	public List getHamletByPanchayatId(Long panchayatId);
}
