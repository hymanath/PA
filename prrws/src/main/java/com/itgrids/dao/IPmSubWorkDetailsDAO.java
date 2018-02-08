package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.PmSubWorkDetails;

public interface IPmSubWorkDetailsDAO extends GenericDao<PmSubWorkDetails, Long> {
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId);
	public List<Object[]> getAllDistricts(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> subjtIds,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllConstituenciesByDistricId(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList);
	public List<Object[]> getDepartmentsByWorks(List<Long> deptIds ,Date startDate,Date endDate,List<Long> statusIds,Set<Long> petitionIdsLst);
	
	public List<Long> getPmSubWorkDetailsIds(Long petitionId);
	public int updatePmsubWorkDetails(List<Long> subWorkDetailsIds,Date updateTime,Long userId);
	public List<Object[]> getCompleteOrStatusOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,String type,Set<Long> petitionsIdsList);
	public List<Object[]> getLeadWiseOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,Set<Long> petitionsIdsList);
	public List<Long> getPetitionsSubWorksIdsList(List<Long>  petitionIdsList);
	public int updatePetitionSubWorkStatusdetails(List<Long>  petitionIdsList,Date updatedDate,Long updatedUserId,Long pmStatusId);
	public List<Object[]> getPetitionsDetailedSubWorksIdsList(List<Long>  petitionIdsList);
	public List<Object[]> getSubjectsForSearchPage(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusId,Long subjectId,Set<Long> petitionIdsList);
	public List<Object[]> getReferralWiseOverviewDetails(InputVO inputVO,Date startDate,Date endDate,Set<Long> petitionsIdsList);
	public List<Object[]> getPmBriefLeadIds(List<Long> deptIds);
	public List<Object[]> getAllWorksLatesStatusDetails(Long petitionId);
	public Object[] getMaxEndorsementAndTempEndorsementNos();
	public int saveTempEndorseNo(Long petitionId,List<Long> subWorkDetailsIds,String tempEndorsNo,Long userId,Date updateTime);
	public String getMaxEndirseNoAndValidatingEndorseNo(String endorseNo);
	
}
