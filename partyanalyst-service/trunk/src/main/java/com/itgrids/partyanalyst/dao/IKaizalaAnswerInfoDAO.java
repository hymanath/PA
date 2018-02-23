package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaAnswerInfo;
import com.itgrids.partyanalyst.model.KaizalaAnswers;

public interface IKaizalaAnswerInfoDAO extends GenericDao<KaizalaAnswerInfo, Long> {
	public List<Object[]> getConstituencyWiseTargetList(Long stateId,String location);
	public List<Object[]> getConstituencyWiseInstalledCommitteeMembers(Long stateId,String location);
	public List<Object[]> getConstituencyWiseNotHavingSmartPhone(Long stateId,String location);
	public List<Object[]> getConstituencyWisePublicInstalled(Long stateId,String location);
	public List<Object[]> getConstituecnyWiseCadreInstalled(Long stateId,String location);
	public Long getTotalTarget(Long stateId);
	public Long getTotalInstalled(Long stateId);
	public Long getNotHavingSmartPhone(Long stateId);
}