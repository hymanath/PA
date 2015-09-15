package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;

public interface ITrainingCampScheduleInviteeTrackDAO extends GenericDao<TrainingCampScheduleInviteeTrack, Long>{
	public List<Object[]> getScheduleConfirmationDetails(Long purposeId,Long userId);
	public List<Object[]> getMemberRemarks(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate,List<Long> cadreIds);
	public List<Object[]> getTrackDetailsOfCandidateByCallPurposeWise(Long tdpCadreId,Long programId);
}
