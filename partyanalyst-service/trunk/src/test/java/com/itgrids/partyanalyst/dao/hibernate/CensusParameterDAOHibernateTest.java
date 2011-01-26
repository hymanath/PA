package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICensusParameterDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CensusParameter;

public class CensusParameterDAOHibernateTest extends BaseDaoTestCase{
	
	public ICensusParameterDAO censusParameterDAO;

	public void setCensusParameterDAO(ICensusParameterDAO censusParameterDAO) {
		this.censusParameterDAO = censusParameterDAO;
	}

	public void test()
	{
		List<CensusParameter> list = censusParameterDAO.getAll();
		List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
		
		for(CensusParameter censusParameter:list)
		{
			SelectOptionVO optionVO = new SelectOptionVO();
			optionVO.setId(censusParameter.getCensusParameterId());
			optionVO.setName(censusParameter.getParameterName());
			
			selectOptionVOList.add(optionVO);
		}
		
		for(SelectOptionVO selectOptionVO:selectOptionVOList)
		{
			System.out.println("=="+selectOptionVO.getId()+")==="+selectOptionVO.getName());
		}
		
	}
}
