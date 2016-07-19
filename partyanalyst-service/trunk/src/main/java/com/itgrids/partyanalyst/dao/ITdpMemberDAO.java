package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.model.TdpMember;

public interface ITdpMemberDAO extends GenericDao<TdpMember, Long>{
	 public List<Object[]>  searchTdpMemberByCriteria(String searchType,String searchValue,LocationInputVO locationVo);

}
