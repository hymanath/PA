package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamAnnualIncome;

public interface IMeekosamAnnualIncomeDAO  extends GenericDao<MeekosamAnnualIncome, Long>{
	public List<Object[]> getMeekosamAnnualIncomeList();

}
