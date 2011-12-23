package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.Panchayat;

public interface IPanchayatDAO extends GenericDao<Panchayat,Long>{
	
	public List<Object[]> getPanchayatsByTehsilId(Long tehsilId);

}
