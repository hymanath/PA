package com.itgrids.service;

import java.util.List;

public interface IFundSanctionMatrixReportService {
	public void calculateFinancialWiseMatrxReportDetail(List<Long> financialYearIdsList, List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId);
}
