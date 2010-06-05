package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Assert;
import org.junit.Test;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;

public class PoliticalChangesInformationSourceDAOHibernateTest extends
		BaseDaoTestCase {

	private PoliticalChangesInformationSourceDAO politicalChangesInformationSourceDAO;

	public PoliticalChangesInformationSourceDAO getPoliticalChangesInformationSourceDAO() {
		return politicalChangesInformationSourceDAO;
	}

	public void setPoliticalChangesInformationSourceDAO(
			PoliticalChangesInformationSourceDAO politicalChangesInformationSourceDAO) {
		this.politicalChangesInformationSourceDAO = politicalChangesInformationSourceDAO;
	}
	
	@Test
	public void testGetBySourceId(){
		List<PoliticalChangesInformationSource> source = politicalChangesInformationSourceDAO.findBySourceId(1l);
		Assert.assertEquals(source.size(), 0);
	}
	
	@Test
	public void testGetAllSources(){
		List<PoliticalChangesInformationSource> source = politicalChangesInformationSourceDAO.getAll();
		List<SelectOptionVO> sourcesIdsAndNames = new ArrayList<SelectOptionVO>();		
		for(PoliticalChangesInformationSource sources : source){
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(sources.getSourceId());
			selectOptionVO.setName(sources.getSourceName());
			sourcesIdsAndNames.add(selectOptionVO);
		}
	}	
}
