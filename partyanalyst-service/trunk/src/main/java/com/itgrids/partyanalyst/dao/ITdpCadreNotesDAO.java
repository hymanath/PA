package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreNotes;

public interface ITdpCadreNotesDAO extends GenericDao<TdpCadreNotes, Long>{
	public List<Object[]> getCadreNotesInformation(Long tdpCadreId,Integer startIndex,Integer maxIndex,Long userId);
	public Integer updateCadreNotesInformationData(Long cadreNotes);
	public Integer updateCadreNotesAllData(Long primaryTdpCadrenotesId);
	public Long getTotalCadreNotesInformation(Long tdpCadreId);
	
}
