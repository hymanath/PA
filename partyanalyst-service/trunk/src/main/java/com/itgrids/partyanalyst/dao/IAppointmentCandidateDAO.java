package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.UserAddress;

public interface IAppointmentCandidateDAO extends GenericDao<AppointmentCandidate, Long> {

 public	List<Object[]> getAppointmentCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId, Long appntmntSttsId,Integer crrntMnth);
 public	List<Object[]> getAppCandidatePreviousCountDetails(Long tdpCadreId,String mobileNO,Integer crrntMnth);
 public	List<Object[]> getAppCandidatePreviousRequestedDetails(Long tdpCadreId,String mobileNO,Integer crrntMnth);
 public Object getMaxDate(Long tdpCadreId,String mobileNo,Integer crrntMnth);
 public List<Object[]>  searchAppointmentRequestedMember(String searchType,String searchValue);
 public List<UserAddress> getUserWorkAddress(Long id);
 public List<Object[]> advancedSearchAppointmentMembersForCadreCommittee(String searchType,LocationInputVO locationVo,String locationType,LocationInputVO inputVo);
 //public List<Object[]>  advancedSearchAppointmentRequestedMembersForPublicRepresentative(String searchType,Long searchValue);
 public List<Object[]>  advancedSearchAppointmentRequestedMembersForPublicRepresentative(String searchType,LocationInputVO locationVo,LocationInputVO inputVo);
 public List<Object[]> getAppointmentCandidateIdForCadreIds(List<Long> cadreIds,Long aptUserId);
 public List<AppointmentCandidate> getAppointmentCandidateObjByMemship(String membershipId);
 public List<Object[]>  getPublicRepresentativeWiseAppointmentCnt(List<Long> statusIds,String type,Long apointmntCandateId);
 public List<Object[]>  getPublicRepresentativeWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId);
 public List<Object[]>  getCommitteeWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId);
 public List<Object[]>  getCommitteeMemROleWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId,Long levelId);
 public Long appointmntCandExist(String memberShipNo);
 public Long appointmntCandExistForVtrId(String voterId);
 public Long todayAppointmentCandidateCount(Long userId,Date insertedDate,Date endDate);
 public List<Object[]> appointmentCandidateDetails(Date fromDate,Date toDate,Long userId);
 public Long getAppointmentCandUniqueCount(Long userId,Date fromDate,Date endDate);
 public Long todayAppointmentCandUniqueCount(Long userId,Date insertedDate,Date endDate);
 
}
