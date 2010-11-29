package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.CadreFamilyMemberInfo;

public interface ICadreFamilyMemberInfoDAO extends GenericDao<CadreFamilyMemberInfo, Long>{
	
	public List<Object[]> findByCadreId(Long CadreId);
	public Integer deleteFamilyMemberDetailsByCadre(Long cadreId);

}
