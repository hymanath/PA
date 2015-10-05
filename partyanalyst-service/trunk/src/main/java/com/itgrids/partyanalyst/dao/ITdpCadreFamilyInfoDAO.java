package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;


public interface ITdpCadreFamilyInfoDAO extends GenericDao<TdpCadreFamilyInfo, Long>{
	public Integer moveFamilyInfoToHistoryByCadre(Long tdpCadreId);
	public List<TdpCadreFamilyInfo> getCadreFamilyDetailsBytdpCadreId(Long tdpCadreId);
	public List<TdpCadreFamilyInfo> getCadresFamilyDetailsBytdpCadreIdList(Set<Long> tdpCadreIdsSet);
	public List<Long> getFamilyUpdatedOrNot(List<Long> tdpCadreIds);
	public List<Object[]> getWhatsAppAndFbDetailsOfCadre(Long tdpCadreId);
}
