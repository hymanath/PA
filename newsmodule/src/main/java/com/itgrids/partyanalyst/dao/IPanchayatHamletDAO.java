package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public interface IPanchayatHamletDAO extends GenericDao<PanchayatHamlet,Long>{
	
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId);
	/*
	public List<Panchayat> getPanchayatByHamletId(Long hamletId);
	*/
	public List<Long> getHamletsOfPanchayitis(List<Long> panchayitIds);
	/*
	public List<Object> getHamletsCountOfAPanchayat(Long panchayatId);
	public List<Object[]> getHamletsByPanchayatsList(List<Long> panchayatsList);
	public List<Object> getAssignedVotersCountDetails(Long panchayatId);
	
	public List<Object[]> getHamletsListByConstituencyId(Long constituencyId,Long publicationDateId);
	
	public List<Object[]> getPanchayatsListByMandalIdsList(List<Long> mandalIdsList);
	
*/}
