package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;


public interface ITdpCadreFamilyInfoDAO extends GenericDao<TdpCadreFamilyInfo, Long>{
	public Integer deleteFamilyInfoByCadre(Long tdpCadreId);
	public List<TdpCadreFamilyInfo> getCadreFamilyDetailsBytdpCadreId(Long tdpCadreId);
}
