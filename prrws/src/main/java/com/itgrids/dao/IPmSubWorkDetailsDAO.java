package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.PmSubWorkDetails;

public interface IPmSubWorkDetailsDAO extends GenericDao<PmSubWorkDetails, Long> {
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId);
	public List<Object[]> getAllDistricts(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> subjtIds,List<Long> statIds);
	public List<Object[]> getAllConstituenciesByDistricId(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds);
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds);
	public List<Object[]> getDepartmentsByWorks(List<Long> deptIds ,Date startDate,Date endDate,List<Long> statusIds);
	
	public List<Long> getPmSubWorkDetailsIds(Long petitionId);
	public int updatePmsubWorkDetails(List<Long> subWorkDetailsIds,Date updateTime,Long userId);
	public List<Object[]> getCompleteOrStatusOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,String type);
	public List<Object[]> getLeadWiseOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate);
	public List<Long> getPetitionsSubWorksIdsList(List<Long>  petitionIdsList);
	public int updatePetitionSubWorkStatusdetails(List<Long>  petitionIdsList,Date updatedDate,Long updatedUserId,Long pmStatusId);
	public List<Object[]> getPetitionsDetailedSubWorksIdsList(List<Long>  petitionIdsList);
	public List<Object[]> getSubjectsForSearchPage(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusId,Long subjectId);
	public List<Object[]> getReferralWiseOverviewDetails(InputVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getPmBriefLeadIds(List<Long> deptIds);
	
	
}
