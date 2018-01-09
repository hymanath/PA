package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Panchayat;

public interface IPanchayatDAO extends GenericDao<Panchayat,Long>{
	
	public List<Object[]> getPanchayatsByTehsilId(Long tehsilId);
	
	public List<Object[]> getPanchayatsByConstituencyId(Long constituencyId);
	
	public List<Object[]> getPanchayatsBymandalId(Long mandalId);

	//public Long getPanchayatiesCount(Long id,String type);
	
	public List<Object[]> getPanchayatiesCount(Long id,String type,Long publicationId);
	
	public List<Long> getPanchayatIdsByTehsilId(Long tehsilId);
	
	public List<Object[]> getPanchayatIdsByMandalIdsList(List<Long> mandalIdsList);
	
	public List<Long> getPanchayatIdsBytehsilIdsList(List<Long> mandalIdsList);
	public List<Object> getPanchayatsBymandalId1(Long mandalId);
	
	public List<Long> getPanchayatIdsListByMandalId(Long mandalId);
	
	public List<Object[]> getPanchayatsInAMandal(Long id);
	
	public List<Object[]> getAllPanchaytesInAConstituency(List<Long> ids) ;
	
	public List<Object[]> getAllPanchayatsInAListOfMandals(List<Long> ids);
	
	public List<Object[]> getPanchayatsByPanchayatIdsList(List<Long> panchayatIdsList);
	
	public String getPanchayatNameById(Long panchayatId);
	
	public List<Object[]> getPanchaytsForConstituencyList(List<Long> maldalIds);
	
	public List<Long> getPanchayatsByMangals(List<Long> tehsilIds);
	
	public List<Object[]> getCasteInPanchayat(List<Long> panchayatIds);
	
	public List<Object[]> getPanchayatDetails();
	
	public List<Object[]> getMandalsAnaPanchayays(List<Long> mandalIds);
	
	public List<Object[]> getPanchayatIdsForMandals(List<Long> mandalIdsList,Set<Long> panchayatIds);
	
	public List<Object[]> getMandalAndPanchayatIds(Set<Long> partialPanchayatIds);
	
	public List<Object[]> getPanchayatsByPanchayatIdsListAlongMandal(List<Long> panchayatIdsList);
	
	public List<Long> getPanchayatIdsForDelemationEffect(Long constituencyId,Long year);
	
	public List<Object[]> getPanchayatsAndMandalsForPanchayatIdsList(List<Long> panchayatIdsList);
	
	public List<Object[]> getPanchayatNamesByIds(List<Long> panchayatIds);
	
	public List<Object[]> getAllPanchayatsInMandals(List<Long> ids);
	
	public List<Object[]> getAllPanchayatsWithTehsilIdsInMandals(List<Long> ids);
	public List<Object[]> getAllPanchayatsInMandalsByPublciationId(Long constituencyId,List<Long> ids,Long publicationId);
	public Long getPanchayatIdByTehsilIdAndPanchayatName(Long tehsilId,String panchayatName);
	public List<Object[]> getAllPanchayatList(Long stateId);
	public List<Object[]> getPanchayatList(Long panchatId);

	public Long getHamletCountOnPanchayatIds(List<Long> tehsilIds);
	public Long getBoothIdsCount(Long locationTypeId, List<Long> constituencyIds,Long publicationDateId);
	public List<Object[]> getAllPanchayatsInMandalsList(List<Long> ids);
	public List<Long> getPanchayatInfoOfTehsilByPanchayatName(Long tehsilId,String panchayatName);
}
