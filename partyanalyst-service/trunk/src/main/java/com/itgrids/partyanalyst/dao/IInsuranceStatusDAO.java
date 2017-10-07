package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CadreInsuranceInputVO;
import com.itgrids.partyanalyst.model.InsuranceStatus;

public interface IInsuranceStatusDAO extends GenericDao<InsuranceStatus, Long>{

	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaints(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate);
	public List<Object[]> getAllInsuranceCompanies();
	public List<Long> getStatusAndInsuranceCompanyWiseComplaintIds(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate,
			List<Long> statusIds,Long companyId,String issueType);
	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaintDetails(List<Long> complaintIds);
	public List<Object[]> getLocationWiseComplaintCntBasedOnUserAccessLevel(CadreInsuranceInputVO inputVO);
	public List<String> getAllIssueType(Long stateId);
	public List<Object[]> getAllGrievanceInsuranceStatus();
	public List<Object[]> getLatestComplaintResponsesForComplaintIds(List<Long> complaintIds); 
	public List<Object[]> getDistrictWiseThenCategoryWiseInsuranceMemberCount(Long levelId, Set<Long> locationValuesSet, Long userTypeId, Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type,String filter);
	public List<Object[]> getConstituencyWiseThenCategoryWiseInsuranceMemberCount(Long levelId, Set<Long> locationValuesSet, Long userTypeId, Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type,String filter);
	public List<Object[]> getLocationWiseComplaintAndBenefitMemberCntBasedOnUserAccessLevel(CadreInsuranceInputVO inputVO,String resultType);
	
	public List<Object[]> getAllStatusDetailsByComplaint(Long complaintId,String type);
	public List<Object[]> getInsuranceStatus();
	public List<String> getStatusBycomplaintIdForInsurance(Long complaintId);
	public List<Object[]> getComplaintScanCopies(Long complaintId);
	public List getScanCopyForComplaint(Long complaintId);
	public List<Object[]> getSubjectAndDescForComplaint(Long complaintId);
	public List<Object[]> getRemarks(Long complaintId);
	public List<Object[]> getComplaintResponsesByComplaintId(Long complaintId);
	public List<Object[]> getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type, String locationType,String filter);
	public List<Object[]> getConstituencyWiseInsuranceStatusCounts(Date fromDate,Date toDate,Long locationTypeId,List<Long> locationValues,String year,Long yearId);
    public List<Object[]> getGrivenceTrustStatusCounts(Date fromDate,Date toDate,Long locationTypeId,List<Long> locationValues,String year,Long yearId);
	public List<String> getGrivenceStatus();
	public List<Object[]> grievanceInsuranceStatusId();
	
	public List<Object[]> getConstituencyWiseInsuranceWiseIssueTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId);
	public List<Object[]> getAllTypeOfIssues();
	public List<Object[]> getGrivenceIssueTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) ;
	public List<Object[]> getLevelValuesByLevel(Long locationTypeId,List<Long> locationValues);
	public List<Object[]> getGrievanceTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId);
	public List<Object[]> getInsuranceTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId);
	public List<Object[]> getNTRTrustTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId);
	public List<Object[]> getLocationWiseGrivenceTrustIssueTypesCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId);
}
