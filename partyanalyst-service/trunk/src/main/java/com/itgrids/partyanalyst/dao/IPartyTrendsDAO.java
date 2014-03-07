/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 3, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.entity.Country;
import com.itgrids.partyanalyst.model.PartyTrainingCamps;
import com.itgrids.partyanalyst.model.PartyTrends;


/**
 * Interface for CountryDAO.
 * 
 * @author <a href="mailto:shan.amrita@gmail.com">Shan Nagarajan</a>
 */

public interface IPartyTrendsDAO extends GenericDao<PartyTrends, Long> {
	
	public List<?> loadConst();
	public List<?> loadEntitiesForXl(List<Long> constIds );
	public List<Object[]> findAssemblyConstituenciesForSimaAndra(Long electionYear,Long staetId,List<String> areas,List<Long> districIds);
	public List<?> loadConst(List<Long> constIds);
	public List<Long> getConstituencyIds();
	public List<PartyTrends> getAllTrends(Long constiId);
    public List<Object[]> getPreviousTrendsData(Long constId);
    public List<Object[]> getPreviousForPrp(Long constId);
    public List<Object[]> getParliamentCountForPrpAndYsr(Long constId);
    public List<Object[]> getParliamentCountForInc(Long constId);
    public List<?> getPreviousTrendsData(List<Long> partyIds,Long constId);
    public List<?> getPreviousTrendsDataForParleament(List<Long> partyIds,Long constId);
    public List<Object[]> getTotalVotersForConst(Long constId);







}