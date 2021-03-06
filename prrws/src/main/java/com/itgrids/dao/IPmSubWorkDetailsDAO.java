package com.itgrids.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PetitionPriorityVO;
import com.itgrids.model.PmSubWorkDetails;

public interface IPmSubWorkDetailsDAO extends GenericDao<PmSubWorkDetails, Long> {
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId,List<Long> userAccesseDeptIds);
	public List<Object[]> getAllDistricts(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> subjtIds,List<Long> statIds,List<Long> leadIdsList,Set<Long> petitionIdsList);
	public List<Object[]> getAllConstituenciesByDistricId(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsList,List<Long> subjtIds,List<Long> leadIdsList);
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList,List<Long> subjtIds,List<Long> leadIdsList);
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
	public List<Object[]> getChildOfficersByParentOfficerId(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusIds,List<Long> pmOfficerid,Set<Long> petitionIdsList,Set<Long> pmOffcrDesigids);
	public List<Object[]> getLocationWiseRepresentationsOverviewDetails(InputVO inputVO,Date fromDate,Date toDate);
	public int updatePetitionSubWorkPriorityDetails(PetitionPriorityVO priorityVO,Date dateTime);

	public List<Object[]> getSubSubjectsBySubjectId(Date fromDate,Date toDate,List<Long> statusIds,List<Long> subSubjectIdsLst,List<Long> deptIdsLst,List<Long> subjectIdsLst ,Set<Long> petitionIdsList);
	public List<Object[]> getAllDistrictsInSubjectWiseSearch(Date fromDate,Date toDate,List<Long> deptIdsList,List<Long> subjectIdsLst,List<Long> subSubjectIdsLst,Set<Long> petitionIdsLst);
	public List<Object[]> getConstituencyBySearchTypeAndDistrictIdInSubSubject(Date fromDate,Date toDate,List<Long> districtIds,String filterType,List<Long> deptIdsList,List<Long> subjectIdsLst,List<Long> subSubjectIdsLst,Set<Long> petitionIdsLst);
}
