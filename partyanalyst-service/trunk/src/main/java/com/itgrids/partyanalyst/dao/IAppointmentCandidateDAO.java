package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentCandidate;

public interface IAppointmentCandidateDAO extends GenericDao<AppointmentCandidate, Long> {

 public	List<Object[]> getAppointmentCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId, Long appntmntSttsId,Integer crrntMnth);
 public	List<Object[]> getAppCandidatePreviousCountDetails(Long tdpCadreId,String mobileNO,Integer crrntMnth);
 public	List<Object[]> getAppCandidatePreviousRequestedDetails(Long tdpCadreId,String mobileNO,Integer crrntMnth);
 public Object getMaxDate(Long tdpCadreId,String mobileNo,Integer crrntMnth);
	
}
