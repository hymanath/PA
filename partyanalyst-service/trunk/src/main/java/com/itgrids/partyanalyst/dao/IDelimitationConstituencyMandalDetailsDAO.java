/**
 * 
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationConstituencyMandalDetails;

/**
 * @author sys
 * 
 */
public interface IDelimitationConstituencyMandalDetailsDAO extends
		GenericDao<DelimitationConstituencyMandalDetails, Long> {
	public List<Object[]> getConstitencyWiseTehsil(Long constituencyId);
	public List<Long> getAllParliamentMandalByAllLevels(List<Long> locationValues,Long loactionTypeId);
}
