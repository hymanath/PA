package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InsuranceStatus;

public interface IInsuranceStatusDAO extends GenericDao<InsuranceStatus, Long>{

	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaints(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate);
	public List<Object[]> getAllInsuranceCompanies();
	public List<Long> getStatusAndInsuranceCompanyWiseComplaintIds(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate,
			List<Long> statusIds,Long companyId,String issueType);
	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaintDetails(List<Long> complaintIds);
}
