package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	
	
	
}
