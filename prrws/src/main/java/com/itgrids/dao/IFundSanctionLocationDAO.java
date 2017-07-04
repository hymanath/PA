package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.FundSanctionLocation;

public interface IFundSanctionLocationDAO extends GenericDao<FundSanctionLocation,Long> {
	public List<Object[]> getLocationWiseFundSanctionDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			Date sDate,Date eDate,Long locationScopeId,List<Long> searchLvlVals,List<Long> schmeIdsList );
	public List<Object[]> getALlProgramesAmountDetails(InputVO inputVO,Date sDate,Date eDate);

}
