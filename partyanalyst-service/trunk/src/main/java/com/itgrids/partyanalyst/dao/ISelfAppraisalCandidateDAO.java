package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalCandidate;

public interface ISelfAppraisalCandidateDAO extends GenericDao<SelfAppraisalCandidate, Long> {
	public List<Object[]> getCandiateList(Long designationId);
	public Object[] getCandiateDetailsByCandidateId(Long candiateId);
	public Long getCandidateId(Long tdpCadreId,Long designationId);
	public List<Object[]> getTotalLeadersDesignationBy(List<Long> desigId);
	public List<Long> getCandidateList(Long desigId);
	public List<Object[]> getMemberAccessLevelIdsAndValue();
	public List<Object[]> getCandidateIdsAndDesignationByActivityMemberIds(List<Long> activityMemberIds);
	public List<Long> getCandidateIdOfCadre(Long tdpCadreId,Long designationId);
	public List<Object[]> getDesignationsList(Long tdpCadreId);
	public List<Object[]> getSelfAppraisalCandidateIdAndDesignationByTdpCadreId(Long tdpCadreId);
	public Long getTdpCadreId(Long selfAppraisalCandiateId);
	public List<Long> getDesignationIdsList(Long tdpCadreId);
	public List<Object[]> getCandidateInfoOfDesginations(Long tdpCadreId,Set<Long> designationIds);
	public List<Object[]> getTourMemberDetails();
}
